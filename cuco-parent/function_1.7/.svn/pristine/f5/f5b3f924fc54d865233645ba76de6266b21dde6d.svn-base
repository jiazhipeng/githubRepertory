package com.hy.gcar.web.app.memberPower;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.DesUtil;
import com.hy.constant.Constant;

/**会员权益意见建议controller
 * @author gbw
 */
@Controller
public class MemberPowerAppController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	String POWER_H5=Constant.load.getProperty("power_app");
	/**进入会员权益-安卓
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/memberPower/create")
	public String toAboutAndroid(HttpServletRequest request){
		logger.info("进入会员权益---android");
		String mobile = "";
		String name = "";
		String source = "";
		if(null!=request.getParameter("mobile")){
			mobile = request.getParameter("mobile");
		}
		if(null!=request.getParameter("name")){
			name = request.getParameter("name");
		}
		if(null!=request.getParameter("source")){
			source = request.getParameter("source");
		}
//		Map<String,String> map = Maps.newHashMap();
//		map.put("mobile", mobile);
		if("极车会员".equals(name)){
			name="";
		}
		/*map.put("name", name);
		map.put("orderFrom","2");
		String jsonString = JSONObject.toJSONString(map);
		logger.info("提交销售意参数向加密前json======"+jsonString);
		String key="hycx!@#$";
		String encrypt = "";
		String encode = "";*/
		try {
//			encrypt = DesUtil.encrypt(jsonString, key);
//			logger.info("==========提交销售意向参数加密后json=========="+encrypt);
			name = URLEncoder.encode(name,"UTF-8");
//			logger.info("==========提交销售意向参数encode后=========="+encode);
		} catch (Exception e) {
			this.logger.error("提交异常");
		}
		return "redirect:"+POWER_H5+"?mobile="+mobile+"&name="+name+"&source="+source;
	}
	
	/**进入会员权益-IOS
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/memberPower/create")
	public String toAboutIOS(HttpServletRequest request){
		logger.info("进入会员权益---IOS");
		String mobile = "";
		String name = "";
		String source = "";
		if(null!=request.getParameter("mobile")){
			mobile = request.getParameter("mobile");
		}
		if(null!=request.getParameter("name")){
			name = request.getParameter("name");
		}
		if(null!=request.getParameter("source")){
			source = request.getParameter("source");
		}
//		Map<String,String> map = Maps.newHashMap();
//		map.put("mobile", mobile);
		if("极车会员".equals(name)){
			name="";
		}
//		map.put("name", name);
//		map.put("orderFrom","3");
//		String jsonString = JSONObject.toJSONString(map);
//		logger.info("提交销售意参数向加密前json======"+jsonString);
		/*String key="hycx!@#$";
		String encrypt = "";
		String encode = "";*/
			try {
//			encrypt = DesUtil.encrypt(jsonString, key);
//			logger.info("==========提交销售意向参数加密后json=========="+encrypt);
			name = URLEncoder.encode(name,"UTF-8");
//			logger.info("==========提交销售意向参数encode后=========="+encode);
		} catch (Exception e) {
			this.logger.error("提交异常");
		}
//		return "redirect:"+POWER_H5;
		return "redirect:"+POWER_H5+"?mobile="+mobile+"&name="+name+"&source="+source;
	}
}
