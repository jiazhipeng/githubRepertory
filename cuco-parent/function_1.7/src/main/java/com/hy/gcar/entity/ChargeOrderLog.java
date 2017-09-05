package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-22 19:30:21
 */
public class ChargeOrderLog implements Serializable {

    private static final long serialVersionUID = 5036334112321518L;

    private Long id;//

    private Long orderId;//支付订单ID

    private String orderNo;//支付订单号

    private String payWaterNo;//支付流水号

    private String bank;//支付银行

    private Integer paymentType;//支付类型 0:京东；1:微信；2:后台人工

    private BigDecimal discountMoney;//折后金额

    private BigDecimal deposit;//押金

    private BigDecimal balance;//余额

    private BigDecimal orderAmount;//订单金额

    private Date completionTime;//交易完成时间

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private Long memberId;//用户id

    private String memberName;//用户名

    private String memberMobile;//用户手机号

    private String wechatJson;//微信支付返回json串

    private String jdJson;//京东支付返回json串

    private Integer orderStatus;//支付订单状态 ：1，待支付；2，已取消；3，已完成；

    private Integer logType;//日志类型：0，客户端操作；1，后台操作

    private String remark;//备注(后台人工操作时填写)

    private String modifierId;//操作人ID

    private String modifier;//操作人名称

    private Integer memberRole;//会员角色：1，会员；2，销售；3，管家；

    private Integer accountType;//续费类型：1，余额；2，押金；3，余额和押金

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Integer getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getDiscountMoney() {
        return this.discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getDeposit() {
        return this.deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getCompletionTime() {
        return this.completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
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

    public String getWechatJson() {
        return this.wechatJson;
    }

    public void setWechatJson(String wechatJson) {
        this.wechatJson = wechatJson;
    }

    public String getJdJson() {
        return this.jdJson;
    }

    public void setJdJson(String jdJson) {
        this.jdJson = jdJson;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getLogType() {
        return this.logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
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

    public Integer getMemberRole() {
        return this.memberRole;
    }

    public void setMemberRole(Integer memberRole) {
        this.memberRole = memberRole;
    }

    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

}
