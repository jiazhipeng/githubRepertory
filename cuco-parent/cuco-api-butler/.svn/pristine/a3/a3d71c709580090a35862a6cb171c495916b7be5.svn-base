package cn.cuco.controller.entity;

import cn.cuco.common.utils.BeanUtils;
import cn.cuco.entity.CarRepair;
import cn.cuco.entity.Supplier;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月27 15:51:00
 */
public class CarRepairVO extends CarRepair {

    public CarRepairVO() {
    }

    public CarRepairVO(CarRepair carRepair) {
        BeanUtils.copyProperties(carRepair,this);
    }

    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
