package com.hy.gcar.service.chargeOrderLog.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.ChargeOrderLogMapper;
import com.hy.gcar.entity.ChargeOrder;
import com.hy.gcar.entity.ChargeOrderLog;
import com.hy.gcar.entity.ChargeReward;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.chargeOrderLog.ChargeOrderLogService;
import com.hy.gcar.service.chargeReward.ChargeRewardService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;

@Service(value = "tdChargeOrderLogService")
public class ChargeOrderLogServiceImpl implements ChargeOrderLogService {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ChargeOrderLogMapper<ChargeOrderLog>tdChargeOrderLogMapper;

    @Autowired
    private ChargeOrderService chargeOrderService;

    @Autowired
    private MemberAccountLogService memberAccountLogService;

    @Autowired
    MemberItemService memeberItemService;
    
    @Autowired
    MemberItemShareService memberItemShareService;
    
    @Autowired
    private ChargeRewardService chargeRewardService;

    
    @Override
    public Integer insertSelective(ChargeOrderLog tdChargeOrderLog) throws Exception {
           return this.tdChargeOrderLogMapper.insertSelective(tdChargeOrderLog);
        }

    @Override
    public Integer insertBatch(List<ChargeOrderLog> tdChargeOrderLog) throws Exception {
           return this.tdChargeOrderLogMapper.insertBatch(tdChargeOrderLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdChargeOrderLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdChargeOrderLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ChargeOrderLog tdChargeOrderLog) {
           return this.tdChargeOrderLogMapper.updateByPrimaryKeySelective(tdChargeOrderLog);
    }

    @Override
    public ChargeOrderLog findById(Object id) {
           return (ChargeOrderLog) this.tdChargeOrderLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据订单号查询订单日志表
     * @param orderNo
     * @return
     */
    @Override
    public ChargeOrderLog findChargeOrderLogByOrderNo(String orderNo){

        ChargeOrderLog chargeOrderLog = new ChargeOrderLog();
        chargeOrderLog.setOrderNo(orderNo);
        List<ChargeOrderLog> chargeOrderLogs = this.selectByCondition(chargeOrderLog);
        if(CollectionUtils.isNotEmpty(chargeOrderLogs)){
            return chargeOrderLogs.get(0);
        }
        return null;
    }

    @Override
    public List<ChargeOrderLog> selectByCondition(ChargeOrderLog tdChargeOrderLog) {
           return (List<ChargeOrderLog>) this.tdChargeOrderLogMapper.selectByCondition(tdChargeOrderLog);
    }

    @Override
    public Integer selectCountByCondition(ChargeOrderLog tdChargeOrderLog) {
           return  this.tdChargeOrderLogMapper.selectCountByCondition(tdChargeOrderLog);
    }

    @Override
    public ChargeOrderLog createChargeOrderLog(ChargeOrderLog chargeOrderLog) throws Exception {
        Assert.notNull(chargeOrderLog,"权益充值消费日志不能为空");
        chargeOrderLog.setCreated(new Date());
        chargeOrderLog.setOrderStatus(1);
        chargeOrderLog.setLasttimeModify(new Date());
        chargeOrderLog.setLogType(0);
        this.insertSelective(chargeOrderLog);
        return chargeOrderLog;
    }

    @Override
    public ChargeOrderLog updateChargeOrderLogCancel(ChargeOrderLog chargeOrderLog) throws Exception {
        Assert.notNull(chargeOrderLog,"权益充值消费日志不能为空");
        Assert.notNull(chargeOrderLog.getOrderId(),"支付id不能为空");
        chargeOrderLog.setOrderStatus(2);
        chargeOrderLog.setLasttimeModify(new Date());
        this.updateByPrimaryKeySelective(chargeOrderLog);
        return chargeOrderLog;
    }

    @Override
    public ChargeOrderLog updateChargeOrderLogComplete(ChargeOrderLog chargeOrderLog) throws Exception {
        Assert.notNull(chargeOrderLog,"权益充值消费日志不能为空");
        Assert.notNull(chargeOrderLog.getOrderNo(),"订单号不允许为空");
        String orderNo = chargeOrderLog.getOrderNo();
        ChargeOrder co = chargeOrderService.findChargeOrderByChargeOrderNo(chargeOrderLog.getOrderNo());
        /******充值返现金额  比例查表********/
        ChargeReward chargeReward = chargeRewardService.selectObjectByCodition(new ChargeReward());
        if(chargeReward != null){
        	 Date start = chargeReward.getStartTime();
        	 Date end = chargeReward.getEndTime();
        	 Date nowDate = new Date();
        	 //查询是否有充返活动
        	 if(start.before(nowDate) && end.after(nowDate)){
        		  Double proportion = Double.parseDouble(chargeReward.getPercent().toEngineeringString());
        		    //金额扣除赠送金额
        	    	BigDecimal total = co.getBalance();
        	    	//实际优惠的金额   比例后期调整
        	    	if(proportion > 1){
        	    		BigDecimal disCount = total.multiply(new BigDecimal(proportion -1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            	    	BigDecimal balance = total.add(disCount).setScale(2, BigDecimal.ROUND_HALF_UP);
            	    	chargeOrderLog.setBalance(balance);
            	    	chargeOrderLog.setRemark("充返活动赠送"+disCount+"元");
            	    	ChargeOrder chargeOrder = new ChargeOrder();
            	    	chargeOrder.setId(co.getId());
            	    	chargeOrder.setChargeNo(co.getChargeNo());
            	    	chargeOrder.setGiftAmount(disCount);
            	    	chargeOrder.setRemark("充返活动赠送"+disCount+"元");
            	    	co.setGiftAmount(disCount);
            	    	chargeOrderService.updateByPrimaryKeySelective(chargeOrder);
        	    	}
        	 }
        }
       
    	 /******充值返现金额********/
        chargeOrderLog.setOrderStatus(3);
        chargeOrderLog.setLasttimeModify(new Date());
        chargeOrderLog.setCompletionTime(new Date());
        chargeOrderLog = this.updateChargeOrderLogByNotPaid(chargeOrderLog);
        if(chargeOrderLog == null){
            logger.info(orderNo + "已经更新，不允许再次更新");
            return chargeOrderLog;
        }

        ChargeOrder chargeOrder_old = chargeOrderService.findChargeOrderByChargeOrderNo(chargeOrderLog.getOrderNo());
        
        //更新chargeOrder 表
        updateChargeOrderComplete(chargeOrderLog);
        
       // ChargeOrder co = chargeOrderService.findChargeOrderByChargeOrderNo(chargeOrderLog.getOrderNo());
       // co.setBalance(chargeOrderLog.getBalance());
       
        //先查询 MemberItemShare 
        MemberItemShare memberItemShare = memberItemShareService.selectByMemberId(Long.valueOf(co.getCreatedMemberId()));
        
        MemberItem memberItem = new MemberItem();
        memberItem.setId(memberItemShare.getMemberItemId());
        memberItem = memeberItemService.findById(memberItem);
        //插入权益充值消费日志表
        this.createMemberAccountLog(co, memberItem,memberItemShare,chargeOrderLog);
        //支付成功还没有修改订单状态的时候，续费订单主表已经在后台运营系统被修改过，则不需要把用户押金或者余额相加
        this.updateMemeberItem(chargeOrder_old, co, memberItem);
        return chargeOrderLog;
    }
    
    /**
     * 更新chargeOrderLog为已经完成
     * @param chargeOrderLog
     */
	public void updateChargeOrderComplete(ChargeOrderLog chargeOrderLog) {
		ChargeOrder chargeOrder = new ChargeOrder();
        chargeOrder.setStatus(3);
        chargeOrder.setChargeOrderNo(chargeOrderLog.getOrderNo());
        chargeOrder.setCompletedTime(chargeOrderLog.getCompletionTime());
        chargeOrder.setBank(chargeOrderLog.getBank());
        chargeOrder.setLasttimeModify(new Date());
        chargeOrderService.updateByPrimaryKeySelective(chargeOrder);
	}
    
    /**
     * 支付成功还没有修改订单状态的时候，续费订单主表已经在后台运营系统被修改过，则不需要把用户押金或者余额相加
     * @param chargeOrder_old
     * @param co
     * @param memberItem
     */
	public void updateMemeberItem(ChargeOrder chargeOrder_old, ChargeOrder co, MemberItem memberItem) {
		if(chargeOrder_old.getStatus() != 3){
			MemberItem memberItemUpdate = new MemberItem();
			memberItemUpdate.setBalance(memberItem.getBalance().add(co.getBalance().add(co.getGiftAmount())));
			memberItemUpdate.setDeposit(memberItem.getDeposit().add(co.getDeposit()));
			memberItemUpdate.setId(memberItem.getId());
			memberItemUpdate.setLasttimeModify(new Date());
			memeberItemService.updateByPrimaryKeySelective(memberItemUpdate);
        }
	}
	
	/**
	 * 余额
	 * 创建createMemberAccountLog
	 * @param co
	 * @param memberItem
	 * @throws Exception
	 */
	public void createMemberAccountLog(ChargeOrder co, MemberItem memberItem,MemberItemShare memberItemShare,ChargeOrderLog chargeOrderLog) throws Exception {
		MemberAccountLog memberAccountLog = new MemberAccountLog();
      //插入一条权益消费的记录td_member_account_log
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
        memberAccountLog.setMemberItemId(memberItem.getId());
        memberAccountLog.setAccountType(co.getAccountType());
        memberAccountLog.setTransformType(1);
       // memberAccountLog.setDeposit(co.getDeposit());
        memberAccountLog.setBalance(co.getBalance());
        memberAccountLog.setCreated(new Date());
        memberAccountLog.setTotal(memberAccountLog.getBalance().add(co.getGiftAmount()));
        memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
        memberAccountLog.setMemberItemName(memberItem.getItemName());
        memberAccountLog.setPreBalance(memberItem.getBalance());
        memberAccountLog.setPreDeposit(memberItem.getDeposit());
      //  memberAccountLog.setTransformType(co.getCreatedSource());//变动类型：1，客户续费；2，后台人工续费；3，后台人工扣费；4，用车扣费；
        //private Integer accountType;//续费类型：1，余额；2，押金；3，余额和押金
        if(memberAccountLog.getAccountType().intValue() == 1){
            memberAccountLog.setRemark("余额-客户续费");
            if(co.getGiftAmount().compareTo(BigDecimal.ZERO) > 0){
            	 memberAccountLog.setRemark("余额-客户续费"+chargeOrderLog.getRemark());
            }
    		memberAccountLog.setBalanceReason("余额-客户续费");
        }else if(memberAccountLog.getAccountType().intValue() == 2){
        	createMemberAccountDepositLog(co, memberItem, memberItemShare, chargeOrderLog);
            return;
        }else if(memberAccountLog.getAccountType().intValue() == 3){
        	  memberAccountLog.setAccountType(1);
        	  memberAccountLog.setRemark("余额-客户续费");
              if(co.getGiftAmount().compareTo(BigDecimal.ZERO) > 0){
              	 memberAccountLog.setRemark("余额-客户续费"+chargeOrderLog.getRemark());
              }
      		  memberAccountLog.setBalanceReason("余额-客户续费");
      		  createMemberAccountDepositLog(co, memberItem, memberItemShare, chargeOrderLog);
        }

        memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
        memberAccountLog.setModifier(co.getCreatedMemberName());
        memberAccountLog.setModifierId(co.getCreatedMemberId());
        this.memberAccountLogService.insertSelective(memberAccountLog);       
	}
	
	/**
	 * 押金
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-23 下午3:10:23   
	* @return String
	 */
	private void createMemberAccountDepositLog(ChargeOrder co, MemberItem memberItem,MemberItemShare memberItemShare,ChargeOrderLog chargeOrderLog) throws Exception {
		MemberAccountLog memberAccountLog = new MemberAccountLog();
	      //插入一条权益消费的记录td_member_account_log
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
	        memberAccountLog.setMemberItemId(memberItem.getId());
	        memberAccountLog.setAccountType(co.getAccountType());
	        memberAccountLog.setTransformType(1);
	        memberAccountLog.setDeposit(co.getDeposit());
	       // memberAccountLog.setBalance(co.getBalance());
	        memberAccountLog.setCreated(new Date());
	        memberAccountLog.setTotal(memberAccountLog.getDeposit());
	        memberAccountLog.setLasttimeModify(memberAccountLog.getCreated());
	        memberAccountLog.setMemberItemName(memberItem.getItemName());
	        memberAccountLog.setPreBalance(memberItem.getBalance());
	        memberAccountLog.setPreDeposit(memberItem.getDeposit());
	        memberAccountLog.setTransformType(co.getCreatedSource());//变动类型：1，客户续费；2，后台人工续费；3，后台人工扣费；4，用车扣费；
	        //private Integer accountType;//续费类型：1，余额；2，押金；3，余额和押金
	        if(memberAccountLog.getAccountType().intValue() == 1){
	        	memberAccountLog.setAccountType(2);
	            memberAccountLog.setRemark("余额-客户续费");
	            if(co.getGiftAmount().compareTo(BigDecimal.ZERO) > 0){
	            	 memberAccountLog.setRemark("余额-客户续费"+chargeOrderLog.getRemark());
	            }
	    		memberAccountLog.setBalanceReason("余额-客户续费");
	        }else if(memberAccountLog.getAccountType().intValue() == 2){
	            memberAccountLog.setRemark("押金-客户续费");
	     		memberAccountLog.setDepositReason("押金-客户续费");
	        }else if(memberAccountLog.getAccountType().intValue() == 3){
	        	 memberAccountLog.setAccountType(2);
	        	 memberAccountLog.setRemark("押金-客户续费");
		     	 memberAccountLog.setDepositReason("押金-客户续费");
	        }

	        memberAccountLog.setMemberItemOwnerId(memberItem.getMemberId());
	        memberAccountLog.setModifier(co.getCreatedMemberName());
	        memberAccountLog.setModifierId(co.getCreatedMemberId());
	        this.memberAccountLogService.insertSelective(memberAccountLog);    
	}

	
    @Override
    public ChargeOrderLog updateChargeOrderLogByNotPaid(ChargeOrderLog chargeOrderLog) {
        Integer count = this.tdChargeOrderLogMapper.updateChargeOrderLogByNotPaid(chargeOrderLog);
        if(count == 0){
            return null;
        }
        return this.findChargeOrderLogByOrderNo(chargeOrderLog.getOrderNo());
    }

	@Override
	public ChargeOrderLog updateChargeOrderLogFail(ChargeOrderLog chargeOrderLog) {
        Assert.notNull(chargeOrderLog,"权益充值消费日志不能为空");
        Assert.notNull(chargeOrderLog.getOrderNo(),"订单号不允许为空");

        chargeOrderLog.setOrderStatus(4);
        chargeOrderLog.setLasttimeModify(new Date());
        chargeOrderLog.setCompletionTime(new Date());
        ChargeOrderLog chargeOrderLogNew = this.updateChargeOrderLogByNotPaid(chargeOrderLog);
        if(chargeOrderLogNew == null){
            logger.info(chargeOrderLog.getOrderNo() + "已经更新，不允许再次更新");
            return null;
        }
        //更新订单状态为失败
        this.updateChargeOrderFail(chargeOrderLogNew);
        return chargeOrderLog;
	}

	/**
	 * 更新订单表状态为失败
	 * @param chargeOrderLog
	 */
	public void updateChargeOrderFail(ChargeOrderLog chargeOrderLog) {
		//更新chargeOrder 表
        ChargeOrder chargeOrder = new ChargeOrder();
        chargeOrder.setStatus(4);
        chargeOrder.setChargeOrderNo(chargeOrderLog.getOrderNo());
        chargeOrder.setCompletedTime(new Date());
        chargeOrder.setLasttimeModify(chargeOrderLog.getCompletionTime());
        chargeOrderService.updateByPrimaryKeySelective(chargeOrder);
	}

}
