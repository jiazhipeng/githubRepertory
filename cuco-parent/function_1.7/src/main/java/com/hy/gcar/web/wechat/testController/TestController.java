package com.hy.gcar.web.wechat.testController;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.ButlerTaskType;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.dao.ButlerTaskMapper;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.entity.PowerUsedCost;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.powerUserd.PowerUsedCostLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.service.test.TestService;

@Controller
@RequestMapping("/wechat/test")
public class TestController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	//首先查询涛哥插入的用车记录
	@Autowired
	private PowerUsedService powerUsedService;
	@Autowired
	private ButlerTaskService butlerTaskService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private BasicNoticeService basicNoticeService;
	@Autowired
	private ButlerTaskMapper butlerTaskMapper;
	@Autowired
	private CarOperateService carOperateService;
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	@Autowired
	private PowerUsedCostService powerUsedCostService;
	@Autowired
	private ButlerTaskStatusLogService butlerTaskStatusLogService;
	@Autowired
	PowerUsedCostLogService powerUsedCostLogService;
	@Autowired
	CarOperateLogService carOperateLogService;
	@Autowired
	TestService testService;
	
	/**
	 * 通过用车记录主表创建用车任务，以及运营计划数据(此方法主要是创建老数据)
	 * @return
	 */
	@RequestMapping(value="/createDataCompeleted")
	public String createData(PowerUsed powerUsed){
		this.logger.info("根据用车记录主生成一些关联记录信息开始---");
		try {
			//首先根据用车记录生成任务表
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setCarTypeId(powerUsed.getCarTypeId());
			//查询用户信息
			Member member = memberService.findMemberByID(powerUsed.getMemberId());
			//根据用户Id查询用户权益
			MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
			
			//插入用车金额记录
			this.insertPowerUsedCost(powerUsed, new BigDecimal(0), new CouponInfo());
			
			if( 3== powerUsed.getUsedStatus()){
				//插入完成的数据
				//插入送车任务
				butlerTask.setMemberId(powerUsed.getMemberId()+"");
				butlerTask.setMemberItemId(memberItemShare.getMemberItemId());
				butlerTask.setMemberMobile(member.getMobile());
				butlerTask.setMemberName(member.getName());
				butlerTask.setModifierType(0);
				butlerTask.setPowerUsedId(powerUsed.getId());
				butlerTask.setPlanTime(powerUsed.getCarUsedTime());
				butlerTask.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
				butlerTask.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
				butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
				butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
				//设置管家负责人
				butlerTask = this.setOperater(butlerTask, 1);
				//设置客服负责人
				this.setCustomer(butlerTask);
				//创建送车任务
				butlerTask.setCreated(new Date());
				butlerTask.setLasttimeModify(butlerTask.getCreated());
				butlerTask.setCompleteTime(new Date());
				this.butlerTaskMapper.insertSelective(butlerTask);
				ButlerTask sendtask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
				//插入任务完成日志
				this.createButlerTaskStatusLog(sendtask);
				//插入车辆运营日志
				this.insertCarRecord(sendtask, CarOperatePlanEnum.status.SUCCESS.getValue());
				
				ButlerTask butlerTask2 = new ButlerTask();
				//创建还车任务
				butlerTask2.setMemberId(powerUsed.getMemberId()+"");
				butlerTask2.setMemberItemId(memberItemShare.getMemberItemId());
				butlerTask2.setMemberMobile(member.getMobile());
				butlerTask2.setMemberName(member.getName());
				butlerTask2.setModifierType(0);
				butlerTask2.setPowerUsedId(powerUsed.getId());
				butlerTask2 .setPlanTime(powerUsed.getCarReturnTime());
				butlerTask2.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
				butlerTask2.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
				butlerTask2.setType(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex());
				butlerTask2.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
				this.setOperater(butlerTask2, 2);
				butlerTask2.setCreated(new Date());
				butlerTask2.setLasttimeModify(butlerTask2.getCreated());
				butlerTask2.setCompleteTime(new Date());
				this.butlerTaskMapper.insertSelective(butlerTask2);
				ButlerTask gettask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
				//插入任务完成日志
				this.createButlerTaskStatusLog(gettask);
				
			}else if( 6== powerUsed.getUsedStatus()){
				//插入完成的数据
				//插入送车任务
				butlerTask.setMemberId(powerUsed.getMemberId()+"");
				butlerTask.setMemberItemId(memberItemShare.getMemberItemId());
				butlerTask.setMemberMobile(member.getMobile());
				butlerTask.setMemberName(member.getName());
				butlerTask.setModifierType(0);
				butlerTask.setPowerUsedId(powerUsed.getId());
				butlerTask.setPlanTime(powerUsed.getCarUsedTime());
				butlerTask.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
				butlerTask.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
				butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
				butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
				//设置管家负责人
				butlerTask = this.setOperater(butlerTask, 1);
				//设置客服负责人
				this.setCustomer(butlerTask);
				//创建送车任务
				butlerTask.setCreated(new Date());
				butlerTask.setLasttimeModify(butlerTask.getCreated());
				butlerTask.setCompleteTime(new Date());
				this.butlerTaskMapper.insertSelective(butlerTask);
				ButlerTask sendtask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
				//插入任务完成日志
				this.createButlerTaskStatusLog(sendtask);
				//插入车辆运营日志
				this.insertCarRecord(sendtask, CarOperatePlanEnum.status.USEING.getValue());
				
				ButlerTask butlerTask2 = new ButlerTask();
				//创建还车任务
				butlerTask2.setMemberId(powerUsed.getMemberId()+"");
				butlerTask2.setMemberItemId(memberItemShare.getMemberItemId());
				butlerTask2.setMemberMobile(member.getMobile());
				butlerTask2.setMemberName(member.getName());
				butlerTask2.setModifierType(0);
				butlerTask2.setPowerUsedId(powerUsed.getId());
				butlerTask2 .setPlanTime(powerUsed.getCarReturnTime());
				butlerTask2.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
				butlerTask2.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
				butlerTask2.setType(ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex());
				butlerTask2.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_GET_FOR_A_CAR.getIndex());
				this.setOperater(butlerTask2, 2);
				butlerTask2.setCreated(new Date());
				butlerTask2.setLasttimeModify(butlerTask2.getCreated());
				butlerTask2.setCompleteTime(new Date());
				this.butlerTaskMapper.insertSelective(butlerTask2);
			}
			
			
		} catch (Exception e) {
			this.logger.error("插入数据失败-----");
			e.printStackTrace();
		}
		
		return null;
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
	
	
	private void insertPowerUsedCost(PowerUsed powerUsed, BigDecimal couponPrice,
			CouponInfo couponInfo) throws Exception {
		//插入用车金额记录
		PowerUsedCost powerUsedCost= new PowerUsedCost();
		if(null!=powerUsed.getCouponInfoId()){
			powerUsedCost.setCouponId(powerUsed.getCouponInfoId());
			powerUsedCost.setCouponName(couponInfo.getCouponName());
			powerUsedCost.setCouponPrice(couponPrice);;
		}
		powerUsedCost.setPowerUsedId(powerUsed.getId());
		this.powerUsedCostService.insertSelective(powerUsedCost);
	}
	
	private void createButlerTaskStatusLog(ButlerTask butlerTask){
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		Member member = new Member();
		member.setSysuserId(butlerTask.getOperaterId());
		member = memberService.selectBySysUserId(member);
		butlerTaskStatusLog.setModifierType(1);
		butlerTaskStatusLog.setModifierId(butlerTask.getOperaterId());
		butlerTaskStatusLog.setModifier(butlerTask.getOperaterName());
		butlerTaskStatusLog.setModifierMobile(member.getMobile());
		butlerTaskStatusLog.setRemark("手动插入完成");
		butlerTaskStatusLog.setStatus(butlerTask.getStatus());
		butlerTaskStatusLog.setTaskId(butlerTask.getId());
		this.butlerTaskStatusLogService.createButlerTaskStatusLog(butlerTaskStatusLog);
	}
	
	@RequestMapping(value="/readExcelTest")
	public void readExcelTest(){
		 try {  
			 logger.info("====读取excel开始====================================================================================");
			 List<Map<String, Object>> lists = ReadExcel.exportListFromExcel(new File("/hycx/bak/importcardata/td_power_used.xls"), 0);  
		        System.out.println(lists);
		        for (Map<String, Object> map : lists) {
		        	PowerUsed powerUsed = new PowerUsed();
					for (String key:map.keySet()) {
//						System.out.println(key);
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
					powerUsedService.insertSelectiveReadExcel(powerUsed);
					
					ButlerTask butlerTask = testService.createData(powerUsed);
					carOperateLogService.autoCreateUseCarLog(powerUsed, butlerTask);
					powerUsedCostLogService.deductionOfFare(butlerTask, powerUsed);
				}
		        logger.info("====读取excel结束====================================================================================");
		          
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		
		
	}
	
}
