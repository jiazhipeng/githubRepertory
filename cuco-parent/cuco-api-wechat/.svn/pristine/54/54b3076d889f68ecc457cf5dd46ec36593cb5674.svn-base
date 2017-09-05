package cn.cuco.controller.upload;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.cuco.common.utils.fileUpload.FileUploadUtil;

@RestController
public class UploadController {

	@Autowired  
	private HttpServletRequest request; 
	
	@RequestMapping("fileUpload")  
	public Object fileUpload(@RequestParam("file") MultipartFile file){
		return FileUploadUtil.fileUpload(file, request);
	}
}
