package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-03-08 15:18:02
 */
public class Payment extends PageQuery<Payment> implements Serializable {

    private static final long serialVersionUID = 3040253740188326L;

    private Long id;//支付记录主键ID

    private Integer orderType;//订单类型： 0:解锁订单; 1:续费订单; 2:用车结算订单

    private Long orderId;//支付订单ID

    private String orderNo;//支付订单号

    private String payWaterNo;//支付流水号

    private String bank;//支付银行

    private Integer paymentType;//支付类型 0:京东；1:微信；2:后台人工 3:支付宝

    private BigDecimal discountMoney;//折后金额

    private BigDecimal deposit;//押金

    private BigDecimal balance;//余额

    private BigDecimal payment;//支付金额

    private Date completionTime;//交易完成时间

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private Long memberId;//用户id

    private String memberName;//用户名

    private String memberMobile;//用户手机号

    private String paymentJson;//微信/京东/支付宝支付返回json串

    private Integer paymentStatus;//支付状态 ：1:取消支付；2:支付失败；3:支付成功；

    private Integer logType;//日志类型：0，客户端操作；1，后台操作

    private String remark;//备注

    private Long modifierId;//操作人ID

    private String modifier;//操作人名称

    private Integer accountType;//续费类型：1:余额;2:押金;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderType() {
        return this.orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
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

    public String getPaymentJson() {
        return this.paymentJson;
    }

    public void setPaymentJson(String paymentJson) {
        this.paymentJson = paymentJson;
    }

    public Integer getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

}
