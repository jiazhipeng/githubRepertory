package com.hy.gcar.web.wechat.butlerTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.common.utils.DateUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.ButlerTaskType;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.entity.CarJson;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.PowerUsedCost;
import com.hy.gcar.entity.TransferList;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.transferList.TransferListService;

/**
 * 取车任务controller
 * @author gongbw
 *
 */
@Controller
@RequestMapping("/wechat/butlerTask/getCar")
public class ButlerTaskGetCarController {

protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ButlerTaskService butlerTaskService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ButlerTaskStatusLogService butlerTaskStatusLogService;
	@Autowired
	private CarOperateService carOperateService;
	@Autowired
	private TransferListService  transferListService;
	@Autowired
	private BasicThresholdService basicThresholdService;
	@Autowired
	private PowerUsedCostService powerUsedCostService;
	@Autowired
	private BasicNoticeService basicNoticeService;
	
	/**
	 * 跳转取车任务列表
	 * @param loginId
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toButlerTaskList")
	public String toButlerTaskList(ButlerTask butlerTask,String butlerId, ModelMap map) throws Exception{
		this.logger.info("跳转充电任务页面---当前登录管家ID为---"+butlerTask.getLoginId());
		this.logger.info("跳转充电任务页面---查询的日期为---"+butlerTask.getQueryTime());
		//查询所有的管家人员列表
		List<Member> butlers = this.memberService.findButlerMemberList();
		//首次进入加载数据列表
		//ButlerTask butlerTask = new ButlerTask();
		
		String butlerSysuserId ="";
		if(null!=butlerId && !"".equals(butlerId)){
			Member member = this.memberService.findMemberByID(Long.parseLong(butlerId));
			butlerSysuserId = member.getSysuserId();
			butlerTask.setOperaterId(butlerSysuserId);
		}
		List<ButlerTask> butlerTaskList = this.butlerTaskService.getButlerTaskListForGetCar(butlerTask);
		map.put("butlerSysuserId",butlerSysuserId);
		//前端返回
		map.put("butlers",butlers);		
		map.put("loginId",butlerTask.getLoginId());
		map.put("queryTime", butlerTask.getQueryTime());
		map.put("butlerTaskList", butlerTaskList);
		return "gcar/butler/getCar/list";
	}
	
	/**
	 * 动态加载取车任务列表
	 * @param butlerTask
	 * @return
	 */
	@RequestMapping(value="/butlerTaskList")
	public @ResponseBody Map<String,Object> butlerList(ButlerTask butlerTask){
		this.logger.info("加载动态加载任务开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		List<ButlerTask> butlerTaskList = this.butlerTaskService.getButlerTaskListForGetCar(butlerTask);
		//前台返值
		obj.put("butlerTaskList", butlerTaskList);
		return obj;
	}
	
	/**
	 * 点击查看任务详情
	 * @param butlerTask
	 * @param map
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/detail")
	public String butlerTaskDetail(ButlerTask butlerTask,ModelMap map) throws Exception{
		String queryTime = butlerTask.getQueryTime();
		//当前登录人
		String loginId = butlerTask.getLoginId();
		
		//查询当前登录人的信息
		Member member = this.memberService.findMemberByID(Long.parseLong(loginId));
		//根据任务ID查询当前任务的详情
		butlerTask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
		
		//判断任务如果是待接单的状态，进入待接单的页面
		if(ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_ORDERS.getIndex()==butlerTask.getStatus()){
			//跳转待接单的页面
			boolean isOperate = true;
			if(!member.getSysuserId().equals(butlerTask.getOperaterId())){
				isOperate = false;
			}
			//查询所有的管家人员列表
			List<Member> butlers = this.memberService.findButlerMemberList();
			map.put("butlers",butlers);
			map.put("isOperate", isOperate);
			map.put("loginId",loginId);
			map.put("queryTime", queryTime);
			map.put("butlerTask", butlerTask);
			String plan_time = DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd");
            butlerTask.setQueryBegin(DateUtils.parseDate(plan_time + " 00:00:00"));
            butlerTask.setQueryEnd(DateUtils.parseDate(plan_time + " 23:59:59"));
            List<ButlerTask> butlerTasks = this.butlerTaskService.selectHaveInHandButlerTaskByPlanTime(butlerTask);
            map.put("butlerTasks", butlerTasks);
            
            //进判断当前任务的管家是不是 后台设置送车任务管家
            Member member_notice = this.getMemberNotice();
            boolean isFillInNotes = false;
            if(Long.parseLong(loginId)==member_notice.getId()){
            	isFillInNotes = true;
            }
            map.put("isFillInNotes", isFillInNotes);
			
			return "gcar/butler/getCar/pendingOrders";
		}
		
		//判断如果是已完成或者是已取消或者当前管家不是当前任务的负责人的情况进入查看详情的页面，无法编辑
		if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex()==butlerTask.getStatus() || ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex()==butlerTask.getStatus()
				|| ButlerTaskStatus.BUTLER_TASK_STATUS_LOST_CONTACT.getIndex()==butlerTask.getStatus() || (!member.getSysuserId().equals(butlerTask.getOperaterId()))){
			//跳转详情页
			return "redirect:/wechat/butlerTask/getCar/detailInfo?id="+butlerTask.getId()+"&loginId="+loginId+"&queryTime="+queryTime;
		}
		
		//根据任务ID查询任务详情
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		butlerTaskStatusLog.setTaskId(butlerTask.getId());
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog);
		
		TransferList t = new TransferList();
        t.setTaskId(butlerTask.getId());
        t.setType(0);
        Integer transferCount = transferListService.selectCountByCondition(t);
        map.put("transferCount",transferCount);
        //查询交接单车辆照片数目
        t.setType(1);
        Integer carCount = transferListService.selectCountByCondition(t);
        map.put("carCount",carCount);
        
		//查询车辆信息
		CarOperate carOperate = new CarOperate();
		String carOperateMessage ="";
		if(null!=butlerTask.getCarOperateId()){
			carOperate = this.carOperateService.findById(butlerTask.getCarOperateId());
			carOperateMessage = carOperate.getCarBrand()+" "+carOperate.getCarType()+" "+carOperate.getCarPlateNum();
		}
		
		//前台返值
		map.put("loginId",loginId);
		map.put("queryTime", queryTime);
		map.put("butlerTask", butlerTask);
		map.put("carOperateMessage", carOperateMessage);
		map.put("logList", butlerTaskStatusLogList);
		
		return "gcar/butler/getCar/butlerInfo";
	}
	
	/**
	 * 已完成或者是已取消或者当前管家不是当前任务的负责人的情况进入查看详情的页面，无法编辑,跳转到此方法
	 * @param butlerTask
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detailInfo")
	public String detailinfo(ButlerTask butlerTask,ModelMap map)throws Exception{
		String queryTime = butlerTask.getQueryTime();
		//当前登录人
		String loginId = butlerTask.getLoginId();
		//查询当前登录人的信息
		Member member = this.memberService.findMemberByID(Long.parseLong(loginId));
		//根据任务ID查询当前任务的详情
		butlerTask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
		//查询任务上关联的用户的真实姓名
		Member userMember = this.memberService.findMemberByID(Long.parseLong(butlerTask.getMemberId()));
		if(null!=userMember && null!=userMember.getSureName()){
			butlerTask.setMemberName(userMember.getSureName());
		}
		//根据任务ID查询任务详情
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		butlerTaskStatusLog.setTaskId(butlerTask.getId());
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog);
		
		TransferList t = new TransferList();
        t.setTaskId(butlerTask.getId());
        t.setType(0);
        Integer transferCount = transferListService.selectCountByCondition(t);
        map.put("transferCount",transferCount);
        //查询交接单车辆照片数目
        t.setType(1);
        Integer carCount = transferListService.selectCountByCondition(t);
        map.put("carCount",carCount);
        
		//查询车辆信息
		CarOperate carOperate = new CarOperate();
		String carOperateMessage ="";
		if(null!=butlerTask.getCarOperateId()){
			carOperate = this.carOperateService.findById(butlerTask.getCarOperateId());
			carOperateMessage = carOperate.getCarBrand()+" "+carOperate.getCarType()+" "+carOperate.getCarPlateNum();
		}
		
		PowerUsedCost powerUsedCost = new PowerUsedCost();
		if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex()==butlerTask.getStatus()){
			
			powerUsedCost= this.powerUsedCostService.selectByPowerUsedId(butlerTask.getPowerUsedId());
		}
		
		//前台返值
		map.put("member", member);
		map.put("powerUsedCost", powerUsedCost);
		map.put("loginId",loginId);
		map.put("queryTime", queryTime);
		map.put("butlerTask", butlerTask);
		map.put("carOperateMessage", carOperateMessage);
		map.put("logList", butlerTaskStatusLogList);
		
		return "gcar/butler/getCar/butlerDetailInfo";
	}
	
	/**
	 * 修改取车任务信息
	 * @param butlerTask
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public @ResponseBody Map<String,Object> edit(ButlerTask butlerTask)throws Exception{
		this.logger.info("取车任务修改开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		
		String queryTime = butlerTask.getQueryTime();
		//当前登录人
		String loginId = butlerTask.getLoginId();
		
		obj.put("loginId", loginId);
		obj.put("butlerTaskId", butlerTask.getId());
		obj.put("queryTime", queryTime);
		try {
			//根据任务ID查询修改之前的任务状态 ，看能否满足修改条件
			ButlerTask butlerTask_old = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
			if(null!=butlerTask.getStatus()){
				//有状态的变更情况
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_GET_FOR_A_CAR.getIndex() == butlerTask.getStatus()){
					if(ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_ORDERS.getIndex() != butlerTask_old.getStatus()){
						obj.put("result",false );
						obj.put("msg","该任务已被修改");
						return obj;
					}
					this.butlerTaskService.updateGetCarButlerTaskByWaitGet(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_TAKE_THE_CAR.getIndex() == butlerTask.getStatus()){
					if(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_GET_FOR_A_CAR.getIndex() != butlerTask_old.getStatus()){
						obj.put("result",false );
						obj.put("msg","该任务已被修改");
						return obj;
					}
					this.butlerTaskService.updateGetCarButlerTaskByGeting(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex() == butlerTask.getStatus()){
					if(ButlerTaskStatus.BUTLER_TASK_STATUS_TAKE_THE_CAR.getIndex() != butlerTask_old.getStatus()){
						obj.put("result",false );
						obj.put("msg","该任务已被修改");
						return obj;
					}
					this.butlerTaskService.updateGetCarButlerTaskByArrived(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_LOST_CONTACT.getIndex() == butlerTask.getStatus()){
					Member member = memberService.findMemberByID(Long.decode(loginId));
					butlerTask.setOperaterId(member.getSysuserId());
					this.butlerTaskService.updateGetCarButlerTaskByLose(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex() == butlerTask.getStatus()){
					if(ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex() != butlerTask_old.getStatus()){
						obj.put("result",false );
						obj.put("msg","该任务已被修改");
						return obj;
					}
					this.butlerTaskService.updateGetCarButlerTaskByComplete(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex() == butlerTask.getStatus()){
					this.butlerTaskService.updateGetCarButlerTaskByCancel(butlerTask);
				}
			}
			else{
				this.butlerTaskService.updateButlerTaskByNoStatus(butlerTask);
			}
			obj.put("result", true);
			obj.put("msg","修改任务成功");
		} catch (Exception e) {
			this.logger.error("修改任务失败---");
			obj.put("result",false );
			obj.put("msg","修改任务失败");
			e.printStackTrace();
		}
			return obj;
	}
	
	/**
	 * 进入会员搜索页面
	 * @param butlerTask
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/toSearchMemberList")
	public String toSearchMemberList(ButlerTask butlerTask,String type,ModelMap map){
		//当前登录人
		String loginId = butlerTask.getLoginId();
		map.put("loginId",loginId);
		//
		String url="";
		if("1".equals(type)){
			url = "gcar/butler/getCar/butlerByManager";
		}else{
			url = "gcar/butler/getCar/butlerByMember";
		}
		return url;
	}
	/**
	 * 动态获取会员数据
	 * @param member
	 * @return
	 */
	@RequestMapping(value="/memberList")
	public @ResponseBody Map<String,Object> memberList(Member member){
		this.logger.info("加载动态会员列表开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		List<Member> memberList = this.memberService.findMemberListByCondition(member);
		//前台返值
		obj.put("memberList", memberList);
		return obj;
	}
	
	/**
	 * 通过点击会员取车任务列表
	 * @param loginId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/toButlerTaskListByMember")
	public String toButlerTaskListByMember(ButlerTask butlerTask,ModelMap map){
		this.logger.info("跳转充电任务页面---当前登录管家ID为---"+butlerTask.getLoginId());
		String memberId = butlerTask.getMemberId();
		//查询所有的管家人员列表
		List<Member> butlers = this.memberService.findButlerMemberList();
		//首次进入加载数据列表
		List<ButlerTask> butlerTaskList = this.butlerTaskService.getButlerTaskListForGetCar(butlerTask);
		//前端返回
		map.put("butlers",butlers);	
		map.put("memberId", memberId);
		map.put("loginId",butlerTask.getLoginId());
		map.put("butlerTaskList", butlerTaskList);
		return "gcar/butler/getCar/list";
	}
	
	/**
	 * 跳转取车任务日历
	 * @param loginId
	 * @param map
	 * @return
	 */
	@RequestMapping("/tobutlerCalendar")
	public String tobutlerCalendar(String loginId,String memberId,ModelMap map){
		this.logger.info("跳转取车任务日历---");
		map.put("loginId",loginId); 
		map.put("changeButlerId",memberId); 
		if(StringUtils.isBlank(memberId)){
			memberId=loginId;
		}
		try {
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			map.put("butlerName",member.getName());
			map.put("butlerId",member.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gcar/butler/getCar/butlerCalendar";
	}
	
	/**
	 * 获取取车任务日历数据
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getButlerCalendar")
	@ResponseBody
	public String getButlerCalendar(ButlerTask butlerTask,ModelMap map) throws Exception{
		this.logger.info("获取取车任务日历数据 ---");
		// 替换前段使用的/日期 为- 供后台可解析
		//dateStr = dateStr.replace('/', '-');
		String json = "";
		//查询日历数据用于封装
		List<CarJson> carJsonList = this.getButlerCalendarData(butlerTask, map);
				
	    json = JsonUtil.extractJson(carJsonList);
		this.logger.info(json);
		return json;
	}
	
	private List<CarJson> getButlerCalendarData(ButlerTask butlerTask,ModelMap map) throws Exception{
		String dateStr = "";
		List<CarJson> carJsonList = null;
		Calendar maxDate;
		//当前登录人
		String loginId = butlerTask.getLoginId();
		if(null!=loginId){
			Member member = this.memberService.findMemberByID(Long.parseLong(loginId));
			if(null!= member){
				butlerTask.setOperaterId(member.getSysuserId());
			}
		}
		try {
			dateStr = butlerTask.getQueryTime();
			// 替换前段使用的/日期 为- 供后台可解析
			dateStr = dateStr.replace('/', '-');
			maxDate = getMaxDate(dateStr,true);
			List<Date> dates = getAllTheDateOftheMonth(maxDate.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			carJsonList = new ArrayList<CarJson>();
			for(Date date : dates){
				CarJson car = new CarJson();
				String allDate = sdf.format(date);
				car.setDate(allDate);
				//ButlerTask butlerTask = new ButlerTask();
				/*dateStr = dateStr.replace("-", "/");
				butlerTask.setPlanTime(sdf.parse(dateStr));*/
				butlerTask.setPlanTime(date);
				butlerTask.setType(2);
				//查询待分配的所有车辆
				List<ButlerTask> butlerTaskList= this.butlerTaskService.getButlerTaskForCalendar(butlerTask);
				car.setCount(butlerTaskList.size());
				carJsonList.add(car);
				map.put(allDate, butlerTaskList.size());
			}
		} catch (ParseException e) {
			this.logger.error("日期转换异常---");
			e.printStackTrace();
		}
		return carJsonList;
		
		
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
	
	private static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	 private Member getMemberNotice(){
		//给后台基础设置-通知管理设置的通知人发送微信以及app通知
        BasicNotice b = new BasicNotice();
        //查询正常的状态
        b.setStatus(0);
        //查询正常的状态
        b.setNoticeType(2);
        List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
        long memberId;
        Member member_notice = new Member();
        if(CollectionUtils.isNotEmpty(basicNotices)) {
            b = basicNotices.get(0);
            memberId = b.getNoticeUserId();
            try {
                member_notice = this.memberService.findMemberByID(memberId);
            } catch (Exception e) {
                this.logger.error("查询用户异常");
                e.printStackTrace();
            }
        }
        return member_notice;
    }
}
