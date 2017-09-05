package com.hy.common.utils;


import com.hy.constant.Constant;

/**
 * 持久化前处理通用属性
 * Created by WangShuai on 2016/7/29.
 */
public class PrePersistUtil {

    public static void onCreate(Object object,Object userId,String userName){
        if(object == null){
            return;
        }

        /* created */
        handleCreated(object);

        /* isValue */
        handleIsValue(object);

        onModify(object,userId,userName);
    }

    public static void onModify(Object object,Object userId,String userName){
        if(object == null){
            return;
        }

        /* lasttimeModify */
        handleLasttimeModify(object);

        /* modifierId */
        handleModifierId(object,userId);

        /* modifier */
        handleModifier(object,userName);
    }

    public static void handleCreated(Object object){
        handleValue(object, Constant.created, DateUtils.now());
    }

    public static void handleIsValue(Object object){
        handleValue(object,Constant.isValue,Constant.IS_VALUE_ENABLE);
    }

    public static void handleLasttimeModify(Object object){
        handleValue(object,Constant.lasttimeModify, DateUtils.now());
    }

    public static void handleModifierId(Object object,Object userId){
        handleValue(object,Constant.modifierId,userId);
    }

    public static void handleModifier(Object object,String userName){
        handleValue(object,Constant.modifier,userName);
    }

    /**
     * 为对象的某属性赋值
     * @param object
     * @param filed
     * @param value
     */
    private static void handleValue(Object object,String filed,Object value){
        try {
            //是否有该属性
            if(Reflections.getAccessibleField(object,filed) == null){
                return;
            }
            //修改属性值
            Reflections.setFieldValue(object,filed,value);
        }catch (Exception e){
            LogUtil.getLogger().error("给对象属性赋值失败：",e);
        }
    }

}
