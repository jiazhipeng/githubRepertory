package cn.cuco.wechat.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import cn.cuco.wechat.entity.AccessToken;

/**
 * 
* @ClassName: OpenIdUtils 
* @Description: wechatOpenId  util
* @author jiaxiaoxian
* @date 2017年3月6日 上午10:12:39
 */
public class OpenIdUtils {
	
	/**
	 * logger工具
	 */
	private static Logger logger = Logger.getLogger(AccessTokenUtil.class);
	
	//redis openId的 key标志
	private final static String OPENIDKEY = "OPENID";
	
	/**
	 * 
	* @Title: getOpenId 
	* @Description: 通过memberId 或者 code 获取 openId
	* @author jiaxiaoxian
	* @param code  wechat回调的code,没有可传""
	* @param memberId  必传项,查询openId后,保存到redis中,memberId为key
	* @return String
	 */
	public static String getOpenId(String code,Long memberId){
		
		if(memberId != null){
			Object openId = RelyJedis.getObject(memberId.toString() + OPENIDKEY);
			if(openId != null){
				logger.info("从 redis 获取 openId>>>>>>>>>>>>>"+openId.toString());
				return openId.toString();
			}
			
			try {
				if(StringUtils.isNotBlank(code)){
					AccessToken accessToken = AccessTokenUtil.getAccToken(code);
					openId = accessToken.getOpenid();
					RelyJedis.set(memberId.toString() + OPENIDKEY, openId.toString(), 0);
					logger.info("openId 为空 重新获取 openId保存到redis中值为 >>>>>>>>>>>>" + openId);
					return openId.toString();
				}
			} catch (Exception e) {
				logger.error("获取accesstoken 异常.......",e);
				return null;
			}
		}
		return null;
	} 
	
	/**
	 * 
	* @Title: setOpenId 
	* @Description: 设置openId到redis中
	* @author jiaxiaoxian
	* @param openId
	* @param memberId
	* @return void
	 */
	public static void setOpenId(String openId,Long memberId){
		RelyJedis.set(memberId.toString() + OPENIDKEY, openId.toString(), 0);
	}
}
