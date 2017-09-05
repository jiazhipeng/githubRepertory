package com.hy.security.websoket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.hy.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.hy.security.entity.User;

public class SystemWebSocketHandler implements WebSocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
	

	private static final ArrayList<WebSocketSession> users = new ArrayList<>();

	    
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception {
		  logger.info("websocket connection closed......");
		try {
			logger.info("websocket connection closed session" + arg1.toString() + "\t" + JsonUtil.objectToJson(session.getAttributes()));
		}catch (Exception e){

		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		logger.info("connect to the websocket success......");
		session.sendMessage(new TextMessage("connect to the websocket success"));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.info("客户端发给server的消息是：======="+message.toString());
		
		session.sendMessage(new TextMessage("this  is a websocket message"));
//		sendMessageToUsers(new TextMessage("this  is a websocket message"));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1) throws Exception {
		  users.remove(session);
		  if(session.isOpen()){
	            session.close();
	       }
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	 /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
            	Map<String, Object> attributes = user.getAttributes();
            	for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            		String openid=entry.getKey().substring(0, entry.getKey().indexOf("_"));
            		logger.info("======openid>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>========="+openid);
            		if(openid.equals("openid1")){
            			if (user.isOpen()) {
                            user.sendMessage(message);
                            User userMode = (User) entry.getValue();
                            logger.info("给用户：》》》"+userMode.getName()+"发送了消息：》》》》》"+message.getPayload());
                        }
            		}else {
            			 user.sendMessage(message);
                         User userMode = (User) entry.getValue();
                         logger.info("给用户：》》》"+userMode.getName()+"发送了消息：》》》》》"+message.getPayload());
					}
				}
                if (user.isOpen()) {
                    user.sendMessage(message);
//                    user.get
                    logger.info("给用户：》》》"+user.getPrincipal().getName()+"发送了消息：》》》》》"+message.getPayload());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().containsKey(userName) && user.getAttributes().get(userName).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                        logger.info("给用户：》》》"+userName+"发送了消息：》》》》》"+message.getPayload());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
