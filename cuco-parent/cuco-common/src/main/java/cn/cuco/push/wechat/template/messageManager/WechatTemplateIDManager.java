package cn.cuco.push.wechat.template.messageManager;


/**
 * 
* @ClassName: WechatTemplateIDManager 
* @Description: 微信消息模板id管理者，在调用微信服务端发送消息时用到
* @author jiaxiaoxian
* @date 2017年2月21日 上午10:10:36
 */
public class WechatTemplateIDManager {

	/**
	 * 测试wechat模板id
	 */
	public static final String TEST_MSG_ID = RelyProperties.getProperty("template.test.id", "wechat.properties");
}
