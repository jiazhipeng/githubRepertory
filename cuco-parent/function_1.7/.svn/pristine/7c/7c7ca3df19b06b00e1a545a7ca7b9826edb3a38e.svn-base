package com.hy.gcar.web.app.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.facede.LoginFacede;

@Controller
public class LoginAppController {

	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoginFacede loginFacede;
	
	@RequestMapping("/gcarapp/ios/loginfacede/login")
	@ResponseBody
	public Object loginForIOS(@RequestParam(value = "message", required = true) String message){
		
		return loginFacede.login(message,"5");
	}
	
	@RequestMapping("/gcarapp/android/loginfacede/login")
	@ResponseBody
	public Object loginForAndroid(@RequestParam(value = "message", required = true) String message){
		
		return loginFacede.login(message,"4");
	}
	
	@RequestMapping("/gcarapp/ios/loginfacede/getcaptcha")
	@ResponseBody
	public Object getCaptchaForIOS(@RequestParam(value = "message", required = true) String message){
		
		return loginFacede.getCaptcha(message);
	}
	
	@RequestMapping("/gcarapp/android/loginfacede/getcaptcha")
	@ResponseBody
	public Object getCaptchaForAndroid(@RequestParam(value = "message", required = true) String message){
		
		return loginFacede.getCaptcha(message);
	}

}
