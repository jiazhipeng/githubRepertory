package com.hy.gcar.service.member.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hy.common.utils.Identities;
import com.hy.constant.Constant;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberAppService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.utils.Global;


@Service
public class MemberAppServiceImpl implements MemberAppService {

	protected Logger logger = LoggerFactory.getLogger(MemberAppServiceImpl.class);

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberItemService memberItemService;
	
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	NationService nationService;
	@Autowired
	MemberItemShareService memberItemShareService;
	
	@Override
	public Member upload(HttpServletRequest request) throws Exception{
        //转换 成多部分 request    
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
        //取得request中的所有文件名  
        Iterator<String> iter = multiRequest.getFileNames();  
        Member member = new Member();
        while(iter.hasNext()){  
	        //取得上传文件  
	        MultipartFile file = multiRequest.getFile(iter.next());  
	        if(file == null){
	        	continue;
	        }
            //取得当前上传文件的文件名称  
            String myFileName = file.getOriginalFilename();  
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            if (StringUtils.isEmpty(myFileName)){
            	continue;
            }
            if(StringUtils.isEmpty(myFileName.trim())){
            	continue;
            }
            //重命名上传后的文件名  
            String fileName =  System.currentTimeMillis() + file.getOriginalFilename();  
            //定义上传路径  
//          String path = request.getSession().getServletContext().getRealPath(File.separator+"static"+File.separator+"gcar"+File.separator+"images"+File.separator+"memberInfo")+File.separator + fileName;  
            String path = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path")+File.separator+"memberInfo";
            String savePath = Constant.APPLICATION_LOAD.getProperty("picture_database_path")+File.separator+"memberInfo"+File.separator+fileName;
            saveFileFromInputStream(file.getInputStream(), path, fileName);
            member.setImageUrl(savePath);
          } 
        return member;
	}

	@Override
	public String saveFileFromInputStream(InputStream stream, String fileSavePath, String filename) throws Exception {
		
		File dir = new File(fileSavePath);
		if (!dir.exists()) {
		  dir.mkdirs();
		}
		 if (!fileSavePath.endsWith("/")) {
		  fileSavePath += "/";
		 }
		
		 fileSavePath += filename; 
		 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileSavePath));
		 byte[] data = new byte[1024];
		 int len = -1;
		 while ((len = stream.read(data)) != -1) {
		  bos.write(data,0,len);
		 }
		 bos.close();
		 stream.close();
	    return filename;  
	}

	@Override
	public OpenResponse getverificationMain(String mobile) {
		String methodName = "getverificationMain";
		logger.info("执行({})操作开始.....", methodName);
		Map<String, String> map = new HashMap<String, String>();

		// 解析json数据
		try {
			Member member = new Member();
			member.setMobile(mobile);
			List<Member> list = memberService.findMember(member);// 查找用户
			if(list.size()>0){
				member=list.get(0);
			}
			
			 List<MemberItem> mplist  =null;  
			 String hasMemberPower="0";
			 if(null!=member.getId()){
				 MemberItem memberItem =  new MemberItem();
				 memberItem.setMemberId(member.getId());
				 mplist = memberItemService.selectByCondition(memberItem);
				 if(CollectionUtils.isEmpty(mplist)){
					 MemberItemShare share = new MemberItemShare();
					 share.setMemberId(member.getId());
					 List<MemberItemShare> shareList = memberItemShareService.selectByCondition(share );
					 if(CollectionUtils.isNotEmpty(shareList)){
						 hasMemberPower="1";
					 }
				 }else{
					 hasMemberPower="1";
				 }
			 }
			
			map.put("mobile", member.getMobile()+"");
			map.put("sex", member.getSex()+"");// 性别 -1未知;1:男;0:女; name sex
			map.put("name", member.getName());
			
			map.put("city", member.getCityName());
			if(StringUtils.isNotEmpty(member.getCityCode())){
				Nation n = new Nation();
				n.setCode(member.getCityCode());
				n = nationService.getParentNationByCityeCodeOrId(n);
				map.put("city", n.getName() + " " + member.getCityName());
			}

			map.put("type", member.getType() + "");
//			map.put("ismain", adminmemer == null ? "0" : "1");
			map.put("hasMemberPower", hasMemberPower);// 1：有权益																			// 0：没有权益
			map.put("isTryDriver", member.getIsTestDriver() + "");// 1：试驾过
														// 0：没试驾过
			map.put("token", Identities.uuid2());
			map.put("memberId", member.getId() + "");// 用户Id
			setIsPerfect(map, member);			
			map.put("image", member.getImageUrl());// 用户头像
		} catch (Exception e) {
			logger.error("error:" + e.getMessage(), e);
			 new OpenResponse("false", "出现异常", null, "100014");
		}
		return new OpenResponse("true", "验证人员成功", map, "100000");
	}

	private void setIsPerfect(Map<String, String> map, Member member) throws Exception {
		//查询会员info信息
		MemberInfo memberInfo = this.memberInfoService.findMemberInfoByMemberID(member.getId());
		boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(memberInfo);
		if(isPerfect){
			map.put("isPerfect", "已完善");
		}else{
			map.put("isPerfect", "未完善");
		}
	}

	
	
	
}
