package cn.cuco.exception;

import cn.cuco.common.utils.regex.RelyLog;
import cn.cuco.enums.ServerStatus;
import cn.cuco.httpservice.ResponseBody;
import cn.cuco.httpservice.ResponseUtil;

/**
 * 
 * @ClassName: ExceptionUtil 
 * @Description: 异常工具类
 * @author: wangchuntao 
 * @date: 2017年2月20日 上午11:50:42
 */
public class ExceptionUtil {

    public static ResponseBody processException(Exception e){
        if(e instanceof RuntimeExceptionWarn){
            return ((RuntimeExceptionWarn) e).getResponseBody();
        }
        //遇到未处理的异常，则转换为友好的用户提示信息
        /*getLog().error("错误预警：", e);
        return ResponseUtil.toFailureBody(ServerStatus.EXECUTE_FAILURE,"服务繁忙");*/

        RelyLog.getLogger().error("服务器异常：",e);
        return ResponseUtil.toFailureBody(ServerStatus.UNKNOW_ERROR,"");
    }

    public static void throwWarn(ServerStatus serverStatus){
        throw new RuntimeExceptionWarn(serverStatus);
    }
    public static void throwWarn(String message) throws RuntimeExceptionWarn{
        throw new RuntimeExceptionWarn(RelyString.fillNull(message));
    }
    public static void throwWarn(ServerStatus serverStatus,String message){
    	throw new RuntimeExceptionWarn(serverStatus,RelyString.fillNull(message));
    }

  
}
