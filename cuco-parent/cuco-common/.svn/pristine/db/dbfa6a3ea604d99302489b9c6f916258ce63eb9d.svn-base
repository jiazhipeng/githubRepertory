package cn.cuco.push.wechat.util;

import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.cuco.httpservice.HttpClientUtils;
import cn.cuco.wechat.util.AccessTokenUtil;

public class WechatMsgUtil {

	private final static String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	public static int send(Map<String, Object> json) throws Exception{
		
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
	}
}
