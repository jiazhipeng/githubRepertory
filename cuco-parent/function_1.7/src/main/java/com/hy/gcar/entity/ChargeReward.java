package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-11-03 17:02:16
 */
public class ChargeReward implements Serializable {

    private static final long serialVersionUID = 4722279921280012L;

    private Long id;//

    private Date startTime;//充返开始时间

    private Date endTime;//充返结束时间

    private BigDecimal percent;//充返比例

    private Date created;//创建时间

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPercent() {
        return this.percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
