package com.hy.security.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-03 16:25:55
 */
public class PermissionExchange implements Serializable {

    private static final long serialVersionUID = 9895573346518153L;

    private Long id;//主键

    private Long systemId;//系统ID

    private Long modifierId;//操作人ID

    private String modifier;//操作人

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private Integer type;//修改的资源类型 0：菜单操作；1：角色操作；2：角色菜单；3：角色人员；

    private Long userId;//用户ID

    private Integer isSend;//是否发送 0 未发送 1 已发送 默认0

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsSend() {
        return this.isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

}
