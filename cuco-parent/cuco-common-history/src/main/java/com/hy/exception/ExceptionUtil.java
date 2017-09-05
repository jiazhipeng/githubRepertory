package com.hy.exception;

import com.hy.common.utils.LogUtil;
import com.hy.common.utils.RegexUtils;
import com.hy.common.utils.StringUtils;
import com.hy.enums.ServerStatus;
import com.hy.httpservice.ResponseBody;
import com.hy.httpservice.ResponseUtil;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class ExceptionUtil {

    public static ResponseBody processException(Exception e){
        if(e instanceof RuntimeExceptionWarn){
            return ((RuntimeExceptionWarn) e).getResponseBody();
        }
        //遇到未处理的异常，则转换为友好的用户提示信息
        /*getLog().error("错误预警：", e);
        return ResponseUtil.toFailureBody(ServerStatus.EXECUTE_FAILURE,"服务繁忙");*/

        LogUtil.getLogger().error("服务器异常：",e);
        return ResponseUtil.toFailureBody(ServerStatus.UNKNOW_ERROR,"");
    }

    public static void throwWarn(ServerStatus serverStatus){
        throw new RuntimeExceptionWarn(serverStatus);
    }
    public static void throwWarn(String message){
        throw new RuntimeExceptionWarn(StringUtils.fillNull(message));
    }
    public static void throwWarn(ServerStatus serverStatus,String message){
    	throw new RuntimeExceptionWarn(serverStatus,StringUtils.fillNull(message));
    }
}
