package com.hy.gcar.entity;

import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-12 17:51:04
 */
public class CarOperatePlan implements Serializable {

    private static final long serialVersionUID = 5147494077346847L;

    private Long id;//

    private String operateNum;//运营车辆编号

    private String carPlateNum;//

    private Date created;//创建时间

    private Date started;//开始日期

    private Date ended;//结束日期

    private Long operateType;//平台运营类型名称(如:专车、婚庆、短租等)的id

    private BigDecimal money;//运营收益金额

    private Integer status;//运营状态 0:待执行；1:执行中；2:已完成；3:已取消；4:已终止；

    private String operateTo;//使用方

    private Long memberId;//会员id

    private String remark;//备注

    private String reason;//取消原因

    private String repairManufacturer;//修理厂商

    private Date failureTime;//故障时间

    private String modifierId;//操作人ID

    private String modifier;//操作人姓名
    
    private Date finishTime; //修理完成时间
    
    private BigDecimal maintenanceCosts; //维修费用
    
    private Integer handle; //处理方式1.用户继续使用2.车辆入库
    
    private String memberName; //会员姓名
    
    private String memberMobile;  //会员手机号
    
    private String carUsedAddress;  //用车地址
    
    private String expectedReturn;  //管家设置的预计收益

    private Long powerUsedId;//用车记录id

    private String statusInfo;//运营状态

    /**
     * 运营状态  --供查询使用
     */
    private String operateStatusInfo;
    
    private List<Integer> statusList;


    public Long getPowerUsedId() {
		return powerUsedId;
	}

	public void setPowerUsedId(Long powerUsedId) {
		this.powerUsedId = powerUsedId;
	}

	public String getStatusInfo() {
        if(this.status == null){
            return "";
        }
        return CarOperatePlanEnum.status.getName(this.status);
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getOperateStatusInfo() {
        if(this.operateStatus == null){
            return "";
        }
        return CarOperateEnum.status.getKey(this.operateStatus);
    }

    public void setOperateStatusInfo(String operateStatusInfo) {
        this.operateStatusInfo = operateStatusInfo;
    }

    /**
     * 车辆状态 -- 每个运营记录上都有一条对应的车辆状态
     */
    private Integer operateStatus; 
    
	/**
     * 开始日期  --供查询使用
     */
    private String begin;
    
    /**
     * 结束日期 --供查询使用
     */
    private String end;
    
    /**
     * 车辆 主键id
     */
    private Long operateId;
    
    /**
     * 开始时间 --String
     */
    private String startedStr;
    
    /**
     * 管家id
     */
    private String butlerId;
    
    /**
     * 管家姓名
     */
    private String butlerName;
    
    
    /**
     * 车型id
     */
    private Long carTypeId;
    
    /**
     * 是否有库存 1有库存2无库存
     */
    private Integer isStock;

    private Integer readyTime;//整备时间 臨時字段

    public Integer getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Integer readyTime) {
        this.readyTime = readyTime;
    }
    
    public Long getCarTypeId() {
  		return carTypeId;
  	}

  	public void setCarTypeId(Long carTypeId) {
  		this.carTypeId = carTypeId;
  	}
  	
  	 public Integer getIsStock() {
   		return isStock;
   	}

   	public void setIsStock(Integer isStock) {
   		this.isStock = isStock;
   	}

    
    public String getButlerId() {
		return butlerId;
	}

	public void setButlerId(String butlerId) {
		this.butlerId = butlerId;
	}
	
	 public String getButlerName() {
		return butlerName;
	}

	public void setButlerName(String butlerName) {
		this.butlerName = butlerName;
	}
    
    public String getStartedStr() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	return sdf.format(this.started);
	}

	public void setStartedStr(String startedStr) {
		this.startedStr = startedStr;
	}
    
    public Long getOperateId() {
		return operateId;
	}

	public void setOperateId(Long operateId) {
		this.operateId = operateId;
	}

	public String getExpectedReturn() {
		return expectedReturn;
	}

	public void setExpectedReturn(String expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public String getCarUsedAddress() {
		return carUsedAddress;
	}

	public void setCarUsedAddress(String carUsedAddress) {
		this.carUsedAddress = carUsedAddress;
	}

    public Integer getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public BigDecimal getMaintenanceCosts() {
		return maintenanceCosts;
	}

	public void setMaintenanceCosts(BigDecimal maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}

	public Integer getHandle() {
		return handle;
	}

	public void setHandle(Integer handle) {
		this.handle = handle;
	}

    public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateNum() {
        return this.operateNum;
    }

    public void setOperateNum(String operateNum) {
        this.operateNum = operateNum;
    }

    public String getCarPlateNum() {
        return this.carPlateNum;
    }

    public void setCarPlateNum(String carPlateNum) {
        this.carPlateNum = carPlateNum;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStarted() {
        return this.started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return this.ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public Long getOperateType() {
        return this.operateType;
    }

    public void setOperateType(Long operateType) {
        this.operateType = operateType;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperateTo() {
        return this.operateTo;
    }

    public void setOperateTo(String operateTo) {
        this.operateTo = operateTo;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRepairManufacturer() {
        return this.repairManufacturer;
    }

    public void setRepairManufacturer(String repairManufacturer) {
        this.repairManufacturer = repairManufacturer;
    }

    public Date getFailureTime() {
        return this.failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    public String getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }
}
