package com.hy.gcar.service.chargeOrder.impl;

import com.hy.common.utils.DateUtils;
import com.hy.gcar.dao.ChargeOrderMapper;
import com.hy.gcar.entity.*;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.chargeOrderLog.ChargeOrderLogService;
import com.hy.gcar.service.chargeOrdertypeLog.ChargeOrdertypeLogService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.utils.RandomNumberUtil;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service(value = "tdChargeOrderService")
public class ChargeOrderServiceImpl implements ChargeOrderService {

    @Autowired
    MemberItemShareService memberItemShareService;

    @Autowired
    MemberService memberService;
    
    @Autowired
    MemberItemService memberItemService;

    @Autowired
    ItemService itemService;

    @Autowired
    ChargeOrderLogService chargeOrderLogService;

    @Autowired
    ChargeOrdertypeLogService chargeOrdertypeLogService;

    @Autowired
    private ChargeOrderMapper<ChargeOrder>tdChargeOrderMapper;
    
    @Override
    public Integer insertSelective(ChargeOrder tdChargeOrder) throws Exception {
           return this.tdChargeOrderMapper.insertSelective(tdChargeOrder);
        }

    @Override
    public Integer insertBatch(List<ChargeOrder> tdChargeOrder) throws Exception {
           return this.tdChargeOrderMapper.insertBatch(tdChargeOrder) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdChargeOrderMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdChargeOrderMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ChargeOrder tdChargeOrder) {
           return this.tdChargeOrderMapper.updateByPrimaryKeySelective(tdChargeOrder);
    }

    @Override
    public Integer updateByChargeOrder(ChargeOrder chargeOrder) {
        return this.tdChargeOrderMapper.updateByChargeOrder(chargeOrder);
    }

    @Override
    public ChargeOrder findById(Object id) {
           return (ChargeOrder) this.tdChargeOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public ChargeOrder findChargeOrderByOrderNo(String orderNo) {
        Assert.notNull(orderNo,"第三方订单号不能为空");

        ChargeOrder chargeOrder = new ChargeOrder();
        chargeOrder.setChargeNo(orderNo);
        List<ChargeOrder>  chargeOrders = this.selectByCondition(chargeOrder);
        if(CollectionUtils.isNotEmpty(chargeOrders)){
            return chargeOrders.get(0);
        }
        return null;
    }

    @Override
    public ChargeOrder findChargeOrderByChargeOrderNo(String chargeOrderNo) {
        Assert.notNull(chargeOrderNo,"订单号不能为空");
        ChargeOrder chargeOrder = new ChargeOrder();
        chargeOrder.setChargeOrderNo(chargeOrderNo);
        List<ChargeOrder>  chargeOrders = this.selectByCondition(chargeOrder);
        if(CollectionUtils.isNotEmpty(chargeOrders)){
            return chargeOrders.get(0);
        }
        return null;
    }

    @Override
    public List<ChargeOrder> selectByCondition(ChargeOrder tdChargeOrder) {
           return (List<ChargeOrder>) this.tdChargeOrderMapper.selectByCondition(tdChargeOrder);
    }

    @Override
    public Integer selectCountByCondition(ChargeOrder tdChargeOrder) {
           return  this.tdChargeOrderMapper.selectCountByCondition(tdChargeOrder);
    }

    @Override
    public ChargeOrder createChargeOrder(ChargeOrder chargeOrder) throws Exception {
        Assert.notNull(chargeOrder,"支付订单对象不能为空");
        Assert.notNull(chargeOrder.getCreatedMemberId(),"会员id不能为空");
        chargeOrder.setStatus(1);//
        chargeOrder.setCreated(new Date());
        chargeOrder.setLasttimeModify(new Date());
        chargeOrder.setCreatedSource(1);
        
        Member m = memberService.findMemberByID(Long.valueOf(chargeOrder.getCreatedMemberId()));
        chargeOrder.setCreatedMemberName(m.getName());
        chargeOrder.setCreatedMemberMobile(m.getMobile());
        chargeOrder.setModifier(m.getName());
        chargeOrder.setModifierId(m.getId()+"");
        //判断是否未公用人
        MemberItemShare memberItemShare = memberItemShareService.selectByMemberId(Long.valueOf(chargeOrder.getCreatedMemberId()));
        chargeOrder.setMemberShareType(0);
        if(memberItemShare != null){
            //共用人状态：0，不是；1，普通共用人；2，主共用人
            if(memberItemShare.getIsMain() == 0){
                chargeOrder.setMemberShareType(1);
            }else if(memberItemShare.getIsMain() == 1){
                chargeOrder.setMemberShareType(2);
            }
        }
        //查询itemid 和itemName
        MemberItem memberItem = new MemberItem();
        memberItem.setId(memberItemShare.getMemberItemId());
        memberItem = memberItemService.findById(memberItem);
        
        chargeOrder.setMemberItemId(memberItem.getItemId());
        chargeOrder.setMemberItemName(memberItem.getItemName());
        chargeOrder.setMemberItemOwnerId(memberItem.getMemberId());
        //创建 chargeOrder
        this.insertSelective(chargeOrder);
        //创建 chargeOrderLog
        ChargeOrderLog chargeOrderLog = this.createChargeOrderLog(chargeOrder);
        //创建td_charge_ordertype_log
        this.createChargeOrderTypeLog(chargeOrder, chargeOrderLog);
        return chargeOrder;
    }

	public void createChargeOrderTypeLog(ChargeOrder chargeOrder, ChargeOrderLog chargeOrderLog) throws Exception {
		ChargeOrdertypeLog chargeOrdertypeLog = new ChargeOrdertypeLog();
        chargeOrdertypeLog.setOrderId(chargeOrderLog.getOrderId());
        chargeOrdertypeLog.setOrderNo(chargeOrderLog.getOrderNo());
        chargeOrdertypeLog.setIsOpenpage(0);
        chargeOrdertypeLog.setOrderPrice(chargeOrder.getTotal());
        chargeOrdertypeLog.setUpdateTime(new Date());
        chargeOrdertypeLog.setPayType(chargeOrder.getChargeSource());
        chargeOrdertypeLogService.createChargeOrdertypeLog(chargeOrdertypeLog);
	}

	public ChargeOrderLog createChargeOrderLog(ChargeOrder chargeOrder) throws Exception {
		//创建td_charge_order_log
        ChargeOrderLog chargeOrderLog = new ChargeOrderLog();
        chargeOrderLog.setOrderId(chargeOrder.getId());
        chargeOrderLog.setOrderNo(chargeOrder.getChargeOrderNo());
        chargeOrderLog.setDeposit(chargeOrder.getDeposit());
        chargeOrderLog.setBalance(chargeOrder.getBalance());
        chargeOrderLog.setOrderAmount(chargeOrder.getTotal());
        chargeOrderLog.setDiscountMoney(chargeOrder.getTotal());
        chargeOrderLog.setMemberId(Long.valueOf(chargeOrder.getCreatedMemberId()));
        chargeOrderLog.setMemberName(chargeOrder.getCreatedMemberName());
        chargeOrderLog.setMemberMobile(chargeOrder.getCreatedMemberMobile());
        chargeOrderLog.setModifier(chargeOrderLog.getMemberName());
        chargeOrderLog.setModifierId(chargeOrderLog.getMemberId()+"");
        chargeOrderLog.setAccountType(chargeOrder.getAccountType());
        chargeOrderLog.setMemberRole(1);//会员角色：1，会员；2，销售；3，管家
        chargeOrderLogService.createChargeOrderLog(chargeOrderLog);
		return chargeOrderLog;
	}


    @Override
    public ChargeOrder updateChargeOrderCancel(ChargeOrder chargeOrder) throws Exception {
        Assert.notNull(chargeOrder,"支付订单对象不能为空");
        chargeOrder.setStatus(2);
        chargeOrder.setLasttimeModify(new Date());
        this.updateByPrimaryKeySelective(chargeOrder);
        return this.findById(chargeOrder.getId());
    }

    @Override
    public String createOrderNo() {
    	StringBuffer buffer = new StringBuffer("jcgs");
    	String middle = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
    	String end = RandomNumberUtil.getRandNumber(3);
    	buffer.append(middle).append(end);
        return buffer.toString();
    }

	public static void main(String[] args) {
		String start = "jcgs";
    	String middle = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
    	String end = RandomNumberUtil.getRandNumber(3);
    	System.out.println(start+middle+"---" + end);

	}
    @Override
    public ChargeOrder updateChargeOrderComplete(ChargeOrder chargeOrder) throws Exception {
        Assert.notNull(chargeOrder,"支付订单对象不能为空");
        chargeOrder.setStatus(3);
        chargeOrder.setLasttimeModify(new Date());
        this.updateByPrimaryKeySelective(chargeOrder);
        return this.findById(chargeOrder.getId());
    }

    @Override
    public ChargeOrder updateChargeOrderByPayType(ChargeOrder chargeOrder) throws Exception {
        Assert.notNull(chargeOrder,"对象不允许为空");
        Assert.notNull(chargeOrder.getId(),"订单id不允许为空");
        Assert.notNull(chargeOrder.getChargeOrderNo(),"订单号不允许为空");
        chargeOrder.setLasttimeModify(new Date());
        //td_charge_order
        this.updateByPrimaryKeySelective(chargeOrder);
        chargeOrder = this.findById(chargeOrder);
        //td_charge_order_log
        ChargeOrderLog chargeOrderLog = new ChargeOrderLog();
        chargeOrderLog.setOrderNo(chargeOrder.getChargeOrderNo());
        chargeOrderLog.setPaymentType(chargeOrder.getChargeSource());
        chargeOrderLog.setLasttimeModify(new Date());
        chargeOrderLogService.updateByPrimaryKeySelective(chargeOrderLog);
        //td_charge_orderType_log
        ChargeOrdertypeLog chargeOrdertypeLog = new ChargeOrdertypeLog();
        chargeOrdertypeLog.setOrderId(chargeOrder.getId());
        chargeOrdertypeLog.setOrderNo(chargeOrder.getChargeOrderNo());
        chargeOrdertypeLog.setOrderPrice(chargeOrder.getTotal());
        chargeOrdertypeLog.setPayType(chargeOrder.getChargeSource());
        chargeOrdertypeLog.setIsOpenpage(0);
        chargeOrdertypeLog.setUpdateTime(new Date());
        chargeOrdertypeLogService.createChargeOrdertypeLog(chargeOrdertypeLog);
        return chargeOrder;
    }

}
