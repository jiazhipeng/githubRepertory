package com.hy.weixin.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.AccessTokenUtils;
import com.hy.common.utils.HttpClientUtils;
import com.hy.weixin.entity.WxTemplate;

public class WechatSend{
		private static Logger log = LoggerFactory.getLogger(WechatSend.class);

		/**
		 * 发送微信消息 util
		* @author:JIAZHIPENG  
		* @time:2016-9-8 上午10:48:16   
		* @return String
		 */
		public static String sendMessage(WxTemplate wxTemplate) {
			String result = "";
			try {
	        	log.info(".......发送模板消息开始......");
	        	String param = JSONObject.toJSONString(wxTemplate);
	        	log.info("发送的报文为>>>>>>>："+ param);
	        	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + AccessTokenUtils.getAccessToken();
				log.info("发送的url 为:"+ url);
				//JSONObject result = this.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+JedisUtils.getObject("accesstoken"), "POST",JSONObject.toJSONString(t));
				result = HttpClientUtils.sendPost(url, param);
				log.info("返回的报文结果为>>>>>>:" + result);
	        } catch (Exception e) {
				log.info("发送微信消息报错"+e);
			}	
			return result;
		}
	}