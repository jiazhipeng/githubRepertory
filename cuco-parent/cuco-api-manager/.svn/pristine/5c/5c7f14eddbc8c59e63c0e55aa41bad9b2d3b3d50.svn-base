package cn.cuco.controller.entity;

import java.math.BigDecimal;

import cn.cuco.entity.CarAccident;
import cn.cuco.entity.CarRepair;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月27 14:52:00
 */
public class CarRepairStatusVO {

    private Long id;

    private String remark;

    private Integer status;


    public CarRepair toCarRepair(){
        CarRepair carRepair = new CarRepair();
        carRepair.setId(this.getId());
        carRepair.setRemark(this.getRemark());

        return carRepair;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
