package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class QueryCofigFile
{
    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String fullPackage = info.getPackage_entity() + "." + MyUtils.formatToClassName(tableName);
            String fullPackageExample = fullPackage + "Example";
            String fullPackageKey = fullPackage + "Key";
            String className = MyUtils.formatToClassName(tableName);
            String idName = list.get(0).getName();
            String idJdbcType = MyUtils.formatJdbcType(list.get(0).getDateType());
            //            String idFullType = MyUtils.formatFullType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_query_config() + className + "-Mapper.xml", "UTF-8");
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
            out.println("<mapper namespace=\"" + info.getPackage_mapper() + "." + className + "Mapper\" >");
            out.println("  <resultMap id=\"BaseResultMap\" type=\"" + fullPackage + "\" >");
            boolean is_first = true;
            boolean is_del_flag = false;
            for (TableProperties tableProperties : list)
            {
                if ("is_del".equals(tableProperties.getName()))
                {
                    is_del_flag = true;//有is_del字段
                }
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

            out.println("<!-- 查询where部分sql限制条件集合 -->");
            out.println("  <sql id=\"Example_Where_Clause\">");
            out.println("    <where>");
            out.println("      <foreach collection=\"oredCriteria\" item=\"criteria\" separator=\"or\">");
            out.println("        <if test=\"criteria.valid\">");
            out.println("          <trim  prefixOverrides=\"and\" prefix=\"(\" suffix=\")\">");
            out.println("            <foreach collection=\"criteria.criteria\" item=\"criterion\">");
            out.println("              <choose>");
            out.println("                <when test=\"criterion.noValue\">");
            out.println("                  and ${criterion.condition}");
            out.println("                </when>");
            out.println("                <when test=\"criterion.singleValue\">");
            out.println("                  and ${criterion.condition} #{criterion.value}");
            out.println("                </when>");
            out.println("                <when test=\"criterion.betweenValue\">");
            out.println("                  and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}");
            out.println("                </when>");
            out.println("                <when test=\"criterion.listValue\">");
            out.println("                  and ${criterion.condition}");
            out.println("                  <foreach collection=\"criterion.value\" item=\"listItem\" open=\"(\"  close=\")\" separator=\",\">");
            out.println("                    #{listItem}");
            out.println("                  </foreach>");
            out.println("                </when>");
            out.println("              </choose>");
            out.println("            </foreach>");
            out.println("          </trim>");
            out.println("        </if>");
            out.println("      </foreach>");
            out.println("    </where>");
            out.println("  </sql>");
            out.println();

            //            out.println("<!-- 修改where部分sql限制条件集合 -->");
            //            out.println("  <sql id=\"Update_By_Example_Where_Clause\">");
            //            out.println("    <where>");
            //            out.println("      <foreach collection=\"example.oredCriteria\" item=\"criteria\" separator=\"or\">");
            //            out.println("        <if test=\"criteria.valid\">");
            //            out.println("          <trim prefix=\"(\" prefixOverrides=\"and\" suffix=\")\">");
            //            out.println("            <foreach collection=\"criteria.criteria\" item=\"criterion\">");
            //            out.println("              <choose>");
            //            out.println("                <when test=\"criterion.noValue\">");
            //            out.println("                  and ${criterion.condition}");
            //            out.println("                </when>");
            //            out.println("                <when test=\"criterion.singleValue\">");
            //            out.println("                  and ${criterion.condition} #{criterion.value}");
            //            out.println("                </when>");
            //            out.println("                <when test=\"criterion.betweenValue\">");
            //            out.println("                  and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}");
            //            out.println("                </when>");
            //            out.println("                <when test=\"criterion.listValue\">");
            //            out.println("                  and ${criterion.condition}");
            //            out.println("                  <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">");
            //            out.println("                    #{listItem}");
            //            out.println("                  </foreach>");
            //            out.println("                </when>");
            //            out.println("              </choose>");
            //            out.println("            </foreach>");
            //            out.println("          </trim>");
            //            out.println("        </if>");
            //            out.println("      </foreach>");
            //            out.println("    </where>");
            //            out.println("  </sql>");
            //            out.println();

            out.println("<!-- select部分查询字段集合 -->");
            out.println("  <sql id=\"Base_Column_List\">");
            is_first = true;
            int i = 0;
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
                else
                {
                    out.print("      ");
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println();
            out.println("  </sql>");
            out.println();

            out.println("  <!-- 按对象可分页可去重查询 -->");
            out.println("  <select id=\"selectByExample\" parameterType=\"" + fullPackageExample + "\" resultMap=\"BaseResultMap\">");
            out.println("    select");
            out.println("      <if test=\"distinct\">");
            out.println("         distinct");
            out.println("      </if>");
            out.println("      <if test=\"columnName != null\">");
            out.println("            ${columnName.queryColumnNameStr}");
            out.println("      </if>");
            out.println("      <if test=\"columnName == null\">");
            out.println("            <include refid=\"Base_Column_List\" />");
            out.println("      </if>");
            out.println("    from " + tableName);
            out.println("    <if test=\"_parameter != null\">");
            out.println("       <include refid=\"Example_Where_Clause\" />");
            out.println("    </if>");
            out.println("    <if test=\"orderByClause != null\">");
            out.println("       order by ${orderByClause}");
            out.println("    </if>");
            out.println("    <if test=\"limitStart &gt;= 0 and limitEnd &gt;= 0\">");
            out.println("       limit ${limitStart} , ${limitEnd}");
            out.println("    </if>");
            out.println("  </select>");
            out.println();

            out.println("  <!-- 按对象查询数据条数 -->");
            out.println("  <select id=\"countByExample\" parameterType=\"" + fullPackageExample + "\" resultType=\"java.lang.Integer\">");
            out.println("    select count(*) from " + tableName);
            out.println("    <if test=\"_parameter != null\">");
            out.println("       <include refid=\"Example_Where_Clause\" />");
            out.println("    </if>");
            out.println("  </select>");
            out.println();

            out.println("  <!-- 按主键查询表信息 -->");
            out.println("  <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"" + fullPackageKey + "\" >");
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
            if (!is_del_flag)
            {
                out.println("    from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            }
            else
            {
                out.println("    from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
                out.println("    <if test= \"isDel != null\">");
                out.println("         and is_del=#{isDel}");
                out.println("    </if>");
            }
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
