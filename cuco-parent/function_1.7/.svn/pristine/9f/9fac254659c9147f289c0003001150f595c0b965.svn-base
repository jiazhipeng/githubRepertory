package com.hy.gcar.service.MemberItemShare.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.MemberItemShareMapper;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Order;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.item.ItemService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.message.WechatMessageService;
import com.hy.gcar.service.order.OrderService;
import com.hy.umeng.push.entity.MessageUmeng;
import com.hy.umeng.push.service.MessageAppService;

@Service
public class MemberItemShareServiceImpl implements MemberItemShareService {

    @Autowired
    private MemberItemShareMapper<MemberItemShare> trMemberItemShareMapper;
    @Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;
	@Autowired
	WechatMessageService wechatMessageService;
	@Autowired
	ItemService itemService;
	@Autowired
	private MessageAppService messageAppService;
	@Autowired
	private MemberItemService memberItemService;
	
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    @Override
    public MemberItemShare insertSelective(MemberItemShare trMemberItemShare) throws Exception {
    	   this.trMemberItemShareMapper.insertSelective(trMemberItemShare);
           return trMemberItemShareMapper.selectByPrimaryKey(trMemberItemShare.getId());
        }

    @Override
    public Integer insertBatch(List<MemberItemShare> trMemberItemShare) throws Exception {
           return this.trMemberItemShareMapper.insertBatch(trMemberItemShare) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
    		try {
				MemberItemShare memberItemShare = trMemberItemShareMapper.selectByPrimaryKey(id);
				Member member = memberService.findMemberByID(memberItemShare.getMemberId());
				wechatMessageService.sharedDeleted(member,memberItemShare);
				
				//删除共用人消息推送
				this.sendMessageByDeleteShareApp(member, memberItemShare);
				
			} catch (Exception e) {
				logger.info("删除共有人异常，，共享人memberItemShare ID============="+id);
				e.printStackTrace();
			}
            return this.trMemberItemShareMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.trMemberItemShareMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public MemberItemShare updateByPrimaryKeySelective(MemberItemShare trMemberItemShare) {
    	   this.trMemberItemShareMapper.updateByPrimaryKeySelective(trMemberItemShare);
           return trMemberItemShareMapper.selectByPrimaryKey(trMemberItemShare.getId());
    }

    @Override
    public MemberItemShare findById(Object id) {
           return (MemberItemShare) this.trMemberItemShareMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberItemShare> selectByCondition(MemberItemShare trMemberItemShare) {
           return (List<MemberItemShare>) this.trMemberItemShareMapper.selectByCondition(trMemberItemShare);
    }

    @Override
    public Integer selectCountByCondition(MemberItemShare trMemberItemShare) {
           return  this.trMemberItemShareMapper.selectCountByCondition(trMemberItemShare);
    }

	@Override
	public List<MemberItemShare> selectByNoPersonalIsMain(MemberItemShare trMemberItemShare) {
		Assert.notNull(trMemberItemShare, "权益共享表对象不能为空");
		if(null==trMemberItemShare.getMemberId()){
			this.logger.error("查询权益共享表，会员ID为空");
			return null;
		}
		List<MemberItemShare> memberItemShareList= this.trMemberItemShareMapper.selectByNoPersonalIsMain(trMemberItemShare);
		return memberItemShareList;
	}

	@Override
	public Map<String, Object> createMemberItemShare(MemberItemShare trMemberItemShare) {
		Assert.notNull(trMemberItemShare, "权益共享表对象不能为空");
		if(null==trMemberItemShare.getMemberItemId()){
			this.logger.error("添加共用人，权益ID不能为空");
			return null;
		}
		if(null==trMemberItemShare.getMemberMobile()){
			this.logger.error("添加共用人，共用人手机号不能为空");
			return null;
		}
		if(null==trMemberItemShare.getMemberId()){
			this.logger.error("添加共用人，会员ID不能为空");
			return null;
		}
		if(null==trMemberItemShare.getIsShare()){
			this.logger.error("添加共用人，必须选择是否共享");
			return null;
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//校验当前权益下已经存在多少共用人-最多三个
		MemberItemShare memberItemShare = new MemberItemShare();
		memberItemShare.setMemberItemId(trMemberItemShare.getMemberItemId());
		List<MemberItemShare> memberItemShares = this.selectByCondition(memberItemShare);
		if(memberItemShares.size()>=3){
			returnMap.put("result", false);
			returnMap.put("msg","共用人最多为3人");
			return returnMap;
		}
		//根据手机号查人
		Member member = new Member();
		Member ParentMember = new Member();
		member.setMobile(trMemberItemShare.getMemberMobile());
		try {
			List<Member> memberlist = this.memberService.findMember(member);
			ParentMember = this.memberService.findMemberByID(trMemberItemShare.getMemberId());
			if(memberlist.size()<=0){
				//不存在，先创建用户，再关联权益
				member.setName("极车会员");
				//需求要求来源都是线下
				member.setFromMember(0);
				member = this.memberService.createPersonalMember(member);
			}
			else{
				//存在取用户
				member = memberlist.get(0);
				//如果被冻结该用户不能被添加共用人
				if(0==member.getStatuts()){
					//表示该用户被冻结，不能被添加
					returnMap.put("result", false);
					returnMap.put("msg","该用户不能被添加");
					return returnMap;
				}
				//判断是否是共用人，是否拥有自己的权益
				MemberItemShare memberItemS = new MemberItemShare();
				memberItemS.setMemberId(member.getId());
				List<MemberItemShare> memberItemShareList = this.selectByCondition(memberItemS);
				if(memberItemShareList.size()>0){
					returnMap.put("result", false);
					returnMap.put("msg","该用户不能被添加");
					return returnMap;
				}
				//查看是否有未完成的订单
				Order order =new Order();
				order.setMemberId(member.getId());
				List<Order> orderList = this.orderService.findOrderByNoComplete(order);
				if(orderList.size()>0){
					returnMap.put("result", false);
					returnMap.put("msg","该用户不能被添加");
					return returnMap;
				}
				/*if(1==member.getType()&&memberItemShares.size()==0){
					//如果是添加的第一个企业主共用人
					isShare = true;
					isMain = true;
				}*/
			}
			//查询权益
			MemberItem mi = this.memberItemService.findById(trMemberItemShare.getMemberItemId());
			
			Member company_member = this.memberService.findMemberByID(mi.getMemberId());
			//关联权益
			MemberItemShare share = new MemberItemShare();
			share.setCreated(new Date());
			share.setMemberId(member.getId());
			share.setParentMemberId(mi.getMemberId());
			share.setLasttimeModify(share.getCreated());
			share.setMemberItemId(trMemberItemShare.getMemberItemId());
			share.setModifierId(String.valueOf(trMemberItemShare.getMemberId()));
			share.setModifier(ParentMember.getName());
			share.setParentMemberType(company_member.getType());
			share.setIsShare(trMemberItemShare.getIsShare());
			share = this.insertSelective(share);
			returnMap.put("result", true);
			returnMap.put("share",share);
			
			//添加添加共用人的消息推送
			this.sendMessageByAddShare(member,share);
			//添加添加共用人的消息推送-app
			this.sendMessageByAddShareApp(member,share);
		} catch (Exception e) {
			returnMap.put("result", false);
			returnMap.put("msg","共用人添加异常");
			return returnMap;
		}
		
		return returnMap;
	}

	/**添加共用人微信通知
	 * @param member
	 * @param share
	 */
	public void sendMessageByAddShare(Member member,MemberItemShare share){
		this.logger.info("添加共用人消息推送开始---");
		if(null==share.getMemberItemId()){
			this.logger.error("权益共享没有权益ID--");
			return;
		}
		MemberItem memberPower= this.memberItemService.findById(share.getMemberItemId());
		Item item = this.itemService.findById(memberPower.getItemId());
		wechatMessageService.commonPeopleAdd(member, item, share);
	}
	
	/**添加共用人微信通知-app
	 * @param member
	 * @param share
	 */
	public void sendMessageByAddShareApp(Member member,MemberItemShare share){
		this.logger.info("添加共用人消息app推送开始---");
		if(null==share.getMemberItemId()){
			this.logger.error("权益共享没有权益ID--");
			return;
		}
		String parent_name ="";
		String itemName ="";
		Member parent_member;
		MemberItem memberPower;
		Item item;
		try {
			memberPower= this.memberItemService.findById(share.getMemberItemId());
			item = this.itemService.findById(memberPower.getItemId());
			parent_member = this.memberService.findMemberByID(share.getParentMemberId());
		} catch (Exception e1) {
			this.logger.error("查询主共用人异常---");
			e1.printStackTrace();
			return;
		}
		if(null!=parent_member){
			parent_name = parent_member.getName();
		}
		if(null!=item){
			itemName = item.getLevelName();
		}
		String value = "您已成为"+parent_name+"的共用人，可共同享用"+itemName+"权益";
		//开始推送
		MessageUmeng messageUmeng = new MessageUmeng();
		//0代表极车公社
		messageUmeng.setApplicationType(0);
		messageUmeng.setAlias(member.getId()+"");
		messageUmeng.setTitle("海易出行");
		messageUmeng.setTicker("系统");
		messageUmeng.setMessageContent(value);
		messageUmeng.setCreateUser(share.getModifier());
		messageUmeng.setBeginTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		messageUmeng.setReadStatus(0);//未读消息
		try {
			messageAppService.sendMessageByAndroidAndIosAsync(messageUmeng);
		} catch (Exception e) {
			logger.error("给用户app端消息推送失败----用户ID："+member.getId()+" 推送内容为:'"+value+"'");
		}
	}
	/**删除共用人微信通知-app
	 * @param member
	 * @param share
	 */
	public void sendMessageByDeleteShareApp(Member member,MemberItemShare share){
		this.logger.info("删除共用人消息app推送开始---");
		if(null==share.getMemberItemId()){
			this.logger.error("权益共享没有权益ID--");
			return;
		}
		String itemName ="";
		MemberItem memberPower = new MemberItem();
		Item item = new Item();
		Member parentMember=new Member();
		try {
			if(share.getIsMain()==1){
				parentMember = memberService.findMemberByID(share.getParentMemberId());
			}else{
				MemberItemShare trMemberItemShare = new MemberItemShare();
				trMemberItemShare.setParentMemberId(share.getParentMemberId());
				trMemberItemShare.setIsMain(1);
				trMemberItemShare.setMemberItemId(share.getMemberItemId());
				List<MemberItemShare> shares = this.selectByCondition(trMemberItemShare);
				if(CollectionUtils.isNotEmpty(shares)){
					MemberItemShare shar = shares.get(0);
					parentMember = memberService.findMemberByID(shar.getMemberId());
				}
			}
			memberPower= this.memberItemService.findById(share.getMemberItemId());
			item = this.itemService.findById(memberPower.getItemId());
		} catch (Exception e1) {
			this.logger.error("查询主共用人异常---");
			e1.printStackTrace();
			return;
		}
		if(null!=item){
			itemName = item.getLevelName();
		}
		String value = parentMember.getName()+"已将您从共用人列表中移除，已停止享用"+itemName+"权益";
		//开始推送
		MessageUmeng messageUmeng = new MessageUmeng();
		//0代表极车公社
		messageUmeng.setApplicationType(0);
		messageUmeng.setAlias(member.getId()+"");
		messageUmeng.setTitle("海易出行");
		messageUmeng.setTicker("系统");
		messageUmeng.setMessageContent(value);
		messageUmeng.setCreateUser(parentMember.getName());
		messageUmeng.setBeginTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		messageUmeng.setReadStatus(0);//未读消息
		try {
			messageAppService.sendMessageByAndroidAndIosAsync(messageUmeng);
		} catch (Exception e) {
			logger.error("给用户app端消息推送失败----用户ID："+member.getId()+" 推送内容为:'"+value+"'");
		}
	}

	@Override
	public MemberItemShare selectByMemberId(Long memberId) {
		
		return trMemberItemShareMapper.selectByMemberId(memberId);
	}
}
