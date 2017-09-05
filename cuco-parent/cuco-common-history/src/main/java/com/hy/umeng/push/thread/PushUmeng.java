package com.hy.umeng.push.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hy.common.utils.SpringContextHolder;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.service.MessageAppService;
import com.hy.umeng.push.service.impl.MessageAppServiceImpl;

public class PushUmeng implements Runnable{
		private static Logger log = LoggerFactory.getLogger(PushUmeng.class);


        private MessageAppService messageService = SpringContextHolder.getBean(MessageAppServiceImpl.class);


        private MessageUmeng messageUmeng;


        public PushUmeng(MessageUmeng messageUmeng) {
            this.messageUmeng = messageUmeng;
        }

        @Override
        public void run() {
            try {
            	log.info("............异步发送app通知消息............");
                messageService.sendMessageByAndroidAndIos(messageUmeng);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }