package cn.cuco.wechat.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.cuco.httpservice.HttpClientUtils;

/**
 * 
* description：weChat临时二维码 和 永久二维码生成Util    
* author：1mobility    
* time：2017年2月20日 下午12:00:38    
* 修改时间：2017年2月20日 下午12:00:38    
* 修改备注：
 */
public class QRCodeUtils {
	
	/**
	 * wechat临时二维码url
	 */
	public final static String TEMPORARY_QR_CODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={TOKEN}";
	
	/**
	 * wechat永久二维码url
	 */
	public final static String PERMANENT_QR_CODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={TOKEN}";

	/**
	 * 通过ticket获取二维码图片的url
	 */
	public final static String QR_CODE_TICKET_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={TICKET}";
	/**
	 * 临时二维码
	 */
	public final static String QR_SCENE = "QR_SCENE";
	
	/**
	 * 永久二维码
	 */
	public final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	
	/**
	 * 
	* @Description:创建临时二维码      
	* @author:1mobility  
	* @param expireSeconds二维码有效时间,以秒为单位,最大不可超过604800（即7天）
	* @param sceneId  场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	* @time:2017年2月20日 下午1:53:27   
	* @return String 返回正确为url，错误为code码加errmsg
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static String createTemporaryQRCode(int expireSeconds,int sceneId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		
		JSONObject json = sendTemporaryQRCode(expireSeconds, sceneId);
		Object QRCodeUrl = json.get("url");
		if(QRCodeUrl != null){
			return (String) QRCodeUrl;
		}else{
			Object errcode = json.get("errcode");
			Object errmsg = json.get("errmsg");
			return errcode.toString() + errmsg.toString();
		}
	}
	
	/**
	 * 
	* @Description:构造请求临时二维码的参数,获取wechat给的返回值    
	* @author:1mobility  
	* @time:2017年2月20日 下午3:01:04   
	* @return String ticket	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	* @return String expire_seconds 	该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。
	* @return String url 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 */
	private  static JSONObject sendTemporaryQRCode(int expireSeconds,int sceneId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		String token = AccessTokenUtil.getAccessToken();
		String url = TEMPORARY_QR_CODE_URL.replace("{TOKEN}", token);
		Map<String,Object> param = new HashMap<>();
		param.put("expire_seconds", expireSeconds);
		param.put("action_name", QR_SCENE);
		Map<String,Object> sceneIdParam = new HashMap<>();
		sceneIdParam.put("scene_id", sceneId);
		Map<String,Object> sceneParam = new HashMap<>();
		sceneParam.put("scene", sceneIdParam);
		param.put("action_info", sceneParam);
		JSONObject json = HttpClientUtils.sendSSL(url, "POST", param.toString());
		return json;
	}
	
	/**
	 * 
	* @Description:创建永久二维码     
	* @author:1mobility  
	* @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	* @time:2017年2月20日 下午2:46:28   
	* @return String 返回正确为url，错误为code码加errmsg
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static String createPermanentQRCode(int sceneId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		JSONObject json = sendPermanentQRCode(sceneId);
		Object QRCodeUrl = json.get("url");
		if(QRCodeUrl != null){
			return (String) QRCodeUrl;
		}else{
			Object errcode = json.get("errcode");
			Object errmsg = json.get("errmsg");
			return errcode.toString() + errmsg.toString();
		}
	}
	
	/**
	 * 
	* @Description:构造永久二维码参数,获取wechat给的返回值    
	* @author:1mobility  
	* @time:2017年2月20日 下午3:10:58   
	* @return String ticket	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	* @return String expire_seconds 	该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。
	* @return String url 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 */
	private static JSONObject sendPermanentQRCode(int sceneId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
		String token = AccessTokenUtil.getAccessToken();
		String url = PERMANENT_QR_CODE_URL.replace("{TOKEN}", token);
		Map<String,Object> param = new HashMap<>();
		param.put("action_name", QR_LIMIT_SCENE);
		Map<String,Object> sceneIdParam = new HashMap<>();
		sceneIdParam.put("scene_id", sceneId);
		Map<String,Object> sceneParam = new HashMap<>();
		sceneParam.put("scene", sceneIdParam);
		param.put("action_info", sceneParam);
		JSONObject json = HttpClientUtils.sendSSL(url, "POST", param.toString());
		return json;
	}
	
	/**
	 * 
	* @Description:根据ticket换取二维码图片 
	* @author:1mobility  
	* @time:2017年2月20日 下午3:30:39   
	* @return String
	 */
	public static String getQRCodeByTicket(){
		
		return "";
	}

}
