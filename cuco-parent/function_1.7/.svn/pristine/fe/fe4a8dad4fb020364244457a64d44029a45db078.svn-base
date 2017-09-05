package com.hy.gcar.constant;

/**
 * Created by 海易出行 on 2016/9/10.
 * 任务类型枚举
 */
public enum ButlerTaskType {

    /**
     * 充电任务
     */
    BUTLER_TASK_TYPE_CHARGING(1,"充电任务"),
    /**
     * 取车任务
     */
    BUTLER_TASK_TYPE_GETCAR(2,"取车任务"),
    /**
     * 送车任务
     */
    BUTLER_TASK_TYPE_RETURNCAR(3,"送车任务");


    ButlerTaskType(Integer index,String type){
        this.index = index;
        this.type = type;
    };
    
    private Integer index;

    private String type;

    public Integer getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public static String getType(Integer index){
        if(index == null){
            return "";
        }
        for(ButlerTaskType butlerTaskType: ButlerTaskType.values()){
            if(butlerTaskType.getIndex() == index.intValue()){
                return butlerTaskType.getType();
            }
        }
        return "";
    }
}
