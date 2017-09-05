package cn.cuco.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 从xmlStr转换到Map<String,Object>
* description：    
* author：1mobility    
* time：2017年2月20日 下午4:27:16    
* 修改时间：2017年2月20日 下午4:27:16    
* 修改备注：
 */
public class XMLParser {

	/**
	 * 
	* @Description:从xmlStr转换到Map<String,Object>   
	* @author:1mobility  
	* @time:2017年2月20日 下午4:27:37   
	* @return String
	 */
	public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
		 //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder = factory.newDocumentBuilder();
		 InputStream is =  WechatUrl.getStringStream(xmlString);
		 Document document = builder.parse(is);
		 //获取到document里面的全部结点
		 NodeList allNodes = document.getFirstChild().getChildNodes();
		 Node node;
		 Map<String, Object> map = new HashMap<String, Object>();
		 int i=0;
		 while (i < allNodes.getLength()) {
		  node = allNodes.item(i);
		  if(node instanceof Element){
		   map.put(node.getNodeName(),node.getTextContent());
		  }
		  i++;
		 }
		 return map;
		}
}
