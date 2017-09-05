package com.toolkit.auto.mybatis.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public final class DbBean
{

    private DbBean()
    {
    }

    private static String mysql_driver = "com.mysql.jdbc.Driver";
    private static String mysql_url;
    private static String mysql_user;
    private static String mysql_passwd;

    public static String getMysql_driver()
    {
        return mysql_driver;
    }

    public static void setMysql_driver(String mysqlDriver)
    {
        mysql_driver = mysqlDriver;
    }

    public static String getMysql_url()
    {
        return mysql_url;
    }

    public static void setMysql_url(String mysqlUrl)
    {
        mysql_url = mysqlUrl;
    }

    public static String getMysql_user()
    {
        return mysql_user;
    }

    public static void setMysql_user(String mysqlUser)
    {
        mysql_user = mysqlUser;
    }

    public static String getMysql_passwd()
    {
        return mysql_passwd;
    }

    public static void setMysql_passwd(String mysqlPasswd)
    {
        mysql_passwd = mysqlPasswd;
    }

    /**
     * JDBC直连
     * @param m_driver		驱动
     * @param m_url			地址
     * @param m_user		用户名
     * @param m_password	密码
     * @return				Connection连接
     * @throws Exception
     */
    public static synchronized Connection getConnectionFromJDBC() throws Exception
    {
        Driver driver = (Driver) Class.forName(mysql_driver).newInstance();
        DriverManager.registerDriver(driver);
        return DriverManager.getConnection(mysql_url, mysql_user, mysql_passwd);
    }
}