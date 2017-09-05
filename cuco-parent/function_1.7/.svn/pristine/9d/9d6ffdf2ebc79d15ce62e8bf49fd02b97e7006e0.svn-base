package com.hy.gcar.web.app.mycars;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.gcar.entity.BasicGarageImage;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basicGarageImage.BasicGarageImageService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

@Controller
//@RequestMapping("/app/mycars")
public class MyCarsForAppController {
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private BasicGarageImageService basicGarageImageService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CarOperateService carOperateService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private ItemCartypeService itemCartypeService;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 我的车库列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/mycarsList")
	@ResponseBody
	public Object mycarsListForAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
		String mobile = params.getString("mobile");
		logger.info("ios 我的车库》》》》》》》》》》》》》》memberId="+memberId+">>>>>>>>>>>>>mobile="+mobile);	
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		Integer maxEnableCount = itemService.selectMaxEnableCount();//最大可用车位
		
		MemberItem memberItem =  new MemberItem();
		memberItem.setMemberId(Long.valueOf(memberId));
		memberItem = memberItemService.selectMyPowerList(memberItem);
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		
				
		//获取车位图片
		BasicGarageImage tcBasicGarageImage = new BasicGarageImage();
		List<BasicGarageImage> basic = basicGarageImageService.selectByCondition(tcBasicGarageImage);
		String enableImageUrl=null;
		String notEnableImageUrl=null;
		String preOrderImageUrl=null;
		
		if(CollectionUtils.isNotEmpty(basic)){
			for (int i = 0; i < basic.size(); i++) {
				if(basic.get(i).getType()==0){//不可用图片
					notEnableImageUrl=basic.get(i).getImage();
				}else if(basic.get(i).getType()==1){
					enableImageUrl=basic.get(i).getImage();//可用车位图片
				}else{
					//已预约车位图片
					preOrderImageUrl=basic.get(i).getImage();
				}
			}
		}
		//说明该会员没有购买套餐
		if(memberItem==null){
			returnMap.put("flag", "0");
			for (int i = 0; i < maxEnableCount; i++) {
				Map<String, Object> returnMap2 = new HashMap<String, Object>();
				returnMap2.put("carTypeImageUrl", notEnableImageUrl);
				returnMap2.put("status", "0");//待开启
				returnList.add(returnMap2);
			}
			returnMap.put("cartypes", returnList);
			return  new OpenResponse("true", "操作成功", returnMap, "100000");
		}
		
		List<PowerUsed> powerUseds = memberItem.getPowerUseds();
		
		
		for (PowerUsed powerUsed :powerUseds) {
			Map<String, Object> returnMap2 = new HashMap<String, Object>();
			//待使用
			CarType carType = carTypeService.findById(powerUsed.getCarTypeId());
			//已预约
			if(powerUsed.getUsedStatus()==0||powerUsed.getUsedStatus()==1||powerUsed.getUsedStatus()==2){
				returnMap2.put("carTypeImageUrl", preOrderImageUrl);
				returnMap2.put("status", "3");
				returnMap2.put("carTypeId", carType.getId()+"");
				returnMap2.put("brand", carType.getBrand());
				returnMap2.put("carType", carType.getCarType());
			}else {// 使用中
				returnMap2.put("status", "2");
				returnMap2.put("carTypeId", carType.getId()+"");
				returnMap2.put("brand", carType.getBrand());
				returnMap2.put("carType", carType.getCarType());
				CarOperate carOperate = carOperateService.findById(powerUsed.getCaroperateId());
				if(null!=carOperate){
					if(StringUtils.isBlank(carOperate.getImageUrl())){//车辆图片为空 则默认显示车型图片
						CarType carTypeUsed = carTypeService.findById(carOperate.getCarTypeId());
						returnMap2.put("carTypeImageUrl", carTypeUsed.getImageUrl());
					}else{
						returnMap2.put("carTypeImageUrl", carOperate.getImageUrl());
					}
					returnMap2.put("carTypeId",carOperate.getCarTypeId()+"");
					returnMap2.put("brand", carOperate.getCarBrand());
					returnMap2.put("carType", carOperate.getCarType());
				}
			}
			
			returnList.add(returnMap2);
		}
		
		
		
		//没有用车记录
		if(CollectionUtils.isEmpty(returnList)){
			for (int i = 0; i < memberItem.getEnableCount(); i++) {
				Map<String, Object> returnMap2 = new HashMap<String, Object>();
				returnMap2.put("carTypeImageUrl", enableImageUrl);
				returnMap2.put("status", "1");//待使用
				returnList.add(returnMap2);
			}
			
			
		}else{
			//有用车记录 且用车记录数小于可用车位数  则加上可用车位数
			if(returnList.size()<memberItem.getEnableCount()){
				int forcount=memberItem.getEnableCount()-returnList.size();
				for (int i = 0; i < forcount; i++) {
					Map<String, Object> returnMap2 = new HashMap<String, Object>();
					returnMap2.put("carTypeImageUrl", enableImageUrl);
					returnMap2.put("status", "1");//待使用
					returnList.add(returnMap2);
				}
			}
			
		}
		//未解锁车型
		int notEnable=  maxEnableCount-memberItem.getEnableCount();
		for (int i = 0; i < notEnable; i++) {
			Map<String, Object> returnMap2 = new HashMap<String, Object>();
			returnMap2.put("carTypeImageUrl", notEnableImageUrl);
			returnMap2.put("status", "0");//待开启
			returnList.add(returnMap2);
		}
		
		returnMap.put("memberItemId", memberItem.getId()+"");
		returnMap.put("cartypes", returnList);
		returnMap.put("itemId", memberItem.getItemId()+"");
		returnMap.put("itemName", memberItem.getItemName());
		returnMap.put("flag", "1");
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 我的车库列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/mycarsList")
	@ResponseBody
	public Object mycarsListForIos(@RequestParam(value = "message", required = true) String message){
		return mycarsListForAndroid(message);
	}
	/**
	 * 我的车库之套餐权益
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/carTypeDetail")
	@ResponseBody
	public Object carTypeDetail(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberId = params.getString("memberId");
//		String itemId = params.getString("itemId");
		String memberItemId = params.getString("id");
		String mobile = params.getString("mobile");
		logger.info("android 我的车库之权益信息》》》》》》》》memberItemId="+memberItemId+"》》》》》>>>>>>>>>>>>>mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "套餐权益ID不能为空！", null, "100001");
		}
		MemberItem memberItem = new MemberItem();
		memberItem.setId(Long.valueOf(memberItemId));
		memberItem = memberItemService.selectByMemberItemId(memberItem);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//查询共有人数
		MemberItemShare share = new MemberItemShare();
		share.setMemberItemId(memberItem.getId());
		List<MemberItemShare> shareList = memberItemShareService.selectByCondition(share);
		
		MemberItemShare memberShare = new MemberItemShare();
		memberShare.setMemberId(Long.valueOf(memberId));
		List<MemberItemShare> mshareList = memberItemShareService.selectByCondition(memberShare);
		if(CollectionUtils.isNotEmpty(mshareList)){
			returnMap.put("isMain", mshareList.get(0).getIsMain());
		}
		
		if(CollectionUtils.isNotEmpty(shareList)){
			logger.info("权益套餐id为"+memberItemId+"的共享人个数是》》"+shareList.size());
			returnMap.put("count", shareList.size()+"");
		}else{
			logger.info("权益套餐id为"+memberItemId+"的共享人个数是》》0");
			returnMap.put("count", "0");
		}
		/*List<Map<String, Object>> cartypes = new ArrayList<>();
		if(memberItem.getCartypes()!=null||memberItem.getCartypes().size()>0){
			for (MemberItemCartype item: memberItem.getCartypes()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("brand", item.getBrand());
				map.put("carType", item.getCarType());
				cartypes.add(map);
			}
		}*/
	
//		returnMap.put("cartypes",cartypes);
		returnMap.put("deposit", memberItem.getDeposit().toString());
		returnMap.put("balance", memberItem.getBalance().toString());
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	/**
	 * 我的车库之套餐权益
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/carTypeDetail")
	@ResponseBody
	public Object carTypeDetailForIos(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("id");
		String memberId = params.getString("memberId");
		String mobile = params.getString("mobile");
		logger.info("android 我的车库之权益信息》》》》》》》》memberItemId="+memberItemId+"》》》》》>>>>>>>>>>>>>mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "套餐权益ID不能为空！", null, "100001");
		}
		MemberItem memberItem = new MemberItem();
		memberItem.setId(Long.valueOf(memberItemId));
		memberItem = memberItemService.selectByMemberItemId(memberItem);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//查询共有人数
		MemberItemShare share = new MemberItemShare();
		share.setMemberItemId(memberItem.getId());
		List<MemberItemShare> shareList = memberItemShareService.selectByCondition(share);
		if(CollectionUtils.isNotEmpty(shareList)){
			logger.info("权益套餐id为"+memberItemId+"的共享人个数是》》"+shareList.size());
			returnMap.put("count", shareList.size()+"");
		}else{
			logger.info("权益套餐id为"+memberItemId+"的共享人个数是》》0");
			returnMap.put("count", "0");
		}
		MemberItemShare memberShare = new MemberItemShare();
		memberShare.setMemberId(Long.valueOf(memberId));
		List<MemberItemShare> mshareList = memberItemShareService.selectByCondition(memberShare);
		if(CollectionUtils.isNotEmpty(mshareList)){
			returnMap.put("isMain", mshareList.get(0).getIsMain());
		}
		List<Map<String, Object>> cartypes = new ArrayList<>();
		if(memberItem.getCartypes()!=null&&memberItem.getCartypes().size()>0){
			for (MemberItemCartype item: memberItem.getCartypes()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("brand", item.getBrand());
				map.put("carType", item.getCarType());
				cartypes.add(map);
			}
		}
		
		returnMap.put("cartypes",cartypes);
		returnMap.put("deposit", memberItem.getDeposit().toString());
		returnMap.put("balance", memberItem.getBalance().toString());
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	/**
	 * 跳转进入我的共用人列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/memberItemShare/toShareList")
	@ResponseBody
	public Object toShareListIOS(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		logger.info("IOS 进入共用人列表---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "套餐权益ID不能为空！", null, "100001");
		}
		
		MemberItemShare memberItemShare = new MemberItemShare();
		memberItemShare.setMemberItemId(Long.valueOf(memberItemId));
		List<MemberItemShare> memberItemShares = this.memberItemShareService.selectByCondition(memberItemShare);
		//结果返回
		List<Map<String, Object>> returnList = new LinkedList<>();
		for(MemberItemShare items: memberItemShares){
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("shareId", items.getId()+"");
			returnMap.put("memberId", items.getMemberId()+"");
			returnMap.put("isMain", items.getIsMain()+"");
			returnMap.put("isShare", items.getIsShare()+"");
			try {
				Member member = this.memberService.findMemberByID(items.getMemberId());
				returnMap.put("memberName", member.getName());
				returnMap.put("mobile", member.getMobile()+"");
			} catch (Exception e) {
				return  new OpenResponse("false", "操作失败", null, "100002");
			}
			if(1==items.getIsMain()){
				returnList.add(0, returnMap);
			}else{
				returnList.add(returnMap);
			}
		}
		
		
		return  new OpenResponse("true", "操作成功", returnList, "100000");
	}
	
	/**
	 * 跳转进入我的共用人列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/memberItemShare/toShareList")
	@ResponseBody
	public Object toShareListAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		logger.info("Android 进入共用人列表---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "套餐权益ID不能为空！", null, "100001");
		}
		
		MemberItemShare memberItemShare = new MemberItemShare();
		memberItemShare.setMemberItemId(Long.valueOf(memberItemId));
		List<MemberItemShare> memberItemShares = this.memberItemShareService.selectByCondition(memberItemShare);
		//结果返回
		List<Map<String, Object>> returnList = new LinkedList<>();
		for(MemberItemShare items: memberItemShares){
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("shareId", items.getId()+"");
			returnMap.put("memberId", items.getMemberId()+"");
			returnMap.put("isMain", items.getIsMain()+"");
			returnMap.put("isShare", items.getIsShare()+"");
			try {
				Member member = this.memberService.findMemberByID(items.getMemberId());
				returnMap.put("memberName", member.getName());
				returnMap.put("mobile", member.getMobile()+"");
			} catch (Exception e) {
				return  new OpenResponse("false", "操作失败", null, "100002");
			}
			if(1==items.getIsMain()){
				returnList.add(0, returnMap);
			}else{
				returnList.add(returnMap);
			}
		}
		
		
		return  new OpenResponse("true", "操作成功", returnList, "100000");
	}
	
	/**
	 * 检验用户是否被冻结
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/memberItemShare/checkMemberStatuts")
	@ResponseBody
	public Object checkMemberStatutsIOS(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		String memberId = params.getString("memberId");
		logger.info("校验用户是否被冻结 memberId"+memberId);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap=new HashMap<>();
		try {
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			if(member!=null ){
				if(member.getStatuts()==0){
					//被冻结
					returnMap.put("code", "0");
				}else{
					returnMap.put("code", "1");
				}
				return new OpenResponse("true", "操作成功", returnMap, "100000");
			}else{
				return new OpenResponse("false", "用户不存在", null, "100002");
			}
		}  catch (Exception e) {
			e.printStackTrace();
			return new OpenResponse("false", "操作失败", null, "100002");
		}
	}
	/**
	 * 检验用户是否被冻结
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/memberItemShare/checkMemberStatuts")
	@ResponseBody
	public Object checkMemberStatutsAndroid(@RequestParam(value = "message", required = true) String message){
		return checkMemberStatutsIOS(message);
	}
	
	
	/**
	 * 校验添加共用人
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/memberItemShare/addShareMember")
	@ResponseBody
	public Object addShareMemberIOS(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		String memberMobile = params.getString("memberMobile");
		String memberId = params.getString("memberId");
		String isShare = params.getString("isShare");
		logger.info("IOS 进入共用人列表---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberMobile)){
			return new OpenResponse("false", "公用人手机号不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "套餐权益ID不能为空！", null, "100001");
		}

		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(isShare)){
			return new OpenResponse("false", "是否能同时操作为空！", null, "100001");
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberItemShare memberItemShare = new MemberItemShare();
		//将参数放入对象
		memberItemShare.setMemberItemId(Long.valueOf(memberItemId));
		memberItemShare.setMemberId(Long.valueOf(memberId));
		memberItemShare.setMemberMobile(memberMobile);
		memberItemShare.setIsShare(Integer.parseInt(isShare));
		//来源安卓
		memberItemShare.setMemberType(5);
		
		map = this.memberItemShareService.createMemberItemShare(memberItemShare);
		
		if((boolean) map.get("result")){
			returnMap.put("share", map.get("share"));
			
		}else{
			return new OpenResponse("false", ""+map.get("msg"), null, "100001");
		}
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	/**
	 * 校验添加共用人
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/memberItemShare/addShareMember")
	@ResponseBody
	public Object addShareMemberAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
		String memberMobile = params.getString("memberMobile");
		String memberId = params.getString("memberId");
		String isShare = params.getString("isShare");
		logger.info("Android 进入共用人列表---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberMobile)){
			return new OpenResponse("false", "公用人手机号不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "套餐权益ID不能为空！", null, "100001");
		}

		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(isShare)){
			return new OpenResponse("false", "是否能同时操作为空！", null, "100001");
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberItemShare memberItemShare = new MemberItemShare();
		//将参数放入对象
		memberItemShare.setMemberItemId(Long.valueOf(memberItemId));
		memberItemShare.setMemberId(Long.valueOf(memberId));
		memberItemShare.setMemberMobile(memberMobile);
		memberItemShare.setIsShare(Integer.parseInt(isShare));
		//来源安卓
		memberItemShare.setMemberType(4);
		
		map = this.memberItemShareService.createMemberItemShare(memberItemShare);
		
		if((boolean) map.get("result")){
			returnMap.put("share", map.get("share"));
			
		}else{
			return new OpenResponse("false", ""+map.get("msg"), null, "100001");
		}
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	/**
	 * 修改共用人信息
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/memberItemShare/updateShareMember")
	@ResponseBody
	public Object updateShareMemberIOS(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemShareId = params.getString("id");
		String isShare = params.getString("isShare");
		String memberId = params.getString("memberId");
		String mobile = params.getString("mobile");
		logger.info("IOS 修改共用人---memberItemShareId="+memberItemShareId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemShareId)){
			return new OpenResponse("false", "权益共享ID不能为空！", null, "100001");
		}
		
		if(StringUtils.isBlank(isShare)){
			return new OpenResponse("false", "是否共享不能为空！", null, "100001");
		}
		
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		
		try {
			Member parentMember = this.memberService.findMemberByID(Long.parseLong(memberId));
			MemberItemShare memberItemS = new MemberItemShare();
			memberItemS.setId(Long.parseLong(memberItemShareId));
			memberItemS.setLasttimeModify(new Date());
			memberItemS.setModifierId(memberId);
			memberItemS.setModifier(parentMember.getName());
			memberItemS.setIsShare(Integer.parseInt(isShare));
			this.memberItemShareService.updateByPrimaryKeySelective(memberItemS);
			
		} catch (Exception e) {
			return new OpenResponse("false", "权益修改异常！", null, "100001");
		}
		
		return  new OpenResponse("true", "操作成功", null, "100000");
	}
	
	/**
	 * 修改共用人信息
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/memberItemShare/updateShareMember")
	@ResponseBody
	public Object updateShareMemberAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemShareId = params.getString("id");
		String isShare = params.getString("isShare");
		String memberId = params.getString("memberId");
		String mobile = params.getString("mobile");
		logger.info("Android 修改共用人---memberItemShareId="+memberItemShareId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemShareId)){
			return new OpenResponse("false", "权益共享ID不能为空！", null, "100001");
		}
		
		if(StringUtils.isBlank(isShare)){
			return new OpenResponse("false", "是否共享不能为空！", null, "100001");
		}
		
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "会员ID不能为空！", null, "100001");
		}
		
		try {
			Member parentMember = this.memberService.findMemberByID(Long.parseLong(memberId));
			MemberItemShare memberItemS = new MemberItemShare();
			memberItemS.setId(Long.parseLong(memberItemShareId));
			memberItemS.setLasttimeModify(new Date());
			memberItemS.setModifierId(memberId);
			memberItemS.setModifier(parentMember.getName());
			memberItemS.setIsShare(Integer.parseInt(isShare));
			this.memberItemShareService.updateByPrimaryKeySelective(memberItemS);
			
		} catch (Exception e) {
			return new OpenResponse("false", "权益修改异常！", null, "100001");
		}
		
		return  new OpenResponse("true", "操作成功", null, "100000");
	}
	
	/**
	 * 修改共用人信息
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/memberItemShare/deleteShareMember")
	@ResponseBody
	public Object deleteShareMemberIOS(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemShareId = params.getString("id");
		String mobile = params.getString("mobile");
		logger.info("IOS 删除共用人---memberItemShareId="+memberItemShareId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemShareId)){
			return new OpenResponse("false", "权益共享ID不能为空！", null, "100001");
		}
		try {
			this.memberItemShareService.deleteByPrimaryKey(Long.valueOf(memberItemShareId));
			
		} catch (Exception e) {
			return new OpenResponse("false", "权益修改异常！", null, "100001");
		}
		return  new OpenResponse("true", "操作成功", null, "100000");
	}
	
	/**
	 * 修改共用人信息
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/memberItemShare/deleteShareMember")
	@ResponseBody
	public Object deleteShareMemberAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemShareId = params.getString("id");
		String mobile = params.getString("mobile");
		logger.info("Android 删除共用人---memberItemShareId="+memberItemShareId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		if(StringUtils.isBlank(memberItemShareId)){
			return new OpenResponse("false", "权益共享ID不能为空！", null, "100001");
		}
		try {
			this.memberItemShareService.deleteByPrimaryKey(Long.valueOf(memberItemShareId));
			
		} catch (Exception e) {
			return new OpenResponse("false", "权益修改异常！", null, "100001");
		}
		return  new OpenResponse("true", "操作成功", null, "100000");
	}
	/**
	 * 权益车型列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/android/mycars/memberItemList")
	@ResponseBody
	public Object memberItemListAndroid(@RequestParam(value = "message", required = true) String message){
		// 解析json数据
		message = AppUtil.decode(message);
		JSONObject params = JSONObject.parseObject(message);
		String memberItemId = params.getString("memberItemId");
		String mobile = params.getString("mobile");
//		String memberId = params.getString("memberId");
		logger.info("权益车型列表---memberItemId="+memberItemId+"----mobile="+mobile);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		Map<String, Object> returnMap = new HashMap<>();
		try {
			MemberItem memberItem = memberItemService.findById(Long.valueOf(memberItemId));
			//查询权益下的所有车型
			ItemCartype itemCartype = new ItemCartype();
			itemCartype.setItemId(memberItem.getItemId());
			List<ItemCartype> itemCartypeList = itemCartypeService.selectByCondition(itemCartype);
			List<Long> ownCartypeIds = new ArrayList<>();
			for (ItemCartype itemCar : itemCartypeList) {
				ownCartypeIds.add(itemCar.getCartypeId().longValue());
			}
			
//			 '适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；',
//			SUV、商务轿车、新能源、MPV、炫目轿跑、性能跑车；
			JSONArray ownList  = getSceneCartype(ownCartypeIds);
			returnMap.put("ownList", ownList);
			//查询所有的已上架的套餐
			 Item item = new Item();
			 item.setStatus(1);
			 List<Item> itemList = this.itemService.selectCarTypeList(item);
			 List<Long> notOwnCartypeIds = new ArrayList<>();
			 //查询非权益下的其他车型
			 for(Item model : itemList){
				 for(CarType cartype : model.getCarTypes()){
					 if(!ownCartypeIds.contains(cartype.getId().longValue())&&!notOwnCartypeIds.contains(cartype.getId().longValue())){
						 notOwnCartypeIds.add(cartype.getId());
					 }
				 }
			}
			//封装非权益车型
			 JSONArray notOwnList  = getSceneCartype(notOwnCartypeIds);
			 returnMap.put("notOwnList", notOwnList);
		} catch (Exception e) {
			return new OpenResponse("false", "获取权益车型失败！", null, "100001");
		}
		return  new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	private JSONArray getSceneCartype(List<Long> notOwnCartypeIds) {
		JSONArray arr = new JSONArray();
		 List<CarType> carTypes0 = null;
		List<CarType> carTypes1 = null;
		List<CarType> carTypes2 = null;
		List<CarType> carTypes3 = null;
		List<CarType> carTypes4 = null;
		List<CarType> carTypes5 = null;
		JSONObject json0 = null;
		JSONObject json1 = null;
		JSONObject json2 = null;
		JSONObject json3 = null;
		JSONObject json4 = null;
		JSONObject json5 = null;
		
		for (Long cartypeId : notOwnCartypeIds) {
			CarType cart = carTypeService.findById(cartypeId);
//			 '适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；',

			if(0==cart.getScene()){
				 carTypes0 = addCarType(carTypes0, cart);
				 json0 = jsonPut(carTypes0, json0,"限行无忧");
//				 map.put("0", carTypes0);
			 }
			 if(1==cart.getScene()){
				 carTypes1 = addCarType(carTypes1, cart);
				 json1 = jsonPut(carTypes1, json1,"尊贵出行");
//				 map.put("1", carTypes1);
			 }
			 if(2==cart.getScene()){
				 carTypes2 = addCarType(carTypes2, cart);
				 json2 = jsonPut(carTypes2, json2,"畅享旅途");
//				 map.put("2", carTypes2);
			 }
			 if(3==cart.getScene()){
				 carTypes3 = addCarType(carTypes3, cart);
				 json3 = jsonPut(carTypes3, json3,"豪华商务");
//				 map.put("3", carTypes3);
			 }
			 if(4==cart.getScene()){
				 carTypes4 = addCarType(carTypes4, cart);
				 json4 = jsonPut(carTypes4, json4,"彰显个性");
//				 map.put("4", carTypes4);
			 }
			 if(5==cart.getScene()){
				 carTypes5 = addCarType(carTypes5, cart);
				 json5 = jsonPut(carTypes5, json5,"速度激情");
//				 map.put("5", carTypes5);
			 }
		}
//		 '适用场景 0:新能源；1:商务轿车；2:SUV；3:MPV；4:炫目轿跑；5:性能跑车；',
//		SUV、商务轿车、新能源、MPV、炫目轿跑、性能跑车；排序
		jsonArrayAdd(arr, json0);
		jsonArrayAdd(arr, json1);
		jsonArrayAdd(arr, json2);
		jsonArrayAdd(arr, json3);
		jsonArrayAdd(arr, json4);
		jsonArrayAdd(arr, json5);
//		ownList.add(map);
		return arr;
	}
	private void jsonArrayAdd(JSONArray arr, JSONObject json) {
		if(json!=null){
			arr.add(json);
		}
	}
	private JSONObject jsonPut(List<CarType> carTypes, JSONObject json,String type) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (CarType cartype : carTypes) {
			Map<String, Object> map =  new HashMap<String, Object>();
			map.put("brand", cartype.getBrand());
			map.put("carType", cartype.getCarType());
			map.put("cartypeId", cartype.getId()+"");
			map.put("dayPrice", cartype.getDayPrice());
			map.put("store", cartype.isStore());
			list.add(map);
		}
		if(json==null){
			 json= new JSONObject();
			 json.put("type", type);
			 json.put("data", list);
		 }else{
			 json.put("data", list);
		 }
		return json;
	}
	private List<CarType> addCarType(List<CarType> carTypes, CarType cart) {
		if(carTypes==null){
			 carTypes=new ArrayList<>();
		 }
		 carTypes.add(cart);
		return carTypes;
	}
	
	/**
	 * 权益车型列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/gcarapp/ios/mycars/memberItemList")
	@ResponseBody
	public Object memberItemListIos(@RequestParam(value = "message", required = true) String message){
		return memberItemListAndroid(message);
	}
	
	
}
