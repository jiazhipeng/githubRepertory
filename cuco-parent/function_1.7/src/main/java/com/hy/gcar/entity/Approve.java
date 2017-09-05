package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-12 10:36:29
 */
public class Approve implements Serializable {

    private static final long serialVersionUID = 4032186706238461L;

    private Long id;//

    private String memberName;//会员名称

    private Long memberId;//会员ID

    private String memberMobile;//会员手机号

    private Long memberItemId;//会员权益ID

    private String memberItemName;//

    private Integer membership;//会员身份：0，共用人；1，主共用人

    private Integer approveType;//审核项目：0：用车申请；1：入会申请；2：升级申请

    private Integer status;//审核状态：0：待审核；1：复核申请；2：驳回申请；3：通过审核

    private String operaterId;//审核人ID

    private String operaterName;//审核人姓名

    private Date created;//提交审核时间

    private Date approveTime;//审核时间

    private String idcardFront;//身份证正面

    private String idcardBack;//身份证反面

    private String drivercardOriginal;//驾驶证正本

    private String drivercardCopy;//驾驶证副本

    private String license;//营业执照

    private String taxReg;//税务登记证

    private String remark;//

    private Integer isLog;//是否为日志记录：0，不是日志；1日志；

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Long getMemberItemId() {
        return this.memberItemId;
    }

    public void setMemberItemId(Long memberItemId) {
        this.memberItemId = memberItemId;
    }

    public String getMemberItemName() {
        return this.memberItemName;
    }

    public void setMemberItemName(String memberItemName) {
        this.memberItemName = memberItemName;
    }

    public Integer getMembership() {
        return this.membership;
    }

    public void setMembership(Integer membership) {
        this.membership = membership;
    }

    public Integer getApproveType() {
        return this.approveType;
    }

    public void setApproveType(Integer approveType) {
        this.approveType = approveType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getApproveTime() {
        return this.approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getIdcardFront() {
        return this.idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront;
    }

    public String getIdcardBack() {
        return this.idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack;
    }

    public String getDrivercardOriginal() {
        return this.drivercardOriginal;
    }

    public void setDrivercardOriginal(String drivercardOriginal) {
        this.drivercardOriginal = drivercardOriginal;
    }

    public String getDrivercardCopy() {
        return this.drivercardCopy;
    }

    public void setDrivercardCopy(String drivercardCopy) {
        this.drivercardCopy = drivercardCopy;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTaxReg() {
        return this.taxReg;
    }

    public void setTaxReg(String taxReg) {
        this.taxReg = taxReg;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsLog() {
        return this.isLog;
    }

    public void setIsLog(Integer isLog) {
        this.isLog = isLog;
    }

}
