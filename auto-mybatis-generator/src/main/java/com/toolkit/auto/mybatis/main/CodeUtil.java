package com.toolkit.auto.mybatis.main;


import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.service.FileCreate;

public class CodeUtil {

    //    String path_base = "E:\\U\\jcode\\code\\yw-project";
    //    FrameInfo frameInfo = new FrameInfo();
    //    frameInfo.setPath_base(path_base);//工程项目所在目录，如yw-project所在目录
    //    frameInfo.setProject_name("yw-pay");//子系统所属项目，如支付子系统所在项目yw-pay
    //    frameInfo.setPackage_base("pay");//业务代码所在包名，如支付子系统代码所在包为com.offen.pay,基础系统代码所在包名为com.offen.base
    //    frameInfo.setIp("192.168.1.191");//数据库地址
    //    frameInfo.setPort("3306");// 数据库端口
    //    frameInfo.setUser("root");// 数据库用户
    //    frameInfo.setPassword("123456");// 数据库密码
    //    frameInfo.setSchema("yw_youwe");// 数据库名
    //    frameInfo.setTables("member_growth,growth_rule,growth_level,growth_log");// 表名(为空时,根据库中所有表生成代码)
    //    //生成代码框架信息,放置到项目下面
    //    if (FileCreate.start(frameInfo, 1)) {
    //        System.out.println("成功生成代码框架信息,放置到项目下面:"+frameInfo.getPath_base()+"/"+frameInfo.getProject_name());
    //    } 
	
//	public static void main(String[] args) {
//
//        String path_base = "/Users/leic/hepbum/temp/autocode";
//        FrameInfo frameInfo = new FrameInfo();
//        frameInfo.setPath_base(path_base);//工程项目所在目录，如yw-project所在目录
//        frameInfo.setProject_name("hy-security");//子系统所属项目，如支付子系统所在项目yw-pay
//        frameInfo.setPackage_base("com.hy.security");//业务代码所在包名，如支付子系统代码所在包为com.offen.pay,基础系统代码所在包名为com.offen.base
//        frameInfo.setIp("192.168.1.232");//数据库地址
//        frameInfo.setPort("3306");// 数据库端口
//        frameInfo.setUser("root");// 数据库用户
//        frameInfo.setPassword("root");// 数据库密码
//        frameInfo.setSchema("zc-security");// 数据growth_level库名
////        String tables = "nation,ss_act_user,ss_content,ss_elfinder,ss_elfinder_checker,ss_permission,ss_role, ss_role_permission,ss_user,ss_user_organization,ss_user_permission,ss_user_role,ss_user_shortcut,sys_dict_item,sys_dict_type,sys_log,sys_organization,sys_system,tc_car_operate_type,tc_car_type,tc_car_type_parameter,tc_city,tc_operate_type,tc_product,tc_product_category,td_business,td_butler_task,td_car_operate,td_car_operate_log,td_car_operate_parameter,td_item,td_item_view_detail,td_marketing,td_member,td_member_balance,td_member_balance_log,td_member_power,td_member_power_log,td_order,td_order_status_log,td_refund,td_suggest,td_wechat_notice,td_wechat_notice_template,tr_product_category_car";//app_info,file_upload_info,business_area_geo,community_area_geo,article,netPhoto_album,region_province,region_county,region_city,region_town,region_village,business_area,system_config,sys_permission,enterpriseGeo_record,customerGeo_record,certification,constant_config,growth,growth_level,growth_record,level_right,yw_account,ywAccount_record,ywCAccount_record,rule_engine";
//        String tables = "td_api,td_menu,td_operation,td_organization,td_role,td_system,td_user,td_user_city";
//        frameInfo.setTables(tables.replace(" ", ""));// 表名(为空时,根据库中所有表生成代码)
//        //生成代码框架信息.1:放置到项目下面;2:生成到 com.toolkit.auto.mybatis 目录下
//        if (FileCreate.start(frameInfo, 1)) {
//            System.out.println("成功生成代码框架信息,放置到项目下面:" + frameInfo.getPath_base() + "/" + frameInfo.getProject_name());
//        } else {
//            System.out.println("生成代码框架信息失败!");
//        }
//    }
	
    public static void main(String[] args) {
    
        String path_base = "D:\\workspaces";
        FrameInfo frameInfo = new FrameInfo();
        frameInfo.setPath_base(path_base);//工程项目所在目录，如yw-project所在目录
        frameInfo.setProject_name("hy-function_1.7");//子系统所属项目，如支付子系统所在项目yw-pay
        frameInfo.setPackage_base("hy.gcar");//业务代码所在包名，如支付子系统代码所在包为com.offen.pay,基础系统代码所在包名为com.offen.base
        frameInfo.setIp("192.168.1.232");//数据库地址
        frameInfo.setPort("3306");// 数据库端口
        frameInfo.setUser("root");// 数据库用户
        frameInfo.setPassword("root");// 数据库密码
        frameInfo.setSchema("g-car2");// 数据growth_level库名
//        String tables = "nation,ss_act_user,ss_content,ss_elfinder,ss_elfinder_checker,ss_permission,ss_role, ss_role_permission,ss_user,ss_user_organization,ss_user_permission,ss_user_role,ss_user_shortcut,sys_dict_item,sys_dict_type,sys_log,sys_organization,sys_system,tc_car_operate_type,tc_car_type,tc_car_type_parameter,tc_city,tc_operate_type,tc_product,tc_product_category,td_business,td_butler_task,td_car_operate,td_car_operate_log,td_car_operate_parameter,td_item,td_item_view_detail,td_marketing,td_member,td_member_balance,td_member_balance_log,td_member_power,td_member_power_log,td_order,td_order_status_log,td_refund,td_suggest,td_wechat_notice,td_wechat_notice_template,tr_product_category_car";//app_info,file_upload_info,business_area_geo,community_area_geo,article,netPhoto_album,region_province,region_county,region_city,region_town,region_village,business_area,system_config,sys_permission,enterpriseGeo_record,customerGeo_record,certification,constant_config,growth,growth_level,growth_record,level_right,yw_account,ywAccount_record,ywCAccount_record,rule_engine";
        String tables = "td_member_status_log";
        frameInfo.setTables(tables.replace(" ", ""));// 表名(为空时,根据库中所有表生成代码)
        //生成代码框架信息.1:放置到项目下面;2:生成到 com.toolkit.auto.mybatis 目录下
        if (FileCreate.start(frameInfo, 1)) {
            System.out.println("成功生成代码框架信息,放置到项目下面:" + frameInfo.getPath_base() + "/" + frameInfo.getProject_name());
        } else {
            System.out.println("生成代码框架信息失败!");
        }
    }
}
