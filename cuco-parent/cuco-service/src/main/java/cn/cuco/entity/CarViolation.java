package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-03-08 15:39:33
 */
public class CarViolation extends PageQuery<CarViolation> implements Serializable {

    private static final long serialVersionUID = 6388849973537035L;

    private Long id;//车辆违章ID

    private Long carId;//违章车辆ID

    private String carPlateNum;//违章车辆车牌号

    private Long memberId;//违章用户ID

    private String memberName;//用户ID

    private String memberMobile;//用户手机号

    private Integer status;//违章处理状态（0：待确认；1：未处理；2：已处理）

    private Date violationTime;//违章时间

    private String violationAddress;//违章地点

    private Integer violationType;//违章行为（）

    private Integer type;//违章用户类型（0：用户违章；1：管家违章）

    private Integer dealType;//处理方式（0：自行处理；1：cuco代处理）

    private BigDecimal fine;//罚款

    private Integer pointPenalty;//罚分

    private BigDecimal deductionPrice;//扣费金额(元）

    private Long carUsedId;//用车记录ID

    private String description;//违章描述

    private String remark;//备注

    private String modifier;//操作人名称

    private Long modifierId;//操作人ID

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间
    
    //业务字段
    private List<CarAttachment> carAttachmentList;

    private String[] uploadImages; //出发凭证上传图片地址

    public String[] getUploadImages() {
        return uploadImages;
    }

    public void setUploadImages(String[] uploadImages) {
        this.uploadImages = uploadImages;
    }

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

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getViolationTime() {
        return this.violationTime;
    }

    public void setViolationTime(Date violationTime) {
        this.violationTime = violationTime;
    }

    public String getViolationAddress() {
        return this.violationAddress;
    }

    public void setViolationAddress(String violationAddress) {
        this.violationAddress = violationAddress;
    }

    public Integer getViolationType() {
        return this.violationType;
    }

    public void setViolationType(Integer violationType) {
        this.violationType = violationType;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDealType() {
        return this.dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public BigDecimal getFine() {
        return this.fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public Integer getPointPenalty() {
        return this.pointPenalty;
    }

    public void setPointPenalty(Integer pointPenalty) {
        this.pointPenalty = pointPenalty;
    }

    public BigDecimal getDeductionPrice() {
        return this.deductionPrice;
    }

    public void setDeductionPrice(BigDecimal deductionPrice) {
        this.deductionPrice = deductionPrice;
    }

    public Long getCarUsedId() {
        return this.carUsedId;
    }

    public void setCarUsedId(Long carUsedId) {
        this.carUsedId = carUsedId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

	public List<CarAttachment> getCarAttachmentList() {
		return carAttachmentList;
	}

	public void setCarAttachmentList(List<CarAttachment> carAttachmentList) {
		this.carAttachmentList = carAttachmentList;
	}

}
