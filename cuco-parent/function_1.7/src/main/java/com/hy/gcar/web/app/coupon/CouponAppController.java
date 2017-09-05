package com.hy.gcar.web.app.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.Coupon;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.coupon.CouponService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

@Controller
public class CouponAppController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private CouponService couponService;
	/**
	* @Title: getMemberInfo 
	* @Description: TODO(优惠券列表) 
	* @param @param message
	* @param @return    设定文件 
	* @return OpenResponse    返回类型 
	* @throws
	 */
	@RequestMapping("/gcarapp/android/couponInfoList")
	@ResponseBody
	public OpenResponse getCouponInfoList(@RequestParam(value = "message", required = true) String message){
		logger.info("查询优惠券列表---android");
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
			return new OpenResponse("false", "参数异常：查询优惠券列表,会员ID不能为空！", null, "100001");
			
		}
		
		Map<String, Object> obj = new HashMap<String, Object>();
		try{
			this.logger.info("查询会员ID为"+memberId+"的优惠券列表");
			CouponInfo couponInfo = new CouponInfo();
			couponInfo.setMemberId(Long.parseLong(memberId));
			//首先需要获取可用券的数量
			int couponInfoCount = this.couponInfoService.getCouponInfoCountByMemberId(couponInfo);
			//得到该用户下面所有的优惠券信息
			List<CouponInfo> couponInfoList = this.couponInfoService.getCouponInfoListByEnable(couponInfo);
			List<Map<String, Object>> returnList = new ArrayList<>();
			if(couponInfoList.size()>0){
				for (CouponInfo couponInfoView : couponInfoList) {
					Map<String, Object> returnMap = new HashMap<String, Object>();
					returnMap.put("couponInfoId", couponInfoView.getId());
					returnMap.put("name", couponInfoView.getCouponName());
					returnMap.put("statusDisplay", couponInfoView.getStatusDisplay());
					returnMap.put("status", couponInfoView.getStatus());
					if(couponInfoView.getOutTime()!=null){
						returnMap.put("outTime", DateUtils.formatDate(couponInfoView.getOutTime(), null));
					}else{
						returnMap.put("outTime","");
					}
					if( couponInfoView.getGetTime()!=null){
						returnMap.put("getTime",  DateUtils.formatDate(couponInfoView.getGetTime(),null));
					}else{
						returnMap.put("getTime", "");
					}
					returnList.add(returnMap);
				}
			}
			//用于前台返值
			obj.put("couponInfoCount", couponInfoCount);
			obj.put("couponInfoList", returnList);
		}catch(Exception e){
			this.logger.error("查询优惠券明细列表失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		return new OpenResponse("true", "操作成功", obj, "100000");
	}
	
	@RequestMapping("/gcarapp/ios/couponInfoList")
	@ResponseBody
	public OpenResponse getCouponInfoListIos(@RequestParam(value = "message", required = true) String message){
		return this.getCouponInfoList(message);
	}
	
	/**
	* @Title: cleanUpCouponOutTime 
	* @Description: TODO(一键清除优惠券) 
	* @param @param message
	* @param @return    设定文件 
	* @return OpenResponse    返回类型 
	* @throws
	 */
	@RequestMapping("/gcarapp/android/cleanCouponInfo")
	@ResponseBody
	public OpenResponse cleanUpCouponOutTime(@RequestParam(value = "message", required = true) String message){
		logger.info("查询优惠券列表---android");
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
			return new OpenResponse("false", "参数异常：查询优惠券列表,会员ID不能为空！", null, "100001");
		}
		
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			this.logger.info("一键清除开始---");
			CouponInfo couponInfo = new CouponInfo();
			couponInfo.setMemberId(Long.parseLong(memberId));
			this.couponInfoService.removeCouponInfoByOutTime(couponInfo);
			this.logger.info("查询会员ID为"+memberId+"的优惠券列表");
			//首先需要获取可用券的数量
			int couponInfoCount = this.couponInfoService.getCouponInfoCountByMemberId(couponInfo);
			//得到该用户下面所有的优惠券信息
			List<CouponInfo> couponInfoList = this.couponInfoService.getCouponInfoListByEnable(couponInfo);
			List<Map<String, Object>> returnList = new ArrayList<>();
			if(couponInfoList.size()>0){
				for (CouponInfo couponInfoView : couponInfoList) {
					Map<String, Object> returnMap = new HashMap<String, Object>();
					returnMap.put("couponInfoId", couponInfoView.getId());
					returnMap.put("name", couponInfoView.getCouponName());
					returnMap.put("statusDisplay", couponInfoView.getStatusDisplay());
					returnMap.put("status", couponInfoView.getStatus());
					if(couponInfoView.getOutTime()!=null){
						returnMap.put("outTime", DateUtils.formatDate(couponInfoView.getOutTime(), null));
					}else{
						returnMap.put("outTime","");
					}
					if( couponInfoView.getGetTime()!=null){
						returnMap.put("getTime",  DateUtils.formatDate(couponInfoView.getGetTime(),null));
					}else{
						returnMap.put("getTime", "");
					}
					returnList.add(returnMap);
				}
			}
			//用于前台返值
			obj.put("couponInfoCount", couponInfoCount);
			obj.put("couponInfoList", returnList);
		} catch (Exception e) {
			this.logger.error("一键清除失败---"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		return new OpenResponse("true", "操作成功", obj, "100000");
	}
	
	@RequestMapping("/gcarapp/ios/cleanCouponInfo")
	@ResponseBody
	public OpenResponse cleanUpCouponOutTimeIos(@RequestParam(value = "message", required = true) String message){
		return this.cleanUpCouponOutTime(message);
	}
	/**
	* @Title: getCouponInfo 
	* @Description: TODO(查看优惠券详情) 
	* @param @param message
	* @param @return    设定文件 
	* @return OpenResponse    返回类型 
	* @throws
	 */
	@RequestMapping("/gcarapp/android/getCouponInfo")
	@ResponseBody
	public OpenResponse getCouponInfo(@RequestParam(value = "message", required = true) String message){
		logger.info("查询优惠券列表---android");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String id = params.getString("id");
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(null == id || "".equals(id)){
			return new OpenResponse("false", "参数异常：查询优惠券详情,ID不能为空！", null, "100001");
		}
		
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			this.logger.info("查询开始---");
			CouponInfo couponInfo = this.couponInfoService.findById(Long.parseLong(id));
			Coupon coupon = this.couponService.findById(couponInfo.getCouponId());
			obj.put("items", coupon.getItems());
			obj.put("remark", coupon.getRemark());
			obj.put("name", coupon.getName());
			
		} catch (Exception e) {
			this.logger.error("查询优惠券详情失败---"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		return new OpenResponse("true", "操作成功", obj, "100000");
	}
	
	@RequestMapping("/gcarapp/ios/getCouponInfo")
	@ResponseBody
	public OpenResponse getCouponInfoIos(@RequestParam(value = "message", required = true) String message){
		return this.getCouponInfo(message);
	}
}
