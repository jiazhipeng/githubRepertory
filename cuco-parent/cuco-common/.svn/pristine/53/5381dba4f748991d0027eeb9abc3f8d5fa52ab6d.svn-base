package cn.cuco.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description：日期处理工具类
 * author：WangShuai
 * time：2017年02月20 14:15:00
 * 修改时间：2017年02月20 14:15:00
 * 修改备注：
 */
public class RelyLog {
    public static Logger logger = LoggerFactory.getLogger(RelyLog.class);

    /**
     * 获取调用log的类
     * @return
     */
    public static Logger getLogger(){
        return LoggerFactory.getLogger(getPreCallStack(RelyLog.class,"getLogger").getClassName());
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
