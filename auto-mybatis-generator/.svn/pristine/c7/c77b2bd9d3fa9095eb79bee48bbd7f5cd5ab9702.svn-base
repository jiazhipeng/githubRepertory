package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class CoreConfigFile {
    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String returnId = "";
            int j = 0;
            for (TableProperties tableProperties : list) {
                if (j == 0 || "is_del".equals(tableProperties.getName())) {
                    if ("Integer".equals(MyUtils.formatDataType(tableProperties.getDateType())) ||
                    		"Long".equals(MyUtils.formatDataType(tableProperties.getDateType()))) {
                        returnId = " useGeneratedKeys=\"true\" keyProperty=\"" + MyUtils.formatToVarName(tableProperties.getName()) + "\"";
                    }
                    break;
                }
                j++;
            }
            String fullPackage = info.getPackage_entity() + "." + MyUtils.formatToClassName(tableName);
            //String fullPackageExample = "com.offen.model.CriteriaExample";
            String fullPackageKey = fullPackage;// + "Key";
            String className = MyUtils.formatToClassName(tableName);
            String idName = list.get(0).getName();
            String idJdbcType = MyUtils.formatJdbcType(list.get(0).getDateType());
            String idFullType = MyUtils.formatFullType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_core_config() + className + "Mapper.xml", "UTF-8");
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
            out.println("<mapper namespace=\"" + info.getPackage_mapper() + "." + className + "Mapper\" >");
            out.println("  <resultMap id=\"BaseResultMap\" type=\"" + fullPackage + "\" >");
            boolean is_first = true;
            boolean is_del_flag = false;
            for (TableProperties tableProperties : list) {
                if ("is_del".equals(tableProperties.getName())) {
                    is_del_flag = true;//有is_del字段
                }
                if (is_first) {
                    out.print("    <id");
                } else {
                    out.print("    <result");
                }
                out.println(" column=\"" + tableProperties.getName() + "\" property=\"" + MyUtils.formatToVarName(tableProperties.getName()) + "\" jdbcType=\"" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "\" />");
                is_first = false;
            }
            out.println("  </resultMap>");
            out.println();
            //分条件查询sql总数开始
            out.println("<!-- 分条件查询sql 语句 -->");
            out.println("  <select id=\"selectCountByCondition\" resultType=\"INTEGER\" parameterType=\"" + fullPackageKey + "\" >");
            out.print("      ");
            out.println(" select  count(1) ");
            out.println("    from " + tableName +"  ");
            out.println("  <where>");
            is_first = true;
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.println("      ");
                    out.println("      </if>");
                }
                if(MyUtils.formatJdbcType(tableProperties.getDateType()).equalsIgnoreCase("VARCHAR")){
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null and " + MyUtils.formatToVarName(tableProperties.getName())+" != '' \">");
                }else{
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null \">");
                }
                out.print("        and "+ tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");

                is_first = false;
            }
            out.println("      ");
            out.println("      </if>");
            out.println("  </where>");
            out.println("  </select>");
            out.println();  
            //分条件查询sql总数结束
            
            
            //分条件查询sql 语句 开始
            out.println("<!-- 分条件查询sql 语句 -->");
            out.println("  <select id=\"selectByCondition\" resultMap=\"BaseResultMap\" parameterType=\"" + fullPackageKey + "\" >");
            out.print("      ");
            out.println(" select  <include refid=\"Base_Column_List\" />");
            out.println("    from " + tableName +"  ");
            out.println("  <where>");
            is_first = true;
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.println("      ");
                    out.println("      </if>");
                }
                if(MyUtils.formatJdbcType(tableProperties.getDateType()).equalsIgnoreCase("VARCHAR")){
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null and " + MyUtils.formatToVarName(tableProperties.getName())+" != '' \">");
                }else{
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null \">");
                }
                out.print("        and "+ tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");

                is_first = false;
            }
            out.println("      ");
            out.println("      </if>");
            out.println("  </where>");
            out.println("  </select>");
            out.println();
          //分条件查询sql 语句 结束
            
            
            
            
            
            
            
            

            out.println("<!-- select部分查询字段集合 -->");
            out.println("  <sql id=\"Base_Column_List\">");
            out.print("      ");
            is_first = true;
            int i = 0;
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.print(", ");
                    i++;
                    if (i % 3 == 0) {
                        out.println();
                        out.print("      ");
                    }
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println();
            out.println("  </sql>");
            out.println();

            out.println("  <!-- 按主键实体查询表信息 -->");
            out.println("  <select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap\" parameterType=\"" + fullPackageKey + "\" >");
            out.println("    select ");
            out.print("      ");
            is_first = true;
            i = 0;
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.print(", ");
                    i++;
                    if (i % 3 == 0) {
                        out.println();
                        out.print("      ");
                    }
                }
                out.print(tableProperties.getName());
                is_first = false;
            }
            out.println();
            if (!is_del_flag) {
                out.println("    from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            } else {
                out.println("    from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
                //                out.println("      <if test= \"isDel != null\">");
                //                out.println("         and is_del=#{isDel}");
                //                out.println("      </if>");
            }
            out.println("  </select>");
            out.println();

//            out.println("  <!-- 按条件查询表信息 -->");
//            out.println("  <select id=\"selectByExample\" resultMap=\"BaseResultMap\" parameterType=\"" + fullPackage + "\" >");
//            out.println("    select");
//            out.println("      <if test=\"columnName != null and columnName.queryColumnNameStr != null\">");
//            //            out.println("           <if test=\"columnName.queryColumnNameStr != null\">");
//            out.println("           ${columnName.queryColumnNameStr}");
//            //            out.println("           </if>");
//            //            out.println("           <if test=\"columnName.queryColumnNameStr == null\">");
//            //            out.println("               <include refid=\"Base_Column_List\" />");
//            //            out.println("           </if>");
//            out.println("      </if>");
//            out.println("      <if test=\"columnName == null\">");
//            out.println("            <include refid=\"Base_Column_List\" />");
//            out.println("      </if>");
//            out.println("    from " + tableName);
//            out.println("    <if test=\"_parameter != null\">");
//            out.println("       <include refid=\"Example_Where_Clause\" />");
//            out.println("    </if>");
//            out.println("  </select>");
//            out.println();

//            out.println("  <!-- 按对象删除数据 -->");
//            out.println("  <delete id=\"deleteByExample\" parameterType=\"" + fullPackage + "\">");
//            out.println("    delete from " + tableName + "");
//            out.println("    <if test=\"_parameter != null\">");
//            out.println("       <include refid=\"Example_Where_Clause\" />");
//            out.println("    </if>");
//            out.println("  </delete>");
//            out.println();

            out.println("  <!-- 按表主键删除数据 -->");
            out.println("  <delete id=\"deleteByPrimaryKey\" parameterType=\"" + fullPackageKey + "\" >");
            if (!is_del_flag) {
                out.println("    delete from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            } else {
                out.println("    delete from " + tableName + " where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
                //                out.println("    <if test= \"isDel != null\">");
                //                out.println("        and is_del=#{isDel}");
                //                out.println("    </if>");
            }
            out.println("  </delete>");
            out.println();

            out.println("  <!-- 按表主键批量删除数据 -->");
            out.println("  <delete id=\"deleteBatchByPrimaryKey\" parameterType=\"java.util.List\" >");
            out.println("    delete from " + tableName + " where ");
            out.println("    <foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"or\" close=\")\">");
            //            out.println("        " + idName + " = #{item." + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            out.println("        " + idName + " = #{item}");
            out.println("    </foreach>");
            out.println("  </delete>");
            out.println();

            out.println("  <!-- 可选择性添加数据 -->");
            out.println("  <insert id=\"insertSelective\" " + returnId + " parameterType=\"" + fullPackage + "\" >");
            out.println("    insert into " + tableName);
            out.println("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
            is_first = true;
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.println(",");
                    out.println("      </if>");
                }

                if(MyUtils.formatJdbcType(tableProperties.getDateType()).equalsIgnoreCase("VARCHAR")){
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null and " + MyUtils.formatToVarName(tableProperties.getName())+" != '' \">");
                }else{
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null \">");
                }
                out.print("         " + tableProperties.getName());

                is_first = false;
            }
            out.println(",");
            out.println("      </if>");
            for (TableProperties tableProperties : list) {
                if ("create_time".equals(tableProperties.getName())) {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
                    out.println("         " + tableProperties.getName() + ",");
                    out.println("      </if>");
                }
                if ("c_time_stamp".equals(tableProperties.getName())) {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
                    out.println("         " + tableProperties.getName() + ",");
                    out.println("      </if>");
                }
            }

            out.println("    </trim>");
            out.println("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
            is_first = true;
            for (TableProperties tableProperties : list) {

                if (!is_first) {
                    out.println(",");
                    out.println("      </if>");
                }
                if(MyUtils.formatJdbcType(tableProperties.getDateType()).equalsIgnoreCase("VARCHAR")){
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null and " + MyUtils.formatToVarName(tableProperties.getName())+" != '' \">");
                }else{
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null \">");
                }
                out.print("         #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                is_first = false;
            }
            out.println(",");
            out.println("      </if>");
            for (TableProperties tableProperties : list) {

                if ("create_time".equals(tableProperties.getName())) {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
                    out.println("         CURRENT_TIMESTAMP(),");
                    out.println("      </if>");

                } else if ("c_time_stamp".equals(tableProperties.getName())) {
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
                    out.println("         CURRENT_TIMESTAMP(),");
                    out.println("      </if>");
                }
            }
            out.println("    </trim>");
            out.println("  </insert>");
            out.println();

            out.println("  <!-- 可批量添加数据 -->");
            out.println("  <insert id=\"insertBatch\" parameterType=\"java.util.List\" >");
            out.println("    insert into " + tableName + " (");
            is_first = true;
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.println(",");
                }
                out.print("         " + tableProperties.getName());
                is_first = false;
            }
            out.println(")");
            out.println("    values ");
            //            for (TableProperties tableProperties : list) {
            //                if ("create_time".equals(tableProperties.getName())) {
            //                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
            //                    out.println("         " + tableProperties.getName() + ",");
            //                    out.println("      </if>");
            //                }
            //                if ("c_time_stamp".equals(tableProperties.getName())) {
            //                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
            //                    out.println("         " + tableProperties.getName() + ",");
            //                    out.println("      </if>");
            //                }
            //            }

            //            out.println("    </trim>");
            out.println("    <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">");
            is_first = true;
            out.println("         (");
            for (TableProperties tableProperties : list) {
                if (!is_first) {
                    out.println(",");
                    //                    out.println("      </if>");
                }

                //                out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null\">");
                out.print("         #{item." + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                is_first = false;
            }
            out.println(")");
            //            out.println("      </if>");
            //            for (TableProperties tableProperties : list) {
            //
            //                if ("create_time".equals(tableProperties.getName())) {
            //                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
            //                    out.println("         CURRENT_TIMESTAMP(),");
            //                    out.println("      </if>");
            //
            //                } else if ("c_time_stamp".equals(tableProperties.getName())) {
            //                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " == null\">");
            //                    out.println("         CURRENT_TIMESTAMP(),");
            //                    out.println("      </if>");
            //                }
            //            }
            out.println("    </foreach>");
            out.println("  </insert>");
            out.println();

//            out.println("  <!-- 按对象更新表所有字段信息（create_time,c_time_stamp字段除外） -->");
//            out.println("  <update id=\"updateAllByExample\">");
//            out.println("    update " + tableName);
//            out.println("    <set>");
//            is_first = true;
//            for (TableProperties tableProperties : list) {
//
//                if ("create_time".equals(tableProperties.getName()) || "c_time_stamp".equals(tableProperties.getName())) {
//                    continue;
//                }
//                if (!is_first) {
//                    out.println(",");
//                }
//                out.print("        " + tableProperties.getName() + " = #{record." + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
//                is_first = false;
//            }
//            out.println();
//            out.println("    </set>");
//            out.println("    <if test=\"_parameter != null\">");
//            out.println("       <include refid=\"Update_By_Example_Where_Clause\" />");
//            out.println("    </if>");
//            out.println("  </update>");
//            out.println();

//            out.println("  <!-- 按对象选择性更新表字段信息（create_time,c_time_stamp字段除外） -->");
//            out.println("  <update id=\"updateByExampleSelective\">");
//            out.println("    update " + tableName);
//            out.println("    <set>");
//            is_first = true;
//            for (TableProperties tableProperties : list) {
//
//                //                if ("create_time".equals(tableProperties.getName()) || "c_time_stamp".equals(tableProperties.getName()))
//                //                {
//                //                    continue;
//                //                }
//                if ("c_time_stamp".equals(tableProperties.getName())) {
//                    continue;
//                }
//                if (!is_first) {
//                    out.println(",");
//                    out.println("      </if>");
//                }
//                out.println("      <if test=\"record." + MyUtils.formatToVarName(tableProperties.getName()) + " !=null\">");
//                out.print("        " + tableProperties.getName() + " = #{record." + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
//                is_first = false;
//            }
//            out.println();
//            out.println("      </if>");
//            out.println("    </set>");
//            out.println("    <if test=\"_parameter != null\">");
//            out.println("       <include refid=\"Update_By_Example_Where_Clause\" />");
//            out.println("    </if>");
//            out.println("  </update>");
//            out.println();

            //            out.println("  <!-- 按对象更新表中所有字段信息（create_time,c_time_stamp字段除外）-->");
            //            out.println("  <update id=\"updateByExample\" parameterType=\"" + fullPackage + "\" >");
            //            out.println("    update " + tableName + " set");
            //            is_first = true;
            //            for (TableProperties tableProperties : list)
            //            {
            //                if ("create_time".equals(tableProperties.getName()) || "c_time_stamp".equals(tableProperties.getName()))
            //                {
            //                    continue;
            //                }
            //                if (!is_first)
            //                {
            //                    out.println(",");
            //                }
            //                out.print("      record." + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
            //                is_first = false;
            //            }
            //            out.println();
            //            out.println("    <if test=\"_parameter != null\">");
            //            out.println("       <include refid=\"Example_Where_Clause\" />");
            //            out.println("    </if>");
            //            out.println("  </update>");
            //            out.println();

            out.println("  <!-- 按主键更新表中所有字段信息（create_time,c_time_stamp字段除外）-->");
            out.println("  <update id=\"updateByPrimaryKeySelective\" parameterType=\"" + fullPackage + "\" >");
            out.println("    update " + tableName);
            out.println("    <set>");
            is_first = true;
            for (TableProperties tableProperties : list) {
                if ("c_time_stamp".equals(tableProperties.getName())) {
                    continue;
                }
                if (!is_first) {
                    out.println(",");
                    out.println("      </if>");
                }
                if(MyUtils.formatJdbcType(tableProperties.getDateType()).equalsIgnoreCase("VARCHAR")){
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null and " + MyUtils.formatToVarName(tableProperties.getName())+" != '' \">");
                }else{
                    out.println("      <if test= \"" + MyUtils.formatToVarName(tableProperties.getName()) + " != null \">");
                }
                out.print("      " + tableProperties.getName() + " = #{" + MyUtils.formatToVarName(tableProperties.getName()) + ",jdbcType=" + MyUtils.formatJdbcType(tableProperties.getDateType()) + "}");
                is_first = false;
            }
            out.println();
            out.println("      </if>");
            out.println("    </set>");
            //            if (!is_del_flag)
            //            {
            out.println("    where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
            //            }
            //            else
            //            {
            //                out.println(" where " + idName + " = #{" + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "} and is_del=#{isDel}");
            //            }

            out.println("  </update>");
            out.println();

//            out.println("  <!-- 按主键批量更新表中所有字段信息（create_time,c_time_stamp字段除外）-->");
//            out.println("  <update id=\"updateBatchByPrimaryKey\" parameterType=\"java.util.List\" >");
//            out.println("    update " + tableName);
//            //            out.println("    <set>");
//            out.println("       <trim prefix=\"set\" suffixOverrides=\",\">");
//            //            out.println("         <trim prefix=\"email =case\" suffix=\"end,\">");
//            //            out.println("              <foreach collection=\"list\" item=\"item\" index=\"index\">");
//            //            out.println("                  <if test=\"item.email!=null\">");
//            //            out.println("                      when passport_id=#{item.passportId,jdbcType=VARCHAR} then #{item.email}");
//            //            out.println("                  </if>");
//            //            out.println("              </foreach>");
//            //            out.println("         </trim>");
//            //            out.println("       </trim>");
//            is_first = true;
//            for (TableProperties tableProperties : list) {
//                if ("c_time_stamp".equals(tableProperties.getName())) {
//                    continue;
//                }
//                out.println("         <trim prefix=\"" + tableProperties.getName() + " =case\" suffix=\"end,\">");
//                out.println("              <foreach collection=\"list\" item=\"item\" index=\"index\">");
//                out.println("                  <if test=\"item." + MyUtils.formatToVarName(tableProperties.getName()) + " !=null\">");
//                out.println("                      when " + idName + " = #{item." + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "} then #{item." + MyUtils.formatToVarName(tableProperties.getName()) + "}");
//                out.println("                  </if>");
//                out.println("              </foreach>");
//                out.println("         </trim>");
//                is_first = false;
//            }
//            out.println();
//            out.println("      </trim>");
//            //            out.println("    </set>");
//            out.println("    where ");
//            out.println("    <foreach collection=\"list\" separator=\"or\" item=\"item\" index=\"index\" >");
//            out.println("          " + idName + " = #{item." + MyUtils.formatToVarName(idName) + ",jdbcType=" + idJdbcType + "}");
//            out.println("    </foreach>");
//            out.println("  </update>");
//            out.println();
            out.println("</mapper>");
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
