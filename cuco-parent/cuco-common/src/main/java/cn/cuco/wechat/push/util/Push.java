package cn.cuco.wechat.push.util;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import cn.cuco.httpservice.HttpClientUtils;
import cn.cuco.wechat.util.AccessTokenUtil;

public class Push implements Runnable{
		
	private static Logger log = LoggerFactory.getLogger(Push.class);
	
	private final static String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	private Map<String, Object> json;
	
	public Push(Map<String, Object> json){
		this.json = json;
	}

	@Override
	public void run() {
		try {
	    	log.info(".......发送模板消息开始......");
	    	String param = JSONObject.toJSONString(json);
	    	log.info("发送的报文为>>>>>>>："+ param);
	    	int resultCode = 0;
			if(json != null && json.size() > 0){
				
				String url = new StringBuilder("").append(SEND_MESSAGE_URL).append(AccessTokenUtil.getAccessToken()).toString();
				String result = HttpClientUtils.sendPost(url, param);
				JSONObject resultJson = JSONObject.parseObject(result);
				if(resultJson.containsKey("errcode")){
					resultCode = Integer.parseInt(resultJson.getString("errcode"));
					if(resultCode == 40001){
						AccessTokenUtil.refreshAccessToken();
					}
				}
			}
	    } catch (Exception e) {
			e.printStackTrace();
		}		
	}
		
		
}