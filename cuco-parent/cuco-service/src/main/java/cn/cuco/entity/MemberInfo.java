package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-23 13:41:40
 */
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 9545651549212575L;

    private Long id;//主键

    private Long memberId;//用户ID

    private String idcardFront;//身份证正面

    private String idcardBack;//身份证反面

    private String drivingLicenseOriginal;//驾驶证正本

    private String drivingLicenseCopy;//驾驶证副本

    private String creditCard;//信用卡(或储蓄卡)

    private Date created;//创建时间

    private String modifer;//修改人

    private Long modifierId;//修改人ID

    private Date lasttimeModify;//最后修改时间

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

    public String getDrivingLicenseOriginal() {
        return this.drivingLicenseOriginal;
    }

    public void setDrivingLicenseOriginal(String drivingLicenseOriginal) {
        this.drivingLicenseOriginal = drivingLicenseOriginal;
    }

    public String getDrivingLicenseCopy() {
        return this.drivingLicenseCopy;
    }

    public void setDrivingLicenseCopy(String drivingLicenseCopy) {
        this.drivingLicenseCopy = drivingLicenseCopy;
    }

    public String getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getModifer() {
        return this.modifer;
    }

    public void setModifer(String modifer) {
        this.modifer = modifer;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

}
