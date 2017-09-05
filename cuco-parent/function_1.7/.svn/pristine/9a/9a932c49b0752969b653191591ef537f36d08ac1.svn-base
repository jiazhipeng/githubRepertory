package com.hy.gcar.web.wechat.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.Order;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.order.OrderService;
import com.hy.gcar.utils.ProvincesUtils;

@Controller
@RequestMapping("/wechat/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCartypeService itemCartypeService;
	@Autowired
	private ProvincesUtils provincesUtils;
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private CouponInfoService couponInfoService;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 跳转到订单列表
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/toOrderList")
	public String toOrderList(Order order,ModelMap map) throws Exception{
		//当前的登录人
		this.logger.info("当前登录人loginId》》》》》》》》》》》》》》》》》》》》》》》》》》》"+order.getLoginId());
		map.put("loginId", order.getLoginId());
		//查询所有的销售人员列表
		List<Member> sales = this.memberService.findSaleMemberList();
		//首次进入查询订单列表
		List<Order> orderList= orderService.getOrderList(order);
		map.put("orderList", orderList);
		map.put("sales", sales);
		return "gcar/order/orderList";
	}
	
	/**动态加载订单列表数据
	 * @param order
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/orderList")
	public @ResponseBody Map<String, Object> orderList(Order order){
		this.logger.info("加载车管家任务开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		List<Order> orderList= orderService.getOrderList(order);
		obj.put("orders", orderList);
		this.logger.info("加载车管家任务结束---");
		return obj;
	}
	
	/**动态查询订单列表数据
	 * @param order
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/orderSearch")
	public @ResponseBody Map<String, Object> orderSearch(Order order){
		this.logger.info("动态查询车管家任务开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		List<Order> orderList= orderService.findOrderListByFuzzySearch(order);
		obj.put("orders", orderList);
		this.logger.info("动态查询车管家结束---");
		return obj;
	}
	
	/**
	 * 用户搜索
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/searchMember")
	public @ResponseBody Map<String, Object> searchMember(Member member) {
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			List<Member> list = memberService.findMember(member);
			if(null == list || list.size() ==0){
				//表示没有查到结果
				obj.put("result",false);
				return obj;
			}
			obj.put("member", list.get(0));
			obj.put("result",true);
		} catch (Exception e) {
			this.logger.info("查讯用户失败----");
			e.printStackTrace();
		}
		return obj;
	}
	
	/**根据订单id查询订单
	 * @param 
	 * @return 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/detail")
	public String orderDetail(Order order,ModelMap map) throws Exception{
		//当前登录人
		String loginId = order.getLoginId();
		//根据订单ID查询订单详细信息
		order = this.orderService.findOrderById(order.getId());
		//判断用户是否为管家
		Member currMember = this.memberService.findMemberByID(Long.parseLong(loginId));
		
		if(order.getStatus()==0 || order.getStatus()==5 || (!currMember.getSysuserId().equals(order.getModifierId()))){
			//已取消或已完成状态，或者不是销售订单负责人查看的情况
			return "redirect:/wechat/order/detailinfo?id="+order.getId()+"&loginId="+loginId;
		}
		
		//查询所有的销售
		List<Member> memberList= memberService.findSaleMemberList();
		//查询用户信息
		Member member = memberService.findMemberByID(order.getMemberId());
		//查询套餐信息
		Item item = this.itemService.findById(order.getItemId());
		
		//查询会员已有权益
		MemberItem memberItem = new MemberItem();
		memberItem.setMemberId(order.getMemberId());
		List<MemberItem> memberItemList = this.memberItemService.selectByCondition(memberItem);
		if(null!=memberItemList && memberItemList.size()>0){
			memberItem = memberItemList.get(0);
		}
		
		map.put("order", order);
		map.put("memberList", memberList);
		//当前的登录人
		map.put("loginId", loginId);
		map.put("member", member);
		map.put("item", item);
		map.put("memberItem", memberItem);
		String url="";
		if(1==order.getStatus()||6==order.getStatus()){
			//查询
			MemberInfo memberInfo = memberInfoService.findMemberInfoByMemberID(order.getMemberId());
			map.put("memberInfo", memberInfo);
			//如果是入会申请或者升级申请
			if(1==member.getType()){
				url = "gcar/order/orderInfoApplyCompany";
			}else{
				url = "gcar/order/orderInfoApply";
			}
		}
		List<CouponInfo> couponinfoList = new ArrayList<CouponInfo>();
		if(2==order.getStatus()||3==order.getStatus()||4==order.getStatus()){
			//如果是待签约
			CouponInfo couponInfo = new CouponInfo();
			couponInfo.setMemberId(member.getId());
			//待使用
			couponInfo.setStatus(2);
			//入会卷
			couponInfo.setCouponType(2);
			couponInfo.setValid(1);
			couponInfo.setItemId(order.getItemId());
			couponinfoList = this.couponInfoService.getCouponInfoByMemberIdAndItemId(couponInfo);
			url = "gcar/order/orderInfo";
		}
		map.put("couponinfoList", couponinfoList);
		return url;
	}
	
	/**订单详情（针对已取消，已完成的订单）
	 * @param 
	 * @return 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/detailinfo")
	public String detailinfo(Order order,ModelMap map) throws Exception{
		//当前登录人
		String loginId = order.getLoginId();
		
		//查询订单详情
		order = this.orderService.findOrderById(order.getId());
		//查询用户信息
		Member member = memberService.findMemberByID(order.getMemberId());
		//查询套餐信息
		Item item = this.itemService.findById(order.getItemId());
		
		//查询会员已有权益
		MemberItem memberItem = new MemberItem();
		memberItem.setMemberId(order.getMemberId());
		List<MemberItem> memberItemList = this.memberItemService.selectByCondition(memberItem);
		if(null!=memberItemList && memberItemList.size()>0){
			memberItem = memberItemList.get(0);
		}
		
		map.put("order", order);
		map.put("member", member);
		map.put("item", item);
		map.put("memberItem", memberItem);
		//当前的登录人
		map.put("loginId", loginId);
		return "gcar/order/orderDetailInfo";
	}
	
	/**修改订单
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/edit")
	public String edit(Order order) throws Exception{
		String url = "";
		if(null != order.getStatus()){
			//状态发生了改变
			if(order.getStatus()==2){
				//销售意向变成待签约
				this.orderService.updateOrderStatusByWaitSign(order);
			}else if(order.getStatus()==3){
				//待签约变成已签约
				this.orderService.updateOrderStatusWaitPay(order);
			}else if(order.getStatus()==4){
				//已签约变成待结款
				this.orderService.updateOrderStautsByPaying(order);
			}else if(order.getStatus()==5){
				//变成已完成
				this.orderService.updateOrderStatusByComplete(order);
			}else if(order.getStatus()==0){
				//变成已取消
				this.orderService.updateOrderByCancle(order);
			}
			url = "redirect:/wechat/order/detail?id="+order.getId()+"&loginId="+order.getLoginId();
		}else{
			//订单没有状态改变
			orderService.updateOrderByNoStatus(order);
			url = "redirect:/wechat/order/toOrderList?loginId="+order.getLoginId();
		}
		return url;
	}
	
	/**修改订单
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/editAjax")
	public @ResponseBody Map<String, Object> editAjax(Order order){
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			if(null != order.getStatus()){
				//状态发生了改变
				if(order.getStatus()==2){
					//销售意向变成待签约
					this.orderService.updateOrderStatusByWaitSign(order);
				}else if(order.getStatus()==3){
					//待签约变成已签约
					this.orderService.updateOrderStatusWaitPay(order);
				}else if(order.getStatus()==4){
					//已签约变成待结款
					this.orderService.updateOrderStautsByPaying(order);
				}else if(order.getStatus()==5){
					//变成已完成
					this.orderService.updateOrderStatusByComplete(order);
				}else if(order.getStatus()==0){
					//变成已取消
					this.orderService.updateOrderByCancle(order);
				}
				obj.put("result", true);
				obj.put("orderId", order.getId());
				obj.put("loginId", order.getLoginId());
				obj.put("flag",true );//因安卓手机redirect 不跳转 改此方式ajax 好使
			}else{
				//订单没有状态改变
				orderService.updateOrderByNoStatus(order);
				obj.put("orderId", order.getId());
				obj.put("loginId", order.getLoginId());
				obj.put("result", true);
				obj.put("flag", false);
			}
		} catch (Exception e) {
			obj.put("result", false);
			obj.put("msg", "订单修改失败");
			this.logger.error("订单修改失败---"+e);
		}
		return obj;
	}
	
	/**进入创建订单页
	 * @param member
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toCreateOrder")
	public String toCreateOrder(Member member,ModelMap map) throws Exception {
		String loginId = member.getLoginId();
		this.logger.info("当前登录人loginId》》》》》》》》》》》》》》》》》》》》》》》》》》》》》"+loginId);
		Member m = memberService.findMemberByID(Long.parseLong(loginId));
		//查询用户信息
		member = memberService.findMemberByID(member.getId());
		//查询所有的销售人员列表
		List<Member> sales = this.memberService.findSaleMemberList();
		map.put("loginMember", m);
		map.put("memberList", sales);
		map.put("loginId", loginId);
		map.put("member", member);
		return "gcar/order/createOrder";
	}

	/**创建订单
	 * @param member
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/createOrder")
	public @ResponseBody Map<String, Object> createOrder(Order order,ModelMap map){
		Map<String, Object> obj = new HashMap<String, Object>();
		//获取当前登录人的ID
		String loginId = order.getLoginId();
		try {
			//校验当前用户是不是共用人，共用人不允许创建订单
			MemberItemShare memberItemShare = new MemberItemShare();
			memberItemShare.setMemberId(order.getMemberId());
			List<MemberItemShare> memberItemshareList = this.memberItemShareService.selectByNoPersonalIsMain(memberItemShare);
			if(null!=memberItemshareList && memberItemshareList.size()>0){
				obj.put("result", false);
				obj.put("msg", "该会员已是共用人，不能创建");
				return obj;
			}
			//已经购买了当前产品不能再次购买当前产品
			MemberItem memberItem = new MemberItem();
			memberItem.setMemberId(order.getMemberId());
			memberItem.setItemId(order.getItemId());
			List<MemberItem> memberItemList = this.memberItemService.selectByCondition(memberItem);
			if(null!=memberItemList && memberItemList.size()>0){
				obj.put("result", false);
				obj.put("msg", "会员已购买该套餐 ,不能进行购买");
				return obj;
			}
			//校验查询是否存在未完成的订单
			Order order_new = new Order();
			order_new.setMemberId(order.getMemberId());
			List<Order> orderList = this.orderService.findOrderByNoComplete(order);
			if(null!=orderList && orderList.size()>0){
				obj.put("result", false);
				obj.put("msg", "已有正在进行中的订单，不能创建");
				return obj;
			}
			//来源为微信
			order.setOrderFrom(1);
			this.orderService.createOrderByApplyOrUpgrade(order);
			obj.put("result", true);
		} catch (Exception e) {
			obj.put("result", false);
			obj.put("msg", "创建订单失败");
			this.logger.error("创建订单失败---"+e);
		}
		
		return obj;
	}
	/**创建会员前的校验-手机组织机构代码不能存在
	 * @param member
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/beforeCreate",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> beforeCreate(Member member){
		Map<String, Object> obj = new HashMap<String, Object>();
		try{
			this.logger.info("创建会员前的校验---");
			List<Member> memberByMobile = memberService.findMember(member);
			if(memberByMobile.size()>0){
				obj.put("result",false);
				if(null!=member.getMobile()&&!"".equals(member.getMobile())){
					obj.put("msg", "该手机号已存在，请确认");
				}
				if(null!=member.getOrgId()&&!"".equals(member.getOrgId())){
					obj.put("msg", "该组织机构代码已存在，请确认");
				}
				return obj;
			}
			obj.put("result", true);
		}catch(Exception e){
			obj.put("result", false);
			obj.put("msg", "创建会员前校验失败");
			this.logger.error("创建会员前校验失败---"+e);
		}
		return obj;
	}
	
	@RequestMapping(value = "/createMember")
	public @ResponseBody Map<String, Object> createMember(Member member,ModelMap map) {
		Map<String, Object> obj = new HashMap<String, Object>();
		String loginId= member.getLoginId();
		this.logger.info("登录人ID》》》》》》》》》》》》》》》》》》》》》"+loginId);
		try {
			this.logger.info("创建会员start---");
			//查询当前操作人
			if(loginId!=null){
				Member m = memberService.findMemberByID(Long.parseLong(loginId));
				if(m!=null){
					member.setModifier(m.getSysuserName());
					member.setModifierId(m.getSysuserId());
				}
				map.put("loginMember",m);
			}
			member.setFromMember(0);
			if(0==member.getType()){
				//个人会员
				member = this.memberService.createPersonalMember(member);
			}else if(1==member.getType()){
				//企业会员
				member = this.memberService.createCompanyMember(member);
			}
			this.logger.info("创建会员成功---");
			obj.put("result", true);
			obj.put("loginId", loginId);
			obj.put("memberId", member.getId());
		} catch (Exception e) {
			this.logger.error("创建会员失败---");
			obj.put("result", false);
			obj.put("msg", "创建会员失败");
			e.printStackTrace();
		}
		return obj;
	}
	
	/**查询所有的上架的套餐
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/getItems",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getItems(Item item){
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			this.logger.info("查询所有已上架的套餐产品开始---");
			List<Item> items = this.itemService.selectByCondition(item);
			obj.put("result", true);
			obj.put("items", items);
		} catch (Exception e) {
			this.logger.error("查询所有已上架的套餐失败---");
			obj.put("result", false);
			obj.put("msg", "查询所有已上架的套餐失败");
			e.printStackTrace();
		}
		return obj;
	}
	
	/**根据套餐ID查询套餐下的可选车型
	 * @return
	 */
	@RequestMapping(value="/getCarTypes",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCarTypes(ItemCartype itemCartype, Long orderItemId, String orderCarType){
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			this.logger.info("查询套餐下的可选车型开始---");
			List<ItemCartype> cartypes =this.itemCartypeService.selectByCondition(itemCartype);
			for (ItemCartype itemCarty : cartypes){
				CarType carty = this.carTypeService.findById(itemCarty.getCartypeId());
				itemCarty.setCarBrand(carty.getBrand());
				itemCarty.setCartype(carty.getCarType());
				itemCarty.setFlagStr(false);
			}
			
			if(itemCartype.getItemId()==orderItemId){
				//订单上的套餐跟此套餐匹配上
				if(null!=orderCarType&&!"".equals(orderCarType)){
					List<CarType> cartypelist = JSON.parseArray(orderCarType,CarType.class);
					for(CarType cart : cartypelist){
						for(ItemCartype itemCarty : cartypes){
							if(cart.getId()==itemCarty.getCartypeId()){
								itemCarty.setFlagStr(true);
								continue;
							}
						}
					}
				}
			}
			obj.put("cartypes", cartypes);
			obj.put("result", true);
		} catch (Exception e) {
			this.logger.error("查询套餐下的可选车型失败---");
			obj.put("result", false);
			obj.put("msg", "查询套餐下的可选车型失败");
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 获取城市
	 * @return
	 */
	@RequestMapping(value="/getNation",method = RequestMethod.GET)
	public @ResponseBody List<Nation> getNation(){
		return provincesUtils.getCityMap();
		
	}
}
