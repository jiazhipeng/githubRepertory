package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-12-27 18:19:36
 */
public class WechatMenu implements Serializable {

    private static final long serialVersionUID = 9672958324506919L;

    private Long id;//主键

    private String name;//

    private Integer hasSubmenu;//是否含有子菜单 0：没有 1：有

    private String type;//菜单的响应动作类型

    private String msgKey;//菜单KEY值，用于消息接口推送

    private String message;//key对应的推送内容

    private String url;//网页链接，用户点击菜单可打开链接，不超过1024字节

    private String submenu;//子菜单内容

    private Date created;//创建时间

    private String modifierId;//操作人id

    private String modifier;//操作人姓名

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHasSubmenu() {
        return this.hasSubmenu;
    }

    public void setHasSubmenu(Integer hasSubmenu) {
        this.hasSubmenu = hasSubmenu;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgKey() {
        return this.msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubmenu() {
        return this.submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
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

}
