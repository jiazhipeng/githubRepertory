package cn.cuco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-25 14:44:59
 */
public class Carport extends PageQuery implements Serializable {

    private static final long serialVersionUID = 1595439843270230L;

    private Long id;//主键

    private String carportName;//车库名称

    private BigDecimal unlockPrice;//解锁价格

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private String modifier;//操作人姓名

    private Long modifierId;//操作人ID

    private Integer valid;//上下架 0：下架；1：上架

    private List<CarType> carTypes;
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarportName() {
        return this.carportName;
    }

    public void setCarportName(String carportName) {
        this.carportName = carportName;
    }

    public BigDecimal getUnlockPrice() {
        return this.unlockPrice;
    }

    public void setUnlockPrice(BigDecimal unlockPrice) {
        this.unlockPrice = unlockPrice;
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

	public List<CarType> getCarTypes() {
		return carTypes;
	}

	public void setCarTypes(List<CarType> carTypes) {
		this.carTypes = carTypes;
	}
    
}
