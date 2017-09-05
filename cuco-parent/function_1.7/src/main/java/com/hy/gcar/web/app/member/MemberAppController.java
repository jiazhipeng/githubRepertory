package com.hy.gcar.web.app.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.StringUtils;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.service.member.MemberAppService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

@Controller
public class MemberAppController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberAppService memberAppService;
	@Autowired
	private NationService nationService;
	
	/**
	 * 查看会员详情接口
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/android/memberInfo")
	@ResponseBody
	public OpenResponse getMemberInfo(@RequestParam(value = "message", required = true) String message){
		logger.info("查看会员信息---android");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			return new OpenResponse("false", "sign不匹配", null, "100002");
			
		}
		if(null == memberId || "".equals(memberId)){
			return new OpenResponse("false", "参数异常：查询会员详情,会员ID不能为空！", null, "100001");
			
		}
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			//查询用户信息
			Member member = this.memberService.findMemberByID(Long.parseLong(memberId));
			resultMap.put("id", member.getId()+"");
			resultMap.put("city", member.getCityName());
			resultMap.put("imageUrl", member.getImageUrl());
			resultMap.put("mobile", member.getMobile());
			resultMap.put("name", member.getName());
			resultMap.put("sex", member.getSex()+"");
			//查询会员info信息
			MemberInfo memberInfo = this.memberInfoService.findMemberInfoByMemberID(member.getId());
			boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(memberInfo);
			if(isPerfect){
				resultMap.put("isPerfect", "已完善");
			}else{
				resultMap.put("isPerfect", "未完善");
			}

			return new OpenResponse("true", "操作成功", resultMap, "100000");
		} catch (Exception e) {
			this.logger.error("查询用户信息失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		
	}
	
	/**
	 * 查看会员详情接口
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/ios/memberInfo")
	@ResponseBody
	public OpenResponse getMemberInfoIOS(@RequestParam(value = "message", required = true) String message){
		return this.getMemberInfo(message);
		
	}
	
	/**
	 * 修改个人信息
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/android/update/memberInfo")
	@ResponseBody
	public OpenResponse updateMemberAndroid(@RequestParam(value = "message", required = true) String message){
		logger.info("修改会员信息---android");
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		String name = params.getString("name");
		String sex = params.getString("sex");
		String cityCode = params.getString("cityCode");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(memberId + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(null == memberId || "".equals(memberId)){
			return new OpenResponse("false", "参数异常：修改会员信息,会员ID不能为空！", null, "100001");
		}
		try {
			Member member = new Member();
			member.setName(name);
			member.setId(Long.parseLong(memberId));
			if(StringUtils.isNotEmpty(sex)){
				member.setSex(Integer.parseInt(sex));
			}
			if(StringUtils.isNotEmpty(cityCode)){
				Nation n = nationService.getNationByCityCode(cityCode);
				if(n == null){
					return new OpenResponse("false", "城市代码不存在", null, "100014");
				}
				member.setCityCode(n.getCode());
				member.setCityName(n.getName());
				member.setCityId(n.getId().toString());
			}
			Member m = this.memberService.updatePersonalMember(member);
			
			return memberAppService.getverificationMain(m.getMobile());
		} catch (Exception e) {
			this.logger.error("修改用户信息失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
	}
	
	/**
	 * 修改个人信息
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/ios/update/memberInfo")
	@ResponseBody
	public OpenResponse updateMemberIOS(@RequestParam(value = "message", required = true) String message){
		return this.updateMemberAndroid(message);
	}
	
	
	/**
	 * 上传头像
	 * @param request
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/android/upload/member/headpicture")
	@ResponseBody
	public OpenResponse uploadMemberHeadPictureForAndroid(HttpServletRequest request,@RequestParam(value = "message", required = true) String message){
		System.out.println(message);
        if(StringUtils.isBlank(message)){
        	return new OpenResponse("false", "参数", null, "100020");
        }
		message = AppUtil.decode(message);
        JSONObject object = JSONObject.parseObject(message);
        String memberId = object.getString("memberId");
		String sign = object.getString("sign");
		boolean validate = AppUtil.validateBySign(memberId + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
        try {
			Member member = memberAppService.upload(request);
			member.setId(Long.valueOf(memberId));
			memberService.updatePersonalMember(member);
			Map<String,Object> map = Maps.newHashMap();
		    map.put("imageUrl", member.getImageUrl());
		    map.put("memberId", memberId);
		    return new OpenResponse("true", "头像上传成功", map, "100000");
		} catch (Exception e) {
			e.printStackTrace();
		    return new OpenResponse("false", "头像上传失败", null, "100011");
		}
	}
	
	
	/**
	 * 上传头像
	 * @param request
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/ios/upload/member/headpicture")
	@ResponseBody
	public OpenResponse uploadMemberHeadPictureForIOS(HttpServletRequest request,@RequestParam(value = "message", required = true) String message){
		return this.uploadMemberHeadPictureForAndroid(request,message);
	}
}
