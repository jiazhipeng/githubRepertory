package com.hy.accessToken.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.hy.common.utils.DesUtil;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.exception.RuntimeExceptionWarn;

/**权限系统accessToken工厂
 * @author 1mobility
 *
 */
public class SystemTokenFactory {
	
	private static Logger logger = Logger.getLogger(SystemTokenFactory.class);

	/**根据appid以及秘钥生成一个token
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static String getToken(String appid,String appsecret){
		logger.info("注册生成token开始---");
		//首先根据appid获取配置文件中的appsecret明文
		String secret = Constant.load.getProperty(appid);
		String decrypt = "";
		//根据传输过来的数据解密
		try {
			//解密后的appsecret
			decrypt = DesUtil.decrypt(appsecret, appid);
		} catch (Exception e) {
			logger.error("根据appid解密异常---",e);
			e.printStackTrace();
		}
		if(!decrypt.equals(secret)){
			//秘钥匹配失败
			logger.error("根据appid匹配秘钥失败---");
			//没匹配上的code
			return "false";
		}
		//首先根绝appid从redis里面取子系统的token
		Object systemTokenObj = JedisUtils.getObject(appid);
		if(systemTokenObj != null){
			logger.info("从 redis中获取 systemToken---"+systemTokenObj.toString());
			return systemTokenObj.toString();
		}
		//没有生成token，生成一个新的放到redis里面
		logger.info("根据appid生成一个systemToken放到redis中---");
		try {
			String token = appid+RandomStringUtils.randomAlphanumeric(4);
			JedisUtils.setObject(appid, token, 0);
			systemTokenObj = JedisUtils.getObject(appid);
			logger.info("为空重新获取 systemToken保存到redis中值为 ---" + systemTokenObj.toString());
		} catch (Exception e) {
			logger.error("获取systemToken 异常---",e);
			return null;
		}
		return systemTokenObj.toString();
		
	}
	
}
