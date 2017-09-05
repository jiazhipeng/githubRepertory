package com.hy.gcar.web.wechat.login;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.common.utils.CookieUtils;
import com.hy.common.utils.DateUtils;
import com.hy.common.utils.HttpClientUtils;
import com.hy.common.utils.JedisUtils;
import com.hy.common.utils.MD5Util;
import com.hy.constant.Constant;
import com.hy.gcar.entity.Captcha;
import com.hy.gcar.entity.Card;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.card.CardService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.modules.springframework.data.jpa.domain.Results;
import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.utils.WeiXinUtils;



@Controller
@RequestMapping("/wechat/login")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private CardService cardService;

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		String openidKey=Constant.load.getProperty("openidKey");
//		String openid = CookieUtils.getCookie(request, openidKey);
		CookieUtils.setCookie(response, openidKey, "");
		return "redirect:/wechat/carsshow/toCarsList";
	}
	
	
	/**判断用户是否登录
	 * @param member
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/checkLogin")
	@ResponseBody
	public   Map<String,Object> checkLogin(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String openidKey=Constant.load.getProperty("openidKey");
		String openid = CookieUtils.getCookie(request, openidKey);
		if(StringUtils.isBlank(openid)){
			resultMap.put("isLogin", false);
			return resultMap;
		}
		Member  m = memberService.findMemberByOpenID(openid);
		if (m==null||StringUtils.isBlank(m.getMobile())) {
			resultMap.put("isLogin", false);
			return resultMap;
		}
		//销售名片判断
		Card card = new Card();
		card.setCardMobile(m.getMobile());
		card.setStatus(0);
		List<Card> cardList = this.cardService.selectByCondition(card);
		if(CollectionUtils.isNotEmpty(cardList)){
			//销售名片
			resultMap.put("isCard", true);
		}else{
			//销售名片
			resultMap.put("isCard", false);
		}
		
		resultMap.put("isLogin", true);
		resultMap.put("member", m);
		resultMap.put("memberId", m.getId());
		return resultMap;
	}
	
	/**判断用户是否登录
	 * @param member
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getMember")
	@ResponseBody
	public  Object checkLogin(Long memberId) throws Exception{
		
		Member  m = memberService.findMemberByID(memberId);
		if(null!=m){
			return m;
		}
		return null;
	}
	
	
	
	@RequestMapping(value = "/getcaptcha")
	@ResponseBody
	public Results getCaptcha(String mobile){
		String key = "captcha_"+mobile;
		try{
			Captcha captcha = new Captcha();//(Captcha)JedisUtils.getObject(key);
			String curCode = RandomStringUtils.randomNumeric(6);
			String info = "验证码："+curCode+"(10分钟内有效，如非本人操作请忽略)";
			boolean flag = sendMessage(info,mobile);
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
				return new Results(false,"出现异常，请稍后再试！");
			}
			return new Results(true,"验证码发送成功");
		}catch(Exception e){
			logger.error("验证码发送至{}失败",mobile,e);
		}
		return new Results(false,"出现异常，请稍后再试！");
	}
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(String mobile,String openid,String code1,String name,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String openidKey = Constant.load.getProperty("openidKey");
		boolean messageFlag = Boolean.parseBoolean(Constant.APPLICATION_LOAD.getProperty("message_check_flag"));
		String key = "captcha_" + mobile;
		//1.验证码的时效性、准确性
		try{
			if(messageFlag){
				Captcha captcha = (Captcha)JedisUtils.getObject(key);
				if(null == captcha || !captcha.getCode().equals(code1)){
					logger.info("redis中不存在{}",key);
					return new Results(false,"验证码不正确！");
				}else{
					Date end = DateUtils.addMinutes(captcha.getCreateDate(), 10);
					if(end.before(new Date()) || captcha.isUsed()){//是否失效
						return new Results(false,"验证码已失效，请重新获取验证码");//"没有失效，已经可用";
					}
				}
				captcha.setUsed(true);
				JedisUtils.setObject(key, captcha, 0);
			}
			//判断openid是否为空
			if(StringUtils.isBlank(openid)){
				return new Results(false,"请先关注微信号，再登录！");
			}
			
			Member member = new Member();
			member.setOpenid(openid);
			member.setMobile(mobile);
			if(StringUtils.isNotBlank(name)){
				member.setName(name);
			}else{
				member.setName("极车会员");
			}
			member.setImageQrcode(request.getServletContext().getRealPath("/"));
			member = memberService.memberLoginFromWechat(member);
			if(member.isSuccess()){
				CookieUtils.setCookie(response, openidKey, openid);
			}
			
			MemberItem memberItem =  new MemberItem();
			memberItem.setMemberId(member.getId());
			List<MemberItem> mplist = memberItemService.selectByCondition(memberItem);
			if(CollectionUtils.isEmpty(mplist)){
				 MemberItemShare share = new MemberItemShare();
				 share.setMemberId(member.getId());
				 List<MemberItemShare> shareList = memberItemShareService.selectByCondition(share );
				 if(CollectionUtils.isNotEmpty(shareList)){
					 return new Results(member.isSuccess(),member.getMessage(),"/wechat/mycars/mycarsList?memberId="+member.getId());
				 }
			 }else{
				 return new Results(member.isSuccess(),member.getMessage(),"/wechat/mycars/mycarsList?memberId="+member.getId());
			 }
			
			return new Results(member.isSuccess(),member.getMessage(),"/wechat/carsshow/toCarsList?memberId="+member.getId());
			
		}catch(Exception e){
			logger.error("{}登陆出现异常",mobile,e);
		}
		return new Results(false,"登录失败");
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toLogin(HttpServletRequest request,HttpServletResponse response,String code,ModelMap map){
		String openidKey=Constant.load.getProperty("openidKey");
		String openid = CookieUtils.getCookie(request, openidKey);
//		 openid = "oFCx8wJtYJo4TYTRwz8JAukTNWTQ";
		try {
			if(StringUtils.isBlank(openid)){
				if(StringUtils.isBlank(code)){
					String url_prefix = Constant.load.getProperty("url_prefix");
					String newurl = URLEncoder.encode(url_prefix + "/wechat/login", "UTF-8");
					String redirectPath = WeiXinUtils.getUrlBySnsapibase(newurl);
					return "redirect:" + redirectPath;
				}
				W3AccessToken  acctoken  =    WeiXinUtils.getAccToken(code);
				openid = acctoken.getOpenid();    
//				 openid = "oFCx8wJtYJo4TYTRwz8JAukTNWTQ";
				//CookieUtils.setCookie(response, openidKey, openid,30*60*1000);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		map.put("openid",openid);
		return "index";
	}
	
	/*public boolean sendMessage(String message,String mobile){
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
   }*/
	public boolean sendMessage(String message,String mobile){
		boolean result = false;
		String userId = "JI1093";
		String password = "102102";
		String pszMobis = mobile;
		String pszMsg = message;
		String iMobiCount="1";
		String pszSubPort="*";
		String MsgId=RandomStringUtils.randomNumeric(19);
		try {
			String url = "http://101.251.214.153:8901/MWGate/wmgw.asmx/MongateSendSubmit?userId="+userId+"&password="+password+"&pszMobis="+pszMobis+"&pszMsg="+pszMsg+"&iMobiCount="+iMobiCount+"&pszSubPort="+pszSubPort+"&MsgId="+MsgId;
			String responseBody = HttpClientUtils.sendGet(url, null, "UTF-8");
			logger.info("调用短信接口======================返回值===="+responseBody);
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-5168371777020179491</string>
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-12</string>
			 if(StringUtils.isNotBlank(responseBody)){
				 String sub=responseBody.substring(74, responseBody.lastIndexOf("</string>"));
				 logger.info("截取返回值================="+sub);
				 if(sub.length()>15){//返回值长度大于15则表示成功
					 result = true;
					 logger.info("验证码发至{}送成功",mobile);
				 }else{
					 logger.error("验证码发至{}失败{}",mobile,responseBody);
				 }
			  } 
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
	}
		
}
