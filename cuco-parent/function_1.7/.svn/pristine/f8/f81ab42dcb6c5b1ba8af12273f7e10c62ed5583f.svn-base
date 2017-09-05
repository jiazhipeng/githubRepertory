package com.hy.gcar.web.wechat.myCars;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.CookieUtils;
import com.hy.common.utils.DateUtils;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.constant.Constant;
import com.hy.gcar.entity.BasicGarageImage;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.basicGarageImage.BasicGarageImageService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.powerUserd.PowerUsedService;

@Controller
@RequestMapping("/wechat/mycars")
public class MyCarsForWechatController {
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BasicGarageImageService basicGarageImageService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private CarTypeService carTypeService;
	@Autowired
	private PowerUsedService powerUsedService;
	@Autowired
	private CarOperateService carOperateService ;
	@Autowired
	private ButlerTaskService butlerTaskService ;
	@Autowired
	private ButlerTaskStatusLogService butlerTaskStatusLogService ;
	@Autowired
	private MemberItemCartypeService memberItemCartypeService ;
	@Autowired
	private BasicThresholdService basicThresholdService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCartypeService itemCartypeService;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 我的车库列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/mycarsList")
	public String mycarsList(MemberItem memberItem,ModelMap modelMap){
		ParamVerifyUtil.paramNotNull(memberItem);
//		ParamVerifyUtil.paramNotNull(memberItem.getMemberId(),"会员ID不能为空！");
		//跳登录页面
		Long memberId = memberItem.getMemberId();
		modelMap.put("memberId",memberId);
		if(null ==memberItem.getMemberId()){
			return "redirect:/wechat/login";
		}
		

		Integer maxEnableCount = itemService.selectMaxEnableCount();//最大可用车位
//		memberItem.setMaxEnableCount(maxEnableCount);
//		modelMap.put("maxEnableCount", maxEnableCount+"");
		
		
		memberItem = memberItemService.selectMyPowerList(memberItem);
		//说明该会员没有购买套餐
//		if(memberItem==null){
//			modelMap.put("flag", 0);
//			return "gcar/mycar/mycar";
//		}
//		Integer enableCount = memberItem.getEnableCount();
//		modelMap.put("enableCount", enableCount);
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
		
		if(memberItem==null){
			modelMap.put("flag", "0");
			for (int i = 0; i < maxEnableCount; i++) {
				Map<String, Object> returnMap2 = new HashMap<String, Object>();
				returnMap2.put("carTypeImageUrl", notEnableImageUrl);
				returnMap2.put("status", "0");//待开启
				returnList.add(returnMap2);
			}
			modelMap.put("cartypes", returnList);
			return "gcar/mycar/mycar";
		}
		
		
		List<PowerUsed> powerUseds = memberItem.getPowerUseds();
		
		for (PowerUsed powerUsed :powerUseds) {
			Map<String, Object> returnMap2 = new HashMap<String, Object>();
			//待使用
			CarType carType = carTypeService.findById(powerUsed.getCarTypeId());
		
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
		
		modelMap.put("memberItemId", memberItem.getId()+"");
		modelMap.put("cartypes", returnList);
		modelMap.put("itemId", memberItem.getItemId()+"");
		modelMap.put("itemName", memberItem.getItemName());
		modelMap.put("flag", "1");
		
		return "gcar/mycar/mycar";
	}
	
	/**
	 * 我的车库之套餐权益
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/carTypeDetail")
	public String carTypeDetail(MemberItem memberItem,ModelMap modelMap){
		ParamVerifyUtil.paramNotNull(memberItem);
		ParamVerifyUtil.paramNotNull(memberItem.getId(),"权益ID不能为空！");
		ParamVerifyUtil.paramNotNull(memberItem.getMemberId(),"会员ID不能为空！");
		Long memberId = memberItem.getMemberId();
		modelMap.put("memberId", memberId);
		
		MemberItem item = new MemberItem();
		item.setId(memberItem.getId());
		memberItem = memberItemService.selectByMemberItemId(item);
		
		MemberItemShare share = new MemberItemShare();
		share.setMemberItemId(memberItem.getId());
		List<MemberItemShare> shareList = memberItemShareService.selectByCondition(share);
		if(CollectionUtils.isNotEmpty(shareList)){
			modelMap.put("count", shareList.size());
		}else{
			modelMap.put("count", "0");
		}
		
		MemberItemShare memberShare = new MemberItemShare();
		memberShare.setMemberId(memberId);
		List<MemberItemShare> mshareList = memberItemShareService.selectByCondition(memberShare);
		if(CollectionUtils.isNotEmpty(mshareList)){
			modelMap.put("isMain", mshareList.get(0).getIsMain());
		}
		
//		Map<String, Object> returnMap = new HashMap<String, Object>();
		/*List<Map<String, Object>> cartypes = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(memberItem.getCartypes())){
			for (MemberItemCartype itemcartype: memberItem.getCartypes()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("brand", itemcartype.getBrand());
				map.put("carType", itemcartype.getCarType());
				cartypes.add(map);
			}
		}*/
		modelMap.put("memberItemId", memberItem.getId());
//		modelMap.put("cartypes",cartypes);
		modelMap.put("deposit", memberItem.getDeposit());
		modelMap.put("balance", memberItem.getBalance());
		return "gcar/mycar/myPower";
	}
	/**
	 * 跳转权益余额
	 * @param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/toMyBalance")
	public String toMyBalance(Long memberItemId,ModelMap modelMap){
		ParamVerifyUtil.paramNotNull(memberItemId);

		/*会员权益信息*/
		MemberItem memberItem = memberItemService.findById(memberItemId);
		ParamVerifyUtil.paramNotNull(memberItem,"会员权益不存在");
		modelMap.put("memberItem",memberItem);

		/*余额日志*/
		MemberAccountLog searchMemberAccountLog = new MemberAccountLog();
		searchMemberAccountLog.setMemberItemId(memberItemId);
		searchMemberAccountLog.setOrderByType(1);
		searchMemberAccountLog.setSearchAccountType("balance");

		List<MemberAccountLog>  memberAccountLogs = memberAccountLogService.selectByCondition(searchMemberAccountLog);
		for(MemberAccountLog memberAccountLog : memberAccountLogs){
			/*设置用车记录*/
			Long powerUsedId = memberAccountLog.getPowerUsedId();
			if(powerUsedId!=null){
				PowerUsed powerUsed = powerUsedService.findById(powerUsedId);
				memberAccountLog.setPowerUsed(powerUsed);
				/*车型*/
				if(powerUsed==null){
					continue;
				}
				CarType carType = this.getCarTypeByPowerUsed(powerUsed);
				if(carType==null){
					continue;
				}
				powerUsed.setBrand(carType.getBrand());
				powerUsed.setCarType(carType.getCarType());
			}
		}

		modelMap.put("memberAccountLogs",memberAccountLogs);

		return "gcar/mycar/myBalance";
	}

	private CarType getCarTypeByPowerUsed(PowerUsed powerUsed) {
		Long carTypeId = powerUsed.getChangeCarTypeId()!=null ? powerUsed.getChangeCarTypeId() : powerUsed.getCarTypeId();
		if(carTypeId==null){
			return null;
		}
		return carTypeService.findById(carTypeId);
	}

	/**
	 * 跳转押金
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/toMyDeposit")
	public String toMyDeposit(Long memberItemId,ModelMap modelMap){
		ParamVerifyUtil.paramNotNull(memberItemId);

		/*会员权益信息*/
		MemberItem memberItem = memberItemService.findById(memberItemId);
		ParamVerifyUtil.paramNotNull(memberItem,"会员权益不存在");
		modelMap.put("memberItem",memberItem);

		/*余额日志*/
		MemberAccountLog searchMemberAccountLog = new MemberAccountLog();
		searchMemberAccountLog.setMemberItemId(memberItemId);
		searchMemberAccountLog.setOrderByType(1);
		searchMemberAccountLog.setSearchAccountType("deposit");

		List<MemberAccountLog>  memberAccountLogs = memberAccountLogService.selectByCondition(searchMemberAccountLog);
		for(MemberAccountLog memberAccountLog : memberAccountLogs){
			/*设置用车记录*/
			Long powerUsedId = memberAccountLog.getPowerUsedId();
			if(powerUsedId!=null){
				PowerUsed powerUsed = powerUsedService.findById(powerUsedId);
				memberAccountLog.setPowerUsed(powerUsed);
				/*车型*/
				if(powerUsed==null){
					continue;
				}
				CarType carType = this.getCarTypeByPowerUsed(powerUsed);
				if(carType==null){
					continue;
				}
				powerUsed.setBrand(carType.getBrand());
				powerUsed.setCarType(carType.getCarType());
			}
		}

		modelMap.put("memberAccountLogs",memberAccountLogs);
		return "gcar/mycar/myDeposit";
	}
	/**
	 * 跳转用车记录
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/toMyUsedLog")
	public String toMyUsedLog(PowerUsed powerUsed,ModelMap modelMap){
		modelMap.put("powerUserList", powerUsedService.selectPowerUsedListOfStatus(powerUsed));
		return "gcar/mycar/myCarUsedLog";
	}
	
	/**
	 * 跳转用车记录详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/toMyUsedLogView")
	public String toMyUsedLogView(PowerUsed powerUsed,ModelMap modelMap){
		modelMap.put("item",powerUsedService.selectPowerUsed(powerUsed));
		return "gcar/mycar/myCarUsedLogView";
	}
	
	/**
	 * 跳转我的共有人页面
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/toMyShareDetail")
	public String toMyShareDetail(MemberItemShare itemShare,ModelMap modelMap) throws Exception{
		ParamVerifyUtil.paramNotNull(itemShare);
		ParamVerifyUtil.paramNotNull(itemShare.getMemberItemId(),"权益ID不能为空！");
		//带入当前用户的memberId
		modelMap.put("memberId", itemShare.getMemberId());
		MemberItemShare itemShar = new MemberItemShare();
		itemShar.setMemberItemId(itemShare.getMemberItemId());
		List<MemberItemShare> shareList = memberItemShareService.selectByCondition(itemShar);
		List<MemberItemShare> returnList = new LinkedList<>();
		for (MemberItemShare memberItemShare : shareList) {
			Member member = memberService.findMemberByID(memberItemShare.getMemberId());
			memberItemShare.setMember(member);
			if(memberItemShare.getIsMain()==1){
				returnList.add(0, memberItemShare);
				modelMap.put("parentMemberId", memberItemShare.getParentMemberId());
				modelMap.put("memberItemId", memberItemShare.getMemberItemId());
			}else{
				returnList.add(memberItemShare);
			}
		}
		
		
		modelMap.put("shareList", returnList);
		return "gcar/mycar/myShareDetail";
	}
	/**
	 * 跳转添加共有人
	 * @param itemShare
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/toAddShareMember")
	public String toAddShareMember (MemberItemShare itemShare,ModelMap modelMap){
		
		return "gcar/mycar/addShareMember";
	}
	/**
	 * 添加共有人
	 * @param itemShare
	 * @return
	 */
	@RequestMapping(value="/addShareMember")
	@ResponseBody
	public Map<String, Object> addShareMember (MemberItemShare itemShare){
		ParamVerifyUtil.paramNotNull(itemShare);
		ParamVerifyUtil.paramNotNull(itemShare.getMemberMobile()," 添加公用人手机号不能为空！");
		ParamVerifyUtil.paramNotNull(itemShare.getMemberItemId(),"权益ID不能为空！");
		ParamVerifyUtil.paramNotNull(itemShare.getMemberId(),"权益所属父ID不能为空！");
		ParamVerifyUtil.paramNotNull(itemShare.getIsShare(),"是否可以同时操作同一辆车！");
		Map<String, Object> map = new HashMap<>();
		try {
			//来源微信
			itemShare.setMemberType(2);
			map = memberItemShareService.createMemberItemShare(itemShare);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	/**
	 * 修改是否可同时操作一辆车
	 * @param itemShare
	 * @return
	 */
	@RequestMapping(value="/updateShareMemberIsShare")
	@ResponseBody
	public Map<String, Object> updateShareMemberIsShare (MemberItemShare itemShare){
		ParamVerifyUtil.paramNotNull(itemShare);
		ParamVerifyUtil.paramNotNull(itemShare.getId(),"ID不能为空！");
		Map<String, Object> map = new HashMap<>();
		try {
			memberItemShareService.updateByPrimaryKeySelective(itemShare);
			map.put("isSuccess", true);
		} catch (Exception e) {
			map.put("isSuccess", false);
			e.printStackTrace();
		}
		
		return map;
	}
	/**
	 * 删除共有人
	 * @param itemShare
	 * @return
	 */
	@RequestMapping(value="/deleteShare")
	@ResponseBody
	public Map<String, Object> deleteShare (MemberItemShare itemShare){
		ParamVerifyUtil.paramNotNull(itemShare);
		ParamVerifyUtil.paramNotNull(itemShare.getId(),"ID不能为空！");
		Map<String, Object> map = new HashMap<>();
		try {
			memberItemShareService.deleteByPrimaryKey(itemShare.getId());
			map.put("isSuccess", true);
		} catch (Exception e) {
			map.put("isSuccess", false);
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	/**
	 * 非空车位发起用车
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/userCar")
	@ResponseBody
	public Object myCarsUseCarForAndroid(HttpServletRequest request,String index){
		// 解析json数据
		logger.info("---调用----非空车位发起用车------");
		
		ParamVerifyUtil.paramNotNull(index,"车位id不能为空");
		
		String openidKey=Constant.load.getProperty("openidKey");
		String openid = CookieUtils.getCookie(request, openidKey);
//		 openid = "oFCx8wJtYJo4TYTRwz8JAukTNWTQ";
		Map<String , Object> result = new HashMap<>();
		try {
			/*if(carTypeId==null){
				result.put("isUsedCar", false);
				return result;
			}*/
			
			Member  m = memberService.findMemberByOpenID(openid);
			result=  powerUsedService.checkIsUsedCar(Integer.parseInt(index),m);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
//		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
//		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
//		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+"天":"")+hour+"小时";
    }
	
	/**
	 * 用车信息
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/powerUsed")
	public String powerUsedInfoForIos(Long memberItemId,Long memberId,ModelMap modelMap,String index){
		// 解析json数据
		logger.info("微信---调用---- 用车信息------");
		modelMap.put("memberId", memberId);
		try {
			PowerUsed powerUsed = new PowerUsed();
			powerUsed.setMemberItemId(memberItemId);
			List<PowerUsed> powerUsedList = powerUsedService.selectPowerUsedOfUseingCars(powerUsed );
			
			//已发起用车的情况
			if(CollectionUtils.isNotEmpty(powerUsedList)&&Integer.parseInt(index)<=powerUsedList.size()){
				//车位已发起用车 进入用车信息页面
				PowerUsed p = powerUsedList.get(Integer.parseInt(index));
				//已发起
				CarType carType = carTypeService.findById(p.getCarTypeId());
				
				modelMap.put("brand", carType.getBrand());
				modelMap.put("carType", carType.getCarType());
				modelMap.put("carTypeId", p.getCarTypeId());//车位的车型id
//				使用中 使用 用车记录caroperateId 查询车型
		
				Long caroperateId = p.getCaroperateId();
				if(null!=caroperateId){
					CarOperate carOperate = carOperateService.findById(caroperateId);
					modelMap.put("brand", carOperate.getCarBrand());
					modelMap.put("carType", carOperate.getCarType());
				}
				
				modelMap.put("powerUsedId", p.getId());
//				CarType type = carTypeService.findById(p.getCarTypeId());//原车型
				modelMap.put("roadRescue", carType.getRoadRescue());
				modelMap.put("charging", carType.getCharging());
				
				modelMap.put("carReturnAddress", p.getCarReturnAddress());
				modelMap.put("carReturnTime", DateUtils.formatDate(p.getCarReturnTime(), "yyyy-MM-dd HH:mm") );
				modelMap.put("carUsedAddress", p.getCarUsedAddress());
				modelMap.put("carUsedTime", DateUtils.formatDate(p.getCarUsedTime(), "yyyy-MM-dd HH:mm"));
				
				//查询任务
				ButlerTask butlerTask = new ButlerTask();
				butlerTask.setPowerUsedId(p.getId());
				butlerTask.setType(3);
				//送车任务
				ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask);
				if(null!=butlerTaskSend){
						if(butlerTaskSend.getStatus()==7){
							
							ButlerTask butlerTaskNotComplete = new ButlerTask();
							butlerTaskNotComplete.setPowerUsedId(p.getId());
							butlerTaskNotComplete.setType(2);
							ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTaskNotComplete);
							if(null!=butlerTaskGet){
//										送车任务日志
								List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskGet);
								if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
									//取车任务最新的状态
									ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
									if(log.getStatus()==6){//已失联
										log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-2);
									}
									long returnTime = p.getCarReturnTime().getTime();//预计还车时间
									long timeNow = new Date().getTime();
//												距您用车时间还剩xx,等待管家为您送车
									if(returnTime>timeNow){
										modelMap.put("time", formatDateTime(returnTime-timeNow));
									}else{
										modelMap.put("time", "0小时");
									}
									
									if(log.getStatus()==2){//待取车
										modelMap.put("msg", "等待管家取车");
									}
									if(log.getStatus()==3){//取车中
										modelMap.put("msg", "管家已出发取车");
										modelMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
									}
									if(log.getStatus()==4){//4:已到达
										modelMap.put("msg", "管家已到达");
										modelMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
									}
									if(log.getStatus()==7){//7:已完成
										modelMap.put("status", "3");
										modelMap.put("msg", "取车完成");
										return "gcar/mycar/powerUsed";
									}
									
								}
							}
						}else if(butlerTaskSend.getStatus()==8){//已取消
							modelMap.put("status", "0");
							return "gcar/mycar/powerUsed";
							
						}else{//送车任务没完成 排除取消任务
							List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskSend);
							if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
								//最新的状态
								ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
//										'任务状态 0:待送车；1:送车中；2:待取车；3:取车中；4:已到达；5:充电中；6:已失联；7:已完成；8:已取消；',
								long carUsedTime = p.getCarUsedTime().getTime();//预计用车时间
								long timeNow = new Date().getTime();
//											距您用车时间还剩xx,等待管家为您送车
								if(carUsedTime>timeNow){
									modelMap.put("time", formatDateTime(carUsedTime-timeNow));
								}else{
									modelMap.put("time", "0小时");
								}
								
								if(log.getStatus()==0||log.getStatus()==9){
									modelMap.put("msg", "等待管家为您送车");
								}
								
								if(log.getStatus()==1){//送车中
									modelMap.put("msg", "管家已出发为您送车");
									modelMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
								}
								if(log.getStatus()==4){//已到达
									modelMap.put("msg", "管家已到达");
									modelMap.put("butlerInfo", log.getModifier()+log.getModifierMobile()+"】");
								}
								
							}
						}
				}
				
				if(p.getCaroperateId()!=null){
					CarOperate carOperate = carOperateService.findById(p.getCaroperateId());
					if(null!=carOperate){
						modelMap.put("carPlateNum", carOperate.getCarPlateNum());
						modelMap.put("carOperateId", carOperate.getId());
					}
				}
				//如临时还车
				if(p.getChangeStatus()==1){
					CarOperate carOperateChane = carOperateService.findById(p.getChangeCaroperateId());
					if(null!=carOperateChane){
						modelMap.put("changeCarPlateNum", carOperateChane.getCarPlateNum());
						modelMap.put("carOperateId", carOperateChane.getId());
						modelMap.put("changeCarType", carOperateChane.getCarType());
						modelMap.put("changeBrand", carOperateChane.getCarBrand());
					}
					CarType tcCarType = new CarType();
					tcCarType.setBrand(carOperateChane.getCarBrand());
					tcCarType.setCarType(carOperateChane.getCarType());
					List<CarType> carTypes = carTypeService.selectByCondition(tcCarType);
					if(CollectionUtils.isNotEmpty(carTypes)){
						CarType carType1 = carTypes.get(0);
						modelMap.put("roadRescue", carType1.getRoadRescue());
						modelMap.put("charging", carType1.getCharging());
					}
					
				}
				if(p.getUsedStatus()==0||p.getUsedStatus()==1||p.getUsedStatus()==2){
					modelMap.put("status", "1");//已预约
				}else if(p.getUsedStatus()==3||p.getUsedStatus()==4||p.getUsedStatus()==5){
					modelMap.put("status", "2");//使用中
				}else if(p.getUsedStatus()==5){//已完成
					modelMap.put("status", "3");
				}else{
					modelMap.put("status", "0");//已取消
				}
				
			}
			BasicThreshold selectBasicThreshold = basicThresholdService.selectBasicThreshold();
			modelMap.put("userUsecarCancelAdvance", selectBasicThreshold.getUserUsecarCancelAdvance());
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return "gcar/mycar/powerUsed";
	}
	
	/**
	 * 检验用户是否被冻结
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/checkMemberStatuts")
	@ResponseBody
	public Object checkMemberStatutsIOS(String memberId){
		logger.info("校验用户是否被冻结 memberId"+memberId);	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		try {
			Member member = memberService.findMemberByID(Long.valueOf(memberId));
			if(member!=null ){
				if(member.getStatuts()==0){
					//被冻结
					return true;
				}else{
					return false;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	private List<ButlerTaskStatusLog> getTaskLogs(ButlerTask task) {
		ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
		butlerTaskStatusLog.setTaskId(task.getId());
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
		return butlerTaskStatusLogList;
	}
	
	/**
	 * 车辆追踪
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/carTracking")
	public String carTrackingForIos(Long powerUsedId,ModelMap modelMap){
		// 解析json数据
		logger.info("---调用---- 车辆追踪-----");
		
		List<Map<String,Object>> list =null;
		try {
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(powerUsedId);
			butlerTask.setType(3);
			//送车任务
			ButlerTask butlerTaskSend = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask);
			
			if(null!=butlerTaskSend){
					if(butlerTaskSend.getStatus()==7){
						//已完成 查取车任务
						ButlerTask butlerTaskNotComplete = new ButlerTask();
						butlerTaskNotComplete.setPowerUsedId(Long.valueOf(powerUsedId));
						butlerTaskNotComplete.setType(2);
						ButlerTask butlerTaskGet = butlerTaskService.getButlerTaskByPowerUsedId(butlerTaskNotComplete);
						if(null!=butlerTaskGet){
//							送车任务日志
							List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskGet);
							if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
								//取车任务最新的状态
								ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);

								//	拼接送车任务
								ButlerTaskStatusLog logSend = new ButlerTaskStatusLog();
								logSend.setStatus(7);
								logSend.setTaskId(butlerTaskSend.getId());
								List<ButlerTaskStatusLog> logList = butlerTaskStatusLogService.getButlerTaskStatusLogList(logSend);
//								list = getCarBulterTask(logList.get(0));
								list = getCarBulterTask2(butlerTaskSend, logList.get(0));
								if(log.getStatus()==3){//3:取车中
									Map<String, Object> map4 = list.get(4);
									map4.put("msg", "管家已出发取车");
									map4.put("butlerInfo", log.getModifier());
									map4.put("mobile", log.getModifierMobile());
									map4.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
									map4.put("status", "1");
								}
								if(log.getStatus()==4){//4:已到达
									Map<String, Object> map4 = list.get(4);
									ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
									butlerTaskStatusLog.setTaskId(butlerTaskGet.getId());
									butlerTaskStatusLog.setStatus(3);
									List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
									if(CollectionUtils.isNotEmpty(logList2)){
										map4.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
										map4.put("butlerInfo", logList2.get(0).getModifier());
										map4.put("mobile", logList2.get(0).getModifierMobile());
									}
									map4.put("msg", "管家已出发取车");
									map4.put("status", "1");
									
									Map<String, Object> map5 = list.get(5);
									map5.put("msg", "管家已到达");
									map5.put("butlerInfo", log.getModifier());
									map5.put("mobile", log.getModifierMobile());
									map5.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
									map5.put("status", "1");
									
								}
								if(log.getStatus()==7){//7:已完成
									Map<String, Object> map4 = list.get(4);
									ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
									butlerTaskStatusLog.setTaskId(butlerTaskGet.getId());
									butlerTaskStatusLog.setStatus(3);
									List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
									if(CollectionUtils.isNotEmpty(logList2)){
										map4.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
										map4.put("butlerInfo", logList2.get(0).getModifier());
										map4.put("mobile", logList2.get(0).getModifierMobile());
									}
									map4.put("msg", "管家已出发取车");
									map4.put("status", "1");
									
									Map<String, Object> map5 = list.get(5);
									butlerTaskStatusLog.setStatus(4);
									
									List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
									if(CollectionUtils.isNotEmpty(logList3)){
										map5.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
										map5.put("butlerInfo", logList3.get(0).getModifier());
										map5.put("mobile", logList3.get(0).getModifierMobile());
									}
									map5.put("msg", "管家已到达");
									map5.put("status", "1");
									
									Map<String, Object> map6 = list.get(6);
									map6.put("msg", "取车完成");
									map6.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
									map6.put("status", "1");
								}
								
							}
						}
					}else{//送车任务没完成 排除取消任务
						List<ButlerTaskStatusLog> butlerTaskStatusLogList = getTaskLogs(butlerTaskSend);
						if(CollectionUtils.isNotEmpty(butlerTaskStatusLogList)){
							//最新的状态
							ButlerTaskStatusLog log = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-1);
							//已取消
							if(log.getStatus()==8){
//								查看取消之前的状态
								ButlerTaskStatusLog logBeforeCancel = butlerTaskStatusLogList.get(butlerTaskStatusLogList.size()-2);
//								list = getCarBulterTask(logBeforeCancel);
								list = getCarBulterTask2(butlerTaskSend, logBeforeCancel);
							}else{
//								list = getCarBulterTask(log);
								list = getCarBulterTask2(butlerTaskSend, log);
							}
						}
					}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.put("list", list);
		return "gcar/mycar/carTrack";
	}
	private List<Map<String, Object>> getCarBulterTask2(ButlerTask butlerTaskSend, ButlerTaskStatusLog log) {
		List<Map<String, Object>> list;
		list = getConstansMap();
		if(log.getStatus()==0){
			Map<String, Object> map = list.get(0);
			map.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map.put("status", "1");
		}
		else if(log.getStatus()==1){
			
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			butlerTaskStatusLog.setStatus(0);
			List<ButlerTaskStatusLog> logList = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList)){
				map.put("time", DateUtils.formatDate(logList.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
			}
			map.put("status", "1");
			
			Map<String, Object> map1 = list.get(1);
			map1.put("msg", "管家已出发为您送车");
			map1.put("butlerInfo", log.getModifier());
			map1.put("mobile", log.getModifierMobile());
			map1.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map1.put("status", "1");
		}
		else if(log.getStatus()==4){
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			butlerTaskStatusLog.setStatus(0);
			List<ButlerTaskStatusLog> logList = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList)){
				map.put("time", DateUtils.formatDate(logList.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
			}
			map.put("status", "1");
			
			//待送车
			butlerTaskStatusLog.setStatus(1);
			Map<String, Object> map1 = list.get(1);
			
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList2)){
				map1.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", logList2.get(0).getModifier());
				map1.put("mobile", logList2.get(0).getModifierMobile());
			}
			map1.put("msg", "管家已出发为您送车");
			map1.put("status", "1");
			
			Map<String, Object> map2 = list.get(2);
			map2.put("msg", "管家已到达");
			map2.put("butlerInfo", log.getModifier());
			map2.put("mobile", log.getModifierMobile());
			map2.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map2.put("status", "1");
		}
		else if(log.getStatus()==7){
			Map<String, Object> map = list.get(0);
			ButlerTaskStatusLog butlerTaskStatusLog = new ButlerTaskStatusLog();
			butlerTaskStatusLog.setTaskId(butlerTaskSend.getId());
			butlerTaskStatusLog.setStatus(0);
			List<ButlerTaskStatusLog> logList = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList)){
				map.put("time", DateUtils.formatDate(logList.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
			}
			map.put("status", "1");
			
			//待送车
			butlerTaskStatusLog.setStatus(1);
			Map<String, Object> map1 = list.get(1);
			
			List<ButlerTaskStatusLog> logList2 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList2)){
				map1.put("time", DateUtils.formatDate(logList2.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map1.put("butlerInfo", logList2.get(0).getModifier());
				map1.put("mobile", logList2.get(0).getModifierMobile());
			}
			map1.put("msg", "管家已出发为您送车");
			map1.put("status", "1");
			
			
			Map<String, Object> map2 = list.get(2);
			butlerTaskStatusLog.setStatus(4);
			List<ButlerTaskStatusLog> logList3 = butlerTaskStatusLogService.getButlerTaskStatusLogList(butlerTaskStatusLog );
			if(CollectionUtils.isNotEmpty(logList3)){
				map2.put("time", DateUtils.formatDate(logList3.get(0).getCreated(),"yyyy-MM-dd HH:mm"));
				map2.put("butlerInfo", logList3.get(0).getModifier());
				map2.put("mobile", logList3.get(0).getModifierMobile());
			}
			map2.put("msg", "管家已到达");
			map2.put("status", "1");
			
			Map<String, Object> map3 = list.get(3);
			map3.put("msg", "送车完成");
			map3.put("time", DateUtils.formatDate(log.getCreated(),"yyyy-MM-dd HH:mm"));
			map3.put("status", "1");
		}
		return list;
	}
	public List<Map<String, Object>> getConstansMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map0 = new HashMap<>();
		map0.put("msg", "等待管家为您送车");
		map0.put("status", "0");
		list.add(map0);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("msg", "管家送车");
		map1.put("status", "0");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("msg", "管家到达");
		map2.put("status", "0");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("msg", "送车完成");
		map3.put("status", "0");
		list.add(map3);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("msg", "管家取车");
		map4.put("status", "0");
		list.add(map4);
		Map<String, Object> map5 = new HashMap<>();
		map5.put("msg", "管家到达");
		map5.put("status", "0");
		list.add(map5);
		Map<String, Object> map6 = new HashMap<>();
		map6.put("msg", "取车完成");
		map6.put("status", "0");
		list.add(map6);
		return list;
	}
	/**
	 * 权益车型列表
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/memberItemList")
	public String memberItemListAndroid(Long memberItemId,ModelMap modelMap){
		
		logger.info("权益车型列表---");	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
	
		try {
			//获取升级之后跳转的页面链接
			String power_app=Constant.load.getProperty("power_app");
			modelMap.put("power_app", power_app);
			//查询权益
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
			JSONArray ownList  = getSceneCartype(ownCartypeIds);
			modelMap.put("ownList", ownList);
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
			 modelMap.put("notOwnList", notOwnList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gcar/mycar/memberItemCarList";
	}
	
	/**
	 * 封装车型
	 * @param notOwnCartypeIds
	 * @return
	 */
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
		jsonArrayAdd(arr, json0);
		jsonArrayAdd(arr, json1);
		jsonArrayAdd(arr, json2);
		jsonArrayAdd(arr, json3);
		jsonArrayAdd(arr, json4);
		jsonArrayAdd(arr, json5);
//		ownList.add(map);
		return arr;
	}
	
	private List<CarType> addCarType(List<CarType> carTypes, CarType cart) {
		if(carTypes==null){
			 carTypes=new ArrayList<>();
		 }
		 carTypes.add(cart);
		return carTypes;
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
	
}
