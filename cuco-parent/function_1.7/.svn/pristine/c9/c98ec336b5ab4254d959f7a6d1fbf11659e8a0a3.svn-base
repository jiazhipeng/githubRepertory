package com.hy.gcar.web.wechat.carOperate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
//import com.aliyun.api.gateway.demo.util.CommonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hy.common.utils.DateUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.common.utils.StringUtils;
import com.hy.gcar.constant.Basic;
import com.hy.gcar.constant.ButlerTaskType;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.constant.ResultCode;
import com.hy.gcar.entity.BasicCost;
import com.hy.gcar.entity.BasicOperationType;
import com.hy.gcar.entity.BasicRestriction;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarJson;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperateLog;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.basicOperationType.BasicOperationTypeService;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.utils.ResultUtils;

@Controller
@RequestMapping("/wechat/carOperate")
public class CarOperateController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CarOperateService carOperateService;
	
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	
	@Autowired
	private BasicOperationTypeService basicOperationTypeService;
	
	@Autowired
	private ButlerTaskService butlerTaskService;
	
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private BasicThresholdService basicThresholdService;
    
    @Autowired
    private BasicRestrictionService basicRestrictionService;
    
    @Autowired
    private PowerUsedService powerUsedService;
    
	@Autowired
	private CarOperateLogService carOperateLogService;
	
	@Autowired
	private WechatMessageService wechatMessageService;
	
	@Autowired
	private MemberItemShareService memberItemShareService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MemberItemService memberItemService;
	
	@Autowired
	private ItemCartypeService itemCarTypeService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private CarTypeService carTypeService;
    
	@RequestMapping("/toCarList")
	public String toCarList(Long loginId,ModelMap map){
		map.put("loginId", loginId);
		return "gcar/car/carList";
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public String find(ModelMap map,int type,String dateStr){
		String json = "";
		// 替换前段使用的/日期 为- 供后台可解析
		dateStr = dateStr.replace('/', '-');
		//替换type类型从4 到6维修中的查询
		if(type ==  CarOperateEnum.status.MEMBERUSEING.getValue()){
			type =  CarOperateEnum.status.REPAIRSING.getValue();
		}
		List<CarJson> carJsonList = null;
		//根据不同状态查询  对应的 车辆运营 数量
		if(type == CarOperateEnum.status.STAY.getValue()){
			
			carJsonList = makeStayCar(dateStr, type, map);
			
		}else if(type ==  CarOperateEnum.status.MEMBERUSE.getValue()){
			
			carJsonList = makeMemberuseCar(dateStr, type, map);
			
		}else if(type ==  CarOperateEnum.status.PLATFORMUSE.getValue()){
			
			carJsonList = makePlatformUseCar(dateStr, type, map);
			
		}else if(type ==  CarOperateEnum.status.REPAIRSING.getValue()){
			
			carJsonList = makeRepairsingCar(dateStr, type, map);
			
		}
		json = JsonUtil.extractJson(carJsonList);
		return json;
	}
	
	/**
	 *  构造通用  运营 记录 algorithms  1 + 2-
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-19 上午11:01:48   
	* @return String
	 */
	private List<CarJson> makeAddOrSubtract(Calendar maxDate,int algorithms ,String dateStr,ModelMap map,int type,List<CarJson> carJsonList){
		try {
			Calendar miniDate = getMiniDate(dateStr,true);
			//查询当月之前的所有待执行和执行中的运营计划
			List<CarOperatePlan> list = carOperatePlanService.selectListByStatus(maxDate.getTime(),miniDate.getTime(),type);
			//查询已经预定的所有车辆
			for(CarOperatePlan car : list){
				String begin = car.getBegin();
				String end = car.getEnd();
				Integer beginInt = Integer.parseInt(begin.substring(8));
				Integer endInt = Integer.parseInt(end.substring(8));
				String top = dateStr.replace('-', '/').substring(0, 8);
				String day = beginInt.toString();
				//如果预约 时间超过一月，则设置到31号都为 预约或者运营中
				if(car.getEnded().after(maxDate.getTime())){
					endInt = maxDate.get(Calendar.DATE);
				}
				//如果预约 时间早于本月最小时间，则设置到1号都为 预约或者运营中
				if(car.getStarted().before(miniDate.getTime())){
					day = "1";
				}
				for(int i= Integer.decode(day); i <= endInt; i++){
					day = "";
					if(i<10){
						day +="0"+i;
					}else{
						day = i+"";
					}
					int oldCount = (int) map.get(top+day);
					CarJson oldCar = new CarJson();
					oldCar.setDate(top+day);
					oldCar.setCount(oldCount);
					carJsonList.remove(oldCar);
					if(algorithms  == CarOperatePlanEnum.algorithms.add.getValue()){
						oldCar.setCount(oldCount+1);
						carJsonList.add(oldCar);
						map.put(top+day, oldCount+1);
					}else if(algorithms  == CarOperatePlanEnum.algorithms.subtract.getValue()){
						oldCar.setCount(oldCount-1);
						carJsonList.add(oldCar);
						map.put(top+day, oldCount-1);
					}
				}
			}
		}  catch (ParseException e) {
			logger.info("CarOperateController--find--makeAddOrSubtract的方法异常--日期转换"+e.getMessage());
		}
		return carJsonList;
	}
	
	/**
	 * 构造待分配车辆 json
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-19 上午10:52:56   
	* @return String
	 */
	private List<CarJson> makeStayCar(String dateStr,int type,ModelMap map){
		List<CarJson> carJsonList = null;
		try {
			Calendar maxDate = getMaxDate(dateStr,true);
			CarOperate carOperate = new CarOperate();
			carOperate.setStatus(type);
			//查询待分配的所有车辆
			int count = carOperateService.getCountByStatus(carOperate);
			List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			carJsonList = new ArrayList<CarJson>();
			for(Date date : dates){
				CarJson car = new CarJson();
				String allDate = sdf.format(date);
				car.setDate(allDate);
				car.setCount(count);
				carJsonList.add(car);
				map.put(allDate, count);
			}
			carJsonList = makeAddOrSubtract(maxDate, CarOperatePlanEnum.algorithms.subtract.getValue(), dateStr, map,type,carJsonList);
		} catch (ParseException e) {
			logger.info("CarOperateController--find--makeStayCar的方法异常--日期转换"+e.getMessage());
		}
		return carJsonList;
	}
	
	/**
	 * 构造车辆  会员预约的状态
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-19 上午11:07:29   
	* @return String
	 */
	private List<CarJson> makeMemberuseCar(String dateStr,int type,ModelMap map){
		List<CarJson> carJsonList = null;
		try {
			Calendar maxDate = getMaxDate(dateStr,true);
			List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			carJsonList = new ArrayList<CarJson>();
			for(Date date : dates){
				CarJson car = new CarJson();
				String allDate = sdf.format(date);
				car.setDate(allDate);
				car.setCount(0);
				carJsonList.add(car);
				map.put(allDate, 0);
			}
			carJsonList = makeAddOrSubtract(maxDate, CarOperatePlanEnum.algorithms.add.getValue(), dateStr, map,type,carJsonList);
		} catch (ParseException e) {
			logger.info("CarOperateController--find--makeMemberuseCar的方法异常--日期转换"+e.getMessage());
		}
		return carJsonList;
	}
	
	/**
	 * 构造车辆  平台预约的状态
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-19 上午11:07:29   
	* @return String
	 */
	private List<CarJson> makePlatformUseCar(String dateStr,int type,ModelMap map){
		List<CarJson> carJsonList = null;
		try {
			Calendar maxDate = getMaxDate(dateStr,true);
			List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			carJsonList = new ArrayList<CarJson>();
			for(Date date : dates){
				CarJson car = new CarJson();
				String allDate = sdf.format(date);
				car.setDate(allDate);
				car.setCount(0);
				carJsonList.add(car);
				map.put(allDate, 0);
			}
			carJsonList = makeAddOrSubtract(maxDate, CarOperatePlanEnum.algorithms.add.getValue(), dateStr, map,type,carJsonList);
		} catch (ParseException e) {
			logger.info("CarOperateController--find--makePlatformUseCar的方法异常--日期转换"+e.getMessage());
		}
		return carJsonList;
	}
	
	/**
	 * 构造车辆  维修中的状态
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-19 上午11:07:29   
	* @return String
	 */
	private List<CarJson> makeRepairsingCar(String dateStr,int type,ModelMap map){
		List<CarJson> carJsonList = null;
		try {
			Calendar maxDate = getMaxDate(dateStr,true);
			CarOperate carOperate = new CarOperate();
			carOperate.setStatus(type);
			
			//查询维修中的所有车辆
			List<CarOperate> carOperateList = carOperateService.selectByCondition(carOperate);
			int count = 0;
			int day = 1;
			if(carOperateList != null && carOperateList.size() > 0){
				count = carOperateList.size();
				for(CarOperate car : carOperateList){
					if(car.getFailureTime() != null){
						Date badTime = car.getFailureTime();
						int badDay = badTime.getDate();
						if(maxDate.getTime().getMonth() == badTime.getMonth()){
							if(badDay >= 1){
								day = badDay;
							}
						}
					}
				}
			}
			
			List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),day);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			carJsonList = new ArrayList<CarJson>();
			for(Date date : dates){
				CarJson car = new CarJson();
				String allDate = sdf.format(date);
				car.setDate(allDate);
				car.setCount(count);
				carJsonList.add(car);
				map.put(allDate, count);
			}
		} catch (ParseException e) {
			logger.info("CarOperateController--find--makeRepairsingCar的方法异常--日期转换"+e.getMessage());
		}
		return carJsonList;
	} 
	
	@RequestMapping("/toCar")
	public String toCar(String carPlateNum,ModelMap map,Long loginId){
		map.put("carPlateNum", carPlateNum);
		map.put("loginId", loginId);
		CarOperate carOperate = new CarOperate();
		carOperate.setCarPlateNum(carPlateNum);
		List<CarOperate> carOperateList = carOperateService.selectByCondition(carOperate);
		if(carOperateList != null && carOperateList.size() > 0){
			carOperate = carOperateList.get(0);
			map.put("carOperateId", carOperate.getId());
		}
		return "gcar/car/car";
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public String get(String carPlateNum,String dateStr){
			List<CarJson> carJsonList = new ArrayList<CarJson>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			// 替换前段使用的/日期 为- 供后台可解析
			dateStr = dateStr.replace('/', '-');
			Calendar maxDate = getMaxDate(dateStr,true);
			Calendar miniDate = getMiniDate(dateStr, true);
			//根据车牌号查询运营中未执行、执行中的 预定计划
			List<CarOperatePlan> list = carOperatePlanService.selectListByPlateNum(carPlateNum, maxDate.getTime(),miniDate.getTime());
			SimpleDateFormat df = new SimpleDateFormat("dd");
			//故障
			CarOperate carOperate = new CarOperate();
			carOperate.setStatus(CarOperateEnum.status.REPAIRSING.getValue());
			carOperate.setCarPlateNum(carPlateNum);
			Integer repairCount = carOperateService.getCountByStatus(carOperate);
			String day = df.format(new Date());
			if(repairCount > 0){
				if(new Date().before(miniDate.getTime())){
					day = "1";
				}
				List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),Integer.parseInt(day));
				carJsonList = new ArrayList<CarJson>();
				for(Date date : dates){
					CarJson car = new CarJson();
					String allDate = sdf.format(date);
					car.setDate(allDate);
					car.setCount(CarOperatePlanEnum.wapType.bad.getValue());
					carJsonList.add(car);
				}
				return JsonUtil.extractJson(carJsonList);
			}
			//签约
			carOperate = new CarOperate();
			carOperate.setStatus(CarOperateEnum.status.APPOINTMENT.getValue());
			carOperate.setCarPlateNum(carPlateNum);
			Integer appointmentCount = carOperateService.getCountByStatus(carOperate);
			day = df.format(new Date());
			if(appointmentCount > 0){
				if(new Date().before(miniDate.getTime())){
					day = "1";
				}
				List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),Integer.parseInt(day));
				carJsonList = new ArrayList<CarJson>();
				for(Date date : dates){
					CarJson car = new CarJson();
					String allDate = sdf.format(date);
					car.setDate(allDate);
					car.setCount(CarOperatePlanEnum.wapType.APPOINTMENT.getValue());
					carJsonList.add(car);
				}
				return JsonUtil.extractJson(carJsonList);
			}
			
			CarOperate outCarOperate = new CarOperate();
			outCarOperate.setStatus(CarOperateEnum.status.OUTOFCONTACT.getValue());
			outCarOperate.setCarPlateNum(carPlateNum);
			Integer outOfContact = carOperateService.getCountByStatus(outCarOperate);
			String nowDay = df.format(new Date());
			if(outOfContact > 0){
				if(new Date().before(miniDate.getTime())){
					nowDay = "1";
				}
				List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime(),Integer.parseInt(nowDay));
				carJsonList = new ArrayList<CarJson>();
				for(Date date : dates){
					CarJson car = new CarJson();
					String allDate = sdf.format(date);
					car.setDate(allDate);
					car.setCount(CarOperatePlanEnum.wapType.OutOfContact.getValue());
					carJsonList.add(car);
				}
				return JsonUtil.extractJson(carJsonList);
			}
			Date nowDate = new Date();
			Integer date = nowDate.getDate();
			for(CarOperatePlan carOperatePlan : list){
				String begin = carOperatePlan.getBegin();
				String end = carOperatePlan.getEnd();
				Integer beginInt = Integer.parseInt(begin.substring(8));
				Integer endInt = Integer.parseInt(end.substring(8));
				String top = dateStr.replace('-', '/').substring(0, 8);
				day = beginInt.toString();
				//如果预约 时间超过一月，则设置到31号都为预约
				if(carOperatePlan.getEnded().after(maxDate.getTime())){
					endInt = maxDate.get(Calendar.DATE);
				}
				//如果预约 时间早于本月最小时间，则设置到1号都为预约
				if(carOperatePlan.getStarted().before(miniDate.getTime())){
					day = "1";
				}
				if(carOperatePlan.getStarted().getMonth() == nowDate.getMonth()){
					if(date > Integer.decode(day)){
						day = date.toString();
					}
				}
				
				
				for(int i = Integer.decode(day); i <= endInt; i++){
					day = "";
					if(i<10){
						day +="0"+i;
					}else{
						day = i+"";
					}
					CarJson carJson = new CarJson();
					carJson.setDate(top+day);
					carJson.setCount(CarOperatePlanEnum.wapType.reserved.getValue());
					if(carOperatePlan.getStatus() == CarOperatePlanEnum.status.USEING.getValue() &&
							i == nowDate.getDate()){
						carJson.setCount(CarOperatePlanEnum.wapType.use.getValue());
					}
					if(carOperatePlan.getStatus() == CarOperatePlanEnum.status.STAY.getValue()){
						carJson.setCount(CarOperatePlanEnum.wapType.reserved.getValue());
					}
					
					/*Date dayDate = sdf.parse(top+day);
					if(new Date().after(dayDate)){
						carJson.setCount(CarOperatePlanEnum.wapType.use.getValue());
					}
					if(dayDate.getDate() - new Date().getDate() >= 1){
						carJson.setCount(CarOperatePlanEnum.wapType.reserved.getValue());
					}*/
					carJsonList.add(carJson);
				}
			}
			CarOperate car = new CarOperate();
			car.setCarPlateNum(carPlateNum);
			List<CarOperate> carList = carOperateService.selectByCondition(car);
			for(CarOperate cars : carList){
				int status = cars.getStatus();
				if(status == CarOperateEnum.status.READINESSING.getValue()){
					int i = cars.getReadyTime().getDate();
					String days = "";
					if(i<10){
						days +="0"+i;
					}else{
						days = i+"";
					}
					CarJson carJson = new CarJson();
					String top = dateStr.replace('-', '/').substring(0, 8);
					carJson.setDate(top+days);
					carJson.setCount(CarOperatePlanEnum.wapType.READINESSING.getValue());
					carJsonList.add(carJson);
				}
			}
		} catch (ParseException e) {
			logger.info("CarOperateController--get的方法异常--日期转换"+e.getMessage());
		}
         return JsonUtil.extractJson(carJsonList);
    }
	
	@RequestMapping("/getByDay")
	public String getByDay(String dateStr,int type,ModelMap map,Long loginId){
		try {
			// 替换前段使用的/日期 为- 供后台可解析
			dateStr = dateStr.replace('/', '-');
			if(type ==  CarOperateEnum.status.MEMBERUSEING.getValue()){
				type =  CarOperateEnum.status.REPAIRSING.getValue();
			}
			Calendar calendar = getMaxDate(dateStr,false);
			Calendar miniCalendar = getMiniDate(dateStr, false);
			List<CarOperate> list = carOperateService.selectListByDay(calendar.getTime(),miniCalendar.getTime(),type);
			System.out.println("after"+list.size());
			List<CarOperate> removeList = new ArrayList<>();
			if(type == CarOperateEnum.status.STAY.getValue()){
				for(CarOperate car : list){
					if(car.getOperatePlanStatus() != null){
						removeList.add(car);
					}
				}
			}
			list.removeAll(removeList);
			map.put("list", list);
			map.put("dateStr", dateStr);
			map.put("type", type);
			map.put("size", list.size());
			map.put("loginId", loginId);
			System.out.println("back"+list.size());
			System.out.println("remove"+removeList.size());
		} catch (ParseException e) {
			logger.info("CarOperateController--getByDay的方法异常--日期转换"+e.getMessage());
		}
		return "gcar/carOperate/carDayList";
	}
	
	@RequestMapping("/getCarById")
	public String getCarById(long id,String dateStr,ModelMap map,Long loginId){
		// 替换前段使用的/日期 为- 供后台可解析
		dateStr = dateStr.replace('/', '-');
		map.put("dateStr", dateStr);
		map.put("fix", false);
		try {
			map.put("loginId", loginId);
			CarOperate carOperate = carOperateService.findById(id);
			if(carOperate != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(dateStr);
				//车辆整备开始时间
				map.put("car", carOperate);
				if(carOperate.getStatus() == CarOperateEnum.status.READINESSING.getValue()){
					if(date.getDate() == new Date().getDate()){
						CarOperatePlan newCarOperatePlan = carOperatePlanService.selectByOperateNum(carOperate.getOperateNum());
					    map.put("carOperatePlan", newCarOperatePlan);
					    map.put("type", carOperate.getStatus());
					    return "gcar/carOperate/carDetails";
					}
				}
				
				//会员预约详情    ||   管家预约详情 ||  故障详情 --维修中
				map.put("type", 1);
				map.put("flag", false);
				
				Calendar calendar = Calendar.getInstance();
				Calendar miniCalendar = Calendar.getInstance();
				if(carOperate.getStatus() == CarOperateEnum.status.REPAIRSING.getValue()){
					CarOperatePlan carOperatePlan = carOperatePlanService.selectByOperateIdAndStartedEnded(carOperate.getId(), date);
				    map.put("type", 6);
				    map.put("carOperatePlan", carOperatePlan);
				    try {
						Member member = memberService.findMemberByID(loginId);
						if(member != null){
							if(carOperatePlan != null){
								if(carOperatePlan.getButlerId().equals(member.getSysuserId())){
									map.put("flag", true);
								}
							}
							if(member.getIsButler() == 1){
								map.put("fix", true);
							}
							if(member.getIsCustomer() == 1){
								map.put("fix", true);
							}
						}
					} catch (Exception e) {
						logger.info("查询管家信息错误"+e.getMessage());
					}
				    return "gcar/carOperate/carDetails";
				}
				//已失联
				if(carOperate.getStatus() == CarOperateEnum.status.OUTOFCONTACT.getValue()){
					CarOperatePlan carOperatePlan = carOperatePlanService.selectoutofcontactByOperateIdAndStartedEnded(carOperate.getId());
				    map.put("type", 8);
				    map.put("carOperatePlan", carOperatePlan);
				    try {
						Member member = memberService.findMemberByID(loginId);
						if(member != null){
							if(member.getIsButler() == 1){
								map.put("flag", true);
							}
							if(member.getIsCustomer() == 1){
								map.put("flag", true);
							}
						}
					} catch (Exception e) {
						logger.info("查询管家信息错误"+e.getMessage());
					}
				    return "gcar/carOperate/carDetails";
				}
				
				//预约
				if(carOperate.getStatus() == CarOperateEnum.status.APPOINTMENT.getValue()){
					CarOperatePlan carOperatePlan = carOperatePlanService.selectAppointmentByOperateId(carOperate.getId());
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					carOperatePlan.setBegin(sdf.format(carOperatePlan.getStarted()));
					map.put("type", 9);
				    map.put("carOperatePlan", carOperatePlan);
				    try {
						Member member = memberService.findMemberByID(loginId);
						if(member != null){
							if(carOperatePlan != null){
								if(carOperatePlan.getButlerId().equals(member.getSysuserId())){
									map.put("flag", true);
								}
							}
						}
					} catch (Exception e) {
						logger.info("查询管家信息错误"+e.getMessage());
					}
				    return "gcar/carOperate/carDetails";
				}
				
				calendar.setTime(date);
				miniCalendar.setTime(date);
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
				miniCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
				CarOperatePlan carOperatePlan = carOperatePlanService.selectByCarPlateNumAndStartedEnded(carOperate.getCarPlateNum(), calendar.getTime(),miniCalendar.getTime());
			    if(carOperatePlan != null){
			    	map.put("carOperatePlan", carOperatePlan);
			    }
			    
				//预约记录
				calendar = getMiniDate(dateStr,false);
				List<CarOperatePlan> newList = new ArrayList<CarOperatePlan>();
				List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNum(carOperate.getOperateNum(), calendar.getTime());
				for(int index = 0;index<list.size();index++){
					CarOperatePlan plan = list.get(index);
					//Date oldStarted = plan.getStarted();
					int endDate = plan.getEnded().getDate();
					if(plan.getEnded().getMonth() > new Date().getMonth()){
						Calendar cal = Calendar.getInstance();
						endDate = cal.getMaximum(Calendar.DATE);
					}
					for(int i =plan.getStarted().getDate();i<=endDate;i++ ){
						//@Description:TODO  研究这块时间怎么改  时间的方法
						Date startedTime = plan.getStarted();
						CarOperatePlan newCarPlan = new CarOperatePlan();
						newCarPlan.setId(plan.getId());
						newCarPlan.setMemberId(plan.getMemberId());
						newCarPlan.setOperateTo(plan.getOperateTo());
						newCarPlan.setMemberName(plan.getMemberName());
						newCarPlan.setButlerName(plan.getButlerName());
						//BeanUtils.copyProperties(plan, newCarPlan);
						Date newDate = null;
						if(i == plan.getStarted().getDate()){
							newDate = new Date(startedTime.getYear(), startedTime.getMonth(), i,startedTime.getHours(), startedTime.getMinutes());
						}
						if(endDate == i){
							newDate = plan.getEnded();
						}
						newCarPlan.setStarted(newDate);
						newList.add(newCarPlan);
					}
				}
				map.put("list", newList);
			    
			    
			    try {
					Member member = memberService.findMemberByID(loginId);
					if(member != null){
						if(carOperatePlan != null){
							if(carOperatePlan.getButlerId().equals(member.getSysuserId())){
								map.put("flag", true);
							}
						}
						if(member.getIsButler() == 1){
							map.put("fix", true);
						}
						if(member.getIsCustomer() == 1){
							map.put("fix", true);
						}
					}
				} catch (Exception e) {
					logger.info("查询管家信息错误"+e.getMessage());
				}
			    if(carOperatePlan !=null && carOperatePlan.getStatus() == CarOperatePlanEnum.status.USEING.getValue()){
			    	map.put("type", 4);
			    }
			    if(carOperatePlan != null){
				    Date nowDate = new Date();
				    if(date.getDate() - nowDate.getDate() >= 1){
				    	carOperatePlan.setStatus(CarOperatePlanEnum.status.STAY.getValue());
				    }
				    
				    if(carOperatePlan !=null && carOperatePlan.getStatus() == CarOperatePlanEnum.status.STAY.getValue()){
				    	if(carOperatePlan.getMemberId() != 0){
				    		map.put("type", 2);
				    	}else{
				    		map.put("type", 3);
				    	}
				    	if(carOperatePlan != null){
					    	ButlerTask butlerTask = new ButlerTask();
						    butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
						    butlerTask.setMemberId(carOperatePlan.getMemberId().toString());
						    butlerTask.setPlanTime(carOperatePlan.getStarted());
						    butlerTask.setCarOperateId(id);
						    List<ButlerTask> taskList = butlerTaskService.selectByCondition(butlerTask);
						    if(taskList != null && taskList.size() > 0){
						    	ButlerTask task = taskList.get(0);
						    	map.put("butlerTaskId", task.getId());
						    }
						}
				    }
			    }
			}
		} catch (ParseException e) {
			logger.info("CarOperateController--getCarById的方法异常 --日期转换"+e.getMessage());
		}
		return "gcar/carOperate/carDetails";
	}
	/**
	 * 
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-10-11 下午4:42:42   
	* @return String
	 */
	@RequestMapping("/getById")
	public String getById(long id, int type,String dateStr,String started,ModelMap map,Long loginId){
		try {
			CarOperate carOperate = carOperateService.findById(id);
			map.put("dateStr", dateStr);
			map.put("started", started);
			map.put("loginId", loginId);
			map.put("fix", false);
			if(carOperate != null){
				map.put("car", carOperate);
				map.put("type", type);
				
				//预约记录
				if(type == CarOperateEnum.status.STAY.getValue()){
					//车辆整备开始时间
					if(carOperate.getStatus() == CarOperateEnum.status.READINESSING.getValue()){
						CarOperatePlan carOperatePlan = carOperatePlanService.selectByOperateNum(carOperate.getOperateNum());
					    map.put("carOperatePlan", carOperatePlan);
					    map.put("type", carOperate.getStatus());
					    return "gcar/carOperate/carDetails";
					}
					Calendar calendar = getMiniDate(dateStr,true);
					List<CarOperatePlan> newList = new ArrayList<CarOperatePlan>();
					List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNum(carOperate.getOperateNum(), calendar.getTime());
					for(int index = 0;index<list.size();index++){
						CarOperatePlan plan = list.get(index);
						//Date oldStarted = plan.getStarted();
						int endDate = plan.getEnded().getDate();
						if(plan.getEnded().getMonth() > new Date().getMonth()){
							Calendar cal = Calendar.getInstance();
							endDate = cal.getMaximum(Calendar.DATE);
						}
						for(int i =plan.getStarted().getDate();i<=endDate;i++ ){
							//@Description:TODO  研究这块时间怎么改  时间的方法
							Date startedTime = plan.getStarted();
							CarOperatePlan newCarPlan = new CarOperatePlan();
							newCarPlan.setId(plan.getId());
							newCarPlan.setMemberId(plan.getMemberId());
							newCarPlan.setOperateTo(plan.getOperateTo());
							newCarPlan.setMemberName(plan.getMemberName());
							newCarPlan.setButlerName(plan.getButlerName());
							//BeanUtils.copyProperties(plan, newCarPlan);
							Date newDate = null;
							if(i == plan.getStarted().getDate()){
								newDate = new Date(startedTime.getYear(), startedTime.getMonth(), i,startedTime.getHours(), startedTime.getMinutes());
							}
							if(endDate == i){
								newDate = plan.getEnded();
							}
							newCarPlan.setStarted(newDate);
							newList.add(newCarPlan);
						}
					}
					map.put("list", newList);
				}
				//车辆整备开始时间
				if(type == CarOperateEnum.status.READINESSING.getValue()){
					CarOperatePlan carOperatePlan = carOperatePlanService.selectByOperateNum(carOperate.getOperateNum());
				    map.put("carOperatePlan", carOperatePlan);
				}
				//会员预约详情    ||   管家预约详情 ||  故障详情 --维修中
				if(type == CarOperateEnum.status.MEMBERUSE.getValue() ||
						type == CarOperateEnum.status.PLATFORMUSE.getValue() ||
						type == CarOperateEnum.status.REPAIRSING.getValue()){
					Date date = new Date();
					if(!"0".equals(started)){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						date = sdf.parse(started);
					}
					CarOperatePlan carOperatePlan = carOperatePlanService.selectByOperateNumAndStarted(carOperate.getOperateNum(), date);
					 if(carOperate.getStatus() == CarOperateEnum.status.REPAIRSING.getValue()){
						 carOperatePlan = carOperatePlanService.selectByOperateIdAndStartedEnded(carOperate.getId(), date);
					    	//map.put("type", 6);
					    	//map.put("carOperatePlan", carOperatePlan);
					    }
					map.put("carOperatePlan", carOperatePlan);
				    if(carOperatePlan != null){
				    	ButlerTask butlerTask = new ButlerTask();
					    butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
					    butlerTask.setMemberId(carOperatePlan.getMemberId().toString());
					    butlerTask.setPlanTime(carOperatePlan.getStarted());
					    butlerTask.setCarOperateId(id);
					    List<ButlerTask> list = butlerTaskService.selectByCondition(butlerTask);
					    if(list != null && list.size() > 0){
					    	ButlerTask task = list.get(0);
					    	map.put("butlerTaskId", task.getId());
					    }
				    }
				    try {
						Member member = memberService.findMemberByID(loginId);
						if(member != null){
							if(carOperatePlan != null){
								if(carOperatePlan.getButlerId().equals(member.getSysuserId())){
									map.put("flag", true);
								}
							}
							if(member.getIsButler() == 1){
								map.put("fix", true);
							}
							if(member.getIsCustomer() == 1){
								map.put("fix", true);
							}
						}
					} catch (Exception e) {
						logger.info("查询管家信息错误"+e.getMessage());
					}
				}
				
			}
		} catch (ParseException e) {
			logger.info("CarOperateController--getById的方法异常 --日期转换"+e.getMessage());
		}
		return "gcar/carOperate/carDetails";
	}
	
	@RequestMapping("/getOperate")
	public String getOperate(ModelMap map,Long loginId,long id,int type,String dateStr,String started){
		CarOperate carOperate = carOperateService.findById(id);
		BasicOperationType basicOperationType = new BasicOperationType();
		basicOperationType.setStatus(Basic.status.use.getValue());
		List<BasicOperationType> list = basicOperationTypeService.selectByCondition(basicOperationType);
		//基础设置—系统阈值设置
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		if(basicThreshold != null){
			map.put("time", basicThreshold.getButlerUsecarAdvance());
		}
		map.put("list", list);
		map.put("loginId", loginId);
		map.put("type", type);
		map.put("dateStr", dateStr);
		map.put("started", started);
		map.put("carOperate", carOperate);
		return "gcar/carOperate/platform";
	}
	
	@RequestMapping("/platformOperation")
	@ResponseBody
	public String platformOperation(String dateStr,String started,String code,Long operateType,String expectedReturn,String beginTime,String endTime,
			String operateTo,String phone,String remark,String operateNum,String carPlateNum,ModelMap map,long operateId,
			Long loginId,int stock,HttpServletRequest request, HttpServletResponse response){
		map.put("loginId",loginId);
		map.put("dateStr",dateStr);
		map.put("started",started);
		if(operateType == null || operateType == 0 || StringUtils.isEmpty(expectedReturn) || StringUtils.isEmpty(operateTo) ||
				StringUtils.isEmpty(phone)){
			map.put("code", ResultCode.errorCode);
			map.put("msg", "请填写信息");
			return JsonUtil.extractJson(map);
		}
	        Member m = null;
			try {
				m = memberService.findMemberByID(loginId);
			} catch (Exception e1) {
				logger.info("管家查询错误"+e1.getMessage()+loginId);
			}
			if(m != null && m.getIsCustomer() == 1){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "客服无权限发起");
			}
		try {
			map.put("code", ResultCode.errorCode);
			map.put("msg", "预约失败");
			//查询后台设置的服务时间
			BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date beginTimeDate = sdf.parse(beginTime);
			Date endTimeDate = sdf.parse(endTime);
			beginTimeDate.setHours(beginTimeDate.getHours() - 2);
			endTimeDate.setHours(endTimeDate.getHours() + 2);
			List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNumAndStarted(endTimeDate, beginTimeDate, operateNum);
			
			//查询后台设置的服务时间
			if(basicThreshold != null){
				Time startTime = basicThreshold.getServiceTimeStart();
				int startHours = startTime.getHours();
				Time noticeEndTime = basicThreshold.getServiceTimeEnd();
				int endHours = noticeEndTime.getHours(); 
				Date nowDate = new Date();
				//查询车辆开始时间 是否提前几小时
				if(sdf.parse(beginTime).getDate() == new Date().getDate()){
					if(sdf.parse(beginTime).getHours() < (nowDate.getHours()+basicThreshold.getButlerUsecarAdvance())){
						map.put("code", ResultCode.errorCode);
						map.put("msg", "预约需提前"+basicThreshold.getButlerUsecarAdvance()+"小时");
						return JsonUtil.extractJson(map);
					}
				}
				if(sdf.parse(beginTime).getHours() < startHours || sdf.parse(endTime).getHours() > endHours){
					map.put("code", ResultCode.errorCode);
					map.put("msg", "选择时间段必须为早"+startTime+"-晚"+noticeEndTime+"之间");
					return JsonUtil.extractJson(map);
				}
				if(sdf.parse(endTime).getHours() < startHours){
					map.put("code", ResultCode.errorCode);
					map.put("msg", "选择时间段必须为早"+startTime+"-晚"+noticeEndTime+"之间");
					return JsonUtil.extractJson(map);
				}
				if(sdf.parse(endTime).getHours() == endHours){
					if(sdf.parse(endTime).getMinutes() > 0){
						map.put("code", ResultCode.errorCode);
						map.put("msg", "选择时间段必须为早"+startTime+"-晚"+noticeEndTime+"之间");
						return JsonUtil.extractJson(map);
					}
				}
				
			}
			
			if(list != null && list.size() > 0){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "选择时间段已被预约，请重新选择时间预约");
			}else{
				CarOperatePlan carOperatePlan = new CarOperatePlan();
				CarOperate car = new CarOperate();
				car.setCarPlateNum(carPlateNum);
				List<CarOperate> carList = carOperateService.selectByCondition(car);
				if(carList != null && carList.size() > 0){
					CarOperate newCar = carList.get(0);
					if(newCar != null){
						carOperatePlan.setCarTypeId(newCar.getCarTypeId());
					}
				}
				carOperatePlan.setOperateNum(operateNum);
				carOperatePlan.setCarPlateNum(carPlateNum);
				carOperatePlan.setIsStock(stock);
				carOperatePlan.setOperateType(Long.valueOf(operateType));
				carOperatePlan.setExpectedReturn(expectedReturn);
				carOperatePlan.setStarted(sdf.parse(beginTime));
				carOperatePlan.setEnded(sdf.parse(endTime));
				carOperatePlan.setOperateTo(operateTo);
				carOperatePlan.setCreated(new Date());
				carOperatePlan.setMemberMobile(phone);
				carOperatePlan.setRemark(remark);
				BigDecimal money = new BigDecimal(expectedReturn).setScale(2, BigDecimal.ROUND_HALF_UP);
				carOperatePlan.setMoney(money);
				carOperatePlan.setMemberId(0L);
				carOperatePlan.setStatus(CarOperatePlanEnum.status.STAY.getValue());
				carOperatePlan.setOperateStatus(CarOperateEnum.status.PLATFORMUSE.getValue());
				carOperatePlan.setOperateId(operateId);
				if(m != null){
					carOperatePlan.setButlerId(m.getSysuserId());
					carOperatePlan.setButlerName(m.getSysuserName());
				}
				Integer id = carOperatePlanService.insertSelective(carOperatePlan);
				if(id != null && id > 0){
					map.put("msg","预约成功");
					map.put("code", ResultCode.successCode);
				}
			}
			
		} catch (Exception e) {
			logger.info("CarOperateController--platformOperation的方法异常"+e.getMessage());
		}
		return JsonUtil.extractJson(map);
		
	}
	
	//type 1.有收益，手动记录收益，2.无收益，删除当天收益记录
	@RequestMapping("/cancelOperate")
	@ResponseBody
	public String cancelOperate(String reason,int type,String operateNum,long carOperateId,long carOperatePlanId,ModelMap map){
		map.put("msg", "取消失败，请重试");
		map.put("code", ResultCode.errorCode);
		//type收益  再做
		Integer id = carOperatePlanService.cacleOperatePlan(reason, type, operateNum, carOperateId, carOperatePlanId);
		if(id != null && id >0){
			map.put("msg", "取消成功");
			map.put("code", ResultCode.successCode);
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/repairFinish")
	@ResponseBody
	public String repairFinish(BigDecimal maintenanceCosts,int handle,Long carOperateId,Long carOperatePlanId,String repairManufacturer,Long memberId,ModelMap map){
		map.put("msg", "修理失败，请重试");
		map.put("code", ResultCode.errorCode);
		try {
			Date nowDate = new Date();
			Integer id = carOperatePlanService.repairFinish(nowDate, maintenanceCosts, handle, carOperateId, carOperatePlanId,repairManufacturer, memberId);
			if(id != null && id > 0){
				map.put("msg", "修理成功");
				map.put("code", ResultCode.successCode);
			}
		} catch (Exception e) {
			logger.info("CarOperateController--repairFinish的方法异常"+e.getMessage());
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/getByType")
	@ResponseBody
	public Map<String,Object> getByType(String newCarPlateNum,String oldCarPlateNum,Long carOperatePlanId,int status,Long loginId,Long powerUsedId,Date started){
		//List<CarOperate> list = carOperateService.selectListBystatus(new Date());
		
		ButlerTask butlerTask= new ButlerTask();
		butlerTask.setPowerUsedId(powerUsedId);
		butlerTask.setPlanTime(started);
		 List<CarOperate> carOperates = carOperateService.selectAvailableVehiclesCar(butlerTask);
		if(carOperates == null){
			carOperates = carOperateService.selectListBystatus(new Date());
		}
		 List<Long> carOperateIds = Lists.newArrayList();
//	      根据当前时间查询运营状态
	        if(CollectionUtils.isNotEmpty(carOperates)){
	        	int readyTime = 0;
	        	BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
	        	if(basicThreshold!=null && basicThreshold.getReadyTime()!=null){
	        		readyTime = basicThreshold.getReadyTime();
	        	}
	        	for(CarOperate carOperate:carOperates){
	        		carOperateIds.add(carOperate.getId());
	        	}
	        	//查询入库时间
	        	List<CarOperatePlan> carOperatePlans = carOperatePlanService.selectCarOperateStateByNow(carOperateIds);
	    		for(CarOperate carOperate:carOperates){
		        	for(CarOperatePlan carOperatePlan:carOperatePlans){
		        			if(carOperatePlan.getOperateId().equals(carOperate.getId())){
		        				carOperate.setOperatePlanStatus(carOperatePlan.getOperateStatus());
		        				this.logger.info(JSON.toJSONString(carOperatePlan));
		        				carOperate.setEndTime(DateUtils.addHours(carOperatePlan.getEnded(),readyTime));
		        				this.logger.info(JSON.toJSONString(carOperate));
		        			}
		        		}
	        	}

	        	//车辆列表加上限号时间
	            BasicRestriction basicRestriction = new BasicRestriction();
	            basicRestriction.setCityId(2);
	            basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd")));
	            List<BasicRestriction>   basicRestrictions = basicRestrictionService.selectByCondition(basicRestriction);
	            Set<Long> carTypeIdSet = Sets.newHashSet();
	            if(CollectionUtils.isNotEmpty(basicRestrictions)){
	                basicRestriction = basicRestrictions.get(0);
	                String restrictions = basicRestriction.getRestrictions();
	                List<String> restrictionList = Arrays.asList(restrictions.split(","));
	                String carPlateNum = null;
	                String last = null;
	                for(CarOperate carOperate:carOperates){
	                    carPlateNum = carOperate.getCarPlateNum();
	                    last = carPlateNum.substring(carPlateNum.length()-1);
	                    if(!isInteger(last)){
	                        last = "0";
	                    }
	                    Integer nowStatus = carOperatePlanService.getCarStatus(carOperate.getId());
	                    carOperate.setStatus(nowStatus);
	                    if(restrictionList.contains(last)){
	                        carOperate.setIsLimitLine(true);
	                    }
	                    carTypeIdSet.add(carOperate.getCarTypeId());
	                }
	            }
	            //根据车型判断车辆是否限号
	            if(CollectionUtils.isNotEmpty(carTypeIdSet)){
	                List<CarType> carTypes = carTypeService.selectCarTypeByIds(new ArrayList<Long>(carTypeIdSet));
	                for(CarType carType:carTypes){
	                    for(CarOperate carOperate:carOperates){
	                        if(carType.getId().longValue() == carOperate.getCarTypeId().longValue()){
	                            if(carType.getIsRestriction().intValue() == 0){
	                                carOperate.setIsLimitLine(false);
	                            }
	                        }
	                    }
	                }
	            }
	        	
	        }
	        //排序-用户权益车型在前面
	        PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
	        List<CarOperate> carOperate_news = null;
	        if(powerUsed != null){
	            long carTypeId = powerUsed.getCarTypeId();
	            carOperate_news = new ArrayList<CarOperate>();
		        for(CarOperate carOperate:carOperates){
		            if(carOperate.getCarTypeId().longValue() == carTypeId){
		                carOperate_news.add(0,carOperate);
		            }else{
		                carOperate_news.add(carOperate);
		            }
		        }
	        }
	    if(carOperate_news == null){
	    	carOperate_news = carOperates;
	    }
	    List<Map<String,Object>> carTypeAndCar = Lists.newArrayList();
        //加载 所有的车型
        List<CarType> carTypes = carTypeService.selectByCondition(new CarType());
        for(CarType carType:carTypes){
        	 Map<String,Object> map = Maps.newHashMap();
        	 map.put("carTypeName",carType.getBrand() + carType.getCarType());
        	 map.put("carTypeId",carType.getId());
            List<CarOperate> lists = Lists.newArrayList();
            for(CarOperate carOperate:carOperate_news){
                if(carOperate.getCarTypeId().longValue() == carType.getId().longValue()){
                    lists.add(carOperate);
                }
            }
            map.put("carOperates",lists);
            carTypeAndCar.add(map);
        }
		return ResultUtils.success(carTypeAndCar);
	}
	
	@RequestMapping("/toFailureChange")
	public String toFailureChange(ModelMap map,String newCarPlateNum,String oldCarPlateNum,Long carOperatePlanId,int status,Long loginId,Long powerUsedId,Date started){
        CarOperate car = carOperateService.findByCarPlanNum(oldCarPlateNum);
		map.put("car", car);
		map.put("loginId",loginId);
		map.put("newCarPlateNum", newCarPlateNum);
		map.put("oldCarPlateNum", oldCarPlateNum);
		map.put("carOperatePlanId", carOperatePlanId);
		map.put("status", status);
		if(powerUsedId == null){
			powerUsedId = 0L;
		}
		map.put("powerUsedId", powerUsedId);
		map.put("started", started);
		return "gcar/carOperate/failureChange";
	}
	
		 /*
		  * 判断是否为整数
		  * @param str 传入的字符串
		  * @return 是整数返回true,否则返回false
		*/
	    public static boolean isInteger(String str) {
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	        return pattern.matcher(str).matches();
	    }
	
	
	@RequestMapping("/replaceCar")
	@ResponseBody
	public String replaceCar(String oldCarPlateNum,String newCarPlateNum,long carOperatePlanId,String remark,int status,Long loginId,ModelMap map){
		map.put("msg", "换车失败，请重试");
		map.put("code", ResultCode.errorCode);
		Integer id = carOperateService.replaceCar(oldCarPlateNum, newCarPlateNum, remark, carOperatePlanId, status,loginId);
		if(id != null && id > 0){
			map.put("msg", "换车成功");
			map.put("code", ResultCode.successCode);
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/toReplace")
	public String toReplace(Long carId,String carPlateNum,Long loginId,ModelMap map){
		map.put("carId", carId);
		map.put("loginId", loginId);
		map.put("carPlateNum", carPlateNum);
		map.put("code", ResultCode.successCode);
		map.put("msg", "");
		CarOperate car = carOperateService.findById(carId);
		if(car != null){
			Date useDate = new Date();
			Date emdUseDate = new Date();
			emdUseDate.setYear(useDate.getYear() + 1);
			List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNumAndStartedByRepair(emdUseDate,useDate, car.getOperateNum());
			if(list != null && list.size() > 0){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "该车辆正在被使用，无法发起维修");
			}
		}
		return "gcar/carOperate/repair";
	}
	
	@RequestMapping("/replace")
	@ResponseBody
	public String replace(Long carId,String remark,Long loginId,ModelMap map){
		map.put("msg", "发起维修失败，请重试");
		map.put("code", ResultCode.errorCode);
		CarOperate car = carOperateService.findById(carId);
		if(car != null){
			Date useDate = new Date();
			Date emdUseDate = new Date();
			emdUseDate.setYear(useDate.getYear() + 1);
			List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNumAndStartedByRepair(emdUseDate,useDate, car.getOperateNum());
			if(list != null && list.size() > 0){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "该车辆正在被使用，无法发起维修");
				return JsonUtil.extractJson(map);
			}
		}
		Integer id = carOperateService.repairsing(carId);
		if(id != null && id > 0){
			map.put("msg", "发起维修成功");
			map.put("code", ResultCode.successCode);
			if(car != null){
				
				//清空运营计划 表的车
				Date now = new Date();
				CarOperatePlan carOperate = new CarOperatePlan();
				carOperate.setCarPlateNum(car.getCarPlateNum());
				carOperate.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
				carOperate.setStarted(now);
				//会员用车清空车辆
				carOperatePlanService.updateStatusByOperateIdAndStarted(carOperate);
				//tdCarOperatePlanMapper.updateStatusByOperateIdAndStarted(carOperateP);
				//平台运营直接变成取消
				carOperatePlanService.updateStatusByOperateIdAndStartedForPlatform(carOperate);
				
				//查询此车辆拥有的任务,取消任务的车辆
				ButlerTask task = new ButlerTask();
				task.setCarOperateId(car.getId());
				task.setCompleteTime(now);
				Member member = null;
				try {
					member = memberService.findMemberByID(loginId);
				} catch (Exception e1) {
					logger.error("发起车辆维修replace--carId"+carId+""+e1.getMessage());
				}
				if(member != null){
					task.setOperaterId(member.getSysuserId());	
				}
				butlerTaskService.updateButlerTaskCarOperate(task,3);
				//发起维修发送消息
				checkSendMessageByRepair(car);
				
				//发送消息
				CarOperatePlan plan = new CarOperatePlan();
				plan.setCarPlateNum(car.getCarPlateNum());
				plan.setPowerUsedId(null);
				now.setSeconds(now.getSeconds() - 1);
				plan.setStarted(now);
				List<CarOperatePlan> planList = carOperatePlanService.selectCarPlanNumAndpowerUsedId(plan);
				for(CarOperatePlan plans : planList){
					//插入一条新的运营中的日志
					CarOperateLog carOperateLog = new CarOperateLog();
					if(member != null){
						carOperateLog.setOperateName(member.getSysuserName());
						carOperateLog.setOperateId(member.getSysuserId());
					}
					carOperateLog.setRemark("平台运营受到发起维修影响自动取消");
					carOperateLog.setType(CarOperateEnum.status.REPAIRSING.getValue());
					carOperateLog.setOperateNum(plans.getOperateNum());
					carOperateLog.setCreated(new Date());
					try {
						carOperateLogService.insertSelective(carOperateLog);
					} catch (Exception e) {
						logger.error("发起车辆维修replace--carId"+carId);
					}
					String message = "您安排的平台运营车辆由于车辆维修已被取消，请尽快处理";
					wechatMessageService.sendMessageByPlatformOperation(plans,message);
				}
				//插入一条新的运营中的日志
				CarOperateLog carOperateLog = new CarOperateLog();
				if(member != null){
					carOperateLog.setOperateName(member.getSysuserName());
					carOperateLog.setOperateId(member.getSysuserId());
				}
				carOperateLog.setRemark("发起维修,维修原因:"+remark);
				carOperateLog.setType(CarOperateEnum.status.REPAIRSING.getValue());
				carOperateLog.setOperateNum(car.getOperateNum());
				carOperateLog.setCreated(new Date());
				try {
					carOperateLogService.insertSelective(carOperateLog);
				} catch (Exception e) {
					logger.error("发起车辆维修replace--carId"+carId);
				}
			}
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/readySuccess")
	@ResponseBody
	public String readySuccess(long carOperateId,ModelMap map,Long loginId){
		map.put("msg", "整备失败，请重试");
		map.put("code", ResultCode.errorCode);
		Integer id = carOperateService.stay(carOperateId,loginId,1);
		if(id != null && id > 0){
			map.put("msg", "整备成功");
			map.put("code", ResultCode.successCode);
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/searchCar")
	public String searchCar(Long loginId,ModelMap map){
		map.put("loginId",loginId);
		return "gcar/car/searchCar";
	}
	
	@RequestMapping("/searchCarPlateNum")
	@ResponseBody
	public String searchCarPlateNum(String carPlateNum){
		List<CarOperate> list = carOperateService.searchCarPlateNum("%"+carPlateNum+"%");
		return JsonUtil.extractJson(list);
	}
	
	@RequestMapping("/comeBack")
	@ResponseBody
	public String comeBack(Long carOperateId,Long loginId,ModelMap map){
		Integer result = carOperateService.stay(carOperateId,loginId,2);
		map.put("code", ResultCode.errorCode);
		map.put("msg", "找回失败");
		if(result > 0){
			map.put("msg", "找回成功");
			map.put("code", ResultCode.successCode);
		}
		return JsonUtil.extractJson(map);
	}
	
	
	@RequestMapping("/toAppointment")
	public String toAppointment(Long carId,int type,String dateStr,String started,String carPlateNum,Long loginId,ModelMap map){
		map.put("carId", carId);
		map.put("loginId", loginId);
		map.put("carPlateNum", carPlateNum);
		map.put("type", type);
		map.put("dateStr", dateStr);
		map.put("started", started);
		map.put("code", ResultCode.successCode);
		map.put("msg", "");
		CarOperate car = carOperateService.findById(carId);
		if(car != null){
			Date useDate = new Date();
			Date emdUseDate = new Date();
			emdUseDate.setYear(useDate.getYear() + 1);
			List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNumAndStartedByRepair(emdUseDate,useDate, car.getOperateNum());
			if(list != null && list.size() > 0){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "该车辆正在被使用，无法发起签约预约");
			}
		}
		return "gcar/carOperate/toAppointment";
	}
	
	@RequestMapping("/toStartAppointment")
	public String toStartAppointment(Long carId,String sendCarTime,String address,int type,String dateStr,String started,String carPlateNum,Long loginId,ModelMap map){
		map.put("carId", carId);
		map.put("loginId", loginId);
		map.put("carPlateNum", carPlateNum);
		map.put("type", type);
		map.put("dateStr", dateStr);
		map.put("started", started);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date sendCarDate = null;
		try {
			sendCarDate = sdf.parse(sendCarTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("sendCarDate", sendCarDate);
		map.put("address", address);
		map.put("code", ResultCode.successCode);
		map.put("msg", "");
		return "gcar/carOperate/appointment";
	}
	
	@RequestMapping("/appointment")
	@ResponseBody
	public String appointment(Long carId,Long loginId,String sendCarTime,String address,ModelMap map){
		map.put("code", ResultCode.errorCode);
		map.put("msg", "签约失败");
		//查询后台设置的服务时间
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date sendCarDate;
		try {
			sendCarDate = sdf.parse(sendCarTime);
		} catch (ParseException e2) {
			logger.info("签约失败"+e2.getMessage());
			return JsonUtil.extractJson(map);
		}
		CarOperate car = carOperateService.findById(carId);
		if(car != null){
			Date useDate = new Date();
			Date emdUseDate = new Date();
			emdUseDate.setYear(useDate.getYear() + 1);
			List<CarOperatePlan> list = carOperatePlanService.selectListByOperateNumAndStartedByRepair(emdUseDate,useDate, car.getOperateNum());
			if(list != null && list.size() > 0){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "该车辆正在被使用，无法发起签约预约");
			}
		}
		//查询后台设置的服务时间
		if(basicThreshold != null){
			Time startTime = basicThreshold.getServiceTimeStart();
			int startHours = startTime.getHours();
			Time noticeEndTime = basicThreshold.getServiceTimeEnd();
			int endHours = noticeEndTime.getHours(); 
			if(sendCarDate.getHours() < startHours || sendCarDate.getHours() > endHours){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "预计送车时间必须为早"+startTime+"-晚"+noticeEndTime+"之间");
				return JsonUtil.extractJson(map);
			}
			if(sendCarDate.getHours() == endHours){
				if(sendCarDate.getMinutes() > startTime.getMinutes()){
					map.put("code", ResultCode.errorCode);
					map.put("msg", "预计送车时间必须为早"+startTime+"-晚"+noticeEndTime+"之间");
					return JsonUtil.extractJson(map);
				}
			}
		}

		Member member = null;
		try {
			member = memberService.findMemberByID(loginId);
			if(member.getIsButler() != 1){
				map.put("code", ResultCode.errorCode);
				map.put("msg", "只有管家才可以操作签约");
				return JsonUtil.extractJson(map);
			}
		} catch (Exception e1) {
			logger.error("发起签约appointment--carId"+carId+""+e1.getMessage());
			return JsonUtil.extractJson(map);
		}
		//修改车辆状态为已预约
		Integer id = carOperateService.appointment(carId);
		if(id > 0){
			//查询车辆
			if(car != null){
				Date now = new Date();
				
				//取消该车辆的后续预约计划
				CarOperatePlan carOperateP = new CarOperatePlan();
				//查询车辆车牌号
				carOperateP.setCarPlateNum(car.getCarPlateNum());
				carOperateP.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
				carOperateP.setStarted(now);
				//会员用车清空车辆
				carOperatePlanService.updateStatusByOperateIdAndStarted(carOperateP);
				//tdCarOperatePlanMapper.updateStatusByOperateIdAndStarted(carOperateP);
				//平台运营直接变成取消
				carOperatePlanService.updateStatusByOperateIdAndStartedForPlatform(carOperateP);
				
				//查询此车辆拥有的任务,取消任务的车辆
				ButlerTask butlerTask = new ButlerTask();
				butlerTask.setCarOperateId(carId);
				butlerTask.setCompleteTime(now);
				butlerTask.setOperaterId(member.getSysuserId());
				butlerTaskService.updateButlerTaskCarOperate(butlerTask,4);
				//发送消息  车辆签约
				checkSendMessageByFault(car);
				
				//发送消息
				CarOperatePlan plan = new CarOperatePlan();
				plan.setCarPlateNum(car.getCarPlateNum());
				plan.setPowerUsedId(null);
				now.setSeconds(now.getSeconds() - 1);
				plan.setStarted(now);
				List<CarOperatePlan> planList = carOperatePlanService.selectCarPlanNumAndpowerUsedId(plan);
				for(CarOperatePlan plans : planList){
					//插入一条新的运营中的日志
					CarOperateLog carOperateLog = new CarOperateLog();
					if(member != null){
						carOperateLog.setOperateName(member.getSysuserName());
						carOperateLog.setOperateId(member.getSysuserId());
					}
					carOperateLog.setRemark("平台运营受到签约预约影响自动取消");
					carOperateLog.setType(CarOperateEnum.status.APPOINTMENT.getValue());
					carOperateLog.setOperateNum(plans.getOperateNum());
					carOperateLog.setCreated(new Date());
					try {
						carOperateLogService.insertSelective(carOperateLog);
					} catch (Exception e) {
						logger.error("发起车辆维修replace--carId"+carId);
					}
					String message = "您安排的平台运营车辆由于车辆签约已被取消，请尽快处理";
					wechatMessageService.sendMessageByPlatformOperation(plans,message);
				}
				try {
					//添加平台运营
					CarOperatePlan carOperatePlan = new CarOperatePlan();
					carOperatePlan.setCarTypeId(car.getCarTypeId());
					carOperatePlan.setOperateNum(car.getOperateNum());
					carOperatePlan.setCarPlateNum(car.getCarPlateNum());
					carOperatePlan.setIsStock(1);
					carOperatePlan.setOperateType(0L);
					carOperatePlan.setStarted(sendCarDate);
					carOperatePlan.setEnded(sendCarDate);
					carOperatePlan.setCreated(new Date());
					carOperatePlan.setCarUsedAddress(address);
					carOperatePlan.setRemark("签约发起");
					BigDecimal money = new BigDecimal(1199);
					carOperatePlan.setMoney(money);
					//签约预约  memberId先设置成-1
					carOperatePlan.setMemberId(-1L);
					carOperatePlan.setStatus(CarOperatePlanEnum.status.STAY.getValue());
					carOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSE.getValue());
					carOperatePlan.setOperateId(carId);
					if(member != null){
						carOperatePlan.setButlerId(member.getSysuserId());
						carOperatePlan.setButlerName(member.getSysuserName());
					}
					carOperatePlanService.insertSelective(carOperatePlan);
				} catch (Exception e1) {
					logger.info("签约预约 添加计划失败"+e1.getMessage());
					return JsonUtil.extractJson(map);
				}
				//插入一条新的运营中的日志
				CarOperateLog carOperateLog = new CarOperateLog();
				if(member != null){
					carOperateLog.setOperateName(member.getSysuserName());
					carOperateLog.setOperateId(member.getSysuserId());
				}
				carOperateLog.setRemark("发起签约");
				carOperateLog.setType(CarOperateEnum.status.APPOINTMENT.getValue());
				carOperateLog.setOperateNum(car.getOperateNum());
				carOperateLog.setCreated(new Date());
				try {
					carOperateLogService.insertSelective(carOperateLog);
				} catch (Exception e) {
					logger.error("发起签约appointment--carId"+carId);
					return JsonUtil.extractJson(map);
				}
			}
			map.put("code", ResultCode.successCode);
			map.put("msg", "签约成功");
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/searchDayStore")
	@ResponseBody
	public Map<String,Object> searchDayStore(String date,Long carTypeId){
		List<Map<String,Object>> storeList = Lists.newArrayList();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			date = date.replace('/', '-');
			Calendar startCalendar = getMiniDate(date, false);
		 	Calendar endCalendar = getMaxDate(date, false);
		 	Date startDate = startCalendar.getTime();
		 	Date endDate = endCalendar.getTime();
		 	//计算得到两个需要查询的时间段
			long count = ((endDate.getTime()-startDate.getTime()) / (1000 * 60 * 60)) + 1;
			for (int i = 0; i < count; i++) {
				endDate = DateUtils.addHours(startDate, 1);
				//boolean flag = carOperatePlanService.isStoreByHours(carTypeId, startDate, endDate);
				Long num = carOperatePlanService.isStoreByHours(carTypeId, startDate, endDate);
				int startHours = startDate.getHours();
				int endHours = endDate.getHours();
				map.put("flag", startHours+"-"+endHours+":"+num);
				/*if(flag){
					map.put("flag", startHours+"-"+endHours+"有库存");
				}else{
					map.put("flag", startHours+"-"+endHours+"无库存");
				}*/
				startDate=endDate;
				storeList.add(map);
				map = new HashMap<String, Object>();
			}	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResultUtils.success(storeList);
	}
	
	/**
	 * 发起签约给收到影响的任务的负责管家发送消息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-13 下午6:07:34   
	* @return String
	 */
	private void checkSendMessageByFault(CarOperate car){

		//查看失联之后的一个月时间内有没有影响库存，相当于再次发起一次用车（开始时间是延时之前的时间，结束时间为修改的最新时间）
		Date currentDate = new Date();
		boolean storeFlag = this.carOperatePlanService.isStoreForDelay(car.getCarTypeId(), currentDate, DateUtils.addMonths(currentDate, 1));
		if(!storeFlag){
			//然后查询这段时间这个车型的所有（待接单，带送车）送车任务，给管家发消息推送
			ButlerTask getCarButlerTask = new ButlerTask();
			getCarButlerTask.setCarTypeId(car.getCarTypeId());
			getCarButlerTask.setQueryBegin(currentDate);
			getCarButlerTask.setQueryEnd(DateUtils.addMonths(currentDate, 1));
			List<ButlerTask> butlertasklist = butlerTaskService.selectByCartypeIdAndStartEnd(getCarButlerTask);
			if(CollectionUtils.isNotEmpty(butlertasklist)){
				Member butlerMember = new Member();
				for(ButlerTask butlerT : butlertasklist){
					butlerMember.setSysuserId(butlerT.getOperaterId());
					butlerMember = this.memberService.selectBySysUserId(butlerMember);
					//循环发送消息推送
					this.wechatMessageService.sendMessageByLoseOrTrouble(car.getId(), 4 , butlerMember, butlerT);
				}
			}
		}
	}
	
	/**
	 * 发起维修给收到影响的任务的负责管家发送消息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-13 下午6:07:34   
	* @return String
	 */
	private void checkSendMessageByRepair(CarOperate car){

		//查看失联之后的一个月时间内有没有影响库存，相当于再次发起一次用车（开始时间是延时之前的时间，结束时间为修改的最新时间）
		Date currentDate = new Date();
		boolean storeFlag = this.carOperatePlanService.isStoreForDelay(car.getCarTypeId(), currentDate, DateUtils.addMonths(currentDate, 1));
		if(!storeFlag){
			//然后查询这段时间这个车型的所有（待接单，带送车）送车任务，给管家发消息推送
			ButlerTask getCarButlerTask = new ButlerTask();
			getCarButlerTask.setCarTypeId(car.getCarTypeId());
			getCarButlerTask.setQueryBegin(currentDate);
			getCarButlerTask.setQueryEnd(DateUtils.addMonths(currentDate, 1));
			List<ButlerTask> butlertasklist = butlerTaskService.selectByCartypeIdAndStartEnd(getCarButlerTask);
			if(CollectionUtils.isNotEmpty(butlertasklist)){
				Member butlerMember = new Member();
				for(ButlerTask butlerT : butlertasklist){
					butlerMember.setSysuserId(butlerT.getOperaterId());
					butlerMember = this.memberService.selectBySysUserId(butlerMember);
					//循环发送消息推送
					this.wechatMessageService.sendMessageByLoseOrTrouble(car.getId(), 3 , butlerMember, butlerT);
				}
			}
		}
	}
	
	@RequestMapping("/cacleAppointment")
	@ResponseBody
	public String cacleAppointment(Long carId,Long loginId,ModelMap map){
		map.put("carId", carId);
		map.put("loginId", loginId);
		map.put("code", ResultCode.errorCode);
		map.put("msg", "取消签约失败");
		//管家手动取消签约
		Integer id = carOperateService.stay(carId, loginId, 3);
		if(id > 0){
			map.put("code", ResultCode.successCode);
			map.put("msg", "取消签约成功");
			//查询计划中的  签约记录，手动取消
			CarOperatePlan carOperatePlan = carOperatePlanService.selectAppointmentByOperateId(carId);
			if(carOperatePlan != null){
				CarOperatePlan updateCarPlan = new CarOperatePlan();
				//修改计划的状态为取消
				updateCarPlan.setId(carOperatePlan.getId());
				updateCarPlan.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
				carOperatePlanService.updateByPrimaryKeySelective(updateCarPlan);
			}
		}
		return JsonUtil.extractJson(map);
	}
	
	@RequestMapping("/launchAppointment")
	@ResponseBody
	public String launchAppointment(Long carId,String mobile,String beginTime,String endTime,String sendAddress,String getAddress,Long loginId,ModelMap map){
		map.put("code", ResultCode.errorCode);
		map.put("msg", "发起签约失败");
		//通过手机号查询 member 会员信息
		Member member = memberService.findMemberByMobile(mobile);
		//查询后台设置的服务时间
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date beginTimeDate = new Date();
			Date endTimeDate = sdf.parse(endTime);
			//查询后台设置的服务时间
			if(basicThreshold != null){
				Time startTime = basicThreshold.getServiceTimeStart();
				int startHours = startTime.getHours();
				Time noticeEndTime = basicThreshold.getServiceTimeEnd();
				int endHours = noticeEndTime.getHours(); 
				Date nowDate = new Date();
				//查询开始时间 是否 在服务时间内
				if(beginTimeDate.getHours() < startHours || endTimeDate.getHours() > endHours){
					map.put("msg", "只能选择"+startTime+"-"+noticeEndTime+"内的时间");
					return JsonUtil.extractJson(map);
				}
				if(endTimeDate.getHours() < startHours){
					map.put("msg", "只能选择"+startTime+"-"+noticeEndTime+"内的时间");
					return JsonUtil.extractJson(map);
				}
				if(endTimeDate.getHours() == endHours){
					if(endTimeDate.getMinutes() > noticeEndTime.getMinutes()){
						map.put("code", ResultCode.errorCode);
						map.put("msg", "预计送车时间必须为早"+startTime+"-晚"+noticeEndTime+"之间");
						return JsonUtil.extractJson(map);
					}
				}
				if(beginTimeDate.getHours() == startHours){
					if(beginTimeDate.getMinutes() < startTime.getMinutes()){
						map.put("code", ResultCode.errorCode);
						map.put("msg", "预计送车时间必须为早"+startTime+"-晚"+noticeEndTime+"之间");
						return JsonUtil.extractJson(map);
					}
				}
			}
		if(member == null){
			map.put("msg", "查询无此会员");
			return JsonUtil.extractJson(map);
		}
		if(member.getStatuts() == 0){
			map.put("msg", "该用户已被冻结。无法发起用车");
			return JsonUtil.extractJson(map);
		}
		
		//权益
		MemberItemShare memberItemShare = memberItemShareService.selectByMemberId(member.getId());
		if(memberItemShare == null){
			map.put("msg", "该用户无权益，无法发起用车");
			return JsonUtil.extractJson(map);
		}
		
		//查询权益	
		MemberItem memberItem = memberItemService.findById(memberItemShare.getMemberItemId());
		//可用车位
		//套餐最大启用数， 使用中的 用车 记录数,
		//判断当前是否有可用车位
		PowerUsed powerUsedNew = new PowerUsed();
		powerUsedNew.setMemberItemId(memberItemShare.getMemberItemId());
		List<PowerUsed> powerUsedList = powerUsedService.selectPowerUsedOfUseingCars(powerUsedNew);
		Item item = itemService.findById(memberItem.getItemId());
//		//我的可用车位数
		Integer enableCount = item.getEnableCount();
		if(CollectionUtils.isNotEmpty(powerUsedList)&&enableCount<=powerUsedList.size()){
			map.put("msg", "该用户无可用车位，无法发起用车");
			return JsonUtil.extractJson(map);
		}
			
		BigDecimal balance = memberItem.getBalance();//余额
		BigDecimal deposit = memberItem.getDeposit();//押金
//			日租金x用车天数  四舍五入 保留2位小数
		BigDecimal totalPrice = null;
		
		//查询车辆信息
		CarOperate car = carOperateService.findById(carId);
		map.put("carPlateNum", car.getCarPlateNum());
		
		ItemCartype itemCartype = new ItemCartype();
		itemCartype.setItemId(memberItem.getItemId());
		itemCartype.setCartypeId(car.getCarTypeId());
		List<ItemCartype> itemCartypeList = itemCarTypeService.selectByCondition(itemCartype);
		BigDecimal dayPrice =null;
		//获取套餐下该车型
		if(CollectionUtils.isNotEmpty(itemCartypeList)){
			dayPrice=itemCartypeList.get(0).getDayPrice();
		}else{
			List<CarType> carTypes = carTypeService.selectCarTypes(car.getCarType());
			if(CollectionUtils.isNotEmpty(carTypes)){
				CarType carType = carTypes.get(0);
				dayPrice = carType.getDayPrice();
			}
		}
		//用车天数
		int usedDay = getDistanceOfTwoDate(beginTimeDate, endTimeDate);
		if(usedDay == 0){
			usedDay = 1;
		}
		//用车天数 * 日使用费
		totalPrice = dayPrice.multiply(new BigDecimal(usedDay).setScale(2, BigDecimal.ROUND_HALF_UP));
	
		BigDecimal depositUsecar = basicThreshold.getDepositUsecar();//'发起用车时会员押金不得少于(元)',
//			当前用户押金小于 阀值
		if(deposit.doubleValue()<depositUsecar.doubleValue()){
			map.put("msg", "该用户押金不足，无法发起用车");
			return JsonUtil.extractJson(map);
		}
		
		//余额不足支持本次用车
		if(totalPrice.doubleValue()>balance.doubleValue()){
			map.put("msg", "该用户余额不足以支付本次用车费用");
			return JsonUtil.extractJson(map);
		} 
		
		Integer useCarApproved = member.getUseCarApproved();//用车审核状态：0：待审核；1：驳回审核；2：通过审核；3未审核（无提交审核记录）',
		if(useCarApproved != 2){
			map.put("msg", "该用户未通过用车审核，需先通过用车审核");
			return JsonUtil.extractJson(map);
		}
		//修改车辆状态 为待分配
		Integer id = carOperateService.stay(carId);
		if(id > 0){
			Map<String, Object> hashMap = createPowerUsed(car, member, usedDay, beginTimeDate, endTime, sendAddress, getAddress,loginId);
			if(StringUtils.isNotEmpty(hashMap.get("msg").toString())){
				carOperateService.appointment(carId);
				map.put("msg", hashMap.get("msg"));
				return JsonUtil.extractJson(map);
			}else{
				if(StringUtils.isNotEmpty(hashMap.get("powerUsedId").toString())){
					map.put("code", ResultCode.successCode);
					map.put("msg", "签约成功");
					Long powerUsedId = Long.decode(hashMap.get("powerUsedId").toString());
					CarOperatePlan carOperatePlan = carOperatePlanService.selectAppointmentByOperateId(carId);
					if(carOperatePlan != null){
						CarOperatePlan newCarOperatePlan = new CarOperatePlan();
						newCarOperatePlan.setId(carOperatePlan.getId());
						newCarOperatePlan.setStarted(beginTimeDate);
						newCarOperatePlan.setEnded(endTimeDate);
						newCarOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
						newCarOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSEING.getValue());
						newCarOperatePlan.setMemberId(member.getId());
						newCarOperatePlan.setMemberName(member.getName());
						newCarOperatePlan.setMemberMobile(member.getMobile());
						newCarOperatePlan.setCarUsedAddress(sendAddress);
						newCarOperatePlan.setPowerUsedId(powerUsedId);
						carOperatePlanService.updateByPrimaryKeySelective(newCarOperatePlan);
						//插入一条新的运营中的日志
						CarOperateLog carOperateLog = new CarOperateLog();
						if(member != null){
							carOperateLog.setMemberId(member.getId());
							carOperateLog.setMemberName(member.getName());
							carOperateLog.setOperateName(member.getSysuserName());
							carOperateLog.setOperateId(member.getSysuserId());
						}
						carOperateLog.setTakein(dayPrice);
						carOperateLog.setRemark("签约完成");
						carOperateLog.setType(CarOperateEnum.status.MEMBERUSEING.getValue());
						carOperateLog.setOperateNum(car.getOperateNum());
						carOperateLog.setCreated(new Date());
						try {
							carOperateLogService.insertSelective(carOperateLog);
						} catch (Exception e) {
							logger.error("签约确认launchAppointment--carId"+carId);
							return JsonUtil.extractJson(map);
						}
					}
				}
			}
		}
	
		/*if(!isdeposit&&!isbalance){//押金不足且余额不足
			returnMap.put("code", "4");
			returnMap.put("price", depositUsecar.longValue());
			returnMap.put("msg", "您的余额不足支付本次用车且押金不满"+depositUsecar.longValue()+"元，请及时续费");
			return returnMap;
		}
		if(!isbalance){//余额不足
			returnMap.put("code", "2");
			returnMap.put("msg", "您余额不足以支付本次用车，请及时续费");
			return returnMap;
		}
		if(!isdeposit){//押金不足
			returnMap.put("code", "3");
			returnMap.put("price", depositUsecar.longValue());
//				returnMap.put("msg", "您的用车押金不满"+depositUsecar.longValue()+"元，请及时续费");
			return returnMap;
		}*/
		} catch (ParseException e) {
			logger.info("launchAppointment发起签约失败"+e.getMessage());
			map.put("msg", "签约失败");
			return JsonUtil.extractJson(map);
		}catch (Exception e) {
			logger.info("launchAppointment发起签约失败"+e.getMessage());
		}
		return JsonUtil.extractJson(map);
	}
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		double dayDouble =  (afterTime - beforeTime) / (1000 * 60 * 60 * 24d);
		int day = (int) Math.ceil(dayDouble);
		return day;
	}
	
	/**
	 * 创建用车记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-12 下午5:07:34   
	* @return String
	 */
	private Map<String, Object> createPowerUsed(CarOperate car,Member member,int usedDay,Date beginTime,String endTime,String sendAddress,String getAddress,Long loginId){
		try {
			PowerUsed tdPowerUsed = new PowerUsed();
	//		tdPowerUsed.setClickCartype(clickCartype);
			tdPowerUsed.setUsedStatus(3);
			tdPowerUsed.setCaroperateId(car.getId());
			tdPowerUsed.setCarTypeId(car.getCarTypeId());
			tdPowerUsed.setCarReturnAddress(getAddress);
			tdPowerUsed.setCarUsedAddress(sendAddress);
			tdPowerUsed.setMemberId(member.getId());
			tdPowerUsed.setCarUsedDay(usedDay);
			//默认北京
			tdPowerUsed.setCityCode("110000");
	//		tdPowerUsed.setPrice(new BigDecimal(price));
			tdPowerUsed.setCarReturnTime(DateUtils.parseDate(endTime));
			tdPowerUsed.setCarUsedTime(beginTime);
			Map<String, Object> map = powerUsedService.insertPowerUsedAndButler(tdPowerUsed,loginId);
			return map;
		} catch (Exception e) {
			logger.info("createPowerUsed"+e.getMessage());
		}
		return null;
	}
	
	@RequestMapping("toSelectCarType")
	public String toSelectCarType(ModelMap map){
		List<CarType> carTypeList = carTypeService.selectByCondition(null);
		map.put("carTypeList", carTypeList);
		return "gcar/car/searchCarType";
	}
	
	
//	/**
//	 * 查询库存 排期
//	* @Description:TODO
//	* @param carTypeId 车型
//	* @param start 开始时间  end 结束时间    精确到天
//	* @author:JIAZHIPENG
//	* @time:2016-11-7 上午10:14:39
//	* @return String
//	 */
//	@RequestMapping("/searchStore")
//	@ResponseBody
//	public Map<String,Object> searchStore(String carTypeId,String start,String end,ModelMap map){
//
//		Date startDate = DateUtils.parseDate(start);
//		Date endDate = new Date(end);
//		if(endDate.getMonth() - startDate.getMonth() == 0 ){
//			map = makeStore(carTypeId, startDate, endDate, map);
//		}else{
//			int forNum = endDate.getMonth() - startDate.getMonth();
//			for(int i = 0;i <= forNum; i++){
//				startDate.setMonth(startDate.getMonth()+i);
//				if(i == forNum){
//					map = makeStoreEnd(carTypeId, startDate, endDate, map);
//				}else if(i == 0){
//					map = makeStoreStart(carTypeId, startDate, endDate, map);
//				}else{
//					map = makeStore(carTypeId, startDate, endDate, map);
//				}
//			}
//		}
//		return  map;
//	}


	/**
	 * 查询库存 排期
	 * @Description:TODO
	 * @param carTypeId 车型
	 * @author:JIAZHIPENG
	 * @time:2016-11-7 上午10:14:39
	 * @return String
	 */
	@RequestMapping("/searchStore")
	@ResponseBody
	public Object searchStore(int page,String carTypeId){
		// 解析json数据
		List<Map<String,Object>> list = null;
		List<List<Map<String,Object>>> resultList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			resultList = new ArrayList<List<Map<String,Object>>>();
			Date startDate = new Date();
			Date nowDate = new Date();
			Date endDate = new Date();
			//第一页   3个月数据
//			if("0".equals(page)){
//				endDate.setMonth(startDate.getMonth() + 2);
//				Calendar calendar = getMaxDate(sdf.format(endDate), true);
//				endDate = calendar.getTime();
//				//第二页   2个月数据
//			}else if("1".equals(page)){
//				startDate.setMonth(startDate.getMonth() + 3);
//				endDate.setMonth(endDate.getMonth() + 3 + 1);
//				Calendar calendar = getMaxDate(sdf.format(endDate), true);
//				endDate = calendar.getTime();
//				// 每页 2个月数据
//			}else{
//				startDate.setMonth(startDate.getMonth() + (Integer.decode(page) * 2) + 1);
//				endDate.setMonth(endDate.getMonth() + (Integer.decode(page) * 2) + 2);
//				Calendar calendar = getMaxDate(sdf.format(endDate), true);
//				endDate = calendar.getTime();
//				if(endDate.getYear() - nowDate.getYear() >= 2){
//					endDate.setYear(nowDate.getYear() + 1);
//					endDate.setMonth(11);
//					calendar = getMaxDate(sdf.format(endDate), true);
//					endDate = calendar.getTime();
//					if(startDate.getYear() - nowDate.getYear() >= 2){
//						startDate.setYear(nowDate.getYear() + 1);
//						startDate.setMonth(10);
//					}
//				}
//			}

			startDate.setMonth(startDate.getMonth() + page);
			endDate.setMonth(endDate.getMonth()+ page);
			Calendar calendar = getMaxDate(sdf.format(endDate), true);
			endDate = calendar.getTime();
			if(endDate.getYear() - nowDate.getYear() >= 2){
				endDate.setYear(nowDate.getYear() + 1);
				endDate.setMonth(11);
				calendar = getMaxDate(sdf.format(endDate), true);
				endDate = calendar.getTime();
				if(startDate.getYear() - nowDate.getYear() >= 2){
					startDate.setYear(nowDate.getYear() + 1);
					startDate.setMonth(10);
				}
			}


			//开始时间  结束时间 在一个月的
			if(endDate.getMonth() - startDate.getMonth() == 0 ){
				list = new ArrayList<Map<String,Object>>();
				list = makeStore(carTypeId, startDate, endDate, list);
				resultList.add(list);
			}
//			else{
//				//循环多个月的排期
//				int forNum = 0;
//				if(endDate.getYear() == startDate.getYear()){
//					forNum = endDate.getMonth() -  startDate.getMonth();
//				}else{
//					forNum = 12 - startDate.getMonth() + endDate.getMonth();
//				}
//				int month = startDate.getMonth();
//				for(int i = 0;i <= forNum; i++){
//					//根据APP的需要  每个月都得从 1号到31号查询返回,不根据开始结束时间的 日期查询，
//					//保留目前的 根据开始时间结束时间查询方法
//					//每个月的排期
//					startDate.setMonth(month);
//					if(i == forNum){
//						list = new ArrayList<Map<String,Object>>();
//						//最后一个月的排期
//						list = makeStoreEnd(carTypeId, startDate, endDate, list);
//						resultList.add(list);
//					}else if(i == 0){
//						list = new ArrayList<Map<String,Object>>();
//						//开始第一个月份的排期
//						list = makeStoreStart(carTypeId, startDate, endDate, list);
//						resultList.add(list);
//					}else{
//						list = new ArrayList<Map<String,Object>>();
//						//中间月份的排期
//						list = makeStore(carTypeId, startDate, endDate, list);
//						resultList.add(list);
//					}
//					++month;
//				}
//			}
		} catch (ParseException e) {
			logger.info("查看排期错误searchStore"+e);
			return ResultUtils.fail("查看排期错误");
		}
		this.logger.info("resultList >>>>" + resultList);
		return ResultUtils.success(resultList);
	}

	/**
	 * 开始 第一个月的排期
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-11-7 上午10:17:45
	 * @return String
	 */
	private List<Map<String,Object>> makeStoreStart(String carTypeId,Date startDate,Date endDate,List<Map<String,Object>> list){
		List<Date> dates = getAllTheDateOftheMonth(startDate,1);
		return isStore(dates, carTypeId,list);
	}

	/**
	 * 中间月份的排期
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-11-7 上午10:18:00
	 * @return String
	 */
	private List<Map<String,Object>> makeStore(String carTypeId,Date startDate,Date endDate,List<Map<String,Object>> list){
		List<Date> dates = getAllTheDateOftheMonth(startDate,1);
		return isStore(dates, carTypeId,list);
	}

	/**
	 * 最后一个月的排期
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-11-7 上午10:18:10
	 * @return String
	 */
	private List<Map<String,Object>> makeStoreEnd(String carTypeId,Date startDate,Date endDate,List<Map<String,Object>> list){
		List<Date> dates = getAllTheDateOftheMonth(startDate,1);
		return isStore(dates, carTypeId,list);
	}

	/**
	 * 查询排期
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-11-7 上午10:18:17
	 * @return String
	 */
	private List<Map<String,Object>> isStore(List<Date> dates,String carTypeId,List<Map<String,Object>> list){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(Date date : dates){
				Map<String,Object> map = new HashMap<String, Object>();
				String allDate = sdf.format(date);
				int have = 2;
				try {
					if(org.apache.commons.lang3.StringUtils.isEmpty(carTypeId)){
						carTypeId = null;
					}
					have = carOperatePlanService.isStoreByRestriction(Long.decode(carTypeId), getMiniDate(allDate, false).getTime(), getMaxDate(allDate, false).getTime());
				} catch (NumberFormatException e) {
					logger.info("查询这段时间是否限号错误---searchStore"+e);
				} catch (ParseException e) {
					logger.info("查询这段时间是否限号错误---searchStore"+e);
				}
				Date nowDate = sdf.parse(allDate);
				Long time = nowDate.getTime()/1000;
				map.put("time", time);
				map.put("flag", have);
				
				if(date.before(new Date())){
					map.put("time", time);
					map.put("flag", 2);
				}
				if(nowDate.getDate() == new Date().getDate()){
					map.put("time", time);
					map.put("flag", have);
				}
				list.add(map);
			}
		} catch (ParseException e) {
			logger.info("查看排期错误isStore"+e);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/getDate")
	public String getDate(Long carTypeId,ModelMap map){
		map.put("carTypeId", carTypeId);
        return "gcar/car/calendarSchedule";
    }


	/**
	 * 获取本月最大的日期  例：2016-8-31 23:59:59
	 *  flag true设置date天为最大， false不设置天
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-9-13 上午9:52:26
	 * @return String
	 */
	private static Calendar getMaxDate(String dateStr,boolean flag) throws ParseException{
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
	
	public static void main(String[] args) throws ParseException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr= sdf.format(now);
		double dayDouble = (now.getTime() - (now.getTime() -240000000)) / (1000 * 60 * 60 * 24d);
		int day = (int) Math.ceil(dayDouble);
		System.out.println(day);
	}	
	
	/*  //APP KEY  234082620
    private final static String APP_KEY = "23497324";
    // APP密钥  abd2f66288792669548bb84ba0672b204
    private final static String APP_SECRET = "ddb1a13a3b883509fd6cfda35eb8f3c0";
    //测试图片位置
    private final static String basePath = "C:\\Users\\1mobility\\Desktop\\aaa.jpg";
    //API域名
    private final static String HOST = "dm-51.data.aliyun.com";
    //url
    private final static String URL = "/rest/160601/ocr/ocr_idcard.json";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    static {
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("Custom");
    }
    
    @RequestMapping("/uploadPic")
    public String uploadPic(){
    	return "gcar/test";
    }

    @RequestMapping("/test")
    @ResponseBody
	public String test(HttpServletRequest request,ModelMap map) throws Exception{
    	String base64Code = request.getParameter("base64");  
    	int length = base64Code.indexOf(',');
    	//base64Code = base64Code.substring("data:image/jpeg;base64,".length());
    	base64Code = base64Code.substring(length + 1);
    	CommonUtils commonUtil = new CommonUtils(HOST,URL,APP_KEY,APP_SECRET,CUSTOM_HEADERS_TO_SIGN_PREFIX,base64Code);
        String json = commonUtil.sendPostRequestWithBody();
        //JSONObject jsonObject = JSONObject.parseObject(json);
        //IDCard idCard = JSONObject.toJavaObject(jsonObject, IDCard.class);
        //map.put("idCard", idCard);
        map.put("json", json);
        return json;
	}*/
}
