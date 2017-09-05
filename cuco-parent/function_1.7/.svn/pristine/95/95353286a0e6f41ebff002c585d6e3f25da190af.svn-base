package com.hy.gcar.service.message.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.constant.Constant;
import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.ButlerTaskType;
import com.hy.gcar.entity.Approve;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Order;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.message.MessageService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.utils.AppUtil;
import com.hy.weixin.entity.TemplateData;
import com.hy.weixin.entity.WxTemplate;

@Service
public class WechatMessageServiceImpl implements WechatMessageService {

	private  Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	MessageService messageService;
	@Autowired
	ItemService itemService;
	@Autowired
	ItemCartypeService itemCartypeService;
	@Autowired
	MemberItemCartypeService memberItemCartypeService;
	@Autowired
	CarTypeService carTypeService;
	@Autowired
	MemberService memberService;
	@Autowired
	MemberItemService memberItemService;
	@Autowired
	MemberItemShareService itemShareService;
	@Autowired
	BasicNoticeService basicNoticeService;
	@Autowired
	CarOperateService carOperateService;
	
	/**
	 * 基础 订单模板
	 */
	public static final String BASICS_ORDER_TEMPLATE = Constant.messageLoad.getProperty("BASICS_ORDER_TEMPLATE");

	/**
	 * 平安行消息通知模板
	 */
	public static final String PING_AN_INFORMATION_NOTICE = Constant.messageLoad.getProperty("PING_AN_INFORMATION_NOTICE");
	
	
	/**
	 * 服务受理通知
	 */
	public static final String SERVICE_ACCEPTANCE = Constant.messageLoad.getProperty("SERVICE_ACCEPTANCE");

	/**
	 *  绑定通知
	 */
	public static final String  BINDING_NOTIFICATION = Constant.messageLoad.getProperty("BINDING_NOTIFICATION");
	
	/**
	 *  任务处理
	 */
	public static final String TASK_NOTICE = Constant.messageLoad.getProperty("SERVICE_HANDLE");
	
	/**
	 *  扣费成功通知
	 */
	public static final String CONSUM_SUCCESS = Constant.messageLoad.getProperty("CONSUM_SUCCESS");
	
	/**
	 *  服务受理通知(任务)
	 */
	public static final String TASK_SERVICE_NOTIFICATION = Constant.messageLoad.getProperty("SERVICE_NOTIFICATION");
	
	/**
	 *  会员充值通知
	 */
	public static final String MEMBER_RECHARGE = Constant.messageLoad.getProperty("MEMBER_RECHARGE");
	
	/**
	 *  余额不足提醒
	 */
	public static final String BALANCE_LACKS = Constant.messageLoad.getProperty("BALANCE_LACKS");
	
	/**
	 *  用车审核通知
	 */
	public static final String AUDIT_NOTIFY = Constant.messageLoad.getProperty("AUDIT_NOTIFY");
	
	/**
	 * 域名
	 */
	public static final String domain = Constant.domain;
	
	/**
	 * 域名
	 */
	public static final String urlPrefix = Constant.load.getProperty("url_prefix");

    @Override
	public void toExamineNotThrough(Member member, Order order) {
		log.info("微信审核未通过消息通知开始.....");
		boolean v = verification(member, order);
		if(!v){
			return;
		}
		String itemName = "--";
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
			price = item.getPrice().toString();
		}
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser(member.getOpenid());
		
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(price);
		
		TemplateData status = data.get("keyword3");
		status.setValue(order.getStatusInfo());
		
		if(!"".equals(itemName)){
			TemplateData itemLevelName = data.get("keyword1");
			itemLevelName.setValue(itemName);
		}
		
		TemplateData title = data.get("first");
		title.setValue("您的申请未通过，需完善资料");
		messageService.sendMessageWxTemplate(wxTemplate);
	}

	@Override
	public void auditPass(Member member, Order order) {
		log.info("微信审核通过消息通知开始.....");
		boolean v = verification(member, order);
		if(!v){
			return;
		}
		if(null == member.getOpenid()){
			this.log.error("用户没有openId");
			return;
		}
		String itemName = "--";
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
			price = item.getPrice().toString();
		}
		
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser(member.getOpenid());
			
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(price);
		
		TemplateData status = data.get("keyword3");
		status.setValue("待签约");
		
		if(!"".equals(itemName)){
			TemplateData itemLevelName = data.get("keyword1");
			itemLevelName.setValue(itemName);
		}
		
		TemplateData title = data.get("first");
		title.setValue("您的申请已通过，可进行签约");
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}

	private boolean verification(Member member, Order order) {
		if(member == null){
			log.info("微信审核未通过开始..... member 为空");
			return false;
		}
		if(order == null){
			log.info("微信审核未通过开始..... order 为空");
			return false;
		}
		
		if(member.getFocusType().intValue() != 1){
			log.info("微信审核未通过开始..... " + member.getId() + "未关注微信");
			return false;
		}
		return true;
	}

	@Override
	public void applicationForCancellation(Member member, Order order) {
		log.info("微信申请取消消息通知开始.....");
		boolean v = verification(member, order);
		if(!v){
			return;
		}
		if(null == member.getOpenid()){
			this.log.error("用户没有openId");
			return;
		}
		String itemName = "--";
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
			price = item.getPrice().toString();
		}
		
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser(member.getOpenid());
		
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(price);
		
		TemplateData status = data.get("keyword3");
		status.setValue("已取消");
		
		if(!"".equals(itemName)){
			TemplateData itemLevelName = data.get("keyword1");
			itemLevelName.setValue(itemName);
		}
		
		TemplateData title = data.get("first");
		title.setValue("您的申请已取消");
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}

	@Override
	public void partialPayment(Member member, Order order) {
		log.info("微信部分退款消息通知开始.....");
		boolean v = verification(member, order);
		if(!v){
			return;
		}
		
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser(member.getOpenid());
		
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(order.getTotal().toString());
		
		TemplateData status = data.get("keyword3");
		status.setValue("待结款");
		
		TemplateData title = data.get("first");
		title.setValue("您申请的XXX（当前状态下套餐名称）已付款XX元。");
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}

	@Override
	public void settleTheFullAmount(Member member, Order order, MemberItem memberItem) {
		log.info("微信结清全款消息通知开始.....");
		boolean v = verification(member, order);
		if(!v){
			return;
		}
		if(null == member.getOpenid()){
			this.log.error("用户没有openId");
			return;
		}
		String itemName = "--";
		String enableCarType = "";
		String usingCarType = "";
		int cartypeCount = 0;
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
			price = item.getPrice()+"";
			List<CarType> carTypeList = this.carTypeService.selectByItemId(item.getId());
			for (int i=0; i<carTypeList.size(); i++) {
				enableCarType += carTypeList.get(i).getBrand()+" "+carTypeList.get(i).getCarType();
				if(i!=carTypeList.size()-1){
					enableCarType += "、";
				}
			}
		}
		if(null!=order.getCarType()&&!"".equals(order.getCarType())){
			
			List<CarType> carTypeArray = JSON.parseArray(order.getCarType(), CarType.class);
			for (int i=0; i<carTypeArray.size(); i++) {
				usingCarType += carTypeArray.get(i).getBrand()+" "+carTypeArray.get(i).getCarType();
				if(i!=carTypeArray.size()-1){
					usingCarType += "、";
				}
			}
			cartypeCount = carTypeArray.size();
		}
		
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser(member.getOpenid());
		
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(price);
		
		TemplateData status = data.get("keyword3");
		status.setValue("已付款");
		
		if(!"".equals(itemName)){
			TemplateData itemLevelName = data.get("keyword1");
			itemLevelName.setValue(itemName);
		}
		String titileStr = "";
		titileStr = "恭喜您已获得"+itemName+"使用权  余额："+memberItem.getBalance()+"元；押金："+memberItem.getDeposit()+"元；可用车型："+enableCarType+"；启用车型："+usingCarType+"；开启车位数："+cartypeCount;
		titileStr.replace("null", "");
		TemplateData title = data.get("first");
		title.setValue(titileStr);
		messageService.sendMessageWxTemplate(wxTemplate);
	}

	@Override
	public void commonPeopleAdd(Member member, Item item, MemberItemShare share) {
		log.info("共用人添加通知开始.....");
		
		if(null==member){
			this.log.error("会员对象为空");
			return;
		}
		if(null==item){
			this.log.error("套餐对象为空");
			return;
		}
		Member parentMember=new Member();
		try {
			parentMember = memberService.findMemberByID(share.getParentMemberId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = BINDING_NOTIFICATION;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		wxTemplate.setUrl("");
		wxTemplate.setTouser(member.getOpenid());
		Map<String, TemplateData> data = wxTemplate.getData();

		TemplateData content = data.get("keyword2");
		content.setValue("您已成为"+parentMember.getName()+"的共用人，可共同享用"+item.getLevelName()+"权益。");
		
		TemplateData date = data.get("keyword1");
		date.setValue(member.getMobile());
		
		
		TemplateData title = data.get("first");
		title.setValue("共用人添加通知");
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}

	@Override
	public void sharedDeleted(Member member,MemberItemShare itemShare) {
		log.info("删除共用人通知开始.....");
		
		if(member == null){
			log.info("微信审核未通过开始..... member 为空");
			return;
		}
		Member parentMember=null;
		try {
			if(itemShare.getIsMain()==1){
				parentMember = memberService.findMemberByID(itemShare.getParentMemberId());
			}else{
				MemberItemShare trMemberItemShare = new MemberItemShare();
				trMemberItemShare.setParentMemberId(itemShare.getParentMemberId());
				trMemberItemShare.setIsMain(1);
				trMemberItemShare.setMemberItemId(itemShare.getMemberItemId());
				List<MemberItemShare> shares = itemShareService.selectByCondition(trMemberItemShare);
				if(CollectionUtils.isNotEmpty(shares)){
					MemberItemShare share = shares.get(0);
					parentMember = memberService.findMemberByID(share.getMemberId());
				}
			}
			MemberItem tdMemberItem = new MemberItem();
			tdMemberItem.setId(itemShare.getMemberItemId());
			List<MemberItem> list = memberItemService.selectByCondition(tdMemberItem );
			
			String message = BINDING_NOTIFICATION;
			log.info(message);
			
			WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
			wxTemplate.setUrl("");
			wxTemplate.setTouser(member.getOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData content = data.get("keyword2");
			content.setValue(parentMember.getName()+"已将您从共用人列表中移除，您已停止享用"+list.get(0).getItemName()+"权益");
			
			TemplateData date = data.get("keyword1");
			date.setValue(DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm"));
			
			
			TemplateData title = data.get("first");
			title.setValue("共用人删除通知");
			messageService.sendMessageWxTemplate(wxTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void sendMessageBySubmit(Member member,Order order) {
		log.info("微信端创建订单，微信通知开始.....");
		
		if(order == null){
			log.error("销售转派通知开始..... order 为空");
			return ;
		}
		if(null == member.getOpenid()){
			log.error("用户没有openId");
			return ;
		}
		
		String itemName = "--";
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
			price = item.getPrice().toString();
		}
		
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		String url = Constant.load.getProperty("order_detail")+"?id="+order.getId()+"&loginId="+member.getId();
		wxTemplate.setUrl(url);
		wxTemplate.setTouser(member.getOpenid());
		
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(price);
		
		TemplateData status = data.get("keyword3");
		status.setValue(order.getStatusInfo());
		
		if(!"".equals(itemName)){
			TemplateData itemLevelName = data.get("keyword1");
			itemLevelName.setValue(itemName);
		}
		
		TemplateData title = data.get("first");
		title.setValue("你有一个新的订单，请及时处理。");
		messageService.sendMessageWxTemplate(wxTemplate);
	}

	@Override
	public void sendMessageBySaleChange(Member member,Order order) {
		log.info("销售转派通知开始.....");
		
		if(order == null){
			log.error("销售转派通知开始..... order 为空");
			return ;
		}
		if(null == member.getOpenid()){
			log.error("用户没有openId");
			return ;
		}
		
		String itemName = "--";
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
			price = item.getPrice().toString();
		}
		
		String message = BASICS_ORDER_TEMPLATE;
		log.info(message);
		
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		String url = Constant.load.getProperty("order_detail")+"?id="+order.getId()+"&loginId="+member.getId();
		wxTemplate.setUrl(url);
		wxTemplate.setTouser(member.getOpenid());
		
		Map<String, TemplateData> data = wxTemplate.getData();
		TemplateData money = data.get("keyword2");
		money.setValue(price);
		
		TemplateData status = data.get("keyword3");
		status.setValue(order.getStatusInfo());
		
		if(!"".equals(itemName)){
			TemplateData itemLevelName = data.get("keyword1");
			itemLevelName.setValue(itemName);
		}
		
		TemplateData title = data.get("first");
		title.setValue("你有一个转派的订单，请及时处理。");
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}

	@Override
	public void sendMessageBySellesOrder(Order order) {
		//推送消息--发送销售意向开始
		String message = BASICS_ORDER_TEMPLATE;
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		
		//获取 tc_basic_notice 中的销售的openid
		BasicNotice b = new BasicNotice();
		b.setNoticeType(6);
		//查询正常的
		b.setStatus(0);
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
		
		String price = "--";
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			price = item.getPrice().toString();
		}
		
		if(CollectionUtils.isNotEmpty(basicNotices)){
			b = basicNotices.get(0);
			//显示详情的url
			String market_Task = Constant.load.getProperty("market_Task");
			if(StringUtils.isNotBlank(market_Task)){
				market_Task = market_Task.replace("{id}",order.getId()+"");
				market_Task = market_Task.replace("{loginId}",b.getNoticeUserId()+"");
			}
			this.log.info(market_Task);
			wxTemplate.setUrl(market_Task);
			wxTemplate.setTouser(b.getUserOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData money = data.get("keyword2");
			money.setValue(price);
			
			TemplateData a = data.get("keyword1");
			a.setValue("销售意向（"+ order.getMemberName()+ "）");
			
			TemplateData title = data.get("first");
			title.setValue("您有一个新订单，请及时处理。");
			
			TemplateData ststus = data.get("keyword3");
			ststus.setValue(order.getStatusInfo());
			messageService.sendMessageWxTemplate(wxTemplate);
		}
	}

	@Override
	public void sendMessageByTaskOperateChange(ButlerTask new_butlerTask) {
		log.info("微信管家转派,给管家发送消息通知消息通知开始---");
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		
		String operaterId = new_butlerTask.getOperaterId();//转派的执行专家id
		Member member = new Member();
		member.setSysuserId(operaterId);
		member = this.memberService.selectBySysUserId(member);
		if(null == member){
			this.log.error("转派的管家根据sysuserid没有查询到用户---sysuserid为----"+operaterId);
			return ;
		}
		if(null == member.getOpenid()){
			this.log.error("转派的管家没有对应的openid---,该管家的ID为---"+member.getId());
			return ;
		}
		//获取查看详情的url
		String task_Task = "";
		if(1==new_butlerTask.getType()){
			//充电任务
			task_Task = Constant.load.getProperty("charingTask_detail");
		}
		if(2==new_butlerTask.getType()){
			//取车任务
			task_Task = Constant.load.getProperty("getCarTask_detail");
		}
		if(3==new_butlerTask.getType()){
			//送车任务
			task_Task = Constant.load.getProperty("sendCarTask_detail");
		}
		if(StringUtils.isNotBlank(task_Task)){
			task_Task = task_Task.replace("{id}",new_butlerTask.getId()+"");
			task_Task = task_Task.replace("{loginId}",member.getId()+"");
		}
		this.log.info(message);
		wxTemplate.setUrl(task_Task);
		wxTemplate.setTouser(member.getOpenid());
		Map<String, TemplateData> data = wxTemplate.getData();
		
		TemplateData first = data.get("first");
		first.setValue("您有新的转派任务，请尽快处理");
		
		TemplateData a = data.get("keyword1");
		a.setValue(ButlerTaskType.getType(new_butlerTask.getType())+"（"+ new_butlerTask.getMemberName()+ "）");
		
		TemplateData b = data.get("keyword2");
		String content = "";
		if(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex()==new_butlerTask.getType()){
			content = "请于"+DateUtils.formatDate(new_butlerTask.getPlanTime(), "MM/dd HH:mm")+"取车";
		}else if(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()==new_butlerTask.getType()){
			content = "请于"+DateUtils.formatDate(new_butlerTask.getPlanTime(), "MM/dd HH:mm")+"送车";
		}else if(ButlerTaskType.BUTLER_TASK_TYPE_CHARGING.getIndex()==new_butlerTask.getType()){
			content = "请于"+DateUtils.formatDate(new_butlerTask.getPlanTime(), "MM/dd HH:mm")+"取车";
		}
		b.setValue(content);
		messageService.sendMessageWxTemplate(wxTemplate);
	}

	@Override
	public void sendMessageByTaskStatusChange(ButlerTask new_butlerTask, String value) {
		log.info("---微信端任务状态处理通知开始---");
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_SERVICE_NOTIFICATION;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		
		
		Member member = new Member();
		try {
			member = this.memberService.findMemberByID(Long.parseLong(new_butlerTask.getMemberId()));
		} catch (Exception e) {
			this.log.error("任务状态处理,给用户发送消息推送，查询用户失败---");
			e.printStackTrace();
			return ;
		}
		if(null == member.getOpenid()){
			this.log.error("转派的管家没又对应的openid---,该管家的ID为空---"+member.getId());
			return ;
		}
		this.log.info(message);
		String task_Task = "";
		task_Task = Constant.load.getProperty("handleTask_detail");
		task_Task = task_Task.replace("{powerUsedId}",new_butlerTask.getPowerUsedId()+"");
		wxTemplate.setTouser(member.getOpenid());
		if(1!=new_butlerTask.getType()){
			//充电任务不需要详情
			wxTemplate.setUrl(task_Task);
		}
		Map<String, TemplateData> data = wxTemplate.getData();
		
		TemplateData first = data.get("first");
		first.setValue(value);
		
		TemplateData a = data.get("keyword1");
		a.setValue(ButlerTaskType.getType(new_butlerTask.getType()));
		
		TemplateData b = data.get("keyword2");
		b.setValue(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}
	@Override
	public void sendMessageByPlatformOperation(CarOperatePlan carOperatePlan, String value) {
		log.info("分配了被平台运营的车辆通知开始---");
		//您安排的平台运营车辆已被分配给会员用车，请尽快处理
		//任务名称：特斯拉S70D（京A12338）    //括号内为车牌号
		//通知类型：平台运营                  //平台运营
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);

		String butlerId = carOperatePlan.getButlerId();//转派的客服id
		if(StringUtils.isEmpty(butlerId)){
			this.log.info(carOperatePlan.getId() + "<<<运营计划的管家id为空");
			return;
		}
		Member member = new Member();
		member.setSysuserId(butlerId);
		member = this.memberService.selectBySysUserId(member);
		if(null == member.getOpenid()){
			this.log.error("分配了被平台运营的车辆服负责人没有对应的openid---,该客服的ID为---"+member.getId());
			return ;
		}

		this.log.info(message);
		wxTemplate.setTouser(member.getOpenid());
		Map<String, TemplateData> data = wxTemplate.getData();

		TemplateData first = data.get("first");
		first.setValue(value);

		TemplateData a = data.get("keyword1");
		CarType carType = carTypeService.findById(carOperatePlan.getCarTypeId());
		String keyword1 = carType.getBrand()+carType.getCarType()+"("+carOperatePlan.getCarPlateNum()+")";//特斯拉S70D（京A12338）    //括号内为车牌号";
		a.setValue(keyword1);
		
		TemplateData b = data.get("keyword2");
		b.setValue("平台运营");
		wxTemplate.setUrl(urlPrefix+"/wechat/carOperate/toCar?carPlateNum="+carOperatePlan.getCarPlateNum()+"&loginId="+member.getId());
		
		messageService.sendMessageWxTemplate(wxTemplate);
		

	}

	@Override
	public void sendMessageByTaskCreateToCustomer(ButlerTask butlerTask, String value) {
		log.info("微信创建任务,给客服发送消息通知消息通知开始---");
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);

		String customerId = butlerTask.getCustomerId();//转派的客服id
		if(StringUtils.isEmpty(customerId)){
			this.log.info(butlerTask.getId() + "<<<任务的客服id为空");
			return;
		}
		Member member = new Member();
		member.setSysuserId(customerId);
		member = this.memberService.selectBySysUserId(member);
		if(null == member.getOpenid()){
			this.log.error("创建任务，任务客服负责人没有对应的openid---,该客服的ID为---"+member.getId());
			return ;
		}
		//获取查看详情的url
		String task_Task = Constant.load.getProperty("sendCarTask_detail");
		if(StringUtils.isNotBlank(task_Task)){
			task_Task = task_Task.replace("{id}",butlerTask.getId()+"");
			task_Task = task_Task.replace("{loginId}",member.getId()+"");
		}
		this.log.info(message);
		wxTemplate.setUrl(task_Task);
		wxTemplate.setTouser(member.getOpenid());
		Map<String, TemplateData> data = wxTemplate.getData();

		TemplateData first = data.get("first");
		first.setValue(value);

		TemplateData a = data.get("keyword1");
		a.setValue(ButlerTaskType.getType(butlerTask.getType())+"（"+ butlerTask.getMemberName()+ "）");

		TemplateData b = data.get("keyword2");
		b.setValue(ButlerTaskStatus.getStatus(butlerTask.getStatus()));
		messageService.sendMessageWxTemplate(wxTemplate);
	}

	@Override
	public void sendMessageByTaskCreate(ButlerTask butlerTask, String value) {
		log.info("微信创建任务,给管家发送消息通知消息通知开始---");
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		
		String operaterId = butlerTask.getOperaterId();//转派的执行专家id
		Member member = new Member();
		member.setSysuserId(operaterId);
		member = this.memberService.selectBySysUserId(member);
		if(null == member.getOpenid()){
			this.log.error("创建任务，任务负责人没有对应的openid---,该管家的ID为---"+member.getId());
			return ;
		}
		//获取查看详情的url
		String task_Task = "";
		if(1==butlerTask.getType()){
			//充电任务
			task_Task = Constant.load.getProperty("charingTask_detail");
		}
		if(2==butlerTask.getType()){
			//取车任务
			task_Task = Constant.load.getProperty("getCarTask_detail");
		}
		if(3==butlerTask.getType()){
			//送车任务
			task_Task = Constant.load.getProperty("sendCarTask_detail");
		}
		if(StringUtils.isNotBlank(task_Task)){
			task_Task = task_Task.replace("{id}",butlerTask.getId()+"");
			task_Task = task_Task.replace("{loginId}",member.getId()+"");
		}
		this.log.info(message);
		wxTemplate.setUrl(task_Task);
		wxTemplate.setTouser(member.getOpenid());
		Map<String, TemplateData> data = wxTemplate.getData();
		
		TemplateData first = data.get("first");
		first.setValue(value);
		
		TemplateData a = data.get("keyword1");
		a.setValue(ButlerTaskType.getType(butlerTask.getType())+"（"+ butlerTask.getMemberName()+ "）");
		
		TemplateData b = data.get("keyword2");
		b.setValue(ButlerTaskStatus.getStatus(butlerTask.getStatus()));
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}

	@Override
	public void sendMessageByTaskCancel(ButlerTask butlerTask, String value, Integer modifierType) {
		log.info("微信取消任务,给管家发送消息通知消息通知开始---");
		try {
			value = "任务已取消";
			//推送消息--管家转派给管家发送消息通知
			String message = TASK_NOTICE;
			this.log.info(message);
			WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
			
			String operaterId = butlerTask.getOperaterId();//转派的执行专家id
			Member member = new Member();
			member.setSysuserId(operaterId);
			member = this.memberService.selectBySysUserId(member);
			if(null == member.getOpenid()){
				this.log.error("取消任务，任务负责人没有对应的openid---,该管家的ID为---"+member.getId());
				return ;
			}
			Member MemberNotice = new Member();
			if(null == modifierType || 0 != modifierType){
				//表示操作人为管家，显示操作人为管家
				value += "，操作人："+member.getName()+"（"+member.getMobile()+"）";
			}else{
				//操作人为用户，显示操作人为用户信息
				MemberNotice = this.memberService.findMemberByID(Long.parseLong(butlerTask.getMemberId()));
				if(null != MemberNotice){
					value += "，操作人："+MemberNotice.getName()+"（"+MemberNotice.getMobile()+"）";
				}
			}
			
			//获取查看详情的url
			String task_Task = "";
			if(1==butlerTask.getType()){
				//充电任务
				task_Task = Constant.load.getProperty("charingTask_detail");
			}
			if(2==butlerTask.getType()){
				//取车任务
				task_Task = Constant.load.getProperty("getCarTask_detail");
			}
			if(3==butlerTask.getType()){
				//送车任务
				task_Task = Constant.load.getProperty("sendCarTask_detail");
			}
			if(StringUtils.isNotBlank(task_Task)){
				task_Task = task_Task.replace("{id}",butlerTask.getId()+"");
				task_Task = task_Task.replace("{loginId}",member.getId()+"");
			}
			this.log.info(message);
			wxTemplate.setUrl(task_Task);
			wxTemplate.setTouser(member.getOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData first = data.get("first");
			first.setValue(value);
			
			TemplateData a = data.get("keyword1");
			a.setValue(ButlerTaskType.getType(butlerTask.getType())+"（"+ butlerTask.getMemberName()+ "）");
			
			TemplateData b = data.get("keyword2");
			String content = "";
			if(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex()==butlerTask.getType()){
				content = "请于"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM/dd HH:mm")+"取车";
			}else if(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()==butlerTask.getType()){
				content = "请于"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM/dd HH:mm")+"送车";
			}else if(ButlerTaskType.BUTLER_TASK_TYPE_CHARGING.getIndex()==butlerTask.getType()){
				content = "请于"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM/dd HH:mm")+"取车";
			}
			b.setValue(content);
			messageService.sendMessageWxTemplate(wxTemplate);
		} catch (Exception e) {
			this.log.error("取消任务消息推送异常");
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessageByBreakPromise(ButlerTask butlerTask, String value, String price) {
		log.info("取消用车违约,给用户发送消息通知消息通知开始---");
		//推送消息--管家转派给管家发送消息通知
		String message = CONSUM_SUCCESS;
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		this.log.info(message);
		
		Member member = new Member();
		List<MemberItem> memberItemList = new ArrayList<MemberItem>();
		try {
			member = this.memberService.findMemberByID(Long.parseLong(butlerTask.getMemberId()));
			MemberItem tdMemberItem = new MemberItem();
			tdMemberItem.setMemberId(member.getId());
			memberItemList = memberItemService.selectByCondition(tdMemberItem );
		} catch (Exception e) {
			this.log.error("取消用车违约,给用户发送消息推送，查询用户失败---");
			e.printStackTrace();
			return ;
		}
		if(null == member.getOpenid()){
			this.log.error("用户没有找到的openid---,该用户的ID为空---"+member.getId());
			return ;
		}
		if(memberItemList.size()!=1){
			this.log.error("用户没有找到相应的权益---,该用户的ID为空---"+member.getId());
			return ;	
		}
		
		this.log.info(message);
		wxTemplate.setTouser(member.getOpenid());
		Map<String, TemplateData> data = wxTemplate.getData();
		
		TemplateData first = data.get("first");
		first.setValue(value);
		
		TemplateData b = data.get("keyword1");
		b.setValue(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		
		TemplateData a = data.get("keyword2");
		a.setValue("余额"+price+"元");
		
		TemplateData c = data.get("keyword3");
		c.setValue("余额"+memberItemList.get(0).getBalance()+"元");
		
		messageService.sendMessageWxTemplate(wxTemplate);
		
	}
	
	@Override
	public void sendMessageByApproveCreate(Approve approve) {
		if(null==approve){
			log.error("会员信息完善生成审核记录,返回的审核对象为空---");
			return ;
		}
		log.info("会员信息完善生成审核记录,给管家发送消息通知消息通知开始---");
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		
		//获取 tc_basic_notice 中的销售的openid
		BasicNotice b = new BasicNotice();
		b.setNoticeType(3);
		//查询正常的
		b.setStatus(0);
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
		
		if(CollectionUtils.isNotEmpty(basicNotices)){
			b = basicNotices.get(0);
			wxTemplate.setTouser(b.getUserOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData first = data.get("first");
			first.setValue("您有新审核任务，请尽快处理");
			
			TemplateData keyword1 = data.get("keyword1");
			keyword1.setValue("信息审核（"+approve.getMemberName()+"）");
			
			TemplateData keyword2 = data.get("keyword2");
			String statusStr = "待审核";
			if(0==approve.getStatus()){
				statusStr = "待审核";
			}
			if(1==approve.getStatus()){
				statusStr = "驳回审核";
			}
			if(2==approve.getStatus()){
				statusStr = "通过审核";
			}
			keyword2.setValue(statusStr);
			
			messageService.sendMessageWxTemplate(wxTemplate);
		}
	}

	@Override
	public void sendMessageByLoseOrTrouble(Long carOperateId , Integer type, Member member_notice, ButlerTask butlerTask) {
		if(null==carOperateId){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,车辆ID为空---");
			return ;
		}
		if(null==type){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,类型为空---");
			return ;
		}
		if(null==member_notice){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,通知管家对象为空---");
			return ;
		}
		if(null==butlerTask){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,任务对象为空---");
			return ;
		}
		CarOperate caroperate = this.carOperateService.findById(carOperateId);
		if(null==caroperate){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,查询车辆信息为空---");
			return ;
		}
		log.info("车辆失联或者车辆故障或者延迟还车给管家发送消息推送---");
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		
		//获取 tc_basic_notice 中的销售的openid
		/*BasicNotice b = new BasicNotice();
		//查询送车任务负责人
		b.setNoticeType(1);
		//查询正常的
		b.setStatus(0);
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);*/
		
		if(null!=member_notice.getOpenid() && !"".equals(member_notice.getOpenid())){
			//b = basicNotices.get(0);
			//获取查看详情的url
			String task_Task = "";
			if(1==butlerTask.getType()){
				//充电任务
				task_Task = Constant.load.getProperty("charingTask_detail");
			}
			if(2==butlerTask.getType()){
				//取车任务
				task_Task = Constant.load.getProperty("getCarTask_detail");
			}
			if(3==butlerTask.getType()){
				//送车任务
				task_Task = Constant.load.getProperty("sendCarTask_detail");
			}
			if(StringUtils.isNotBlank(task_Task)){
				task_Task = task_Task.replace("{id}",butlerTask.getId()+"");
				task_Task = task_Task.replace("{loginId}",member_notice.getId()+"");
			}
			wxTemplate.setUrl(task_Task);
			wxTemplate.setTouser(member_notice.getOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData first = data.get("first");
			if(1==type){
				first.setValue("由于用户延时还车，造成后续库存不足，请尽快处理");
			}else if(2==type){
				first.setValue("由于车辆失联，造成后续库存不足，请尽快处理");
			}else if(3==type){
				first.setValue("由于车辆需要维修，造成后续库存不足，请尽快处理");
			}else if(4==type){
				first.setValue("由于车辆签约，造成后续库存不足，请尽快处理");
			}
			
			TemplateData keyword1 = data.get("keyword1");
			keyword1.setValue(caroperate.getCarBrand()+" "+caroperate.getCarType()+"（"+caroperate.getCarPlateNum()+"）");
			
			TemplateData keyword2 = data.get("keyword2");
			if(1==type){
				keyword2.setValue("延时用车 ");
			}else if(2==type){
				keyword2.setValue("车辆失联 ");
			}else if(3==type){
				keyword2.setValue("车辆维修");
			}else if(4==type){
				keyword2.setValue("签约预约 ");
			}
			
			messageService.sendMessageWxTemplate(wxTemplate);
		}
		
	}
	
	@Override
	public void sendMessageByEmptyTaskCarOperate(ButlerTask butlerTask){
		log.info("由于车辆被其他任务使用，可能造成任务（MM-dd HH:mm)需重新分配车辆，请尽快处理---");
		if(null == butlerTask.getCarOperateId()){
			log.error("由于车辆被其他任务使用，可能造成任务（MM-dd HH:mm)需重新分配车辆，请尽快处理,车辆ID为空---");
			return ;
		}	
		
		CarOperate caroperate = this.carOperateService.findById(butlerTask.getCarOperateId());
		
		if(null == caroperate){
			log.error("由于车辆被其他任务使用，可能造成任务（MM-dd HH:mm)需重新分配车辆，请尽快处理,查询车辆信息为空---");
			return ;
		}
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		//查出负责管家
		Member member_notice =new Member();
		member_notice.setSysuserId(butlerTask.getOperaterId());
		member_notice = this.memberService.selectBySysUserId(member_notice);
		if(null!=member_notice && null !=member_notice.getOpenid()){
			//获取查看详情的url
			String task_Task = "";
			if(1==butlerTask.getType()){
				//充电任务
				task_Task = Constant.load.getProperty("charingTask_detail");
			}
			if(2==butlerTask.getType()){
				//取车任务
				task_Task = Constant.load.getProperty("getCarTask_detail");
			}
			if(3==butlerTask.getType()){
				//送车任务
				task_Task = Constant.load.getProperty("sendCarTask_detail");
			}
			if(StringUtils.isNotBlank(task_Task)){
				task_Task = task_Task.replace("{id}",butlerTask.getId()+"");
				task_Task = task_Task.replace("{loginId}",member_notice.getId()+"");
			}
			wxTemplate.setUrl(task_Task);
			wxTemplate.setTouser(member_notice.getOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData first = data.get("first");
			String firstValue = "由于车辆被其他任务使用，可能造成任务("+DateUtils.formatDate(butlerTask.getPlanTime(), "MM-dd HH:mm")+")需重新分配车辆，请尽快处理";

			first.setValue(firstValue);
			TemplateData keyword1 = data.get("keyword1");
			keyword1.setValue(caroperate.getCarBrand()+" "+caroperate.getCarType()+"("+caroperate.getCarPlateNum()+")");
			
			TemplateData keyword2 = data.get("keyword2");
			keyword2.setValue("车辆占用");
			messageService.sendMessageWxTemplate(wxTemplate);
		}
	}

	
	@Override
	public void sendMessageByEmptyCarOperate(Long carOperateId , Integer type, ButlerTask butlerTask) {
		if(null==carOperateId){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,车辆ID为空---");
			return ;
		}
		if(null==type){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,类型为空---");
			return ;
		}
		if(null==butlerTask){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,任务对象为空---");
			return ;
		}
		CarOperate caroperate = this.carOperateService.findById(carOperateId);
		if(null==caroperate){
			log.error("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送,查询车辆信息为空---");
			return ;
		}
		log.info("车辆失联或者车辆故障或者延迟还车给管家发送消息童推送---");
		//推送消息--管家转派给管家发送消息通知
		String message = TASK_NOTICE;
		this.log.info(message);
		WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
		//查出负责管家
		Member member_notice =new Member();
		member_notice.setSysuserId(butlerTask.getOperaterId());
		member_notice = this.memberService.selectBySysUserId(member_notice);
		if(null!=member_notice && null !=member_notice.getOpenid()){
			//获取查看详情的url
			String task_Task = "";
			if(1==butlerTask.getType()){
				//充电任务
				task_Task = Constant.load.getProperty("charingTask_detail");
			}
			if(2==butlerTask.getType()){
				//取车任务
				task_Task = Constant.load.getProperty("getCarTask_detail");
			}
			if(3==butlerTask.getType()){
				//送车任务
				task_Task = Constant.load.getProperty("sendCarTask_detail");
			}
			if(StringUtils.isNotBlank(task_Task)){
				task_Task = task_Task.replace("{id}",butlerTask.getId()+"");
				task_Task = task_Task.replace("{loginId}",member_notice.getId()+"");
			}
			wxTemplate.setUrl(task_Task);
			wxTemplate.setTouser(member_notice.getOpenid());
			Map<String, TemplateData> data = wxTemplate.getData();
			
			TemplateData first = data.get("first");
			String a = "";
			if(1==type){
				a = "由于车辆延时，可能造成任务（"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM-dd HH:mm")+"）需重新分配车辆，请尽快处理";
			}else if(2==type){
				a = "由于车辆失联，可能造成任务（"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM-dd HH:mm")+"）需重新分配车辆，请尽快处理";
			}else if(3==type){
				a = "由于车辆故障，可能造成任务（"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM-dd HH:mm")+"）需重新分配车辆，请尽快处理";
			}else if(4==type){
				a = "由于车辆签约，可能造成任务（"+DateUtils.formatDate(butlerTask.getPlanTime(), "MM-dd HH:mm")+"）需重新分配车辆，请尽快处理";
			}
			first.setValue(a);
			TemplateData keyword1 = data.get("keyword1");
			keyword1.setValue(caroperate.getCarBrand()+" "+caroperate.getCarType()+"（"+caroperate.getCarPlateNum()+"）");
			
			TemplateData keyword2 = data.get("keyword2");
			if(1==type){
				keyword2.setValue("延时用车 ");
			}else if(2==type){
				keyword2.setValue("车辆失联 ");
			}else if(3==type){
				keyword2.setValue("车辆维修");
			}else if(4==type){
				keyword2.setValue("签约预约 ");
			}
			
			messageService.sendMessageWxTemplate(wxTemplate);
		}
		
	}

	@Override
	public void receiveTask(ButlerTask butlerTask, String value) throws Exception {
		String memberId = butlerTask.getMemberId();
		Member member = memberService.findMemberByID(Long.valueOf(memberId));
		if(member == null){
			throw new Exception("用户信息为空");
		}
		if(member.getFocusType().intValue() == 1){//已关注发送微信消息
			
			if(StringUtils.isNotEmpty(member.getOpenid())){//已关注发送微信消息
				
				String message = SERVICE_ACCEPTANCE;
				this.log.info(message);
				WxTemplate wxTemplate= JSONObject.parseObject(message, WxTemplate.class);
				wxTemplate.setTouser(member.getOpenid());
				Map<String, TemplateData> data = wxTemplate.getData();
				
				TemplateData first = data.get("first");
				first.setValue(value);
				
				TemplateData a = data.get("keyword1");
				a.setValue("预约用车");
				
				TemplateData b = data.get("keyword2");
				b.setValue(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
				messageService.sendMessageWxTemplate(wxTemplate);
			}else{
				String message = "极车管家已受理您的用车预约，车辆将及时为您送达，预祝您用车愉快。";
				//未关注发送短信
				AppUtil.sendMessage(message, member.getMobile());
			}	
		}else{//未关注发送短信
			String message = "极车管家已受理您的用车预约，车辆将及时为您送达，预祝您用车愉快。";
			//未关注发送短信
			AppUtil.sendMessage(message, member.getMobile());
		}
		
		
	}

}
