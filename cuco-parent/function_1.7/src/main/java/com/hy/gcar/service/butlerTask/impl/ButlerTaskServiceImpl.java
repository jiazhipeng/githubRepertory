package com.hy.gcar.service.butlerTask.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hy.gcar.entity.*;
import com.hy.gcar.service.getmoney.GetmoneyApplyService;
import com.hy.gcar.utils.ResultUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.ButlerTaskType;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.dao.ButlerTaskMapper;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicCostService;
import com.hy.gcar.service.basic.BasicServiceService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.butlerTask.ButlerTaskSendCarService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.member.MemberStatusLogService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.powerUserd.PowerUsedCostLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.service.MessageAppService;

@Service
public class ButlerTaskServiceImpl implements ButlerTaskService {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ButlerTaskMapper<ButlerTask> butlerTaskMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ButlerTaskStatusLogService butlerTaskStatusLogService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	ItemService itemService;
	@Autowired
	PowerUsedService powerUsedService;
	@Autowired
	MemberItemCartypeService memberItemCartypeService;
	@Autowired
	MemberItemShareService memberItemShareService;
	@Autowired
	ItemCartypeService itemCartypeService;
	@Autowired
	BasicNoticeService basicNoticeService;
	@Autowired
	private CarOperateService carOperateService;
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	@Autowired
	private PowerUsedCostService powerUsedCostService;
	@Autowired
	private BasicCostService basicCostService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private BasicServiceService basicServiceService;
	@Autowired
	private PowerUsedCostLogService powerUsedCostLogService;
	@Autowired
	WechatMessageService wechatMessageService;
	@Autowired
	private MessageAppService messageAppService;
	@Autowired
	private BasicThresholdService basicThresholdService;
	
	@Autowired
	private CarOperateLogService carOperateLogService;
	@Autowired
	private GetmoneyApplyService getmoneyApplyService;
	@Autowired
	private ButlerTaskSendCarService butlerTaskSendCarService;
	@Autowired
	private MemberStatusLogService memberStatusLogService;


	@Override
	public ButlerTask createButlerTask(ButlerTask butlerTask) {
		butlerTask.setCreated(new Date());
		butlerTask.setLasttimeModify(butlerTask.getCreated());
		this.butlerTaskMapper.insertSelective(butlerTask);
		return butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
	}

	@Override
	public ButlerTask updateButlerTask(ButlerTask butlerTask) {
		butlerTask.setLasttimeModify(new Date());
		this.butlerTaskMapper.updateByPrimaryKeySelective(butlerTask);
		return butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
	}

	@Override
	public List<ButlerTask> getButlerTask(ButlerTask butlerTask) {
		List<ButlerTask> butlerTaskList = this.butlerTaskMapper.selectByCondition(butlerTask);
		return butlerTaskList;
	}

	@Override
	public ButlerTask getButlerTaskById(Long id) {
		this.logger.info("任务查询ID为--"+id);
		return butlerTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public void DeleteButlerTaskById(Long id) {
		this.logger.info("任务删除ID为--"+id);
		this.butlerTaskMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<ButlerTask> getButlerTasksBySendCar(ButlerTask butlerTask) {
		Assert.notNull(butlerTask,"查询对象不允许为空");
		return butlerTaskMapper.selectListBySendCar(butlerTask);
	}

	@Override
	public List<ButlerTask> getButlerTaskCalendarList(List<ButlerTask> butlerTasks, int maxDay) {
		List<ButlerTask> butlers = Lists.newArrayList();
		ButlerTask butlerT = null;
		for(int i = 1; i<= maxDay ; i++){
			butlerT = new ButlerTask();
			butlerT.setOperaterId(butlerTasks.get(0).getOperaterId());
			butlerT.setNumOfdays(String.valueOf(i));
			int total = this.getEveryDaySendCarTaskTotal(butlerTasks,i);
			butlerT.setTotal(total);
			butlers.add(butlerT);
		}
		return butlers;
	}

	@Override
	public ButlerTask createSendCarButlerTask(ButlerTask butlerTask) throws Exception {
		Assert.notNull(butlerTask,"送车任务实体不能为空");
		Member operaterMember = memberService.findMemberByID(Long.valueOf(butlerTask.getOperaterId()));
		if(operaterMember.getIsButler() == 1){//管家
			//如果创建任务人的身份为管家，设置客服负责人为后台设置的总客服，管家为当前操作人。
			butlerTask.setOperaterId(operaterMember.getSysuserId());
			butlerTask.setOperaterName(operaterMember.getSysuserName());
			//设置客服负责人
			this.setCustomer(butlerTask);

		}else if(operaterMember.getIsCustomer() == 1){
			//如果创建任务人的身份为客服，设置管家负责人为后台设置的总管家，客服为当前操作人。
			butlerTask = this.setOperater(butlerTask, 1);
			butlerTask.setCustomerId(operaterMember.getSysuserId());
			butlerTask.setCustomerName(operaterMember.getSysuserName());
		}

		MemberItemShare memberItemShare = memberItemShareService.selectByMemberId(Long.valueOf(butlerTask.getMemberId()));
		MemberItem memberItem =null;
		if(memberItemShare != null){
			memberItem = memberItemService.findById(memberItemShare.getMemberItemId());
			butlerTask.setMemberItemId(memberItem.getId());
		}
		//1、生成一条用车记录
		PowerUsed powerUsed = this.createPowerUsed(butlerTask,memberItem);
		//2、生成一条送车任务
		butlerTask.setCreateType(1);
		butlerTask.setCompleteTime(null);
		butlerTask.setPowerUsedId(powerUsed.getId());
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_TREATMENT.getIndex());
		//设置负责人
//		butlerTask = this.setOperater(butlerTask, 1);
//		this.setCustomer(butlerTask);
		this.createButlerTask(butlerTask);
		this.createButlerTaskStatusLog(butlerTask);

		//3、生成一条取车任务
		ButlerTask butlerTaskGet = new ButlerTask();
		BeanUtils.copyProperties(butlerTask,butlerTaskGet);
		butlerTaskGet.setId(null);
        butlerTaskGet.setCompleteTime(null);
		butlerTaskGet.setPlanTime(powerUsed.getCarReturnTime());//还车开始时间为送车任务的结束时间
		//butlerTaskGet.setTaskSendCarAddress();
		this.createGetCarButlerTask(butlerTaskGet);
		//创建一条 td_power_used_cost
		this.createPowerUsedCost(butlerTask);
		//创建车辆运营记录
		this.insertCarRecordToBeExecuted(butlerTask);
		//给客服发送微信消息推送
		String value = "您有新的送车任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreateToCustomer(butlerTask, value);
		return butlerTask;
	}

	public void createPowerUsedCost(ButlerTask butlerTask) throws Exception {
		PowerUsedCost powerUsedCost = new PowerUsedCost();
		powerUsedCost.setPowerUsedId(butlerTask.getPowerUsedId());//用车记录主表ID
		powerUsedCost.setTotal(new BigDecimal(0));//用车总费用
		powerUsedCost.setHurryPrice(new BigDecimal(0));//用车加急费
		powerUsedCost.setUsedcarPrice(new BigDecimal(0));//用车费用
		powerUsedCost.setGasoline(new BigDecimal(0));//油耗（L）
		powerUsedCost.setGasolinePrice(new BigDecimal(0));//油耗费用（非电动）
		powerUsedCost.setReplacecarPrice(new BigDecimal(0));//更换启用车费用
		powerUsedCost.setAccidentcarPrice(new BigDecimal(0));//紧急救援费用
		powerUsedCost.setCouponId(null);//优惠券ID
		powerUsedCost.setCouponName(null);//优惠券名称
		powerUsedCost.setCouponPrice(new BigDecimal(0));//优惠券金额
		powerUsedCost.setContractPrice(new BigDecimal(0));//违约费
		powerUsedCostService.insertSelective(powerUsedCost);
	}

	public PowerUsed createPowerUsed(ButlerTask butlerTask,MemberItem memberItem) throws Exception {
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setMemberId(Long.valueOf(butlerTask.getMemberId()));
		powerUsed.setMemberItemId(memberItem.getId());
		powerUsed.setMemberItemName(memberItem.getItemName());
		powerUsed.setCaroperateId(null);
		//公用人类型
		MemberItemShare mis = new MemberItemShare();
		mis.setMemberId(Long.valueOf(butlerTask.getMemberId()));
		List<MemberItemShare> memberItemShares = memberItemShareService.selectByCondition(mis);
		if(CollectionUtils.isNotEmpty(memberItemShares)){
			//共用人类型 0:共用人；1:主；
			powerUsed.setMemberType(memberItemShares.get(0).getIsMain());

		}
		powerUsed.setCarUsedTime(butlerTask.getPlanTime());
		powerUsed.setCarUsedAddress(butlerTask.getTaskSendCarAddress());
		Date planTime = DateUtils.parseDate(DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd 00:00:00"));
		Date completeTime = DateUtils.parseDate(DateUtils.formatDate(butlerTask.getCompleteTime(),"yyyy-MM-dd 00:00:00"));
		int days = (int)DateUtils.getDistanceOfTwoDate(planTime,completeTime);
		powerUsed.setCarUsedDay(days + 1);
		//原启用车型的车型ID
		powerUsed.setCarTypeId(butlerTask.getCarTypeId());
		//用车的状态0：进行中 1：已完成 2：已取消
		powerUsed.setUsedStatus(0);
		powerUsed.setCarReturnAddress(butlerTask.getTaskGetCarAddress());
		powerUsed.setCarReturnTime(butlerTask.getCompleteTime());

		powerUsed.setChangeStatus(0);
		powerUsed.setCityCode(null);
		powerUsedService.insertSelective(powerUsed);
		return powerUsed;
	}

	@Override
	public ButlerTask createMemberSendCarButlerTask(ButlerTask butlerTask) throws Exception {
		//2、生成一条送车任务
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_TREATMENT.getIndex());
		//设置管家负责人
		butlerTask = this.setOperater(butlerTask, 1);
		//设置客服负责人
		this.setCustomer(butlerTask);
		this.createButlerTask(butlerTask);
		this.createButlerTaskStatusLog(butlerTask);

		//给客服发送微信消息推送
		String value = "您有新的送车任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreateToCustomer(butlerTask, value);
		return butlerTask;
	}
	
	

	private ButlerTask setCustomer(ButlerTask butlerTask) {
		//给后台基础设置-通知管理设置的通知人发送微信以及app通知
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
			try {
				member_notice = this.memberService.findMemberByID(memberId);
			} catch (Exception e) {
				this.logger.error("查询用户异常");
				e.printStackTrace();
			}
		}
		butlerTask.setCustomerName(member_notice.getSysuserName());
		butlerTask.setCustomerId(member_notice.getSysuserId());
		return butlerTask;
	}

	@Override
	public ButlerTask updateSendCarButlerTaskBySending(ButlerTask butlerTask) throws Exception {
		Assert.notNull(butlerTask,"修改送车任务-送车中-对象不能为空");
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_SEND_CAR.getIndex());
		butlerTask.setCompleteTime(null);
		butlerTask = this.updateButlerTask(butlerTask);
        this.createButlerTaskStatusLog(butlerTask_old);
       //将用车记录状态出发送车
      	this.updatePowerUsedStatus(butlerTask, 1);
		if(butlerTask.getCarOperateId()!=null){
			//插入一条车辆预约记录
			if(null==butlerTask_old.getCarOperateId()){
				//根据用车日期查询，用车日期小于数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划  做修改
				updateCarOperatePlanByStarted(butlerTask);
			}else if(butlerTask.getCarOperateId().longValue() != butlerTask_old.getCarOperateId().longValue()){
				//根据用车日期查询，用车日期小于数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划  做修改
				updateCarOperatePlanByStarted(butlerTask);
			}
			//修改 车辆计划表的车辆id 和车型id
			updateCarOperatePlan(butlerTask,butlerTask_old,CarOperatePlanEnum.status.USEING.getValue());
		}
		//给用户发送消息推送--出发取车微信
		String value ="极车管家已出发送车，请您保持电话畅通。";
		this.sendMessageByTaskStatusChangeApp(butlerTask,value);
		//给用户发送app推送消息
		String wechatMessage = "极车管家已出发，请您保持电话畅通";
		this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
				
        return butlerTask;
	}

	/**
	 * 插入车辆记录
	 * @param butlerTask
	 */
	public CarOperatePlan insertCarRecord(ButlerTask butlerTask,int status) throws Exception {
//		Assert.notNull(butlerTask.getCarOperateId(),"车辆id不能为空");
        logger.info("插入预约记录carOperatePlan记录");
      //插入用车计划表中的 预计还车时间
  		PowerUsed powerUsed = new PowerUsed();
  		powerUsed.setId(butlerTask.getPowerUsedId());
  		powerUsed = powerUsedService.findById(powerUsed);
	    CarOperatePlan carOperatePlan = new CarOperatePlan();
		if(butlerTask.getCarOperateId()!=null){
			CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
			carOperatePlan.setOperateNum(carOperate.getOperateNum());
			carOperatePlan.setCarPlateNum(carOperate.getCarPlateNum());
			carOperatePlan.setOperateId(carOperate.getId());
			carOperatePlan.setCarTypeId(carOperate.getCarTypeId());
		}else{
			carOperatePlan.setCarTypeId(powerUsed.getCarTypeId());
		}
		carOperatePlan.setCreated(new Date());
		carOperatePlan.setStarted(butlerTask.getPlanTime());
		
		carOperatePlan.setPowerUsedId(powerUsed.getId());
		carOperatePlan.setEnded(powerUsed.getCarReturnTime());
		//台运营类型名称(如:专车、婚庆、短租等)的id
		carOperatePlan.setOperateType(null);
		carOperatePlan.setMoney(null);//运营收益金额
		carOperatePlan.setIsStock(1);//'是否影响库存 1影响库存2无影响库存', 
		carOperatePlan.setStatus(status);//运营状态 0:待执行；1:执行中；2:已完成；3:已取消；4:已终止；
		carOperatePlan.setOperateTo(null);//使用方
		carOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSE.getValue());
		carOperatePlan.setMemberId(Long.decode(butlerTask.getMemberId()));//会员id

		carOperatePlan.setMemberName(butlerTask.getMemberName()); //会员姓名

		carOperatePlan.setMemberMobile(butlerTask.getMemberMobile());  //会员手机号

		carOperatePlan.setCarUsedAddress(butlerTask.getTaskSendCarAddress());  //用车地址
		
		carOperatePlan.setButlerId(butlerTask.getOperaterId()); //管家id
		
		carOperatePlan.setButlerName(butlerTask.getOperaterName()); //管家姓名

		int id = carOperatePlanService.insertSelective(carOperatePlan);
		logger.info("插入预约记录carOperatePlan记录  结束 carPlan的id"+id);
		return carOperatePlan;
	}

	@Override
	public ButlerTask updateSendCarButlerTaskByArrived(ButlerTask butlerTask) {
		Assert.notNull(butlerTask,"修改-送车任务-已到达-对象不能为空");
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex());
		butlerTask = this.updateButlerTask(butlerTask);
        
        this.createButlerTaskStatusLog(butlerTask_old);
        //将用车记录状态送车完成
      	this.updatePowerUsedStatus(butlerTask, 2);
      	
      	//给用户发送消息推送--出发取车微信
  		String value ="极车管家已到达送车地点，请您及时取车。";
  		this.sendMessageByTaskStatusChangeApp(butlerTask,value);
  		//给用户发送app推送消息
  		String wechatMessage = "极车管家已到达送车地点，请您及时取车";
  		this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
      		
        return butlerTask;
	}

	@Override
	public ButlerTask updateSendCarButlerTaskByComplete(ButlerTask butlerTask) throws Exception {
		Assert.notNull(butlerTask,"修改-送车任务-已完成-对象不能为空");
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
		//设置完成时间
		butlerTask.setCompleteTime(new Date());
        butlerTask.setStatusCondition(ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex());
        int result = this.butlerTaskMapper.updateSendCarButlerTaskByStatus(butlerTask);
        butlerTask =  butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
        if (result == 0){
            this.logger.info("没有进行修改，送车任务状态为：" + JSONObject.toJSONString(butlerTask));
            return butlerTask;
        }
		//根据用车人id 查询取车任务，并且将车辆更新到到取车任务。
		this.updateCarOperateByGetCar(butlerTask);
		//将车辆id绑定到用车记录
		this.updateCarOperateByPowerUsed(butlerTask);
		//插入任务日志
        this.createButlerTaskStatusLog(butlerTask_old);
        //将用车记录状态送车完成
      	this.updatePowerUsedStatus(butlerTask, 3);
      	//送车完成将权益车位变成使用中
      	//this.updateMemberItemCartypeStatus(butlerTask, 2);
        //车型的日租金
        BigDecimal price = this.findCarTypeDailyRent(butlerTask);
      	//送车完成扣第一次用车费
		this.deductionOfFare(butlerTask,price);
		//更改车辆运营状态为会员使用中，运营状态 为执行中
		this.updateCarOperatePlan(butlerTask,price);
		//更改车辆状态
		//this.updateCarOperateStatus(butlerTask);
		//TODO 扣除加急费
        this.updateUrgentCost(butlerTask,price);
		//TODO 送车完成的时候 修改 td_member_item_cartype status改为 使用中 修改当前使用车辆ID
//		this.updateMemberItemCartype(butlerTask);
		//给用户发送消息推送--出发取车微信
  		String value ="极车管家已将您的爱车送到。";
  		this.sendMessageByTaskStatusChangeApp(butlerTask,value);
  		//给用户发送app推送消息
  		String wechatMessage = "极车管家已将您的爱车送到";
  		this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
  		
        return butlerTask;
	}

	/**
	 * 修改车辆状态
	 * @param butlerTask
	 *//*
	public void updateCarOperateStatus(ButlerTask butlerTask) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(butlerTask.getCarOperateId());
		carOperate.setStatus(2);
		this.carOperateService.updateByPrimaryKeySelective(carOperate);
	}*/
	/**
	 * 送车完成的时候 修改 td_member_item_cartype status改为 使用中 修改当前使用车辆ID
	 * @param butlerTask
	 * @throws Exception
	 */
	public  void updateMemberItemCartype(ButlerTask butlerTask) throws Exception {

		MemberItemCartype memberItemCartype = new MemberItemCartype();
		memberItemCartype.setMemberItemId(butlerTask.getMemberItemId());
        PowerUsed powerUsed = this.powerUsedService.findById(butlerTask.getPowerUsedId());
        memberItemCartype.setCarTypeId(powerUsed.getCarTypeId());
		List<MemberItemCartype> memberItemCartypes = memberItemCartypeService.selectByCondition(memberItemCartype);
		if(memberItemCartypes.size() == 0){
			this.logger.info("根据条件："+ JSONObject.toJSONString(memberItemCartype) + ",查询MemberItemCartype 为空");
			throw  new Exception("查询MemberItemCartype 为空");
		}
		memberItemCartype = memberItemCartypes.get(0);
		MemberItemCartype memberItemUpdate = new MemberItemCartype();
		memberItemUpdate.setId(memberItemCartype.getId());
		memberItemUpdate.setStatus(2);
		memberItemUpdate.setCarOperateId(butlerTask.getCarOperateId());
		memberItemCartypeService.updateByPrimaryKeySelective(memberItemUpdate);
	}

    /**
	 * 扣除加急费
	 */
	public void updateUrgentCost(ButlerTask butlerTask,BigDecimal price) throws Exception {
	    this.logger.info("扣除加急费开始...."+ JSONObject.toJSONString(butlerTask));
        BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
        this.logger.info("basicThreshold>>>>>" + JSONObject.toJSONString(basicThreshold));
        if(basicThreshold == null){
            this.logger.info(" basicThreshold 表为空 ");
            return;
        }

        PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
        this.logger.info("powerUsed>>>>>" + JSONObject.toJSONString(powerUsed));
        // 用户端发起用车需提前(小时)
        Integer userUsecarAdvance = basicThreshold.getUserUsecarAdvance();
        if(userUsecarAdvance == null || userUsecarAdvance.intValue() < 0){
            return;
        }
        Date  created = butlerTask.getCreated();//任务创建时间
        Date completeTime = butlerTask.getPlanTime();//送车完成时间-改成计划时间跟创建时间做比较
        Date difference = DateUtils.addHours(completeTime, - userUsecarAdvance);
        if(difference.getTime() > created.getTime()){
            return;
        }
        this.logger.info("开始扣除加急费...."+ JSONObject.toJSONString(butlerTask));
        //查询加急费
        BasicCost basicCost = basicCostService.selectBasicCost();
        if(basicCost == null){
            return;
        }
        BigDecimal hurryPrice = basicCost.getHurryPrice();//加急费百分比



        BigDecimal  urgentCase = price.multiply(hurryPrice.divide(new BigDecimal(100)));//计算的加急费

        //td_power_used_cost 用户费用进行累加为负数，把总用车费用进行累加

        PowerUsedCost powerUsedCost = new PowerUsedCost();
        powerUsedCost.setPowerUsedId(butlerTask.getPowerUsedId());
        List<PowerUsedCost> powerUsedCosts = powerUsedCostService.selectByCondition(powerUsedCost);
        if(powerUsedCosts.size() == 0){
            this.logger.info("任务  butlerTask  "+ butlerTask.getId() +",根据 getPowerUsedId " + butlerTask.getPowerUsedId() + "用车记录费用明细表 为空");
            throw  new Exception("用车记录费用明细表 为空");
        }
        powerUsedCost = powerUsedCosts.get(0);
        //防止更新没有必要的数据，重新new 一个实体
        PowerUsedCost powerUsedCostNew = new PowerUsedCost();
        powerUsedCostNew.setTotal(powerUsedCost.getTotal().subtract(urgentCase).setScale(2, BigDecimal.ROUND_HALF_UP));
        powerUsedCostNew.setHurryPrice(powerUsedCost.getHurryPrice().subtract(urgentCase).setScale(2, BigDecimal.ROUND_HALF_UP));
        powerUsedCostNew.setId(powerUsedCost.getId());
        powerUsedCostService.updateByPrimaryKeySelective(powerUsedCostNew);

        //插入一条权益消费的记录td_member_account_log
        MemberAccountLog memberAccountLog = new MemberAccountLog();
        MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
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
        memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
        memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
        memberAccountLog.setAccountType(1);
        memberAccountLog.setTransformType(6);
        memberAccountLog.setDeposit(new BigDecimal(0));
        memberAccountLog.setBalance(urgentCase.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
        memberAccountLog.setCreated(new Date());
        memberAccountLog.setTotal(memberAccountLog.getBalance().add(memberAccountLog.getDeposit()));
        memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
        MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
        memberAccountLog.setMemberItemName(memberItem.getItemName());
        memberAccountLog.setPreBalance(memberItem.getBalance());
        memberAccountLog.setPreDeposit(memberItem.getDeposit());
        memberAccountLog.setRemark("用车加急费用");
		memberAccountLog.setBalanceReason("用车加急费用");
        memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
        memberAccountLog.setModifier(butlerTask.getOperaterName());
        memberAccountLog.setModifierId(butlerTask.getOperaterId());
        this.memberAccountLogService.insertSelective(memberAccountLog);


        //新增td_power_used_cost_log 日志表
        /*PowerUsedCostLog powerUsedCostLog = new PowerUsedCostLog();
        powerUsedCostLog.setMemberId(Long.valueOf(butlerTask.getMemberId()));
        powerUsedCostLog.setCaroperateId(butlerTask.getCarOperateId());
        powerUsedCostLog.setDeductionPrice(urgentCase.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
        powerUsedCostLog.setMemberName(butlerTask.getMemberName());
        powerUsedCostLog.setPowerUsedCostId(powerUsedCost.getId());
        powerUsedCostLog.setDeductionTime(new Date());
        powerUsedCostLogService.insertSelective(powerUsedCostLog);*/

        //扣减权益yu余额
        MemberItem memberItem_new = new MemberItem();
        memberItem_new.setId(memberItem.getId());
        memberItem_new.setBalance(memberItem.getBalance().subtract(urgentCase).setScale(2, BigDecimal.ROUND_HALF_UP));
        this.memberItemService.updateByPrimaryKeySelective(memberItem_new);


    }

	public void updateCarOperatePlan(ButlerTask butlerTask,BigDecimal price) throws Exception {
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setPowerUsedId(butlerTask.getPowerUsedId());
		carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
		List<CarOperatePlan> carOperatePlens = carOperatePlanService.selectByCondition(carOperatePlan);
		if(CollectionUtils.isEmpty(carOperatePlens)){
			this.logger.info("根据"+carOperatePlan.getOperateId()+"和"+carOperatePlan.getStarted()+" 查询 carOperatePlens 为空");
			return;
		}
		carOperatePlan = carOperatePlens.get(0);

		CarOperatePlan update = new CarOperatePlan();
		update.setId(carOperatePlan.getId());
		update.setStatus(CarOperatePlanEnum.status.USEING.getValue());
		update.setMoney(price);
		String remark = "管家送车完成,修改车辆状态为会员使用中";
		if(carOperatePlan.getMemberId() != 0){
			update.setOperateStatus(CarOperateEnum.status.MEMBERUSEING.getValue());
			remark = "管家送车完成,修改车辆状态为会员使用中";
		}else{
			update.setOperateStatus(CarOperateEnum.status.PLATFORMUSEING.getValue());
			remark = "管家送车完成,修改车辆状态为平台使用中";
		}
		carOperatePlanService.updateByPrimaryKeySelective(update);
		carOperatePlan.setMoney(price);
		//新增车辆日志
		insertCarLog(1,carOperatePlan,update.getOperateStatus(),remark);
	}

	public BigDecimal findCarTypeDailyRent(ButlerTask butlerTask){
        // 根据'会员权益ID'，'当前使用车辆ID' 查询 车型的日租金
        PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
        Long memberItemID = powerUsed.getMemberItemId();
        MemberItem memberItem = new MemberItem();
        memberItem.setId(memberItemID);
        memberItem = memberItemService.findById(memberItem);


        ItemCartype itemCartype = new ItemCartype();
        itemCartype.setItemId(memberItem.getItemId());
        itemCartype.setCartypeId(powerUsed.getCarTypeId());
        List<ItemCartype> itemCartypes = itemCartypeService.selectByCondition(itemCartype);
        BigDecimal price = null;
        if(itemCartypes.size() == 0){
            this.logger.info("根据 memberItem.getItemId() "+ memberItem.getItemId() +",powerUsed.getCarTypeId()"+powerUsed.getCarTypeId()+"查询车型日租金 ItemCartype 不存在 ");
        }else{
            itemCartype  = itemCartypes.get(0);
            price = itemCartype.getDayPrice();
        }
        return price;
    }

	/**
	 * 送车完成时 权益余额扣减第一次费用(如果有优惠卷，则需要用优惠券抵扣)，同时插入用车费用日志表
	 */
	@Override
	public void deductionOfFare(ButlerTask butlerTask,BigDecimal price) throws Exception {

		//查找当前用车的优惠卷
		PowerUsedCost powerUsedCost = new PowerUsedCost();
		powerUsedCost.setPowerUsedId(butlerTask.getPowerUsedId());
		List<PowerUsedCost> powerUsedCosts = powerUsedCostService.selectByCondition(powerUsedCost);
		if(powerUsedCosts.size() == 0){
			this.logger.info("任务  butlerTask  "+butlerTask.getId() +",根据 getPowerUsedId " + butlerTask.getPowerUsedId() + "用车记录费用明细表 为空");
			throw  new Exception("用车记录费用明细表 为空");
		}
		powerUsedCost = powerUsedCosts.get(0);
		BigDecimal couponPrice = powerUsedCosts.get(0).getCouponPrice();
		//如果有优惠卷第一次扣费的时候则抵扣用车费用
		BigDecimal withFare = price.subtract(couponPrice).setScale(2, BigDecimal.ROUND_HALF_UP);//用车费
		if(-1 == withFare.compareTo(new BigDecimal(0))){
			//表示优惠券金额大于日使用用车费
			withFare = new BigDecimal(0);
		}
		//td_power_used_cost 用户费用进行累加，吧总用车费用进行累加
		//防止更新没有必要的数据，重新new 一个实体
		PowerUsedCost powerUsedCostNew = new PowerUsedCost();
		powerUsedCostNew.setTotal(powerUsedCost.getTotal().subtract(withFare).setScale(2, BigDecimal.ROUND_HALF_UP));
		powerUsedCostNew.setUsedcarPrice(powerUsedCost.getUsedcarPrice().subtract(withFare).setScale(2, BigDecimal.ROUND_HALF_UP));
		powerUsedCostNew.setId(powerUsedCost.getId());
		powerUsedCostService.updateByPrimaryKeySelective(powerUsedCostNew);

		//插入一条权益消费的记录  td_member_account_log
		PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
		MemberAccountLog memberAccountLog = new MemberAccountLog();
		MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
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
		memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
		memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
		memberAccountLog.setAccountType(1);
		memberAccountLog.setTransformType(4);
		memberAccountLog.setDeposit(new BigDecimal(0));
		memberAccountLog.setBalance(withFare.multiply(new BigDecimal(-1)));
		memberAccountLog.setCreated(new Date());
		memberAccountLog.setTotal(memberAccountLog.getBalance().add(memberAccountLog.getDeposit()).setScale(2, BigDecimal.ROUND_HALF_UP));
		memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
		MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
		memberAccountLog.setMemberItemName(memberItem.getItemName());
		memberAccountLog.setPreBalance(memberItem.getBalance());
		memberAccountLog.setPreDeposit(memberItem.getDeposit());
		memberAccountLog.setRemark("送车任务取车完成扣第一次的用车费");
		memberAccountLog.setBalanceReason("车辆使用费");
		memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
		memberAccountLog.setModifierId(butlerTask.getOperaterId());
		memberAccountLog.setModifier(butlerTask.getOperaterName());
		this.memberAccountLogService.insertSelective(memberAccountLog);


		//新增td_power_used_cost_log 日志表
		PowerUsedCostLog powerUsedCostLog = new PowerUsedCostLog();
		powerUsedCostLog.setMemberId(Long.valueOf(butlerTask.getMemberId()));
		powerUsedCostLog.setCaroperateId(butlerTask.getCarOperateId());
		powerUsedCostLog.setDeductionPrice(withFare.multiply(new BigDecimal(-1)));
		powerUsedCostLog.setMemberName(butlerTask.getMemberName());
		powerUsedCostLog.setPowerUsedCostId(powerUsedCost.getId());
		powerUsedCostLog.setDeductionTime(new Date());
		powerUsedCostLogService.insertSelective(powerUsedCostLog);

		//扣减权益yu余额
		MemberItem memberItem_new = new MemberItem();
		memberItem_new.setId(memberItem.getId());
		memberItem_new.setBalance(memberItem.getBalance().subtract(withFare).setScale(2, BigDecimal.ROUND_HALF_UP));
		this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
	}

	/**
	 * 根据用车人id 查询取车任务，并且将车辆更新到到取车任务。
	 * @param butlerTask
	 */
	public void updateCarOperateByGetCar(ButlerTask butlerTask){
		//根据用车人id 查询取车任务，并且将车辆更新到到取车任务。
		ButlerTask b = new ButlerTask();
		b.setPowerUsedId(butlerTask.getPowerUsedId());
		b.setType(2);
		List<ButlerTask> butlerTaskList = this.getButlerTask(b);
		if(butlerTaskList.size() > 0){
			b = butlerTaskList.get(0);
			ButlerTask  update = new ButlerTask();
			update.setId(b.getId());
			update.setCarOperateId(butlerTask.getCarOperateId());
			this.updateButlerTask(update);
		}
	}

	/**
	 * 将车辆id绑定到用车记录
	 * @param butlerTask
	 */
	public void updateCarOperateByPowerUsed(ButlerTask butlerTask){
		//将车辆id绑定到用车记录
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setId(butlerTask.getPowerUsedId());
		powerUsed.setCaroperateId(butlerTask.getCarOperateId());
		powerUsedService.updateByPrimaryKeySelective(powerUsed);

	}

	@Override
	public ButlerTask updateSendCarButlerTaskByCancel(ButlerTask butlerTask) throws Exception {
		Assert.notNull(butlerTask,"修改-送车任务-已取消-对象不能为空");
		//判断是谁操作的
		Integer modifierType = butlerTask.getModifierType();
		
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//取消任务-只是修改备注以及状态，其他不做修改
		ButlerTask butlerTask_update = new ButlerTask();
		butlerTask_update.setId(butlerTask.getId());
		butlerTask_update.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex());
		butlerTask_update.setRemark(butlerTask.getRemark());
		//设置完成时间
		butlerTask_update.setCompleteTime(new Date());
		butlerTask = this.updateButlerTask(butlerTask_update);
		butlerTask_old.setModifierType(modifierType);
        this.createButlerTaskStatusLog(butlerTask_old);
		//取消取车任务
		this.getCarButlerTaskByCancel(butlerTask);


		//将用车记录状态变为已取消
        this.updatePowerUsedStatus(butlerTask, 7);
        //送车取消将权益车位变成待使用
      	//this.updateMemberItemCartypeStatus(butlerTask, 1);
      	//车辆运营状态变为 已取消
        this.updateCarOperatePlanCancel(butlerTask);
        //超过预约用车两个小时后取消用车，扣除50%日使用费
        this.deductionBreachOfContract(butlerTask);

        //给管家发送微信消息推送
        String value = "您有一个送车任务已被取消";
        this.wechatMessageService.sendMessageByTaskCancel(butlerTask, value, modifierType);
	    
        return butlerTask;
	}

	public void getCarButlerTaskByCancel(ButlerTask butlerTask) {
		//取消取车任务
		ButlerTask butlerTaskGet = new ButlerTask();
		butlerTaskGet.setType(2);
		butlerTaskGet.setPowerUsedId(butlerTask.getPowerUsedId());
		List<ButlerTask> butlerTaskGets = this.selectByCondition(butlerTaskGet);
		if(CollectionUtils.isEmpty(butlerTaskGets)){
			this.logger.info(JSONObject.toJSONString(butlerTaskGet) + "查找取车任务为空");
			return;
		}
		butlerTaskGet = butlerTaskGets.get(0);
		ButlerTask butlerTaskUpdate = new ButlerTask();
		butlerTaskUpdate.setId(butlerTaskGet.getId());
		this.updateGetCarButlerTaskByCancel(butlerTaskUpdate);
	}

	/**
     * 扣除违约费
     * @param butlerTask
     */
    public void deductionBreachOfContract(ButlerTask butlerTask) throws Exception {
        //超过预约用车两个小时后取消用车，扣除50%日使用费
		//超过预约用车1个小时后取消用车，扣除50%日使用费 2016-12-09 新需求
        this.logger.info("扣除违约费...."+ JSONObject.toJSONString(butlerTask));

        PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
        this.logger.info("powerUsed>>>>>" + JSONObject.toJSONString(powerUsed));

        Date  carUsedTime = powerUsed.getCarUsedTime();//用车时间
        Date nowDate = new Date();//当前时间
        Date difference = DateUtils.addHours(nowDate, -1);
        if(difference.getTime() < carUsedTime.getTime()){
            return;
        }
        this.logger.info("开始扣除违约费...."+ JSONObject.toJSONString(butlerTask));
        //查询延时还车费用
        BasicCost basicCost = basicCostService.selectBasicCost();
        if(basicCost == null){
            return;
        }
        BigDecimal delaycancelPrice = basicCost.getDelaycancelPrice();//延时还车百分比
        //查询 车型的日租金
        BigDecimal price = this.findCarTypeDailyRent(butlerTask);
        BigDecimal  urgentCase = price.multiply(delaycancelPrice.divide(new BigDecimal(100)));//计算的取消用车费用


        PowerUsedCost powerUsedCost = new PowerUsedCost();
        powerUsedCost.setPowerUsedId(butlerTask.getPowerUsedId());
        List<PowerUsedCost> powerUsedCosts = powerUsedCostService.selectByCondition(powerUsedCost);
        if(powerUsedCosts.size() == 0){
            this.logger.info("任务  butlerTask  "+butlerTask.getId() +",根据 getPowerUsedId " + butlerTask.getPowerUsedId() + "用车记录费用明细表 为空");
            throw  new Exception("用车记录费用明细表 为空");
        }
        powerUsedCost = powerUsedCosts.get(0);
        //防止更新没有必要的数据，重新new 一个实体
        PowerUsedCost powerUsedCostNew = new PowerUsedCost();
        powerUsedCostNew.setTotal(powerUsedCost.getTotal().subtract(urgentCase).setScale(2, BigDecimal.ROUND_HALF_UP));
        powerUsedCostNew.setContractPrice(powerUsedCost.getContractPrice().subtract(urgentCase).setScale(2, BigDecimal.ROUND_HALF_UP));
        powerUsedCostNew.setId(powerUsedCost.getId());
        powerUsedCostService.updateByPrimaryKeySelective(powerUsedCostNew);

        //插入一条权益消费的记录td_member_account_log
        MemberAccountLog memberAccountLog = new MemberAccountLog();
        MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
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
        memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
        memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
        memberAccountLog.setAccountType(1);
        memberAccountLog.setTransformType(8);
        memberAccountLog.setDeposit(new BigDecimal(0));
        memberAccountLog.setBalance(urgentCase.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
        memberAccountLog.setCreated(new Date());
        memberAccountLog.setTotal(memberAccountLog.getBalance().add(memberAccountLog.getDeposit()));
        memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
        MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
        memberAccountLog.setMemberItemName(memberItem.getItemName());
        memberAccountLog.setPreBalance(memberItem.getBalance());
        memberAccountLog.setPreDeposit(memberItem.getDeposit());
        memberAccountLog.setModifierId(butlerTask.getOperaterId());
		memberAccountLog.setModifier(butlerTask.getOperaterName());
        memberAccountLog.setRemark("取消用车违约费");
		memberAccountLog.setBalanceReason("违约费用");
		memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
        this.memberAccountLogService.insertSelective(memberAccountLog);


        //新增td_power_used_cost_log 日志表
        /*PowerUsedCostLog powerUsedCostLog = new PowerUsedCostLog();
        powerUsedCostLog.setMemberId(Long.valueOf(butlerTask.getMemberId()));
        powerUsedCostLog.setCaroperateId(butlerTask.getCarOperateId());
        powerUsedCostLog.setDeductionPrice(urgentCase.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
        powerUsedCostLog.setMemberName(butlerTask.getMemberName());
        powerUsedCostLog.setPowerUsedCostId(powerUsedCost.getId());
        powerUsedCostLog.setDeductionTime(new Date());
        powerUsedCostLogService.insertSelective(powerUsedCostLog);*/

        //扣减权益yu余额
        MemberItem memberItem_new = new MemberItem();
        memberItem_new.setId(memberItem.getId());
        memberItem_new.setBalance(memberItem.getBalance().subtract(urgentCase).setScale(2, BigDecimal.ROUND_HALF_UP));
        this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
        
        //给用户发送消息推送--出发取车微信
	    String value ="余额扣费"+urgentCase.setScale(2, BigDecimal.ROUND_HALF_UP)+"元（延时取消用车）";
	    this.sendMessageByTaskStatusChangeApp(butlerTask,value);
        //给用户发送扣除违约费的消息通知
        this.wechatMessageService.sendMessageByBreakPromise(butlerTask, " 您好，系统已扣除您的延时取消用车费用", urgentCase.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
        

    }

    /**
     * 将车辆运营状态变成已取消
     * @param butlerTask
     */
    public void updateCarOperatePlanCancel(ButlerTask butlerTask) throws Exception {

        CarOperatePlan update = new CarOperatePlan();
        update.setPowerUsedId(butlerTask.getPowerUsedId());
        update.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
        carOperatePlanService.updateCarOperatePlanByPowerPowerUsedId(update);

    }

    @Override
	public ButlerTask createGetCarButlerTask(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "取车任务数据不能为空！");
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_TREATMENT.getIndex());
		//设置负责人
		if(null == butlerTask.getOperaterId()){
			butlerTask = this.setOperater(butlerTask, 2);
		}
		//设置客服负责人
		this.setCustomer(butlerTask);
		//创建取车任务
		butlerTask = this.createButlerTask(butlerTask);
		
		//操作类型设置为会员
		butlerTask.setModifierType(0);
		//生成一条取车任务
		this.createButlerTaskStatusLog(butlerTask);
		
		//给管家发送微信消息推送
		String value = "您有新的取车任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreateToCustomer(butlerTask, value);
		return butlerTask;
	}
    
    @Override
	public ButlerTask createGetCarButlerTasked(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "取车任务数据不能为空！");
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_TREATMENT.getIndex());
		//设置负责人
		if(null == butlerTask.getOperaterId()){
			butlerTask = this.setOperater(butlerTask, 2);
		}
		//设置客服负责人
		this.setCustomer(butlerTask);
		//创建取车任务
		butlerTask = this.createButlerTask(butlerTask);
		
		//操作类型设置为会员
		//butlerTask.setModifierType(0);
		//生成一条取车任务
		this.createButlerTaskStatusLog(butlerTask);
		
		//给管家发送微信消息推送
		String value = "您有新的取车任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreateToCustomer(butlerTask, value);
		return butlerTask;
	}
    

	public ButlerTask setOperater(ButlerTask butlerTask, Integer type){
		//给后台基础设置-通知管理设置的通知人发送微信以及app通知
		BasicNotice b = new BasicNotice();
		//查询正常的状态
		b.setStatus(0);
		//查询正常的状态
		b.setNoticeType(type);
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
		long memberId;
		Member member_notice = new Member();
		if(CollectionUtils.isNotEmpty(basicNotices)){
			b = basicNotices.get(0);
			memberId = b.getNoticeUserId();
			try {
				member_notice = this.memberService.findMemberByID(memberId);
			} catch (Exception e) {
				this.logger.error("查询用户异常");
				e.printStackTrace();
			}
		}
		butlerTask.setOperaterName(member_notice.getSysuserName());
		butlerTask.setOperaterId(member_notice.getSysuserId());
		return butlerTask;
	}

	@Override
	public ButlerTask updateGetCarButlerTaskByWaitGet(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-取车任务-取车中-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		if(ButlerTaskStatus.BUTLER_TASK_STATUS_TAKE_THE_CAR.getIndex()==butlerTask_old.getStatus()){
			//表示中止取车
			CarOperatePlan carOperatePlan =new CarOperatePlan();
			//查询车辆车牌号
			CarOperate car = carOperateService.findById(butlerTask_old.getCarOperateId());
			if(car != null){
				carOperatePlan.setCarPlateNum(car.getCarPlateNum());
				//carOperatePlan.setOperateId();
				carOperatePlan.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
				carOperatePlan.setStarted(butlerTask.getPlanTime());
				this.carOperatePlanService.updateStatusByOperateIdAndEnded(carOperatePlan);
			}
		}
		
		//设置状态为带取车-2
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_GET_FOR_A_CAR.getIndex());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		return butlerTask;
	}
	
	@Override
	public ButlerTask updateGetCarButlerTaskByGeting(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-取车任务-取车中-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//设置状态为取车中-3
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_TAKE_THE_CAR.getIndex());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//将用车记录状态出发取车
		this.updatePowerUsedStatus(butlerTask, 4);
		
		//给用户发送消息推送--出发取车微信
		String value ="极车管家已出发取车，请您保持电话畅通。";
		this.sendMessageByTaskStatusChangeApp(butlerTask,value);
		//给用户发送app推送消息
		String wechatMessage = "极车管家已出发，请您保持电话畅通";
		this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
		
		return butlerTask;
		
	}

	@Override
	public void  sendMessageByTaskStatusChangeApp(ButlerTask butlerTask,String value){
		
		MessageUmeng messageUmeng = new MessageUmeng();
		//0代表极车公社
		messageUmeng.setAlias(butlerTask.getMemberId()+"");
		messageUmeng.setTitle("海易出行");
		messageUmeng.setTicker("系统");
		messageUmeng.setMessageContent(value);
		messageUmeng.setCreateUser(butlerTask.getOperaterName());
		messageUmeng.setBeginTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		try {
			messageAppService.sendMessageByAndroidAndIosAsync(messageUmeng);
		} catch (Exception e) {
			logger.error("给用户app端消息推送失败----用户ID："+butlerTask.getMemberId()+" 推送内容为:'"+value+"'");
		}
	}
	@Override
	public ButlerTask updateGetCarButlerTaskByArrived(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-取车任务-已到达-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
				
		//设置状态为已到达-4
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//将用车记录状态管家到达
		this.updatePowerUsedStatus(butlerTask, 5);
		
		//给用户发送消息推送--出发取车微信
		String value ="极车管家已到达取车地点，请您及时送车。";
		this.sendMessageByTaskStatusChangeApp(butlerTask,value);
		//给用户发送app推送消息
		String wechatMessage = "极车管家已到达取车地点，请您及时送车";
		this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
		
		return butlerTask;
	}

	@Override
	public ButlerTask updateGetCarButlerTaskByLose(ButlerTask butlerTask) throws Exception {
		Assert.notNull(butlerTask, "修改-取车任务-已失联-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		//设置状态为已失联-6
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_LOST_CONTACT.getIndex());
		//设置完成时间
		butlerTask.setCompleteTime(new Date());
		
		Member operateMember = this.memberService.findMemberByID(Long.parseLong(butlerTask.getLoginId()));
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//首先将该车辆的状态变为已失联，同时取消该车辆后续所有预约
		this.updateCarStatusByLose(butlerTask);
		
		//将后续关联了该车辆的任务的车辆ID设置为null
		this.updateButlerTaskCarOperate(butlerTask,2);
		
		//将用车记录状态改为取车完成
		this.updatePowerUsedStatus(butlerTask, 6);
		//需要冻结会员
		Member member = new Member();
		member.setId(Long.parseLong(butlerTask.getMemberId()));
		member.setStatuts(0);
		this.memberService.updateMember(member);
		//插入一条冻结会员的日志
		MemberStatusLog memberStatusLog = new MemberStatusLog();
		memberStatusLog.setCreated(new Date());
		memberStatusLog.setMemberId(Long.parseLong(butlerTask.getMemberId()));
		memberStatusLog.setStatus(0);
		if(null!=operateMember){
			memberStatusLog.setModifierId(operateMember.getSysuserId());
			memberStatusLog.setModifier(operateMember.getSysuserName());
		}
		memberStatusLog.setRemark("由于车辆失联冻结用户");
		this.memberStatusLogService.insertSelective(memberStatusLog);
		
		//失联给管家发送微信推送消息
		this.checkSendMessageByLose(butlerTask);
		
		return butlerTask;
	}
	
	private void checkSendMessageByLose(ButlerTask butlerTask){

		PowerUsed powerUsed = this.powerUsedService.findById(butlerTask.getPowerUsedId());
		if(null==powerUsed){
			return;
		}
		Long carTypeId = powerUsed.getCarTypeId();
		//查看失联之后的一个月时间内有没有影响库存，相当于再次发起一次用车（开始时间是延时之前的时间，结束时间为修改的最新时间）
		Date currentDate = new Date();
		boolean storeFlag = this.carOperatePlanService.isStoreForDelay(powerUsed.getCarTypeId(), currentDate, DateUtils.addMonths(currentDate, 1));
		if(!storeFlag){
			//然后查询这段时间这个车型的所有（待接单，带送车）送车任务，给管家发消息推送
			if(null!=butlerTask.getCarOperateId()){
				CarOperate car = this.carOperateService.findById(butlerTask.getCarOperateId());
				carTypeId = car.getCarTypeId();
			}
			ButlerTask getCarButlerTask = new ButlerTask();
			getCarButlerTask.setCarTypeId(carTypeId);
			getCarButlerTask.setQueryBegin(currentDate);
			getCarButlerTask.setQueryEnd(DateUtils.addMonths(currentDate, 1));
			List<ButlerTask> butlertasklist = this.selectByCartypeIdAndStartEnd(getCarButlerTask);
			this.logger.info("查询时间区间为---"+DateUtils.formatDate(currentDate, "yyyy-MM-dd HH:mm")+"之后一个月的待取车已经带送车的送车任务");
			if(CollectionUtils.isNotEmpty(butlertasklist)){
				this.logger.info("查询出来的满足条件的任务数量为----"+butlertasklist.size());
				Member butlerMember = new Member();
				for(ButlerTask butlerT : butlertasklist){
					butlerMember.setSysuserId(butlerT.getOperaterId());
					butlerMember = this.memberService.selectBySysUserId(butlerMember);
					//循环发送消息推送
					this.wechatMessageService.sendMessageByLoseOrTrouble(butlerTask.getCarOperateId(), 1 , butlerMember, butlerT);
				}
			}
		}
		
	}

	private void updateCarStatusByLose(ButlerTask butlerTask) throws Exception{
		if(null!=butlerTask.getCarOperateId()){
			//首先将车辆的状态变为已失联
			CarOperate carOperate = new CarOperate();
			carOperate.setId(butlerTask.getCarOperateId());
			carOperate.setStatus(CarOperateEnum.status.OUTOFCONTACT.getValue());
			this.carOperateService.updateByPrimaryKeySelective(carOperate);
			
			//修改车辆运营状态为已完成
			CarOperatePlan carOperatePlan = new CarOperatePlan();
			carOperatePlan.setPowerUsedId(butlerTask.getPowerUsedId());
			carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
			List<CarOperatePlan> carOperatePlens = carOperatePlanService.selectByCondition(carOperatePlan);
			if(CollectionUtils.isEmpty(carOperatePlens)){
				this.logger.info("根据"+carOperatePlan.getOperateId()+"和"+carOperatePlan.getStarted()+" 查询 carOperatePlens 为空");
				return;
			}
			carOperatePlan = carOperatePlens.get(0);
			CarOperatePlan update = new CarOperatePlan();
			update.setId(carOperatePlan.getId());
			update.setEnded(butlerTask.getCompleteTime());
			update.setStatus(CarOperatePlanEnum.status.SUCCESS.getValue());
			carOperatePlanService.updateByPrimaryKeySelective(update);
			//新增车辆日志
			insertCarLog(0,carOperatePlan,CarOperateEnum.status.OUTOFCONTACT.getValue(),"车辆失联");
			
			//取消该车辆的后续预约计划
			CarOperatePlan carOperateP = new CarOperatePlan();
			//查询车辆车牌号
			CarOperate car = carOperateService.findById(butlerTask.getCarOperateId());
			
			if(car != null){
				Date currentDay = new Date();
				carOperateP.setCarPlateNum(car.getCarPlateNum());
				carOperateP.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
				carOperateP.setStarted(currentDay);
				//会员用车清空车辆
				this.carOperatePlanService.updateStatusByOperateIdAndStarted(carOperateP);
				//平台运营直接变成取消
				this.carOperatePlanService.updateStatusByOperateIdAndStartedForPlatform(carOperateP);
				
				CarOperatePlan plan = new CarOperatePlan();
				plan.setCarPlateNum(car.getCarPlateNum());
				plan.setPowerUsedId(null);
				currentDay.setSeconds(currentDay.getSeconds() - 1);
				plan.setStarted(currentDay);
				List<CarOperatePlan> planList = carOperatePlanService.selectCarPlanNumAndpowerUsedId(plan);
				//查询操作人信息
				Member member = new Member();
				member.setSysuserId(butlerTask.getOperaterId());;
				member = this.memberService.selectBySysUserId(member);
				for(CarOperatePlan plans : planList){
					String message = "您安排的平台运营车辆由于失联已被取消，请尽快处理";
					wechatMessageService.sendMessageByPlatformOperation(plans,message);
					String remark = "平台运营受到失联影响自动取消";
					this.carOperateService.cancelPlanLog(car.getOperateNum(), member, remark,8);
				}
			}
		}
	}
	
	@Override
	public void updateButlerTaskCarOperate(ButlerTask butlerTask,Integer type){
		if(null!=butlerTask.getCarOperateId()){
			ButlerTask butlerT= new ButlerTask();
			butlerT.setCarOperateId(butlerTask.getCarOperateId());
			butlerT.setPlanTime(butlerTask.getCompleteTime());
			List<ButlerTask> butlerList = this.selectButlerTaskListByCarOperateId(butlerT);
			Member operateMember = new Member();
			//查询车辆信息
			CarOperate car = this.carOperateService.findById(butlerTask.getCarOperateId());
			for(ButlerTask bt : butlerList){
				Long id = butlerTask.getId();
				Long buId = bt.getId();
				if(id != null && buId != null){
					if(!id.equals(buId)){
						//给需要清空的任务发送消息推送
						this.wechatMessageService.sendMessageByEmptyCarOperate(butlerTask.getCarOperateId(), type, bt);
						//插入清空车辆任务的日志
						operateMember.setSysuserId(butlerTask.getOperaterId());
						operateMember = this.memberService.selectBySysUserId(operateMember);
						this.createButlertaskLogByemptyCar(bt, operateMember, 1, type, car);
						//循环修改将车协任务的车辆ID设置为null
						this.updateButlerTaskCarOperateNull(bt);
					}
				}else{
					//给需要清空的任务发送消息推送
					this.wechatMessageService.sendMessageByEmptyCarOperate(butlerTask.getCarOperateId(), type, bt);
					//插入清空车辆任务的日志
					operateMember.setSysuserId(butlerTask.getOperaterId());
					operateMember = this.memberService.selectBySysUserId(operateMember);
					this.createButlertaskLogByemptyCar(bt, operateMember, 1, type, car);
					//循环修改将车协任务的车辆ID设置为null
					this.updateButlerTaskCarOperateNull(bt);
				}
			}
		}
	}
	
	@Override
	public ButlerTask updateGetCarButlerTaskByComplete(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-取车任务-已完成-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		if(null!=butlerTask.getGasoline()){
			this.logger.info("取车完成需要扣除燃油费---");
			butlerTask_old.setGasoline(butlerTask.getGasoline());
			this.updatePowerUsedAndAccoutByGasoline(butlerTask_old);
		}
		
		//设置状态为已完成-7
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
		//设置完成时间
		butlerTask.setCompleteTime(new Date());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//将用车记录状态改为取车完成
		this.updatePowerUsedStatus(butlerTask, 6);
		//取车完成将权益车位变成待使用
      	//this.updateMemberItemCartypeStatus(butlerTask, 1);
      	//修改车辆状态 为整备中，运营状态为已完成
      	updateCarStatus(butlerTask);	
        //给用户发送消息推送--出发取车微信
        String value ="极车管家已取走您的爱车，期待再次为您服务。";
	    this.sendMessageByTaskStatusChangeApp(butlerTask,value);
	    //给用户发送app推送消息
	    String wechatMessage = "极车管家已取走您的爱车，期待再次为您服务";
	    this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
				
		return butlerTask;
	}
	
	private void updateCarStatus(ButlerTask butlerTask){
		//修改车辆运营状态为 已完成
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setPowerUsedId(butlerTask.getPowerUsedId());
		carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
		List<CarOperatePlan> carOperatePlens = carOperatePlanService.selectByCondition(carOperatePlan);
		if(CollectionUtils.isEmpty(carOperatePlens)){
			this.logger.info("根据"+carOperatePlan.getOperateId()+"和"+carOperatePlan.getStarted()+" 查询 carOperatePlens 为空");
			return;
		}
		carOperatePlan = carOperatePlens.get(0);
		if(carOperatePlan != null){
			
			//根据车辆 车牌号查询车辆信息
			CarOperate carOperate = new CarOperate();
			carOperate.setCarPlateNum(carOperatePlan.getCarPlateNum());
			List<CarOperate> carOperateList = carOperateService.selectByCondition(carOperate);
			long id = 0L;
			if(carOperateList != null){
				CarOperate car = carOperateList.get(0);
				if(car != null){
					id = car.getId();
				}
			}
			//修改车辆状态为整备中
			CarOperate car = new CarOperate();
			car.setId(id);
			car.setStatus(CarOperateEnum.status.READINESSING.getValue());
			car.setReadyTime(new Date());
			carOperateService.updateByPrimaryKeySelective(car);
			//修改车辆运营状态为已完成
			CarOperatePlan update = new CarOperatePlan();
			update.setId(carOperatePlan.getId());
			update.setStatus(CarOperatePlanEnum.status.SUCCESS.getValue());
			carOperatePlanService.updateByPrimaryKeySelective(update);
			//新增车辆日志
			insertCarLog(0,carOperatePlan,CarOperateEnum.status.READINESSING.getValue(),"会员使用完毕,修改车辆状态为整备中");
		}
	}
	
	private void insertCarLog(int money,CarOperatePlan carOperatePlan,int status,String remark){
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		//运营记录
		carOperateLog.setMemberId(carOperatePlan.getMemberId());
		carOperateLog.setMemberName(carOperatePlan.getMemberName());
		carOperateLog.setOperateId(carOperatePlan.getButlerId());
		carOperateLog.setOperateName(carOperatePlan.getButlerName());
		carOperateLog.setOperateTypeId(carOperatePlan.getOperateType().toString());
		carOperateLog.setOperateTypeName(carOperatePlan.getOperateType().toString());
		if(carOperatePlan.getMoney() != null && money != 0){
			carOperateLog.setTakein(carOperatePlan.getMoney());
		}
		carOperateLog.setRemark(remark);
		carOperateLog.setType(status);
		carOperateLog.setOperateNum(carOperatePlan.getOperateNum());
		carOperateLog.setCreated(new Date());
		try {
			carOperateLogService.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--carOperatePlanId"+carOperatePlan.getId());
		}
	}
	
	/**
	 * 取车完成之后需要修改用车记录的油耗费用的信息，同时需要往费用日志里面插入一条记录
	 */
	public void updatePowerUsedAndAccoutByGasoline(ButlerTask butlerTask){

		//存在油耗,首先找到用车记录费用表
		PowerUsedCost powerUsedCost = this.powerUsedCostService.selectByPowerUsedId(butlerTask.getPowerUsedId());
		//查询用车记录主表-查询出用车记录
		PowerUsed powerUsed = this.powerUsedService.findById(butlerTask.getPowerUsedId());
		if(null!=powerUsedCost && null !=powerUsed){
			//查询权益
			MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
			
			BigDecimal gasolinePrice = new BigDecimal(0);
			BigDecimal total = new BigDecimal(0);
			total = powerUsedCost.getTotal();
			//查询燃油价格(单价)
			BasicCost BasicCost = new BasicCost();
			List<BasicCost> BasicCostList = this.basicCostService.selectByCondition(BasicCost);
			if(BasicCostList.size()>0){
				gasolinePrice = BasicCostList.get(0).getGasolinePrice();
			}
			powerUsedCost.setGasoline(butlerTask.getGasoline().setScale(2, BigDecimal.ROUND_HALF_UP));
			powerUsedCost.setGasolinePrice(((gasolinePrice.multiply(butlerTask.getGasoline())).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(-1)));
			powerUsedCost.setTotal((total.add(powerUsedCost.getGasolinePrice())).setScale(2, BigDecimal.ROUND_HALF_UP));
			//修改用车记录扣费信息
			this.powerUsedCostService.updateByPrimaryKeySelective(powerUsedCost);
			//插入一条权益消费的记录td_member_account_log
			MemberAccountLog memberAccountLog = new MemberAccountLog();
			MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
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
			memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
			memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
			memberAccountLog.setMemberItemName(memberItem.getItemName());
			memberAccountLog.setAccountType(2);
			memberAccountLog.setTransformType(7);
			memberAccountLog.setDeposit(((gasolinePrice.multiply(butlerTask.getGasoline())).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(-1)));
			memberAccountLog.setBalance(new BigDecimal(0));
			memberAccountLog.setCreated(new Date());
			memberAccountLog.setTotal(((gasolinePrice.multiply(butlerTask.getGasoline())).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(-1)));
			memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
			memberAccountLog.setPreBalance(memberItem.getBalance());
			memberAccountLog.setPreDeposit(memberItem.getDeposit());
			memberAccountLog.setModifierId(butlerTask.getOperaterId());
			memberAccountLog.setModifier(butlerTask.getOperaterName());
			memberAccountLog.setRemark("油耗费用");
			memberAccountLog.setDepositReason("油耗费用");
			memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
			try {
				this.memberAccountLogService.insertSelective(memberAccountLog);
			} catch (Exception e) {
				this.logger.error("插入会员权益消费记录失败---");
				e.printStackTrace();
			}
			//修改权益信息主表信息
			MemberItem memberItem_new = new MemberItem();
			memberItem_new.setId(memberItem.getId());
			memberItem_new.setDeposit(memberItem.getDeposit().subtract(gasolinePrice.multiply(butlerTask.getGasoline())).setScale(2, BigDecimal.ROUND_HALF_UP));
			this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
		}
	
	}

	@Override
	public ButlerTask updateGetCarButlerTaskByCancel(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-取车任务-已取消-任务对象数据不能为空！");
		//判断是谁操作的
		Integer modifierType = butlerTask.getModifierType();
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//取消任务-只是修改备注以及状态，其他不做修改
		ButlerTask butlerTask_update = new ButlerTask();
		butlerTask_update.setId(butlerTask.getId());
		butlerTask_update.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex());
		butlerTask_update.setRemark(butlerTask.getRemark());
		//设置完成时间
		butlerTask_update.setCompleteTime(new Date());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask_update);
		//插入一条任务日志
		butlerTask_old.setModifierType(modifierType);
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//将用车记录状态改为取消
		//this.updatePowerUsedStatus(butlerTask, 7);
		//给管家发送微信消息推送
        String value = "您有一个取车任务已被取消";
	    this.wechatMessageService.sendMessageByTaskCancel(butlerTask, value, modifierType);
		
		return butlerTask;
	}

	//修改用车记录状态
	public void updatePowerUsedStatus(ButlerTask butlerTask,Integer type){
		if(null!=butlerTask.getPowerUsedId()){
			PowerUsed powerUsed = new PowerUsed();
			powerUsed.setId(butlerTask.getPowerUsedId());
			if(null != type){
				powerUsed.setUsedStatus(type);
			}
			if(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()==butlerTask.getType()){
				powerUsed.setCarUsedTime(butlerTask.getPlanTime());
				powerUsed.setCarUsedAddress(butlerTask.getTaskSendCarAddress());
			}
			if(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex()==butlerTask.getType()){
				powerUsed.setCarReturnTime(butlerTask.getPlanTime());
				powerUsed.setCarReturnAddress(butlerTask.getTaskGetCarAddress());
			}
			this.powerUsedService.updateByPrimaryKeySelective(powerUsed);
		}
	}
	
	//修改用车记录状态
	public void updateMemberItemCartypeStatus(ButlerTask butlerTask,Integer type){
		if(null!=butlerTask.getPowerUsedId()){
			PowerUsed powerUsed = this.powerUsedService.findById(butlerTask.getPowerUsedId());
			MemberItemCartype memberItemCartype = new MemberItemCartype();
			memberItemCartype.setMemberItemId(powerUsed.getMemberItemId());
			memberItemCartype.setCarTypeId(powerUsed.getCarTypeId());
			if(null!=powerUsed.getChangeCarTypeId()){
				memberItemCartype.setCarTypeId(powerUsed.getChangeCarTypeId());
			}
			List<MemberItemCartype> memberItemCartypeList = this.memberItemCartypeService.selectByCondition(memberItemCartype);
			if(memberItemCartypeList.size()>0){
				memberItemCartype = memberItemCartypeList.get(0);
				MemberItemCartype memberItemCart = new MemberItemCartype();
				memberItemCart.setId(memberItemCartype.getId());
				memberItemCart.setStatus(type);
				this.memberItemCartypeService.updateMemberItemCartypeByGetCarComplete(memberItemCart);
			}
			
		}
	}
	
	@Override
	public ButlerTask createChargingButlerTask(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "充电任务数据不能为空！");
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_CHARGING.getIndex());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_GET_FOR_A_CAR.getIndex());
		//设置负责人
		butlerTask = this.setOperater(butlerTask, 4);
		//创建充电任务
		butlerTask = this.createButlerTask(butlerTask);
		
		//生成一条充电任务日志
		//this.createButlerTaskStatusLog(butlerTask);
		//操作类型设置为会员
		butlerTask.setModifierType(0);
		this.createButlerTaskStatusLog(butlerTask);
		
		//给管家发送微信消息推送
		String value = "您有新的任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreate(butlerTask, value);
		
		return butlerTask;
	}

	@Override
	public ButlerTask updateChargingButlerTaskByWay(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-充电任务-取车中-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//设置状态为取车中
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_TAKE_THE_CAR.getIndex());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//给用户发送消息推送--出发取车微信
        String value ="极车管家已出发取车，请您保持电话畅通。";
	    this.sendMessageByTaskStatusChangeApp(butlerTask,value);
	    //给用户发送app推送消息
	    String wechatMessage = "极车管家已出发，请您保持电话畅通";
	    this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
		
		return butlerTask;
	}

	@Override
	public ButlerTask updateChargingButlerTaskByCharging(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-充电任务-充电中-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//设置状态为取车中
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_CHARGE_IN.getIndex());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		return butlerTask;
	}

	/**
	 * 代充电任务，送车完成的时候，需要扣除充电费用
	 * @param butlerTask
	 */
	public void updatePowerUsedAndAccoutBYCharing(ButlerTask butlerTask){
		
		//首先找到用车记录费用表
		PowerUsedCost powerUsedCost = this.powerUsedCostService.selectByPowerUsedId(butlerTask.getPowerUsedId());
		//查询用车记录主表-查询出用车记录
		PowerUsed powerUsed = this.powerUsedService.findById(butlerTask.getPowerUsedId());
		
		if(null != powerUsed && null!= powerUsed){
			//查询权益
			MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
			BigDecimal Charingprice = new BigDecimal(0);
			//基础服务对象
			BasicService basicService = new BasicService();
			basicService = this.basicServiceService.findById(Long.parseLong("2"));
			if(null != basicService){
				Charingprice = basicService.getPrice();
			}
			//插入一条权益消费的记录td_member_account_log
			MemberAccountLog memberAccountLog = new MemberAccountLog();
			MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
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
			memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
			memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
			memberAccountLog.setMemberItemName(memberItem.getItemName());
			memberAccountLog.setAccountType(2);
			memberAccountLog.setTransformType(11);
			memberAccountLog.setDeposit(Charingprice.multiply(new BigDecimal(-1)));
			memberAccountLog.setBalance(new BigDecimal(0));
			memberAccountLog.setCreated(new Date());
			memberAccountLog.setTotal(Charingprice.multiply(new BigDecimal(-1)));
			memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
			memberAccountLog.setPreBalance(memberItem.getBalance());
			memberAccountLog.setPreDeposit(memberItem.getDeposit());
			memberAccountLog.setRemark("代充电费用");
			memberAccountLog.setDepositReason("代充电费用");
			memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
			try {
				this.memberAccountLogService.insertSelective(memberAccountLog);
			} catch (Exception e) {
				this.logger.error("插入会员权益消费记录失败---");
				e.printStackTrace();
			}
			//修改权益信息主表信息
			MemberItem memberItem_new = new MemberItem();
			memberItem_new.setId(memberItem.getId());
			memberItem_new.setDeposit(memberItem.getDeposit().subtract(Charingprice).setScale(2, BigDecimal.ROUND_HALF_UP));
			this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
		}
	}
	
	@Override
	public ButlerTask updateChargingButlerTaskBySending(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-充电任务-送车中-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//设置状态为送车中
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_SEND_CAR.getIndex());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//给用户发送消息推送--出发取车微信
        String value ="充电完毕，极车管家已返程，请您保持电话通畅。";
	    this.sendMessageByTaskStatusChangeApp(butlerTask,value);
	    //给用户发送app推送消息
	    String wechatMessage = "充电完毕，极车管家已返程，请您保持电话畅通";
	    this.wechatMessageService.sendMessageByTaskStatusChange(butlerTask,wechatMessage);
		
		return butlerTask;
	}

	@Override
	public ButlerTask updateChargingButlerTaskByCancel(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-充电任务-已取消-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		//取消任务-只是修改备注以及状态，其他不做修改
		ButlerTask butlerTask_update = new ButlerTask();
		butlerTask_update.setId(butlerTask.getId());
		butlerTask_update.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex());
		butlerTask_update.setRemark(butlerTask.getRemark());
		
		//设置完成时间
		butlerTask_update.setCompleteTime(new Date());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask_update);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		return butlerTask;
	}

	@Override
	public ButlerTask updateChargingButlerTaskByComplete(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改-充电任务-已完成-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
				
		//管家充电完成需要扣除充电费用
		this.updatePowerUsedAndAccoutBYCharing(butlerTask_old);
				
		//设置状态为已完成
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
		//设置完成时间
		butlerTask.setCompleteTime(new Date());
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		return butlerTask;
	}

	@Override
	public List<ButlerTask> getButlerTaskListForCharging(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "查询充电任务列表，任务对象不能为空");
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_CHARGING.getIndex());
		
		List<ButlerTask> butlerTaskList = this.getButlerTask(butlerTask);
		this.logger.info("查询的管家充电任务数量为"+butlerTaskList.size());
		return butlerTaskList;
	}

	@Override
	public int getEveryDaySendCarTaskTotal(List<ButlerTask> arrays, int content) {
		if(CollectionUtils.isEmpty(arrays)){
			return 0;
		}
		int start = 0;
		int end = arrays.size() -1;
		int max = 0;
		while (start <= end){
			max = (start + end)/2;
			if(arrays.get(max).getNumOfdays() > content){
				end = max - 1;
			}else if(arrays.get(max).getNumOfdays() < content){
				start = max +1;
			}else{
				return  arrays.get(max).getTotal();
			}
		}
		return 0;
	}

	/**
	 * 插入任务日志
	 * @param
	 */
	public void createButlerTaskStatusLog(ButlerTask old_butlerTask) {
		logger.info("管家任务插入日志开始---");
		//查询数据库里最新的管家任务
		ButlerTask new_butlerTask = this.getButlerTaskById(old_butlerTask.getId());
		
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		//用户
		Member member = new Member();
		
		if(null == old_butlerTask.getModifierType() || 1 == old_butlerTask.getModifierType()){
			//不设置表示为管家操作
			if(old_butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_TREATMENT.getIndex()){
				member.setSysuserId(old_butlerTask.getCustomerId());
			}else{
				member.setSysuserId(old_butlerTask.getOperaterId());
			}
			member = memberService.selectBySysUserId(member);
			butlerTaskStatusLog.setModifier(member.getSysuserName());
			butlerTaskStatusLog.setModifierId(member.getSysuserId());
			butlerTaskStatusLog.setModifierMobile(member.getMobile());
			butlerTaskStatusLog.setModifierType(1);
		}else{
			//0表示是用户创建
			try {
				member = memberService.findMemberByID(Long.parseLong(old_butlerTask.getMemberId()));
				if(StringUtils.isNotBlank(member.getSureName())){
					butlerTaskStatusLog.setModifier(member.getSureName());
				}else{
					butlerTaskStatusLog.setModifier(member.getName());
				}
				butlerTaskStatusLog.setModifierId(member.getId().toString());
				butlerTaskStatusLog.setModifierType(0);
			} catch (Exception e) {
				this.logger.error("创建日志--查询用户失败");
				e.printStackTrace();
			}
			
		}
		
		String remark = "";
		if(StringUtils.isNotBlank(new_butlerTask.getRemark())){
			if(!new_butlerTask.getRemark().equals(old_butlerTask.getRemark())){
				remark += "备注："+new_butlerTask.getRemark()+"<br/>";	
			}
		}
		//变更状态判断
		if(null != new_butlerTask.getStatus()){
			if(!new_butlerTask.getStatus().equals(old_butlerTask.getStatus())){
				remark += "备注：将状态变更为"+ButlerTaskStatus.getStatus(new_butlerTask.getStatus())+"<br/>";
			}
		}
		//管家变更
		if(null != new_butlerTask.getOperaterId()){
			if(!new_butlerTask.getOperaterId().equals(old_butlerTask.getOperaterId())){
				if(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()==new_butlerTask.getType()){
					//送车发生转派了
//					CarOperatePlan carOperatePlan = new CarOperatePlan();
//					carOperatePlan.setOperateId(old_butlerTask.getCarOperateId());
//					carOperatePlan.setStarted(old_butlerTask.getPlanTime());
//					List<CarOperatePlan> carOperatePlens = carOperatePlanService.selectByCondition(carOperatePlan);
//					if(CollectionUtils.isEmpty(carOperatePlens)){
//						this.logger.info("根据"+carOperatePlan.getOperateId()+"和"+carOperatePlan.getStarted()+" 查询 carOperatePlens 为空");
//					}else{
//						//修改车辆运营的管家id
//						carOperatePlan = carOperatePlens.get(0);
//						CarOperatePlan carOperatePlanUpdate = new CarOperatePlan();
//						carOperatePlanUpdate.setId(carOperatePlan.getId());
//						carOperatePlanUpdate.setButlerId(new_butlerTask.getOperaterId());
//						carOperatePlanService.updateByPrimaryKeySelective(carOperatePlanUpdate);
//					}
					CarOperatePlan carOperatePlanUpdate = new CarOperatePlan();
					carOperatePlanUpdate.setPowerUsedId(old_butlerTask.getPowerUsedId());
					carOperatePlanUpdate.setButlerId(new_butlerTask.getOperaterId());
					carOperatePlanService.updateCarOperatePlanByPowerPowerUsedId(carOperatePlanUpdate);
				}
				remark+="备注："+old_butlerTask.getOperaterName()+"将任务指派给"+new_butlerTask.getOperaterName()+"<br/>";
				//发生转派时需要给管家发送转派
				this.wechatMessageService.sendMessageByTaskOperateChange(new_butlerTask);
			}
		}
		//客服变更
		if(null != new_butlerTask.getCustomerId()){
			if(!new_butlerTask.getCustomerId().equals(old_butlerTask.getCustomerId())){
				if(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()==new_butlerTask.getType()){
					remark+="备注："+old_butlerTask.getCustomerName()+"将任务指派给"+new_butlerTask.getCustomerName()+"<br/>";
					String value = "您有新的转派任务，请及时处理";
					this.wechatMessageService.sendMessageByTaskCreateToCustomer(new_butlerTask,value);

				}
			}
		}
		//变更送车时间判断
		if(null != new_butlerTask.getPlanTime()){
			if(null!=new_butlerTask.getPlanTime()&&null!=old_butlerTask.getPlanTime()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				if(new_butlerTask.getPlanTime().getTime() != old_butlerTask.getPlanTime().getTime()){
					PowerUsed powerUsed =new PowerUsed();
					powerUsed.setId(new_butlerTask.getPowerUsedId());
					if(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()==new_butlerTask.getType()){
						powerUsed.setCarUsedTime(new_butlerTask.getPlanTime());
						this.powerUsedService.updateByPrimaryKeySelective(powerUsed);
						
						//修改车辆运营开始时间
//						CarOperatePlan carOperatePlan = new CarOperatePlan();
//						carOperatePlan.setOperateId(old_butlerTask.getCarOperateId());
//						carOperatePlan.setStarted(old_butlerTask.getPlanTime());
//						List<CarOperatePlan> carOperatePlens = carOperatePlanService.selectByCondition(carOperatePlan);
//						if(CollectionUtils.isEmpty(carOperatePlens)){
//							this.logger.info("根据"+carOperatePlan.getOperateId()+"和"+carOperatePlan.getStarted()+" 查询 carOperatePlens 为空");
//						}else{
//							//修改车辆运营开始时间
//							carOperatePlan = carOperatePlens.get(0);
//							CarOperatePlan carOperatePlanUpdate = new CarOperatePlan();
//							carOperatePlanUpdate.setId(carOperatePlan.getId());
//							carOperatePlanUpdate.setStarted(new_butlerTask.getPlanTime());
//							carOperatePlanService.updateByPrimaryKeySelective(carOperatePlanUpdate);
//						}
						CarOperatePlan carOperatePlanUpdate = new CarOperatePlan();
						carOperatePlanUpdate.setPowerUsedId(old_butlerTask.getPowerUsedId());
						carOperatePlanUpdate.setStarted(new_butlerTask.getPlanTime());
						carOperatePlanService.updateCarOperatePlanByPowerPowerUsedId(carOperatePlanUpdate);

						
						remark+="备注：变更预计送车时间，由"+sdf.format(old_butlerTask.getPlanTime())+"变为"+sdf.format(new_butlerTask.getPlanTime())+"<br/>";
					}
					if(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex()==new_butlerTask.getType()){
						powerUsed.setCarReturnTime(new_butlerTask.getPlanTime());
						this.powerUsedService.updateByPrimaryKeySelective(powerUsed);
						
						//修改车辆运营结束时间
//						CarOperatePlan carOperatePlan = new CarOperatePlan();
//						carOperatePlan.setOperateId(old_butlerTask.getCarOperateId());
//						carOperatePlan.setStarted(old_butlerTask.getPlanTime());
//						List<CarOperatePlan> carOperatePlens = carOperatePlanService.selectByCondition(carOperatePlan);
//						if(CollectionUtils.isEmpty(carOperatePlens)){
//							this.logger.info("根据"+carOperatePlan.getOperateId()+"和"+carOperatePlan.getStarted()+" 查询 carOperatePlens 为空");
//						}else{
//							//修改车辆运营开始时间
//							carOperatePlan = carOperatePlens.get(0);
//							CarOperatePlan carOperatePlanUpdate = new CarOperatePlan();
//							carOperatePlanUpdate.setId(carOperatePlan.getId());
//							carOperatePlanUpdate.setEnded(new_butlerTask.getPlanTime());
//							carOperatePlanService.updateByPrimaryKeySelective(carOperatePlanUpdate);
//						}
							CarOperatePlan carOperatePlanUpdate = new CarOperatePlan();
							carOperatePlanUpdate.setPowerUsedId(old_butlerTask.getPowerUsedId());
							carOperatePlanUpdate.setEnded(new_butlerTask.getPlanTime());
							carOperatePlanService.updateCarOperatePlanByPowerPowerUsedId(carOperatePlanUpdate);
						remark+="备注：变更预计取车时间，由"+sdf.format(old_butlerTask.getPlanTime())+"变为"+sdf.format(new_butlerTask.getPlanTime())+"<br/>";
						
						//如果是延迟的情况，需要判断延迟的这段期间有没有影响以后的运营计划，如果有影响需要给管家发消息
						this.checkSendMessageByLoseOrTrouble(new_butlerTask,old_butlerTask);
						
						//查询这段期间如果有别的运营计划影响到别的运营计划，平台运营取消掉，会员使用清空车辆
						this.updateButlertaskAndCarOperateByDelay(new_butlerTask,old_butlerTask,1);
					}
					if(ButlerTaskType.BUTLER_TASK_TYPE_CHARGING.getIndex()==new_butlerTask.getType()){
						remark+="备注：变更预计取车车时间，由"+sdf.format(old_butlerTask.getPlanTime())+"变为"+sdf.format(new_butlerTask.getPlanTime())+"<br/>";
					}
				}
			}
		}



		//送地点变更
		if(null != new_butlerTask.getTaskSendCarAddress()){
			if(!new_butlerTask.getTaskSendCarAddress().equals(old_butlerTask.getTaskSendCarAddress())){
				PowerUsed powerUsed =new PowerUsed();
				powerUsed.setId(new_butlerTask.getPowerUsedId());
				powerUsed.setCarUsedAddress(new_butlerTask.getTaskSendCarAddress());
				this.powerUsedService.updateByPrimaryKeySelective(powerUsed);
				remark+="备注：变更预计送车地点，由"+old_butlerTask.getTaskSendCarAddress()+"变为"+new_butlerTask.getTaskSendCarAddress()+"<br/>";
			}
		}

		//车辆变更
		if(null != new_butlerTask.getCarOperateId()){
			if(!new_butlerTask.getCarOperateId().equals(old_butlerTask.getCarOperateId())){
				CarOperate carOperate_old = this.carOperateService.findById(old_butlerTask.getCarOperateId());
				CarOperate carOperate_new = this.carOperateService.findById(new_butlerTask.getCarOperateId());
				if(null==carOperate_old){
					remark+="备注：分配车辆"+carOperate_new.getCarBrand()+" "+carOperate_new.getCarType()+" "+carOperate_new.getCarPlateNum()+"<br/>";
				}else{
					remark+="备注：变更车辆，由"+carOperate_old.getCarBrand()+" "+carOperate_old.getCarType()+" "+carOperate_old.getCarPlateNum()+"变为"+carOperate_new.getCarBrand()+" "+carOperate_new.getCarType()+" "+carOperate_new.getCarPlateNum()+"<br/>";
				}
			}
		}

		//还车时间变更
		PowerUsed powerUsed_old = powerUsedService.findById(new_butlerTask.getPowerUsedId());
		if(old_butlerTask.getCarReturnTime()!=null && powerUsed_old.getCarReturnTime()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String old_returnTime = sdf.format(powerUsed_old.getCarReturnTime());
			String new_returnTime = sdf.format(old_butlerTask.getCarReturnTime());
			if(!old_returnTime.equals(new_returnTime)){
				remark+="备注：变更预计还车时间，由"+sdf.format(powerUsed_old.getCarReturnTime())+"变为"+sdf.format(old_butlerTask.getCarReturnTime())+"<br/>";
			}
		}
		//地点变更
		if(null != new_butlerTask.getTaskGetCarAddress()){
			if(!new_butlerTask.getTaskGetCarAddress().equals(old_butlerTask.getTaskGetCarAddress())){
				PowerUsed powerUsed =new PowerUsed();
				powerUsed.setId(new_butlerTask.getPowerUsedId());
				powerUsed.setCarReturnAddress(new_butlerTask.getTaskGetCarAddress());
				this.powerUsedService.updateByPrimaryKeySelective(powerUsed);
				remark+="备注：变更预计取车地点，由"+old_butlerTask.getTaskGetCarAddress()+"变为"+new_butlerTask.getTaskGetCarAddress()+"<br/>";
			}
		}
		
		//插入日志
		if(!remark.isEmpty()){
			this.logger.info("日志记录为---"+remark);
			butlerTaskStatusLog.setRemark(remark.substring(0, remark.lastIndexOf("<br/>")));
		}
		if("发起签约".equals(old_butlerTask.getRemark())){
			if(butlerTaskStatusLog.getRemark() != null){
				butlerTaskStatusLog.setRemark("发起签约"+butlerTaskStatusLog.getRemark());
			}else{
				butlerTaskStatusLog.setRemark("发起签约");
			}
		}
		
		//插入记录
		butlerTaskStatusLog.setStatus(new_butlerTask.getStatus());
		butlerTaskStatusLog.setTaskId(new_butlerTask.getId());
		this.butlerTaskStatusLogService.createButlerTaskStatusLog(butlerTaskStatusLog);
		
		logger.info("管家任务插入日志结束---");
	}

	@Override
	public List<ButlerTask> selectHaveInHandButlerTaskByPlanTime(ButlerTask butlerTask) {
		return this.butlerTaskMapper.selectHaveInHandButlerTaskByPlanTime(butlerTask);
	}

	//如果是延迟的情况，需要判断延迟的这段期间有没有影响以后的运营计划，如果有影响需要给管家发消息
	public void checkSendMessageByLoseOrTrouble(ButlerTask new_butlerTask,ButlerTask old_butlerTask){
		if(new_butlerTask.getPlanTime().after(old_butlerTask.getPlanTime())){
			PowerUsed powerUsed = this.powerUsedService.findById(new_butlerTask.getPowerUsedId());
			if(null==powerUsed){
				return;
			}
			Long carTypeId = powerUsed.getCarTypeId();
			BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
			//查看延时这段时间内有没有影响库存，相当于再次发起一次用车（开始时间是延时之前的时间，结束时间为修改的最新时间）
			//如果延时之后还是当天，则不需要判断了
			Date old_planTime = DateUtils.addHours(old_butlerTask.getPlanTime(), threshold.getReadyTime()+2);
			Date new_planTime = DateUtils.addHours(new_butlerTask.getPlanTime(), threshold.getReadyTime()+2);
			if(old_planTime.getYear()==new_planTime.getYear() && old_planTime.getMonth()==new_planTime.getMonth() && old_planTime.getDay()==new_planTime.getDay()){
				//延期之后还是当天则不需要判断了
				return;
			}
			boolean storeFlag = this.carOperatePlanService.isStoreForDelay(powerUsed.getCarTypeId(), old_planTime, new_planTime);
			if(!storeFlag){
				//然后查询这段时间这个车型的所有（待接单，带送车）送车任务，给管家发消息推送
				CarOperate car = this.carOperateService.findById(new_butlerTask.getCarOperateId());
				if(null != car){
					carTypeId = car.getCarTypeId();
				}
				ButlerTask getCarButlerTask = new ButlerTask();
				getCarButlerTask.setCarTypeId(carTypeId);
				getCarButlerTask.setQueryBegin(old_planTime);
				getCarButlerTask.setQueryEnd(new_planTime);
				List<ButlerTask> butlertasklist = this.selectByCartypeIdAndStartEnd(getCarButlerTask);
				this.logger.info("查询时间区间为---"+DateUtils.formatDate(old_planTime, "yyyy-MM-dd HH:mm")+"至"+DateUtils.formatDate(new_planTime, "yyyy-MM-dd HH:mm")+"的待取车已经带送车的送车任务");
				if(CollectionUtils.isNotEmpty(butlertasklist)){
					this.logger.info("查询出来的满足条件的任务数量为----"+butlertasklist.size());
					Member butlerMember = new Member();
					for(ButlerTask butlerT : butlertasklist){
						butlerMember.setSysuserId(butlerT.getOperaterId());
						butlerMember = this.memberService.selectBySysUserId(butlerMember);
						//循环发送消息推送
						this.wechatMessageService.sendMessageByLoseOrTrouble(new_butlerTask.getCarOperateId(), 1 ,butlerMember, butlerT);
					}
				}
			}
		}
	}

	/**
	 * 由于延时，失联，故障，维修，签约造成的任务清空车辆需要记录日志
	 * @param butlertask
	 * @param member
	 * @param operateType
	 */
	@Override
	public void createButlertaskLogByemptyCar(ButlerTask butlertask, Member member, Integer operateType, Integer dealType, CarOperate car){
		
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		if(0==operateType){
			if(null != member){
				butlerTaskStatusLog.setModifier(member.getName());
				butlerTaskStatusLog.setModifierId(String.valueOf(member.getId()));
				butlerTaskStatusLog.setModifierMobile(member.getMobile());
				butlerTaskStatusLog.setModifierType(0);
			}
		}else{
			if(null != member){
				butlerTaskStatusLog.setModifier(member.getSysuserName());
				butlerTaskStatusLog.setModifierId(member.getSysuserId());
				butlerTaskStatusLog.setModifierMobile(member.getMobile());
				butlerTaskStatusLog.setModifierType(1);
			}
		}
		
		//插入记录
		butlerTaskStatusLog.setStatus(butlertask.getStatus());
		butlerTaskStatusLog.setTaskId(butlertask.getId());
		String remark="";
		if(1==dealType){
			remark="由于"+butlerTaskStatusLog.getModifier()+"对车辆"+car.getCarPlateNum()+"进行延时还车，导致任务车辆被清空";
		}else if(2==dealType){
			remark="由于"+butlerTaskStatusLog.getModifier()+"对车辆"+car.getCarPlateNum()+"进行失联操作，导致任务车辆被清空";
		}else if(3==dealType){
			remark="由于"+butlerTaskStatusLog.getModifier()+"对车辆"+car.getCarPlateNum()+"进行故障维修，导致任务车辆被清空";
		}else if(4==dealType){
			remark="由于"+butlerTaskStatusLog.getModifier()+"对车辆"+car.getCarPlateNum()+"进行签约操作，导致任务车辆被清空";
		}
		butlerTaskStatusLog.setRemark(remark);
		this.butlerTaskStatusLogService.createButlerTaskStatusLog(butlerTaskStatusLog);
	}
	@Override
	public ButlerTask updateButlerTaskByNoStatus(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "非状态修改任务-任务对象数据不能为空！");
		//取变更前任务管家信息
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		//修改任务
		butlerTask.setCompleteTime(null);
		butlerTask = this.updateButlerTask(butlerTask);
//		//给车辆新建运营计划
//		if(null==butlerTask_old.getCarOperateId() && null!=butlerTask.getCarOperateId()){
//			try {
//				this.insertCarRecord(butlerTask,CarOperatePlanEnum.status.STAY.getValue());
//			} catch (Exception e) {
//				this.logger.error("创建车辆运营计划失败---"+butlerTask.getId());
//				e.printStackTrace();
//			}
//		}
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		return butlerTask;
	}
	
	
	public ButlerTask updateSendCarButlerTaskByNoStatus(ButlerTask butlerTask) throws Exception{
		Assert.notNull(butlerTask, "非状态修改任务-任务对象数据不能为空！");
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		butlerTask.setCompleteTime(null);
		//修改任务
		butlerTask = this.updateButlerTask(butlerTask);
		//修改td_power_used 
		this.updatePowerUsedStatus(butlerTask,null);
		// 取变更前任务管家信息
		// 第一次 分配车辆的时候   给车辆新建运营计划  
		// 2016-11-09 10:00   新需求  -- 车辆运营计划 是在创建任务的时候生成的 carTypeId 不为空，carOperateId 为空.
		if(butlerTask.getCarOperateId()!=null){
			if(null == butlerTask_old.getCarOperateId()){
				//根据用车日期查询，用车日期小于数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划  做修改
				updateCarOperatePlanByStarted(butlerTask);
			}else if(butlerTask.getCarOperateId().longValue() != butlerTask_old.getCarOperateId().longValue()){
				//根据用车日期查询，用车日期小于数据库中用车日期并且 用车结束日期  + 整备时间  >= 数据库中的用车日期 的运营计划  做修改
				updateCarOperatePlanByStarted(butlerTask);
			}
			//根据用车日期查询，用车日期小于数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划  做修改
			updateCarOperatePlan(butlerTask,butlerTask_old,CarOperatePlanEnum.status.STAY.getValue());
		}

		
		//插入一条任务日志
		this.createButlerTaskStatusLog(butlerTask_old);
		
		return butlerTask;
	}
	
	/**
	 * 修改车辆运营计划
	 * @param butlerTask
	 * @param status
	 * @throws Exception 
	 */
	public void updateCarOperatePlan(ButlerTask butlerTask,ButlerTask  butlerTask_old,int status) throws Exception {
		//根据operate
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setPowerUsedId(butlerTask_old.getPowerUsedId());
		carOperatePlan.setStatus(CarOperatePlanEnum.status.STAY.getValue());
		//carOperatePlan.setStatusList(Arrays.asList(CarOperatePlanEnum.status.STAY.getValue(),CarOperatePlanEnum.status.USEING.getValue()));
		List<CarOperatePlan> cop = carOperatePlanService.selectByCondition(carOperatePlan);
		if(CollectionUtils.isNotEmpty(cop)){
			//修改
			CarOperatePlan copUpdate = new CarOperatePlan();
			copUpdate.setPowerUsedId(butlerTask.getPowerUsedId());
			if(butlerTask.getCarOperateId()!=null){
				CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
				copUpdate.setCarPlateNum(carOperate.getCarPlateNum());
				copUpdate.setOperateNum(carOperate.getOperateNum());
				copUpdate.setOperateId(carOperate.getId());
				copUpdate.setCarTypeId(carOperate.getCarTypeId());
			}
			copUpdate.setStarted(butlerTask.getPlanTime());
			copUpdate.setCarUsedAddress(butlerTask.getTaskSendCarAddress());
			copUpdate.setStatus(status);
			carOperatePlanService.updateCarOperatePlanByPowerPowerUsedId(copUpdate);
		}else{
			this.insertCarRecord(butlerTask,status);
		}
		
	}

	public void updateCarOperatePlanByStarted(ButlerTask butlerTask) throws Exception {
		//根据用车日期查询，用车日期小于=数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划
		CarOperatePlan  carOperatePlan = new CarOperatePlan();
		PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
		carOperatePlan.setStarted(powerUsed.getCarUsedTime());
		carOperatePlan.setEnded(powerUsed.getCarReturnTime());
		
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		if(basicThreshold != null && basicThreshold.getReadyTime() != null){
			carOperatePlan.setReadyTime(basicThreshold.getReadyTime());
		}
		carOperatePlan.setOperateId(butlerTask.getCarOperateId());
		List<CarOperatePlan> carOperatePlans = carOperatePlanService.selectCarOperatePlanByStarted(carOperatePlan);
		if(CollectionUtils.isNotEmpty(carOperatePlans)){
			Member operateMember = new Member();
			CarOperate car = this.carOperateService.findById(butlerTask.getCarOperateId());
			//插入清空车辆任务的日志
			operateMember.setSysuserId(butlerTask.getOperaterId());
			operateMember = this.memberService.selectBySysUserId(operateMember);
			this.logger.info("根据用车日期查询，用车日期小于=数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划:"+ JSON.toJSONString(carOperatePlans));
			//循环运营计划
			List<Long> powerUsedIds = Lists.newArrayList();
			List<Long> carOperatePlanIds = Lists.newArrayList();
			List<Long> cancelCarOperatePlanIds = Lists.newArrayList();
			String remark = null;
			String message = "您安排的平台运营车辆已被分配给会员用车，请尽快处理";
			for(CarOperatePlan cop:carOperatePlans){
				if(cop.getIsStock() == null){
					this.logger.info("运营计划" + cop.getId()+ "的IsStock 为空");
					continue;
				}
				//平台运营的车辆只是发消息
				//TODO 2016/12/20 更改 发消息和取消平台运营并且记录车辆日志
				if(cop.getPowerUsedId() == null){
					wechatMessageService.sendMessageByPlatformOperation(cop,message);
					cancelCarOperatePlanIds.add(cop.getId());
					remark="由于车辆("+car.getCarPlateNum()+")被其他任务id:"+butlerTask.getId()+",会员手机号：("+butlerTask.getMemberMobile()+")使用,导致运营计划取消";
					carOperateService.cancelPlanLog(cop.getOperateNum(), operateMember, remark, CarOperateEnum.status.STAY.getValue());

					
				}else if (cop.getPowerUsedId()!=null){
					//如果选择的是未发起占用库存的平台运营车辆，则该车受到影响的运营计划取消，对应已经选择该车的任务的“选择车辆”字段清空；
					carOperatePlanIds.add(cop.getId());
					powerUsedIds.add(cop.getPowerUsedId());
					//this.createButlerTaskStatusLog(old_butlerTask);
				}
			}
			
			if(CollectionUtils.isNotEmpty(cancelCarOperatePlanIds)){
				this.logger.info("批量更新运营计划为取消开始...运营id为："+ JSON.toJSONString(cancelCarOperatePlanIds) + "用车任务的开始时间为"+DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd HH:mm:ss"));
				carOperatePlanService.updateCarOperatePlanByCancel(cancelCarOperatePlanIds);			
			}
			
			
			if(CollectionUtils.isNotEmpty(carOperatePlanIds)){
				//this.logger.info("批量更新运营计划为取消开始...运营id为："+ JSON.toJSONString(carOperatePlanIds) + "用车任务的开始时间为"+DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd HH:mm:ss"));
				//carOperatePlanService.updateCarOperatePlanByCancel(carOperatePlanIds);
				this.logger.info("批量更新运营计划车辆id为空开始开始...运营id为："+ JSON.toJSONString(carOperatePlanIds) + "用车任务的开始时间为"+DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd HH:mm:ss"));
				carOperatePlanService.updateClearCarOperateId(carOperatePlanIds);
			}
			if(CollectionUtils.isNotEmpty(powerUsedIds)){
				List<ButlerTask> butlerTasks = this.getButlerTaskBypowerUsedIds(powerUsedIds);
				this.logger.info("批量更新任务车辆为空开始...powerUsedIds 的 id为："+ JSON.toJSONString(powerUsedIds) + "用车任务的开始时间为"+DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd HH:mm:ss"));
				this.updateClearCarOperateId(powerUsedIds);
				
				//清空任务车辆 清空通知
				remark="由于车辆("+car.getCarPlateNum()+")被其他任务id:"+butlerTask.getId()+",会员手机号：("+butlerTask.getMemberMobile()+")使用,导致任务车辆被清空";
				for(ButlerTask bt:butlerTasks){
					butlerTaskSendCarService.createButlertaskLogByemptyCar(bt, operateMember, remark);
//					this.createButlerTaskStatusLog(bt);
					wechatMessageService.sendMessageByEmptyTaskCarOperate(bt);
				}
			}
						
		}
	}

	@Override
	public List<ButlerTask> getButlerTaskBypowerUsedIds(List<Long> powerUsedIds) {
		return butlerTaskMapper.getButlerTaskBypowerUsedIds(powerUsedIds);
	}

	public void updateClearCarOperateId(List<Long> powerUsedIds) {
		this.butlerTaskMapper.updateClearCarOperateId(powerUsedIds);
	}

	@Override
	public List<ButlerTask> getButlerTaskListForGetCar(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "查询取车任务列表，任务对象不能为空");
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex());
		
		List<ButlerTask> butlerTaskList = this.getButlerTask(butlerTask);
		this.logger.info("查询的管家取车任务数量为"+butlerTaskList.size());
		return butlerTaskList;
	}

	@Override
	public List<ButlerTask> getButlerTaskListForSendCar(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "查询送车任务列表，任务对象不能为空");
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
		
		List<ButlerTask> butlerTaskList = this.getButlerTask(butlerTask);
		this.logger.info("查询的管家送车任务数量为"+butlerTaskList.size());
		return butlerTaskList;
	}
	@Override
	public String checkCreateSendCarButlerTask(Member m) throws Exception {

		if(m == null){
			return "该会员不存在，无法创建任务";
		}
		if(m.getStatuts() == null || m.getStatuts().intValue() == 0){
			return "该会员已被冻结，无法创建任务";
		}

		/*MemberItem mitem = new MemberItem();
		mitem.setMemberId(m.getId());
		MemberItem memberItem = memberItemService.selectMemberItem(mitem);
		if(memberItem == null){
			return "该会员没有权益，不能创建送车任务";
		}*/

		MemberItemShare memberItemShare = memberItemShareService.selectByMemberId(m.getId());

		if(memberItemShare == null){
			return "该用户当前未购买权益，无法创建任务";
		}
		MemberItem memberItem = memberItemService.findById(memberItemShare.getMemberItemId());
		Item item = itemService.findById(memberItem.getItemId());
		if(item == null){
			return "该用户没有购买任何套餐，不能创建任务";
		}
		
		if(m.getUseCarApproved() == null || m.getUseCarApproved().intValue() != 2){
			return "该会员未通过用车审核，无法创建任务";
		}
//		//获取套餐中车型的数量
//		MemberItemCartype  mict = new MemberItemCartype();
//        mict.setMemberItemId(memberItem.getId());
//		int itemCarTypeCount  = memberItemCartypeService.selectCountByCondition(mict);

		//套餐中车位的数量
		int enableCount = item.getEnableCount();
//		if(enableCount < itemCarTypeCount){
//			enableCount = itemCarTypeCount;//如果车位数量小于车型的数量则取车型的数量
//		}
//		//查询用户用车的任务数量
		PowerUsed t = new PowerUsed();
		t.setMemberItemId(memberItem.getId());
		List<PowerUsed> taskCounts = powerUsedService.selectPowerUsedOfUseingCars(t);
		int taskCount = taskCounts.size();

		/*判断用车任务小于车位启用数量才能下单*/
		if(taskCount >= enableCount){
			return "该用户的车位已用完，还车后方可发起";
		}

		GetmoneyApply getmoneyApply = new GetmoneyApply();
		getmoneyApply.setMemberItemId(memberItem.getId());
		//判断当前权益是否在押金提现审核中，结果如下：
		List<GetmoneyApply> getmoneyApplys = getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply);
		if(CollectionUtils.isNotEmpty(getmoneyApplys)){
			return "该用户正在提现中，不可发起用车";
		}
		return null;
	}

	@Override
	public int findHaveInHandButlerTask(Long memberID) {
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setMemberId(String.valueOf(memberID));
		return butlerTaskMapper.findHaveInHandButlerTask(butlerTask);
	}

	@Override
	public String checkSendCarButlerTaskMemberBalance(ButlerTask butlerTask) {
        //需要考虑共用人的情况不能直接查询，要从share表里面查询
		MemberItemShare memberitemShare = memberItemShareService.selectByMemberId(Long.valueOf(butlerTask.getMemberId()));
		/*根据用户id 和权益id 查询用户所购买的启用车型*/
		MemberItem memberItem = memberItemService.findById(memberitemShare.getMemberItemId());
		//用户余额
		double balance = memberItem.getBalance().doubleValue();
		//车型日租金
		ItemCartype itemCartype  = new ItemCartype();
		itemCartype.setItemId(memberItem.getItemId());
		itemCartype.setCartypeId(butlerTask.getCarTypeId());
		List<ItemCartype> itemCartypes = itemCartypeService.selectByCondition(itemCartype);
		if(CollectionUtils.isEmpty(itemCartypes)){
			return "会员权益不存在";
		}
		double carTypeRent = itemCartypes.get(0).getDayPrice().doubleValue();
		if(carTypeRent <= 0){
			return "车辆日租金不能小于等于0";
		}

		if(balance < carTypeRent){
			return "该用户余额不足，无法创建任务";
		}

		//判断当前用户当前是否正在使用当前车型。
//		PowerUsed t = new PowerUsed();
//		t.setMemberItemId(memberItem.getId());
//		//t.setCarTypeId(powerUsed.getCarTypeId());
//		List<PowerUsed> list = powerUsedService.selectPowerUsedOfUseingCars(t);
//		if(CollectionUtils.isNotEmpty(list)){
//			for(PowerUsed p:list){
//				if(p.getCarTypeId().equals(butlerTask.getCarTypeId())){
//					return "该车型正在被用户使用中，无法再次创建该车型的任务";
//				}
//			}
//		}

		boolean isStore = carOperatePlanService.isStore(butlerTask.getCarTypeId(),butlerTask.getPlanTime(),butlerTask.getCompleteTime());
		if(isStore ==  false){
			return "您选择的车型暂无库存";
		}
		return null;
	}

	@Override
	public ButlerTask getButlerTaskByPowerUsedId(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "根据用车记录ID以及任务类型查询任务，任务对象不能为空");
		Assert.notNull(butlerTask.getType(), "根据用车记录ID以及任务类型查询任务，任务类型不能为空");
		Assert.notNull(butlerTask.getPowerUsedId(), "根据用车记录ID以及任务类型查询任务，任务类型不能为空");
		
		butlerTask = this.butlerTaskMapper.getButlerTaskByPowerUsedId(butlerTask);
		return butlerTask;
	}

	@Override
	public List<ButlerTask> getButlerTaskForCalendar(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "送/取车任务获取日历数据，任务对象不能为空");
		Assert.notNull(butlerTask.getPlanTime(), "送/取车任务获取日历数据,传入的时间不能为空");
		Assert.notNull(butlerTask.getType(), "送/取车任务获取日历数据,传入的类型不能为空");
		List<ButlerTask> butlerList = this.butlerTaskMapper.getButlerTaskForCalendar(butlerTask);
		return butlerList;
	}

	@Override
	public ButlerTask getButlerTaskIsChargeing(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "任务对象不能为空");
		Assert.notNull(butlerTask.getCarOperateId(), "车辆id不能为空");
		return this.butlerTaskMapper.getButlerTaskIsChargeing(butlerTask);
	}

	@Override
	public List<ButlerTask> selectByCondition(ButlerTask butlerTask) {
		return butlerTaskMapper.selectByCondition(butlerTask);
	}

	@Override
	public void updateGetCarButlerTaskByMember(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "修改还车信息，任务对象不能为空");
		Assert.notNull(butlerTask.getId()+"", "修改还车信息，任务id不能为空");
		//判断是谁操作的
		Integer modifierType = butlerTask.getModifierType();
		
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		
		butlerTask = this.updateButlerTask(butlerTask);
		
		//插入一条任务日志
		butlerTask_old.setModifierType(modifierType);
		this.createButlerTaskStatusLog(butlerTask_old);
		
		//给管家发送消息推送
		String value= "您有一个取车任务时间发生变更，请及时查看";
		this.wechatMessageService.sendMessageByTaskCreate(butlerTask, value);
		
		//如果是延迟的情况，需要判断延迟的这段期间有没有影响以后的运营计划，如果有影响需要给管家发消息
		this.checkSendMessageByLoseOrTrouble(butlerTask,butlerTask_old);
		
		//查询这段期间如果有别的运营计划影响到别的运营计划，平台运营取消掉，会员使用清空车辆
		this.updateButlertaskAndCarOperateByDelay(butlerTask,butlerTask_old,0);
	}
	
	/**
	 * 查询这段期间如果有别的运营计划影响到别的运营计划，平台运营取消掉，会员使用清空车辆
	 * @param new_butlerTask
	 * @param old_butlerTask
	 * 
	 */
	private void updateButlertaskAndCarOperateByDelay(ButlerTask new_butlerTask,ButlerTask old_butlerTask, Integer operateType){
		try {
			//首先将影响的任务的车辆ID清空，然后将对应的会员使用的运营计划车辆id清空，如果是平台运营，则将运营计划取消
			if(new_butlerTask.getPlanTime().after(old_butlerTask.getPlanTime())){
				PowerUsed powerUsed = this.powerUsedService.findById(new_butlerTask.getPowerUsedId());
				if(null==powerUsed){
					return;
				}
				//将后续关联了该车辆的任务的车辆ID设置为null,后面表示是谁操作的
				this.updateButlerTaskCarOperateByDelay(new_butlerTask,old_butlerTask, operateType);
				CarOperatePlan carOperateP = new CarOperatePlan();
				//查询车辆车牌号
				CarOperate car = carOperateService.findById(new_butlerTask.getCarOperateId());
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				if(car != null){
					this.logger.info("延期的运营计划车辆车牌为---"+car.getCarPlateNum());
					carOperateP.setCarPlateNum(car.getCarPlateNum());
					carOperateP.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
					carOperateP.setStarted(DateUtils.addHours(old_butlerTask.getPlanTime(), threshold.getReadyTime()+2));
					carOperateP.setEnded(DateUtils.addHours(new_butlerTask.getPlanTime(), threshold.getReadyTime()+2));
					carOperateP.setPowerUsedId(new_butlerTask.getPowerUsedId());
					this.logger.info("延期引起修改运营计划---");
					//会员用车清空车辆
					this.carOperatePlanService.updateStatusByOperateIdStartedAndEnd(carOperateP);
					CarOperatePlan plan = new CarOperatePlan();
					plan.setCarPlateNum(car.getCarPlateNum());
					plan.setPowerUsedId(null);
					plan.setStarted(DateUtils.addHours(old_butlerTask.getPlanTime(), threshold.getReadyTime()+2));
					plan.setEnded(DateUtils.addHours(new_butlerTask.getPlanTime(), threshold.getReadyTime()+2));
					List<CarOperatePlan> planList = carOperatePlanService.selectCarPlanByOperateIdStartedAndEndForPlatform(plan);
					
					Member operateMember = new Member();
					if(null!=operateType && 0==operateType){
						//表示是用户操作的
						operateMember = this.memberService.findMemberByID(Long.parseLong(new_butlerTask.getMemberId()));
					}else{
						operateMember.setSysuserId(new_butlerTask.getOperaterId());
						operateMember = this.memberService.selectBySysUserId(operateMember);
					}
					for(CarOperatePlan plans : planList){
						String message = "您安排的平台运营车辆由于延时已被取消，请尽快处理";
						wechatMessageService.sendMessageByPlatformOperation(plans,message);
						String remark = "平台运营受到延时影响自动取消";
						this.carOperateService.cancelPlanLog(car.getOperateNum(), operateMember, remark,4);
					}
					//平台运营直接变成取消
					this.carOperatePlanService.updateStatusByOperateIdStartedAndEndForPlatform(carOperateP);
				}
			}
		} catch (Exception e) {
			this.logger.info("查询这段期间如果有别的运营计划影响到别的运营计划，平台运营取消掉，会员使用清空车辆失败---");
			e.printStackTrace();
			
		}
		
	}

	public void updateButlerTaskCarOperateByDelay(ButlerTask new_butlerTask , ButlerTask old_butlerTask, Integer operateType){
		try {
			if(null!=new_butlerTask.getCarOperateId()){
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				ButlerTask butlerT= new ButlerTask();
				butlerT.setCarOperateId(new_butlerTask.getCarOperateId());
				butlerT.setPlanTime(DateUtils.addHours(old_butlerTask.getPlanTime(), threshold.getReadyTime()+2));
				butlerT.setCompleteTime(DateUtils.addHours(new_butlerTask.getPlanTime(), threshold.getReadyTime()+2));
				List<ButlerTask> butlerList = this.selectButlerTaskListByCarOperateIdAndTime(butlerT);
				Member operateMember = null;
				if(null!=operateType && 0==operateType){
					//表示是用户操作的
					operateMember = this.memberService.findMemberByID(Long.parseLong(new_butlerTask.getMemberId()));
				}
				if(CollectionUtils.isNotEmpty(butlerList)){
					//查询车辆信息
					CarOperate car = this.carOperateService.findById(new_butlerTask.getCarOperateId());
					for(ButlerTask bt : butlerList){
						//循环修改将车协任务的车辆ID设置为null
						if(!bt.getId().equals(new_butlerTask.getId())){
							//给需要清空的任务发送消息推送
							this.wechatMessageService.sendMessageByEmptyCarOperate(new_butlerTask.getCarOperateId(), 1, bt);
							//记录任务日志
							if(null != operateMember){
								this.createButlertaskLogByemptyCar(old_butlerTask, operateMember, operateType, 1, car);
							}else{
								operateMember.setSysuserId(bt.getOperaterId());
								operateMember = this.memberService.selectBySysUserId(operateMember);
								this.createButlertaskLogByemptyCar(old_butlerTask, operateMember, operateType, 1, car);
							}
							
							this.updateButlerTaskCarOperateNull(bt);
						}
					}
				}
			}
		} catch (Exception e) {
			this.logger.error("验收清空车辆操作错误---");
			e.printStackTrace();
		}
	
	}
	
	@Override
	public ButlerTask updateSendCarButlerTaskByWaitingForACar(ButlerTask butlerTask) {
		Assert.notNull(butlerTask,"修改送车任务-待送车-对象不能为空");
//        butlerTask = this.updateWaitingForACar(butlerTask);
		//修改送车任务
		ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		ButlerTask butlerTaskUpdate = new ButlerTask();
		butlerTaskUpdate.setId(butlerTask.getId());
		butlerTaskUpdate.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_SEND_FOR_A_CAR.getIndex());
		butlerTask = this.updateButlerTask(butlerTaskUpdate);
		this.createButlerTaskStatusLog(butlerTask_old);

		String value = "您有新的任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreate(butlerTask, value);

//		ButlerTask butlerTaskGet = new ButlerTask();
//		butlerTaskGet.setPowerUsedId(butlerTask.getPowerUsedId());
//		butlerTaskGet.setType(2);
//		List<ButlerTask> butlerTaskGets = this.getButlerTask(butlerTaskGet);
//		//给取车任务的送车管家发消息
//		this.wechatMessageService.sendMessageByTaskCreate(butlerTaskGets.get(0), value);

		return butlerTask;
	}

	@Override
	public ButlerTask updateWaitingForACar(ButlerTask butlerTask) {
		Assert.notNull(butlerTask,"送车任务对象不能为空");

        //修改送车任务
        ButlerTask butlerTask_old = this.getButlerTaskById(butlerTask.getId());
		butlerTask_old.setCarReturnTime(butlerTask.getCompleteTime());
		butlerTask.setCompleteTime(null);
		butlerTask = this.updateButlerTask(butlerTask);
        this.createButlerTaskStatusLog(butlerTask_old);


		Long powerUsedId = butlerTask.getPowerUsedId();
		//修改用车任务的还车时间和还车地址、用车时间和用车地址
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setId(powerUsedId);
		powerUsed.setCarUsedTime(butlerTask.getPlanTime());
		powerUsed.setCarUsedAddress(butlerTask.getTaskSendCarAddress());
		powerUsed.setCarReturnAddress(butlerTask.getTaskGetCarAddress());
		powerUsed.setCarReturnTime(butlerTask_old.getCarReturnTime());
		powerUsedService.updateByPrimaryKeySelective(powerUsed);

        //修改取车任务
        ButlerTask butlerTaskGet = new ButlerTask();
        butlerTaskGet.setType(2);
        butlerTaskGet.setPowerUsedId(butlerTask.getPowerUsedId());
        List<ButlerTask> butlerTaskGets = this.selectByCondition(butlerTaskGet);
        if(CollectionUtils.isEmpty(butlerTaskGets)){
            this.logger.info(JSONObject.toJSONString(butlerTaskGet) + "查找取车任务为空");
            return null;
        }
        butlerTaskGet = butlerTaskGets.get(0);
        ButlerTask butlerTaskUpdate = new ButlerTask();
        butlerTaskUpdate.setId(butlerTaskGet.getId());
        butlerTaskUpdate.setPlanTime(powerUsed.getCarReturnTime());
		butlerTaskUpdate.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
        butlerTaskUpdate.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
        this.updateButlerTask(butlerTaskUpdate);
        this.createButlerTaskStatusLog(butlerTaskGet);
        
        //修改车辆运营计划表
		CarOperatePlan copUpdate = new CarOperatePlan();
		copUpdate.setPowerUsedId(powerUsed.getId());
		copUpdate.setStarted(powerUsed.getCarUsedTime());
		copUpdate.setEnded(powerUsed.getCarReturnTime());
		copUpdate.setCarUsedAddress(powerUsed.getCarUsedAddress());
		this.carOperatePlanService.updateCarOperatePlanByPowerPowerUsedId(copUpdate);
        return butlerTask;
	}

	@Override
	public List<ButlerTask> selectNotAvailableCar(ButlerTask butlerTask) {
		return this.butlerTaskMapper.selectNotAvailableCar(butlerTask);
	}

	@Override
	public List<ButlerTask> selectButlerTaskListByCarOperateId(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "给定一个车辆Id，查询开始时间在当前时间之后的所有任务，任务对象不能为空");
		Assert.notNull(butlerTask.getCarOperateId(), "给定一个车辆Id，查询开始时间在当前时间之后的所有任务，车辆ID不能为空");
		Assert.notNull(butlerTask.getPlanTime(), "给定一个车辆Id，查询开始时间在当前时间之后的所有任务，开始时间不能为空");
		
		List<ButlerTask> butlerTaskList = this.butlerTaskMapper.selectButlerTaskListByCarOperateId(butlerTask);
		this.logger.info("给定一个车辆Id，查询开始时间在当前时间之后的所有任务数量为"+butlerTaskList.size());
		return butlerTaskList;
		
	}

	@Override
	public void updateButlerTaskCarOperateNull(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "按主键将任务上的车辆Id设置为null，任务对象不能为空");
		Assert.notNull(butlerTask.getId(), "按主键将任务上的车辆Id设置为null，任务ID不能为空");
		this.butlerTaskMapper.updateButlerTaskCarOperateNull(butlerTask);
	}

	@Override
	public CarOperatePlan insertCarRecordToBeExecuted(ButlerTask butlerTask) throws Exception {
		return this.insertCarRecord(butlerTask, CarOperatePlanEnum.status.STAY.getValue());
	}

	@Override
	public CarOperatePlan insertCarRecordImplementationIn(ButlerTask butlerTask) throws Exception {
		return this.insertCarRecord(butlerTask, CarOperatePlanEnum.status.USEING.getValue());
	}

	@Override
	public List<ButlerTask> selectButlerTaskListByCarOperateIdAndTime(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "给定一个车辆Id，查询延期时间段的所有任务，任务对象不能为空");
		Assert.notNull(butlerTask.getCarOperateId(), "给定一个车辆Id，查询延期时间段的所有任务，车辆ID不能为空");
		Assert.notNull(butlerTask.getPlanTime(), "给定一个车辆Id，查询延期时间段的所有任务，开始时间不能为空");
		Assert.notNull(butlerTask.getCompleteTime(), "给定一个车辆Id，查询延期时间段的所有任务，开始时间不能为空");
		
		List<ButlerTask> butlerTaskList = this.butlerTaskMapper.selectButlerTaskListByCarOperateIdAndTime(butlerTask);
		this.logger.info("给定一个车辆Id，查询延期时间段的所有任务数量为"+butlerTaskList.size());
		return butlerTaskList;
	}

	/**
	 * 用车时 修改用车、还车任务 时间或地点
	 */
	public Boolean updateSendAndReturnButlerTask(PowerUsed powerUsed) {
		Boolean b =true;
		if(powerUsed.getCarUsedTime()!=null || StringUtils.isNotBlank(powerUsed.getCarUsedAddress())){
			//查询任务
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(powerUsed.getId());
			butlerTask.setType(3);
			//送车任务
			ButlerTask butlerTaskSend = butlerTaskMapper.getButlerTaskByPowerUsedId(butlerTask);
			
			if(butlerTaskSend.getStatus() != 9){//不是待处理则提示
				b=false;
				return b;
			}
			ButlerTask butlerTaskSend_new = new ButlerTask();
			butlerTaskSend_new.setId(butlerTaskSend.getId());
			if(StringUtils.isNotBlank(powerUsed.getCarUsedAddress())){
				butlerTaskSend_new.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
			}
			if(powerUsed.getCarUsedTime()!=null){
				butlerTaskSend_new.setPlanTime(powerUsed.getCarUsedTime());
			}
			butlerTaskMapper.updateByPrimaryKeySelective(butlerTaskSend_new);
			
			butlerTaskSend.setModifierType(0);
			
			createButlerTaskStatusLog(butlerTaskSend);
			if(powerUsed.getCarUsedTime()!=null){
				CarOperatePlan carOperatePlan = new CarOperatePlan();
				carOperatePlan.setPowerUsedId(powerUsed.getId());
				//修改还车任务
				List<CarOperatePlan> plans = carOperatePlanService.selectByCondition(carOperatePlan );
				if(CollectionUtils.isNotEmpty(plans)){
					CarOperatePlan carOperatePlan2 = new CarOperatePlan();
					carOperatePlan2.setId(plans.get(0).getId());
					carOperatePlan2.setStarted(powerUsed.getCarUsedTime());
					carOperatePlanService.updateByPrimaryKeySelective(carOperatePlan2);
				}
			}
		}
		if(powerUsed.getCarReturnTime()!=null || StringUtils.isNotBlank(powerUsed.getCarReturnAddress())){
			//已完成 查取车任务
			ButlerTask butlerTaskNotComplete = new ButlerTask();
			butlerTaskNotComplete.setPowerUsedId(powerUsed.getId());
			butlerTaskNotComplete.setType(2);
			ButlerTask butlerTaskGet = butlerTaskMapper.getButlerTaskByPowerUsedId(butlerTaskNotComplete);
			if(butlerTaskGet.getStatus() != 9){//不是待处理则提示
				b=false;
				return b;
			}
			if(powerUsed.getCarReturnTime()!=null){
				butlerTaskGet.setPlanTime(powerUsed.getCarReturnTime());
			}
			if(StringUtils.isNotBlank(powerUsed.getCarReturnAddress())){
				butlerTaskGet.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
			}
			
			butlerTaskGet.setModifierType(0);
			updateGetCarButlerTaskByMember(butlerTaskGet);
//			butlerTaskMapper.updateByPrimaryKeySelective(butlerTaskGet);
//			createButlerTaskStatusLog(butlerTaskGet);
			
			if(powerUsed.getCarReturnTime()!=null){
				CarOperatePlan carOperatePlan = new CarOperatePlan();
				carOperatePlan.setPowerUsedId(powerUsed.getId());
				//修改还车任务
				List<CarOperatePlan> plans = carOperatePlanService.selectByCondition(carOperatePlan );
				if(CollectionUtils.isNotEmpty(plans)){
					CarOperatePlan carOperatePlan2 = new CarOperatePlan();
					carOperatePlan2.setId(plans.get(0).getId());
					carOperatePlan2.setEnded(powerUsed.getCarReturnTime());
					carOperatePlanService.updateByPrimaryKeySelective(carOperatePlan2);
				}
			}
			
		}
		powerUsedService.updateByPrimaryKeySelective(powerUsed);
		return b;
	}

	@Override
	public ButlerTask createMemberSendCarButlerTasked(ButlerTask butlerTask) {
		//2、生成一条送车任务
		butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
		
		//设置客服负责人
		//this.setCustomer(butlerTask);
		this.createButlerTask(butlerTask);
		this.createButlerTaskStatusLog(butlerTask);

		/*//给客服发送微信消息推送
		String value = "您有新的送车任务，请尽快处理";
		this.wechatMessageService.sendMessageByTaskCreateToCustomer(butlerTask, value);*/
		return butlerTask;
	}

	@Override
	public List<ButlerTask> selectByCartypeIdAndStartEnd(ButlerTask butlerTask) {
		Assert.notNull(butlerTask, "根据车型ID查询两个时间段内的待接单待送车的送车任务,对象不能为空");
		Assert.notNull(butlerTask.getCarTypeId(), "根据车型ID查询两个时间段内的待接单待送车的送车任务,车型ID不能为空");
		return this.butlerTaskMapper.selectByCartypeIdAndStartEnd(butlerTask);
	}

}
