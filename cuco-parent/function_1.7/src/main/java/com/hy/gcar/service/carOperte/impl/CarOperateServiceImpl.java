package com.hy.gcar.service.carOperte.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.common.utils.DateUtils;
import com.hy.common.utils.StringUtils;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.constant.PowerUsedEnum;
import com.hy.gcar.dao.CarOperateMapper;
import com.hy.gcar.dao.CarOperatePlanMapper;
import com.hy.gcar.entity.BasicCost;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperateLog;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.entity.PowerUsedCost;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicCostService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;

@Service(value = "tdCarOperateService")
public class CarOperateServiceImpl implements CarOperateService {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
    @Autowired
    private CarOperateMapper<CarOperate>tdCarOperateMapper;
    
    @Autowired
    private CarOperatePlanMapper<CarOperatePlan>tdCarOperatePlanMapper;
    
    @Autowired
    private CarOperatePlanService carOperatePlanService;
    
    @Autowired
	private BasicCostService basicCostService;
    
    @Autowired
  	private MemberItemShareService memberItemShareService;
    
	@Autowired
	private MemberItemService memberItemService;
	
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	
	@Autowired
	private PowerUsedService powerUsedService;
	
	@Autowired
	private PowerUsedCostService powerUsedCostService;
	
	@Autowired
	private CarOperateLogService carOperateLogService;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private ButlerTaskService butlerTaskService;


	@Autowired
	private BasicThresholdService basicThresholdService;
	
	@Autowired
	private WechatMessageService wechatMessageService;
	
	@Autowired
	private ItemCartypeService itemCartypeService;
	
    
    @Override
    public Integer insertSelective(CarOperate tdCarOperate) throws Exception {
           return this.tdCarOperateMapper.insertSelective(tdCarOperate);
        }

    @Override
    public Integer insertBatch(List<CarOperate> tdCarOperate) throws Exception {
           return this.tdCarOperateMapper.insertBatch(tdCarOperate) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdCarOperateMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdCarOperateMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CarOperate tdCarOperate) {
           return this.tdCarOperateMapper.updateByPrimaryKeySelective(tdCarOperate);
    }

    @Override
    public CarOperate findById(Object id) {
           return (CarOperate) this.tdCarOperateMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public CarOperate findByCarPlanNum(String carPlanNum) {
    	CarOperate car = new CarOperate();
    	car.setCarPlateNum(carPlanNum);
    	List<CarOperate> carList = tdCarOperateMapper.selectByCondition(car);
    	if(CollectionUtils.isNotEmpty(carList)){
    		return carList.get(0);
    	}
    	return null;
    }

    @Override
    public List<CarOperate> selectByCondition(CarOperate tdCarOperate) {
           return (List<CarOperate>) this.tdCarOperateMapper.selectByCondition(tdCarOperate);
    }

    @Override
    public Integer selectCountByCondition(CarOperate tdCarOperate) {
           return  this.tdCarOperateMapper.selectCountByCondition(tdCarOperate);
    }

	@Override
	public List<CarOperate> selectListByDay(Date maxDate,Date miniDate,int type) {
		return this.tdCarOperateMapper.selectListByDay(CarOperatePlanEnum.status.STAY.getValue(), CarOperatePlanEnum.status.USEING.getValue(), CarOperateEnum.status.STAY.getValue(), CarOperateEnum.status.READINESSING.getValue(), maxDate,miniDate,type,CarOperateEnum.status.MEMBERUSE.getValue(),CarOperateEnum.status.PLATFORMUSE.getValue(),CarOperateEnum.status.REPAIRSING.getValue());
	}

	@Override
	public List<CarOperate> selectListBystatus(Date nowDate) {
		List<CarOperate> list = tdCarOperateMapper.selectListBystatus(CarOperateEnum.status.STAY.getValue());
		List<CarOperatePlan> cacleList = tdCarOperatePlanMapper.selectListBystatusAndNow(CarOperatePlanEnum.status.STAY.getValue(), CarOperatePlanEnum.status.USEING.getValue(), nowDate);
		list = new CopyOnWriteArrayList<>(list);
		
		for(CarOperatePlan carOperatePlan : cacleList){
			for(CarOperate car : list){
				if(carOperatePlan.getOperateId() != null){
					if(car.getId().longValue() == carOperatePlan.getOperateId().longValue()){
						list.remove(car);
					}
				}
			}
			
//			CarOperate carOperate = findById(carOperatePlan.getOperateId());
//			for(CarOperate item : list){
//				if(null != item && carOperatePlan.getOperateId() == item.getId()){
//					list.remove(item);
//					break;
//				}
//			}
		/*	for(int i = list.size() -1 ; i < list.size() ; i--){
				CarOperate item = list.get(i);
				if(null != item && carOperatePlan.getOperateId() == item.getId()){
					list.remove(item);
				}
			}*/
		}
		return list;
	}
	
	@Override
	public Integer stay(long carOperateId){
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.STAY.getValue());
		return updateByPrimaryKeySelective(carOperate);
	}

	@Override
	public Integer stay(long carOperateId,Long loginId,int type) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.STAY.getValue());
		//查询车辆
		CarOperate car = findById(carOperateId);
		//运营记录
		CarOperateLog carOperateLog = new  CarOperateLog();
		carOperateLog.setType(CarOperateEnum.status.STAY.getValue());
		carOperateLog.setOperateNum(car.getOperateNum());
		if(loginId != null){
			try {
				Member member = memberService.findMemberByID(loginId);
				if(member != null){
					carOperateLog.setOperateName(member.getSysuserName());
				}
			} catch (Exception e) {
				logger.info("车辆手动整备或者手动失联找回查询管家姓名错误"+e);
			}
			carOperateLog.setOperateId(loginId.toString());
		}
		if(type == 1){
			carOperateLog.setRemark("管家手动整备完成");	
		}else if(type == 2){
			carOperateLog.setRemark("管家手动失联找回完成");	
		}else if(type == 3){
			carOperateLog.setRemark("管家手动取消签约");	
		}
		
		carOperateLog.setCreated(new Date());
		try {
			carOperateLogService.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--carOperatePlanId"+car.getId());
		}
		return updateByPrimaryKeySelective(carOperate);
	}
	
	@Override
	public Integer repairsing(long carOperateId) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.REPAIRSING.getValue());
		carOperate.setFailureTime(new Date());
		return updateByPrimaryKeySelective(carOperate);
	}
	
	//车辆中只提供待分配，维修中，整备中 三个业务状态修改
/*	
	@Override
	public Integer memberuseing(long carOperateId) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.MEMBERUSEING.getValue());
		return updateByPrimaryKeySelective(carOperate);
	}

	@Override
	public Integer platformuseing(long carOperateId) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.PLATFORMUSEING.getValue());
		return updateByPrimaryKeySelective(carOperate);
	}*/

	
	/**
	 * 故障换车收取用户一次性换车费用，同时需要往费用日志里面插入一条记录
	 */
	private BigDecimal updatePowerUsedAndAccoutByGasoline(PowerUsed powerUsed,ItemCartype itemCarType,MemberItemShare memberItemShare,Long newCarOperateId,CarOperatePlan selectCarOperatePlan){

		    BigDecimal accidentcarPrice = null;
			//查询故障换车占日用车费百分比   扣除一次性故障换车费用
			BasicCost BasicCost = new BasicCost();
			List<BasicCost> BasicCostList = this.basicCostService.selectByCondition(BasicCost);
			BigDecimal price = null;
			if(BasicCostList.size()>0){
				accidentcarPrice = BasicCostList.get(0).getAccidentcarPrice();
				BigDecimal mo = new BigDecimal(100);
				BigDecimal divide = accidentcarPrice.divide(mo);
				price = itemCarType.getDayPrice().multiply(divide).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			//查询权益
			MemberItem memberItem = this.memberItemService.findById(memberItemShare.getMemberItemId());
			
			//插入一条权益消费的记录td_member_account_log
			MemberAccountLog memberAccountLog = new MemberAccountLog();
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
			BigDecimal balance = price.multiply(new BigDecimal(-1));
			//memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
			if(selectCarOperatePlan != null){
				memberAccountLog.setModifierId(selectCarOperatePlan.getModifierId());
				memberAccountLog.setModifier(selectCarOperatePlan.getModifier());
			}
			memberAccountLog.setMemberItemId(memberItemShare.getMemberItemId());
			memberAccountLog.setMemberItemName(memberItem.getItemName());
			memberAccountLog.setAccountType(1);
			memberAccountLog.setTransformType(12);
			memberAccountLog.setBalance(balance);
			memberAccountLog.setCreated(new Date());
			memberAccountLog.setTotal(balance);
			memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
			memberAccountLog.setPreBalance(memberItem.getBalance());
			memberAccountLog.setPreDeposit(memberItem.getDeposit());
			memberAccountLog.setRemark("故障换车费用");
			memberAccountLog.setBalanceReason("故障换车费用");
			memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
			try {
				this.memberAccountLogService.insertSelective(memberAccountLog);
			} catch (Exception e) {
				this.logger.error("插入会员权益消费记录失败---");
			}
			//修改权益信息主表信息
			MemberItem memberItem_new = new MemberItem();
			memberItem_new.setId(memberItem.getId());
			memberItem_new.setBalance(memberItem.getBalance().subtract(price));
			this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
			//修改用车记录中的故障换车信息
			updatePowerUsedStatus(powerUsed, memberItem.getId(), newCarOperateId,balance);
			return price;
	}
	
	/**
	 * 修改用车记录中的故障换车信息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-26 下午2:35:52   
	* @return String
	 */
	private void updatePowerUsedStatus(PowerUsed powerUsed,Long memberItemId,Long newCarOperateId,BigDecimal balance){
			if(powerUsed != null){
				Long powerUsedId = powerUsed.getId();
				powerUsed = new PowerUsed();
				powerUsed.setId(powerUsedId);
				powerUsed.setChangeStatus(PowerUsedEnum.status.YES.getValue());
				powerUsed.setChangeCaroperateId(newCarOperateId);
				powerUsedService.updateByPrimaryKeySelective(powerUsed);
				//修改用车记录费用明细 增加故障换车费用
				PowerUsedCost powerUsedCost = powerUsedCostService.selectByPowerUsedId(powerUsedId);
				if(powerUsedCost != null){
					PowerUsedCost newPowerUsedCost = new PowerUsedCost();
					newPowerUsedCost.setAccidentcarPrice(balance);
					newPowerUsedCost.setId(powerUsedCost.getId());
					powerUsedCostService.updateByPrimaryKeySelective(newPowerUsedCost);
				}
			}
	}
	
	private MemberItemShare getMemberItemShare(Long memberId){
		//1.普通会员

		return memberItemShareService.selectByMemberId(memberId);
	}
	
	/**
	 * 修理 去除任务上车辆
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-24 下午3:15:58   
	* @return String
	 */
	public void updateButlerTaskCarOperateByfix(CarOperate newCarOperate,CarOperatePlan selectCarOperatePlan,Long loginId){

		if(null != newCarOperate){
			BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
			ButlerTask butlerT= new ButlerTask();
			butlerT.setCarOperateId(newCarOperate.getId());
			butlerT.setPlanTime(new Date());
			butlerT.setCompleteTime(DateUtils.addHours(selectCarOperatePlan.getEnded(), threshold.getReadyTime()+2));
			List<ButlerTask> butlerList = butlerTaskService.selectButlerTaskListByCarOperateIdAndTime(butlerT);
			Member operateMember = null;
			//表示是用户操作的
			try {
				operateMember = this.memberService.findMemberByID(loginId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(butlerList != null){
				for(ButlerTask bt : butlerList){
					Long powerUsedId = selectCarOperatePlan.getPowerUsedId();
					if(powerUsedId != null){
						if(!bt.getPowerUsedId().equals(powerUsedId)){
							//给需要清空的任务发送消息推送
							this.wechatMessageService.sendMessageByEmptyCarOperate(newCarOperate.getId(), 3, bt);
							//记录任务日志
							if(null != operateMember){
								butlerTaskService.createButlertaskLogByemptyCar(bt, operateMember, 1, 3, newCarOperate);
							}else{
								operateMember.setSysuserId(bt.getOperaterId());
								operateMember = this.memberService.selectBySysUserId(operateMember);
								butlerTaskService.createButlertaskLogByemptyCar(bt, operateMember, 1, 3, newCarOperate);
							}
							
							butlerTaskService.updateButlerTaskCarOperateNull(bt);
						}
					}
				}
			}
		}
		
	}

	/**
	 * 平台运营  变为已取消
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-24 下午3:26:27   
	* @return String
	 */
	public void updateStatusByOperateIdStartedAndEndForPlatform(CarOperatePlan carOperatePlan,String carPlanNum,Long loginId){
		Date now = carOperatePlan.getStarted();
		carOperatePlan.setCarPlateNum(carOperatePlan.getCarPlateNum());
		carOperatePlan.setEnded(carOperatePlan.getEnded());
		carOperatePlan.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
		carOperatePlan.setId(carOperatePlan.getId());
		tdCarOperatePlanMapper.updateStatusByOperateIdStartedAndEndForPlatformNotById(carOperatePlan);
		CarOperatePlan plan = new CarOperatePlan();
		plan.setCarPlateNum(carPlanNum);
		plan.setPowerUsedId(null);
		now.setSeconds(now.getSeconds() - 1);
		plan.setStarted(now);
		List<CarOperatePlan> planList = carOperatePlanService.selectCarPlanNumAndpowerUsedId(plan);
		for(CarOperatePlan plans : planList){
			//插入一条新的运营中的日志
			CarOperateLog carOperateLog = new CarOperateLog();
			Member member = null;
			try {
				member = memberService.findMemberByID(loginId);
				if(member != null){
					carOperateLog.setOperateName(member.getSysuserName());
					carOperateLog.setOperateId(member.getSysuserId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			carOperateLog.setRemark("平台运营受到故障换车影响自动取消");
			carOperateLog.setType(CarOperateEnum.status.REPAIRSING.getValue());
			carOperateLog.setOperateNum(plans.getOperateNum());
			carOperateLog.setCreated(new Date());
			try {
				carOperateLogService.insertSelective(carOperateLog);
			} catch (Exception e) {
				logger.error("发起车辆维修replace--carId"+plans.getOperateId());
			}
			String message = "您安排的平台运营车辆由于车辆故障已被取消，请尽快处理";
			wechatMessageService.sendMessageByPlatformOperation(plans,message);
		}
	}
	
	/**
	 * 会员预约变为  清空车辆
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-24 下午3:28:56   
	* @return String
	 */
	public void updateStatusByOperateIdStartedAndEnd(CarOperatePlan carOperatePlan){
		carOperatePlan.setStarted(new Date());
		carOperatePlan.setCarPlateNum(carOperatePlan.getCarPlateNum());
		carOperatePlan.setEnded(carOperatePlan.getEnded());
		carOperatePlan.setId(carOperatePlan.getId());
		tdCarOperatePlanMapper.updateStatusByOperateIdStartedAndEndNotById(carOperatePlan);
	}


	@Override
	public Integer replaceCar(String oldCarPlateNum, String newCarPlateNum,
			String remark, long carOperatePlanId,int status,Long loginId) {
		Date firstDate = new Date();
		//根据车牌号 查询新换车辆信息
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		CarOperate newCarOperate = new CarOperate();
		newCarOperate.setCarPlateNum(newCarPlateNum);
		List<CarOperate> newCarOperateList = selectByCondition(newCarOperate);
		//根据车牌号 查询旧换车辆信息
		CarOperate carOperate = new CarOperate();
		carOperate.setCarPlateNum(oldCarPlateNum);
		List<CarOperate> carOperateList = selectByCondition(carOperate);
		Integer result = null;
		CarOperate car = null;
		BigDecimal price;
		CarOperatePlan selectCarOperatePlan = null;
		try {
			if(carOperateList != null && carOperateList.size() > 0){
				car = carOperateList.get(0);
			}	
			if(newCarOperateList != null && newCarOperateList.size() > 0){
				newCarOperate = newCarOperateList.get(0);
			}	
			selectCarOperatePlan = tdCarOperatePlanMapper.selectByPrimaryKey(carOperatePlanId);
			
			if(selectCarOperatePlan != null){
				if(selectCarOperatePlan.getPowerUsedId() != null){
					ButlerTask task = new ButlerTask();
					task.setPowerUsedId(selectCarOperatePlan.getPowerUsedId());
					List<ButlerTask> taskList = butlerTaskService.selectByCondition(task);
					if(CollectionUtils.isNotEmpty(taskList)){
						for(ButlerTask taskt : taskList){
							ButlerTask taskf = new ButlerTask();
							taskf.setId(taskt.getId());
							if(newCarOperate != null){
								taskf.setCarOperateId(newCarOperate.getId());
							}
							butlerTaskService.updateButlerTask(taskf);
						}
					}
				}
				//扣除平台运营的故障换车费用
				if(selectCarOperatePlan.getMemberId() != 0){
					//会员预约扣除故障换车费用
					MemberItemShare memberItemShare = getMemberItemShare(selectCarOperatePlan.getMemberId());
					
					if(memberItemShare != null){
						PowerUsed powerUsed = new PowerUsed();
						powerUsed.setMemberId(selectCarOperatePlan.getMemberId());
						powerUsed.setMemberItemId(memberItemShare.getMemberItemId());
						powerUsed.setCaroperateId(selectCarOperatePlan.getOperateId());
						List<PowerUsed> powerUsedList = powerUsedService.selectByMemBerIdItemIdCarId(powerUsed);
						if(powerUsedList != null && powerUsedList.size() > 0){
							PowerUsed newPowerUsed = powerUsedList.get(0);
							if(newPowerUsed != null){
								Long carTypeId = newPowerUsed.getCarTypeId();
								ItemCartype itemCarType = getItemCarType(memberItemShare.getMemberItemId());
								//扣除一次性故障换车费用。。。 业务方法 
								price = updatePowerUsedAndAccoutByGasoline(newPowerUsed,itemCarType,memberItemShare,newCarOperate.getId(),selectCarOperatePlan);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("故障换车replaceCar错误"+e.getMessage());
		}
		
		//修改旧车辆状态 为维修中
		if(car != null){
			//替换车辆状态为维修中
			result = repairsing(car.getId());
			//查询此车辆拥有的任务,取消任务的车辆
			ButlerTask task = new ButlerTask();
			task.setCarOperateId(car.getId());
			task.setCompleteTime(firstDate);
			Member member;
			try {
				member = memberService.findMemberByID(loginId);
				task.setOperaterId(member.getSysuserId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(selectCarOperatePlan != null){
				Long powerUsedId = selectCarOperatePlan.getPowerUsedId();
				if(powerUsedId != null){
					ButlerTask butlerTask = new ButlerTask();
					butlerTask.setPowerUsedId(powerUsedId);
					butlerTask.setType(2);
					List<ButlerTask> list = butlerTaskService.selectByCondition(butlerTask);
					if(CollectionUtils.isNotEmpty(list)){
						task.setId(list.get(0).getId());
					}
				}
			}
			
			butlerTaskService.updateButlerTaskCarOperate(task,3);
			checkSendMessageByFault(car);
			
		/*	CarOperatePlan carPlan = new CarOperatePlan();
			carPlan.setCarPlateNum(car.getCarPlateNum());
			carPlan.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
			carPlan.setStarted(new Date());
			carOperatePlanService.updateStatusByOperateIdAndStarted(selectCarOperatePlan);*/
			//插入一条新的运营中的日志
			CarOperateLog carOperateLog = new CarOperateLog();
			
			//运营记录
			if(selectCarOperatePlan != null){
				//查询故障换车占日用车费百分比   扣除一次性故障换车费用
				BasicCost BasicCost = new BasicCost();
				List<BasicCost> BasicCostList = this.basicCostService.selectByCondition(BasicCost);
				//BigDecimal price = null;
				if(BasicCostList.size()>0){
					BigDecimal accidentcarPrice = BasicCostList.get(0).getAccidentcarPrice();
					BigDecimal mo = new BigDecimal(100);
					BigDecimal divide = accidentcarPrice.divide(mo);
					
					if(selectCarOperatePlan.getMemberId() != 0){
						//carOperateLog.setTakein(price);
						carOperateLog.setRemark("会员预约故障换车");
					}else{
						/*if(selectCarOperatePlan.getMoney() != null){
							price = selectCarOperatePlan.getMoney().multiply(divide).setScale(2, BigDecimal.ROUND_HALF_UP);
						}*/
						//carOperateLog.setTakein(price);
						carOperateLog.setRemark("平台预约故障换车");
					}
				}
				
				carOperateLog.setMemberId(selectCarOperatePlan.getMemberId());
				if(StringUtils.isNotEmpty(selectCarOperatePlan.getMemberName())){
					carOperateLog.setMemberName(selectCarOperatePlan.getMemberName());
				}else{
					carOperateLog.setMemberName(selectCarOperatePlan.getOperateTo());
				}
				carOperateLog.setOperateId(selectCarOperatePlan.getButlerId());
				carOperateLog.setOperateName(selectCarOperatePlan.getButlerName());
				//carOperateLog.setOperateTypeId(selectCarOperatePlan.getOperateType().toString());
				carOperateLog.setOperateTypeName(selectCarOperatePlan.getOperateType().toString());
			}
			//carOperateLog.setRemark("发起故障");
			carOperateLog.setType(CarOperateEnum.status.REPAIRSING.getValue());
			carOperateLog.setOperateNum(car.getOperateNum());
			carOperateLog.setCreated(new Date());
			try {
				carOperateLogService.insertSelective(carOperateLog);
			} catch (Exception e) {
				logger.error("添加车辆日志错误insertCarLog--carOperatePlanId"+carOperatePlan.getId());
			}
			
		}
		
		if(result != null && result > 0){
			selectCarOperatePlan.setStarted(firstDate);
			selectCarOperatePlan.setCarPlateNum(newCarPlateNum);
			//修理  换新车 去除新车影响到的   任务上的车辆
			updateButlerTaskCarOperateByfix(newCarOperate, selectCarOperatePlan,loginId);
			//平台运营 变为已取消
			updateStatusByOperateIdStartedAndEndForPlatform(selectCarOperatePlan,newCarPlateNum,loginId);
			//会员预约变为 清空车辆
			updateStatusByOperateIdStartedAndEnd(selectCarOperatePlan);
			
			checkSendMessageByFault(newCarOperate);
			
			//车辆状态只修改  运营表里的状态
			if(status == CarOperateEnum.status.MEMBERUSEING.getValue()){
				result = carOperatePlanService.memberuse(carOperatePlanId);
				//result = memberuseing(carOperateId);
			}else if(status == CarOperateEnum.status.PLATFORMUSEING.getValue()){
				//result = platformuseing(carOperateId);
				result = carOperatePlanService.memberuseing(carOperatePlanId);
			}
			//修改运营表里 运营车辆编号 和 车牌号 为新换车辆的 车牌号 和运营车辆编号
			//operate_id 还是旧车辆的车辆id  修理完成后用户选择旧车辆入库后 更新为新车辆的id
			//如果修理完成后 用户选择继续使用 则 修改运营车辆编号 和车牌号为 修理好的旧车辆的车牌号 车辆id不做修改
			if(result != null && result > 0){
				if(StringUtils.isNotEmpty(newCarOperate.getOperateNum())){
					carOperatePlan.setOperateNum(newCarOperate.getOperateNum());
				}
				carOperatePlan.setRemark(remark);
				carOperatePlan.setCarPlateNum(newCarPlateNum);
				carOperatePlan.setFailureTime(firstDate);
				carOperatePlan.setId(carOperatePlanId);
				if(newCarOperate != null){
					carOperatePlan.setCarTypeId(newCarOperate.getCarTypeId());
				}
				result = carOperatePlanService.updateByPrimaryKeySelective(carOperatePlan);
				//取消该车辆的后续预约计划
				CarOperatePlan carOperateP = new CarOperatePlan();
				//查询车辆车牌号
				if(car != null){
					Date now = new Date();
					carOperateP.setCarPlateNum(car.getCarPlateNum());
					carOperateP.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
					carOperateP.setStarted(now);
					//会员用车清空车辆
					carOperatePlanService.updateStatusByOperateIdAndStarted(carOperateP);
					//tdCarOperatePlanMapper.updateStatusByOperateIdAndStarted(carOperateP);
					//平台运营直接变成取消
					carOperatePlanService.updateStatusByOperateIdAndStartedForPlatform(carOperateP);
					CarOperatePlan plan = new CarOperatePlan();
					plan.setCarPlateNum(car.getCarPlateNum());
					plan.setPowerUsedId(null);
					now.setSeconds(now.getSeconds() - 1);
					plan.setStarted(now);
					List<CarOperatePlan> planList = carOperatePlanService.selectCarPlanNumAndpowerUsedId(plan);
					for(CarOperatePlan plans : planList){
						//插入一条新的运营中的日志
						CarOperateLog carOperateLog = new CarOperateLog();
						Member member = null;
						try {
							member = memberService.findMemberByID(loginId);
							if(member != null){
								carOperateLog.setOperateName(member.getSysuserName());
								carOperateLog.setOperateId(member.getSysuserId());
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						carOperateLog.setRemark("平台运营受到故障换车影响自动取消");
						carOperateLog.setType(CarOperateEnum.status.REPAIRSING.getValue());
						carOperateLog.setOperateNum(plans.getOperateNum());
						carOperateLog.setCreated(new Date());
						try {
							carOperateLogService.insertSelective(carOperateLog);
						} catch (Exception e) {
							logger.error("发起车辆维修replace--carId"+plans.getOperateId());
						}
						String message = "您安排的平台运营车辆由于车辆故障已被取消，请尽快处理";
						wechatMessageService.sendMessageByPlatformOperation(plans,message);
					}
				}
			}
			
		}
		return result;
	}
	
	/**
	 * 车辆故障给收到影响的任务的负责管家发送消息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-13 下午6:07:34   
	* @return String
	 */
	private void checkSendMessageByFault(CarOperate car){

		//查看失联之后的一个月时间内有没有影响库存，相当于再次发起一次用车（开始时间是延时之前的时间，结束时间为修改的最新时间）
		Date currentDate = new Date();
		boolean storeFlag = this.carOperatePlanService.isStoreForDelay(car.getCarTypeId(), currentDate, DateUtils.addMonths(currentDate, 1));
		if(!storeFlag){
			//然后查询这段时间这个车型的所有（待接单，带送车）送车任务，给管家发消息推送
			ButlerTask getCarButlerTask = new ButlerTask();
			getCarButlerTask.setCarTypeId(car.getCarTypeId());
			getCarButlerTask.setQueryBegin(currentDate);
			getCarButlerTask.setQueryEnd(DateUtils.addMonths(currentDate, 1));
			List<ButlerTask> butlertasklist = butlerTaskService.selectByCartypeIdAndStartEnd(getCarButlerTask);
			if(CollectionUtils.isNotEmpty(butlertasklist)){
				Member butlerMember = new Member();
				for(ButlerTask butlerT : butlertasklist){
					butlerMember.setSysuserId(butlerT.getOperaterId());
					butlerMember = this.memberService.selectBySysUserId(butlerMember);
					//循环发送消息推送
					this.wechatMessageService.sendMessageByLoseOrTrouble(car.getId(), 3 , butlerMember, butlerT);
				}
			}
		}
	}

	@Override
	public Integer readinessing(long carOperateId) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.READINESSING.getValue());
		return updateByPrimaryKeySelective(carOperate);
	}
	
	@Override
	public Integer outOfContact(long carOperateId) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.OUTOFCONTACT.getValue());
		return updateByPrimaryKeySelective(carOperate);
	}
	
	@Override
	public List<CarOperate> selectAvailableVehicles(ButlerTask butlerTask) {
		//查询所有 带分配和整备中的车辆
		List<CarOperate>  carOperates = tdCarOperateMapper.selectAvailableVehicles(butlerTask);
		carOperates = new CopyOnWriteArrayList<>(carOperates);
		//查询用车开始时间大于 运营计划结束的时间的运营计划 用来替换
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setEnded(butlerTask.getPlanTime());
		List<CarOperatePlan>  greaterThans = carOperatePlanService.selectPlanTimeGreaterThanEndTime(carOperatePlan);
		for(CarOperatePlan greaterThan :greaterThans){
			for(CarOperate arOperate:carOperates){
				if(greaterThan.getCarPlateNum().equals(arOperate.getOperateNum())){
					arOperate.setCarPlanStatusInfo(greaterThan.getStatusInfo());
					arOperate.setOperateStatusInfo(greaterThan.getOperateStatusInfo());
					arOperate.setEndTime(greaterThan.getEnded());
					break;
				}
			}

		}


		//查询用户计划完成时间
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setId(butlerTask.getPowerUsedId());
		powerUsed = powerUsedService.findById(powerUsed);

		//查询用车开始时间小于 运营计划结束的时间的运营计划 用来删除
		List<CarOperatePlan>  lessThans = carOperatePlanService.selectPlanTimeLessThanEndTime(carOperatePlan);
		for(CarOperatePlan lessThan:lessThans){
			if(butlerTask.getPlanTime().getTime() < lessThan.getStarted().getTime() && powerUsed.getCarReturnTime().getTime() < lessThan.getStarted().getTime()){
				continue;
			}
			for(CarOperate arOperate:carOperates){
				if(lessThan.getCarPlateNum().equals(arOperate.getCarPlateNum())){
					carOperates.remove(arOperate);
					break;
				}
			}
		}
		//查询用车开始时间大于 运营计划
		return carOperates;
	}
	
	@Override
	public void cancelPlanLog(String operateNum,Member member,String remark,Integer status) throws Exception{
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		if(member != null){
			carOperateLog.setOperateName(member.getSysuserName());
			carOperateLog.setOperateId(member.getSysuserId());
		}
		carOperateLog.setRemark(remark);
		carOperateLog.setType(status);
		carOperateLog.setOperateNum(operateNum);
		carOperateLog.setCreated(new Date());
		carOperateLogService.insertSelective(carOperateLog);
		
	}
	

	@Override
	public List<CarOperate> selectAvailableVehiclesCar(ButlerTask butlerTask) {
		//查询所有 带分配的车辆
		List<CarOperate> carOperates;
		try {
			carOperates = tdCarOperateMapper.selectAvailableVehicles(butlerTask);
			carOperates = new CopyOnWriteArrayList<>(carOperates);
			
			//查询运营计划 运营状态为 待执行 执行中且历史数据的开始用车时间 <= 新任务的预计用车时间  and 历史数据的结束时间 >= 历史数据的开始用车时间
			CarOperatePlan carOperatePlan = new CarOperatePlan();

			PowerUsed powerUsed = new PowerUsed();
			powerUsed.setId(butlerTask.getPowerUsedId());
			powerUsed = powerUsedService.findById(powerUsed);
			carOperatePlan.setStarted(butlerTask.getPlanTime());
			carOperatePlan.setEnded(powerUsed.getCarReturnTime());
			BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
			if(basicThreshold != null && basicThreshold.getReadyTime() != null){
				carOperatePlan.setReadyTime(basicThreshold.getReadyTime());
			}
			List<CarOperatePlan>  notAvailableCar = carOperatePlanService.selectNotAvailableCar(carOperatePlan);
			for(CarOperate carOperate:carOperates){
				for(CarOperatePlan cop:notAvailableCar){
						if(carOperate.getCarPlateNum().equals(cop.getCarPlateNum())){
							if(!butlerTask.getPowerUsedId().equals(cop.getPowerUsedId())){
								carOperates.remove(carOperate);
							}
						}
				}
			}
		} catch (Exception e) {
			carOperates = null;
		}
		
		return carOperates;
	}

	@Override
	public Integer getCountByStatus(CarOperate carOperate) {
		return tdCarOperateMapper.getCountByStatus(carOperate);
	}

	@Override
	public List<CarOperate> searchCarPlateNum(String carPlateNum) {
		return tdCarOperateMapper.searchCarPlateNum(carPlateNum);
	}

	@Override
	public ItemCartype getItemCarType(Long memberItemId) {
		ItemCartype itemCartype = new ItemCartype();
		MemberItem memberItem = this.memberItemService.findById(memberItemId);
		if(null != memberItemId){
			itemCartype.setItemId(memberItem.getItemId());
			List<ItemCartype> itemCartypeList = this.itemCartypeService.selectByCondition(itemCartype);
			if(CollectionUtils.isNotEmpty(itemCartypeList)){
				return itemCartypeList.get(0);
			}
		}
		return itemCartype;
	}

	@Override
	public boolean checkVehicleLegitimate(ButlerTask butlerTask,ButlerTask butlerTaskData) {
		if(butlerTask.getCarOperateId()!= null && butlerTaskData.getCarOperateId() == null){
			List<CarOperate> carOperates = this.selectAvailableVehiclesCar(butlerTask);
			for(CarOperate carOperate:carOperates){
				if(carOperate.getId().longValue() == butlerTask.getCarOperateId().longValue()){
					return true;
				}
			}
		}else if(butlerTask.getCarOperateId().longValue() == butlerTaskData.getCarOperateId().longValue()){
			return true;
		}else{
			List<CarOperate> carOperates = this.selectAvailableVehiclesCar(butlerTask);
			for(CarOperate carOperate:carOperates){
				if(carOperate.getId().longValue() == butlerTask.getCarOperateId().longValue()){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Integer appointment(long carOperateId) {
		CarOperate carOperate = new CarOperate();
		carOperate.setId(carOperateId);
		carOperate.setStatus(CarOperateEnum.status.APPOINTMENT.getValue());
		return updateByPrimaryKeySelective(carOperate);
	}

}
