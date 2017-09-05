package com.hy.gcar.service.memberInfo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.MemberInfoMapper;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
@Service
public class MemberInfoServiceImpl implements MemberInfoService{

	@Autowired
	private MemberInfoMapper<MemberInfo> memberInfoMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public MemberInfo findMemberInfoByMemberID(Long memberID) throws Exception{
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setMemberId(memberID);
		List<MemberInfo> memberInfos = memberInfoMapper.selectByCondition(memberInfo);
		if(CollectionUtils.isNotEmpty(memberInfos)){
			return memberInfos.get(0);
		}
		return null;
	}

	@Override
	public MemberInfo createMemberInfo(MemberInfo memberInfo) throws Exception{
		Assert.notNull(memberInfo);
		memberInfo.setCreated(new Date());
		memberInfoMapper.insertSelective(memberInfo);
		return memberInfo;
	}

	@Override
	public MemberInfo updateMemberInfo(MemberInfo memberInfo) throws Exception{
		Assert.notNull(memberInfo);
		memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
		return memberInfoMapper.selectByPrimaryKey(memberInfo.getId());
	}

	@Override
	public MemberInfo uploadMemberInfo(MemberInfo memberInfo) throws Exception {
		
		MemberInfo mInfo = this.findMemberInfoByMemberID(memberInfo.getMemberId());
		
		MemberInfo mInfoUpdate = new MemberInfo();
		mInfoUpdate.setMemberId(memberInfo.getMemberId());
		String idcardFront = this.downloadMediaFromWxByIdcardFront(memberInfo.getIdcardFront());
		mInfoUpdate.setIdcardFront(idcardFront);
		
		String idcardBack = this.downloadMediaFromWxByIdcardBack(memberInfo.getIdcardBack());
		mInfoUpdate.setIdcardBack(idcardBack);
		
		String  drivercardOriginal = this.downloadMediaFromWxByDrivercardOriginal(memberInfo.getDrivercardOriginal());
		mInfoUpdate.setDrivercardOriginal(drivercardOriginal);
		//执行更换 头像 update 到数据库操作
		
		String drivercardCopy = this.downloadMediaFromWxByDrivercardCopy(memberInfo.getDrivercardCopy());
		mInfoUpdate.setDrivercardCopy(drivercardCopy);
		
		MemberInfo updateMemberInfo = null;
		if(mInfo == null){
			updateMemberInfo = this.createMemberInfo(mInfoUpdate);
		}else{
			mInfoUpdate.setId(mInfo.getId());
			updateMemberInfo = this.updateMemberInfo(mInfoUpdate);
		}
		//根据名称删除图片
		this.delMemberInfoPicture(mInfo);
		return updateMemberInfo;
		
	}	
	/**
	 * @throws Exception 
	 * 
	 * @Title: downloadMediaFromWxByIdcardFront 
	 * @Description: 从微信下载身份证正面到本地服务器
	 * @param @param idcardFront
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 上午10:57:52 
	 * @throws
	 */
	private String downloadMediaFromWxByIdcardFront(String idcardFront) throws Exception{
		if(StringUtils.isEmpty(idcardFront)){
			return null;
		}
		//调用微信接口返回微信图片地址 下载到本地服务器图片服务器硬盘路径
		String fileSavePathUrl = memberService.downloadMediaFromWx(idcardFront);

		//裁剪图片
		fileSavePathUrl = memberService.cutCenterImage(fileSavePathUrl);

		//解析服务器硬盘路径为web服务器的图片路径 返回web路径
		String webPictureUrl =  memberService.getWebPathMemberHeadPictureUrl(fileSavePathUrl);
		return webPictureUrl;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: downloadMediaFromWxByIdcardFront 
	 * @Description: 从微信下载身份证反面到本地服务器
	 * @param @param idcardFront
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 上午10:57:52 
	 * @throws
	 */
	private String downloadMediaFromWxByIdcardBack(String idcardBack) throws Exception{
		
		if(StringUtils.isEmpty(idcardBack)){
			return null;
		}
		//调用微信接口返回微信图片地址 下载到本地服务器图片服务器硬盘路径
		String fileSavePathUrl = memberService.downloadMediaFromWx(idcardBack);
		//裁剪图片
		fileSavePathUrl = memberService.cutCenterImage(fileSavePathUrl);
		
		//解析服务器硬盘路径为web服务器的图片路径 返回web路径
		String webPictureUrl =  memberService.getWebPathMemberHeadPictureUrl(fileSavePathUrl);
		return webPictureUrl;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: downloadMediaFromWxByIdcardBack 
	 * @Description:  从微信下载驾驶证正本到本地服务器
	 * @param @param IdcardBack
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 上午10:58:56 
	 * @throws
	 */
	private String downloadMediaFromWxByDrivercardOriginal(String drivercardOriginal) throws Exception{
		if(StringUtils.isEmpty(drivercardOriginal)){
			return null;
		}
		//调用微信接口返回微信图片地址 下载到本地服务器图片服务器硬盘路径
		String fileSavePathUrl = memberService.downloadMediaFromWx(drivercardOriginal);
		
		//裁剪图片
		fileSavePathUrl = memberService.cutCenterImage(fileSavePathUrl);
		
		//解析服务器硬盘路径为web服务器的图片路径 返回web路径
		String webPictureUrl =  memberService.getWebPathMemberHeadPictureUrl(fileSavePathUrl);
		return webPictureUrl;
	}
	
	/**
	 * @throws Exception 
	 * @Title: downloadMediaFromWxByIdcardBack 
	 * @Description:  从微信下载驾驶证副本到本地服务器
	 * @param @param IdcardBack
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 上午10:58:56 
	 * @throws
	 */
	private String downloadMediaFromWxByDrivercardCopy(String drivercardCopy) throws Exception{
		if(StringUtils.isEmpty(drivercardCopy)){
			return null;
		}
		//调用微信接口返回微信图片地址 下载到本地服务器图片服务器硬盘路径
		String fileSavePathUrl = memberService.downloadMediaFromWx(drivercardCopy);
		
		//裁剪图片
		fileSavePathUrl = memberService.cutCenterImage(fileSavePathUrl);
		
		//解析服务器硬盘路径为web服务器的图片路径 返回web路径
		String webPictureUrl =  memberService.getWebPathMemberHeadPictureUrl(fileSavePathUrl);
		return webPictureUrl;
	}

	@Override
	public void delMemberInfoPicture(MemberInfo memberInfo) throws Exception {
		if(memberInfo == null){
			return;
		}
		memberService.delHardDiskHeadPicture(memberInfo.getIdcardBack());
		memberService.delHardDiskHeadPicture(memberInfo.getIdcardFront());
		memberService.delHardDiskHeadPicture(memberInfo.getDrivercardCopy());
		memberService.delHardDiskHeadPicture(memberInfo.getDrivercardOriginal());
	}

	@Override
	public boolean checkMemberInfoIsPerfect(MemberInfo memberInfo) throws Exception {
		if(memberInfo == null){
			return false;
		}
		
		boolean isPerfect = StringUtils.isNotEmpty(memberInfo.getIdcardFront())
							&&StringUtils.isNotEmpty(memberInfo.getIdcardBack())
							&&StringUtils.isNotEmpty(memberInfo.getDrivercardOriginal())
							&&StringUtils.isNotEmpty(memberInfo.getDrivercardCopy());
		
		return isPerfect;
	}
	
	
	

}
