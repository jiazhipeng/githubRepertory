package com.hy.gcar.service.memberInfo;

import com.hy.gcar.entity.MemberInfo;

/**
 * 会员详情信息
 * @author q.p.x
 *
 */
public interface MemberInfoService {
	
	/**
	 * @Title: findMemberInfoByMemberID  
	 * @Description: 根据会员id查找身份证、驾驶证等信息
	 * @param:@param memberID
	 * @param:@return    
	 * @return:MemberInfo 
	 * @createTime:2016年8月13日 下午1:17:00
	 * @throws
	 */
	public MemberInfo findMemberInfoByMemberID(Long memberID) throws Exception;
	
	/**
	 * 
	 * @Title: createMemberInfo  
	 * @Description: 创建会员详情表里面包含（会员的身份证、驾驶证等照片信息）
	 * @param:@param memberInfo
	 * @param:@return    
	 * @return:MemberInfo 
	 * @createTime:2016年8月13日 下午3:31:24
	 * @throws
	 */
	public MemberInfo createMemberInfo(MemberInfo memberInfo) throws Exception;
	
	/**
	 * 
	 * @Title: updateMemberInfo  
	 * @Description: 修改会员详情里面包含(会员的身份证、驾驶证等照片信息)
	 * @param:memberInfo
	 * @param:    
	 * @return:MenberInfo 
	 * @createTime:2016年8月13日 下午3:32:37
	 * @throws
	 */
	public MemberInfo updateMemberInfo(MemberInfo memberInfo) throws Exception;

	
	/**
	 * @Title: uploadMemberInfo 
	 * @Description:上传会员信息 (身份证正反面、驾驶证正反面)
	 * @param @param memberInfo
	 * @return void
	 * @author q.p.x 
	 * @date 2016年8月18日 上午10:46:55 
	 * @throws
	 */
	public MemberInfo uploadMemberInfo(MemberInfo memberInfo) throws Exception;
	
	/**
	 * @Title: delMemberInfoPicture 
	 * @Description: 删除memberInfo对应的照片信息
	 * @param @param memberInfo
	 * @return void
	 * @author q.p.x 
	 * @date 2016年8月18日 上午11:31:46 
	 * @throws
	 */
	public void delMemberInfoPicture(MemberInfo memberInfo) throws Exception;
	
	/**
	 * 
	 * @Title: checkMemberInfoIsPerfect 
	 * @Description: 校验会员信息是否完善
	 * @param @param memberInfo
	 * @param @return
	 * @param @throws Exception
	 * @return boolean
	 * @author q.p.x 
	 * @date 2016年8月18日 下午1:46:49 
	 * @throws
	 */
	public boolean checkMemberInfoIsPerfect(MemberInfo memberInfo) throws Exception;

}
