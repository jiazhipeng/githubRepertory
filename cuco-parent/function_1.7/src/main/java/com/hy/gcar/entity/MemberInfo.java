package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:07
 */
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 7369753342220656L;

    private Long id;//

    private Long memberId;//会员ID

    private String idcardFront;//身份证正面

    private String idcardBack;//身份证反面

    private String drivercardOriginal;//驾驶证正本

    private String drivercardCopy;//驾驶证副本

    private String license;//营业执照

    private String taxReg;//税务登记证

    private Date created;//创建时间

    private String flag;//请求来源0:个人中心 1用车发起


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

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
