package com.hy.weixin.utils;




import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
/*import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;*/
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.hy.weixin.http.HttpClientConnectionManager;












public class GetWxOrderno
{
	protected static Logger logger = Logger.getLogger(GetWxOrderno.class);  

  public static DefaultHttpClient httpclient;

  static
  {
    httpclient = new DefaultHttpClient();
    httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
  }
  
  


  @SuppressWarnings({ "deprecation", "unchecked" })
public static Map<String,Object> getMap(String url,String xmlParam){
	  DefaultHttpClient client = new DefaultHttpClient();
	  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	  Map<String,Object> map = null;
     try {
		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
		 HttpResponse response = httpclient.execute(httpost);
	     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
	     
	     logger.info("--------------jsonStr-------------" + jsonStr);
	     
	    if(jsonStr.indexOf("FAIL")!=-1){
	    	return null;
	    }
		map = doXMLParse(jsonStr);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		client.close();
	}
	return map;
  }
  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
  public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
  
}