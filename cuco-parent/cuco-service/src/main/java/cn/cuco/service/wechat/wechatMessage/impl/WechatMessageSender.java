package cn.cuco.service.wechat.wechatMessage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.service.wechat.wechatMessage.IWechatMessageFactory;
import cn.cuco.service.wechat.wechatMessage.IWechatMessageSender;
import cn.cuco.service.wechat.wechatMessage.template.parent.WechatMsgParent;
import cn.cuco.wechat.push.util.WechatMsgUtil;

/**
 * 
* @ClassName: WechatMessageSender 
* @Description: wechatMessage工厂发送者
* @author jiaxiaoxian
* @date 2017年2月21日 上午11:51:03
 */
@Service
public class WechatMessageSender implements IWechatMessageSender{
	
	@Autowired
	private IWechatMessageFactory wechatMessagefactory;

	@Override
	public void sendmessage(String openId, String wechatTemplateType, 
			WechatMsgParent wechatMsgParent) throws Exception {
		
		Map<String, Object> msg = getMessage(openId, wechatTemplateType, wechatMsgParent);
		//发送
		WechatMsgUtil.send(msg);
	}
	
	private Map<String, Object> getMessage(String openId, String wechatTemplateType,
			WechatMsgParent wechatMsgParent){
		
		Map<String, Object> msg = null;
		
		msg = getByType(openId,wechatTemplateType,wechatMsgParent);
		
		return msg;
	}
	
	/**
	 * 
	* @Title: getByType 
	* @Description: 消息制造
	* @author jiaxiaoxian
	* @param openId
	* @param wechatTemplateType
	* @param wechatMsgParent
	* @return
	* @return Map<String,Object>
	* @throws
	 */
	private Map<String, Object> getByType(String openId, String wechatTemplateType,
			WechatMsgParent wechatMsgParent){
		
		Map<String, Object> result = new HashMap<String, Object>();
		//启动工厂工作引擎
		wechatMessagefactory.startEngine(openId, wechatMsgParent);
		//根据type创建指定的模板消息   见cn.cuco.enums.WechatMsgType
		switch (wechatTemplateType) {
		//此处的  1必须在  cn.cuco.enums.WechatMsgType配置 枚举
		case "1":
			result = wechatMessagefactory.createTestMessage();
			break;
		case "2"://送车送车中
			result = wechatMessagefactory.createSendInCarMessage();
			break;
		case "3"://送车已到达
			result = wechatMessagefactory.createSendArrivedMessage();
			break;
		case "4"://送车已完成
			result = wechatMessagefactory.createSendCompletedMessage();
			break;
		case "5"://取车取车中
			result = wechatMessagefactory.createGetTakeCarMessage();
			break;
		case "6"://取车已到达
			result = wechatMessagefactory.createGetArrivedMessage();
			break;
		case "7"://取车待入库
			result = wechatMessagefactory.createGetPendingStorageMessage();
			break;
		case "8"://限号通知
			result = wechatMessagefactory.createCarRestrictionMessage();
			break;
		case "9"://送车客服通知
			result = wechatMessagefactory.createSendTaskToCustomer();
			break;
		case "10"://取车客服通知
			result = wechatMessagefactory.createGetTaskToCustomer();
			break;
		case "11"://客服分配管家送车通知
			result = wechatMessagefactory.createSendTaskCustomerToOperater();
			break;
		case "12"://客服分配管家取车通知
			result = wechatMessagefactory.createGetTaskCustomerToOperater();
			break;
		case "13"://管家接单给车务通知
			result = wechatMessagefactory.createSendOrderTakingtoCarService();
			break;
		case "14"://管家接车完成送车入库给车务通知
			result = wechatMessagefactory.createOperaterGettoCarService();
			break;
		default:
			break;
		}
		return result;
	}

	
}
