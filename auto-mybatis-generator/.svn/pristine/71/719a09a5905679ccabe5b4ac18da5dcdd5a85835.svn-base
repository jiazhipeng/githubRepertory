package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class QueryMapper
{
    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String className = MyUtils.formatToClassName(tableName);
            String classNameExample = className + "Example";
            String classNameKey = className + "Key";
            String varName = MyUtils.formatToVarName(tableName);
            String varNameExample = varName + "Example";
            String varNameKey = varName + "Key";
            out = new PrintWriter(info.getPath_query_mapper() + className + "Mapper.java", info.getEncode());
            out.println("package " + info.getPackage_mapper() + ";");
            out.println("");

            out.println("import java.util.List;");
            out.println("");
            out.println("import org.apache.ibatis.annotations.Param;");
            out.println("");
            out.println("import " + info.getPackage_entity() + "." + className + ";");
            out.println("import " + info.getPackage_entity() + "." + className + "Example;");
            out.println("import " + info.getPackage_entity() + "." + className + "Key;");
            out.println("");

            out.println("public interface " + className + "Mapper {");
//            out.println("{");

            out.println("");
            out.println("    public Integer countByExample(" + classNameExample + " " + varNameExample + ");");
            out.println("");
            out.println("    public List<" + className + "> selectByExample(" + classNameExample + " " + varNameExample + ");");
            out.println("");

            out.println("    public " + className + " selectByPrimaryKey(" + classNameKey + " " + varNameKey + ");");
            out.println("");

            out.println("    public List<" + className + "> pageByExample(@Param(\"example\")" + classNameExample + " " + varNameExample + ");");
            out.println("");

            //            out.println("    public " + className + " selectByPrimaryKey(String primaryKey);");
            //            out.println("");
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
