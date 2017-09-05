package com.hy.gcar.entity;

import java.math.BigDecimal;

public class WeiChatPreAppOrder {

	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String body;
	private String out_trade_no;
	private int total_fee;
	private String spbill_create_ip;
	private String notify_url;
	private String trade_type = "APP";
	public String toXml(){
	StringBuffer requestStr = new StringBuffer(
	  "<xml>");
	  requestStr.append("<appid><![CDATA[");
	  requestStr.append(appid);
	  requestStr.append("]]></appid>");
	  requestStr.append("<body><![CDATA[");
	  requestStr.append(body);
	  requestStr.append("]]></body>");
	  requestStr.append("<mch_id><![CDATA[");
	  requestStr.append(mch_id);
	  requestStr.append("]]></mch_id>");
	  requestStr.append("<nonce_str><![CDATA[");
	  requestStr.append(nonce_str);
	  requestStr.append("]]></nonce_str>");
	  requestStr.append("<notify_url><![CDATA[");
	  requestStr.append(notify_url);
	  requestStr.append("]]></notify_url>");
	  requestStr.append("<out_trade_no><![CDATA[");
	  requestStr.append(out_trade_no);
	  requestStr.append("]]></out_trade_no>");
	  requestStr.append("<spbill_create_ip><![CDATA[");
	  requestStr.append(spbill_create_ip);
	  requestStr.append("]]></spbill_create_ip>");
	  requestStr.append("<total_fee><![CDATA[");
	  requestStr.append(total_fee);
	  requestStr.append("]]></total_fee>");
	  requestStr.append("<trade_type><![CDATA[");
	  requestStr.append(trade_type);
	  requestStr.append("]]></trade_type>");
	  requestStr.append("<sign><![CDATA[");
	  requestStr.append(sign);
	  requestStr.append("]]></sign>");
	  requestStr.append("</xml>");
	  return requestStr.toString().trim();
	}
	public String getAppid() {
		return appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public String getBody() {
		return body;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
}
