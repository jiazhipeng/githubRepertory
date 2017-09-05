package com.hy.weixin.pay.entity;

import com.hy.constant.Constant;

public class WeiXinUnifiedorder {
	
	/**
	 * 退款类型微信退款
	 */
	public static int REFUND_TYPE_WEIXIN = 0;
	/**
	 * 退款类型app 退款
	 */
	public static int REFUND_TYPE_APP = 1;
	
	
	
	private String body ;//商品描述
	
	private String totalFee;//总金额
	
	private String outTradeNo;//商户订单号
	
	private String spbillCreateIp;//ip地址
	
	private String openId;//openId
	
	private String mchId;//商户号
	
	private String certificate;//证书
	
	private String appid;//appid
	
	private String secretKey;//密钥
	
	private String basePath;
	
	private String transactionId;//微信支付流水号
	
	
	
	

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public WeiXinUnifiedorder(int refundType) {
		
		if(refundType == REFUND_TYPE_APP){//app id
			this.mchId = Constant.load.getProperty("WX_PARTNER_ID");
			this.certificate = "/apiclient_cert_app.p12";
			this.appid = Constant.load.getProperty("WX_APP_ID");
			this.secretKey = Constant.load.getProperty("weixinApp.pay.key");
			
		}else if(refundType == REFUND_TYPE_WEIXIN){//微信
			this.mchId = Constant.load.getProperty("weixin.mch_id");
			this.certificate = "/apiclient_cert.p12";
			this.appid = Constant.load.getProperty("appid");
			this.secretKey = Constant.load.getProperty("weixin.pay.key");
		}
		
		
	}
	
	public WeiXinUnifiedorder() {
	}

	public String getMchId() {
		return mchId;
	}

	public String getCertificate() {
		return certificate;
	}

	public String getAppid() {
		return appid;
	}
	

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
	

}
