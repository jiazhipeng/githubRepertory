package cn.cuco.controller.entity;

import cn.cuco.entity.CarAccident;

import java.math.BigDecimal;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月27 14:52:00
 */
public class CarAccidentStatusVO {

    private Long id;

    private String remark;

    private Integer status;

    private BigDecimal paymentAmount;


    public CarAccident toCarAccident(){
        CarAccident carAccident = new CarAccident();
        carAccident.setId(this.getId());
        carAccident.setRemark(this.getRemark());
        carAccident.setPaymentAmount(this.getPaymentAmount());

        return carAccident;
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

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
