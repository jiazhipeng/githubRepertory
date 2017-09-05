package cn.cuco.service.wechat.wechatMessage.impl;

import cn.cuco.service.wechat.wechatMessage.IWechatMessageFactory;
import cn.cuco.service.wechat.wechatMessage.template.parent.WechatMsgParent;

/**
 * 
* @ClassName: FactoryEngine 
* @Description: wechatMessage工厂引擎 入口
* @author jiaxiaoxian
* @date 2017年2月21日 上午11:39:38
 */
public abstract class FactoryEngine implements IWechatMessageFactory{
	
	/**
	 * 微信用户的openId
	 */
	protected String openId;
	
	/**
	 * 微信推送通知的入参对象
	 */
	protected WechatMsgParent wechatMsgParent;

	@Override
	public void startEngine(String openId, WechatMsgParent wechatMsgParent) {
		
		this.openId = openId;
		this.wechatMsgParent = wechatMsgParent;
	}

}
