package com.hy.gcar.web.app.personalCenter;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

/**关于社区controller
 * @author gongbw
 *
 */
@Controller
public class PersonalCenterAppController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	
	/**进入关于公社-安卓
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/personalCenter/toAbout")
	public String toAboutAndroid(){
		/*String result = "";
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			result = new OpenResponse("false", "sign不匹配", null, "100002").toJson();
			this.logger.info("解密错误---");
			return result;
		}
		if(null == memberId || "".equals(memberId)){
			result = new OpenResponse("false", "参数异常：创建意见建议,会员ID不能为空！", null, "100001").toJson();
			return result;
		}
		
		try {
			this.logger.info("进入关于公社---Android");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", memberId);
			//加入关于我们的H5链接地址
			map.put("aboutUs_URL", "http://www.1mobility.cn/about_v1/index.html");
			result = new OpenResponse("true", "操作成功", map, "100000").toJson();
		} catch (Exception e) {
			this.logger.error("创建意见建议失败--"+e);
			result = new OpenResponse("false", "操作异常", null, "100014").toJson();
		}
		
		return result;*/
		return "redirect:http://www.gcarclub.com/about_v1/index.html" ;
	}
	
	/**进入关于公社-IOS
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/personalCenter/toAbout")
	public String toAboutIOS(){
		/*String result = "";
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			result = new OpenResponse("false", "sign不匹配", null, "100002").toJson();
			this.logger.info("解密错误---");
			return result;
		}
		if(null == memberId || "".equals(memberId)){
			result = new OpenResponse("false", "参数异常：创建意见建议,会员ID不能为空！", null, "100001").toJson();
			return result;
		}
		
		try {
			this.logger.info("进入关于公社---Android");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", memberId);
			//加入关于我们的H5链接地址
			map.put("aboutUs_URL", "http://www.1mobility.cn/about_v1/index.html");
			result = new OpenResponse("true", "操作成功", map, "100000").toJson();
		} catch (Exception e) {
			this.logger.error("创建意见建议失败--"+e);
			result = new OpenResponse("false", "操作异常", null, "100014").toJson();
		}
		
		return result;*/
		return "redirect:http://www.gcarclub.com/about_v1/index.html" ;
	}
}
