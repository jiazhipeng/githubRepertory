package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 14:30:41
 */
public class MemberAccount implements Serializable {

    private static final long serialVersionUID = 1110843496744545L;

    private Long id;//主键

    private Long memberId;//用户ID

    private BigDecimal balance;//金额(扣费时存负数)

    private BigDecimal deposit;//押金

    private Integer integral;//积分

    private Integer valid;//数据状态 0:无效;1:有效;默认1

    private Date created;//创建时间

    private String modifer;//修改人

    private Long modifierId;//修改人ID
    
    private Integer payAccount;//充值次数
    
    private Date lastPayTime;//最后充值时间
    
    private String remark;//备注

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

    public Integer getIntegral() {
        return this.integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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

	public Integer getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(Integer payAccount) {
		this.payAccount = payAccount;
	}

	public Date getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(Date lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
