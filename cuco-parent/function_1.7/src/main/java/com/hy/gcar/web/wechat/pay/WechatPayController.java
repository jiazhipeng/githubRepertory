package com.hy.gcar.web.wechat.pay;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.MD5Util;
import com.hy.common.utils.PropertiesLoader;
import com.hy.constant.Constant;
import com.hy.gcar.constant.ResultCode;
import com.hy.gcar.entity.AuthJsonObject;
import com.hy.gcar.entity.ChargeOrder;
import com.hy.gcar.entity.ChargeOrderLog;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.WeChatRefundAppOrder;
import com.hy.gcar.entity.WeChatUserInfo;
import com.hy.gcar.entity.WeiChatPreAppOrder;
import com.hy.gcar.entity.WeiChatPreOrder;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.chargeOrderLog.ChargeOrderLogService;
import com.hy.weixin.http.HttpClientConnectionManager;
import com.hy.weixin.utils.Signature;
import com.hy.weixin.utils.WechatUrl;
import com.hy.weixin.utils.XMLParser;

@Controller
@RequestMapping("/wechat/wechatPay")
public class WechatPayController {

	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ChargeOrderLogService chargeOrderLogService;
	
	@Autowired
	private ChargeOrderService chargeOrderService;
	
	/**
	 * 获取微信支付第三方授权URL
	 * 
	 * @param orderNo
	 *            订单编号，授权之前要拿到自己服务端的订单号
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/generateAuthurlWithOuttradeno")
	public String generateAuthurlWithOuttradeno(
			String orderNo,
			ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		// 重定向URL，用户授权后会自动重定向到这里，就可以获取用户授权信息
		String basePath = Constant.domain+"wechat/wechatPay/doWeChatAuth.do";
		//orderNo = "20160806125346";
		//String basePath = "http://localhost:8080/wechat/wechatPay/doWeChatAuth.do";
		if (orderNo != null && orderNo.length() > 0) {
			// 根据重定向URL构建授权URL，具体方法见下面的方法：buildAuthUrl
			String authUrl = WechatUrl.buildAuthUrl(Constant.APPIDPAY, basePath,
					orderNo);
			modelMap.put("stateCode", 200);
			modelMap.put("msg", "操作成功");
			modelMap.put("data", authUrl);
		} else {
			modelMap.put("stateCode", 500);
			modelMap.put("msg","错误的订单号：" + orderNo );
			modelMap.put("data", "");
		}
		logger.info("进来了----------"+modelMap.get("data"));
		return "gcar/weChat/weChatUserInfo";
	}

	/**
	 * AUTH2.0 网页授权处理，这个不需要自己调用，微信会重定向到这里
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("doWeChatAuth")
	public void doWeiChatAuth(
			@RequestParam("state") String outTradeNo,// 订单编号，来自授权url的state
			ModelAndView modelAndView, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取授权后的code
		String code = request.getParameter("code");
		logger.info("微信回调，获取用户授权code"+code);
		if ("authdeny".equals(code) == false) {
			// 获取用户授权后的信息
			String authMsg = WechatUrl.fetchAuthReturnMsg(Constant.APPIDPAY,
					Constant.APPSECRET, code, "POST");
			logger.info("微信回调，获取用户授权信息"+authMsg);
			// JSONObject为阿里的fastjson提供
			AuthJsonObject authJsonObject = JSONObject.parseObject(authMsg,
					AuthJsonObject.class);
			// 刷新AccessToken，默认获取的有效期太短
			String refreshTokenobject = WechatUrl.refreshAccessToken(
					Constant.APPIDPAY, authJsonObject.getRefresh_token(), "POST");
			AuthJsonObject refreshObject = JSONObject.parseObject(
					refreshTokenobject, AuthJsonObject.class);
			// 根据AccessToken和用户的openId获取用户信息
			String userInfo = WechatUrl.getUserinfo(
					refreshObject.getAccess_token(), refreshObject.getOpenid(),
					"POST");
			WeChatUserInfo weChatUserInfo = JSONObject.parseObject(userInfo,
					WeChatUserInfo.class);
			if (weChatUserInfo != null) {
				// 请求转发到处理微信统一下单的接口，获取JS SDK调用的配置参数和支付用的参数
				response.sendRedirect(Constant.domain+"wechat/wechatPay/redirectToPayDetail.do?no="
						+ outTradeNo
						+ "&param_0="
						+ weChatUserInfo.getOpenid());
			}
		} else {
			response.getOutputStream().write(new String("用户拒绝授权").getBytes());
		}
	}

	/**
	 * 微信公众号 支付
	 * 获取微信JS SDK初始化配置参数，调用微信统一下单接口获取微信prepay_id,获取JS支付用的参数
	 * 
	 * @param op_d
	 *            用户openId
	 * @param no
	 *            订单号
	 * @param modelAndView
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("redirectToPayDetail")
	public String redirectToPayDetail(
			@RequestParam("param_0") String op_d,
			@RequestParam("no") String no, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException {
		logger.info("进去系统统一下单接口");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// JS SDK签名URL，是当前页面的location，这里就是第三步重定向的URL，用来参与计算JS SDK初始化的签名
		String signUrl = Constant.domain+"wechat/wechatPay/redirectToPayDetail.do?no="
				+ no + "&param_0=" + op_d;
		// 签名随机字符串，由于微信里面的坑太多，所以我们所有的签名字符串和时间戳最好公用，不然真的会蒙圈的
		String weiPaySignStr = WechatUrl.getRandomStringByLength(20);
		// 签名用时间戳
		String timestamp = (System.currentTimeMillis()/1000) + "";
		// 获取JS API 初始化配置参数
		Map<String, String> initConfigParams = WechatUrl.FetchConfigParams(signUrl,
				weiPaySignStr, timestamp);
		// 根据订单号验证订单是否存在，根据自己业务写  查询订单
		//Userorderlist order = userorderlistMapper.selectByOrderNo(no);
	//	if (order != null && order.getOrderno().length() > 0) {// 订单存在的时候就进行支付前一系列准备工作
			// 统一下单签名参数哈希表
			// 统一下单的body,就是商品详情，要是传中文记得newString(body.getBytes("ISO8859-1"))，不然JS签名失败，
			// 但是即使这样,支付成功后微信给你发的支付凭证中商品详情还是乱码显示，所以传英文吧，这坑懒得去填
			String body = "jiche WeChat Order Pay";
			Map<String, Object> prepaySignParam = new HashMap<String, Object>();
			prepaySignParam.put("appid",  Constant.APPIDPAY);
			prepaySignParam.put("mch_id",  Constant.MCHID);
			prepaySignParam.put("body", body);
			prepaySignParam.put("nonce_str", weiPaySignStr);
			// 微信异步通知地址，在这里处理后续订单逻辑
			prepaySignParam.put("notify_url",
					Constant.domain+"wechat/wechatPay/weipayCallBack.do");
			prepaySignParam.put("out_trade_no", no);
			prepaySignParam.put("spbill_create_ip", request.getRemoteAddr());// 用户IP地址
			// 单位分
			//prepaySignParam.put("total_fee",new BigDecimal(Double.parseDouble(order.getTotalprice()) * 100).intValue());
			prepaySignParam.put("total_fee",1);
			prepaySignParam.put("trade_type", "JSAPI");
			prepaySignParam.put("openid", op_d);

			// 构造微信预支付订单
			WeiChatPreOrder weiChatPreOrder = new WeiChatPreOrder();
			weiChatPreOrder.setAppid(Constant.APPIDPAY);
			weiChatPreOrder.setMch_id(Constant.MCHID);
			weiChatPreOrder.setBody(body);
			weiChatPreOrder.setNonce_str(weiPaySignStr);
			weiChatPreOrder
					.setNotify_url(Constant.domain+"wechat/wechatPay/weipayCallBack.do");
			weiChatPreOrder.setOut_trade_no(no);// 系统订单号
			weiChatPreOrder.setSpbill_create_ip(request.getRemoteAddr());
			//weiChatPreOrder.setTotal_fee(new BigDecimal(Double
			//		.parseDouble(order.getTotalprice()) * 100).intValue());// 交易金额
			weiChatPreOrder.setTotal_fee(1);
			weiChatPreOrder.setTrade_type("JSAPI");
			weiChatPreOrder.setOpenid(op_d);
			// 计算统一下单签名参数计算预支付订单的MD5签名
			weiChatPreOrder.setSign(Signature.getSign(prepaySignParam));
			// 生成XML订单
			String xmlOrder = weiChatPreOrder.toXml();
			logger.info("统一下单接口的xml"+xmlOrder);
			// 通过微信下单接口获取prepay_id
			String prepayId = WechatUrl.getPrepayId(xmlOrder);
			logger.info("统一下单接口的prepayId"+prepayId);
			// JS支付参数
			SortedMap<String, String> paySignparams = new TreeMap<String, String>();
			paySignparams.put("appId", Constant.APPIDPAY);
			paySignparams.put("timeStamp", timestamp);
			paySignparams.put("nonceStr", weiPaySignStr);
			paySignparams.put("signType", "MD5");
			// 计算JS SDK支付调用签名字符串【appId, nonceStr,package,signType,timeStamp】
			StringBuffer signBuff = new StringBuffer();
			signBuff.append("appId=").append(Constant.APPIDPAY + "&nonceStr=")
					.append(weiPaySignStr + "&package=prepay_id=")
					.append(prepayId + "&signType=MD5&timeStamp=")
					.append(timestamp + "&key=").append(Constant.WECHAT_KEY);
			// 计算MD5签名
			String paySign = MD5Util.MD5Encode(signBuff.toString());
			paySignparams.put("paySign", paySign);
			modelMap.put("configParam", initConfigParams);
			modelMap.put("payParams", paySignparams);
			modelMap.put("msg", "ok");
			modelMap.put("payId", prepayId);
			//modelMap.put("order", order);// 订单信息
			
			//request.setAttribute("payId", prepayId);
		/*} else {
			modelAndView.addObject("msg", "找不到订单/无效的订单编号");
		}*/
			// 返回支付页面
		return "gcar/weChat/weChatPay";
	}
	
	/**
	 *  微信支付异步通知处理接口
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("weipayCallBack")
	public void weipayCallBack(HttpServletRequest request,HttpServletResponse response) throws IOException{
	logger.info("**************************微信支付异步回调通知开始***********************");
		//System.out.println("收到微信异步通知。。。");
	 InputStream inStream = request.getInputStream();
	 ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	 byte[] buffer = new byte[1024];
	 int len = 0;
	 while ((len = inStream.read(buffer)) != -1) {
	  outSteam.write(buffer, 0, len);
	 }
	 outSteam.close();
	 inStream.close();
	 String resultStr  = new String(outSteam.toByteArray(),"utf-8");
	 Map<String, Object> resultMap = new HashMap<String, Object>();
	 try {
	  resultMap = XMLParser.getMapFromXML(resultStr);
	  String out_trade_no = (String) resultMap.get("out_trade_no");
	  String return_code = (String) resultMap.get("return_code");
	  String total_fee = (String) resultMap.get("total_fee");
	  String bank = (String) resultMap.get("bank_type");
	  String transaction_id = (String) resultMap.get("transaction_id");
	  Integer fee = Integer.decode(total_fee)/100;
	  //签名验证
	  boolean valid = Signature.checkIsSignValidFromResponseString(resultStr);
	  ChargeOrderLog chargeOrderLog = chargeOrderLogService.findChargeOrderLogByOrderNo(out_trade_no);
	  ChargeOrderLog log = new ChargeOrderLog();
	  if(chargeOrderLog == null){
          this.logger.info(out_trade_no + ",订单不存在.....");
      }else{
    	  //日志
    	  log.setOrderNo(chargeOrderLog.getOrderNo());
    	  log.setBalance(new BigDecimal(fee));
		  log.setWechatJson(resultMap.toString());
		  log.setLogType(0);
		  log.setCompletionTime(new Date());
		  log.setBank(bank);
		  log.setPayWaterNo(transaction_id);
      }	  
	  if(return_code.equals("SUCCESS") && valid){
		  try {
			chargeOrderLogService.updateChargeOrderLogComplete(log);
		  } catch (Exception e) {
				 logger.info("************微信支付异步回调查询订单异常"+out_trade_no);
		  } 
	      //处理订单后续业务
		  logger.info("************微信支付异步回调通知支付成功"+resultMap);
	  }else{
		  try {
			  chargeOrderLogService.updateChargeOrderLogFail(log);
		} catch (Exception e) {
			 logger.info("************微信支付异步回调插入日志异常"+e.getMessage());
		}
		logger.info("************微信支付异步回调通知支付失败"+resultMap);
		 
	  }
	 } catch (ParserConfigurationException e) {
		 logger.info("************微信支付异步回调异常"+e.getMessage()+"微信返回"+resultStr);
	  e.printStackTrace();
	 } catch (SAXException e) {
		 logger.info("************微信支付异步回调异常"+e.getMessage()+"微信返回"+resultStr);
	  e.printStackTrace();
	 }
	 //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.[一定别手贱传return_msg回去，他们傻逼会继续回调的]
	 String success = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
	 response.getOutputStream().write(new String(success).getBytes());
	 logger.info("**************************微信支付异步回调通知结束***********************");
	}
	
	public static void main(String[] args) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException{
		  DefaultHttpClient client = new DefaultHttpClient();
		  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		  HttpPost httpost= HttpClientConnectionManager.getPostMethod("https://api.mch.weixin.qq.com/secapi/pay/refund");
		  String weiPaySignStr = WechatUrl.getRandomStringByLength(20);
		  Map<String,Object> map = new HashMap<String, Object>();
		  map.put("appid",  "wx60a4abe71b2961e3");
		  map.put("mch_id",  "1356203002");
		  map.put("nonce_str", weiPaySignStr);
		  map.put("out_trade_no", "20160806125346");
		  map.put("out_refund_no", "20160806125346");
		  // 单位分
		  map.put("total_fee",10);
		  map.put("refund_fee", "10");
		  map.put("op_user_id", "1356203002");
		  map.put("refund_account", "REFUND_SOURCE_RECHARGE_FUNDS");
		  WeChatRefundAppOrder refundAppOrder = new WeChatRefundAppOrder();
		  refundAppOrder.setAppid("wx60a4abe71b2961e3");
		  refundAppOrder.setMch_id("1356203002");
		  refundAppOrder.setNonce_str(weiPaySignStr);
		  refundAppOrder.setSign(Signature.getSign(map));
		  refundAppOrder.setOut_trade_no("20160806125346");
		  refundAppOrder.setOut_refund_no("20160806125346");
		  refundAppOrder.setTotal_fee(10);
		  refundAppOrder.setRefund_fee(10);
		  refundAppOrder.setOp_user_id("1356203002");
		  refundAppOrder.setRefund_account("REFUND_SOURCE_RECHARGE_FUNDS");
		  String xmlParam = refundAppOrder.toXml();
		  KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		  FileInputStream instream = new FileInputStream(new File("E:/weChat/cert/apiclient_cert.p12"));//放退款证书的路径
	      try {
	          keyStore.load(instream, "1356203002".toCharArray());
	      }    
	 	 finally {
	 		  instream.close();
	      }
	 	 
	      SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "1356203002".toCharArray()).build();
          SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                  sslcontext,
                  new String[] { "TLSv1" },
                  null,
                  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
          CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	     try {
			 httpost.setEntity(new StringEntity(xmlParam, "ISO-8859-1"));
			 HttpResponse response = httpclient.execute(httpost);
		     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
		    System.out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
	}
	
	  public static DefaultHttpClient httpclient;

	  static
	  {
	    httpclient = new DefaultHttpClient();
	    httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
	  }
	
	/*public static void main(String[] args) throws IOException, ReflectiveOperationException {
		
// 签名随机字符串，由于微信里面的坑太多，所以我们所有的签名字符串和时间戳最好公用，不然真的会蒙圈的
		String weiPaySignStr = WechatUrl.getRandomStringByLength(20);
		// 签名用时间戳
		String timestamp = (System.currentTimeMillis()/1000) + "";
		System.out.println(timestamp);
		Map<String,Object> modelMap = new HashMap<String, Object>();
		// 根据订单号验证订单是否存在，根据自己业务写  查询订单
		//Userorderlist order = userorderlistMapper.selectByOrderNo(no);
//			if (order != null && order.getOrderno().length() > 0) {// 订单存在的时候就进行支付前一系列准备工作
			// 统一下单签名参数哈希表
			// 统一下单的body,就是商品详情，要是传中文记得newString(body.getBytes("ISO8859-1"))，不然JS签名失败，
			// 但是即使这样,支付成功后微信给你发的支付凭证中商品详情还是乱码显示，所以传英文吧，这坑懒得去填
			String body = "jiche WeChat Order Pay";
			Map<String, Object> prepaySignParam = new HashMap<String, Object>();
			prepaySignParam.put("appid",  "wx77b567355ce87b40");
			prepaySignParam.put("mch_id",  "1314892501");
			prepaySignParam.put("body", body);
			prepaySignParam.put("nonce_str", "hhxsgq7nhrt8ynebsi1l");
			System.out.println("weiPaySignStr");
			// 微信异步通知地址，在这里处理后续订单逻辑
			prepaySignParam.put("notify_url",
					"test224224.1mobility.cnwechat/wechatPay/weipayCallBack.do");
			prepaySignParam.put("out_trade_no", "jcgs20161018165119627");
			prepaySignParam.put("spbill_create_ip", "192.168.3.165");// 用户IP地址
			// 单位分
			//prepaySignParam.put("total_fee",new BigDecimal(Double.parseDouble(order.getTotalprice()) * 100).intValue());
			prepaySignParam.put("total_fee",975100);
			prepaySignParam.put("trade_type", "APP");

			// 构造微信预支付订单
			WeiChatPreAppOrder weiChatPreOrder = new WeiChatPreAppOrder();
			weiChatPreOrder.setAppid("wx77b567355ce87b40");
			weiChatPreOrder.setMch_id("1314892501");
			weiChatPreOrder.setBody(body);
			weiChatPreOrder.setNonce_str("hhxsgq7nhrt8ynebsi1l");
			weiChatPreOrder
					.setNotify_url("test224224.1mobility.cnwechat/wechatPay/weipayCallBack.do");
			weiChatPreOrder.setOut_trade_no("jcgs20161018165119627");// 系统订单号
			weiChatPreOrder.setSpbill_create_ip("192.168.3.165");
			//weiChatPreOrder.setTotal_fee(new BigDecimal(Double
			//		.parseDouble(order.getTotalprice()) * 100).intValue());// 交易金额
			weiChatPreOrder.setTotal_fee(975100);
			weiChatPreOrder.setTrade_type("APP");
			System.out.println("key"+Constant.WECHAT_KEY);
			// 计算统一下单签名参数计算预支付订单的MD5签名
			weiChatPreOrder.setSign(Signature.getSign(prepaySignParam));
			System.out.println(weiChatPreOrder.getSign());
			// 生成XML订单
			String xmlOrder = weiChatPreOrder.toXml();
			System.out.println(xmlOrder);
			// 通过微信下单接口获取prepay_id
			String prepayId = WechatUrl.getPrepayId(xmlOrder);
			// JS支付参数
			SortedMap<String, Object> paySignparams = new TreeMap<String, Object>();
			paySignparams.put("appId", "wxb9cbb99c39a74651");
			paySignparams.put("partnerId", "1314892501");
			paySignparams.put("prepayId", "wx2016101912225488677328920170174361");
			paySignparams.put("packageValue", "Sign=WXPay");
			paySignparams.put("nonceStr", "fbp2t3kkuqcf6dbo61si");
			paySignparams.put("timeStamp", "1476850968");
			System.out.println(timestamp);
			
			// 计算JS SDK支付调用签名字符串【appId, nonceStr,package,signType,timeStamp】
			StringBuffer signBuff = new StringBuffer();
			signBuff.append("appId=").append(Constant.APPIDPAY + "&nonceStr=")
					.append(weiPaySignStr + "&package=Sign=WXPay&prepay_id=")
					.append(prepayId + "&signType=MD5&timeStamp=")
					.append(timestamp + "&key=").append(Constant.WECHAT_KEY);
			// 计算MD5签名 APP发起支付参与 [参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package]
			String paySign = Signature.getSign(paySignparams);
			System.out.println(paySign);
			//String paySign = MD5Util.MD5Encode(signBuff.toString());
			paySignparams.put("sign", paySign);
			modelMap.put("payParams", paySignparams);
			modelMap.put("msg", "ok");
			
		
		// 构造微信预支付订单
		WeiChatPreAppOrder weiChatPreOrder = new WeiChatPreAppOrder();
		weiChatPreOrder.setAppid("wx77b567355ce87b40");
		weiChatPreOrder.setMch_id("1314892501");
		weiChatPreOrder.setBody("jiche WeChat Order Pay");
		weiChatPreOrder.setNonce_str("hhxsgq7nhrt8ynebsi1l");
		weiChatPreOrder
				.setNotify_url("test224224.1mobility.cn/wechat/wechatPay/weipayCallBack.do");
		weiChatPreOrder.setOut_trade_no("jcgs20161018165119627");// 系统订单号
		weiChatPreOrder.setSpbill_create_ip("192.168.3.165");
		//weiChatPreOrder.setTotal_fee(new BigDecimal(Double
		//		.parseDouble(order.getTotalprice()) * 100).intValue());// 交易金额
		weiChatPreOrder.setTotal_fee(975100);
		weiChatPreOrder.setTrade_type("APP");
		//weiChatPreOrder.setOpenid("oFCx8wKWP_dufXCxSPQGDyZsXITM");
		// 计算统一下单签名参数计算预支付订单的MD5签名
		weiChatPreOrder.setSign("1FE9B2BEA6F66110706322CCE66BD9E9");
		// 生成XML订单
		System.out.println("key"+Constant.WECHAT_KEY);
		String xmlOrder = weiChatPreOrder.toXml();
		System.out.println(xmlOrder);
		String xmlParam = "";
		try {
			 xmlParam = new String(xmlOrder.getBytes(),"ISO-8859-1");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		
		  DefaultHttpClient client = new DefaultHttpClient();
		  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		  HttpPost httpost= HttpClientConnectionManager.getPostMethod("https://api.mch.weixin.qq.com/pay/unifiedorder");
		  Map<String,Object> map = null;
	     try {
			 httpost.setEntity(new StringEntity(xmlParam, "ISO-8859-1"));
			 HttpResponse response = httpclient.execute(httpost);
		     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
		    System.out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			client.close();
		}
	}*/
}
