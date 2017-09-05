package com.hy.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class ImageQrcodeUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ImageQrcodeUtil.class);
	
	/**
	 * 创建临时二维码
	 * @param token access_token
	 * @param basePath 二维码文件存放位置(不能以'/'结尾)
	 * @param expireSeconds 二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
	 * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return
	 * @throws Exception
	 */
	public static String getTempImageQrcode(String token,String basePath,Integer expireSeconds,Integer sceneId) throws Exception{
		if(null == expireSeconds || expireSeconds <= 0|| expireSeconds > 2592000)
			expireSeconds = 2592000;
		if(null==sceneId ||sceneId.intValue() == 0)
			throw new RuntimeException("创建临时二维码时,场景值ID为32位非0整型");
		return getImageQrcode(token, basePath, true, expireSeconds, sceneId,null);
	}
	
	/**
	 * 创建永久二维码
	 * @param token access_token
	 * @param basePath 二维码文件存放位置(不能以'/'结尾)
	 * @param sceneId 场景值ID，目前参数只支持1--100000，如果需要生成字符串型的场景值，该参数必须设置为null
	 * @param scene_str 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	 * @return
	 * @throws Exception
	 */
	public static String getPermanentImageQrcode(String token,String basePath,Integer sceneId,String sceneStr)throws Exception{
		return getImageQrcode(token, basePath, false, null, sceneId, sceneStr);
	}
	
	
	/**
	 * 
	 * @param type 1:专属邀请码  2:市场活动
	 * @param id
	 * @param basePath （不能以'/'结尾）
	 * @return
	 */
	public static String getImageQrcode(int type,Long id,String basePath) throws Exception{
		String path = "";
		String scene_str = "";
		//String token = JedisUtils.getObject("accesstoken").toString();
		String token = AccessTokenUtils.getAccessToken();
		//获取ticket
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
		if(type==1){
			scene_str = "member_"+id;
		}else if(type==2){
			scene_str = "marketing_"+id;
		}
		String jsonParam = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\": {\"scene_str\":\""+scene_str+"\"}}}";//二维码参数为字符串
		logger.info("生成ticket参数："+jsonParam);
		String ticket_result = execute(url,"POST",jsonParam);
		JSONObject ticket_resultObj = JSONObject.parseObject(ticket_result);
		String ticket = ticket_resultObj.getString("ticket");
		String expire_seconds = ticket_resultObj.getString("expire_seconds");
		
		logger.info("ticket:"+ticket);
		logger.info("expire_seconds:"+expire_seconds);
		logger.info("url:"+ticket_resultObj.getString("url"));
		
		//根据ticket换取二维码
		jsonParam = URLEncoder.encode(ticket,"UTF-8");
		logger.info("jsonParam:"+jsonParam);
		url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+jsonParam;
		path = getPic(url,"GET",jsonParam,basePath);
		return path;
	}
	
	
	/**
	 * 生成二维码
	 * @param token  access_token
	 * @param basePath  二维码文件存放位置(不能以'/'结尾)
	 * @param isTemp  是否为临时二维码
	 * @param expireSeconds  如果为临时二维码时，需要指定二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
	 * @param sceneId  	场景值ID 临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param content  	场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段 
	 * @return
	 * @throws Exception
	 */
	private static String getImageQrcode(String token,String basePath,boolean isTemp,Integer expireSeconds,Integer sceneId,String scene_str) throws Exception{
		String path = "";
		//获取ticket
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
		String jsonParam = "";
		if(isTemp){
			logger.info("请求生成临时二维码========");
			jsonParam = "{\"expire_seconds\": "+expireSeconds+",\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\": {\"scene_id\":"+sceneId+"}}}";//二维码参数为字符串
		}else{
			logger.info("请求生成永久二维码========");
			if(sceneId > 0){//{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
				jsonParam = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\": {\"scene_id\":"+sceneId+"}}}";//二维码参数为整型
			}else{
				jsonParam = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\": {\"scene_str\":\""+scene_str+"\"}}}";//二维码参数为字符串
			}
		}
		logger.info("生成ticket参数："+jsonParam);
		String ticket_result = execute(url,"POST",jsonParam);
		JSONObject ticket_resultObj = JSONObject.parseObject(ticket_result);
		String ticket = ticket_resultObj.getString("ticket");
		String expire_seconds = ticket_resultObj.getString("expire_seconds");
		
		logger.info("ticket:"+ticket);
		logger.info("expire_seconds:"+expire_seconds);
		logger.info("url:"+ticket_resultObj.getString("url"));
		
		//根据ticket换取二维码
		jsonParam = URLEncoder.encode(ticket,"UTF-8");
		logger.info("jsonParam:"+jsonParam);
		url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+jsonParam;
		path = getPic(url,"GET",jsonParam,basePath);
		return path;
	}
	
	private static String getPic(String path,String method,String jsonParam,String filePath) throws Exception{
		Date date = new Date();
		//建立连接
	    URL url = new URL(path);
	    HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
	    httpUrlConn.setRequestProperty("Accept-Ranges", "bytes");
	    byte[] requestStringBytes = jsonParam.getBytes("UTF-8");
	    httpUrlConn.setRequestProperty("Cache-control", "max-age=604800");
	    httpUrlConn.setRequestProperty("connection", "Keep-Alive");
	    httpUrlConn.setRequestProperty("Content-Length", ""+requestStringBytes.length);
	    httpUrlConn.setRequestProperty("Content-Type", "image/jpg");
	    httpUrlConn.setRequestProperty("Date", DateFormatUtils.format(date, "EEE, d MMM yyyy HH:mm:ss Z",Locale.US));
	    date = org.apache.commons.lang3.time.DateUtils.addDays(date, 1);
	    httpUrlConn.setRequestProperty("Expires", DateFormatUtils.format(date, "EEE, d MMM yyyy HH:mm:ss Z",Locale.US));
	    httpUrlConn.setRequestProperty("Server","nginx/1.4.1");
	    
	    // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	    TrustManager[] tm = { new MyX509TrustManager() };
	    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	    sslContext.init(null, tm, new java.security.SecureRandom());
	    // 从上述SSLContext对象中得到SSLSocketFactory对象
	    SSLSocketFactory ssf = sslContext.getSocketFactory();
	    httpUrlConn.setSSLSocketFactory(ssf);
	    httpUrlConn.setDoOutput(true);
	    httpUrlConn.setDoInput(true);
	    // 设置请求方式（GET/POST）
	    if(StringUtils.isBlank(method)){
	    	method = "GET";
	    }
	    method = method.toUpperCase();
	    httpUrlConn.setRequestMethod(method);
	    
	    if("POST".equals(method)){
	    	PrintWriter out = new PrintWriter(new OutputStreamWriter(httpUrlConn.getOutputStream(),"UTF-8"));
	    	out.print(jsonParam);
	    	out.flush();
	    }
	    // 取得输入流
	    InputStream inputStream = httpUrlConn.getInputStream();
	    File file = new File(filePath);
	    if(!file.exists())
	    	file.mkdirs();
	    filePath = filePath+"/"+new Date().getTime()+".jpg";
		FileOutputStream fos = new FileOutputStream(filePath);
		byte[] b = new byte[1024];
		int len;
	    while ((len = inputStream.read(b)) != -1) {
	      fos.write(b, 0, len);
	    }
		fos.close();
	    inputStream.close();
	    httpUrlConn.disconnect();
	    //输出返回结果
		return filePath;
	}
	
	private static String execute(String path,String method,String jsonParam) throws Exception {
	    URL url = new URL(path);
	    HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
	    // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	    TrustManager[] tm = { new MyX509TrustManager() };
	    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	    sslContext.init(null, tm, new java.security.SecureRandom());
	    // 从上述SSLContext对象中得到SSLSocketFactory对象
	    SSLSocketFactory ssf = sslContext.getSocketFactory();
	    httpUrlConn.setSSLSocketFactory(ssf);
	    httpUrlConn.setDoOutput(true);
	    httpUrlConn.setDoInput(true);
	    // 设置请求方式（GET/POST）
	    if(StringUtils.isBlank(method)){
	    	method = "GET";
	    }
	    method = method.toUpperCase();
	    httpUrlConn.setRequestMethod(method);
	    
	    if("POST".equals(method)){
	    	PrintWriter out = new PrintWriter(new OutputStreamWriter(httpUrlConn.getOutputStream(),"UTF-8"));
	    	out.print(jsonParam);
	    	out.flush();
	    }
	    // 取得输入流
	    InputStream inputStream = httpUrlConn.getInputStream();
	    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	    //读取响应内容
	    StringBuffer buffer = new StringBuffer();
	    String str = null;
	    while ((str = bufferedReader.readLine()) != null) {
	      buffer.append(str);
	    }
	    bufferedReader.close();
	    inputStreamReader.close();
	    // 释放资源
	    inputStream.close();
	    httpUrlConn.disconnect();
	    //输出返回结果
	    logger.info(buffer.toString());
	    return buffer.toString();
	}
	
	public static void main(String[] args) throws Exception {
		/*String token = "RdaOvA-DGw2vGr-LkgK5KjY0jbRHabC81hsYDUjiLCmjsCDx4hratiXHoK9m0z7PCoonOyoqDx6AFY5feMOk2dlvBnInsbvZgdmKcg7fkQhpuEOyjcwCjkSkrVLZW2XuZOLhAIAFUR";
		String basePath = "d:/test/";
		Long expireSeconds = 60 * 5L;//五分钟
		int sceneId = 1;//2147483648-2147483648 
		String sceneStr = "";
		//ImageQrcodeUtil.getTempImageQrcode(token, basePath, expireSeconds, sceneId);//生成临时二维码
		ImageQrcodeUtil.getPermanentImageQrcode(token, basePath, sceneId, null);//生成整型场景值的永久二维码
		//ImageQrcodeUtil.getPermanentImageQrcode(token, basePath, null, sceneStr);//生成整型场景值的永久二维码
		
		long cur = System.currentTimeMillis();
		System.out.println(DateFormatUtils.format(cur, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateFormatUtils.format(cur, "yyyy-MM-dd HH:mm"));
		System.out.println(cur+"\n"+(cur/1000));*/
		String redirectUrl = URLEncoder.encode("http://test188.1mobility.cn/weixin");
		System.out.println(redirectUrl);
		String path = "https://open.weixin.qq.com/connect/qrconnect?appid=wx70682d3e89cc9fdd&redirect_uri="+redirectUrl+"&response_type=code&scope=snsapi_login&state=715852983395f88ce38c1030f088f3f7#wechat_redirect";
		System.out.println(path);
		String method = "GET";
		String jsonParam = "";
		String result = ImageQrcodeUtil.execute(path, method, jsonParam);
		System.out.println(result);
	}
	
	//https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	
	

}
