package cn.cuco.service.payment.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.PaymentMapper;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderRenewal;
import cn.cuco.entity.Payment;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.page.PageResult;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.order.OrderRenewalService;
import cn.cuco.service.payment.PaymentService;

/**
 * @ClassName: PaymentServiceImpl
 * @Description: 支付记录相关接口的实现
 * @author zc.du
 * @date 2017年2月23日 上午10:58:02
 */
@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentMapper<Payment> paymentMapper;
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private OrderRenewalService orderRenewalService;

	@Override
	public Payment callBackForPayment(Payment payment) {
		ParamVerifyUtils.paramNotNull(payment, "支付回调时，支付记录不能为空");
		BigDecimal cur = payment.getPayment();
		ParamVerifyUtils.paramNotNull(cur, "支付回调时，支付记录金额不能为空");
		ParamVerifyUtils.paramNotNull(payment.getPaymentType(), "支付回调时，支付类型不能为空");
		ParamVerifyUtils.paramNotNull(payment.getOrderNo(), "支付回调时，支付订单号不能为空");
		ParamVerifyUtils.paramNotNull(payment.getPayWaterNo(), "支付回调时，支付流水号号不能为空");
		ParamVerifyUtils.paramNotNull(payment.getPaymentJson(), "支付回调时，支付json不能为空");
		ParamVerifyUtils.paramNotNull(payment.getPaymentStatus(), "支付回调时，支付状态不能为空");
		// 查找对应的支付订单
		if (payment.getOrderNo().indexOf("CZ") == 0) {
			// 续费订单
			OrderRenewal order = new OrderRenewal();
			order.setOrderNum(payment.getOrderNo());
			order = this.orderRenewalService.getOrderRenewal(order);
			if (null == order) {
				ExceptionUtil.throwWarn("储值订单不存在");
			}
			if(payment.getPaymentStatus().equals(3)){
				//支付成功
				OrderRenewal orderRenewal = new OrderRenewal();
				orderRenewal.setId(order.getId());
				orderRenewal.setPaymentNum(payment.getPayWaterNo());
				orderRenewal.setPayment(cur);
				BigDecimal paymented = order.getPayment();
				if(paymented.add(cur).subtract(order.getTotal()).doubleValue() < 0){
					//部分付款
					this.orderRenewalService.updateOrderRenewalStatusByPartPay(orderRenewal);
				}else{
					//完成付款
					this.orderRenewalService.updateOrderRenewalStatusByCompletePay(orderRenewal);
				}
			}
			//支付失败
			Date date = new Date();
			payment.setModifier(order.getCreatedMemberName());
			payment.setModifierId(order.getCreatedMemberId());
			payment.setOrderId(order.getId());
			payment.setOrderType(1);
			payment.setCompletionTime(date);
			payment.setCreated(date);
			payment.setMemberId(order.getCreatedMemberId());
			payment.setMemberName(order.getCreatedMemberName());
			payment.setMemberMobile(order.getCreatedMemberMobile());
			this.paymentMapper.insertSelective(payment);
		}
		return payment;
	}

	@Override
	public PageResult<Payment> getPaymentListByPage(Payment payment) {
		ParamVerifyUtils.paramNotNull(payment);
		Integer page = payment.getPage();
		Integer pageSize = payment.getPageSize();
		Integer totalSize = this.paymentMapper.selectCountByCondition(payment);
		PageHelper.startPage(page, pageSize);
		List<Payment> list = this.paymentMapper.selectByCondition(payment);
		PageResult<Payment> pageResult = new PageResult<Payment>(page, pageSize, totalSize, list);
		return pageResult;
	}

	@Override
	public PageResult<OperateLog> getPaymentLogListByPage(Payment payment) {
		ParamVerifyUtils.paramNotNull(payment);
		ParamVerifyUtils.paramNotNull(payment.getId(), "支付记录ID不能为空");
		OperateLog log = new OperateLog();
		log.setPage(payment.getPage());
		log.setPageSize(payment.getPageSize());
		log.setOperationId(payment.getId());
		log.setType(OperateLogEnum.PAYMENT.getValue());
		return this.operateLogService.getOperateLogByPage(log);
	}

}
