package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.Constants;
import com.toolkit.auto.mybatis.utils.MyUtils;
import com.toolkit.auto.mybatis.utils.RandomUtils;
import com.toolkit.auto.mybatis.utils.TimeUtils;

public class EntityFile {

    public static void createFile(String tableName, String table_comment, List<TableProperties> list, FrameInfo info) throws Exception {
        createTableEntityFile(tableName, table_comment, list, info);
        //        createTableEntityExampleFile(tableName, table_comment, list, info);
        //        createTableEntityKeyFile(tableName, table_comment, list, info);
    }

    /**
     * @方法名: createTableEntityFile
     * @方法描述: TODO(生成表对应实体)
     * @param tableName
     * @param table_comment
     * @param list
     * @param info
     * @throws Exception
     * @返回值 void 返回类型
     * @作者：FuShihua
     * @创建时间 2014年12月11日 下午11:42:27
     * @修改时间 2014年12月11日 下午11:42:27
     * @版本 V1.0
     * @异常
     */
    public static void createTableEntityFile(String tableName, String table_comment, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            if (table_comment.indexOf("\n") > 0) {
                table_comment = table_comment.substring(0, table_comment.indexOf("\n") - 1);
            }
            String className = MyUtils.formatToClassName(tableName);
            out = new PrintWriter(info.getPath_entity() + className + ".java", info.getEncode());
            out.println("package " + info.getPackage_entity() + ";");
            out.println();
            out.println("import java.io.Serializable;");

            for (TableProperties tableProperties : list) {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Time".equals(type)) {
                    out.println("import java.sql.Time;");
                    break;
                }
            }

            for (TableProperties tableProperties : list) {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type)) {
                    out.println("import java.util.Date;");
                    break;
                }
            }
            for (TableProperties tableProperties : list) {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("BigInteger".equals(type)) {
                    //out.println("import java.math.BigInteger;");
                	tableProperties.setDateType("Long");
                }
            }
            for (TableProperties tableProperties : list) {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("BigDecimal".equals(type)) {
                    out.println("import java.math.BigDecimal;");
                    break;
                }
            }

            out.println();
            out.println("/**");
            out.println(" * " + table_comment);
            out.println(" * @author auto create");
            out.println(" * @since 1.0," + TimeUtils.format(new Date(), Constants.SHOW_DATETIME_FORMAT));
            out.println(" */");
            out.println("public class " + className + " implements Serializable {");
            //            out.println("{");
            out.println();
            out.println("    private static final long serialVersionUID = " + RandomUtils.strLength10() + RandomUtils.longLength6() + "L;");
            out.println();
            for (TableProperties tableProperties : list) {
                String comment = tableProperties.getComment();
                if (comment.indexOf("\n") > 0) {
                    comment = comment.substring(0, comment.indexOf("\n") - 1);
                }
                out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + ";//" + comment);
                out.println();
            }
            String varNameTemp = "";
            String classNameTemp = "";
            for (TableProperties tableProperties : list) {
                varNameTemp = MyUtils.formatToVarName(tableProperties.getName());
                classNameTemp = MyUtils.formatToClassName(tableProperties.getName());
                out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "() {");
                //                out.println("    {");
                out.println("        return this." + varNameTemp + ";");
                out.println("    }");
                out.println("");
                out.println("    public void set" + classNameTemp + "(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + ") {");
                //                out.println("    {");
                out.println("        this." + varNameTemp + " = " + varNameTemp + ";");
                out.println("    }");
                out.println();
            }
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
     * @方法名: createTableEntityExampleFile
     * @方法描述: TODO(生成实体实例)
     * @param tableName
     * @param table_comment
     * @param list
     * @param info
     * @throws Exception
     * @返回值 void 返回类型
     * @作者：FuShihua
     * @创建时间 2014年12月12日 上午1:04:40
     * @修改时间 2014年12月12日 上午1:04:40
     * @版本 V1.0
     * @异常
     */
    public static void createTableEntityExampleFile(String tableName, String table_comment, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            if (table_comment.indexOf("\n") > 0) {
                table_comment = table_comment.substring(0, table_comment.indexOf("\n") - 1);
            }
            String className = MyUtils.formatToClassName(tableName) + "Example";
            out = new PrintWriter(info.getPath_entity() + className + ".java", info.getEncode());
            out.println("package " + info.getPackage_entity() + ";");
            out.println();
            out.println("import java.io.Serializable;");
            out.println("import java.util.ArrayList;");
            for (TableProperties tableProperties : list) {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type)) {
                    out.println("import java.util.Date;");
                    break;
                }
            }
            //            for (TableProperties tableProperties : list)
            //            {
            //                String type = MyUtils.formatDataType(tableProperties.getDateType());
            //                if ("Date".equals(type))
            //                {
            //                    out.println("import java.util.Date;");
            //                    break;
            //                }
            //            }
            //            for (TableProperties tableProperties : list)
            //            {
            //                String type = MyUtils.formatDataType(tableProperties.getDateType());
            //                if ("BigInteger".equals(type))
            //                {
            //                    out.println("import java.math.BigInteger;");
            //                    break;
            //                }
            //            }
            out.println("import java.util.List;");

            out.println();
            out.println("/**");
            out.println(" * " + table_comment);
            out.println(" * @author auto creater");
            out.println(" * @since 1.0," + TimeUtils.format(new Date(), Constants.SHOW_DATETIME_FORMAT));
            out.println(" */");
            out.println("public class " + className + " extends BaseEntity implements Serializable {");
            //            out.println("{");
            out.println();
            out.println("    private static final long serialVersionUID = " + RandomUtils.strLength10() + RandomUtils.longLength6() + "L;");
            out.println();

            createBaseEntity(out, className);
            createAbstract(out, list);
            createColumnNameClass(out, list);

            out.println("    public static class Criteria extends GeneratedCriteria implements Serializable {");
            //            out.println("    {");
            out.println();
            out.println("        private static final long serialVersionUID = " + RandomUtils.strLength10() + RandomUtils.longLength6() + "L;");
            out.println();
            out.println("        protected Criteria() {");
            //            out.println("        {");
            out.println("            super();");
            out.println("        }");
            out.println("    }");

            //开始构建Criterion类
            out.println("    public static class Criterion implements Serializable {");
            //            out.println("    {");
            out.println();
            out.println("        private static final long serialVersionUID = " + RandomUtils.strLength10() + RandomUtils.longLength6() + "L;");
            out.println();
            out.println("        private String condition;");
            out.println();
            out.println("        private Object value;");
            out.println();
            out.println("        private Object secondValue;");
            out.println();
            out.println("        private boolean noValue;");
            out.println();
            out.println("        private boolean singleValue;");
            out.println();
            out.println("        private boolean betweenValue;");
            out.println();
            out.println("        private boolean listValue;");
            out.println();
            out.println("        private String typeHandler;");
            out.println();
            out.println("        public String getCondition() {");
            //            out.println("        {");
            out.println("            return this.condition;");
            out.println("        }");
            out.println();
            out.println("        public Object getValue() {");
            //            out.println("        {");
            out.println("            return this.value;");
            out.println("        }");
            out.println();
            out.println("        public Object getSecondValue() {");
            //            out.println("        {");
            out.println("            return this.secondValue;");
            out.println("        }");
            out.println();
            out.println("        public boolean isNoValue() {");
            //            out.println("        {");
            out.println("            return this.noValue;");
            out.println("        }");
            out.println();
            out.println("        public boolean isSingleValue() {");
            //            out.println("        {");
            out.println("            return this.singleValue;");
            out.println("        }");
            out.println();
            out.println("        public boolean isBetweenValue() {");
            //            out.println("        {");
            out.println("            return this.betweenValue;");
            out.println("        }");
            out.println();
            out.println("        public boolean isListValue() {");
            //            out.println("        {");
            out.println("            return this.listValue;");
            out.println("        }");
            out.println();
            out.println("        public String getTypeHandler() {");
            //            out.println("        {");
            out.println("            return this.typeHandler;");
            out.println("        }");
            out.println();
            out.println("        protected Criterion(String condition) {");
            //            out.println("        {");
            out.println("            super();");
            out.println("            this.condition = condition;");
            out.println("            this.typeHandler = null;");
            out.println("            this.noValue = true;");
            out.println("        }");

            out.println();
            out.println("        protected Criterion(String condition, Object value, String typeHandler) {");
            //            out.println("        {");
            out.println("            super();");
            out.println("            this.condition = condition;");
            out.println("            this.value = value;");
            out.println("            this.typeHandler = typeHandler;");
            out.println("            if (value instanceof List<?>)");
            out.println("            {");
            out.println("                this.listValue = true;");
            out.println("            }");
            out.println("            else");
            out.println("            {");
            out.println("                this.singleValue = true;");
            out.println("            }");
            out.println("        }");

            out.println();
            out.println("        protected Criterion(String condition, Object value) {");
            //            out.println("        {");
            out.println("            this(condition, value, null);");
            out.println("        }");

            out.println();
            out.println("        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {");
            //            out.println("        {");
            out.println("            super();");
            out.println("            this.condition = condition;");
            out.println("            this.value = value;");
            out.println("            this.secondValue = secondValue;");
            out.println("            this.typeHandler = typeHandler;");
            out.println("            this.betweenValue = true;");
            out.println("        }");

            out.println();
            out.println("        protected Criterion(String condition, Object value, Object secondValue) {");
            //            out.println("        {");
            out.println("            this(condition, value, secondValue, null);");
            out.println("        }");
            out.println("    }");
            out.println();

            out.println("    public ColumnName createColumnName()");
            out.println("    {");
            out.println("        return this.columnName;");
            out.println("    }");

            out.println();

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

    public static void createTableEntityKeyFile(String tableName, String table_comment, List<TableProperties> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {

            if (table_comment.indexOf("\n") > 0) {
                table_comment = table_comment.substring(0, table_comment.indexOf("\n") - 1);
            }
            String className = MyUtils.formatToClassName(tableName);
            out = new PrintWriter(info.getPath_entity() + className + "Key.java", info.getEncode());
            out.println("package " + info.getPackage_entity() + ";");
            out.println();
            out.println("import java.io.Serializable;");
            out.println();
            out.println("/**");
            out.println(" * " + table_comment);
            out.println(" * @author auto creater");
            out.println(" * @since 1.0," + TimeUtils.format(new Date(), Constants.SHOW_DATETIME_FORMAT));
            out.println(" */");
            out.println("public class " + className + "Key implements Serializable {");
            //            out.println("{");
            out.println();
            out.println("    private static final long serialVersionUID = " + RandomUtils.strLength10() + RandomUtils.longLength6() + "L;");
            out.println();
            int i = 0;
            for (TableProperties tableProperties : list) {
                if (i == 0 || "is_del".equals(tableProperties.getName())) {
                    String comment = tableProperties.getComment();
                    if (comment.indexOf("\n") > 0) {
                        comment = comment.substring(0, comment.indexOf("\n") - 1);
                    }
                    out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + ";//" + comment);
                    out.println();
                }
                i++;
            }
            String varNameTemp = "";
            String classNameTemp = "";
            i = 0;
            for (TableProperties tableProperties : list) {
                varNameTemp = MyUtils.formatToVarName(tableProperties.getName());
                classNameTemp = MyUtils.formatToClassName(tableProperties.getName());
                if (i == 0 || "is_del".equals(tableProperties.getName())) {
                    out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "() {");
                    //                    out.println("    {");
                    out.println("        return " + varNameTemp + ";");
                    out.println("    }");
                    out.println("");
                    out.println("    public void set" + classNameTemp + "(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + ") {");
                    //                    out.println("    {");
                    out.println("        this." + varNameTemp + " = " + varNameTemp + ";");
                    out.println("    }");
                    out.println();
                }
                i++;
            }

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
     * 构建xxxExample中的基础属性
     * 
     * @param out
     * @param className
     */
    public static void createBaseEntity(PrintWriter out, String className) {
        List<String> list = new ArrayList<String>();
        list.add("orderByClause");
        list.add("distinct");
        list.add("oredCriteria");
        list.add("limitStart");
        list.add("limitEnd");
        for (String temp : list) {
            String type = createBaseEntity(temp);
            if ("limitStart".equals(temp) || "limitEnd".equals(temp)) {
                out.println("    private " + type + " " + temp + " = -1;");
            } else {
                out.println("    protected " + type + " " + temp + ";");
            }
            out.println();
        }
        out.println("    public ColumnName columnName=new ColumnName();");
        out.println();
        out.println("    public " + className + "() {");
        //        out.println("    {");
        out.println("        oredCriteria = new ArrayList<Criteria>();");
        out.println("    }");
        out.println();
        list.add("columnName");

        for (String temp : list) {
            String type = createBaseEntity(temp);
            String classNameTemp = MyUtils.upperFirstLetter(temp);
            String varNameTemp = temp;
            String returnType = "public ";
            if ("limitStart".equals(temp) || "limitEnd".equals(temp)) {
                returnType = "public ";
            }
            if (!"limitStart".equals(temp) && !"limitEnd".equals(temp)) {
                out.println("    " + returnType + type + " get" + classNameTemp + "() {");
                //                out.println("    {");
                out.println("        return " + varNameTemp + ";");
                out.println("    }");
                out.println("");
            }
            if (!"oredCriteria".equals(temp)) {
                out.println("    " + returnType + "void set" + classNameTemp + "(" + type + " " + varNameTemp + ") {");
                //                out.println("    {");
                out.println("        this." + varNameTemp + " = " + varNameTemp + ";");
                out.println("    }");
                out.println();
            }
        }

        out.println("    public void or(Criteria criteria) {");
        //        out.println("    {");
        out.println("        oredCriteria.add(criteria);");
        out.println("    }");
        out.println();

        out.println("    public Criteria or() {");
        //        out.println("    {");
        out.println("        Criteria criteria = createCriteriaInternal();");
        out.println("        oredCriteria.add(criteria);");
        out.println("        return criteria;");
        out.println("    }");
        out.println();

        out.println("    public Criteria createCriteria() {");
        //        out.println("    {");
        out.println("        Criteria criteria = createCriteriaInternal();");
        out.println("        if (oredCriteria.isEmpty()) {");
        //        out.println("        {");
        out.println("            oredCriteria.add(criteria);");
        out.println("        }");
        out.println("        return criteria;");
        out.println("    }");
        out.println();

        out.println("    protected Criteria createCriteriaInternal() {");
        //        out.println("    {");
        out.println("        Criteria criteria = new Criteria();");
        out.println("        return criteria;");
        out.println("    }");
        out.println();

        out.println("    public void clear() {");
        //        out.println("    {");
        out.println("        oredCriteria.clear();");
        out.println("        orderByClause = null;");
        out.println("        distinct = false;");
        out.println("        limitStart = -1;");
        out.println("        limitEnd = -1;");
        out.println("    }");
        out.println();

        out.println("    /**");
        out.println("     * 设置分页");
        out.println("     * @param pageIndex 页码{1,2,3,4,....},当pageIndex=0时，不分页。所以使用此方法分页pageIndex>0,pageSize>0");
        out.println("     * @param pageSize 每页显示条数");
        out.println("     */");
        out.println("    public void setPage(int pageIndex, int pageSize) {");
        //        out.println("    {");
        out.println("        this.setLimitStart((pageIndex - 1) * pageSize);");
        out.println("        this.setLimitEnd(pageSize);");
        out.println("    }");
        out.println();

    }

    /**
     * 获取要打印的类型
     * 
     * @param param
     * @return
     */
    public static String createBaseEntity(String param) {
        String type = "String";
        if ("distinct".equals(param)) {
            type = "boolean";
        } else if ("limitStart".equals(param) || "limitEnd".equals(param)) {
            type = "Integer";
        } else if ("oredCriteria".equals(param)) {
            type = "List<Criteria>";
        } else if ("columnName".equals(param)) {
            type = "ColumnName";
        }
        return type;
    }

    /**
     * 构建xxxExample中抽象类，涉及到主要的sql逻辑判断
     * 
     * @param param
     */
    public static void createAbstract(PrintWriter out, List<TableProperties> list) {
        out.println("    protected abstract static class GeneratedCriteria  implements Serializable {");
        //        out.println("    {");
        out.println();
        out.println("        private static final long serialVersionUID = " + RandomUtils.strLength10() + RandomUtils.longLength6() + "L;");
        out.println();
        out.println();
        out.println("        protected List<Criterion> criteria;");
        out.println();

        out.println("        protected GeneratedCriteria() {");
        //        out.println("         {");
        out.println("            super();");
        out.println("            this.criteria = new ArrayList<Criterion>();");
        out.println("        }");
        out.println();

        out.println("        public boolean isValid() {");
        //        out.println("         {");
        out.println("            return !this.criteria.isEmpty();");
        out.println("        }");
        out.println();

        out.println("        public List<Criterion> getAllCriteria() {");
        //        out.println("         {");
        out.println("            return this.criteria;");
        out.println("        }");
        out.println();

        out.println("        public List<Criterion> getCriteria() {");
        //        out.println("         {");
        out.println("            return this.criteria;");
        out.println("        }");
        out.println();

        out.println("        protected void addCriterion(String condition) {");
        //        out.println("         {");
        out.println("            if (condition == null) {");
        //        out.println("             {");
        out.println("                throw new RuntimeException(\"Value for condition cannot be null\");");
        out.println("            }");
        out.println("            this.criteria.add(new Criterion(condition));");
        out.println("        }");
        out.println();

        out.println("        protected void addCriterion(String condition, Object value, String property) {");
        //        out.println("         {");
        out.println("            if (value == null) {");
        //        out.println("             {");
        out.println("                throw new RuntimeException(\"Value for \" + property + \" cannot be null\");");
        out.println("            }");
        out.println("            this.criteria.add(new Criterion(condition, value));");
        out.println("        }");
        out.println();

        out.println("        protected void addCriterion(String condition, Object value1, Object value2, String property) {");
        //        out.println("         {");
        out.println("            if (value1 == null|| value2 == null) {");
        //        out.println("             {");
        out.println("                throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");");
        out.println("            }");
        out.println("            this.criteria.add(new Criterion(condition, value1, value2));");
        out.println("        }");
        out.println();

        for (TableProperties tableProperties : list) {
            String columnName = tableProperties.getName();
            String classNameTemp = MyUtils.formatToClassName(tableProperties.getName());
            String type = tableProperties.getDateType();
            String realType = "String";
            if ("date".equals(type) || "datetime".equals(type) || "timestamp".equals(type)) {
                realType = "Date";
            }

            out.println("        public Criteria and" + classNameTemp + "IsNull() {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " is null\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "IsNotNull() {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " is not null\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "EqualTo(" + realType + " value) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " = \", value, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "NotEqualTo(" + realType + " value) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " <> \", value, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "GreaterThan(" + realType + " value) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " > \", value, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "GreaterThanOrEqualTo(" + realType + " value) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " >= \", value, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "LessThan(" + realType + " value) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " < \", value, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "LessThanOrEqualTo(" + realType + " value) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " <= \", value, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            if (!"date".equals(type) && !"datetime".equals(type) && !"timestamp".equals(type)) {
                out.println("        public Criteria and" + classNameTemp + "Like(" + realType + " value) {");
                //                out.println("         {");
                out.println("            StringBuilder temp_value = new StringBuilder(\"%\");");
                out.println("            temp_value.append(value);");
                out.println("            temp_value.append(\"%\");");
                out.println("            this.addCriterion(\"" + columnName + " like \", temp_value.toString(), \"" + columnName + "\");");
                out.println("            return (Criteria) this;");
                out.println("        }");
                out.println();

                out.println("        public Criteria and" + classNameTemp + "NotLike(" + realType + " value) {");
                //                out.println("         {");
                out.println("            StringBuilder temp_value = new StringBuilder(\"%\");");
                out.println("            temp_value.append(value);");
                out.println("            temp_value.append(\"%\");");
                out.println("            this.addCriterion(\"" + columnName + " not like \", temp_value.toString(), \"" + columnName + "\");");
                out.println("            return (Criteria) this;");
                out.println("        }");
                out.println();
            }
            out.println("        public Criteria and" + classNameTemp + "In(List<" + realType + "> values) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " in \", values, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "NotIn(List<" + realType + "> values) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " not in \", values, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "Between(" + realType + " value1, " + realType + " value2) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " between \", value1,value2, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();

            out.println("        public Criteria and" + classNameTemp + "NotBetween(" + realType + " value1, " + realType + " value2) {");
            //            out.println("         {");
            out.println("            this.addCriterion(\"" + columnName + " not between \", value1,value2, \"" + columnName + "\");");
            out.println("            return (Criteria) this;");
            out.println("        }");
            out.println();
        }
        out.println("    }");
        out.println();
    }

    /**
     * 构建xxxExample中ColumnName内部类，供动态查询属性列调用
     * 
     * @param param
     */
    public static void createColumnNameClass(PrintWriter out, List<TableProperties> list) {
        out.println();
        out.println("    public static class ColumnName implements Serializable {");
        //        out.println("    {");
        out.println();
        out.println("        private static final long serialVersionUID = 1L;");
        out.println();
        out.println("        public String queryColumnNameStr=\"\";");
        out.println();
        for (TableProperties tableProperties : list) {
            String columnName = tableProperties.getName();
            String classNameTemp = MyUtils.formatToClassName(tableProperties.getName());
            out.println("        public ColumnName add" + classNameTemp + "Column() {");
            //            out.println("        {");
            out.println("            if(this.queryColumnNameStr.length()==0) {");
            out.println("                this.queryColumnNameStr=\"" + columnName + "\";");
            out.println("            } else {");
            out.println("                this.queryColumnNameStr=this.queryColumnNameStr+\"," + columnName + "\";");
            out.println("            }");
            out.println("            return this;");
            out.println("        }");
            out.println();
        }
        out.println("    }");
        out.println();
    }
}
