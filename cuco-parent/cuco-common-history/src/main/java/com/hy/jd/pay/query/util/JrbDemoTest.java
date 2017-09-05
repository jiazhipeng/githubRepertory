//package com.hy.jd.pay.query.util;
//
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 注意：本demo只做演示，处理正常流程，请勿直接使用
// * 网关退款测试商户号1001  密钥test
// * 京东支付测试商户号22294531  密钥 1qa2ws3ed~!@360080000222945314
// */
//public class JrbDemoTest {
//    // 交易查询
//   @Test
//    public void query() throws Exception {
//
//	   JDPayQueryUtils.transactionQuery("jcgs20161018165119627");
//    }
//
//    // 退款
//   //@Test
//    public void refundTest() throws Exception {
////        Map<String, String> dataMap = new HashMap<String, String>();
////        dataMap.put(API_DATA_VERSION, "1.0.0");// 无需修改
////        dataMap.put(API_DATA_MERCHANT, "1001");// 根据业务不一样商户号不一样
////        dataMap.put(API_DATA_TYPE, "R");// 无需修改
////        dataMap.put(API_DATA_TRADE, "test001211");// 同一商户号不能重复
////        dataMap.put(API_DATA_ORDER, "201601203245027");// 消费时的TRADE值
////        dataMap.put(API_DATA_AMOUNT, "1");
////        dataMap.put(API_DATA_CURRENCY, "CNY");// 无需修改
////
////        dataMap.put(API_DATA_DATETIME, "2015-08-04 00:04:13 0");// yyyy-MM-dd HH:mm:ss S 格式
////        dataMap.put(API_DATA_NOTE, "test");// 备注
////        dataMap.put(API_DATA_NOTICE, "http://api.wangyin.com:8888/test/utf8.htm");// 异步通知地址
//
//       // JrbDemo.process(dataMap, "UTF-8", KEY, URL);// KEY,URL根据需要修改
//    }
//
//}
