package cn.cuco.service.wechat.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.cuco.service.wechat.event.EventsService;
import cn.cuco.wechat.entity.WeChatReceiveMsg;

/**
 * Created by 海易出行 on 2016/9/20.
 */
@Service
public class ScanEventsServiceImpl implements EventsService {

    @Autowired
    SubscribeEventsServiceImpl subscribeEventsService;

    @Override
    public String response(WeChatReceiveMsg msg) throws Exception {
    	return "";
    }
}
