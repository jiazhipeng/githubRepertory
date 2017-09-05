package com.hy.gcar.service.memberInfo.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hy.gcar.entity.*;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hy.constant.Constant;
import com.hy.gcar.service.Approve.ApproveService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberAppService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoAppService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.utils.RandomNumberUtil;


@Service
public class MemberInfoAppServiceImpl implements MemberInfoAppService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MemberAppService memberAppService;
	
	@Autowired
	MemberInfoService memberInfoService;

	@Autowired
	MemberService memberService;

	@Autowired
	MemberItemService memberItemService;

	@Autowired
	MemberItemShareService memberItemShareService;

	@Autowired
	ApproveService approveService;
	
	@Autowired
	WechatMessageService wechatMessageService;

	@Autowired
	BasicNoticeService basicNoticeService;

	@Override
	public MemberInfo upload(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
        //取得request中的所有文件名  
        Iterator<String> iter = multiRequest.getFileNames();  
        MemberInfo memberInfo = new MemberInfo();
        while(iter.hasNext()){  
	        //取得上传文件  
	        MultipartFile file = multiRequest.getFile(iter.next());  
	        if(file == null){
	        	continue;
	        }
            //取得当前上传文件的文件名称  
        	String name = file.getName();
            String myFileName = file.getOriginalFilename();  
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            if (StringUtils.isEmpty(myFileName)){
            	continue;
            }
            if(StringUtils.isEmpty(myFileName.trim())){
            	continue;
            }
            //重命名上传后的文件名  
            String fileName =  DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS")  + RandomNumberUtil.getRandNumber(4)+ file.getOriginalFilename();
            //定义上传路径  
//          String path = request.getSession().getServletContext().getRealPath(File.separator+"static"+File.separator+"gcar"+File.separator+"images"+File.separator+"memberInfo")+File.separator + fileName;  
            String path = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path")+"/memberInfo";
            String savePath = Constant.APPLICATION_LOAD.getProperty("picture_database_path")+"/memberInfo"+"/" + fileName;
			memberAppService.saveFileFromInputStream(file.getInputStream(), path, fileName);
            setMemberInfo(memberInfo, name, savePath);
          }
        return memberInfo;
	}
	
	
	private void setMemberInfo(MemberInfo memberInfo, String name, String savePath) {
		switch (name) {
		case "idcardFront":
			memberInfo.setIdcardFront(savePath);
			break;
		case "idcardBack":
			memberInfo.setIdcardBack(savePath);
			break;
		case "drivercardOriginal":
			memberInfo.setDrivercardOriginal(savePath);
			break;
		case "drivercardCopy":
			memberInfo.setDrivercardCopy(savePath);
			break;
		default:
			break;
		}
	}


	@Override
	public MemberInfo savePicture(MemberInfo memberInfo) throws Exception {
		MemberInfo memInfo = memberInfoService.findMemberInfoByMemberID(memberInfo.getMemberId());
		if (memInfo == null) {
			//执行新增
			memInfo = memberInfoService.createMemberInfo(memberInfo);
		}else{
			//执行修改
			memberInfo.setId(memInfo.getId());
			memInfo = memberInfoService.updateMemberInfo(memberInfo);
			//修改会员权益
		}
		//	请求来源0:个人中心1用车发起 新增审核记录
		if(memberInfo.getFlag().equals("1")){
			Member m = memberService.findMemberByID(memberInfo.getMemberId());
			Approve approve = new Approve();
			approve.setMemberId(m.getId());
			//新增
			approve = this.insertApprove(memInfo,m);
			updateUseCarApproved(m);
			//给审核人员发送消息推送
			this.wechatMessageService.sendMessageByApproveCreate(approve);
		}

		return memInfo;
			
	}

	/**
	 * 修改用户资料审核状态
	 * @param m
	 * @throws Exception
	 */
	public void updateUseCarApproved(Member m) throws Exception {
		Member mupdate = new Member();
		mupdate.setId(m.getId());
		mupdate.setUseCarApproved(0);
		memberService.updateMember(mupdate);
	}

	/**
	 * 新增 td_approve
	 * @param memberInfo
	 * @param m
	 * @throws Exception
	 */
	public Approve insertApprove(MemberInfo memberInfo,Member m) throws Exception {
		//新增个人信息审核表
		Approve approve = new Approve();
		approve.setMemberId(memberInfo.getMemberId());
		MemberItem memberItem = new MemberItem();
		memberItem.setMemberId(memberInfo.getMemberId());
		memberItem = memberItemService.selectMemberItem(memberItem);
		if(memberItem != null){
			approve.setMemberItemId(memberItem.getId());
			approve.setMemberItemName(memberItem.getItemName());
		}
		approve.setMemberName(m.getName());//会员名称
		approve.setMemberMobile(m.getMobile());//会员手机号
		MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(m.getId());
		//会员身份：0，共用人；1，主共用人
		if(null==memberItemShare){
			approve.setMembership(null);
		}
		if(null!=memberItemShare){
			if(1==memberItemShare.getIsMain()){
				approve.setMembership(1);
			}else{
				approve.setMembership(0);
			}
			approve.setMemberItemId(memberItemShare.getMemberItemId());
			MemberItem memberItem_new = memberItemService.findById(memberItemShare.getMemberItemId());
			if(null!=memberItem_new){
				approve.setMemberItemName(memberItem_new.getItemName());
			}
		}
		approve.setApproveType(0);//审核项目：0：用车申请；1：入会申请；2：升级申请
		approve.setStatus(0);//审核状态：0：待审核；1：复核申请；2：驳回申请；3：通过审核
		approve.setCreated(new Date());//提交审核时间

		Member member = this.getOperater(3);
		if(member != null){
			approve.setOperaterId(member.getSysuserId());//审核人ID
			approve.setOperaterName(member.getSysuserName());//审核人姓名
		}
		approve.setIdcardFront(memberInfo.getIdcardFront());//身份证正面
		approve.setIdcardBack(memberInfo.getIdcardBack());//身份证反面
		approve.setDrivercardOriginal(memberInfo.getDrivercardOriginal());//驾驶证正本
		approve.setDrivercardCopy(memberInfo.getDrivercardCopy());//驾驶证副本
		approve.setIsLog(0);//是否为日志记录：0，不是日志；1日志；
		approveService.insertSelective(approve);
		
		return approve;
	}

	public Member getOperater(Integer type) throws Exception {
		//给后台基础设置-通知管理设置的通知人发送微信以及app通知
		BasicNotice b = new BasicNotice();
		//查询正常的状态
		b.setStatus(0);
		//查询正常的状态
		b.setNoticeType(type);
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
		long memberId;
		Member member_notice = new Member();
		if(CollectionUtils.isNotEmpty(basicNotices)){
			b = basicNotices.get(0);
			memberId = b.getNoticeUserId();
			member_notice = this.memberService.findMemberByID(memberId);
			return member_notice;
		}
		return null;
	}

}
