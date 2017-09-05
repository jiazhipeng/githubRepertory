package cn.cuco.controller.butler.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.authorization.entity.Captcha;
import com.hy.authorization.entity.User;

import cn.cuco.annotation.API;
import cn.cuco.cache.JedisUtils;
import cn.cuco.common.utils.cookie.CookieUtils;
import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.sms.SMSUtils;
import cn.cuco.constant.Constant;
import cn.cuco.entity.ButlerTask;
import cn.cuco.enums.ServerStatus;
import cn.cuco.enums.security.RoleEnum;
import cn.cuco.httpservice.ResponseUtil;
import cn.cuco.service.security.SecurityService;
import cn.cuco.service.task.ButlerTaskService;
import cn.cuco.wechat.entity.AccessToken;
import cn.cuco.wechat.util.AccessTokenUtil;

/**
 * @ClassName: LoginController 
 * @Description: 管家登录
 * @author: wangchuntao 
 * @date: 2017年3月8日 上午9:03:30
 */
@RestController
public class LoginController {
	protected Logger logger =LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ButlerTaskService butlerTaskService;
    @Autowired
    private SecurityService securityService;
    /**
     * @Title: getCaptcha 
     * @Description: 获取验证码
     * @author: wangchuntao 
     * @param mobile
     * @return Object
     * @date: 2017年3月8日 上午11:56:49
     */
    @API(value="获取验证码")
    @RequestMapping(value = "/wechat/getcaptcha",method=RequestMethod.GET)
	public Object getCaptcha(String mobile){
		String key = "captcha_"+mobile;
		try{
			Captcha captcha = new Captcha();//(Captcha)JedisUtils.getObject(key);
			String curCode = RandomStringUtils.randomNumeric(6);
			String info = "验证码："+curCode+"(10分钟内有效，如非本人操作请忽略)";
			boolean flag = SMSUtils.sendMessage(info,mobile);
			if(flag){
				logger.info("验证码{}发送至{}成功",curCode,mobile);
				captcha.setCode(curCode);
				captcha.setCreateDate(new Date());
				captcha.setUsed(false);
				captcha.setCount(1L);
				//captcha.setCount(captcha.getCount()+1);
				JedisUtils.setObject(key, captcha, 600);
			}else{
				logger.info("验证码{}发送至{}失败",curCode,mobile);
				return ResponseUtil.toFailureBody(ServerStatus.USER_DEFINITION, "出现异常，请稍后再试！");
			}
			return ResponseUtil.toSuccessBody("验证码发送成功");
		}catch(Exception e){
			logger.error("验证码发送至{}失败",mobile,e);
		}
		return ResponseUtil.toFailureBody(ServerStatus.USER_DEFINITION, "出现异常，请稍后再试！");
	}
    /**
     * @Title: login 
     * @Description: 管家登录
     * @author: wangchuntao 
     * @param mobile
     * @param code
     * @param captcha
     * @param email
     * @param request
     * @param response
     * @return Object
     * @date: 2017年3月8日 上午11:57:20
     */
    @API(value="管家登录")
    @RequestMapping(value = "/wechat/login",method=RequestMethod.GET)
	public Object login(String mobile,String code,String captcha,String email, HttpServletRequest request,HttpServletResponse response) {
	
		boolean messageFlag = Boolean.parseBoolean(Constant.APPLICATION_LOAD.getProperty("message_check_flag"));
		String key = "captcha_" + mobile;
		//1.验证码的时效性、准确性
		try{
			if(messageFlag){
				Captcha captchaJedis = (Captcha)JedisUtils.getObject(key);
				if(null == captchaJedis || !captchaJedis.getCode().equals(captcha)){
					logger.info("redis中不存在{}",key);
					return ResponseUtil.toFailureBody(ServerStatus.USER_DEFINITION, "验证码不正确！");
//							new Results(false,"验证码不正确！");
				}else{
					Date end = DateUtils.addMinutes(captchaJedis.getCreateDate(), 10);
					if(end.before(new Date()) || captchaJedis.isUsed()){//是否失效
//						return new Results(false,"验证码已失效，请重新获取验证码");//"没有失效，已经可用";
						return ResponseUtil.toFailureBody(ServerStatus.USER_DEFINITION, "验证码已失效，请重新获取验证码");
					}
				}
				captchaJedis.setUsed(true);
				JedisUtils.setObject(key, captchaJedis, 0);
			}
			
			
//			获取权限系统人员角色信息 通过手机号和邮箱
//			如何返回null "请先关注微信号，再登录！
			User user = securityService.getUserByMobileAndEmail(mobile, email);
			if(user==null){
				return ResponseUtil.toFailureBody(ServerStatus.USER_REGISTER, "请先注册");
			}
			
			String openidKey=Constant.load.getProperty("openidKey");
			AccessToken accToken = AccessTokenUtil.getAccToken(code);
			String openid = accToken.getOpenid();    
			CookieUtils.setCookie(response, openidKey, openid,30*60*1000);
			
			Date date = new Date();
			ButlerTask butlerTask = new ButlerTask();
			butlerTask .setCreatedStart(DateUtils.getDayMiniDate(date));
	    	butlerTask.setCreatedEnd(DateUtils.getDayMaxDate(date));
	    	if(user.hasRole(RoleEnum.CUCO_GJFZR.toString())){
	    		return butlerTaskService.getButlerTaskPageForOfficialEnd(butlerTask);
	    	}else if(user.hasRole(RoleEnum.CUCO_CW.toString())){
	    		return butlerTaskService.getButlerTaskPageForCarService(butlerTask);
	    	}else if(user.hasRole(RoleEnum.CUCO_GJ.toString())){
	    		butlerTask.setOperaterId(user.getId());
	    		return butlerTaskService.getButlerTaskPageForOfficialEnd(butlerTask);
	    	}
			
		}catch(Exception e){
			logger.error("{}登陆出现异常",mobile,e);
		}
		return ResponseUtil.toFailureBody(ServerStatus.USER_DEFINITION, "登录失败！");
	}

   
    
 /*   @RequestMapping(value="/index",method=RequestMethod.GET)
  	public Object toLogin(HttpServletRequest request,HttpServletResponse response,String code,ModelMap map){
  		String openidKey=Constant.load.getProperty("openidKey");
  		String openid = CookieUtils.getCookie(request, openidKey);
  		try {
  			if(StringUtils.isBlank(openid)){
  				if(StringUtils.isBlank(code)){
  					String url_prefix = Constant.load.getProperty("url_prefix");
  					String newurl = URLEncoder.encode(url_prefix + "/index", "UTF-8");
  					String redirectPath = WechatUrl.getUrlBySnsapibase(newurl, "shouquan");
  					return "redirect:" + redirectPath;
  				}
  				AccessToken accToken = AccessTokenUtil.getAccToken(code);
  				openid = accToken.getOpenid();    
  				CookieUtils.setCookie(response, openidKey, openid,30*60*1000);
  			}
  		}  catch (Exception e) {
  			e.printStackTrace();
  		}
//  		map.put("openid",openid);
  		return ResponseUtil.toSuccessBody(null);
  	}
      */
}
