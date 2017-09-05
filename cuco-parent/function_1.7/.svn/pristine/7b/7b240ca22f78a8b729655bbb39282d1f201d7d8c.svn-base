package com.hy.gcar.service.weixin.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hy.gcar.entity.WechatMenu;
import com.hy.gcar.service.wechatmenu.WechatMenuService;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.weixin.entity.WeChatReceiveMsg;
import com.hy.weixin.entity.WxBtnMsgBack;
import com.thoughtworks.xstream.XStream;

@Service
public class ClickEventsServiceImpl implements EventsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WechatMenuService wechatMenuService;
	
	@Override
	public String response(WeChatReceiveMsg msg) throws Exception {
		
//		String eventKey = msg.getEventKey();
//		String message = "";
//		if (!StringUtils.isBlank(eventKey)) {
//			if (eventKey.startsWith("but_tiyanshijia")) {//体验试驾
//				message = "终于等到你~~\n预约体验试驾，拨打服务热线：400-6091-510（工作日9:00-18:00)";
//			} else if (eventKey.startsWith("but_lianxikefu")) {//联系客服 
//				message = "我是社员甲，有问题尽管问我！\n点击左下角键盘直接给我留言；你也可以拨打官方服务热线 : 400-6091-510（工作日9:00-18:00)";
//			}else if(eventKey.startsWith("btn_daolujiuyuan")){//||道路救援
//				message = "拨打官方服务热线400-6091-510（工作日9:00—18:00），若满足救援条件管家2小时内免费救援。";
//			}else if (eventKey.startsWith("btn_yuyuechongdian")) {// 预约充电
//				message = "拨打官方服务热线400-6091-510（工作日9:00—18:00），专人管家为您取车充电。";
//			}
//		}
//		//发送 回复文本消息  
//		/**
//		 * <xml>
//			<ToUserName><![CDATA[toUser]]></ToUserName>
//			<FromUserName><![CDATA[fromUser]]></FromUserName>
//			<CreateTime>12345678</CreateTime>
//			<MsgType><![CDATA[text]]></MsgType>
//			<Content><![CDATA[你好]]></Content>
//			</xml>
//		*/
//		
		String message = this.getResponseMessage(msg.getEventKey());
		Map<String,String> param = Maps.newHashMap();
		param.put("ToUserName", msg.getToUserName());
		param.put("FromUserName", msg.getFromUserName());
		param.put("CreateTime", System.currentTimeMillis()/1000/60+"");
		param.put("MsgType", "text");
		param.put("Content", message);
//
		WxBtnMsgBack wxmsg = new WxBtnMsgBack();
		wxmsg.setToUserName(msg.getFromUserName());
		wxmsg.setFromUserName(msg.getToUserName());
		wxmsg.setCreateTime(System.currentTimeMillis()/1000/60/60+"");
		wxmsg.setMsgType("text");
        wxmsg.setContent(message);
		//hwInfo.setDiskInfos(diskInfos);
		
		XStream xStream = new XStream();  
        xStream.autodetectAnnotations(true);  
        String xml = xStream.toXML(wxmsg); 
        this.logger.info("发送的报文为>>>>>>>：" + xml);
        return xml; 		
	}
	
	/**
	 * 获取自动回复内容
	 * @param eventKey
	 * @return
	 */
	public String getResponseMessage(String eventKey){
		//String message = "我是社员甲，有问题尽管问我！\n点击左下角键盘直接给我留言；你也可以拨打官方服务热线 : 400-6091-510（工作日9:00-18:00)";
		String message = "我是严肃的大极，有问题尽管问我！\n" +
				"点击左下角键盘直接给我留言；你也可以拨打官方服务热线 : \n400-6091-510（我们可是7*24接听电话的噢/::D)";

		List<WechatMenu> list = wechatMenuService.getWechatMenuList();
		for(WechatMenu menu : list){
			String mainKey = menu.getKey();
			if(StringUtils.isNotBlank(mainKey) && eventKey.equals(mainKey)){
				String mainMsg = menu.getMessage();
				if(StringUtils.isNotBlank(mainMsg)){
					return mainMsg;
				}else{
					return message;
				}
			}
			List<WechatMenu> sonList = JSON.parseArray(menu.getSubMenu(), WechatMenu.class);
			if(CollectionUtils.isNotEmpty(sonList)){
				for(WechatMenu son : sonList){
					String sonKey = son.getKey();
					if(StringUtils.isNotBlank(sonKey) && eventKey.equals(sonKey)){
						String sonMsg = son.getMessage();
						if(StringUtils.isNotBlank(sonMsg)){
							return sonMsg;
						}else{
							return message;
						}
					}
				}
			}
		}
		return message;
	}

}
