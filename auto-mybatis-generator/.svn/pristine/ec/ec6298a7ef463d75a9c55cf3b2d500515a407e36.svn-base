package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class Impl {

    public static void createCoreApiImplFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String className = MyUtils.formatToClassName(tableName);
            String varName = MyUtils.formatToVarName(tableName);
            String classNameExample = className + "Example";
            String varNameExample = varName + "Example";
            String idName = MyUtils.formatToVarName(list.get(0).getName());
            String idType = MyUtils.formatDataType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_core_impl() + className + "CoreServiceImpl.java", info.getEncode());
            out.println("package " + info.getPackage_impl_core() + ";");
            out.println("");
            out.println("import java.util.ArrayList;");
            out.println("import java.util.HashMap;");
            out.println("import java.util.List;");
            out.println("import java.util.Map;");
            out.println("");
            out.println("import org.springframework.beans.factory.annotation.Autowired;");
            out.println("import org.springframework.stereotype.Service;");
            out.println("");
            List<String> tmp = new ArrayList<String>();
            tmp.add("import " + info.getPackage_mapper() + "." + className + "Mapper;");
            tmp.add("import " + info.getPackage_entity() + "." + className + ";");
            tmp.add("import " + info.getPackage_entity() + "." + className + "Example;");
            tmp.add("import " + info.getPackage_entity() + "." + className + "Key;");
            tmp.add("import " + info.getPackage_api_core() + "." + className + "CoreService;");
            tmp.add("import com.offen.common.utils.StringUtils;");
            Collections.sort(tmp);
            for (String str : tmp) {
                out.println(str);
            }
            out.println("");
            out.println("@Service(value = \"" + varName + "Service\")");
            out.println("public class " + className + "CoreServiceImpl implements " + className + "CoreService");
            out.println("{");
            out.println("");
            out.println("    @Autowired");
            out.println("    private " + className + "Mapper " + varName + "Mapper;");
            out.println("    ");
            //            out.println("    @Override");
            out.println("    public Integer countByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.countByExample(" + varNameExample + ");");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean insertSelective(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.insertSelective(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean batchInsert(List<" + className + "> " + varName + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.batchInsert(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean deleteByPrimaryKey(String " + idName + ")");
            out.println("    {");
            out.println("        if (StringUtils.isNotNull(" + idName + "))");
            out.println("        {");
            out.println("            " + className + "Key " + varName + "Key = new " + className + "Key();");
            if ("String".equals(idType)) {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(" + idName + ");");
            } else {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(Integer.parseInt(" + idName + "));");
            }
            out.println("            return this." + varName + "Mapper.deleteByPrimaryKey(" + varName + "Key) > 0 ? true : false;");
            out.println("        }");
            out.println("        return false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean deleteByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.deleteByExample(" + varNameExample + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean updateAllByExample(" + className + " " + varName + "," + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.updateAllByExample(" + varName + "," + varNameExample + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean updateByExampleSelective(" + className + " " + varName + "," + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.updateByExampleSelective(" + varName + "," + varNameExample + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean updateByPrimaryKeySelective(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.updateByPrimaryKeySelective(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public " + className + " findById(String " + idName + ")");
            out.println("    {");
            out.println("        if (StringUtils.isNotNull(" + idName + "))");
            out.println("        {");
            out.println("            " + className + "Key " + varName + "Key = new " + className + "Key();");
            if ("String".equals(idType)) {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(" + idName + ");");
            } else {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(Integer.parseInt(" + idName + "));");
            }
            out.println("            return this." + varName + "Mapper.selectByPrimaryKey(" + varName + "Key);");
            out.println("        }");
            out.println("        return null;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public List<" + className + "> selectByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.selectByExample(this.setColumnNameToNull(" + varNameExample + "));");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public List<" + className + "> pageByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.selectByExample(this.setColumnNameToNull(" + varNameExample + "));");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public Map<String, Object> selectPageByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        " + varNameExample + " = this.setColumnNameToNull(" + varNameExample + ");");
            out.println("        List<" + className + "> list = new ArrayList<" + className + ">();");
            out.println("        list = this." + varName + "Mapper.selectByExample(" + varNameExample + ");");
            out.println("        int pageCount = this." + varName + "Mapper.countByExample(" + varNameExample + ");");
            out.println("        Map<String, Object> jsonMap = new HashMap<String, Object>();");
            out.println("        jsonMap.put(\"total\", pageCount);");
            out.println("        jsonMap.put(\"rows\", list);");
            out.println("        return jsonMap;");
            out.println("    }");
            out.println("");
            out.println("    private " + classNameExample + " setColumnNameToNull(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        if (!StringUtils.isEmpty(" + varNameExample + "))");
            out.println("        {");
            out.println("            if (StringUtils.isEmpty(" + varNameExample + ".getColumnName().queryColumnNameStr))");
            out.println("            {");
            out.println("                " + varNameExample + ".setColumnName(null);");
            out.println("            }");
            out.println("        }");
            out.println("        return " + varNameExample + ";");
            out.println("    }");
            out.println("}");
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void createQueryApiImplFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String className = MyUtils.formatToClassName(tableName);
            String varName = MyUtils.formatToVarName(tableName);
            String classNameExample = className + "Example";
            String varNameExample = varName + "Example";
            String idName = MyUtils.formatToVarName(list.get(0).getName());
            String idType = MyUtils.formatDataType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_query_impl() + className + "QueryServiceImpl.java", info.getEncode());
            out.println("package " + info.getPackage_impl_query() + ";");
            out.println("");
            out.println("import java.util.ArrayList;");
            out.println("import java.util.HashMap;");
            out.println("import java.util.List;");
            out.println("import java.util.Map;");
            out.println("");
            out.println("import org.springframework.beans.factory.annotation.Autowired;");
            out.println("import org.springframework.stereotype.Service;");
            out.println("");
            List<String> tmp = new ArrayList();
            tmp.add("import " + info.getPackage_mapper() + "." + className + "Mapper;");
            tmp.add("import " + info.getPackage_entity() + "." + className + ";");
            tmp.add("import " + info.getPackage_entity() + "." + className + "Example;");
            tmp.add("import " + info.getPackage_entity() + "." + className + "Key;");
            tmp.add("import " + info.getPackage_api_query() + "." + className + "QueryService;");
            tmp.add("import com.offen.common.utils.StringUtils;");
            Collections.sort(tmp);
            for (String str : tmp) {
                out.println(str);
            }
            out.println("");
            out.println("@Service(value = \"" + varName + "Service\")");
            out.println("public class " + className + "QueryServiceImpl implements " + className + "QueryService");
            out.println("{");
            out.println("");
            out.println("    @Autowired");
            out.println("    private " + className + "Mapper " + varName + "Mapper;");
            out.println("    ");
            //            out.println("    @Override");
            out.println("    public Integer countByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.countByExample(" + varNameExample + ");");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public " + className + " findById(String " + idName + ")");
            out.println("    {");
            out.println("        if (StringUtils.isNotNull(" + idName + "))");
            out.println("        {");
            out.println("            " + className + "Key " + varName + "Key = new " + className + "Key();");
            if ("String".equals(idType)) {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(" + idName + ");");
            } else {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(Integer.parseInt(" + idName + "));");
            }
            out.println("            return this." + varName + "Mapper.selectByPrimaryKey(" + varName + "Key);");
            out.println("        }");
            out.println("        return null;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public List<" + className + "> selectByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.selectByExample(this.setColumnNameToNull(" + varNameExample + "));");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public List<" + className + "> pageByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        return this." + varName + "Mapper.selectByExample(this.setColumnNameToNull(" + varNameExample + "));");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public Map<String, Object> selectPageByExample(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        " + varNameExample + " = this.setColumnNameToNull(" + varNameExample + ");");
            out.println("        List<" + className + "> list = new ArrayList<" + className + ">();");
            out.println("        list = this." + varName + "Mapper.selectByExample(" + varNameExample + ");");
            out.println("        int pageCount = this." + varName + "Mapper.countByExample(" + varNameExample + ");");
            out.println("        Map<String, Object> jsonMap = new HashMap<String, Object>();");
            out.println("        jsonMap.put(\"total\", pageCount);");
            out.println("        jsonMap.put(\"rows\", list);");
            out.println("        return jsonMap;");
            out.println("    }");
            out.println("");
            out.println("    private " + classNameExample + " setColumnNameToNull(" + classNameExample + " " + varNameExample + ")");
            out.println("    {");
            out.println("        if (!StringUtils.isEmpty(" + varNameExample + "))");
            out.println("        {");
            out.println("            if (StringUtils.isEmpty(" + varNameExample + ".getColumnName().queryColumnNameStr))");
            out.println("            {");
            out.println("                " + varNameExample + ".setColumnName(null);");
            out.println("            }");
            out.println("        }");
            out.println("        return " + varNameExample + ";");
            out.println("    }");
            out.println("}");
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * @方法名: createServiceImplFile
     * @方法描述: TODO(创建ServiceImpl 实现类文件)
     * @param tableName
     * @param list
     * @param info
     * @throws Exception
     * @返回值 void 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月9日 下午6:46:05
     * @修改时间 2015年3月9日 下午6:46:05
     * @版本 V1.0
     * @异常
     */
    public static void createServiceImplFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String className = MyUtils.formatToClassName(tableName);
            String varName = MyUtils.formatToVarName(tableName);
            //            String classNameExample = className + "Example";
            String classNameExample = "CriteriaExample";
            //            String varNameExample = varName + "Example";
            String varNameExample = "criteriaExample";
            String classNameKey = className + "Key";
            String varNameKey = varName + "Key";
            String idName = MyUtils.formatToVarName(list.get(0).getName());
            String idType = MyUtils.formatDataType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_core_impl() + className + "ServiceImpl.java", info.getEncode());
            out.println("package " + info.getPackage_impl_core() + ";");
            out.println("");
            out.println("import java.util.ArrayList;");
            out.println("import java.util.HashMap;");
            out.println("import java.util.List;");
            out.println("import java.util.Map;");
            out.println("");
            out.println("import org.springframework.beans.factory.annotation.Autowired;");
            out.println("import org.springframework.stereotype.Service;");
            out.println("");
            List<String> tmp = new ArrayList<String>();
            tmp.add("import " + info.getPackage_mapper() + "." + className + "Mapper;");
            tmp.add("import " + info.getPackage_entity() + "." + className + ";");
            //            out.println("import " + info.getPackage_entity() + "." + classNameKey + ";");
            //            tmp.add("import " + info.getPackage_entity() + "." + className + "Example;");
            out.println("import com.offen.model." + classNameExample + ";");
            tmp.add("import " + info.getPackage_entity() + "." + className + "Key;");
            tmp.add("import " + info.getPackage_api_core() + "." + className + "Service;");
            tmp.add("import com.offen.common.utils.StringUtils;");
            Collections.sort(tmp);
            for (String str : tmp) {
                out.println(str);
            }
            out.println("");
            out.println("@Service(value = \"" + varName + "Service\")");
            out.println("public class " + className + "ServiceImpl implements " + className + "Service {");
            //            out.println("{");
            out.println("");
            out.println("    @Autowired");
            out.println("    private " + className + "Mapper " + varName + "Mapper;");
            out.println("    ");
            //            out.println("    @Override");
            out.println("    public Integer countByExample(" + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.countByExample(" + varNameExample + ");");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean insertSelective(" + className + " " + varName + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.insertSelective(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean batchInsert(List<" + className + "> " + varName + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.batchInsert(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean deleteByPrimaryKey(String " + idName + ") {");
            //            out.println("    {");
            out.println("        if (StringUtils.isNotNull(" + idName + ")) {");
            //            out.println("        {");
            out.println("            " + className + "Key " + varName + "Key = new " + className + "Key();");
            if ("String".equals(idType)) {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(" + idName + ");");
            } else {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(Integer.parseInt(" + idName + "));");
            }
            out.println("            return this." + varName + "Mapper.deleteByPrimaryKey(" + varName + "Key) > 0 ? true : false;");
            out.println("        }");
            out.println("        return false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean batchDeleteByPrimaryKey(List<" + classNameKey + "> " + varNameKey + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.batchDeleteByPrimaryKey(" + varNameKey + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean deleteByExample(" + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.deleteByExample(" + varNameExample + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean updateAllByExample(" + className + " " + varName + "," + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.updateAllByExample(" + varName + "," + varNameExample + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean updateByExampleSelective(" + className + " " + varName + "," + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.updateByExampleSelective(" + varName + "," + varNameExample + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean updateByPrimaryKeySelective(" + className + " " + varName + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.updateByPrimaryKeySelective(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public boolean batchUpdateByPrimaryKey(List<" + className + "> " + varName + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.batchUpdateByPrimaryKey(" + varName + ") > 0 ? true : false;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public " + className + " findById(String " + idName + ") {");
            //            out.println("    {");
            out.println("        if (StringUtils.isNotNull(" + idName + ")) {");
            //            out.println("        {");
            out.println("            " + className + "Key " + varName + "Key = new " + className + "Key();");
            if ("String".equals(idType)) {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(" + idName + ");");
            } else {
                out.println("            " + varName + "Key.set" + MyUtils.upperFirstLetter(idName) + "(Integer.parseInt(" + idName + "));");
            }
            out.println("            return (" + className + ") this." + varName + "Mapper.selectByPrimaryKey(" + varName + "Key);");
            out.println("        }");
            out.println("        return null;");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public List<" + className + "> selectByExample(" + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.selectListByExample(" + varNameExample + ");");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public List<" + className + "> pageByExample(" + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            out.println("        return this." + varName + "Mapper.selectByExample(" + varNameExample + ");");
            out.println("    }");
            out.println("");
            //            out.println("    @Override");
            out.println("    public Map<String, Object> selectPageByExample(" + classNameExample + " " + varNameExample + ") {");
            //            out.println("    {");
            //            out.println("        " + varNameExample + " = this.setColumnNameToNull(" + varNameExample + ");");
            out.println("        List<" + className + "> list = new ArrayList<" + className + ">();");
            out.println("        list = this." + varName + "Mapper.selectByExample(" + varNameExample + ");");
            out.println("        int pageCount = this." + varName + "Mapper.countByExample(" + varNameExample + ");");
            out.println("        Map<String, Object> jsonMap = new HashMap<String, Object>();");
            out.println("        jsonMap.put(\"total\", pageCount);");
            out.println("        jsonMap.put(\"rows\", list);");
            out.println("        return jsonMap;");
            out.println("    }");
            out.println("");
            //            out.println("    private " + classNameExample + " setColumnNameToNull(" + classNameExample + " " + varNameExample + ") {");
            //            //            out.println("    {");
            //            out.println("        if (!StringUtils.isEmpty(" + varNameExample + ")) {");
            //            //            out.println("        {");
            //            out.println("            if (StringUtils.isEmpty(" + varNameExample + ".getColumnName().queryColumnNameStr))");
            //            out.println("            {");
            //            out.println("                " + varNameExample + ".setColumnName(null);");
            //            out.println("            }");
            //            out.println("        }");
            //            out.println("        return " + varNameExample + ";");
            //            out.println("    }");
            out.println("}");
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * @方法名: createGenericityServiceImplFile
     * @方法描述: TODO(创建ServiceImple 泛型实现类)
     * @param tableName
     * @param list
     * @param info
     * @throws Exception
     * @返回值 void 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月15日 下午8:40:44
     * @修改时间 2015年3月15日 下午8:40:44
     * @版本 V1.0
     * @异常
     */
    public static void createGenericityServiceImplFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String primaryKey = "id";
            int i = 0;
            boolean isdel = false;
            for (TableProperties tableProperties : list) {
                if ("is_del".equals(tableProperties.getName())) {
                    isdel = true;
                    break;
                }
            }
            for (TableProperties tableProperties : list) {
                if ("is_del".equals(tableProperties.getName())) {
                    isdel = true;
                }
                if (i == 0 || "is_del".equals(tableProperties.getName())) {
                    String comment = tableProperties.getComment();
                    if (comment.indexOf("\n") > 0) {
                        comment = comment.substring(0, comment.indexOf("\n") - 1);
                    }
                    primaryKey = MyUtils.formatToVarName(tableProperties.getName());
                    break;
                }
                i++;
            }

            String className = MyUtils.formatToClassName(tableName);
            String varName = MyUtils.formatToVarName(tableName);
            //            String classNameExample = className + "Example";
            //            String varNameExample = varName + "Example";
//            String idName = MyUtils.formatToVarName(list.get(0).getName());
//            String idType = MyUtils.formatDataType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_core_impl() + className + "ServiceImpl.java", info.getEncode());
            out.println("package " + info.getPackage_impl_core() + ";");
            out.println("");
//            out.println("import java.util.ArrayList;");
//            out.println("import java.util.HashMap;");
            out.println("import java.util.List;");
//            out.println("import java.util.Map;");
            out.println("");
            out.println("import org.springframework.beans.factory.annotation.Autowired;");
            out.println("import org.springframework.stereotype.Service;");
            out.println("");
            List<String> tmp = new ArrayList<String>();
            tmp.add("import " + info.getPackage_mapper() + "." + className + "Mapper;");
            tmp.add("import " + info.getPackage_entity() + "." + className + ";");
            //            out.println("import " + info.getPackage_entity() + "." + classNameKey + ";");
            //            tmp.add("import " + info.getPackage_entity() + "." + className + "Example;");
//            tmp.add("import com.offen.common.primarykey.ClassReflectUtil;");
            tmp.add("import " + info.getPackage_api_core() + "." + className + "Service;");
            Collections.sort(tmp);
            for (String str : tmp) {
                out.println(str);
            }
            out.println("");
            out.println("@Service(value = \"" + varName + "Service\")");
            out.println("public class " + className + "ServiceImpl implements " + className + "Service {");
            out.println("");
            out.println("    @Autowired");
            out.println("    private " + className + "Mapper<"+className+">" + varName + "Mapper;");
            out.println("    ");
            out.println("    @Override");
            out.println("    public Integer insertSelective(" + className + " " + varName + ") throws Exception {");
            out.println("           return this." + varName + "Mapper.insertSelective(" + varName + ");");
            out.println("        }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer insertBatch(List<" + className + "> " + varName + ") throws Exception {");
            out.println("           return this." + varName + "Mapper.insertBatch(" + varName + ") ;");
            out.println("        }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer deleteByPrimaryKey(Object id) {");
            out.println("            return this." + varName + "Mapper.deleteByPrimaryKey(id);");
            out.println("    }");
            out.println("");
            out.println("    public Integer deleteBatchByPrimaryKey(List<Object> ids) {");
            out.println("           return this." + varName + "Mapper.deleteBatchByPrimaryKey(ids);");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer updateByPrimaryKeySelective(" + className + " " + varName + ") {");
            out.println("           return this." + varName + "Mapper.updateByPrimaryKeySelective(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public " + className + " findById(Object id) {");
            out.println("           return (" + className + ") this." + varName + "Mapper.selectByPrimaryKey(id);");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public List<" + className + "> selectByCondition(" + className + " " + varName + ") {");
            out.println("           return (List<" + className + ">) this." + varName + "Mapper.selectByCondition(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer selectCountByCondition(" + className + " " + varName + ") {");
            out.println("           return  this." + varName + "Mapper.selectCountByCondition(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("}");
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
