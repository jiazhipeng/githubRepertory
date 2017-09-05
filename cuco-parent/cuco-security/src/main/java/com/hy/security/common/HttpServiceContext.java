package com.hy.security.common;

import com.hy.security.entity.User;

/**
 * 服务运行上下文
 * Created by WangShuai on 2016/7/29.
 */
public class HttpServiceContext {

	private static final ThreadLocal threadUser = new ThreadLocal();
	
	public static User getUser(){
		
		User user = (User) threadUser.get();
		if(null == user){
			user = new User();
		}
		return user;
	}
	
	public static void setUser(User user){
		threadUser.set(user);
	}
    /**
     * 获取当前用户ID
     * @return
     * @throws Exception 
     */
    public static Long getCurrentUserId(){
    	//return 1L;
        return getUser().getId();
    }

    public static String getCurrentUserName(){
    	//return "userName";
        return getUser().getName();
    }

}
