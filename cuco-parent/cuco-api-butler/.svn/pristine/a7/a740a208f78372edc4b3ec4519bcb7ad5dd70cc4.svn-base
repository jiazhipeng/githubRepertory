package cn.cuco.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月27 10:19:00
 */
public class LogUtils {


    public static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public LogUtils() {
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(getPreCallStack(LogUtils.class, "getLogger").getClassName());
    }

    public static StackTraceElement getPreCallStack(Class<?> clazz, String method) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String clazzMethod = clazz.getName() + method;
        boolean target = false;
        StackTraceElement preStack = null;
        StackTraceElement[] arr$ = stacks;
        int len$ = stacks.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            StackTraceElement stack = arr$[i$];
            if(target) {
                preStack = stack;
                break;
            }

            if((stack.getClassName() + stack.getMethodName()).contains(clazzMethod)) {
                target = true;
            }
        }

        if(preStack == null) {
            preStack = stacks[0];
        }

        return preStack;
    }
}
