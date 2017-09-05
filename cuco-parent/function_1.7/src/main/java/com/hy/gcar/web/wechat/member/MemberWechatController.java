package com.hy.gcar.web.wechat.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.common.utils.DateUtils;
import com.hy.common.utils.StringUtils;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.entity.BasicOperationType;
import com.hy.gcar.entity.BasicRestriction;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperateLog;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.ChargeOrder;
import com.hy.gcar.entity.ChargeOrderLog;
import com.hy.gcar.entity.ChargeReward;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.basicOperationType.BasicOperationTypeService;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.chargeReward.ChargeRewardService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.utils.ResultUtils;

@Controller
@RequestMapping("/wechat/member")
public class MemberWechatController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	NationService nationService;
	
	@Autowired
	private MemberItemService memberItemService;
	
	@Autowired
	private ChargeRewardService chargeRewardService;
	
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	
	@Autowired
	private CarOperateLogService carOperateLogService;
	
	@Autowired
	private CarOperateService carOperateService;
	
	@Autowired
	private CarOperatePlanService carOperatePlanService;
	
	@Autowired
	private PowerUsedService powerUsedService;
	
	@Autowired
	private BasicNoticeService basicNoticeService;
	
	@Autowired
	private BasicOperationTypeService basicOperationTypeService;
	
	@Autowired
	private BasicRestrictionService  basicRestrictionService;
	
	@Autowired
	private CarTypeService carTypeService;
	
	private static final String SHOW_MEMBERINFO_JSP_URL = "gcar/member/memberInfo";
	
	/**
	 * 
	 * @Title: showMemberInfo  
	 * @Description: 进入会员详情页面
	 * @param:@param member
	 * @param:@param map
	 * @param:@return    
	 * @return:String 
	 * @createTime:2016年8月13日 下午1:47:36
	 * @throws
	 */
	@RequestMapping("show/memberInfo")
	public String showMemberInfo(Member member,ModelMap map){
		logger.info("进入会员详情页面....");
		
		try {
			Member m = memberService.findMemberByID(member.getId());
			if(m == null){
				return SHOW_MEMBERINFO_JSP_URL;
			}
			if(StringUtils.isNotBlank(m.getName())){
				if(m.getName().length() > 5){
					m.setName(m.getName().substring(0, 4) + "...");

				}
			}
			
			if(StringUtils.isNotEmpty(m.getCityCode())){
				Nation n = new Nation();
				n.setCode(m.getCityCode());
				n = nationService.getParentNationByCityeCodeOrId(n);
				m.setCityName(n.getName() + " " + m.getCityName());
			}
			map.put("m", m);
			
			//完善个人信息
			MemberInfo memberInfo = memberInfoService.findMemberInfoByMemberID(m.getId());
			
			boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(memberInfo);
			
			if(isPerfect == false){
				map.put("isPerfect", "未完善");
				return SHOW_MEMBERINFO_JSP_URL;
			}
			map.put("isPerfect", "已完善");
		} catch (Exception e) {
			logger.error("进入会员详情页面异常",e);
		}
		return SHOW_MEMBERINFO_JSP_URL;
	}
	
	
	/**
	 * @Title: saveMember  
	 * @Description: 创建个人会员
	 * @param:@param member
	 * @param:@return    
	 * @return:Map<String,Object> 
	 * @createTime:2016年8月13日 下午1:45:52
	 * @throws
	 */
	@RequestMapping("/createPersonalMember")
	public @ResponseBody Map<String,Object> createPersonalMember(Member member){
		logger.info("进入创建个人会员.....");
		try {
			memberService.createPersonalMember(member);
		} catch (Exception e) {
			logger.error("创建个人会员异常", e);
			return ResultUtils.fail("error", "操作异常");
		}
		return ResultUtils.success("操作成功", null);
	}
	
	/**
	 * 
	 * @Title: createCompanyMember  
	 * @Description: 创建企业会员
	 * @param:@param member
	 * @param:@return    
	 * @return:Map<String,Object> 
	 * @createTime:2016年8月13日 下午2:00:55
	 * @throws
	 */
	@RequestMapping("/createCompanyMember")
	public @ResponseBody Map<String,Object> createCompanyMember(Member member){
		logger.info("进入创建企业会员.....");
		try {
			memberService.createCompanyMember(member);
		} catch (Exception e) {
			logger.error("创建企业会员异常", e);
			return ResultUtils.fail("error", "操作异常");
		}
		return ResultUtils.success("操作成功", null);	
	}


	/**
	 * 查询会员信息
	 * @param member
	 * @return
	 */
	@RequestMapping("/findMemberList")
	public @ResponseBody Map<String,Object> findMemberList(Member member){
		logger.info("进入查询会员信息开始,参数为：" + JSONObject.toJSONString(member));
		List<Member> memberList = null;
		try {
			memberList = memberService.findMember(member);
		} catch (Exception e) {
			logger.error("查询用户异常",e);
			return ResultUtils.fail("error", "查询用户异常");
		}
		return ResultUtils.success("操作成功",memberList);

	}
	
	/**
	 * @Title: saveMember  
	 * @Description: 修改个人会员
	 * @param:@param member
	 * @param:@return    
	 * @return:Map<String,Object> 
	 * @createTime:2016年8月13日 下午1:45:52
	 * @throws
	 */
	@RequestMapping("/updatePersonalMember")
	public @ResponseBody Map<String,Object> updatePersonalMember(Member member){
		logger.info("进入修改个人会员.....");
		try {
			member = memberService.updatePersonalMember(member);

			if(StringUtils.isNotEmpty(member.getCityCode())){
				Nation n = new Nation();
				n.setCode(member.getCityCode());
				n = nationService.getParentNationByCityeCodeOrId(n);
				member.setCityName(n.getName() + " " + member.getCityName());
			}
		} catch (Exception e) {
			logger.error("修改个人会员异常", e);
			return ResultUtils.fail("error", "操作异常");
		}
		return ResultUtils.success("操作成功", member);
	}
	
	/**
	 * 
	 * @Title: createCompanyMember  
	 * @Description: 修改企业会员
	 * @param:@param member
	 * @param:@return    
	 * @return:Map<String,Object> 
	 * @createTime:2016年8月13日 下午2:00:55
	 * @throws
	 */
	@RequestMapping("/updateCompanyMember")
	public @ResponseBody Map<String,Object> updateCompanyMember(Member member){
		logger.info("进入修改企业会员.....");
		try {
			memberService.createCompanyMember(member);
		} catch (Exception e) {
			logger.error("修改企业会员异常", e);
			return ResultUtils.fail("error", "操作异常");
		}
		return ResultUtils.success("操作成功", null);	
	}
	
	/**
	 * 上传图片到本地
	 * @param wechatHeadUrl
	 * @return
	 */
	@RequestMapping("/uploadHead")
	public @ResponseBody Map<String,Object> uploadHead(Member member){

		if(member.getId() == null){
			return ResultUtils.fail("error", "用户id不允许为空");
		}
		//查询member 信息  返回member 实体 查询 用户原理的头像
		try {
			Member picture = memberService.uploadMemberHeadPicture(member);
			return ResultUtils.success("ok", picture.getImageUrl());//返回会员 web头像操作 
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.fail("error", "上传失败");
		}
	}

	@RequestMapping("/rechargeReturn")
	public @ResponseBody String rechargeReturn(String pwd){
		String message = "密码错误哦";
		if("jiaxiaoxian".equals(pwd)){
			message = "正则执行余额充返";
			List<MemberItem> memberItemList = memberItemService.selectByCondition(new MemberItem());
			for(MemberItem item : memberItemList){
				//判断余额是否 大于0,大于0 开始执行 老系统 充返
				logger.info("判断余额是否 大于0,大于0 开始执行 老系统 充返");
				if(item.getBalance().compareTo(BigDecimal.ZERO) == 1){
					logger.info("开始执行 老系统 充返");
					  /******充值返现金额  比例查表********/
			        ChargeReward chargeReward = chargeRewardService.selectObjectByCodition(new ChargeReward());
			        if(chargeReward != null){
			        	 Date start = chargeReward.getStartTime();
			        	 Date end = chargeReward.getEndTime();
			        	 Date nowDate = new Date();
			        	 //查询是否有充返活动
			        	 if(start.before(nowDate) && end.after(nowDate)){
			        		  Double proportion = Double.parseDouble(chargeReward.getPercent().toEngineeringString());
			        		  //赠送多少钱
			        		  BigDecimal disCount = item.getBalance().multiply(new BigDecimal(proportion -1)).setScale(2, BigDecimal.ROUND_HALF_UP);
			        		  String remark = "系统赠送余额"+disCount;
			        		  try {
								createMemberAccountLog(item, remark, disCount);
								} catch (Exception e) {
									logger.info("系统自动赠送金额出错"+e.getMessage());
								}
			        		  MemberItem memberItem = new MemberItem();
			        		  memberItem.setId(item.getId());
			        		  memberItem.setBalance(disCount.add(item.getBalance()));
			        		  memberItem.setLasttimeModify(new Date());
			        		  memberItemService.updateByPrimaryKeySelective(memberItem);
			        	 }
			        }
				}
			}
		
		}
		return message;
	}
	
	/**
	 * 余额
	 * 创建createMemberAccountLog
	 * @param co
	 * @param memberItem
	 * @throws Exception
	 */
	public void createMemberAccountLog(MemberItem memberItem,String remark,BigDecimal disCount) throws Exception {
		MemberAccountLog memberAccountLog = new MemberAccountLog();
      //插入一条权益消费的记录td_member_account_log
       
        memberAccountLog.setMemberShareType(2);
        memberAccountLog.setMemberItemId(memberItem.getId());
        memberAccountLog.setAccountType(1);
       // memberAccountLog.setDeposit(co.getDeposit());
       // memberAccountLog.setBalance(co.getBalance());
        memberAccountLog.setCreated(new Date());
        memberAccountLog.setTotal(disCount);
        memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
        memberAccountLog.setMemberItemName(memberItem.getItemName());
        memberAccountLog.setPreBalance(memberItem.getBalance());
        memberAccountLog.setPreDeposit(memberItem.getDeposit());
        memberAccountLog.setTransformType(15);//变动类型：1，客户续费；2，后台人工续费；3，后台人工扣费；4，用车扣费；
        //private Integer accountType;//续费类型：1，余额；2，押金；3，余额和押金
      	memberAccountLog.setRemark(remark);
		memberAccountLog.setBalanceReason(remark);
        memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
        memberAccountLog.setModifier("系统赠送");
        memberAccountLog.setModifierId("1");
        this.memberAccountLogService.insertSelective(memberAccountLog);       
	}
	
	/**
	 * 整天闲置
	* @Description:TODO      
	* @param  begin 开始时间,end结束时间,operateNum运营编号
	* Date date = new Date("2016/11/28 18:10:00");
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午3:14:41   
	* @return String
	 */
	@RequestMapping("/createStayCarLog")
	public @ResponseBody String createStayCarLog(String beginStr,String endStr,String operateNum){
		Calendar start = Calendar.getInstance();  
		Date startDate = new Date(beginStr);
		start.setTime(startDate);
		Long startTIme = start.getTimeInMillis();  
	    
	    Calendar end = Calendar.getInstance();  
	    Date endDate = new Date(endStr);
	    end.setTime(endDate);  
	    
	    Long endTIme = end.getTimeInMillis();  
	  
	    Long oneDay = 1000 * 60 * 60 * 24l;  
	  
	    Long time = startTIme;  
	    while (time <= endTIme) {  
	        Date d = new Date(time);  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        System.out.println(df.format(d));  
	        insertStayCarLog(operateNum, d);
	        time += oneDay;  
	    }  
		return "";
	}
	
	/**
	 * 插入整天闲置的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:34:11   
	* @return String
	 */
	private void insertStayCarLog(String operateNum, Date d){
		
		CarOperate car = new CarOperate();
		car.setOperateNum(operateNum);
		List<CarOperate> carList = carOperateService.selectByCondition(car);
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		carOperateLog.setRemark("数据自动导入整天闲置");
		carOperateLog.setType(CarOperateEnum.status.STAY.getValue());
		carOperateLog.setOperateNum(operateNum);
		carOperateLog.setCreated(d);
		if(CollectionUtils.isNotEmpty(carList)){
			CarOperate newCar = carList.get(0);
			if(newCar != null){
				boolean flag = checkLimitNumber(d, newCar);
				if(flag){
					carOperateLog.setRemark("数据自动导入限行");
				}
			}
		}
		try {
			carOperateLogService.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--operateNum"+operateNum);
		}
	}
	
	 private boolean checkLimitNumber(Date date, CarOperate carOperate) {
	        //验证是否限号
	        BasicRestriction basicRestriction = new BasicRestriction();
	        basicRestriction.setCityId(2);
	        basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(date,"yyyy-MM-dd")));
	        List<BasicRestriction> basicRestrictions = basicRestrictionService.selectByCondition(basicRestriction);
	        //根据车型判断车辆是否限号
	        List<CarType> carTypes = carTypeService.selectCarTypeByIds(Arrays.asList(carOperate.getCarTypeId()));
	        if(CollectionUtils.isNotEmpty(carTypes)){
	           CarType carType = carTypes.get(0);
	            if(carType.getIsRestriction().intValue() == 0){
	                return false;
	            }
	        }

	        if(CollectionUtils.isNotEmpty(basicRestrictions)){
	            basicRestriction = basicRestrictions.get(0);
	            String restrictions = basicRestriction.getRestrictions();
	            List<String> restrictionList = Arrays.asList(restrictions.split(","));
	            String carPlateNum = null;
	            String last = null;
	            carPlateNum = carOperate.getCarPlateNum();
	            last = carPlateNum.substring(carPlateNum.length()-1);
	            if(!isInteger(last)){
	                last = "0";
	            }
	            if(restrictionList.contains(last)){
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    /*
	     * 判断是否为整数
	     * @param str 传入的字符串
	     * @return 是整数返回true,否则返回false
	   */
	   public static boolean isInteger(String str) {
	       Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	       return pattern.matcher(str).matches();
	   }

	
	/**
	 * 修理车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午4:20:32   
	* @return String
	 */
	@RequestMapping("/createRepairCarLog")
	public @ResponseBody String createRepairCarLog(String beginStr,String endStr,String operateNum){
		Calendar start = Calendar.getInstance();  
		Date startDate = new Date(beginStr);
		start.setTime(startDate);
		Long startTIme = start.getTimeInMillis();  
	    
	    Calendar end = Calendar.getInstance();  
	    Date endDate = new Date(endStr);
	    end.setTime(endDate);  
	    
	    Calendar nowCalendar = Calendar.getInstance();  
	    nowCalendar.setTime(new Date());  
	    
	    Long endTime = end.getTimeInMillis();  
	  
	    Long oneDay = 1000 * 60 * 60 * 24l;  
	    Long time = startTIme;  
	    int i = 1;
	    while (time <= endTime) {  
	        Date d = new Date(time);  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        System.out.println(df.format(d));  
	        insertRepairsingCarLog(operateNum, d,i);
	        time += oneDay;  
	        if((endTime - time) < 0 && Math.abs(endTime - time) < oneDay){
	        	i = -1;
	        	Date dd = new Date(endTime);  
	        	//log
	        	insertRepairsingCarLog(operateNum, dd,i);
	        }
	        if(time > nowCalendar.getTimeInMillis()){
	        	break;
	        }
	        if(time.equals(endTime)){
	        	i = -1;
	        }
	    }  
		return "";
	}
	
	/**
	 * 插入故障中的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:34:11   
	* @return String
	 */
	private void insertRepairsingCarLog(String operateNum, Date d,int i){
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		if(i == 1){
			carOperateLog.setRemark("数据自动导入车辆故障");
			carOperateLog.setType(CarOperateEnum.status.REPAIRSING.getValue());
		}else if(i == -1){
			carOperateLog.setRemark("数据自动导入故障维修完成");
			carOperateLog.setType(CarOperateEnum.status.STAY.getValue());
		}
		carOperateLog.setOperateNum(operateNum);
		carOperateLog.setCreated(d);
		try {
			carOperateLogService.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--operateNum"+operateNum);
		}
	}
	
	
	/**
	 * 根据用车记录 自动插入会员的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午4:48:51   
	* @return String
	 */
	public void autoCreateUseCarLog(PowerUsed powerUses,ButlerTask butlerTask){
		
		Calendar start = Calendar.getInstance();  
	    start.setTime(powerUses.getCarUsedTime());
	   // start.set(2014, 6, 11);
	    Long startTIme = start.getTimeInMillis();  
	  
	    Calendar end = Calendar.getInstance();  
	    end.setTime(powerUses.getCarReturnTime());  
	    //end.set(2014, 7, 11);  
	    Long endTime = end.getTimeInMillis();  
	  
	    Long oneDay = 1000 * 60 * 60 * 24l;  
	  
	    Long time = startTIme;  
	    int i = 1;
	    int status = powerUses.getUsedStatus();
	    while (time <= endTime) {  
	        Date d = new Date(time);  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        System.out.println(df.format(d));  
	        //log
	        insertCarLog(powerUses, butlerTask, i,d);
	        time += oneDay;  
	        Date now = new Date(time);
	        Date endDate = new Date(endTime); 
	        Date nowDate = new Date();
	        i++;
	        if(now.getYear() == endDate.getYear() && now.getMonth() == endDate.getMonth()
	        		&& now.getDate() == endDate.getDate()){
	        	if(time > endTime){
	        		time = endTime;
	        	}
	        }
	        if(now.getYear() == nowDate.getYear() && now.getMonth() == nowDate.getMonth()
	        		&& now.getDate() == (nowDate.getDate()+1)){
	        	if(status == 3){
	        		break;
	        	}
	        }
	        if(time.equals(endTime)){
	        	if(status == 6){
	        		i = -1;
	        	}
	        }
	    }  
		
	}
	
	/**
	 * 插入车辆 会员使用中的 日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:38:39   
	* @return String
	 */
	private void insertCarLog(PowerUsed powerUses,ButlerTask butlerTask,int i,Date d){
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		//运营记录
		carOperateLog.setMemberId(powerUses.getMemberId());
		Member member = null;
		try {
			member = memberService.findMemberByID(powerUses.getMemberId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(member != null){
			carOperateLog.setMemberName(member.getName());
		}
		carOperateLog.setOperateId(butlerTask.getOperaterId());
		carOperateLog.setOperateName(butlerTask.getOperaterName());
		carOperateLog.setTakein(new BigDecimal(1199));
		if(i == 1){
			carOperateLog.setRemark("数据自动导入管家送车完成,修改车辆状态为会员使用中");
			carOperateLog.setType(CarOperateEnum.status.MEMBERUSEING.getValue());
		}else if(i == -1){
			carOperateLog.setRemark("数据自动导入会员使用完毕,修改车辆状态为整备中");
			carOperateLog.setType(CarOperateEnum.status.READINESSING.getValue());
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(d);
			calendar.add(Calendar.HOUR_OF_DAY, 4);
			insertReadinessingCarLog(butlerTask, calendar.getTime());
		}else{
			carOperateLog.setRemark("数据自动导入会员使用中");
			carOperateLog.setType(CarOperateEnum.status.MEMBERUSEING.getValue());
		}
		CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
		carOperateLog.setOperateNum(carOperate.getOperateNum());
		carOperateLog.setCreated(d);
		try {
			carOperateLogService.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--powerUsesId"+powerUses.getId());
		}
	}
	
	/**
	 * 插入自动整备完成的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:34:11   
	* @return String
	 */
	private void insertReadinessingCarLog(ButlerTask butlerTask,Date d){
		//插入一条新的运营中的日志
		CarOperateLog carOperateLog = new CarOperateLog();
		carOperateLog.setRemark("数据自动导入系统自动整备完成");
		carOperateLog.setType(CarOperateEnum.status.STAY.getValue());
		CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
		carOperateLog.setOperateNum(carOperate.getOperateNum());
		carOperateLog.setCreated(d);
		try {
			carOperateLogService.insertSelective(carOperateLog);
		} catch (Exception e) {
			logger.error("添加车辆日志错误insertCarLog--butlerTaskId"+butlerTask.getId());
		}
	}
	
	/**
	 * 插入运营计划
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:44:56   
	* @return String
	 */
	public CarOperatePlan insertCarRecord(PowerUsed powerUses,ButlerTask butlerTask,int status) throws Exception {
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
		if(powerUses.getUsedStatus() == 3){
			carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());//运营状态 1:待执行；2:执行中；3:已完成；4:已取消；5:已终止；
		}else if(powerUses.getUsedStatus() == 6){
			carOperatePlan.setStatus(CarOperatePlanEnum.status.SUCCESS.getValue());//运营状态 1:待执行；2:执行中；3:已完成；4:已取消；5:已终止；
		}
		carOperatePlan.setOperateTo(null);//使用方
		carOperatePlan.setOperateStatus(CarOperateEnum.status.MEMBERUSEING.getValue());
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
	
	/**
	 * 插入平台运营计划
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:44:56   
	* @return String
	 */
	@RequestMapping("insertCarPlan")
	public @ResponseBody String insertCarPlan(String carPlateNum,String beginStr,String endStr,
			String operateType,String operateTo,String carUseaddress,String expectedReturn,
			int status,String butlerName){
//		Assert.notNull(butlerTask.getCarOperateId(),"车辆id不能为空");
        logger.info("插入预约记录carOperatePlan记录");
      //插入用车计划表中的 预计还车时间
        CarOperatePlan carOperatePlan = new CarOperatePlan();
	    CarOperate car = new CarOperate();
	    car.setCarPlateNum(carPlateNum);
		List<CarOperate> carList = carOperateService.selectByCondition(car);
		if(CollectionUtils.isNotEmpty(carList)){
			CarOperate newCar = carList.get(0);
			carOperatePlan.setOperateNum(newCar.getOperateNum());
			carOperatePlan.setOperateId(newCar.getId());
			carOperatePlan.setCarTypeId(newCar.getCarTypeId());
		}else{
			return "查询车辆异常,没有此车牌号的车辆"+carPlateNum;
		}
		Member member = new Member();
		member.setSysuserName(butlerName);
		try {
			List<Member> memberList = memberService.selectByCondition(member);
			if(CollectionUtils.isNotEmpty(memberList)){
				Member newMember = memberList.get(0);
				if(newMember != null){
					carOperatePlan.setButlerId(newMember.getSysuserId());
				}
			}
		} catch (Exception e1) {
			return "查询管家异常,没有此管家"+butlerName;
		}
		carOperatePlan.setButlerName(butlerName);
		carOperatePlan.setCarPlateNum(carPlateNum);
		carOperatePlan.setRemark("数据自动导入");
		carOperatePlan.setCreated(new Date());
		Date start = new Date(beginStr);
		carOperatePlan.setStarted(start);
		Date end = new Date(endStr);
		carOperatePlan.setEnded(end);
		
		BasicOperationType operationType = new BasicOperationType();
		operationType.setName(operateType);
		List<BasicOperationType> list = basicOperationTypeService.selectByCondition(operationType);
		if(CollectionUtils.isNotEmpty(list)){
			BasicOperationType type = list.get(0);
			if(type != null){
				//平台台运营类型名称(如:专车、婚庆、短租等)的id
				carOperatePlan.setOperateType(type.getId());
			}
		}
		
		carOperatePlan.setExpectedReturn(expectedReturn);
		carOperatePlan.setMoney(new BigDecimal(expectedReturn));//运营收益金额
		carOperatePlan.setIsStock(1);//'是否影响库存 1影响库存2无影响库存', 
		carOperatePlan.setStatus(status);//运营状态 1:待执行；2:执行中；3:已完成；4:已取消；5:已终止；
		carOperatePlan.setOperateTo(operateTo);//使用方
		carOperatePlan.setOperateStatus(CarOperateEnum.status.PLATFORMUSEING.getValue());
		carOperatePlan.setCarUsedAddress(carUseaddress);  //用车地址
		
		
		//carOperatePlan.setButlerId(butlerTask.getOperaterId()); //管家id
		//carOperatePlan.setButlerName(butlerTask.getOperaterName()); //管家姓名
		int id = 0;
		try {
			id = carOperatePlanService.insertSelective(carOperatePlan);
			autoCreatePlateCarLog(carOperatePlan);
		} catch (Exception e) {
			logger.info("插入预约记录carOperatePlan记录 异常"+e+"的carPlanId"+id);
		}
		logger.info("插入预约记录carOperatePlan记录  结束 carPlan的id"+id);
		return carOperatePlan.toString();
	}
	
	/**
	 * 根据用车记录 自动插入平台的车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午4:48:51   
	* @return String
	 */
	public void autoCreatePlateCarLog(CarOperatePlan carOperatePlan){
		
		Calendar start = Calendar.getInstance();  
	    start.setTime(carOperatePlan.getStarted());
	   // start.set(2014, 6, 11);
	    Long startTIme = start.getTimeInMillis();  
	  
	    Calendar end = Calendar.getInstance();  
	    end.setTime(carOperatePlan.getEnded());  
	    //end.set(2014, 7, 11);  
	    Long endTime = end.getTimeInMillis();  
	    
	    Calendar nowCalendar = Calendar.getInstance();  
	    nowCalendar.setTime(new Date());  
	  
	    Long oneDay = 1000 * 60 * 60 * 24l;  
	    Long twelveHours = 1000 * 60 * 60 * 26l;  
	    
	    Long time = startTIme;  
	    int i = 1;
	    int status = carOperatePlan.getStatus();
	    while (time <= endTime) {  
	        Date d = new Date(time);  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        System.out.println(df.format(d));  
	        //log
	        insertPlatformuseCarLog(carOperatePlan, i,d);
	        if(i == 1){
	        	 time += twelveHours;
	        }else{
	        	  time += oneDay;  
	        }
	        Date now = new Date(time);
	        Date endDate = new Date(endTime); 
	        Date nowDate = new Date();
	        i++;
	        if(time > nowCalendar.getTimeInMillis()){
	        	break;
	        }
	        if((endTime - time) < 0 && Math.abs(endTime - time) < oneDay){
	        	if(status == 3){
	        		i = -1;
	        	}
	        	Date dd = new Date(endTime);  
	        	//log
		        insertPlatformuseCarLog(carOperatePlan, i,dd);
	        }
	       /* if(now.getYear() == endDate.getYear() && now.getMonth() == endDate.getMonth()
	        		&& now.getDate() == endDate.getDate()){
	        	if(time > endTime){
	        		time = endTime;
	        	}
	        }*/
	        if(now.getYear() == nowDate.getYear() && now.getMonth() == nowDate.getMonth()
	        		&& now.getDate() == (nowDate.getDate()+1)){
	        	if(status == 2){
	        		break;
	        	}
	        }
	        if(time.equals(endTime)){
	        	if(status == 3){
	        		i = -1;
	        	}
	        }
	    }  
		
	}
	
	/**
	 * 插入车辆 平台运营的 日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-28 下午5:38:39   
	* @return String
	 */
	private void insertPlatformuseCarLog(CarOperatePlan carOperatePlan,int i,Date d){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr = sdf.format(d);
		CarOperateLog selectCarOperateLog = new CarOperateLog();
		selectCarOperateLog.setOperateNum(carOperatePlan.getOperateNum());
		selectCarOperateLog.setRemark(nowStr);
		CarOperateLog logs = carOperateLogService.selectByOperateNumCreate(selectCarOperateLog);
		if(logs != null){
			//运营记录
			logs.setMemberName(carOperatePlan.getOperateTo());
			logs.setOperateId(carOperatePlan.getButlerId());
			logs.setOperateName(carOperatePlan.getButlerName());
			if(i == 1){
				if(StringUtils.isNotEmpty(carOperatePlan.getExpectedReturn())){
					logs.setTakein(new BigDecimal(carOperatePlan.getExpectedReturn()));
				}
				logs.setRemark("数据自动导入运营开始！");
				logs.setType(CarOperateEnum.status.PLATFORMUSEING.getValue());
			}else if(i == -1){
				logs.setRemark("数据自动导入运营结束！");
				logs.setType(CarOperateEnum.status.STAY.getValue());
			}else{
				if(StringUtils.isNotEmpty(carOperatePlan.getExpectedReturn())){
					logs.setTakein(new BigDecimal(carOperatePlan.getExpectedReturn()));
				}
				logs.setRemark("数据自动导入平台运营中！");
				logs.setType(CarOperateEnum.status.PLATFORMUSEING.getValue());
			}
			logs.setOperateNum(carOperatePlan.getOperateNum());
			logs.setCreated(d);
			carOperateLogService.updateByPrimaryKeySelective(logs);
		}else{
			//插入一条新的运营中的日志
			CarOperateLog carOperateLog = new CarOperateLog();
			//运营记录
			carOperateLog.setMemberName(carOperatePlan.getOperateTo());
			carOperateLog.setOperateId(carOperatePlan.getButlerId());
			carOperateLog.setOperateName(carOperatePlan.getButlerName());
			if(i == 1){
				if(StringUtils.isNotEmpty(carOperatePlan.getExpectedReturn())){
					logs.setTakein(new BigDecimal(carOperatePlan.getExpectedReturn()));
				}
				carOperateLog.setRemark("数据自动导入运营开始！");
				carOperateLog.setType(CarOperateEnum.status.PLATFORMUSEING.getValue());
			}else if(i == -1){
				carOperateLog.setRemark("数据自动导入运营结束！");
				carOperateLog.setType(CarOperateEnum.status.STAY.getValue());
			}else{
				if(StringUtils.isNotEmpty(carOperatePlan.getExpectedReturn())){
					logs.setTakein(new BigDecimal(carOperatePlan.getExpectedReturn()));
				}
				carOperateLog.setRemark("数据自动导入平台运营中！");
				carOperateLog.setType(CarOperateEnum.status.PLATFORMUSEING.getValue());
			}
			carOperateLog.setOperateNum(carOperatePlan.getOperateNum());
			carOperateLog.setCreated(d);
			try {
				carOperateLogService.insertSelective(carOperateLog);
			} catch (Exception e) {
				logger.error("添加车辆平台运营日志错误insertCarLog--carOperatePlanId"+carOperatePlan.getId());
			}
		}
	}
	
	public BasicNotice setOperater(){
		//给后台基础设置-通知管理设置的通知人发送微信以及app通知
		BasicNotice b = new BasicNotice();
		//查询正常的状态
		b.setStatus(0);
		BasicNotice basicNotice = null;
		List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
		if(CollectionUtils.isNotEmpty(basicNotices)){
			basicNotice = basicNotices.get(0);
		}
		return basicNotice;
	}
	
	/** 
     * Excel 2003 
     */  
    private final static String XLS = "xls";  
  
    /** 
     * Excel 2007 
     */  
    private final static String XLSX = "xlsx";

	private static final String String = null;  
  
    /** 
     * 由Excel文件的Sheet导出至List 
     *  
     * @param file 
     *            导入的excel文件 
     * @param sheetNum 
     *            excel工作空间,一般情况为0 
     * @return 
     */  
    public static List<Map<String, Object>> exportListFromExcel(File file, int sheetNum) throws IOException {  
        return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()), sheetNum);  
    }  
  
    /** 
     * 由Excel流的Sheet导出至List 
     *  
     * @param is 
     * @param extensionName 
     * @param sheetNum 
     * @return 
     * @throws IOException 
     */  
    public static List<Map<String, Object>> exportListFromExcel(InputStream is, String extensionName, int sheetNum) throws IOException {  
  
        Workbook workbook = null;  
  
        if (extensionName.toLowerCase().equals(XLS)) {  
            workbook = new HSSFWorkbook(is);  
        } else if (extensionName.toLowerCase().equals(XLSX)) {  
//            workbook = new XSSFWorkbook(is);  
        }  
  
        return readCell(workbook, sheetNum);  
    }  
  
    /** 
     * 读取Cell的值 
     *  
     * @param sheet 
     * @return 
     */  
    public static List<Map<String, Object>> readCell(Workbook workbook, int sheetNum) {  
        Sheet sheet = workbook.getSheetAt(sheetNum);  
  
        // 解析公式结果  
        // FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        // 遍历所有行  
        // for (Row row : sheet)  
        // 除去表头即第一行  
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {  
            Row row = sheet.getRow(i);  
            Map<String, Object> map = new LinkedHashMap<>(); 
            if(row != null){
                // 便利所有列  
                for (Cell cell : row) {  
      
                    // 获取单元格的类型  
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());  
                    String key = cellRef.formatAsString();  
      
                    switch (cell.getCellType()) {  
                    // 字符串  
                    case Cell.CELL_TYPE_STRING:  
                        map.put(key, cell.getRichStringCellValue().getString());  
                        break;  
                    // 数字  
                    case Cell.CELL_TYPE_NUMERIC:  
                        if (DateUtil.isCellDateFormatted(cell)) {  
                            map.put(key, cell.getDateCellValue());  
                        } else {  
                            map.put(key, (int)cell.getNumericCellValue());  
                        }  
                        break;  
                    // boolean  
                    case Cell.CELL_TYPE_BOOLEAN:  
                        map.put(key, cell.getBooleanCellValue());  
                        break;  
                    // 方程式  
                    case Cell.CELL_TYPE_FORMULA:  
                        map.put(key, cell.getCellFormula());  
                        break;  
                    case Cell.CELL_TYPE_BLANK:  
                        break;  
                    case Cell.CELL_TYPE_ERROR:  
                        break;  
                    // 空值  
                    default:  
                        map.put(key, "");  
                    }  
                }  
            }
            if(!map.isEmpty()){
            	 list.add(map);  
            }
        }  
        return list;  
  
    }  
	
    @RequestMapping("/autoCreateLog")
	public @ResponseBody void autoCreateLog() throws Exception {  
//      String paths = "c:\\excel.xlsx";  
//        String paths = ReadExcel.class.getResource("D:\\td_power_used.xls").getFile();  
    	  HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File("/hycx/bak/importcardata/td_car_operate_log.xls")));
          HSSFSheet sheet=null;
          for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
	    	List<Map<String, Object>> lists = exportListFromExcel(new File("/hycx/bak/importcardata/td_car_operate_log.xls"), i);  
	        System.out.println(lists);
	        int size = lists.size();
	        for (Map<String, Object> map : lists) {
	        	CarOperateLog carLog = new CarOperateLog();
				for (String key:map.keySet()) {
					System.out.println(key);
					System.out.println(map.get(key));
					
					String v=map.get(key)+"";
					if(key.startsWith("A")){
						carLog.setOperateNum(v);
					}else if(key.startsWith("B")){
						if(map.get(key) instanceof Date){
							carLog.setCreated((Date)map.get(key));
						}
					}else if(key.startsWith("C")){
					}else if(key.startsWith("D")){
					}else if(key.startsWith("E")){
						carLog.setType(Integer.parseInt(v));
					}else if(key.startsWith("F")){
					}else if(key.startsWith("G")){
					}else if(key.startsWith("H")){
					}else if(key.startsWith("I")){
					}else if(key.startsWith("J")){
					}else if(key.startsWith("K")){
						carLog.setRemark(v+"！");
					}
				}
				if(StringUtils.isNotEmpty(carLog.getRemark())){
					System.out.println(carLog.getOperateNum()+"有。。。。。。。。。。");
					CarOperate car = new CarOperate();
					car.setOperateNum(carLog.getOperateNum());
					List<CarOperate> carList = carOperateService.selectByCondition(car);
					//插入一条新的运营中的日志
					carLog.setRemark("数据自动导入整天闲置");
					if(CollectionUtils.isNotEmpty(carList)){
						CarOperate newCar = carList.get(0);
						if(newCar != null){
							boolean flag = checkLimitNumber(carLog.getCreated(), newCar);
							if(flag){
								carLog.setRemark("数据自动导入限行");
							}
						}
					}
					carOperateLogService.insertSelective(carLog);
				}else{
					System.out.println(carLog.getOperateNum()+"无。。。。。。。");
				}
				//insert log
				System.out.println("============================================"+size--);
			}
          }
    }
    
    @RequestMapping("/autoCreatePlateLog")
	public @ResponseBody String autoCreatePlateLog() throws Exception {  
//      String paths = "c:\\excel.xlsx";  
//        String paths = ReadExcel.class.getResource("D:\\td_power_used.xls").getFile();  
    	 HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File("/hycx/bak/importcardata/td_car_operate_plan.xls")));
          HSSFSheet sheet=null;
          for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
	    	List<Map<String, Object>> lists = exportListFromExcel(new File("/hycx/bak/importcardata/td_car_operate_plan.xls"), i);  
    
    	    System.out.println(lists);
	        int size = lists.size();
	        for (Map<String, Object> map : lists) {
	        	 logger.info("插入预约记录carOperatePlan记录");
	             //插入用车计划表中的 预计还车时间
	             CarOperatePlan carOperatePlan = new CarOperatePlan();
				for (String key:map.keySet()) {
					System.out.println(key);
					System.out.println(map.get(key));
					carOperatePlan.setRemark("数据自动导入");
					carOperatePlan.setCreated(new Date());
					String v=map.get(key)+"";
					if(key.startsWith("A")){
						if(map.get(key) instanceof Date){
							carOperatePlan.setStarted((Date)map.get(key));
						}
					}else if(key.startsWith("B")){
						if(map.get(key) instanceof Date){
							carOperatePlan.setEnded((Date)map.get(key));
						}
					}else if(key.startsWith("C")){
						BasicOperationType operationType = new BasicOperationType();
						operationType.setName(v);
						List<BasicOperationType> list = basicOperationTypeService.selectByCondition(operationType);
						if(CollectionUtils.isNotEmpty(list)){
							BasicOperationType type = list.get(0);
							if(type != null){
								//平台台运营类型名称(如:专车、婚庆、短租等)的id
								carOperatePlan.setOperateType(type.getId());
							}
						}
					}else if(key.startsWith("D")){
						carOperatePlan.setOperateTo(v);
					}else if(key.startsWith("E")){
						carOperatePlan.setCarUsedAddress(v);
					}else if(key.startsWith("F")){
						carOperatePlan.setExpectedReturn(v);
						if(StringUtils.isNotEmpty(v)){
							carOperatePlan.setMoney(new BigDecimal(v));
						}
					}else if(key.startsWith("G")){
						Member member = new Member();
						carOperatePlan.setButlerName(v);
						member.setSysuserName(v);
						try {
							List<Member> memberList = memberService.selectByCondition(member);
							if(CollectionUtils.isNotEmpty(memberList)){
								Member newMember = memberList.get(0);
								if(newMember != null){
									carOperatePlan.setButlerId(newMember.getSysuserId());
								}
							}
						} catch (Exception e1) {
							return "查询管家异常,没有此管家"+v;
						}
					}else if(key.startsWith("H")){
						carOperatePlan.setCarPlateNum(v);
						CarOperate car = new CarOperate();
					    car.setCarPlateNum(v);
						List<CarOperate> carList = carOperateService.selectByCondition(car);
						if(CollectionUtils.isNotEmpty(carList)){
							CarOperate newCar = carList.get(0);
							carOperatePlan.setOperateNum(newCar.getOperateNum());
							carOperatePlan.setOperateId(newCar.getId());
							carOperatePlan.setCarTypeId(newCar.getCarTypeId());
						}else{
							return "查询车辆异常,没有此车牌号的车辆"+v;
						}
					}else if(key.startsWith("I")){
						carOperatePlan.setStatus(Integer.parseInt(v));
					}
				}
				carOperatePlan.setIsStock(1);//'是否影响库存 1影响库存2无影响库存', 
				carOperatePlan.setOperateStatus(CarOperateEnum.status.PLATFORMUSEING.getValue());
				int id = 0;
				try {
					id = carOperatePlanService.insertSelective(carOperatePlan);
					autoCreatePlateCarLog(carOperatePlan);
				} catch (Exception e) {
					logger.info("插入预约记录carOperatePlan记录 异常"+e+"的carPlanId"+id);
				}
				logger.info("插入预约记录carOperatePlan记录  结束 carPlan的id"+id);
				//insert log
				System.out.println("============================================"+size--);
			}
         }
          return "全部导入完成";
    }
    
}
