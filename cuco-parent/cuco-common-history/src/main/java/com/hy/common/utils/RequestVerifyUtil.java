package com.hy.common.utils;


import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;

/**
 * 请求参数校验
 * Created by WangShuai on 2016/7/29.
 */
@Deprecated
public class RequestVerifyUtil {

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

    public static void paramNotEmpty(String param){
        paramNotNull(param);

        if(param.trim().length() == 0){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
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
}
