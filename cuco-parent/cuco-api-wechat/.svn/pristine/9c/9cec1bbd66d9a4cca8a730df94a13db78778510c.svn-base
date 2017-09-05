package cn.cuco.controller.entity;

import cn.cuco.common.utils.BeanUtils;
import cn.cuco.entity.CarAccident;
import cn.cuco.entity.Supplier;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月27 15:28:00
 */
public class CarAccidentVO extends CarAccident {

    public CarAccidentVO() {
    }

    public CarAccidentVO(CarAccident carAccident) {
        BeanUtils.copyProperties(carAccident,this);
    }

    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
