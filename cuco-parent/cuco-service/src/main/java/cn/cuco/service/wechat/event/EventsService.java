package cn.cuco.service.wechat.event;

import cn.cuco.wechat.entity.WeChatReceiveMsg;

/**
 * 
* @ClassName: EventsService 
* @Description: 时间接口
* @author jiaxiaoxian
* @date 2017年3月7日 下午3:51:48
 */
public interface EventsService {
	
	
	public String response(WeChatReceiveMsg msg) throws Exception;

}
