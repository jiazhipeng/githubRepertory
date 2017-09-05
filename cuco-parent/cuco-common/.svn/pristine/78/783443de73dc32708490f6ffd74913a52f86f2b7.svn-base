package cn.cuco.wechat.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

/**
 *
* @ClassName: WechatMenuUtils 
* @Description: wechat Menu工具类
* @author jiaxiaoxian
* @date 2017年3月7日 上午11:42:20
 */
public class WechatMenuUtils {
	
	/**
	 * logger工具
	 */
	private static Logger logger = Logger.getLogger(AccessTokenUtil.class);

	/**
	 * 创建微信menu的url
	 */
	private final static String CREATEMENUURL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	
/*	 {
	     "button":[
	     {	
	          "type":"click",
	          "name":"今日歌曲",
	          "key":"V1001_TODAY_MUSIC"
	      },
	      {
	           "name":"菜单",
	           "sub_button":[
	           {	
	               "type":"view",
	               "name":"搜索",
	               "url":"http://www.soso.com/"
	            },
	            {
	               "type":"view",
	               "name":"视频",
	               "url":"http://v.qq.com/"
	            },
	            {
	               "type":"click",
	               "name":"赞一下我们",
	               "key":"V1001_GOOD"
	            }]
	       }]
	 }*/
	/**
	 * 
	* @Title: createMenu 
	* @Description: 创建微信menu
	* @author jiaxiaoxian
	* @param param  json串
	* @return String
	 */
	public static String createMenu(String param){
		
		String code = "";
		logger.info("----------开始创建微信menu----------");
		String accessToken = AccessTokenUtil.getAccessToken();
		String url = CREATEMENUURL.replace("ACCESS_TOKEN", accessToken);
		logger.info("微信menu的url是" + url);
		try {
			String result = WechatUrl.getRemoteJson(url, RequestMethod.POST.toString(), param);
			JSONObject obj = JSONObject.parseObject(result);//{"errcode":0,"errmsg":"ok"}>
			logger.info("创建微信menu的返回值" + result);
			code = obj.getString("errcode");
			return code;
		} catch (IOException e) {
			logger.error("创建微信menu发送post请求报错"+e.getMessage());
			e.printStackTrace();
		}
		return code;
	}
}
