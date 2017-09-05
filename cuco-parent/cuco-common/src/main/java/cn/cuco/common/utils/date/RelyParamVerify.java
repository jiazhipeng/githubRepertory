package cn.cuco.common.utils.date;

import cn.cuco.cache.RelyRegex;
import cn.cuco.enums.ServerStatus;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.exception.RuntimeExceptionWarn;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * description：参数基本校验工具类
 * author：WangShuai
 * time：2017年02月20 13:54:00
 * 修改时间：2017年02月20 13:54:00
 * 修改备注：
 */
public class RelyParamVerify {


    public static void paramNotNull(Object object){
        if(object == null){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_NULL);
        }
    }
    public static void paramNotNull(Object object,String message){
        if(object == null){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_NULL,fillNull(message));
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
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_NULL, fillNull(message));
        }else if(param.toString().getBytes().length > limitLength){
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_LIMIT_LENGTH,fillNull(ServerStatus.PARAM_LIMIT_LENGTH.getMessage()));
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
            throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY,fillNull(message));
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
            throw new RuntimeExceptionWarn(ServerStatus.RESOURCE_NOT_EXISTS,fillNull(message));
        }
    }

    public static void exists(boolean b,String message){
        if(b){
            throw new RuntimeExceptionWarn(ServerStatus.RESOURCE_EXISTS,fillNull(message));
        }
    }

    public static void verifyMobile(String mobile,String message){
        message = StringUtils.isEmpty(message) ? "手机号不合法" : message;
        if(!RelyRegex.isMobile(mobile)){
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
    public static void verifyStartEndTime(Date startTime, Date endTime){
        RelyParamVerify.paramNotNull(startTime,"开始时间不可为空");
        RelyParamVerify.paramNotNull(endTime,"结束时间不可为空");
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
        RelyParamVerify.paramNotNull(startTime,"开始时间不可为空");
        RelyParamVerify.paramNotNull(endTime,"结束时间不可为空");
        if(startTime.after(endTime)){
            ExceptionUtil.throwWarn(StringUtils.defaultString(message, "开始时间不可晚于结束时间"));
        }
    }


    /**
     * 过滤null为空串
     * @param str
     * @return
     * @author WangShuai
     */
    private static String fillNull(String str){
        return str == null? "":str;
    }
}
