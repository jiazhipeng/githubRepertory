package com.hy.authorization.util;

import java.util.Date;

/**
 * Created by WangShuai on 2016/7/22.
 */
public class StringUtil {

    public static String filterNull(Object obj){
        if(obj==null){
            return "";
        }
        return obj+"";
    }
    public static Date getDateByTime(Long millisecond){
    	
    	if(millisecond == null){
    		return new Date();
    	}
    	if(millisecond <= 0){
    		return new Date();
    	}
    	return new Date(millisecond);
    }
}
