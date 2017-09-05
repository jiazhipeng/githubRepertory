package com.hy.gcar.service.wechatmenu.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hy.gcar.dao.WechatMenuMapper;
import com.hy.gcar.entity.WechatMenu;
import com.hy.gcar.service.wechatmenu.WechatMenuService;

@Service
public class WechatMenuServiceImpl implements WechatMenuService{
	
	@Autowired
	private WechatMenuMapper wechatMenuMapper;

	@Override
	public void create(List<WechatMenu> list) {
		//1.删除原来的自定义菜单
		this.wechatMenuMapper.deleteAll();
		/*String userId = SubjectUtils.getPrincipal(ShiroUser.class).getId();
		String userName = SubjectUtils.getPrincipal(ShiroUser.class).getName();*/
		//2.插入新的自定义菜单
		for(WechatMenu menu:list){
			/*menu.setModifier(userName);
			menu.setModifierId(userId);*/
			menu.setCreated(new Date());
			this.wechatMenuMapper.insertSelective(menu);
		}
	}

	@Override
	public List<WechatMenu> getWechatMenuList() {
		return this.wechatMenuMapper.getWechatMenuList();
	}
	
	public static void main(String[] args) {
		WechatMenu main = new WechatMenu();
		main.setCreated(new Date());
		main.setName("主菜单一");
		main.setHasSubmenu(1);
		main.setType("view");
		main.setUrl("http://www.baidu.com");

		WechatMenu son1 = new WechatMenu();
		son1.setCreated(new Date());
		son1.setName("子菜单111");
		son1.setHasSubmenu(0);
		son1.setType("view");
		son1.setUrl("http://www.111.com");
		
		
		WechatMenu son2 = new WechatMenu();
		son2.setCreated(new Date());
		son2.setName("子菜单222");
		son2.setHasSubmenu(0);
		son2.setType("view");
		son2.setUrl("http://www.222.com");
		
		List<WechatMenu> list = new ArrayList<WechatMenu>();
		list.add(son1);
		list.add(son2);
		
		String subMenu = JSON.toJSONString(list);
		
		System.out.println(subMenu);
	}

}
