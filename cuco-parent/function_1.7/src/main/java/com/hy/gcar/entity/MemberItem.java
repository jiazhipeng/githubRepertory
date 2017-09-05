package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:08
 */
public class MemberItem implements Serializable {

    private static final long serialVersionUID = 8156755337125218L;

    private Long id;//

    private Long memberId;//会员ID

    private Long itemId;//会员成长ID

    private BigDecimal balance;//余额（来源是订单、充值）

    private BigDecimal deposit;//押金(来源订单押金)

    private Date created;//创建时间

    private String modifierId;//操作人ID

    private String modifier;//操作人

    private Date lasttimeModify;//最后修改时间

    private Long orderId;//订单ID

    private String itemName;//会员成长名称

    private String levelUrl;//会员成长图片
    
    private Integer enableCount;//套餐可用车位数
    
    private Integer MaxEnableCount;//最大可用车位数
    
    private List<MemberItemCartype> cartypes;
    
    private List<PowerUsed> powerUseds;
    
    private Integer status; //权益状态：0:正常；1:余额、押金冻结
    
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<PowerUsed> getPowerUseds() {
		return powerUseds;
	}

	public void setPowerUseds(List<PowerUsed> powerUseds) {
		this.powerUseds = powerUseds;
	}

	public Integer getMaxEnableCount() {
		return MaxEnableCount;
	}

	public void setMaxEnableCount(Integer maxEnableCount) {
		MaxEnableCount = maxEnableCount;
	}

	public Integer getEnableCount() {
		return enableCount;
	}

	public void setEnableCount(Integer enableCount) {
		this.enableCount = enableCount;
	}

	public List<MemberItemCartype> getCartypes() {
		return cartypes;
	}

	public void setCartypes(List<MemberItemCartype> cartypes) {
		this.cartypes = cartypes;
	}

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

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLevelUrl() {
        return this.levelUrl;
    }

    public void setLevelUrl(String levelUrl) {
        this.levelUrl = levelUrl;
    }

}
