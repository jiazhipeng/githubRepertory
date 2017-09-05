package com.hy.test.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.Order;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.message.MessageService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.message.impl.WechatMessageServiceImpl;
import com.hy.gcar.service.powerUserd.PowerUsedCostLogService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.service.test.TestService;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.service.MessageAppService;
import com.hy.weixin.entity.WxTemplate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath*:/spring/spring-mvc.xml","classpath*:/spring/applicationContext.xml"}) //加载配置文件  
public class MessageSendTest {
	
	@Autowired
	WechatMessageService wechatMessageService;
	
	@Autowired
	MessageAppService messageAppService;

	@Autowired
	MessageService messageService;


	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberService memberService;
	@Autowired
	CarOperatePlanService carOperatePlanService;
	@Autowired
	PowerUsedService powerUsedService;
	@Autowired
	PowerUsedCostLogService powerUsedCostLogService;
	@Autowired
	CarOperateLogService carOperateLogService;
	@Autowired
	TestService testService;
	
	@Test
	public void toExamineNotThrough() {

		Member m = new Member();
		m.setOpenid("oFCx8wIjCXRVLu4JxaAh7fOLpZuk");
		m.setFocusType(1);
		Order order = new Order();
		order.setTotal(new BigDecimal(80000));
		wechatMessageService.toExamineNotThrough(m, order);


		String message = WechatMessageServiceImpl.BINDING_NOTIFICATION;

		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser("oFCx8wIjCXRVLu4JxaAh7fOLpZuk");

		messageService.sendMessageWxTemplate(wxTemplate);


	}

	@Test
	public void auditPass() {
		MessageUmeng messageUmeng = new MessageUmeng();
//		//推送消息给安卓和ios
		messageUmeng.setApplicationType(0);
		messageUmeng.setAlias("111");
		messageUmeng.setTitle("海易出行");
		messageUmeng.setTicker("系统");
		messageUmeng.setMessageContent("111");
		messageUmeng.setCreateUser("111");
		messageUmeng.setBeginTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		messageUmeng.setReadStatus(0);//未读消息
		try {
			messageAppService.sendMessageByAndroidAndIosAsync(messageUmeng);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void test(){

		Map<String,Object> obj = Maps.newHashMap();
		MemberInfo memberInfo = null;
		try {
			memberInfo = memberInfoService.findMemberInfoByMemberID(Long.valueOf("6520"));

			Member member = memberService.findMemberByID(Long.valueOf("6520"));

			obj.put("idcardFront", memberInfo.getIdcardFront());
			obj.put("idcardBack", memberInfo.getIdcardBack());
			obj.put("drivercardOriginal", memberInfo.getDrivercardOriginal());
			obj.put("drivercardCopy", memberInfo.getDrivercardCopy());
			obj.put("memberId",memberInfo.getMemberId()+"");
			obj.put("auditStatus",member.getUseCarApproved()+"");
			System.out.println(JSONObject.toJSONString(new OpenResponse("true", "成功", obj, "100000")));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	@Test
	public void palnTest(){
		try {
		for (int i = 0; i < 100; i++) {
			CarOperatePlan p = new CarOperatePlan();
//			p.setBegin("2016-12-1 :8:00");
			p.setCreated(new Date());
			p.setStarted(DateUtils.parseDate("2016-12-1 08:00"));
			p.setEnded(DateUtils.parseDate("2016-12-9 08:00"));
			p.setStatus(1);
			p.setOperateStatus(2);
//			p.setMemberId(2756L);
//			p.setMemberName("王春涛");
//			p.setMemberMobile("13021080179");
			p.setCarTypeId(10L);
			carOperatePlanService.insertSelective(p);
			System.out.println("chengg");
		}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void readExcelTest(){
		 try {  
			 List<Map<String, Object>> lists = ReadExcel.exportListFromExcel(new File("D:\\td_power_used.xls"), 0);  
		        System.out.println(lists);
		        for (Map<String, Object> map : lists) {
		        	PowerUsed powerUsed = new PowerUsed();
					for (String key:map.keySet()) {
						System.out.println(key);
						System.out.println(map.get(key));
						
						String v=map.get(key)+"";
						if(key.startsWith("A")){
							powerUsed.setMemberId(Long.valueOf(v));
						}else if(key.startsWith("B")){
							powerUsed.setMemberItemId(Long.valueOf(v));
						}else if(key.startsWith("C")){
							powerUsed.setCaroperateId(Long.valueOf(v));
						}else if(key.startsWith("D")){
							powerUsed.setMemberType(Integer.parseInt(v));
						}else if(key.startsWith("E")){
							if(map.get(key) instanceof Date){
								powerUsed.setCarUsedTime((Date)map.get(key));
							}
						}else if(key.startsWith("F")){
							powerUsed.setCarUsedDay(Integer.parseInt(v));
						}else if(key.startsWith("G")){
							powerUsed.setCarUsedAddress(v);
						}else if(key.startsWith("H")){
							if(map.get(key) instanceof Date){
								powerUsed.setCarReturnTime((Date)map.get(key));
							}
						}else if(key.startsWith("I")){
							powerUsed.setCarReturnAddress(v);
						}else if(key.startsWith("J")){
							powerUsed.setCarTypeId(Long.valueOf(v));
						}else if(key.startsWith("K")){
							powerUsed.setUsedStatus(Integer.parseInt(v));
						}else if(key.startsWith("L")){
							powerUsed.setCityCode(v);
						}else if(key.startsWith("M")){
							powerUsed.setMemberItemName(v);
						}else if(key.startsWith("N")){
							if(map.get(key) instanceof Date){
							powerUsed.setCreated((Date)map.get(key));
							}
						}else if(key.startsWith("O")){
							if(map.get(key) instanceof Date){
							powerUsed.setLasttimeModify((Date)map.get(key));
							}
						}
					}
					System.out.println(powerUsed.toString());
					powerUsedService.insertSelectiveReadExcel(powerUsed);//注释掉是实现层 created
					
					ButlerTask butlerTask = testService.createData(powerUsed);
					carOperateLogService.autoCreateUseCarLog(powerUsed, butlerTask);
					powerUsedCostLogService.deductionOfFare(butlerTask, powerUsed);
				}
		        System.out.println("=========================================================================================");
		          
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		
		
	}

	
}
