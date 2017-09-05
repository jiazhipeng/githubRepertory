package com.hy.gcar.service.message;

import com.hy.weixin.entity.WechatNotice;
import com.hy.weixin.entity.WxTemplate;

public interface MessageService {
	
	/**
	* 任务处理 消息发送
	* @Title: message 
	* @Description: TODO
	* @author q.p.x
	* @param     
	* @return void    
	* @date 2016年4月24日 下午6:31:39 
	* @throws
	 */
	public void taskProcessing(String openID); 
	
	/**
	 * 订单任务提醒
	* @Title: orderMessage 
	* @Description: TODO
	* @author q.p.x
	* @param       
	* @return void    
	* @date 2016年4月24日 下午6:35:52 
	* @throws
	 */
	public void orderMessage(String openID);
	
	/**
	 * 发送微信消息模板
	* @Title: orderMessage 
	* @Description: TODO
	* @author q.p.x
	* @param       
	* @return void    
	* @date 2016年4月24日 下午6:35:52 
	* @throws
	 */
	public void sendMessageWxTemplate(WxTemplate wxTemplate);

	/**
	 * 持久化保存消息
	 * @param wxTemplate
	 * @throws Exception 
	 */
	public  void saveMessage(WxTemplate wxTemplate,WechatNotice wechatNotice) throws Exception;

}
