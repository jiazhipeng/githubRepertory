package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-29 16:55:31
 */
public class BasicNotice implements Serializable {

    private static final long serialVersionUID = 7053367955571196L;

    private Long id;//

    private Integer noticeType;//通知内容

    private String noticeUser;//通知人员

    private Long noticeUserId;//通知人ID(member_id，管家、会员)

    private Integer status;//状态 0:正常；1:停止通知；默认0；

    private Date created;//创建时间

    private String createOperater;//创建人

    private Date lasttime;//最后修改时间

    private Long basicId;//基础设置表ID

    private String userOpenid;//用户openid

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoticeType() {
        return this.noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeUser() {
        return this.noticeUser;
    }

    public void setNoticeUser(String noticeUser) {
        this.noticeUser = noticeUser;
    }

    public Long getNoticeUserId() {
        return this.noticeUserId;
    }

    public void setNoticeUserId(Long noticeUserId) {
        this.noticeUserId = noticeUserId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreateOperater() {
        return this.createOperater;
    }

    public void setCreateOperater(String createOperater) {
        this.createOperater = createOperater;
    }

    public Date getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Long getBasicId() {
        return this.basicId;
    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
    }

    public String getUserOpenid() {
        return this.userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

}
