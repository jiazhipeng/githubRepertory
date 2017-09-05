package com.hy.security.websoket;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.hy.common.utils.DesUtil;
import com.hy.common.utils.JedisUtils;
import com.hy.security.util.StringUtil;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);
	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
	  		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		logger.info(request.getURI().getQuery());
		String param = request.getURI().getQuery();
		if(StringUtils.isNotBlank(param)){
			String token = param.substring(param.indexOf("=")+1);
			String sceneId = DesUtil.decrypt(token, "HY_LOGIN");
			boolean exists = JedisUtils.exists(StringUtil.getFullScenId(sceneId));
			if(!exists){
				//不存在key(1.恶意请求 2.已经失效)，不接受websocket请求
				logger.info("redis中不存在token{}对应的值，不接受websocket请求",token);
				return false;
			}
			logger.info("==========websocket====token======================="+token);
			attributes.put(token,token);
		}
        return true;
	}

}
