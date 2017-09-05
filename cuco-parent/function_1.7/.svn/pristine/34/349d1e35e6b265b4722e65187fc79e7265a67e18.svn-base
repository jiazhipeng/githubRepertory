package com.hy.gcar.web.app.carshow;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.BasicCartype;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.utils.AppUtil;

@Controller
//@RequestMapping("/app/carsshow")
public class CarShowForAppController {

	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private BasicThresholdService basicThresholdService;


	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 极车展厅
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/carsshow/carsList")
	@ResponseBody
	public Object carShowsForAndroid(){
		logger.info("android 进入展厅》》》》》》》》》》》》》》》》》》》》》》》》》》");
		List<CarType> carTypes = carTypeService.selectCarTypes("");
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		for (CarType carType : carTypes) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("carTypeId", carType.getId()+"");
			returnMap.put("brand", carType.getBrand());
			returnMap.put("carType", carType.getCarType());
			returnMap.put("imageUrl", carType.getImageUrl());
			returnMap.put("dayPrice", carType.getDayPrice()+"");
			returnList.add(returnMap);
		}
		return new OpenResponse("true", "操作成功", returnList, "100000");
	}
	/**
	 * 极车展厅ios
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/carsshow/carsList")
	@ResponseBody
	public Object carShowsForIos(){
		logger.info("ios 进入展厅》》》》》》》》》》》》》》》》》》》》》》》》》》");
		List<CarType> carTypes = carTypeService.selectCarTypes("");
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		for (CarType carType : carTypes) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("carTypeId", carType.getId()+"");
			returnMap.put("brand", carType.getBrand());
			returnMap.put("carType", carType.getCarType());
			returnMap.put("imageUrl", carType.getImageUrl());
			returnMap.put("dayPrice", carType.getDayPrice()+"");
			returnList.add(returnMap);
		}
		return new OpenResponse("true", "操作成功", returnList, "100000");
	}
	/**
	 * 极车展厅之车型明细
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/carsshow/carTypeDetail")
	@ResponseBody
	public Object carTypeDetailForAndroid(@RequestParam(value = "message", required = true) String message){
		logger.info("android 进入展厅 车型明细》》》》》》》》》》》》》》》》》》》》》》》》》》");
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String carTypeId = params.getString("id");
		String memberId = params.getString("memberId");
		logger.info("android 进入展厅 车型明细》》》》》》》》》》》》》》》》》》》》》》》》》》carTypeId="+carTypeId+">>>>>>>>>memberId="+memberId);
		if(StringUtils.isBlank(carTypeId)){
			return new OpenResponse("false", "车型ID不能为空！", null, "100001");
		}
		CarType carType = new CarType();
		carType.setId(Long.valueOf(carTypeId));
		if(StringUtils.isNotBlank(memberId)){
			carType.setMemberId(Long.valueOf(memberId));
		}
		carType = carTypeService.selectCarTypeDetail(carType);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("id", carType.getId()+"");
		returnMap.put("brand", carType.getBrand());
		returnMap.put("carType", carType.getCarType());
		returnMap.put("imageUrl", carType.getImageUrl());
		returnMap.put("memberItemId", carType.getMemberItemId());
		returnMap.put("dayPrice", carType.getDayPrice());
		returnMap.put("cartypeIntroduce", carType.getCartypeIntroduce());
		returnMap.put("flag", carType.getFlag()+"");
		returnMap.put("itemNames", carType.getItemNames());
		returnMap.put("vidioUrl", carType.getVidioUrl());
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		returnMap.put("carMatters", "管家服务时间 "+DateFormatUtils.format(basicThreshold.getServiceTimeStart(), "HH:mm")+"至"+DateFormatUtils.format(basicThreshold.getServiceTimeEnd(), "HH:mm"));
		returnMap.put("serviceNames", carType.getServiceNames());
		if(StringUtils.isNotBlank(carType.getCartypeParam())){
			List<BasicCartype> carTypes = JSON.parseArray(carType.getCartypeParam(), BasicCartype.class);
			List<Map<String, Object>> carPrams = new ArrayList<>();
			for (BasicCartype basicCartype : carTypes) {
				Map<String, Object> param = new HashMap<>();
				param.put("paramName", basicCartype.getParamContent());
				param.put("paramImage", basicCartype.getParamImage());
				carPrams.add(param);
			}
			returnMap.put("cartyepParam", carPrams);
		}
		
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 极车展厅之车型明细
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/carsshow/carTypeDetail")
	@ResponseBody
	public Object carTypeDetailForIos(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
//		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String carTypeId = params.getString("id");
		String memberId = params.getString("memberId");
		logger.info("ios 进入展厅 车型明细》》》》》》》》》》》》》》》》》》》》》》》》》》carTypeId="+carTypeId+">>>>>>>>>memberId="+memberId);
		if(StringUtils.isBlank(carTypeId)){
			return new OpenResponse("false", "车型ID不能为空！", null, "100001");
		}
		CarType carType = new CarType();
		carType.setId(Long.valueOf(carTypeId));
		if(StringUtils.isNotBlank(memberId)){
			carType.setMemberId(Long.valueOf(memberId));
		}
		carType = carTypeService.selectCarTypeDetail(carType);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("id", carType.getId()+"");
		returnMap.put("brand", carType.getBrand());
		returnMap.put("carType", carType.getCarType());
		returnMap.put("imageUrl", carType.getImageUrl());
		returnMap.put("dayPrice", carType.getDayPrice());
		returnMap.put("cartypeIntroduce", carType.getCartypeIntroduce());
		returnMap.put("flag", carType.getFlag()+"");
		returnMap.put("itemNames", carType.getItemNames());
		returnMap.put("memberItemId", carType.getMemberItemId());
		returnMap.put("vidioUrl", carType.getVidioUrl());
		returnMap.put("serviceNames", carType.getServiceNames());
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		returnMap.put("carMatters", "管家服务时间 "+DateFormatUtils.format(basicThreshold.getServiceTimeStart(), "HH:mm")+"至"+DateFormatUtils.format(basicThreshold.getServiceTimeEnd(), "HH:mm"));
		if(StringUtils.isNotBlank(carType.getCartypeParam())){
			List<BasicCartype> carTypes = JSON.parseArray(carType.getCartypeParam(), BasicCartype.class);
			List<Map<String, Object>> carPrams = new ArrayList<>();
			for (BasicCartype basicCartype : carTypes) {
				Map<String, Object> param = new HashMap<>();
				param.put("paramName", basicCartype.getParamContent());
				param.put("paramImage", basicCartype.getParamImage());
				carPrams.add(param);
			}
			returnMap.put("cartypeParam", carPrams);
		}
		
		
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	
}
