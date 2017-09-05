package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:07
 */
public class ItemCartype implements Serializable {

    private static final long serialVersionUID = 1495217084386326L;

    private Long id;//ID

    private Long itemId;//会员成长ID

    private String itemName;//会员成长名称

    private BigDecimal dayPrice;//日使用价格

    private Long cartypeId;//车型ID

    private Date created;//创建时间
    
    private String cartype;//车型
    
    private String carBrand;//车品牌
    
    private boolean flagStr;
    
    public boolean getFlagStr() {
		return flagStr;
	}

	public void setFlagStr(boolean flagStr) {
		this.flagStr = flagStr;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getDayPrice() {
        return this.dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    public Long getCartypeId() {
        return this.cartypeId;
    }

    public void setCartypeId(Long cartypeId) {
        this.cartypeId = cartypeId;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
