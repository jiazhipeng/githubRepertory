package cn.cuco.common.utils.sms;

import cn.cuco.common.utils.regex.RelyLog;
import org.apache.commons.lang3.RandomStringUtils;
import java.net.URLEncoder;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月24 11:25:00
 */
public class SMSUtils {

    public static boolean sendMessage(String message,String mobile){
        boolean result = false;
        String username = "hycx";
        String pwd = RelyMD5.MD5Encode("hycx123");
        String epid = "120589";
        String linkid = RandomStringUtils.randomNumeric(12);
        try {
            String url = "http://114.255.71.158:8061/?username="+username+"&password="+pwd+"&message="+ URLEncoder.encode(message, "gb2312")+"&phone="+mobile+"&epid="+epid+"&linkid="+linkid+"&subcode=";
            String responseBody = RelyHttpClient.sendGet(url, null, "UTF-8");
            if("00".equals(responseBody)){
                result = true;
                RelyLog.getLogger().info("验证码发至{}送成功",mobile);
            }else{
                RelyLog.getLogger().error("验证码发至{}失败{}",mobile,responseBody);
            }
        } catch (Exception e) {
            RelyLog.getLogger().error("验证码发至{}异常{}",mobile,e.getMessage());
        }
        return result;
    }
}
