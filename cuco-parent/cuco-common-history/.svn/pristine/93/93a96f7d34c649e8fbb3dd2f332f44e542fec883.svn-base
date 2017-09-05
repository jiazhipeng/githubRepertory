package com.hy.common.utils;


import com.hy.enums.ServerStatus;
import com.hy.exception.ExceptionUtil;
import com.hy.exception.RuntimeExceptionWarn;

import java.util.Date;

/**
 * 请求参数校验
 * Created by WangShuai on 2016/7/29.
 */
public class ParamVerifyUtil {

    public static void paramNotNull(Object object){
        if(object == null){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_NULL);
        }
    }
    public static void paramNotNull(Object object,String message){
        if(object == null){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_NULL,StringUtils.fillNull(message));
        }
    }
    /**
     * 限制长度
     * @param param
     * @param message
     * @param limitLength  定长
     */
    @Deprecated
    public static void paramNotNull(Object param,String message,int limitLength){
        if(param == null){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_NULL,StringUtils.fillNull(message));
        }else if(param.toString().getBytes().length > limitLength){
        	throw new RuntimeExceptionWarn(ServerStatus.PARAM_LIMIT_LENGTH,StringUtils.fillNull(ServerStatus.PARAM_LIMIT_LENGTH.getMessage()));
        }
    }

    public static void paramNotEmpty(String param){
        paramNotNull(param);

        if(param.trim().length() == 0){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
        }
    }

    public static void paramNotEmpty(String param,String message){
        paramNotNull(param,message);

        if(param.trim().length() == 0){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY,StringUtils.fillNull(message));
        }
    }

    public static void notExists(){
        throw new RuntimeExceptionWarn(ServerStatus.RESOURCE_NOT_EXISTS);
    }

    public static void notExists(Boolean b){
        if(b==null || !b){
            throw new RuntimeExceptionWarn(ServerStatus.RESOURCE_NOT_EXISTS);
        }
    }

    public static void notExists(Boolean b,String message){
        if(b==null || !b){
            throw new RuntimeExceptionWarn(ServerStatus.RESOURCE_NOT_EXISTS,StringUtils.fillNull(message));
        }
    }

    public static void exists(boolean b,String message){
        if(b){
            throw new RuntimeExceptionWarn(ServerStatus.RESOURCE_EXISTS,StringUtils.fillNull(message));
        }
    }

    public static void verifyMobile(String mobile,String message){
        message = StringUtils.isEmpty(message) ? "手机号不合法" : message;
        if(!RegexUtils.isMobile(mobile)){
            throw new RuntimeExceptionWarn(message);
        }
    }

    /**
     * 判断开始结束时间是否合规
     * 1.开始时间和结束时间均不可为空
     * 2.开始时间不可晚于结束时间
     * @param startTime
     * @param endTime
     */
    public static void verifyStartEndTime(Date startTime,Date endTime){
        ParamVerifyUtil.paramNotNull(startTime,"开始时间不可为空");
        ParamVerifyUtil.paramNotNull(endTime,"结束时间不可为空");
        if(startTime.after(endTime)){
            ExceptionUtil.throwWarn("开始时间不可晚于结束时间");
        }
    }
    /**
     * 
     * @param startTime
     * @param endTime
     * @param message 提示消息
     */
    public static void verifyStartEndTime(Date startTime,Date endTime,String message){
        ParamVerifyUtil.paramNotNull(startTime,"开始时间不可为空");
        ParamVerifyUtil.paramNotNull(endTime,"结束时间不可为空");
        if(startTime.after(endTime)){
            ExceptionUtil.throwWarn(StringUtils.defaultString(message, "开始时间不可晚于结束时间"));
        }
    }
}
