package com.hy.gcar.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.hy.gcar.dao.OrderLogMapper;
import com.hy.gcar.dao.OrderMapper;
import com.hy.gcar.entity.Approve;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Order;
import com.hy.gcar.entity.OrderLog;
import com.hy.gcar.service.Approve.ApproveService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.order.OrderService;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.service.MessageAppService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper<Order> orderMapper;
	@Autowired
	private OrderLogMapper<OrderLog> orderLogMapper;
	@Autowired
	private ItemService itemService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private MemberItemCartypeService memberItemCartypeService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	WechatMessageService wechatMessageService;
	@Autowired
	private MessageAppService messageAppService;
	@Autowired
	CarTypeService carTypeService;
	@Autowired
	private ItemCartypeService itemCartypeService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private ApproveService approveService;
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Order createOrder(Order order) {
		order.setCreated(new Date());
		order.setLasttimeModify(order.getCreated());
		this.orderMapper.insertSelective(order);
		return orderMapper.selectByPrimaryKey(order.getId());
	}

	@Override
	public Order updateOrder(Order order) {
		order.setLasttimeModify(new Date());
		this.orderMapper.updateByPrimaryKeySelective(order);
		return this.orderMapper.selectByPrimaryKey(order.getId());
	}

	@Override
	public int deleteOrder(Order order) {
		if(null == order.getId()){
			this.logger.error("根据订单ID删除一条订单，没有传入订单ID--");
		}
		return this.orderMapper.deleteByPrimaryKey(order.getId());
	}

	@Override
	public Order findOrderById(long id) {
		return this.orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> getOrderList(Order order) {
		List<Order> orderList = this.orderMapper.selectByCondition(order);
		return orderList;
	}

	@Override
	public Order updateOrderByCancle(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		Assert.hasText(order.getRemark(), "取消订单,备注信息不能为空");
		//查询修改之前的订单
		Order order_old = this.findOrderById(order.getId());
		order_old.setRemark(order.getRemark());
		/*//判断退款金额--取消退款
		if(null != order.getRefundPrice() && !(new BigDecimal(0).equals(order.getRefundPrice())) && null != order_old.getPaymentDeposit()){
			if(1 != order.getRefundPrice().compareTo(order_old.getPaymentDeposit())){
				//退款金额小于或者等于押金
				order.setPaymentDeposit(order_old.getPaymentDeposit().subtract(order.getRefundPrice()));
			}else{
				//优先从实收押金中扣除，如实收押金不足以抵扣，从实收金额中扣除
				order.setPaymentDeposit(new BigDecimal(0));
				order.setPayment(order_old.getPayment().add(order_old.getPaymentDeposit()).subtract(order.getRefundPrice()));
			}
		}*/
		order.setStatus(0);
		//修改订单信息
		order = this.updateOrder(order);
		//创建任务日志
		this.createOrderStatusLog(order_old);
		
		//取消订单给会员发送消息推送--微信
		this.sendMessageByOrderCancel(order);
		
		//取消订单给会员发送消息推送--app
		String itemName = "";
		String value ="";
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
		}
		value = "您的"+itemName+"申请已取消";
		this.sendMessageByOrderCancelApp(order,value);
		
		return order;
	}

	/**
	 * 取消订单消息推送
	 * @param order
	 */
	public void sendMessageByOrderCancel(Order order){
		//取消订单给会员发送消息推送
		if(null == order.getMemberId()){
			this.logger.error("订单会员ID为空");
			return;
		}
		Member member = new Member();
		try {
			member = this.memberService.findMemberByID(order.getMemberId());
		} catch (Exception e) {
			this.logger.error("给会员发送消息推送，查询会员信息失败");
			e.printStackTrace();
		}
		wechatMessageService.applicationForCancellation(member, order);
	}
	
	/**
	 * 取消订单消息推送-app
	 * @param order
	 */
	public void sendMessageByOrderCancelApp(Order order,String value){
		/*String itemName = "";
		String value ="";
		if(null == order.getItemId()){
			this.logger.error("没有选择产品");
		}
		else{
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
		}
		value = "您的"+itemName+"申请已取消";*/
		MessageUmeng messageUmeng = new MessageUmeng();
		//0代表极车公社
		messageUmeng.setAlias(order.getMemberId()+"");
		messageUmeng.setTitle("海易出行");
		messageUmeng.setTicker("系统");
		messageUmeng.setMessageContent(value);
		messageUmeng.setCreateUser(order.getModifier());
		messageUmeng.setBeginTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		try {
			messageAppService.sendMessageByAndroidAndIosAsync(messageUmeng);
		} catch (Exception e) {
			logger.error("给用户app端消息推送失败----用户ID："+order.getItemId()+" 推送内容为:'"+value+"'");
		}
	}
	
	@Override
	public Order updateOrderStatusByWaitSign(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		
		order.setStatus(2);
		//查询修改之前的订单
		Order order_old = this.findOrderById(order.getId());
		order_old.setRemark(order.getRemark());
		//修改订单信息
		order = this.updateOrder(order);
		//创建任务日志
		this.createOrderStatusLog(order_old);
		
		//审核通过将会员审核状态变成已审核-生成审核通过的用车审核记录
		this.updateMember(order);
		//审核通过给会员发送消息推送
		this.sendMessageByOrderApproved(order);
		
		//审核通过给会员发送消息推送--app
		String itemName = "";
		String value ="";
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
		}
		value = "您的"+itemName+"申请已通过，可进行签约";
		this.sendMessageByOrderCancelApp(order,value);
		
		return order;
	}

	/**
	 * 审核通过消息推送
	 * @param order
	 */
	public void sendMessageByOrderApproved(Order order){
		//取消订单给会员发送消息推送
		if(null == order.getMemberId()){
			this.logger.error("订单会员ID为空");
			return;
		}
		Member member = new Member();
		try {
			member = this.memberService.findMemberByID(order.getMemberId());
		} catch (Exception e) {
			this.logger.error("给会员发送消息推送，查询会员信息失败");
			e.printStackTrace();
		}
		wechatMessageService.auditPass(member, order);
	}
	
	@Override
	public Order updateOrderStatusWaitPay(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		order.setStatus(3);
		//查询修改之前的订单
		Order order_old = this.findOrderById(order.getId());
		order_old.setRemark(order.getRemark());
		//修改订单信息
		order = this.updateOrder(order);
		//创建任务日志
		this.createOrderStatusLog(order_old);
		return order;
	}

	@Override
	public Order updateOrderStautsByPaying(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		order.setStatus(4);
		//查询修改之前的订单
		Order order_old = this.findOrderById(order.getId());
		order_old.setRemark(order.getRemark());
		if(null!=order_old.getPayment()){
			//如果之前有支付金额累加
			order.setPayment(order.getPayment().add(order_old.getPayment()));
		}
		if(null!=order_old.getPaymentDeposit()){
			//如果之前有押金累加
			order.setPaymentDeposit(order.getPaymentDeposit().add(order_old.getPaymentDeposit()));
		}
		//修改订单信息
		order = this.updateOrder(order);
		
		//创建任务日志
		this.createOrderStatusLog(order_old);
		
		return order;
	}

	@Override
	public Order updateOrderStatusByComplete(Order order) throws Exception {
		Assert.notNull(order, "订单对象不能为空");
		order.setStatus(5);
		//查询修改之前的订单
		Order order_old = this.findOrderById(order.getId());
		order_old.setRemark(order.getRemark());
		if(null!=order.getCouponPrice()){
			//选了优惠券还需要将优惠券的金额累加到余额
			order.setPayment(order.getPayment().add(order.getCouponPrice()));
		}
		/*//没有部分付款的情况
		if(null!=order_old.getPayment()){
			//如果之前有支付金额累加
			order.setPayment(order.getPayment().add(order_old.getPayment()));
			if(null!=order.getCouponPrice()){
				//选了优惠券还需要将优惠券的金额累加到余额
				order.setPayment(order.getPayment().add(order.getCouponPrice()));
			}
		}
		if(null!=order_old.getPaymentDeposit()){
			//如果之前有押金累加
			order.setPaymentDeposit(order.getPaymentDeposit().add(order_old.getPaymentDeposit()));
		}*/
		//选择优惠券之后需要将优惠券的状态变成已使用
		if(null!=order.getCouponId()){
			this.updateCouponInfoStatus(order);
		}
		//修改订单信息
		order = this.updateOrder(order);
		//创建订单状态修改日志记录
		this.createOrderStatusLog(order_old);
		
		//同时需要生成一条会员权益
		MemberItem memberItem = this.createMemberPower(order);
		
		//结清全款给用户发送消息推送
		this.sendMessageByOrderComplete(order,memberItem);
		
		//订单完成给用户发送消息---app
		this.sendMessageByOrderCompleteApp(order_old, memberItem);
		
		//插入一条权益充值的记录td_member_account_log
		this.createMemberAccountLog(order,memberItem);
		
		return order;
	}

	public void createMemberAccountLog(Order order,MemberItem memberItem){
		MemberAccountLog memberAccountLog = new MemberAccountLog();
		MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(order.getMemberId());
		if(null==memberItemShare){
			memberAccountLog.setMemberShareType(0);
		}
		if(null!=memberItemShare){
			if(1==memberItemShare.getIsMain()){
				memberAccountLog.setMemberShareType(2);
			}else{
				memberAccountLog.setMemberShareType(1);
			}
		}
		memberAccountLog.setMemberItemId(memberItem.getId());
		memberAccountLog.setMemberItemName(memberItem.getItemName());
		memberAccountLog.setAccountType(3);//余额和押金
		memberAccountLog.setTransformType(5);//购买(订单支付成功)
		memberAccountLog.setCreated(new Date());
		memberAccountLog.setDeposit(order.getPaymentDeposit());
		memberAccountLog.setBalance(order.getPayment());
		memberAccountLog.setTotal(order.getPaymentDeposit().add(order.getPayment()));
		memberAccountLog.setPreBalance(memberItem.getBalance().subtract(order.getPayment()));
		memberAccountLog.setPreDeposit(memberItem.getDeposit().subtract(order.getPaymentDeposit()));
		memberAccountLog.setRemark("充值");
		memberAccountLog.setBalanceReason("充值");
		memberAccountLog.setDepositReason("充值");
		memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
		memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
		try {
			this.memberAccountLogService.insertSelective(memberAccountLog);
		} catch (Exception e) {
			this.logger.error("插入会员权益消费记录失败---");
			e.printStackTrace();
		}
	}
	
	public void updateCouponInfoStatus(Order order){
		CouponInfo couponInfo = new CouponInfo();
		couponInfo.setId(order.getCouponId());
		couponInfo.setStatus(4);//已使用
		this.couponInfoService.updateByPrimaryKeySelective(couponInfo);
	}
	/**
	 * 订单结清全款，发送消息推送
	 * @param order
	 */
	public void sendMessageByOrderComplete(Order order, MemberItem memberItem){
		//完成订单给会员发送消息推送
		if(null == order.getMemberId()){
			this.logger.error("订单会员ID为空");
			return;
		}
		if(null == memberItem){
			this.logger.error("会员权益为空");
			return;
		}
		if(null == order.getItemId()){
			this.logger.error("订单套餐为空");
			return;
		}
		Member member = new Member();
		try {
			member = this.memberService.findMemberByID(order.getMemberId());
		} catch (Exception e) {
			this.logger.error("给会员发送消息推送，查询会员信息失败");
			e.printStackTrace();
			return;
		}
		wechatMessageService.settleTheFullAmount(member, order, memberItem);
	}
	
	/**
	 * 订单结清全款，发送消息推送-app
	 * @param order
	 */
	public void sendMessageByOrderCompleteApp(Order order, MemberItem memberItem){
		//完成订单给会员发送消息推送
		if(null == order.getMemberId()){
			this.logger.error("订单会员ID为空");
			return;
		}
		if(null == memberItem){
			this.logger.error("会员权益为空");
			return;
		}
		if(null == order.getItemId()){
			this.logger.error("订单套餐为空");
			return;
		}
		
		String itemName = "--";
		String enableCarType = "";
		String usingCarType = "";
		int cartypeCount = 0;
		//查询套餐信息
		if(null != order.getItemId()){
			Item item = this.itemService.findById(order.getItemId());
			itemName = item.getLevelName();
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
		
		String value = "恭喜您已获得"+itemName+"使用权  余额："+memberItem.getBalance()+"元；押金："+memberItem.getDeposit()+"元；可用车型："+enableCarType+"；启用车型："+usingCarType+"；开启车位数："+cartypeCount;
		//开始推送
		MessageUmeng messageUmeng = new MessageUmeng();
		//0代表极车公社
		messageUmeng.setApplicationType(0);
		messageUmeng.setAlias(order.getMemberId()+"");
		messageUmeng.setTitle("海易出行");
		messageUmeng.setTicker("系统");
		messageUmeng.setMessageContent(value);
		messageUmeng.setCreateUser(order.getModifier());
		messageUmeng.setBeginTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		messageUmeng.setReadStatus(0);//未读消息
		try {
			messageAppService.sendMessageByAndroidAndIosAsync(messageUmeng);
		} catch (Exception e) {
			logger.error("给用户app端消息推送失败----用户ID："+order.getItemId()+" 推送内容为:'"+value+"'");
		}
	}
	/**
	 * 订单完成之后生成会员权益
	 * order
	 * @throws Exception 
	 */
	public MemberItem createMemberPower(Order order) throws Exception{
		if(null ==order.getMemberId()){
			this.logger.error("订单记录会员ID为空---");
			return null;
		}
		//根据会员ID查询存不存在会员权益
		MemberItem memberItem_old = new MemberItem();
		memberItem_old.setMemberId(order.getMemberId());
		List<MemberItem> memberItemList = this.memberItemService.selectByCondition(memberItem_old);
		//查询套餐信息
		Item item = this.itemService.findById(order.getItemId());
		if(null == item){
			this.logger.error("查询套餐错误---");
			return null;
		}
		MemberItem memberItem = new MemberItem();
		if(memberItemList.size()>0){
			//存在之前的权益信息，需要把余额以及押金累加
			memberItem = memberItemList.get(0);
			memberItem.setMemberId(order.getMemberId());
			memberItem.setItemId(order.getItemId());
			memberItem.setCreated(new Date());
			memberItem.setMemberId(order.getMemberId());
			memberItem.setModifier(order.getModifier());
			memberItem.setModifierId(order.getModifierId());
			memberItem.setLasttimeModify(memberItem.getCreated());
			memberItem.setOrderId(order.getId());
			memberItem.setItemName(item.getLevelName());
			memberItem.setLevelUrl(item.getLevelUrl());
			//累加金额
			memberItem.setBalance(order.getPayment().add(memberItem.getBalance()));
			memberItem.setDeposit(order.getPaymentDeposit().add(memberItem.getDeposit()));
			memberItem = this.memberItemService.updateByPrimaryKeySelective(memberItem);
		}
		if(memberItemList.size()<=0){
			//不存在原先的权益，直接创建
			memberItem = new MemberItem();
			memberItem.setMemberId(order.getMemberId());
			memberItem.setItemId(order.getItemId());
			memberItem.setCreated(new Date());
			memberItem.setMemberId(order.getMemberId());
			memberItem.setModifier(order.getModifier());
			memberItem.setModifierId(order.getModifierId());
			memberItem.setLasttimeModify(memberItem.getCreated());
			memberItem.setOrderId(order.getId());
			memberItem.setItemName(item.getLevelName());
			memberItem.setLevelUrl(item.getLevelUrl());
			memberItem.setBalance(order.getPayment());
			memberItem.setDeposit(order.getPaymentDeposit());
			memberItem = this.memberItemService.insertSelective(memberItem);
		}
		
		//生成一个权益子表
		this.createMemberItemCarType(order , memberItem);
		
		//如果是个人,插入用户权益关系表,将自己设置为主共用人
		Member member_new = this.memberService.findMemberByID(order.getMemberId());
		if(0==member_new.getType()){
			this.createMemberItemShare(member_new,memberItem,order);
		}
		
		return memberItem;
	} 
	
	public void createMemberItemShare(Member member_new,MemberItem memberItem,Order order){
		MemberItemShare share_old = new MemberItemShare();
		share_old.setMemberId(member_new.getId());
		share_old.setMemberItemId(memberItem.getId());
		List<MemberItemShare> shareOldList = this.memberItemShareService.selectByCondition(share_old);
		MemberItemShare share = new MemberItemShare();
		if(null!=shareOldList && shareOldList.size()==1){
			//关联权益--删除
			//this.memberItemShareService.deleteByPrimaryKey(shareOldList.get(0).getId());
			//关联权益--修改
			share = shareOldList.get(0);
			share.setCreated(new Date());
			share.setMemberId(member_new.getId());
			share.setParentMemberId(member_new.getId());
			share.setLasttimeModify(share.getCreated());
			share.setMemberItemId(memberItem.getId());
			share.setModifierId(order.getModifierId());
			share.setModifier(order.getModifier());
			share.setParentMemberType(0);
			//设置能被同时操作
			share.setIsShare(1);
			//设置为主共用人
			share.setIsMain(1);
			//插入
			try {
				this.memberItemShareService.updateByPrimaryKeySelective(share);
			} catch (Exception e) {
				this.logger.error("个人创建订单修改共享权益失败---");
				e.printStackTrace();
			}
		}
		else{
			//不存在权益，直接创建
			share.setCreated(new Date());
			share.setMemberId(member_new.getId());
			share.setParentMemberId(member_new.getId());
			share.setLasttimeModify(share.getCreated());
			share.setMemberItemId(memberItem.getId());
			share.setModifierId(order.getModifierId());
			share.setModifier(order.getModifier());
			share.setParentMemberType(0);
			//设置能被同时操作
			share.setIsShare(1);
			//设置为主共用人
			share.setIsMain(1);
			//插入
			try {
				this.memberItemShareService.insertSelective(share);
			} catch (Exception e) {
				this.logger.error("个人创建订单修改共享权益失败---");
				e.printStackTrace();
			}
		}
		
	}
	
	/**生成一条权益车型子表
	 * @param order
	 * @param memberItem
	 */
	public void createMemberItemCarType(Order order , MemberItem memberItem){
		if(null == memberItem.getId()){
			this.logger.error("删除权益车型子表没有传入车型ID---");
			return;
		}
		//首先查询之前的权益车型子表，先根据权益主键删除
		MemberItemCartype memberItemCartype = new MemberItemCartype();
		memberItemCartype.setMemberItemId(memberItem.getId());
		this.memberItemCartypeService.deleteByMemberItemId(memberItemCartype);
		//重新生成子表信息
		String carType = order.getCarType();
		if(null==carType || ""==carType){
			//没有车型信息不需要保存
			return ;
		}
		//循环订单中的cartype生成关系数据
		if(null!=carType && ""!=carType){
			//解析得到车型数组
			List<CarType> carTypeArray = JSON.parseArray(carType, CarType.class);
			for(CarType carTy : carTypeArray){
				MemberItemCartype memberItemCarty_new = new MemberItemCartype();
				memberItemCarty_new.setCarTypeId(carTy.getId());
				memberItemCarty_new.setBrand(carTy.getBrand());
				memberItemCarty_new.setCarType(carTy.getCarType());
				//状态为待使用
				memberItemCarty_new.setStatus(1);
				//ItemC
				ItemCartype itemCartype = new ItemCartype();
				itemCartype.setCartypeId(carTy.getId());
				itemCartype.setItemId(order.getItemId());
				List<ItemCartype> itemCartypeList = this.itemCartypeService.selectByCondition(itemCartype);
				if(itemCartypeList.size()>0){
					memberItemCarty_new.setPrice(itemCartypeList.get(0).getDayPrice());
				}
				memberItemCarty_new.setMemberItemId(memberItem.getId());
				try {
					this.memberItemCartypeService.insertSelective(memberItemCarty_new);
				} catch (Exception e) {
					this.logger.error("插入权益车型关联表失败---"+e);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public Order createOrderByApplyOrUpgrade(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		Assert.hasText(order.getMemberName(), "提交入会申请或者升级申请没有传入会员姓名信息");
		/*Assert.hasText(order.getMemberMobile(), "提交入会申请或者升级申请没有传入手机信息");*/
		
		//创建订单时，可能涉及到修改会员信息
		//this.updateMember(order);
		
		Member member = new Member();
		List<Member> memberlist = null;
		try {
			//订单用户
			Member m = this.memberService.findMemberByID(order.getMemberId());
			if(0==m.getType()){
				//个人
				member.setMobile(m.getMobile());
			}
			if(1==m.getType()){
				member.setOrgId(m.getOrgId());;
			}
			//根据手机号或者组织机构代码查询出会员信息
			memberlist = this.memberService.findMember(member);
			if(null!=memberlist){
				if(memberlist.size()>0){
					//看看该会员有没有已完成的订单
					Order order_member = new Order();
					order_member.setMemberId(memberlist.get(0).getId());
					List<Order> order_members = this.findOrderByComplete(order_member);
					order.setStatus(1);
					if(null!=order_members && order_members.size()>0){
						//表示是升级申请
						order.setStatus(6);
					}
				}
				if(memberlist.size()==0){
					//则表示为入会申请--首先需要创建一条会员记录
					member.setName(order.getMemberName());
					Member member_new = this.memberService.createPersonalMember(member);
					order.setStatus(1);
					order.setMemberId(member_new.getId());
				}
			}
		} catch (Exception e) {
			this.logger.error("查询会员列表失败---");
			e.printStackTrace();
		}
		String num = this.createOrderNum();
		this.logger.info("得到订单号为---"+num);
		order.setOrderNum(num);
		Order order_new = this.createOrder(order);
		String remark="";
		if(StringUtils.isNotBlank(order_new.getRemark())){
			remark += order_new.getRemark()+"<br/>";	
		}
		if(6==order_new.getStatus()){
			remark += "提交升级申请";
			order_new.setRemark(remark);
		}else{
			remark += "提交入会申请";
			order_new.setRemark(remark);
		}
		//插入一条订单状态日志
	    this.createOrderStatusLog(order_new);
	    
	    //需要给指定的销售发送推送 
	    this.sendMessageByOrderCreate(order_new);
	    
		return order;
	}

	/**
	 * 创建订单发送消息推送
	 * @param order
	 */
	public void sendMessageByOrderCreate(Order order){
		//创建订单给订单负责人发送消息推送
		if(null == order.getModifierId()){
			this.logger.error("订单负责人ID为空");
			return;
		}
		Member member = new Member();
		member.setSysuserId(String.valueOf(order.getModifierId()));
		try {
			member = this.memberService.selectBySysUserId(member);
		} catch (Exception e) {
			this.logger.error("给会员发送消息推送，查询会员信息失败");
			e.printStackTrace();
		}
		wechatMessageService.sendMessageBySubmit(member, order);
	}
	
	@Override
	public List<Order> findOrderByNoComplete(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		if(null == order.getMemberId()){
			this.logger.error("根据ID查询会员的未完成订单，传入会员ID不能为空");
			return null;
		}
		List<Order> orderList = this.orderMapper.selectOrderListByNoComplete(order);
		return orderList;
	}

	@Override
	public List<Order> findOrderByComplete(Order order) {
		Assert.notNull(order, "订单对象不能为空");
		if(null == order.getMemberId()){
			this.logger.error("根据ID查询会员的已完成订单，传入会员ID不能为空");
			return null;
		}
		List<Order> orderList = this.orderMapper.selectOrderListByComplete(order);
		return orderList;
	}
	
	@Override
	public Order updateOrderByNoStatus(Order order) {
		Order order_old = this.findOrderById(order.getId());
		
		order = this.updateOrder(order);
		//插入一条订单状态日志
		this.createOrderStatusLog(order_old);
		return order;
	}

	@Override
	public void createOrderStatusLog(OrderLog orderLog) {
		this.orderLogMapper.insertSelective(orderLog);
	}

	/**
	 * 创建订单状态变更日志
	 * @param order
	 * @return
	 */
	public void createOrderStatusLog(Order order_old) {
		//首先查询出修改之后的最新订单与传入的旧订单作比较
		Order order_New = this.findOrderById(order_old.getId());
		
		OrderLog orderStatusLog = new OrderLog();
		orderStatusLog.setCreated(new Date());
		orderStatusLog.setOrderNum(order_New.getOrderNum());
		orderStatusLog.setRemark(order_New.getRemark());
		orderStatusLog.setStatus(order_New.getStatus());
		//销售负责人变更
		if(!order_old.getModifierId().equals(order_New.getModifierId())){
			orderStatusLog.setModifier(order_old.getModifier());
			orderStatusLog.setModifierId(order_old.getModifierId());
		}else{
			orderStatusLog.setModifier(order_New.getModifier());
			orderStatusLog.setModifierId(order_New.getModifierId());
		}
		String remark = "";
		if(StringUtils.isNotBlank(order_New.getRemark())){
			remark += "备注："+order_New.getRemark()+"<br/>";	
		}
		if(null != order_New.getStatus()){
			if(!order_New.getStatus().equals(order_old.getStatus())){
			remark += "备注：将状态变更为"+order_New.getStatusInfo()+"<br/>";	
			}
		}
		//修改销售负责人
		if(!order_old.getModifierId().equals(order_New.getModifierId())){
			remark += "备注：将订单指派给"+order_New.getModifier()+"<br/>";
			//发送销售重新指派通知
			this.sendMessageBySalerChange(order_New);
		}
		//修改权益使用城市
		if(StringUtils.isNotBlank(order_New.getCityId())){
			if(!order_New.getCityId().equals(order_old.getCityId())){
				remark += "备注：变更使用城市为"+order_New.getCityName()+"<br/>";
			}
		}
		//修改产品信息
		if(null!=order_New.getItemId()){
			if(!order_New.getItemId().equals(order_old.getItemId())){
				Item item = this.itemService.findById(order_New.getItemId());
				remark += "备注：变更订单会员成长为"+item.getLevelName()+"车型<br/>";
			}
		}
		if(4==order_New.getStatus()){
			orderStatusLog.setRemark("备注：已付定金"+order_New.getPayment()+"<br/>");
		}else if(5==order_New.getStatus()){
			orderStatusLog.setRemark("备注：已结清全款"+";"+order_New.getRemark()+"<br/>");
		}
		//插入日志
		if(StringUtils.isNotBlank(remark)){
			orderStatusLog.setRemark(remark.substring(0, remark.lastIndexOf("<br/>")));
		}
		this.orderLogMapper.insertSelective(orderStatusLog);
	}
	
	/**更改审核信息
	 * @param 
	 * @return 
	 */
	public void updateMember(Order order){
		Member member = new Member();
		member.setId(order.getMemberId());
		try {
			member.setBuyApproved(2);//购买审核通过
			member.setInfoAudit(1);
			Member m = memberService.findMemberByID(order.getMemberId());
			MemberInfo memberInfo = memberInfoService.findMemberInfoByMemberID(order.getMemberId());
			if(null!=memberInfo.getIdcardFront()&&null!=memberInfo.getIdcardBack()&&null!=memberInfo.getDrivercardOriginal()&&null!=memberInfo.getDrivercardCopy()){
				Approve approve_complete = new Approve();
				approve_complete.setStatus(2);
				approve_complete.setMemberId(m.getId());
				List<Approve> approveList = this.approveService.selectByCondition(approve_complete);
				if(approveList.size()<=0){
					//如果4张证件都上传了，同时生成一个审核通过的用车申请
					Approve approve = new Approve();
					approve.setMemberId(m.getId());
					approve.setMemberName(m.getName());
					approve.setMemberMobile(m.getMobile());
					approve.setIdcardFront(memberInfo.getIdcardFront());
					approve.setIdcardBack(memberInfo.getIdcardBack());
					approve.setDrivercardCopy(memberInfo.getDrivercardCopy());
					approve.setDrivercardOriginal(memberInfo.getDrivercardOriginal());
					approve.setStatus(2);
					approve.setCreated(new Date());
					approve.setApproveTime(approve.getCreated());
					approve.setIsLog(0);
					approve.setOperaterId(order.getModifierId());
					approve.setOperaterName(order.getModifier());
					approve.setApproveType(0);//00：用车申请；1：入会申请；2：升级申请',
					MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(m.getId());
					if(null!=memberItemShare){
						if(1==memberItemShare.getIsMain()){
							approve.setMembership(1);
						}else{
							approve.setMembership(0);
						}
					}
					//查询权益
					MemberItem memberItem_old = new MemberItem();
					memberItem_old.setMemberId(order.getMemberId());
					List<MemberItem> memberItemList = this.memberItemService.selectByCondition(memberItem_old);
					if(memberItemList.size()>0){
						approve.setMemberItemId(memberItemList.get(0).getId());
						approve.setMemberItemName(memberItemList.get(0).getItemName());
						approve.setApproveType(2);
					}
					this.approveService.insertSelective(approve);
				}
				member.setUseCarApproved(2);
			}
			if(0==m.getType()){//个人会员
				this.memberService.updatePersonalMember(member);
			}
			if(1==m.getType()){//企业会员
				this.memberService.updateCompanyMember(member);
			}
		} catch (Exception e) {
			this.logger.error("修改会员信息失败---");
			e.printStackTrace();
		}
		
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
	
	/**销售转派给转派的销售发通知
	 * @param order
	 */
	public void sendMessageBySalerChange(Order order){
		//消息转派给转派的销售发送消息推送
		if(null == order.getModifierId()){
			this.logger.error("订单没有负责人");
			return ; 
		}
		Member member = new Member();
		member.setSysuserId(order.getModifierId());
		member = this.memberService.selectBySysUserId(member);
		
		if(null==member){
			this.logger.error("根据sysuser没有查询到负责人");
			return ; 
		}
		wechatMessageService.sendMessageBySaleChange(member,order);
	}

	@Override
	public List<Order> findOrderListByFuzzySearch(Order order) {
		this.logger.info("会员列表模糊搜索开始---");
		List<Order> orderList = this.orderMapper.findOrderListByFuzzySearch(order);
		return orderList;
	}
}
