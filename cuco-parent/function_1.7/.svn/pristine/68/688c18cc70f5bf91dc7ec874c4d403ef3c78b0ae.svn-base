package com.hy.gcar.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.PropertiesLoader;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.gcar.service.weixin.factory.WechatEventFactory;
import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.entity.WeChatReceiveMsg;
import com.hy.weixin.entity.WeiXinCallBack;
import com.hy.weixin.utils.WeiXinUtils;

@Controller
public class CallBackController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("index")
	public String index(@RequestParam(value = "url") String url, String callbackparam) {
		try {
			String newurl = URLEncoder.encode(url, "UTF-8");
			logger.info("------------- " + url + " -----------");
			String redirectPath = WeiXinUtils.getUrlBySnsapibase(newurl);
			return "redirect:" + redirectPath;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * look
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/toshare", method = RequestMethod.GET)
	public String toshare(@RequestParam(value = "code") String code) throws Exception {
		W3AccessToken acctoken = WeiXinUtils.getAccToken(code);
		String oppid = acctoken.getOpenid();
		logger.info("================================" + code);

		logger.info("================================" + oppid);
		return "/gcar/share/share";
	}

	/**
	 * 验证token
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping("/weixin")
	public void weixin(WeiXinCallBack weiXinCallBack,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		logger.info("weixin callback begin......");
		PropertiesLoader load = new PropertiesLoader("weixin.properties");
		String token = load.getProperty("token");
		
		String signature = weiXinCallBack.getSignature();// 微信加密签名
		String timestamp = weiXinCallBack.getTimestamp(); //时间戳
		String nonce = weiXinCallBack.getNonce();//随机数
		
		String tmpStr = "";
		String returnMessage = "success";
		try {
			tmpStr = WeiXinUtils.getSHA1(token, timestamp, nonce);
			if(!tmpStr.equals(signature)){
				return;
			}
			String method = request.getMethod();
			logger.info("@@@@@@@@@@@@method:"+method);
			if(!method.equals("POST")){
				logger.info("@@@@@@@@@@@@getEchostr:"+weiXinCallBack.getEchostr());
				this.returnMessage(weiXinCallBack.getEchostr(), response);
				return;
			}
			
			WeChatReceiveMsg msg = WeiXinUtils.getMsg(request.getInputStream());
			this.logger.info("微信回掉消息为>>>>>>>>>>>:" + JSONObject.toJSONString(msg));
			String msgType = msg.getMsgType();
			
			if (msgType.equals("event")) {//接收事件消息
				String event = msg.getEvent();
				EventsService eventsService = WechatEventFactory.create(event);
				if(eventsService!=null){
					returnMessage = eventsService.response(msg);
				}
			}else {//接收普通消息
				msg.setEventKey("but_lianxikefu");
				EventsService eventsService = WechatEventFactory.create("click");
				if(eventsService!=null){
					returnMessage = eventsService.response(msg);
				}
			}
			
		} catch (Exception e1) {
			logger.error("callback validate exception {}",e1);
			this.returnMessage(returnMessage, response);
		}
		this.returnMessage(returnMessage, response);
		logger.info("weixin callback end......");
	}



	private void returnMessage(String message, HttpServletResponse response) throws IOException {
		PrintWriter write = response.getWriter();
		write.print(message);
		write.flush();
		write.close();
	}
	
}
