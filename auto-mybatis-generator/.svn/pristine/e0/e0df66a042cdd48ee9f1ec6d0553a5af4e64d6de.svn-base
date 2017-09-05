package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class ConfigFile
{

    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String fullPackage = info.getPackage_entity() + "." + MyUtils.formatToClassName(tableName);
            TableProperties first = list.get(0);
            String className = MyUtils.formatToClassName(tableName);
            String idName = list.get(0).getName();
            String idJdbcType = MyUtils.formatJdbcType(list.get(0).getDateType());
            String idFullType = MyUtils.formatFullType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_config() + className + "-Mapper.xml", "UTF-8");
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
            out.println("<mapper namespace=\"" + info.getPackage_mapper() + "." + className + "Mapper\" >");
            out.println("  <resultMap id=\"BaseResultMap\" type=\"" + fullPackage + "\" >");
            boolean is_first = true;
            for (TableProperties tableProperties : list)
            {
                if (is_first)
                {
                    out.print("    <id");
                }
                else
                {
                    out.print("    <result");
                }
                out.println(" column=\"" + tableProperties.getName() + "\" property=\"" + MyUtils.formatToVarName(tableProperties.getName()) + "\" jdbcType=\"" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "\" />");
                is_first = false;
            }
            out.println("  </resultMap>");
            out.println();

            out.println("  <!-- 计数（对象） -->");
            out.println("  <select id=\"count\" resultType=\"java.lang.Integer\" parameterType=\"" + fullPackage + "\">");
            out.println("    select count(1) from " + tableName + "");
            out.println("    <trim prefix=\"where\" prefixOverrides=\"and | or\">");
            for (TableProperties tableProperties : list)
            {
                out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null\">");
                out.println("        and " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                out.println("      </if>");
                //新增按日期统计数量
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type))
                {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_F != null\">");
                    out.println("        and " + tableProperties.getName() + " > #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_F,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_T != null\">");
                    out.println("        and " + tableProperties.getName() + " &lt; #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_T,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    //新增大于等于和小于等于
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_FE != null\">");
                    out.println("        and " + tableProperties.getName() + " >= #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_FE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_TE != null\">");
                    out.println("        and " + tableProperties.getName() + " <![CDATA[<=]]> #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_TE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                }
            }
            out.println("    </trim>");
            out.println("  </select>");

            out.println("  <!-- 新增 -->");
            out.println("  <insert id=\"add\" parameterType=\"" + fullPackage + "\" >");
            boolean is_auto_increment = false;//是否自动增长
            if (first.getColumn_type().equals("varchar(32)"))
            {
                out.println("    <selectKey keyProperty=\"" + MyUtils.formatToVarName(idName) + "\" resultType=\"java.lang.String\" order=\"BEFORE\">select replace(uuid(), '-', '')</selectKey>");
            }
            else if (first.getExtra().equals("auto_increment"))
            {
                is_auto_increment = true;
                out.println("    <selectKey keyProperty=\"" + MyUtils.formatToVarName(idName) + "\" resultType=\"java.lang.Integer\" order=\"AFTER\">SELECT LAST_INSERT_ID() as " + idName + "</selectKey>");
            }
            out.println("    insert into " + tableName + " (");
            out.print("      ");
            is_first = true;
            int i = 0;
            boolean is_hide_first = false;//是否隐藏过首字段
            for (TableProperties tableProperties : list)
            {
                if (!is_hide_first && is_auto_increment)
                {
                    is_hide_first = true;
                    continue;
                }
                if (!is_first)
                {
                    out.print(", ");
                    i++;
                    if (i % 3 == 0)
                    {
                        out.println();
                        out.print("      ");
                    }
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println(")");
            out.println("    values (");
            out.print("      ");
            is_first = true;
            is_hide_first = false;
            for (TableProperties tableProperties : list)
            {
                if (!is_hide_first && is_auto_increment)
                {
                    is_hide_first = true;
                    continue;
                }
                if (!is_first)
                {
                    out.println(", ");
                    out.print("      ");
                }
                out.print("#{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                is_first = false;
            }
            out.println(")");
            out.println("  </insert>");
            out.println();

            out.println("  <!-- 删除（ID） -->");
            out.println("  <delete id=\"deleteById\" parameterType=\"" + idFullType + "\" >");
            out.println("    delete from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            out.println("  </delete>");
            out.println();

            out.println("  <!-- 删除（对象） -->");
            out.println("  <delete id=\"deleteByExample\" parameterType=\"" + fullPackage + "\">");
            out.println("    delete from " + tableName + "");
            out.println("    <trim prefix=\"where\" prefixOverrides=\"and | or\">");
            for (TableProperties tableProperties : list)
            {
                out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null\">");
                out.println("        and " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                out.println("      </if>");
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type))
                {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_F != null\">");
                    out.println("        and " + tableProperties.getName() + " > #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_F,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_T != null\">");
                    out.println("        and " + tableProperties.getName() + " &lt; #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_T,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    //新增大于等于和小于等于
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_FE != null\">");
                    out.println("        and " + tableProperties.getName() + " >= #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_FE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_TE != null\">");
                    out.println("        and " + tableProperties.getName() + " <![CDATA[<=]]> #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_TE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                }
            }
            out.println("    </trim>");
            out.println("  </delete>");
            out.println();

            out.println("  <!-- 全部更新（timestamp字段除外） -->");
            out.println("  <update id=\"updateAll\" parameterType=\"" + fullPackage + "\" >");
            out.println("    update " + tableName + " set");
            is_first = true;
            for (TableProperties tableProperties : list)
            {
                if ("timestamp".equals(tableProperties))
                {
                    continue;
                }
                if (!is_first)
                {
                    out.println(",");
                }
                out.print("      " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                is_first = false;
            }
            out.println();
            out.println("    where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            out.println("  </update>");
            out.println();

            out.println("  <!-- 选择更新（timestamp字段除外） -->");
            out.println("  <update id=\"updateSelected\" parameterType=\"" + fullPackage + "\" >");
            out.println("    update " + tableName);
            out.println("    <set>");
            is_first = true;
            for (TableProperties tableProperties : list)
            {
                if ("timestamp".equals(tableProperties))
                {
                    continue;
                }
                if (!is_first)
                {
                    out.println(",");
                    out.println("      </if>");
                }
                out.println("      <if test=\"" + MyUtils.formatToVarName(tableProperties.getName()) + " !=null\">");
                out.print("        " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                is_first = false;
            }
            out.println();
            out.println("      </if>");
            out.println("    </set>");
            out.println("    where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            out.println("  </update>");
            out.println();

            out.println("  <!-- 查询（ID） -->");
            out.println("  <select id=\"findById\" resultMap=\"BaseResultMap\" parameterType=\"" + idFullType + "\" >");
            out.println("    select ");
            out.print("      ");
            is_first = true;
            i = 0;
            for (TableProperties tableProperties : list)
            {
                if (!is_first)
                {
                    out.print(", ");
                    i++;
                    if (i % 3 == 0)
                    {
                        out.println();
                        out.print("      ");
                    }
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println();
            out.println("    from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            out.println("  </select>");
            out.println();

            out.println("  <!-- 查询（对象） -->");
            out.println("  <select id=\"findByExample\" resultMap=\"BaseResultMap\" parameterType=\"" + fullPackage + "\">");
            out.println("    select ");
            out.print("      ");
            is_first = true;
            i = 0;
            for (TableProperties tableProperties : list)
            {
                if (!is_first)
                {
                    out.print(", ");
                    i++;
                    if (i % 3 == 0)
                    {
                        out.println();
                        out.print("      ");
                    }
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println();
            out.println("    from " + tableName + "");
            out.println("    <trim prefix=\"where\" prefixOverrides=\"and | or\">");
            for (TableProperties tableProperties : list)
            {
                out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null\">");
                out.println("        and " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                out.println("      </if>");
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type))
                {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_F != null\">");
                    out.println("        and " + tableProperties.getName() + " > #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_F,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_T != null\">");
                    out.println("        and " + tableProperties.getName() + " &lt; #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_T,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    //新增大于等于和小于等于
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_FE != null\">");
                    out.println("        and " + tableProperties.getName() + " >= #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_FE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + "_TE != null\">");
                    out.println("        and " + tableProperties.getName() + " <![CDATA[<=]]> #{" + MyUtils.formatToVarName(tableProperties.getName()) + "_TE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                }
            }
            out.println("    </trim>");
            out.println("    order by " + idName + " desc");
            out.println("  </select>");
            out.println();

            out.println("  <!-- 分页查询（附带排序） -->");
            out.println("  <select id=\"pageByExample\" resultMap=\"BaseResultMap\">");
            out.println("    select ");
            out.print("      ");
            is_first = true;
            i = 0;
            for (TableProperties tableProperties : list)
            {
                if (!is_first)
                {
                    out.print(", ");
                    i++;
                    if (i % 3 == 0)
                    {
                        out.println();
                        out.print("      ");
                    }
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println();
            out.println("    from " + tableName + "");
            out.println("    <trim prefix=\"where\" prefixOverrides=\"and | or\">");
            for (TableProperties tableProperties : list)
            {
                out.println("      <if test= \"" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + " != null\">");
                out.println("        and " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                out.println("      </if>");
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type))
                {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_F != null\">");
                    out.println("        and " + tableProperties.getName() + " > #{" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_F,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_T != null\">");
                    out.println("        and " + tableProperties.getName() + " &lt; #{" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_T,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    //新增大于等于和小于等于
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_FE != null\">");
                    out.println("        and " + tableProperties.getName() + " >= #{" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_FE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_TE != null\">");
                    out.println("        and " + tableProperties.getName() + " <![CDATA[<=]]> #{" + MyUtils.formatToVarName(tableName) + "." + MyUtils.formatToVarName(tableProperties.getName()) + "_TE,jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                    out.println("      </if>");
                }
            }
            out.println("    </trim>");
            //排序
            out.println("    <if test= \"orderBy != null\">");
            out.println("       order by ${orderBy}");
            out.println("    </if>");
            //分页
            out.println("    <if test=\"size !=null\" >");
            out.println("      limit ");
            out.println("       <if test=\"start !=null\" >");
            out.println("      ${start},");
            out.println("    </if>");
            out.println("       ${size}");
            out.println("    </if>");
            out.println("  </select>");
            out.println();
            out.println("</mapper>");
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
}
