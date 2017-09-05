package com.toolkit.auto.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.utils.MyUtils;

/**
 * 生成hessianXml配置信息
 * 
 * @author Administrator
 * 
 */
public class HessianXmlConfigFile {
    /**
     * 生成query hessian 配置XML文件
     * 
     * @param list
     * @param info
     * @throws Exception
     */
    public static void createQueryHessianFile(List<String> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(info.getPath_query_hessian_config() + "hessian-query-" + temp + "-service.xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
            out.println("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"");
            out.println("    xmlns:context=\"http://www.springframework.org/schema/context\"");
            out.println("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans");
            out.println("    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd");
            out.println("    http://www.springframework.org/schema/context");
            out.println("    http://www.springframework.org/schema/context/spring-context-3.0.xsd\">");
            String beanClassName = "";
            String beanIdName = "";
            for (String string : list) {
                String table_name = string.substring(0, string.indexOf("#####"));
                String[] classNameArgs = table_name.split("_");
                if (classNameArgs.length > 0) {
                    beanClassName = MyUtils.formatToClassName(table_name);
                    beanIdName = MyUtils.formatToVarName(table_name);
                } else {
                    continue;
                }
                out.println("");
                out.println("    <bean id=\"" + beanIdName + "QueryService\" class=\"" + info.getPackage_impl_query() + "." + beanClassName + "QueryServiceImpl\" />");
                out.println("    <bean name=\"/" + beanIdName + "QueryService\" class=\"org.springframework.remoting.caucho.HessianServiceExporter\">");
                out.println("        <property name=\"service\" ref=\"" + beanIdName + "QueryService\" />");
                out.println("        <property name=\"serviceInterface\" value=\"" + info.getPackage_api_query() + "." + beanClassName + "QueryService\"/>");
                out.println("    </bean>");
            }
            out.println("</beans>");
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
     * 生成core hessian 配置XML文件
     * 
     * @param list
     * @param info
     * @throws Exception
     */
    public static void createCoreHessianFile(List<String> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(info.getPath_core_hessian_config() + "hessian-core-" + temp + "-service.xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
            out.println("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"");
            out.println("    xmlns:context=\"http://www.springframework.org/schema/context\"");
            out.println("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans");
            out.println("    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd");
            out.println("    http://www.springframework.org/schema/context");
            out.println("    http://www.springframework.org/schema/context/spring-context-3.0.xsd\">");
            String beanClassName = "";
            String beanIdName = "";
            for (String string : list) {
                String table_name = string.substring(0, string.indexOf("#####"));
                String[] classNameArgs = table_name.split("_");
                if (classNameArgs.length > 0) {
                    beanClassName = MyUtils.formatToClassName(table_name);
                    beanIdName = MyUtils.formatToVarName(table_name);
                } else {
                    continue;
                }
                out.println("");
                out.println("    <bean id=\"" + beanIdName + "CoreService\" class=\"" + info.getPackage_impl_core() + "." + beanClassName + "CoreServiceImpl\"/>");
                out.println("    <bean name=\"/" + beanIdName + "CoreService\" class=\"org.springframework.remoting.caucho.HessianServiceExporter\">");
                out.println("        <property name=\"service\" ref=\"" + beanIdName + "CoreService\" />");
                out.println("        <property name=\"serviceInterface\" value=\"" + info.getPackage_api_core() + "." + beanClassName + "CoreService\"/>");
                out.println("    </bean>");
            }
            out.println("</beans>");
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
     * 生成query client hessian 配置XML文件
     * 
     * @param list
     * @param info
     * @throws Exception
     */
    public static void createQueryClientHessianFile(List<String> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(info.getPath_hessian_client_config() + "hessian-query-" + temp + "-client.xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
            out.println("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"");
            out.println("    xmlns:context=\"http://www.springframework.org/schema/context\"");
            out.println("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans");
            out.println("    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd");
            out.println("    http://www.springframework.org/schema/context");
            out.println("    http://www.springframework.org/schema/context/spring-context-3.0.xsd\">");
            String beanClassName = "";
            String beanIdName = "";
            for (String string : list) {
                String table_name = string.substring(0, string.indexOf("#####"));
                String[] classNameArgs = table_name.split("_");
                if (classNameArgs.length > 0) {
                    beanClassName = MyUtils.formatToClassName(table_name);
                    beanIdName = MyUtils.formatToVarName(table_name);
                } else {
                    continue;
                }
                out.println("");
                out.println("    <bean id=\"" + beanIdName + "QueryServiceClient\" class=\"org.springframework.remoting.caucho.HessianServiceExporter\">");
                out.println("        <property name=\"serviceUrl\">");
                out.println("            <value>http://192.168.1.111:6060/iquery/hessian/" + beanIdName + "QueryService</value>");
                out.println("        </property>");
                out.println("        <property name=\"serviceInterface\" value=\"" + info.getPackage_api_query() + "." + beanClassName + "QueryService\"/>");
                out.println("    </bean>");
            }
            out.println("</beans>");
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
     * 生成core client hessian 配置XML文件
     * 
     * @param list
     * @param info
     * @throws Exception
     */
    public static void createCoreClientHessianFile(List<String> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(info.getPath_hessian_client_config() + "hessian-core-" + temp + "-client.xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
            out.println("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"");
            out.println("    xmlns:context=\"http://www.springframework.org/schema/context\"");
            out.println("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans");
            out.println("    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd");
            out.println("    http://www.springframework.org/schema/context");
            out.println("    http://www.springframework.org/schema/context/spring-context-3.0.xsd\">");
            String beanClassName = "";
            String beanIdName = "";
            for (String string : list) {
                String table_name = string.substring(0, string.indexOf("#####"));
                String[] classNameArgs = table_name.split("_");
                if (classNameArgs.length > 0) {
                    beanClassName = MyUtils.formatToClassName(table_name);
                    beanIdName = MyUtils.formatToVarName(table_name);
                } else {
                    continue;
                }
                out.println("");
                out.println("    <bean id=\"" + beanIdName + "CoreServiceClient\" class=\"org.springframework.remoting.caucho.HessianServiceExporter\">");
                out.println("        <property name=\"serviceUrl\">");
                out.println("            <value>http://192.168.1.111:6060/icore/hessian/" + beanIdName + "CoreService</value>");
                out.println("        </property>");
                out.println("        <property name=\"serviceInterface\" value=\"" + info.getPackage_api_core() + "." + beanClassName + "CoreService\"/>");
                out.println("    </bean>");
            }
            out.println("</beans>");
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
     * 生成Dubbo 配置XML文件
     * 
     * @param list
     * @param info
     * @throws Exception
     */
    public static void createDubboConfigFile(List<String> list, FrameInfo info) throws Exception {
        PrintWriter out = null;
        try {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(info.getPath_core_hessian_config() + "dubbo-auto-provide.xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
            out.println("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\"");
            //            out.println("    xmlns:context=\"http://www.springframework.org/schema/context\"");
            out.println("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans");
            out.println("    http://www.springframework.org/schema/beans/spring-beans.xsd");
            out.println("    http://code.alibabatech.com/schema/dubbo");
            out.println("    http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">");
            out.println("    <!-- 以下配置由自动化生成工具生成 -->");
            out.println("    <!-- 提供方应用信息，用于计算依赖关系 -->");
            out.println("    <dubbo:application name=\"" + temp + "_provider\" />");
            out.println("    <!-- 使用zookeeper注册中心暴露服务地址 -->");
            out.println("    <dubbo:registry address=\"zookeeper://dbzk.999care.com:2181\" />");
            out.println("    <!-- 用dubbo协议在20880端口暴露服务 -->");
            out.println("    <dubbo:protocol name=\"dubbo\" port=\"" + info.getDubbo_port() + "\" />");
            out.println("    <!-- 设置默认超时为3秒 -->");
            out.println("    <dubbo:provider timeout=\"3000\"/>");
            String beanClassName = "";
            String beanIdName = "";
            for (String string : list) {
                String table_name = string.substring(0, string.indexOf("#####"));
                String[] classNameArgs = table_name.split("_");
                if (classNameArgs.length > 0) {
                    beanClassName = MyUtils.formatToClassName(table_name);
                    beanIdName = MyUtils.formatToVarName(table_name);
                } else {
                    continue;
                }
                out.println("");
                out.println("    <!-- 服务 -->");
                out.println("    <bean id=\"" + beanIdName + "Service\" class=\"" + info.getPackage_impl_core() + "." + beanClassName + "ServiceImpl\"/>");
                out.println("    <!-- 声明需要暴露的服务接口 -->");
                out.println("    <dubbo:service ref=\"" + beanIdName + "Service\"  interface=\"" + info.getPackage_api_core() + "." + beanClassName + "Service\"/>");
            }
            out.println("</beans>");
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
