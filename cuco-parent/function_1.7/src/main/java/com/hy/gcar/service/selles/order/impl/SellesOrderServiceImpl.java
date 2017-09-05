package com.hy.gcar.service.selles.order.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.common.utils.DateUtils;
import com.hy.common.utils.HttpClientUtils;
import com.hy.common.utils.MD5Util;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.entity.Channel;
import com.hy.gcar.entity.ChannelDetail;
import com.hy.gcar.entity.Coupon;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Order;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.channel.ChannelDetailService;
import com.hy.gcar.service.channel.ChannelService;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.coupon.CouponService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.order.OrderService;
import com.hy.gcar.service.selles.order.SellesOrderService;


/**
 * 销售意向Service
 * @author 海易出行
 *
 */
@Service
public class SellesOrderServiceImpl implements SellesOrderService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;
	@Autowired
	private BasicNoticeService basicNoticeService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ChannelDetailService channelDetailService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ChannelService channelService;
	
	@Override
	public Order createSellesOrder(Order order,Member member) throws Exception {
		
		Order order_member = new Order();
		order_member.setMemberId(member.getId());
		List<Order> order_members = this.orderService.findOrderByComplete(order_member);
		if(CollectionUtils.isNotEmpty(order_members)){
			//表示为升级
			order.setClueType(1);
		}else{
			//购买
			order.setClueType(0);
		}
		order.setStatus(1);//新一期需求初始化状态全部为待处理
		order.setCreated(new Date());
		order.setMemberId(member.getId());
		order.setMemberName(member.getName());
		order.setSureName(member.getSureName());
		order.setMemberMobile(member.getMobile());
		order.setOrderNum(createOrderNum());
		order.setCityId("2");
		order.setCityName("北京");
		if(5==order.getOrderFrom()&&null!=order.getOrderFromId()){
			//渠道来源,根据传过来的渠道code得到渠道ID
			Channel channel = new Channel();
			channel.setCode(order.getOrderFromId());
			List<Channel> channelList =  this.channelService.selectByCondition(channel);
			
		/*	//由于下面发短信的channel.getName()为空，所以set一下，by lei.c
			Channel channelName = new Channel();
			channelName = channelList.get(0);
			channel.setName(channelName.getName());*/
			
			if(CollectionUtils.isNotEmpty(channelList)){
				channel = channelList.get(0);
				ChannelDetail channelDetail = new ChannelDetail();
				channelDetail.setChannelId(channelList.get(0).getId());
				//Channel channel = new Channel();
				//channel = this.channelService.findById(order.getOrderFromId());
				List<ChannelDetail>  channelDetailList= this.channelDetailService.selectByCondition(channelDetail);
				String reword = "";
				for(ChannelDetail channelD : channelDetailList){
					if(null == channelD.getCouponCount()){
						continue;
					}
					reword += channelD.getCouponName()+"优惠券"+channelD.getCouponCount()+"张。";
					Coupon coupon = this.couponService.findById(channelD.getCouponId());
					int count = channelD.getCouponCount();
					for(int i=0;i<count;i++){
						//循环发送优惠券
						CouponInfo couponInfo = new CouponInfo();
						couponInfo.setGetTime(new Date());
						couponInfo.setStatus(2);
						couponInfo.setMemberId(member.getId());
						couponInfo.setMemberName(member.getName());
						couponInfo.setCouponFrom(1);
						couponInfo.setCouponId(coupon.getId());
						couponInfo.setCouponNum("HY"+ RandomStringUtils.randomNumeric(8));
						couponInfo.setCouponName(coupon.getName());
						couponInfo.setCouponType(coupon.getCouponType());
						couponInfo.setPrice(coupon.getPrice());
						couponInfo.setCreated(couponInfo.getGetTime());
						couponInfo.setLasttimeModify(couponInfo.getCreated());
						couponInfo.setValid(1);
						couponInfo.setOutTime(DateUtils.addDays(couponInfo.getGetTime(), coupon.getDaysAfter()));
						this.couponInfoService.insertSelective(couponInfo);
					}
					
				}
				if(CollectionUtils.isNotEmpty(channelDetailList)){
					//给用户发短信推送
//					this.sendMessage("尊敬的极车会员，恭喜您通过"+channel.getName()+"提交信息获得"+reword+"您可以用当前手机号码登录“极车公社”app查看获得奖励。",member.getMobile());
					//上面的模板不对，重新修改，by lei.c
					this.sendMessage("感谢您对极车公社的关注。赠送给您"  + reword + "您可以在极车公社用户端或微信公众号中查看。",member.getMobile());
				}
			}
			
		}
		
		BasicNotice b = new BasicNotice();
		//查询正常的状态
		b.setStatus(0);
		//查询正常的状态
		b.setNoticeType(6);
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
		long memberId;
		Member member_notice = new Member();
		if(CollectionUtils.isNotEmpty(basicNotices)){
			b = basicNotices.get(0);
			memberId = b.getNoticeUserId();
			member_notice = this.memberService.findMemberByID(memberId);
		}
		order.setModifier(member_notice.getSysuserName());
		order.setModifierId(member_notice.getSysuserId());
		order.setItemId(null);
		return this.orderService.createOrder(order);

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
		
	/**生成订单号方法
	 * @param 
	 * @return 订单编号(产品代码＋年月日＋4位随机数)
	 */
	public String createOrderNum(){
		StringBuilder orderNum = new StringBuilder();
		//得到产品code用于拼接订单号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		orderNum.append(sdf.format(new Date()));
		String randomStr = RandomStringUtils.randomNumeric(8);
		orderNum.append(randomStr);
		this.logger.info("生成订单编号为---"+orderNum.toString());
		return orderNum.toString();
	}


}
