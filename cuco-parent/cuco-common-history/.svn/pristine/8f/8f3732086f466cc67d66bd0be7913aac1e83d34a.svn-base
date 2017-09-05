package com.hy.common.utils;

import com.hy.constant.Constant;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Created by WangShuai on 2016/8/2.
 */
public class HttpUtils {

    public static Header[] BASIC_HEADERS = {new BasicHeader("Content-Type","application/json"),new BasicHeader("Accept","text/html;charset=UTF-8")};

    public static Header[] URLENCODED_HEADERS = {new BasicHeader("Content-Type","application/x-www-form-urlencoded"),new BasicHeader("Accept","application/json;charset=UTF-8")};

    public static CloseableHttpResponse sendGet(String url) throws IOException {
        return sendGet(url, null);
    }

    public static CloseableHttpResponse sendGet(String url,Header[] headers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //请求头
        httpGet.setHeaders(headers);

        RequestConfig requestConfig = httpGet.getConfig();
        requestConfig.custom().setConnectionRequestTimeout(2000);
        requestConfig.custom().setConnectTimeout(2000);
        requestConfig.custom().setSocketTimeout(2000);
        httpGet.setConfig(requestConfig);

        return httpClient.execute(httpGet);
    }

    public static CloseableHttpResponse sendPost(String requestUrl,Header[] headers,HttpEntity httpEntity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //请求地址
        HttpPost httpPost = new HttpPost(requestUrl);
        //请求头
        httpPost.setHeaders(headers);
        //请求体
        httpPost.setEntity(httpEntity);

        return httpClient.execute(httpPost);//发出请求,获取响应
    }

    public static String responseToString(CloseableHttpResponse closeableHttpResponse) throws IOException,NullPointerException {
        if(closeableHttpResponse==null){
            throw new NullPointerException("response can't be null.");
        }
        return EntityUtils.toString(closeableHttpResponse.getEntity(), Constant.CHARSET_UTF8);//"gb2312"
    }

}
