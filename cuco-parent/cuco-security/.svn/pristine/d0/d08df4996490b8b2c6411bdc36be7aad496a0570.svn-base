package com.hy.security.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hy.constant.Constant;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.entity.User;

/**
 * Created by WangShuai on 2016/7/23.
 */

/**
 * 数据校验相关
 */
public class VerifyUtil {

    /**
     * 判断是否是标准手机号码
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber){
        return true;
    }
    
    public static boolean isAdmin(User user){
    	
    	if(user == null 
    			|| user.getIsAdmin() == null){
			
			return false;
		}else{
			if(user.getIsAdmin().intValue() == Constant.INTEGER_YES.intValue()){
				return true;
			}else{
				return false;
			}
		}
    }

}
