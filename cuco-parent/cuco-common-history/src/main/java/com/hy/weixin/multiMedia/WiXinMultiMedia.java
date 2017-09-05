package com.hy.weixin.multiMedia;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class WiXinMultiMedia {
	
	protected static Logger logger = Logger.getLogger(WiXinMultiMedia.class);

	 /**
	 * 从微信服务器下载多媒体文件 返回文件名称
	 *
	 * @author qincd
	 * @date Nov 6, 2014 4:32:12 PM
	 */
	 static String download_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	 public static String downloadMediaFromWx(String accessToken,String mediaId,String fileSavePath) throws IOException {
	 if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(mediaId)) return null;
	 
	 logger.info(">>>>>mediaId<<<<<<" + mediaId);
	 HttpURLConnection conn = null;
	 String contentDisposition = null;
	 for(int i = 0; i<3; i++){
		 String requestUrl = download_media_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		 URL url = new URL(requestUrl);
		 conn = (HttpURLConnection) url.openConnection();
		 conn.setRequestMethod("GET");
		 conn.setDoInput(true);
		 conn.setDoOutput(true);
		 contentDisposition = conn.getHeaderField("Content-disposition");
		 if(StringUtils.isNotEmpty(contentDisposition)){
			 break;
		 }
	 }
	
	 logger.info(">>>>>contentDisposition<<<<<<" + contentDisposition);

	 
	 InputStream in = conn.getInputStream();
	 File dir = new File(fileSavePath);
	 if (!dir.exists()) {
	  dir.mkdirs();
	 }
	 if (!fileSavePath.endsWith("/")) {
	  fileSavePath += "/";
	 }
	  
	 String weixinServerFileName = contentDisposition.substring(contentDisposition.indexOf("filename")+10, contentDisposition.length() -1);
	 fileSavePath += weixinServerFileName; 
	 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileSavePath));
	 byte[] data = new byte[1024];
	 int len = -1;
	  
	 while ((len = in.read(data)) != -1) {
	  bos.write(data,0,len);
	 }
	  
	 bos.close();
	 in.close();
	 conn.disconnect();
	  
	 return weixinServerFileName;
	 }

}
