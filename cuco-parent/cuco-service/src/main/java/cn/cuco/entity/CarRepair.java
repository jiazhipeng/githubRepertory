package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-25 11:56:15
 */
public class CarRepair extends PageQuery implements Serializable {

    private static final long serialVersionUID = 5898333378698132L;

    private Long id;//车辆维修ID

    private Long carId;//维修车辆ID

    private String carPlateNum;//维修车辆号

    private String carBrand;//维修车辆品牌

    private String carType;//维修的车型

    private Long carSupplierId;//车辆供应商ID

    private String repairReason;//维修原因（）

    private Date repairTimeStart;//计划维修时间

    private Date repairTimeEnd;//维修完成时间

    private Integer status;//维修状态（0：待维修；1：维修中；2：维修完成）

    private String repairCompany;//维修厂商名称

    private String repairPlace;//维修地点

    private BigDecimal repairPrice;//维修金额

    private Date created;//创建时间

    private String creater;//创建人
    
    private String remark;//备注

    private String modifier;//操作人姓名

    private Long modifierId;//操作人ID
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return this.carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarPlateNum() {
        return this.carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Long getCarSupplierId() {
        return this.carSupplierId;
    }

    public void setCarSupplierId(Long carSupplierId) {
        this.carSupplierId = carSupplierId;
    }

    public String getRepairReason() {
        return this.repairReason;
    }

    public void setRepairReason(String repairReason) {
        this.repairReason = repairReason;
    }

    public Date getRepairTimeStart() {
        return this.repairTimeStart;
    }

    public void setRepairTimeStart(Date repairTimeStart) {
        this.repairTimeStart = repairTimeStart;
    }

    public Date getRepairTimeEnd() {
        return this.repairTimeEnd;
    }

    public void setRepairTimeEnd(Date repairTimeEnd) {
        this.repairTimeEnd = repairTimeEnd;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRepairCompany() {
        return this.repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
    }

    public String getRepairPlace() {
        return this.repairPlace;
    }

    public void setRepairPlace(String repairPlace) {
        this.repairPlace = repairPlace;
    }

    public BigDecimal getRepairPrice() {
        return this.repairPrice;
    }

    public void setRepairPrice(BigDecimal repairPrice) {
        this.repairPrice = repairPrice;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
    
}
