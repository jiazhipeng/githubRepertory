package com.hy.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hy.accessToken.common.SystemTokenFactory;
import com.hy.annotation.API;
import com.hy.exception.RuntimeExceptionWarn;

/**系统交互controller
 * @author 1mobility
 *
 */
public class InteractionController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	//注入token工厂
	@Autowired
	private SystemTokenFactory systemTokenFactory;
	
	/**系统注册通过appid以及secret请求一个token
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	@API(value = "获取子系统Token")
	@RequestMapping(value = "/v1/system/returnSystemToken",method = RequestMethod.GET)
	public Object returnSystemToken(String appid, String appsecret){
		logger.info("appid为--"+appid+"的子系统请求注册token");
		String system_token = systemTokenFactory.getToken(appid, appsecret);
		if("false".equals(system_token)){
			throw new RuntimeExceptionWarn("用户appid与秘钥不匹配");
		}
		return system_token;
	}
	
	/**token验证
	 * @param appid
	 * @param token
	 * @return
	 */
	@API(value = "系统交互验证")
	@RequestMapping(value = "/v1/system/validateToken",method = RequestMethod.GET)
	public Object validateToken(String appid, String appsecret, String token){
		logger.info("appid为--"+appid+"的子系统请求验证token");
		//得到权限系统中的token进行与传过来的token进行比较
		String system_token = systemTokenFactory.getToken(appid, appsecret);
		
		if(!system_token.equals(token)){
			//传过来的token跟权限系统中的token不一致
			return false;
		}
		return true;
	}
}
