package com.hy.weixin.notice.push;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.AccessTokenUtils;
import com.hy.common.utils.HttpClientUtils;
import com.hy.common.utils.SpringContextHolder;
import com.hy.project.service.wechat.notice.WechatNoticeService;
import com.hy.project.service.wechat.notice.impl.WechatNoticeServiceImpl;
import com.hy.weixin.entity.WechatNotice;
import com.hy.weixin.entity.WxTemplate;

public class Push implements Runnable{
		private static Logger log = LoggerFactory.getLogger(Push.class);


		private WechatNoticeService wechatNoticeService = SpringContextHolder.getBean(WechatNoticeServiceImpl.class);

		private WxTemplate wxTemplate;

		public Push(WxTemplate wxTemplate){
			this.wxTemplate = wxTemplate;
		}
		
		
		@Override
		public void run() {
			try {
	        	log.info(".......发送模板消息开始......");
	        	String param = JSONObject.toJSONString(wxTemplate);
	        	log.info("发送的报文为>>>>>>>："+ param);
	        	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + AccessTokenUtils.getAccessToken();
				log.info("发送的url 为:"+ url);
				//JSONObject result = this.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+JedisUtils.getObject("accesstoken"), "POST",JSONObject.toJSONString(t));
				String result = HttpClientUtils.sendPost(url, param);
				log.info("返回的报文结果为>>>>>>:" + result);

				//插入数据库
				WechatNotice wechatNotice = new WechatNotice();
				Map<String,Object> resultMap = JSON.parseObject(result);
				if("0".equals(resultMap.get("errcode").toString())){
					wechatNotice.setStatuts(1);
				}else {
					wechatNotice.setStatuts(-1);
				}

				wechatNotice.setReturnData(result);
				wechatNoticeService.saveMessage(wxTemplate, wechatNotice);
	        } catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		
	}