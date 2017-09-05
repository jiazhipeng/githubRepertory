package com.hy.gcar.service.memberInfo;

import javax.servlet.http.HttpServletRequest;

import com.hy.gcar.entity.MemberInfo;

public interface MemberInfoAppService {
	public MemberInfo upload(HttpServletRequest request) throws Exception;
	
	public MemberInfo savePicture(MemberInfo memberInfo) throws Exception;
}
