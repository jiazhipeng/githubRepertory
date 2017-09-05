package cn.cuco.service.statistics;

import java.util.Date;
import java.util.Map;

/** 
* @ClassName: MemberStatisticsService 
* @Description: 用户统计
* @author zc.du
* @date 2017年3月1日 上午10:27:32  
*/
public interface MemberStatisticsService {

	/**
	 * @Title: getAddMemberStatistics   
	 * @Description: 统计新增用户
	 * @param: type：0：日；1：周；2：月；
	 * @param: startTime
	 * @param: endTime 
	 * @return: Map<String,Integer>      
	 */
	public Map<String,Integer> getAddMemberStatistics(int type,Date startTime, Date endTime);
	
	/**
	 * @Title: getMemberShipStatistics   
	 * @Description: 统计新增会员(新增入会统计)
	 * @param: type：0：日；1：周；2：月；
	 * @param: startTime
	 * @param: endTime 
	 * @return: Map<String,Integer>      
	 */
	public Map<String,Integer> getMemberShipStatistics(int type,Date startTime, Date endTime);
}
