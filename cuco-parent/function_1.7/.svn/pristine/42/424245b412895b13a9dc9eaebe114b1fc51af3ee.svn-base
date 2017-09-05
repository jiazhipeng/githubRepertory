package com.hy.gcar.web.wechat.carsshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hy.common.utils.CookieUtils;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.constant.Constant;
import com.hy.gcar.entity.BasicCartype;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.member.MemberService;

@Controller
@RequestMapping("/wechat/carsshow")
public class CarShowForWechatController {

	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BasicThresholdService basicThresholdService;


	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 去极车展厅
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/toCarsList")
	public String toCarsList(String memberId,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws Exception{
		modelMap.put("memberId", memberId);
		String openidKey=Constant.load.getProperty("openidKey");
		String openid = CookieUtils.getCookie(request, openidKey);
		if(StringUtils.isNotBlank(openid)){
			Member  m = memberService.findMemberByOpenID(openid);
			if(m!=null){
				modelMap.put("memberId", m.getId());
			}
		}
		return "gcar/mycar/carsshow";
	}
	/**
	 * 极车展厅
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/carsList")
	@ResponseBody
	public Object carsList(){
		List<CarType> carTypes = carTypeService.selectCarTypes("");
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		for (CarType carType : carTypes) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("id", carType.getId());
			returnMap.put("brand", carType.getBrand());
			returnMap.put("carType", carType.getCarType());
			returnMap.put("imageUrl", carType.getImageUrl());
			returnMap.put("dayPrice", carType.getDayPrice());
			returnList.add(returnMap);
		}
		
		return returnList;
	}
	
	/**
	 * 极车展厅之车型明细
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/carTypeDetail")
	public String carTypeDetail(CarType carType,String price,ModelMap modelMap){
		ParamVerifyUtil.paramNotNull(carType);
		ParamVerifyUtil.paramNotNull(carType.getId(),"车型ID不能为空！");
		
		carType = carTypeService.selectCarTypeDetail(carType);
		modelMap.put("id", carType.getId());
		modelMap.put("dayPrice", carType.getDayPrice());
		modelMap.put("brand", carType.getBrand());
		modelMap.put("carType", carType.getCarType());
		modelMap.put("imageUrl", carType.getImageUrl());
//		returnMap.put("dayPrice", carType.getDayPrice());
		modelMap.put("cartypeIntroduce", carType.getCartypeIntroduce());
		modelMap.put("flag", carType.getFlag());
		modelMap.put("itemNames", carType.getItemNames());
		modelMap.put("vidioUrl", carType.getVidioUrl());
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		modelMap.put("carMatters", "管家服务时间 "+DateFormatUtils.format(basicThreshold.getServiceTimeStart(), "HH:mm")+"至"+DateFormatUtils.format(basicThreshold.getServiceTimeEnd(), "HH:mm"));
		modelMap.put("serviceNames", carType.getServiceNames());
		List<BasicCartype> carTypes =null;
		if(StringUtils.isNotBlank(carType.getCartypeParam())){
			carTypes = JSON.parseArray(carType.getCartypeParam(), BasicCartype.class);
			
		}
		
		modelMap.put("cartyepParam", carTypes);
		
		return "gcar/mycar/carDetail";
	}
	
	
}
