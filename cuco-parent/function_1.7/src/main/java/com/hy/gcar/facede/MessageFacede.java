package com.hy.gcar.facede;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.JsonUtil;
import com.hy.constant.Constant;
import com.hy.gcar.dao.MemberMapper;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Message;
import com.hy.gcar.entity.Message.ReceivingEquipment;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;
import com.hy.umeng.push.AndroidNotification;
import com.hy.umeng.push.PushClient;
import com.hy.umeng.push.android.AndroidCustomizedcast;
import com.hy.umeng.push.dao.MessageUmengMapper;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.ios.IOSCustomizedcast;


@Service
public class MessageFacede {

	protected Logger logger = LoggerFactory.getLogger(getClass());
//	@Autowired
//	public ItemMapper  itemMapper;
	@Autowired
	public MemberMapper  memberMapper;
//	@Autowired
//    public  CarOperateMapper carOperateMapper;
//    @Autowired
//    public   MemberPowerMapper memberPowerMapper;
    @Autowired
    public   MessageUmengMapper messageManagerMapper; 
    
   
	public Object getPushMessageList(String message,String type){
		//1. 校验sign
		String methodName = "getPushMessageList";
		Object result = null;
    	message = AppUtil.decode(message);
    	if(null == message){
    		result = new OpenResponse("false","参数解码错误",null,"100001");
    		logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
    		return result;
    	}
    	try{
    		JSONObject jsonParams = JSONObject.parseObject(message);
        	String token = jsonParams.getString("token");
        	String mobile = jsonParams.getString("mobile");
        	String sign = jsonParams.getString("sign");
        	int pageNum = jsonParams.getIntValue("pagenum");
        	String client = type;
        	
        	if(pageNum < 1){
        		pageNum = 1;
        	}
        	logger.info("用户【{}】执行({})"+"参数{}",mobile,methodName,jsonParams.toString());
        	boolean validate = AppUtil.validateBySign(mobile+Global.PWD_FOR_APP, sign);
    	 	if(validate == false){
     		   result = new OpenResponse("false","sign不匹配",null,"100002");
     		   logger.info("用户【{}】执行({})操作结束.....执行结果{}：",mobile,methodName,result);
     		   return result;
    	 	 }
    		//校验用户是否存在
			Member  mb  =new  Member();
			mb.setMobile(mobile);
    		List<Member> customerlist = memberMapper.selectByCondition(mb);
    	if(customerlist==null||customerlist.size()==0){
    			result = new OpenResponse("false","用户不存在",null,"100007");
      		   logger.info("用户【{}】执行({})操作结束.....执行结果{}：",mobile,methodName,result);
      		   return result;
    		}
    		//获取记录条数  根据用户id、订单类型、获取订单列表
    		MessageUmeng messageDto = new MessageUmeng();
    		messageDto.setAlias(customerlist.get(0).getId()+"");
    		if(ReceivingEquipment.ANDROID.getName().equals(client)){
    			messageDto.setReceivingEquipment(ReceivingEquipment.ANDROID.getIndex());
    		}else if(ReceivingEquipment.IOS.getName().equals(client)){
    			messageDto.setReceivingEquipment(ReceivingEquipment.IOS.getIndex());
    		}
    		int total = messageManagerMapper.selectByListCount(messageDto);
    		String status = "1";
    		int start = 0;
    		int preCountNum = 0;//已经显示记录数
			int nextCountNum = AppUtil.PAGE_NUM;//请求记录数
			boolean needSelect = false;
    		if(total > 0){
    			if(pageNum > 1){
    				start = AppUtil.PAGE_NUM * (pageNum-1);
    				preCountNum = (pageNum - 1) * AppUtil.PAGE_NUM;
    				nextCountNum = pageNum * AppUtil.PAGE_NUM;
    			}
    			if(preCountNum >= total){//已经加载完毕 无需查询
    				status = "2";
    			}else if(nextCountNum >= total){
    				status = "2";
    				needSelect = true;
    			}else if(nextCountNum < total){
    				status = "1";
    				needSelect = true;
    			}
    		}else{
    			//没有记录
    			status = "2";
    		}
    		JSONArray array = null;
    		if(needSelect){
    			
    			messageDto.setBeginPage(start);
    			messageDto.setPageSize(AppUtil.PAGE_NUM);
    			List<MessageUmeng> list = messageManagerMapper.selectByList(messageDto);
    			array = getChargingPushMessageJSONArray(list);
    		}else{
    			array = new JSONArray();
    		}
    		JSONObject result1 = new JSONObject();
    		result1.put("pageNum", pageNum);
    		result1.put("status", status);//1:加载成功   2：加载完毕
    		result1.put("data", array);
    		result = new OpenResponse("true","成功",result1,"100000");
  		   	logger.info("用户【{}】执行({})操作结束.....执行结果{}：",mobile,methodName,result);
  		   	return result;
    	}catch(Exception e){
    		result = new OpenResponse("false","出现异常",null,"100014"); 
  		   	logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
  		   	e.printStackTrace();
  		   	return result;
    	}
	}
	/*public String getPushMessageListMain(String mobile){
		String methodName = "getPushMessageListMain";
		String result = "";
    	//解析json数据
    	try{
		 	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    		Member  member =new  Member();
    		member.setMobile(mobile);
    	List<Member>   memberlist =	memberMapper.getMemberList(member);
    	 if(memberlist!=null&&memberlist.size()>0){
    			   member =  memberlist.get(0);
    			      CarOperate  caroperate = new CarOperate();  
    		          caroperate.setMemberId( member.getId());
    		      List<CarOperate>  caroperatelist  =   carOperateMapper.getCarOperateList(caroperate);
    				Map<String,String> map = new HashMap<String,String>();
    				map.put("mobile", member.getMobile());
    				map.put("sex", member.getSex()+"");//性别 -1未知;1:男;0:女;     name   sex   mobile    有无车    是否试驾过
    				map.put("name", member.getName());
    				map.put("city", member.getCityName());
    				map.put("type", member.getType()+"" );
    				map.put("hasCar",caroperatelist.size()>0?"1":"0");//1：有车   0：没车
    				map.put("isTryDriver", member.getIsTestDriver()+"");//1：试驾过   0：没试驾过
    				map.put("token", Identities.uuid2());
    				map.put("userId",member.getId()+"");//用户Id
    				map.put("image", Global.getConfig("basePath")+member.getImageUrl());//用户头像
    				return new OpenResponse("true","登录成功",map,"100000");
    	 }
			return new OpenResponse("true","登录成功","没有这个用户","100000");
    	}catch(Exception e){
    		result = new OpenResponse("false","出现异常",null,"100014"); 
  		   	logger.info("执行({})操作结束.....执行结果{}：",methodName,result);
  		   	return result;
    	}
	}*/
	
	private JSONArray getChargingPushMessageJSONArray(List<MessageUmeng> list) {
		JSONArray array = new JSONArray();
		for(MessageUmeng message : list){
			JSONObject json = new JSONObject();
			json.put("sendTime", message.getCreateTime());
			json.put("content", message.getMessageContent());
			array.add(json);
		}
		return array; 
	}
	
	
	
	
	
	/**
	 * android 单独推送
	 * 	 @param message Alias 用户ID   MessageContent: 消息内容            AliasType： 写死 app 端一致
	 * @param message
	 */
	public Map<String,Object> sendAndroidMessageAPush(Message message) throws Exception{
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(Constant.YONGHAOCHE_ANDROID_KEY,Constant.YONGHAOCHE_ANDROID_SECRET);
		// TODO Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setAlias(message.getAlias(),message.getAliasType());
		customizedcast.setTicker(message.getTicker());
		customizedcast.setTitle(message.getTitle());
		customizedcast.setText(message.getMessageContent());
		customizedcast.goCustomAfterOpen(JsonUtil.extractJson(message.getJsonMap()));
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		//customizedcast.setCustomField(custom);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		if(Constant.PUSH_IS_TEST_MODE){
			customizedcast.setTestMode();
		}else{
			customizedcast.setProductionMode();
		}
		PushClient client = new PushClient();
		return client.send(customizedcast);
	}
	
	
	
	/**
	 * ios单独推送
	 * @param message Alias 用户ID   MessageContent: 消息内容            AliasType： 写死 app 端一致
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> sendIosMessageAPush(Message message) throws Exception{
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(Constant.YONGHAOCHE_IOS_KEY,Constant.YONGHAOCHE_IOS_SECRET);
		// TODO Set your alias and alias_type here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setAlias(message.getAlias(), message.getAliasType());
		customizedcast.setAlert(message.getMessageContent());
		customizedcast.setBadge(1);
		customizedcast.setSound("default");
		
//		if(message.getJsonMap()!=null){
//			for (Map.Entry<String, Object> entry : message.getJsonMap().entrySet()) { 	  
//			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//				customizedcast.setCustomizedField(entry.getKey(), entry.getValue()+"");
//			}
//		}
		
		if(Constant.PUSH_IS_TEST_MODE){
			customizedcast.setTestMode();
		}else{
			customizedcast.setProductionMode();
		}
		PushClient client = new PushClient();
		return client.send(customizedcast);
		
		
	}
	
	
	
	
	
	
	
	
	
}
