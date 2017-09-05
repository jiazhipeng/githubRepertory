package com.hy.gcar.service.powerUserd.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.dao.PowerUsedMapper;
import com.hy.gcar.entity.Approve;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.ExpectCartype;
import com.hy.gcar.entity.GetmoneyApply;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberInfo;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Nation;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.entity.PowerUsedCost;
import com.hy.gcar.entity.PowerUsedTimelog;
import com.hy.gcar.entity.TransferList;
import com.hy.gcar.service.Approve.ApproveService;
import com.hy.gcar.service.ExpectCartype.ExpectCartypeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basic.BasicThresholdService;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.carOperatePlan.CarOperatePlanService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.carType.MemberItemCartypeService;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.getmoney.GetmoneyApplyService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.memberInfo.MemberInfoService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.nation.NationService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.service.powerUserd.PowerUsedTimelogService;
import com.hy.gcar.service.transferList.TransferListService;
import com.thoughtworks.xstream.core.util.WeakCache;

@Service(value = "tdPowerUsedService")
public class PowerUsedServiceImpl implements PowerUsedService {

    @Autowired
    private PowerUsedMapper<PowerUsed>powerUsedMapper;
    
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberItemShareService itemShareService;
    @Autowired
    private MemberItemService memberItemService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private ApproveService approveService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private MemberItemCartypeService memberItemCartypeService;
    @Autowired
    private ItemCartypeService itemCartypeService;
    @Autowired
    private BasicThresholdService basicThresholdService;
    @Autowired
    private CouponInfoService couponInfoService;
    @Autowired
    private ButlerTaskService butlerTaskService;
    @Autowired
    private PowerUsedCostService powerUsedCostService;
    @Autowired
    private PowerUsedTimelogService powerUsedTimelogService;
    @Autowired
    private MemberItemShareService memberItemShareService;
    @Autowired
    private CarOperateService carOperateService;
    @Autowired
    private NationService nationService;
    @Autowired
    private MemberAccountLogService memberAccountLogService;
    @Autowired
    private TransferListService transferListService;
    @Autowired
    private CarOperatePlanService carOperatePlanService;
    @Autowired
    private GetmoneyApplyService getmoneyApplyService;
    @Autowired
    private ExpectCartypeService expectCartypeService;
    
    @Autowired
    private WechatMessageService  wechatMessageService;
    
    @Override
    public Integer insertSelective(PowerUsed tdPowerUsed) throws Exception {
    	tdPowerUsed.setCreated(new Date());
    	tdPowerUsed.setLasttimeModify(tdPowerUsed.getCreated());
           return this.powerUsedMapper.insertSelective(tdPowerUsed);
        }

    @Override
    public Integer insertBatch(List<PowerUsed> tdPowerUsed) throws Exception {
           return this.powerUsedMapper.insertBatch(tdPowerUsed) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.powerUsedMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.powerUsedMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(PowerUsed tdPowerUsed) {
    	tdPowerUsed.setLasttimeModify(new Date());
           return this.powerUsedMapper.updateByPrimaryKeySelective(tdPowerUsed);
    }

    @Override
    public PowerUsed findById(Object id) {
           return (PowerUsed) this.powerUsedMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PowerUsed> selectByCondition(PowerUsed tdPowerUsed) {
           return (List<PowerUsed>) this.powerUsedMapper.selectByCondition(tdPowerUsed);
    }

    @Override
    public Integer selectCountByCondition(PowerUsed tdPowerUsed) {
           return  this.powerUsedMapper.selectCountByCondition(tdPowerUsed);
    }

    /**
     * 极车详情页面 发起用车
     * @param powerUsed flag 来源 展厅 0 车位1
     * @return
     * @throws Exception 
     */
	public Map<String, Object> launchCarShowUseCar(PowerUsed powerUsed,String flag,int index) throws Exception {
//		校验车位是否发起用车
			Map<String, Object> returnMap = new HashMap<>();
			
			PowerUsed powerUsed_new= new PowerUsed();
			powerUsed_new.setMemberItemId(powerUsed.getMemberItemId());
			//		查询我的可用车位数，查询我的已发起 或使用中的用车记录数 比较
			List<PowerUsed> powerUsedList = powerUsedMapper.selectPowerUsedOfUseingCars(powerUsed_new);
			
			
		
			if("1".equals(flag)){//车位用车
				//已发起用车的情况
				if(CollectionUtils.isNotEmpty(powerUsedList)&&index<=powerUsedList.size()-1){
					//车位已发起用车 进入用车信息页面
					PowerUsed used = powerUsedList.get(index);  //根据车位坐标获取用车记录中的车型
					returnMap.put("carTypeId", used.getCarTypeId());
					returnMap.put("powerUsedId", used.getId());
					if(used.getCaroperateId()!=null){
						CarOperate carOperate = carOperateService.findById(used.getCaroperateId());
						returnMap.put("brand", carOperate.getCarBrand());
						returnMap.put("carType", carOperate.getCarType());
					}else{
						CarType carType = carTypeService.findById(used.getCarTypeId());
						returnMap.put("brand", carType.getBrand());
						returnMap.put("carType", carType.getCarType());
					}
					returnMap.put("status", "0");//返回车型id和已发起用车的状态
					return returnMap;
				}else{
					returnMap.put("status", "1");//未发起用车
				}
			}
			Member member = memberService.findMemberByID(powerUsed.getMemberId());
			
			GetmoneyApply getmoneyApply = new GetmoneyApply();
			getmoneyApply.setMemberItemId(powerUsed.getMemberItemId());
			//		判断当前权益是否在押金提现审核中，结果如下： 
			List<GetmoneyApply> getmoneyApplys= getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply);
			if(CollectionUtils.isNotEmpty(getmoneyApplys)){
				returnMap.put("code", "5");
				returnMap.put("msg", "您正在提现中，不可发起用车");
				return returnMap;
			}
			
	//		判断当前用户是否被冻结，结果如下：
			if(null!=member){
				if(member.getStatuts()==0){//用户被冻结
					returnMap.put("code", "3");
					returnMap.put("msg", "400-9029-858");
					return returnMap;
				}
			}
			//判断是否公用人
			MemberItem memberItem = checkIsShareMember(powerUsed.getMemberId());
			
			//判断是否公用人
			if(null==memberItem){
				returnMap.put("code", "0");
				returnMap.put("msg", "您已被删除共用人身份");
				return returnMap;
			}
			Integer useCarApproved = member.getUseCarApproved();//用车审核状态：0：待审核；1：驳回审核；2：通过审核；3未审核（无提交审核记录）',
			MemberInfo memberInfo = memberInfoService.findMemberInfoByMemberID(powerUsed.getMemberId());
			if(useCarApproved==2){//2：通过审核
				//基础服务阀值信息
				BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
				if(null!=basicThreshold){
					returnMap.put("serviceTimeEnd", basicThreshold.getServiceTimeEnd());
					returnMap.put("serviceTimeStart", basicThreshold.getServiceTimeStart());
					returnMap.put("userUsecarAdvance", basicThreshold.getUserUsecarAdvance());
				}
				
				if("2".equals(flag)){//展厅 判断是否有可用车位
					//已发起用车的情况
					Item item = itemService.findById(memberItem.getItemId());
//					//我的可用车位数
					Integer enableCount = item.getEnableCount();
					if(CollectionUtils.isNotEmpty(powerUsedList)&&enableCount<=powerUsedList.size()){
						returnMap.put("code", "0");
						returnMap.put("msg", "您的车位已用完，还车后方可发起");
						return returnMap;
					}
				}
				
				//进入用车时间选择页面
				returnMap.put("code", "1"); 
				returnMap.put("msg", "");
				return returnMap;
			}else if(useCarApproved==0){//0：待审核
				returnMap.put("code", "0");
				returnMap.put("msg", "您的信息正在审核中，审核通过后即可用车");
				return returnMap;
			}else if(useCarApproved==1){//1：驳回审核
				returnMap.put("code", "4");
				returnMap.put("msg", "");
				//照片回显
				returnMap.put("idcardBack",memberInfo.getIdcardBack());
				returnMap.put("idcardFront",memberInfo.getIdcardFront());
				returnMap.put("drivercardCopy",memberInfo.getDrivercardCopy());
				returnMap.put("drivercardOriginal",memberInfo.getDrivercardOriginal());
				return returnMap;
			}else{//3未审核（无提交审核记录）',
				if(null==memberInfo){
					returnMap.put("code", "2");
					returnMap.put("msg", "");
					return returnMap;
				}
				boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(memberInfo);
				if(isPerfect){//信息已完善  提交审核记录
					returnMap.put("code", "0");
					returnMap.put("msg", "您的信息正在审核中，审核通过后即可用车");
					//插入审核记录
					inserApprove(member, memberItem, memberInfo);
					
					//修改member表审核状态
					Member memberUpdate = new Member();
					memberUpdate.setId(member.getId());
					memberUpdate.setUseCarApproved(0);
					memberService.updateCompanyMember(memberUpdate );
					return returnMap;
				}else{//信息未完善  跳转上传页面
					returnMap.put("code", "2");
					returnMap.put("msg", "");
					//照片回显
					returnMap.put("idcardBack",memberInfo.getIdcardBack());
					returnMap.put("idcardFront",memberInfo.getIdcardFront());
					returnMap.put("drivercardCopy",memberInfo.getDrivercardCopy());
					returnMap.put("drivercardOriginal",memberInfo.getDrivercardOriginal());
					return returnMap;
				}
				
			}
			
	}

	
	/**
	 * 插入审核记录
	 * @param member
	 * @param memberItem
	 * @param memberInfo
	 * @throws Exception
	 */
	public void inserApprove(Member member, MemberItem memberItem, MemberInfo memberInfo) throws Exception {
		Approve approve = new Approve();
		approve.setMemberId(member.getId());
		approve.setMemberName(member.getName());
		approve.setMemberMobile(member.getMobile());
		approve.setMemberItemId(memberItem.getId());
		approve.setMemberItemName(memberItem.getItemName());
		approve.setApproveType(0);
		approve.setStatus(0);
		approve.setCreated(new Date());
		approve.setIsLog(0);

		approve.setIdcardBack(memberInfo.getIdcardBack());
		approve.setIdcardFront(memberInfo.getIdcardFront());
		approve.setDrivercardCopy(memberInfo.getDrivercardCopy());
		approve.setDrivercardOriginal(memberInfo.getDrivercardOriginal());
		approve.setLicense(memberInfo.getLicense());
		approve.setTaxReg(memberInfo.getTaxReg());
		
		MemberItemShare trMemberItemShare = new MemberItemShare();
		trMemberItemShare.setMemberId(member.getId());
		List<MemberItemShare> list = itemShareService.selectByCondition(trMemberItemShare );
		if(CollectionUtils.isNotEmpty(list)){
			approve.setMembership(list.get(0).getIsMain());
		}
		
		approveService.insertSelective(approve);
	}
	
	
	
	/**
	 * 查询是否删除共有人
	 * @param memberId
	 * @return fasle 删除共有人 ，true 没有删除（有权益）
	 */
	public MemberItem checkIsShareMember(Long memberId){
		MemberItem memberItem = new MemberItem();
		memberItem.setMemberId(memberId);
		List<MemberItem> list =memberItemService.selectByCondition(memberItem );
		Long memberItemId=null;
		//会员没有购买套餐
		if(list==null ||list.size()==0){
			MemberItemShare trMemberItemShare = new MemberItemShare();
			trMemberItemShare.setMemberId(memberId);
			List<MemberItemShare> shareList = itemShareService.selectByCondition(trMemberItemShare);
			if(CollectionUtils.isEmpty(shareList)){
				return null;
			}
			memberItemId=shareList.get(0).getMemberItemId();
		}else{
			memberItemId=list.get(0).getId();
		}
		memberItem = memberItemService.findById(memberItemId);
		return memberItem;
	}

	/**
	 * 空车位点击用车
	 * @param powerUsed
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectEmptyMyCarsUseCar(PowerUsed powerUsed) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Member member = memberService.findMemberByID(powerUsed.getMemberId());
		if(null!=member){
			if(member.getStatuts()==0){//用户被冻结
				returnMap.put("code", "3");
				returnMap.put("msg", "400-9029-858");
				return returnMap;
			}
			//判断是否公用人
			MemberItem memberItem = checkIsShareMember(powerUsed.getMemberId());
			if(null==memberItem){
				returnMap.put("code", "0");
				returnMap.put("msg", "您已被删除共用人身份");
				return returnMap;
			}
			returnMap.put("code", "1");
			returnMap.put("msg", "");
		}
		return returnMap;
	}
	/**
	 * 非空车位发起用车
	 */
	/*@Override
	public Map<String, Object> notEmptyMyCarsUseCar(PowerUsed powerUsed,int index) throws Exception {
		return this.launchCarShowUseCar(powerUsed, "0",index);
	}*/

	@Override
	public Map<String, Object> checkIsMyEnableCar(Long carTypeId, Long memberId) throws Exception {
		MemberItem memberItem = checkIsShareMember(memberId);
		Map<String, Object> returnMap = new HashMap<>();
		if(null!=memberItem){
			MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
			tdMemberItemCartype.setMemberItemId(memberItem.getId());
			List<MemberItemCartype> cartypes = memberItemCartypeService.selectByCondition(tdMemberItemCartype );
			tdMemberItemCartype.setCarTypeId(carTypeId);
			List<MemberItemCartype> list = memberItemCartypeService.selectByCondition(tdMemberItemCartype );
			if(CollectionUtils.isNotEmpty(list)){
				returnMap.put("code", "1");//是我的启用车型
				returnMap.put("msg", "");
				return returnMap;
			}
			//不是我的启用车型 继续判断当前用户是否有空车位
			
			Item item = itemService.findById(memberItem.getItemId());
			Integer enableCount = item.getEnableCount();
			//判断是否有空车位 返回成功// 则直接变成我的启用车型
			if(enableCount>cartypes.size()){
				/*CarType carType = carTypeService.findById(carTypeId);
				ItemCartype tdItemCartype = new ItemCartype();
				tdItemCartype.setCartypeId(carTypeId);
				tdItemCartype.setItemId(item.getId());
				List<ItemCartype> itemCartypes = itemCartypeService.selectByCondition(tdItemCartype );
				if(CollectionUtils.isNotEmpty(itemCartypes)){
					tdMemberItemCartype.setPrice(itemCartypes.get(0).getDayPrice());
				}
				tdMemberItemCartype.setBrand(carType.getBrand());
				tdMemberItemCartype.setCarType(carType.getCarType());
				tdMemberItemCartype.setStatus(1);//待使用状态
				memberItemCartypeService.insertSelective(tdMemberItemCartype);*/
				returnMap.put("code", "1");
				returnMap.put("msg", "");
				return returnMap;
			}
			//返回更换车用费
			returnMap.put("changePrice", item.getChangePrice());
			returnMap.put("code", "0");
		}else{
			return null;//被删除共有人
		}
		return returnMap;
	}

	@Override
	public List<CarType> selectMyEnableCars(Long memberId) {
		MemberItem memberItem = checkIsShareMember(memberId);
		List<CarType> carTypes = new ArrayList<>();
		if(null!=memberItem){
			MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
			tdMemberItemCartype.setMemberItemId(memberItem.getId());
			List<MemberItemCartype> list = memberItemCartypeService.selectByCondition(tdMemberItemCartype );
			if(CollectionUtils.isNotEmpty(list)){
				for (MemberItemCartype memberItemCartype : list) {
					CarType carType = carTypeService.findById(memberItemCartype.getCarTypeId());
					carTypes.add(carType);
				}
			}
		}
		return carTypes;
	}
	
	
	/**
	 * 用车确定按钮 生成用车记录
	 * @param tdPowerUsed
	 * @return
	 * @throws Exception 
	 */
	public synchronized  Map<String, Object>  insertPowerUsed(PowerUsed powerUsed) throws Exception {
		MemberItem memberItem = checkIsShareMember(powerUsed.getMemberId());
		Map<String, Object> returnMap = new HashMap<>();
		
		GetmoneyApply getmoneyApply = new GetmoneyApply();
		getmoneyApply.setMemberItemId(memberItem.getId());
		//		判断当前权益是否在押金提现审核中，结果如下： 
		List<GetmoneyApply> getmoneyApplys= getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply);
		if(CollectionUtils.isNotEmpty(getmoneyApplys)){
			returnMap.put("code", "0");
			returnMap.put("msg", "您正在提现中，不可发起用车");
			return returnMap;
		}
		//判断库存
		boolean isStore = carOperatePlanService.isStore(powerUsed.getCarTypeId(), powerUsed.getCarUsedTime(), powerUsed.getCarReturnTime());
		
		if(!isStore){
			returnMap.put("code", "5");
			returnMap.put("msg", "当前选择的车型库存不足，请重新选择");
			return returnMap;
		}
		
		powerUsed.setMemberItemId(memberItem.getId());
		
		BigDecimal balance = memberItem.getBalance();//余额
		BigDecimal deposit = memberItem.getDeposit();//押金
//		日租金x用车天数  四舍五入 保留2位小数
		BigDecimal totalPrice = null;
		
		boolean isdeposit=true;//押金足
		boolean isbalance=true;//余额
		
		ItemCartype itemCartype = new ItemCartype();
		itemCartype.setItemId(memberItem.getItemId());
		itemCartype.setCartypeId(powerUsed.getCarTypeId());
		List<ItemCartype> itemCartypeList = itemCartypeService.selectByCondition(itemCartype );
		BigDecimal dayPrice =null;
		//获取套餐下该车型
		if(CollectionUtils.isNotEmpty(itemCartypeList)){
			dayPrice=itemCartypeList.get(0).getDayPrice();
		}
		
		Integer usedDay = powerUsed.getCarUsedDay();
		totalPrice = dayPrice.multiply(new BigDecimal(usedDay).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		CouponInfo couponInfo = null;
		BigDecimal couponPrice=null;
		if(null!=powerUsed.getCouponInfoId()){
			couponInfo = couponInfoService.findById(powerUsed.getCouponInfoId());
			couponPrice = couponInfo.getPrice();//优惠券金额
			totalPrice = totalPrice.subtract(couponPrice).setScale(2, BigDecimal.ROUND_HALF_UP); //减去优惠券金额
			
		}
		
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		BigDecimal depositUsecar = basicThreshold.getDepositUsecar();//'发起用车时会员押金不得少于(元)',
//		当前用户押金小于 阀值
		if(deposit.doubleValue()<depositUsecar.doubleValue()){
			isdeposit=false;
		}
		
		//余额不足支持本次用车
		if(totalPrice.doubleValue()>balance.doubleValue()){
			isbalance=false;
		} 
		
		if(isbalance&&isdeposit){//都充足 发起用车成功
			
			//判断当前是否有可用车位
			PowerUsed powerUsedNew = new PowerUsed();
			powerUsedNew.setMemberItemId(memberItem.getId());
			List<PowerUsed> powerUsedList = powerUsedMapper.selectPowerUsedOfUseingCars(powerUsedNew );
			Item item = itemService.findById(memberItem.getItemId());
//			//我的可用车位数
			Integer enableCount = item.getEnableCount();
			if(CollectionUtils.isNotEmpty(powerUsedList)&&enableCount<=powerUsedList.size()){
				returnMap.put("code", "0");
				returnMap.put("msg", "您的车位已用完，还车后方可发起");
				return returnMap;
			}
			
			
			returnMap.put("code", "1");
			returnMap.put("msg", "");
			
			MemberItemShare itemShare = memberItemShareService.selectByMemberId(powerUsed.getMemberId());
			if(itemShare!=null){
				powerUsed.setMemberType(itemShare.getIsMain());
			}
			
			//查询区所属市
			Nation n = new Nation();
			n.setCode(powerUsed.getCityCode());
			n = nationService.getParentNationByCityeCodeOrId(n);
			powerUsed.setCityCode(n.getCode());

			if(null!=powerUsed.getCouponInfoId()){
				//修改优惠券状态
				couponInfo.setStatus(4);
				couponInfo.setUsedTime(new Date());
				couponInfoService.updateByPrimaryKeySelective(couponInfo);
			}
			
//			插入用车记录
			powerUsed.setMemberItemName(memberItem.getItemName());
			powerUsed.setCreated(new Date());
			powerUsed.setLasttimeModify(powerUsed.getCreated());
			powerUsedMapper.insertSelective(powerUsed);
			/*Integer integer = powerUsedMapper.insertIntoSelective(powerUsed);
			if(integer==0){
				returnMap.put("code", "0");
				returnMap.put("msg", "您的车位已用完，还车后方可发起");
				return returnMap;
			}*/
//			powerUsedMapper.insertSelective(powerUsed);
			returnMap.put("powerUsedId", powerUsed.getId());
			//插入用车金额记录
			insertPowerUsedCost(powerUsed, couponPrice, couponInfo);
			//创建任务
			createUseCarTask(powerUsed, memberItem);
			
			
			return returnMap;
		}
		if(!isdeposit&&!isbalance){//押金不足且余额不足
			returnMap.put("code", "4");
			returnMap.put("price", depositUsecar.longValue());
			returnMap.put("msg", "您的余额不足且押金不满"+depositUsecar.longValue()+"元，请及时续费");
			return returnMap;
		}
		if(!isbalance){//余额不足
			returnMap.put("code", "2");
			returnMap.put("msg", "您余额不足，请及时续费");
			return returnMap;
		}
		if(!isdeposit){//押金不足
			returnMap.put("code", "3");
			returnMap.put("price", depositUsecar.longValue());
			returnMap.put("msg", "您的用车押金不满"+depositUsecar.longValue()+"元，请及时续费");
			return returnMap;
		}

		return null;
	}

	private void insertPowerUsedCost(PowerUsed powerUsed, BigDecimal couponPrice,
			CouponInfo couponInfo) throws Exception {
		//插入用车金额记录
		PowerUsedCost powerUsedCost= new PowerUsedCost();
		if(null!=powerUsed.getCouponInfoId()){
			powerUsedCost.setCouponId(powerUsed.getCouponInfoId());
			powerUsedCost.setCouponName(couponInfo.getCouponName());
			powerUsedCost.setCouponPrice(couponPrice);;
		}
		powerUsedCost.setPowerUsedId(powerUsed.getId());
		powerUsedCostService.insertSelective(powerUsedCost);
	}
	
	/**
	 * 创建任务 
	 * 已完成的送车任务  
	 * 带取车的取车任务
	 * @param powerUsed
	 * @param memberItem
	 * @throws Exception
	 */
	private ButlerTask createUseCarTasked(PowerUsed powerUsed, MemberItem memberItem,Long loginId) throws Exception {
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setCarTypeId(powerUsed.getCarTypeId());
		Member member = memberService.findMemberByID(powerUsed.getMemberId());
		Member loginMember = memberService.findMemberByID(loginId);
		
		butlerTask.setStatus(ButlerTaskStatus.BUTLER_TASK_STATUS_HAS_BEEN_COMPLETED.getIndex());
		//设置完成时间
		butlerTask.setCompleteTime(new Date());
        butlerTask.setStatusCondition(ButlerTaskStatus.BUTLER_TASK_STATUS_ALREADY_ARRIVED.getIndex());
		butlerTask.setMemberId(powerUsed.getMemberId()+"");
		butlerTask.setCarOperateId(powerUsed.getCaroperateId());
		butlerTask.setMemberItemId(memberItem.getId());
		butlerTask.setMemberMobile(member.getMobile());
		butlerTask.setMemberName(member.getName());
		butlerTask.setRemark("发起签约");
		butlerTask.setModifierType(1);
		butlerTask.setPowerUsedId(powerUsed.getId());
		butlerTask.setPlanTime(powerUsed.getCarUsedTime());
		butlerTask.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
		butlerTask.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
		butlerTask.setOperaterId(loginMember.getSysuserId());
		butlerTask.setOperaterName(loginMember.getSysuserName());
		//创建送车任务
		ButlerTask sendtask = butlerTaskService.createMemberSendCarButlerTasked(butlerTask);
		
		ButlerTask butlerTask2 = new ButlerTask();
		//创建还车任务
		butlerTask2.setMemberId(powerUsed.getMemberId()+"");
		butlerTask2.setMemberItemId(memberItem.getId());
		butlerTask2.setMemberMobile(member.getMobile());
		butlerTask2.setMemberName(member.getName());
		butlerTask2.setModifierType(1);
		butlerTask2.setCarOperateId(powerUsed.getCaroperateId());
		butlerTask2.setRemark("发起签约");
		butlerTask2.setPowerUsedId(powerUsed.getId());
		butlerTask2 .setPlanTime(powerUsed.getCarReturnTime());
		//butlerTask2.setOperaterId(loginMember.getSysuserId());
		//butlerTask2.setOperaterName(loginMember.getSysuserName());
		butlerTask2.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
		butlerTask2.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
		butlerTaskService.createGetCarButlerTasked(butlerTask2);
		return sendtask;
	}

	/**
	 * 创建任务
	 * @param powerUsed
	 * @param memberItem
	 * @throws Exception
	 */
	private void createUseCarTask(PowerUsed powerUsed, MemberItem memberItem) throws Exception {
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setCarTypeId(powerUsed.getCarTypeId());
		Member member = memberService.findMemberByID(powerUsed.getMemberId());
		
		butlerTask.setMemberId(powerUsed.getMemberId()+"");
		butlerTask.setMemberItemId(memberItem.getId());
		butlerTask.setMemberMobile(member.getMobile());
		if(StringUtils.isNotBlank(member.getSureName())){
			butlerTask.setMemberName(member.getSureName());
		}else{
			butlerTask.setMemberName(member.getName());
		}
		butlerTask.setModifierType(0);
		butlerTask.setPowerUsedId(powerUsed.getId());
		butlerTask.setPlanTime(powerUsed.getCarUsedTime());
		butlerTask.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
		butlerTask.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
		//创建送车任务
		ButlerTask sendtask = butlerTaskService.createMemberSendCarButlerTask(butlerTask);
		
		ButlerTask butlerTask2 = new ButlerTask();
		//创建还车任务
		butlerTask2.setMemberId(powerUsed.getMemberId()+"");
		butlerTask2.setMemberItemId(memberItem.getId());
		butlerTask2.setMemberMobile(member.getMobile());
		if(StringUtils.isNotBlank(member.getSureName())){
			butlerTask2.setMemberName(member.getSureName());
		}else{
			butlerTask2.setMemberName(member.getName());
		}
		butlerTask2.setModifierType(0);
		butlerTask2.setPowerUsedId(powerUsed.getId());
		butlerTask2 .setPlanTime(powerUsed.getCarReturnTime());
		butlerTask2.setTaskSendCarAddress(powerUsed.getCarUsedAddress());
		butlerTask2.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
		butlerTaskService.createGetCarButlerTask(butlerTask2);
		
		
		//创建运营计划
		butlerTaskService.insertCarRecordToBeExecuted(sendtask);
		
		
	}


	/**
	 * 空车位更换完车型点确定按钮
	 * @param powerUsed
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> insertEmptyMyCarsUseCarSubmit(PowerUsed powerUsed) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		Member member = memberService.findMemberByID(powerUsed.getMemberId());
		Integer useCarApproved = member.getUseCarApproved();//用车审核状态：0：待审核；1：驳回审核；2：通过审核；3未审核（无提交审核记录）',
		MemberInfo memberInfo = memberInfoService.findMemberInfoByMemberID(powerUsed.getMemberId());
		CarType carType = carTypeService.findById(powerUsed.getCarTypeId());
		MemberItem memberItem = checkIsShareMember(powerUsed.getMemberId());
		
		//查询是否我的启用车型
		MemberItemCartype memberItemCartype = new MemberItemCartype();
		memberItemCartype.setCarTypeId(powerUsed.getCarTypeId());
		memberItemCartype.setMemberItemId(memberItem.getId());
		List<MemberItemCartype> list = memberItemCartypeService.selectByCondition(memberItemCartype);
		if(CollectionUtils.isNotEmpty(list)){
			returnMap.put("code", "6");
			returnMap.put("msg", carType.getBrand()+" "+carType.getCarType()+"已经是您的启用车型，请重新选择车型");
			return returnMap;
		}
		
		//基础服务阀值信息
//		setBasicServiceInfo(powerUsed, returnMap, memberItem, carType);
		
		//将选择的车型变成我的启用车型
		ItemCartype tdItemCartype = new ItemCartype();
		tdItemCartype.setCartypeId(carType.getId());
		tdItemCartype.setItemId(powerUsed.getItemId());
		List<ItemCartype> itemCartypes = itemCartypeService.selectByCondition(tdItemCartype );
		
		MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
		if(CollectionUtils.isNotEmpty(itemCartypes)){
			tdMemberItemCartype .setPrice(itemCartypes.get(0).getDayPrice());
		}
		tdMemberItemCartype.setCarTypeId(carType.getId());
		tdMemberItemCartype.setBrand(carType.getBrand());
		tdMemberItemCartype.setCarType(carType.getCarType());
		tdMemberItemCartype.setMemberItemId(memberItem.getId());
		tdMemberItemCartype.setStatus(1);//待使用状态
		memberItemCartypeService.insertSelective(tdMemberItemCartype);
		if(useCarApproved==2){//2：通过审核
			returnMap.put("code", "1");
			returnMap.put("msg", "");
			return returnMap;
		}else if(useCarApproved==0){//0：待审核
			returnMap.put("code", "5");
			returnMap.put("msg", "您的信息正在审核中，审核通过后即可用车");
			return returnMap;
		}else if(useCarApproved==1){//1：驳回审核
			returnMap.put("code", "4");
			returnMap.put("msg", "");
			if(null!=memberInfo){
				//照片回显
				returnMap.put("idcardBack",memberInfo.getIdcardBack());
				returnMap.put("idcardFront",memberInfo.getIdcardFront());
				returnMap.put("drivercardCopy",memberInfo.getDrivercardCopy());
				returnMap.put("drivercardOriginal",memberInfo.getDrivercardOriginal());
			}
			return returnMap;
		}else{//3未审核（无提交审核记录）',
			
			boolean isPerfect = memberInfoService.checkMemberInfoIsPerfect(memberInfo);
			if(isPerfect){//信息已完善  提交审核记录
				returnMap.put("code", "5");
				returnMap.put("msg", "您的信息正在审核中，审核通过后即可用车");
				//插入审核记录
				inserApprove(member, memberItem, memberInfo);
				
				//修改member表审核状态
				Member memberUpdate = new Member();
				memberUpdate.setId(member.getId());
				memberUpdate.setUseCarApproved(0);
				memberService.updateCompanyMember(memberUpdate );
				return returnMap;
			}else{//信息未完善  跳转上传页面
				returnMap.put("code", "2");
				returnMap.put("msg", "");
				if(null!=memberInfo){
					//照片回显
					returnMap.put("idcardBack",memberInfo.getIdcardBack());
					returnMap.put("idcardFront",memberInfo.getIdcardFront());
					returnMap.put("drivercardCopy",memberInfo.getDrivercardCopy());
					returnMap.put("drivercardOriginal",memberInfo.getDrivercardOriginal());
				}
				return returnMap;
			}
			
		}
	}
	/**
	 * 查询正在使用的车型记录
	 * @param t
	 * @return
	 */
	@Override
	public List<PowerUsed> selectPowerUsedOfUseingCars(PowerUsed powerUsed) {
		return powerUsedMapper.selectPowerUsedOfUseingCars(powerUsed);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static long formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		if(min>0){
			day=day+1;
		}else{
			if(hour>0){
				day=day+1;
			}
		}
		return day;
    }
   
		
	@Override
	public Map<String, Object> returnCarSubmit(PowerUsed powerUsed) {
		Map<String, Object> returnMap = new HashMap<>();
		PowerUsed powerUsed_old = powerUsedMapper.selectByPrimaryKey(powerUsed.getId());
		Date returnDate = powerUsed_old.getCarReturnTime();
		Date updateDate = powerUsed.getCarReturnTime();
		if(returnDate.getTime() == updateDate.getTime()){
			returnMap.put("code", "1");//成功
		}
//		boolean isChangeTime=false;//是否变更时间
		if(returnDate.getTime() > updateDate.getTime()){ //提前
			returnMap.put("code", "1");//成功
//			isChangeTime=true;
		}
		//还车延期
		if(returnDate.getTime() < updateDate.getTime()){
//			isChangeTime=true;
			MemberItem memberItem = memberItemService.findById(powerUsed_old.getMemberItemId());
			
			BigDecimal balance = memberItem.getBalance();//余额
			BigDecimal deposit = memberItem.getDeposit();//押金
			boolean isdeposit=true;//押金足
			boolean isbalance=true;//余额
			BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
			BigDecimal depositUsecar = basicThreshold.getDepositUsecar();//'发起用车时会员押金不得少于(元)',
			
			long delayDay=formatDateTime(updateDate.getTime()-returnDate.getTime()); //延期天数
			
			
			
			BigDecimal dayPrice=null;//日租金
			ItemCartype tdItemCartype = new ItemCartype();
			tdItemCartype .setItemId(memberItem.getItemId());
			tdItemCartype.setCartypeId(powerUsed.getCarTypeId());
			List<ItemCartype> list2 = itemCartypeService.selectByCondition(tdItemCartype );
			if(CollectionUtils.isNotEmpty(list2)){
				dayPrice = list2.get(0).getDayPrice();
			}
			
//			日租金x延期天数  四舍五入 保留2位小数
			BigDecimal totalPrice = dayPrice.multiply(new BigDecimal(delayDay).setScale(2, BigDecimal.ROUND_HALF_UP));
//			当前用户押金小于 阀值
			if(deposit.doubleValue()<depositUsecar.doubleValue()){
				isdeposit=false;
			}
			
			//余额不足支持本次用车
			if(totalPrice.doubleValue()>balance.doubleValue()){
				isbalance=false;
			}
			if(!isdeposit&&!isbalance){//押金不足且余额不足
				returnMap.put("code", "4");
//				returnMap.put("price", depositUsecar.longValue());
				returnMap.put("msg", "您的余额不足且押金不满"+depositUsecar.longValue()+"元，请及时续费");
				return returnMap;
			}
			if(!isbalance){//余额不足
				returnMap.put("code", "2");
				returnMap.put("msg", "您余额不足，请及时续费");
				return returnMap;
			}
			if(!isdeposit){//押金不足
				returnMap.put("code", "3");
				returnMap.put("price", depositUsecar.longValue());
				returnMap.put("msg", "您的押金不满"+depositUsecar.longValue()+"元，请及时续费");
				return returnMap;
			}
			
			
		}
//		if(isChangeTime){
			//修改用车记录还车时间 地点
			powerUsedMapper.updateByPrimaryKeySelective(powerUsed);
			CarOperatePlan carOperatePlan = new CarOperatePlan();
			carOperatePlan.setPowerUsedId(powerUsed_old.getId());
			carOperatePlan.setStatus(CarOperatePlanEnum.status.USEING.getValue());
			//修改还车任务
			List<CarOperatePlan> plans = carOperatePlanService.selectByCondition(carOperatePlan );
			if(CollectionUtils.isNotEmpty(plans)){
				CarOperatePlan carOperatePlan2 = new CarOperatePlan();
				carOperatePlan2.setId(plans.get(0).getId());
				carOperatePlan2.setEnded(powerUsed.getCarReturnTime());
				carOperatePlanService.updateByPrimaryKeySelective(carOperatePlan2);
			}
			
			ButlerTask butlerTask = new ButlerTask();
			butlerTask.setPowerUsedId(powerUsed.getId());
			butlerTask.setType(2);
			butlerTask = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask );
			butlerTask.setPlanTime(powerUsed.getCarReturnTime());
			butlerTask.setTaskGetCarAddress(powerUsed.getCarReturnAddress());
			butlerTask.setModifierType(0);
			butlerTaskService.updateGetCarButlerTaskByMember(butlerTask);
			//记录时间变更日志
			PowerUsedTimelog tdPowerUsedTimelog = new PowerUsedTimelog();
			tdPowerUsedTimelog.setCreated(new Date());
			tdPowerUsedTimelog.setStartime(powerUsed.getCarReturnTime());
			tdPowerUsedTimelog.setPowerUsedId(powerUsed.getId());
			try {
				powerUsedTimelogService.insertSelective(tdPowerUsedTimelog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//		}
		returnMap.put("code", "1");
		return returnMap;
	}
	/**
	 * 取消用车
	 * @throws Exception 
	 */
	@Override
	public void cancelCarSubmit(PowerUsed powerUsed) throws Exception {
		//取消送车任务
		ButlerTask butlerTask = new ButlerTask();
		butlerTask.setType(3);
		butlerTask.setPowerUsedId(powerUsed.getId());
		ButlerTask butlerTask2 = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask);
		butlerTask2.setModifierType(0);
		butlerTaskService.updateSendCarButlerTaskByCancel(butlerTask2);
		//取消还车任务
		ButlerTask butlerTask3 = new ButlerTask();
		butlerTask3.setType(2);
		butlerTask3.setPowerUsedId(powerUsed.getId());
		butlerTask3 = butlerTaskService.getButlerTaskByPowerUsedId(butlerTask3);
		butlerTask3.setModifierType(0);
		butlerTaskService.updateGetCarButlerTaskByCancel(butlerTask3 );
//		修改启用车型为待使用
//		PowerUsed powerUsed = new PowerUsed();
//		powerUsed.setCarTypeId(Long.valueOf(carTypeId));
//		powerUsed.setMemberItemId(Long.valueOf(memberItemId));
//		powerUsed.setUsedStatus(1);
//		memberItemCartypeService.updateMyCarTypeStatus(powerUsed );
//		修改用车记录为已取消
		PowerUsed powerUsed2 = new PowerUsed();
		powerUsed2.setId(powerUsed.getId());
		powerUsed2.setUsedStatus(7);
		powerUsed2.setLasttimeModify(new Date());
		powerUsedMapper.updateByPrimaryKeySelective(powerUsed2 );
		
//		取消运营计划
		butlerTaskService.updateCarOperatePlanCancel(butlerTask);
		
		
//		carOperatePlanService.

		/*
//		超过用车预计时间2小时取消用车 扣除50%日使用费
		powerUsed = powerUsedMapper.selectByPrimaryKey(powerUsed.getId());
		long useCarTime = powerUsed.getCarUsedTime().getTime();
		long now = new Date().getTime();
		if(now>useCarTime){
			Long time =(now-useCarTime)/(60*60*1000);
			if(time>=2L){
				MemberItemCartype tdMemberItemCartype = new MemberItemCartype();
				tdMemberItemCartype.setMemberItemId(powerUsed.getMemberItemId());
				tdMemberItemCartype.setCarTypeId(powerUsed.getCarTypeId());
				List<MemberItemCartype> list = memberItemCartypeService.selectByCondition(tdMemberItemCartype );
				if(CollectionUtils.isNotEmpty(list)){
					MemberItemCartype itemCartype = list.get(0);
					BigDecimal dayPrice = itemCartype.getPrice();
					PowerUsedCost usedCost = powerUsedCostService.selectByPowerUsedId(powerUsed.getId());
//						查询扣除百分比
					BasicCost basicCost =basicCostService.selectBasicCost();
					BigDecimal delaycancelPrice = basicCost.getDelaycancelPrice();//延时还车百分比
					BigDecimal subtract = dayPrice.multiply(new BigDecimal(delaycancelPrice.doubleValue()/100)).setScale(2, BigDecimal.ROUND_HALF_UP);
					if(null != usedCost){
						usedCost.setContractPrice(subtract.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
						usedCost.setTotal(usedCost.getTotal().add(subtract.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP)));
//						如果用优惠券 将 cost表 优惠券信息为null SQL写
						powerUsedCostService.updateCancelCarSubmit(usedCost);
					}else{
						PowerUsedCost powerUsedCost= new PowerUsedCost();
						powerUsedCost.setPowerUsedId(powerUsed.getId());
						powerUsedCost.setContractPrice(subtract);
						powerUsedCost.setTotal(subtract);
						powerUsedCostService.insertSelective(powerUsedCost);
					}
					//修改memberitem
					MemberItem memberItem = memberItemService.findById(powerUsed.getMemberItemId());
					memberItem.setBalance(memberItem.getBalance().subtract(subtract).setScale(2, BigDecimal.ROUND_HALF_UP));
					memberItemService.updateByPrimaryKeySelective(memberItem);
					
					Member member = memberService.findMemberByID(powerUsed.getMemberId());
					MemberAccountLog accountLog = new MemberAccountLog();
					accountLog.setMemberItemId(memberItem.getId());
					accountLog.setMemberItemName(memberItem.getItemName());
					accountLog.setModifierId(powerUsed.getMemberId()+"");
					accountLog.setModifier(member.getName());
					accountLog.setAccountType(1);
					accountLog.setTransformType(10);
					accountLog.setMemberShareType(powerUsed.getMemberType());
					accountLog.setBalance(subtract.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
					accountLog.setCreated(new Date());
					accountLog.setMemberItemOwnerId(memberItem.getMemberId());
					accountLog.setTotal(subtract.multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP));
					accountLog.setBalanceReason("超过预计用车时间两个小时后取消用车，扣除"+delaycancelPrice+"%违约费");
					accountLog.setPreBalance(memberItem.getBalance());
					accountLog.setPreDeposit(memberItem.getDeposit());
					accountLog.setLasttimeModify(accountLog.getCreated());
					accountLog.setPowerUsedId(powerUsed.getId());
					
					memberAccountLogService.insertSelective(accountLog);
					
					
				}
			}
		}
		*/
	}
	/**
	 * 分页查询用车记录
	 * @param powerUsed
	 * @return
	 */
	@Override
	public List<PowerUsed> selectByPageList(PowerUsed powerUsed) {
		return this.powerUsedMapper.selectByPageList(powerUsed);
	}

	/**
	 * 查询当前权益下所有待送车 、使用中、已完成、已取消
	 * @param powerUsed 
	 * @return
	 */
	public List<PowerUsed> selectPowerUsedListOfStatus(PowerUsed powerUsed) {
		List<PowerUsed> list=powerUsedMapper.selectPowerUsedListOfStatus(powerUsed);
		for (PowerUsed powerUsedView : list) {
			powerUsedView.setPowerUsedCost(powerUsedCostService.selectByPowerUsedId(powerUsedView.getId()));
			if(powerUsedView.getCaroperateId()!=null){
				CarOperate carOperate = carOperateService.findById(powerUsedView.getCaroperateId());
				if(StringUtils.isBlank(carOperate.getImageUrl())){
					CarType carType = carTypeService.findById(carOperate.getCarTypeId());
					carOperate.setImageUrl(carType.getImageUrl());
				}
				powerUsedView.setCarOperate(carOperate);
			}
			if(powerUsedView.getChangeCarTypeId()!=null){
				powerUsedView.setCarTypeView(carTypeService.findById(powerUsedView.getChangeCarTypeId()));
			}else{
				powerUsedView.setCarTypeView(carTypeService.findById(powerUsedView.getCarTypeId()));
			}
			if(powerUsedView.getUsedStatus()==7 || powerUsedView.getUsedStatus()== 6){
				ButlerTask userButlerTask = new ButlerTask();
				userButlerTask.setPowerUsedId(powerUsedView.getId());
				List<ButlerTask> use = butlerTaskService.getButlerTaskListForSendCar(userButlerTask);
				if(use.size()>0){
					powerUsedView.setCarUsedTime(use.get(0).getCompleteTime());
				}
				List<ButlerTask> returnList = butlerTaskService.getButlerTaskListForGetCar(userButlerTask);
				if(returnList.size()>0){
					powerUsedView.setCarReturnTime(returnList.get(0).getCompleteTime());
				}
			}else if(powerUsedView.getUsedStatus()==3 || powerUsedView.getUsedStatus()== 4 || powerUsedView.getUsedStatus()== 5){
				ButlerTask userButlerTask = new ButlerTask();
				userButlerTask.setPowerUsedId(powerUsedView.getId());
				List<ButlerTask> use = butlerTaskService.getButlerTaskListForSendCar(userButlerTask);
				if(use.size()>0){
					powerUsedView.setCarUsedTime(use.get(0).getCompleteTime());
				}
			}
		}
		return list;
	}

	public PowerUsed selectPowerUsed(PowerUsed powerUsed) {
		powerUsed=powerUsedMapper.selectByPrimaryKey(powerUsed.getId());
		powerUsed.setPowerUsedCost(powerUsedCostService.selectByPowerUsedId(powerUsed.getId()));
		if(powerUsed.getCaroperateId()!=null){
			CarOperate carOperate = carOperateService.findById(powerUsed.getCaroperateId());
			powerUsed.setCarOperate(carOperate);
		}
		if(powerUsed.getChangeCarTypeId()!=null){
			powerUsed.setCarTypeView(carTypeService.findById(powerUsed.getChangeCarTypeId()));
		}else{
			powerUsed.setCarTypeView(carTypeService.findById(powerUsed.getCarTypeId()));
		}
		try {
			powerUsed.setMember(memberService.findMemberByID(powerUsed.getMemberId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ButlerTask userButlerTask = new ButlerTask();
		userButlerTask.setPowerUsedId(powerUsed.getId());
		List<ButlerTask> use = butlerTaskService.getButlerTaskListForSendCar(userButlerTask);
		if(use.size()>0){
			TransferList transferList = new TransferList();
			transferList.setTaskId(use.get(0).getId());
			transferList.setType(0);
			List<TransferList> useList = transferListService.selectByCondition(transferList);
			powerUsed.setUseList(useList);
			if(powerUsed.getUsedStatus()==7 || powerUsed.getUsedStatus()==6  || powerUsed.getUsedStatus()==3 || powerUsed.getUsedStatus()== 4 || powerUsed.getUsedStatus()== 5){
				if(use.size()>0){
					powerUsed.setCarUsedTime(use.get(0).getCompleteTime());
				}
			}
			
		}
		List<ButlerTask> returnList = butlerTaskService.getButlerTaskListForGetCar(userButlerTask);
		if(returnList.size()>0){
			TransferList transferList = new TransferList();
			transferList.setTaskId(returnList.get(0).getId());
			transferList.setType(0);
			List<TransferList> useList = transferListService.selectByCondition(transferList);
			powerUsed.setReturnList(useList);
			if(powerUsed.getUsedStatus()==7 || powerUsed.getUsedStatus()==6){
				if(returnList.size()>0){
					powerUsed.setCarReturnTime(returnList.get(0).getCompleteTime());
				}
			}
		}
		if(powerUsed.getUsedStatus() ==3 || powerUsed.getUsedStatus() ==4  || powerUsed.getUsedStatus() ==5 ){
			powerUsed.setCarUsedDay((int)DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(DateUtils.formatDate(use.get(0).getCompleteTime(), "yyyy-MM-dd")), DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd")) )+1);
		}else if(powerUsed.getUsedStatus() == 6){
			powerUsed.setCarUsedDay((int)DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(DateUtils.formatDate(use.get(0).getCompleteTime(), "yyyy-MM-dd")), DateUtils.parseDate(DateUtils.formatDate(returnList.get(0).getCompleteTime(), "yyyy-MM-dd")) )+1);
		}
		
		if(powerUsed.getChangeStatus() == 1){
			CarOperate carOperate = carOperateService.findById(powerUsed.getChangeCaroperateId());
			if(carOperate!=null){
				powerUsed.setBrand(carOperate.getCarBrand());
				powerUsed.setCarType(carOperate.getCarType());
				powerUsed.setCarPlateNum(carOperate.getCarPlateNum());;
			}
		}
		return powerUsed;
	}

	@Override
	public List<PowerUsed> selectByMemBerIdItemIdCarId(PowerUsed t) {
		return powerUsedMapper.selectByMemBerIdItemIdCarId(t);
	}

	/**
	 * 查询该车型是否已发起用车
	 * @param carTypeId
	 * @return false 未发起  true 已发起
	 */
	public Map<String, Object> checkIsUsedCar(Integer index, Member m) {
		
		Map<String, Object> map =  new HashMap<>();
		MemberItem memberItem = checkIsShareMember(m.getId());
		if(null==memberItem){
			map.put("isUsedCar", false);
			return map;
		}
		map.put("index", index);
		PowerUsed powerUsed = new PowerUsed();
		powerUsed.setMemberItemId(memberItem.getId());
		List<PowerUsed> powerUsedList = powerUsedMapper.selectPowerUsedOfUseingCars(powerUsed );
		
		//已发起用车的情况
		if(CollectionUtils.isNotEmpty(powerUsedList)&&index<=powerUsedList.size()){
			//车位已发起用车 进入用车信息页面
			PowerUsed used = powerUsedList.get(index);
			map.put("carTypeId", used.getCarTypeId());
			map.put("isUsedCar", true);
			map.put("memberId", m.getId());
			map.put("memberItemId", memberItem.getId());
			return map;
		}
		map.put("isUsedCar", false);
		return map;
	}

	@Override
	public List<PowerUsed> selectpowerUsedByCompleted(PowerUsed t) {
		return powerUsedMapper.selectpowerUsedByCompleted(t);
	}

	@Override
	public List<PowerUsed> selectpowerUsedByNoCompleted(PowerUsed t) {
		return powerUsedMapper.selectpowerUsedByNoCompleted(t);
	}

	@Override
	public Integer insertSelectiveReadExcel(PowerUsed powerUsed) {
		return this.powerUsedMapper.insertSelective(powerUsed);
	}

	@Override
	public Map<String, Object> insertPowerUsedAndButler(PowerUsed powerUsed,Long loginId) throws Exception {
		MemberItem memberItem = checkIsShareMember(powerUsed.getMemberId());
		Map<String, Object> returnMap = new HashMap<>();
		GetmoneyApply getmoneyApply = new GetmoneyApply();
		getmoneyApply.setMemberItemId(memberItem.getId());
		returnMap.put("msg", "");
		//		判断当前权益是否在押金提现审核中，结果如下： 
		List<GetmoneyApply> getmoneyApplys= getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply);
		if(CollectionUtils.isNotEmpty(getmoneyApplys)){
			returnMap.put("msg", "您正在提现中，不可发起用车");
			return returnMap;
		}
		//判断库存
		/*boolean isStore = carOperatePlanService.isStore(powerUsed.getCarTypeId(), powerUsed.getCarUsedTime(), powerUsed.getCarReturnTime());
		
		if(!isStore){
			returnMap.put("msg", "当前选择的车型库存不足，请重新选择");
			return returnMap;
		}*/
		
		powerUsed.setMemberItemId(memberItem.getId());
		
		BigDecimal balance = memberItem.getBalance();//余额
		BigDecimal deposit = memberItem.getDeposit();//押金
//		日租金x用车天数  四舍五入 保留2位小数
		BigDecimal totalPrice = null;
		
		boolean isdeposit=true;//押金足
		boolean isbalance=true;//余额
		
		ItemCartype itemCartype = new ItemCartype();
		itemCartype.setItemId(memberItem.getItemId());
		itemCartype.setCartypeId(powerUsed.getCarTypeId());
		List<ItemCartype> itemCartypeList = itemCartypeService.selectByCondition(itemCartype );
		BigDecimal dayPrice =null;
		//获取套餐下该车型
		if(CollectionUtils.isNotEmpty(itemCartypeList)){
			dayPrice=itemCartypeList.get(0).getDayPrice();
		}else{
			//查询车辆信息
			CarOperate car = carOperateService.findById(powerUsed.getCaroperateId());
			List<CarType> carTypes = carTypeService.selectCarTypes(car.getCarType());
			if(CollectionUtils.isNotEmpty(carTypes)){
				CarType carType = carTypes.get(0);
				dayPrice = carType.getDayPrice();
			}
		}
		
		Integer usedDay = powerUsed.getCarUsedDay();
		totalPrice = dayPrice.multiply(new BigDecimal(usedDay).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		CouponInfo couponInfo = null;
		BigDecimal couponPrice=null;
		if(null!=powerUsed.getCouponInfoId()){
			couponInfo = couponInfoService.findById(powerUsed.getCouponInfoId());
			couponPrice = couponInfo.getPrice();//优惠券金额
			totalPrice = totalPrice.subtract(couponPrice).setScale(2, BigDecimal.ROUND_HALF_UP); //减去优惠券金额
			
		}
		
		BasicThreshold basicThreshold = basicThresholdService.selectBasicThreshold();
		BigDecimal depositUsecar = basicThreshold.getDepositUsecar();//'发起用车时会员押金不得少于(元)',
//		当前用户押金小于 阀值
		if(deposit.doubleValue()<depositUsecar.doubleValue()){
			isdeposit=false;
		}
		
		//余额不足支持本次用车
		if(totalPrice.doubleValue()>balance.doubleValue()){
			isbalance=false;
		} 
		
		if(isbalance&&isdeposit){//都充足 发起用车成功
			
			//判断当前是否有可用车位
			PowerUsed powerUsedNew = new PowerUsed();
			powerUsedNew.setMemberItemId(memberItem.getId());
			List<PowerUsed> powerUsedList = powerUsedMapper.selectPowerUsedOfUseingCars(powerUsedNew );
			Item item = itemService.findById(memberItem.getItemId());
//			//我的可用车位数
			Integer enableCount = item.getEnableCount();
			if(CollectionUtils.isNotEmpty(powerUsedList)&&enableCount<=powerUsedList.size()){
				returnMap.put("msg", "该用户无可用车位，无法发起用车");
				return returnMap;
			}
			
			MemberItemShare itemShare = memberItemShareService.selectByMemberId(powerUsed.getMemberId());
			if(itemShare!=null){
				powerUsed.setMemberType(itemShare.getIsMain());
			}
			
			//查询区所属市
			Nation n = new Nation();
			n.setCode(powerUsed.getCityCode());
			n = nationService.getParentNationByCityeCodeOrId(n);
			powerUsed.setCityCode(n.getCode());

			if(null!=powerUsed.getCouponInfoId()){
				//修改优惠券状态
				couponInfo.setStatus(4);
				couponInfo.setUsedTime(new Date());
				couponInfoService.updateByPrimaryKeySelective(couponInfo);
			}
			
//			插入用车记录
			powerUsed.setMemberItemName(memberItem.getItemName());
			powerUsed.setCreated(new Date());
			powerUsed.setLasttimeModify(powerUsed.getCreated());
			powerUsedMapper.insertSelective(powerUsed);
			returnMap.put("powerUsedId",powerUsed.getId());
			//插入用车金额记录
			insertPowerUsedCost(powerUsed, couponPrice, couponInfo);
			//创建任务
			ButlerTask sendButlerTask = createUseCarTasked(powerUsed, memberItem,loginId);
			//送车完成扣第一次用车费
			butlerTaskService.deductionOfFare(sendButlerTask, dayPrice);
			//更改车辆状态
			//this.updateCarOperateStatus(butlerTask);
			//TODO 送车完成的时候 修改 td_member_item_cartype status改为 使用中 修改当前使用车辆ID
//			this.updateMemberItemCartype(butlerTask);
			//给用户发送消息推送--出发取车微信
	  		String value ="极车管家已将您的爱车送到。";
	  		butlerTaskService.sendMessageByTaskStatusChangeApp(sendButlerTask,value);
	  		//给用户发送app推送消息
	  		String wechatMessage = "极车管家已将您的爱车送到";
	  		this.wechatMessageService.sendMessageByTaskStatusChange(sendButlerTask,wechatMessage);
			
			return returnMap;
		}
		return null;
	}

}
