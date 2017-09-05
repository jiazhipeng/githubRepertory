package cn.cuco.controller.butler.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.authorization.entity.User;

import cn.cuco.common.utils.PrePersistUtils;

import cn.cuco.annotation.API;
import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.ButlerTask;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarType;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.TransferList;
import cn.cuco.enums.ServerStatus;
import cn.cuco.enums.security.RoleEnum;
import cn.cuco.httpservice.HttpServiceContext;
import cn.cuco.httpservice.ResponseUtil;
import cn.cuco.service.basic.carport.CarTypeService;
import cn.cuco.service.car.carInfo.CarService;
import cn.cuco.service.payment.preAuthorizedDebit.PreAuthorizedDebitService;
import cn.cuco.service.security.SecurityService;
import cn.cuco.service.task.ButlerTaskService;

/**
 * 
 * @ClassName: ButlerTaskController 
 * @Description: 任务
 * @author: wangchuntao 
 * @date: 2017年2月22日 下午2:05:27
 */
@RestController
public class ButlerTaskController {
	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private ButlerTaskService butlerTaskService;
    @Autowired
    private PreAuthorizedDebitService authorizedDebitService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarService carService;
    
    /**
     * @Title: getButlerTaskListByPage 
     * @Description: 任务列表
     * @author: wangchuntao 
     * @param butlerTask
     * @param code
     * @return Object
     * @date: 2017年3月8日 下午1:54:30
     */
    @API(value="任务列表")
    @RequestMapping(value = "/butlerTask/getButlerTaskListByPage",method = RequestMethod.POST)
    public Object getButlerTaskListByPage(@RequestBody ButlerTask butlerTask){
    	
    	ParamVerifyUtils.paramNotNull(butlerTask);
    	ParamVerifyUtils.paramNotNull(butlerTask.getCreated());
    	ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId());
    	ParamVerifyUtils.paramNotNull(butlerTask.getRole());
    	butlerTask.setCreatedStart(DateUtils.getDayMiniDate(butlerTask.getCreated()));
    	butlerTask.setCreatedEnd(DateUtils.getDayMaxDate(butlerTask.getCreated()));
    	if(butlerTask.getRole()==1){
    		 return butlerTaskService.getButlerTaskPageForOfficialEnd(butlerTask);//管家任务
    	} else if(butlerTask.getRole()==2){//管家负责人
    		butlerTask.setOperaterId(null);
    		return butlerTaskService.getButlerTaskPageForOfficialEnd(butlerTask);
    	}else{
    		return butlerTaskService.getButlerTaskPageForCarService(butlerTask);
    	}
    }
    
    /**
     * @Title: findButlerTaskDetail 
     * @Description: 任务详细
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年2月22日 下午2:39:48
     */
    @API(value="任务详情")
    @RequestMapping(value = "/butlerTask/getButlerTaskDetail",method = RequestMethod.GET)
    public Object getButlerTaskDetail(Long id){
    	ParamVerifyUtils.paramNotNull(id);
    	
//    	Map<String, Object> returnMap = new HashMap<>();
    	ButlerTask butlerTask = butlerTaskService.getButlerTaskById(id);
    	
		return butlerTask;
    }
    /**
     * @Title: getButlerTaskLogByPage 
     * @Description: 任务详情日志
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年3月8日 下午2:58:12
     */
    @API(value="任务详情日志")
    @RequestMapping(value = "/butlerTask/getButlerTaskLogByPage",method = RequestMethod.GET)
    public Object getButlerTaskLogByPage(Long id){
    	ParamVerifyUtils.paramNotNull(id);
    	
    	OperateLog operateLog = new OperateLog();
		operateLog.setOperationId(id);
    	return butlerTaskService.getButlerTaskLogByPage(operateLog);
    	
    }
    /**
     * @Title: updateButlerTaskByCancelled 
     * @Description: 取消任务
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年3月8日 下午2:58:29
     */
    @API(value="取消任务")
    @RequestMapping(value = "/butlerTask/updateButlerTaskByCancelled",method = RequestMethod.POST)
    public Object updateButlerTaskByCancelled(@RequestBody ButlerTask butlerTask){
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return butlerTaskService.updateButlerTaskByCancelled(butlerTask );
    	
    }
    /**
     * @Title: updateButlerTaskByFollowUp 
     * @Description: 申请更换管家
     * @author: wangchuntao 
     * @param id
     * @return 
     * @return Object
     * @date: 2017年3月8日 下午3:00:05
     */
    @API(value="申请更换管家")
    @RequestMapping(value = "/butlerTask/updateButlerChangeButler",method = RequestMethod.POST)
    public Object updateButlerChangeButler(@RequestBody ButlerTask butlerTask){
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateButlerTaskByFollowUp(butlerTask);
    	
    }
    /**
     * @Title: updateButlerAddLog 
     * @Description: 添加备注
     * @author: wangchuntao 
     * @param butlerTask
     * @return Object
     * @date: 2017年3月8日 下午3:02:06
     */
    @API(value="添加备注")
    @RequestMapping(value = "/butlerTask/updateButlerAddLog",method = RequestMethod.POST)
    public Object updateButlerAddLog(@RequestBody ButlerTask butlerTask){
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateButlerTaskByFollowUp(butlerTask);
    	
    }
    /**
     * @Title: updateSendButlerTaskBySendIn 
     * @Description: 出发送车
     * @author: wangchuntao 
     * @param butlerTask
     * @return Object
     * @date: 2017年3月8日 下午3:09:22
     */
    @API(value="出发送车")
    @RequestMapping(value = "/butlerTask/updateSendButlerTaskBySendIn",method = RequestMethod.GET)
    public Object updateSendButlerTaskBySendIn(@RequestBody ButlerTask butlerTask){
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateSendButlerTaskBySendIn(butlerTask);
    	
    }
    
    /**
     * @Title: updateSendButlerTaskByArrive 
     * @Description: 已到达
     * @author: wangchuntao 
     * @param butlerTask
     * @return Object
     * @date: 2017年3月8日 下午3:12:23
     */
    @API(value="送车任务已到达")
    @RequestMapping(value = "/butlerTask/updateSendButlerTaskBySendIn",method = RequestMethod.GET)
    public Object updateSendButlerTaskByArrive(@RequestBody ButlerTask butlerTask){
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateSendButlerTaskByArrive(butlerTask);
    	
    }
    
    /**
     * @Title: updateSendButlerTaskByCompleted 
     * @Description: 送车任务已完成
     * @author: wangchuntao 
     * @param butlerTask
     * @return Object
     * @date: 2017年3月8日 下午3:19:29
     */
    @API(value="送车任务交接完成")
    @RequestMapping(value = "/butlerTask/updateSendButlerTaskByCompleted",method = RequestMethod.GET)
    public Object updateSendButlerTaskByCompleted(@RequestBody ButlerTask butlerTask){
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateSendButlerTaskByCompleted(butlerTask);
    	
    }
    /**
     * @Title: uploadImage 
     * @Description: 送车任务上传交接单和押金凭证
     * @author: wangchuntao 
     * @param transferList
     * @return Object
     * @date: 2017年3月8日 下午3:32:01
     */
    @API(value="送车任务上传交接单和押金凭证")
    @RequestMapping(value = "/butlerTask/uploadTransferList",method = RequestMethod.GET)
    public Object uploadImage(@RequestBody TransferList transferList){
//    	TRANSDERTYPE_RECEIPT(0,"交接单照片"),
//    	TRANSDERTYPE_CAR(1,"车辆照片"),
//    	AUTHORIZED_USE_VOUCHER(2,"预授权用车押金凭证"),
//    	AUTHORIZED_VIOLATION_VOUCHER(3,"预授权违章押金凭证");
    	if(transferList.getType()==0){//交接单
    		return butlerTaskService.createButerTaskTransfer(transferList);
    	}else{//押金
    		return authorizedDebitService.createPreAuthorizedDebitTransfer(transferList);
    	}
    	
    }
    
    /**
     * @Title: updateButlerTaskByWait 
     * @Description: 任务接单
     * @author: wangchuntao 
     * @param butlerTask
     * @return 
     * @return Object
     * @date: 2017年3月8日 下午4:19:12
     */
    @API(value="任务接单")
    @RequestMapping(value = "/butlerTask/updateButlerTaskByWait",method = RequestMethod.POST)
    public Object updateButlerTaskByWait(@RequestBody ButlerTask butlerTask){
    	ParamVerifyUtils.paramNotNull(butlerTask);
    	ParamVerifyUtils.paramNotNull(butlerTask.getType());
    	ParamVerifyUtils.paramNotNull(butlerTask.getId());
//    	任务类型 1:充电任务；2:取车任务；3:送车任务；
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	if(butlerTask.getType()==2){
    		butlerTaskService.updateGetButlerTaskByTakeCar(butlerTask);
    	}else if(butlerTask.getType()==3){
    		return butlerTaskService.updateSendButlerTaskByTakeCar(butlerTask);
    	}
    	return null;
    }
    
    /**
     * @Title: updateButlerTaskByRefuse 
     * @Description: 任务拒绝
     * @author: wangchuntao 
     * @param butlerTask
     * @return 
     * @return Object
     * @date: 2017年3月8日 下午4:33:32
     */
    @API(value="任务拒绝")
    @RequestMapping(value = "/butlerTask/updateButlerTaskByRefuse",method = RequestMethod.POST)
    public Object updateButlerTaskByRefuse(@RequestBody ButlerTask butlerTask){
    	ParamVerifyUtils.paramNotNull(butlerTask);
    	ParamVerifyUtils.paramNotNull(butlerTask.getType());
    	ParamVerifyUtils.paramNotNull(butlerTask.getRemark());
    	ParamVerifyUtils.paramNotNull(butlerTask.getId());
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
//    	任务类型 1:充电任务；2:取车任务；3:送车任务；
    	if(butlerTask.getType()==2){
    		return butlerTaskService.updateGetButlerTaskByRefuse(butlerTask);
    	}else if(butlerTask.getType()==3){
    		return butlerTaskService.updateSendButlerTaskByRefuse(butlerTask);
    	}
    	return null;
    }
    /**
     * @Title: updateGetButlerTaskByGetIn 
     * @Description: 取车任务出发取车
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年3月8日 下午4:41:28
     */
    @API(value="取车任务出发取车")
    @RequestMapping(value = "/butlerTask/updateGetButlerTaskByGetIn",method = RequestMethod.GET)
    public Object updateGetButlerTaskByGetIn(Long id){
		ParamVerifyUtils.paramNotNull(id);
    	ButlerTask butlerTask = new ButlerTask();
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	butlerTask.setId(id);
    	return butlerTaskService.updateGetButlerTaskByGetIn(butlerTask);
    }
    
    /**
     * @Title: updateGetButlerTaskByArrive 
     * @Description: 取车任务已到达
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年3月8日 下午4:55:02
     */
    @API(value="取车任务已到达")
    @RequestMapping(value = "/butlerTask/updateGetButlerTaskByArrive",method = RequestMethod.GET)
    public Object updateGetButlerTaskByArrive(Long id){
    	ParamVerifyUtils.paramNotNull(id);
    	ButlerTask butlerTask = new ButlerTask();
    	butlerTask.setId(id);
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateGetButlerTaskByArrive(butlerTask);
    }
    
    /**
     * @Title: updateGetButlerTaskByStorage 
     * @Description: 取车任务交接完成
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年3月8日 下午4:57:05
     */
    @API(value="取车任务交接完成")
    @RequestMapping(value = "/butlerTask/updateGetButlerTaskByStorage",method = RequestMethod.POST)
    public Object updateGetButlerTaskByStorage(Long id){
    	ParamVerifyUtils.paramNotNull(id);
    	ButlerTask butlerTask = new ButlerTask();
    	butlerTask.setId(id);
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateGetButlerTaskByStorage(butlerTask);
    }
    /**
     * @Title: updateGetButlerTaskByOutOfContact 
     * @Description: 取车任务用户失联
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年3月8日 下午5:13:23
     */
    @API(value="取车任务用户失联")
    @RequestMapping(value = "/butlerTask/updateGetButlerTaskByOutOfContact",method = RequestMethod.POST)
    public Object updateGetButlerTaskByOutOfContact(Long id){
    	ParamVerifyUtils.paramNotNull(id);
    	ButlerTask butlerTask = new ButlerTask();
    	butlerTask.setId(id);
    	PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return butlerTaskService.updateGetButlerTaskByOutOfContact(butlerTask);
    }
    
  
    
   /**
    * @Title: getCustomerAndOperaterList 
    * @Description: 管家列表
    * @author: wangchuntao 
    * @return Object
    * @date: 2017年3月8日 下午4:46:45
    */
    @API("管家列表")
    @RequestMapping(value = "/butlerTask/getOperaterList",method = RequestMethod.GET)
    public Object getCustomerAndOperaterList(){
		List<User> usersByRole = securityService.getUsersByRole(RoleEnum.CUCO_GJ);
		return usersByRole;
	 }
    
    
    /**
     * @Title: getCartypeListByPage 
     * @Description: 车型列表
     * @author: wangchuntao 
     * @param carType
     * @return Object
     * @date: 2017年2月23日 上午9:36:03
     */
	@API(value="车型列表")
    @RequestMapping(value = "/cartype/getCartypeListByPage",method = RequestMethod.GET)
    public Object getCartypeListByPage(){
        return carTypeService.getCarTypeByPage(new CarType()).getItems();
    }
	
	/**
	 * @Title: getCartypeListByPage 
	 * @Description: 根据车型获取车辆列表
	 * @author: wangchuntao 
	 * @param carTypeId
	 * @return Object
	 * @date: 2017年3月9日 上午10:23:39
	 */
	@API(value="根据车型获取车辆列表")
	@RequestMapping(value = "/cartype/getCarListByCartype",method = RequestMethod.GET)
	public Object getCartypeListByPage(Long carTypeId){
		ParamVerifyUtils.paramNotNull(carTypeId);
		Car car = new Car();
		car.setCarTypeId(carTypeId);
		return carService.getCarListByCartype(car );
	}
    
	/**
	 * @Title: updateButlerTaskByDistributionCar 
	 * @Description: 提车确认
	 * @author: wangchuntao 
	 * @param butlerTask
	 * @return Object
	 * @date: 2017年3月9日 上午10:34:51
	 */
	@API(value="提车确认")
	@RequestMapping(value = "/butlerTask/updateButlerTaskByDistributionCar",method = RequestMethod.POST)
	public Object updateButlerTaskByDistributionCar(@RequestBody ButlerTask butlerTask){
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return butlerTaskService.updateButlerTaskByDistributionCar(butlerTask);
	}
	/**
	 * @Title: updateSendButlerTaskBySendCar 
	 * @Description: 车务扫码验证
	 * @author: wangchuntao 
	 * @param butlerTask
	 * @return Object
	 * @date: 2017年3月9日 下午1:51:52
	 */
	@API(value="车务扫码确认提车校验")
	@RequestMapping(value = "/butlerTask/updateButlerTaskVerification",method = RequestMethod.POST)
	public Object updateSendButlerTaskBySendCar(@RequestBody ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId());
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId());
		ButlerTask taskDB = butlerTaskService.getButlerTaskById(butlerTask.getId());
		if(taskDB==null||taskDB.getOperaterId()==null||butlerTask.getOperaterId().intValue()!=taskDB.getOperaterId().intValue()){
			return ResponseUtil.toFailureBody(ServerStatus.USER_DEFINITION, "提车凭证错误！");
		}
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		if(butlerTask.getType()==3){//送车
			butlerTaskService.updateSendButlerTaskBySendCar(butlerTask);
		}else if(butlerTask.getType()==2){//取车
			butlerTaskService.updateGetButlerTaskByInReadiness(butlerTask);
		}
		return ResponseUtil.toSuccessBody("提车成功！");
	}
	
	/**
	 * @Title: updateTaskHousekeeper 
	 * @Description: 转派管家
	 * @author: wangchuntao 
	 * @param butlerTask
	 * @return Object
	 * @date: 2017年3月9日 下午2:31:17
	 */
	@API(value="转派管家")
	@RequestMapping(value = "/butlerTask/updateTaskHousekeeper",method = RequestMethod.POST)
	public Object updateTaskHousekeeper(@RequestBody ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId());
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId());
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterName());
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return butlerTaskService.updateTaskHousekeeper(butlerTask);
	}
	
	

    /**
     * @Title: updateGetButlerTaskByCompleted 
     * @Description: 整备完成
     * @author: wangchuntao 
     * @param butlerTask
     * @return Object
     * @date: 2017年3月9日 下午2:54:07
     */
	@API(value="整备完成")
	@RequestMapping(value = "/butlerTask/updateGetButlerTaskByCompleted",method = RequestMethod.GET)
	public Object updateGetButlerTaskByCompleted(@RequestBody ButlerTask butlerTask){
		PrePersistUtils.onModify(butlerTask, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
		return butlerTaskService.updateGetButlerTaskByCompleted(butlerTask);
	}
    
    
    
    
    
    
}
