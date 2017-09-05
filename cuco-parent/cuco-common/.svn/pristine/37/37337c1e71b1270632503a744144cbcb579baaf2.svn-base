package cn.cuco.pay.jd.util;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.cuco.constant.Constant;

/**
 * 京东查询接口
 */
public class JDPayQueryUtils {
	private static Logger logger = LoggerFactory.getLogger(JDPayQueryUtils.class);

    public static final String URL = "https://tmapi.jdpay.com/jd.htm";
    public static final String KEY = "test";// 密钥
   // public static final String METHODA = "A";// 直连
  public static final String METHODB = "B";// 跳转 目前不提供


    public static final String API_DATA_VERSION = "VERSION"; // API 数据参数 版本号
    public static final String API_DATA_MERCHANT = "MERCHANT"; // API 数据参数 商户号
    public static final String API_DATA_TYPE = "TYPE"; // API 数据参数 交易类型
    public static final String API_DATA_TRADE = "TRADE"; // API 数据参数 交易号
    public static final String API_DATA_AMOUNT = "AMOUNT"; // API 数据参数 交易金额/钱包余额 单位 分
    public static final String API_DATA_CURRENCY = "CURRENCY"; // API 数据参数 交易币种
    public static final String API_DATA_USER = "USER"; // API 数据参数 交易用户
    public static final String API_DATA_DATETIME = "DATETIME"; // API 数据参数 交易时间
    public static final String API_DATA_NOTE = "NOTE"; // API 数据参数 交易备注
    public static final String API_DATA_ORDER_TRADE = "ORDER_TRADE"; // API 数据参数 订单号
    public static final String API_DATA_STATUS = "STATUS"; // API 数据参数 交易状态
    public static final String API_DATA_CODE = "CODE"; // API 数据参数 返回码
    public static final String API_DATA_DESC = "DESC"; // API 数据参数 返回信息
    public static final String API_DATA_URL = "URL"; // API 数据参数 返回地址
    public static final String API_DATA_NOTICE = "NOTICE"; // API 数据参数 通知地址
    public static final String API_DATA_ORDER_INFO = "ORDER_INFO"; // API 数据参数 订单信息
    public static final String API_DATA_METHOD = "METHOD"; // API 数据参数 接入方式
    public static final String API_DATA_ORDER = "ORDER"; // API 数据参数 交易号
    public static final String API_DATA_BUSINESS_TYPE = "BUSINESS_TYPE"; // API 数据参数 业务分类码/行业分类码
    public static final String API_DATA_TRADE_SOURCE = "TRADE_SOURCE"; // API 数据参数 交易来源码（OUT_PC/OUT_APP）
    public static final String API_DATA_EXTEDN_PARAMS = "extend_params"; // API 数据参数 订单扩展信息，JSON格式
	
	

    /**
     * 向API发送请求流程
     *
     * @param requestMap 报文主体数据
     * @param charset
     * @param key     md5加密用的key，api提供
     * @param url
     * @throws Exception
     * 注意返回的数据金额是分
     */
    public static String transactionQuery(String transactionNO) throws Exception {
        
    	String key = Constant.JDPAYCONFIG.getProperty("key");
    	String charset = "utf-8";
        String v_mid = Constant.JDPAYCONFIG.getProperty("v_mid");
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put(API_DATA_VERSION, "1.0.0");// 无需修改
        requestMap.put(API_DATA_MERCHANT, v_mid);// 根据业务不一样商户号不一样
        requestMap.put(API_DATA_TYPE, "Q");// 无需修改
        requestMap.put(API_DATA_TRADE, transactionNO);// 查询的交易号
        logger.info("原始数据: charset:"+charset+", key: "+key+", url: "+URL+", requestMap: "+JSON.toJSONString(requestMap));

        
        logger.info("=================1.准备http post请求数据===================");
        //1.准备请求数据
        List<NameValuePair> requestData = prepareRequestData(requestMap, key, charset);
        logger.info("requestData: "+JSONObject.toJSONString(requestData));

        logger.info("=================2.发送http post请求===================");
        //2.发送http post请求
        HttpResponse response = doHttpPost(requestData, URL, charset, 10000);

        logger.info("=================3.处理http response===================");
        //3.处理http response
        Map<String, String> responseMap = parseResponse(response,charset);

        logger.info("response map: " + JSONObject.toJSONString(responseMap));
        String data = new String(Base64.decodeBase64(responseMap.get("DATA")));
        logger.info("response data: " + data);
        return data;
    }

    /**
     * 准备http post请求数据
     * @param dataMap
     * @param key
     * @param charset
     * @return
     * @throws Exception
     */
    private static List<NameValuePair> prepareRequestData(Map<String, String> dataMap, String key, String charset) throws Exception {
        //将map中的数据转换成json格式的string
        String json = JSONObject.toJSONString(dataMap);
        String requestData =  Base64.encodeBase64String(json.getBytes(charset));
        //生成MD5签名
       // String requestSign = MD5.md5(requestData, key, true);
        String requestSign = RelyMD5.MD5Encode(requestData+key).toUpperCase();

//        logger.info("prepareRequestData ----- request data: " + requestData);
//        logger.info("prepareRequestData ----- request sign: " + requestSign);

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("CHAR", charset));
        list.add(new BasicNameValuePair("DATA", requestData));
        list.add(new BasicNameValuePair("SIGN", requestSign));

        return list;
    }

    /**
     * 发送http post请求
     * @param requestList
     * @param url
     * @param charset
     * @param timeout
     * @return
     * @throws Exception
     */
    private static HttpResponse doHttpPost(List<NameValuePair> requestList, String url, String charset, int timeout) throws Exception {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse response = null;

        try {
            logger.info("doHttpPost ----- url: " + url + ", timeout: " + timeout + ")");

            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);

            //设置超时
            HttpParams httpParams = httpClient.getParams();
            httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
            httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);

            //设置消息主体
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(requestList, charset);
            httpPost.setEntity(urlEncodedFormEntity);


            //执行post请求
            response = httpClient.execute(httpPost);
            logger.info("doHttpPost ----- http response status:" + response.getStatusLine());

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new Exception(response.getStatusLine().toString());
            }
            return response;
        } catch (Exception e) {
            logger.error("异常",e);
            throw new Exception(e.getMessage());
        } finally {
            httpClient.getConnectionManager().shutdown();
            logger.info("doHttpPost ----- response: " + response);
        }
    }

    /**
     * 处理http response
     * @param response
     * @param charset
     * @return
     * @throws IOException
     */
    private static Map<String, String> parseResponse(HttpResponse response, String charset) throws IOException {
        String responseStr = "";

        //将response转换成string
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            responseStr = EntityUtils.toString(responseEntity, charset);
        }
//        logger.info("parseResponse ----- responseStr: " + responseStr);

        //将responseStr转换成map
        String[] results = responseStr.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String value : results) {
            //不要用split("="),因为value由base64生成，可能含有多个=号
            int n = value.indexOf("=");
            map.put(value.substring(0, n), value.substring(n + 1));
        }
        return map;
    }

}
