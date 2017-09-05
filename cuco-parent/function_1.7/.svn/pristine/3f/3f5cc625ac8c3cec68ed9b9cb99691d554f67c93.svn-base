package com.hy.gcar.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 微信自定义菜单
 * @author duzc
 *
 */
public class WechatMenu implements Serializable {
	
	private static final long serialVersionUID = -8606724008497987871L;
	
	private Long id;
	private String name;//菜单名
	private Integer hasSubmenu;//是否含有子菜单  0：没有 1：有
	private String subMenu;//子菜单
	private String type;//菜单的响应动作类型     "view"对应 url    "click"对应key
	private String key;//菜单KEY值，用于消息接口推送
	private String url;//菜单对应的链接
	private String message;//key对应的回复内容
	private Date created;//创建日期
    private String modifierId;//操作人id
    private String modifier;//操作人姓名
	private List<WechatMenu> subButtonList;//主菜单对应的子菜单
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHasSubmenu() {
		return hasSubmenu;
	}
	public void setHasSubmenu(Integer hasSubmenu) {
		this.hasSubmenu = hasSubmenu;
	}
	public String getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public List<WechatMenu> getSubButtonList() {
		return subButtonList;
	}
	public void setSubButtonList(List<WechatMenu> subButtonList) {
		this.subButtonList = subButtonList;
	}
}
