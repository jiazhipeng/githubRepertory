package com.hy.gcar.service.message.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.message.MessageService;
import com.hy.gcar.thread.Push;
import com.hy.project.service.wechat.notice.WechatNoticeService;
import com.hy.thread.ThreadUtil;
import com.hy.weixin.entity.TemplateData;
import com.hy.weixin.entity.WechatNotice;
import com.hy.weixin.entity.WxTemplate;


@Service
public class MessageServiceImpl implements MessageService{

	private static Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private WechatNoticeService wechatNoticeService;

	@Autowired
	private MemberService memberService;

	@Override
	public void taskProcessing(String openID) {
		log.info("--- 任务处理通知 ---");
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser(openID);  //openID
        t.setTopcolor("#000000"); //
        t.setTemplate_id("0E9_E__9BKjSMvtdsScoTUw6Bjj8RyW1LSnbmCp6BJs"); // 模板id获取从 https://mp.weixin.qq.com/advanced/tmplmsg?action=list&t=tmplmsg/list&token=1472737226&lang=zh_CN 
        Map<String,TemplateData> m = new HashMap<String,TemplateData>(); 
        
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("您有新的转派任务，请尽快处理");  
        m.put("first", first);
        
        
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");
        keyword1.setValue("企业合作(XXX)");  
        m.put("keyword1", keyword1); 
        
        
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("待处理");  
        m.put("keyword2", keyword2); 
        
        TemplateData remark = new TemplateData();
        remark.setColor("#000000");  
        remark.setValue("");  
        m.put("remark", remark); 
        t.setData(m);  
        
    	String param = JSONObject.toJSONString(t);
    	System.out.println(param);
    	log.info("发送的报文为>>>>>>>："+ param);
        
//        try {
//        	log.info(".......发送模板消息开始......");
//        	String param = JSONObject.toJSONString(t);
//        	log.info("发送的报文为>>>>>>>："+ param);
//        	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+JedisUtils.getObject("accesstoken");
//			log.info("发送的url 为:"+ url, JSONObject.toJSONString(t));
//			//JSONObject result = this.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+JedisUtils.getObject("accesstoken"), "POST",JSONObject.toJSONString(t));
//			String result = PostUtil.sendPost(url, param);
//			log.info("返回的报文结果为>>>>>>:" + result);
//        } catch (Exception e) {
//			e.printStackTrace();
//		}		
//		
	}
	
	@Override
	public void orderMessage(String openID) {
		log.info("--- 订单消息提醒  ---");
		
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser("");  
        t.setTopcolor("#000000");  
        t.setTemplate_id("EEuwIb8DgxCadQGAl3Usks_R3J6p7lhCqfjjPc_Nw8M");  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
        
        
        /**
         * 
		{{first.DATA}}
		商品信息：{{keyword1.DATA}}
		订单金额：{{keyword2.DATA}}
		订单状态：{{keyword3.DATA}}
		{{remark.DATA}}
		在发送时，需要将内容中的参数（{{.DATA}}内为参数）赋值替换为需要的信息
		内容示例
		尊敬的小新，您的订单（121185885425）已下单成功！
		商品信息：小米 NOTE4
		订单金额：￥2563.00
		订单状态：支付成功
		您的订单我们已收到，接下来我们将会按排人员进去发货，敬请关注！
         */
        
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("恭喜您已获得XXX（当前状态下套餐名称）使用权");
        m.put("first", first);  
        
        
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue("基础套餐");
        m.put("keyword1", keyword1);  
        
        
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("8000");
        m.put("keyword2", keyword2);  
        
        
        
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue("已付款");
		m.put("keyword3", keyword3);
        
        TemplateData remark = new TemplateData();  
        remark.setColor("#000000");  
        remark.setValue(""); 
        //remark.setValue("您的订单我们已收到，接下来我们将会按排人员进去发货，敬请关注!");
        m.put("remark", remark); 
        t.setData(m);  
    	String param = JSONObject.toJSONString(t);
    	System.out.println(param);
    	log.info("发送的报文为>>>>>>>："+ param);
//        try {
//        	log.info(".......发送模板消息开始......");
//        	String param = JSONObject.toJSONString(t);
//        	log.info("发送的报文为>>>>>>>："+ param);
//        	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+JedisUtils.getObject("accesstoken");
//			log.info("发送的url 为:"+ url, JSONObject.toJSONString(t));
//			//JSONObject result = this.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+JedisUtils.getObject("accesstoken"), "POST",JSONObject.toJSONString(t));
//			String result = PostUtil.sendPost(url, param);
//			log.info("返回的报文结果为>>>>>>:" + result);
//        } catch (Exception e) {
//			e.printStackTrace();
//		}		
		
	}
	
	
	/**
	 * 服务受理通知
	 */
	public static void serviceAcceptanceNotification(){
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser("");  
        t.setTopcolor("#000000");  
        t.setTemplate_id("xvklA_de7pwKOhdc_8y_HErl7BnkZ0MlAzg5DbqX3mg");  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
        
        
        /**
         * 
		{{first.DATA}}
		服务类型：{{keyword1.DATA}}
		受理时间：{{keyword2.DATA}}
		{{remark.DATA}}
         */
        
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("本次代充电服务结束，期待再次为您服务");
        m.put("first", first);  
        
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue("代充电服务");
        m.put("keyword1", keyword1);  
        
        
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("2016-01-08 15:26");
        m.put("keyword2", keyword2);  
        t.setData(m);  
    	String param = JSONObject.toJSONString(t);
    	System.out.println(param);
	}
	
	
	/**
	 * 平安行消息通知	
	 */
	public static void pingAnInformationNotice(){
		
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser("");  
        t.setTopcolor("#000000");  
        t.setTemplate_id("AtzUMrKrND41TDG6nl-Gg2dguU6WhfGjrDj4hcwBrOQ");  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
        
        
        /**
         * 
		{{first.DATA}}
		服务类型：{{keyword1.DATA}}
		受理时间：{{keyword2.DATA}}
		{{remark.DATA}}
         */
        
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("平安行消息通知");
        m.put("first", first);  
        
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue("");
        m.put("keyword1", keyword1);  
        
        
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("");
        m.put("keyword2", keyword2);  
    	
    	
		 TemplateData remark = new TemplateData();  
	     remark.setColor("#000000");  
	     remark.setValue(""); 
	     m.put("remark", remark); 
	     t.setData(m);  
	 	String param = JSONObject.toJSONString(t);
	 	System.out.println(param);
	 	log.info("发送的报文为>>>>>>>："+ param);
	}

	/**
	 * 绑定通知
	 */
	public  static void  bindingNotification(){
		WxTemplate t = new WxTemplate();
		t.setUrl("");
		t.setTouser("");
		t.setTopcolor("#000000");
		t.setTemplate_id("TbPNnSIUJFEa5dgV4xXxsEiFXXcGyS6rAIRsz3e_00I\n");
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();

		/**
		 *
		 {{first.DATA}}
		 帐号：{{keyword1.DATA}}
		 备注：{{keyword2.DATA}}
		 {{remark.DATA}}
		 */

		TemplateData first = new TemplateData();
		first.setColor("#000000");
		first.setValue("共用人添加通知");
		m.put("first", first);

		TemplateData keyword1 = new TemplateData();
		keyword1.setColor("#000000");
		keyword1.setValue("XXXXXX（显示会员手机号） ");
		m.put("keyword1", keyword1);


		TemplateData keyword2 = new TemplateData();
		keyword2.setColor("#000000");
		keyword2.setValue("您已成为XXX（会员名称）的共用人，可共同享用XXX（套餐名称）权益。   ");
		m.put("keyword2", keyword2);


		TemplateData remark = new TemplateData();
		remark.setColor("#000000");
		remark.setValue("");
		m.put("remark", remark);
		t.setData(m);
		String param = JSONObject.toJSONString(t);
		System.out.println(param);
		log.info("发送的报文为>>>>>>>："+ param);
	}
	
	public static void main(String[] args) {
		MessageServiceImpl.bindingNotification();
	}

	/**
	 * 持久化保存消息
	 * @param wxTemplate
	 * @throws Exception 
	 */
	@Override
	public void saveMessage(WxTemplate wxTemplate,WechatNotice wechatNotice) throws Exception{

		log.info("保存微信发送消息到数据库.....");
		Member member = new Member();
		member.setOpenid(wxTemplate.getTouser());
		Member members = memberService.findMemberByOpenID(wxTemplate.getTouser());

		Long memberID = null;
		String memberName = null;
		if(null != members){
			memberID = members.getId();
			memberName = members.getName();
		}


		wechatNotice.setCreated(new Date());
		wechatNotice.setJsondata(JSON.toJSONString(wxTemplate));
		wechatNotice.setCount(1);
		wechatNotice.setOpenid(wxTemplate.getTouser());

		wechatNotice.setMemberId(memberID);
		wechatNotice.setMemberName(memberName);
		wechatNotice.setUrl(wxTemplate.getUrl());
		wechatNotice.setSended(new Date());
		wechatNotice.setTemplateId(wxTemplate.getTemplate_id());

		log.info("保存的消息为>>>>>>>>>>>>>>>" + JSON.toJSONString(wechatNotice));
		wechatNoticeService.insertSelective(wechatNotice);


	}
	@Override
	public void sendMessageWxTemplate(WxTemplate wxTemplate) {
		Runnable push = new Push(wxTemplate);
		ThreadUtil.fixedThreadPool.execute(push);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			log.info("开启线程发送微信消息"+e);
		}

//		fixedThreadPool.shutdown();
//		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);  
//		for(int i = 0;i<20;i++){
//			fixedThreadPool.execute(push);
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		
	}

}
