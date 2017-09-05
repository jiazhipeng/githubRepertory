package com.hy.gcar.service.member.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.ImageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.common.utils.AccessTokenUtils;
import com.hy.constant.Constant;
import com.hy.gcar.constant.MemberType;
import com.hy.gcar.dao.MemberMapper;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.member.MemberService;
import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.multiMedia.WiXinMultiMedia;
import com.hy.weixin.utils.WeiXinUtils;

@Service(value = "tdMemberService")
public class MemberServiceImpl implements MemberService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberMapper<Member> tdMemberMapper;

	@Override
	public Member createCompanyMember(Member member) throws Exception {
		Assert.notNull(member, "企业会员对象不能为空");
		Assert.notNull(member.getOrgId(), "组织机构代码不能为空");
		Assert.notNull(member.getOrgName(),"联系人不能为空");
		Assert.notNull(member.getOrgTel(),"联系电话不能为空");
		member.setType(MemberType.MEMBER_TYPE_1.getIndex());
		member.setCreated(new Date());
		tdMemberMapper.insertSelective(member);
		return member;
	}

	@Override
	public Member createPersonalMember(Member member) throws Exception {
		Assert.notNull(member, "个人会员对象不能为空");
		Assert.notNull(member.getName(), "会员名称不能为空");
		//Assert.notNull(member.getMobile(),"会员电话不能为空");
		member.setType(MemberType.MEMBER_TYPE_0.getIndex());
		member.setCreated(new Date());
		tdMemberMapper.insertSelective(member);
		return member;
	}

	@Override
	public Member updateCompanyMember(Member member) throws Exception {
		tdMemberMapper.updateByPrimaryKeySelective(member);
		return tdMemberMapper.selectByPrimaryKey(member.getId());
	}

	@Override
	public Member updatePersonalMember(Member member) throws Exception {
		tdMemberMapper.updateByPrimaryKeySelective(member);
		return tdMemberMapper.selectByPrimaryKey(member.getId());
	}

	@Override
	public List<Member> findMember(Member member) throws Exception {
		return tdMemberMapper.selectByCondition(member);
	}

	@Override
	public Member findMemberByID(Long memberID) throws Exception {
		Assert.notNull(memberID, "会员id不能为空");
		Member member = new Member();
		member.setId(memberID);
		List<Member> members = tdMemberMapper.selectByCondition(member);
		if (CollectionUtils.isNotEmpty(members)){
			return members.get(0);
		}
		return null;
	}

	@Override
	public Member findMemberByOpenID(String openID) throws Exception {
		Assert.notNull(openID, "openid不能为空");
		Member member = new Member();
		member.setOpenid(openID);
		List<Member> members = tdMemberMapper.selectByCondition(member);
		if (CollectionUtils.isNotEmpty(members)){
			return members.get(0);
		}
		return null;
	}

	@Override
	public List<Member> findSaleMemberList() throws Exception {
		Member member = new Member();
		member.setIsSelles(1);
		return tdMemberMapper.selectByCondition(member);
	}
    
	@Override
	public Member memberLoginFromWechat(Member member) throws Exception {
		// 微信公众号会员登录专用方法（如会员首次登录，自动完成注册） 此方法除验证手机号和动态密码外，需要验证当前微信openid是否与关注时的openid相同，不同则不能登录
		//1.验证动态密码
		//2.验证手机号、openid关联
		boolean isSuccess = true;
		String message = "登陆成功";
		
		String basePath = member.getImageQrcode();
		String mobile = member.getMobile();
		String openid = member.getOpenid();
		String name = member.getName();
		if(StringUtils.isBlank(name)){
			name = "极车公社会员";
		}
		
		member.setOpenid(openid);
		member.setMobile(null);
		member.setName(null);
		member.setImageQrcode(null);
		List<Member> memberList1 = tdMemberMapper.selectByCondition(member);
		if(!CollectionUtils.isEmpty(memberList1)){//存在用户
			logger.info("memberList1" + JSONObject.toJSONString(memberList1));
			Member member1 = memberList1.get(0);//openi用户
			String mobile0 = member1.getMobile();
			//是否绑定手机号
			if(StringUtils.isBlank(mobile0)){//当前openId未绑定手机号
				logger.info("mobile0" + mobile0);
				//当前手机号是否绑定openId
				Member member2 = new Member();
				member2.setMobile(mobile);
				List<Member> memberList2 = tdMemberMapper.selectByCondition(member2);
				
				if(CollectionUtils.isEmpty(memberList2)){//当前手机号没有绑定 即 当前手机号下不存在用户
					member2.setId(member1.getId());
					member =updatePersonalMember(member2);
				}else{
					Member member3 = memberList2.get(0);//mobile用户
					String openid3 = member3.getOpenid();
					if(StringUtils.isBlank(openid3)){//当前手机号下已经存在用户，判断用户是否绑定openId
						tdMemberMapper.deleteByPrimaryKey(member1.getId());
						Member member4 = new Member();
						member4.setId(member3.getId());
						member4.setOpenid(openid);
						member4.setFocusType(member1.getFocusType());
//						tdMemberMapper.updateByPrimaryKeySelective(member4);
						member=updatePersonalMember(member4);
					}else{
						isSuccess = false;
						message = "当前手机号已被其他微信绑定";
					}
					
				}
			}else if(!mobile0.equals(mobile)){//当前openId绑定了手机号,手机号不一致
				logger.info("mobile0----" + mobile0);
				logger.info("mobile----" + mobile);
				isSuccess = false;
				message = "当前微信已被其他手机号绑定";
			}else{
				member=member1;
			}
		}else{
			//插入一条数据 
			W3AccessToken info = null;
			try{
				info = WeiXinUtils.getNickname(null,openid);
			}catch(Exception e){
				info = new W3AccessToken();
			}
			
			if(null == info)
				info = new W3AccessToken();

			if(StringUtils.isEmpty(info.getNicknamereturn())){
				member.setName("极车会员");
			}else{
				member.setName(info.getNicknamereturn());
			}
			member.setImageUrl(info.getHeadimgurlreturn());
			member.setStatuts(1);
			member.setType(0);
			member.setFromMember(2);
			member.setCreated(new Date());
			member.setLasttimeModify(new Date());
			member = this.createPersonalMember(member);
			
			Member memberNew = new Member();
			memberNew.setOpenid(openid);
			memberNew.setMobile(mobile);
			if(StringUtils.isEmpty(info.getNicknamereturn())){
				memberNew.setName("极车会员");
			}else{
				memberNew.setName(info.getNicknamereturn());
			}
			memberNew.setImageQrcode(basePath);
			return memberLoginFromWechat(memberNew);
		}
		member.setSuccess(isSuccess);
		member.setMessage(message);
		return member;
	}

	@Override
	public String downloadMediaFromWx(String mediaId) throws Exception {
		String token = AccessTokenUtils.getAccessToken();
		String fileSavePath = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path");
		String fileSavePathUrl = WiXinMultiMedia.downloadMediaFromWx(token, mediaId, fileSavePath);
		return fileSavePathUrl;
	}

	@Override
	public String getWebPathMemberHeadPictureUrl(String pictureHardDiskPath) throws Exception {
		String picture_database_path = Constant.APPLICATION_LOAD.getProperty("picture_database_path");
		String memberHeadPictureUrl = picture_database_path + "/" + pictureHardDiskPath;
		return memberHeadPictureUrl;
	}

	@Override
	public void delHardDiskHeadPicture(String imageUrl) throws Exception{
		
		if(StringUtils.isEmpty(imageUrl)){
			return;
		}
		//获取配置文件中数据库保存地址
		String picture_database_path = Constant.APPLICATION_LOAD.getProperty("picture_database_path");
		//获取图片名称
		String[] imgArray = imageUrl.split(picture_database_path);
		String img = "";
		if(imgArray.length > 2){
			img = imageUrl.split(picture_database_path)[1];
		}else{
			img = imageUrl.split(picture_database_path)[0];
		}
		//获取硬盘保存的路径
		String picture_hard_disk_path = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path");
		//得到硬盘图片保存的全路径
		String picture_hard_disk_path_url = picture_hard_disk_path + img;
		//执行删除
		File file = new File(picture_hard_disk_path_url);
		if(file.exists()) {
			file.delete();
		}
		
	}

	@Override
	public Member uploadMemberHeadPicture(Member member) throws Exception {
		Member m = this.findMemberByID(member.getId());
		//调用微信接口返回微信图片地址 下载到本地服务器图片服务器硬盘路径
		String fileSavePathUrl = this.downloadMediaFromWx(member.getMediaId());
		//解析服务器硬盘路径为web服务器的图片路径 返回web路径
		String webPictureUrl =  this.getWebPathMemberHeadPictureUrl(fileSavePathUrl);
		//执行更换 头像 update 到数据库操作
		Member mupdate = new Member();
		mupdate.setId(m.getId());
		mupdate.setImageUrl(webPictureUrl);
		mupdate = this.updatePersonalMember(mupdate);
		//根据名称删除图片
		this.delHardDiskHeadPicture(m.getImageUrl());
		return mupdate;
	}

	@Override
	public List<Member> selectByCondition(Member member) throws Exception {
		return tdMemberMapper.selectByCondition(member);
	}

	@Override
	public Member createMember(Member member) throws Exception {
		tdMemberMapper.insertSelective(member);
		return member;
	}

	@Override
	public Member updateMember(Member member) throws Exception {
		Assert.notNull(member,"会员实体不能为空");
		tdMemberMapper.updateByPrimaryKeySelective(member);
		return tdMemberMapper.selectByPrimaryKey(member);
	}

	@Override
	public Member selectBySysUserId(Member member) {
		member = tdMemberMapper.selectBySysUserId(member);
		return member;
	}

	/**
	 * 裁剪图片
	 *
	 * @param fileSavePathUrl
	 * @return
	 */
	@Override
	public String cutCenterImage(String fileSavePathUrl) throws IOException {
		//获取硬盘保存的路径
		String picture_hard_disk_path = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path");
		//得到硬盘图片保存的全路径
		String picture_hard_disk_path_url = picture_hard_disk_path + "//" + fileSavePathUrl;
		//裁剪图片
		ImageUtils.cutCenterImage(picture_hard_disk_path_url,picture_hard_disk_path_url,470,294);
		return fileSavePathUrl;
	}

	@Override
	public List<Member> findButlerMemberList() {
		Member member = new Member();
		//设置身份为管家
		member.setIsButler(1);
		return tdMemberMapper.selectByCondition(member);
	}

	@Override
	public List<Member> findMemberListByCondition(Member member) {
		List<Member> memberList = this.tdMemberMapper.findMemberListByCondition(member);
		return memberList;
	}

	@Override
	public List<Member> findCustomerMemberList() {
		Member member = new Member();
		//设置身份为管家
		member.setIsCustomer(1);
		return tdMemberMapper.selectByCondition(member);
	}

	@Override
	public Member findMemberByMobile(String mobile) {
		Assert.notNull(mobile, "mobile不能为空");
		Member member = new Member();
		member.setMobile(mobile);
		List<Member> members = tdMemberMapper.selectByCondition(member);
		if (CollectionUtils.isNotEmpty(members)){
			return members.get(0);
		}
		return null;
	}

}
