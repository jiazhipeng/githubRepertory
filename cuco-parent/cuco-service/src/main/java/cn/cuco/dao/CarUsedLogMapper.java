package cn.cuco.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.cuco.entity.CarUsedLog;

public interface CarUsedLogMapper<T> extends BaseDao<T> {
	
	/**   
	 * @Title: selectCarUsedLogByCarId   
	 * @Description: 根据车辆ID获取车辆使用日志
	 * @param: carUsedLog
	 * @return: CarUsedLog      
	 */
	public List<CarUsedLog> selectCarUsedLogByCarId(CarUsedLog carUsedLog);
	
	/**   
	 * @Title: selectCarUsedLogListByDay   
	 * @Description: 根据车辆ID按天获取车辆使用日志
	 * @param: carUsedLog
	 * @return: CarUsedLog      
	 */
	public List<CarUsedLog> selectCarUsedLogListByDay(CarUsedLog carUsedLog);
	
	/**   
	 * @Title: selectTypeByDay   
	 * @Description: 根据车辆ID按天获取车辆使用类型的集合
	 * @param: carUsedLog
	 * @return: CarUsedLog      
	 */
	public List<CarUsedLog> selectTypeByDay(CarUsedLog carUsedLog);
	
	/**   
	 * @Title: selectTypeByDay   
	 * @Description: 根据车辆ID按天获取总的行驶里程数
	 * @param: carUsedLog
	 * @return: BigDecimal      
	 */
	public BigDecimal selectSumMileageByDay(CarUsedLog carUsedLog);
}
