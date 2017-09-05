package cn.cuco.service.statistics;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CarStatisticsService {

	/**   
	 * @Title: getOperationStatisticsByDay   
	 * @Description: 按天统计车辆信息
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @param carTypeId
	 * @param: @param carSupplierId
	 * @param: @return      
	 * @return: List<Map<String,String>>      
	 */
	public List<Map<String,String>> getOperationStatisticsByDay(Date startTime, Date endTime, Long carTypeId, Long carSupplierId);
	
	/**   
	 * @Title: getOperationStatisticsByWeek   
	 * @Description: 按周统计车辆信息
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @param carTypeId
	 * @param: @param carSupplierId
	 * @param: @return      
	 * @return: List<Map<String,String>>      
	 */
	public List<Map<String,String>> getOperationStatisticsByWeek(Date startTime, Date endTime, Long carTypeId, Long carSupplierId);
	
	/**   
	 * @Title: getOperationStatisticsByMonth   
	 * @Description: 按月统计车辆信息
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @param carTypeId
	 * @param: @param carSupplierId
	 * @param: @return      
	 * @return: List<Map<String,String>>      
	 */
	public List<Map<String,String>> getOperationStatisticsByMonth(Date startTime, Date endTime, Long carTypeId, Long carSupplierId);
}
