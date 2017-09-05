package com.toolkit.auto.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import com.toolkit.auto.mybatis.dao.DbAccess;
import com.toolkit.auto.mybatis.entity.TableProperties;

public class MySql
{
    /**
     * 列出所有表
     * @param schema
     * @return
     */
    public List<String> listAllTables(String schema)
    {
        String sql = "select table_name,table_comment from information_schema.tables where table_schema = '" + schema + "'";
        DbAccess db = new DbAccess();
        String[][] str = db.executeQuery(sql, null);
        List<String> list = new ArrayList<String>();
        for (String[] strings : str)
        {
            list.add(strings[0] + "#####" + strings[1]);
        }
        return list;
    }

    /**
     * 列出表的所有字段的属性（列名、数据类型、注释）
     * @param schema
     * @param tableName
     * @return
     */
    public List<TableProperties> listTableProperties(String schema, String tableName)
    {
        String sql = "select COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_TYPE,EXTRA FROM information_schema.columns where TABLE_NAME='" + tableName + "' and TABLE_SCHEMA='" + schema + "'";
        DbAccess db = new DbAccess();
        String[][] str = db.executeQuery(sql, null);
        List<TableProperties> list = new ArrayList<TableProperties>();
        for (String[] strings : str)
        {
            TableProperties tableProperties = new TableProperties();
            tableProperties.setName(strings[0]);
            tableProperties.setDateType(strings[1]);
            tableProperties.setComment(strings[2]);
            tableProperties.setColumn_type(strings[3]);
            tableProperties.setExtra(strings[4]);
            list.add(tableProperties);
        }
        return list;
    }
}
