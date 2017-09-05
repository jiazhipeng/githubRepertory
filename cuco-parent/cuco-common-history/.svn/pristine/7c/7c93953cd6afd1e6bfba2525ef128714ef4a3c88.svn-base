package com.hy.weixin.utils;

import org.apache.log4j.Logger;


public class MD5SignUtil {
	protected static Logger logger = Logger.getLogger(MD5SignUtil.class);  

	public static String sign(String content, String key)
            throws Exception{
        String signStr = "";
        signStr = content + "&key=" + key;
        
        logger.info("signStr-------" + signStr);
        return MD5Util.MD5Encode(signStr, "utf-8");//.MD5(signStr).toUpperCase();
    }
	
	public static void main(String[] args) {
		String a = "appid=wx60a4abe71b2961e3&nonceStr=HkPrzwcmw5iwbaAU&package=prepay_id=wx20160629191247d18a740e820460617554&signType=MD5&timeStamp=1467198768&key=05BFC069CCB9432D9E1CBEB3A52222D3";
		
		System.out.println(MD5Util.MD5(a).toUpperCase());
	}
}
