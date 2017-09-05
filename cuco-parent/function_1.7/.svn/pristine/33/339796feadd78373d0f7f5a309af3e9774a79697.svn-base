package com.hy.gcar.web.app.memberInfo;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hy.gcar.entity.Member;
import com.hy.gcar.service.member.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.service.memberInfo.MemberInfoAppService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

@Controller
@RequestMapping("/gcarapp")
public class MemberInfoAppController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberInfoAppService memberInfoAppService;
	
	/**
	 * 
	 * @Title: uploadMemberInfo 
	 * @Description: 上传会员信息资料图片
	 * @param @param request
	 * @param @param response
	 * @param @param member
	 * @param @return
	 * @param @throws IllegalStateException
	 * @param @throws IOException
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 下午9:15:01 
	 * @throws
	 */
	@RequestMapping(value = "/android/upload/memberinfo")
	@ResponseBody
    public OpenResponse  uploadMemberInfoForAndroid(HttpServletRequest request,@RequestParam(value = "message", required = true) String message){  
		Map<String, String> map = new HashMap<String, String>();
        if(StringUtils.isBlank(message)){
        	return new OpenResponse("false", "参数不允许为空", null, "110000");
        }
        try {
    		message = AppUtil.decode(message);
        	this.logger.info("---------" + message);
	        JSONObject object = JSONObject.parseObject(message);
	        String memberId = object.getString("memberId");
			String flag = object.getString("flag");
			if(StringUtils.isEmpty(flag)){
				return new OpenResponse("false", "请输入请求来源", null, "110001");

			}
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        if(!multipartResolver.isMultipart(request)){
	        	return new OpenResponse("false", "至少选择一张图片进行上传", null, "110001");
	        }
       
			MemberInfo memberInfo = memberInfoAppService.upload(request);
			memberInfo.setMemberId(Long.valueOf(memberId));
			memberInfo.setFlag(flag);
			memberInfo = memberInfoAppService.savePicture(memberInfo);
			map.put("idcardFront", memberInfo.getIdcardFront());
	        map.put("idcardBack", memberInfo.getIdcardBack());
	        map.put("drivercardOriginal", memberInfo.getDrivercardOriginal());
	        map.put("drivercardCopy", memberInfo.getDrivercardCopy());
	        map.put("memberId", memberId);
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			map.put("auditStatus",member.getUseCarApproved()+"");


			return new OpenResponse("true", "资料上传成功", map, "100000");
			
		} catch (Exception e) {
			e.printStackTrace();
	    	return new OpenResponse("false", "资料上传失败", null,"110002");

		}
    }
	
	
	/**
	 * 
	 * @Title: uploadMemberInfo 
	 * @Description: 上传会员信息资料图片
	 * @param @param request
	 * @param @param response
	 * @param @param member
	 * @param @return
	 * @param @throws IllegalStateException
	 * @param @throws IOException
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 下午9:15:01 
	 * @throws
	 */
	@RequestMapping(value = "/ios/upload/memberinfo")
	@ResponseBody
    public Object  uploadMemberInfoForIOS(HttpServletRequest request,@RequestParam(value = "message", required = true) String message){  
		return this.uploadMemberInfoForAndroid(request, message);
    }
	
	/**
	 * 
	 * @Title: findMemberInfoPrictrue 
	 * @Description: 查询会员照片信息
	 * @param @param member
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 下午9:39:08 
	 * @throws
	 */
	@RequestMapping("/android/find/memberinfo/pictrue")
	@ResponseBody
	public OpenResponse findMemberInfoPrictrueForAndroid(@RequestParam(value = "message", required = true) String message){
		System.out.println(message);
        if(StringUtils.isBlank(message)){
        	return new OpenResponse("false", "参数不允许为空", null, "110000");
        }
		try {
			message = AppUtil.decode(message);
			JSONObject params = JSONObject.parseObject(message);
			String memberId = params.getString("memberId");		
			// sign校验
			String sign = params.getString("sign");
			boolean validate = AppUtil.validateBySign(memberId + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				this.logger.info("解密错误---");
				return new OpenResponse("false", "sign不匹配", null, "100002");
			}
			Map<String,Object> obj = Maps.newHashMap();
			Member member = memberService.findMemberByID(Long.valueOf(memberId));

			if(member == null){
				return new OpenResponse("true", "会员不存在", obj, "100002");
			}

			MemberInfo memberInfo = memberInfoService.findMemberInfoByMemberID(Long.valueOf(memberId));
			if(memberInfo == null){
				obj.put("idcardFront", "");
				obj.put("idcardBack", "");
				obj.put("drivercardOriginal","");
				obj.put("drivercardCopy","");
				obj.put("memberId","");
				obj.put("auditStatus","");
				return new OpenResponse("true", "会员资料为空", obj, "100000");
			}
			obj.put("idcardFront", memberInfo.getIdcardFront());
			obj.put("idcardBack", memberInfo.getIdcardBack());
			obj.put("drivercardOriginal", memberInfo.getDrivercardOriginal());
			obj.put("drivercardCopy", memberInfo.getDrivercardCopy());
			obj.put("memberId",memberInfo.getMemberId()+"");
			obj.put("auditStatus",member.getUseCarApproved()+"");
			return new OpenResponse("true", "成功", obj, "100000");

		}  catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "服务器异常", null, "100001");
		}		
	}
	
	
	/**
	 * 
	 * @Title: findMemberInfoPrictrue 
	 * @Description: 查询会员照片信息
	 * @param @param member
	 * @param @return
	 * @return String
	 * @author q.p.x 
	 * @date 2016年8月18日 下午9:39:08 
	 * @throws
	 */
	@RequestMapping("/ios/find/memberinfo/pictrue")
	@ResponseBody
	public OpenResponse findMemberInfoPrictrueForIOS(@RequestParam(value = "message", required = true) String message){
		return this.findMemberInfoPrictrueForAndroid(message);
	}
}
