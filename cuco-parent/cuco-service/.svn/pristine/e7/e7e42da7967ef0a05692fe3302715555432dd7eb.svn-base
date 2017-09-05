package cn.cuco.service.car.carOperate;


import cn.cuco.entity.CarRepair;
import cn.cuco.entity.OperateLog;
import cn.cuco.page.PageResult;

/**
* @ClassName: CarRepairService 
* @Description: 车辆维修接口
* @author huanghua
* @date 2017年2月21日 下午3:23:34
 */
public interface CarRepairService {
    /**
    * @Title: createCarRepair 
    * @Description:创建车辆维修
    * @param  carRepair
    * @return CarRepair
    * @throws
     */
    public CarRepair createCarRepair(CarRepair carRepair);

    /**
    * @Title: updateCarRepairByMaintenance 
    * @Description: 修改车辆维修状态-维修中
    * @param  carRepair
    * @return CarRepair
    * @throws
     */
    public CarRepair updateCarRepairByMaintenance(CarRepair carRepair);
    
    /**
    * @Title: updateCarRepairByMaintenance 
    * @Description: 修改车辆维修状态-完成维修
    * @param  carRepair
    * @return Integer
    * @throws
     */
    public CarRepair updateCarRepairBycompleted(CarRepair carRepair);
    
    /**
    * @Title: getCarRepairById 
    * @Description: 取车辆维修信息
    * @author huanghua
    * @param id
    * @return
    * @return CarRepair
     */
    public CarRepair getCarRepairById(Long id);

    /**
    * @Title: getCarRepairByCondition 
    * @Description: 分页取车辆维修信息
    * @param  tdCarRepair
    * @return List<CarRepair>
    * @throws
     */
    public PageResult<CarRepair> getCarRepairByCondition(CarRepair carRepair);
    
    /**
    * @Title: getCarRepairLogByPage 
    * @Description: 分页取日志
    * @author huanghua
    * @param OperateLog
    * @return
    * @return PageResult<OperateLog>
     */
     public PageResult<OperateLog> getCarRepairLogByPage(OperateLog OperateLog);

     /**
     * @Title: createCarRepairRemark 
     * @Description: 维修备注
     * @author huanghua
     * @param carRepair
     * @return
     * @return CarRepair
      */
     public CarRepair createCarRepairRemark(CarRepair carRepair);
}
