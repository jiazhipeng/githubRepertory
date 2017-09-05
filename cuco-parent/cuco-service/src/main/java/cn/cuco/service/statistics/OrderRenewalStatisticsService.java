package cn.cuco.service.statistics;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface OrderRenewalStatisticsService {
	
	/** 
	* @Title: orderRenewalStatisticsService 
	* @Description: 储值统计
	* @author zc.du
	* @param dateType：0：日；1：周；2：月；
	* @param startTime
	* @param endTime
	* @return Map<String,BigDecimal>
	*/
	public Map<String,BigDecimal> orderRenewalStatisticsService(int dateType,Date startTime, Date endTime);

}
