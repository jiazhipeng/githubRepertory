package com.toolkit.auto.mybatis.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public final class DbAccess
{

    private java.sql.Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement prepstmt = null;
    private CallableStatement callstmt = null;

    /**
     * 普通查询（不分页）
     * @param sql	查询语句
     * @param para	查询条件
     * @return		查询结果
     */
    public String[][] executeQuery(String sql, List<String> para)
    {
        try
        {
            this.setParameter(sql, para);
            this.rs = this.prepstmt.executeQuery();
            return this.getResult();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            this.close();
        }
    }

    /**
     * 设置参数
     * @param sql	sql语句
     * @param para	参数集合，每一个元素都为String类型
     * @throws Exception
     */
    private void setParameter(String sql, List<String> para) throws Exception
    {
        if (para == null)
        {
            para = new ArrayList<String>();
        }
        this.conn = DbBean.getConnectionFromJDBC();
        this.prepstmt = this.conn.prepareStatement(sql);
        for (int i = 0; i < para.size(); i++)
        {
            this.prepstmt.setString(i + 1, para.get(i));
        }
    }

    /**
     * 从结果集获取数据
     * @return	查询结果
     * @throws Exception
     */
    private String[][] getResult() throws Exception
    {
        String[][] result = null;
        ResultSetMetaData md = this.rs.getMetaData();
        int length = md.getColumnCount();
        List<String[]> tmpList = new ArrayList<String[]>();
        String[] tmpStr = null;
        while (this.rs.next())
        {
            tmpStr = new String[length];
            for (int i = 0; i < length; i++)
            {
                tmpStr[i] = this.rs.getString(i + 1);
            }
            tmpList.add(tmpStr);
        }
        this.rs.close();
        this.rs = null;
        //把List中的数据转换成String[][]
        if (tmpList != null)
        {
            result = new String[tmpList.size()][length];
            for (int i = 0; i < tmpList.size(); i++)
            {
                result[i] = tmpList.get(i);
            }
        }
        return result;
    }

    /**
     * 释放所有资源
     */
    private void close()
    {
        try
        {
            if (this.rs != null)
            {
                this.rs.close();
                this.rs = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if (this.prepstmt != null)
            {
                this.prepstmt.close();
                this.prepstmt = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if (this.callstmt != null)
            {
                this.callstmt.close();
                this.callstmt = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if (this.conn != null)
            {
                if (!this.conn.getAutoCommit())
                {
                    try
                    {
                        this.conn.setAutoCommit(true);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        this.conn.rollback();
                        this.conn.setAutoCommit(true);
                    }
                }
                this.conn.close();
                this.conn = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}