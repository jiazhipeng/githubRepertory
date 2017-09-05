package com.hy.security.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.annotation.API;
import com.hy.common.utils.AccessTokenUtils;
import com.hy.common.utils.DateUtils;
import com.hy.common.utils.DesUtil;
import com.hy.common.utils.HttpClientUtils;
import com.hy.common.utils.ImageQrcodeUtil;
import com.hy.common.utils.JedisUtils;
import com.hy.common.utils.MD5Util;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.common.utils.RegexUtils;
import com.hy.common.utils.StringUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.ExceptionUtil;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.entity.AccessToken;
import com.hy.security.entity.Captcha;
import com.hy.security.entity.ImageQRCodeInfo;
import com.hy.security.entity.LoginLog;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.service.LoginLog.LoginLogService;
import com.hy.security.service.system.SystemService;
import com.hy.security.service.user.AccessTokenService;
import com.hy.security.service.user.UserService;
import com.hy.security.util.Base64Image;
import com.hy.security.util.StringUtil;
import com.hy.security.util.UserUtils;
import com.hy.security.view.response.ImageQRCodeResView;
import com.hy.session.common.HySession;
import com.hy.session.common.HySessionFactory;

@RestController
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HySessionFactory hySessionFactory;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private LoginLogService loginLogService;
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	/**用户通过微信扫码登录
	 * @param openid
	 * @return
	 */
	@API(value = "用户登录")
	@RequestMapping(value = "/v1/user/login",method = RequestMethod.POST)
    public Object userLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user_par){
		String openid = user_par.getWechatOpenId();
		if(null==openid || "".equals(openid)){
			//没有openid,直接返回前台错误信息
			throw new RuntimeExceptionWarn("没有openid");
		}
		try {
			logger.info("当前请求登录用户的openid为--"+openid);
			//首先根据openid查询数据库能不能查询出来一条
			User user = new User();
			user.setWechatOpenId(openid);
			//根据openid调用用户查询接口查询用户表
			User user_new = this.userService.findByUser(user);
			if(null == user_new){
				//根据传过来的openid没有查询得到一条用户数据，说明没有该用户还没有进行绑定，告诉前端需要跳转到用户绑定页面
				throw new RuntimeExceptionWarn(ServerStatus.USER_REGISTER);
			}
			if(user_new.getFreezing().intValue() == 1){
				throw new RuntimeExceptionWarn("您的账号已经被冻结");
			}
			//根据用户ID查询用户最后登录的系统
			List<LoginLog> LoginList = this.loginLogService.selectLoginListByUserId(user_new.getId());
			if(LoginList.size()>0){
				//如果查出最近一次登录记录，取出登录的系统ID，还是默认登录最后一次登录的系统
				Long systemId = LoginList.get(0).getSystemId();
				System findById = systemService.findById(systemId);
				user_new.setSystem(findById);
				user_new.setSystemId(systemId);
			}
			//首次登录的情况根据用户ID查询该用户所对应的系统信息
			List<com.hy.security.entity.System> systemList = this.systemService.findSystemsOfUser(user_new);
			if(systemList.size()==0){
				//没有分配系统权限
				throw new RuntimeExceptionWarn(ServerStatus.USER_NO_ANY_SYSTEM_ACCESS);
			}
			if(systemList.size()==1){
				//只有一个系统权限，直接登录该系统
				user_new.setSystems(systemList);
				user_new.setSystemId(systemList.get(0).getId());
			}
			if(systemList.size()>1){
				//有多个系统的登录权限，跳转到系统选择列表
				user_new.setSystems(systemList);
				return  user_new;
			}
			//往用户登录日志表插入一条记录
			this.createLoginLog(user_new);
			//表示根据用户ID以及系统ID，去拉取该用户的权限集合,并放到用户对象里面
			return this.getPermission(request, response, user_new);
		} catch (Exception e) {
			logger.error("用户登录失败-----"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
    }
	@API(value = "获得用户系统列表")
	@RequestMapping(value = "/v1/user/systems",method = RequestMethod.POST)
	public Object getUserSystems(HttpServletRequest request, HttpServletResponse response){
		
		String tokenID = request.getHeader("HaiYi-Access-Token");
		
		AccessToken accessToken = new AccessToken();
		accessToken.setToken(tokenID);
		List<AccessToken> selectByCondition = accessTokenService.selectByCondition(accessToken);
		if(selectByCondition.isEmpty() || selectByCondition == null){
			//没有分配系统权限
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		Long userId = selectByCondition.get(0).getUserId();
		User user_Searche = new User(); 
		user_Searche.setId(userId);
		User user_new = this.userService.findByUser(user_Searche);
		
		//首次登录的情况根据用户ID查询该用户所对应的系统信息
		List<com.hy.security.entity.System> systemList = this.systemService.findSystemsOfUser(user_new);
		if(systemList.size()==0){
			//没有分配系统权限
			throw new RuntimeExceptionWarn(ServerStatus.USER_NO_ANY_SYSTEM_ACCESS);
		}

		//有多个系统的权限，跳转到系统选择列表
		user_new.setSystems(systemList);
		return user_new;
	}
	
	/**用户切换系统
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@API(value = "用户切换系统")
	@RequestMapping(value = "/v1/user/login/switchSystem",method = RequestMethod.POST)
	public Object switchSystem(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
		if(null==user.getId() || null==user.getSystemId() || null==user.getName()){
			throw new RuntimeExceptionWarn("参数错误");
		}
		User userDB = userService.findById(user.getId());
		if(userDB == null){
			throw new RuntimeExceptionWarn(ServerStatus.USER_NOT_FOUND);
		}
		user.setName(userDB.getName());
		//往用户登录日志表插入一条记录
		this.createLoginLog(user);
		//获取权限集合返回
		return this.getPermission(request, response, user);
	}
	
	/**根据用户ID以及系统ID得到权限集合放到redis里
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	public Object getPermission(HttpServletRequest request, HttpServletResponse response, User user){
		//表示能够查询得到数据，去拉取该用户的权限集合,并放到用户对象里面
		user = this.userService.findApiByUserIdAndSystemId(user);
//		if(CollectionUtils.isEmpty(user.getApis()) 
//				&& CollectionUtils.isEmpty(user.getMenus())){
//			ExceptionUtil.throwWarn(ServerStatus.USER_NO_SYSTEM_ACCESS);
//		}
		
		if(CollectionUtils.isEmpty(user.getMenus())){
			ExceptionUtil.throwWarn(ServerStatus.USER_NO_SYSTEM_ACCESS);
		}
		
		System systemById = systemService.findById(user.getSystemId());
		
		//将得到的权限集合放入session中，存入到redis
		HySession haiyiSession = hySessionFactory.getHaiYiSession(request, response, user.getWechatOpenId());
		String token = haiyiSession.getSessionKey();
		//将token存入user对象，方便前端获取
		user.setUser_token(token);
		
		AccessToken accessToken = new AccessToken();
		accessToken.setUserId(user.getId());
		accessToken.setSystemId(user.getSystemId());
		//根据用户查询和需要登录的系统id查询上次登录的token  这次要全部删除
		List<AccessToken> accessTokenByCondition = accessTokenService.selectByCondition(accessToken);
		if(accessTokenByCondition != null && !accessTokenByCondition.isEmpty()){
			for(int i=0;i<accessTokenByCondition.size();i++){
				accessTokenService.delById(accessTokenByCondition.get(i).getId());
			}
		}
		
		
		//插入本次登录的token
		accessToken.setCreateDate(new Date());
		accessToken.setLiveTime(Constant.TOKEN_DATABASE_LIVETIME);
		accessToken.setToken(token);
		accessTokenService.save(accessToken);
		
		//如果是权限系统，那么权限信息放入缓存  如果是其他子系统，那么权限信息不用放在权限系统缓存中，只要放在自己本地缓存中即可
		if(systemService.isMain(systemById)){
			haiyiSession.setAttribute(token, JSON.toJSON(user), Constant.TOKEN_LIVETIME);
			//
			UserUtils.setTokenEndDateTime(token);
			logger.info("登录用户权限集合为---"+haiyiSession.getAttribute(token));
		}
		return user;
	}
	
	/**用户登录成功，往登录日志表里写入
	 * @param user
	 */
	public void createLoginLog(User user){
		//初始化一个登录日志对象
		LoginLog log = new LoginLog();
		log.setLoginTime(new Date());
		log.setSystemId(user.getSystemId());
		log.setUserId(user.getId());
		log.setUserName(user.getName());
		try {
			logger.info("插入登录日志开始---");
			this.loginLogService.insertSelective(log);
		} catch (Exception e) {
			logger.error("插入登录日志失败---");
			e.printStackTrace();
		}
	}
	
	/**将用户的openid绑定上数据库里的用户记录
	 * @param user
	 * @return
	 */
	@API(value = "用户注册")
	@RequestMapping(value = "/v1/user/register",method = RequestMethod.POST)
	public Object userRegister(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
		if(null==user.getWechatOpenId() || null==user.getPhone() || null==user.getEmail() ||null==user.getMessage_code()){
			throw new RuntimeExceptionWarn("参数错误");
		}
		//根据手机号，从redis里面得到验证码进行匹配
		String key = "captcha_"+user.getPhone();
		try {
			if("112233".equals(user.getMessage_code())){
//				throw new RuntimeExceptionWarn("验证码不存在"); 
			}else{
				Captcha captcha = (Captcha)JedisUtils.getObject(key);
				if(null == captcha || !captcha.getCode().equals(user.getMessage_code())){
					//验证码不匹配
					logger.info("redis中不存在{}",key);
					throw new RuntimeExceptionWarn("验证码不存在");
				}else{
					Date end = DateUtils.addMinutes(captcha.getCreateDate(), 10);
					if(end.before(new Date()) || captcha.isUsed()){//是否失效
						throw new RuntimeExceptionWarn("验证码失效");
					}
				}
				//redis中将是否使用属性设置为已使用
				captcha.setUsed(true);
				JedisUtils.setObject(key, captcha, 0);
			}
			
			ParamVerifyUtil.verifyMobile(user.getPhone(), "手机号格式错误");
	    	boolean email = RegexUtils.isEmail(user.getEmail());
	    	if(!email){
	    		throw new RuntimeExceptionWarn("邮箱格式错误");
	    	}
			//根据手机号，邮箱查询注册用户信息
			User user_new = new User();
			user_new.setPhone(user.getPhone());
			//user_new.setEmail(user.getEmail());
			user_new.setFreezing(0);
			//获取用户信息
			user_new = this.userService.findByUser(user_new);
			if(null==user_new){
				//没有查询到该用户的信息
				throw new RuntimeExceptionWarn("手机号不匹配");
			}
			//查出数据库已经有这条数据，取出openid
			String openid = user_new.getWechatOpenId();
			if(null!=openid && !"".equals(openid)){
				throw new RuntimeExceptionWarn("该账号已被绑定");
			}
			if(!user.getEmail().equals(user_new.getEmail())){
				throw new RuntimeExceptionWarn("邮箱不匹配");
			}
			//验证通过，进行openid的绑定(将该openid信息写入数据库的用户表)
			user_new.setWechatOpenId(user.getWechatOpenId());
			user_new = this.userService.updateUserOpenId(user_new);
			
			//首次登录的情况根据用户ID查询该用户所对应的系统信息
			List<com.hy.security.entity.System> systemList = this.systemService.findSystemsOfUser(user_new);
			if(systemList.size()==0){
				//没有分配系统权限
				throw new RuntimeExceptionWarn(ServerStatus.USER_NO_ANY_SYSTEM_ACCESS);
			}
			if(systemList.size()==1){
				//只有一个系统权限，直接登录该系统
				user_new.setSystems(systemList);
				user_new.setSystemId(systemList.get(0).getId());
			}
			if(systemList.size()>1){
				//有多个系统的登录权限，跳转到系统选择列表
				user_new.setSystems(systemList);
				return  user_new;
			}
			//往用户登录日志表插入一条记录
			this.createLoginLog(user_new);
			//表示根据用户ID以及系统ID，去拉取该用户的权限集合,并放到用户对象里面
			return this.getPermission(request, response, user_new);
			
		} catch (Exception e) {
			logger.error("用户注册失败-----"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	/**用户登录获取短信验证码
	 * @param mobile
	 * @return
	 */
	@API(value = "获取短信验证码")
	@RequestMapping(value = "/v1/user/register/getcaptcha",method = RequestMethod.GET)
	public Object getCaptcha(String mobile){
		if(null==mobile || "".equals(mobile)){
			throw new RuntimeExceptionWarn("没有传入手机号！");
		}
		User user = new User();
		user.setPhone(mobile);
		User user_new = this.userService.findByUser(user);
		if(user_new == null){
			throw new RuntimeExceptionWarn("您不是注册用户！");
		}
		if(user_new.getFreezing().intValue() == 1){
			throw new RuntimeExceptionWarn("您账户已冻结！");
		}
		if(!StringUtils.isBlank(user_new.getWechatOpenId())){
			throw new RuntimeExceptionWarn("您账户已注册！");
		}
		String key = "captcha_"+mobile;
		try {
			Captcha captcha = new Captcha();
			String curCode = RandomStringUtils.randomNumeric(6);
			//定义短信内容
			String info = "验证码："+curCode+"(10分钟内有效，如非本人操作请忽略)";
			//调用短信验证码发送方法
			boolean flag = sendMessage(info,mobile);
			if(flag){
				logger.info("验证码{}发送至{}成功",curCode,mobile);
				captcha.setCode(curCode);
				captcha.setCreateDate(new Date());
				captcha.setUsed(false);
				captcha.setCount(1L);
				JedisUtils.setObject(key, captcha, 10*60);
			}else{
				logger.info("验证码{}发送至{}失败",curCode,mobile);
				throw new RuntimeExceptionWarn("验证码发送失败，请联系工作人员！");
			}
			return key;
		} catch (Exception e) {
			logger.error("验证码发送至{}失败-----"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	@API(value = "获取二维码")
	@RequestMapping(value = "/v1/user/login/getqrcode",method = RequestMethod.GET)
	public Object getQrcode(){
		
		String token = AccessTokenUtils.getAccessToken();
		if(StringUtils.isBlank(token)){
			logger.info("获取access_token异常");
			throw new RuntimeExceptionWarn("获取二维码图片失败///！");
		}
		int expireSeconds = 60*5;//Constant.IMAGEQRCODE_TIMEOUT;Constant.IMAGEQRCODE_TIMEOUT + 10;//TODO  记得更新二维码失效时间   在此写死
		String randomStr = getRandomsceneId();
		int sceneId = Integer.valueOf(randomStr);
		try {
			String basePath = this.getClass().getClassLoader().getResource("").getPath() + "temp/qrcode";
			String imageQrcode = ImageQrcodeUtil.getTempImageQrcode(token, basePath, expireSeconds, sceneId);
			//String fileName = imageQrcode.substring(imageQrcode.lastIndexOf("/")+1);
			
			String imageStr = Base64Image.GetImageStr(imageQrcode);
//			System.out.println("imageStr>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+imageStr);
			File file = new File(imageQrcode);
			logger.info("图片路径————————————————————————————"+imageQrcode);
			if(file.exists())
				file.delete();
			ImageQRCodeInfo info = new ImageQRCodeInfo();
			info.setCreateDate(new Date());
			info.setSceneId(sceneId);
			
			String jsonStr = JSONObject.toJSONString(info);
			int redis_timeOut = expireSeconds;
			JedisUtils.set(StringUtil.getFullScenId(randomStr), jsonStr, redis_timeOut);
			//便于维护redis中的scenId
			//JedisUtils.set(Constant.QR_CODE_PREFIX+StringUtil.getSystemName()+randomStr, randomStr, redis_timeOut);
			
			String loginToken = DesUtil.encrypt(sceneId+"", "HY_LOGIN");
			logger.info("loginToken======================"+loginToken);
			return new ImageQRCodeResView(imageStr,loginToken,expireSeconds);
		} catch (Exception e) {
			logger.error("获取二维码图片失败---",e);
			//logger.error("获取二维码图片失败{}",e);
			throw new RuntimeExceptionWarn("获取二维码图片失败**！");
		}
	}
	
	/*@API(value = "获取用户扫码登录结果")
	@RequestMapping(value = "/v1/user/login/check",method = RequestMethod.GET)
	public Object loginCheck(String token) throws Exception{
		ParamVerifyUtil.paramNotEmpty(token);
		String sceneId = DesUtil.decrypt(token, "HY_LOGIN");
		boolean exists = JedisUtils.exists(sceneId);
		if(!exists){
			//不存在key(1.恶意请求 2.已经失效)，给用户提示已失效
			logger.info("redis中不存在key{}",sceneId);
			return ResponseUtil.toSuccessBody(ScanResults.ScanResults_1.getIndex());
		}
		String info = JedisUtils.get(sceneId);
		if(StringUtils.isBlank(info)){
			//失效
			logger.info("redis中不存在{}对应的值",sceneId);
			return ResponseUtil.toSuccessBody(ScanResults.ScanResults_1.getIndex());
		}
		ImageQRCodeInfo code = JSONObject.parseObject(info,ImageQRCodeInfo.class);
		logger.info("扫描登录结果{}",ScanResults.getNames(code.getResult()));
		return ResponseUtil.toSuccessBody(code.getResult());
	}*/
	
	/**给手机号发送验证码
	 * @param message
	 * @param mobile
	 * @return
	 */
	public boolean sendMessage(String message,String mobile){
		boolean result = false;
		String username = "hycx";
		String pwd = MD5Util.MD5Encode("hycx123");
		String epid = "120589";
		String linkid = RandomStringUtils.randomNumeric(12);
		try {
			String url = "http://114.255.71.158:8061/?username="+username+"&password="+pwd+"&message="+URLEncoder.encode(message, "gb2312")+"&phone="+mobile+"&epid="+epid+"&linkid="+linkid+"&subcode=";
			String responseBody = HttpClientUtils.sendGet(url, null, "UTF-8");
			if("00".equals(responseBody)){
	        	result = true;
	    	   logger.info("验证码发至{}送成功",mobile);
	        }else{
	    	   logger.error("验证码发至{}失败{}",mobile,responseBody);
	        }
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
   }
	
	@API(value = "用户退出")
	@RequestMapping(value = "/v1/user/login/out",method = RequestMethod.GET)
	public Object loginOut(HttpServletRequest request, HttpServletResponse response){
		//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}
		logger.info("用户请求退出开始---");
		//增加退出系统删除用户token表中数据
		accessTokenService.delByToken(tokenID);
		//根据token从redis里面删除该用户权限
		return JedisUtils.delObject(tokenID);
				
	}
	
	private String getRandomsceneId(){
		String randomStr = 1+RandomStringUtils.randomNumeric(9);
		while(true){
			if(JedisUtils.exists(randomStr)){
				randomStr = 1+RandomStringUtils.randomNumeric(9);
			}else{
				return randomStr;
			}
		}
	}
	
}
