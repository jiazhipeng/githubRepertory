package com.hy.gcar.dao;

import java.util.List;

import com.hy.gcar.entity.Member;

public interface MemberMapper<T> extends BaseDao<T> {

	public Member selectBySysUserId(Member member);
	
	/**
	 * 根据手机号姓名模糊搜索会员列表
	 * @return
	 */
	public List<Member> findMemberListByCondition(Member member);
}
