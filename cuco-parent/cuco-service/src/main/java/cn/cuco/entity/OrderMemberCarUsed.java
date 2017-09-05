package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-03-07 15:55:44
 */
public class OrderMemberCarUsed extends PageQuery<OrderMemberCarUsed> implements Serializable {

    private static final long serialVersionUID = 7262853943390914L;

    private Long id;//主键ID

    private Long memberId;//用车发起人ID

    private String memberName;//用户姓名

    private String memberMobile;//用户手机号

    private Integer type;//用车类型: 0:非会员用车;1:会员用车;默认0

    private Long carportId;//车库ID

    private Long orderCartypeId;//预约车型ID

    private Date orderCarUsedTime;//预约用车时间

    private Integer orderCarUsedDay;//预约用车天数

    private String orderCarUsedAddress;//预约用车地址

    private Date orderCarReturnTime;//预约还车时间

    private String orderCarReturnAddress;//预约还车地点

    private Long actualCartypeId;//实际使用车型ID

    private Date actualCarUsedTime;//实际用车时间

    private Integer actualCarUsedDay;//实际用车天数

    private String actualCarUsedAddress;//实际用车地点

    private Date actualCarReturnTime;//实际还车时间

    private String actualCarReturnAddress;//实际还车地点

    private Long caroperateId;//运营车辆ID

    private Integer changeStatus;//是否临时换车：0：无换车；1：有换车

    private Integer orderStatus;//订单状态0：待支付 1:已完成; 2:已取消; 

    private Integer usedStatus;//用车的状态0：待送车 1:使用中; 2:已完成; 3:已取消 4:已失联

    private BigDecimal mileage;//行驶里程

    private BigDecimal carUsedCost;//用车费用

    private BigDecimal actualPayment;//实付金额

    private BigDecimal lastBalance;//上次结余

    private BigDecimal thisBalance;//本次结余

    private BigDecimal tankVolume;//油箱体积（L）

    private BigDecimal tankBefore;//用车前油量

    private BigDecimal tankAfter;//用车后油量

    private Integer unit;//单位：0：L；1：百分比

    private BigDecimal extraCarUsedCost;//额外用车费用(超时用车)
    
    private BigDecimal limitedBack;//限号补回

    private BigDecimal perGasolinePrice;//燃油价格(元/L)

    private BigDecimal perInsurancePrice;//保险费用(元/天)

    private BigDecimal perBasicPrice;//基础费用(元/天)

    private String cityCode;//用车城市code

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间
    
    private Date paymentConfirmTime;//确认支付时间
    
    private Date startDate;//开始用车时间
    
    private Date endDate;//结束用车时间
    
    private String longitudeLatitude_useCarAddress;//用车地址经纬度
    
    private String longitudeLatitude_returnCarAddress;//还车地址经纬度
    
    private String modifer;//操作人

    private Long modifierId;//操作人ID
    
    private String remark;//备注
    
    private String carPlateNum;
    
    
    
    public String getCarPlateNum() {
		return carPlateNum;
	}

	public void setCarPlateNum(String carPlateNum) {
		this.carPlateNum = carPlateNum;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Long carportId) {
        this.carportId = carportId;
    }

    public Long getOrderCartypeId() {
        return this.orderCartypeId;
    }

    public void setOrderCartypeId(Long orderCartypeId) {
        this.orderCartypeId = orderCartypeId;
    }

    public Date getOrderCarUsedTime() {
        return this.orderCarUsedTime;
    }

    public void setOrderCarUsedTime(Date orderCarUsedTime) {
        this.orderCarUsedTime = orderCarUsedTime;
    }

    public Integer getOrderCarUsedDay() {
        return this.orderCarUsedDay;
    }

    public void setOrderCarUsedDay(Integer orderCarUsedDay) {
        this.orderCarUsedDay = orderCarUsedDay;
    }

    public String getOrderCarUsedAddress() {
        return this.orderCarUsedAddress;
    }

    public void setOrderCarUsedAddress(String orderCarUsedAddress) {
        this.orderCarUsedAddress = orderCarUsedAddress;
    }

    public Date getOrderCarReturnTime() {
        return this.orderCarReturnTime;
    }

    public void setOrderCarReturnTime(Date orderCarReturnTime) {
        this.orderCarReturnTime = orderCarReturnTime;
    }

    public String getOrderCarReturnAddress() {
        return this.orderCarReturnAddress;
    }

    public void setOrderCarReturnAddress(String orderCarReturnAddress) {
        this.orderCarReturnAddress = orderCarReturnAddress;
    }

    public Long getActualCartypeId() {
        return this.actualCartypeId;
    }

    public void setActualCartypeId(Long actualCartypeId) {
        this.actualCartypeId = actualCartypeId;
    }

    public Date getActualCarUsedTime() {
        return this.actualCarUsedTime;
    }

    public void setActualCarUsedTime(Date actualCarUsedTime) {
        this.actualCarUsedTime = actualCarUsedTime;
    }

    public Integer getActualCarUsedDay() {
        return this.actualCarUsedDay;
    }

    public void setActualCarUsedDay(Integer actualCarUsedDay) {
        this.actualCarUsedDay = actualCarUsedDay;
    }

    public String getActualCarUsedAddress() {
        return this.actualCarUsedAddress;
    }

    public void setActualCarUsedAddress(String actualCarUsedAddress) {
        this.actualCarUsedAddress = actualCarUsedAddress;
    }

    public Date getActualCarReturnTime() {
        return this.actualCarReturnTime;
    }

    public void setActualCarReturnTime(Date actualCarReturnTime) {
        this.actualCarReturnTime = actualCarReturnTime;
    }

    public String getActualCarReturnAddress() {
        return this.actualCarReturnAddress;
    }

    public void setActualCarReturnAddress(String actualCarReturnAddress) {
        this.actualCarReturnAddress = actualCarReturnAddress;
    }

    public Long getCaroperateId() {
        return this.caroperateId;
    }

    public void setCaroperateId(Long caroperateId) {
        this.caroperateId = caroperateId;
    }

    public Integer getChangeStatus() {
        return this.changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUsedStatus() {
        return this.usedStatus;
    }

    public void setUsedStatus(Integer usedStatus) {
        this.usedStatus = usedStatus;
    }

    public BigDecimal getMileage() {
        return this.mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getCarUsedCost() {
        return this.carUsedCost;
    }

    public void setCarUsedCost(BigDecimal carUsedCost) {
        this.carUsedCost = carUsedCost;
    }

    public BigDecimal getActualPayment() {
        return this.actualPayment;
    }

    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public BigDecimal getLastBalance() {
        return this.lastBalance;
    }

    public void setLastBalance(BigDecimal lastBalance) {
        this.lastBalance = lastBalance;
    }

    public BigDecimal getThisBalance() {
        return this.thisBalance;
    }

    public void setThisBalance(BigDecimal thisBalance) {
        this.thisBalance = thisBalance;
    }

    public BigDecimal getTankVolume() {
        return this.tankVolume;
    }

    public void setTankVolume(BigDecimal tankVolume) {
        this.tankVolume = tankVolume;
    }

    public BigDecimal getTankBefore() {
        return this.tankBefore;
    }

    public void setTankBefore(BigDecimal tankBefore) {
        this.tankBefore = tankBefore;
    }

    public BigDecimal getTankAfter() {
        return this.tankAfter;
    }

    public void setTankAfter(BigDecimal tankAfter) {
        this.tankAfter = tankAfter;
    }

    public Integer getUnit() {
        return this.unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public BigDecimal getExtraCarUsedCost() {
        return this.extraCarUsedCost;
    }

    public void setExtraCarUsedCost(BigDecimal extraCarUsedCost) {
        this.extraCarUsedCost = extraCarUsedCost;
    }

    public BigDecimal getPerGasolinePrice() {
        return this.perGasolinePrice;
    }

    public void setPerGasolinePrice(BigDecimal perGasolinePrice) {
        this.perGasolinePrice = perGasolinePrice;
    }

    public BigDecimal getPerInsurancePrice() {
        return this.perInsurancePrice;
    }

    public void setPerInsurancePrice(BigDecimal perInsurancePrice) {
        this.perInsurancePrice = perInsurancePrice;
    }

    public BigDecimal getPerBasicPrice() {
        return this.perBasicPrice;
    }

    public void setPerBasicPrice(BigDecimal perBasicPrice) {
        this.perBasicPrice = perBasicPrice;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLongitudeLatitude_useCarAddress() {
		return longitudeLatitude_useCarAddress;
	}

	public void setLongitudeLatitude_useCarAddress(String longitudeLatitude_useCarAddress) {
		this.longitudeLatitude_useCarAddress = longitudeLatitude_useCarAddress;
	}

	public String getLongitudeLatitude_returnCarAddress() {
		return longitudeLatitude_returnCarAddress;
	}

	public void setLongitudeLatitude_returnCarAddress(String longitudeLatitude_returnCarAddress) {
		this.longitudeLatitude_returnCarAddress = longitudeLatitude_returnCarAddress;
	}

	public BigDecimal getLimitedBack() {
		return limitedBack;
	}

	public void setLimitedBack(BigDecimal limitedBack) {
		this.limitedBack = limitedBack;
	}

	public Date getPaymentConfirmTime() {
		return paymentConfirmTime;
	}

	public void setPaymentConfirmTime(Date paymentConfirmTime) {
		this.paymentConfirmTime = paymentConfirmTime;
	}

	public String getModifer() {
		return modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
