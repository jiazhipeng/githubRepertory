package com.hy.security.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DesUtil;
import com.hy.common.utils.JedisUtils;
import com.hy.common.utils.StringUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.httpservice.ResponseBody;
import com.hy.security.entity.ImageQRCodeInfo;
import com.hy.security.util.StringUtil;
import com.hy.security.websoket.SystemWebSocketHandler;
import com.hy.weixin.entity.WeChatReceiveMsg;

/**
 * Created by duzc on 2016/8/2.
 */
@RestController
public class CallBackController {
	
	private static Logger logger = LoggerFactory.getLogger(CallBackController.class);
	
	@Autowired
	private SystemWebSocketHandler systemWebSocketHandler;
	
	
	@RequestMapping("/wechatLogin")
	public void wechatLogin(String signature, String timestamp, String nonce,String echostr, HttpServletRequest request,HttpServletResponse response) {
		logger.info("weixin callback begin......");
		String token = Constant.WECHAT_TOKEN;
		System.out.println(token+"#################");
		String tmpStr = "";
		
		
		logger.info("----------signature----------"+signature);
		logger.info("----------timestamp----------"+timestamp);
		logger.info("----------nonce----------"+nonce);
		logger.info("----------echostr----------"+echostr);
		try {
			tmpStr = getSHA1(token, timestamp, nonce);
		} catch (NoSuchAlgorithmException e1) {
			logger.info("callback validate exception {}",e1);
		}
		if(!tmpStr.equals(signature)){
			logger.info("signature is not equal...."+tmpStr+" != "+signature);
			return;
		}
		String method = request.getMethod();
		if(!method.equals("POST")){
			replyWechatWithString(response,echostr);
			logger.info("wexin request method is not POST...."+method);
			return;
		}
		try {
			WeChatReceiveMsg msg = getMsg(request.getInputStream());
			logger.info("微信回掉消息为>>>>>>>>>>>:" + JSONObject.toJSONString(msg));
			String msgType = msg.getMsgType();
			if (msgType.equals("event")) {//接收事件消息
				String event = msg.getEvent();
				if("subscribe".equalsIgnoreCase(event) || "SCAN".equalsIgnoreCase(event)){//用户扫码后推送的事件
					validateQrCode(msg, request,response);
				}
			}
		} catch (Exception e) {
			logger.info("when deal with callback :" + e.getMessage());
		}
		replyWechatWithString(response,"success");
		logger.info("weixin callback end......");
	}
	
	/**
	 * 用SHA1算法生成安全签名
	 * 
	 * @param token
	 *            票据
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机字符串
	 * @param encrypt
	 *            密文
	 * @return 安全签名
	 * @throws NoSuchAlgorithmException
	 * @throws AesException
	 */
	private String getSHA1(String token, String timestamp, String nonce) throws NoSuchAlgorithmException {
		String[] array = new String[] { token, timestamp, nonce };
		StringBuffer sb = new StringBuffer();
		// 字符串排序
		Arrays.sort(array);
		for (int i = 0; i < 3; i++) {
			sb.append(array[i]);
		}
		String str = sb.toString();
		// SHA1签名生成
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(str.getBytes());
		byte[] digest = md.digest();

		StringBuffer hexstr = new StringBuffer();
		String shaHex = "";
		for (int i = 0; i < digest.length; i++) {
			shaHex = Integer.toHexString(digest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexstr.append(0);
			}
			hexstr.append(shaHex);
		}
		return hexstr.toString();
	}
	
	private WeChatReceiveMsg getMsg(InputStream request) throws Exception {

		WeChatReceiveMsg receiveMsg = new WeChatReceiveMsg();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(request);

		logger.info("--------------callback-request-------------"+JSONObject.toJSONString(doc));
		
		Element root = doc.getDocumentElement();// 获得根节点
		NodeList msgs = root.getChildNodes();
		for (int i = 0; i < msgs.getLength(); i++) {// 遍历元素
			Node msg = msgs.item(i);
			if (msg.getNodeType() == Node.ELEMENT_NODE) {// 如果是元素节点

				String nodeName = msg.getNodeName().trim();// 获得元素的名
				String nodeValue = msg.getTextContent();
				//String nodeValue = msg.get
				if (nodeName.equals("ToUserName")) {
					receiveMsg.setToUserName(nodeValue);
				} else if (nodeName.equals("FromUserName")) {
					receiveMsg.setFromUserName(nodeValue);
				} else if (nodeName.equals("CreateTime")) {
					receiveMsg.setCreateTime(nodeValue);
				} else if (nodeName.equals("MsgType")) {
					receiveMsg.setMsgType(nodeValue);
				} else if (nodeName.equals("Ticket")) {
					receiveMsg.setTicket(nodeValue);
				} else if (nodeName.equals("Event")) {
					receiveMsg.setEvent(nodeValue);
				} else if (nodeName.equals("EventKey")) {
					receiveMsg.setEventKey(nodeValue);
				} else if (nodeName.equals("Content")) {
					receiveMsg.setContent(nodeValue);
				}else if(nodeName.equals("Latitude")){//获取维度
					receiveMsg.setLatitude(nodeValue);
				}else if(nodeName.equals("Longitude")){//获取经度
					receiveMsg.setLongitude(nodeValue);
				}else if(nodeName.equals("Precision")){//获取精度
					receiveMsg.setPrecision(nodeValue);
				}
			}
		}
		return receiveMsg;
	}
	
	private void validateQrCode(WeChatReceiveMsg msg, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sceneId = msg.getEventKey();
		sceneId = sceneId.replace("qrscene_", "");
		String openId = msg.getFromUserName();
		String user = DesUtil.encrypt(sceneId+"", "HY_LOGIN");
		//给用户推送消息
		ResponseBody begin = new ResponseBody(ServerStatus.QRCODE_CHECKING.getCode(),ServerStatus.QRCODE_CHECKING.getMessage());
		systemWebSocketHandler.sendMessageToUser(user, new TextMessage(JSONObject.toJSONString(begin)));
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("openid", "");
		ResponseBody result = new ResponseBody(ServerStatus.QRCODE_INVALID.getCode(),ServerStatus.QRCODE_INVALID.getMessage(),map);
		boolean exists = JedisUtils.exists(StringUtil.getFullScenId(sceneId));
		if(!exists){
			//不存在key(1.恶意请求 2.已经失效)，给用户提示已失效
			logger.info("redis中不存在key{}",StringUtil.getFullScenId(sceneId));
			systemWebSocketHandler.sendMessageToUser(user, new TextMessage(JSONObject.toJSONString(result)));
			return;
		}
		String info = JedisUtils.get(StringUtil.getFullScenId(sceneId));
		if(StringUtils.isBlank(info)){
			//失效
			logger.info("redis中不存在{}对应的值",StringUtil.getFullScenId(sceneId));
			systemWebSocketHandler.sendMessageToUser(user, new TextMessage(JSONObject.toJSONString(result)));
			return;
		}
		ImageQRCodeInfo code = JSONObject.parseObject(info,ImageQRCodeInfo.class);
		if(code.isScanned()){
			//失效
			logger.info("redis中存在{}对应的值{},但二维码已经失效",StringUtil.getFullScenId(sceneId),info);
			systemWebSocketHandler.sendMessageToUser(user, new TextMessage(JSONObject.toJSONString(result)));
			return;
		}
		code.setScanned(true);
		String jsonStr = JSONObject.toJSONString(code);
		int redis_timeOut = 60000;//Constant.IMAGEQRCODE_TIMEOUT;//TODO  记得更新二维码失效时间   在此写死
		JedisUtils.set(StringUtil.getFullScenId(sceneId), jsonStr, redis_timeOut);
		map.put("openid", openId);
		ResponseBody result0 = new ResponseBody(ServerStatus.SUCCESS.getCode(),ServerStatus.SUCCESS.getMessage(),map);
		systemWebSocketHandler.sendMessageToUser(user, new TextMessage(JSONObject.toJSONString(result0)));
	}
	
	private void replyWechatWithString(HttpServletResponse response,String content){
		try {
			PrintWriter write = response.getWriter();//.getWriter().write(echostr);
			write.print(content);
			write.flush();
			write.close();
		} catch (IOException ex) {
			logger.info("when replay callback :" + ex.getMessage());
		}
	}
}
