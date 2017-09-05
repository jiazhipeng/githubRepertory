package com.hy.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.task.ButlerTaskService;
import cn.cuco.service.wechat.wechatMessage.IWechatMessageSender;
import cn.cuco.wechat.util.WechatUrl;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath*:/spring-junit-config/applicationContext-test.xml","classpath*:/spring/applicationContext-apps.xml"
	,"classpath*:/spring/applicationContext.xml","classpath*:/spring/spring-context-jedis.xml"}) //加载配置文件  
//@ContextConfiguration(locations={"classpath:applicationContext-test.xml","classpath:application-test.properties"}) //加载配置文件  
public class BaseTest {
	
	@Autowired
    private IWechatMessageSender send;
	
	private final String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=";
	
	private final String userListUrl = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	@Test
	public void test(){
		String getUrl = url.replace("ACCESS_TOKEN", "vqIjF8ZASi5Lg8Ts33Wz7sDjpVu_k2Z79SX7u4eUbzFJ6NgBdaS0cWa7TcwPfTWbennk7x74RhImOqbuv4HRwWjT7LWlSly5toQDp4AlF7TNuHsluCv6_Jio8i9rcFjnOJXdAAASNB");
		try {
			String json = WechatUrl.getRemoteJson(getUrl, RequestMethod.GET.toString());
			WechatUserOpenId wechatUserInfo = JSONObject.parseObject(json, WechatUserOpenId.class);
			OpenId getOpenId = wechatUserInfo.getData();
			List<Object> list = new ArrayList<>();
			List<Object> copyList = new ArrayList<>();
			Map<String,Object> map = new HashMap<String, Object>();
			for(String openId : getOpenId.getOpenid()){
				//System.out.println(openId);
				Map<String,Object> openIdMap = new HashMap<>();
				openIdMap.put("openid", openId);
				list.add(openIdMap);
				if(list.size() > 100){
					copyList.add(openIdMap);
				}
				if(list.size() >= 200){
					break;
				}
			}
			map.put("user_list", copyList);
			String param = JSON.toJSONString(map);
			String getUserListUrl = userListUrl.replace("ACCESS_TOKEN", "vqIjF8ZASi5Lg8Ts33Wz7sDjpVu_k2Z79SX7u4eUbzFJ6NgBdaS0cWa7TcwPfTWbennk7x74RhImOqbuv4HRwWjT7LWlSly5toQDp4AlF7TNuHsluCv6_Jio8i9rcFjnOJXdAAASNB");
			String userJson = WechatUrl.getRemoteJson(getUserListUrl, RequestMethod.POST.toString(),param);
			UserInfo userInfo = JSONObject.parseObject(userJson, UserInfo.class);
			List<WechatUserInfo> wechatUserInfoList = userInfo.getUser_info_list();
			for(WechatUserInfo user : wechatUserInfoList){
				System.out.println(user.getNickname());
				/*cn.cuco.entity.Test test = new cn.cuco.entity.Test();
				test.setName(user.getNickname());
				send.insert(test);;*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testgetAll(){
	/*	List<cn.cuco.entity.Test> list = send.getAll();
		for(cn.cuco.entity.Test t : list){
			System.out.println(t.getName());
		}*/
	}
}
