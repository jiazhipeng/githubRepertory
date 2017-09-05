package com.hy.common.utils;

import com.hy.constant.Constant;
import com.hy.exception.ExceptionUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WangShuai on 2016/8/15.
 */
public class RegexUtils {

    public static boolean isMatch(String regex,String param){
        if(StringUtils.isEmpty(regex)){
            ExceptionUtil.throwWarn("正则表达式不能为空");
        }
        if(param == null){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(param);
        return matcher.matches();
    }

    public static boolean isMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }
        Pattern pattern = Pattern.compile("^((13\\d{9}$)|(14[5,7]\\d{8}$)|(15[0,1,2,3,5,6,7,8,9]\\d{8}$)|(17[0,3,6,7,8]\\d{8}$)|(18[0,1,2,3,4,5,6,7,8,9]\\d{8}$))");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static boolean isOrganizationCode(String param){
        if(StringUtils.isEmpty(param)){
            return false;
        }
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{1,15}$");
        Matcher matcher = pattern.matcher(param);
        return matcher.matches();
    }

    public static boolean isUserName(String userName) {
        return isAvailableString(userName,500);
    }

    /**
     * 判断字符串是否不超过500字节
     * @param param
     * @return
     */
    public static boolean isAvailableString(String param) {
        return isAvailableString(param,500);
    }

    /**
     * 判断字符串是否在某字节数以内
     * @param param
     * @param maxLength
     * @return
     */
    public static boolean isAvailableString(String param,int maxLength) {
        if(StringUtils.isEmpty(param)){
            return false;
        }
        if(maxLength<0){
            ExceptionUtil.throwWarn("最大字节数不可小于0");
        }
        int byteLength = 0;
        try {
            byteLength = param.getBytes(Constant.CHARSET_UTF8).length;
        } catch (UnsupportedEncodingException e) {
            LogUtil.getLogger().error("获取字符串字节数失败："+param,e);
            return false;
        }
        if(byteLength > maxLength){
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否：非空并且在有效字符数内
     * @param param
     * @param maxCharacterMaxSize
     * @return
     */
    public static boolean isAvailableSizeCharacter(String param,int maxCharacterMaxSize){
        if(maxCharacterMaxSize<0){
            ExceptionUtil.throwWarn("最大字符数不可小于0");
        }
        if(StringUtils.isEmpty(param)){
            return false;
        }
        if(param.length()>maxCharacterMaxSize){
            return false;
        }
        return true;
    }

    /**
     * 判断是否为邮箱格式
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        return isMatch(regex,email);
    }


    @Test
    public void test(){
      //  System.out.println(isMatch("s23","312313"));
        /*
        String mobile = "13600000000";
        System.out.println(isMobile(mobile));

        String code = "13600000000AAAA";
        System.out.println(isOrganizationCode(code));
        */

        System.out.println(isEmail("shuai.wang@aa.com"));
    }
}
