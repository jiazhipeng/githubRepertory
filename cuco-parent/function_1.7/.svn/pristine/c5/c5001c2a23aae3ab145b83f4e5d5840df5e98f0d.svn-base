package com.hy.gcar.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hy.common.utils.HttpClientUtils;
import com.hy.common.utils.MD5Util;
import com.hy.gcar.utils.http.HttpClientUtil;
public class AppUtil {
	private static Logger logger = LoggerFactory.getLogger(AppUtil.class);
	/**
	 * 将字符串按照UTF-8解码
	 * @param params  待解码参数
	 * @return
	 */
	public static final int CAPTCHA_INTERVAL = 1000 * 60 *10;
	public static final int CAPTION_MAX_COUNT = 50;
	public static final int PAGE_NUM = 10;
	public static String decode(String params){
		String result = null;
		try {
			result = URLDecoder.decode(params, "UTF-8");
		} catch (Exception e) {
			logger.error("解码错误：{}", e.getMessage());
		}
		return result;
	}
   /**
    * 
    * @param params 需要加密的参数
    * @param sign   前台传过来的sign码
    * @return
    */
   public static boolean validateBySign(String params,String sign){
	   boolean result = true;
	   try {
		   	String curSign = MD5Util.MD5Encode(new String(params.getBytes("UTF-8"))).toUpperCase();
			if(!curSign.equals(sign)){
				result = false;
			}
	   	} catch (Exception e) {
	   		result = false;
	   		logger.error("加密验证异常",e.getMessage());
	   }
	   return result;
   }
   
   public static boolean sendMessage(String message,String mobile){
		boolean result = false;
		String userId = "JI1093";
		String password = "102102";
		String pszMobis = mobile;
		String pszMsg = message;
		String iMobiCount="1";
		String pszSubPort="*";
		String MsgId=RandomStringUtils.randomNumeric(19);
		try {
			String url = "http://101.251.214.153:8901/MWGate/wmgw.asmx/MongateSendSubmit?userId="+userId+"&password="+password+"&pszMobis="+pszMobis+"&pszMsg="+pszMsg+"&iMobiCount="+iMobiCount+"&pszSubPort="+pszSubPort+"&MsgId="+MsgId;
			String responseBody = HttpClientUtils.sendGet(url, null, "UTF-8");
			logger.info("调用短信接口======================返回值===="+responseBody);
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-5168371777020179491</string>
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-12</string>
			 if(StringUtils.isNotBlank(responseBody)){
				 String sub=responseBody.substring(74, responseBody.lastIndexOf("</string>"));
				 logger.info("截取返回值================="+sub);
				 if(sub.length()>15){//返回值长度大于15则表示成功
					 result = true;
					 logger.info("验证码发至{}送成功",mobile);
				 }else{
					 logger.error("验证码发至{}失败{}",mobile,responseBody);
				 }
			  } 
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
	}
   public static boolean sendMessage0(String message,String mobile){
		boolean result = false;
		String userId = "JI1093";
		String password = "102102";
		String pszMobis = mobile;
		String pszMsg = message;
		String iMobiCount="1";
		String pszSubPort="*";
		String MsgId=RandomStringUtils.randomNumeric(19);
		try {
			String url = "http://101.251.214.153:8901/MWGate/wmgw.asmx/MongateSendSubmit?userId="+userId+"&password="+password+"&pszMobis="+pszMobis+"&pszMsg="+pszMsg+"&iMobiCount="+iMobiCount+"&pszSubPort="+pszSubPort+"&MsgId="+MsgId;
			String responseBody = HttpClientUtils.sendGet(url, null, "UTF-8");
			logger.info("调用短信接口======================返回值===="+responseBody);
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-5168371777020179491</string>
//			<?xml version="1.0" encoding="utf-8"?><string xmlns="http://tempuri.org/">-12</string>
			 if(StringUtils.isNotBlank(responseBody)){
				 String sub=responseBody.substring(74, responseBody.lastIndexOf("</string>"));
				 logger.info("截取返回值================="+sub);
				 if(sub.length()>15){//返回值长度大于15则表示成功
					 result = true;
					 logger.info("验证码发至{}送成功",mobile);
				 }else{
					 logger.error("验证码发至{}失败{}",mobile,responseBody);
				 }
			  } 
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
	}
   
   /*public static boolean sendMessage0(String message,String mobile){
	   	boolean result = false;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse  response=null;
		try {
			String username = "hycx";
			String pwd = MD5Util.MD5Encode("hycx123");
			String epid = "120589";
			String linkid = RandomNumberUtil.getRandNumber(12);
			String url = "http://114.255.71.158:8061/?username="+username+"&password="+pwd+"&message="+URLEncoder.encode(message, "gb2312")+"&phone="+mobile+"&epid="+epid+"&linkid="+linkid+"&subcode=";
			HttpGet getMethod = null;
	        httpClient = HttpClients.createDefault();
	        getMethod = new HttpGet(url);
	        response = httpClient.execute(getMethod);
	        HttpEntity responseEntity = response.getEntity();
	        String responseBody = EntityUtils.toString(responseEntity, "UTF-8");
	        logger.error("send code result :"+responseBody);
	        if("00".equals(responseBody)){
	        	result = true;
	    	   logger.error("验证码发至{}送成功",mobile);
	        }else{
	    	   logger.error("验证码发至{}异常{}",mobile,responseBody);
	        }
		} catch (Exception e) {
	    	logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}finally{
			try {
				if(null!=response)
					response.close();
				if(null!=httpClient)
					httpClient.close();
			} catch (IOException e) {
				logger.error("验证码发至{}异常{}",mobile,e.getMessage());
			}
		}
		return result;
   }*/
   
   /*public static boolean sendMessage(String message,String mobile){
	   	boolean result = false;
	   	String username = "hycx";
		String pwd = MD5Util.MD5Encode("hycx123");
		String epid = "120589";
		String linkid = RandomNumberUtil.getRandNumber(12);
		String url;
		try {
			url = "http://114.255.71.158:8061/?username="+username+"&password="+pwd+"&message="+URLEncoder.encode(message, "gb2312")+"&phone="+mobile+"&epid="+epid+"&linkid="+linkid+"&subcode=";
			String responseBody = HttpClientUtil.get(url);//EntityUtils.toString(responseEntity, "UTF-8");
	        logger.error("send code result :"+responseBody);
	        if("00".equals(responseBody)){
	        	result = true;
	    	   logger.error("验证码发至{}送成功",mobile);
	        }else{
	    	   logger.error("验证码发至{}异常{}",mobile,responseBody);
	        }
		} catch (Exception e) {
			logger.error("验证码发至{}异常{}",mobile,e.getMessage());
		}
		return result;
   }*/
   
   public static String createTaskId() {
	   
		return new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())+RandomStringUtils.randomNumeric(4);
	}
   
}
