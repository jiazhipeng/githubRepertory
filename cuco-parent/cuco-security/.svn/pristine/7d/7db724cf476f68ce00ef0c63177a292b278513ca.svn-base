package com.techstar.gcar.test.service;


import org.junit.Test;

import com.hy.weixin.entity.WxBtnMsgBack;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class XmlTest {
	
	@Test
	public void xmlToObject(){
		
//		Map<String,String> param = Maps.newHashMap();
//		param.put("ToUserName", "ToUserName");
//		param.put("FromUserName", "FromUserName");
//		param.put("CreateTime", "CreateTime");
//		param.put("MsgType", "MsgType");
//		param.put("Content", "Content");
		WxBtnMsgBack x = new WxBtnMsgBack();
		x.setToUserName("ToUserName");
		x.setFromUserName("fromUserName");
		x.setCreateTime("121212");
		x.setMsgType("3434343");
		x.setContent("2323232");
		
		XStream xStream = new XStream();  
        //xStream.autodetectAnnotations(false);  
        String xml = xStream.toXML(x);
        System.out.println(xml);  
	}
	
	@XStreamAlias("xml")
	public class xml{
		public String ToUserName;
		public String FromUserName;
		public String CreateTime;
		
		public String MsgType;
		
		public String Content;

		public String getToUserName() {
			return ToUserName;
		}

		public void setToUserName(String toUserName) {
			ToUserName = toUserName;
		}

		public String getFromUserName() {
			return FromUserName;
		}

		public void setFromUserName(String fromUserName) {
			FromUserName = fromUserName;
		}

		public String getCreateTime() {
			return CreateTime;
		}

		public void setCreateTime(String createTime) {
			CreateTime = createTime;
		}

		public String getMsgType() {
			return MsgType;
		}

		public void setMsgType(String msgType) {
			MsgType = msgType;
		}

		public String getContent() {
			return Content;
		}

		public void setContent(String content) {
			Content = content;
		}
		
		
		
		
		
	}

}
