package com.hy.gcar.entity;

public class WeChatRefundAppOrder {

	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String out_trade_no;
	private String out_refund_no;
	private int total_fee;
	private int refund_fee;
	private String 	op_user_id;
	private String refund_account;
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
	  requestStr.append("<out_refund_no><![CDATA[");
	  requestStr.append(out_refund_no);
	  requestStr.append("]]></out_refund_no>");
	  requestStr.append("<op_user_id><![CDATA[");
	  requestStr.append(op_user_id);
	  requestStr.append("]]></op_user_id>");
	  requestStr.append("<refund_fee><![CDATA[");
	  requestStr.append(refund_fee);
	  requestStr.append("]]></refund_fee>");
	  requestStr.append("<refund_account><![CDATA[");
	  requestStr.append(refund_account);
	  requestStr.append("]]></refund_account>");
	  requestStr.append("<total_fee><![CDATA[");
	  requestStr.append(total_fee);
	  requestStr.append("]]></total_fee>");
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
	public int getTotal_fee() {
		return total_fee;
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
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getRefund_account() {
		return refund_account;
	}
	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}
	
}
