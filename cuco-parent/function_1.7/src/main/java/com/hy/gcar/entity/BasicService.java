package com.hy.gcar.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-19 16:39:26
 */
public class BasicService implements Serializable {

    private static final long serialVersionUID = 8518418390406548L;

    private Long id;//ID

    private String serviceName;//

    private BigDecimal price;//服务价格

    private Time startTime;//

    private Time endTime;//

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private String modifier;//操作人姓名

    private String modifierId;//操作人ID

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
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

    public String getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

}
