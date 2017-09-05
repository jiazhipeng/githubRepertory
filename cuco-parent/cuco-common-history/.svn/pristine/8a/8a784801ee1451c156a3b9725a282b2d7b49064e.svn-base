package com.hy.common.utils;

import java.security.MessageDigest;

public class MD5Util {
    private final static String[] hexDigits = {
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {

        }
        return resultString;
    }
    
    public final static void  main(String as []){
    	System.out.println();
    	//com.woopen.smspay.LinkSMSMainActivity
    	//com.unicom.woopenoneway.UnicomWoOpenPaymentMainActivity
//    	System.out.println(MD5Util.MD5Encode("com.mas.wawagame.BDDKlordWOJ"+"com.mas.wawapak.activity.MainMenuActivity"+"com.unicom.woopenoneway.UnicomWoOpenPaymentMainActivity"));;
//    	System.out.println(MD5Util.MD5Encode("Linke123"));;
    	System.out.println(MD5Util.MD5Encode("123456"));;
    }

}
