package com.hy.gcar.service.weixin.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.entity.Marketing;
import com.hy.gcar.service.marketing.MarketingService;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.weixin.entity.WeChatReceiveMsg;

/**
 * Created by 海易出行 on 2016/9/20.
 */
@Service
public class ScanEventsServiceImpl implements EventsService {

    @Autowired
    SubscribeEventsServiceImpl subscribeEventsService;

    @Autowired
    MarketingService marketingService;

    @Override
    public String response(WeChatReceiveMsg msg) throws Exception {
        String eventKey = msg.getEventKey();
        String message = null;
        if (StringUtils.isNotBlank(eventKey)) {
            Long id = null;
            if (eventKey.startsWith("qrscene_member_")) {// 推荐
                id = Long.parseLong(eventKey.replace("qrscene_member_", ""));
            } else if (eventKey.startsWith("marketing_")) {// 活动
                id = Long.parseLong(eventKey.replace("marketing_", ""));
            }
            Marketing marketing = new Marketing();
            marketing.setId(id);
            List<Marketing> marketingList = marketingService.getMarketingList(marketing);
            if (!CollectionUtils.isEmpty(marketingList)) {
                marketing = marketingList.get(0);
                message  = marketing.getWelcomeLanguage();
            }
        }
        if(StringUtils.isEmpty(message)){
        	return "";
        }
        return subscribeEventsService.focusOnPush(msg,message);
    }
}
