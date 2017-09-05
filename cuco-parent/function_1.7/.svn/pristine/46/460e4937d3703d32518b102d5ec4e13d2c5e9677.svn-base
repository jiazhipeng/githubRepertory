package com.hy.gcar.web.wechat.suggest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.entity.Suggest;
import com.hy.gcar.service.suggest.SuggestService;

/**微信意见建议controller
 * @author gbw
 */
@Controller
@RequestMapping("/wechat/suggest")
public class SuggestController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SuggestService suggestService;
	
	/**
	 * 创建意见建议
	 * @param suggest
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> suggestcreate(Suggest suggest){
		this.logger.info("创建意见建议---");
		 Map<String,Object> resultMap= new HashMap<String, Object>();
		try{
			 suggest.setCreated(new Date());
			 this.suggestService.createSuggest(suggest);
			 resultMap.put("result", true);
		}catch(Exception e){
			this.logger.error("创建意见建议改失败---"+e);
			resultMap.put("result",false);
		}
		return resultMap;
	}
}
