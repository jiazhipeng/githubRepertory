package com.hy.gcar.constant;

/**
 * Created by 海易出行 on 2016/9/10.
 * 任务状态枚举
 */
public enum ButlerTaskStatus {

    /**
     * 待送车
     */
    BUTLER_TASK_STATUS_WAITING_SEND_FOR_A_CAR(0,"待送车"),
    /**
     * 送车中
     */
    BUTLER_TASK_STATUS_SEND_CAR(1,"送车中"),
    /**
     * 待取车
     */
    BUTLER_TASK_STATUS_WAITING_GET_FOR_A_CAR(2,"待取车"),

    /**
     * 取车中
     */
    BUTLER_TASK_STATUS_TAKE_THE_CAR(3,"取车中"),

    /**
     * 已到达
     */
    BUTLER_TASK_STATUS_ALREADY_ARRIVED(4,"已到达"),

    /**
     * 充电中
     */
    BUTLER_TASK_STATUS_CHARGE_IN(5,"充电中"),

    /**
     * 已失联
     */
    BUTLER_TASK_STATUS_LOST_CONTACT(6,"已失联"),

    /**
     * 已完成
     */
    BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED(7,"已完成"),

    /**
     * 已取消
     */
    BUTLER_TASK_STATUS_HAS_BEEN_CANCELED(8,"已取消"),
    /**
     * 待处理
     */
    BUTLER_TASK_STATUS_PENDING_TREATMENT(9,"待处理"),
    /**
     * 待接单
     */
    BUTLER_TASK_STATUS_PENDING_ORDERS(10,"待接单");


    ButlerTaskStatus(Integer index,String status){
        this.index = index;
        this.status = status;
    };
    private Integer index;

    private String status;

    public Integer getIndex() {
        return index;
    }

    public String getStatus() {
        return status;
    }

    public static String getStatus(Integer index){
        if(index == null){
            return "";
        }
        for(ButlerTaskStatus butlerTaskStatus: ButlerTaskStatus.values()){
            if(butlerTaskStatus.getIndex() == index.intValue()){
                return butlerTaskStatus.getStatus();
            }
        }
        return "";
    }
}
