package com.hy.weixin.utils;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hy.common.utils.Global;
import com.tencent.mm.sdk.modelpay.PayReq;



public class WeiXin {
	private Logger logger = LoggerFactory.getLogger(getClass());

	  /** APP_ID 应用从官方网站申请到的合法appId */
    public static final String WX_APP_ID = "wx77b567355ce87b40";
    /** 商户号 */
    public static final String WX_PARTNER_ID = "1314892501";//商户号
    /** 接口链接 */
    public static final String url="https://api.mch.weixin.qq.com/pay/unifiedorder";
    /** 商户平台和开发平台约定的API密钥，在商户平台设置  */
    public static final String key="hycx0001pay1mobility000111222333";                   //api_key
    
    public static String  prepay_id="";
    
    public StringBuffer wechatPaySb = new StringBuffer();
   
    /**
     * 统一返回
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
   	//封装产品信息
public String getproduct(HttpServletRequest  request) throws UnsupportedEncodingException{
	StringBuffer xml = new StringBuffer();
	request.setCharacterEncoding("utf-8");
	 String	nonceStr = genNonceStr();
     xml.append("</xml>");
     List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
     packageParams.add(new BasicNameValuePair("appid",WX_APP_ID));
     //packageParams.add(new BasicNameValuePair("attach",inputMoney));
     //body为汉字是要转成UTF-8
				String  payurl=	Global.getConfig("payurl");
     packageParams.add(new BasicNameValuePair("body", "定金支付"));//测试服务器http://gcarapp.1mobility.cn/gcarapp/payfacede/callback
     packageParams.add(new BasicNameValuePair("mch_id", WX_PARTNER_ID));//http://wangying.1mobility.cn/gcarapp/payfacede/callback
     packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));//http://j1483926v2.iask.in:2050/hycx-gappfacade/service/customerfacede/app/updatecustomerinfo
     packageParams.add(new BasicNameValuePair("notify_url", payurl));
     packageParams.add(new BasicNameValuePair("out_trade_no",request.getAttribute("orderid").toString()));//request.getAttribute("order_id").toString()
     packageParams.add(new BasicNameValuePair("spbill_create_ip",request.getRemoteAddr()));
     packageParams.add(new BasicNameValuePair("total_fee", "" +  (int)(1)));
     packageParams.add(new BasicNameValuePair("trade_type", "APP"));//JSAPI
     String sign = genPackageSign(packageParams);
     packageParams.add(new BasicNameValuePair("sign", sign));
     String xmlstring =toXml(packageParams);

     logger.info("**********************************************"+sign);

     logger.info("**********************************************"+request.getRemoteAddr());
logger.info("xml**********************************************"+xmlstring);

	return xmlstring;
	
}
//获取随机验证参数
public static String genNonceStr() {
    Random random = new Random();
    return MD5Util.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
}
//获取包签名
private String genPackageSign(List<NameValuePair> params) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < params.size(); i++) {
        sb.append(params.get(i).getName());
        sb.append('=');
        sb.append(params.get(i).getValue());
        sb.append('&');
    }
    sb.append("key=");
    sb.append(key);
    System.out.println("签名前字符串："+sb.toString());
    //String packageSign = MD5Util.getMessageDigest(sb.toString().getBytes()).toUpperCase();
    String packageSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
    System.out.println("包签名：="+packageSign);
    return packageSign;
}

/**
 * 获得app前面
 * @param params 参数
 * @return 签名以后的字符串
 */
private String genAppSign(List<NameValuePair> params) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < params.size(); i++) {
        sb.append(params.get(i).getName());
        sb.append('=');
        sb.append(params.get(i).getValue());
        sb.append('&');
    }
    sb.append("key=");
    sb.append(key);
    this.wechatPaySb.append("sign str\n" + sb.toString() + "\n\n");
    String appSign = MD5Util.getMessageDigest(sb.toString().getBytes()).toUpperCase();
    System.out.println("APP签名="+appSign);
    
    return appSign;
}


               
public PayReq genPayReq(String  prepayid) {  
	PayReq req = new PayReq();  
   // req.appId = Global.getConfig("APP_ID"); 
	 req.appId =  WX_APP_ID;
  //  req.partnerId = Constants.MCH_ID;  
    req.partnerId =  WX_PARTNER_ID;  
    req.prepayId = prepayid;  
//      req.packageValue = "prepay_id="+resultunifiedorder.get("prepay_id");  
    req.packageValue = "Sign=WXPay";  
    req.nonceStr = genNonceStr();  
    req.timeStamp = String.valueOf(genTimeStamp());  


    List<NameValuePair> signParams = new LinkedList<NameValuePair>();  
    signParams.add(new BasicNameValuePair("appid", req.appId));  
    signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));  
    /** 
     * 这里的package参数值必须是Sign=WXPay,否则IOS端调不起微信支付， 
     * (参数值是"prepay_id="+resultunifiedorder.get("prepay_id")的时候Android可以，IOS不可以) 
     */  
    signParams.add(new BasicNameValuePair("package", req.packageValue));  
    /**注意二次签名这里不再是mch_id,变成了prepayid;*/  
    signParams.add(new BasicNameValuePair("partnerid", req.partnerId));  
    signParams.add(new BasicNameValuePair("prepayid", req.prepayId));  
    signParams.add(new BasicNameValuePair("timestamp", req.timeStamp)); 
    req.sign = genAppSign(signParams);
    return  req;
}  

/**
 * 将请求参数转换为xml格式
 * @param params 参数list
 * @return xml字符串
 */
private String toXml(List<NameValuePair> params) {
    StringBuilder sb = new StringBuilder();
    sb.append("<xml>");
    for (int i = 0; i < params.size(); i++) {
        sb.append("<"+params.get(i).getName()+">");
        sb.append(params.get(i).getValue());
        sb.append("</"+params.get(i).getName()+">");
    }
    sb.append("</xml>");
    return sb.toString();
}


    
/**
 * 统一支付接口
 * @param request
 * @return
 * @throws UnsupportedEncodingException
 */
    public Map  doInBackground(HttpServletRequest request) throws UnsupportedEncodingException {
        String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
        String entity = getproduct(request);
//		System.out.println("entity:" + entity);
        String buf = HttpUtil.sendPostUrl(url,entity);
        String content = new String(buf);
//		System.out.println("orion:" + content);
        Map<String,String> xml=null;
        try {
			xml=XMLUtil.doXMLParse(content);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return xml;
}

    /**
     * 微信app退款
     * out_trade_no  订单号
     * out_refund_no  退款单号
     * path "D:/gongju/zhengshu/apiclient_cert.p12"
     * @return
     */
     public  static  void   refund(String out_trade_no,String out_refund_no,String path) throws Exception{
    	   SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
           parameters.put("appid",WeiXin.WX_APP_ID);//appid
           parameters.put("mch_id", WeiXin.WX_PARTNER_ID);//商户号
           parameters.put("nonce_str",WeiXin.genNonceStr());
          //在notify_url中解析微信返回的信息获取到 transaction_id，此项不是必填，详细请看上图文档
           //parameters.put("transaction_id", "微信支付订单中调用统一接口后微信返回的 transaction_id");
           parameters.put("out_trade_no", out_trade_no);//订单好
           parameters.put("out_refund_no", out_refund_no);//我们自己设定的退款申请号，约束为UK
           parameters.put("total_fee", "1") ;//单位为分
           parameters.put("refund_fee", "1");//单位为分
           parameters.put("op_user_id", WeiXin.WX_PARTNER_ID);//操作人员,默认为商户账号
           String sign = createSign("utf-8", parameters);
           parameters.put("sign", sign);
          String reuqestXml = getRequestXml(parameters);
          KeyStore keyStore  = KeyStore.getInstance("PKCS12");
          FileInputStream instream = new FileInputStream(new File(path));//放退款证书的路径
          try {
              keyStore.load(instream,WeiXin.WX_PARTNER_ID.toCharArray());
          } finally {
              instream.close();
          }
          SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,  WeiXin.WX_PARTNER_ID.toCharArray()).build();
          SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                  sslcontext,
                  new String[] { "TLSv1" },
                  null,
                  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
          CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
          try {
              HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");//退款接口
              System.out.println("executing request" + httpPost.getRequestLine());
              StringEntity  reqEntity  = new StringEntity(reuqestXml);
              // 设置类型
              reqEntity.setContentType("application/x-www-form-urlencoded");
              httpPost.setEntity(reqEntity);
              CloseableHttpResponse response = httpclient.execute(httpPost);
              try {
                  HttpEntity entity = response.getEntity();
                  System.out.println("----------------------------------------");
                  System.out.println(response.getStatusLine());
                  if (entity != null) {
                      System.out.println("Response content length: " + entity.getContentLength());
                      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                      String text;
                      while ((text = bufferedReader.readLine()) != null) {
                          System.out.println(text);
                      }
                  }
                  EntityUtils.consume(entity);
              } finally {
                  response.close();
              }
          } finally {
              httpclient.close();
          } 
     }

     public static String createSign(String charSet,SortedMap<Object,Object> parameters){
         StringBuffer sb = new StringBuffer();
         Set es = parameters.entrySet();
         Iterator it = es.iterator();
         while(it.hasNext()) {
             Map.Entry entry = (Map.Entry)it.next();
             String k = (String)entry.getKey();
             Object v = entry.getValue();
             if(null != v && !"".equals(v)
                     && !"sign".equals(k) && !"key".equals(k)) {
                 sb.append(k + "=" + v + "&");
             }
         }
         sb.append("key=" + key);
         String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
         return sign;
       }
     
     public static String getRequestXml(SortedMap<Object,Object> parameters){
         StringBuffer sb = new StringBuffer();
         sb.append("<xml>");
         Set es = parameters.entrySet();
         Iterator it = es.iterator();
         while(it.hasNext()) {
           Map.Entry entry = (Map.Entry)it.next();
           String k = (String)entry.getKey();
           String v = (String)entry.getValue();
           if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
               sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
           }else {
               sb.append("<"+k+">"+v+"</"+k+">");
           }
         }
         sb.append("</xml>");
         return sb.toString();
     }

public String genTimeStamp() {
    return String.valueOf(System.currentTimeMillis() / 1000) ;
}

public static void main(String[] args) {
	WeiXin wx = new WeiXin();
//	Map xml = wx.doInBackground(request);
//	System.out.println(xml.get("prepay_id"));
}


}
