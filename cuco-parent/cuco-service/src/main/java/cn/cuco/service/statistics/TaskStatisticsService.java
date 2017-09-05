package cn.cuco.service.statistics;

import java.util.List;
import java.util.Map;

import cn.cuco.entity.ButlerTask;

/** 
* @ClassName: TaskStatisticsService 
* @Description: 任务统计
* @author huanghua
* @date 2017年3月1日 上午10:56:45
 */
public interface TaskStatisticsService {

	/**  
	* @Title: getExexuteTaskStatistics 
	* @Description:执行任务统计（type：0：日；1：周；2：月；）
	* @author huanghua
	* @param butlerTask  
	* @return
	* @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getExexuteTaskStatistics(ButlerTask butlerTask);
	
}
