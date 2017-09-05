package com.hy.gcar.web.app.powerUsed;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.BasicService;
import com.hy.gcar.entity.BasicServiceServicecity;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.ExpectCartype;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.entity.PowerUsedCost;
import com.hy.gcar.entity.PowerUsedCostLog;
import com.hy.gcar.entity.TransferList;
import com.hy.gcar.service.ExpectCartype.ExpectCartypeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicServiceService;
import com.hy.gcar.service.basic.BasicServiceServicecityService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.service.transferList.TransferListService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

@Controller
public class PowerUsedForAppController {
	private static final String String = null;
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PowerUsedService powerUsedService;
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private ButlerTaskService butlerTaskService;
	@Autowired
	private CarOperateService carOperateService;
	@Autowired
	private BasicThresholdService basicThresholdService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private ButlerTaskStatusLogService butlerTaskStatusLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BasicServiceServicecityService basicServiceServicecityService;
	@Autowired
	private BasicServiceService basicServiceService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private PowerUsedCostService powerUsedCostService;
	@Autowired
	private TransferListService transferListService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	
	@Autowired
	private ChargeOrderService chargeOrderService;
	
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	
	@Autowired
	private ExpectCartypeService expectCartypeService;
	@Autowired
	private ItemCartypeService itemCartypeService;
	
	@Autowired
	private PowerUsedCostLogService powerUsedCostLogService;
	
	/**
	 * 发起用车
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/userCar")
	@ResponseBody
	public Object myCarsUseCarForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----非空车位发起用车------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		String index = params.getString("index");
		String flag = params.getString("flag");
		logger.info("ios 我的车库》》》》》》》》》》》》》》memberItemId="+memberItemId+">>>>>>>>>>>>>mobile="+mobile);	
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "权益ID不能为空！", null, "100001");
		}
		
		Map<String, Object> returnMap = null;
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setMemberId(Long.valueOf(memberId));
		powerUsed.setMemberItemId(Long.valueOf(memberItemId));
		try {
			if(StringUtils.isNotBlank(flag)&&flag.equals("1")){
				returnMap=powerUsedService.launchCarShowUseCar(powerUsed, flag, Integer.parseInt(index));//车位
			}else{
				returnMap=powerUsedService.launchCarShowUseCar(powerUsed, flag,0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "发起用车失败，请稍后重试！", null, "100001");
		}
		
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 非空车位发起用车
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/userCar")
	@ResponseBody
	public Object myCarsUseCarForIos(@RequestParam(value = "message", required = true) String message){
		return myCarsUseCarForAndroid(message);
	}
	
	/**
	 * 查询某时间内车型库存
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/getStoreOfCartype")
	@ResponseBody
	public Object getStoreOfCartypeForIos(@RequestParam(value = "message", required = true) String message){
		return getStoreOfCartypeForAndroid(message);
	}
	
	/**
	 * 查询某时间内车型库存
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/getStoreOfCartype")
	@ResponseBody
	public Object getStoreOfCartypeForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----用车发起页面确定按钮(查询是否是我的启用车型)------");
	/*	String memberItemId = request.getParameter("memberItemId");
		String useCarStartTime = request.getParameter("useCarStartTime");
		String useCarEndTime = request.getParameter("useCarEndTime");*/
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String useCarStartTime = params.getString("useCarStartTime");
		String useCarEndTime = params.getString("useCarEndTime");
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap = null;
		try {
			//getStoreOfCartype(memberItemId, useCarStartTime, useCarEndTime);
			returnMap=carTypeService.getStoreOfCartype(memberItemId, useCarStartTime, useCarEndTime);
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "判断是否是启用车型失败！", null, "100001");
		}
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	/**
	 * 用车发起页面确定按钮(查询是否是我的启用车型)
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/carsshow/isMyEnableCar")
	@ResponseBody
	public Object isMyEnableCarForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----用车发起页面确定按钮(查询是否是我的启用车型)------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		String carTypeId = params.getString("carTypeId");
		
		if(StringUtils.isBlank(carTypeId)){
			return new OpenResponse("false", "车型ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		Map<String, Object> returnMap = null;
		try {
			returnMap=powerUsedService.checkIsMyEnableCar(Long.valueOf(carTypeId),Long.valueOf(memberId));
			if(null==returnMap){
				return new OpenResponse("false", "您已被删除共用人身份！", null, "100001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "判断是否是启用车型失败！", null, "100001");
		}
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 用车发起页面确定按钮
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/carsshow/isMyEnableCar")
	@ResponseBody
	public Object isMyEnableCarForIos(@RequestParam(value = "message", required = true) String message){
		return isMyEnableCarForAndroid(message);
	}
	
	/**
	 * 启用车型列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/carsshow/enableCarTypeList")
	@ResponseBody
	public Object enableCarTypeListForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----启用车型列表------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			List<CarType> cartypes=  powerUsedService.selectMyEnableCars(Long.valueOf(memberId));
			for (CarType carType : cartypes) {
				Map<String, Object> returnMap = new HashMap<>();
				returnMap.put("brand", carType.getBrand());
				returnMap.put("carType", carType.getCarType());
				returnMap.put("carTypeId", carType.getId());
				list.add(returnMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "获取启用车型列表失败！", null, "100001");
		}
		return new OpenResponse("true", "操作成功", list, "100000");
	}
	
	@RequestMapping(value="/gcarapp/ios/carsshow/enableCarTypeList")
	@ResponseBody
	public Object enableCarTypeListForIos(@RequestParam(value = "message", required = true) String message){
		return enableCarTypeListForAndroid(message);
	}
	/**
	 * 获取优惠券数量接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/carsshow/getCouponCount")
	@ResponseBody
	public Object getCouponCountForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----获取优惠券数量接口------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		Map<String, Object> returnMap = new HashMap<>();
		CouponInfo tdCouponInfo = new CouponInfo();
		tdCouponInfo.setMemberId(Long.valueOf(memberId));
		tdCouponInfo.setCouponType(1);
		tdCouponInfo.setStatus(2);
		tdCouponInfo.setValid(1);
		Integer countByCondition = couponInfoService.selectCountByCondition(tdCouponInfo );
		returnMap.put("couponCount", countByCondition);
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 获取优惠券数量接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/carsshow/getCouponCount")
	@ResponseBody
	public Object getCouponCountForAndroid(@RequestParam(value = "message", required = true) String message){
		return getCouponCountForIos(message);
	}
	/**
	 * 获取优惠券列表
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/couponList")
	@ResponseBody
	public Object couponListForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----获取优惠券列表------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		List<Map<String, Object>> list = new ArrayList<>();
		
		CouponInfo tdCouponInfo = new CouponInfo();
		tdCouponInfo.setMemberId(Long.valueOf(memberId));
		tdCouponInfo.setCouponType(1);
		tdCouponInfo.setStatus(2);
		tdCouponInfo.setValid(1);
		tdCouponInfo.setOrderByOutTime("asc");
		List<CouponInfo> selectByCondition = couponInfoService.selectByCondition(tdCouponInfo);
		for (CouponInfo couponInfo : selectByCondition) {
			Map<String, Object> returnMap = new HashMap<>();
			returnMap.put("couponInfoId", couponInfo.getId());
			returnMap.put("couponName", couponInfo.getCouponName());
			returnMap.put("price", couponInfo.getPrice());
			returnMap.put("startTime", DateUtils.formatDate(couponInfo.getGetTime(), "yyyy-MM-dd"));
			returnMap.put("outTime",DateUtils.formatDate(couponInfo.getOutTime(), "yyyy-MM-dd"));
			list.add(returnMap);
		}
		return new OpenResponse("true", "操作成功", list, "100000");
	}
	/**
	 * 获取优惠券列表
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/couponList")
	@ResponseBody
	public Object couponListForAndroid(@RequestParam(value = "message", required = true) String message){
		return couponListForIos(message);
	}
	
	/**
	 * 支付确认页-用车按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/carsshow/pay/useCar")
	@ResponseBody
	public Object payUseCarForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 支付确认页-用车按钮------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
//		String flag = params.getString("flag");
		String memberId = params.getString("memberId");
		String carReturnAddress = params.getString("carReturnAddress");
		String carReturnTime = params.getString("carReturnTime");
		String carTypeId = params.getString("carTypeId");
		String carUsedAddress = params.getString("carUsedAddress");
		String carUsedDay = params.getString("carUsedDay");
		String carUsedTime = params.getString("carUsedTime");
//		String price = params.getString("price");
//		String clickCartype = params.getString("clickCartype");//点击无库存车型 json数组
		
		String cityCode = params.getString("cityCode");
		
		String couponInfoId = params.getString("couponInfoId");
		//加密
		String mobile = params.getString("mobile");
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(carTypeId)){
			return new OpenResponse("false", "车型ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(carUsedAddress)){
			return new OpenResponse("false", "请添加预计用车地址！", null, "100001");
		}
		Map<String, Object> returnMap=null;
		try {
			PowerUsed tdPowerUsed = new PowerUsed();
//			tdPowerUsed.setClickCartype(clickCartype);
			tdPowerUsed.setCarTypeId(Long.valueOf(carTypeId));
			tdPowerUsed.setCarReturnAddress(carReturnAddress);
			tdPowerUsed.setCarUsedAddress(carUsedAddress);
			tdPowerUsed.setMemberId(Long.valueOf(memberId));
			tdPowerUsed.setCarUsedDay(Integer.valueOf(carUsedDay));
			tdPowerUsed.setCityCode(cityCode);
//			tdPowerUsed.setPrice(new BigDecimal(price));
			if(StringUtils.isNotBlank(carReturnTime)){
				tdPowerUsed.setCarReturnTime(DateUtils.parseDate(carReturnTime));
			}
			if(StringUtils.isNotBlank(carUsedTime)){
				tdPowerUsed.setCarUsedTime(DateUtils.parseDate(carUsedTime));
			}
			if(StringUtils.isNotBlank(couponInfoId)){
				tdPowerUsed.setCouponInfoId(Long.valueOf(couponInfoId));
			}
		
			returnMap=powerUsedService.insertPowerUsed(tdPowerUsed);
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "发起用车失败！", null, "100001");
		}
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	/**
	 * 支付确认页-用车按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/carsshow/pay/useCar")
	@ResponseBody
	public Object payUseCarForAndroid(@RequestParam(value = "message", required = true) String message){
		return payUseCarForIos(message);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
//		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
//		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
//		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+"天":"")+hour+"小时";
    }
	

	/**
	 * 用车信息
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/powerUsed")
	@ResponseBody
	public Object powerUsedInfoForIos(@RequestParam(value = "message", required = true) String message){
			// 解析json数据
			logger.info("---调用---- 用车信息------");
			message = AppUtil.decode(message);
			JSONObject params = JSONObject.parseObject(message);
			String memberItemId = params.getString("memberItemId");
			String index = params.getString("index");
			String powerUsedId = params.getString("powerUsedId");//app端刷新时返回
			
			//加密
			String mobile = params.getString("mobile");
			String sign = params.getString("sign");
			boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				this.logger.info("解密错误---");
				return new OpenResponse("false", "sign不匹配", null, "100002");
			}
			
			if(StringUtils.isBlank(memberItemId)){
				return new OpenResponse("false", "权益ID不能为空！", null, "100001");
			}
			/*if(StringUtils.isBlank(index)){
				return new OpenResponse("false", "车位ID不能为空！", null, "100001");
			}*/
			Map<String, Object> returnMap=new HashMap<>();
			try {
				
				PowerUsed p =null;
				if(StringUtils.isNotBlank(powerUsedId)){
					p = powerUsedService.findById(Long.valueOf(powerUsedId));
				}else{
					
					PowerUsed powerUsed = new PowerUsed();
					powerUsed.setMemberItemId(Long.valueOf(memberItemId));
					List<PowerUsed> powerUsedList = powerUsedService.selectPowerUsedOfUseingCars(powerUsed );
					
					//已发起用车的情况
					if(CollectionUtils.isNotEmpty(powerUsedList)&&Integer.parseInt(index)<=powerUsedList.size()){
						//车位已发起用车 进入用车信息页面
						p = powerUsedList.get(Integer.parseInt(index));
					}
						
				}
				
				returnMap.put("powerUsedId", p.getId());
				CarType type = carTypeService.findById(p.getCarTypeId());//原车型
				returnMap.put("roadRescue", type.getRoadRescue());
				returnMap.put("charging", type.getCharging());
				
				returnMap.put("brand", type.getBrand());
				returnMap.put("carType", type.getCarType());
				returnMap.put("carTypeId", type.getId());
				
				
				returnMap.put("carReturnAddress", p.getCarReturnAddress());
				returnMap.put("carReturnTime", p.getCarReturnTime());
				returnMap.put("carUsedAddress", p.getCarUsedAddress());
				returnMap.put("carUsedTime", p.getCarUsedTime());

				
				if(p.getCaroperateId()!=null){
					CarOperate carOperate = carOperateService.findById(p.getCaroperateId());
					if(null!=carOperate){
						CarType car = carTypeService.findById(carOperate.getCarTypeId());
						returnMap.put("carPlateNum", carOperate.getCarPlateNum());
						returnMap.put("carOperateId", carOperate.getId());
						returnMap.put("brand", carOperate.getCarBrand());
						returnMap.put("carType", carOperate.getCarType());
						returnMap.put("carTypeId", carOperate.getCarTypeId());
						
						returnMap.put("roadRescue", car.getRoadRescue());
						returnMap.put("charging", car.getCharging());
					}
				}
				//如临时还车
				if(p.getChangeStatus()==1){
					CarOperate carOperateChane = carOperateService.findById(p.getChangeCaroperateId());
					if(null!=carOperateChane){
						returnMap.put("changeCarPlateNum", carOperateChane.getCarPlateNum());
						returnMap.put("carOperateId", carOperateChane.getId());
						returnMap.put("changeCarType", carOperateChane.getCarType());
						returnMap.put("changeBrand", carOperateChane.getCarBrand());
					}
					CarType tcCarType = new CarType();
					tcCarType.setBrand(carOperateChane.getCarBrand());
					tcCarType.setCarType(carOperateChane.getCarType());
					List<CarType> carTypes = carTypeService.selectByCondition(tcCarType);
					if(CollectionUtils.isNotEmpty(carTypes)){
						CarType carType = carTypes.get(0);
						returnMap.put("roadRescue", carType.getRoadRescue());
						returnMap.put("charging", carType.getCharging());
					}
					
				}
				
				if(p.getUsedStatus()==0||p.getUsedStatus()==1||p.getUsedStatus()==2){
					returnMap.put("status", "1");//已预约
				}else if(p.getUsedStatus()==3||p.getUsedStatus()==4||p.getUsedStatus()==5){
					returnMap.put("status", "2");//使用中
				}else if(p.getUsedStatus()==5){//已完成
					returnMap.put("status", "3");
				}else{
					returnMap.put("status", "0");//已取消
				}
				
				BasicThreshold selectBasicThreshold = basicThresholdService.selectBasicThreshold();
				returnMap.put("userReturncarUpdateAdvance", selectBasicThreshold.getUserReturncarUpdateAdvance());//用户端修改还车需提前(小时)',
				returnMap.put("userUsecarCancelAdvance", selectBasicThreshold.getUserUsecarCancelAdvance());//'用户端取消用车需提前(小时)',
				returnMap.put("userUsecarAdvance", selectBasicThreshold.getUserUsecarAdvance());//'用户端发起用车需提前(小时)',
				
				returnMap.put("serviceTimeStart", selectBasicThreshold.getServiceTimeStart());
				returnMap.put("serviceTimeEnd", selectBasicThreshold.getServiceTimeEnd());
				
				
				
				
				//查询任务
				ButlerTask butlerTask = new ButlerTask();
				butlerTask.setPowerUsedId(p.getId());
				butlerTask.setType(3);
				//送车任务
				ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask);
//				判断用车任务时间是否可修改
				Integer sendButlerStatus = checkButlerIsPendingOrders(butlerTaskSend);
				
				returnMap.put("sendButlerStatus", sendButlerStatus);
				//取车任务
				ButlerTask butlerTaskNotComplete = new ButlerTask();
				butlerTaskNotComplete.setPowerUsedId(p.getId());
				butlerTaskNotComplete.setType(2);
				ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTaskNotComplete);
				
//				判断还车车任务时间是否可修改
				Integer returnButlerStatus = checkButlerIsPendingOrders(butlerTaskGet);
				
				returnMap.put("returnButlerStatus", returnButlerStatus);
				
				if(null!=butlerTaskSend){
						if(butlerTaskSend.getStatus()==7){
							
//							ButlerTask butlerTaskNotComplete = new ButlerTask();
//							butlerTaskNotComplete.setPowerUsedId(p.getId());
//							butlerTaskNotComplete.setType(2);
//							ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTaskNotComplete);
							if(null!=butlerTaskGet){
//										送车任务日志
								List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskGet);
								if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
									//取车任务最新的状态
									ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
									if(log.getStatus()==6){//已失联
										for (int i = butlerTaskStatusLogList.size()-1; i >=0; i--) {
											if(butlerTaskStatusLogList.get(i).getStatus()!=6){
												log= butlerTaskStatusLogList.get(i);
												break;
											}
										}
									}
									long returnTime = p.getCarReturnTime().getTime();//预计还车时间
									long timeNow = new Date().getTime();
//												距您用车时间还剩xx,等待管家为您送车
									if(returnTime>timeNow){
										returnMap.put("time", formatDateTime(returnTime-timeNow));
									}else{
										returnMap.put("time", "0小时");
									}
									
									if(log.getStatus()==2){//待取车
										returnMap.put("msg", "等待管家取车");
									}
									if(log.getStatus()==3){//取车中
										returnMap.put("msg", "管家已出发取车");
										returnMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
									}
									if(log.getStatus()==4){//4:已到达
										returnMap.put("msg", "管家已到达");
										returnMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
									}
									if(log.getStatus()==7){//7:已完成
										returnMap.put("status", "3");
										returnMap.put("msg", "取车完成");
										return new OpenResponse("true", "操作成功", returnMap, "100000");
									}
									
								}
							}
						}else if(butlerTaskSend.getStatus()==8){//已取消
							returnMap.put("status", "0");
							return new OpenResponse("true", "操作成功", returnMap, "100000");
							
						}else{//送车任务没完成 排除取消任务
							List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskSend);
							if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
								//最新的状态
								ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
								//已取消
//								if(log.getStatus()==8){
////											查看取消之前的状态
//									log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-2);
//								}
//										'任务状态 0:待送车；1:送车中；2:待取车；3:取车中；4:已到达；5:充电中；6:已失联；7:已完成；8:已取消；',
								long carUsedTime = p.getCarUsedTime().getTime();//预计用车时间
								long timeNow = new Date().getTime();
//											距您用车时间还剩xx,等待管家为您送车
								if(carUsedTime>timeNow){
									returnMap.put("time", formatDateTime(carUsedTime-timeNow));
								}else{
									returnMap.put("time", "0小时");
								}
								
								if(log.getStatus()==0||log.getStatus()==9){
									returnMap.put("msg", "等待管家为您送车");
								}
								
								if(log.getStatus()==1){//送车中
									returnMap.put("msg", "管家已出发为您送车");
									returnMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
								}
								if(log.getStatus()==4){//已到达
									returnMap.put("msg", "管家已到达");
									returnMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
								}
								
							}
						}
				}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "查看用车失败！", null, "100001");
		}
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	public Integer checkButlerIsPendingOrders(ButlerTask butlerTask){
		if(butlerTask==null){
			return null;
		}
		if(butlerTask.getStatus()==9){//待处理
			return 1;
		}
		return 0;
	}
	
	/**
	 * 用车信息
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/powerUsed")
	@ResponseBody
	public Object powerUsedInfoForAndroid(@RequestParam(value = "message", required = true) String message){
		return powerUsedInfoForIos(message);
	}
	
	/**
	 * 点击取消用车校验接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/cancelCarCheck")
	@ResponseBody
	public Object cancelCarCheckForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 点击取消用车校验接口------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		String powerUsedId = params.getString("powerUsedId");
		Map<String, Object> returnMap=new HashMap<>();
		try {
			PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
			if(powerUsed.getUsedStatus()==7||powerUsed.getUsedStatus()==6){//改任务已取消或已完成
				returnMap.put("code", "3");
				returnMap.put("msg", "本次用车已结束");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			if(Long.valueOf(memberId).doubleValue()!=powerUsed.getMemberId().doubleValue()){
//				MemberItemShare trMemberItemShare = new MemberItemShare();
//				trMemberItemShare.setMemberItemId(Long.valueOf(memberId));
				MemberItemShare itemShareLoginer=memberItemShareService.selectByMemberId(Long.valueOf(memberId));
				boolean share =true;
				if(itemShareLoginer.getIsShare()!=1){//不可同时操作
					share =false;
				}
				MemberItemShare itemShareLaunch=memberItemShareService.selectByMemberId(powerUsed.getMemberId());//发起人
				if(itemShareLaunch.getIsShare()!=1){
					share =false;
				}
				if(!share){
					returnMap.put("code", "0");
					returnMap.put("msg", "只有发起用车的人才可以取消用车");
					return new OpenResponse("true", "操作成功", returnMap, "100000");
				}
			}
			
			//查询任务
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(powerUsed.getId());
			butlerTask.setType(3);
			//送车任务
			ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask);
			Integer status = checkButlerIsPendingOrders(butlerTaskSend);
			if(1!=status){
				returnMap.put("code", "2");
				returnMap.put("msg", "该用车信息已确认，如需修改请联系客服 400-0520-952");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "取消用车失败！", null, "100001");
		}
		returnMap.put("code", "1");
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 点击取消用车校验接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/cancelCarCheck")
	@ResponseBody
	public Object cancelCarCheckForIos(@RequestParam(value = "message", required = true) String message){
		return cancelCarCheckForAndroid(message);
	}
	/**
	 * 取消用车-确定取消按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/cancelCar")
	@ResponseBody
	public Object cancelCarForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 取消用车-确定取消按钮------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
//		String memberId = params.getString("memberId");
		String powerUsedId = params.getString("powerUsedId");
		String carTypeId = params.getString("carTypeId");
		String memberId = params.getString("memberId");
		String memberItemId = params.getString("memberItemId");
		
		try {
			PowerUsed powerUsed = new PowerUsed();
			powerUsed.setId(Long.valueOf(powerUsedId));
			powerUsed.setCarTypeId(Long.valueOf(carTypeId));
			powerUsed.setMemberItemId(Long.valueOf(memberItemId));
			powerUsed.setMemberId(Long.valueOf(memberId));
			powerUsedService.cancelCarSubmit(powerUsed);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "取消用车失败！", null, "100001");
		}
		
		return new OpenResponse("true", "操作成功", null, "100000");
	}
	/**
	 * 取消用车-确定取消按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/cancelCar")
	@ResponseBody
	public Object cancelCarForIos(@RequestParam(value = "message", required = true) String message){
		return cancelCarForAndroid(message);
	}
	/**
	 * 车辆追踪
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/carTracking")
	@ResponseBody
	public Object carTrackingForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 车辆追踪-----");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String powerUsedId = params.getString("powerUsedId");
		
		List<Map<String,Object>> list =null;
		try {
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(Long.valueOf(powerUsedId));
			butlerTask.setType(3);
			//送车任务
			ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask);
			//查询送车管家
			Member member = new Member();
			member.setSysuserId(butlerTaskSend.getOperaterId());
			Member butlerSendMember = memberService.selectBySysUserId(member );
			if(butlerSendMember!=null){
				butlerTaskSend.setOperaterMobile(butlerSendMember.getMobile());
			}
			
			if(null!=butlerTaskSend){
					if(butlerTaskSend.getStatus()==7){
						//已完成 查取车任务
						ButlerTask butlerTaskNotComplete = new ButlerTask();
						butlerTaskNotComplete.setPowerUsedId(Long.valueOf(powerUsedId));
						butlerTaskNotComplete.setType(2);
						ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTaskNotComplete);
						
						//查询取车管家
						Member memberGet = new Member();
						memberGet.setSysuserId(butlerTaskGet.getOperaterId());
						Member butlerGetMember = memberService.selectBySysUserId(memberGet );
						if(butlerGetMember!=null){
							butlerTaskGet.setOperaterMobile(butlerGetMember.getMobile());
						}
						
						if(null!=butlerTaskGet){
//							送车任务日志
							List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskGet);
							if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
								//取车任务最新的状态
								ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
								
								list = getCarButlerTaskReturn(butlerTaskGet, log,butlerTaskSend.getCompleteTime());
								
							}
						}
					}else{//送车任务没完成 排除取消任务
						List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskSend);
						if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
							//最新的状态
							ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
							//已取消
							if(log.getStatus()==8){
//								查看取消之前的状态  
								ButlerTaskStatusLog logBeforeCancel = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-2);
								list = getCarBulterTaskSend(butlerTaskSend, logBeforeCancel);
								
								List<Map<String,Object>> list_new = new ArrayList<>();
								for (int i = 0; i < list.size(); i++) {
									Map<String, Object> map = list.get(i);
									String status = (String)map.get("status");
									if(status.equals("1")){
										list_new.add(map);
									}
								}
								Map<String, Object> map_new = new HashMap<>();
								map_new.put("msg", "任务取消");
								map_new.put("status", "1");
								map_new.put("butlerInfo", "本次用车任务已取消");
								list_new.add(map_new);
								list=new ArrayList<>(list_new);
							}else{
								list = getCarBulterTaskSend(butlerTaskSend, log);
							}
						}
					}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "查看失败！", null, "100001");
		}
		
		return new OpenResponse("true", "操作成功", list, "100000");
	}
	private List<Map<String, Object>> getCarButlerTaskReturn(ButlerTask butlerTaskGet, ButlerTaskStatusLog log,Date butlerSendCompliteTime) {
		List<Map<String, Object>> list;
		list = getReturnConstansMap();
		if(log.getStatus()==9){//待处理
			Map<String, Object> map = list.get(0);
			map.put("time", DateUtils.formatDate(butlerSendCompliteTime,"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
		}
		
		if(log.getStatus()==10||log.getStatus()==2){//3:待接单 2:待取车；
			Map<String, Object> map0 = list.get(0);
			map0.put("status", "1");
			map0.put("time", DateUtils.formatDate(butlerSendCompliteTime,"yyyy-MM-dd HH:mm"));
			
			Map<String, Object> map1 = list.get(1);
			map1.put("butlerInfo", "已为您分配取车管家："+butlerTaskGet.getOperaterName()+"【"+butlerTaskGet.getOperaterMobile()+"】");
			map1.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map1.put("status", "1");
		}
		
		if(log.getStatus()==3){//3:取车中
			
			Map<String, Object> map0 = list.get(0);
			map0.put("status", "1");
			map0.put("time", DateUtils.formatDate(butlerSendCompliteTime,"yyyy-MM-dd HH:mm"));
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskGet.getId());
			butlerTaskStatusLog.setStatus(10);
			List<ButlerTaskStatusLog> logList1 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			Map<String, Object> map1 = list.get(1);
			map1.put("status", "1");
			if(CollectionUtils.isNotEmpty(logList1)){
				map1.put("time", DateUtils.formatDate(logList1.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", "已为您分配取车管家："+butlerTaskGet.getOperaterName()+"【"+butlerTaskGet.getOperaterMobile()+"】");
			}
			
			
			Map<String, Object> map2 = list.get(2);
			map2.put("butlerInfo", "管家已出发为您取车");
			map2.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map2.put("status", "1");
		}
		if(log.getStatus()==4){//4:已到达
			Map<String, Object> map0 = list.get(0);
			map0.put("status", "1");
			map0.put("time", DateUtils.formatDate(butlerSendCompliteTime,"yyyy-MM-dd HH:mm"));
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskGet.getId());
			butlerTaskStatusLog.setStatus(10);
			List<ButlerTaskStatusLog> logList1 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			Map<String, Object> map1 = list.get(1);
			map1.put("status", "1");
			if(CollectionUtils.isNotEmpty(logList1)){
				map1.put("time", DateUtils.formatDate(logList1.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", "已为您分配取车管家："+butlerTaskGet.getOperaterName()+"【"+butlerTaskGet.getOperaterMobile()+"】");
			}
			
			butlerTaskStatusLog.setStatus(3);
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			Map<String, Object> map2 = list.get(2);
			map2.put("status", "1");
			if(CollectionUtils.isNotEmpty(logList2)){
				map2.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", "管家已出发为您取车");
			}
			
			Map<String, Object> map3 = list.get(3);
			map3.put("butlerInfo", "管家已到达取车地点");
			map3.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map3.put("status", "1");
			
		}
		if(log.getStatus()==7){//7:已完成
			Map<String, Object> map0 = list.get(0);
			map0.put("status", "1");
			map0.put("time", DateUtils.formatDate(butlerSendCompliteTime,"yyyy-MM-dd HH:mm"));
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskGet.getId());
			butlerTaskStatusLog.setStatus(10);
			List<ButlerTaskStatusLog> logList1 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			Map<String, Object> map1 = list.get(1);
			map1.put("status", "1");
			if(CollectionUtils.isNotEmpty(logList1)){
				map1.put("time", DateUtils.formatDate(logList1.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", "已为您分配取车管家："+butlerTaskGet.getOperaterName()+"【"+butlerTaskGet.getOperaterMobile()+"】");
			}
			
			butlerTaskStatusLog.setStatus(3);
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			Map<String, Object> map2 = list.get(2);
			map2.put("status", "1");
			if(CollectionUtils.isNotEmpty(logList2)){
				map2.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", "管家已出发为您取车");
//				map2.put("butlerInfo", "管家已出发为您取车："+logList2.get(0).getModifier()+"【"+logList2.get(0).getModifierMobile()+"】");
			}
			
			butlerTaskStatusLog.setStatus(4);
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			Map<String, Object> map3 = list.get(3);
			map3.put("status", "1");
			if(CollectionUtils.isNotEmpty(logList3)){
				map3.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map3.put("butlerInfo", "管家已到达取车地点");
			}
			Map<String, Object> map4 = list.get(4);
			map4.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map4.put("butlerInfo", "管家已取车完成");
			map4.put("status", "1");
		}
		return list;
	}
	private List<Map<String, Object>> getCarBulterTaskSend(ButlerTask butlerTaskSend, ButlerTaskStatusLog log) {
		List<Map<String, Object>> list;
		list = getSendConstansMap();
		if(log.getStatus()==9){//待处理
			Map<String, Object> map = list.get(0);
			map.put("time", DateUtils.formatDate(butlerTaskSend.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
		}
		else if(log.getStatus()==10||log.getStatus()==0){//待接单 或待送车
			
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			map.put("time", DateUtils.formatDate(butlerTaskSend.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
//			map1.put("msg", "管家已出发为您送车");
			map1.put("butlerInfo", "已为您分配送车管家："+butlerTaskSend.getOperaterName()+"【"+butlerTaskSend.getOperaterMobile()+"】");
			map1.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map1.put("status", "1");
		}
		else if(log.getStatus()==1){//送车中
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			map.put("time", DateUtils.formatDate(butlerTaskSend.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
			
			//待接单
			butlerTaskStatusLog.setStatus(10);
			Map<String, Object> map1 = list.get(1);
			
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList2)){
				map1.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", "已为您分配送车管家："+butlerTaskSend.getOperaterName()+"【"+butlerTaskSend.getOperaterMobile()+"】");
			}
			map1.put("status", "1");
			
			Map<String, Object> map2 = list.get(2);
			map2.put("butlerInfo", "管家已出发为您送车");
			map2.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map2.put("status", "1");
		}
		else if(log.getStatus()==4){
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			map.put("time", DateUtils.formatDate(butlerTaskSend.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
			
			//待接单
			butlerTaskStatusLog.setStatus(10);
			Map<String, Object> map1 = list.get(1);
			
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList2)){
				map1.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", "已为您分配送车管家："+butlerTaskSend.getOperaterName()+"【"+butlerTaskSend.getOperaterMobile()+"】");
			}
			map1.put("status", "1");
			
			butlerTaskStatusLog.setStatus(1);
			Map<String, Object> map2 = list.get(2);
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map2.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", "管家已出发为您送车");
			}
			map2.put("status", "1");
			
			Map<String, Object> map3 = list.get(3);
			
			map3.put("butlerInfo", "管家已到达送车地点");
			map3.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map3.put("status", "1");
		}
		else if(log.getStatus()==7){
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			map.put("time", DateUtils.formatDate(butlerTaskSend.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
			
			//待接单
			butlerTaskStatusLog.setStatus(10);
			Map<String, Object> map1 = list.get(1);
			
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList2)){
				map1.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", "已为您分配送车管家："+butlerTaskSend.getOperaterName()+"【"+butlerTaskSend.getOperaterMobile()+"】");
			}
			map1.put("status", "1");
			
			butlerTaskStatusLog.setStatus(1);
			Map<String, Object> map2 = list.get(2);
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map2.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", "管家已出发为您送车");
			}
			map2.put("status", "1");
			
			butlerTaskStatusLog.setStatus(4);
			Map<String, Object> map3 = list.get(3);
			List<ButlerTaskStatusLog> logList4 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList4)){
				map3.put("time", DateUtils.formatDate(logList4.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map3.put("butlerInfo", "管家已到达送车地点");
			}
			map3.put("status", "1");
			
			Map<String, Object> map4 = list.get(4);
			map4.put("butlerInfo", "管家已完成送车");
			map4.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map4.put("status", "1");
		}
		return list;
	}
	private List<ButlerTaskStatusLog> getTaskLogs(ButlerTask task) {
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		butlerTaskStatusLog.setTaskId(task.getId());
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
		return butlerTaskStatusLogList;
	}
	
	/**
	 * 车辆追踪
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/carTracking")
	@ResponseBody
	public Object carTrackingForAndroid(@RequestParam(value = "message", required = true) String message){
		return carTrackingForIos(message);
	}
	
	/*public List<Map<String, Object>> getConstansMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map0 = new HashMap<>();
		map0.put("msg", "等待管家为您送车");
		map0.put("status", "0");
		list.add(map0);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("msg", "管家送车");
		map1.put("status", "0");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("msg", "管家到达");
		map2.put("status", "0");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("msg", "送车完成");
		map3.put("status", "0");
		list.add(map3);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("msg", "管家取车");
		map4.put("status", "0");
		list.add(map4);
		Map<String, Object> map5 = new HashMap<>();
		map5.put("msg", "管家到达");
		map5.put("status", "0");
		list.add(map5);
		Map<String, Object> map6 = new HashMap<>();
		map6.put("msg", "取车完成");
		map6.put("status", "0");
		list.add(map6);
		return list;
	}*/
	public List<Map<String, Object>> getSendConstansMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map0 = new HashMap<>();
		map0.put("msg", "发起用车");
		map0.put("butlerInfo", "用车预约成功，等待确认");
		map0.put("status", "0");
		list.add(map0);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("msg", "分配管家");
		map1.put("status", "0");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("msg", "管家送车");
		map2.put("status", "0");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("msg", "管家到达");
		map3.put("status", "0");
		list.add(map3);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("msg", "送车完成");
		map4.put("status", "0");
		list.add(map4);
		
		return list;
	}
	public List<Map<String, Object>> getReturnConstansMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map0 = new HashMap<>();
		map0.put("msg", "车辆使用");
		map0.put("butlerInfo", "车辆使用中");
		map0.put("status", "0");
		list.add(map0);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("msg", "分配管家");
		map1.put("status", "0");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("msg", "管家取车");
		map2.put("status", "0");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("msg", "管家到达");
		map3.put("status", "0");
		list.add(map3);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("msg", "取车完成");
		map4.put("status", "0");
		list.add(map4);
		
		return list;
	}
	
	/**
	 * 我要还车按钮
	 * @param message
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/toReturnCar")
	@ResponseBody
	public Object toReturnCarForAndroid(@RequestParam(value = "message", required = true) String message) {
		logger.info("---调用---- 我要还车按钮-----------------------------------");
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String powerUsedId = params.getString("powerUsedId");
		String memberId = params.getString("memberId");
		Map<String, Object> returnMap=new HashMap<>();
		
		try {
			PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
			if(powerUsed.getUsedStatus()==7||powerUsed.getUsedStatus()==6){//改任务已取消或已完成
				returnMap.put("code", "2");
				returnMap.put("msg", "本次用车已结束");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			//查询取车任务
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(powerUsed.getId());
			butlerTask.setType(2);
			butlerTask = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
			if(butlerTask.getStatus().intValue()!=9){
				returnMap.put("code", "4");
				returnMap.put("msg", "该用车信息已确认，如需修改请联系客服 400-0520-952");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			
			
			if(Long.valueOf(memberId).doubleValue()!=powerUsed.getMemberId().doubleValue()){
				Member member = memberService.findMemberByID(Long.valueOf(memberId));
//			被冻结
				if(member!=null && member.getStatuts()==0){
					returnMap.put("msg", "400-9029-858");
					returnMap.put("code", "3");
					return new OpenResponse("true", "操作成功", returnMap, "100000");
				}
				
				MemberItemShare itemShareLoginer=memberItemShareService.selectByMemberId(Long.valueOf(memberId));
				boolean share =true;
				if(itemShareLoginer.getIsShare()!=1){//不可同时操作
					share =false;
				}
				MemberItemShare itemShareLaunch=memberItemShareService.selectByMemberId(powerUsed.getMemberId());//发起人
				if(itemShareLaunch.getIsShare()!=1){
					share =false;
				}
				if(!share){
					returnMap.put("msg", "只有发起用车的人才可以进行还车");
					returnMap.put("code", "0");
					return new OpenResponse("true", "操作成功", returnMap, "100000");
				}
			}
			returnMap.put("code", "1");
			BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
			returnMap.put("userReturncarUpdateAdvance", threshold.getUserReturncarUpdateAdvance());
			returnMap.put("serviceTimeStart", threshold.getServiceTimeStart());
			returnMap.put("serviceTimeEnd", threshold.getServiceTimeEnd());
			
			/*returnMap.put("carReturnAddress", powerUsed.getCarReturnAddress());
			returnMap.put("carReturnTime", powerUsed.getCarReturnTime());*/
		}  catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 我要还车按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/toReturnCar")
	@ResponseBody
	public Object toReturnCarForIos(@RequestParam(value = "message", required = true) String message){
		return toReturnCarForAndroid(message);
	}
	
	/**
	 * 我要还车 确定按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/returnCar")
	@ResponseBody
	public Object returnCarForAndroid(@RequestParam(value = "message", required = true) String message){
		logger.info("---调用---- 我要还车 确定按钮-----------------------------------");
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String powerUsedId = params.getString("powerUsedId");
		String carReturnTime = params.getString("carReturnTime");
		String carReturnAddress = params.getString("carReturnAddress");
		//加密
		String mobile = params.getString("mobile");
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap=new HashMap<>();
		PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
		if(powerUsed.getUsedStatus()==7||powerUsed.getUsedStatus()==6){//改任务已取消或已完成
			returnMap.put("code", "5");
			returnMap.put("msg", "本次用车已结束");
			return new OpenResponse("true", "操作成功", returnMap, "100000");
		}
		
		//查询取车任务
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setPowerUsedId(powerUsed.getId());
		butlerTask.setType(2);
		butlerTask = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
		if(butlerTask.getStatus()!=9){
			returnMap.put("code", "6");
			returnMap.put("msg", "该用车信息已确认，如需修改请联系客服 400-0520-952");
			return new OpenResponse("true", "操作成功", returnMap, "100000");
		}
		
		try {
			PowerUsed powerUsed_new = new PowerUsed();
			powerUsed_new.setId(Long.valueOf(powerUsedId));
			powerUsed_new.setCarReturnAddress(carReturnAddress);
			powerUsed_new.setCarReturnTime(DateUtils.parseDate(carReturnTime));
			
			returnMap=powerUsedService.returnCarSubmit(powerUsed_new);
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 我要还车 确定按钮
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/returnCar")
	@ResponseBody
	public Object returnCarForIos(@RequestParam(value = "message", required = true) String message){
		return returnCarForAndroid(message);
	}
	/**
	 * 道路救援
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/roadRescue")
	@ResponseBody
	public Object roadRescueForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 道路救援-----------------------------------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String powerUsedId = params.getString("powerUsedId");
		String memberId = params.getString("memberId");
		Map<String, Object> returnMap=new HashMap<>();
		try {
			PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
			if(powerUsed.getUsedStatus()==7||powerUsed.getUsedStatus()==6){//改任务已取消或已完成
				returnMap.put("code", "2");
				returnMap.put("msg", "本次用车已结束");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			BasicServiceServicecity trBasicServiceServicecity = new BasicServiceServicecity();
			trBasicServiceServicecity.setServiceId(1L);
			trBasicServiceServicecity.setCityCode(powerUsed.getCityCode());
			List<BasicServiceServicecity> list = basicServiceServicecityService.selectByCondition(trBasicServiceServicecity );
			if(CollectionUtils.isEmpty(list)){
				returnMap.put("msg", "当前城市暂未开通，敬请期待");
				returnMap.put("code", "0");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			//被冻结
			if(member!=null && member.getStatuts()==0){
				returnMap.put("msg", "400-9029-858");
				returnMap.put("code", "3");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			if(Long.valueOf(memberId).doubleValue()!=powerUsed.getMemberId().doubleValue()){
				
				MemberItemShare itemShareLoginer=memberItemShareService.selectByMemberId(Long.valueOf(memberId));
				boolean share =true;
				if(itemShareLoginer.getIsShare()!=1){//不可同时操作
					share =false;
				}
				MemberItemShare itemShareLaunch=memberItemShareService.selectByMemberId(powerUsed.getMemberId());//发起人
				if(itemShareLaunch.getIsShare()!=1){
					share =false;
				}
				if(!share){
					returnMap.put("msg", "您不支持与发起人同时使用车辆");
					returnMap.put("code", "0");
					return new OpenResponse("true", "操作成功", returnMap, "100000");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		
		returnMap.put("code", "1");
		return new OpenResponse("true", "操作成功", returnMap, "100000");				
	}
	/**
	 * 道路救援
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/roadRescue")
	@ResponseBody
	public Object roadRescueForAndroid(@RequestParam(value = "message", required = true) String message){
		return roadRescueForIos(message);
	}
	
	/**
	 * 点击待充电服务
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/charge")
	@ResponseBody
	public Object chargeForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 点击待充电服务-----------------------------------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String powerUsedId = params.getString("powerUsedId");
		String memberId = params.getString("memberId");
		String carOperateId = params.getString("carOperateId");
		Map<String, Object> returnMap=new HashMap<>();
		
		returnMap.put("status", "0");
		PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
		if(powerUsed.getUsedStatus()==7||powerUsed.getUsedStatus()==6){//改任务已取消或已完成
			returnMap.put("code", "2");
			returnMap.put("msg", "本次用车已结束");
			return new OpenResponse("true", "操作成功", returnMap, "100000");
		}
		
		//查询是否已发起充电服务
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setCarOperateId(Long.valueOf(carOperateId));
	
		ButlerTask butlerTaskByPowerUsedId = butlerTaskService.getButlerTaskIsChargeing(butlerTask );
		if(null!=butlerTaskByPowerUsedId){
			returnMap.put("status", "1");
			returnMap.put("butlerTaskId", butlerTaskByPowerUsedId.getId());
			return new OpenResponse("true", "操作成功", returnMap, "100000");				
		}
		
		try {
			
			BasicServiceServicecity trBasicServiceServicecity = new BasicServiceServicecity();
			trBasicServiceServicecity.setServiceId(2L);
			trBasicServiceServicecity.setCityCode(powerUsed.getCityCode());
			List<BasicServiceServicecity> list = basicServiceServicecityService.selectByCondition(trBasicServiceServicecity );
			if(CollectionUtils.isEmpty(list)){
				returnMap.put("msg", "当前城市暂未开通，敬请期待");
				returnMap.put("code", "0");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			//被冻结
			if(member!=null && member.getStatuts()==0){
				returnMap.put("msg", "400-9029-858");
				returnMap.put("code", "3");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			if(Long.valueOf(memberId).doubleValue()!=powerUsed.getMemberId().doubleValue()){
				
				MemberItemShare itemShareLoginer=memberItemShareService.selectByMemberId(Long.valueOf(memberId));
				boolean share =true;
				if(itemShareLoginer.getIsShare()!=1){//不可同时操作
					share =false;
				}
				MemberItemShare itemShareLaunch=memberItemShareService.selectByMemberId(powerUsed.getMemberId());//发起人
				if(itemShareLaunch.getIsShare()!=1){
					share =false;
				}
				if(!share){
					returnMap.put("msg", "您不支持与发起人同时使用车辆");
					returnMap.put("code", "0");
					return new OpenResponse("true", "操作成功", returnMap, "100000");
				}
			}
			BasicService basicService = basicServiceService.findById(2L);
			BigDecimal price = basicService.getPrice(); //充电服务费
			
		
			MemberItem memberItem = memberItemService.findById(powerUsed.getMemberItemId());
			BigDecimal deposit = memberItem.getDeposit();//押金
			if(price.doubleValue() > deposit.doubleValue()){
				returnMap.put("msg", "您的押金不足请先进行续费");
				returnMap.put("code", "4");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			returnMap.put("price", price);
			returnMap.put("startTime", basicService.getStartTime());
			returnMap.put("endTime", basicService.getEndTime());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		
		returnMap.put("code", "1");
		return new OpenResponse("true", "操作成功", returnMap, "100000");				
	}
	/**
	 * 带充电
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/charge")
	@ResponseBody
	public Object chargeForAndroid(@RequestParam(value = "message", required = true) String message){
		return chargeForIos(message);
	}
	/**
	 * 带充电支付
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/chargeSubmit")
	@ResponseBody
	public Object chargeSubmitForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 带充电支付-----------------------------------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String chargeTime = params.getString("chargeTime");
		String getcarAddress = params.getString("getcarAddress");
		String returnCarAddress = params.getString("returnCarAddress");
		String memberId = params.getString("memberId");
		String powerUsedId = params.getString("powerUsedId");
		String carOperateId = params.getString("carOperateId");
		String memberItemId = params.getString("memberItemId");
		
		//加密
		String mobile = params.getString("mobile");
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap=new HashMap<>();
		try {
			MemberItem memberItem = memberItemService.findById(Long.valueOf(memberItemId));
			BigDecimal deposit = memberItem.getDeposit();//押金
			BasicService basicService = basicServiceService.findById(2L);
			BigDecimal price = basicService.getPrice(); //充电服务费
			
			if(price.doubleValue() > deposit.doubleValue()){
				returnMap.put("msg", "您的押金不足请先进行续费");
				returnMap.put("code", "4");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setMemberId(memberId);
			butlerTask.setMemberItemId(powerUsed.getMemberItemId());
			butlerTask.setMemberMobile(member.getMobile());
			butlerTask.setMemberName(member.getName());
			butlerTask.setModifierType(0);
			butlerTask.setPowerUsedId(powerUsed.getId());
			butlerTask.setTaskSendCarAddress(returnCarAddress);
			butlerTask.setTaskGetCarAddress(getcarAddress);
			butlerTask.setCarOperateId(Long.valueOf(carOperateId));
			butlerTask.setPlanTime(DateUtils.parseDate(chargeTime));
			butlerTask = butlerTaskService.createChargingButlerTask(butlerTask );
			returnMap.put("butlerTaskId", butlerTask.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");	
	}
	/**
	 * 带充电支付
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/chargeSubmit")
	@ResponseBody
	public Object chargeSubmitForIos(@RequestParam(value = "message", required = true) String message){
		return chargeSubmitForAndroid(message);
	}
	
	/**
	 *  充电详情
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/chargeDetail")
	@ResponseBody
	public Object chargeDetailForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用----充电详情-----------------------------------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
//		String chargeTime = params.getString("chargeTime");
//		String getcarAddress = params.getString("getcarAddress");
//		String returnCarAddress = params.getString("returnCarAddress");
//		String memberId = params.getString("memberId");
		String butlerTaskId = params.getString("butlerTaskId");
		Map<String, Object> returnMap=new HashMap<>();
		
		
		ButlerTask butlerTask = butlerTaskService.getButlerTaskById(Long.valueOf(butlerTaskId));
		
		returnMap.put("returnCarAddress", butlerTask.getTaskSendCarAddress());
		returnMap.put("getcarAddress", butlerTask.getTaskGetCarAddress());
		returnMap.put("chargeTime", DateUtils.formatDate(butlerTask.getPlanTime(), "yyyy-MM-dd HH:mm"));
//		0:待送车；1:送车中；2:待取车；3:取车中；4:已到达；5:充电中；6:已失联；7:已完成；8:已取消；',
//		String statusTrack = butlerTask.getButlerTaskStatusTrack();
		
		List<ButlerTaskStatusLog> taskLogs = getTaskLogs(butlerTask);
		String butlerInfo=null;
		if(CollectionUtils.isNotEmpty(taskLogs)){
			for (ButlerTaskStatusLog log : taskLogs) {
				if(log.getStatus()==butlerTask.getStatus()){
					butlerInfo= log.getModifier()+log.getModifierMobile()+"";
				}
			}
		}
		if(StringUtils.isNotBlank(butlerInfo)){
			returnMap.put("butlerInfo", butlerTask.getButlerTaskStatusTrack());
		}
		returnMap.put("msg", butlerTask.getButlerTaskStatusTrack());
		return new OpenResponse("true", "操作成功", returnMap, "100000");	
	}
	
	/**
	 *  充电详情
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/chargeDetail")
	@ResponseBody
	public Object chargeDetailForAndroid(@RequestParam(value = "message", required = true) String message){
		return chargeDetailForIos(message);
	}
	
	/**
	 *  充电追踪
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/chargeTracking")
	@ResponseBody
	public Object chargeTrackingForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 充电追踪----------------------------------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String butlerTaskId = params.getString("butlerTaskId");
		ButlerTask butlerTask = butlerTaskService.getButlerTaskById(Long.valueOf(butlerTaskId));
		
//		任务状态 0:待送车；1:送车中；2:待取车；3:取车中；4:已到达；5:充电中；6:已失联；7:已完成；8:已取消；9：待处理 10 待接单',
		
		List<ButlerTaskStatusLog> taskLogs = getTaskLogs(butlerTask);
		
		ButlerTaskStatusLog logNow =null;
		if(CollectionUtils.isNotEmpty(taskLogs)){
			for (ButlerTaskStatusLog log : taskLogs) {
				if(log.getStatus()==butlerTask.getStatus()){
					if(butlerTask.getStatus()==8){//已取消
						logNow=taskLogs.get(taskLogs.size()-2);
						break;
					}
					logNow=log;
				}
			}
		}
		List<Map<String,Object>> list=chargeTracking(butlerTask, logNow);
		
		return new OpenResponse("true", "操作成功", list, "100000");	
	}
	private List<Map<String,Object>> chargeTracking(ButlerTask butlerTask, ButlerTaskStatusLog logNow) {
		List<Map<String,Object>> list = getConstansChargeMap();
		if(logNow.getStatus()==2){
			Map<String, Object> map = list.get(0);
			map.put("time", DateUtils.formatDate(logNow.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
		}
		
		if(logNow.getStatus()==3){
			Map<String, Object> map = list.get(0);
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTask.getId());
			butlerTaskStatusLog .setStatus(2);
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
//				map.put("butlerInfo", logList3.get(0).getModifier()+logList3.get(0).getModifierMobile()+"");
			}
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
//			map1.put("msg", "管家已出发取车");
			map1.put("butlerInfo", logNow.getModifier()+"【"+logNow.getModifierMobile()+"】");
			map1.put("time", DateUtils.formatDate(logNow.getCreated(),"yyyy-MM-dd HH:mm"));
			map1.put("status", "1");
		}
		
		if(logNow.getStatus()==5){
			Map<String, Object> map = list.get(0);
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog .setStatus(2);
			butlerTaskStatusLog.setTaskId(butlerTask.getId());
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
//				map.put("butlerInfo", logList3.get(0).getModifier()+logList3.get(0).getModifierMobile()+"】");
			}
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
			butlerTaskStatusLog .setStatus(3);
			List<ButlerTaskStatusLog> logList4 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList4)){
				map1.put("time", DateUtils.formatDate(logList4.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", logList4.get(0).getModifier()+"【"+logList4.get(0).getModifierMobile()+"】");
			}
//			map1.put("msg", "管家已出发取车");
			map1.put("status", "1");
			
			Map<String, Object> map2 = list.get(2);
//			map2.put("msg", "管家已出发充电");
			map2.put("butlerInfo", logNow.getModifier()+"【"+logNow.getModifierMobile()+"】");
			map2.put("time", DateUtils.formatDate(logNow.getCreated(),"yyyy-MM-dd HH:mm"));
			map2.put("status", "1");
		}
		
		if(logNow.getStatus()==0){
//			chargeTrackStatus0(logNow, list);
			Map<String, Object> map = list.get(0);
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog .setStatus(2);
			butlerTaskStatusLog.setTaskId(butlerTask.getId());
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
//				map.put("butlerInfo", logList3.get(0).getModifier()+logList3.get(0).getModifierMobile()+"");
			}
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
			butlerTaskStatusLog .setStatus(3);
			List<ButlerTaskStatusLog> logList4 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList4)){
				map1.put("time", DateUtils.formatDate(logList4.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", logList4.get(0).getModifier()+"【"+logList4.get(0).getModifierMobile()+"】");
			}
//			map1.put("msg", "管家已出发取车");
			map1.put("status", "1");
			
			Map<String, Object> map2 = list.get(2);
			butlerTaskStatusLog .setStatus(5);
			List<ButlerTaskStatusLog> logList5 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList5)){
				map2.put("time", DateUtils.formatDate(logList5.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", logList5.get(0).getModifier()+"【"+logList5.get(0).getModifierMobile()+"】");
			}
//			map2.put("msg", "管家已出发充电");
			map2.put("status", "1");
			
			Map<String, Object> map3 = list.get(3);
//			map3.put("msg", "管家已充电完成，等待管家送车");
			map3.put("butlerInfo", logNow.getModifier()+"【"+logNow.getModifierMobile()+"】");
			map3.put("time", DateUtils.formatDate(logNow.getCreated(),"yyyy-MM-dd HH:mm"));
			map3.put("status", "1");
		}
		
		if(logNow.getStatus()==1){
//			chargeTrackStatus1(logNow, list);
			Map<String, Object> map = list.get(0);
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog .setStatus(2);
			butlerTaskStatusLog.setTaskId(butlerTask.getId());
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
//				map.put("butlerInfo", logList3.get(0).getModifier()+logList3.get(0).getModifierMobile()+"】");
			}
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
			butlerTaskStatusLog .setStatus(3);
			List<ButlerTaskStatusLog> logList4 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList4)){
				map1.put("time", DateUtils.formatDate(logList4.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", logList4.get(0).getModifier()+"【"+logList4.get(0).getModifierMobile()+"】");
			}
//			map1.put("msg", "管家已出发取车");
			map1.put("status", "1");
			
			Map<String, Object> map2 = list.get(2);
			butlerTaskStatusLog .setStatus(5);
			List<ButlerTaskStatusLog> logList5 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList5)){
				map2.put("time", DateUtils.formatDate(logList5.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", logList5.get(0).getModifier()+"【"+logList5.get(0).getModifierMobile()+"】");
			}
//			map2.put("msg", "管家已出发充电");
			map2.put("status", "1");
			
			Map<String, Object> map3 = list.get(3);
			butlerTaskStatusLog .setStatus(1);
			List<ButlerTaskStatusLog> logList6 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList6)){
				map3.put("time", DateUtils.formatDate(logList6.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map3.put("butlerInfo", logList6.get(0).getModifier()+"【"+logList6.get(0).getModifierMobile()+"】");
			}
			map3.put("msg", "管家已充电完成，等待管家送车");
			map3.put("status", "1");
			
		/*	Map<String, Object> map4 = list.get(4);
			butlerTaskStatusLog .setStatus(1);
			List<ButlerTaskStatusLog> logList7 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList7)){
				map4.put("time", DateUtils.formatDateTime(logList7.get(0).getCreated()));
				map4.put("butlerInfo", logList7.get(0).getModifier()+logList7.get(0).getModifierMobile()+"");
			}
			map4.put("msg", "管家已出发送车");
			map4.put("status", "1");*/
		}
		
		if(logNow.getStatus()==7){
//			chargeTrackStatus1(logNow, list);
			Map<String, Object> map = list.get(0);
			
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog .setStatus(2);
			butlerTaskStatusLog.setTaskId(butlerTask.getId());
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
//				map.put("butlerInfo", logList3.get(0).getModifier()+logList3.get(0).getModifierMobile()+"");
			}
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
			butlerTaskStatusLog .setStatus(3);
			List<ButlerTaskStatusLog> logList4 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList4)){
				map1.put("time", DateUtils.formatDate(logList4.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", logList4.get(0).getModifier()+"【"+logList4.get(0).getModifierMobile()+"】");
			}
//			map1.put("msg", "管家已出发取车");
			map1.put("status", "1");
			
			Map<String, Object> map2 = list.get(2);
			butlerTaskStatusLog .setStatus(5);
			List<ButlerTaskStatusLog> logList5 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList5)){
				map2.put("time", DateUtils.formatDate(logList5.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", logList5.get(0).getModifier()+"【"+logList5.get(0).getModifierMobile()+"】");
			}
//			map2.put("msg", "管家已出发充电");
			map2.put("status", "1");
			
			Map<String, Object> map3 = list.get(3);
			butlerTaskStatusLog .setStatus(1);
			List<ButlerTaskStatusLog> logList6 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList6)){
				map3.put("time", DateUtils.formatDate(logList6.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map3.put("butlerInfo", logList6.get(0).getModifier()+"【"+logList6.get(0).getModifierMobile()+"】");
			}
//			map3.put("msg", "管家已充电完成，等待管家送车");
			map3.put("status", "1");
			
		/*	Map<String, Object> map4 = list.get(4);
			butlerTaskStatusLog .setStatus(1);
			List<ButlerTaskStatusLog> logList7 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList7)){
				map4.put("time", DateUtils.formatDateTime(logList7.get(0).getCreated()));
				map4.put("butlerInfo", logList7.get(0).getModifier()+logList7.get(0).getModifierMobile()+"");
			}
			map4.put("msg", "管家已出发送车");
			map4.put("status", "1");*/
			
			Map<String, Object> map5 = list.get(4);
			map5.put("time", DateUtils.formatDate(logNow.getCreated(),"yyyy-MM-dd HH:mm"));
			map5.put("status", "1");
		}
		return list;
	}
	
	
	/**
	 *  充电详情
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/chargeTracking")
	@ResponseBody
	public Object chargeTrackingForAndroid(@RequestParam(value = "message", required = true) String message){
		return chargeTrackingForIos(message);
	}
	
	public List<Map<String, Object>> getConstansChargeMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map0 = new HashMap<>();
		map0.put("msg", "等待管家取车");
		map0.put("status", "0");
		list.add(map0);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("msg", "管家已出发取车");
		map1.put("status", "0");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("msg", "管家已出发充电");
		map2.put("status", "0");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("msg", "管家已充电完成，等待管家送车");
		map3.put("status", "0");
		list.add(map3);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("msg", "充电完成");
		map4.put("status", "0");
		list.add(map4);
		/*Map<String, Object> map5 = new HashMap<>();
		map5.put("msg", "充电完成");
		map5.put("status", "0");
		list.add(map5);*/
		return list;
	}
	
	/**
	 * 用车记录
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/powerUsedRecords")
	@ResponseBody
	public Object powerUsedRecordsAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		Integer pageNum = Integer.parseInt(params.getString("pageNum"));
		logger.info("用车记录列表---memberItemId="+memberItemId+"----mobile="+mobile+"pagenum="+pageNum);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> recordList = new ArrayList<>();
		try {
			PowerUsed powerUsed = new PowerUsed();
//			powerUsed.setMemberId(Long.valueOf(memberId));
			powerUsed.setMemberItemId(Long.valueOf(memberItemId));
			Integer total = powerUsedService.selectCountByCondition(powerUsed);
			
			String flag = "1";
    		int start = 0;
    		int preCountNum = 0;//已经显示记录数
			int nextCountNum = AppUtil.PAGE_NUM;//请求记录数
			boolean needSelect = false;
    		if(total > 0){
    			if(pageNum > 1){
    				start = AppUtil.PAGE_NUM * (pageNum-1);
    				preCountNum = (pageNum - 1) * AppUtil.PAGE_NUM;
    				nextCountNum = pageNum * AppUtil.PAGE_NUM;
    			}
    			if(preCountNum >= total){//已经加载完毕 无需查询
    				flag = "2";
    			}else if(nextCountNum >= total){
    				flag = "2";
    				needSelect = true;
    			}else if(nextCountNum < total){
    				flag = "1";
    				needSelect = true;
    			}
    		}else{
    			//没有记录
    			flag = "2";
    		}
    		if(needSelect){
    			
    			powerUsed.setBeginPage(start);
    			powerUsed.setPageSize(AppUtil.PAGE_NUM);
    			List<PowerUsed> list= powerUsedService.selectByPageList(powerUsed);
//				用车的状态0：待送车 1：已出发送车 2 已到达 3送车完成 4已出发取车 5 管家已到达 6取车完成 7：已取消',
    			
    			for (PowerUsed powerU : list) {
    				PowerUsedCost powerUsedCost = powerUsedCostService.selectByPowerUsedId(powerU.getId());
    				CarOperate carOperate = carOperateService.findById(powerU.getCaroperateId());
    				CarType carType = carTypeService.findById(powerU.getCarTypeId());
    				ButlerTask butlerTask = new ButlerTask();
    				butlerTask.setPowerUsedId(powerU.getId());
    				butlerTask.setType(2);//取车任务
					ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
					butlerTask.setType(3);//送车任务
					ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
					
					Map<String , Object> map = new HashMap<>();
					
					if(powerU.getUsedStatus()==0||powerU.getUsedStatus()==1||powerU.getUsedStatus()==2){
						map.put("carUsedTime", DateUtils.formatDate(powerU.getCarUsedTime(), "yyyy-MM-dd") );
						map.put("carReturnTime", DateUtils.formatDate(powerU.getCarReturnTime(), "yyyy-MM-dd") );
						map.put("status", "待送车");
						map.put("carType", carType.getCarType());
						map.put("brand", carType.getBrand());
						map.put("carTypeId", powerU.getCarTypeId());
						map.put("powerUsedId", powerU.getId());
						map.put("price", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}else if(powerU.getUsedStatus()==6){
						map.put("carUsedTime", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd") );
						map.put("carReturnTime", DateUtils.formatDate(butlerTaskGet.getCompleteTime(), "yyyy-MM-dd") );
						map.put("status", "已完成");
						map.put("carType", carOperate.getCarType());
						map.put("brand", carOperate.getCarBrand());
						map.put("carTypeId", powerU.getCarTypeId());
						map.put("price", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						map.put("carPlateNum", carOperate.getCarPlateNum());
						CarType carType2 = carTypeService.findById(carOperate.getCarTypeId());
						if(StringUtils.isBlank(carOperate.getImageUrl())){
							map.put("imageurl",carType2.getImageUrl());
						}else{
							map.put("imageurl",carOperate.getImageUrl());
						}
						map.put("powerUsedId", powerU.getId());
					}else if(powerU.getUsedStatus()==7){
						//取消用车的时间
						map.put("carUsedTime", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd"));
						map.put("status", "已取消");
						map.put("carType", carType.getCarType());
						map.put("brand", carType.getBrand());
						map.put("carTypeId", powerU.getCarTypeId());
						map.put("powerUsedId", powerU.getId());
						map.put("price", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if(butlerTaskSend.getStatus()==7){
						if(powerU.getUsedStatus()==3||powerU.getUsedStatus()==4||powerU.getUsedStatus()==5){
							map.put("carUsedTime", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd") );
							map.put("carReturnTime", DateUtils.formatDate(powerU.getCarReturnTime(), "yyyy-MM-dd"));
							map.put("status", "使用中");
							map.put("carType", carOperate.getCarType());
							map.put("brand", carOperate.getCarBrand());
							map.put("carTypeId", powerU.getCarTypeId());
							map.put("price", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							map.put("carPlateNum", carOperate.getCarPlateNum());
							CarType carType2 = carTypeService.findById(carOperate.getCarTypeId());
							if(StringUtils.isBlank(carOperate.getImageUrl())){
								map.put("imageurl",carType2.getImageUrl());
							}else{
								map.put("imageurl",carOperate.getImageUrl());
							}
							map.put("powerUsedId", powerU.getId());
						}
					}
					recordList.add(map);
    			}
    		}
    		returnMap.put("pageNum", pageNum+"");
    		returnMap.put("flag", flag+"");
    		returnMap.put("recordList", recordList);
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 用车记录
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/powerUsedRecords")
	@ResponseBody
	public Object powerUsedRecordsIos(@RequestParam(value = "message", required = true) String message){
		return powerUsedRecordsAndroid(message);
	}
	/**
	 * 用车记录详情
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/powerUsedRecordDetail")
	@ResponseBody
	public Object powerUsedRecordDetailIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		String powerUsedId = params.getString("powerUsedId");
		logger.info("用车记录详情---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap = new HashMap<>();
		
//		PowerUsed powerUsed = new PowerUsed();
//		powerUsed.setMemberId(Long.valueOf(memberId));
//		powerUsed.setMemberItemId(Long.valueOf(memberItemId));
//		用车的状态0：待送车 1：已出发送车 2 已到达 3送车完成 4已出发取车 5 管家已到达 6取车完成 7：已取消',
		
		try {
			PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
//			MemberItem memberItem = memberItemService.findById(powerUsed.getMemberItemId());
			Member member = memberService.findMemberByID(powerUsed.getMemberId());
			
			/*MemberItemCartype memberItemCartype = new MemberItemCartype();
			memberItemCartype.setCarTypeId(powerUsed.getCarTypeId());
			memberItemCartype.setMemberItemId(memberItem.getId());
			List<MemberItemCartype> carTypes = memberItemCartypeService.selectByCondition(memberItemCartype );*/
			
			PowerUsedCost powerUsedCost = powerUsedCostService.selectByPowerUsedId(Long.valueOf(powerUsedId));
			
			//查询已扣费天数
			PowerUsedCostLog tdPowerUsedCostLog = new PowerUsedCostLog();
			tdPowerUsedCostLog.setPowerUsedCostId(powerUsedCost.getId());
			List<PowerUsedCostLog> PowerUsedCostLogList = powerUsedCostLogService.selectByCondition(tdPowerUsedCostLog );
			
			
			CarOperate carOperate = carOperateService.findById(powerUsed.getCaroperateId());
			CarType carType = carTypeService.findById(powerUsed.getCarTypeId());
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(powerUsed.getId());
			butlerTask.setType(2);//取车任务
			ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
			
			TransferList transferList = new TransferList();
			transferList.setTaskId(butlerTaskGet.getId());
			transferList.setType(0);
			List<TransferList> transferListGet = transferListService.selectByCondition(transferList);
			
			butlerTask.setType(3);//送车任务
			ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
			
			TransferList transferList2 = new TransferList();
			transferList2.setTaskId(butlerTaskSend.getId());
			transferList2.setType(0);
			List<TransferList> transferListSend = transferListService.selectByCondition(transferList2);
			returnMap.put("carType", carType.getCarType());
			returnMap.put("brand", carType.getBrand());
			if(carOperate!=null){
				returnMap.put("carType", carOperate.getCarType());
				returnMap.put("brand", carOperate.getCarBrand());
			}
			returnMap.put("memberItemName", powerUsed.getMemberItemName());
			returnMap.put("memberName", member.getName());
			
//		用车的状态0：待送车 1：已出发送车 2 已到达 3送车完成 4已出发取车 5 管家已到达 6取车完成 7：已取消',
			if(powerUsed.getUsedStatus()==0||powerUsed.getUsedStatus()==1||powerUsed.getUsedStatus()==2){
				returnMap.put("carUsedTime", DateUtils.formatDate(powerUsed.getCarUsedTime(), "yyyy-MM-dd"));
				returnMap.put("carReturnTime", DateUtils.formatDate(powerUsed.getCarReturnTime(), "yyyy-MM-dd"));
				returnMap.put("status", "待送车");
				returnMap.put("total", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//				替换车型费
				returnMap.put("replacecarPrice", powerUsedCost.getReplacecarPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}else if(powerUsed.getUsedStatus()==6){
				returnMap.put("carUsedTime", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd") );
				returnMap.put("carReturnTime", DateUtils.formatDate(butlerTaskGet.getCompleteTime(), "yyyy-MM-dd") );
				returnMap.put("status", "已完成");
				returnMap.put("total", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				returnMap.put("carPlateNum", carOperate.getCarPlateNum());
//				加急费
				returnMap.put("hurryPrice", powerUsedCost.getHurryPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				/*用车费*/
				returnMap.put("totalPrice", powerUsedCost.getUsedcarPrice().subtract(powerUsedCost.getCouponPrice()).abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				returnMap.put("couponPrice", powerUsedCost.getCouponPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				returnMap.put("balance", powerUsedCost.getUsedcarPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//				替换车型费
				returnMap.put("replacecarPrice", powerUsedCost.getReplacecarPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				List<String> imageListGet = new ArrayList<>();
				if(CollectionUtils.isNotEmpty(transferListGet)){
					for (TransferList tran : transferListGet) {
						imageListGet.add(tran.getImageUrl());
					}
				}
				returnMap.put("returntransfer", imageListGet);
				
				List<String> imageListSend = new ArrayList<>();
				if(CollectionUtils.isNotEmpty(transferListSend)){
					for (TransferList tran : transferListSend) {
						imageListSend.add(tran.getImageUrl());
					}
				}
				returnMap.put("sendtransfer", imageListSend);
				
			/*	if(CollectionUtils.isNotEmpty(transferListGet)){
					returnMap.put("returntransfer", transferListGet.get(0).getImageUrl());
				}
				if(CollectionUtils.isNotEmpty(transferListSend)){
					returnMap.put("sendtransfer", transferListSend.get(0).getImageUrl());
				}*/
//				油耗
				returnMap.put("gasolinePrice", powerUsedCost.getGasolinePrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				returnMap.put("usedDay", PowerUsedCostLogList.size());
//				是否临时换车
				if(powerUsed.getChangeStatus()==1){
					CarOperate carOperateChange = carOperateService.findById(powerUsed.getChangeCaroperateId());
					returnMap.put("changeCarPlateNum", carOperateChange.getCarPlateNum());
					returnMap.put("changeCarType", carOperateChange.getCarType());
					returnMap.put("changeBrand", carOperateChange.getCarBrand());
					
				}
				
			}else if(powerUsed.getUsedStatus()==7){
				returnMap.put("status", "已取消");
				//取消用车的时间
				returnMap.put("carUsedTime", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd"));
				returnMap.put("contractPrice", powerUsedCost.getContractPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				returnMap.put("total", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//				替换车型费
				returnMap.put("replacecarPrice", powerUsedCost.getReplacecarPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			if(butlerTaskSend.getStatus()==7){
				if(powerUsed.getUsedStatus()==3||powerUsed.getUsedStatus()==4||powerUsed.getUsedStatus()==5){
					returnMap.put("carUsedTime", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd") );
					returnMap.put("carReturnTime",DateUtils.formatDate(powerUsed.getCarReturnTime(), "yyyy-MM-dd"));
					returnMap.put("status", "使用中");
					returnMap.put("total", powerUsedCost.getTotal().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					returnMap.put("hurryPrice", powerUsedCost.getHurryPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					returnMap.put("carPlateNum", carOperate.getCarPlateNum());
					
					/*用车费*/
					returnMap.put("totalPrice", powerUsedCost.getUsedcarPrice().subtract(powerUsedCost.getCouponPrice()).abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					returnMap.put("couponPrice", powerUsedCost.getCouponPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					returnMap.put("balance", powerUsedCost.getUsedcarPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					
					returnMap.put("replacecarPrice", powerUsedCost.getReplacecarPrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					
//					if(CollectionUtils.isNotEmpty(transferListGet)){
//						returnMap.put("returntransfer", transferListGet.get(0).getImageUrl());
//					}
					
					List<String> imageListSend = new ArrayList<>();
					if(CollectionUtils.isNotEmpty(transferListSend)){
						for (TransferList tran : transferListSend) {
							imageListSend.add(tran.getImageUrl());
						}
					}
					returnMap.put("sendtransfer", imageListSend);
					
					/*if(CollectionUtils.isNotEmpty(transferListSend)){
						returnMap.put("sendtransfer", transferListSend.get(0).getImageUrl());
					}*/
//					油耗
					returnMap.put("gasolinePrice", powerUsedCost.getGasolinePrice().abs().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					
					returnMap.put("usedDay", PowerUsedCostLogList.size());
//					是否临时换车
					if(powerUsed.getChangeStatus()==1){
						CarOperate carOperateChange = carOperateService.findById(powerUsed.getChangeCaroperateId());
						returnMap.put("changeCarPlateNum", carOperateChange.getCarPlateNum());
						returnMap.put("changeCarType", carOperateChange.getCarType());
						returnMap.put("changeBrand", carOperateChange.getCarBrand());
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 用车记录详情
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/powerUsedRecordDetail")
	@ResponseBody
	public Object powerUsedRecordDetailAndroid(@RequestParam(value = "message", required = true) String message){
		return powerUsedRecordDetailIos(message);
	}
	
	/**
	 * 我的余额
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/myBalance")
	@ResponseBody
	public Object myBalanceIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
//		String memberId = params.getString("memberId");
//		String powerUsedId = params.getString("powerUsedId");
		logger.info("我的余额---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		/*会员权益信息*/
		MemberItem memberItem = memberItemService.findById(Long.valueOf(memberItemId));
	
		returnMap.put("balance",memberItem.getBalance().toString());

		/*余额日志*/
		MemberAccountLog searchMemberAccountLog = new MemberAccountLog();
		searchMemberAccountLog.setMemberItemId(Long.valueOf(memberItemId));
		searchMemberAccountLog.setOrderByType(1);
		searchMemberAccountLog.setSearchAccountType("balance");

		List<MemberAccountLog>  memberAccountLogs = memberAccountLogService.selectByCondition(searchMemberAccountLog);

		for(MemberAccountLog memberAccountLog : memberAccountLogs){
			//if(memberAccountLog.getBalance().compareTo(BigDecimal.ZERO) == 1){
				Map<String, Object> map = new HashMap<>();
				
				if(memberAccountLog.getBalance().compareTo(BigDecimal.ZERO) != 0){
					map.put("balanceReason", memberAccountLog.getBalanceReason());
				}
				
				String balance = memberAccountLog.getBalance().toString();
				int last =0;
				int lastIndexOf=0;
				if(balance.indexOf(".")!=-1){
					lastIndexOf = balance.lastIndexOf(".");
					String string2 = balance.substring(lastIndexOf+1);
					last = Integer.parseInt(string2);
				}
				if(memberAccountLog.getBalance().compareTo(BigDecimal.ZERO) != 0){
					if(memberAccountLog.getBalance().signum()==-1){//是负数
						if(last>0){
							map.put("total",balance);
						}else{
							map.put("total",balance.substring(0, lastIndexOf));
						}
						
					}else{
						if(last>0){
							map.put("total","+"+balance);
						}else{
							map.put("total","+"+balance.substring(0, lastIndexOf));
						}
						
					}
				}	
				if(memberAccountLog.getRemark() != null){
					if(memberAccountLog.getRemark().indexOf("赠送") != -1){
						//赠送金额
						BigDecimal giftAmount = memberAccountLog.getTotal().subtract(memberAccountLog.getBalance()).subtract(memberAccountLog.getDeposit());
						//余额
						BigDecimal total = memberAccountLog.getTotal().subtract(giftAmount).subtract(memberAccountLog.getDeposit());
						if(giftAmount.compareTo(BigDecimal.ZERO) == 1){
							map.put("giftReason", "赠送金额");
							map.put("giftAmount", "+"+giftAmount);
						}
						if(memberAccountLog.getBalance().compareTo(BigDecimal.ZERO) != 0){
							map.put("balanceReason", "充值");
							map.put("total", "+"+total);
						}
					}
				}
				
				map.put("memberItemName",memberAccountLog.getMemberItemName());
				map.put("time", DateUtils.formatDate(memberAccountLog.getCreated(), "yyyy-MM-dd"));
				/*设置用车记录*/
				Long powerUsedId = memberAccountLog.getPowerUsedId();
				try {
					powerUsedId = powerUsedId.longValue();
				} catch (Exception e) {
					powerUsedId = 0L;
				}
				if(powerUsedId != 0){
					map.put("powerUsedId", powerUsedId+"");
					PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
					if(powerUsed != null){
						CarType carType = carTypeService.findById(powerUsed.getCarTypeId());
						if(carType != null){
							if(memberAccountLog.getTransformType()==4){
								map.put("balanceReason", carType.getBrand()+" "+carType.getCarType()+"使用费用");
							}else{
								map.put("balanceReason", memberAccountLog.getBalanceReason());
							}
						}
						ButlerTask butlerTask = new ButlerTask();
						butlerTask.setPowerUsedId(powerUsed.getId());
						butlerTask.setType(2);//取车任务
						ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
						
						butlerTask.setType(3);//送车任务
						ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
						if(butlerTaskSend.getStatus()==7){ //送车任务完成 取送车完成的时间 加 取车任务的时间
							map.put("time", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(butlerTaskGet.getPlanTime(), "yyyy-MM-dd"));
							if(butlerTaskGet.getStatus()==7){
								map.put("time", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(butlerTaskGet.getCompleteTime(), "yyyy-MM-dd"));
							}
						}else{
							map.put("time", DateUtils.formatDate(powerUsed.getCarUsedTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(powerUsed.getCarReturnTime(), "yyyy-MM-dd"));
						}
					}
				}
				returnList.add(map);
			}
		//}
		
		//储值订单
	/*	ChargeOrder order = new ChargeOrder();
		order.setStatus(3);
		order.setCreatedMemberId(memberItem.getMemberId().toString());
		List<ChargeOrder> list = chargeOrderService.selectByCondition(order);
		if(CollectionUtils.isNotEmpty(list)){
			for(ChargeOrder chargeOrder : list){
				if(chargeOrder.getBalance().signum() > 0){
					Map<String, Object> map = new HashMap<>();
					map.put("memberItemName",chargeOrder.getMemberItemName());
					map.put("time",  DateUtils.formatDate(chargeOrder.getCreated(), "yyyy-MM-dd"));
					if(chargeOrder.getGiftAmount().compareTo(BigDecimal.ZERO) > 0){
						if(chargeOrder.getBalance().compareTo(BigDecimal.ONE) == -1){
							map.put("giftReason", "赠送金额");
							map.put("giftAmount", "+"+chargeOrder.getGiftAmount());
							map.put("balanceReason", "充值");
							map.put("total", "+"+chargeOrder.getBalance());
						}else{
							
							map.put("giftReason", "赠送金额");
							BigInteger giftAmount = chargeOrder.getGiftAmount().toBigIntegerExact();
							map.put("giftAmount", "+"+giftAmount);
							map.put("balanceReason", "充值");
							BigInteger balance = chargeOrder.getBalance().toBigIntegerExact();
							map.put("total", "+"+balance);
						}
					}else{
						if(chargeOrder.getBalance().compareTo(BigDecimal.ONE) == -1){
							map.put("balanceReason", "充值");
							map.put("total", "+"+chargeOrder.getBalance());
						}else{
							map.put("balanceReason", "充值");
							BigInteger balance = chargeOrder.getBalance().toBigIntegerExact();
							map.put("total", "+"+balance);
						}
					}
					//map.put("powerUsedId", chargeOrder.getCreatedMemberId());
					returnList.add(map);
				}
			}
		}*/
		returnMap.put("balanceList", returnList);
		
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
		
	}
	public static void main(String[] args) {
		BigDecimal s= new BigDecimal(-25.00);
		String string = s.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		if(string.indexOf(".")!=-1){
			int lastIndexOf = string.lastIndexOf(".");
			String string2 = string.substring(lastIndexOf+1);
			System.out.println(Integer.parseInt(string2));
			System.out.println(string.substring(0, lastIndexOf));
		}
	}
	/**
	 * 我的余额
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/myBalance")
	@ResponseBody
	public Object myBalanceAndroid(@RequestParam(value = "message", required = true) String message){
		return myBalanceIos(message);
	}
	/**
	 * 我的押金
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/myDeposit")
	@ResponseBody
	public Object myDepositIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		logger.info("我的押金---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		Map<String, Object> returnMap = new HashMap<>();
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		/*会员权益信息*/
		MemberItem memberItem = memberItemService.findById(Long.valueOf(memberItemId));
		
		returnMap.put("deposit",memberItem.getDeposit().toString());
		
		/*押金日志*/
		MemberAccountLog searchMemberAccountLog = new MemberAccountLog();
		searchMemberAccountLog.setMemberItemId(Long.valueOf(memberItemId));
		searchMemberAccountLog.setOrderByType(1);
		searchMemberAccountLog.setSearchAccountType("deposit");
		
		List<MemberAccountLog>  memberAccountLogs = memberAccountLogService.selectByCondition(searchMemberAccountLog);

		for(MemberAccountLog memberAccountLog : memberAccountLogs){
			Map<String, Object> map = new HashMap<>();
			
			map.put("depositReason", memberAccountLog.getDepositReason());
			
//			map.put("total",memberAccountLog.getDeposit());
			
			String deposit = memberAccountLog.getDeposit().toString();
			int last =0;
			int lastIndexOf=0;
			if(deposit.indexOf(".")!=-1){
				lastIndexOf = deposit.lastIndexOf(".");
				String string2 = deposit.substring(lastIndexOf+1);
				last = Integer.parseInt(string2);
			}
			if(memberAccountLog.getDeposit().signum()==-1){//是负数
				if(last>0){
					map.put("total",deposit);
				}else{
					map.put("total",deposit.substring(0, lastIndexOf));
				}
				
			}else{
				if(last>0){
					map.put("total","+"+deposit);
				}else{
					map.put("total","+"+deposit.substring(0, lastIndexOf));
				}
				
			}
			String total=(String)map.get("total");
			if("+0".equals(total)){
				continue;
			}
			map.put("memberItemName", memberAccountLog.getMemberItemName());
			map.put("time", DateUtils.formatDate(memberAccountLog.getCreated(), "yyyy-MM-dd"));
			/*设置用车记录*/
			Long powerUsedId = memberAccountLog.getPowerUsedId();
			try {
				powerUsedId = powerUsedId.longValue();
			} catch (Exception e) {
				powerUsedId = 0L;
			}
			if(powerUsedId != 0){
				if(memberAccountLog.getTransformType()!=11){//带充电
					map.put("powerUsedId", powerUsedId+"");
				}
				PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
				if(powerUsed != null){
					CarType carType = carTypeService.findById(powerUsed.getCarTypeId());
					if(carType != null){
						if(memberAccountLog.getTransformType()==4){
							map.put("depositReason", carType.getBrand()+" "+carType.getCarType()+"使用费用");
						}else{
							map.put("depositReason", memberAccountLog.getDepositReason());
						}
					}
					ButlerTask butlerTask = new ButlerTask();
					butlerTask.setPowerUsedId(powerUsed.getId());
					butlerTask.setType(2);//取车任务
					ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
					
					butlerTask.setType(3);//送车任务
					ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
					if(butlerTaskSend != null){
						if(butlerTaskSend.getStatus()==7){ //送车任务完成 取送车完成的时间 加 取车任务的时间
							map.put("time", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(butlerTaskGet.getPlanTime(), "yyyy-MM-dd"));
							if(butlerTaskGet != null){
								if(butlerTaskGet.getStatus()==7){
									map.put("time", DateUtils.formatDate(butlerTaskSend.getCompleteTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(butlerTaskGet.getCompleteTime(), "yyyy-MM-dd"));
								}
							}
						}else{
							map.put("time", DateUtils.formatDate(powerUsed.getCarUsedTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(powerUsed.getCarReturnTime(), "yyyy-MM-dd"));
						}
					}
				}
			}
			returnList.add(map);
		}
		//储值订单
		/*ChargeOrder order = new ChargeOrder();
		order.setStatus(3);
		order.setCreatedMemberId(memberItem.getMemberId().toString());
		List<ChargeOrder> list = chargeOrderService.selectByCondition(order);
		if(CollectionUtils.isNotEmpty(list)){
			for(ChargeOrder chargeOrder : list){
				Map<String, Object> map = new HashMap<>();
				if(chargeOrder.getDeposit().compareTo(BigDecimal.ZERO) == 1){
					if(chargeOrder.getDeposit().compareTo(BigDecimal.ONE) == -1){
						map.put("memberItemName",chargeOrder.getMemberItemName());
						map.put("time",  DateUtils.formatDate(chargeOrder.getCompletedTime(), "yyyy-MM-dd"));
						map.put("depositReason", "充值");
						//map.put("powerUsedId", chargeOrder.getCreatedMemberId());
						map.put("total", "+"+chargeOrder.getDeposit());
						
					}else{
						map.put("memberItemName",chargeOrder.getMemberItemName());
						map.put("time",  DateUtils.formatDate(chargeOrder.getCompletedTime(), "yyyy-MM-dd"));
						map.put("depositReason", "充值");
						//map.put("powerUsedId", chargeOrder.getCreatedMemberId());
						BigInteger deposit = chargeOrder.getDeposit().toBigIntegerExact();
						map.put("total", "+"+deposit);
					}
					returnList.add(map);
				}
			}
		}*/
		returnMap.put("depositList", returnList);
		
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
		
	}
	/**
	 * 我的押金
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/myDeposit")
	@ResponseBody
	public Object myDepositAndroid(@RequestParam(value = "message", required = true) String message){
		return myDepositIos(message);
	}
	
	/**
	 * 查询库存 排期
	* @Description:TODO  
	* @param carTypeId 车型
	* @param start 开始时间  end 结束时间    
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:14:39   
	* @return String
	 */
	@RequestMapping("/gcarapp/android/mycars/searchStore")
	@ResponseBody
	public Object searchStore(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		List<Map<String,Object>> list = null;
		List<List<Map<String,Object>>> resultList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			message = AppUtil.decode(message);
			JSONObject params = JSONObject.parseObject(message);
			//String useCarStartTime = params.getString("useCarStartTime");
			//String useCarEndTime = params.getString("useCarEndTime");
			String page = params.getString("page");
			String carTypeId = params.getString("carTypeId");
			String mobile = params.getString("mobile");
		
			// sign校验
			String sign = params.getString("sign");
			boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				this.logger.info("解密错误---");
				return new OpenResponse("false", "sign不匹配", null, "100002");
			}
			Date startDate = new Date();
			Date nowDate = new Date();
			Date endDate = new Date();
			//后期需求更改 全部都是3个月数据
			//第一页   3个月数据
			if("0".equals(page)){
				endDate.setMonth(startDate.getMonth() + 2);
				Calendar calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
			//第二页   2个月数据
			}else if("1".equals(page)){
				startDate.setMonth(startDate.getMonth() + 3);
				//endDate.setYear(startDate.getYear());
				endDate.setMonth(endDate.getMonth() + 3 + 2);
				Calendar calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
			// 每页 2个月数据	
			}else{
				startDate.setMonth(startDate.getMonth() + (Integer.decode(page) * 2) + 1);
				//endDate.setYear(startDate.getYear());
				endDate.setMonth(endDate.getMonth() + (Integer.decode(page) * 2) + 3);
				Calendar calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
				if(endDate.getYear() - nowDate.getYear() >= 2){
					endDate.setYear(nowDate.getYear() + 1);
					endDate.setMonth(11);
					calendar = getMaxDate(sdf.format(endDate), true);
					endDate = calendar.getTime();
					if(startDate.getYear() - nowDate.getYear() >= 2){
						return  new OpenResponse("true", "操作成功", resultList, "100000");
					}
				}
			}		
			resultList = new ArrayList<List<Map<String,Object>>>();
			//开始时间  结束时间 在一个月的
			if(endDate.getMonth() - startDate.getMonth() == 0 ){
				list = new ArrayList<Map<String,Object>>();
				list = makeStore(carTypeId, startDate, endDate, list,"0");
				resultList.add(list);
			}else{
				//循环多个月的排期
				int forNum = 0;
				if(endDate.getYear() == startDate.getYear()){
					forNum = endDate.getMonth() -  startDate.getMonth();
				}else{
					forNum = 12 - startDate.getMonth() + endDate.getMonth();
				}
				int month = startDate.getMonth();
				for(int i = 0;i <= forNum; i++){
					//根据APP的需要  每个月都得从 1号到31号查询返回,不根据开始结束时间的 日期查询，
					//保留目前的 根据开始时间结束时间查询方法
					//每个月的排期
					startDate.setMonth(month);
					if(i == forNum){
						list = new ArrayList<Map<String,Object>>();
						//最后一个月的排期
						list = makeStoreEnd(carTypeId, startDate, endDate, list,"0");
						resultList.add(list);
					}else if(i == 0){
						list = new ArrayList<Map<String,Object>>();
						//开始第一个月份的排期
						list = makeStoreStart(carTypeId, startDate, endDate, list,"0");
						resultList.add(list);
					}else{
						list = new ArrayList<Map<String,Object>>();
						//中间月份的排期
						list = makeStore(carTypeId, startDate, endDate, list,"0");
						resultList.add(list);
					}
					++month;
					if(month > 12){
						month = 1;
					}
				}
			}
		} catch (ParseException e) {
			logger.info("查看排期错误searchStore"+e);
		}
		return  new OpenResponse("true", "操作成功", resultList, "100000");
	}
	
	/**
	 * 查询库存 排期
	* @Description:TODO  
	* @param carTypeId 车型
	* @param start 开始时间  end 结束时间    
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:14:39   
	* @return String
	 */
	@RequestMapping("/gcarapp/ios/mycars/searchStore")
	@ResponseBody
	public Object searchiosStore(@RequestParam(value = "message", required = true) String message){
		return searchStore(message);
	}
	
	
	/**
	 * 查询库存 排期
	* @Description:TODO  
	* @param carTypeId 车型
	* @param start 开始时间  end 结束时间    
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:14:39   
	* @return String
	 */
	@RequestMapping("/gcarapp/android/mycars/searchEndStore")
	@ResponseBody
	public Object searchEndStore(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		List<Map<String,Object>> list = null;
		List<List<Map<String,Object>>> resultList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			message = AppUtil.decode(message);
			JSONObject params = JSONObject.parseObject(message);
			//String useCarStartTime = params.getString("useCarStartTime");
			//String useCarEndTime = params.getString("useCarEndTime");
			String page = params.getString("page");
			String endPage = params.getString("endPage");
			String carTypeId = params.getString("carTypeId");
			/*String mobile = params.getString("mobile");
			// sign校验
			String sign = params.getString("sign");
			boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				this.logger.info("解密错误---");
				return new OpenResponse("false", "sign不匹配", null, "100002");
			}*/
			Date startDate = new Date();
			Date nowDate = new Date();
			Date endDate = new Date();
			if("0".equals(page)){
				
			}else if("1".equals(page)){
				startDate.setMonth(startDate.getMonth() + 3);
			}else{
				startDate.setMonth(startDate.getMonth() + (Integer.decode(page) * 2) + 1);
			}
			if("0".equals(endPage)){
				endDate.setYear(startDate.getYear());
				endDate.setMonth(startDate.getMonth() + 2);
			}else if("1".equals(endPage)){
				endDate.setYear(startDate.getYear());
				endDate.setMonth(endDate.getMonth() + 3 + 1);
			}else{
				endDate.setYear(startDate.getYear());
				endDate.setMonth(endDate.getMonth() + (Integer.decode(endPage) * 2) + 2);
			}
			//第一页   3个月数据
			if("0".equals(page)){
				//endDate.setMonth(startDate.getMonth() + 2);
				Calendar calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
			//第二页   2个月数据
			}else if("1".equals(page)){
				//startDate.setMonth(startDate.getMonth() + 3);
				//endDate.setMonth(endDate.getMonth() + 3 + 1);
				Calendar calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
			// 每页 2个月数据	
			}else{
				//startDate.setMonth(startDate.getMonth() + (Integer.decode(page) * 2) + 1);
				//endDate.setMonth(endDate.getMonth() + (Integer.decode(page) * 2) + 2);
				Calendar calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
				if(endDate.getYear() - nowDate.getYear() >= 2){
					endDate.setYear(nowDate.getYear() + 1);
					endDate.setMonth(11);
					calendar = getMaxDate(sdf.format(endDate), true);
					endDate = calendar.getTime();
					if(startDate.getYear() - nowDate.getYear() >= 2){
						return  new OpenResponse("true", "操作成功", resultList, "100000");
					}
				}
			}		
			resultList = new ArrayList<List<Map<String,Object>>>();
			//开始时间  结束时间 在一个月的
			if(endDate.getYear() == startDate.getYear() && endDate.getMonth() - startDate.getMonth() == 0 ){
				list = new ArrayList<Map<String,Object>>();
				list = makeStore(carTypeId, startDate, endDate, list,"0");
				resultList.add(list);
			}else{
				//循环多个月的排期
				int forNum = 0;
				if(endDate.getYear() == startDate.getYear()){
					forNum = endDate.getMonth() -  startDate.getMonth();
				}else{
					forNum = 12 - startDate.getMonth() + endDate.getMonth();
				}
				int month = startDate.getMonth();
				for(int i = 0;i <= forNum; i++){
					//根据APP的需要  每个月都得从 1号到31号查询返回,不根据开始结束时间的 日期查询，
					//保留目前的 根据开始时间结束时间查询方法
					//每个月的排期
					startDate.setMonth(month);
					if(i == forNum){
						list = new ArrayList<Map<String,Object>>();
						//最后一个月的排期
						list = makeStoreEnd(carTypeId, startDate, endDate, list,"0");
						resultList.add(list);
					}else if(i == 0){
						list = new ArrayList<Map<String,Object>>();
						//开始第一个月份的排期
						list = makeStoreStart(carTypeId, startDate, endDate, list,"0");
						resultList.add(list);
					}else{
						list = new ArrayList<Map<String,Object>>();
						//中间月份的排期
						list = makeStore(carTypeId, startDate, endDate, list,"0");
						resultList.add(list);
					}
					++month;
					if(month > 12){
						month = 1;
					}
					
				}
			}
		} catch (ParseException e) {
			logger.info("查看排期错误searchStore"+e);
		}
		return  new OpenResponse("true", "操作成功", resultList, "100000");
	}
	
	/**
	 * 查询库存 排期
	* @Description:TODO  
	* @param carTypeId 车型
	* @param start 开始时间  end 结束时间    
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:14:39   
	* @return String
	 */
	@RequestMapping("/gcarapp/ios/mycars/searchEndStore")
	@ResponseBody
	public Object searchiosEndStore(@RequestParam(value = "message", required = true) String message){
		return searchEndStore(message);
	}
	
	/**
	 * 查询库存 排期
	* @Description:TODO  
	* @param carTypeId 车型
	* @param start 开始时间  end 结束时间    
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:14:39   
	* @return String
	 */
	@RequestMapping("/gcarapp/android/mycars/searchDateStore")
	@ResponseBody
	public Object searchDateStore(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		List<Map<String,Object>> list = null;
		//Map<String,Object> result = new HashMap<String, Object>();
		List<List<Map<String,Object>>> resultList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");  
		try {
			message = AppUtil.decode(message);
			JSONObject params = JSONObject.parseObject(message);
			//String useCarStartTime = params.getString("useCarStartTime");
			//String useCarEndTime = params.getString("useCarEndTime");
			//String page = params.getString("page");
			//String endPage = params.getString("endPage");
			String startStr = params.getString("startStr");
			String endStr = params.getString("endStr");
			String carTypeId = params.getString("carTypeId");
			String powerUsedId = params.getString("powerUsedId");
			/*String mobile = params.getString("mobile");
			// sign校验
			String sign = params.getString("sign");
			boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
			if (validate == false) {
				this.logger.info("解密错误---");
				return new OpenResponse("false", "sign不匹配", null, "100002");
			}*/
			Date start = sdf.parse(startStr);
			Date end = sdf.parse(endStr);
			resultList = new ArrayList<List<Map<String,Object>>>();
			
			//开始时间  结束时间 在一个月的
			list = new ArrayList<Map<String,Object>>();
			//开始时间  结束时间 在一个月的
			if(end.getYear() == start.getYear() && end.getMonth() - start.getMonth() == 0 ){
				list = new ArrayList<Map<String,Object>>();
				list = makeStore(carTypeId, start, end, list,"0");
				resultList.add(list);
			}else{
				//循环多个月的排期
				int forNum = 0;
				if(end.getYear() == start.getYear()){
					forNum = end.getMonth() -  start.getMonth();
				}else{
					forNum = 12 - start.getMonth() + end.getMonth();
				}
				int month = start.getMonth();
				for(int i = 0;i <= forNum; i++){
					//根据APP的需要  每个月都得从 1号到31号查询返回,不根据开始结束时间的 日期查询，
					//保留目前的 根据开始时间结束时间查询方法
					//每个月的排期
					start.setMonth(month);
					if(i == forNum){
						list = new ArrayList<Map<String,Object>>();
						//最后一个月的排期
						list = makeStoreEnd(carTypeId, start, end, list,powerUsedId);
						resultList.add(list);
					}else if(i == 0){
						list = new ArrayList<Map<String,Object>>();
						//开始第一个月份的排期
						list = makeStoreStart(carTypeId, start, end, list,powerUsedId);
						resultList.add(list);
					}else{
						list = new ArrayList<Map<String,Object>>();
						//中间月份的排期
						list = makeStore(carTypeId, start, end, list,powerUsedId);
						resultList.add(list);
					}
					++month;
					if(month > 12){
						month = 1;
					}
					
				}
			}
		//	resultList.add(list);
			
		} catch (ParseException e) {
			logger.info("查看排期错误searchStore"+e);
		}
		return  new OpenResponse("true", "操作成功", resultList, "100000");
	}
	
	/**
	 * 查询库存 排期
	* @Description:TODO  
	* @param carTypeId 车型
	* @param start 开始时间  end 结束时间    
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:14:39   
	* @return String
	 */
	@RequestMapping("/gcarapp/ios/mycars/searchDateStore")
	@ResponseBody
	public Object searchiosDateStore(@RequestParam(value = "message", required = true) String message){
		return searchDateStore(message);
	}
	
	/**
	 * 开始 第一个月的排期
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:17:45   
	* @return String
	 */
	private List<Map<String,Object>> makeStoreStart(String carTypeId,Date startDate,Date endDate,List<Map<String,Object>> list,String powerUsedId){
		List<Date> dates = getAllTheDateOftheMonth(startDate,1);
		return isStore(dates, carTypeId,list,powerUsedId);
	}
	
	/**
	 * 中间月份的排期
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:18:00   
	* @return String
	 */
	private List<Map<String,Object>> makeStore(String carTypeId,Date startDate,Date endDate,List<Map<String,Object>> list,String powerUsedId){
		List<Date> dates = getAllTheDateOftheMonth(startDate,1);
		return isStore(dates, carTypeId,list,powerUsedId);
	}
	
	/**
	 * 最后一个月的排期
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:18:10   
	* @return String
	 */
	private List<Map<String,Object>> makeStoreEnd(String carTypeId,Date startDate,Date endDate,List<Map<String,Object>> list,String powerUsedId){
		List<Date> dates = getAllTheDateOftheMonth(startDate,1);
		return isStore(dates, carTypeId,list,powerUsedId);
	}
	
	/**
	 * 查询排期
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:18:17   
	* @return String
	 */
	private List<Map<String,Object>> isStore(List<Date> dates,String carTypeId,List<Map<String,Object>> list,String powerUsedId){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nowTime = new Date();
			int one = 1;
			for(Date date : dates){
				Map<String,Object> map = new HashMap<String, Object>();
				String allDate = sdf.format(date);
				boolean flag = false;
				boolean limitLine = false;
				int store = 2;
				try {
					if(StringUtils.isEmpty(carTypeId)){
						carTypeId = null;
					}
					store = carOperatePlanService.searchStore(Long.decode(carTypeId), getMiniDate(allDate, false).getTime(), getMaxDate(allDate, false).getTime(),powerUsedId);
					one++;
				} catch (NumberFormatException e) {
					logger.info("查询这段时间是否限号错误---searchStore"+e);
				} catch (ParseException e) {
					logger.info("查询这段时间是否限号错误---searchStore"+e);
				}
				Date nowDate = sdf.parse(allDate);
				Long time = nowDate.getTime()/1000;
				if(store == 1 || store == 3){
					flag = true;
				}
				if(store == 3){
					limitLine = true;
				}
				map.put("time", time);
				map.put("flag", flag);
				map.put("limitLine", limitLine);
				if(date.before(nowTime)){
					map.put("time", time);
					map.put("flag", false);
				}
				if(nowDate.getDate() == nowTime.getDate()){
					map.put("time", time);
					map.put("flag", flag);
				}
				//测试需要，后期需要注释
				/*if(date.getDate() == 9){
					map.put("time", time);
					map.put("flag", true);
					map.put("limitLine", true);
				}*/
				list.add(map);
			}
		} catch (ParseException e) {
			logger.info("查看排期错误isStore"+e);
		}
		return list;
	}
	
	
	/**
	 * 获取本月最大的日期  例：2016-8-31 23:59:59
	 *  flag true设置date天为最大， false不设置天
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 上午9:52:26   
	* @return String
	 */
	private Calendar getMaxDate(String dateStr,boolean flag) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if(flag){
			// 设置日期为本月最大日期
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		return calendar;
	}
	
	/**
	 * 获取本月最小的日期  例：2016-8-01 00:00:00
	 * flag true设置date天为最小， false不设置天
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 上午9:52:26   
	* @return String
	 */
	private Calendar getMiniDate(String dateStr,boolean flag) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if(flag){
			// 设置日期为本月最小日期
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		return calendar;
	}
	
	/**
	 * 循环这个月的每一天
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:22:00   
	* @return String
	 */
	private static List<Date> getAllTheDateOftheMonth(Date date,int day) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, day);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	/**
	 * 循环这个月的每一天  到传入的end天数
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 上午10:22:19   
	* @return String
	 */
	private static List<Date> getAllTheDateOftheMonthEnd(Date date,int day,int end) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, day);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
			if(end < cal.get(Calendar.DATE)){
				return list;
			}
		}
		return list;
	}
	/**
	 * 记录用户用车意向车型---点击率
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/ios/mycars/expectCartype")
	@ResponseBody
	public Object expectCartypeForIos(@RequestParam(value = "message", required = true) String message){
		try {
			message = AppUtil.decode(message);
			JSONObject params = JSONObject.parseObject(message);
			String planStartTime = params.getString("planStartTime");
			String planEndTime = params.getString("planEndTime");
			String carBrand = params.getString("carBrand");
			String carFrom = params.getString("carFrom");
			String carToken = params.getString("carToken");
			String cartypeId = params.getString("cartypeId");
			String cartypeName = params.getString("cartypeName");
			String clickCount = params.getString("clickCount");
			String memberId = params.getString("memberId");
			String memberItemId = params.getString("memberItemId");
//			String memberItemName = params.getString("memberItemName");
//			String memberName = params.getString("memberName");
			String status = params.getString("status");
			logger.info("记录用户用车意向车型---点击率>>>>>>>>>>>memberId="+memberId+"点击了 车型》》》》》"+cartypeName);
			MemberItem memberItem = memberItemService.findById(Long.valueOf(memberItemId));
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			
			ExpectCartype expectCartype = new ExpectCartype();
			expectCartype.setCarBrand(carBrand);
			expectCartype.setCarFrom(Integer.parseInt(carFrom));
			expectCartype.setCarToken(Long.valueOf(carToken));
			expectCartype.setCartypeId(Long.valueOf(cartypeId));
			expectCartype.setCartypeName(cartypeName);
			expectCartype.setClickCount(Integer.parseInt(clickCount));
			expectCartype.setCreated(new Date());
			expectCartype.setMemberId(Long.valueOf(memberId));
			expectCartype.setMemberItemId(Long.valueOf(memberItemId));
			expectCartype.setMemberItemName(memberItem.getItemName());
			expectCartype.setMemberName(member.getName());
			expectCartype.setPlanEndTime(DateUtils.parseDate(planEndTime));
			expectCartype.setPlanStartTime(DateUtils.parseDate(planStartTime));
			expectCartype.setStatus(Integer.parseInt(status));
			expectCartypeService.insertSelective(expectCartype);
		} catch (Exception e) {
			logger.info("记录用户用车意向车型---点击率异常"+e.getMessage());
			return  new OpenResponse("true", "操作成功", true, "100000");
		}
		return  new OpenResponse("true", "操作成功", true, "100000");
	}
	/**
	 * 记录用户用车意向车型---点击率
	 * @param message
	 * @return
	 */
	@RequestMapping("/gcarapp/android/mycars/expectCartype")
	public void expectCartypeForAndroid(@RequestParam(value = "message", required = true) String message){
		expectCartypeForIos(message);
	}
	
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static long formatDate(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		if(min>0){
			day=day+1;
		}else{
			if(hour>0){
				day=day+1;
			}
		}
		return day;
    }
    
	
	/**
	 * 修改用车、还车任务时间地点接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/mycars/updateButlerTask")
	@ResponseBody
	public Object updateButlerTaskForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		logger.info("---调用---- 点击取消用车校验接口------");
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		String powerUsedId = params.getString("powerUsedId");
		String carUsedTime = params.getString("carUsedTime");
		String carUsedAddress = params.getString("carUsedAddress");
		String carReturnTime = params.getString("carReturnTime");
		String carReturnAddress = params.getString("carReturnAddress");
		String carUsedDay = params.getString("carUsedDay");
		
		String mobile = params.getString("mobile");
		logger.info("修改用车、还车任务时间地点接口----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		
		Map<String, Object> returnMap=new HashMap<>();
		try {
			PowerUsed powerUsed = powerUsedService.findById(Long.valueOf(powerUsedId));
			
			if(powerUsed.getUsedStatus()==7||powerUsed.getUsedStatus()==6){//改任务已取消或已完成
				returnMap.put("code", "6");
				returnMap.put("msg", "本次用车已结束");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
			
			
			
			if(Long.valueOf(memberId).doubleValue()!=powerUsed.getMemberId().doubleValue()){
//				MemberItemShare trMemberItemShare = new MemberItemShare();
//				trMemberItemShare.setMemberItemId(Long.valueOf(memberId));
				MemberItemShare itemShareLoginer=memberItemShareService.selectByMemberId(Long.valueOf(memberId));
				boolean share =true;
				if(itemShareLoginer.getIsShare()!=1){//不可同时操作
					share =false;
				}
				MemberItemShare itemShareLaunch=memberItemShareService.selectByMemberId(powerUsed.getMemberId());//发起人
				if(itemShareLaunch.getIsShare()!=1){
					share =false;
				}
				if(!share){
					returnMap.put("code", "0");
					returnMap.put("msg", "只有发起用车的人才可以进行修改");
					return new OpenResponse("true", "操作成功", returnMap, "100000");
				}
			}
			
			
			PowerUsed p = new PowerUsed();
			p.setId(powerUsed.getId());
			
//			只修改了用车地点或还车地点
			if(StringUtils.isBlank(carUsedTime)&&StringUtils.isBlank(carReturnTime)){
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>只修改了用车地点或还车地点》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
			}else{
				
				if(StringUtils.isNotBlank(carUsedDay)){
					p.setCarUsedDay(Integer.parseInt(carUsedDay));
				}
				
				BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
//				Integer userUsecarAdvance = basicThreshold.getUserUsecarAdvance();//用户发起用车需提前X小时
				/*if(StringUtils.isNotBlank(carUsedTime)){
					Date carUsedTimeD = DateUtils.parseDate(carUsedTime);
					//判断用车时间是否提前X小时预约的
					if(carUsedTimeD.getTime() < DateUtils.addHours(new Date(), userUsecarAdvance).getTime()){
						returnMap.put("code", "1");
						returnMap.put("userUsecarAdvance", userUsecarAdvance+"");
						return new OpenResponse("true", "操作成功", returnMap, "100000");
					}
				}*/
				
				MemberItem memberItem = memberItemService.findById(powerUsed.getMemberItemId());
				BigDecimal totalPrice = null;
				BigDecimal balance = memberItem.getBalance();//余额
				BigDecimal deposit = memberItem.getDeposit();//押金
				boolean isdeposit=true;//押金足
				boolean isbalance=true;//余额
				BigDecimal depositUsecar = basicThreshold.getDepositUsecar();//'发起用车时会员押金不得少于(元)',
				
				ItemCartype itemCartype = new ItemCartype();
				itemCartype.setItemId(memberItem.getItemId());
				itemCartype.setCartypeId(powerUsed.getCarTypeId());
				List<ItemCartype> itemCartypeList = itemCartypeService.selectByCondition(itemCartype );
				BigDecimal dayPrice =null;
				//获取套餐下该车型
				if(CollectionUtils.isNotEmpty(itemCartypeList)){
					dayPrice=itemCartypeList.get(0).getDayPrice();
				}
				
				boolean isCheck=false;//是否判断金额标示
				if(StringUtils.isNotBlank(carUsedTime)){
//					相当于重新发起用车
					totalPrice = dayPrice.multiply(new BigDecimal(Integer.parseInt(carUsedDay)).setScale(2, BigDecimal.ROUND_HALF_UP));
					isCheck=true;
				}else if(StringUtils.isBlank(carUsedTime)&&StringUtils.isNotBlank(carReturnTime)){//延迟还车
					Date oldReturnTime = powerUsed.getCarReturnTime();
					Date updateDate = DateUtils.parseDate(carReturnTime);
					//还车延期
					if(oldReturnTime.getTime() < updateDate.getTime()){
						
						long delayDay=formatDate(updateDate.getTime()-oldReturnTime.getTime()); //延期天数
					
//						日租金x延期天数  四舍五入 保留2位小数
						totalPrice = dayPrice.multiply(new BigDecimal(delayDay).setScale(2, BigDecimal.ROUND_HALF_UP));
						isCheck=true;
					}
							
				}
				if(isCheck){//提前还车不需要判断 金额
					
//				当前用户押金小于 阀值
					if(deposit.doubleValue()<depositUsecar.doubleValue()){
						isdeposit=false;
					}
					
					//余额不足支持本次用车
					if(totalPrice.doubleValue()>balance.doubleValue()){
						isbalance=false;
					}
					if(!isdeposit&&!isbalance){//押金不足且余额不足
						returnMap.put("code", "4");
						returnMap.put("price", depositUsecar.longValue());
						returnMap.put("msg", "您的余额不足且押金不满"+depositUsecar.longValue()+"元，请及时续费");
						return new OpenResponse("true", "操作成功", returnMap, "100000");
					}
					if(!isbalance){//余额不足
						returnMap.put("code", "2");
						returnMap.put("msg", "您余额不足，请及时续费");
						return new OpenResponse("true", "操作成功", returnMap, "100000");
					}
					if(!isdeposit){//押金不足
						returnMap.put("code", "3");
						returnMap.put("price", depositUsecar.longValue());
						returnMap.put("msg", "您的押金不满"+depositUsecar.longValue()+"元，请及时续费");
						return new OpenResponse("true", "操作成功", returnMap, "100000");
					}
				}
			}
			
			
		
			if(StringUtils.isNotBlank(carReturnTime)){
				p.setCarReturnTime(DateUtils.parseDate(carReturnTime));
			}
			p.setCarReturnAddress(carReturnAddress);
			if(StringUtils.isNotBlank(carUsedTime)){
				p.setCarUsedTime(DateUtils.parseDate(carUsedTime));
			}
			p.setCarUsedAddress(carUsedAddress);
			//修改用车、还车任务
			
			Boolean b =butlerTaskService.updateSendAndReturnButlerTask(p);
			if(!b){//您已确认信息，不可编辑
				returnMap.put("code", "5");
				returnMap.put("msg", "该用车信息已确认，如需修改请联系客服 400-0520-952");
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}
				
			returnMap.put("code", "1");
		} catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败！", null, "100001");
		}
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	/**
	 * 修改用车、还车任务时间地点接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/updateButlerTask")
	@ResponseBody
	public Object updateButlerTaskForIos(@RequestParam(value = "message", required = true) String message){
		return updateButlerTaskForAndroid(message);
	}
	
	
	
	
	
}
