package cn.cuco.service.statistics;

import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: CarportStatisticsService 
* @Description: 车库统计
* @author zc.du
* @date 2017年3月1日 上午10:27:32  
*/
public interface CarportStatisticsService {

	/** 
	* @Title: getUnlockCarportStatistics 
	* @Description: 解锁车库统计
	* @author zc.du
	* @param dateType：0：日；1：周；2：月；
	* @param startTime
	* @param endTime
	* @param type 0:按解锁数量统计；1：按解锁金额统计
	* @return List<Map<String,Integer>>
	*/
	public List<Map<String,Object>> getUnlockCarportStatistics(int dateType,Date startTime, Date endTime,int type);
	
}
