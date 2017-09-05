package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class Mapper
{

    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String className = MyUtils.formatToClassName(tableName);
            String varName = MyUtils.formatToVarName(tableName);
            String idName = MyUtils.formatToVarName(list.get(0).getName());
            String idType = MyUtils.formatDataType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_mapper() + className + "Mapper.java", info.getEncode());
            out.println("package " + info.getPackage_mapper() + ";");
            out.println("");
            out.println("import java.util.List;");
            out.println("");
            out.println("import org.apache.ibatis.annotations.Param;");
            out.println("");
            out.println("import " + info.getPackage_entity() + "." + className + ";");
            out.println("import com.fetchview.mapper.SqlMapper;");
            out.println("");
            out.println("public interface " + className + "Mapper extends SqlMapper<" + className + ", Integer>  {");
//            out.println("{");
            out.println("    public Integer count(" + className + " " + varName + ");");
            out.println("");
            out.println("    public Integer add(" + className + " " + varName + ");");
            out.println("");
            out.println("    public Integer deleteById(" + idType + " " + idName + ");");
            out.println("");
            out.println("    public Integer deleteByExample(" + className + " " + varName + ");");
            out.println("");
            out.println("    public Integer updateAll(" + className + " " + varName + ");");
            out.println("");
            out.println("    public Integer updateSelected(" + className + " " + varName + ");");
            out.println("");
            out.println("    public " + className + " findById(" + idType + " " + idName + ");");
            out.println("");
            out.println("    public List<" + className + "> findByExample(" + className + " " + varName + ");");
            out.println("");
            out.println("    public List<" + className + "> pageByExample(@Param(\"" + varName + "\")" + className + " " + varName + ", @Param(\"start\")Integer start, @Param(\"size\")Integer size,@Param(\"orderBy\")String orderBy);");
            out.println("");
            out.println("}");
            out.flush();
            out.close();
            out = null;
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
