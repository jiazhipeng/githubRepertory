package com.hy.gcar.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class ResultUtils {
	
	
	public static final String ERROR_CODE = "errcode";
	public static final String ERR_MSG = "errmsg";
	public static final String RESULT = "result";
	
	
	/**
	 * 接口返回失败信息
	 * @param errorCode
	 * @param errmsg
	 * @return
	 */
	public static Map<String,Object> fail(String errorCode,String errmsg){
		Map<String,Object> map = Maps.newHashMap();
		map.put(ERROR_CODE, errorCode);
		map.put(ERR_MSG, errmsg);
		return map;
	}
	/**
	 * 接口返回失败信息
	 * @param errorCode
	 * @param errmsg
	 * @return
	 */
	public static Map<String,Object> fail(String errmsg){
		Map<String,Object> map = Maps.newHashMap();
		map.put(ERROR_CODE, "error");
		map.put(ERR_MSG, errmsg);
		return map;
	}
	
	/**
	 * 接口返回成功信息
	 * @param result
	 * @return
	 */
	public static Map<String,Object> success(String errmsg,Object result){
		Map<String,Object> map = Maps.newHashMap();
		map.put(ERROR_CODE, "ok");
		map.put(ERR_MSG, "操作成功");
		if(StringUtils.isNotBlank(errmsg)){
			map.put(ERR_MSG,errmsg);
		}
		if(result != null){
			map.put(RESULT, result);
		}
		return map;
	}

	/**
	 * 接口返回成功信息
	 * @param result
	 * @return
	 */
	public static Map<String,Object> success(Object result){
		Map<String,Object> map = Maps.newHashMap();
		map.put(ERROR_CODE, "ok");
		map.put(ERR_MSG, "操作成功");
		if(result != null){
			map.put(RESULT, result);
		}
		return map;
	}

}
