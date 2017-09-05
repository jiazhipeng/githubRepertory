package com.hy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hy.common.utils.LogUtil;
import com.hy.enums.ServerStatus;
import com.hy.httpservice.ResponseBody;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class RuntimeExceptionWarn extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String code;

    private String msg;

    public RuntimeExceptionWarn(ServerStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.getLog().warn("[code:"+status.getCode()+" ; message:"+status.getMessage()+"]");
    }

    public RuntimeExceptionWarn(ServerStatus status,String msg) {
        super(msg);
        this.code = status.getCode();
        this.msg = msg;
        this.getLog().warn("[code:"+status.getCode()+" ; message:"+msg+"]");
    }

    public RuntimeExceptionWarn(String msg) {
        super(msg);
        ServerStatus status = ServerStatus.USER_DEFINITION;
        this.code = status.getCode();
        this.msg = msg;
        this.getLog().warn("[code:"+status.getCode()+" ; message:"+msg+"]");
    }

    public RuntimeExceptionWarn(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.getLog().warn("[code:"+code+" ; message:"+msg+"]");
    }

    public ResponseBody getResponseBody(){
        return new ResponseBody(code,msg);
    }

    private Logger getLog(){
        StackTraceElement preStack = LogUtil.getPreCallStack(RuntimeExceptionWarn.class, "<init>");
        return LoggerFactory.getLogger(preStack.getClassName());
    }

    public String getCode() {
        return code;
    }

    public static void throwException(ServerStatus serverStatus){
        throw new RuntimeExceptionWarn(serverStatus);
    }

    public static void throwException(){

    }
}
