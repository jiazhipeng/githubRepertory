package cn.cuco.controller.manager.car.violation;

import cn.cuco.common.httpservice.HttpServiceContext;
import cn.cuco.common.utils.PrePersistUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarViolation;
import cn.cuco.entity.OperateLog;
import cn.cuco.service.car.carInfo.CarService;
import cn.cuco.service.car.carOperate.CarViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.cuco.annotation.API;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @ClassName:
 * @Description：
 * @author：WS
 * @date：2017年03月08 17:20:00
 */
@RestController
public class CarViolationController {

    @Autowired
    private CarViolationService carViolationService;
    @Autowired
    private CarService carService;


    @API(value = "违章-详情")
    @RequestMapping(value = "/carViolation/getCarViolationById",method = RequestMethod.GET)
    private Object getCarViolationById(Long id){
        return carViolationService.getCarViolationById(id);
    }

    @API(value = "违章-创建")
    @RequestMapping(value = "/carViolation/createCarViolation",method = RequestMethod.POST)
    private Object createCarViolation(@RequestBody CarViolation carViolation){
        PrePersistUtils.onCreate(carViolation, HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());

        Car car = carService.getCarByCarPlateNum(carViolation.getCarPlateNum());
        ParamVerifyUtils.paramNotNull(car,"车辆不存在");
        carViolation.setCarId(car.getId());

        return carViolationService.createCarViolation(carViolation);
    }

    @API(value = "违章-修改")
    @RequestMapping(value = "/carViolation/updateCarViolation",method = RequestMethod.POST)
    private Object updateCarViolation(@RequestBody CarViolation carViolation){
        PrePersistUtils.onModify(carViolation,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());

        return carViolationService.updateCarViolation2processed(carViolation);
    }

    @API(value = "违章-分页")
    @RequestMapping(value = "/carViolation/getCarViolationListByPage",method = RequestMethod.POST)
    private Object getCarViolationListByPage(@RequestBody CarViolation carViolation){

        return carViolationService.getCarViolationByPage(carViolation);
    }

    @API(value = "违章-操作日志-分页")
    @RequestMapping(value = "/carViolation/getCarViolationLogListByPage",method = RequestMethod.POST)
    private Object getCarViolationLogListByPage(@RequestBody OperateLog operateLog){

        return carViolationService.getCarViolationLogByPage(operateLog);
    }
}
