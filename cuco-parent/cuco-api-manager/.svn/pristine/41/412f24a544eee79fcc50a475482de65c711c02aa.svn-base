package cn.cuco.controller.manager.car.insurance;

import cn.cuco.entity.OperateLog;
import cn.cuco.service.car.carInfo.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.cuco.annotation.API;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月24 17:57:00
 */
@RestController
public class CarInsuranceController {

    @Autowired
    private CarService carService;

    @API(value = "车辆-保险-详情")
    @RequestMapping(value = "/car/getCarInsuranceDetailById",method = RequestMethod.GET)
    private Object getCarInsuranceDetailById(@RequestParam Long carId){

        return carService.getCarInsuranceDetailByCarId(carId);
    }

    @API(value = "车辆-保险-操作日志-分页")
    @RequestMapping(value = "/carInsurance/getCarInsuranceLogListByPage",method = RequestMethod.POST)
    private Object getCarInsuranceLogListByPage(@RequestBody OperateLog operateLog){

        return carService.getOperationLogList(operateLog);
    }
}
