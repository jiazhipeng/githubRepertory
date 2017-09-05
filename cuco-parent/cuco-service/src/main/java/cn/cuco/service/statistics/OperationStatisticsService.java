package cn.cuco.service.statistics;

import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: OperationStatistics 
* @Description: 车辆运营率统计
* @author gongbw
* @date 2017年3月1日 上午10:27:32  
*/
public interface OperationStatisticsService {

	/**   
	 * @Title: getOperationStatisticsByDay   
	 * @Description: 按天统计运营率   
	 * @param: startTime
	 * @param: endTime 
	 * @param: carportId
	 * @param: carTypeId
	 * @return: List<Map<String,String>>      
	 */
	public List<Map<String,String>> getOperationStatisticsByDay(Date startTime, Date endTime, Long carportId, Long carTypeId);
	
	/**   
	 * @Title: getOperationStatisticsByWeek   
	 * @Description: 按周统计运营率   
	 * @param: startTime
	 * @param: endTime 
	 * @param: carportId
	 * @param: carTypeId    
	 * @return: List<Map<String,String>>      
	 */
	public List<Map<String,String>> getOperationStatisticsByWeek(Date startTime, Date endTime, Long carportId, Long carTypeId);
	
	/**   
	 * @Title: getOperationStatisticsByMonth   
	 * @Description: 按月统计运营率   
	 * @param: startTime
	 * @param: endTime 
	 * @param: carportId
	 * @param: carTypeId    
	 * @return: List<Map<String,String>>      
	 */
	public List<Map<String,String>> getOperationStatisticsByMonth(Date startTime, Date endTime, Long carportId, Long carTypeId);
	
}
