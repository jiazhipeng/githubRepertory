package cn.cuco.service.wechat.event.factory;


import cn.cuco.common.utils.springApplicationContext.SpringContextHolder;
import cn.cuco.service.wechat.event.EventsService;
import cn.cuco.service.wechat.event.impl.ClickEventsServiceImpl;
import cn.cuco.service.wechat.event.impl.LocationEventsServiceImpl;
import cn.cuco.service.wechat.event.impl.ScanEventsServiceImpl;
import cn.cuco.service.wechat.event.impl.SubscribeEventsServiceImpl;
import cn.cuco.service.wechat.event.impl.UnsubscribeEventsServiceImpl;

public class WechatEventFactory {
	
	
	public static EventsService create(String event){
		if("subscribe".equalsIgnoreCase(event)){
			return SpringContextHolder.getBean(SubscribeEventsServiceImpl.class);
		}else if("unsubscribe".equalsIgnoreCase(event)){
			return SpringContextHolder.getBean(UnsubscribeEventsServiceImpl.class);
		}else if(event.equalsIgnoreCase("click")){
			return SpringContextHolder.getBean(ClickEventsServiceImpl.class);
		}else if(event.equalsIgnoreCase("LOCATION")){
			return SpringContextHolder.getBean(LocationEventsServiceImpl.class);
		}else if(event.equalsIgnoreCase("SCAN")){
			return SpringContextHolder.getBean(ScanEventsServiceImpl.class);

		}
		return null;
		
	}

}
