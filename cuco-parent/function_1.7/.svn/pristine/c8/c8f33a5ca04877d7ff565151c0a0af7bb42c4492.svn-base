package com.hy.gcar.web.app.message;

import com.hy.gcar.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.facede.MessageFacede;
@Controller
public class SystemMessageController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MessageFacede messageFacede;
	
	@RequestMapping("/gcarapp/ios/pushmessagefacede/getpushmessagelist")
	@ResponseBody
	public Object getAllItemListForIOS(@RequestParam(value = "message", required = true) String message){
		
		return messageFacede.getPushMessageList(message, Message.ReceivingEquipment.IOS.getName());

	}
	
	@RequestMapping("/gcarapp/android/pushmessagefacede/getpushmessagelist")
	@ResponseBody
	public Object getAllItemListForAndroid(@RequestParam(value = "message", required = true) String message){
		
		return messageFacede.getPushMessageList(message, Message.ReceivingEquipment.ANDROID.getName());
	}
	
	
	
	
}
