package com.hy.gcar.service.message;

import com.hy.gcar.entity.Approve;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.Order;

public interface WechatMessageService {
	
		
		/**
		 * 审核未通过
		 */
		public void toExamineNotThrough(Member member,Order order);	
		
		/**
		 * 审核通过
		 */
		public void auditPass(Member member, Order order);
		
		
		/**
		 * 申请取消通知
		 */
		public void applicationForCancellation(Member member,Order order);
		

		
		/**
		 * 部分退款
		 */
		public void partialPayment(Member member,Order order);


		/**
		 * 结清全款
		 */
		public void settleTheFullAmount(Member member,Order order,MemberItem memberItem);
		
		
		
		/**
		 * 共用人添加通知（新增模板：模板库中“平安行消息通知”模板） AtzUMrKrND41TDG6nl-Gg2dguU6WhfGjrDj4hcwBrOQ	
		 * 共用人添加通知
		 * 通知内容：您已成为XXX（会员）的共用人，可共同享用XXX（套餐名称）权益。
		 * 通知时间：显示通知时间（2016/09/09 17:00）
		 */
		public void commonPeopleAdd(Member member,Item item,MemberItemShare share);
		
		/**
		 * 7. 删除共用人通知
	     * 共用人删除通知
	     * 通知内容：XXX（会员）已将您从共用人列表中移除，已停止享用XXX（套餐名称）权益。
	     * 通知时间：显示通知时间（2016/09/09 17:00）
	     *     
	     */
		public void sharedDeleted(Member member,MemberItemShare itemShare);

		/**
		 * 提交入会申请（微信端创建）
		 * @param order
		 */
		public void sendMessageBySubmit(Member member,Order order);
		
		/**
		 * 销售转派
		 * @param order
		 */
		public void sendMessageBySaleChange(Member member,Order order);
		
		
		/**
		 * 发送消息意向微信消息
		 * @param order
		 */
		public void sendMessageBySellesOrder(Order order);

		/**
		 * 管家任务转派微信消息推送
		 * @param old_butlerTask
		 * @param new_butlerTask
		 */
		public void sendMessageByTaskOperateChange(ButlerTask new_butlerTask);
		
		/**
		 * 任务处理消息推送消息推送
		 * @param new_butlerTask
		 */
		public void sendMessageByTaskStatusChange(ButlerTask new_butlerTask,String value);

		/**
		 * 占用平台运营通知 (分配了被平台运营的车辆)
		 * @param butlerTask
		 * @param value
		 */
		public void sendMessageByPlatformOperation(CarOperatePlan carOperatePlan,String value);
		
		/**
		 * 创建任务后给客服发送通知
		 * @param new_butlerTask
		 * @param value
		 */
		public void sendMessageByTaskCreateToCustomer(ButlerTask butlerTask,String value);

		/**
		 * 创建任务之后消息给管家消息推送
		 * @param butlerTask
		 */
		public void sendMessageByTaskCreate(ButlerTask butlerTask,String value);
		
		/**
		 * 创建任务之后消息给管家消息推送
		 * @param butlerTask
		 */
		public void sendMessageByTaskCancel(ButlerTask butlerTask,String value,Integer modifierType);
		/**
		 * 取消用车如果有扣除违约费的情况，需要给用户发送消息推送
		 * @param butlerTask
		 */
		public void sendMessageByBreakPromise(ButlerTask butlerTask,String value,String price);
		
		/**
		 * 完善信息之后给信审人员发送消息推送
		 * @param butlerTask
		 */
		public void sendMessageByApproveCreate(Approve approve);
		
		/**
		 * 车辆失联或者车辆故障或者延迟还车给管家发送消息童推送
		 * @param 
		 */
		public void sendMessageByLoseOrTrouble(Long carOperateId , Integer type,  Member member_notice, ButlerTask butlerTask);
		
		/**
		 * 接收任务通知
		 * @param butlerTask
		 * @param value
		 */
		public void receiveTask(ButlerTask butlerTask,String value) throws Exception;
		
		/**
		 * 清空车辆对影响的任务发送消息推送
		 * @param butlerTask
		 */
		public void sendMessageByEmptyCarOperate(Long carOperateId , Integer type, ButlerTask butlerTask);
		
		
		/**
		 * 清空车辆对影响的任务发送消息推送
		 * @param butlerTask
		 */
		public void sendMessageByEmptyTaskCarOperate(ButlerTask butlerTask);
		
}
