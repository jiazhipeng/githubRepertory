package com.hy.gcar.common.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.AccessTokenUtils;
import com.hy.common.utils.DesUtil;
import com.hy.weixin.entity.Share;

@Controller
@RequestMapping("gcar/common")
public class CommonController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/wxshare")
	public @ResponseBody Map<String, String> getWxShare(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
		   @RequestParam(value = "url") String url) throws Exception {
		String s = null;
		try {
			s = new String(request.getParameter("url").getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String ticket = AccessTokenUtils.getTicket();
		Map<String, String> ret = Share.sign(ticket.toString(), s);
		logger.info("============index========jsapi_ticket=========>>" + ret.get("jsapi_ticket"));
		logger.info("============index========appId=========>>" + ret.get("appId"));
		logger.info("============index========url=========>>" + s);
		logger.info("============index========nonceStr=========>>" + ret.get("nonceStr"));
		logger.info("============index========timestamp=========>>" + ret.get("timestamp"));
		logger.info("============index========signature=========>>" + ret.get("sign"));
		logger.info("============index========link=========>>" + ret.get("link"));
		//openResponse.setUserdata(ret);
		// mv.addAllObjects(ret);
		 return ret;

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/decryptParameter")
	public @ResponseBody  Map<String,Object> decryptParameter(String salePram) throws UnsupportedEncodingException{
		 
		 Map<String,Object> param =  null;
		 if(StringUtils.isNotBlank(salePram)){
			 
			 this.logger.info("解码器前的" + salePram);
			 salePram = URLDecoder.decode(salePram,"UTF-8");
			 this.logger.info("解码后的"+ salePram);
			 String json  = null;
			 try {
				 json = DesUtil.decrypt(salePram, "hycx!@#$");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 logger.info("json>>>>>>" +  json);
			 param = JSONObject.parseObject(json, Map.class);
		 }
		 return param;
		
	}
}
