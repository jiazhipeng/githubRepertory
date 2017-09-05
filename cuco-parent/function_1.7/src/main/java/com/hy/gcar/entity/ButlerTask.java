package com.hy.gcar.entity;

import com.hy.gcar.constant.ButlerTaskType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.ButlerTaskStatusTrack;
import com.hy.gcar.entity.Order.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-10 15:42:34
 */
public class ButlerTask implements Serializable {

    private static final long serialVersionUID = 4967133787527476L;

    private Long id;//主键

    private Integer type;//任务类型 1:充电任务；2:取车任务；3:送车任务；

    private String memberId;//会员id

    private String memberName;//会员姓名

    private Integer status;//任务状态 0:待送车；1:送车中；2:待取车；3:取车中；4:已到达；5:充电中；6:已失联；7:已完成；8:已取消 ；9待处理,10待接单；

    private Integer statusCondition;//修改任务状态的条件
    
    private String statusInfo;//任务中文展示

    private String typeInfo;//任务类型描述

    public String getStatusInfo() {
    	//返回响应状态的中文描述
    	return ButlerTaskStatus.getStatus(this.status);
	}
    public String getButlerTaskStatusTrack() {
    	//返回响应状态的中文描述
    	return ButlerTaskStatusTrack.getStatus(this.status);
    }

    private Date created;//创建时间
    
    private Date planTime;//计划执行时间

    private String planTimeQuery;//计划执行时间-查询条件

    private int total = 0;//统计天数

    private String numOfdays;//每个月的天数

    private Date completeTime;//完成时间
    
    private Date carReturnTime;//预计还车时间

    private String operaterId;//执行管家id

    private String operaterName;//执行管家姓名

    private String operaterMobile;//执行管家手机号
    private String customerId;//客服id

    private String customerName;//客服名称

    private String remark;//任务取消原因描述

    private String taskNum;//管家任务编号(任务类型编码＋年月日＋4位随机数)(加唯一索引，代码中cach异常，捕获后重新调用生成编号，再插入)

    private String memberMobile;//会员手机号

    private Integer messageStatus;//消息发送状态 0:未发送； 1:发送成功；

    private Long memberItemId;//会员用车权益ID

    private String taskSendCarAddress;//任务送车地址

    private Integer createType;//任务发起人 0:用户；1:管家；（默认0）

    private Long powerUsedId;//用车记录主表ID

    private Long carOperateId;//运营车辆ID
    
    private Integer modifierType;//操作人类型 0:用户；1:管家（默认0）
    
    private String taskGetCarAddress;//任务取车地址
    
    private String loginId;//登录人ID

    private Long carTypeId;//当前车位主车型
    
    private String queryTime;//点击日历查询
    
    private BigDecimal gasoline;//油耗数
    
    private List<Long> carOperateIds;//車輛id

    private Integer readyTime;//整备时间 臨時字段
    
    private Date lasttimeModify;//最后修改时间

    private Date queryBegin;//查询开始时间

    private Date queryEnd;//查询结束时间
    
    
    public String getOperaterMobile() {
		return operaterMobile;
	}
	public void setOperaterMobile(String operaterMobile) {
		this.operaterMobile = operaterMobile;
	}
	public Date getLasttimeModify() {
		return lasttimeModify;
	}
	public void setLasttimeModify(Date lasttimeModify) {
		this.lasttimeModify = lasttimeModify;
	}
	public String getLoginId() {
		return loginId;
	}

	public BigDecimal getGasoline() {
		return gasoline;
	}
	public void setGasoline(BigDecimal gasoline) {
		this.gasoline = gasoline;
	}
	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Integer getModifierType() {
		return modifierType;
	}

	public String getTaskGetCarAddress() {
		return taskGetCarAddress;
	}

	public void setTaskGetCarAddress(String taskGetCarAddress) {
		this.taskGetCarAddress = taskGetCarAddress;
	}

	public void setModifierType(Integer modifierType) {
		this.modifierType = modifierType;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public String getOperaterId() {
        return this.operaterId;
    }

    public void setOperaterId(String operaterId) {
        this.operaterId = operaterId;
    }

    public String getOperaterName() {
        return this.operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTaskNum() {
        return this.taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Integer getMessageStatus() {
        return this.messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Long getMemberItemId() {
        return this.memberItemId;
    }

    public void setMemberItemId(Long memberItemId) {
        this.memberItemId = memberItemId;
    }

    public String getTaskSendCarAddress() {
        return this.taskSendCarAddress;
    }

    public void setTaskSendCarAddress(String taskSendCarAddress) {
        this.taskSendCarAddress = taskSendCarAddress;
    }

    public Integer getCreateType() {
        return this.createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Long getPowerUsedId() {
        return this.powerUsedId;
    }

    public void setPowerUsedId(Long powerUsedId) {
        this.powerUsedId = powerUsedId;
    }

    public Long getCarOperateId() {
        return this.carOperateId;
    }

    public void setCarOperateId(Long carOperateId) {
        this.carOperateId = carOperateId;
    }

    public String getPlanTimeQuery() {
        return planTimeQuery;
    }

    public void setPlanTimeQuery(String planTimeQuery) {
        this.planTimeQuery = planTimeQuery;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumOfdays() {
        if(StringUtils.isNotEmpty(this.numOfdays)){
            return Integer.valueOf(this.numOfdays);
        }
        return 0;
    }


    public Long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public void setNumOfdays(String numOfdays) {
        this.numOfdays = numOfdays;
    }

    public Integer getStatusCondition() {
        return statusCondition;
    }

    public void setStatusCondition(Integer statusCondition) {
        this.statusCondition = statusCondition;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
	public List<Long> getCarOperateIds() {
		return carOperateIds;
	}
	public void setCarOperateIds(List<Long> carOperateIds) {
		this.carOperateIds = carOperateIds;
	}
	public Date getCarReturnTime() {
		return carReturnTime;
	}
	public void setCarReturnTime(Date carReturnTime) {
		this.carReturnTime = carReturnTime;
	}
	public Integer getReadyTime() {
		return readyTime;
	}
	public void setReadyTime(Integer readyTime) {
		this.readyTime = readyTime;
	}

    public String getTypeInfo() {
        return ButlerTaskType.getType(this.type);
    }

    public Date getQueryBegin() {
        return queryBegin;
    }

    public void setQueryBegin(Date queryBegin) {
        this.queryBegin = queryBegin;
    }

    public Date getQueryEnd() {
        return queryEnd;
    }

    public void setQueryEnd(Date queryEnd) {
        this.queryEnd = queryEnd;
    }
}
