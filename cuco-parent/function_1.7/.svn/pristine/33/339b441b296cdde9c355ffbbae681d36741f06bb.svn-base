package com.hy.gcar.web.wechat.store;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;

@Controller
@RequestMapping("/wechat/store")
public class StoreController {

	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 测试使用,查看用车时间段，每一个时段的车辆库存数
	 * @return
	 */
	@RequestMapping(value="/getStoreTest")
	@ResponseBody
	public Object getStoreTest(String carTypeId, String useCarStartTime, String useCarEndTime){
		List<Map<String, Object>> returnList = new ArrayList<>();
		if(StringUtils.isBlank(carTypeId)){
			return new OpenResponse("false", "参数异常：车型ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(useCarStartTime)){
			return new OpenResponse("false", "参数异常：用车开始时间不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(useCarEndTime)){
			return new OpenResponse("false", "参数异常：用车结束时间不能为空！", null, "100001");
		}
		try {
			Long carTypeId_long = Long.parseLong(carTypeId);
			Date useCarStartTime_date = sdf.parse(useCarStartTime);
			Date useCarEndTime_date = sdf.parse(useCarEndTime);
			returnList = this.carOperatePlanService.getStoreByTime(carTypeId_long, useCarStartTime_date, useCarEndTime_date);
		} catch (Exception e) {
			this.logger.error("查询错误--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		return returnList;
	}
}
