package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-24 09:57:49
 */
public class ButlerTask extends PageQuery implements Serializable {

    private static final long serialVersionUID = 8146613629283947L;

    private Long id;//主键

    private String taskNum;//管家任务编号(任务类型编码＋年月日＋4位随机数)(加唯一索引，代码中cach异常，捕获后重新调用生成编号，再插入)

    private Integer type;//任务类型 1:充电任务；2:取车任务；3:送车任务；

    private Integer createType;//任务发起人 0:用户；1:管家；（默认0）

    private Integer status;//任务状态 1：待处理 2：待接单；3:待送车；4:送车中；5:待取车；6:取车中；7:已到达；8:已完成；9:已取消；

    private Long carUsedId;//用车记录主表ID

    private String taskAddress;//任务地址

    private Date planTime;//计划送车时间

    private Date completeTime;//完成时间

    private Long memberId;//会员id

    private String memberName;//会员姓名

    private String memberMobile;//会员手机号

    private Long operaterId;//执行管家id

    private String operaterName;//执行管家姓名
    
    private String operaterMobile;//执行管家手机号

    private Long carOperateId;//运营车辆ID

    private Long customerId;//客服负责人id

    private String customerName;//客服负责人姓名

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private String modifier;//操作人

    private Long modifierId;//操作人Id

    private Integer isChange;//有无更改送车/取车任务时间&地址;无：0;有：1

    private Integer messageStatus;//消息发送状态 0:未发送； 1:发送成功；

    private Long oldTaskId;//换车之前任务ID

    private Integer useCarType;//任务类型 1:用户用车；2:用户换车；3:限号换车；

    private String remark;//任务取消原因描述

    private Date planTimeStart;//预计开始时间
    
    private Date planTimeEnd;//预计结束时间
    
    private Date createdStart;//创建开始时间
    
    private Date createdEnd;//创建结束时间
    
    private BigDecimal cashDeposit;//押金扣款
    
    private List<TransferList> receipeImages; //交接单集合
    
    private List<TransferList> carImages;//车辆照片集合
    
    private Date completeTimeStart;//完成开始时间
    
    private Date completeTimeEnd;//完成结束时间
    
    private String brand;
    private String carType;
    private Long carTypeId;//车型ID
    private String carPlateNum;
    private Integer orderCarUsedDay;//用车天数
    private String parkingName;//停车场名称    
    private BigDecimal deposit;//押金
    private Long preAuthorizedDebitId;//预授权押金ID
    private Date planGetTime;//计划取车时间
    private Integer role;//1管家任务 2管家负责人 3车务
    
    
    public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	private Car car;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskNum() {
        return this.taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateType() {
        return this.createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCarUsedId() {
        return this.carUsedId;
    }

    public void setCarUsedId(Long carUsedId) {
        this.carUsedId = carUsedId;
    }

    public String getTaskAddress() {
        return this.taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress;
    }

    public Date getPlanTime() {
        return this.planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public Date getCompleteTime() {
        return this.completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
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

    public Long getOperaterId() {
        return this.operaterId;
    }

    public void setOperaterId(Long operaterId) {
        this.operaterId = operaterId;
    }

    public String getOperaterName() {
        return this.operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public Long getCarOperateId() {
        return this.carOperateId;
    }

    public void setCarOperateId(Long carOperateId) {
        this.carOperateId = carOperateId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getIsChange() {
        return this.isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public Integer getMessageStatus() {
        return this.messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Long getOldTaskId() {
        return this.oldTaskId;
    }

    public void setOldTaskId(Long oldTaskId) {
        this.oldTaskId = oldTaskId;
    }

    public Integer getUseCarType() {
        return this.useCarType;
    }

    public void setUseCarType(Integer useCarType) {
        this.useCarType = useCarType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Date getPlanTimeStart() {
		return planTimeStart;
	}

	public void setPlanTimeStart(Date planTimeStart) {
		this.planTimeStart = planTimeStart;
	}

	public Date getPlanTimeEnd() {
		return planTimeEnd;
	}

	public void setPlanTimeEnd(Date planTimeEnd) {
		this.planTimeEnd = planTimeEnd;
	}

	public Date getCreatedStart() {
		return createdStart;
	}

	public void setCreatedStart(Date createdStart) {
		this.createdStart = createdStart;
	}

	public Date getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}

	public List<TransferList> getReceipeImages() {
		return receipeImages;
	}

	public void setReceipeImages(List<TransferList> receipeImages) {
		this.receipeImages = receipeImages;
	}

	public List<TransferList> getCarImages() {
		return carImages;
	}

	public void setCarImages(List<TransferList> carImages) {
		this.carImages = carImages;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarPlateNum() {
		return carPlateNum;
	}

	public void setCarPlateNum(String carPlateNum) {
		this.carPlateNum = carPlateNum;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Integer getOrderCarUsedDay() {
		return orderCarUsedDay;
	}

	public void setOrderCarUsedDay(Integer orderCarUsedDay) {
		this.orderCarUsedDay = orderCarUsedDay;
	}

	public BigDecimal getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(BigDecimal cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public String getParkingName() {
		if(StringUtils.isBlank(parkingName)){
			return "爱琴海停车场";
		}
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Long getPreAuthorizedDebitId() {
		return preAuthorizedDebitId;
	}

	public void setPreAuthorizedDebitId(Long preAuthorizedDebitId) {
		this.preAuthorizedDebitId = preAuthorizedDebitId;
	}

	public Date getCompleteTimeStart() {
		return completeTimeStart;
	}

	public void setCompleteTimeStart(Date completeTimeStart) {
		this.completeTimeStart = completeTimeStart;
	}

	public Date getCompleteTimeEnd() {
		return completeTimeEnd;
	}

	public void setCompleteTimeEnd(Date completeTimeEnd) {
		this.completeTimeEnd = completeTimeEnd;
	}

	public Date getPlanGetTime() {
		return planGetTime;
	}

	public void setPlanGetTime(Date planGetTime) {
		this.planGetTime = planGetTime;
	}

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getOperaterMobile() {
		return operaterMobile;
	}

	public void setOperaterMobile(String operaterMobile) {
		this.operaterMobile = operaterMobile;
	}
	
}
