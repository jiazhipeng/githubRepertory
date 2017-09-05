package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-25 14:44:02
 */
public class CarType extends PageQuery  implements Serializable {

    private static final long serialVersionUID = 1101213323162157L;

    private Long id;//主键

    private String brand;//品牌名称

    private String carType;//车型名称

    private String imageUrl;//车型剪影图片地址

    private BigDecimal tankVolume;//油箱体积（L）

    private BigDecimal basicCost;//基础费用
    
    private BigDecimal deposit;//押金

    private BigDecimal insuranceUnlock;//保险费用（解锁）

    private BigDecimal insuranceNotUnlock;//保险费用（非解锁）

    private Integer isRestriction;//是否参与限号 0：不参与；1：参与

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private String modifier;//操作人姓名

    private Long modifierId;//操作人id

    private Integer valid;//上下架 0：下架；1：上架

    private List<CarTypePrice> carTypePrices;
    
    //******************************************************************************
    private Long cityId;//城市ID

    private BigDecimal kmOne;//公里价格 : >50 <=100；

    private BigDecimal kmTwo;//公里价格： >100  <=200；

    private BigDecimal kmThree;//公里价格 :>200  <=300；

    private BigDecimal kmFour;//公里价格 :>300 <=500；

    private BigDecimal kmFive;//公里价格 :>500；
    private Integer carCount;//供应数量
    
    public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public BigDecimal getKmOne() {
		return kmOne;
	}

	public void setKmOne(BigDecimal kmOne) {
		this.kmOne = kmOne;
	}

	public BigDecimal getKmTwo() {
		return kmTwo;
	}

	public void setKmTwo(BigDecimal kmTwo) {
		this.kmTwo = kmTwo;
	}

	public BigDecimal getKmThree() {
		return kmThree;
	}

	public void setKmThree(BigDecimal kmThree) {
		this.kmThree = kmThree;
	}

	public BigDecimal getKmFour() {
		return kmFour;
	}

	public void setKmFour(BigDecimal kmFour) {
		this.kmFour = kmFour;
	}

	public BigDecimal getKmFive() {
		return kmFive;
	}

	public void setKmFive(BigDecimal kmFive) {
		this.kmFive = kmFive;
	}

    public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getTankVolume() {
        return this.tankVolume;
    }

    public void setTankVolume(BigDecimal tankVolume) {
        this.tankVolume = tankVolume;
    }

    public BigDecimal getBasicCost() {
        return this.basicCost;
    }

    public void setBasicCost(BigDecimal basicCost) {
        this.basicCost = basicCost;
    }

    public BigDecimal getInsuranceUnlock() {
        return this.insuranceUnlock;
    }

    public void setInsuranceUnlock(BigDecimal insuranceUnlock) {
        this.insuranceUnlock = insuranceUnlock;
    }

    public BigDecimal getInsuranceNotUnlock() {
        return this.insuranceNotUnlock;
    }

    public void setInsuranceNotUnlock(BigDecimal insuranceNotUnlock) {
        this.insuranceNotUnlock = insuranceNotUnlock;
    }

    public Integer getIsRestriction() {
        return this.isRestriction;
    }

    public void setIsRestriction(Integer isRestriction) {
        this.isRestriction = isRestriction;
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

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

	public List<CarTypePrice> getCarTypePrices() {
		return carTypePrices;
	}

	public void setCarTypePrices(List<CarTypePrice> carTypePrices) {
		this.carTypePrices = carTypePrices;
	}

	public Integer getCarCount() {
		return carCount;
	}

	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}
	
}
