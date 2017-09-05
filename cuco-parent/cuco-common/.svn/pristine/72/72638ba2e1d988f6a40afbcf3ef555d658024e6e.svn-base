package cn.cuco.wechat.push.util;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.cuco.httpservice.HttpClientUtils;
import cn.cuco.threadUtil.ThreadUtil;
import cn.cuco.wechat.util.AccessTokenUtil;

public class WechatMsgUtil {

	
	  public static void send(Map<String, Object> json){
	        Runnable push = new Push(json);
	        ThreadUtil.fixedThreadPool.execute(push);
	        try {
	            TimeUnit.SECONDS.sleep(2);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	
	/*private static int send(Map<String, Object> json) throws Exception{
		
		int resultCode = 0;
		if(json != null && json.size() > 0){
			
			String url = new StringBuilder("").append(SEND_MESSAGE_URL).append(AccessTokenUtil.getAccessToken()).toString();
			String param = JSON.toJSONString(json);
			String result = HttpClientUtils.sendPost(url, param);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.containsKey("errcode")){
				resultCode = Integer.parseInt(resultJson.getString("errcode"));
				if(resultCode == 40001){
					AccessTokenUtil.refreshAccessToken();
				}
			}
		}
		return resultCode;
	}*/
}
