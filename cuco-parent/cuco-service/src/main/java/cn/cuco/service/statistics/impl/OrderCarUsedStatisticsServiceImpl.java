package cn.cuco.service.statistics.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.OrderCarUsed;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.service.order.OrderCarUsedService;
import cn.cuco.service.order.OrderMemberCarUsedService;
import cn.cuco.service.statistics.OrderCarUsedStatisticsService;

@Service
public class OrderCarUsedStatisticsServiceImpl implements OrderCarUsedStatisticsService {

	@Autowired
	private OrderCarUsedService orderCarUsedService;
	@Autowired
	private OrderMemberCarUsedService orderMemberCarUsedService;
	
	@Override
	public List<Map<String, Object>> orderCarUsedStatistics(int dateType, Date startTime, Date endTime, Long carportId, Long carTypeId, int type) {
		ParamVerifyUtils.paramNotNull(startTime, "统计用车时，开始时间不能为空");
		ParamVerifyUtils.paramNotNull(endTime, "统计用车时，结束时间不能为空");
		if (startTime.after(endTime)) {
			ExceptionUtil.throwWarn("统计用车时，开始是时间不能大于结束时间");
		}
		List<Map<String, Object>> result = new ArrayList<>();
		OrderCarUsed search = new OrderCarUsed();
		search.setPage(1);
		search.setPageSize(1000000000);
		List<OrderCarUsed> list = this.orderCarUsedService.getOrderCarUsedListByPage(search).getItems();
		if(CollectionUtils.isEmpty(list)){
			return result;
		}
		if(0 == type){
			result = orderCarUsedStatisticsByCount(dateType,startTime,endTime,carportId,carTypeId);
		}else{
			result = orderCarUsedStatisticsByMoney(dateType,startTime,endTime,carportId,carTypeId);
		}
		return result;
	}
	
	private List<Map<String, Object>> orderCarUsedStatisticsByCount(int type,Date startTime, Date endTime, Long carportId, Long carTypeId){
		List<Map<String, Object>> result = new ArrayList<>();
		OrderCarUsed order = new OrderCarUsed();
		order.setCarportId(carportId);
		order.setCartypeId(carTypeId);
		order.setTimeStart(startTime);
		order.setTimeEnd(endTime);
		try {
			long count = 0;
			if (0 == type) {
				// 按天查询
				count = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24) + 1;
				for (int i = 0; i < count; i++) {
					startTime = DateUtils.getDayMiniDate(startTime);
					endTime = DateUtils.getDayMaxDate(startTime);
					this.setMapForCount(result, order);
					startTime = DateUtils.addDays(startTime, 1);
				}
			} else if (1 == type) {
				// 按周查询
				count = DateUtils.getCountTwoDayWeek(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date monday = DateUtils.getMondayByDate(DateUtils.addWeeks(startTime, i));
					if (monday.before(startTime)) {
						monday = startTime;
					}
					monday = DateUtils.getDayMiniDate(monday);
					Date sunday = DateUtils.getDayMaxDate(DateUtils.getSundayByDate(monday));
					if (sunday.after(endTime)) {
						sunday = endTime;
					}
					this.setMapForCount(result,order);
				}
			} else {
				// 按月查询
				count = DateUtils.getCountTwoDayMonth(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date first = DateUtils.getFirstOfMonth(DateUtils.addMonths(startTime, i));
					if (first.before(startTime)) {
						first = startTime;
					}
					first = DateUtils.getDayMiniDate(first);
					Date end = DateUtils.getDayMaxDate(DateUtils.getLastOfMonth(first));
					if (end.after(endTime)) {
						end = endTime;
					}
					this.setMapForCount(result,order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private List<Map<String, Object>> orderCarUsedStatisticsByMoney(int type,Date startTime, Date endTime, Long carportId, Long carTypeId){
		List<Map<String, Object>> result = new ArrayList<>();
		OrderCarUsed order = new OrderCarUsed();
		order.setCarportId(carportId);
		order.setCartypeId(carTypeId);
		order.setTimeStart(startTime);
		order.setTimeEnd(endTime);
		try {
			long count = 0;
			if (0 == type) {
				// 按天查询
				count = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24) + 1;
				for (int i = 0; i < count; i++) {
					startTime = DateUtils.getDayMiniDate(startTime);
					endTime = DateUtils.getDayMaxDate(startTime);
					this.setMapForMoney(result, order);
					startTime = DateUtils.addDays(startTime, 1);
				}
			} else if (1 == type) {
				// 按周查询
				count = DateUtils.getCountTwoDayWeek(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date monday = DateUtils.getMondayByDate(DateUtils.addWeeks(startTime, i));
					if (monday.before(startTime)) {
						monday = startTime;
					}
					monday = DateUtils.getDayMiniDate(monday);
					Date sunday = DateUtils.getDayMaxDate(DateUtils.getSundayByDate(monday));
					if (sunday.after(endTime)) {
						sunday = endTime;
					}
					this.setMapForMoney(result,order);
				}
			} else {
				// 按月查询
				count = DateUtils.getCountTwoDayMonth(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date first = DateUtils.getFirstOfMonth(DateUtils.addMonths(startTime, i));
					if (first.before(startTime)) {
						first = startTime;
					}
					first = DateUtils.getDayMiniDate(first);
					Date end = DateUtils.getDayMaxDate(DateUtils.getLastOfMonth(first));
					if (end.after(endTime)) {
						end = endTime;
					}
					this.setMapForMoney(result,order);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void setMapForCount(List<Map<String, Object>> target,OrderCarUsed order){
		Map<String,Object> data = new HashMap<>();
		data.put("date", order.getTimeStart());
		order.setType(1);
		data.put("member", this.orderCarUsedService.getOrderCarUsedCount(order));
		order.setType(0);
		data.put("tourist", this.orderCarUsedService.getOrderCarUsedCount(order));
		target.add(data);
	}
	
	private void setMapForMoney(List<Map<String, Object>> target,OrderCarUsed order){
		Map<String,Object> data = new HashMap<>();
		data.put("date", order.getTimeStart());
		order.setType(1);
		data.put("member", this.orderCarUsedService.getOrderCarUsedMoney(order));
		order.setType(0);
		data.put("tourist", this.orderCarUsedService.getOrderCarUsedMoney(order));
		target.add(data);
	}

	@Override
	public Map<String, Object> orderMemberCarUsedStatistics(int dateType, Date startTime, Date endTime, Long carTypeId, int type) {
		ParamVerifyUtils.paramNotNull(startTime, "统计用车流水时，开始时间不能为空");
		ParamVerifyUtils.paramNotNull(endTime, "统计用车流水时，结束时间不能为空");
		if (startTime.after(endTime)) {
			ExceptionUtil.throwWarn("统计用车流水时，开始是时间不能大于结束时间");
		}
		Map<String, Object> result = new HashMap<>();
		OrderMemberCarUsed search = new OrderMemberCarUsed();
		search.setPage(1);
		search.setPageSize(1000000000);
		List<OrderMemberCarUsed> list = this.orderMemberCarUsedService.getOrderMemberCarUsedListByPage(search).getItems();
		if(CollectionUtils.isEmpty(list)){
			return result;
		}
		if(0 == type){
			result = this.orderMemberCarUsedStatisticsByCount(dateType,startTime,endTime,carTypeId);
		}else{
			result = this.orderMemberCarUsedStatisticsByMoney(dateType,startTime,endTime,carTypeId);
		}
		return result;
	}
	
	private Map<String,Object> orderMemberCarUsedStatisticsByCount(int type,Date startTime, Date endTime, Long carTypeId){
		Map<String, Object> result = new HashMap<>();
		OrderMemberCarUsed order = new OrderMemberCarUsed();
		order.setOrderCartypeId(carTypeId);
		try {
			long count = 0;
			if (0 == type) {
				// 按天查询
				count = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24) + 1;
				for (int i = 0; i < count; i++) {
					startTime = DateUtils.getDayMiniDate(startTime);
					endTime = DateUtils.getDayMaxDate(startTime);
					order.setStartDate(startTime);
					order.setEndDate(endTime);
					Integer total = this.orderMemberCarUsedService.getCarUsedCountByDate(order);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
					startTime = DateUtils.addDays(startTime, 1);
				}
			} else if (1 == type) {
				// 按周查询
				count = DateUtils.getCountTwoDayWeek(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date monday = DateUtils.getMondayByDate(DateUtils.addWeeks(startTime, i));
					if (monday.before(startTime)) {
						monday = startTime;
					}
					monday = DateUtils.getDayMiniDate(monday);
					Date sunday = DateUtils.getDayMaxDate(DateUtils.getSundayByDate(monday));
					if (sunday.after(endTime)) {
						sunday = endTime;
					}
					order.setStartDate(startTime);
					order.setEndDate(endTime);
					Integer total = this.orderMemberCarUsedService.getCarUsedCountByDate(order);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
				}
			} else {
				// 按月查询
				count = DateUtils.getCountTwoDayMonth(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date first = DateUtils.getFirstOfMonth(DateUtils.addMonths(startTime, i));
					if (first.before(startTime)) {
						first = startTime;
					}
					first = DateUtils.getDayMiniDate(first);
					Date end = DateUtils.getDayMaxDate(DateUtils.getLastOfMonth(first));
					if (end.after(endTime)) {
						end = endTime;
					}
					order.setStartDate(startTime);
					order.setEndDate(endTime);
					Integer total = this.orderMemberCarUsedService.getCarUsedCountByDate(order);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private Map<String,Object> orderMemberCarUsedStatisticsByMoney(int type,Date startTime, Date endTime, Long carTypeId){
		Map<String, Object> result = new HashMap<>();
		OrderMemberCarUsed order = new OrderMemberCarUsed();
		order.setOrderCartypeId(carTypeId);
		try {
			long count = 0;
			if (0 == type) {
				// 按天查询
				count = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24) + 1;
				for (int i = 0; i < count; i++) {
					startTime = DateUtils.getDayMiniDate(startTime);
					endTime = DateUtils.getDayMaxDate(startTime);
					order.setStartDate(startTime);
					order.setEndDate(endTime);
					BigDecimal total = this.orderMemberCarUsedService.getCarUsedCostMoneyByDate(order);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
					startTime = DateUtils.addDays(startTime, 1);
				}
			} else if (1 == type) {
				// 按周查询
				count = DateUtils.getCountTwoDayWeek(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date monday = DateUtils.getMondayByDate(DateUtils.addWeeks(startTime, i));
					if (monday.before(startTime)) {
						monday = startTime;
					}
					monday = DateUtils.getDayMiniDate(monday);
					Date sunday = DateUtils.getDayMaxDate(DateUtils.getSundayByDate(monday));
					if (sunday.after(endTime)) {
						sunday = endTime;
					}
					order.setStartDate(startTime);
					order.setEndDate(endTime);
					BigDecimal total = this.orderMemberCarUsedService.getCarUsedCostMoneyByDate(order);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
				}
			} else {
				// 按月查询
				count = DateUtils.getCountTwoDayMonth(startTime, endTime);
				for (int i = 0; i < count; i++) {
					Date first = DateUtils.getFirstOfMonth(DateUtils.addMonths(startTime, i));
					if (first.before(startTime)) {
						first = startTime;
					}
					first = DateUtils.getDayMiniDate(first);
					Date end = DateUtils.getDayMaxDate(DateUtils.getLastOfMonth(first));
					if (end.after(endTime)) {
						end = endTime;
					}
					order.setStartDate(startTime);
					order.setEndDate(endTime);
					BigDecimal total = this.orderMemberCarUsedService.getCarUsedCostMoneyByDate(order);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
