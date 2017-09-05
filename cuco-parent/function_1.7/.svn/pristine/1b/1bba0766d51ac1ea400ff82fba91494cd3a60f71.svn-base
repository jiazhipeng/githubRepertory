package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-11-03 17:02:16
 */
public class GetmoneyApplyLog implements Serializable {

    private static final long serialVersionUID = 8982526346785649L;

    private Long id;//
    
    private Long getmoneyApplyId;//提现审核ID

    private String operaterId;//审核人ID

    private String operaterName;//审核人姓名

    private Date approveTime;//审核时间

    public Long getGetmoneyApplyId() {
		return getmoneyApplyId;
	}

	public void setGetmoneyApplyId(Long getmoneyApplyId) {
		this.getmoneyApplyId = getmoneyApplyId;
	}

	private Integer status;//审核状态 0:待处理；1:待汇款；2:汇款成功；3:汇款失败；（默认0）

    private String remark;//备注信息

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getApproveTime() {
        return this.approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
