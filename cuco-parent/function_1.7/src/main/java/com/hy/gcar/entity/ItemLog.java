package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:07
 */
public class ItemLog implements Serializable {

    private static final long serialVersionUID = 4983489802405943L;

    private Long id;//

    private String modifier;//操作人

    private Date lasttimeModify;//最后修改时间

    private String contentModify;//修改内容

    private Long modifierId;//操作人ID

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContentModify() {
        return this.contentModify;
    }

    public void setContentModify(String contentModify) {
        this.contentModify = contentModify;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

}
