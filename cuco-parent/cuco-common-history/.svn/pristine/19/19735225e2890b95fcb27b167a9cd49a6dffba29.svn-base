package com.hy.httpservice;

import com.hy.common.utils.JsonUtil;
import com.hy.common.utils.StringUtils;
import com.hy.enums.ServerStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class ResponseUtil {

    public static ResponseBody toFailureBody(ServerStatus serverStatus, String message){
        StringBuffer msg = new StringBuffer(serverStatus.getMessage());
        if(StringUtils.isNotEmpty(message) && StringUtils.isNotEmpty(msg.toString())){
            msg.append(":");
        }
        msg.append(message);
        return new ResponseBody(serverStatus.getCode(),msg.toString());
    }

    public static ResponseBody toSuccessBody(Object data){
        return new ResponseBody(ServerStatus.SUCCESS.getCode(),ServerStatus.SUCCESS.getMessage(),data);
    }
    public static ResponseBody toSuccessBody(ServerStatus serverStatus,Object data){
        return new ResponseBody(serverStatus.getCode(),serverStatus.getMessage(),data);
    }

    public static void writeResponse(HttpServletResponse response,ServerStatus serverStatus,Object data) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        ResponseBody responseBody = new ResponseBody(serverStatus.getCode(),serverStatus.getMessage(),data);
        response.getWriter().write(JsonUtil.getObjectMapper().writeValueAsString(responseBody));
    }

}
