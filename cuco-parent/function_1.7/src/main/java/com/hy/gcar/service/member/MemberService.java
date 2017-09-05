package com.hy.gcar.service.member;


import com.hy.gcar.entity.Member;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <Member>
 * @since 1.0,2016-08-11 16:59:56
 */
public interface MemberService {
   
	public Member createMember(Member member) throws Exception;

	/**
	 * 修改用户信息
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public Member updateMember(Member member) throws  Exception;

	/**
	 * @Title: createCompanyMember  
	 * @Description: 创建企业会员 仅销售在微信端
	 * @param:@param member
	 * @param:@throws Exception    
	 * @createTime:2016年8月11日 下午6:30:22
	 * @throws
	 */
	public Member createCompanyMember(Member member) throws Exception;
	
	/**
	 * 
	 * @Title: createPersonalMember  
	 * @Description: 创建个人会员
	 * @param:@param member
	 * @param:@throws Exception    
	 * @createTime:2016年8月11日 下午7:16:44
	 * @throws
	 */
	public Member createPersonalMember(Member member) throws Exception;

	/**
	 * @Title: updateCompanyMember  
	 * @Description: 修改企业用户
	 * @param:@param member
	 * @param:@throws Exception    
	 * @createTime:2016年8月11日 下午6:47:38
	 * @throws
	 */
	public Member updateCompanyMember(Member member) throws Exception;
	
	/**
	 * 
	 * @Title: updatePersonalMember  
	 * @Description: 修改个人用户
	 * @param:@param member
	 * @param:@throws Exception    
	 * @createTime:2016年8月11日 下午6:48:32
	 * @throws
	 */
	public Member updatePersonalMember(Member member) throws Exception;
	
	/**
	 * 
	 * @Title: findMember  
	 * @Description: 分条件查找用户信息
	 * @param:@param member
	 * @param:@return
	 * @param:@throws Exception    
	 * @return:List<Member> 
	 * @createTime:2016年8月11日 下午6:56:42
	 * @throws
	 */
	public List<Member> findMember(Member member) throws Exception;
	
	/**
	 * @Title: findMemberByID  
	 * @Description: 根据 memberID 查询member实体
	 * @param:@param memberID
	 * @param:@return
	 * @param:@throws Exception    
	 * @return:Member 
	 * @createTime:2016年8月11日 下午7:01:59
	 * @throws
	 */
	public Member findMemberByID(Long memberID) throws Exception;
	
	
	/**
	 * @Title: findMemberByOpenID  
	 * @Description: 根据openID 查询会员信息
	 * @param:@param openID
	 * @param:@return
	 * @param:@throws Exception    
	 * @return:Member 
	 * @createTime:2016年8月11日 下午7:07:55
	 * @throws
	 */
	public Member findMemberByOpenID(String openID) throws Exception;
	
	/**
	 * 
	 * @Title: findSaleMemberList  
	 * @Description: 查询销售人员列表
	 * @param:@return
	 * @param:@throws Exception    
	 * @return:List<Member> 
	 * @createTime:2016年8月11日 下午7:24:00
	 * @throws
	 */
	public List<Member> findSaleMemberList() throws Exception;
	
	/**
	 * 微信公众号会员登录专用方法（如会员首次登录，自动完成注册）
	 * 此方法除验证手机号和动态密码外，需要验证当前微信openid是否与关注时的openid相同，不同则不能登录
	 * 
	 * @param member
	 * @return
	 */
	public Member memberLoginFromWechat(Member member) throws Exception ;
	
	
	/**
	 * @Title: downloadMediaFromWx 
	 * @Description: 下载微信 图片到本地
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月16日 下午8:01:36 
	 * @throws
	 */
	public String downloadMediaFromWx(String mediaId) throws Exception;
	
	/**
	 * 
	 * @Title: getWebPathMemberHeadPictureUrl 
	 * @Description: 获取web路径的url地址
	 * @param @param pictureHardDiskPath
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月16日 下午8:05:35 
	 * @throws
	 */
	public String getWebPathMemberHeadPictureUrl(String pictureHardDiskPath) throws Exception;

	
	/**
	 * 
	 * @Title: delHardDiskHeadPicture 
	 * @Description: 从硬盘存储路径删除会员的头像
	 * @param @param imageUrl
	 * @return void
	 * @author q.p.x 
	 * @date 2016年8月16日 下午8:24:17 
	 * @throws
	 */
	public void delHardDiskHeadPicture(String imageUrl) throws Exception;
	
	/**
	 * @Title: uploadMemberHeadPicture 
	 * @Description: 上传会员头像
	 * @param @param member
	 * @param @throws Exception
	 * @return void
	 * @author q.p.x 
	 * @date 2016年8月16日 下午8:32:46 
	 * @throws
	 */
	public Member uploadMemberHeadPicture(Member member) throws Exception;
	
	
	/**
	 * @Title: uploadMemberHeadPicture 
	 * @Description: 分条件查询会员信息
	 * @param @param member
	 * @param @throws Exception
	 * @return void
	 * @author q.p.x 
	 * @date 2016年8月16日 下午8:32:46 
	 * @throws
	 */
	public List<Member> selectByCondition(Member member) throws Exception;
	
	/**
	 * 根据sysuserID查询会员信息
	 * @param member
	 * @return
	 */
	public Member selectBySysUserId(Member member);

	/**
	 * 裁剪图片
	 * @param fileSavePathUrl
	 * @return
	 */
	public String cutCenterImage(String fileSavePathUrl) throws IOException;
	
	/**
	 * 获取所有角色为管家的会员列表
	 * @return
	 */
	public List<Member> findButlerMemberList();
	
	/**
	 * 根据手机号姓名模糊搜索会员列表
	 * @return
	 */
	public List<Member> findMemberListByCondition(Member member);

	/**
	 * 查询所有客服的列表
	 * @return
	 */
    public List<Member> findCustomerMemberList();
    
	/**
	 * findMemberByMobile  
	 * 根据手机号查询用户信息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-12 下午2:36:47   
	* @return String
	 */
	public Member findMemberByMobile(String mobile);
}
