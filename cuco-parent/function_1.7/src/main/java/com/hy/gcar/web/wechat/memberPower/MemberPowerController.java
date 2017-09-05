package com.hy.gcar.web.wechat.memberPower;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.CookieUtils;
import com.hy.common.utils.DesUtil;
import com.hy.constant.Constant;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.member.MemberService;

@Controller
@RequestMapping("/wechat/memberPower")
public class MemberPowerController {
	
	@Autowired
	private MemberService memberService;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	String POWER_H5=Constant.load.getProperty("power_app");
	
	
	@RequestMapping(value="/toCreate")
	public String toAboutAndroid(HttpServletRequest request){
		logger.info("进入会员权益---wechat");
		String openidKey=Constant.load.getProperty("openidKey");
		String openid = CookieUtils.getCookie(request, openidKey);
		
		String name = "";
		String mobile = "";
		if(null!=openid && !"".equals(openid)){
			try {
				Member  m = memberService.findMemberByOpenID(openid);
				if(null != m){
					name = m.getName();
					mobile = m.getMobile();
				}
			} catch (Exception e1) {
				this.logger.error("根据openid查询用户错误");
				e1.printStackTrace();
			}
		}
		
//		Map<String,String> map = Maps.newHashMap();
//		map.put("mobile", mobile);
		if("极车会员".equals(name)){
			name="";
		}
//		map.put("name", name);
//		map.put("orderFrom","1");//设置来源微信
//		String jsonString = JSONObject.toJSONString(map);
//		logger.info("提交销售意参数向加密前json======"+jsonString);
//		String key="hycx!@#$";
//		String encrypt = "";
//		String encode = "";
		try {
//			encrypt = DesUtil.encrypt(jsonString, key);
//			logger.info("==========提交销售意向参数加密后json=========="+encrypt);
			name = URLEncoder.encode(name,"UTF-8");
//			logger.info("==========提交销售意向参数encode后=========="+encode);
		} catch (Exception e) {
			this.logger.error("提交异常");
		}
		
		return "redirect:"+POWER_H5+"?mobile="+mobile+"&name="+name+"&source=1";
	}
}
