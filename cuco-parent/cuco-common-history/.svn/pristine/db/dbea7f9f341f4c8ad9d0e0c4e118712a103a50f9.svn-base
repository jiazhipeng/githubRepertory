package com.hy.weixin.entity;

import java.util.Arrays;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("xml")
public class WxLinkBack {
	
	/**
	 * 接收方微信号
	 */
	@XStreamAlias("ToUserName") 
	private String toUserName;
	/**
	 * 发送方微信号，若为普通用户，则是一个OpenID
	 */
	@XStreamAlias("FromUserName") 
	private String fromUserName;
	/**
	 * 消息创建时间
	 */
	@XStreamAlias("CreateTime") 
	private String createTime;
	/**
	 * 消息类型，link
	 */
	@XStreamAlias("MsgType") 
	private String msgType;
	
	@XStreamAlias("ArticleCount") 
	private String articleCount;

	@XStreamAlias("Articles") 
	private WxArticlesBack articles;
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	
	public WxArticlesBack getArticles() {
		return articles;
	}

	public void setArticles(WxArticlesBack articles) {
		this.articles = articles;
	}

	public static void main(String[] args) {
		WxLinkBack x = new WxLinkBack();
		x.setToUserName("ToUserName");
		x.setFromUserName("fromUserName");
		x.setCreateTime("121212");
		x.setMsgType("news");
		x.setArticleCount("1");
		
		WxNewsBack news = new WxNewsBack();
		news.setDescription("一个给聪明人看的h5");
		news.setTitle("一个给聪明人看的h5");
		news.setPicUrl("http://1mobility.cn/newV1/images/page.jpg");
		news.setUrl("http://1mobility.cn/newV1/index.html");
		
		WxArticlesBack wxArticlesBack= new WxArticlesBack();
		wxArticlesBack.setArticles(Arrays.asList(news));
		
		x.setArticles(wxArticlesBack);
		
		XStream xStream = new XStream(new DomDriver());  
		xStream.autodetectAnnotations(true);  
        String xml = xStream.toXML(x);
        System.out.println(xml);  
	}
	

}
