package cn.cuco.service.order;


import java.math.BigDecimal;
import java.util.Date;

import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderRenewal;
import cn.cuco.page.PageResult;

/** 
* @ClassName: OrderRenewalService 
* @Description: 续费订单相关接口
* @author zc.du
* @date 2017年2月23日 上午10:52:48  
*/
public interface OrderRenewalService {
    /** 
    * @Title: createOrderRenewal 
    * @Description: 创建续费订单
    * @author zc.du
    * @param orderRenewal
    * @return OrderRenewal
    */
    public OrderRenewal createOrderRenewal(OrderRenewal orderRenewal);
    
    /** 
    * @Title: getOrderRenewal 
    * @Description: 查询续费订单
    * @author zc.du
    * @param orderRenewal
    * @return OrderRenewal
    */
    public OrderRenewal getOrderRenewal(OrderRenewal orderRenewal); 
    
    /** 
    * @Title: getOrderRenewalListByPage 
    * @Description: 分页查询续费订单列表
    * @author zc.du
    * @param orderRenewal
    * @return PageResult<OrderRenewal>
    */
    public PageResult<OrderRenewal> getOrderRenewalListByPage(OrderRenewal orderRenewal);
    
    /** 
     * @Title: getOrderRenewalListByPageForBackstage 
     * @Description: 后台分页查询续费订单列表
     * @author zc.du
     * @param orderRenewal
     * @return PageResult<OrderRenewal>
     */
     public PageResult<OrderRenewal> getOrderRenewalListByPageForBackstage(OrderRenewal orderRenewal);
    
    /** 
    * @Title: updateOrderRenewalStatusByCancelForApp 
    * @Description: app端修改续费订单状态为"已取消"
    * @author zc.du
    * @param orderRenewal
    * @return OrderRenewal
    */
    public OrderRenewal updateOrderRenewalStatusByCancelForApp(OrderRenewal orderRenewal);
    
    /** 
    * @Title: updateOrderRenewalStatusByCancelForTask 
    * @Description: 系统自动取消订单
    * @author zc.du
    */
    public void updateOrderRenewalStatusByCancelForTask();
    
    /** 
    * @Title: updateOrderRenewalStatusByPaying 
    * @Description: 修改续费订单状态为"支付中"
    * @author zc.du
    * @param orderRenewal
    * @return OrderRenewal
    */
    public OrderRenewal updateOrderRenewalStatusByPaying(OrderRenewal orderRenewal);
    
    /** 
     * @Title: updateOrderRenewalStatusByPartPay 
     * @Description: 修改续费订单状态为"部分付款"
     * @author zc.du
     * @param orderRenewal
     * @return OrderRenewal
     */
     public OrderRenewal updateOrderRenewalStatusByPartPay(OrderRenewal orderRenewal);
    
    /** 
    * @Title: updateOrderRenewalStatusByCompletePay 
    * @Description: 修改续费订单状态为"已付款"
    * @author zc.du
    * @param orderRenewal
    * @return OrderRenewal
    */
    public OrderRenewal updateOrderRenewalStatusByCompletePay(OrderRenewal orderRenewal);

    /** 
    * @Title: getOrderRenewalLogListByPage 
    * @Description: 分页查询续费订单日志列表
    * @author zc.du
    * @param orderRenewal
    * @return PageResult<OperateLog>
    */
    public PageResult<OperateLog> getOrderRenewalLogListByPage(OrderRenewal orderRenewal);
    
    /** 
    * @Title: getOrderRenewalCount 
    * @Description: 根据用户ID查询用户充值次数
    * @author zc.du
    * @param memberId 用户ID
    * @return Integer
    */
    public Integer getOrderRenewalCount(Long memberId);

    /** 
    * @Title: getUnfinishOrderRenewalCountByMemberId 
    * @Description: 根据用户ID查询未完成的储值订单数量
    * @author zc.du
    * @param memberId
    * @return Integer
    */
    public Integer getUnfinishOrderRenewalCountByMemberId(Long memberId);
    
    /** 
    * @Title: getCompletedPayOrderRenewalMoneyByDate 
    * @Description: 查询指定时间段内支付完成的储值订单金额
    * @author zc.du
    * @param start
    * @param end
    * @return BigDecimal
    */
    public BigDecimal getCompletedPayOrderRenewalMoneyByDate(Date start,Date end);
}
