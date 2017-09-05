package cn.cuco.service.statistics.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.OrderRenewal;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.service.order.OrderRenewalService;
import cn.cuco.service.statistics.OrderRenewalStatisticsService;

@Service
public class OrderRenewalStatisticsServiceImpl implements OrderRenewalStatisticsService {
	
	@Autowired
	private OrderRenewalService orderRenewalService;

	@Override
	public Map<String, BigDecimal> orderRenewalStatisticsService(int dateType, Date startTime, Date endTime) {
		ParamVerifyUtils.paramNotNull(startTime, "统计用车流水时，开始时间不能为空");
		ParamVerifyUtils.paramNotNull(endTime, "统计用车流水时，结束时间不能为空");
		if (startTime.after(endTime)) {
			ExceptionUtil.throwWarn("统计用车流水时，开始是时间不能大于结束时间");
		}
		Map<String, BigDecimal> result = new HashMap<>();
		OrderRenewal search = new OrderRenewal();
		search.setPage(1);
		search.setPageSize(1000000000);
		List<OrderRenewal> list = this.orderRenewalService.getOrderRenewalListByPage(search).getItems();
		if(CollectionUtils.isEmpty(list)){
			return result;
		}
		try {
			long count = 0;
			if (0 == dateType) {
				// 按天查询
				count = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24) + 1;
				for (int i = 0; i < count; i++) {
					startTime = DateUtils.getDayMiniDate(startTime);
					endTime = DateUtils.getDayMaxDate(startTime);
					BigDecimal total = this.orderRenewalService.getCompletedPayOrderRenewalMoneyByDate(startTime,endTime);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
					startTime = DateUtils.addDays(startTime, 1);
				}
			} else if (1 == dateType) {
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
					BigDecimal total = this.orderRenewalService.getCompletedPayOrderRenewalMoneyByDate(startTime,endTime);
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
					BigDecimal total = this.orderRenewalService.getCompletedPayOrderRenewalMoneyByDate(startTime,endTime);
					result.put(DateUtils.formatDate(startTime, "yyyy-MM-dd"), total);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
