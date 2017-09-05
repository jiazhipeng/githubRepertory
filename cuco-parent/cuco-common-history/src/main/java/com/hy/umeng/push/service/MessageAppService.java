package com.hy.umeng.push.service;

import java.util.Map;

import com.hy.umeng.push.entity.MessageUmeng;

public interface MessageAppService {

    /**
     * 友盟 android 单独推送
     * @param message
     */
    public Map<String,Object> sendAndroidMessageAPush(MessageUmeng message) throws Exception;


    /**
     * ios单独推送
     * @param message
     * @return
     * @throws Exception
     */
    public Map<String,Object> sendIosMessageAPush(MessageUmeng message) throws Exception;


    /**
     * 安卓和ios 推送
     * @param message
     * @throws Exception
     */
    public void sendMessageByAndroidAndIos(MessageUmeng message) throws Exception;


    public  void sendMessageByAndroidAndIosAsync(MessageUmeng message)throws  Exception;

}
