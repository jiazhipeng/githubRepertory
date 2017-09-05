package cn.cuco.wechat.push.template;

import java.util.HashMap;
import java.util.Map;

import cn.cuco.wechat.push.template.messageManager.WechatMsgTemplate;

/**
* @ClassName: TaskMessage 
* @Description: first:您有新的送车任务，请尽快处理
* @Description: info:送车任务
* @Description: type:待处理
* @author huanghua
* @date 2017年3月6日 下午2:05:15
 */
public class TaskMessage extends WechatMsgTemplate{

	private String info;

	private String type;

	@Override
	public Map<String, Object> getSendMessage() {
		
		Map<String, Object> dataJson = this.getDateJson();
		
		Map<String, Object> resultJson = this.getResultJson();
		
		dataJson.put("keyword1", this.getInfoJson());
		
		dataJson.put("keyword2", this.getTypeNameJson());
		
		resultJson.put("date", dataJson);
		
		return null;
	}
	
	private Map<String, Object> getTypeNameJson(){
		
		Map<String, Object> json = new HashMap<String, Object>();
		
		json.put("value", this.getType());
		
		json.put("color", this.getColor());
		
		return json;
	}
	
	private Map<String, Object> getInfoJson(){
		
		Map<String, Object> json = new HashMap<String, Object>();
		
		json.put("value", this.getInfo());
		
		json.put("color", this.getColor());
		
		return json;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
