package cn.cuco.httpservice;

import cn.cuco.exception.ExceptionUtil;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class APIProcessor {

    public static Object execute(APIHandler action){
        try {
            return ResponseUtil.toSuccessBody(action.invoke());
        }catch (Exception e){
            return ExceptionUtil.processException(e);
        }
    }
}
