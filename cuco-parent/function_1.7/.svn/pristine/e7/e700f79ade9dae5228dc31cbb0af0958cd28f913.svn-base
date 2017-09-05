package com.hy.gcar.service.powerUserd.impl;

import com.alibaba.fastjson.JSON;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.constant.CarOperateEnum;
import com.hy.gcar.dao.PowerUsedCostLogMapper;
import com.hy.gcar.entity.*;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;
import com.hy.gcar.service.carOperte.CarOperateLogService;
import com.hy.gcar.service.carOperte.CarOperateService;
import com.hy.gcar.service.carType.CarTypeService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service(value = "tdPowerUsedCostLogService")
public class PowerUsedCostLogServiceImpl implements PowerUsedCostLogService {

    @Autowired
    private PowerUsedCostLogMapper<PowerUsedCostLog>tdPowerUsedCostLogMapper;


    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MemberItemService memberItemService;
    @Autowired
    ItemCartypeService itemCartypeService;
    @Autowired
    PowerUsedCostService powerUsedCostService;
    @Autowired
    MemberItemShareService memberItemShareService;
    @Autowired
    PowerUsedService powerUsedService;

    @Autowired
    MemberAccountLogService memberAccountLogService;
    
    @Autowired
    BasicRestrictionService basicRestrictionService;
    
    @Autowired
    CarTypeService carTypeService;
    
    @Autowired
    CarOperateService carOperateService;
    
    @Autowired
    CarOperateLogService carOperateLogService;

    @Override
    public Integer insertSelective(PowerUsedCostLog tdPowerUsedCostLog) throws Exception {
           return this.tdPowerUsedCostLogMapper.insertSelective(tdPowerUsedCostLog);
        }

    @Override
    public Integer insertBatch(List<PowerUsedCostLog> tdPowerUsedCostLog) throws Exception {
           return this.tdPowerUsedCostLogMapper.insertBatch(tdPowerUsedCostLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdPowerUsedCostLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdPowerUsedCostLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(PowerUsedCostLog tdPowerUsedCostLog) {
           return this.tdPowerUsedCostLogMapper.updateByPrimaryKeySelective(tdPowerUsedCostLog);
    }

    @Override
    public PowerUsedCostLog findById(Object id) {
           return (PowerUsedCostLog) this.tdPowerUsedCostLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PowerUsedCostLog> selectByCondition(PowerUsedCostLog tdPowerUsedCostLog) {
           return (List<PowerUsedCostLog>) this.tdPowerUsedCostLogMapper.selectByCondition(tdPowerUsedCostLog);
    }

    @Override
    public Integer selectCountByCondition(PowerUsedCostLog tdPowerUsedCostLog) {
           return  this.tdPowerUsedCostLogMapper.selectCountByCondition(tdPowerUsedCostLog);
    }


    public BigDecimal findCarTypeDailyRent(ButlerTask butlerTask,PowerUsed powerUsed){
        // 根据'会员权益ID'，'当前使用车辆ID' 查询 车型的日租金
        Long memberItemID = powerUsed.getMemberItemId();
        MemberItem memberItem = new MemberItem();
        memberItem.setId(memberItemID);
        memberItem = memberItemService.findById(memberItem);


        ItemCartype itemCartype = new ItemCartype();
        itemCartype.setItemId(memberItem.getItemId());
        itemCartype.setCartypeId(powerUsed.getCarTypeId());
        List<ItemCartype> itemCartypes = itemCartypeService.selectByCondition(itemCartype);
        BigDecimal price = null;
        if(itemCartypes.size() == 0){
            this.logger.info("根据 memberItem.getItemId() "+ memberItem.getItemId() +",powerUsed.getCarTypeId()"+powerUsed.getCarTypeId()+"查询车型日租金 ItemCartype 不存在 ");
        }else{
            itemCartype  = itemCartypes.get(0);
            price = itemCartype.getDayPrice();
        }
        return price;
    }

    @Override
    public void deductionOfFare(ButlerTask butlerTask, PowerUsed powerUsed) throws Exception {
        this.logger.info("导入线下数据生成 扣费记录开始.......powerUsed:" + JSON.toJSONString(powerUsed));
        Assert.notNull(butlerTask, "任务对象不允许为空");
        Assert.notNull(powerUsed, "用车任务对象不允许为空");
        Assert.notNull(powerUsed.getId(), "用车任务对象id不允许为空");
        //BigDecimal price = this.findCarTypeDailyRent(butlerTask, powerUsed);
        BigDecimal price = new BigDecimal(1199);

        CarOperate carOperate = carOperateService.findById(butlerTask.getCarOperateId());
//        BigDecimal couponPrice = powerUsedCosts.get(0).getCouponPrice();
//        //如果有优惠卷第一次扣费的时候则抵扣用车费用
//        BigDecimal withFare = price.subtract(couponPrice).setScale(2, BigDecimal.ROUND_HALF_UP);//用车费
//        if(-1 == withFare.compareTo(new BigDecimal(0))){
//            //表示优惠券金额大于日使用用车费
//            withFare = new BigDecimal(0);
//        }
        //td_power_used_cost 用户费用进行累加，吧总用车费用进行累加
        //防止更新没有必要的数据，重新new一个实体

        Date begin = powerUsed.getCarUsedTime();
        Date end = null;
        if (powerUsed.getUsedStatus().intValue() == 3) {
            end = new Date();
        } else if (powerUsed.getUsedStatus().intValue() == 6) {
            end = powerUsed.getCarReturnTime();
        }
        
    	long t = end.getTime() - begin.getTime();
		double count = t/(60*60*1000);
		this.logger.info("用车开始时间 和用车结束时间相差 多少小时     count:>>>" + count);
		int days = (int) count/24;
		if(count%24 >= 2){
			days = days + 1;
	        this.logger.info("----- days" + days);
		} 
        
        this.logger.info("导入线下数据生成 powerUsedID :" + powerUsed.getId() + ",begin:" + DateUtils.formatDate(begin,"yyyy-MM-dd HH:mm:ss") + "end:"+ DateUtils.formatDate(end,"yyyy-MM-dd HH:mm:ss"));
        
        Date log_date = null;
        BigDecimal usageAmount = new BigDecimal(0);
        MemberAccountLog mal = new MemberAccountLog();
        mal.setPowerUsedId(powerUsed.getId());
        MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
        for (int i = 0; i < days; i++) {
            if(i==0){
            	log_date = powerUsed.getCarUsedTime();
            }else if(i == 1){
            	log_date = DateUtils.addHours(log_date, 26);
            }else{
            	log_date = DateUtils.addHours(log_date, 24);
            }
            boolean  checkLimitNumber = this.checkLimitNumber(log_date, carOperate);
            if(checkLimitNumber){
            	usageAmount = new BigDecimal(0); 
            }else{
            	usageAmount = price;
            }
        	
            //查找当前用车的优惠卷
            PowerUsedCost powerUsedCost = new PowerUsedCost();
            powerUsedCost.setPowerUsedId(butlerTask.getPowerUsedId());
            List<PowerUsedCost> powerUsedCosts = powerUsedCostService.selectByCondition(powerUsedCost);
            if (powerUsedCosts.size() == 0) {
                this.logger.info("任务  butlerTask  " + butlerTask.getId() + ",根据 getPowerUsedId " + butlerTask.getPowerUsedId() + "用车记录费用明细表 为空");
                throw new Exception("用车记录费用明细表 为空");
            }
            powerUsedCost = powerUsedCosts.get(0);
            PowerUsedCost powerUsedCostNew = new PowerUsedCost();
            powerUsedCostNew.setTotal(powerUsedCost.getTotal().subtract(usageAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
            powerUsedCostNew.setUsedcarPrice(powerUsedCost.getUsedcarPrice().subtract(usageAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
            

            powerUsedCostNew.setId(powerUsedCost.getId());
            powerUsedCostService.updateByPrimaryKeySelective(powerUsedCostNew);

            //插入一条权益消费的记录  td_member_account_log
            List<MemberAccountLog>  mals =  memberAccountLogService.selectByCondition(mal);
            if(CollectionUtils.isEmpty(mals)){
            	MemberAccountLog memberAccountLog = new MemberAccountLog();
                MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
                if (null == memberItemShare) {
                    memberAccountLog.setMemberShareType(0);
                }
                if (null != memberItemShare) {
                    if (1 == memberItemShare.getIsMain()) {
                        memberAccountLog.setMemberShareType(2);
                    } else {
                        memberAccountLog.setMemberShareType(1);
                    }
                }
                memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
                memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
                memberAccountLog.setAccountType(1);
                memberAccountLog.setTransformType(4);
                memberAccountLog.setDeposit(new BigDecimal(0));
                
                memberAccountLog.setBalance(usageAmount.multiply(new BigDecimal(-1)));
     
                memberAccountLog.setCreated(log_date);
                memberAccountLog.setTotal(memberAccountLog.getBalance().add(memberAccountLog.getDeposit()).setScale(2, BigDecimal.ROUND_HALF_UP));
                memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
                memberAccountLog.setMemberItemName(memberItem.getItemName());
                memberAccountLog.setPreBalance(memberItem.getBalance());
                memberAccountLog.setPreDeposit(memberItem.getDeposit());
                if(checkLimitNumber){
                    memberAccountLog.setRemark("导入线下数据扣除每天的用车费(限行)");
                }else{
                    memberAccountLog.setRemark("导入线下数据扣除每天的用车费");
                }
                memberAccountLog.setBalanceReason("车辆使用费");
                memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
                memberAccountLog.setModifierId(butlerTask.getOperaterId());
                memberAccountLog.setModifier(butlerTask.getOperaterName());
                this.memberAccountLogService.insertSelective(memberAccountLog);
            	
            }else{
            	MemberAccountLog malUpdate = new MemberAccountLog();
            	malUpdate.setId(mals.get(0).getId());
            	malUpdate.setBalance(mals.get(0).getBalance().subtract(usageAmount));
            	malUpdate.setTotal(mals.get(0).getTotal().subtract(usageAmount)); 
            	this.memberAccountLogService.updateByPrimaryKeySelective(malUpdate);
            }
            


            //新增td_power_used_cost_log 日志表
            PowerUsedCostLog powerUsedCostLog = new PowerUsedCostLog();
            powerUsedCostLog.setMemberId(Long.valueOf(butlerTask.getMemberId()));
            powerUsedCostLog.setCaroperateId(butlerTask.getCarOperateId());
            powerUsedCostLog.setDeductionPrice(usageAmount.multiply(new BigDecimal(-1)));
            powerUsedCostLog.setMemberName(butlerTask.getMemberName());
            powerUsedCostLog.setPowerUsedCostId(powerUsedCost.getId());
            powerUsedCostLog.setDeductionTime(log_date);
            this.insertSelective(powerUsedCostLog);
            
            
//            CarOperateLog carLog = new CarOperateLog();
//			carLog.setCreated(log_date);
//			carLog.setMemberId(Long.valueOf(butlerTask.getMemberId()));
//			carLog.setMemberName(butlerTask.getMemberName());
//			carLog.setOperateId(butlerTask.getOperaterId());
//			carLog.setOperateName(butlerTask.getOperaterName());
//			carLog.setOperateNum(carOperate.getOperateNum());
//			carLog.setTakein(usageAmount);
//			carLog.setType(CarOperateEnum.status.MEMBERUSEING.getValue());
//			carLog.setRemark("会员使用中");
//			if(checkLimitNumber){
//				carLog.setRemark(carLog.getRemark()+"（限行）");
//			}
//			carOperateLogService.insertSelective(carLog);
           
            //扣减权益yu余额
            MemberItem memberItem_new = new MemberItem();
            memberItem_new.setId(memberItem.getId());
            memberItem_new.setBalance(memberItem.getBalance().subtract(usageAmount).setScale(2, BigDecimal.ROUND_HALF_UP));
            this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
        }
    }
    
    
    private boolean checkLimitNumber(Date date, CarOperate carOperate) {
        //验证是否限号
        BasicRestriction basicRestriction = new BasicRestriction();
        basicRestriction.setCityId(2);
        basicRestriction.setRestrictionsDate(DateUtils.parseDate(DateUtils.formatDate(date,"yyyy-MM-dd")));
        List<BasicRestriction> basicRestrictions = basicRestrictionService.selectByCondition(basicRestriction);
        //根据车型判断车辆是否限号
        List<CarType> carTypes = carTypeService.selectCarTypeByIds(Arrays.asList(carOperate.getCarTypeId()));
        if(CollectionUtils.isNotEmpty(carTypes)){
           CarType carType = carTypes.get(0);
            if(carType.getIsRestriction().intValue() == 0){
                return false;
            }
        }

        if(CollectionUtils.isNotEmpty(basicRestrictions)){
            basicRestriction = basicRestrictions.get(0);
            String restrictions = basicRestriction.getRestrictions();
            List<String> restrictionList = Arrays.asList(restrictions.split(","));
            String carPlateNum = null;
            String last = null;
            carPlateNum = carOperate.getCarPlateNum();
            last = carPlateNum.substring(carPlateNum.length()-1);
            if(!isInteger(last)){
                last = "0";
            }
            if(restrictionList.contains(last)){
                return true;
            }
        }
        return false;
    }
    
    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
   */
   public static boolean isInteger(String str) {
       Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
       return pattern.matcher(str).matches();
   }
    
    public static void main(String[] args) {
		Date begin = DateUtils.parseDate("2016-01-01 23:00:00");
		
		Date end = DateUtils.parseDate("2016-01-05 23:59:00");
		
		long t = end.getTime()-begin.getTime();
		double count = t/(60*60*1000);
		System.out.println(count);
		int dates = (int)count/24;
		if(count%24 >= 2){
			dates = dates + 1;
			System.out.println("----------");
		} 
		
		System.out.println(dates);
		
		
				
	}
}
