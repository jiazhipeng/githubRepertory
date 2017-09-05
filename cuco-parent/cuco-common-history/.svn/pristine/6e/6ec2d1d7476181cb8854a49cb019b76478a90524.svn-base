package com.hy.umeng.push.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.common.utils.JsonUtil;
import com.hy.constant.Constant;
import com.hy.thread.ThreadUtil;
import com.hy.umeng.push.AndroidNotification;
import com.hy.umeng.push.PushClient;
import com.hy.umeng.push.android.AndroidCustomizedcast;
import com.hy.umeng.push.dao.MessageUmengMapper;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.ios.IOSCustomizedcast;
import com.hy.umeng.push.service.MessageAppService;
import com.hy.umeng.push.thread.PushUmeng;

@Service
public class MessageAppServiceImpl implements MessageAppService{

    public   Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private MessageUmengMapper messageManagerMapper;

    @Override
    public Map<String, Object> sendAndroidMessageAPush(MessageUmeng message) throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(Constant.YONGHAOCHE_ANDROID_KEY,Constant.YONGHAOCHE_ANDROID_SECRET);
        customizedcast.setAlias(message.getAlias(),message.getAliasType());
        customizedcast.setTicker(message.getTicker());
        customizedcast.setTitle(message.getTitle());
        customizedcast.setText(message.getMessageContent());
        customizedcast.goCustomAfterOpen(JsonUtil.extractJson(message.getJsonMap()));
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        customizedcast.setProductionMode();
        if(Constant.PUSH_IS_TEST_MODE){
            customizedcast.setTestMode();
        }else{
            customizedcast.setProductionMode();
        }
        PushClient client = new PushClient();
        return client.send(customizedcast);
    }

    @Override
    public Map<String, Object> sendIosMessageAPush(MessageUmeng message) throws Exception {
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(Constant.YONGHAOCHE_IOS_KEY,Constant.YONGHAOCHE_IOS_SECRET);
        customizedcast.setAlias(message.getAlias(), message.getAliasType());
        customizedcast.setAlert(message.getMessageContent());
        customizedcast.setBadge(1);
        customizedcast.setSound("default");

        if(Constant.PUSH_IS_TEST_MODE){
            customizedcast.setTestMode();
        }else{
            customizedcast.setProductionMode();
        }
        PushClient client = new PushClient();
        return client.send(customizedcast);
    }

    @Override
    public void sendMessageByAndroidAndIos(MessageUmeng message) throws Exception {
        //发送给 android
        Map<String, Object> map = null;
        try{
            map = this.sendAndroidMessageAPush(message);
        }catch (Exception e){
            for(int i = 1;i<=3;i++){
                logger.info("安卓消息发送失败....正在尝试第" + i + "次发送........失败原因...."+e.getLocalizedMessage());
                try{
                    map = this.sendAndroidMessageAPush(message);
                    break;
                }catch (Exception ex){
                    logger.info("安卓消息发送失败....正在尝试第" + i + "次发送失败........失败原因...."+ex.getLocalizedMessage());
                    if(i == 3){
                        throw ex;
                    }
                }
                TimeUnit.SECONDS.sleep(3);

            }
        }
        if(map.get("status").toString().equals("200")){
            message.setStatus(1);
        }else{
            message.setStatus(0);
        }
        message.setReceivingEquipment(2);//安卓
        message.setReturnMessage(map.get("result").toString());
        message.setExtendContent(map.get("postBody").toString());
        this.insertSelective(message);

        //发送给 ios
        try{
            map = this.sendIosMessageAPush(message);
        }catch(Exception e){
            for(int i = 1;i<=3;i++){
                logger.info("ios 消息发送失败....正在尝试第" + i + "次发送........失败原因...."+e.getLocalizedMessage());
                try{
                    map = this.sendAndroidMessageAPush(message);
                    break;
                }catch (Exception ex){
                    logger.info("ios 消息发送失败....正在尝试第" + i + "次发送失败........失败原因...."+ex.getLocalizedMessage());
                    if(i == 3){
                        throw ex;
                    }
                }
                TimeUnit.SECONDS.sleep(3);
            }


        }
        if(map.get("status").toString().equals("200")){
            message.setStatus(1);
        }else{
            message.setStatus(0);
        }
        //发送给 android
        message.setReturnMessage(map.get("result").toString());
        message.setMessageId(null);
        message.setReceivingEquipment(1);//ios
        message.setExtendContent(map.get("postBody").toString());
        this.insertSelective(message);
    }

    /**
     * app 异步发送消息
     * @param message
     */
    public  void  sendMessageByAndroidAndIosAsync(MessageUmeng message){
        PushUmeng pushUmeng = new PushUmeng(message);
        ThreadUtil.fixedThreadPool.execute(pushUmeng);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
    /**
     * 添加
     * @param message
     * @throws Exception
     */
    public void insertSelective(MessageUmeng message)throws Exception{
        messageManagerMapper.insertSelective(message);
    }
}
