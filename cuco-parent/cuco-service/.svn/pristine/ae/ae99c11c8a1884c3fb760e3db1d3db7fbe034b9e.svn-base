package cn.cuco.service.payment;


import cn.cuco.entity.OperateLog;
import cn.cuco.entity.Payment;
import cn.cuco.page.PageResult;

/** 
* @ClassName: PaymentService 
* @Description: 支付记录相关接口
* @author zc.du
* @date 2017年2月23日 上午10:56:41  
*/
public interface PaymentService {
	
	/** 
	* @Title: callBackForPayment 
	* @Description: 支付回调方法
	* @author zc.du
	* @param payment
	* @return Payment
	*/
	public Payment callBackForPayment(Payment payment);
    
    /** 
    * @Title: getPaymentListByPage 
    * @Description: 分页查询支付记录列表
    * @author zc.du
    * @param payment
    * @return PageResult<Payment>
    */
    public PageResult<Payment> getPaymentListByPage(Payment payment);
    
    /** 
     * @Title: getPaymentListByPage
     * @Description: 分页查询支付日志列表
     * @author zc.du
     * @param payment
     * @return PageResult<Payment>
     */
     public PageResult<OperateLog> getPaymentLogListByPage(Payment payment);
}
