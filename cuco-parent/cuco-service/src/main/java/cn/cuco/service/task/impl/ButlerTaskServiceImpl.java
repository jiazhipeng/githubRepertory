package cn.cuco.service.task.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hy.authorization.entity.User;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.ButlerTaskMapper;
import cn.cuco.dao.TransferListMapper;
import cn.cuco.entity.ButlerTask;
import cn.cuco.entity.Car;
import cn.cuco.entity.CarOperatePlan;
import cn.cuco.entity.CarType;
import cn.cuco.entity.Member;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderCarUsed;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.entity.PreAuthorizedDebit;
import cn.cuco.entity.TransferList;
import cn.cuco.enums.ButlerTaskType;
import cn.cuco.enums.MemberCarUsedStatus;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.enums.TaskStatus;
import cn.cuco.enums.TransferType;
import cn.cuco.enums.UserCarType;
import cn.cuco.enums.Valid;
import cn.cuco.enums.WechatMsgType;
import cn.cuco.enums.security.RoleEnum;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.carport.CarTypeService;
import cn.cuco.service.car.carInfo.CarService;
import cn.cuco.service.car.carOperate.CarOperatePlanService;
import cn.cuco.service.car.stock.StockService;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.member.info.MemberService;
import cn.cuco.service.order.OrderCarUsedService;
import cn.cuco.service.order.OrderMemberCarUsedService;
import cn.cuco.service.payment.preAuthorizedDebit.PreAuthorizedDebitService;
import cn.cuco.service.security.SecurityService;
import cn.cuco.service.task.ButlerTaskService;
import cn.cuco.service.wechat.wechatMessage.IWechatMessageSender;

@Service(value = "butlerTaskService")
public class ButlerTaskServiceImpl implements ButlerTaskService {

    @Autowired
    private ButlerTaskMapper<ButlerTask> butlerTaskMapper;
    @Autowired
    private TransferListMapper<TransferList> transferListMapper;
    @Autowired
    private OperateLogService operateLogService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderMemberCarUsedService memberCarUsedService;
    @Autowired
    private CarOperatePlanService carOperatePlanService;
    @Autowired
    private OrderCarUsedService orderCarUsedService;
    @Autowired
    private StockService stockService;
    @Autowired
    private PreAuthorizedDebitService preAuthorizedDebitService;
    @Autowired
    private IWechatMessageSender iWechatMessageSender;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private SecurityService securityService;
    
    protected Logger log = LoggerFactory.getLogger(ButlerTaskServiceImpl.class);
    /**
     * 创建送车任务
     */
	@Override
	public ButlerTask createUserCarSendCarButlerTask(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"创建-送车任务-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getCarUsedId(), "创建任务-送车任务-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getTaskAddress(), "创建任务-送车任务-用车地址不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTime(), "创建任务-送车任务-取车时间不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getMemberId(), "创建任务-送车任务-用户ID不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "创建任务-送车任务-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "创建任务-送车任务-操作人id不能为空");		
		
		butlerTask.setCreateType(Valid.DOWN.getValue());
		//验证用户
		Member member = memberService.getMemberById(butlerTask.getMemberId());
		ParamVerifyUtils.paramNotNull(member, "创建任务,用户不存在");		
		butlerTask.setMemberName(member.getName());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setUseCarType(UserCarType.USERCARTYPE_USERUSECAR.getIndex());
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
		//设置管家负责人
		butlerTask = this.setCustomerAndOperater(butlerTask);
		//创建取车任务
		butlerTask = this.createTask(butlerTask);
		///给客服推送消息
		this.SendMessageToOperater(butlerTask.getCustomerId(), WechatMsgType.SENDCUSTOMERSUCCESS.getType());
		
		return butlerTask;
	}

	/**
	 * 创建取车任务
	 */
	@Override
	public ButlerTask createUserCarGetCarButlerTask(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"创建-取车任务-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getCarUsedId(), "创建任务-取车任务-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getTaskAddress(), "创建任务-取车任务-用车地址不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTime(), "创建任务-取车任务-取车时间不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getMemberId(), "创建任务-取车任务-用户ID不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "创建任务-取车任务-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "创建任务-取车任务-操作人id不能为空");		
		
		butlerTask.setCreateType(Valid.DOWN.getValue());
		//验证用户
		Member member = memberService.getMemberById(butlerTask.getMemberId());
		ParamVerifyUtils.paramNotNull(member, "创建任务,用户不存在");		
		butlerTask.setMemberName(member.getName());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setUseCarType(UserCarType.USERCARTYPE_USERUSECAR.getIndex());
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex());
		//设置管家负责人
		butlerTask = this.setCustomerAndOperater(butlerTask);
		//创建取车任务
		butlerTask = this.createTask(butlerTask);
		///给客服推送消息
		this.SendMessageToOperater(butlerTask.getCustomerId(), WechatMsgType.GETCUSTOMERSUCCESS.getType());
		return butlerTask;
	}

	/**
	 * 创建用户换车送车任务
	 */
	@Override
	public ButlerTask createUserChangeSendCarButlerTask(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"创建-送车任务-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getOldTaskId(), "创建任务-送车任务-换车前任务ID不能为空");		
		ParamVerifyUtils.paramNotNull(butlerTask.getCarUsedId(), "创建任务-送车任务-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getTaskAddress(), "创建任务-送车任务-用车地址不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTime(), "创建任务-送车任务-取车时间不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getMemberId(), "创建任务-送车任务-用户ID不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "创建任务-送车任务-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "创建任务-送车任务-操作人id不能为空");
		
		butlerTask.setCreateType(Valid.DOWN.getValue());
		//验证用户
		Member member = memberService.getMemberById(butlerTask.getMemberId());
		ParamVerifyUtils.paramNotNull(member, "创建任务,用户不存在");		
		butlerTask.setMemberName(member.getName());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setUseCarType(UserCarType.USERCARTYPE_USERCHANGE.getIndex());
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
		//设置管家负责人
		butlerTask = this.setCustomerAndOperater(butlerTask);
		//创建取车任务
		butlerTask = this.createTask(butlerTask);
		return butlerTask;
	}

	/**
	 * 创建用户换车取车任务
	 */
	@Override
	public ButlerTask createUserChangeGetCarButlerTask(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"创建-取车任务-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getOldTaskId(), "创建任务-取车任务-换车前任务ID不能为空");		
		ParamVerifyUtils.paramNotNull(butlerTask.getCarUsedId(), "创建任务-取车任务-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getTaskAddress(), "创建任务-取车任务-用车地址不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTime(), "创建任务-取车任务-取车时间不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getMemberId(), "创建任务-取车任务-用户ID不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "创建任务-取车任务-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "创建任务-取车任务-操作人id不能为空");
		
		butlerTask.setCreateType(Valid.DOWN.getValue());
		//验证用户
		Member member = memberService.getMemberById(butlerTask.getMemberId());
		ParamVerifyUtils.paramNotNull(member, "创建任务,用户不存在");		
		butlerTask.setMemberName(member.getName());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setUseCarType(UserCarType.USERCARTYPE_USERCHANGE.getIndex());
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
		//设置管家负责人
		butlerTask = this.setCustomerAndOperater(butlerTask);
		//创建取车任务
		butlerTask = this.createTask(butlerTask);
		
		///给客服推送消息
		this.SendMessageToOperater(butlerTask.getCustomerId(), WechatMsgType.GETCUSTOMERSUCCESS.getType());
		return butlerTask;
	}

	/**
	 * 创建限号换车送车任务
	 */
	@Override
	public ButlerTask createRestrictionChangeSendCarButlerTask(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"创建-送车任务-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getOldTaskId(), "创建任务-送车任务-换车前任务ID不能为空");		
		ParamVerifyUtils.paramNotNull(butlerTask.getCarUsedId(), "创建任务-送车任务-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getTaskAddress(), "创建任务-送车任务-用车地址不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTime(), "创建任务-送车任务-取车时间不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getMemberId(), "创建任务-送车任务-用户ID不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "创建任务-送车任务-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "创建任务-送车任务-操作人id不能为空");
		
		butlerTask.setCreateType(Valid.DOWN.getValue());
		//验证用户
		Member member = memberService.getMemberById(butlerTask.getMemberId());
		ParamVerifyUtils.paramNotNull(member, "创建任务,用户不存在");		
		butlerTask.setMemberName(member.getName());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setUseCarType(UserCarType.USERCARTYPE_RESTRICTION.getIndex());
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex());
		//设置管家负责人
		butlerTask = this.setCustomerAndOperater(butlerTask);
		//创建取车任务
		butlerTask = this.createTask(butlerTask);
		///给客服推送消息
		this.SendMessageToOperater(butlerTask.getCustomerId(), WechatMsgType.SENDCUSTOMERSUCCESS.getType());
		return butlerTask;
	}

	/**
	 * 创建限号换车取车任务
	 */
	@Override
	public ButlerTask createRestrictionChangeGetCarButlerTask(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"创建-取车任务-任务对象数据不能为空！");
		ParamVerifyUtils.paramNotNull(butlerTask.getOldTaskId(), "创建任务-取车任务-换车前任务ID不能为空");		
		ParamVerifyUtils.paramNotNull(butlerTask.getCarUsedId(), "创建任务-取车任务-用车记录id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getTaskAddress(), "创建任务-取车任务-用车地址不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getPlanTime(), "创建任务-取车任务-取车时间不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getMemberId(), "创建任务-取车任务-用户ID不能为空");	
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "创建任务-取车任务-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "创建任务-取车任务-操作人id不能为空");
		
		butlerTask.setCreateType(Valid.DOWN.getValue());
		//验证用户
		Member member = memberService.getMemberById(butlerTask.getMemberId());
		ParamVerifyUtils.paramNotNull(member, "创建任务,用户不存在");		
		butlerTask.setMemberName(member.getName());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setUseCarType(UserCarType.USERCARTYPE_RESTRICTION.getIndex());
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex());
		//设置管家负责人
		butlerTask = this.setCustomerAndOperater(butlerTask);
		//创建取车任务
		butlerTask = this.createTask(butlerTask);
		//给客服推送消息
		this.SendMessageToOperater(butlerTask.getCustomerId(), WechatMsgType.GETCUSTOMERSUCCESS.getType());
		return butlerTask;
	}
	
	/**
	 * 取任务详情
	 */
	@Override
	public ButlerTask getButlerTaskById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "查询任务详情，id不能为空");
		ButlerTask butlerTask = this.butlerTaskMapper.selectByPrimaryKey(id);
		if(butlerTask!=null){
			TransferList transferList = new TransferList();
			transferList.setTaskId(butlerTask.getId());
			//查询车辆图片数据
			transferList.setType(TransferType.TRANSDERTYPE_RECEIPT.getIndex());
			List<TransferList> receiptList =  this.getButerTaskTransferList(transferList);
			butlerTask.setReceipeImages(receiptList);
			//查询车辆交接单图片数据
			transferList.setType(TransferType.TRANSDERTYPE_CAR.getIndex());
			List<TransferList> carList =  this.getButerTaskTransferList(transferList);
			butlerTask.setCarImages(carList);
			if(butlerTask.getCarOperateId()!=null){
				butlerTask.setCar(carService.getCarById(butlerTask.getCarOperateId()));
			}
			OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
    		orderCarUsed.setId(butlerTask.getCarUsedId());
    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
    		CarType carType = carTypeService.getCarTypeById(orderCarUsed.getOrderCartypeId());
    		butlerTask.setBrand(carType.getBrand());
    		butlerTask.setCarType(carType.getCarType());
    		butlerTask.setDeposit(carType.getDeposit());
    		butlerTask.setCarTypeId(orderCarUsed.getOrderCartypeId());
    		//预授权信息
    		PreAuthorizedDebit preAuthorizedDebit = new PreAuthorizedDebit();
    		preAuthorizedDebit.setCarUsedId(butlerTask.getCarUsedId());
    		preAuthorizedDebit = preAuthorizedDebitService.getPreAuthorizedDebitByUsed(preAuthorizedDebit);
    		if(preAuthorizedDebit!=null){
    			butlerTask.setPreAuthorizedDebitId(preAuthorizedDebit.getId());
    		}
    		if(null != butlerTask.getOperaterId()){
    			User user = securityService.getUserById(butlerTask.getOperaterId());
    			butlerTask.setOperaterMobile(user.getPhone());
    		}
		}
		return butlerTask;
	}

	/**
	 * 任务集合
	 */
	public List<ButlerTask> getButlerTaskList(ButlerTask butlerTask){
		return this.butlerTaskMapper.selectByCondition(butlerTask);
	}
	
	/**
	 * 分页任务
	 */
	@Override
	public PageResult<ButlerTask> getButlerTaskPageForOfficialEnd(ButlerTask butlerTask) {
		Integer page = butlerTask.getPage();
	    Integer pageSize = butlerTask.getPageSize();
	     /*搜索条件*/
	    ButlerTask butlerTaskSearch = new ButlerTask();
        if(StringUtils.isNotBlank(butlerTask.getMemberName())){
        	butlerTaskSearch.setMemberName(butlerTask.getMemberName());
        }
        if(StringUtils.isNotBlank(butlerTask.getMemberMobile())){
        	butlerTaskSearch.setMemberMobile(butlerTask.getMemberMobile());
        }
        if(butlerTask.getType()!=null){
        	butlerTaskSearch.setType(butlerTask.getType());
        }
        if(butlerTask.getStatus()!=null){
        	butlerTaskSearch.setStatus(butlerTask.getStatus());
        }
        if(butlerTask.getOperaterId()!=null){
        	butlerTaskSearch.setOperaterId(butlerTask.getOperaterId());
        }
        //计划时间查询
        if(butlerTask.getPlanTimeStart() != null && butlerTask.getPlanTimeEnd() != null){
        	butlerTaskSearch.setPlanTimeStart(butlerTask.getPlanTimeStart());
        	butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
        }
        //创建时间查询
        if(butlerTask.getCreatedStart() != null && butlerTask.getCreatedEnd() != null){
        	butlerTaskSearch.setCreatedStart(butlerTask.getCreatedStart());
        	butlerTaskSearch.setCreatedEnd(butlerTask.getCreatedEnd());
        }
	    List<ButlerTask> butlerTasks = null;
	    /*总记录数*/
	    Integer totalSize = this.butlerTaskMapper.selectCountForOfficialEndByPage(butlerTaskSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    butlerTasks = this.butlerTaskMapper.selectForOfficialEndByPage(butlerTaskSearch);
	    if(CollectionUtils.isNotEmpty(butlerTasks)){
	    	for (ButlerTask butlerTaskView : butlerTasks) {
	    		OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
	    		orderCarUsed.setId(butlerTaskView.getCarUsedId());
	    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
	    		CarType carType = carTypeService.getCarTypeById(orderCarUsed.getOrderCartypeId());
	    		butlerTaskView.setBrand(carType.getBrand());
	    		butlerTaskView.setCarType(carType.getCarType());
	    	}
	    }
        PageResult<ButlerTask> pageResult = new PageResult<ButlerTask>(page,pageSize,totalSize,butlerTasks);
		return pageResult;
	}
	
	/**
	 * 车务任务分页
	 */
	public PageResult<ButlerTask> getButlerTaskPageForCarService(ButlerTask butlerTask){
	 	Integer page = butlerTask.getPage();
	    Integer pageSize = butlerTask.getPageSize();
	     /*搜索条件*/
	    ButlerTask butlerTaskSearch = new ButlerTask();
        if(StringUtils.isNotBlank(butlerTask.getMemberName())){
        	butlerTaskSearch.setMemberName(butlerTask.getMemberName());
        }
        if(StringUtils.isNotBlank(butlerTask.getMemberMobile())){
        	butlerTaskSearch.setMemberMobile(butlerTask.getMemberMobile());
        }
        if(butlerTask.getType()!=null){
        	butlerTaskSearch.setType(butlerTask.getType());
        }
        if(butlerTask.getStatus()!=null){
        	butlerTaskSearch.setStatus(butlerTask.getStatus());
        }
        if(butlerTask.getOperaterId()!=null){
        	butlerTaskSearch.setOperaterId(butlerTask.getOperaterId());
        }
        //计划时间查询
        if(butlerTask.getPlanTimeStart() != null && butlerTask.getPlanTimeEnd() != null){
        	butlerTaskSearch.setPlanTimeStart(butlerTask.getPlanTimeStart());
        	butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
        }
        //创建时间查询
        if(butlerTask.getCreatedStart() != null && butlerTask.getCreatedEnd() != null){
        	butlerTaskSearch.setCreatedStart(butlerTask.getCreatedStart());
        	butlerTaskSearch.setCreatedEnd(butlerTask.getCreatedEnd());
        }
	    List<ButlerTask> butlerTasks = null;
	    /*总记录数*/
	    Integer totalSize = this.butlerTaskMapper.selectCountForCarServiceByPage(butlerTaskSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    butlerTasks = this.butlerTaskMapper.selectForCarServiceByPage(butlerTaskSearch);
	    if(CollectionUtils.isNotEmpty(butlerTasks)){
	    	for (ButlerTask butlerTaskView : butlerTasks) {
	    		OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
	    		orderCarUsed.setId(butlerTaskView.getCarUsedId());
	    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
	    		CarType carType = carTypeService.getCarTypeById(orderCarUsed.getOrderCartypeId());
	    		butlerTaskView.setBrand(carType.getBrand());
	    		butlerTaskView.setCarType(carType.getCarType());
	    		if(butlerTask.getType() == ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
	    			butlerTaskView.setPlanGetTime(orderCarUsed.getOrderCarReturnTime());
	    		}
	    	}
	    }
        PageResult<ButlerTask> pageResult = new PageResult<ButlerTask>(page,pageSize,totalSize,butlerTasks);
        return pageResult;
	 }
	
	/**
	 * 分页待处理任务
	 */
	@Override
	public PageResult<ButlerTask> getButlerTaskPageForWaitDealWith(ButlerTask butlerTask) {
		Integer page = butlerTask.getPage();
	    Integer pageSize = butlerTask.getPageSize();
	     /*搜索条件*/
	    ButlerTask butlerTaskSearch = new ButlerTask();
        if(StringUtils.isNotBlank(butlerTask.getMemberName())){
        	butlerTaskSearch.setMemberName(butlerTask.getMemberName());
        }
        if(StringUtils.isNotBlank(butlerTask.getMemberMobile())){
        	butlerTaskSearch.setMemberMobile(butlerTask.getMemberMobile());
        }
        if(butlerTask.getType()!=null){
        	butlerTaskSearch.setType(butlerTask.getType());
        }
        if(butlerTask.getStatus()!=null){
        	butlerTaskSearch.setStatus(butlerTask.getStatus());
        }
        if(butlerTask.getCustomerId()!=null){
        	butlerTaskSearch.setCustomerId(butlerTask.getCustomerId());
        }
        //计划时间查询
        if(butlerTask.getPlanTimeStart() != null && butlerTask.getPlanTimeEnd() != null){
        	butlerTaskSearch.setPlanTimeStart(butlerTask.getPlanTimeStart());
        	butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
        }
        //创建时间查询
        if(butlerTask.getCreatedStart() != null && butlerTask.getCreatedEnd() != null){
        	butlerTaskSearch.setCreatedStart(butlerTask.getCreatedStart());
        	butlerTaskSearch.setCreatedEnd(butlerTask.getCreatedEnd());
        }
	    List<ButlerTask> butlerTasks = null;
	    /*总记录数*/
	    Integer totalSize = this.butlerTaskMapper.selectCountForWaitDealWithByPage(butlerTaskSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    butlerTasks = this.butlerTaskMapper.selectForWaitDealWithByPage(butlerTaskSearch);
	    if(CollectionUtils.isNotEmpty(butlerTasks)){
	    	for (ButlerTask butlerTaskView : butlerTasks) {
	    		OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
	    		orderCarUsed.setId(butlerTaskView.getCarUsedId());
	    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
	    		CarType carType = carTypeService.getCarTypeById(orderCarUsed.getOrderCartypeId());
	    		butlerTaskView.setBrand(carType.getBrand());
	    		butlerTaskView.setCarType(carType.getCarType());
	    	}
	    }
        PageResult<ButlerTask> pageResult = new PageResult<ButlerTask>(page,pageSize,totalSize,butlerTasks);
		return pageResult;
	}


	/**
	 * 分页处理中任务
	 */
	@Override
	public PageResult<ButlerTask> getButlerTaskPageForProcessing(ButlerTask butlerTask) {
		Integer page = butlerTask.getPage();
	    Integer pageSize = butlerTask.getPageSize();
	     /*搜索条件*/
	    ButlerTask butlerTaskSearch = new ButlerTask();
        if(StringUtils.isNotBlank(butlerTask.getMemberName())){
        	butlerTaskSearch.setMemberName(butlerTask.getMemberName());
        }
        if(StringUtils.isNotBlank(butlerTask.getMemberMobile())){
        	butlerTaskSearch.setMemberMobile(butlerTask.getMemberMobile());
        }
        if(butlerTask.getType()!=null){
        	butlerTaskSearch.setType(butlerTask.getType());
        }
        if(butlerTask.getStatus()!=null){
        	butlerTaskSearch.setStatus(butlerTask.getStatus());
        }
        if(butlerTask.getOperaterId()!=null){
        	butlerTaskSearch.setOperaterId(butlerTask.getOperaterId());
        }
        //计划时间查询
        if(butlerTask.getPlanTimeStart() != null && butlerTask.getPlanTimeEnd() != null){
        	butlerTaskSearch.setPlanTimeStart(butlerTask.getPlanTimeStart());
        	butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
        }
        //创建时间查询
        if(butlerTask.getCreatedStart() != null && butlerTask.getCreatedEnd() != null){
        	butlerTaskSearch.setCreatedStart(butlerTask.getCreatedStart());
        	butlerTaskSearch.setCreatedEnd(butlerTask.getCreatedEnd());
        }
        //时间查询
	    List<ButlerTask> butlerTasks = null;
	    /*总记录数*/
	    Integer totalSize = this.butlerTaskMapper.selectCountForProcessingByPage(butlerTaskSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    butlerTasks = this.butlerTaskMapper.selectForProcessingByPage(butlerTaskSearch);
	    if(CollectionUtils.isNotEmpty(butlerTasks)){
	    	for (ButlerTask butlerTaskView : butlerTasks) {
	    		OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
	    		orderCarUsed.setId(butlerTaskView.getCarUsedId());
	    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
	    		CarType carType = carTypeService.getCarTypeById(orderCarUsed.getOrderCartypeId());
	    		butlerTaskView.setBrand(carType.getBrand());
	    		butlerTaskView.setCarType(carType.getCarType());
	    	}
	    }
        PageResult<ButlerTask> pageResult = new PageResult<ButlerTask>(page,pageSize,totalSize,butlerTasks);
		return pageResult;
	}


	/**
	 * 分页已结束任务
	 */
	@Override
	public PageResult<ButlerTask> getButlerTaskPageForComplete(ButlerTask butlerTask) {
		Integer page = butlerTask.getPage();
	    Integer pageSize = butlerTask.getPageSize();
	     /*搜索条件*/
	    ButlerTask butlerTaskSearch = new ButlerTask();
        if(StringUtils.isNotBlank(butlerTask.getMemberName())){
        	butlerTaskSearch.setMemberName(butlerTask.getMemberName());
        }
        if(StringUtils.isNotBlank(butlerTask.getMemberMobile())){
        	butlerTaskSearch.setMemberMobile(butlerTask.getMemberMobile());
        }
        if(butlerTask.getType()!=null){
        	butlerTaskSearch.setType(butlerTask.getType());
        }
        if(butlerTask.getStatus()!=null){
        	butlerTaskSearch.setStatus(butlerTask.getStatus());
        }
        if(butlerTask.getOperaterId()!=null){
        	butlerTaskSearch.setOperaterId(butlerTask.getOperaterId());
        }
        //计划时间查询
        if(butlerTask.getPlanTimeStart() != null && butlerTask.getPlanTimeEnd() != null){
        	butlerTaskSearch.setPlanTimeStart(butlerTask.getPlanTimeStart());
        	butlerTaskSearch.setPlanTimeEnd(butlerTask.getPlanTimeEnd());
        }
        //创建时间查询
        if(butlerTask.getCreatedStart() != null && butlerTask.getCreatedEnd() != null){
        	butlerTaskSearch.setCreatedStart(butlerTask.getCreatedStart());
        	butlerTaskSearch.setCreatedEnd(butlerTask.getCreatedEnd());
        }
        //时间查询
	    List<ButlerTask> butlerTasks = null;
	    /*总记录数*/
	    Integer totalSize = this.butlerTaskMapper.selectCountForCompleteByPage(butlerTaskSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    butlerTasks = this.butlerTaskMapper.selectForCompleteByPage(butlerTaskSearch);
	    if(CollectionUtils.isNotEmpty(butlerTasks)){
	    	for (ButlerTask butlerTaskView : butlerTasks) {
	    		OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
	    		orderCarUsed.setId(butlerTaskView.getCarUsedId());
	    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
	    		CarType carType = carTypeService.getCarTypeById(orderCarUsed.getOrderCartypeId());
	    		butlerTaskView.setBrand(carType.getBrand());
	    		butlerTaskView.setCarType(carType.getCarType());
	    	}
	    }
        PageResult<ButlerTask> pageResult = new PageResult<ButlerTask>(page,pageSize,totalSize,butlerTasks);
		return pageResult;
	}

	/**
	 * 转派管家
	 */
	@Override
	public ButlerTask updateTaskHousekeeper(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"转派管家对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId(), "转派管家-管家ID不能为空");		
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterName(), "转派管家-管家姓名不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "转派管家-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "转派管家-操作人id不能为空");
		//修改任务信息
		butlerTask = this.updateTask(butlerTask); 
		//推送消息
		return butlerTask;
	}
	
	/**
	 * 分配管家
	 */
	@Override
	public ButlerTask updateTaskCustomerToOperater(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"分配管家对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId(), "分配管家-管家ID不能为空");		
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterName(), "分配管家-管家姓名不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "分配管家-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "分配管家-操作人id不能为空");
		//修改任务信息
		butlerTask = this.updateTask(butlerTask); 
		//推送消息
		if(butlerTask.getType() == ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
			//送车推送
			this.SendMessageToOperater(butlerTask.getOperaterId(), WechatMsgType.SENDCUSTOMERTOOPERATERSUCCESS.getType());
		}
		if(butlerTask.getType() == ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex().intValue()){
			//取推送
			this.SendMessageToOperater(butlerTask.getOperaterId(), WechatMsgType.GETCUSTOMERTOOPERATERSUCCESS.getType());
		}
		return butlerTask;
	}

	/**
	 * 修改送车任务状态为-待处理
	 */
	public ButlerTask updateSendButlerTaskByHandle(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改送车任务状态为-待处理-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改送车任务状态为-待处理-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改送车任务状态为-待处理-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改送车任务状态为-待处理-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}
	
	/**
	 * 修改送车任务状态为-待接单
	 */
	@Override
	public ButlerTask updateSendButlerTaskByWaiting(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改送车任务状态为-待接单-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改送车任务状态为-待接单-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改送车任务状态为-待接单-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改送车任务状态为-待接单-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_WAITACCEPT.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}

	/**
	 * 修改送车任务状态为-拒绝
	 */
	public ButlerTask updateSendButlerTaskByRefuse(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改送车任务状态为-拒绝-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改送车任务状态为-拒绝-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改送车任务状态为-拒绝-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改送车任务状态为-拒绝-操作人ID不能为空");
		
		//清空管家
		butlerTask.setStatus(TaskStatus.TASKSTATUS_WAITACCEPT.getIndex());
		//取用户信息
		User user = this.getUser(RoleEnum.CUCO_GJFZR);
		butlerTask.setOperaterId(user.getId());
		butlerTask.setOperaterName(user.getName());
		//推送消息
		this.SendMessageToOperater(user.getId(), WechatMsgType.SENDCUSTOMERTOOPERATERSUCCESS.getType());
		//修改任务信息
		return this.updateTask(butlerTask);
	}
	
	  /**
     * @Title: updateSendButlerTaskByTakeCar 
     * @Description: 修改送车车任务状态为-送车待取车
     * @param  butlerTask
     * @return ButlerTask
      */
    public ButlerTask updateSendButlerTaskByTakeCar(ButlerTask butlerTask){
    	ParamVerifyUtils.paramNotNull(butlerTask,"修改送车任务状态为-待取车-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改送车任务状态为-待取车-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改送车任务状态为-待取车-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改送车任务状态为-待取车-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_SENDWAITTASKCAR.getIndex());
		//修改送车任务信息
		butlerTask = this.updateTask(butlerTask);
		//取用户信息
		User user = this.getUser(RoleEnum.CUCO_CW);
		//给车务推送消息
		this.SendMessageToOperater(user.getId(), WechatMsgType.ORDERTAKINGTOCARSERVICESUCCESS.getType());
		return butlerTask;
    }
	
	/**
	 * 修改送车任务状态为-待送车
	 */
	@Override
	public ButlerTask updateSendButlerTaskBySendCar(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-待送车-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-待送车-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-待送车-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-待送车-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_WAITSENDCAR.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}

	/**
	 * 修改送车任务状态为-送车中
	 */
	@Override
	public ButlerTask updateSendButlerTaskBySendIn(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-送车中-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-送车中-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-送车中-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-送车中-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_SENDCAR.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		butlerTask = this.updateTask(butlerTask);
		//给用户推送消息
		this.SendMessageToMember(butlerTask.getMemberId(), WechatMsgType.SENDINCARSUCCESS.getType());
		return butlerTask;
	}

	/**
	 * 修改送车任务状态为-已到达
	 */
	@Override
	public ButlerTask updateSendButlerTaskByArrive(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-已到达-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-已到达-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-已到达-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-已到达-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_ARRIVE.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		butlerTask = this.updateTask(butlerTask);
		//给用户推送消息
		this.SendMessageToMember(butlerTask.getMemberId(), WechatMsgType.SENDARRIVEDSUCCESS.getType());
		
		return butlerTask;
	}

	/**
	 * 修改送车任务状态为-已完成
	 */
	@Override
	public ButlerTask updateSendButlerTaskByCompleted(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-已完成-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-已完成-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-已完成-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-已完成-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_COMPLETE.getIndex());
		ButlerTask butlerTask_old = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//车辆运营状态变为 使用中
        Car car = new Car();
        car.setId(butlerTask_old.getCarOperateId());
        car.setModifierId(butlerTask.getModifierId());
        car.setModifier(butlerTask.getModifier());
        carService.updateCarByMemberUsing(car);
        //运营计划变为使用中
        CarOperatePlan carOperatePlan=new CarOperatePlan();
        carOperatePlan.setMemberUsecarId(butlerTask_old.getCarUsedId());
	    carOperatePlanService.updateOperatePlan2ExecutingByCarUsedId(carOperatePlan);
	    //用车记录变为使用中
	    OrderMemberCarUsed memberCarUsed= new OrderMemberCarUsed();
	    memberCarUsed.setId(butlerTask_old.getCarUsedId());
	    memberCarUsed.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_USING.getIndex());
	    memberCarUsed.setModifer(butlerTask.getModifier());
	    memberCarUsed.setModifierId(butlerTask.getModifierId());
	    memberCarUsedService.updateOrderMemberCarUsedStatusByUsing(memberCarUsed);
		//修改送车任务信息
		this.updateTask(butlerTask);
		
		if(butlerTask_old.getUseCarType() == Valid.UP.getValue().intValue()){
			//创建用车押金
			PreAuthorizedDebit preAuthorizedDebit = new PreAuthorizedDebit(); 
			preAuthorizedDebit.setMemberId(butlerTask_old.getMemberId());
			preAuthorizedDebit.setCarId(butlerTask_old.getCarOperateId());
			preAuthorizedDebit.setCarUsedId(butlerTask_old.getCarUsedId());
			preAuthorizedDebit.setSource(Valid.DOWN.getValue());
			preAuthorizedDebit.setModifier(butlerTask.getModifier());
			preAuthorizedDebit.setModifierId(butlerTask.getModifierId());
			preAuthorizedDebitService.createUseCarPreAuthorizedDebit(preAuthorizedDebit);
		}
		
		//给用户推送消息
		this.SendMessageToMember(butlerTask.getMemberId(), WechatMsgType.SENDCOMPLETEDSUCCESS.getType());
		
		return butlerTask;
	}

	/**
	 * 修改任务状态为-已取消
	 */
	@Override
	public ButlerTask updateButlerTaskByCancelled(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-已取消-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-已取消-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-已取消-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-已取消-操作人ID不能为空");
		
		ButlerTask butlerTask_old = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//车辆状态改为待整备
		if(butlerTask_old.getCarOperateId()!=null){
			Car car = new Car();
			car.setId(butlerTask_old.getCarOperateId());
			car.setModifierId(butlerTask.getModifierId());
			car.setModifier(butlerTask.getModifier());
			carService.updateCarByWaitReorganize(car);
		}
        //运营计划变为取消
        CarOperatePlan carOperatePlan=new CarOperatePlan();
        carOperatePlan.setMemberUsecarId(butlerTask_old.getCarUsedId());
	    carOperatePlanService.updateOperatePlan2CancelByCarUsedId(carOperatePlan);
	    //用车记录变为已取消
	    OrderMemberCarUsed memberCarUsed= new OrderMemberCarUsed();
	    memberCarUsed.setId(butlerTask_old.getCarUsedId());
	    memberCarUsed.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_CANCEL.getIndex());
	    memberCarUsed.setModifer(butlerTask.getModifier());
	    memberCarUsed.setModifierId(butlerTask.getModifierId());
	    memberCarUsedService.updateOrderMemberCarUsedStatusBycancel(memberCarUsed);
	    //结算
  		OrderCarUsed orderCarUsed = new OrderCarUsed();
  		orderCarUsed.setMemberCarUsedId(butlerTask_old.getCarUsedId());
  		orderCarUsed.setGasoline(new BigDecimal("0"));
  		orderCarUsed.setModifer(butlerTask.getModifier());
  		orderCarUsed.setModifierId(butlerTask.getModifierId());
  		orderCarUsedService.createOrderCarUsedForButtler(orderCarUsed);
	    //将任务状态变为已取消
	    this.updateButlerTaskStatusForCancel(butlerTask);
        return butlerTask;
	}

	/**
	 * 待处理
	 */
	public ButlerTask updateGetButlerTaskByHandle(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-待处理-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-待处理-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-待处理-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-待处理-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}
	

	/**
	 * 修改取车任务状态为-待接单
	 */
	@Override
	public ButlerTask updateGetButlerTaskByWaiting(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-待接单-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-待接单-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-待接单-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-待接单-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_WAITACCEPT.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}

	/**
	 * 修改取车任务状态为-拒绝
	 */
	public ButlerTask updateGetButlerTaskByRefuse(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-拒绝-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-拒绝-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-拒绝-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-拒绝-操作人ID不能为空");
		
		//修改任务状态为待接单
		butlerTask.setStatus(TaskStatus.TASKSTATUS_WAITACCEPT.getIndex());
		
		User user = this.getUser(RoleEnum.CUCO_GJFZR);
		butlerTask.setOperaterId(user.getId());
		butlerTask.setOperaterName(user.getName());
		//推送消息
		this.SendMessageToOperater(user.getId(), WechatMsgType.GETCUSTOMERTOOPERATERSUCCESS.getType());
		//修改任务信息
		return this.updateTask(butlerTask);
	}

	/**
	 * 修改取车任务状态为-待取车
	 */
	@Override
	public ButlerTask updateGetButlerTaskByTakeCar(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-待取车-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-待取车-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-待取车-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-待取车-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_WAITTASKCAR.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}


	/**
	 * 修改取车任务状态为-取车中
	 */
	@Override
	public ButlerTask updateGetButlerTaskByGetIn(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-取车中-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-取车中-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-取车中-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-取车中-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_TASKCAR.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		butlerTask = this.updateTask(butlerTask);
		//给用户推送消息
		this.SendMessageToMember(butlerTask.getMemberId(), WechatMsgType.GETTAKECARSUCCESS.getType());
		
		return butlerTask;
	}

	/**
	 * 修改取车任务状态为-已到达
	 */
	@Override
	public ButlerTask updateGetButlerTaskByArrive(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-已到达-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-已到达-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-已到达-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-已到达-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_ARRIVE.getIndex());
		//修改车辆运营计划信息
		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		butlerTask = this.updateTask(butlerTask);
		//给用户推送消息
		this.SendMessageToMember(butlerTask.getMemberId(), WechatMsgType.GETARRIVEDSUCCESS.getType());
		return butlerTask;
	}

	/**
	 * 修改取车任务状态为-待入库
	 */
	public ButlerTask updateGetButlerTaskByStorage(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-待入库-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-待入库-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-待入库-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-待入库-操作人ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getCashDeposit(), "修改取车任务状态为-待入库-扣除押金金额不能为空");
		
		butlerTask.setStatus(TaskStatus.TASKSTATUS_STORAGE.getIndex());
		ButlerTask butlerTaskView = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		
		//车辆运营计划改为完成
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setMemberUsecarId(butlerTaskView.getCarUsedId());
		carOperatePlanService.updateOperatePlan2CompeleteByCarUsedId(carOperatePlan);
		//车辆改为待整备
		Car car = new Car();
		car.setId(butlerTaskView.getCarOperateId());
		car.setModifierId(butlerTask.getModifierId());
        car.setModifier(butlerTask.getModifier());
		carService.updateCarByWaitReorganize(car);
		//结算
		OrderCarUsed orderCarUsed = new OrderCarUsed();
		orderCarUsed.setMemberCarUsedId(butlerTaskView.getCarUsedId());
		orderCarUsed.setModifer(butlerTask.getModifier());
		orderCarUsed.setModifierId(butlerTask.getModifierId());
		orderCarUsedService.createOrderCarUsedForButtler(orderCarUsed);
		//解冻用车押金
		PreAuthorizedDebit preAuthorizedDebit = new PreAuthorizedDebit(); 
		preAuthorizedDebit.setCarUsedId(butlerTaskView.getCarUsedId());
		preAuthorizedDebit = preAuthorizedDebitService.getPreAuthorizedDebitByUsed(preAuthorizedDebit);
		if(preAuthorizedDebit!=null){
			preAuthorizedDebit.setDeductionAmount(butlerTask.getCashDeposit());
			preAuthorizedDebitService.updatePreAuthorizedDebitUnfreeze(preAuthorizedDebit);
		}
		
	    //用车记录变为完成
	    OrderMemberCarUsed memberCarUsed= new OrderMemberCarUsed();
	    memberCarUsed.setId(butlerTaskView.getCarUsedId());
	    memberCarUsed.setActualCarReturnTime(new Date());
	    memberCarUsed.setModifer(butlerTask.getModifier());
	    memberCarUsed.setModifierId(butlerTask.getModifierId());
	    memberCarUsedService.updateOrderMemberCarUsedStatusByComplete(memberCarUsed);
		
		//修改用车记录信息
		this.updateMemberCarUsed(butlerTask);
		butlerTask.setCompleteTime(new Date());
		//修改送车任务信息
		butlerTask = this.updateTask(butlerTask);
		//创建违章押金
		preAuthorizedDebit = new PreAuthorizedDebit(); 
		preAuthorizedDebit.setMemberId(butlerTask.getMemberId());
		preAuthorizedDebit.setCarId(butlerTask.getCarOperateId());
		preAuthorizedDebit.setCarUsedId(butlerTask.getCarUsedId());
		preAuthorizedDebit.setSource(Valid.DOWN.getValue());
		preAuthorizedDebit.setModifier(butlerTask.getModifier());
		preAuthorizedDebit.setModifierId(butlerTask.getModifierId());
		preAuthorizedDebitService.createViolationPreAuthorizedDebit(preAuthorizedDebit);
		//给用户推送消息
		this.SendMessageToMember(butlerTask.getMemberId(), WechatMsgType.GETPENDINGSTORAGESUCCESS.getType());
		//给车务推送信息
		User user = this.getUser(RoleEnum.CUCO_CW);
		this.SendMessageToOperater(user.getId(), WechatMsgType.OPERATERGETTOCARSERVICESUCCESS.getType());
		
		return butlerTask;
	}
	
	 /**
	  * 修改取车任务状态为-整备中
	  */
    public ButlerTask updateGetButlerTaskByInReadiness(ButlerTask butlerTask){
    	ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-整备中-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-整备中-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-整备中-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-整备中-操作人ID不能为空");
		
		butlerTask.setStatus(TaskStatus.TASKSTATUS_INREADINESS.getIndex());
		ButlerTask butlerTaskView = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//车辆改为整备中
		Car car = new Car();
		car.setId(butlerTaskView.getCarOperateId());
		car.setModifierId(butlerTask.getModifierId());
        car.setModifier(butlerTask.getModifier());
		carService.updateCarByReorganizing(car);
		//修改送车任务信息
		return this.updateTask(butlerTask);
    }
	
	/**
	 * 修改取车任务状态为-已完成
	 */
	@Override
	public ButlerTask updateGetButlerTaskByCompleted(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-已完成-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-已完成-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-已完成-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-已完成-操作人ID不能为空");
		butlerTask.setStatus(TaskStatus.TASKSTATUS_COMPLETE.getIndex());
		ButlerTask butlerTaskView = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//车辆改为待分配
		Car car = new Car();
		car.setId(butlerTaskView.getCarOperateId());
		car.setModifierId(butlerTask.getModifierId());
        car.setModifier(butlerTask.getModifier());
		carService.updateCarByWaitDistribute(car);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}

	/**
	 * 修改取车任务状态为-已失联
	 */
	public ButlerTask updateGetButlerTaskByOutOfContact(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改取车任务状态为-已失联-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改取车任务状态为-已失联-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改取车任务状态为-已失联-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改取车任务状态为-已失联-操作人ID不能为空");
		
		butlerTask.setStatus(TaskStatus.TASKSTATUS_LOSTCONTACT.getIndex());
		ButlerTask butlerTaskView = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//车辆改为失联
		Car car = new Car();
		car.setId(butlerTaskView.getCarOperateId());
		car.setModifierId(butlerTask.getModifierId());
		car.setModifier(butlerTask.getModifier());
		carService.updateCarByLosed(car);
		//用车记录变为失联
		OrderMemberCarUsed memberCarUsed= new OrderMemberCarUsed();
		memberCarUsed.setId(butlerTaskView.getCarUsedId());
		memberCarUsed.setUsedStatus(MemberCarUsedStatus.MEMBERCARUSED_LOSED.getIndex());
		memberCarUsed.setModifer(butlerTask.getModifier());
	    memberCarUsed.setModifierId(butlerTask.getModifierId());
		memberCarUsedService.updateOrderMemberCarUsedStatusByLose(memberCarUsed);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	 }
	
	/**
	 * 分配车辆
	 */
	public ButlerTask updateButlerTaskByDistributionCar(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"分配车辆-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "分配车辆-任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getCarOperateId(), "分配车辆-车辆ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "分配车辆-操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "分配车辆-操作人ID不能为空");
		
		ButlerTask butlerTaskView = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//车辆改为已分配
		Car car = new Car();
		car.setId(butlerTask.getCarOperateId());
		car.setModifierId(butlerTask.getModifierId());
        car.setModifier(butlerTask.getModifier());
        car = carService.updateCarByDistributed(car);

		//车辆运营计划分配车辆
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setMemberUsecarId(butlerTaskView.getCarUsedId());
		carOperatePlan.setCarPlateNum(car.getCarPlateNum());
		carOperatePlan.setCarId(car.getId());
		carOperatePlanService.updateOperatePlanInfoByCarUsedId(carOperatePlan);
		 //用车记录分配车辆
	    OrderMemberCarUsed memberCarUsed= new OrderMemberCarUsed();
	    memberCarUsed.setId(butlerTaskView.getCarUsedId());
	    memberCarUsed.setCaroperateId(car.getId());
	    memberCarUsed.setModifer(butlerTask.getModifier());
	    memberCarUsed.setModifierId(butlerTask.getModifierId());
	    memberCarUsedService.updateOrderMemberCarUsedForSettingCar(memberCarUsed);
	    //修改送车任务信息
		return this.updateTask(butlerTask);
	 }
	
	/**
	 * 跟进中
	 */
	public ButlerTask updateButlerTaskByFollowUp(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"修改任务-跟进中--任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "修改任务-跟进中--任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifier(), "修改任务-跟进中--操作人不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getModifierId(), "修改任务-跟进中--操作人ID不能为空");
		
		//修改车辆运营计划信息
//		this.updateCarOpernatePlanInfo(butlerTask);
		//修改用车记录信息
//		this.updateMemberCarUsed(butlerTask);
		//修改送车任务信息
		return this.updateTask(butlerTask);
	}
	
	/**
	 * 按时间统计完成任务数量
	 */
	public Integer getTaskNumByCompleteDate(ButlerTask butlerTask){
		ParamVerifyUtils.paramNotNull(butlerTask,"统计任务-任务对象不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getOperaterId(), "统计任务-操作人Id不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getCompleteTimeStart(), "统计任务-任务完成开始时间不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getCompleteTimeEnd(), "统计任务-任务完成结束时间不能为空");
		return this.butlerTaskMapper.getTaskNumByCompleteDate(butlerTask);
	}
	

	/**
	 *日志分页
	 */
	@Override
	public PageResult<OperateLog> getButlerTaskLogByPage(OperateLog operateLog) {
		ParamVerifyUtils.paramNotNull(operateLog);
		ParamVerifyUtils.paramNotNull(operateLog.getOperationId(), "查询任务ID不能为空");
		operateLog.setType(OperateLogEnum.TASK.getValue());
		return operateLogService.getOperateLogByPage(operateLog);
	}

	/**
	 * 定时器-整备中变为完成
	 */
	@Override
	public void updateInReadinessToComplete(){
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setStatus(TaskStatus.TASKSTATUS_INREADINESS.getIndex());
		List<ButlerTask> list = this.butlerTaskMapper.selectByCondition(butlerTask);
		if(CollectionUtils.isNotEmpty(list)){
			for (ButlerTask butlerTaskView : list) {
				OperateLog operateLog = new OperateLog();
				operateLog.setType(OperateLogEnum.TASK.getValue());
				operateLog.setOperationId(butlerTaskView.getId());
				operateLog.setStatus(TaskStatus.TASKSTATUS_INREADINESS.getIndex());
				List<OperateLog> OperateLogs = operateLogService.getOperateLogList(operateLog);
				if(CollectionUtils.isNotEmpty(OperateLogs)){
					operateLog = OperateLogs.get(OperateLogs.size()-1);
					if( (new Date().getTime()-operateLog.getCreated().getTime()) >1000*60*60*4){
						butlerTaskView.setStatus(TaskStatus.TASKSTATUS_COMPLETE.getIndex());
						butlerTaskView.setModifier("系统");
						butlerTaskView.setModifierId(0L);
						//车辆改为待分配
						Car car = new Car();
						car.setId(butlerTaskView.getCarOperateId());
						car.setModifierId(butlerTaskView.getModifierId());
				        car.setModifier(butlerTaskView.getModifier());
						carService.updateCarByWaitDistribute(car);
						//修改送车任务信息
						this.updateTask(butlerTaskView);
						this.log.error("定时器-整备中变为完成，ID为{}任务整备已超过四小时，系统将将其被更为已完成",butlerTaskView.getId());
					}
				} 
			}
		}
	}
	
	/**
	 * 定时器-已到达变为已失联
	 */
	@Override
	public void updateArrivedToLosed(){
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setStatus(TaskStatus.TASKSTATUS_ARRIVE.getIndex());
		butlerTask.setType(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex());
		List<ButlerTask> list = this.butlerTaskMapper.selectByCondition(butlerTask);
		if(CollectionUtils.isNotEmpty(list)){
			for (ButlerTask butlerTaskView : list) {
				if( (new Date().getTime()-butlerTaskView.getPlanTime().getTime()) >1000*60*60){
					//查询用车记录
					OrderMemberCarUsed orderCarUsed = new OrderMemberCarUsed();
		    		orderCarUsed.setId(butlerTaskView.getCarUsedId());
		    		orderCarUsed = memberCarUsedService.getOrderMemberCarUsed(orderCarUsed);
		    		if(orderCarUsed.getUsedStatus()!= MemberCarUsedStatus.MEMBERCARUSED_LOSED.getIndex()){
		    			//变更为已失联
		    			butlerTaskView.setModifier("系统");
		    			butlerTaskView.setModifierId(0L);
		    			this.updateGetButlerTaskByOutOfContact(butlerTaskView);
		    			this.log.error("定时器-已到达变为已失联，ID为{}任务管家已到达超过一小时，系统将将其被更为已失联",butlerTaskView.getId());
		    		}
		    			
				} 
			}
		}
	}
	
	/**
	 * 定时器-待处理变为待接单
	 */
	@Override
	public void updateWaitToWaitOrders(){
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setStatus(TaskStatus.TASKSTATUS_PROCESSED.getIndex());
		List<ButlerTask> list = this.butlerTaskMapper.selectByCondition(butlerTask);
		if(CollectionUtils.isNotEmpty(list)){
			for (ButlerTask butlerTaskView : list) {
				if( (butlerTaskView.getPlanTime().getTime()-new Date().getTime()) < 1000*60*60*4){
					butlerTaskView.setOperaterId(0L);
					butlerTaskView.setOperaterName("");
	    			this.updateSendButlerTaskByWaiting(butlerTaskView);
	    			//取用户信息
    				User user = this.getUser(RoleEnum.CUCO_GJFZR);
    				//推送消息
    				if(butlerTask.getType() == ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
    					//送车推送
    					this.SendMessageToOperater(user.getId(), WechatMsgType.SENDCUSTOMERTOOPERATERSUCCESS.getType());
    				}
    				if(butlerTask.getType() == ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex().intValue()){
    					//取推送
    					this.SendMessageToOperater(user.getId(), WechatMsgType.GETCUSTOMERTOOPERATERSUCCESS.getType());
    				}
    				this.log.error("定时器-已到达变为已失联，ID为{}任务管家已到达超过一小时，系统将将其被更为已失联",butlerTaskView.getId());
				} 
			}
		}
	}
	/**
	* @Title: getUser 
	* @Description: 取用户信息
	* @author huanghua
	* @param roleEnum
	* @return
	* @return User
	 */
	private User getUser(RoleEnum roleEnum){
		List<User> users =  securityService.getUsersByRole(roleEnum);
		if(CollectionUtils.isNotEmpty(users)){
			User user = users.get(0);
			return user;
		}
		return null;
	}
	
	/**
	 * 创建任务附件
	 */
	@Override
	public TransferList createButerTaskTransfer(TransferList transferList) {
		ParamVerifyUtils.paramNotNull(transferList);
    	ParamVerifyUtils.paramNotNull(transferList.getTaskId(), "新增管家任务附件，管家任务id不能为空");
    	ParamVerifyUtils.paramNotNull(transferList.getType(), "新增管家任务附件，附件类型不能为空");
    	ParamVerifyUtils.paramNotNull(transferList.getImageUrl(), "新增管家任务附件，附件路径不能为空");
    	ParamVerifyUtils.paramNotNull(transferList.getCarUsedId(), "新增管家任务附件，用车记录id不能为空");
    	TransferList transferListView = new TransferList(); 
    	transferListView.setTaskId(transferList.getTaskId());
    	if(transferList.getType()==TransferType.TRANSDERTYPE_CAR.getIndex()){
    		transferListView.setType(TransferType.TRANSDERTYPE_CAR.getIndex());
    		if(transferListMapper.selectCountByCondition(transferListView)>=10){
    			throw new RuntimeExceptionWarn("车辆照片不得大于10张");
    		}
    	}
    	if(transferList.getType()==TransferType.TRANSDERTYPE_RECEIPT.getIndex()){
    		transferListView.setType(TransferType.TRANSDERTYPE_RECEIPT.getIndex());
    		if(transferListMapper.selectCountByCondition(transferListView)>=10){
    			throw new RuntimeExceptionWarn("车辆交接单不得大于10张");
    		}
    	}
    	transferListMapper.insertSelective(transferList);
		return transferList;
	}
	
	/**
	* @Title: getButerTaskTransferList 
	* @Description: 取任务附件列表
	* @author huanghua
	* @param transferList
	* @return
	* @return List<TransferList>
	 */
	private List<TransferList> getButerTaskTransferList(TransferList transferList){
		ParamVerifyUtils.paramNotNull(transferList);
		ParamVerifyUtils.paramNotNull(transferList.getType(), "查询附件，附件类型不能为空");
		ParamVerifyUtils.paramNotNull(transferList.getTaskId(), "查询附件，任务Id不能为空");
		return  this.transferListMapper.selectByCondition(transferList);
	}
	
	/**
	 * 删除附件
	 */
	@Override
	public Integer deleteButerTaskTransfer(Long id) {
		ParamVerifyUtils.paramNotNull(id, "删除任务附件，附件id不能为空");
    	TransferList transferList = transferListMapper.selectByPrimaryKey(id);
    	if(transferList == null){
    		throw new RuntimeExceptionWarn("无此附件");
    	}
		return transferListMapper.deleteByPrimaryKey(id);
	}
	
	/**
	* @Title: createTask 
	* @Description: 创建
	* @author huanghua
	* @param butlerTask
	* @return ButlerTask
	 */
	private ButlerTask createTask(ButlerTask butlerTask) {
		butlerTask.setTaskNum(createTaskId());
		butlerTask.setCreated(new Date());
		butlerTask.setLasttimeModify(butlerTask.getCreated());
		this.butlerTaskMapper.insertSelective(butlerTask);
		//添加任务日志
		operateLogService.createeOperateLogForButlerTask(butlerTask);
		
		return this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
	}
	
	/**
	* @Title: updateTask 
	* @Description: 修改任务
	* @author huanghua
	* @param butlerTask
	* @return void
	 */
	private ButlerTask updateTask(ButlerTask butlerTask) {
		butlerTask.setLasttimeModify(new Date());
		//添加任务日志
		operateLogService.createeOperateLogForButlerTask(butlerTask);
		//修改任务信息
		this.butlerTaskMapper.updateByPrimaryKeySelective(butlerTask);
		
		return this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
	}
	
	/**
	 * 后台创建任务负责管家是选择的管家，负责客服是当前登录人的信息；前端创建任务负责管家以及负责客服都是后台设置的默认管家及客服
	 * @param butlerTask
	 * @return
	 */
	private ButlerTask setCustomerAndOperater(ButlerTask butlerTask){
		//判断是后台操作还是前台操作
		if(null==butlerTask.getOperaterId()){
			//表示是前台操作，都取后台设置的默认管家
			butlerTask = this.setCustomer(butlerTask);
			if(ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex()==butlerTask.getType().intValue()){
				butlerTask = this.setOperater(butlerTask);
			}
			if(ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex()==butlerTask.getType().intValue()){
				butlerTask = this.setOperater(butlerTask);
			}
		}else{
			//表示是后台操作（前端会传入管家的信息，controller会传入客服的信息-客服即登录人）
			butlerTask.setOperaterId(butlerTask.getOperaterId());
			butlerTask.setOperaterName(butlerTask.getOperaterName());
			butlerTask.setCustomerId(butlerTask.getCustomerId());
			butlerTask.setCustomerName(butlerTask.getCustomerName());
		}
		return butlerTask;
	}
	
	/**
	 * 设置任务客服
	 * @param butlerTask
	 * @return
	 */
	private ButlerTask setCustomer(ButlerTask butlerTask) {
		butlerTask.setCustomerName(null);
		butlerTask.setCustomerId(null);
		return butlerTask;
	}
	
	/**
	 * 任务设置任务负责管家
	 * @param butlerTask
	 * @param type
	 * @return
	 */
	private ButlerTask setOperater(ButlerTask butlerTask){
		butlerTask.setOperaterName(null);
		butlerTask.setOperaterId(null);
		return butlerTask;
	}
	
	// 订单编号生成规则
	private static String createTaskId() {
		return new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())+RandomStringUtils.randomNumeric(4);
	}
	
	/**
	* @Title: editCarOpernatePlanInfo 
	* @Description: 修改运营计划
	* @author huanghua
	* @param butlerTask
	* @return void
	 */
	private void updateCarOpernatePlanInfo(ButlerTask butlerTask) {
		ParamVerifyUtils.paramNotNull(butlerTask);
		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "客服操作，任务ID不能为空");
		ParamVerifyUtils.paramNotNull(butlerTask.getStatus(), "客服操作，任务状态不能为空");
		
		ButlerTask old = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		CarOperatePlan carOperatePlan = new CarOperatePlan();
		carOperatePlan.setMemberUsecarId(old.getCarUsedId());
		if(old.getType()== ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
			if(butlerTask .getPlanTime() != null && old.getPlanTime().getTime() != butlerTask .getPlanTime().getTime()){
				carOperatePlan.setStartTime(butlerTask.getPlanTime());
			}
		}
		if(old.getType()== ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex().intValue()){
			if(butlerTask .getPlanTime() != null && old.getPlanTime().getTime() != butlerTask .getPlanTime().getTime()){
				carOperatePlan.setEndTime(butlerTask.getPlanTime());
			}
		}
		carOperatePlanService.updateOperatePlanInfoByCarUsedId(carOperatePlan);
		
	}
	
	/**
	* @Title: updateCarUsedInfo 
	* @Description: 修改用车记录信息
	* @author huanghua
	* @param butlerTask
	* @return void
	 */
	private void updateMemberCarUsed(ButlerTask butlerTask) {
//		ParamVerifyUtils.paramNotNull(butlerTask);
//		ParamVerifyUtils.paramNotNull(butlerTask.getId(), "客服操作，任务ID不能为空");
//		ParamVerifyUtils.paramNotNull(butlerTask.getStatus(), "客服操作，任务状态不能为空");
//		//旧的预约时间
//		ButlerTask old = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
//		OrderMemberCarUsed memberCarUsed = new OrderMemberCarUsed();
//		memberCarUsed.setId(old.getCarUsedId());
//		if(old.getType()== ButlerTaskType.CAROPERATESTATUS_SENDCAR.getIndex().intValue()){
//			if(butlerTask .getPlanTime() != null && old.getPlanTime().getTime()!= butlerTask .getPlanTime().getTime()){
//				memberCarUsed.setOrderCarUsedTime(butlerTask.getPlanTime());
//				//预约时间变更时-判断库存
//				this.isExitStockByMember(butlerTask);
//			}
//			if(StringUtils.isNotBlank(butlerTask.getTaskAddress()) && !old.getTaskAddress().equals(butlerTask.getTaskAddress())){
//				memberCarUsed.setOrderCarUsedAddress(butlerTask.getTaskAddress());
//			}
//		}
//		if(old.getType()== ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex().intValue()){
//			if(butlerTask .getPlanTime() != null && old.getPlanTime().getTime()!= butlerTask .getPlanTime().getTime()){
//				memberCarUsed.setOrderCarReturnTime(butlerTask.getPlanTime());
//			}
//			if(StringUtils.isNotBlank(butlerTask.getTaskAddress()) && !old.getTaskAddress().equals(butlerTask.getTaskAddress())){
//				memberCarUsed.setOrderCarReturnAddress(butlerTask.getTaskAddress());
//			}
//		}
//		if(butlerTask.getOrderCarUsedDay()!=null){
//			memberCarUsed.setActualCarUsedDay(butlerTask.getOrderCarUsedDay());
//		}
//		memberCarUsedService.updateOrderMemberCarUsedForBackstage(memberCarUsed);
	}
	
	/**
	* @Title: updateButerTaskStatusForPowerUser 
	* @Description: 改任务状态为取消
	* @author huanghua
	* @param butlerTask
	* @return void
	 */

	private void updateButlerTaskStatusForCancel(ButlerTask butlerTask){
		ButlerTask butlerTaskInfo = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		butlerTask.setCarUsedId(butlerTaskInfo.getCarUsedId());
		List<ButlerTask> list = this.butlerTaskMapper.selectByCondition(butlerTask);
		String remark = butlerTask.getRemark();
		for (ButlerTask butlerTaskView : list) {
			butlerTask = new ButlerTask();
			butlerTask.setId(butlerTaskView.getId());
			butlerTask.setCompleteTime(new Date());
			butlerTask.setStatus(TaskStatus.TASKSTATUS_CANCEL.getIndex());
			butlerTask.setRemark(remark);
			//当已分配车辆时,取车任务状态为待入库
			if(butlerTaskView.getType() == ButlerTaskType.CAROPERATESTATUS_TASKCAR.getIndex().intValue() && butlerTaskInfo.getCarOperateId()!=null){
				butlerTask.setStatus(TaskStatus.TASKSTATUS_STORAGE.getIndex());
			}
			//修改任务信息
			this.updateTask(butlerTask);
		}
	}
	
	/**
	* @Title: isExitStockByMember 
	* @Description: 判断库存
	* @author huanghua
	* @param butlerTask
	* @return void
	 */
	private void isExitStockByMember(ButlerTask butlerTask){
		//查询任务
		ButlerTask old = this.butlerTaskMapper.selectByPrimaryKey(butlerTask.getId());
		//查询用车记录
		OrderMemberCarUsed memberCarUsed = new OrderMemberCarUsed();
		memberCarUsed.setId(old.getCarUsedId());
		memberCarUsed = memberCarUsedService.getOrderMemberCarUsed(memberCarUsed);
		//非会员库存
		if(memberCarUsed.getType() == Valid.DOWN.getValue().intValue()){
			
		}else{
		//会员库存
			if(!stockService.isExitStockByMember(memberCarUsed.getOrderCartypeId(), butlerTask.getPlanTimeStart(), memberCarUsed.getOrderCarReturnTime())){
				throw new RuntimeExceptionWarn("库存不足重新选择时间");
			}
		}
	}
	
	/**
	* @Title: SendMessageToMember 
	* @Description: 给用户推送消息
	* @author huanghua
	* @param id
	* @return void
	 */
	private void SendMessageToMember(Long id,String wechatTemplateType){
		Member member = memberService.getMemberById(id);
		if(member==null){
			this.log.error("给会员发送消息推送，ID为{}的会员不存在，无法发送微信消息",id);
			return;
		}
		final String openId = member.getOpenid();
		if(StringUtils.isBlank(openId)){
			this.log.error("给会员发送消息推送，ID为{}的会员openid为空，无法发送微信消息",member.getId());
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					iWechatMessageSender.sendmessage(openId, wechatTemplateType, null);
				}catch (Exception e){
					log.error("推送失败",e);
				}
			}
		}).start();
	}
	
	/**
	* @Title: SendMessageToOperater 
	* @Description: 给管家推送消息
	* @author huanghua
	* @param id
	* @param wechatTemplateType
	* @return void
	 */
	private void SendMessageToOperater(Long id,String wechatTemplateType){
		User  member = securityService.getUserById(id);
		if(member==null){
			this.log.error("给会员发送消息推送，ID为{}的会员不存在，无法发送微信消息",id);
			return;
		}
		final String openId = member.getWechatOpenId();
		if(StringUtils.isBlank(openId)){
			this.log.error("给会员发送消息推送，ID为{}的后台人员openid为空，无法发送微信消息",member.getId());
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					iWechatMessageSender.sendmessage(openId, wechatTemplateType, null);
				}catch (Exception e){
					log.error("推送失败",e);
				}
			}
		}).start();
	}
}
