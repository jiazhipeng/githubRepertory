package com.hy.test.service;

import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.*;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostLogService;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 海易出行 on 2016/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath*:/spring-junit-config/applicationContext-test.xml"}) //加载配置文件
public class DeductionOfFareTest {
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
    PowerUsedCostLogService powerUsedCostLogService;

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









    /**
     * 送车完成时 权益余额扣减第一次费用(如果有优惠卷，则需要用优惠券抵扣)，同时插入用车费用日志表
     */
    public void DeductionOfFare(ButlerTask butlerTask,PowerUsed powerUsed) throws Exception {
        Assert.notNull(butlerTask,"任务对象不允许为空");
        Assert.notNull(powerUsed,"用车任务对象不允许为空");
        Assert.notNull(powerUsed.getId(),"用车任务对象id不允许为空");
        BigDecimal price = this.findCarTypeDailyRent(butlerTask,powerUsed);
        //查找当前用车的优惠卷
        PowerUsedCost powerUsedCost = new PowerUsedCost();
        powerUsedCost.setPowerUsedId(butlerTask.getPowerUsedId());
        List<PowerUsedCost> powerUsedCosts = powerUsedCostService.selectByCondition(powerUsedCost);
        if(powerUsedCosts.size() == 0){
            this.logger.info("任务  butlerTask  "+butlerTask.getId() +",根据 getPowerUsedId " + butlerTask.getPowerUsedId() + "用车记录费用明细表 为空");
            throw  new Exception("用车记录费用明细表 为空");
        }
//        powerUsedCost = powerUsedCosts.get(0);
//        BigDecimal couponPrice = powerUsedCosts.get(0).getCouponPrice();
//        //如果有优惠卷第一次扣费的时候则抵扣用车费用
//        BigDecimal withFare = price.subtract(couponPrice).setScale(2, BigDecimal.ROUND_HALF_UP);//用车费
//        if(-1 == withFare.compareTo(new BigDecimal(0))){
//            //表示优惠券金额大于日使用用车费
//            withFare = new BigDecimal(0);
//        }
        //td_power_used_cost 用户费用进行累加，吧总用车费用进行累加
        //防止更新没有必要的数据，重新new 一个实体

        Date begin = DateUtils.parseDate(DateUtils.formatDate(powerUsed.getCarUsedTime(),"yyyy-MM-dd 00:00:00"));
        Date end = null;
        if(powerUsed.getUsedStatus().intValue() == 3){
            end = DateUtils.parseDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd 00:00:00"));
        }else if(powerUsed.getUsedStatus().intValue() == 6){
            end = DateUtils.parseDate(DateUtils.formatDate(powerUsed.getCarReturnTime(),"yyyy-MM-dd 00:00:00"));
        }
        int days = (int)DateUtils.getDistanceOfTwoDate(begin,end);

        for(int i = 0; i <= days; i++){
            PowerUsedCost powerUsedCostNew = new PowerUsedCost();
            powerUsedCostNew.setTotal(powerUsedCost.getTotal().subtract(price).setScale(2, BigDecimal.ROUND_HALF_UP));
            powerUsedCostNew.setUsedcarPrice(powerUsedCost.getUsedcarPrice().subtract(price).setScale(2, BigDecimal.ROUND_HALF_UP));
            powerUsedCostNew.setId(powerUsedCost.getId());
            powerUsedCostService.updateByPrimaryKeySelective(powerUsedCostNew);

            //插入一条权益消费的记录  td_member_account_log
            MemberAccountLog memberAccountLog = new MemberAccountLog();
            MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(powerUsed.getMemberId());
            if(null==memberItemShare){
                memberAccountLog.setMemberShareType(0);
            }
            if(null!=memberItemShare){
                if(1==memberItemShare.getIsMain()){
                    memberAccountLog.setMemberShareType(2);
                }else{
                    memberAccountLog.setMemberShareType(1);
                }
            }
            memberAccountLog.setPowerUsedId(powerUsedCost.getPowerUsedId());
            memberAccountLog.setMemberItemId(powerUsed.getMemberItemId());
            memberAccountLog.setAccountType(1);
            memberAccountLog.setTransformType(4);
            memberAccountLog.setDeposit(new BigDecimal(0));
            memberAccountLog.setBalance(price.multiply(new BigDecimal(-1)));
            memberAccountLog.setCreated(DateUtils.addDays(powerUsed.getCarUsedTime(),i));
            memberAccountLog.setTotal(memberAccountLog.getBalance().add(memberAccountLog.getDeposit()).setScale(2, BigDecimal.ROUND_HALF_UP));
            memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
            MemberItem memberItem = this.memberItemService.findById(powerUsed.getMemberItemId());
            memberAccountLog.setMemberItemName(memberItem.getItemName());
            memberAccountLog.setPreBalance(memberItem.getBalance());
            memberAccountLog.setPreDeposit(memberItem.getDeposit());
            memberAccountLog.setRemark("导入线下数据扣除每天的用车费");
            memberAccountLog.setBalanceReason("车辆使用费");
            memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
            memberAccountLog.setModifierId(butlerTask.getOperaterId());
            memberAccountLog.setModifier(butlerTask.getOperaterName());
            this.memberAccountLogService.insertSelective(memberAccountLog);


            //新增td_power_used_cost_log 日志表
            PowerUsedCostLog powerUsedCostLog = new PowerUsedCostLog();
            powerUsedCostLog.setMemberId(Long.valueOf(butlerTask.getMemberId()));
            powerUsedCostLog.setCaroperateId(butlerTask.getCarOperateId());
            powerUsedCostLog.setDeductionPrice(price.multiply(new BigDecimal(-1)));
            powerUsedCostLog.setMemberName(butlerTask.getMemberName());
            powerUsedCostLog.setPowerUsedCostId(powerUsedCost.getId());
            powerUsedCostLog.setDeductionTime(DateUtils.addDays(powerUsed.getCarUsedTime(),i));
            powerUsedCostLogService.insertSelective(powerUsedCostLog);

            //扣减权益yu余额
            MemberItem memberItem_new = new MemberItem();
            memberItem_new.setId(memberItem.getId());
            memberItem_new.setBalance(memberItem.getBalance().subtract(price).setScale(2, BigDecimal.ROUND_HALF_UP));
            this.memberItemService.updateByPrimaryKeySelective(memberItem_new);
        }
    }
}
