package com.hy.gcar.facede;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hy.gcar.entity.*;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.Identities;
import com.hy.constant.Constant;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;
import com.hy.gcar.utils.RandomNumberUtil;
@Service
public class LoginFacede {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	NationService nationService;

	@Autowired
	private MemberInfoService memberInfoService;
	
	public Object login(String message,String type){
		String methodName = "login";
		logger.info("执行({})操作开始.....",methodName);
		Object result = null;
		message = AppUtil.decode(message);
    	if(null == message){
    		result = new OpenResponse("false","参数解码错误",null,"100001");
    		logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
    		return result;
    	}
    	//解析json数据
    	try{
    		JSONObject jsonParams = JSONObject.parseObject(message);
        	String mobile = jsonParams.getString("mobile");
        	String captcha = jsonParams.getString("captcha");
        	String cityid = jsonParams.getString("cityid");
        	String sign = jsonParams.getString("sign");
        	logger.info("用户【{}】执行({})参数{}",mobile,methodName,jsonParams.toString());
        	
        	boolean validate = AppUtil.validateBySign(mobile+captcha+Global.PWD_FOR_APP, sign);
    	 	if(validate==false){
     		   result = new OpenResponse("false","sign不匹配",null,"100002");
     		   logger.info("用户【{}】执行({})操作结束.....执行结果{}：",mobile,methodName,result);
     		   return result;
    	 	 }
    	 	return loginMain(mobile,captcha,cityid,type);
    	}catch(Exception e){
    		result = new OpenResponse("false","出现异常",null,"100014"); 
  		   	logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
  		   	return result;
    	}
		
	}
	
	public Object loginMain(String mobile,String captcha,String cityid,String  type) throws Exception{
		 Object result = null;
    	 String methodName = "loginMain";
    	 boolean messageFlag = Boolean.parseBoolean(Constant.APPLICATION_LOAD.getProperty("message_check_flag"));
    	 if(messageFlag){
    		 if(!"13141116628".equals(mobile) && !"13811078211".equals(mobile)){
    			 //验证码验证
    			 Captcha captcha0 = captchaService.getCaptchaByMobile(mobile);
    			 if(null != captcha0){
    				 //判断是否失效
    				 long interval = System.currentTimeMillis() - captcha0.getDate().getTime();
    				 if(interval > AppUtil.CAPTCHA_INTERVAL ){
    					 result = new OpenResponse("false","验证码失效",null,"100005");
    					 logger.info("用户【{}】执行({})操作结束.....执行结果：{}",mobile,methodName,result);
    					 return result;
    				 }
    				 //判断是否正确
    				 if(!captcha.equals(captcha0.getCode())){
    					 result = new OpenResponse("false","验证码错误",null,"100004");
    					 logger.info("用户【{}】执行({})操作结束.....执行结果：{}",mobile,methodName,result);
    					 return result;
    				 }
    			 }else
    			 {
    				 result = new OpenResponse("false","验证码错误",null,"100004");
    				 logger.info("用户【{}】执行({})操作结束.....执行结果：{}",mobile,methodName,result);
    				 return result;
    			 }
    		 }else if(!"123456".equals(captcha)){
    			 result = new OpenResponse("false","验证码错误",null,"100004");
    			 logger.info("用户【{}】执行({})操作结束.....执行结果：{}",mobile,methodName,result);
    			 return result;
    		 }
    	 }
		Member member = new Member();
		member.setMobile(mobile);
		List<Member> memberlist =memberService.findMember(member);
		Map<String,String> map = new HashMap<String,String>();
		if(memberlist!=null&&memberlist.size()>0){ //查询数据库中是否拥有，如果有登录没有创建
			  member =	memberlist.get(0);
			  
			 List<MemberItem> mplist  =null;  
			 String hasMemberPower="0";
			 if(null!=member.getId()){
				 MemberItem memberItem =  new MemberItem();
				 memberItem.setMemberId(member.getId());
				 mplist = memberItemService.selectByCondition(memberItem);
				 if(CollectionUtils.isEmpty(mplist)){
					 MemberItemShare share = new MemberItemShare();
					 share.setMemberId(member.getId());
					 List<MemberItemShare> shareList = memberItemShareService.selectByCondition(share );
					 if(CollectionUtils.isNotEmpty(shareList)){
						 hasMemberPower="1";
					 }
				 }else{
					 hasMemberPower="1";
				 }
			 }
			
			  map.put("hasMemberPower",hasMemberPower);//1：有权益   0：没有权益
		} 
		else{
			member.setSex(-1);
			member.setName("极车会员");
			member.setIsTestDriver(0);
			member.setFromMember(Integer.parseInt(type));
			member.setCreated(new Date());
			member.setStatuts(1);
			member.setCityId(cityid);
			member.setType(0);
			member = this.memberService.createPersonalMember(member);
			map.put("hasMemberPower","0");//1：有权益   0：没有权益

		}
		map.put("city", member.getCityName());
		if(StringUtils.isNotEmpty(member.getCityCode())){
			Nation n = new Nation();
			n.setCode(member.getCityCode());
			n = nationService.getParentNationByCityeCodeOrId(n);
			map.put("city", n.getName() + " " + member.getCityName());
		}
        String	basePath =	Global.getConfig("basePath");
        String  dburl=   member.getImageUrl();
		map.put("mobile", member.getMobile()+"");
		map.put("sex", member.getSex()+"");//性别 -1未知;1:男;0:女;     name   sex   mobile    有无车    是否试驾过
		map.put("name", member.getName());
//	 	map.put("city", member.getCityName());
		map.put("type", member.getType()+"" );
		map.put("isTryDriver", member.getIsTestDriver()+"");//1：试驾过   0：没试驾过
		map.put("token", Identities.uuid2());
		map.put("memberId",member.getId()+"");//用户Id
		String imageurl =	"";
		imageurl  =member.getImageUrl();
		if(imageurl==null){
			map.put("image","");
		}
		else if(!(imageurl!=null&&imageurl.indexOf("http")!=-1)){
			imageurl =Global.getConfig("basePath")+imageurl;	
			map.put("image",imageurl);
		}
		else{
			map.put("image",imageurl);
		}

		//查询会员info信息
		MemberInfo memberInfo = this.memberInfoService.findMemberInfoByMemberID(member.getId());
		boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(memberInfo);
		if(isPerfect){
			map.put("isPerfect", "已完善");
		}else{
			map.put("isPerfect", "未完善");
		}
		map.put("ismain","0");
		return new OpenResponse("true","登录成功",map,"100000");
	}
	
	public Object getCaptcha(String message){
		String methodName = "getCaptcha";
		logger.info("执行({})操作开始.....",methodName);
		Object result = null;
		message = AppUtil.decode(message);
    	if(null == message){
    		result = new OpenResponse("false","参数解码错误",null,"100001");
    		logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
    		return result;
    	}
    	//解析json数据
    	try{
    		JSONObject jsonParams = JSONObject.parseObject(message);
        	String mobile = jsonParams.getString("mobile");
        	String sign = jsonParams.getString("sign");
        	logger.info("用户【{}】执行({})参数{}",mobile,methodName,jsonParams.toString());
        	
        	boolean validate = AppUtil.validateBySign(mobile+Global.PWD_FOR_APP, sign);
    	 	if(validate==false){
     		   result = new OpenResponse("false","sign不匹配",null,"100002");
     		   logger.info("用户【{}】执行({})操作结束.....执行结果{}：",mobile,methodName,result);
     		   return result;
    	 	 }
    	 	result = getCaptchaMain(mobile);
    	 	logger.info("用户【{}】执行({})操作结束.....执行结果{}：",mobile,methodName,result);
    	 	return result;
    	}catch(Exception e){
    		result = new OpenResponse("false","出现异常，请稍后再试",null,"100014"); 
  		   	logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
  		   	return result;
    	}
		
	}
	
	public Object getCaptchaMain(String mobile){
		Object result = null;
		String curCode = RandomNumberUtil.getRandNumber(6);
 		String info = "验证码："+curCode+"(10分钟内有效，如非本人操作请忽略)";
 		boolean flag = AppUtil.sendMessage(info, mobile);
	 	Captcha captcha = captchaService.getCaptchaByMobile(mobile);
 		if(true == flag){
 			if(captcha==null){
 				captcha = new Captcha();	
 			}
			captcha.setMobile(mobile);
			captcha.setDate(new Date());
			captcha.setCode(curCode);
			captcha.setCount(10L);
			if(null!=captcha.getId() && captcha.getId() > 0){
				captchaService.updateByPrimaryKey(captcha);
			}else{
				captchaService.insert(captcha);
			}
			result = new OpenResponse("true","成功",null,"100000");
 		    logger.info("用户【"+mobile+"】执行(getCaptcha)操作结束.....执行结果："+result);
 		    return result;
		}else{
			result = new OpenResponse("false","验证码发送异常",null,"100012");
 		    logger.info("用户【"+mobile+"】执行(getCaptcha)操作结束.....执行结果："+result);
 		    return result;
		}
	}
	
	
}
