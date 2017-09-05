package com.hy.gcar.web.wechat.butlerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.member.MemberService;

/**
 * 充电任务controller
 * @author gbw
 *
 */

@Controller
@RequestMapping("/wechat/butlerTask/charging")
public class ButlerTaskChargingController {

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
	private BasicThresholdService basicThresholdService;
	
	/**
	 * 跳转充电任务列表
	 * @param loginId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/toButlerTaskList")
	public String toButlerTaskList(String loginId,ModelMap map){
		this.logger.info("跳转充电任务页面---当前登录管家ID为---"+loginId);
		//查询所有的管家人员列表
		List<Member> butlers = this.memberService.findButlerMemberList();
		map.put("butlers",butlers);

		//首次进入加载数据列表
		ButlerTask butlerTask = new ButlerTask();
		List<ButlerTask> butlerTaskList = this.butlerTaskService.getButlerTaskListForCharging(butlerTask);
		//前端返回
		map.put("butlers",butlers);		
		map.put("loginId",loginId);
		map.put("butlerTaskList", butlerTaskList);
		return "gcar/butler/charging/list";
	}
	
	/**
	 * 动态加载充电任务列表
	 * @param butlerTask
	 * @return
	 */
	@RequestMapping(value="/butlerTaskList")
	public @ResponseBody Map<String,Object> butlerList(ButlerTask butlerTask){
		this.logger.info("加载动态加载任务开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		List<ButlerTask> butlerTaskList = this.butlerTaskService.getButlerTaskListForCharging(butlerTask);
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
		//当前登录人
		String loginId = butlerTask.getLoginId();
		//根据任务ID查询当前任务的详情
		butlerTask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
		//查询当前登录人的信息
		Member member = this.memberService.findMemberByID(Long.parseLong(loginId));
		//判断如果是已完成或者是已取消或者当前管家不是当前任务的负责人的情况进入查看详情的页面，无法编辑
		if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex() == butlerTask.getStatus() || ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex()==butlerTask.getStatus() || (!member.getSysuserId().equals(butlerTask.getOperaterId()))){
			//跳转详情页
			return "redirect:/wechat/butlerTask/charging/detailInfo?id="+butlerTask.getId()+"&loginId="+loginId;
		}
				//如果不满足上述情况，则进入的是修改页面
		BasicThreshold basicThreshold = new BasicThreshold();
		if(2==butlerTask.getStatus()){
			//带取车时，需要查服务时间
			basicThreshold = this.basicThresholdService.selectBasicThreshold();
			this.logger.info("服务开始时间--"+basicThreshold.getServiceTimeStart());
			this.logger.info("服务结束时间--"+basicThreshold.getServiceTimeEnd());
		}
		//根据任务ID查询任务详情
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		butlerTaskStatusLog.setTaskId(butlerTask.getId());
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog);
		//查询车辆信息
		CarOperate carOperate = new CarOperate();
		String carOperateMessage ="";
		if(null!=butlerTask.getCarOperateId()){
			carOperate = this.carOperateService.findById(butlerTask.getCarOperateId());
			carOperateMessage = carOperate.getCarBrand()+" "+carOperate.getCarType()+" "+carOperate.getCarPlateNum();
		}
		
		//查询所有的管家人员列表
		List<Member> butlers = this.memberService.findButlerMemberList();
		
		//前台返值
		map.put("basicThreshold", basicThreshold);
		map.put("butlers", butlers);
		map.put("loginId",loginId);
		map.put("butlerTask", butlerTask);
		map.put("carOperateMessage", carOperateMessage);
		map.put("logList", butlerTaskStatusLogList);
		
		return "gcar/butler/charging/butlerInfo";
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
		//当前登录人
		String loginId = butlerTask.getLoginId();
		//根据任务ID查询当前任务的详情
		butlerTask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
		//查询车辆信息
		CarOperate carOperate = new CarOperate();
		String carOperateMessage ="";
		if(null!=butlerTask.getCarOperateId()){
			carOperate = this.carOperateService.findById(butlerTask.getCarOperateId());
			carOperateMessage = carOperate.getCarBrand()+" "+carOperate.getCarType()+" "+carOperate.getCarPlateNum();
		}
		//根据任务ID查询任务详情
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		butlerTaskStatusLog.setTaskId(butlerTask.getId());
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog);
		//查询所有的管家人员列表
		List<Member> butlers = this.memberService.findButlerMemberList();
		
		//前台返值
		map.put("butlers", butlers);
		map.put("loginId",loginId);
		map.put("butlerTask", butlerTask);
		map.put("carOperateMessage", carOperateMessage);
		map.put("logList", butlerTaskStatusLogList);
		
		return "gcar/butler/charging/butlerDetailInfo";
	}
	
	/**
	 * 修改充电任务信息
	 * @param butlerTask
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public @ResponseBody Map<String,Object> edit(ButlerTask butlerTask)throws Exception{
		this.logger.info("充电任务修改开始---");
		Map<String, Object> obj = new HashMap<String, Object>();
		//当前登录人
		String loginId = butlerTask.getLoginId();
		try {
			if(null!=butlerTask.getStatus()){
				//有状态的变更情况
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_TAKE_THE_CAR.getIndex() == butlerTask.getStatus()){
					this.butlerTaskService.updateChargingButlerTaskByWay(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_CHARGE_IN.getIndex() == butlerTask.getStatus()){
					this.butlerTaskService.updateChargingButlerTaskByCharging(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_SEND_CAR.getIndex() == butlerTask.getStatus()){
					this.butlerTaskService.updateChargingButlerTaskBySending(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex() == butlerTask.getStatus()){
					this.butlerTaskService.updateChargingButlerTaskByComplete(butlerTask);
				}
				if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex() == butlerTask.getStatus()){
					this.butlerTaskService.updateChargingButlerTaskByCancel(butlerTask);
				}
			}
			else{
				this.butlerTaskService.updateButlerTaskByNoStatus(butlerTask);
			}
			obj.put("loginId", loginId);
			obj.put("butlerTaskId", butlerTask.getId());
			obj.put("result", true);
			obj.put("msg","修改任务成功");
		} catch (Exception e) {
			this.logger.error("修改任务失败---");
			obj.put("result",false );
			obj.put("msg","修改任务失败");
		}
			return obj;
	}
	
}
