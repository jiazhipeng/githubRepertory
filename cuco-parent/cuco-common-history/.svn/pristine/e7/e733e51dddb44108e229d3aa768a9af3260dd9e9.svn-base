package com.hy.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WangShuai on 2016/7/26.
 */
public class LogUtil {

    public static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    /**
     * 获取调用log的类
     * @return
     */
    public static Logger getLogger(){
        return LoggerFactory.getLogger(getPreCallStack(LogUtil.class,"getLogger").getClassName());
    }

    /**
     * 获取指定类和方法的调用前一个堆栈
     * @param clazz
     * @param method
     * @return
     */
    public static StackTraceElement getPreCallStack(Class<?> clazz,String method){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String clazzMethod = clazz.getName()+method;
        boolean target = false;
        StackTraceElement preStack = null;
        for(StackTraceElement stack:stacks){
            if(target){
                preStack = stack;
                break;
            }
            if((stack.getClassName()+stack.getMethodName()).contains(clazzMethod)){
                target = true;
            }
        }
        if(preStack==null){
            preStack = stacks[0];
        }
        return preStack;
    }

}
