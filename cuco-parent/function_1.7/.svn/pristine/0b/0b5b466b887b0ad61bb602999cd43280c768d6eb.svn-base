package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-23 15:50:12
 */
public class ChargeOrder implements Serializable {

    private static final long serialVersionUID = 6903846805750642L;

    private Long id;//

    private String chargeOrderNo;//订单编号(产品代码＋年月日＋4位随机数)(加唯一索引，代码中cach异常，捕获后重新调用生成编号，再插入)

    private BigDecimal total;//支付总金额

    private BigDecimal balance;//充值权益余额

    private BigDecimal deposit;//充值押金

    private Date created;//创建时间

    private Integer status;//状态 ：1，待支付；2，已取消；3，已完成；

    private Integer chargeSource;//支付渠道 0:京东；1:微信；2:后台人工；

    private String chargeNo;//第三方支付流水单号

    private Date completedTime;//交易完成时间

    private String remark;//交易备注

    private Long memberItemId;//会员权益ID

    private String memberItemName;//权益名称

    private Long memberItemOwnerId;//会员权益拥有者ID

    private String modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间

    private Integer createdSource;//发起渠道：1，用户发起；2，后台人工续费；

    private Integer accountType;//续费类型：1，余额；2，押金；3，余额和押金

    private Integer memberShareType;//共用人状态：0，不是；1，普通共用人；2，主共用人

    private String createdMemberId;//充值人ID

    private String createdMemberName;//充值人姓名

    private String createdMemberMobile;//充值人手机号

    private String payWaterNo;//支付流水号

    private String bank;//支付银行
    
    /**
     * 赠送金额
     */
    private BigDecimal giftAmount;
    
    public BigDecimal getGiftAmount(){
    	return this.giftAmount;
    }
    
    public void setGiftAmount(BigDecimal giftAmount){
    	this.giftAmount = giftAmount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChargeOrderNo() {
        return this.chargeOrderNo;
    }

    public void setChargeOrderNo(String chargeOrderNo) {
        this.chargeOrderNo = chargeOrderNo;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDeposit() {
        return this.deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChargeSource() {
        return this.chargeSource;
    }

    public void setChargeSource(Integer chargeSource) {
        this.chargeSource = chargeSource;
    }

    public String getChargeNo() {
        return this.chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public Date getCompletedTime() {
        return this.completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getMemberItemOwnerId() {
        return this.memberItemOwnerId;
    }

    public void setMemberItemOwnerId(Long memberItemOwnerId) {
        this.memberItemOwnerId = memberItemOwnerId;
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

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public Integer getCreatedSource() {
        return this.createdSource;
    }

    public void setCreatedSource(Integer createdSource) {
        this.createdSource = createdSource;
    }

    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getMemberShareType() {
        return this.memberShareType;
    }

    public void setMemberShareType(Integer memberShareType) {
        this.memberShareType = memberShareType;
    }

    public String getCreatedMemberId() {
        return this.createdMemberId;
    }

    public void setCreatedMemberId(String createdMemberId) {
        this.createdMemberId = createdMemberId;
    }

    public String getCreatedMemberName() {
        return this.createdMemberName;
    }

    public void setCreatedMemberName(String createdMemberName) {
        this.createdMemberName = createdMemberName;
    }

    public String getCreatedMemberMobile() {
        return this.createdMemberMobile;
    }

    public void setCreatedMemberMobile(String createdMemberMobile) {
        this.createdMemberMobile = createdMemberMobile;
    }

    public String getPayWaterNo() {
        return this.payWaterNo;
    }

    public void setPayWaterNo(String payWaterNo) {
        this.payWaterNo = payWaterNo;
    }

    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

}
