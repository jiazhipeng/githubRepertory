package com.hy.gcar.web.app.main;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.Version;
import com.hy.gcar.service.member.MemberAppService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.service.version.VersionService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

/**
 * Created by 海易出行 on 2016/5/24.
 */
@Controller
public class MainController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberAppService memberAppService;

	@Autowired
	private NationService nationService;
	@Autowired
	private VersionService versionService;
	/**
	 * @param 验证人员信息
	 * @return
	 * @author auto
	 */
	@RequestMapping("/gcarapp/ios/mainfacede/verification")
	@ResponseBody
	public Object verificationForIOS(@RequestParam(value = "message", required = true) String message) {
		// 1. 校验sign
		String methodName = "verification";
		logger.info("执行({})操作开始.....", methodName);
		Object result = null;
		message = AppUtil.decode(message);
		if (null == message) {
			result = new OpenResponse("false", "参数解码错误", null, "100001");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			return result;
		}
		try {
			JSONObject jsonParams = JSONObject.parseObject(message);
			String mobile = jsonParams.getString("mobile");
			String cityCode = jsonParams.getString("cityCode");
			String sign = jsonParams.getString("sign");
			logger.info("执行({})参数{}", methodName, jsonParams.toString());
			boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				result = new OpenResponse("false", "sign不匹配", null, "100002");
				logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
				return result;
			}
			//执行cityCode 操作
			setCityInfo(cityCode,mobile);
			// 2. 组织业务
			return memberAppService.getverificationMain(mobile);
		} catch (Exception e) {
			result = new OpenResponse("false", "出现异常", null, "100014");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			logger.error(methodName + "error:" + e.getMessage());
			return result;
		}
	}

	/**
	 * 设置城市信息
	 * @throws Exception 
	 */
	public void setCityInfo(String cityCode,String mobile) throws Exception{
		if(StringUtils.isEmpty(cityCode)){
			return ;
		}
		Nation nation = nationService.getNationByCityCode(cityCode);
		if(nation == null){
			return;
		}
		Member member = new Member();
		member.setMobile(mobile);
		List<Member> list = memberService.findMember(member);// 查找用户
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		member = list.get(0);
		if(StringUtils.isEmpty(member.getCityCode())){
			Member m = new Member();
			m.setId(member.getId());
			m.setCityCode(nation.getCode());
			m.setCityId(nation.getId().toString());
			m.setCityName(nation.getName());
			memberService.updatePersonalMember(m);
		}	
		
	}

	/**
	 * @param 验证人员信息android调用类
	 * @return
	 * @author auto
	 */
	@RequestMapping("/gcarapp/android/mainfacede/verification")
	@ResponseBody
	public Object verificationForAndroid(@RequestParam(value = "message", required = true) String message) {
		return this.verificationForIOS(message);
	}

	
	/**
	 * @param 获取版本信息方法（IOS）
	 * @return
	 * @author auto
	 */
	@RequestMapping("/gcarapp/ios/mainfacede/version")
	@ResponseBody
	public Object versionForIOS(@RequestParam(value = "message", required = true) String message) {
		// 1. 校验sign
		String methodName = "version";
		logger.info("执行({})操作开始.....", methodName);
		Object result = null;
		message = AppUtil.decode(message);
		if (null == message) {
			result = new OpenResponse("false", "参数解码错误", null, "100001");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			return result;
		}
		try {
			JSONObject jsonParams = JSONObject.parseObject(message);
			String mobile = jsonParams.getString("mobile");
			String sign = jsonParams.getString("sign");
			String code = jsonParams.getString("code");
//			String client = jsonParams.getString("client");
			Integer client = 1;
			logger.info("执行({})参数{}", methodName, jsonParams.toString());
			boolean validate = AppUtil.validateBySign(code + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				result = new OpenResponse("false", "sign不匹配", null, "100002");
				logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
				return result;
			}
			// 2. 组织业务
			return getVersionMain(mobile, code, client);

		} catch (Exception e) {
			result = new OpenResponse("false", "出现异常", null, "100014");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			logger.error(methodName + "error:" + e.getMessage());
			return result;
		}
	}

	/**
	 * @param 获取版本信息方法（android）
	 * @return
	 * @author auto
	 */
	@RequestMapping("/gcarapp/android/mainfacede/version")
	@ResponseBody
	public Object versionForAndroid(@RequestParam(value = "message", required = true) String message) {
		// 1. 校验sign
		String methodName = "version";
		logger.info("执行({})操作开始.....", methodName);
		Object result = null;
		message = AppUtil.decode(message);
		if (null == message) {
			result = new OpenResponse("false", "参数解码错误", null, "100001");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			return result;
		}
		try {
			JSONObject jsonParams = JSONObject.parseObject(message);
			String mobile = jsonParams.getString("mobile");
			String sign = jsonParams.getString("sign");
			String code = jsonParams.getString("code");
			Integer client = 0;
			logger.info("执行({})参数{}", methodName, jsonParams.toString());
			boolean validate = AppUtil.validateBySign(code + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				result = new OpenResponse("false", "sign不匹配", null, "100002");
				logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
				return result;
			}
			// 2. 组织业务
			return getVersionMain(mobile, code, client);

		} catch (Exception e) {
			result = new OpenResponse("false", "出现异常", null, "100014");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			logger.error(methodName + "error:" + e.getMessage());
			return result;
		}
	}
	/**
	 * 版本更新主方法
	 * 
	 * @param mobile
	 * @param code
	 * @param client
	 * @return
	 */
	public Object getVersionMain(String mobile, String code, Integer client) {
		String methodName = "getVersionMain";
		Object result = null;
		// 解析json数据
		try {
			int c = Integer.parseInt(code);
			HashMap<String, String> hm = new HashMap<String, String>();
			List<Version> isonlist = null;
			Version version = null;
			isonlist = versionService.selectVersinByFlag(client);
			if (client ==1) {//IOS
				if (isonlist != null && isonlist.size() > 0) {
					version = isonlist.get(0);
					hm.put("describe", version.getDescribe());
					hm.put("name", version.getName());
					hm.put("forceCode", version.getForceCode());
					OpenResponse openResponse = new OpenResponse("true", "ok", hm, "100000");
					return openResponse;
				}
			}
			
			if (isonlist != null && isonlist.size() > 0) {
				version = isonlist.get(0);
				//	如果forcCode >code force为1 
				
				Integer forceCode = Integer.parseInt(version.getForceCode());
				if (StringUtils.isNotBlank(code) && forceCode > c) {
					hm.put("force", "1");
					hm.put("name", version.getName());
					hm.put("code", version.getCode() + "");
					hm.put("address", version.getAddress());
					hm.put("describe", version.getDescribe());
					OpenResponse openResponse = new OpenResponse();
					openResponse.setIsSuccess("true");
					openResponse.setResponse(hm);
					openResponse.setCode("100000");
					openResponse.setErrorMessage("获取成功");
					return openResponse;
				} else {
					hm.put("force","0");
					hm.put("name", version.getName());
					hm.put("code", version.getCode() + "");
					hm.put("address", version.getAddress());
					hm.put("describe", version.getDescribe());
					OpenResponse openResponse = new OpenResponse("true", "不用升级", hm, "100000");
					return openResponse;
				}
			} 
			result = new OpenResponse("true", "数据为空", "", "100000");
			
			return result;
		} catch (Exception e) {
			result = new OpenResponse("false", "出现异常", null, "100014");
			logger.info("执行({})操作结束.....执行结果{}：", methodName, result);
			logger.error(methodName + "error:" + e.getMessage());
			return result;
		}
	}
	
	
}
