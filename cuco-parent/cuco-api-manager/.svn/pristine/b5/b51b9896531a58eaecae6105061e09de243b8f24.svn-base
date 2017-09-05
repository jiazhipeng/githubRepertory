package cn.cuco.controller.manager.wechat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.cuco.controller.enums.WechatMenuEnum;
import cn.cuco.entity.WechatMenu;
import cn.cuco.service.wechat.menu.WechatMenuService;
import cn.cuco.wechat.util.WechatMenuUtils;

@Controller
@RequestMapping("/wechat/menu")
public class CustomMenuController {
	
	@Autowired
	private WechatMenuService wechatMenuService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toMenuPage(ModelMap map){
		/*List<WechatMenu> list = wechatMenuService.getWechatMenuList();
		for(WechatMenu menu : list){
			List<WechatMenu> curList = JSON.parseArray(menu.getSubMenu(), WechatMenu.class);
			if(CollectionUtils.isNotEmpty(curList)){
				menu.setSubButtonList(curList);
			}
		}
		map.put("menuList", list);*/
		return "/WEB-INF/views/wechatMenu/edit.jsp";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateMenu(String data,String pwd){
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		if("jiaxiaoxian".equals(pwd)){
			
			try{
				List<WechatMenu> list = JSON.parseArray(data, WechatMenu.class);
				
				//1.重新加载菜单
				String status = reloadWechatMenu(list);
				
				if(StringUtils.isNotBlank(status) && "0".equals(status)){
					
					//清库
					wechatMenuService.deleteAll();
					//1.入库
					wechatMenuService.createList(list);
					
					result.put("isSuccess", true);
					result.put("msg", "更新成功");
				}else{
					result.put("isSuccess", false);
					result.put("msg", "更新失败");
				}
			}catch(Exception e){
				log.error("更新微信菜单操作异常",e);
				result.put("isSuccess", false);
				result.put("msg", "更新异常");
			}
			return result;
		}
		result.put("isSuccess", false);
		result.put("msg", "密码错误");
		return result;
	}
	
	/**
	 * 读取解析json文件
	 * @param filePath
	 * @return
	 */
	public List<WechatMenu> getMenuFromFile(String filePath){
		List<WechatMenu> list = null;
		File file = new File(filePath);
		if(file.exists()){
			try {
				String data = FileUtils.readFileToString(file, "utf-8");
				JSONObject obj = JSONObject.parseObject(data);
				list = JSON.parseArray(obj.getString("button"), WechatMenu.class);
			} catch (IOException e) {
				log.info("{} when get menu list from {}{}",e,filePath);
			}
		}
		return list;
	}
	
	//重新加载微信菜单
	public String reloadWechatMenu(List<WechatMenu> list) {
		//1.构建接口参数
		Map<String,List<Map<String,Object>>> data = Maps.newHashMap();
		List<Map<String,Object>> dataList = Lists.newArrayList();
		data.put("button", dataList);
		
		for(WechatMenu main : list){
			Map<String,Object> tmp = Maps.newLinkedHashMap();
			tmp.put("name", main.getName());
			if(main.getHasSubmenu() == WechatMenuEnum.YES.getValue()){//有子菜单
				List<WechatMenu> subMenuList = JSON.parseArray(main.getSubmenu(), WechatMenu.class);
				List<Map<String,Object>> sonList = Lists.newLinkedList();
				tmp.put("sub_button", sonList);
				for(WechatMenu son : subMenuList){//组装子菜单数据
					Map<String,Object> sonMap = Maps.newHashMap();
					String sonType = son.getType();
					if("view".equals(sonType)){
						sonMap.put("url", son.getUrl());
					}else{
						sonMap.put("key", son.getMsgKey());
					}
					sonMap.put("name", son.getName());
					sonMap.put("type", sonType);
					sonList.add(sonMap);
				}
			}else{//没有子菜单
				String sonType = main.getType();
				if("view".equals(sonType)){
					tmp.put("url", main.getUrl());
				}else{
					tmp.put("key", main.getMsgKey());
				}
				tmp.put("type", sonType);
			}
			dataList.add(tmp);
		}
		
		//2.调用微信接口，重新加载微信菜单
    	log.info(".......重新加载微信菜单开始......");
    	String param = JSONObject.toJSONString(data);
    	return WechatMenuUtils.createMenu(param);
	}

}
