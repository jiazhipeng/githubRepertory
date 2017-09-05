package com.hy.gcar.service.weixin.factory;

import com.hy.common.utils.SpringContextHolder;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.gcar.service.weixin.impl.*;

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
