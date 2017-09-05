package cn.cuco.service.car.carOperate;

import java.math.BigDecimal;

import cn.cuco.entity.CarViolation;
import cn.cuco.entity.OperateLog;
import cn.cuco.page.PageResult;

/** 
* @ClassName: CarViolationService 
* @Description: 车辆违章service接口 
* @author gongbw
* @date 2017年3月6日 下午1:54:03  
*/
public interface CarViolationService {

	/**   
	 * @Title: getCarRepairByCondition   
	 * @Description: 分页获取车辆违章记录 
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: PageResult<CarViolation>       
	 */
	public PageResult<CarViolation> getCarViolationByPage(CarViolation carViolation);
	
	/**   
	 * @Title: createCarViolation   
	 * @Description: 创建车辆违章 
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: CarViolation       
	 */
	public CarViolation createCarViolation(CarViolation carViolation);
	
	/**   
	 * @Title: getCarViolationById   
	 * @Description: 根据车辆ID查询车辆违章详情 
	 * @param: @param id
	 * @param: @return      
	 * @return: CarViolation       
	 */
	public CarViolation getCarViolationById(Long id);
	
	/**   
	 * @Title: getCarViolationLogByPage   
	 * @Description: 分页获取车辆违章操作日志   
	 * @param: @param log
	 * @param: @return      
	 * @return: PageResult<OperateLog>       
	 */
	public PageResult<OperateLog> getCarViolationLogByPage(OperateLog log);
	
	/**   
	 * @Title: updateCarViolation2pending   
	 * @Description: 修改车辆违章状态为待处理 
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: CarViolation       
	 */
	public CarViolation updateCarViolation2processing(CarViolation carViolation);
	
	/**   
	 * @Title: updateCarViolation2processed   
	 * @Description: 修改车辆违章状态为已处理   
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: CarViolation       
	 */
	public CarViolation updateCarViolation2processed(CarViolation carViolation);
	
	/**   
	 * @Title: calculatePriceDealWithBycucuo   
	 * @Description: 用户端申请cuco处理违章返回代处理费用  
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: BigDecimal       
	 */
	public BigDecimal calculatePriceDealWithBycucuo(CarViolation carViolation);
	
	/**   
	 * @Title: updateCarViolation2processingBysystem   
	 * @Description: 自押金预授权被刷15日用户自己还没清除的车辆违章记录变为cuco代为处理(供定时器用)   
	 * @param: @param carViolation      
	 * @return: void       
	 */
	public void updateCarViolation2processingBysystem(CarViolation carViolation);
}
