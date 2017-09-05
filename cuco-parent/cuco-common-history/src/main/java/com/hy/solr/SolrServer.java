package com.hy.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/** 
 * @author  zhipeng
 * @date 创建时间：2015-10-10 下午12:15:35 
 * @parameter   
 * @return  
 */
public class SolrServer {
	
	 private static Logger log = Logger.getLogger(SolrServer.class);
	 
	 private static Map<Object, Object> proMap = new HashMap<Object, Object>();
	 
    public static SolrClient server=null;
    
    static{
        Properties properties = new Properties();
        try {
            properties.load(SolrServer.class
                    .getResource("/solrparams.properties").openStream());
        } catch (IOException e) {
            log.error("IOException has been cactched SolrServer solrparams.properties读取配置错误", e);
        }
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            //log.debug("---> key :" +key +" : value" +value);
            proMap.put(key, value);
        }
    }
    
    public static synchronized SolrClient getInstance() {
    	 if (server == null){
    		 String solrurl = (String) proMap.get("solrurl");
    	     server = new HttpSolrClient(solrurl);
          }
          return server;
    }
  
   /*     private static SolrServer solrServer = null;
       
        private static HttpSolrServer server=null;
         
        private static Map<Object, Object> proMap = new HashMap<Object, Object>();
 
        private static Logger log = Logger.getLogger(SolrServer.class);
         
         
        public static synchronized SolrServer getInstance() {
            if (solrServer==null){
               solrServer=new SolrServer();
            }
            return solrServer;
        }
        static{
            Properties properties = new Properties();
            try {
                properties.load(SolrServer.class
                        .getResource("/solrparams.properties").openStream());
            } catch (IOException e) {
                log.error("IOException has been cactched SolrServer solrparams.properties读取配置错误", e);
            }
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                //log.debug("---> key :" +key +" : value" +value);
                proMap.put(key, value);
            }
        }
        public static HttpSolrServer getServer(){
             try {  
                if(server != null && server.getBaseURL() != null){
                    log.debug("加载solr的URL:"+server.getBaseURL());
                }else{
                      String solrurl=(String) proMap.get("solrurl");
                      server = new HttpSolrServer(solrurl);
                      server.setSoTimeout(1000*60);  // socket read timeout
                      server.setConnectionTimeout(1000*60);  //1分钟的提交时间 防止提交超时
                      server.setDefaultMaxConnectionsPerHost(100);
                      server.setMaxTotalConnections(100);
                      server.setFollowRedirects(false);  // defaults to false
                      //allowCompression defaults to false.
                      //Server side must support gzip or deflate for this to have any effect.
                      server.setAllowCompression(true);
                      server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.               
                }
            } catch (Exception e) {
                log.debug("SolrServer getServer 获取solr服务错误"+e);
            }
            return server;
        }*/
         
}
