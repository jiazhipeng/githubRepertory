package cn.cuco.service.statistics;

import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: OrderCarUsedStatisticsService 
* @Description: 用车结算统计
* @author zc.du
* @date 2017年3月1日 上午10:27:32  
*/
public interface OrderCarUsedStatisticsService {

	/**
	 * @Title: orderCarUsedStatisticsService   
	 * @Description: 用车结算统计
	 * @param: dateType：0：日；1：周；2：月；
	 * @param: startTime
	 * @param: endTime 
	 * @param: carportId 车库Id，查看所有车库的时候传null 
	 * @param: carTypeId 车型Id,车看所有车型时候传null
	 * @param type 0:按用车次数统计；1：按用车金额统计
	 * @return: Map<String,Integer>      
	 */
	public List<Map<String,Object>> orderCarUsedStatistics(int dateType,Date startTime, Date endTime,Long carportId,Long carTypeId,int type);
	
	/** 
	* @Title: orderMemberCarUsedStatistics 
	* @Description: 用车流水统计
	* @author zc.du
	* @param dateType：0：日；1：周；2：月；
	* @param startTime
	* @param endTime
	* @param carTypeId:车型ID,查看所有车型时，传null
	* @param type 0:按用车次数统计；1：按用车金额统计
	* @return Map<String,Object>
	*/
	public Map<String,Object> orderMemberCarUsedStatistics(int dateType,Date startTime, Date endTime,Long carTypeId,int type);
}
