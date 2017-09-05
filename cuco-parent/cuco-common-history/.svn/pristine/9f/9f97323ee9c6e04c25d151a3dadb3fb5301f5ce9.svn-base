package com.hy.common.utils;

import com.hy.exception.RuntimeExceptionWarn;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WangShuai on 2016/8/17.
 */
public class HttpServletUtils {

    public static String getBasePath(HttpServletRequest request){
        try {
            return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        }catch (Exception e){
            throw new RuntimeExceptionWarn("获取项目访问路径失败");
        }
    }
}
