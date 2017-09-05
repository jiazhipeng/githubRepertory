package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-28 09:59:58
 */
public class OrderRenewal extends PageQuery<OrderRenewal> implements Serializable {

    private static final long serialVersionUID = 5266384428769088L;

    private Long id;//主键ID

    private String orderNum;//续费订单编号

    private BigDecimal total;//充值总金额

    private BigDecimal payment;//已充值金额

    private Date created;//创建时间

    private Integer status;//状态: 0:待付款;1:支付中;2:已付款;3:已取消 默认0

    private Integer paymentType;//支付渠道 0:京东；1:微信；2:支付宝；

    private String paymentNum;//第三方支付流水单号

    private Date completedTime;//充值完成时间
    
    private Date lastPaymentTime;//最后一次支付时间

    private String remark;//备注

    private Date lasttimeModify;//最后修改时间

    private Integer createdSource;//发起渠道：1:用户发起;2:后台人工续费;

    private Integer accountType;//续费类型：1:余额；2:押金;3，余额和押金

    private Long createdMemberId;//充值人ID

    private String createdMemberName;//充值人姓名

    private String createdMemberMobile;//充值人手机号

    private String bank;//支付银行

    private BigDecimal giftAmount;//充返赠送金额

    private Long modifierId;//操作人id

    private String modifier;//操作人姓名
    
    private Date createStart;//创建开始时间
    
    private Date createEnd;//创建结束时间
    
    private Date paymentStart;//付款开始时间
    
    private Date paymentEnd;//付款结束时间

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
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

    public Integer getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentNum() {
        return this.paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
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

    public Long getCreatedMemberId() {
        return this.createdMemberId;
    }

    public void setCreatedMemberId(Long createdMemberId) {
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

    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public BigDecimal getGiftAmount() {
        return this.giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
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

	public Date getCreateStart() {
		return createStart;
	}

	public void setCreateStart(Date createStart) {
		this.createStart = createStart;
	}

	public Date getCreateEnd() {
		return createEnd;
	}

	public void setCreateEnd(Date createEnd) {
		this.createEnd = createEnd;
	}

	public Date getPaymentStart() {
		return paymentStart;
	}

	public void setPaymentStart(Date paymentStart) {
		this.paymentStart = paymentStart;
	}

	public Date getPaymentEnd() {
		return paymentEnd;
	}

	public void setPaymentEnd(Date paymentEnd) {
		this.paymentEnd = paymentEnd;
	}

	public Date getLastPaymentTime() {
		return lastPaymentTime;
	}

	public void setLastPaymentTime(Date lastPaymentTime) {
		this.lastPaymentTime = lastPaymentTime;
	}

}
