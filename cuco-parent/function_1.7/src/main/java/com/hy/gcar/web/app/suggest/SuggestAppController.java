package com.hy.gcar.web.app.suggest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.Suggest;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.suggest.SuggestService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

/**app意见建议controller
 * @author gbw
 */
@Controller
public class SuggestAppController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private SuggestService suggestService;
	
	/**创建意见建议
	 * @param suggest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/gcarapp/android/suggest/create")
	@ResponseBody
	public Object suggestcreateAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		String content = params.getString("content");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(null == memberId || "".equals(memberId)){
			return new OpenResponse("false", "参数异常：创建意见建议,会员ID不能为空！", null, "100001");
		}
		if(null == content || "".equals(content)){
			return new OpenResponse("false", "参数异常:创建意见建议,建议内容不能为空！", null, "100001");
		}
		try {
			this.logger.info("创建意见建议---IOS");
			Suggest suggest = new Suggest();
			Member member =memberService.findMemberByID(Long.parseLong(memberId));
			suggest.setContent(content);
			suggest.setMemberName(member.getName());
			suggest.setMobile(member.getMobile());
			suggest.setCreated(new Date());
			suggest = this.suggestService.createSuggest(suggest);
			return new OpenResponse("true", "操作成功", null, "100000");
		} catch (Exception e) {
			this.logger.error("创建意见建议失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
	
	}
	
	/**创建意见建议
	 * @param suggest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/gcarapp/ios/suggest/create")
	@ResponseBody
	public Object suggestcreateIOS(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		String content = params.getString("content");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(null == memberId || "".equals(memberId)){
			return new OpenResponse("false", "参数异常：创建意见建议,会员ID不能为空！", null, "100001");
		}
		if(null == content || "".equals(content)){
			return new OpenResponse("false", "参数异常:创建意见建议,建议内容不能为空！", null, "100001");
		}
		try {
			this.logger.info("创建意见建议---IOS");
			Suggest suggest = new Suggest();
			Member member =memberService.findMemberByID(Long.parseLong(memberId));
			suggest.setContent(content);
			suggest.setMemberName(member.getName());
			suggest.setMobile(member.getMobile());
			suggest.setCreated(new Date());
			suggest = this.suggestService.createSuggest(suggest);
			return new OpenResponse("true", "操作成功", null, "100000");
		} catch (Exception e) {
			this.logger.error("创建意见建议失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
	}
}
