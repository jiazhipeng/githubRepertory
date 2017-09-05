package cn.cuco.service.order.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.MemberCarUsedAddressMapper;
import cn.cuco.dao.OrderMemberCarUsedMapper;
import cn.cuco.entity.ButlerTask;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarOperatePlan;
import cn.cuco.entity.CarType;
import cn.cuco.entity.Member;
import cn.cuco.entity.MemberAccount;
import cn.cuco.entity.MemberAccountLog;
import cn.cuco.entity.MemberCarUsedAddress;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.entity.Restriction;
import cn.cuco.enums.AccountOperateType;
import cn.cuco.enums.AccountTransformType;
import cn.cuco.enums.MemberCarUsedStatus;
import cn.cuco.enums.MemberStatus;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.enums.OrderMemberCarUsedStatus;
import cn.cuco.enums.WechatMsgType;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.business.RestrictionService;
import cn.cuco.service.basic.carport.CarTypeService;
import cn.cuco.service.car.carInfo.CarService;
import cn.cuco.service.car.carOperate.CarOperatePlanService;
import cn.cuco.service.car.stock.StockService;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.member.account.MemberAccountService;
import cn.cuco.service.member.info.MemberService;
import cn.cuco.service.order.OrderCarUsedService;
import cn.cuco.service.order.OrderMemberCarUsedService;
import cn.cuco.service.task.ButlerTaskService;
import cn.cuco.service.task.impl.ButlerTaskServiceImpl;
import cn.cuco.service.wechat.wechatMessage.IWechatMessageSender;

@Service
public class OrderMemberCarUsedServiceImpl implements OrderMemberCarUsedService {

	@Autowired
	private OrderMemberCarUsedMapper<OrderMemberCarUsed> memberCarUsedMapper;
	@Autowired
	private MemberCarUsedAddressMapper<MemberCarUsedAddress> memberCarUsedAddressMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private StockService stockService;
	@Autowired
	private OrderCarUsedService orderCarUsedService;
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private CarService carService;
	@Autowired
	private RestrictionService restrictionService;
	@Autowired
	private IWechatMessageSender iWechatMessageSender;
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private ButlerTaskService butlerTaskService;

	protected Logger log = LoggerFactory.getLogger(ButlerTaskServiceImpl.class);

	@Override
	public List<OrderMemberCarUsed> getOrderMemberCarUsedNotComplete(OrderMemberCarUsed memberCarUsed) {
		ParamVerifyUtils.paramNotNull(memberCarUsed, "查询用户待支付或者正在进行中的用车订单，用车订单不能为空");
		ParamVerifyUtils.paramNotNull(memberCarUsed.getMemberId(), "查询用户待支付或者正在进行中的用车订单，用户ID不能为空");
		return this.memberCarUsedMapper.selectOrderMemberCarUsedNotComplete(memberCarUsed);
	}

	@Override
	public OrderMemberCarUsed createOrderMemberCarUsedForApp(OrderMemberCarUsed memberCarUsed) {
		// 基础验证
		this.validateForCreateOrder(memberCarUsed);
		// 判断库存
		this.validateStock(memberCarUsed.getOrderCartypeId(), memberCarUsed.getOrderCarUsedTime(), memberCarUsed.getOrderCarReturnTime());
		// 设置用车相关金额、邮箱体积
		this.setOrderMemberCarUsedParam(memberCarUsed);
		// 创建用车记录
		memberCarUsed = this.createOrderMemberCarUsed(memberCarUsed);
		// 创建用车日志
		this.createOrderLog(memberCarUsed);
		this.createUseCarLog(memberCarUsed);
		// 创建车辆运营计划
		this.createCarOperatePlan(memberCarUsed);
		// 用车地址维护
		this.updateUseCarAddress(
				new MemberCarUsedAddress(memberCarUsed.getMemberId(), memberCarUsed.getMemberName(), 0, memberCarUsed.getOrderCarUsedAddress(), memberCarUsed.getLongitudeLatitude_useCarAddress(), null, memberCarUsed.getCreated()));
		this.updateUseCarAddress(
				new MemberCarUsedAddress(memberCarUsed.getMemberId(), memberCarUsed.getMemberName(), 1, memberCarUsed.getOrderCarReturnAddress(), memberCarUsed.getLongitudeLatitude_returnCarAddress(), null, memberCarUsed.getCreated()));
		return memberCarUsed;
	}

	/**
	 * @Title: createOrderMemberCarUsed
	 * @Description: 创建用车订单底层方法
	 * @param: memberCarUsed
	 * @return: OrderMemberCarUsed
	 */
	private OrderMemberCarUsed createOrderMemberCarUsed(OrderMemberCarUsed memberCarUsed) {
		memberCarUsed.setCreated(new Date());
		memberCarUsed.setLasttimeModify(memberCarUsed.getCreated());
		// 写死北京用车
		memberCarUsed.setCityCode("110000");
		this.memberCarUsedMapper.insertSelective(memberCarUsed);
		return this.memberCarUsedMapper.selectByPrimaryKey(memberCarUsed.getId());
	}

	private void createCarOperatePlan(OrderMemberCarUsed memberCarUsed) {
		CarOperatePlan plan = new CarOperatePlan();
		plan.setCarTypeId(memberCarUsed.getOrderCartypeId());
		plan.setMemberId(memberCarUsed.getMemberId());
		plan.setStartTime(memberCarUsed.getOrderCarUsedTime());
		plan.setEndTime(memberCarUsed.getOrderCarReturnTime());
		plan.setMemberUsecarId(memberCarUsed.getId());
		plan.setType(1);
		this.carOperatePlanService.createCarOperatePlan(plan);
	}
	
	private void deleteCarOperatePlan(OrderMemberCarUsed memberCarUsed){
		CarOperatePlan plan = new CarOperatePlan();
		plan.setMemberUsecarId(memberCarUsed.getId());
		this.carOperatePlanService.deleteOperatePlanByCarUsedId(plan);
	}
	
	private void updateCarOperatePlanEndTime(OrderMemberCarUsed memberCarUsed) {
		CarOperatePlan plan = new CarOperatePlan();
		plan.setEndTime(memberCarUsed.getOrderCarReturnTime());
		plan.setMemberUsecarId(memberCarUsed.getId());
		this.carOperatePlanService.updateOperatePlanInfoByCarUsedId(plan);
	}
	
	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedForChangeCar(OrderMemberCarUsed memberCarUsed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderMemberCarUsed getOrderMemberCarUsed(OrderMemberCarUsed memberCarUsed) {
		List<OrderMemberCarUsed> list = this.memberCarUsedMapper.getOrderMemberCarUsedListWithSort(memberCarUsed);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageResult<OrderMemberCarUsed> getOrderMemberCarUsedListByPage(OrderMemberCarUsed memberCarUsed) {
		ParamVerifyUtils.paramNotNull(memberCarUsed);
		Integer page = memberCarUsed.getPage();
		Integer pageSize = memberCarUsed.getPageSize();
		Integer totalSize = this.memberCarUsedMapper.selectCountByCondition(memberCarUsed);
		PageHelper.startPage(page, pageSize);
		List<OrderMemberCarUsed> list = this.memberCarUsedMapper.getOrderMemberCarUsedListWithSort(memberCarUsed);
		// setTotalCost(list);//计算总的消费金额
		PageResult<OrderMemberCarUsed> pageResult = new PageResult<OrderMemberCarUsed>(page, pageSize, totalSize, list);
		return pageResult;
	}

	@Override
	public Integer getOrderMemberCarUsedCountByMemberId(Long memberId) {
		ParamVerifyUtils.paramNotNull(memberId, "根据用户ID获取用车次数时，用户ID不能为空");
		return this.memberCarUsedMapper.getOrderMemberCarUsedCountByMemberId(memberId);
	}

	@Override
	public BigDecimal getOrderMemberCarUsedMileagesByMemberId(Long memberId) {
		ParamVerifyUtils.paramNotNull(memberId, "根据用户ID获取用户行驶公里数时，用户ID不能为空");
		return this.memberCarUsedMapper.getOrderMemberCarUsedMileagesByMemberId(memberId);
	}

	@Override
	public Map<String, Integer> getMemberDriveMileageStatistics(Long memberId) {
		ParamVerifyUtils.paramNotNull(memberId, "根据用户ID获取用户用车里程统计，用户ID不能为空");
		return this.orderCarUsedService.getMemberDriveMileageStatistics(memberId);
	}

	@Override
	public Map<String, Integer> getMemberCarTypeUsedStatistics(Long memberId) {
		ParamVerifyUtils.paramNotNull(memberId, "根据用户ID获取用户使用车型统计，用户ID不能为空");
		Map<String, Integer> result = new HashMap<String, Integer>();
		Map<Long, Integer> map = this.memberCarUsedMapper.getMemberCarTypeUsedStatisticsByMemberId(memberId);
		if (!MapUtils.isEmpty(map)) {
			for (Map.Entry<Long, Integer> entry : map.entrySet()) {
				CarType car = this.carTypeService.getCarTypeById(entry.getKey());
				result.put(car.getBrand() + " " + car.getCarType(), entry.getValue());
			}
		}
		return result;
	}

	@Override
	public Integer deleteMemberCarUsedAddress(MemberCarUsedAddress memberCarUsedAddress) {
		ParamVerifyUtils.paramNotNull(memberCarUsedAddress);
		ParamVerifyUtils.paramNotNull(memberCarUsedAddress.getId(), "删除用户用车地址时，用车地址ID不能为空");
		MemberCarUsedAddress target = new MemberCarUsedAddress();
		target.setId(memberCarUsedAddress.getId());
		target.setValid(0);
		return this.memberCarUsedAddressMapper.updateByPrimaryKeySelective(target);
	}

	@Override
	public PageResult<MemberCarUsedAddress> getMemberCarUsedAddressListByPage(MemberCarUsedAddress memberCarUsedAddress) {
		ParamVerifyUtils.paramNotNull(memberCarUsedAddress);
		ParamVerifyUtils.paramNotNull(memberCarUsedAddress.getMemberId(), "获取用户用车地址时，用户ID不能为空");
		memberCarUsedAddress.setValid(1);
		Integer page = memberCarUsedAddress.getPage();
		Integer pageSize = memberCarUsedAddress.getPageSize();
		Integer totalSize = this.memberCarUsedAddressMapper.selectCountByCondition(memberCarUsedAddress);
		PageHelper.startPage(page, pageSize);
		List<MemberCarUsedAddress> list = this.memberCarUsedAddressMapper.getMemberCarUsedAddressListWithSort(memberCarUsedAddress);
		PageResult<MemberCarUsedAddress> pageResult = new PageResult<MemberCarUsedAddress>(page, pageSize, totalSize, list);
		return pageResult;
	}

	@Override
	public OrderMemberCarUsed getLastOrderMemberCarUsedByMemberId(OrderMemberCarUsed memberCarUsed) {
		ParamVerifyUtils.paramNotNull(memberCarUsed.getMemberId(), "查询用户最后一次用车记录时，用户ID不能为空");
		return this.memberCarUsedMapper.getLastOrderMemberCarUsed(memberCarUsed);
	}

	@Override
	public OrderMemberCarUsed updateOrderInfoBeforePayment(OrderMemberCarUsed orderMemberCarUsed) {
		this.validateForCreateOrder(orderMemberCarUsed);
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		if (!OrderMemberCarUsedStatus.WAIT_PAY.getIndex().equals(old.getOrderStatus())) {
			ExceptionUtil.throwWarn("当前订单状态为" + OrderMemberCarUsedStatus.getName(old.getOrderStatus()) + ",无法执行该操作");
		}
		
		boolean resetPlan = false;
		StringBuffer result = new StringBuffer();
		
		//对比地址
		result.append(this.dealWithOrderCarUsedAddress(orderMemberCarUsed, old));
		result.append(this.dealWithOrderCarReturnAddress(orderMemberCarUsed, old));
		
		//对比时间
		String useTimeUpdate = this.compareOrderTime(orderMemberCarUsed.getOrderCarUsedTime(), old.getOrderCarUsedTime(), 0);
		if(StringUtils.isNotEmpty(useTimeUpdate)){
			result.append(useTimeUpdate);
			resetPlan = true;
		}
		String returnTimeUpdate = this.compareOrderTime(orderMemberCarUsed.getOrderCarReturnTime(), old.getOrderCarReturnTime(), 1);
		if(StringUtils.isNotEmpty(returnTimeUpdate)){
			result.append(returnTimeUpdate);
			resetPlan = true;
		}
		String carTypeUpdate = this.compareCarType(orderMemberCarUsed.getOrderCartypeId(), old.getOrderCartypeId());
		if(StringUtils.isNotEmpty(carTypeUpdate)){
			result.append(carTypeUpdate);
			resetPlan = true;
		}
		//处理库存
		if (resetPlan) {
			// 取消原来的运营计划，判断新的库存，
			this.deleteCarOperatePlan(orderMemberCarUsed);
			this.validateStock(orderMemberCarUsed.getOrderCartypeId(), orderMemberCarUsed.getOrderCarUsedTime(), orderMemberCarUsed.getOrderCarReturnTime());
			this.createCarOperatePlan(orderMemberCarUsed);
		}
		// 设置用车相关金额、邮箱体积
		this.setOrderMemberCarUsedParam(orderMemberCarUsed);
		// 修改用车记录
		orderMemberCarUsed = this.updateOrderMemberCarUsed(orderMemberCarUsed);
		// 创建用车日志
		orderMemberCarUsed.setRemark(result.toString());
		this.createOrderLog(orderMemberCarUsed);
		this.createUseCarLog(orderMemberCarUsed);
		return orderMemberCarUsed;
	}
	
	@Override
	public OrderMemberCarUsed updateOrderForReturnCarAdvance(OrderMemberCarUsed orderMemberCarUsed) {
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed);
		//检验地址
		String address = orderMemberCarUsed.getOrderCarReturnAddress();
		ParamVerifyUtils.paramNotNull(address,"提前还车时，还车地址不能为空");
		this.validateAddress(address);
		//校验还车时间
		Date returnTime = orderMemberCarUsed.getOrderCarReturnTime();
		ParamVerifyUtils.paramNotNull(returnTime,"提前还车时，还车时间不能为空");
		Date min = DateUtils.addHours(new Date(), 4);
		if(returnTime.before(min)){
			ExceptionUtil.throwWarn("还车时间必须大于当前时间4小时");
		}
		
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		
		StringBuffer result = new StringBuffer();
		result.append(this.dealWithOrderCarReturnAddress(orderMemberCarUsed, old));
		//时间没变
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(orderMemberCarUsed.getId());
		target.setOrderCarReturnAddress(address);
		if(returnTime.equals(old.getOrderCarReturnTime())){
			//地点没变
			if(StringUtils.isEmpty(result.toString().trim())){
				return old;
			}else{
				//只是修改了地点
				target = this.updateOrderMemberCarUsed(target);
				target.setRemark(result.toString());
				this.createUseCarLog(target);
				return target;
			}
		}
		
		String returnTimeUpdate = this.compareOrderTime(returnTime, old.getOrderCarReturnTime(), 1);
		result.append(returnTimeUpdate);
		//修改库存
		CarOperatePlan plan = new CarOperatePlan();
		plan.setEndTime(returnTime);
		plan.setMemberUsecarId(orderMemberCarUsed.getId());
		this.carOperatePlanService.updateOperatePlanInfoByCarUsedId(plan);
		//修改用车天数、费用、退款
		target.setOrderCarReturnTime(returnTime);
		this.dealMoneyForReturnCarAdvance(target,old);
		//修改订单
		target = this.updateOrderMemberCarUsed(target);
		//记录日志(用车日志、订单日志)
		target.setRemark(result.toString());
		this.createUseCarLog(target);
		this.createOrderLog(target);
		return target;
	}

	@Override
	public OrderMemberCarUsed updateOrderForReturnCarDelay(OrderMemberCarUsed orderMemberCarUsed) {
		// 校验
		// 创建订单
		// 插入日志
		// 扣金额
		// 预授权如何处理？
		// 库存
		return null;
	}

	
	/** 
	* @Title: dealWithOrderCarUsedAddress 
	* @Description: 处理用户用车地址
	* @author zc.du
	* @param cur 新的用车记录
	* @param old 旧的用车记录
	* @return String 地址变更日志
	*/
	private String dealWithOrderCarUsedAddress(OrderMemberCarUsed cur,OrderMemberCarUsed old){
		String oldUseAddress = old.getOrderCarUsedAddress();
		String curUseAddress = cur.getOrderCarUsedAddress();
		StringBuffer result = new StringBuffer();
		if (!StringUtils.equals(oldUseAddress, curUseAddress)) {
			result.append("预约用车地点由").append(oldUseAddress).append("改为").append(curUseAddress).append(";");
			this.updateUseCarAddress(
					new MemberCarUsedAddress(old.getMemberId(), old.getMemberName(), 0, curUseAddress, cur.getLongitudeLatitude_useCarAddress(), null, new Date()));
		}
		return result.toString();
	}
	
	/** 
	* @Title: dealWithOrderCarUsedAddress 
	* @Description: 处理用户还车地址
	* @author zc.du
	* @param cur 新的用车记录
	* @param old 旧的用车记录
	* @return String 地址变更日志
	*/
	private String dealWithOrderCarReturnAddress(OrderMemberCarUsed cur,OrderMemberCarUsed old){
		String oldAddress = old.getOrderCarReturnAddress();
		String curAddress = cur.getOrderCarReturnAddress();
		StringBuffer result = new StringBuffer();
		if (!StringUtils.equals(oldAddress, curAddress)) {
			result.append("预约用车地点由").append(oldAddress).append("改为").append(curAddress).append(";");
			this.updateUseCarAddress(
					new MemberCarUsedAddress(old.getMemberId(), old.getMemberName(), 0, curAddress, cur.getLongitudeLatitude_returnCarAddress(), null, new Date()));
		}
		return result.toString();
	}
	
	/** 
	* @Title: dealMoneyForReturnCarAdvance 
	* @Description: 处理日期变动造成的订单金额变动、账户变动
	* @author zc.du
	* @param cur
	* @param old
	*/
	private void dealMoneyForReturnCarAdvance(OrderMemberCarUsed cur,OrderMemberCarUsed old){
		//用车日期、用车费用
		Date end = cur.getOrderCarReturnTime();
		Date start = old.getOrderCarUsedTime();
		int days = this.calculateUseCarDays(start, end);
		cur.setOrderCarUsedDay(days);
		BigDecimal newCost = old.getPerBasicPrice().multiply(new BigDecimal(days));
		cur.setCarUsedCost(newCost);
		BigDecimal intervalMoney = old.getCarUsedCost().subtract(newCost);
		cur.setActualPayment(old.getActualPayment().subtract(intervalMoney));
		//退款、同步账户信息
		MemberAccountLog accountLog = new MemberAccountLog();
		accountLog.setAccountId(old.getMemberId());
		accountLog.setModifer(old.getMemberName());
		accountLog.setModifierId(old.getMemberId());
		accountLog.setOperateType(AccountOperateType.SYSTEM_BACK.getIndex());
		accountLog.setRemark("用户提前还车");
		accountLog.setTransformType(AccountTransformType.STORE.getIndex());
		accountLog.setTotal(intervalMoney);
		this.memberAccountService.updateMemberAccountBalance(accountLog);
	}
	/** 
	* @Title: compareOrderTime 
	* @Description: 对比预约时间，记录变更内容
	* @author zc.du
	* @param curDate 修改后的时间
	* @param oldDate 修改前的时间
	* @param type 0：用车；1：还车
	* @return String
	*/
	private String compareOrderTime(Date curDate,Date oldDate,int type){
		String cur = DateUtils.formatDate(curDate, "yyyy-MM-dd HH:mm:ss");
		String old = DateUtils.formatDate(oldDate, "yyyy-MM-dd HH:mm:ss");
		String result = "";
		if (null != cur && !StringUtils.equals(cur, old)) {
			result = "预约用车时间由";
			if(1 == type){
				result = "预约还车时间由";
			}
			result += old +"改为" +cur + ";";
		}
		return result;
	}
	
	private String compareCarType(Long cur,Long old){
		String result = "";
		if (!old.equals(cur)) {
			CarType oldCarType = this.carTypeService.getCarTypeById(old);
			CarType curCarType = this.carTypeService.getCarTypeById(cur);
			result = "预约车型由" + "-" + oldCarType.getCarType() + "改为" + curCarType.getBrand() + "-" + curCarType.getCarType() + ";";
		}
		return result;
	}

	private void updateUseCarAddress(MemberCarUsedAddress memberCarUsedAddress) {
		MemberCarUsedAddress search = new MemberCarUsedAddress();
		search.setMemberId(memberCarUsedAddress.getMemberId());
		search.setAddress(memberCarUsedAddress.getAddress());
		search.setType(memberCarUsedAddress.getType());
		List<MemberCarUsedAddress> list = this.memberCarUsedAddressMapper.selectByCondition(search);
		if (CollectionUtils.isEmpty(list)) {
			// 创建
			memberCarUsedAddress.setCount(1);
			memberCarUsedAddress.setCreated(new Date());
			this.memberCarUsedAddressMapper.insertSelective(memberCarUsedAddress);
		} else {
			search = list.get(0);
			if (search.getValid().equals(0)) {
				memberCarUsedAddress.setValid(1);
			}
			memberCarUsedAddress.setCount(search.getCount() + 1);
			this.memberCarUsedAddressMapper.updateByPrimaryKeySelective(memberCarUsedAddress);
		}
	}

	/**
	 * 车辆限行通知
	 */
	public void getVehicleRestrictions() {
		// 查询需要扣费用车用车任务
		OrderMemberCarUsed memberCarUsed = new OrderMemberCarUsed();
		memberCarUsed.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_USING.getIndex());
		List<OrderMemberCarUsed> powerUseds = memberCarUsedMapper.selectByCondition(memberCarUsed);
		this.log.info("定时任务：车辆限行通知扫描开始");
		if (powerUseds.size() > 0) {
			for (OrderMemberCarUsed powerUsed : powerUseds) {
				// 查询车辆信息
				Car car = new Car();
				if (powerUsed.getCaroperateId() != null && powerUsed.getCaroperateId() != 0) {
					car = this.carService.getCarById(powerUsed.getCaroperateId());
				}
				// 是否限号
				boolean isRestriction = isRestrictionDayAdd(car);
				if (isRestriction) {
					// 发送微信通知
					this.SendMessageToMember(powerUsed.getMemberId(), WechatMsgType.RESTRICTIONSUCCESS.getType());
				}
			}
		}
		this.log.info("定时任务：车辆限行通知扫描结束");
	}

	/**
	 * @Title: isRestrictionDayAdd
	 * @Description: 当前车辆明天是否参与限号
	 * @author huanghua
	 * @param car
	 * @return boolean
	 */
	private boolean isRestrictionDayAdd(Car car) {
		boolean isRestriction = false;
		// 是否参与限行
		CarType carType = carTypeService.getCarTypeById(car.getCarTypeId());
		if (carType.getIsRestriction() == 0) {
			return isRestriction;
		}
		Restriction basicRestriction = new Restriction();
		basicRestriction.setCityId(2L);
		basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(DateUtils.addDays(new Date(), 1), "yyyy-MM-dd")));
		basicRestriction = restrictionService.getRestrictionByDate(basicRestriction);
		if (basicRestriction != null) {
			// 限号尾号
			String temp = basicRestriction.getRestrictions();
			// 当前车辆尾号
			String plateNum = this.checkCode(car.getCarPlateNum());
			if (StringUtils.isNotBlank(temp)) {
				String[] code = temp.split(",");
				for (int i = 0; i < code.length; i++) {
					if (Integer.parseInt(plateNum) == Integer.parseInt(code[i])) {
						isRestriction = true;
					}
				}
			}
		}
		return isRestriction;
	}

	/**
	 * @Title: checkCode @Description: TODO(判断是否为数字) @param @param
	 *         code @param @return 设定文件 @return String 返回类型 @throws
	 */
	private String checkCode(String code) {
		String Lastcode = code.substring(code.length() - 1);
		// 判断是否为数字
		if (!Character.isDigit(Lastcode.charAt(0))) {
			Lastcode = "0";
		}
		return Lastcode;
	}

	/**
	 * @Title: SendMessageToMember
	 * @Description: 给用户推送消息
	 * @author huanghua
	 * @param id
	 * @return void
	 */
	private void SendMessageToMember(Long id, String wechatTemplateType) {
		Member member = memberService.getMemberById(id);
		if (member == null) {
			this.log.error("给会员发送消息推送，ID为{}的会员不存在，无法发送微信消息", id);
			return;
		}
		final String openId = member.getOpenid();
		if (StringUtils.isBlank(openId)) {
			this.log.error("给会员发送消息推送，ID为{}的会员openid为空，无法发送微信消息", member.getId());
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					iWechatMessageSender.sendmessage(openId, wechatTemplateType, null);
				} catch (Exception e) {
					log.error("推送失败", e);
				}
			}
		}).start();
	}

	@Override
	public List<OrderMemberCarUsed> getOrderMemberCarUsedListByCarIdAndDate(OrderMemberCarUsed memberCarUsed) {
		ParamVerifyUtils.paramNotNull(memberCarUsed, "根据车辆ID、时间查询用车记录(违章专用)时，参数不能为空");
		ParamVerifyUtils.paramNotNull(memberCarUsed.getCaroperateId(), "根据车辆ID、时间查询用车记录(违章专用)时，车辆ID不能为空");
		ParamVerifyUtils.paramNotNull(memberCarUsed.getStartDate(), "根据车辆ID、时间查询用车记录(违章专用)时，日期不能为空");
		return this.memberCarUsedMapper.getOrderMemberCarUsedListByCarIdAndDate(memberCarUsed);
	}

	private void setTotalCost(List<OrderMemberCarUsed> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			for (OrderMemberCarUsed order : list) {
				BigDecimal carUsedCost = order.getCarUsedCost();
				BigDecimal extraCost = order.getExtraCarUsedCost();
				BigDecimal limitBack = order.getLimitedBack();
				BigDecimal gasoline = this.getGasolinePrice(order);
				// order.setTotalCost(carUsedCost.add(extraCost).add(limitBack).add(gasoline));
			}
		}
	}

	private BigDecimal getGasolinePrice(OrderMemberCarUsed order) {
		BigDecimal total = new BigDecimal(0);
		BigDecimal gasV = order.getTankBefore().subtract(order.getTankAfter());
		if (order.getUnit().equals(1)) {
			gasV = gasV.multiply(order.getTankVolume()).setScale(2, BigDecimal.ROUND_UP);
		}
		total = gasV.multiply(order.getPerGasolinePrice()).setScale(2, BigDecimal.ROUND_UP);
		return total;
	}

	@Override
	public Integer getCarUsedCountByDate(OrderMemberCarUsed orderMemberCarUsed) {
		return this.memberCarUsedMapper.getCarUsedCountByDate(orderMemberCarUsed);
	}

	@Override
	public BigDecimal getCarUsedCostMoneyByDate(OrderMemberCarUsed orderMemberCarUsed) {
		return this.memberCarUsedMapper.getCarUsedCostMoneyByDate(orderMemberCarUsed);
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByConfirmUse(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 验证订单状态
		if (!MemberCarUsedStatus.MEMBERCARUSED_CREATE.getIndex().equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车记录状态为" + MemberCarUsedStatus.getName(old.getUsedStatus()));
		}
		// 校验修改的信息，重新计算费用
		// 修改订单状态
		Date date = new Date();
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_CONFIRMUSE.getIndex());
		target.setLasttimeModify(date);
		// 插入订单日志
		//
		return null;
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByConfirmPayment(OrderMemberCarUsed orderMemberCarUsed) {
		// 验证订单状态
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		if (!OrderMemberCarUsedStatus.WAIT_PAY.getIndex().equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车订单状态为" + OrderMemberCarUsedStatus.getName(old.getUsedStatus()) + "，无法进行该操作");
		}
		// 验证余额
		this.validateBalance(old.getMemberId(), old.getCarUsedCost(), "余额补足支付用车订单，请储值");
		// 修改订单状态
		Date date = new Date();
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_WAITSENDCAR.getIndex());
		target.setOrderStatus(OrderMemberCarUsedStatus.COMPLETE_PAY.getIndex());
		target.setActualPayment(old.getCarUsedCost());
		target.setPaymentConfirmTime(date);
		target.setLasttimeModify(date);
		this.memberCarUsedMapper.updateByPrimaryKeySelective(target);
		old = this.memberCarUsedMapper.selectByPrimaryKey(old.getId());
		// 扣除余额
		this.updateMemberAccountBalance(old);
		// 插入订单日志
		this.createOrderLog(old);
		this.createUseCarLog(old);
		// 创建任务
		this.createButlerTask(orderMemberCarUsed);
		// 发消息
		return old;
	}

	private OrderMemberCarUsed getOrderMemberCarUsedByIdForValidate(OrderMemberCarUsed orderMemberCarUsed) {
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed);
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getId(), "用户用车记录ID不能为空");
		OrderMemberCarUsed old = this.memberCarUsedMapper.selectByPrimaryKey(orderMemberCarUsed.getId());
		ParamVerifyUtils.paramNotNull(old, "用车记录不存在");
		return old;
	}

	private void updateMemberAccountBalance(OrderMemberCarUsed orderMemberCarUsed) {
		MemberAccountLog log = new MemberAccountLog();
		log.setMemberId(orderMemberCarUsed.getMemberId());
		log.setModifer(orderMemberCarUsed.getMemberName());
		log.setModifierId(orderMemberCarUsed.getMemberId());
		log.setOperateType(AccountOperateType.SYSTEM_DEDUCTION.getIndex());
		log.setRemark("用户用车扣费");
		log.setTotal(orderMemberCarUsed.getCarUsedCost());
		log.setTransformType(AccountTransformType.SPEND.getIndex());
		this.memberAccountService.updateMemberAccountBalance(log);
	}

	private void createOrderLog(OrderMemberCarUsed orderMemberCarUsed) {
		// 订单日志
		OperateLog log = new OperateLog();
		log.setCreated(new Date());
		log.setModifier(orderMemberCarUsed.getModifer());
		log.setModifierId(orderMemberCarUsed.getModifierId());
		log.setOperationId(orderMemberCarUsed.getId());
		log.setRemark(orderMemberCarUsed.getRemark());
		log.setStatus(orderMemberCarUsed.getOrderStatus());
		log.setType(OperateLogEnum.ORDER_MEMBER_USE_CAR.getValue());
		this.operateLogService.createOperateLog(log);
	}

	private void createUseCarLog(OrderMemberCarUsed orderMemberCarUsed) {
		OperateLog log = new OperateLog();
		log.setCreated(new Date());
		log.setModifier(orderMemberCarUsed.getModifer());
		log.setModifierId(orderMemberCarUsed.getModifierId());
		log.setOperationId(orderMemberCarUsed.getId());
		log.setRemark(orderMemberCarUsed.getRemark());
		log.setStatus(orderMemberCarUsed.getUsedStatus());
		log.setType(OperateLogEnum.MEMBER_USE_CAR.getValue());
		this.operateLogService.createOperateLog(log);
	}

	private void createButlerTask(OrderMemberCarUsed orderMemberCarUsed) {
		// 创建送车任务
		ButlerTask send = new ButlerTask();
		send.setCarUsedId(orderMemberCarUsed.getId());
		send.setTaskAddress(orderMemberCarUsed.getOrderCarUsedAddress());
		send.setPlanTime(orderMemberCarUsed.getOrderCarUsedTime());
		send.setMemberId(orderMemberCarUsed.getMemberId());
		send.setModifier(orderMemberCarUsed.getModifer());
		send.setModifierId(orderMemberCarUsed.getModifierId());
		this.butlerTaskService.createUserCarSendCarButlerTask(send);
		// 创建取车任务
		ButlerTask get = new ButlerTask();
		get.setCarUsedId(orderMemberCarUsed.getId());
		get.setTaskAddress(orderMemberCarUsed.getOrderCarReturnAddress());
		get.setPlanTime(orderMemberCarUsed.getOrderCarReturnTime());
		get.setMemberId(orderMemberCarUsed.getMemberId());
		get.setModifier(orderMemberCarUsed.getModifer());
		get.setModifierId(orderMemberCarUsed.getModifierId());
		this.butlerTaskService.createUserCarGetCarButlerTask(get);
	}

	private void setOrderMemberCarUsedParam(OrderMemberCarUsed orderMemberCarUsed) {
		// 设置用车费用、基础费用、保险费用
		CarType type = this.carTypeService.getCarTypeById(orderMemberCarUsed.getOrderCartypeId());
		BigDecimal baseUseCarPrice = type.getBasicCost();
		BigDecimal insurance = type.getInsuranceUnlock();
		BigDecimal useDay = new BigDecimal(orderMemberCarUsed.getOrderCarUsedDay());
		BigDecimal carUsedCost = (baseUseCarPrice.add(insurance)).multiply(useDay).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal tankVolume = type.getTankVolume();
		orderMemberCarUsed.setPerBasicPrice(baseUseCarPrice);
		orderMemberCarUsed.setPerInsurancePrice(insurance);
		orderMemberCarUsed.setCarUsedCost(carUsedCost);
		orderMemberCarUsed.setTankVolume(tankVolume);
	}

	private void validateForCreateOrder(OrderMemberCarUsed memberCarUsed) {
		ParamVerifyUtils.paramNotNull(memberCarUsed);
		Date start = memberCarUsed.getOrderCarUsedTime();
		Date end = memberCarUsed.getOrderCarReturnTime();
		Long carTypeId = memberCarUsed.getOrderCartypeId();
		ParamVerifyUtils.paramNotNull(memberCarUsed.getMemberId(), "用户发起用车时，用户ID不能为空");
		ParamVerifyUtils.paramNotNull(carTypeId, "用户发起用车时，预约车型ID不能为空");
		ParamVerifyUtils.paramNotNull(start, "用户发起用车时，预计用车开始时间不能为空");
		ParamVerifyUtils.paramNotNull(end, "用户发起用车时，预计用车结束时间不能为空");
		ParamVerifyUtils.paramNotEmpty(memberCarUsed.getOrderCarUsedAddress(), "用户发起用车时，预约用车地址不能为空");
		ParamVerifyUtils.paramNotEmpty(memberCarUsed.getOrderCarReturnAddress(), "用户发起用车时，预约还车地点不能为空");
		// 验证地址
		this.validateAddress(memberCarUsed.getOrderCarUsedAddress());
		this.validateAddress(memberCarUsed.getOrderCarReturnAddress());
		// 验证用车/还车时间
		this.validateTimeForCreate(start, end);
		int interval = this.calculateUseCarDays(start, end);
		if (interval > 27) {
			ExceptionUtil.throwWarn("用车天数必须小于等于27天");
		}
		// 判断用户
		Member member = this.validateMember(memberCarUsed.getMemberId());
		// 判断余额
		this.validateBalance(memberCarUsed.getMemberId(), new BigDecimal(0), "请先补足欠款");
		memberCarUsed.setMemberName(member.getName());
		memberCarUsed.setMemberMobile(member.getMobile());
		memberCarUsed.setOrderCarUsedDay(interval);
	}

	private Member validateMember(Long memberId) {
		// 判断用户是否存在
		Member member = this.memberService.getMemberById(memberId);
		ParamVerifyUtils.paramNotNull(member, "用户不存在，无法发起用车");
		// 判断用户是否冻结
		if (member.getStatuts().equals(MemberStatus.FROZEN.getIndex())) {
			ExceptionUtil.throwWarn("用户被冻结，无法发起用车");
		}
		return member;
	}

	private void validateBalance(Long memberId, BigDecimal target, String memssage) {
		MemberAccount account = this.memberAccountService.getMemberAccountByMemberId(memberId);
		if (account.getBalance().subtract(target).doubleValue() < 0) {
			ExceptionUtil.throwWarn(memssage);
		}
	}

	private void validateTimeForCreate(Date start, Date end) {
		if (start.after(end)) {
			ExceptionUtil.throwWarn("预计用车时间不能早于预计还车时间");
		}

		Date min = DateUtils.addHours(new Date(), 2);
		if (min.before(start)) {
			ExceptionUtil.throwWarn("用车时间必须大于当前时间2小时");
		}
	}

	private int calculateUseCarDays(Date start, Date end) {
		Long result = 0L;
		long interval = start.getTime() - end.getTime();
		long remainder = interval % (1000 * 60 * 60 * 24);
		if (remainder == 0) {
			result = interval / (1000 * 60 * 60 * 24);
		} else {
			result = interval / (1000 * 60 * 60 * 24) + 1;
		}
		return result.intValue();
	}

	private void validateStock(Long carTypeId, Date start, Date end) {
		boolean hasStock = this.stockService.isExitStock(carTypeId, start, end);
		if (!hasStock) {
			ExceptionUtil.throwWarn("所选时间段内，所选车型无库存，发起用车失败");
		}
	}

	private String getUpdateContent(OrderMemberCarUsed cur, OrderMemberCarUsed old) {
		StringBuffer result = new StringBuffer();
		String oldGetAddress = old.getOrderCarUsedAddress();
		String oldReturnAddress = old.getOrderCarReturnAddress();
		String curGetAddress = cur.getOrderCarUsedAddress();
		String curReturnAddress = cur.getOrderCarReturnAddress();
		String oldUseTime = DateUtils.formatDate(old.getOrderCarUsedTime(), "yyyy-MM-dd HH:mm:ss");
		String curUseTime = cur.getOrderCarUsedTime() == null ? null : DateUtils.formatDate(cur.getOrderCarUsedTime(), "yyyy-MM-dd HH:mm:ss");
		String oldReturnTime = DateUtils.formatDate(old.getOrderCarReturnTime(), "yyyy-MM-dd HH:mm:ss");
		String curReturnTime = cur.getOrderCarReturnTime() == null ? null : DateUtils.formatDate(cur.getOrderCarReturnTime(), "yyyy-MM-dd HH:mm:ss");

		if (null != curGetAddress && !StringUtils.equals(oldGetAddress, curGetAddress)) {
			result.append("预约用车地点由").append(oldGetAddress).append("改为").append(curGetAddress).append(";");
		}
		if (null != curReturnAddress && !StringUtils.equals(oldReturnAddress, curReturnAddress)) {
			result.append("预约还车地点由").append(oldReturnAddress).append("改为").append(curReturnAddress).append(";");
		}
		if (null != curUseTime && !StringUtils.equals(oldUseTime, curUseTime)) {
			result.append("预约用车时间由").append(oldReturnAddress).append("改为").append(curReturnAddress).append(";");
		}
		if (null != curReturnTime && !StringUtils.equals(oldReturnTime, curReturnTime)) {
			result.append("预约还车时间由").append(oldReturnAddress).append("改为").append(curReturnAddress).append(";");
		}
		if (null != cur.getOrderCartypeId() && !old.getOrderCartypeId().equals(cur.getOrderCartypeId())) {
			CarType oldCarType = this.carTypeService.getCarTypeById(old.getOrderCartypeId());
			CarType curCarType = this.carTypeService.getCarTypeById(cur.getOrderCartypeId());
			result.append("预约车型由").append(oldCarType.getBrand() + "-" + oldCarType.getCarType()).append("改为").append(curCarType.getBrand() + "-" + curCarType.getCarType()).append(";");
		}
		return result.toString();
	}

	/**
	 * @Title: validateAddress
	 * @Description: 验证地址是否在六环内容
	 * @author zc.du
	 * @param address
	 * @return void
	 */
	private void validateAddress(String address) {

	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByUsing(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 操作人验证
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifer(), "操作人不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifierId(), "操作人ID不能为空");
		// 状态校验
		if (!MemberCarUsedStatus.MEMBERCARUSED_WAITSENDCAR.equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车状态为" + MemberCarUsedStatus.getName(old.getUsedStatus()) + ",无法执行该操作");
		}
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_USING.getIndex());
		target.setActualCarUsedTime(new Date());
		target.setModifer(orderMemberCarUsed.getModifer());
		target.setModifierId(orderMemberCarUsed.getModifierId());
		target.setRemark(orderMemberCarUsed.getRemark());
		this.createUseCarLog(orderMemberCarUsed);
		return this.updateOrderMemberCarUsed(target);
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByComplete(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 操作人验证
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifer(), "操作人不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifierId(), "操作人ID不能为空");
		// 状态校验
		if (!MemberCarUsedStatus.MEMBERCARUSED_USING.equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车状态为" + MemberCarUsedStatus.getName(old.getUsedStatus()) + ",无法执行该操作");
		}
		// 计算金额 更新油价、油箱油量、扣除油费 插入计算记录？？？

		// 修改订单状态
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_COMPLETE.getIndex());
		target.setActualCarReturnTime(new Date());
		target.setModifer(orderMemberCarUsed.getModifer());
		target.setModifierId(orderMemberCarUsed.getModifierId());
		target.setRemark(orderMemberCarUsed.getRemark());
		this.createUseCarLog(orderMemberCarUsed);
		return this.updateOrderMemberCarUsed(target);
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByLose(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 操作人验证
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifer(), "操作人不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifierId(), "操作人ID不能为空");
		// 状态校验
		if (!MemberCarUsedStatus.MEMBERCARUSED_USING.equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车状态为" + MemberCarUsedStatus.getName(old.getUsedStatus()) + ",无法执行该操作");
		}
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_LOSED.getIndex());
		target.setModifer(orderMemberCarUsed.getModifer());
		target.setModifierId(orderMemberCarUsed.getModifierId());
		target.setRemark(orderMemberCarUsed.getRemark());
		this.createUseCarLog(orderMemberCarUsed);
		return this.updateOrderMemberCarUsed(target);
	}

	private OrderMemberCarUsed updateOrderMemberCarUsed(OrderMemberCarUsed orderMemberCarUsed) {
		orderMemberCarUsed.setLasttimeModify(new Date());
		orderMemberCarUsed.setRemark(null);
		this.memberCarUsedMapper.updateByPrimaryKeySelective(orderMemberCarUsed);
		return this.memberCarUsedMapper.selectByPrimaryKey(orderMemberCarUsed.getId());
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusByCompleteForLose(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 操作人验证
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifer(), "操作人不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifierId(), "操作人ID不能为空");
		// 状态校验
		if (!MemberCarUsedStatus.MEMBERCARUSED_LOSED.equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车状态为" + MemberCarUsedStatus.getName(old.getUsedStatus()) + ",无法执行该操作");
		}
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_COMPLETE.getIndex());
		target.setActualCarReturnTime(new Date());
		target.setModifer(orderMemberCarUsed.getModifer());
		target.setModifierId(orderMemberCarUsed.getModifierId());
		target.setRemark(orderMemberCarUsed.getRemark());
		this.createUseCarLog(orderMemberCarUsed);
		return this.updateOrderMemberCarUsed(target);
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedStatusBycancel(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 操作人验证
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifer(), "操作人不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifierId(), "操作人ID不能为空");
		// 状态校验
		if (MemberCarUsedStatus.MEMBERCARUSED_COMPLETE.equals(old.getUsedStatus()) || MemberCarUsedStatus.MEMBERCARUSED_CANCEL.equals(old.getUsedStatus())) {
			ExceptionUtil.throwWarn("当前用车状态为" + MemberCarUsedStatus.getName(old.getUsedStatus()) + ",无法执行该操作");
		}
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_CANCEL.getIndex());
		target.setModifer(orderMemberCarUsed.getModifer());
		target.setModifierId(orderMemberCarUsed.getModifierId());
		target.setRemark(orderMemberCarUsed.getRemark());
		this.createOrderLog(orderMemberCarUsed);
		this.createUseCarLog(orderMemberCarUsed);
		return this.updateOrderMemberCarUsed(target);
	}

	@Override
	public OrderMemberCarUsed updateOrderMemberCarUsedForSettingCar(OrderMemberCarUsed orderMemberCarUsed) {
		OrderMemberCarUsed old = this.getOrderMemberCarUsedByIdForValidate(orderMemberCarUsed);
		// 操作人验证
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getCaroperateId(), "车辆ID不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifer(), "操作人不能为空");
		ParamVerifyUtils.paramNotNull(orderMemberCarUsed.getModifierId(), "操作人ID不能为空");
		Car car = this.carService.getCarById(orderMemberCarUsed.getCaroperateId());
		ParamVerifyUtils.paramNotNull(car, "分配车辆不存在");
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		target.setId(old.getId());
		target.setCaroperateId(orderMemberCarUsed.getCaroperateId());
		target.setModifer(orderMemberCarUsed.getModifer());
		target.setModifierId(orderMemberCarUsed.getModifierId());
		target.setRemark("分配车辆：" + car.getCarPlateNum());
		this.createUseCarLog(orderMemberCarUsed);
		return this.updateOrderMemberCarUsed(target);
	}

	@Override
	public OrderMemberCarUsed getOrderMemberCarUsedByMobileAndCarPlateNum(String mobile, String carPlateNum) {
		if(StringUtils.isEmpty(mobile) && StringUtils.isEmpty(carPlateNum)){
			ExceptionUtil.throwWarn("根据手机号、车牌号查询使用中的用车记录时，手机号、车牌号不能同时为空");
		}
		OrderMemberCarUsed target = new OrderMemberCarUsed();
		if(StringUtils.isNotEmpty(carPlateNum)){
			Car car = this.carService.getCarByCarPlateNum(carPlateNum);
			ParamVerifyUtils.paramNotNull(car, "车牌号为"+carPlateNum+"的车辆不存在");
			target.setCaroperateId(car.getId());
		}
		if(StringUtils.isNotEmpty(mobile)){
			target.setMemberMobile(mobile);
		}
		target.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_USING.getIndex());
		List<OrderMemberCarUsed> list = this.memberCarUsedMapper.selectByCondition(target);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
}
