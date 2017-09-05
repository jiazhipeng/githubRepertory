package cn.cuco.controller.manager.car.repair;

import cn.cuco.common.httpservice.HttpServiceContext;
import cn.cuco.common.utils.PrePersistUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.controller.entity.CarRepairStatusVO;
import cn.cuco.controller.entity.CarRepairVO;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarRepair;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.Supplier;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.business.SupplierService;
import cn.cuco.service.car.carInfo.CarService;
import cn.cuco.service.car.carOperate.CarRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.cuco.annotation.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月24 18:20:00
 */
@RestController
public class CarRepairController {

    @Autowired
    private CarRepairService carRepairService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CarService carService;

    @API(value = "维修-分页")
    @RequestMapping(value = "/carRepair/getCarRepairListByPage",method = RequestMethod.POST)
    private Object getCarRepairListByPage(@RequestBody CarRepair carRepair){

        PageResult<CarRepair> pageResult =  carRepairService.getCarRepairByCondition(carRepair);
        PageResult<CarRepairVO> repairVOPageResult = new PageResult();

        repairVOPageResult.setPage(pageResult.getPage());
        repairVOPageResult.setPageSize(pageResult.getPageSize());
        repairVOPageResult.setTotalPage(pageResult.getTotalPage());
        repairVOPageResult.setTotalSize(pageResult.getTotalSize());
        List<CarRepairVO> carRepairVOs = new ArrayList();
        repairVOPageResult.setItems(carRepairVOs);

        List<CarRepair> carRepairList = pageResult.getItems();

        for(CarRepair tempCarRepair : carRepairList){
            carRepairVOs.add(this.getCarRepairVO(tempCarRepair.getId()));
        }

        return repairVOPageResult;
    }

    @API(value = "维修-创建")
    @RequestMapping(value = "/carRepair/createCarRepair",method = RequestMethod.POST)
    private Object createCarRepair(@RequestBody CarRepair carRepair){
        PrePersistUtils.onCreate(carRepair,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());

        Car car = carService.getCarByCarPlateNum(carRepair.getCarPlateNum());
        ParamVerifyUtils.paramNotNull(car,"车辆不存在");
        carRepair.setCarId(car.getId());

        return carRepairService.createCarRepair(carRepair);
    }

    @API(value = "维修-操作日志-分页")
    @RequestMapping(value = "/carRepair/getCarRepairLogListByPage",method = RequestMethod.POST)
    private Object getCarRepairLogListByPage(@RequestBody OperateLog operateLog){
        PrePersistUtils.onCreate(operateLog,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
        return carRepairService.getCarRepairLogByPage(operateLog);
    }

    @API(value = "维修-状态修改")
    @RequestMapping(value = "/carRepair/updateStatus",method = RequestMethod.POST)
    private Object updateStatus(@RequestBody CarRepairStatusVO carRepairStatusVO){
        Integer status = carRepairStatusVO.getStatus();
        if(status!=null && !Arrays.asList(1,2).contains(status)){
            ExceptionUtil.throwWarn("状态无效");
        }

        /*bean convert*/
        CarRepair carRepair = carRepairStatusVO.toCarRepair();

        PrePersistUtils.onModify(carRepair, HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());

        /*handle by status*/
        switch (status){
            case 1 : return carRepairService.updateCarRepairByMaintenance(carRepair);
            case 2 : return carRepairService.updateCarRepairBycompleted(carRepair);
        }

        if(status==null){
            return carRepairService.createCarRepairRemark(carRepair);
        }

        throw new RuntimeExceptionWarn("维修状态修改失败");
    }

    @API(value = "维修-详情")
    @RequestMapping(value = "/carRepair/getCarRepairById",method = RequestMethod.GET)
    private Object getCarRepairById(@RequestParam Long id){

        return this.getCarRepairVO(id);
    }

    /**
     * 根据维修ID获取含供应商信息的车辆出险详情
     * @param id
     * @return
     */
    private CarRepairVO getCarRepairVO(Long id){
        CarRepair carRepair = carRepairService.getCarRepairById(id);
        ParamVerifyUtils.paramNotNull(carRepair,"获取车辆维修详情失败：车辆维修信息不存在");
        CarRepairVO carRepairVO = new CarRepairVO(carRepair);


        /*get supplier*/
        Long supplierId = carRepairVO.getCarSupplierId();
        Supplier supplier = supplierService.getSupplierById(supplierId);

        carRepairVO.setSupplier(supplier);

        return carRepairVO;
    }
}
