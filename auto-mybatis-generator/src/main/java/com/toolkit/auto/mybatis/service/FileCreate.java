package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.toolkit.auto.mybatis.dao.DbBean;
import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.entity.TableProperties;
import com.toolkit.auto.mybatis.utils.Data;
import com.toolkit.auto.mybatis.utils.MyUtils;

public class FileCreate {
    private static String tempPath = "";

    /**
     * 生成代码框架信息,生成代码放置位置 1.需要setPackage_base(String package_name) 2.自定义目录方式可以是项目名称 3.生成的目录结构是从src开始(包括src)
     * 
     * @param info 框架信息
     * @param time {1:生成代码放置到项目自定义目录下,2:生成代码放置到根目录下}
     * @return
     */
    public static boolean start(FrameInfo info, Integer time) {
        try {
            if (time > 2 || time < 1) {
                return false;
            }
            String base_schema = info.getSchema();//数据库名
            String base_bao = info.getPackage_base();//业务代码所在包
            //设置数据库名称
            String[] dbNames = base_schema.split(",");

            PrintWriter out = new PrintWriter("表列表一览.txt");
            for (String dbName : dbNames) {
                if (dbName.contains("_") && !info.isDelPrefix()) {
                    dbName = dbName.substring(dbName.lastIndexOf("_") + 1);
                } else if (dbName.contains("_") && info.isDelPrefix()) {
                    dbName = dbName.replace("_", "");
                }
                info.setPackage_base(base_bao);
                //info.setEncode("GBK");
                info.setEncode("UTF-8");
                DbBean.setMysql_url("jdbc:mysql://" + info.getIp() + ":" + info.getPort() + "/" + info.getSchema());
                DbBean.setMysql_user(info.getUser());
                DbBean.setMysql_passwd(info.getPassword());
                if (time == 1) {
                    if (null == info.getPath_base() || null == info.getProject_name()) {
                        return false;
                    }
                }
                /**
                 * 设置包名
                 */
                formatFrameInfoToRootDir(info);
                /**
                 * 创建实体类的路径
                 */
                createLocalPath(info.getPath_entity());
                /**
                 * 创建配置文件的路径
                 */
                createLocalPath(info.getPath_core_config());
                /**
                 * 创建mapper 的路径
                 */
                createLocalPath(info.getPath_core_mapper());
                /**
                 * 创建service 的路径
                 */
                createLocalPath(info.getPath_core_api());
                /**
                 * 创建 serviceImpl 的路径
                 */
                createLocalPath(info.getPath_core_impl());
                
                MySql mySql = new MySql();
                List<String> list = new ArrayList<String>();
                System.out.println("".equals(StringUtils.trimAllWhitespace(info.getTables())));
                if ("".equals(StringUtils.trimAllWhitespace(info.getTables()))) {
                    list = mySql.listAllTables(info.getSchema());
                } else {
                    String[] tbNames = info.getTables().split(",");
                    for (String t : tbNames) {
                        list.add(t + "#####");
                    }
                }
                //                info.setSchema(dbName);
                out.println("数据库->" + dbName + "中表如下所示:");//生成表列表
                for (String string : list) {

                    String table_name = string.substring(0, string.indexOf("#####"));
                    String table_comment = string.substring(string.indexOf("#####") + 5);
                    out.println("	" + table_name + "	" + table_comment);//生成表列表

                    List<TableProperties> list2 = mySql.listTableProperties(info.getSchema(), table_name);
                    if (null == list2 || list2.size() < 1) {
                        System.out.println(">>> Table_Name (" + table_name + ") isn't exist !");
                        continue;
                    }
                    //生成实体类[数据库表名.java--数据库表名.java--数据库表名Key.java]
                    EntityFile.createFile(table_name, table_comment, list2, info);
                    //生成Mapper件:[数据库表名-Mapper.java]
                    CoreMapper.createExtendsFile(table_name, list2, info);
                    //生成sql配置文件:[数据库表名-Mapper.xml]
                    CoreConfigFile.createFile(table_name, list2, info);
                    //生成接口类文件:
                    InterfaceFile.createServiceExtendFile(table_name, table_comment, list2, info);
                    //生成接口实现类文件
                    Impl.createGenericityServiceImplFile(table_name, list2, info);
                }
               // HessianXmlConfigFile.createDubboConfigFile(list, info);
                out.println();
            }

            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成代码框架信息 到根目录autoMybatis 下
     * 
     * @param info
     */
    private static void formatFrameInfoToRootDir(FrameInfo info) {
        String base_path = info.getPath_base();
        String project_name = info.getProject_name();
        if ("".equals(base_path)) {
            base_path = Data.getProjectLocalPath();
        }
        String temp_package = info.getPackage_base();//.substring(0, info.getPackage_base().lastIndexOf("."));
        String temp_path = genePath(temp_package);


        //实体类包 package com.yw.entity.generator.xxx
        info.setPackage_entity("com." + temp_package + ".entity");
        //dao层mapper包,query与core公用, package com.yw.dao.generator.xxx
        info.setPackage_mapper("com." + temp_package + ".dao");
        //core api 接口包,package com.yw.api.generator.core.xxx
        info.setPackage_api_core("com." + temp_package + ".service");
        //core 接口实现包,package com.yw.core.generator.xxx.impl
        info.setPackage_impl_core("com." + temp_package + ".service.impl");

        //entity(实体类)包路径
        info.setPath_entity(base_path  + "/" + project_name + "/src/main/java/com/" + temp_path +"entity/");

        info.setPath_core_mapper(base_path  + "/" + project_name + "/src/main/java/com/" + temp_path + "dao/");

        info.setPath_core_api(base_path  + "/" + project_name + "/src/main/java/com/" + temp_path + "service/");

        info.setPath_core_impl(base_path  + "/" + project_name + "/src/main/java/com/" + temp_path + "service/impl/");

        info.setPath_core_config(base_path + "/" +project_name + "/src/main/resources/com/" + temp_path + "mapper/");
        //core sqlmap包路径
        String tmp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
        info.setPackage_config("dao.sqlmap." + tmp);
        info.setPath_config(base_path +  "/" + project_name + "/src/main/resources/com/" + temp_path);
        //core hessian service 配置路径
        info.setPath_core_hessian_config(base_path + project_name + "/" + project_name + "-service/src/main/resources/");
        System.out.println("--------------"+new Gson().toJson(info)+"---------------");
        
        //        Gson gson = new Gson();
        //        Data.writeToSys("mybatis-5623487", gson.toJson(info));
    }

    private static String genePath(String src) {
        String[] tmp = src.split("\\.");
        String path = "";
        for (String string : tmp) {
            path += string + "/";
        }
        return path;
    }

    /**
     * 创建本地目录
     * 
     * @param path 目录
     * @return true-成功 false-失败
     */
    public static boolean createLocalPath(String path) throws Exception {
        java.io.File f = new java.io.File(path);
        if (f.exists()) {
            System.out.println("Local directory is already exist:" + path);
            return true;
        }
        if (f.mkdirs()) {
            System.out.println("create Local directory success:" + path);
            return true;
        } else {
            System.out.println("create Local directory fail:" + path);
            return false;
        }
    }

    /**
     * 删除本地目录及该目录下所有文件
     * 
     * @param path 目录
     * @return true-成功 false-失败
     */
    public static boolean deleteLocalPath(String path) {
        if (path == null || path.equals("")) {
            return false;
        }
        java.io.File f = new java.io.File(path);
        if (!f.exists()) {
            System.out.println("Local directory is not exist:" + path);
            return true;
        }
        String[] tmp = f.list();
        for (int i = 0; tmp != null && i < tmp.length; i++) {
            deleteLocalPath(path + "/" + tmp[i]);
        }
        if (f.delete()) {
            System.out.println("delete Local directory success:" + path);
            return true;
        } else {
            System.out.println("delete Local directory fail:" + path);
            return false;
        }
    }

    public static void createFile(List<String> list, FrameInfo info, String path) throws Exception {
        PrintWriter out = null;
        try {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(path + "mybatis-" + temp + ".xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">");
            out.println("<configuration>");
            out.println("  <mappers>");
            for (String string : list) {
                String table_name = string.substring(0, string.indexOf("#####"));
                out.println("    <mapper resource=\"dao/generator/" + temp + "/" + MyUtils.formatToClassName(table_name) + "-Mapper.xml\" /> ");
            }
            out.println("  </mappers>");
            out.println("</configuration>");
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
