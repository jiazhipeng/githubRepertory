package com.hy.gcar.thread;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.hy.common.utils.SpringContextHolder;
import com.hy.gcar.service.message.MessageService;
import com.hy.gcar.service.message.impl.MessageServiceImpl;
import com.hy.weixin.entity.WechatNotice;
import com.hy.weixin.entity.WxTemplate;
import com.hy.weixin.utils.WechatSend;

public class Push implements Runnable{
	
		private static Logger log = LoggerFactory.getLogger(Push.class);

		private  MessageService messageService = SpringContextHolder.getBean(MessageServiceImpl.class);

		private WxTemplate wxTemplate;

		public Push(WxTemplate wxTemplate){
			this.wxTemplate = wxTemplate;
		}
		
		@Override
		public void run() {
			String result = WechatSend.sendMessage(wxTemplate);
			try {
				//插入数据库
				WechatNotice wechatNotice = new WechatNotice();
				Map<String,Object> resultMap = JSON.parseObject(result);
				if("0".equals(resultMap.get("errcode").toString())){
					wechatNotice.setStatuts(1);
				}else {
					wechatNotice.setStatuts(-1);
				}
				wechatNotice.setReturnData(result);
				messageService.saveMessage(wxTemplate, wechatNotice);
			} catch (Exception e) {
				log.info("微信消息发送后保存消息入库失败"+e);
			}
		}
		
	}