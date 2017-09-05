package com.hy.security.child.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.common.utils.JedisUtils;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.child.constant.NamePassWord;
import com.hy.security.child.view.request.ParentReqView;
import com.hy.security.child.view.response.ParentResView;
import com.hy.security.util.DigestUtil;
/**
 * 子系统接受权限系统通知权限变化接口
 * @author zhubing
 *
 */
@RestController
public class AcceptSecurityChangController {
	
	
	@API(value = "接受权限系统通知权限变化")
	@RequestMapping(value = "/parent/login",method = RequestMethod.POST)
	public Object parentLogin(@RequestBody ParentReqView parentReqView){
		
		if(parentReqView == null
				|| StringUtils.isBlank(parentReqView.getPassWord())
				|| StringUtils.isBlank(parentReqView.getUserName())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		
		if(NamePassWord.USER_NAME.equals(parentReqView.getUserName()) 
				&& NamePassWord.PASSWORD.equals(parentReqView.getPassWord())){
			String childToken = DigestUtil.MD5(NamePassWord.USER_NAME+NamePassWord.PASSWORD+NamePassWord.class.getPackage());
			ParentResView parentResView = new ParentResView(childToken);
			return parentResView;
			
		}
		throw new RuntimeExceptionWarn("用户名密码错误，请重试...");
		
	}

	@API(value = "接受权限系统通知权限变化")
	@RequestMapping(value = "/parent/security/change",method = RequestMethod.POST)
	public Object systemSecurityChange(){
		
		//清空用户的权限信息
		JedisUtils.delLikeKey("token*");
		//清空系统无权限集合
		JedisUtils.delLikeKey("system*");
		return "success";
	}
	
	@API(value = "接受权限系统通知无权限集合权限变化")
	@RequestMapping(value = "/parent/nosecurity/change",method = RequestMethod.POST)
	public Object systemNoSecurityChangeNotice(){
		
		//清空系统无权限集合
		JedisUtils.delLikeKey("system*");
		return "success";
	}
	
}
