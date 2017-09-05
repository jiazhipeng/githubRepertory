package cn.cuco.service.wechat.event.impl;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import cn.cuco.service.wechat.event.EventsService;
import cn.cuco.wechat.entity.WeChatReceiveMsg;

@Service
public class ClickEventsServiceImpl implements EventsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
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
		WeChatReceiveMsg wxmsg = new WeChatReceiveMsg();
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

		return message;
	}

}
