package cn.cuco.push.wechat.template;

import java.util.HashMap;
import java.util.Map;

import cn.cuco.push.wechat.template.messageManager.WechatMsgTemplate;

/**
 * 
* @ClassName: TestMessage 
* @Description: 测试demo 发送wechat消息， 竞拍成功通知
* @Description: 竞拍成功通知模板（测试）
* @Description: first:恭喜您以神勇的表现力压群雄,成功拍得宝贝
* @Description: productName:蒙娜丽莎的微笑
* @Description: price:1200000
* @Description: remark:请在72小时内支付，点击详情进行支付
* @author jiaxiaoxian
* @date 2017年2月21日 上午10:39:29
 */
public class TestMessage extends WechatMsgTemplate{

	private String productName;
	
	private String price;

	@Override
	public Map<String, Object> getSendMessage() {
		
		Map<String, Object> dataJson = this.getDateJson();
		
		Map<String, Object> resultJson = this.getResultJson();
		
		dataJson.put("keyword1", this.getProductNameJson());
		
		dataJson.put("keyword2", this.getPriceJson());
		
		resultJson.put("date", dataJson);
		
		return null;
	}
	
	private Map<String, Object> getProductNameJson(){
		
		Map<String, Object> json = new HashMap<String, Object>();
		
		json.put("value", this.getProductName());
		
		json.put("color", this.getColor());
		
		return json;
	}
	
	private Map<String, Object> getPriceJson(){
		
		Map<String, Object> json = new HashMap<String, Object>();
		
		json.put("value", this.getPrice());
		
		json.put("color", this.getColor());
		
		return json;
	}

	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
