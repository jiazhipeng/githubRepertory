package com.toolkit.auto.mybatis.utils;

import java.util.ResourceBundle;

public class MyUtils
{
    private static ResourceBundle rb = null;
    private static ResourceBundle jdbc_rb = null;
    private static ResourceBundle full_rb = null;

    static
    {
        rb = ResourceBundle.getBundle("type");
        jdbc_rb = ResourceBundle.getBundle("jdbcType");
        full_rb = ResourceBundle.getBundle("fullType");
    }

    public static String formatToClassName(String name)
    {
        if (name == null){
            return name;
        }else if (name.indexOf("_") == -1){
            return upperFirstLetter(name);
        }else{
            String[] temp = name.split("_");
            String result = "";
            for (String string : temp)
            {
            	if(string.equalsIgnoreCase("td") || string.equalsIgnoreCase("tc") || string.equalsIgnoreCase("tr")){
            		continue;
            	}
                result += upperFirstLetter(string);
            }
            return result;
        }
    }

    public static String formatToVarName(String name)
    {
        if (name == null)
        {
            return name;
        }
        else if (name.indexOf("_") == -1)
        {
            return lowerFirstLetter(name);
        }
        else
        {
            String[] temp = name.split("_");
            String result = "";
            for (String string : temp)
            {
                result += upperFirstLetter(string);
            }
            return lowerFirstLetter(result);
        }
    }

    public static String formatDataType(String name)
    {
        if (name == null || "".equals(name))
        {
            return name;
        }
        else
        {
            try
            {
                return rb.getString(name);
            }
            catch (Exception e)
            {
                return name;
            }
        }
    }

    public static String formatJdbcType(String name)
    {
        if (name == null || "".equals(name))
        {
            return name;
        }
        else
        {
            try
            {
                return jdbc_rb.getString(name);
            }
            catch (Exception e)
            {
                return name;
            }
        }
    }

    public static String formatFullType(String name)
    {
        if (name == null || "".equals(name))
        {
            return name;
        }
        else
        {
            try
            {
                return full_rb.getString(name);
            }
            catch (Exception e)
            {
                return name;
            }
        }
    }

    /**
     * 首字母大写化
     * @param src   字符串
     * @return      首字母大写后的字符串
     */
    public static String upperFirstLetter(String src)
    {
        if (src == null || "".equals(src))
        {
            return src;
        }
        return src.substring(0, 1).toUpperCase() + src.substring(1);
    }

    /**
     * 首字母小写
     * @param src   字符串
     * @return      首字母小写后的字符串
     */
    public static String lowerFirstLetter(String src)
    {
        if (src == null || "".equals(src))
        {
            return src;
        }
        return src.substring(0, 1).toLowerCase() + src.substring(1);
    }

    /**
     * 转换Beans 的class 名称
     * @param name
     * @return
     */
    public static String formatToBeansClassName(String name)
    {
        if (name == null)
        {
            return name;
        }
        else if (name.indexOf("_") == -1)
        {
            return upperFirstLetter(name);
        }
        else
        {
            String[] temp = name.split("_");
            String result = "";
            for (int i = 1; i < temp.length; i++)
            {
                result += upperFirstLetter(temp[i]);
            }
            return result;
        }
    }

    /**
     * 转换Beans 的id 名称
     * @param name
     * @return
     */
    public static String formatToBeansIdName(String name)
    {
        if (name == null)
        {
            return name;
        }
        else if (name.indexOf("_") == -1)
        {
            return lowerFirstLetter(name);
        }
        else
        {
            String[] temp = name.split("_");
            String result = "";
            for (int i = 1; i < temp.length; i++)
            {
                result += upperFirstLetter(temp[i]);
            }
            return lowerFirstLetter(result);
        }
    }
}
