package com.hy.security.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DesUtil;
import com.hy.common.utils.Global;
import com.hy.common.utils.HttpUtils;
import com.hy.security.entity.PermissionExchange;
import com.hy.security.service.permissionExchange.PermissionExchangeService;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

/**运营车辆定时任务
 * 定时扫描临时表，通知权限变更
 */
public class ChangeAuthWebsoketQuartz {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private PermissionExchangeService permissionExchangeService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:MM:ss");
	
	/**
	 *  每5秒执行一次
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	public void changeCarOpeToOperating(){
		
		List<PermissionExchange> systemIds=permissionExchangeService.findSystemIds();
		for (PermissionExchange change : systemIds) {
			PermissionExchange exchange = new PermissionExchange();
			exchange.setIsSend(0);
			Long systemId = change.getSystemId();
			exchange.setSystemId(systemId);
			List<PermissionExchange> allExchangesOfSystem = permissionExchangeService.selectByCondition(exchange);
			Set<Long> set = new HashSet<>();
			List<PermissionExchange> notSends = new ArrayList<>();
			for (PermissionExchange permissionExchange : allExchangesOfSystem) {
				Long userId = permissionExchange.getUserId();
				if(userId!=null){
					if(!set.contains(userId)){
						set.add(userId);
						notSends.add(permissionExchange);
					}
				}
			}
			set.clear();
//			List<PermissionExchange> notSends = permissionExchangeService.selectNotSendExchange(exchange);
			if(1==systemId.longValue()&&notSends.size()>0){
				String returnMessage = sendMessage(notSends, systemId, "secretKey"+systemId);
				batchUpdate(returnMessage, allExchangesOfSystem);
			}else if(2==systemId.longValue()){
				String returnMessage = sendMessage(notSends, systemId, "secretKey"+systemId);
				batchUpdate(returnMessage, allExchangesOfSystem);
			}
		}
		
		
	}
	public String sendMessage(List<PermissionExchange> list,Long systemId,String systemFlag){
		if(list!=null && list.size()>0){
			
			Header[] array = {new BasicHeader("Content-Type","application/json"),new BasicHeader("Accept","application/json;charset=UTF-8")};
			String requestUrl=Global.getConfig(String.valueOf(systemId));
			logger.info("子系统接口url>>>>>>>>>>>>>>>>>>>"+requestUrl);
			CloseableHttpResponse respone=null;
			try {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String key = "hycx!@#$";
				logger.info("加密后的串>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+DesUtil.encrypt(Global.getConfig(systemFlag)+uuid, key));
				String encrypt = DesUtil.encrypt(Global.getConfig(systemFlag)+uuid, key);
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				map.put("key", encrypt);
				map.put("exchange", JSON.toJSONString(list));
				String jsonString = JSON.toJSONString(map);
				logger.info("调用子系统结构传递的参数：》》》》》》》》》》》》"+jsonString);
				HttpEntity httpEntity = new StringEntity(jsonString,"UTF-8");
				
				respone = HttpUtils.sendPost(requestUrl, array, httpEntity);
				if(respone.getStatusLine().getStatusCode()==200){
					HttpEntity entity = respone.getEntity();
					InputStream in = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));  
					StringBuffer sb = new StringBuffer();  
					String str="";
					while ((str = reader.readLine()) != null)  
					{  
						sb.append(str);  
					}
					in.close();
					logger.info("回调系统返回信息：》》》》》》》》》》》》》》》》》》》》》"+sb.toString());
					return sb.toString();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public void batchUpdate(String returnMessage,List<PermissionExchange> list){
		JSONObject jsonObject = JSON.parseObject(returnMessage);
		String code = (String) jsonObject.get("code");
		if("0000".equals(code)){
			logger.info("批量更新已经发送的通知");
			permissionExchangeService.updateBatch(list);
		}
	}
}
