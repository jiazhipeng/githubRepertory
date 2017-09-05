package cn.cuco.dao;

import org.apache.ibatis.annotations.Param;

import cn.cuco.entity.MemberInfo;

public interface MemberInfoMapper<T> extends BaseDao<T> {

	/** 
	* @Title: getMemberInfoByMemberId 
	* @Description: 根据用户ID获取用户信息
	* @author zc.du
	* @param id
	* @return
	* @return MemberInfo
	*/
	public MemberInfo getMemberInfoByMemberId(@Param("memberId")Long memberId);
}
