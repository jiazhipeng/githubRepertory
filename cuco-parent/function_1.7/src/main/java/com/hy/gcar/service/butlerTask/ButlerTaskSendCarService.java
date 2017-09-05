package com.hy.gcar.service.butlerTask;

import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.Member;

/**
 * Created by 海易出行 on 2016/12/8.
 */
public interface ButlerTaskSendCarService {
    /**
     * 更换管家
     * @param butlerTask
     * @return
     */
    public ButlerTask updateSendCarHousekeeper(ButlerTask butlerTask);
    
    /**
     * 等待送车
     */
    public ButlerTask updateWaitForSendCar(ButlerTask butlerTask) throws Exception;
    
    /**
     * 由于车辆被其他任务使用，可能造成任务（MM-dd HH:mm)需重新分配车辆,插入日志
     * @param butlertask
     * @param member
     * @param remark
     */
    public void createButlertaskLogByemptyCar(ButlerTask butlertask, Member member,String remark);
}
