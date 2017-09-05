package com.hy.security.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zhubing
 *
 */
public class MemoryToken {

	
	/**
	 * 服务器端存储的token
	 */
	public static Map<String,Long> serverToken = new HashMap<String,Long>();
	
	/**
	 * 服务器返回客户端存储的token
	 */
	public static Map<String,Long> loginToken = new HashMap<String,Long>();
	
}
