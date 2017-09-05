package com.hy.gcar.web.wechat.butlerTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hy.common.utils.CookieUtils;
import com.hy.common.utils.DateUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.constant.Constant;
import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.ButlerTaskType;
import com.hy.gcar.entity.*;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;
import com.hy.gcar.service.butlerTask.ButlerTaskSendCarService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.service.transferList.TransferListService;
import com.hy.gcar.utils.ResultUtils;
import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.utils.WeiXinUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * Created by 海易出行 on 2016/9/12.
 * 管家任务-送车任务类
 */
@Controller
@RequestMapping("/wechat/butler/task/sendCar")
public class ButlerTaskSendCarController {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ButlerTaskService butlerTaskService;

    @Autowired
    ButlerTaskSendCarService butlerTaskSendCarService;

    @Autowired
    MemberService memberService;
    
    @Autowired
    ItemService itemService;

    @Autowired
    PowerUsedService powerUsedService;
    
    @Autowired
    MemberItemService memberItemService;

    @Autowired
    MemberItemCartypeService memberItemCartypeService;

    @Autowired
    CarOperateService carOperateService;
    
    @Autowired
    CarOperatePlanService carOperatePlanService;

    @Autowired
    private ButlerTaskStatusLogService butlerTaskStatusLogService;

    @Autowired
    BasicThresholdService basicThresholdService;

    @Autowired
    TransferListService  transferListService;

    @Autowired
    private ItemCartypeService itemCartypeService;

    @Autowired
    private CarTypeService carTypeService;
    
    @Autowired
    private MemberItemShareService memberItemShareService;

    @Autowired
    private BasicRestrictionService basicRestrictionService;

    @Autowired
    private BasicNoticeService basicNoticeService;

    /**
     * 管家任务送车任务列表
     * @param butlerTaskFrom
     * @param map
     * @return
     */
    @RequestMapping("/toButlerTaskSendCarList")
    public String toButlerTaskSendCarList(ButlerTask butlerTaskFrom,ModelMap map){
        String operaterId = butlerTaskFrom.getOperaterId();


        ButlerTask butlerTask = new ButlerTask();
        if(StringUtils.isNotEmpty(operaterId)){
            butlerTask.setOperaterId(operaterId);
        }
        butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
        if(StringUtils.isEmpty(butlerTaskFrom.getPlanTimeQuery())){
           butlerTask.setPlanTimeQuery(DateFormatUtils.format(new Date(),"yyyy-MM"));
        }
        //根据计划执行时间  - 送车任务类型- 管家id
        List<ButlerTask> butlerTasks = butlerTaskService.getButlerTasksBySendCar(butlerTask);

        //获取查询时间的最大天数
        String[] dates =  butlerTask.getPlanTimeQuery().split("-");
        int maxDay = DateUtils.getMaxDay(Integer.valueOf(dates[0]),Integer.valueOf(dates[1]));

        //根据查询的list 和 当月最大的天数 以日历的方式组装数据结构
        List<ButlerTask> btCalendarList = butlerTaskService.getButlerTaskCalendarList(butlerTasks,maxDay);
        map.put("btCalendarList",btCalendarList);

        return "";
    }


    /**
     * 查询送车任务列表
     * @param butlerTask
     * @param map
     * @return
     */
    @RequestMapping("/toTaskSendCarList")
    public String toTaskSendCarList(ButlerTask butlerTask,String butlerId,ModelMap map) throws Exception {
        String operaterId = butlerTask.getOperaterId();
        map.put("operaterId",operaterId);

        //ButlerTask butlerTask = new ButlerTask();
        if(StringUtils.isNotEmpty(operaterId)){
            butlerTask.setOperaterId(operaterId);
        }
        butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
        map.put("queryTime", butlerTask.getQueryTime());
        String butlerSysuserId ="";
        if(null!=butlerId && !"".equals(butlerId)){
            Member member = this.memberService.findMemberByID(Long.parseLong(butlerId));
            butlerSysuserId = member.getSysuserId();
            butlerTask.setOperaterId(butlerSysuserId);
        }
        List<ButlerTask> butlerTasks = butlerTaskService.getButlerTask(butlerTask);
        map.put("butlerSysuserId",butlerSysuserId);
        map.put("butlerTasks",butlerTasks);
        //查询所有的管家人员列表
        List<Member> butlers = this.memberService.findButlerMemberList();
        map.put("butlers",butlers);

        return "gcar/butler/sendcar/list";
    }

    /**
     * 查询送车任务列表
     * @param butlerTask
     * @return
     */
    @RequestMapping("/taskSendCarList")
    public @ResponseBody Map<String,Object> taskSendCarList(ButlerTask butlerTask){

        try {
            String operaterId = butlerTask.getOperaterId();
            //ButlerTask butlerTask = new ButlerTask();
            if(StringUtils.isNotEmpty(operaterId)){
                butlerTask.setOperaterId(operaterId);
            }
            butlerTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
//            if(StringUtils.isEmpty(butlerTaskFrom.getPlanTimeQuery())){
//                butlerTask.setPlanTimeQuery(DateFormatUtils.format(new Date(),"yyyy-MM"));
//            }
          
            List<ButlerTask> butlerTaskList = butlerTaskService.getButlerTask(butlerTask);
            return ResultUtils.success(butlerTaskList);
        } catch (Exception e) {
            this.logger.error("服务器异常",e);
            return ResultUtils.fail("服务器异常");
        }
    }

    /**
     * 编辑送车任务
     * @return
     */
    @RequestMapping("/editSendCarTask")
    public @ResponseBody Map<String,Object> editSendCarTask(ButlerTask butlerTask){
//        ButlerTask bt = butlerTaskService.getButlerTaskById(butlerTask.getId());
        try {
            Integer status = butlerTask.getStatus();
            ButlerTask butlerTask_old = butlerTaskService.getButlerTaskById(butlerTask.getId());
            butlerTask.setPowerUsedId(butlerTask_old.getPowerUsedId());
            String remark = butlerTask.getRemark();
            if(remark == null){
            	remark = "";
            }
            if(butlerTask.getPlanTime()==null){
            	butlerTask.setPlanTime(butlerTask_old.getPlanTime());
            }
           //butlerTask.setRemark(butlerTask_old.getRemark() + "||" + remark);
            if(status == null){//跟进中的状态
            	return sendCarButlerTaskFollowUp(butlerTask, butlerTask_old);
            }
            String fail = "该任务已被修改";
            switch (status.intValue()){
                case 0://待送车 客服处理待送车
                    if(butlerTask_old.getStatus().intValue() != ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_ORDERS.getIndex()){
                       // return ResultUtils.fail("当前任务状态为"+ButlerTaskStatus.getStatus(butlerTask_old.getStatus()) + ",不允许确认送车");
                        return ResultUtils.fail(fail);
                    }
//                    butlerTask.setPowerUsedId(butlerTask_new.getPowerUsedId());
//                    butlerTaskService.updateSendCarButlerTaskByWaitingForACar(butlerTask);
                    butlerTaskSendCarService.updateWaitForSendCar(butlerTask_old);
                    break;
                case 1://出发送车 验证选车是否合法
                    if(butlerTask_old.getStatus().intValue() != ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_SEND_FOR_A_CAR.getIndex()){
                        //return ResultUtils.fail("当前任务状态为:"+ButlerTaskStatus.getStatus(butlerTask_old.getStatus()) + ",不允许出发送车");
                        return ResultUtils.fail(fail);
                    }
        		   if(butlerTask.getCarOperateId() != null){
            	       boolean isLegitimate = carOperateService.checkVehicleLegitimate(butlerTask,butlerTask_old);
            	       if(isLegitimate == false){
                           return ResultUtils.fail("车辆已经被使用，请重新选择车辆");
            	       }
            	       CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
                       boolean result = checkLimitNumber(butlerTask, carOperate);
                       if(result){
                           return ResultUtils.fail("该车辆限行，请重新选择车辆");
                       }
                   }
                    butlerTaskService.updateSendCarButlerTaskBySending(butlerTask);
                    break;
                case 4://已到达
                    if(butlerTask_old.getStatus().intValue() != ButlerTaskStatus.BUTLER_TASK_STATUS_SEND_CAR.getIndex()){
                        //return ResultUtils.fail("当前任务状态为:"+ButlerTaskStatus.getStatus(butlerTask_old.getStatus()) + ",不允许已到达");
                        return ResultUtils.fail(fail);
                    }
                    butlerTaskService.updateSendCarButlerTaskByArrived(butlerTask);
                    break;
                case 7://确认送达
                    if(butlerTask_old.getStatus().intValue() != ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex()){
                        //return ResultUtils.fail("当前任务状态为:"+ButlerTaskStatus.getStatus(butlerTask_old.getStatus()) + ",不允许确认送达");
                        return ResultUtils.fail(fail);
                    }
                    butlerTaskService.updateSendCarButlerTaskByComplete(butlerTask);
                    break;
                case 8://取消
                    butlerTaskService.updateSendCarButlerTaskByCancel(butlerTask);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail("修改失败");
        }
        return ResultUtils.success("修改成功！");

    }

    private boolean checkLimitNumber(ButlerTask butlerTask, CarOperate carOperate) {
        //验证是否限号
        BasicRestriction basicRestriction = new BasicRestriction();
        basicRestriction.setCityId(2);
        basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd")));
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

    /**
     * 送车任务跟进中处理
     * @param butlerTask
     * @param butlerTask_old
     * @return
     * @throws Exception
     */
	private Map<String, Object> sendCarButlerTaskFollowUp(ButlerTask butlerTask, ButlerTask butlerTask_old)throws Exception {
		//待处理
		if(butlerTask_old.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_ORDERS.getIndex()){
		    //butlerTaskService.updateWaitingForACar(butlerTask);
            butlerTaskSendCarService.updateSendCarHousekeeper(butlerTask);
		}else if(butlerTask_old.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_SEND_FOR_A_CAR.getIndex()){//待送车
		   //验证选车是否合法
		   if(butlerTask.getCarOperateId() !=null){
		       boolean isLegitimate = carOperateService.checkVehicleLegitimate(butlerTask,butlerTask_old);
		       if(isLegitimate == false){
		           return ResultUtils.fail("车辆已经被使用，请重新选择车辆");
		       }
               CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
               boolean result = checkLimitNumber(butlerTask, carOperate);
               if(result){
                   return ResultUtils.fail("该车辆限行，请重新选择车辆");
               }
		   }
		    butlerTaskService.updateSendCarButlerTaskByNoStatus(butlerTask);
		}else{
			butlerTaskService.updateButlerTaskByNoStatus(butlerTask);
		}
		return ResultUtils.success("修改成功！");
	}


    /**
     * 进入编辑送车任务页面
     * @return
     */
    @RequestMapping("/toEditSendCarTask")
    public String toEditSendCarTask(String code, Long taskId,String from,ModelMap map, HttpServletRequest request, HttpServletResponse response){
        String openidKey = Constant.load.getProperty("openidKey");
        String openid = CookieUtils.getCookie(request, openidKey);
//      openid = "oFCx8wI3qhZfypezKc-_4qAE87Dc11";
        //openid = "oFCx8wJ6CRZPr_UB4A0plIi7cM_w";
        //openid = "oFCx8wPAwdNodVvoBhDQaKVDBHac";
        try {
            if(StringUtils.isBlank(openid)){
                if(StringUtils.isBlank(code)){
                    String url_prefix = Constant.load.getProperty("url_prefix");
                    String newurl = URLEncoder.encode(url_prefix + "/wechat/butler/task/sendCar/toEditSendCarTask?taskId="+taskId, "UTF-8");
                    String redirectPath = WeiXinUtils.getUrlBySnsapibase(newurl);
                    return "redirect:" + redirectPath;
                }
                W3AccessToken acctoken  =   WeiXinUtils.getAccToken(code);
                openid = acctoken.getOpenid();
                CookieUtils.setCookie(response, openidKey, openid,30*60*1000);
            }
            Member m = memberService.findMemberByOpenID(openid);
            if(m == null){
                map.put("error","用户不存在");
                return "gcar/butler/sendcar/info";
            }
//            if(m.getIsButler() != 1){
//                map.put("error","您不是管家，无法查看和操作当前任务");
//                return "gcar/butler/sendcar/info";
//            }
            if(taskId == null){
                map.put("error","参数不正确");
                return "gcar/butler/sendcar/info";
            }
            map.put("member",m);
            ButlerTask butlerTask = butlerTaskService.getButlerTaskById(taskId);
            if(butlerTask == null){
                map.put("error","没有任务");
                return "gcar/butler/sendcar/info";
            }
            //判断登录的管家的id 和任务的执行管家id 是否一致
            boolean isUpdate = true;
            if(m.getSysuserId() == null){
                isUpdate = false;
            }else{
                isUpdate = m.getSysuserId().equals(butlerTask.getOperaterId());
            }

            //根据任务ID查询任务详情
            ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
            butlerTaskStatusLog.setTaskId(butlerTask.getId());
            List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog);
            //根据 car_operate_id 查询车辆信息
            CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
            map.put("carOperate",carOperate);
            map.put("logList", butlerTaskStatusLogList);
            map.put("butlerTask",butlerTask);
            //查询所有的管家人员列表
            List<Member> butlers = this.memberService.findButlerMemberList();
            map.put("butlers",butlers);
//            //查询所有的客服人员列表
//            List<Member> customers = this.memberService.findCustomerMemberList();
//            map.put("customers",customers);

            TransferList t = new TransferList();
            t.setTaskId(taskId);
            t.setType(0);
            Integer transferCount = transferListService.selectCountByCondition(t);
            map.put("transferCount",transferCount);

            t.setType(1);
            Integer carCount = transferListService.selectCountByCondition(t);
            map.put("carCount",carCount);

            BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
            map.put("basicThreshold",basicThreshold);

            //查询车型
            PowerUsed powerUsed = new PowerUsed();
            powerUsed.setId(butlerTask.getPowerUsedId());
            powerUsed = powerUsedService.findById(powerUsed);
            map.put("powerUsed",powerUsed);
            CarType carType = new CarType();
            carType.setId(powerUsed.getCarTypeId());
            carType = carTypeService.findById(carType);
            map.put("carType",carType);
            map.put("from", from);


            if(butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex() ||
                    butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex()){
                return "gcar/butler/sendcar/info";
            }
            
//            //判断如果是待处理 状态 只能是当前登录客服和任务的执行客服一致才能编辑，否则只能查看，管家只能查看
//            if(butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_TREATMENT.getIndex()){
//                if(StringUtils.isEmpty(butlerTask.getCustomerId())){
//                	isUpdate = false;
//                }else{
//                	isUpdate = butlerTask.getCustomerId().equals(m.getSysuserId());
//                }
//            }
            if(butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_PENDING_ORDERS.getIndex().intValue()){
                String plan_time = DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd");
                butlerTask.setQueryBegin(DateUtils.parseDate(plan_time + " 00:00:00"));
                butlerTask.setQueryEnd(DateUtils.parseDate(plan_time + " 23:59:59"));
                List<ButlerTask> butlerTasks = this.butlerTaskService.selectHaveInHandButlerTaskByPlanTime(butlerTask);
                map.put("butlerTasks", butlerTasks);
                //进判断当前任务的管家是不是 后台设置送车任务管家
                Member member = this.getMemberNotice();
                boolean isFillInNotes   = butlerTask.getOperaterId().equals(member.getSysuserId());
                map.put("isFillInNotes", isFillInNotes);
                //进入待接单页面
                map.put("isUpdate",isUpdate);
                return "gcar/butler/sendcar/pendingOrders";
            }
            if(isUpdate){
                //执行编辑页面
                return "gcar/butler/sendcar/edit";
            }
            //进入详情页面
            return "gcar/butler/sendcar/info";
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private Member getMemberNotice(){
    //给后台基础设置-通知管理设置的通知人发送微信以及app通知
        BasicNotice b = new BasicNotice();
        //查询正常的状态
        b.setStatus(0);
        //查询正常的状态
        b.setNoticeType(1);
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
    /**
     * 获取可选择车辆
     * @param butlerTask
     * @return
     */
    @RequestMapping("/getAvailableVehicles")
    public @ResponseBody Map<String,Object> getAvailableVehicles(ButlerTask butlerTask){
        //查询所有可使用车辆列表

        List<CarOperate> carOperates = carOperateService.selectAvailableVehiclesCar(butlerTask);
        List<Long> carOperateIds = Lists.newArrayList();
//      根据当前时间查询运营状态
        if(CollectionUtils.isNotEmpty(carOperates)){
        	int readyTime = 0;
        	BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
        	if(basicThreshold!=null && basicThreshold.getReadyTime()!=null){
        		readyTime = basicThreshold.getReadyTime();
        	}
        	for(CarOperate carOperate:carOperates){
        		carOperateIds.add(carOperate.getId());
        	}
        	//查询入库时间
        	List<CarOperatePlan> carOperatePlans = carOperatePlanService.selectCarOperateStateByNow(carOperateIds);
    		for(CarOperate carOperate:carOperates){
	        	for(CarOperatePlan carOperatePlan:carOperatePlans){
	        			if(carOperatePlan.getOperateId().equals(carOperate.getId())){
	        				carOperate.setOperatePlanStatus(carOperatePlan.getOperateStatus());
	        				this.logger.info(JSON.toJSONString(carOperatePlan));
	        				if(carOperatePlan.getPowerUsedId()!=null){//正常的任务需要加整备时间
		        				carOperate.setEndTime(DateUtils.addHours(carOperatePlan.getEnded(),readyTime));
	        				}else{//平台运营不需要加整备时间
		        				carOperate.setEndTime(carOperatePlan.getEnded());
	        				}
	        				this.logger.info(JSON.toJSONString(carOperate));
	        			}
	        		}
        	}

        	//车辆列表加上限号时间
            BasicRestriction basicRestriction = new BasicRestriction();
            basicRestriction.setCityId(2);
            basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(butlerTask.getPlanTime(),"yyyy-MM-dd")));
            List<BasicRestriction>   basicRestrictions = basicRestrictionService.selectByCondition(basicRestriction);
            Set<Long> carTypeIdSet = Sets.newHashSet();
            if(CollectionUtils.isNotEmpty(basicRestrictions)){
                basicRestriction = basicRestrictions.get(0);
                String restrictions = basicRestriction.getRestrictions();
                List<String> restrictionList = Arrays.asList(restrictions.split(","));
                String carPlateNum = null;
                String last = null;
                for(CarOperate carOperate:carOperates){
                    carPlateNum = carOperate.getCarPlateNum();
                    last = carPlateNum.substring(carPlateNum.length()-1);
                    if(!isInteger(last)){
                        last = "0";
                    }
                    Integer nowStatus = carOperatePlanService.getCarStatus(carOperate.getId());
                    carOperate.setStatus(nowStatus);
                    if(restrictionList.contains(last)){
                        carOperate.setIsLimitLine(true);
                    }
                    carTypeIdSet.add(carOperate.getCarTypeId());
                }
            }
            //根据车型判断车辆是否限号
            if(CollectionUtils.isNotEmpty(carTypeIdSet)){
                List<CarType> carTypes = carTypeService.selectCarTypeByIds(new ArrayList<Long>(carTypeIdSet));
                for(CarType carType:carTypes){
                    for(CarOperate carOperate:carOperates){
                        if(carType.getId().longValue() == carOperate.getCarTypeId().longValue()){
                            if(carType.getIsRestriction().intValue() == 0){
                                carOperate.setIsLimitLine(false);
                            }
                        }
                    }
                }
            }
        	
        }

        //排序-用户权益车型在前面
        PowerUsed powerUsed = powerUsedService.findById(butlerTask.getPowerUsedId());
        long carTypeId = powerUsed.getCarTypeId();
//        List<CarOperate> carOperate_news = new ArrayList<CarOperate>();
//        for(CarOperate carOperate:carOperates){
//            if(carOperate.getCarTypeId().longValue() == carTypeId){
//                carOperate_news.add(0,carOperate);
//            }else{
//                carOperate_news.add(carOperate);
//            }
//        }
        List<Map<String,Object>> carTypeAndCar = Lists.newArrayList();
        //加载 所有的车型
        List<CarType> carTypes = carTypeService.selectByCondition(new CarType());
        for(CarType carType:carTypes){
            Map<String,Object> map = Maps.newHashMap();
            map.put("carTypeName",carType.getBrand() + carType.getCarType());
            map.put("carTypeId",carType.getId());
            List<CarOperate> lists = Lists.newArrayList();
            for(CarOperate carOperate:carOperates){
                if(carOperate.getCarTypeId().longValue() == carType.getId().longValue()){
                    lists.add(carOperate);
                }
            }
            map.put("carOperates",lists);
            if(carType.getId().longValue() == carTypeId){
                carTypeAndCar.add(0,map);
            }else{
                carTypeAndCar.add(map);
            }
        }
        return ResultUtils.success(carTypeAndCar);
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
     * 跳转到请输入管家电话页面
     * @return
     */
    @RequestMapping("/housekeeperPage")
    public String  selectHousekeeperPage(){
        return "";
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
			url = "gcar/butler/sendcar/butlerByManager";
		}else{
			url = "gcar/butler/sendcar/butlerByMember";
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
     * 校验创建送车任务的会员是否合法
     * 判断该会员是否已被冻，结若被冻结则弹窗提示该会员已被冻结，无法创建任务
     * 判断该会员是否通过用车审核，若没有通过用车审核则弹窗提示该会员未通过用车审核，无法创建任务
     * 若会员用车任务大于等于启用车位数量，则不可新建任务，弹窗提示“该用户正在进行任务，无法创建新任务”
     * @param memeberID
     * @return
     */
    @RequestMapping("checkCreateSendCarMember")
    public @ResponseBody Map<String,Object> checkCreateSendCarMember(Long memeberID){

        try {
            Member member = memberService.findMemberByID(memeberID);
            String result = butlerTaskService.checkCreateSendCarButlerTask(member);
            if(result != null){
               return ResultUtils.fail(result);
            }
        } catch (Exception e) {
           return ResultUtils.fail("服务器异常...");
        }
        return ResultUtils.success(true);

    }

    /**
     * 跳转到创建送车任务页面
     * @return
     */
    @RequestMapping("/createButlerTaskBySendCarPage")
    public String createButlerTaskBySendCarPage(ButlerTask butlerTask,ModelMap map){
        Member member = new Member();
        try {
            member = memberService.findMemberByID(Long.parseLong(butlerTask.getMemberId()));
//            memberItem.setMemberId(Long.parseLong(butlerTask.getMemberId()));
//            List<MemberItem> memberItemList = this.memberItemService.selectByCondition(memberItem);
//            if(memberItemList.size()>0){
//            	memberItem = memberItemList.get(0);
//            	//该会员权益车型
//            	memberItemCarType.setMemberItemId(memberItem.getId());
//            	memberItemCarTypes = this.memberItemCartypeService.selectByCondition(memberItemCarType);
//            	//套餐车型
//            	itemCartype.setItemId(memberItem.getItemId());
//            	List<ItemCartype> itemCartypeList = this.itemCartypeService.selectByCondition(itemCartype);
//            	if(itemCartypeList.size()>0){
//            		for(ItemCartype itemCart:itemCartypeList){
//            			for(MemberItemCartype memberItemCarTy:memberItemCarTypes){
//            				if(){
//
//            				}
//            			}
//            		}
//            	}
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("loginId", butlerTask.getLoginId());
        map.put("member",member);

        BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
        map.put("basicThreshold",basicThreshold);
        return "gcar/butler/sendcar/createButler";
    }

    /**
     * 创建送车任务
     * @param butlerTask
     * @return
     */
    @RequestMapping("/createButlerTaskBySendCar")
    public @ResponseBody Map<String,Object> createButlerTaskBySendCar(ButlerTask butlerTask){
        try {
            Member member = memberService.findMemberByID(Long.valueOf(butlerTask.getMemberId()));
            String result = butlerTaskService.checkCreateSendCarButlerTask(member);
            if(result != null){
                return ResultUtils.fail(result);
            }

          /*判断用户当前余额，若余额小于车辆一日使用费（小于车辆日使用费、0、负数），则弹窗提示“该用户余额不足，无法创建任务*/
            result = butlerTaskService.checkSendCarButlerTaskMemberBalance(butlerTask);
            if(result != null){
                return ResultUtils.fail(result);
            }


            butlerTask.setMemberMobile(member.getMobile());
            butlerTask.setMemberName(member.getName());

            ButlerTask sendCarButlerTask = butlerTaskService.createSendCarButlerTask(butlerTask);
            return ResultUtils.success(sendCarButlerTask);
        }catch (Exception e){
            logger.error("创建送车任务异常---",e);
            return ResultUtils.fail("error", "操作异常");
        }
    }

    @RequestMapping("/isStore")
    @ResponseBody
    public Map<String,Object> isStore(ButlerTask butlerTask){
        boolean isStore = carOperatePlanService.isStore(butlerTask.getCarTypeId(),butlerTask.getPlanTime(),butlerTask.getCompleteTime());
        if(isStore ==  false){
            return ResultUtils.fail("您选择的车型无库存");
        }
        return ResultUtils.success("您选择的车型有库存");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gcar/butler/sendcar/butlerCalendar";
	}
	
    /**
	 * 获取取车任务日历数据
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getButlerCalendar")
	@ResponseBody
	public String getButlerCalendar(ButlerTask butlerTask,ModelMap map) throws Exception{
		this.logger.info("获取送车任务日历数据 ---");
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
				butlerTask.setType(3);
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
	
	/**
	 * 检验用户是否被冻结
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/checkMemberStatuts")
	@ResponseBody
	public Object checkMemberStatuts(String memberId){
		logger.info("校验用户是否被冻结 memberId"+memberId);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		try {
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			if(member!=null ){
				if(member.getStatuts()==0){
					//被冻结
					return true;
				}else{
					return false;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
     /**
      * 根据二维码跳转到任务详情页面
     * @Description:TODO      
     * @author:JIAZHIPENG  
     * @time:2016-10-20 下午5:06:14   
     * @return String
      */
	@RequestMapping(value="/toEditTaskByQRCode")
	public String toEditTaskByQRCode(String code, Long taskId, ModelMap map, HttpServletRequest request, HttpServletResponse response){
		
		return toEditCarTask(code, taskId, map, request, response);
	}	 
	
	/**
	 * 点击查看取车任务详情
	 * @param butlerTask
	 * @param map
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private String butlerTaskGetDetail(ButlerTask butlerTask,ModelMap map) throws Exception{
		String queryTime = butlerTask.getQueryTime();
		//当前登录人
		String loginId = butlerTask.getLoginId();
		
		//查询当前登录人的信息
		Member member = this.memberService.findMemberByID(Long.parseLong(loginId));
		//根据任务ID查询当前任务的详情
		butlerTask = this.butlerTaskService.getButlerTaskById(butlerTask.getId());
	
		//判断如果是已完成或者是已取消或者当前管家不是当前任务的负责人的情况进入查看详情的页面，无法编辑
		if(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex()==butlerTask.getStatus() || ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex()==butlerTask.getStatus()
				|| ButlerTaskStatus.BUTLER_TASK_STATUS_LOST_CONTACT.getIndex()==butlerTask.getStatus() || (!member.getSysuserId().equals(butlerTask.getOperaterId()))){
			//跳转详情页
			return "redirect:/wechat/butlerTask/getCar/detailInfo?id="+butlerTask.getId()+"&loginId="+loginId+"&queryTime="+queryTime;
		}
		
		BasicThreshold basicThreshold = new BasicThreshold();
		
		//带取车时，需要查服务时间
		basicThreshold = this.basicThresholdService.selectBasicThreshold();
		this.logger.info("服务开始时间--"+basicThreshold.getServiceTimeStart());
		this.logger.info("服务结束时间--"+basicThreshold.getServiceTimeEnd());
		
		//需要取出对应的送车任务
		ButlerTask sendTask = new ButlerTask();
		sendTask.setPowerUsedId(butlerTask.getPowerUsedId());
		sendTask.setType(ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex());
		List<ButlerTask> sendTaskList = this.butlerTaskService.getButlerTask(sendTask);
		if(sendTaskList.size()>0){
			sendTask = sendTaskList.get(0);
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
		
		//如果不满足上述情况，则进入的是修改页面
		
		//查询所有的管家人员列表
		List<Member> butlers = this.memberService.findButlerMemberList();
		
		//前台返值
		map.put("sendTask", sendTask);
		map.put("basicThreshold", basicThreshold);
		map.put("butlers", butlers);
		map.put("loginId",loginId);
		map.put("queryTime", queryTime);
		map.put("butlerTask", butlerTask);
		map.put("carOperateMessage", carOperateMessage);
		map.put("logList", butlerTaskStatusLogList);
		
		return "gcar/butler/getCar/butlerInfo";
	}
	
	/**
	 * 送车任务详情页
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-10-20 下午5:46:30   
	* @return String
	 */
	private String butlerTaskSendDetail(ButlerTask butlerTask,Long taskId, Member m ,ModelMap map){
		 //判断登录的管家的id 和任务的执行管家id 是否一致
        boolean isUpdate = true;
        if(m.getSysuserId() == null){
            isUpdate = false;
        }else{
            isUpdate = m.getSysuserId().equals(butlerTask.getOperaterId());
        }
        //根据任务ID查询任务详情
        ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
        butlerTaskStatusLog.setTaskId(butlerTask.getId());
        List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog);
        //根据 car_operate_id 查询车辆信息
        CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
        map.put("carOperate",carOperate);
        map.put("logList", butlerTaskStatusLogList);
        map.put("butlerTask",butlerTask);
        //查询所有的管家人员列表
        List<Member> butlers = this.memberService.findButlerMemberList();
        map.put("butlers",butlers);


        TransferList t = new TransferList();
        t.setTaskId(taskId);
        t.setType(0);
        Integer transferCount = transferListService.selectCountByCondition(t);
        map.put("transferCount",transferCount);

        t.setType(1);
        Integer carCount = transferListService.selectCountByCondition(t);
        map.put("carCount",carCount);

        BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
        map.put("basicThreshold",basicThreshold);

        //查询车型
        PowerUsed powerUsed = new PowerUsed();
        powerUsed.setId(butlerTask.getPowerUsedId());
        powerUsed = powerUsedService.findById(powerUsed);
        map.put("powerUsed",powerUsed);

        CarType carType = new CarType();
        carType.setId(powerUsed.getCarTypeId());
        carType = carTypeService.findById(carType);
        map.put("carType",carType);

        if(butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex() ||
                butlerTask.getStatus().intValue() == ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_CANCELED.getIndex()){
            return "gcar/butler/sendcar/info";
        }
        if(isUpdate){
            //执行编辑页面
            return "gcar/butler/sendcar/edit";
        }

        //进入详情页面
        return "gcar/butler/sendcar/info";
	}
	
	/**
	 * 点击查看充电任务详情
	 * @param butlerTask
	 * @param map
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private String butlerTaskChargeDetail(ButlerTask butlerTask,ModelMap map) throws Exception{
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
     * 进入编辑任务页面
     * @return
     */
    private String toEditCarTask(String code, Long taskId, ModelMap map, HttpServletRequest request, HttpServletResponse response){
        String openidKey = Constant.load.getProperty("openidKey");
        String openid = CookieUtils.getCookie(request, openidKey);
//      openid = "oFCx8wI3qhZfypezKc-_4qAE87Dc";
        try {
            if(StringUtils.isBlank(openid)){
                if(StringUtils.isBlank(code)){
                    String url_prefix = Constant.load.getProperty("url_prefix");
                    String newurl = URLEncoder.encode(url_prefix + "/wechat/butler/task/sendCar/toEditTaskByQRCode?taskId="+taskId, "UTF-8");
                    String redirectPath = WeiXinUtils.getUrlBySnsapibase(newurl);
                    return "redirect:" + redirectPath;
                }
                W3AccessToken acctoken  =   WeiXinUtils.getAccToken(code);
                openid = acctoken.getOpenid();
                CookieUtils.setCookie(response, openidKey, openid,30*60*1000);
            }
            Member m = memberService.findMemberByOpenID(openid);
            if(m == null){
                // map.put("error","用户不存在");
                //return "gcar/butler/sendcar/info";
                //关注页面
                map.put("biz", Constant.biz);
                return "gcar/carOperate/toWeChat"; 
            }
            if(m.getFocusType() == 0){
               //map.put("error","您已取消关注,无法查看和操作当前任务");
               // return "gcar/butler/sendcar/info";
               //关注页面
               map.put("biz", Constant.biz);
               return "gcar/carOperate/toWeChat"; 
            }
            if(m.getIsButler() != 1){
            	//无权限页面,待开发
                //map.put("error","您不是管家，无权限页面,待开发");
                //return "gcar/butler/sendcar/info";
                return "gcar/carOperate/notPowers";
            }
            if(taskId == null){
                map.put("error","参数不正确");
                return "gcar/butler/sendcar/info";
            }
            map.put("member",m);
            ButlerTask butlerTask = butlerTaskService.getButlerTaskById(taskId);
            if(butlerTask == null){
                map.put("error","没有任务");
                return "gcar/butler/sendcar/info";
            }
            butlerTask.setLoginId(m.getId().toString());
            //取车任务
            if(butlerTask.getType() == ButlerTaskType.BUTLER_TASK_TYPE_GETCAR.getIndex()){
            	//map = new ModelMap();
            	return butlerTaskGetDetail(butlerTask, map);
            	
            //送车任务	
            }else if(butlerTask.getType() == ButlerTaskType.BUTLER_TASK_TYPE_RETURNCAR.getIndex()){
            	//map = new ModelMap();
            	return butlerTaskSendDetail(butlerTask,taskId,m, map);
            //充电任务
            }else{
            	//map = new ModelMap();
            	return butlerTaskChargeDetail(butlerTask, map);
            }
           
        }  catch (Exception e) {
            logger.info("后台扫描二维码进入任务详情页错误"+e.getMessage());
        }
        return "";
    }
    
    
    /**
	 * 查询某时间内车型库存
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/getStoreOfCartype")
	@ResponseBody
	public Map<String,Object> getStoreOfCartypeForAndroid(String memberId,String useCarStartTime,String useCarEndTime){
		// 解析json数据
		logger.info("---调用----用车发起页面确定按钮(查询是否是我的启用车型)------");

        MemberItemShare  memberItemShare = memberItemShareService.selectByMemberId(Long.valueOf(memberId));

		Map<String,Object> returnMap = null;
		try{
			returnMap = carTypeService.getStoreOfCartype(memberItemShare.getMemberItemId().toString(), useCarStartTime, useCarEndTime);
            this.logger.info("returnMap" + JSON.toJSONString(returnMap));
		} catch (Exception e) {
			e.printStackTrace();
			return  ResultUtils.fail("查询启用车型失败");
		}
        return  ResultUtils.success("查询启用车型成功",returnMap);
	}



	@RequestMapping(value = "/getDate")
	public String getDate(){
        return "gcar/butler/sendcar/calendarSchedule";
    }


    @RequestMapping(value = "/getServiceTime")
    public @ResponseBody  Map<String,Object> getServiceTime(){
        Map<String,Object> returnMap = Maps.newHashMap();
        BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
        if(null!=basicThreshold){
            returnMap.put("serviceTimeEnd", basicThreshold.getServiceTimeEnd());
            returnMap.put("serviceTimeStart", basicThreshold.getServiceTimeStart());
            returnMap.put("userUsecarAdvance", basicThreshold.getUserUsecarAdvance());
        }
        return ResultUtils.success(returnMap);
    }
}
