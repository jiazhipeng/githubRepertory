package com.hy.gcar.web.wap.selles;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.common.utils.HttpClientUtils;
import com.hy.common.utils.JedisUtils;
import com.hy.common.utils.MD5Util;
import com.hy.constant.Constant;
import com.hy.gcar.entity.Captcha;
import com.hy.gcar.entity.Card;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Order;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.card.CardService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.order.OrderService;
import com.hy.gcar.service.selles.order.SellesOrderService;

@Controller	
@RequestMapping("/wap/selles/order")
public class SellesOrderController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellesOrderService sellesOrderService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WechatMessageService wechatMessageService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CardService cardService;
	
	/**
	 * 获取验证码
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/getcaptcha")
	@ResponseBody
	public Map<String,String> getCaptcha(String mobile){
		String key = "captcha_wap_"+mobile;
		//用于前台判断结果
		Map<String,String> resultMap = new HashMap<String, String>();
		try{
			Captcha captcha = new Captcha();//(Captcha)JedisUtils.getObject(key);
			String curCode = RandomStringUtils.randomNumeric(6);
			String info = "验证码："+curCode+"(10分钟内有效，如非本人操作请忽略)";
			boolean flag = sendMessage(info,mobile);
			if(flag){
				logger.info("验证码{}发送至{}成功",curCode,mobile);
				captcha.setCode(curCode);
				captcha.setCreateDate(new Date());
				captcha.setUsed(false);
				captcha.setCount(1L);
				JedisUtils.setObject(key, captcha, 600);
			}else{
				logger.info("验证码{}发送至{}失败",curCode,mobile);
				resultMap.put("reslut", "false");
				resultMap.put("reason", "验证码发送失败，请联系工作人员！");
				return resultMap;
			}
			resultMap.put("reslut", "true");
			resultMap.put("reason", "验证码发送成功");
			return resultMap;
//			return new Results(true,"验证码发送成功");
		}catch(Exception e){
			logger.error("验证码发送至{}失败",mobile,e);
		}
		resultMap.put("reslut", "false");
		resultMap.put("reason", "验证码发送失败！");
		return resultMap;
	}
	
	/*public boolean sendMessage(String message,String mobile){
		boolean result = false;
		String username = "hycx";
		String pwd = MD5Util.MD5Encode("hycx123");
		String epid = "120589";
		String linkid = RandomStringUtils.randomNumeric(12);
		try {
			String url = "http://114.255.71.158:8061/?username="+username+"&password="+pwd+"&message="+URLEncoder.encode(message, "gb2312")+"&phone="+mobile+"&epid="+epid+"&linkid="+linkid+"&subcode=";
			String responseBody = HttpClientUtils.sendGet(url, null, "UTF-8");
			if("00".equals(responseBody)){
	        	result = true;
	    	   logger.info("验证码发至{}送成功",mobile);
	        }else{
	    	   logger.error("验证码发至{}失败{}",mobile,responseBody);
	        }
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
   }*/
	public boolean sendMessage(String message,String mobile){
		boolean result = false;
		String userId = "JI1093";
		String password = "102102";
		String pszMobis = mobile;
		String pszMsg = message;
		String iMobiCount="1";
		String pszSubPort="*";
		String MsgId=RandomStringUtils.randomNumeric(19);
		try {
			String url = "http://101.251.214.153:8901/MWGate/wmgw.asmx/MongateSendSubmit?userId="+userId+"&password="+password+"&pszMobis="+pszMobis+"&pszMsg="+pszMsg+"&iMobiCount="+iMobiCount+"&pszSubPort="+pszSubPort+"&MsgId="+MsgId;
			String responseBody = HttpClientUtils.sendGet(url, null, "UTF-8");
			logger.info("调用短信接口======================返回值===="+responseBody);
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-5168371777020179491</string>
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-12</string>
			 if(StringUtils.isNotBlank(responseBody)){
				 String sub=responseBody.substring(74, responseBody.lastIndexOf("</string>"));
				 logger.info("截取返回值================="+sub);
				 if(sub.length()>15){//返回值长度大于15则表示成功
					 result = true;
					 logger.info("验证码发至{}送成功",mobile);
				 }else{
					 logger.error("验证码发至{}失败{}",mobile,responseBody);
				 }
			  } 
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
	}
	
	
	
	/**微信创建订单 验证码加去重
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/createVerify",method = RequestMethod.GET)
	public @ResponseBody Map<String,String>  createVerify(Order order,String mobile,String code1){
		//用于前台判断结果
		Map<String,String> resultMap = new HashMap<String, String>();
		
		if(StringUtils.isEmpty(mobile) && StringUtils.isNotEmpty(order.getMemberMobile())){
			mobile  = order.getMemberMobile();
		}
		//1.验证码的时效性、准确性
		boolean messageFlag = Boolean.parseBoolean(Constant.APPLICATION_LOAD.getProperty("message_check_flag"));
		String key = "captcha_wap_" + mobile;
		if(messageFlag){
			try{
				Captcha captcha = (Captcha)JedisUtils.getObject(key);
				if(null == captcha || !captcha.getCode().equals(code1)){
					logger.info("redis中不存在{}",key);
					resultMap.put("result", "false");
					resultMap.put("check","false");
					resultMap.put("reason", "验证码不正确！");
					return resultMap;
				}else{
					Date end = DateUtils.addMinutes(captcha.getCreateDate(), 10);
					if(end.before(new Date()) || captcha.isUsed()){//是否失效
						resultMap.put("result", "false");
						resultMap.put("check","false");
						resultMap.put("reason", "验证码已失效，请重新获取验证码");
						return resultMap;
					}
				}
				captcha.setUsed(true);
				JedisUtils.setObject(key, captcha, 0);
			}catch(Exception e){
				logger.error("{}登陆出现异常",mobile,e);
				resultMap.put("result", "false");
				resultMap.put("reason", "登录出现异常");
				return resultMap;
			}
		}
		
		return create(order);
	}
		
	
	
	
	/**微信创建订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/create",method = RequestMethod.GET)
	@ResponseBody
	public  Map<String,String>  create(Order order){
		this.logger.info("order>>>>>>" + JSONObject.toJSONString(order));

		Map<String,String> resultMap = new HashMap<String, String>();
		/*if(order.getOrderFrom() == null){  html1/2/3 有  4 没有 但有soure
			resultMap.put("result", "false");
			resultMap.put("check","false");
			resultMap.put("reason", "来源必须不为空..");
			return resultMap;
		}*/
		if(order.getOrderFrom() == null && order.getSource() != null){
			order.setOrderFrom(order.getSource());
		}
		
		if(order.getMemberMobile() == null){
			resultMap.put("result", "false");
			resultMap.put("check","false");
			resultMap.put("reason", "手机号必须不为空..");
			return resultMap;
		}
		if(order.getMemberName() == null){
			resultMap.put("result", "false");
			resultMap.put("check","false");
			resultMap.put("reason", "姓名必须不为空..");
			return resultMap;
		}
		
		/* 
		 * 1、首先判断系统是否存在该会员。如会员不存在，则创建会员同时创建入会申请意向订单并给销售发送微信通知（这种情况就不需要判断共用人以及未完成的订单了）
		 * 2、如果存在该用户，判断当前用户是否是别人的共用人（个人主共用人除外）；
		 * 3、判断当前用户有没有没完成的订单，如果存在未完成的订单不能创建新的订单；
		 * 4、以上条件满足创建订单，查询存在已完结的订单不，存在则是套餐升级，不存在则是入会申请；发送销售意向
		 */
		try {
			Member member = new Member();
			member.setMobile(order.getMemberMobile());
			List<Member> memberList = this.memberService.selectByCondition(member);
			if(CollectionUtils.isEmpty(memberList)){
				//不存在该用户的情况--创建会员同时创建入会申请意向订单并给销售发送微信通知（这种情况就不需要判断共用人以及未完成的订单了）
				//如果不为上面的判断，那么默认用户来源为极车产品，自主关注、推荐不会走这个方法。by lei.c
				member.setFromMember(6);
				
				if(1==order.getOrderFrom()){//微信
					member.setFromMember(2);
				}
				if(2==order.getOrderFrom()){//安卓
					member.setFromMember(4);
				}
				if(3==order.getOrderFrom()){//IOS
					member.setFromMember(5);
				}
				if(4==order.getOrderFrom()){//线下
					member.setFromMember(0);
				}
				if(6==order.getOrderFrom()){//销售名片
					member.setFromMember(8);
					//加销售名片ID
					member.setCardId(order.getOrderFromId());
					//card表上面的提交次数累计加1
					Card card = new Card();
					card = this.cardService.findById(order.getOrderFromId());
					int submitCount = 0;
					if(null!=card.getSubmitCount()){
						submitCount = card.getSubmitCount();
					}
					Card card_new = new Card();
					card_new.setId(order.getOrderFromId());
					card_new.setSubmitCount(submitCount+1);
					this.cardService.updateByPrimaryKeySelective(card_new);
				}
				if(5==order.getOrderFrom()){//渠道
					member.setFromMember(7);
					//渠道code
					member.setChannelId(order.getOrderFromId());
				}
				
				member.setName(order.getMemberName());
				member.setSureName(order.getMemberName());
				member = this.memberService.createPersonalMember(member);
				Order o = sellesOrderService.createSellesOrder(order,member);
				//wechatMessageService.sendMessageBySellesOrder(o);现在管家端没有消息入口了，所以去掉消息推送
			}
			else{
				member = memberList.get(0);
				//如果被冻结该用户不能被添加共用人
				if(0==member.getStatuts()){
					//表示该用户被冻结，不能被添加
					resultMap.put("result", "false");
					resultMap.put("reason","您已被冻结,请联系客服<br/><a href='tel:400-9029-858'><span class='tel' style='color:#337ab7'>400-9029-858</span></a>");
					return resultMap;
				}
				//校验当前用户是不是共用人，共用人不允许创建订单
				MemberItemShare memberItemShare = new MemberItemShare();
				memberItemShare.setMemberId(member.getId());
				List<MemberItemShare> memberItemshareList = this.memberItemShareService.selectByNoPersonalIsMain(memberItemShare);
				if(CollectionUtils.isNotEmpty(memberItemshareList)){
					resultMap.put("result", "false");
					resultMap.put("reason", "该会员存在共用人关系，不能创建销售意向");
					logger.info("该会员存在共用人关系，不能创建销售意向===="+member.getId());
					return resultMap;
				}
				//校验查询是否存在未完成的订单
				Order order_new = new Order();
				order_new.setMemberId(member.getId());
				List<Order> orderList = this.orderService.findOrderByNoComplete(order_new);
				if(CollectionUtils.isNotEmpty(orderList)){
					resultMap.put("result", "false");
					resultMap.put("reason", "您已经有一条线索正在处理，请耐心等待");
					logger.info("已有正在进行中的订单，不能创建====" + member.getId());
					return resultMap;
				}
				//都满足条件，创建订单
				Order o = sellesOrderService.createSellesOrder(order,member);
				//wechatMessageService.sendMessageBySellesOrder(o);
			}
			resultMap.put("result", "success");
			resultMap.put("reason", "提交销售意向成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", "false");
			resultMap.put("reason", "提交销售意向异常");

		}
		return resultMap;
		
	}
}
