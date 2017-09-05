package cn.cuco.service.car.carInfo;


import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cuco.entity.Car;
import cn.cuco.entity.CarInsuranceDetail;
import cn.cuco.entity.CarUsedLog;
import cn.cuco.entity.OperateLog;
import cn.cuco.page.PageResult;
import cn.cuco.vo.CarUsedLogVO;

/** 
* @ClassName: CarService 
* @Description: 车辆主表service
* @author gongbw
* @date 2017年2月21日 下午1:35:02  
*/
public interface CarService {
    
    /**   
     * @Title: createCar   
     * @Description: 创建车辆
     * @param: Car
     * @return: Car      
     */
    public Car createCar(Car car);

    /**   
     * @Title: updateCar   
     * @Description: 修改车辆信息
     * @param: Car
     * @return: Car      
     */
    public Car updateCar(Car car);

    /**   
     * @Title: getCarById   
     * @Description: 通过车辆ID查询车辆信息
     * @param: Car
     * @return: Car      
     */
    public Car getCarById(Long id);
    
    /**   
     * @Title: getCarListByPage   
     * @Description: 分页查询车辆列表信息的方法
     * @param: Car
     * @return: List<Car>      
     */
    public PageResult<Car> getCarListByPage(Car car);
    
    /**   
     * @Title: getCarListByCartype   
     * @Description: 根据车型ID查询待分配的车辆列表(供管家端分车使用)   
     * @param: @param car
     * @param: @return      
     * @return: List<Car>       
     */
    public List<Car> getCarListByCartype(Car car);
    
    /**   
     * @Title: updateCarByWaitDistribute   
     * @Description: 修改车辆状态为待分配
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByWaitDistribute(Car car);
    
    /**   
     * @Title: updateCarByDistributed   
     * @Description: 修改车辆状态为已分配
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByDistributed(Car car);
    
    /**   
     * @Title: updateCarByMemberUsing   
     * @Description: 修改车辆状态为会员使用中
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByMemberUsing(Car car);
    
    /**   
     * @Title: updateCarByNotMemberUsing   
     * @Description: 修改车辆状态为非会员使用中   
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByNotMemberUsing(Car car);
    
    /**   
     * @Title: updateCarByRepairing   
     * @Description: 修改车辆状态为维修中
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByRepairing(Car car);
    
    /**   
     * @Title: updateCarByWaitReorganize   
     * @Description: 修改车辆状态为待整备
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByWaitReorganize(Car car);
    
    /**   
     * @Title: updateCarByReorganizing   
     * @Description: 修改车辆状态为整备中   
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByReorganizing(Car car);
    
    /**   
     * @Title: updateCarByReorganizedBySystem   
     * @Description: 系统自动整备完成(供定时器调用)
     * @return: void      
     */
    public void updateCarByReorganizedBySystem();
    
    /**   
     * @Title: updateCarByLosed   
     * @Description: 修改车辆状态为失联
     * @param: Car
     * @return: Car      
     */
    public Car updateCarByLosed(Car car);
    
    /**   
     * @Title: createCarUsedLog   
     * @Description: 创建车辆使用日志 (用于定时器) 
     * @param: 
     * @return:void     
     */
    public void createCarUsedLog();
    
    /**   
     * @Title: getCarUsedLogListByMonth   
     * @Description: 根绝车辆ID按月查询车辆日志
     * @param: CarLog
     * @return: Map<Date,CarUsedLogVO>      
     */
	 public Map<Date,CarUsedLogVO> getCarUsedLogListByMonth(CarUsedLog log) throws Exception ;
	 
	 /**   
     * @Title: getCarUsedLogListByDay   
     * @Description: 根据车辆ID按天查询车辆日志
     * @param: CarLog
     * @return: List<CarUsedLog>      
     */
	 public List<CarUsedLog> getCarUsedLogListByDay(CarUsedLog log);
    
     /**   
     * @Title: createCarInsuranceDetail   
     * @Description: 给车辆续保
     * @param: CarInsuranceDetail
     * @return: CarInsuranceDetail      
     */
	 public CarInsuranceDetail createCarInsuranceDetail(CarInsuranceDetail carInsuranceDetail);
	
	 /**   
     * @Title: getCarInsuranceDetailByCarId   
     * @Description: 查询保险详情   
     * @param: CarInsuranceDetail
     * @return: CarInsuranceDetail      
     */
	 public CarInsuranceDetail getCarInsuranceDetailByCarId(Long carId);
	 /**   
     * @Title: createCarUsedLogByCar   
     * @Description:根据车辆生成生成使用日志 
     * @param: Car
     * @return:void     
     */
	 public void createCarUsedLogByCar(Car car) throws Exception ;
	 
	 /**   
	 * @Title: getOperationLogList   
	 * @Description: 获取保险详情日志
     * @param: OperateLog
     * @return:PageResult<OperateLog>     
     */
	public PageResult<OperateLog> getOperationLogList(OperateLog log);

    /**
    * @Title:
    * @Description:根据车牌号查询车辆信息
    * @author：WS
    * @time：2017年03月11日 下午02:37:31
    * @param:
    * @return:
    * @throws:
    */
    public Car getCarByCarPlateNum(String carPlateNum);
}
