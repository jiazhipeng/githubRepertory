package com.hy.gcar.entity;

public class WeChatQueryOrder {

	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String out_trade_no;
	public String toXml(){
	StringBuffer requestStr = new StringBuffer(
	  "<xml>");
	  requestStr.append("<appid><![CDATA[");
	  requestStr.append(appid);
	  requestStr.append("]]></appid>");
	  requestStr.append("<mch_id><![CDATA[");
	  requestStr.append(mch_id);
	  requestStr.append("]]></mch_id>");
	  requestStr.append("<nonce_str><![CDATA[");
	  requestStr.append(nonce_str);
	  requestStr.append("]]></nonce_str>");
	  requestStr.append("<out_trade_no><![CDATA[");
	  requestStr.append(out_trade_no);
	  requestStr.append("]]></out_trade_no>");
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
	public String getOut_trade_no() {
		return out_trade_no;
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
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
}
