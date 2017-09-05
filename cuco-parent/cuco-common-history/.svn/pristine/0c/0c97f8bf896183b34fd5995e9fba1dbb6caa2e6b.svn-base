package com.hy.weixin.utils;

import java.util.Map;

import org.apache.log4j.Logger;

public class SignUtil {
	protected static Logger logger = Logger.getLogger(SignUtil.class);  

	/**
     * 获取支付所需签名
     * @param ticket
     * @param timeStamp
     * @param card_id
     * @param code
     * @return
     * @throws Exception
     */
    public static String getPayCustomSign(Map<String, String> bizObj,String key) throws Exception {
        String bizString = CommonUtil.FormatBizQueryParaMap(bizObj,false);
        logger.info(bizString);
        return MD5SignUtil.sign(bizString, key);
    }

}
