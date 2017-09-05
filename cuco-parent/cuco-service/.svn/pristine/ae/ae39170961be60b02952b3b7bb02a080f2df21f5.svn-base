package cn.cuco.service.car.carOperate;


import cn.cuco.entity.CarAccident;
import cn.cuco.entity.OperateLog;
import cn.cuco.page.PageResult;

/**
* @ClassName: CarAccidentService 
* @Description: 车辆出险接口
* @author huanghua
* @date 2017年2月21日 下午3:13:12
 */
public interface CarAccidentService {
	
    /**
    * @Title: createSelective 
    * @Description: 创建出险-待处理
    * @param @param carAccident
    * @param @return
    * @return CarAccident
    * @throws
     */
    public CarAccident createCarAccident(CarAccident carAccident);

    /**
    * @Title: updateCarAccidentByFollowUp 
    * @Description: 修改出险状态为-跟进中
    * @param @param carAccident
    * @param @return
    * @return CarAccident
    * @throws
     */
    public CarAccident updateCarAccidentByFollowUp(CarAccident carAccident);
    
    /**
    * @Title: updateCarAccidentByRepairCompleted 
    * @Description:修改出险状态为-维修完成
    * @param @param carAccident
    * @param @return
    * @return CarAccident
    * @throws
     */
    public CarAccident updateCarAccidentByRepairCompleted(CarAccident carAccident);
    
    /**
    * @Title: updateCarAccidentBycompleted 
    * @Description: 修改出险状态为-已完成
    * @param @param carAccident
    * @param @return
    * @return CarAccident
    * @throws
     */
    public CarAccident updateCarAccidentByCompleted(CarAccident carAccident);
    
    /**
    * @Title: getCarAccidentById 
    * @Description: 查询出险信息详情
    * @author huanghua
    * @param id
    * @return
    * @return CarAccident
     */
    public CarAccident getCarAccidentById(Long id);

    /**
    * @Title: getCarAccidentByCondition 
    * @Description: 分页查询出险信息
    * @param @param carAccident
    * @param @return
    * @return List<CarAccident>
    * @throws
     */
    public PageResult<CarAccident> getCarAccidentByPage(CarAccident carAccident);
    
    /**
    * @Title: getCarAccidentLogByPage 
    * @Description: 
    * @author huanghua
    * @param carAccident
    * @return
    * @return PageResult<OperateLog>
     */
    public PageResult<OperateLog> getCarAccidentLogByPage(OperateLog operateLog);
    
    /**
    * @Title: createCarAccidentRemark 
    * @Description: 创建备注
    * @author huanghua
    * @param CarAccident
    * @return
    * @return CarAccident
     */
    public CarAccident createCarAccidentRemark(CarAccident CarAccident);
}
