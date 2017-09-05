package com.hy.gcar.service.butlerTask.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.butlerTask.ButlerTaskSendCarService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.message.WechatMessageService;

/**
 * Created by 海易出行 on 2016/12/8.
 */
@Service
public class ButlerTaskSendCarServiceImpl implements ButlerTaskSendCarService {
    @Autowired
    ButlerTaskService butlerTaskService;
    
    @Autowired
    WechatMessageService wechatMessageService;
    
    @Autowired
    ButlerTaskStatusLogService butlerTaskStatusLogService;

    @Override
    public ButlerTask updateSendCarHousekeeper(ButlerTask butlerTask) {
        Assert.notNull(butlerTask.getId(),"任务id不能为空");
        Assert.notNull(butlerTask.getOperaterId(),"管家负责人id不能为空");
        Assert.notNull(butlerTask.getOperaterName(),"管家负责人姓名不能为空");
        ButlerTask butlerTask_old = butlerTaskService.getButlerTaskById(butlerTask.getId());

        ButlerTask butlerTaskUpdate = new ButlerTask();
        butlerTaskUpdate.setOperaterId(butlerTask.getOperaterId());
        butlerTaskUpdate.setOperaterName(butlerTask.getOperaterName());
        butlerTaskUpdate.setId(butlerTask.getId());
        butlerTaskUpdate.setRemark(butlerTask.getRemark());
        butlerTaskUpdate =  butlerTaskService.updateButlerTask(butlerTask);
        this.butlerTaskService.createButlerTaskStatusLog(butlerTask_old);
        return butlerTaskUpdate;
    }

	@Override
	public ButlerTask updateWaitForSendCar(ButlerTask butlerTask) throws Exception {
		
        Assert.notNull(butlerTask.getId(),"任务id不能为空");
        ButlerTask butlerTask_old = butlerTaskService.getButlerTaskById(butlerTask.getId());
        
        
        ButlerTask butlerTaskUpdate = new ButlerTask();
        butlerTaskUpdate.setId(butlerTask.getId());
        butlerTaskUpdate.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_WAITING_SEND_FOR_A_CAR.getIndex());
        butlerTaskUpdate.setRemark(butlerTask.getRemark());
        butlerTaskUpdate =  butlerTaskService.updateButlerTask(butlerTaskUpdate);
        this.butlerTaskService.createButlerTaskStatusLog(butlerTask_old);
        
        
  		String value ="极车管家已受理您的用车预约";
  		butlerTaskService.sendMessageByTaskStatusChangeApp(butlerTask,value);
        
        //发送微信或者短信消息
        wechatMessageService.receiveTask(butlerTaskUpdate, value);
        
        
        return butlerTaskUpdate;
	}
	
	/**
	 * 由于延时，失联，故障，维修，签约造成的任务清空车辆需要记录日志
	 * @param butlertask
	 * @param member
	 * @param operateType
	 */
	public void createButlertaskLogByemptyCar(ButlerTask butlertask, Member member,String remark){
		
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		if(null != member){
			butlerTaskStatusLog.setModifier(member.getSysuserName());
			butlerTaskStatusLog.setModifierId(member.getSysuserId());
			butlerTaskStatusLog.setModifierMobile(member.getMobile());
			butlerTaskStatusLog.setModifierType(1);

		}
		
		//插入记录
		butlerTaskStatusLog.setStatus(butlertask.getStatus());
		butlerTaskStatusLog.setTaskId(butlertask.getId());
		
		butlerTaskStatusLog.setRemark(remark);
		this.butlerTaskStatusLogService.createButlerTaskStatusLog(butlerTaskStatusLog);
	}
}
