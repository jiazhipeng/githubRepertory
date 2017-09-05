package com.hy.gcar.service.wechatmenu;

import java.util.List;

import com.hy.gcar.entity.WechatMenu;


public interface WechatMenuService {
	
	/**
	 * 创建自定义菜单
	 * @param list
	 */
	public void create(List<WechatMenu> list);
	
	/**
	 * 获取所有的自定义菜单
	 * @return
	 */
	public List<WechatMenu> getWechatMenuList();

}
