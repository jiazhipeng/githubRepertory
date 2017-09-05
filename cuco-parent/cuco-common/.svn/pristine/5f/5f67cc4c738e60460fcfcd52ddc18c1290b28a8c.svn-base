package cn.cuco.common.utils.fileUpload;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.cuco.constant.Constant;

public class FileUploadUtil {

	/**
	 * 
	* @Title: fileUpload 
	* @Description:   使用此方法需要注入 HttpServletRequest
	* @author jiaxiaoxian
	* @param file @RequestParam("file") MultipartFile file
	* @param request
	* @return   
	* @return String
	 */
	 public static String fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {  
		 	String filePath = "";
		 	Date now = new Date();
	        // 判断文件是否为空  
	        if (!file.isEmpty()) {  
	            try {  
	                // 文件保存路径  
	                filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/"  
	                        + now.getTime() + file.getOriginalFilename();  
	                File newFile =  new File(filePath);
	               
	            	if (!newFile.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
	    				// 如果目标文件所在的文件夹不存在，则创建父文件夹
	    				if (!newFile.getParentFile().mkdirs()) {// 判断创建目录是否成功
	    					return "";
	    				}
	    			}
	            	 // 转存文件  
	                file.transferTo(newFile);  
	            
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return Constant.domain + "upload/" + now.getTime() + file.getOriginalFilename();  
	    }  
}
