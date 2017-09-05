package com.hy.gcar.service.carOperatePlan.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.common.utils.DateUtils;
import com.hy.common.utils.StringUtils;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.constant.PowerUsedEnum;
import com.hy.gcar.dao.BasicOperationTypeMapper;
import com.hy.gcar.dao.CarOperateMapper;
import com.hy.gcar.dao.CarOperatePlanMapper;
import com.hy.gcar.entity.BasicOperationType;
import com.hy.gcar.entity.BasicRestriction;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperateLog;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.powerUserd.PowerUsedService;

@Service(value = "tdCarOperatePlanService")
public class CarOperatePlanServiceImpl implements CarOperatePlanService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
    @Autowired
    private CarOperatePlanMapper<CarOperatePlan>tdCarOperatePlanMapper;
    
    @Autowired
    private CarOperateMapper<CarOperate>tdCarOperateMapper;
    
    @Autowired
    private BasicOperationTypeMapper<BasicOperationType>tcBasicOperationTypeMapper;
    
    @Autowired
    private CarOperateService carOperateService;
    
    @Autowired
    private MemberItemShareService memberItemShareService;
    
	@Autowired
	private PowerUsedService powerUsedService;
	
	@Autowired
	private CarOperateLogService carOperateLogService;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	@Autowired
	private BasicThresholdService basicThresholdService;
	
	@Autowired
	private BasicRestrictionService basicRestrictionService;
	
	@Autowired
	private CarTypeService carTypeService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ButlerTaskService butlerTaskService;
    
    @Override
    public Integer insertSelective(CarOperatePlan tdCarOperatePlan) throws Exception {
           Integer id = this.tdCarOperatePlanMapper.insertSelective(tdCarOperatePlan);
           return id;
        }

    @Override
    public Integer insertBatch(List<CarOperatePlan> tdCarOperatePlan) throws Exception {
           return this.tdCarOperatePlanMapper.insertBatch(tdCarOperatePlan) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdCarOperatePlanMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdCarOperatePlanMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CarOperatePlan tdCarOperatePlan) {
           return this.tdCarOperatePlanMapper.updateByPrimaryKeySelective(tdCarOperatePlan);
    }

    @Override
    public CarOperatePlan findById(Object id) {
           return (CarOperatePlan) this.tdCarOperatePlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarOperatePlan> selectByCondition(CarOperatePlan tdCarOperatePlan) {
           return (List<CarOperatePlan>) this.tdCarOperatePlanMapper.selectByCondition(tdCarOperatePlan);
    }

    @Override
    public Integer selectCountByCondition(CarOperatePlan tdCarOperatePlan) {
           return  this.tdCarOperatePlanMapper.selectCountByCondition(tdCarOperatePlan);
    }

	@Override
	public List<CarOperatePlan> selectListByStatus(Date maxDate,Date miniDate,int type) {
		return  this.tdCarOperatePlanMapper.selectListByStatus(maxDate,miniDate,CarOperatePlanEnum.status.STAY.getValue(),CarOperatePlanEnum.status.USEING.getValue(),type);
	}

	@Override
	public List<CarOperatePlan> selectListByPlateNum(String carPlateNum,
			Date maxDate,Date miniDate) {
		return tdCarOperatePlanMapper.selectListByPlateNum(carPlateNum, maxDate,miniDate,CarOperatePlanEnum.status.STAY.getValue(),CarOperatePlanEnum.status.USEING.getValue());
	}

	@Override
	public List<CarOperatePlan> selectListByOperateNum(String operateNum,
			Date miniDate) {
		return tdCarOperatePlanMapper.selectListByOperateNum(operateNum, miniDate);
	}

	@Override
	public CarOperatePlan selectByOperateNum(String operateNum) {
		return tdCarOperatePlanMapper.selectByOperateNum(operateNum,new Date());
	}

	@Override
	public CarOperatePlan selectByOperateNumAndStarted(String operateNum,
			Date started) {
		return tdCarOperatePlanMapper.selectByOperateNumAndStarted(operateNum, started);
	}

	@Override
	public List<CarOperatePlan> selectListByOperateNumAndStarted(Date ended,
			Date started, String operateNum) {
		return tdCarOperatePlanMapper.selectListByOperateNumAndStarted(CarOperatePlanEnum.status.STAY.getValue(), CarOperatePlanEnum.status.USEING.getValue(), ended, started, operateNum);
	}
	
	@Override
	public List<CarOperatePlan> selectListByOperateNumAndStartedByRepair(Date ended,
			Date started, String operateNum) {
		return tdCarOperatePlanMapper.selectListByOperateNumAndStarted(CarOperatePlanEnum.status.USEING.getValue(), CarOperatePlanEnum.status.USEING.getValue(), ended, started, operateNum);
	}

	@Override
	public Integer cacleOperatePlan(String reason, int type, String operateNum,long carOperateId,long carOperatePlanId) {
		
		CarOperatePlan carPlan = findById(carOperatePlanId);
		if(carPlan != null){
			CarOperate carOperate = new CarOperate();
			carOperate.setId(carOperateId);
			carOperate.setStatus(CarOperateEnum.status.STAY.getValue());
			tdCarOperateMapper.updateByPrimaryKeySelective(carOperate);
			
			//根据type 判断是否 删除收益记录 还是   新增收益记录
			//处理收益 代码 先省略  删除当天收益
			//新增收益
			if(type == 1){
				CarOperateLog carOperateLog = new CarOperateLog();
				carOperateLog.setOperateNum(operateNum);
				carOperateLog.setType(carOperate.getStatus());
				carOperateLog.setCreated(new Date());
				carOperateLog.setRemark("取消平台运营记录收益");
				carOperateLog.setTakein(carPlan.getMoney());
				if(carPlan != null){
					carOperateLog.setMemberName(carPlan.getOperateTo());
					carOperateLog.setOperateName(carPlan.getButlerName());
					carOperateLog.setOperateId(carPlan.getButlerId());
				}
				try {
					carOperateLogService.insertSelective(carOperateLog);
				} catch (Exception e) {
					logger.error("添加车辆日志错误insertCarLog--carOperatePlanId"+carOperate.getId());
				}
			}
		}
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setId(carOperatePlanId);
		carOperatePlan.setReason(reason);
		carOperatePlan.setStatus(CarOperatePlanEnum.status.CANCLE.getValue());
		Integer keyId = updateByPrimaryKeySelective(carOperatePlan);
		return keyId;
	}
	
	private Integer getGaultCarStatus(Long carOperateId) {
		//默认为车辆状态  待分配 整备中
		CarOperate carOperate = carOperateService.findById(carOperateId);
		int status = CarOperateEnum.status.STAY.getValue();
		if(carOperate != null){
			status = carOperate.getStatus();
			if(status == CarOperateEnum.status.REPAIRSING.getValue() ||
        			status == CarOperateEnum.status.READINESSING.getValue()){
        		return status;
        	}
			//查询 执行中 平台运营的记录
			CarOperatePlan carOperatePlan = new CarOperatePlan();
			carOperatePlan.setOperateId(carOperateId);
			//carOperatePlan.setCarPlateNum(carOperate.getCarPlateNum());
			carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
			carOperatePlan.setMemberId(0L);
			CarOperatePlan carPlanStatus = tdCarOperatePlanMapper.selectCarStatus(carOperatePlan);
			if(carPlanStatus != null){
				status = CarOperateEnum.status.PLATFORMUSEING.getValue();
			}else{
				//会员使用中
				carOperatePlan = new CarOperatePlan();
				carOperatePlan.setOperateId(carOperateId);
				//carOperatePlan.setCarPlateNum(carOperate.getCarPlateNum());
				carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
				carOperatePlan.setMemberId(1L);
				carPlanStatus = tdCarOperatePlanMapper.selectCarStatus(carOperatePlan);
				if(carPlanStatus != null){
					status = CarOperateEnum.status.MEMBERUSEING.getValue();
				}
			}
		}
		
		//查询会员使用中状态
		//select * from td_car_operate_plan p where p.operate_id='426' and p.`status`=2 and p.member_id !=0;
		
		//查询平台使用中状态
		//select * from td_car_operate_plan p where p.operate_id='426' and p.`status`=2 and p.member_id =0;
		
		//如果都没有则为 车辆的状态
		return status;
	}

	@Override
	public Integer repairFinish(Date finishDate, BigDecimal maintenanceCosts, int handle,
			Long carOperateId, Long carOperatePlanId,String repairManufacturer,Long memberId) {
		//车辆修理 完成
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setId(carOperatePlanId);
		carOperatePlan.setFinishTime(finishDate);
		carOperatePlan.setMaintenanceCosts(maintenanceCosts);
		carOperatePlan.setHandle(handle);
		carOperatePlan.setRepairManufacturer(repairManufacturer);
		Integer id= 0;
		//修改车辆的运营状态
		if(memberId != null && memberId > 0){
			carOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSEING.getValue());
		}else{
			carOperatePlan.setOperateStatus(CarOperateEnum.status.PLATFORMUSEING.getValue());
		}
		//旧车信息
		CarOperate car = carOperateService.findById(carOperateId);
		//用户选择 继续使用   修改 运营车辆编号 和 车牌号 为 修理好的旧车辆 车牌号
		CarOperatePlan carPlan = findById(carOperatePlanId);
		Member member = null;
		try {
			member = memberService.findMemberByID(memberId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(handle == CarOperatePlanEnum.handle.use.getValue()){
			//故障换车后的新车信息
			CarOperate carOperate = new CarOperate();
			if(carPlan != null){
				carOperate.setCarPlateNum(carPlan.getCarPlateNum());
				List<CarOperate> carLists = carOperateService.selectByCondition(carOperate);
				if(carLists != null && carLists.size() > 0){
					carOperate = carLists.get(0);
					if(carOperate != null){
						CarOperate newCar = new CarOperate();
						newCar.setId(carOperate.getId());
						newCar.setStatus(CarOperateEnum.status.READINESSING.getValue());
						newCar.setReadyTime(new Date());
						tdCarOperateMapper.updateByPrimaryKeySelective(newCar);
						carOperate.setStatus(CarOperateEnum.status.READINESSING.getValue());
						
						insertCarLog(carOperate,null,carPlan, carOperate.getStatus(),"故障维修完成,旧车用户继续使用,新换车辆为整备中",member);
					}
				}
			}
			
			if(car != null){
				try {
					if(carPlan !=null && carPlan.getPowerUsedId() != null){
						ButlerTask task = new ButlerTask();
						task.setPowerUsedId(carPlan.getPowerUsedId());
						//task.setType(but)
						List<ButlerTask> taskList = butlerTaskService.selectByCondition(task);
						if(CollectionUtils.isNotEmpty(taskList)){
							for(ButlerTask taskt : taskList){
								ButlerTask taskf = new ButlerTask();
								taskf.setId(taskt.getId());
								taskf.setCarOperateId(car.getId());
								butlerTaskService.updateButlerTask(taskf);
							}
						}
					}
					
					carOperatePlan.setCarTypeId(car.getCarTypeId());
					carOperatePlan.setCarPlateNum(car.getCarPlateNum());
					carOperatePlan.setOperateNum(car.getOperateNum());
					carOperatePlan.setOperateId(car.getId());
					//修改  旧车辆状态为 待分配
					carOperate = new CarOperate();
					carOperate.setId(carOperateId);
					carOperate.setStatus(CarOperateEnum.status.STAY.getValue());
					//carOperate.setReadyTime(new Date());
					id = tdCarOperateMapper.updateByPrimaryKeySelective(carOperate);
					id = updateByPrimaryKeySelective(carOperatePlan);
					int status = getGaultCarStatus(carOperateId);
					car.setStatus(status);
					insertCarLog(car,maintenanceCosts,carPlan, car.getStatus(),"故障维修完成",member);
					MemberItemShare memberItemShare = memberItemShareService.selectByMemberId(memberId);
					if(memberItemShare != null){
						ItemCartype itemCarType = carOperateService.getItemCarType(memberItemShare.getMemberItemId());
						if(itemCarType != null){
							if(carPlan != null){
								//会员预约扣除故障换车费用
								PowerUsed powerUsed = new PowerUsed();
								powerUsed.setMemberId(carPlan.getMemberId());
								powerUsed.setMemberItemId(memberItemShare.getMemberItemId());
								powerUsed.setCaroperateId(car.getId());
								
								//powerUsed.setCarTypeId(car.getCarTypeId());
								powerUsed.setMemberItemId(memberItemShare.getMemberItemId());
								List<PowerUsed> powerUsedList = powerUsedService.selectByMemBerIdItemIdCarId(powerUsed);
								if(powerUsedList != null && powerUsedList.size() > 0){
									powerUsed = powerUsedList.get(0);
								}
								Long powerUsedId = powerUsed.getId();
								powerUsed = new PowerUsed();
								powerUsed.setId(powerUsedId);
								powerUsed.setChangeStatus(PowerUsedEnum.status.NO.getValue());
								powerUsed.setChangeCaroperateId(0L);
								powerUsedService.updateByPrimaryKeySelective(powerUsed);
							}
						}
					}
				} catch (Exception e) {
					logger.error("维修完成repairFinish错误--用户继续使用use"+e.getMessage());
				}
			
			}
		//用户选择 车辆入库 修改 运营车辆operate_id 车辆id为 新车辆id  运营车辆编号和车牌号也为新车辆信息
		}else if(handle == CarOperatePlanEnum.handle.storage.getValue()){
			//车辆入库 不做操作。 为了配合 修理完成操作
			/*CarOperatePlan nowCarOperatePlan = findById(carOperatePlanId);
			if(nowCarOperatePlan != null){
				CarOperate carOperate = new CarOperate();
				carOperate.setCarPlateNum(nowCarOperatePlan.getCarPlateNum());
				List<CarOperate> carList = carOperateService.selectByCondition(carOperate);
				if(carList != null && carList.size() > 0){
					CarOperate nowCarOperate = carList.get(0);
					if(nowCarOperate != null){
						carOperatePlan.setOperateId(nowCarOperate.getId());
					}
				}
			}*/
			//int status = getGaultCarStatus(carOperateId);
			
			if(carPlan != null){
				CarOperate carOperate = new CarOperate();
				carOperate.setCarPlateNum(carPlan.getCarPlateNum());
				List<CarOperate> carLists = carOperateService.selectByCondition(carOperate);
				if(CollectionUtils.isNotEmpty(carLists)){
					carOperate = carLists.get(0);
					if(carPlan.getOperateId() != null){
						if(carOperateId.equals(carPlan.getOperateId())){
							carOperatePlan.setOperateId(carOperate.getId());
							carOperatePlan.setCarTypeId(carOperate.getCarTypeId());
						}
					}
				}	
			}
			car.setStatus(CarOperateEnum.status.STAY.getValue());
			insertCarLog(car,maintenanceCosts,carPlan, car.getStatus(),"故障维修完成,维修厂商"+repairManufacturer+"维修费用"+maintenanceCosts,member);
			//修改  旧车辆状态为 待分配
			CarOperate carOperate = new CarOperate();
			carOperate.setId(carOperateId);
			carOperate.setStatus(CarOperateEnum.status.STAY.getValue());
			//carOperate.setReadyTime(new Date());
			id = updateByPrimaryKeySelective(carOperatePlan);
			id = tdCarOperateMapper.updateByPrimaryKeySelective(carOperate);
		}
		return id;
	}
	
	private void insertCarLog(CarOperate carOperate,BigDecimal maintenanceCosts,CarOperatePlan carPlan,int status,String remark,Member member){
		if(carOperate != null){
			//运营记录
			CarOperateLog carOperateLog = new  CarOperateLog();
			carOperateLog.setType(status);
			carOperateLog.setOperateNum(carOperate.getOperateNum());
			carOperateLog.setCreated(new Date());
			carOperateLog.setRemark(remark);
			if(maintenanceCosts != null){
				carOperateLog.setTakein(maintenanceCosts.multiply(new BigDecimal(-1)));
			}
			if(carPlan != null){
				if(StringUtils.isNotEmpty(carPlan.getMemberName())){
					carOperateLog.setMemberName(carPlan.getMemberName());
				}else{
					carOperateLog.setMemberName(carPlan.getOperateTo());
				}
				carOperateLog.setMemberId(carPlan.getMemberId());
				carOperateLog.setOperateName(carPlan.getButlerName());
				carOperateLog.setOperateId(carPlan.getButlerId());
			}
			if(member != null){
				carOperateLog.setOperateName(member.getSysuserName());
				carOperateLog.setOperateId(member.getSysuserId());
			}
			try {
				carOperateLogService.insertSelective(carOperateLog);
			} catch (Exception e) {
				logger.error("添加车辆日志错误insertCarLog--carOperatePlanId"+carOperate.getId());
			}
		}
	}

	@Override
	public Integer memberuseing(long id) {
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setId(id);
		carOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSEING.getValue());
		return tdCarOperatePlanMapper.updateByPrimaryKeySelective(carOperatePlan);
	}

	@Override
	public Integer platformuseing(long id) {
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setId(id);
		carOperatePlan.setOperateStatus(CarOperateEnum.status.PLATFORMUSEING.getValue());
		return tdCarOperatePlanMapper.updateByPrimaryKeySelective(carOperatePlan);
	}

	@Override
	public Integer memberuse(long id) {
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setId(id);
		carOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSE.getValue());
		return tdCarOperatePlanMapper.updateByPrimaryKeySelective(carOperatePlan);
	}

	@Override
	public Integer platformuse(long id) {
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setId(id);
		carOperatePlan.setOperateStatus(CarOperateEnum.status.PLATFORMUSE.getValue());
		return tdCarOperatePlanMapper.updateByPrimaryKeySelective(carOperatePlan);
	}

	@Override
	public CarOperatePlan selectByCarPlateNumAndStartedEnded(String carPlateNum,
			Date started,Date ended) {
		return tdCarOperatePlanMapper.selectByCarPlateNumAndStartedEnded(carPlateNum, started,ended);
	}
	
	@Override
	public CarOperatePlan selectByOperateIdAndStartedEnded(Long operateId,
			Date started) {
		return tdCarOperatePlanMapper.selectByOperateIdAndStartedEnded(operateId, started);
	}

	@Override
	public Integer updateStatusByOperateIdAndStarted(
			CarOperatePlan carOperatePlan) {
		return tdCarOperatePlanMapper.updateStatusByOperateIdAndStarted(carOperatePlan);
	}

	@Override
	public Integer getCarStatus(Long carOperateId) {
		//默认为车辆状态  待分配 整备中
		CarOperate carOperate = carOperateService.findById(carOperateId);
		int status = CarOperateEnum.status.STAY.getValue();
		if(carOperate != null){
			status = carOperate.getStatus();
			if(status == CarOperateEnum.status.REPAIRSING.getValue() ||
        			status == CarOperateEnum.status.READINESSING.getValue() ||
        			status == CarOperateEnum.status.OUTOFCONTACT.getValue()){
        		return status;
        	}
			//查询 执行中 平台运营的记录
			CarOperatePlan carOperatePlan = new CarOperatePlan();
			//carOperatePlan.setOperateId(carOperateId);
			carOperatePlan.setCarPlateNum(carOperate.getCarPlateNum());
			carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
			carOperatePlan.setMemberId(0L);
			CarOperatePlan carPlanStatus = tdCarOperatePlanMapper.selectCarStatus(carOperatePlan);
			if(carPlanStatus != null){
				status = CarOperateEnum.status.PLATFORMUSEING.getValue();
			}else{
				//会员使用中
				carOperatePlan = new CarOperatePlan();
				//carOperatePlan.setOperateId(carOperateId);
				carOperatePlan.setCarPlateNum(carOperate.getCarPlateNum());
				carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
				carOperatePlan.setMemberId(1L);
				carPlanStatus = tdCarOperatePlanMapper.selectCarStatus(carOperatePlan);
				if(carPlanStatus != null){
					status = CarOperateEnum.status.MEMBERUSEING.getValue();
				}
			}
		}
		
		//查询会员使用中状态
		//select * from td_car_operate_plan p where p.operate_id='426' and p.`status`=2 and p.member_id !=0;
		
		//查询平台使用中状态
		//select * from td_car_operate_plan p where p.operate_id='426' and p.`status`=2 and p.member_id =0;
		
		//如果都没有则为 车辆的状态
		return status;
	}

	@Override
	public Integer updateStatusByOperateIdAndEnded(CarOperatePlan carOperatePlan) {
		return tdCarOperatePlanMapper.updateStatusByOperateIdAndEnded(carOperatePlan);
	}

	@Override
	public List<CarOperatePlan> selectPlanTimeGreaterThanEndTime(CarOperatePlan carOperatePlan) {
		return tdCarOperatePlanMapper.selectPlanTimeGreaterThanEndTime(carOperatePlan);
	}

	@Override
	public List<CarOperatePlan> selectPlanTimeLessThanEndTime(CarOperatePlan carOperatePlan) {
		return tdCarOperatePlanMapper.selectPlanTimeLessThanEndTime(carOperatePlan);
	}
	
	/**
	 * 查询车型是否计算库存
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 下午5:04:02   
	* @return String
	 */
	private boolean isInfiniteStock(Long carTypeId){
		//查询车型是否计算库存
		CarType carType = carTypeService.findById(carTypeId);
		if(carType != null){
			Integer isInfiniteStock = carType.getIsInfiniteStock();
			//无限库存 0:不是；1:是
			if(isInfiniteStock != null && isInfiniteStock == 1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 计算限号后的库存总数
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 下午5:02:46   
	* @return String
	 */
	private long isRestriction(Long carTypeId,Date useCarStartTime, Date useCarEndTime,List<CarOperate> carOperateList,long store){
		CarType carType = carTypeService.findById(carTypeId);
		if(carType != null){
			Integer isRestriction = carType.getIsRestriction();
			if(isRestriction != null && isRestriction == 0){
				//这种车型不考虑限号的情况
				return store;
			}else{
				//限号
				BasicRestriction basicRestriction = new BasicRestriction();
				//目前只查北京
				basicRestriction.setCityId(2);
				Date startDate = new Date(useCarStartTime.getYear(), useCarStartTime.getMonth(), useCarStartTime.getDate());
				basicRestriction.setRestrictionsDate(startDate);
				//查询限号信息
				List<BasicRestriction> basicRestrictionList = basicRestrictionService.selectByCondition(basicRestriction);
				if(CollectionUtils.isNotEmpty(basicRestrictionList)){
					//取出第一条限号记录
					BasicRestriction restriction = basicRestrictionList.get(0);
					String restrictions = restriction.getRestrictions();
					//得到当天的限制号码
					String[] num = restrictions.split(",");
					for(String card : num){
						for(CarOperate car : carOperateList){
							//查询车辆状态
							int status = getCarStatus(car.getId());
							//查询状态是待分配和整备中的车辆
							if(status == CarOperateEnum.status.STAY.getValue() || status == CarOperateEnum.status.READINESSING.getValue()){
								String carPlateNum = car.getCarPlateNum();
								String lastNum = car.getCarPlateNum().substring(carPlateNum.length() - 1);
								//判断尾号是否是0-9，如果不是，将尾号设置为0
								boolean isNum = lastNum.matches("[0-9]+");  
								if(!isNum){
									lastNum = "0";
								}
								if(lastNum.endsWith(card)){
									--store;
								}
							}
						}
					}
				}
				
			}
		}
		return store;
	}

	@Override
	public List<CarOperatePlan> selectNotAvailableCar(CarOperatePlan carOperatePlan) {
		return tdCarOperatePlanMapper.selectNotAvailableCar(carOperatePlan);
	}

	/**
	 * 判断时间段内是否有库存
	 */
	@Override
	public boolean isStore(Long carTypeId, Date useCarStartTime, Date useCarEndTime) {
		this.logger.info("调用方法为----isStore");
		SqlSession session=null;
		//查询车型是否计算库存
		boolean flag = isInfiniteStock(carTypeId);
		if(flag){
			//是无限库存的情况，不需要去比较了，直接返回true
			this.logger.info("当前车型是无限库存---");
			return flag;
		}
		 try {
			 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			 	//判断全天24小时是否都有库存，如果都有  库存显示返回为true
				session=sqlSessionFactoryBean.getObject().openSession();
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				CarOperate carOperate = new CarOperate();
				carOperate.setCarTypeId(carTypeId);
				carOperate.setUseCarStartTime(useCarStartTime);
				carOperate.setReadyHour(threshold.getReadyTime()+2);//整备时间+送车时间2小时
				long store = 0;
				long storeAterRestriction = 0;
				//查询总库存
				List<CarOperate> carOperateList=session.selectList("com.hy.gcar.dao.CarOperateMapper.selectStoreTotal", carOperate);
				if(carOperateList != null){
					store = carOperateList.size();
					this.logger.info("不考虑限号的可用库存(排除了维修中、已失联、已预约)的情况----剩余库存为---"+store);
				}
				this.logger.info("车型ID为---"+carTypeId+"----的可用库存(排除了维修中、已失联、已预约)的情况为"+store);
				//例如开始时间是 8:30  则库存查询开始时间是9:00 ；小于08：00 开始时间8点
				this.logger.info("查询库存开始时间是----"+useCarStartTime);
				Calendar startCalendar = getMiniDate(sdf.format(useCarStartTime), false);
				useCarStartTime = startCalendar.getTime();
				Calendar startEndCalendar = getMaxDate(sdf.format(useCarStartTime), false);
				Date useCarStartEndTime = startEndCalendar.getTime();
				this.logger.info("查询库存开始时间规则化之后的时间是----"+useCarStartTime);
				this.logger.info("查询库存结束时间是----"+useCarEndTime);
				Calendar endCalendar = getMaxDate(sdf.format(useCarEndTime), false);
				useCarEndTime = endCalendar.getTime();
				this.logger.info("查询库存结束时间规则化之后的时间是----"+useCarEndTime);
				//useCarEndTime = DateUtils.addHours(useCarEndTime, threshold.getReadyTime()+2);
				
				//查询车型是否限号
				storeAterRestriction = isRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, store);
				this.logger.info("计算限号之后的库存情况----剩余库存---"+storeAterRestriction);
				
				//计算得到两个需要查询的时间段
				long count =(useCarEndTime.getTime()-useCarStartTime.getTime()) / (1000 * 60 * 60 * 24) +1;
				logger.info("循环次数"+count);	
				Date currentDate = useCarStartTime;//限号只考虑发起用车当天
				for (int i = 0; i < count; i++) {
					CarOperatePlan carOperatePlan = new CarOperatePlan();
					carOperatePlan.setStarted(useCarStartTime);
					carOperatePlan.setEnded(useCarStartEndTime);
					carOperatePlan.setCarTypeId(carTypeId);
					carOperatePlan.setReadyTime(threshold.getReadyTime()+2);
					//查询每一个小时段运营计划总数	   
					//List<CarOperatePlan> planList =
					Integer planSize = session.selectOne("com.hy.gcar.dao.CarOperatePlanMapper.selectCountByCarTypeIdAndStartedEnded", carOperatePlan);
					this.logger.info("查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd HH:mm:ss")+"的运营计划数量为---"+planSize);
					//判断运营计划数与库存总数量大小.如果是当前的情况：与限号之后的库存总数进行比较，如果不是当前，直接与总库存进行比较
					if(currentDate.getYear() == useCarStartTime.getYear() && currentDate.getMonth() ==useCarStartTime.getMonth() && currentDate.getDate() == useCarStartTime.getDate()){
						//表示是当天，需要考虑限号
						if(planSize>=storeAterRestriction){
							//发现给的一个时间段存在库存小于0的情况，接口返回数据false
							return false;
						}
					}else{
						//不是当天，不需要考虑限号的情况
						if(planSize>=store){
							//发现给的一个时间段存在库存小于0的情况，接口返回数据false
							return false;
						}
					}
					//循环结束之后日期都+1
					useCarStartTime=DateUtils.addDays(useCarStartTime, 1);
					useCarStartEndTime=DateUtils.addDays(useCarStartEndTime, 1);
					//useCarEndTime.setDate(useCarStartTime.getDate());
					this.logger.info("下次循环查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd HH:mm:ss")+"的运营计划数量为---"+planSize);
					this.logger.info("下次循环查询结束时段为"+DateUtils.formatDate(useCarEndTime, "yyyy-MM-dd HH:mm:ss")+"的运营计划数量为---"+planSize);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return true;
			
	}
	
	/**
	 * 判断时间段内是否有库存
	 */
	@Override
	public Long isStoreByHours(Long carTypeId, Date useCarStartTime, Date useCarEndTime) {
		this.logger.info("调用方法为----isStoreByHours");
		SqlSession session=null;
		//查询车型是否计算库存
		boolean flag = isInfiniteStock(carTypeId);
		if(flag){
			//是无限库存的情况，不需要去比较了，直接返回true
			this.logger.info("当前车型是无限库存---");
			return 8888L;
		}
		 try {
			 	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			 	//判断全天24小时是否都有库存，如果都有  库存显示返回为true
			 	//如果1天中有1小时 无库存，则返回false
			 	//Calendar startCalendar = getMiniDate(sdf.format(useCarStartTime), false);
			 	//Calendar endCalendar = getMaxDate(sdf.format(useCarEndTime), false);
			 //	useCarStartTime = startCalendar.getTime();
			 	//useCarEndTime = endCalendar.getTime();
				session=sqlSessionFactoryBean.getObject().openSession();
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				CarOperate carOperate = new CarOperate();
				carOperate.setCarTypeId(carTypeId);
				carOperate.setUseCarStartTime(useCarStartTime);
				carOperate.setReadyHour(threshold.getReadyTime()+2);//整备时间+送车时间2小时
				long store = 0;
				long storeAterRestriction = 0;
				//查询总库存
				List<CarOperate> carOperateList=session.selectList("com.hy.gcar.dao.CarOperateMapper.selectStoreTotal", carOperate);
				if(carOperateList != null){
					store = carOperateList.size();
					this.logger.info("不考虑限号的库存情况----剩余库存为---"+store);
				}
				this.logger.info("车型ID为---"+carTypeId+"----的总库存为"+store);
				//例如开始时间是 8:30  则库存查询开始时间是9:00 ；小于08：00 开始时间8点
				this.logger.info("查询库存开始时间是----"+useCarStartTime);
				//useCarStartTime = chechMinutes(useCarStartTime);
				this.logger.info("查询库存开始时间规则化之后的时间是----"+useCarStartTime);
				this.logger.info("查询库存结束时间是----"+useCarEndTime);
				//useCarEndTime = chechMinutes(useCarEndTime);
				this.logger.info("查询库存结束时间规则化之后的时间是----"+useCarEndTime);
				
				//查询车型是否限号
				storeAterRestriction = isRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, store);
				this.logger.info("计算限号之后的库存情况----剩余库存---"+storeAterRestriction);
				
					
				Date currentDate = useCarStartTime;//限号只考虑发起用车当天
				CarOperatePlan carOperatePlan = new CarOperatePlan();
				carOperatePlan.setStarted(useCarStartTime);
				carOperatePlan.setEnded(useCarEndTime);
				carOperatePlan.setCarTypeId(carTypeId);
				carOperatePlan.setReadyTime(threshold.getReadyTime()+2);
				//查询每一个小时段运营计划总数	   
				//List<CarOperatePlan> planList =
				Integer planSize = session.selectOne("com.hy.gcar.dao.CarOperatePlanMapper.selectCountByCarTypeIdAndStartedEnded", carOperatePlan);
				this.logger.info("查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd HH:mm")+"的运营计划数量为---"+planSize);
				//判断运营计划数与库存总数量大小.如果是当前的情况：与限号之后的库存总数进行比较，如果不是当前，直接与总库存进行比较
				/*if(currentDate.getYear() == useCarStartTime.getYear() && currentDate.getMonth() ==useCarStartTime.getMonth() && currentDate.getDate() == useCarStartTime.getDate()){
					//表示是当天，需要考虑限号
					if(planSize>=storeAterRestriction){
						//发现给的一个时间段存在库存小于0的情况，接口返回数据false
						return 0;
					}
				}else{*/
					//不是当天，不需要考虑限号的情况
					if(planSize>=store){
						//发现给的一个时间段存在库存小于0的情况，接口返回数据false
						return store-planSize;
					}
				//}
					return store-planSize;
					
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return 0L;
			
	}
	
	/**
	 * 判断时间段内是否有库存(延时查看是否对之后的排期造成影响用)
	 */
	@Override
	public boolean isStoreForDelay(Long carTypeId, Date useCarStartTime, Date useCarEndTime) {
		this.logger.info("调用方法为----isStoreForDelay");
		SqlSession session=null;
		//查询车型是否计算库存
		boolean flag = isInfiniteStock(carTypeId);
		if(flag){
			//是无限库存的情况，不需要去比较了，直接返回true
			this.logger.info("当前车型是无限库存---");
			return flag;
		}
		 try {
			 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			 	//判断全天24小时是否都有库存，如果都有  库存显示返回为true
				session=sqlSessionFactoryBean.getObject().openSession();
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				CarOperate carOperate = new CarOperate();
				carOperate.setCarTypeId(carTypeId);
				carOperate.setUseCarStartTime(useCarStartTime);
				carOperate.setReadyHour(threshold.getReadyTime()+2);//整备时间+送车时间2小时
				long store = 0;
				//查询总库存
				List<CarOperate> carOperateList=session.selectList("com.hy.gcar.dao.CarOperateMapper.selectStoreTotal", carOperate);
				if(carOperateList != null){
					store = carOperateList.size();
					this.logger.info("不考虑限号的可用库存(排除了维修中、已失联、已预约)的情况----剩余库存为---"+store);
				}
				this.logger.info("车型ID为---"+carTypeId+"----的可用库存(排除了维修中、已失联、已预约)的情况为"+store);
				//例如开始时间是 8:30  则库存查询开始时间是9:00 ；小于08：00 开始时间8点
				this.logger.info("查询库存开始时间是----"+useCarStartTime);
				Calendar startCalendar = getMiniDate(sdf.format(useCarStartTime), false);
				useCarStartTime = startCalendar.getTime();
				Calendar startEndCalendar = getMaxDate(sdf.format(useCarStartTime), false);
				Date useCarStartEndTime = startEndCalendar.getTime();
				this.logger.info("查询库存开始时间规则化之后的时间是----"+useCarStartTime);
				this.logger.info("查询库存结束时间是----"+useCarEndTime);
				Calendar endCalendar = getMaxDate(sdf.format(useCarEndTime), false);
				useCarEndTime = endCalendar.getTime();
				this.logger.info("查询库存结束时间规则化之后的时间是----"+useCarEndTime);
				
				
				//计算得到两个需要查询的时间段
				long count =(useCarEndTime.getTime()-useCarStartTime.getTime()) / (1000 * 60 * 60 * 24) +1;
						
				for (int i = 0; i < count; i++) {
					CarOperatePlan carOperatePlan = new CarOperatePlan();
					carOperatePlan.setStarted(useCarStartTime);
					carOperatePlan.setEnded(useCarStartEndTime);
					carOperatePlan.setCarTypeId(carTypeId);
					carOperatePlan.setReadyTime(threshold.getReadyTime()+2);
					//查询每一个小时段运营计划总数	   
					//List<CarOperatePlan> planList =
					Integer planSize = session.selectOne("com.hy.gcar.dao.CarOperatePlanMapper.selectCountByCarTypeIdAndStartedEnded", carOperatePlan);
					this.logger.info("查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd")+"的运营计划数量为---"+planSize);
					
					if(planSize>=store){
						//发现给的一个时间段存在库存小于0的情况，接口返回数据false
						return false;
					}
					
					//循环结束之后日期都+1
					useCarStartTime=DateUtils.addDays(useCarStartTime, 1);
					useCarStartEndTime=DateUtils.addDays(useCarStartEndTime, 1);
					//useCarEndTime.setDate(useCarStartTime.getDate());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return true;
			
	}
	
	/**
	 * 获取本月最小的日期  例：2016-8-01 00:00:00
	 * flag true设置date天为最小， false不设置天
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 上午9:52:26   
	* @return String
	 */
	private Calendar getMiniDate(String dateStr,boolean flag) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if(flag){
			// 设置日期为本月最小日期
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		return calendar;
	}
	
	/**
	 * 获取本月最大的日期  例：2016-8-31 23:59:59
	 *  flag true设置date天为最大， false不设置天
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 上午9:52:26   
	* @return String
	 */
	private Calendar getMaxDate(String dateStr,boolean flag) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if(flag){
			// 设置日期为本月最大日期
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		return calendar;
	}
	
	/**
	 * 查询限号没限号
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-7 下午5:02:46   
	* @return String
	 */
	private boolean searchRestriction(Long carTypeId,Date useCarStartTime, Date useCarEndTime,List<CarOperate> carOperateList,long store){
		CarType carType = carTypeService.findById(carTypeId);
		if(carType != null){
			Integer isRestriction = carType.getIsRestriction();
			if(isRestriction != null && isRestriction == 0){
				//这种车型不考虑限号的情况
				return false;
			}else{
				//限号
				BasicRestriction basicRestriction = new BasicRestriction();
				//目前只查北京
				basicRestriction.setCityId(2);
				Date startDate = new Date(useCarStartTime.getYear(), useCarStartTime.getMonth(), useCarStartTime.getDate());
				basicRestriction.setRestrictionsDate(startDate);
				//查询限号信息
				List<BasicRestriction> basicRestrictionList = basicRestrictionService.selectByCondition(basicRestriction);
				if(CollectionUtils.isNotEmpty(basicRestrictionList)){
					//取出第一条限号记录
					BasicRestriction restriction = basicRestrictionList.get(0);
					String restrictions = restriction.getRestrictions();
					//得到当天的限制号码
					String[] num = restrictions.split(",");
					for(String card : num){
						for(CarOperate car : carOperateList){
							//查询车辆状态
							int status = getCarStatus(car.getId());
							//查询状态是待分配和整备中的车辆
							if(status == CarOperateEnum.status.STAY.getValue() || status == CarOperateEnum.status.READINESSING.getValue()){
								String carPlateNum = car.getCarPlateNum();
								String lastNum = car.getCarPlateNum().substring(carPlateNum.length() - 1);
								//判断尾号是否是0-9，如果不是，将尾号设置为0
								boolean isNum = lastNum.matches("[0-9]+");  
								if(!isNum){
									lastNum = "0";
								}
								if(lastNum.endsWith(card)){
									return true;
								}
							}
						}
					}
				}
				
			}
		}
		return false;
	}
	
 	/**
  	 * 判断时间段内 是否有库存
  	* @Description: 
  	* @author:JIAZHIPENG  
  	* @time:2016-12-7 下午5:00:55   
  	* @return 1有库存 2 无库存 3限号无库存
  	 */
	@Override
	public int searchStore(Long carTypeId, Date useCarStartTime, Date useCarEndTime,String powerUsedId) {
		this.logger.info("调用方法为----searchStore");
		SqlSession session=null;
		//查询车型是否计算库存
		boolean flag = isInfiniteStock(carTypeId);
		if(flag){
			//是无限库存的情况，不需要去比较了，直接返回true
			this.logger.info("当前车型是无限库存---");
			return 1;
		}
		 try {
			 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			 	//判断全天24小时是否都有库存，如果都有  库存显示返回为true
				session=sqlSessionFactoryBean.getObject().openSession();
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				CarOperate carOperate = new CarOperate();
				carOperate.setCarTypeId(carTypeId);
				carOperate.setUseCarStartTime(useCarStartTime);
				carOperate.setReadyHour(threshold.getReadyTime()+2);//整备时间+送车时间2小时
				long store = 0;
				long storeAterRestriction = 0;
				//查询总库存
				List<CarOperate> carOperateList=session.selectList("com.hy.gcar.dao.CarOperateMapper.selectStoreTotal", carOperate);
				if(carOperateList != null){
					store = carOperateList.size();
					this.logger.info("不考虑限号的可用库存(排除了维修中、已失联、已预约)的情况----剩余库存为---"+store);
				}
				this.logger.info("车型ID为---"+carTypeId+"----的可用库存(排除了维修中、已失联、已预约)的情况为"+store);
				//例如开始时间是 8:30  则库存查询开始时间是9:00 ；小于08：00 开始时间8点
				this.logger.info("查询库存开始时间是----"+useCarStartTime);
				Calendar startCalendar = getMiniDate(sdf.format(useCarStartTime), false);
				useCarStartTime = startCalendar.getTime();
				this.logger.info("查询库存开始时间规则化之后的时间是----"+useCarStartTime);
				this.logger.info("查询库存结束时间是----"+useCarEndTime);
				Calendar endCalendar = getMaxDate(sdf.format(useCarEndTime), false);
				useCarEndTime = endCalendar.getTime();
				this.logger.info("查询库存结束时间规则化之后的时间是----"+useCarEndTime);
				//useCarEndTime = DateUtils.addHours(useCarEndTime, threshold.getReadyTime()+2);
				
				//查询车型是否限号
				storeAterRestriction = isRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, store);
				this.logger.info("计算限号之后的库存情况----剩余库存---"+storeAterRestriction);
				
				//计算得到两个需要查询的时间段
				//long count =(useCarEndTime.getTime()-useCarStartTime.getTime()) / (1000 * 60 * 60 * 24) +1;
						
				//for (int i = 0; i < count; i++) {
					CarOperatePlan carOperatePlan = new CarOperatePlan();
					carOperatePlan.setStarted(useCarStartTime);
					carOperatePlan.setEnded(useCarEndTime);
					carOperatePlan.setCarTypeId(carTypeId);
					carOperatePlan.setReadyTime(threshold.getReadyTime()+2);
					//查询每一个小时段运营计划总数	   
					//List<CarOperatePlan> planList =
					Integer size = session.selectOne("com.hy.gcar.dao.CarOperatePlanMapper.selectCountByCarTypeIdAndStartedEnded", carOperatePlan);
					this.logger.info("查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd")+"的运营计划数量为---"+size);
					//判断运营计划数与库存总数量大小.如果是当前的情况：与限号之后的库存总数进行比较，如果不是当前，直接与总库存进行比较
					//查询用车任务
					PowerUsed powerUsed = powerUsedService.findById(Long.decode(powerUsedId));
					if(powerUsed != null){
						Date carUsedTime = powerUsed.getCarUsedTime();
						Date carReturnTime = powerUsed.getCarReturnTime();
						if(carUsedTime.before(useCarStartTime) && carReturnTime.after(useCarEndTime)){
							if(size >= store){
								return 1;
							}
							if(size>=storeAterRestriction){
								boolean isRestriction = searchRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, storeAterRestriction);
								if(isRestriction){
									return 3;
								}else{
									return 1;
								}
							}
							return 1;
						}
						if((carUsedTime.getYear() == useCarStartTime.getYear() &&
							carUsedTime.getMonth() == useCarStartTime.getMonth() &&
							carUsedTime.getDate() == useCarStartTime.getDate()) ||
							(carUsedTime.getYear() == useCarEndTime.getYear() &&
							carUsedTime.getMonth() == useCarEndTime.getMonth() &&
							carUsedTime.getDate() == useCarEndTime.getDate())){
							if(size >= store){
								return 1;
							}
							if(size>=storeAterRestriction){
								boolean isRestriction = searchRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, storeAterRestriction);
								if(isRestriction){
									return 3;
								}else{
									return 1;
								}
							}
							return 1;
						}
						if((carReturnTime.getYear() == useCarStartTime.getYear() &&
							carReturnTime.getMonth() == useCarStartTime.getMonth() &&
							carReturnTime.getDate() == useCarStartTime.getDate()) ||
							(carReturnTime.getYear() == useCarEndTime.getYear() &&
							carReturnTime.getMonth() == useCarEndTime.getMonth() &&
							carReturnTime.getDate() == useCarEndTime.getDate())){
							if(size >= store){
								return 1;
							}
							if(size>=storeAterRestriction){
								boolean isRestriction = searchRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, storeAterRestriction);
								if(isRestriction){
									return 3;
								}else{
									return 1;
								}
							}
							return 1;
						}	
					}
					if(size >= store){
						return 2;
					}else if(size>=storeAterRestriction){
						boolean isRestriction = searchRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, storeAterRestriction);
						if(isRestriction){
							return 3;
						}else{
							return 2;
						}
					}
					
					//循环结束之后日期都+1
					//useCarStartTime=DateUtils.addDays(useCarStartTime, 1);
					//useCarEndTime=DateUtils.addDays(useCarEndTime, 1);
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return 1;
			
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<Map<String, Object>> getStoreByTime(Long carTypeId, Date useCarStartTime, Date useCarEndTime) {
		this.logger.info("调用方法为----getStoreByTime");
		SqlSession session=null;
		List<Map<String, Object>> returnList = new ArrayList<>();
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		//查询车型是否计算库存
		boolean flag = isInfiniteStock(carTypeId);
		if(flag){
			//是无限库存的情况，不需要去比较了，直接返回true
			this.logger.info("当前车型是无限库存---");
			returnMap.put("carTypeId", "该车型是无限库存，不需要考虑实际库存");
			returnList.add(returnMap);
			return returnList;
		}
		 try {
				session=sqlSessionFactoryBean.getObject().openSession();
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				CarOperate carOperate = new CarOperate();
				carOperate.setCarTypeId(carTypeId);
				carOperate.setUseCarStartTime(useCarStartTime);
				carOperate.setReadyHour(threshold.getReadyTime()+2);//整备时间+送车时间2小时
				long store = 0;
				long storeAterRestriction = 0;
				//查询总库存
				List<CarOperate> carOperateList=session.selectList("com.hy.gcar.dao.CarOperateMapper.selectStoreTotal", carOperate);
				if(carOperateList != null){
					store = carOperateList.size();
					this.logger.info("不考虑限号的库存情况----剩余可用库存为---"+store);
				}
				this.logger.info("车型ID为---"+carTypeId+"----的可用总库存为"+store);
				//例如开始时间是 8:30  则库存查询开始时间是9:00 ；小于08：00 开始时间8点
				this.logger.info("查询库存开始时间是----"+sdf.format(useCarStartTime));
				useCarStartTime = chechMinutes(useCarStartTime);
				this.logger.info("查询库存开始时间规则化之后的时间是----"+sdf.format(useCarStartTime));
				this.logger.info("查询库存结束时间是----"+sdf.format(useCarEndTime));
				useCarEndTime = chechMinutes(useCarEndTime);
				this.logger.info("查询库存结束时间规则化之后的时间是----"+useCarEndTime);
				useCarEndTime = DateUtils.addHours(useCarEndTime, threshold.getReadyTime()+2);
				
				//查询车型是否限号
				storeAterRestriction = isRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, store);
				this.logger.info("计算限号之后的库存情况----剩余可用库存---"+storeAterRestriction);
				
				//计算得到两个需要查询的时间段
				long count =(useCarEndTime.getTime()-useCarStartTime.getTime()) / (1000 * 60 * 60);
				this.logger.info("需要循环的总次数为----"+count);		
				Date currentDate = useCarStartTime;//限号只考虑发起用车当天
				for (int i = 0; i < count; i++) {
					useCarEndTime = DateUtils.addHours(useCarStartTime, 1);
					CarOperatePlan carOperatePlan = new CarOperatePlan();
					carOperatePlan.setStarted(useCarStartTime);
					carOperatePlan.setEnded(useCarEndTime);
					carOperatePlan.setCarTypeId(carTypeId);
					carOperatePlan.setReadyTime(threshold.getReadyTime()+2);
					//查询每一个小时段运营计划总数	          
					Integer planSize = session.selectOne("com.hy.gcar.dao.CarOperatePlanMapper.selectCountByCarTypeIdAndStartedEnded", carOperatePlan);
					this.logger.info("查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd HH:mm")+"的运营计划数量为---"+planSize);
					//判断运营计划数与库存总数量大小.如果是当前的情况：与限号之后的库存总数进行比较，如果不是当前，直接与总库存进行比较
					if(currentDate.getYear() == useCarStartTime.getYear() && currentDate.getMonth() ==useCarStartTime.getMonth() && currentDate.getDate() == useCarStartTime.getDate()){
						//表示是当天，需要考虑限号
						returnMap.put(sdf.format(useCarStartTime), storeAterRestriction-planSize);
					}else{
						//不是当天，不需要考虑限号的情况
						returnMap.put(sdf.format(useCarStartTime), store-planSize);
					}
					useCarStartTime=useCarEndTime;
				}
				returnList.add(returnMap);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return returnList;
			
	}


	@Override
	public void updateCarOperatePlanByPowerPowerUsedId(CarOperatePlan carOperatePlan) {
		this.tdCarOperatePlanMapper.updateCarOperatePlanByPowerPowerUsedId(carOperatePlan);

	}

	/**
	 * 判断分钟，精确到小时，如果是大于30分钟，则算入下一个小时，如果小于30分钟，则算上一个小时(例如，时间是2016-11-10 10:55， 则算2016-11-10 11；时间是2016-11-10 10:25，则算2016-11-10 10)
	 * @param date
	 * @return
	 */
	private Date chechMinutes(Date date) {
		if(date==null){
			return null;
		}
		int start = Integer.parseInt(DateUtils.getMinutes(date));
		if(start>=30){
			date=DateUtils.addMinutes(date, 30);
		}else{
			date=DateUtils.addMinutes(date, -start);
		}
		return date;
	}

	@Override
	public List<CarOperatePlan> selectCarOperatePlanByStarted(CarOperatePlan carOperatePlan) {
		return tdCarOperatePlanMapper.selectCarOperatePlanByStarted(carOperatePlan);
	}

	@Override
	public void updateCarOperatePlanByCancel(List<Long> carOperatePlanIds) {
		tdCarOperatePlanMapper.updateCarOperatePlanByCancel(carOperatePlanIds);
		
	}

	@Override
	public void updateClearCarOperateId(List<Long> carOperatePlanIds) {
		tdCarOperatePlanMapper.updateClearCarOperateId(carOperatePlanIds);
	}

	@Override
	public List<CarOperatePlan> selectCarOperateStateByNow(List<Long> carOperateIds) {
		
		return 	tdCarOperatePlanMapper.selectCarOperateStateByNow(carOperateIds);
	}

	@Override
	public List<CarOperatePlan> selectCarPlanNumAndpowerUsedId(
			CarOperatePlan carOperatePlan) {
		// TODO Auto-generated method stub
		return tdCarOperatePlanMapper.selectCarPlanNumAndpowerUsedId(carOperatePlan);
	}

	@Override
	public Integer updateStatusByOperateIdAndStartedForPlatform(CarOperatePlan carOperatePlan) {
		
		return this.tdCarOperatePlanMapper.updateStatusByOperateIdAndStartedForPlatform(carOperatePlan);
	}

	@Override
	public Integer updateStatusByOperateIdAndEndedForPlatform(CarOperatePlan carOperatePlan) {
		
		return this.tdCarOperatePlanMapper.updateStatusByOperateIdAndEndedForPlatform(carOperatePlan);
	}

	@Override
	public Integer updateStatusByOperateIdStartedAndEndForPlatform(CarOperatePlan carOperatePlan) {
		
		return this.tdCarOperatePlanMapper.updateStatusByOperateIdStartedAndEndForPlatform(carOperatePlan);
	}
	
	@Override
	public Integer updateStatusByOperateIdStartedAndEnd(CarOperatePlan carOperatePlan) {
		
		return this.tdCarOperatePlanMapper.updateStatusByOperateIdStartedAndEnd(carOperatePlan);
	}

	@Override
	public CarOperatePlan selectoutofcontactByOperateIdAndStartedEnded(Long operateId) {
		return tdCarOperatePlanMapper.selectoutofcontactByOperateIdAndStartedEnded(operateId);
	}

	@Override
	public CarOperatePlan selectAppointmentByOperateId(Long operateId) {
		return tdCarOperatePlanMapper.selectAppointmentByOperateId(operateId);
	}
	
	@Override
	public List<CarOperatePlan> selectCarPlanByOperateIdStartedAndEndForPlatform(CarOperatePlan carOperatePlan) {
		
		return tdCarOperatePlanMapper.selectCarPlanByOperateIdStartedAndEndForPlatform(carOperatePlan);
	}
	
	/**
	 * 判断时间段内是否有库存
	 * 是否有限号
	 */
	@Override
	public int isStoreByRestriction(Long carTypeId, Date useCarStartTime, Date useCarEndTime) {
		
		SqlSession session=null;
		//查询车型是否计算库存
		boolean flag = isInfiniteStock(carTypeId);
		if(flag){
			//是无限库存的情况，不需要去比较了，直接返回true
			this.logger.info("当前车型是无限库存---");
			return 1;
		}
		 try {
			 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			 	//判断全天24小时是否都有库存，如果都有  库存显示返回为true
				session=sqlSessionFactoryBean.getObject().openSession();
				BasicThreshold threshold = basicThresholdService.selectBasicThreshold();
				CarOperate carOperate = new CarOperate();
				carOperate.setCarTypeId(carTypeId);
				carOperate.setUseCarStartTime(useCarStartTime);
				carOperate.setReadyHour(threshold.getReadyTime()+2);//整备时间+送车时间2小时
				long store = 0;
				long storeAterRestriction = 0;
				//查询总库存
				List<CarOperate> carOperateList=session.selectList("com.hy.gcar.dao.CarOperateMapper.selectStoreTotal", carOperate);
				if(carOperateList != null){
					store = carOperateList.size();
					this.logger.info("不考虑限号的可用库存(排除了维修中、已失联、已预约)的情况----剩余库存为---"+store);
				}
				this.logger.info("车型ID为---"+carTypeId+"----的可用库存(排除了维修中、已失联、已预约)的情况为"+store);
				//例如开始时间是 8:30  则库存查询开始时间是9:00 ；小于08：00 开始时间8点
				this.logger.info("查询库存开始时间是----"+useCarStartTime);
				Calendar startCalendar = getMiniDate(sdf.format(useCarStartTime), false);
				useCarStartTime = startCalendar.getTime();
				this.logger.info("查询库存开始时间规则化之后的时间是----"+useCarStartTime);
				this.logger.info("查询库存结束时间是----"+useCarEndTime);
				Calendar endCalendar = getMaxDate(sdf.format(useCarEndTime), false);
				useCarEndTime = endCalendar.getTime();
				this.logger.info("查询库存结束时间规则化之后的时间是----"+useCarEndTime);
				//useCarEndTime = DateUtils.addHours(useCarEndTime, threshold.getReadyTime()+2);
				
				//查询车型是否限号
				storeAterRestriction = isRestriction(carTypeId, useCarStartTime, useCarEndTime, carOperateList, store);
				this.logger.info("计算限号之后的库存情况----剩余库存---"+storeAterRestriction);
				
				//计算得到两个需要查询的时间段
				long count =(useCarEndTime.getTime()-useCarStartTime.getTime()) / (1000 * 60 * 60 * 24) +1;
						
				Date currentDate = useCarStartTime;//限号只考虑发起用车当天
				for (int i = 0; i < count; i++) {
					CarOperatePlan carOperatePlan = new CarOperatePlan();
					carOperatePlan.setStarted(useCarStartTime);
					carOperatePlan.setEnded(useCarEndTime);
					carOperatePlan.setCarTypeId(carTypeId);
					carOperatePlan.setReadyTime(threshold.getReadyTime()+2);
					//查询每一个小时段运营计划总数	   
					//List<CarOperatePlan> planList =
					Integer planSize = session.selectOne("com.hy.gcar.dao.CarOperatePlanMapper.selectCountByCarTypeIdAndStartedEnded", carOperatePlan);
					this.logger.info("查询开始时段为"+DateUtils.formatDate(useCarStartTime, "yyyy-MM-dd")+"的运营计划数量为---"+planSize);
					//判断运营计划数与库存总数量大小.如果是当前的情况：与限号之后的库存总数进行比较，如果不是当前，直接与总库存进行比较
					if(currentDate.getYear() == useCarStartTime.getYear() && currentDate.getMonth() ==useCarStartTime.getMonth() && currentDate.getDate() == useCarStartTime.getDate()){
						if(planSize >= store){
							return 2;
						}
						//表示是当天，需要考虑限号
						if(planSize>=storeAterRestriction){
							if(store - storeAterRestriction > 0){
								return 3;
							}
							//发现给的一个时间段存在库存小于0的情况，接口返回数据false
							return 2;
						}
					}else{
						//不是当天，不需要考虑限号的情况
						if(planSize>=store){
							//发现给的一个时间段存在库存小于0的情况，接口返回数据false
							return 2;
						}
					}
					//循环结束之后日期都+1
					useCarStartTime=DateUtils.addDays(useCarStartTime, 1);
					useCarEndTime=DateUtils.addDays(useCarEndTime, 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
			return 1;
			
	}
}
