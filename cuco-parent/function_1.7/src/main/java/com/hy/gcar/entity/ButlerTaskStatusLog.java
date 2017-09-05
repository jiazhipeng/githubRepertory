package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

import com.hy.gcar.constant.ButlerTaskStatus;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-10 15:42:35
 */
public class ButlerTaskStatusLog implements Serializable {

    private static final long serialVersionUID = 8948077460413329L;

    private Long id;//

    private Long taskId;//管家任务ID

    private Integer status;//任务状态 0:待送车；1:送车中；2:待取车；3:取车中；4:已到达；5:充电中；6:已失联；7:已完成；8:已取消；
    
    private String statusInfo;//任务中文展示
    
    public String getStatusInfo() {
    	//返回响应状态的中文描述
    	return ButlerTaskStatus.getStatus(this.status);
	}

    private Date created;//创建时间

    private String remark;//备注

    private String modifierId;//操作人ID

    private String modifier;//操作人姓名

    private String modifierMobile;//操作人手机号码

    private Integer modifierType;//操作人类型 0:用户；1:管家（默认0）

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getModifierMobile() {
        return this.modifierMobile;
    }

    public void setModifierMobile(String modifierMobile) {
        this.modifierMobile = modifierMobile;
    }

    public Integer getModifierType() {
        return this.modifierType;
    }

    public void setModifierType(Integer modifierType) {
        this.modifierType = modifierType;
    }

}
