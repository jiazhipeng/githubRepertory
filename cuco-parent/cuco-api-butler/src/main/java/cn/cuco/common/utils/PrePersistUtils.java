package cn.cuco.common.utils;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.reflection.Reflections;
import cn.cuco.constant.Constant;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月27 10:18:00
 */
public class PrePersistUtils {

    public PrePersistUtils() {
    }

    public static void onCreate(Object object, Object userId, String userName) {
        if(object != null) {
            handleCreated(object);
            handleIsValue(object);
            handleCreater(object,userName);
            onModify(object, userId, userName);
        }
    }

    public static void onModify(Object object, Object userId, String userName) {
        if(object != null) {
            handleLasttimeModify(object);
            handleModifierId(object, userId);
            handleModifier(object, userName);
        }
    }

    public static void handleCreated(Object object) {
        handleValue(object, "created", DateUtils.now());
    }

    public static void handleIsValue(Object object) {
        handleValue(object, "isValue", Constant.IS_VALUE_ENABLE);
    }

    public static void handleLasttimeModify(Object object) {
        handleValue(object, "lasttimeModify", DateUtils.now());
    }

    public static void handleModifierId(Object object, Object userId) {
        handleValue(object, "modifierId", userId);
    }

    public static void handleModifier(Object object, String userName) {
        handleValue(object, "modifier", userName);
    }

    public static void handleCreater(Object object, String userName) {
        handleValue(object, "creater", userName);
    }

    private static void handleValue(Object object, String filed, Object value) {
        try {
            if(Reflections.getAccessibleField(object, filed) == null) {
                return;
            }

            Reflections.setFieldValue(object, filed, value);
        } catch (Exception var4) {
            LogUtils.getLogger().error("给对象属性赋值失败：", var4);
        }

    }
}
