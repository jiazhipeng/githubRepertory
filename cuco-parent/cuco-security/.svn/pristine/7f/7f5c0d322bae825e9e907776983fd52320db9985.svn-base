package com.hy.security.util;

import java.util.Date;

import com.hy.constant.Constant;

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
    
    public static String getSystemName(){
    	return "security_";
    }
    
    
    public static String getFullScenId(String scenId){
    	return getSystemName()+Constant.QR_CODE_PREFIX+scenId;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
    	Date dateByTime = StringUtil.getDateByTime(new Date().getTime()+Constant.TOKEN_ENDTIME_MILLISECOND);
    	System.out.println(dateByTime);
	}
}
