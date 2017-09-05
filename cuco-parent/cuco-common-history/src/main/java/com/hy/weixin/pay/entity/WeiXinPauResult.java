package com.hy.weixin.pay.entity;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class WeiXinPauResult {
	
	
	private String return_code;
	
	private String return_msg;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	
	public String toXml(){
		
		StringBuffer buffer = new StringBuffer("<xml>");
		buffer.append("<return_code>");
		buffer.append("<![CDATA["+this.return_code+"]]>");
		buffer.append("</return_code>");
		buffer.append("<return_msg>");
		buffer.append("<![CDATA["+this.return_msg+"]]>");
		buffer.append("</return_msg>");
		buffer.append("</xml>");
		return buffer.toString();

	}
	
	
	

}
