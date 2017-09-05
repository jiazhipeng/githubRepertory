package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class Test
{

    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String className = MyUtils.formatToClassName(tableName);
            out = new PrintWriter(info.getPath_test() + "Test" + className + "Service.java", info.getEncode());
            out.println("package " + info.getPackage_test() + ";");
            out.println();
            out.println("public class Test" + className + "Service {");
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
