package com.techstar.gcar.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hy.security.entity.Menu;
import com.hy.security.service.menu.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath*:/spring-junit-config/applicationContext-test.xml","classpath*:/spring/applicationContext-apps.xml"}) //加载配置文件  
public class Test {
	
	
	@Autowired
	private MenuService menuService;
	
	
	
	@org.junit.Test
	public void test(){
		Menu menu = new Menu();
		menu.setName("aa");
		//boolean aaaa = menuService.findNameIsExistsBySystemIdAndParentIdAndName(menu);
		
		
	}

}
