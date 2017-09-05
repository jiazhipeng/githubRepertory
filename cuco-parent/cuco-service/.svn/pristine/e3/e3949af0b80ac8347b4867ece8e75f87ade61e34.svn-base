package cn.cuco.service.order.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.date.DateUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.OrderCarportMapper;
import cn.cuco.entity.Carport;
import cn.cuco.entity.Member;
import cn.cuco.entity.MemberCarport;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OrderCarport;
import cn.cuco.enums.MemberCarportStatus;
import cn.cuco.enums.MemberStatus;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.enums.OrderCarportStatus;
import cn.cuco.enums.RiskAuditStatus;
import cn.cuco.enums.Valid;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.carport.CarportService;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.member.account.MemberAccountService;
import cn.cuco.service.member.carUsed.MemberCarportService;
import cn.cuco.service.member.info.MemberService;
import cn.cuco.service.order.OrderCarportService;

/**
 * @ClassName: OrderCarportServiceImpl
 * @Description: 解锁订单相关接口实现
 * @author zc.du
 * @date 2017年2月23日 上午10:46:47
 */
@Service(value = "orderCarportService")
public class OrderCarportServiceImpl implements OrderCarportService {

	@Autowired
	private OrderCarportMapper<OrderCarport> orderCarportMapper;
	@Autowired
	private MemberCarportService memberCarportService;
	@Autowired
	private CarportService carportService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private MemberAccountService memberAccountService;

	@Override
	public OrderCarport createOrderCarport(OrderCarport orderCarport) {
		ParamVerifyUtils.paramNotNull(orderCarport);
		Long memberId = orderCarport.getMemberId();
		Long carportId = orderCarport.getCarportId();
		ParamVerifyUtils.paramNotNull(memberId, "解锁车库时，用户ID不能为空");
		ParamVerifyUtils.paramNotNull(carportId, "解锁车库时，车库ID不能为空");

		Member member = this.memberService.getMemberById(memberId);
		ParamVerifyUtils.paramNotNull(member, "解锁车库时，用户不存在");
		if (!RiskAuditStatus.MANUAL_PASS.getIndex().equals(member.getRiskAuditStatus())) {
			ExceptionUtil.throwWarn("用户未审核通过，无法进行解锁车库操作");
		}
		if (MemberStatus.FROZEN.getIndex().equals(member.getStatuts())) {
			ExceptionUtil.throwWarn("用户已冻结，无法进行解锁车库操作");
		}
		Carport carport = this.carportService.getCarportById(carportId);
		ParamVerifyUtils.paramNotNull(carport, "解锁车库时，所选车库不存在");
		if (Valid.DOWN.getValue().equals(carport.getValid())) {
			ExceptionUtil.throwWarn("车库已下架，无法进行解锁车库操作");
		}

		OrderCarport search = new OrderCarport();
		search.setCarportId(carportId);
		search.setMemberId(memberId);
		search.setStatus(OrderCarportStatus.COMPLETE_PAY.getIndex());
		List<OrderCarport> list = this.orderCarportMapper.selectByCondition(search);
		if (CollectionUtils.isNotEmpty(list)) {
			ExceptionUtil.throwWarn("您已解锁该车库，无法进行重复解锁");
		}
		search.setStatus(null);
		list = this.orderCarportMapper.getUnfinishOrderCarportList(search);
		if (CollectionUtils.isNotEmpty(list)) {
			ExceptionUtil.throwWarn("您已发起该车库的解锁操作，请进行支付");
		}
		// 创建解锁订单
		Date date = new Date();
		orderCarport.setCreated(date);
		orderCarport.setOrderNum(this.createOrderNum());
		orderCarport.setPayment(null);
		orderCarport.setPaymentTime(null);
		orderCarport.setStatus(OrderCarportStatus.WAIT_PAY.getIndex());
		orderCarport.setTotal(carport.getUnlockPrice());
		orderCarport.setIsTimeout(0);
		this.orderCarportMapper.insertSelective(orderCarport);
		// 创建操作日志
		createOrderCarportLog(orderCarport, "用户创建解锁订单");
		MemberCarport memberCarport = new MemberCarport(memberId, carportId, null, MemberCarportStatus.LOCK.getIndex(), null, null, null, date, null, null, member.getName(), memberId);
		this.memberCarportService.createMemberCarport(memberCarport);
		return this.getOrderCarportById(orderCarport.getId());
	}

	@Override
	public OrderCarport getOrderCarport(OrderCarport orderCarport) {
		List<OrderCarport> list = this.orderCarportMapper.selectByCondition(orderCarport);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageResult<OrderCarport> getOrderCarportListByPage(OrderCarport orderCarport) {
		ParamVerifyUtils.paramNotNull(orderCarport);
		Integer page = orderCarport.getPage();
		Integer pageSize = orderCarport.getPageSize();
		Integer totalSize = this.orderCarportMapper.getOrderCarportCount(orderCarport);
		PageHelper.startPage(page, pageSize);
		List<OrderCarport> list = this.orderCarportMapper.getOrderCarportListWithSort(orderCarport);
		PageResult<OrderCarport> pageResult = new PageResult<OrderCarport>(page, pageSize, totalSize, list);
		return pageResult;
	}

	@Override
	public OrderCarport updateOrderCarportStatusByCancelForApp(OrderCarport orderCarport) {
		// 判断解锁订单状态
		ParamVerifyUtils.paramNotNull(orderCarport);
		ParamVerifyUtils.paramNotNull(orderCarport.getId(), "取消解锁订单时，订单ID不能为空");
		OrderCarport old = this.orderCarportMapper.selectByPrimaryKey(orderCarport.getId());
		ParamVerifyUtils.paramNotNull(old, "解锁订单不存在，无法取消");
		if (!OrderCarportStatus.WAIT_PAY.getIndex().equals(old.getStatus())) {
			ExceptionUtil.throwWarn("当前订单状态为" + OrderCarportStatus.getName(old.getStatus()) + "无法进行取消操作");
		}
		OrderCarport target = new OrderCarport();
		target.setId(orderCarport.getId());
		target.setStatus(OrderCarportStatus.CANCEL.getIndex());
		this.orderCarportMapper.updateByPrimaryKeySelective(target);
		old.setStatus(OrderCarportStatus.CANCEL.getIndex());
		this.createOrderCarportLog(old, "用户取消订单");
		return old;
	}

	@Override
	public void updateOrderCarportStatusByCancelForTask() {
		OrderCarport target = new OrderCarport();
		target.setStatus(OrderCarportStatus.WAIT_PAY.getIndex());
		target.setIsTimeout(0);
		List<OrderCarport> list = this.orderCarportMapper.selectByCondition(target);
		if (CollectionUtils.isNotEmpty(list)) {
			Date date = new Date();
			long interval = 1000 * 60 * 60 * 24;
			for (OrderCarport cur : list) {
				if (date.getTime() - cur.getCreated().getTime() >= interval) {
					OrderCarport newOne = new OrderCarport();
					newOne.setId(cur.getId());
					newOne.setStatus(OrderCarportStatus.CANCEL.getIndex());
					newOne.setIsTimeout(1);
					this.orderCarportMapper.updateByPrimaryKeySelective(newOne);
					this.createOrderCarportLog(newOne, "超时未支付，系统自动取消订单");
				}
			}
		}
	}

	@Override
	public OrderCarport updateOrderCarportStatusByPaying(OrderCarport orderCarport) {
		// 判断解锁订单状态
		ParamVerifyUtils.paramNotNull(orderCarport);
		ParamVerifyUtils.paramNotNull(orderCarport.getId(), "修改解锁订单状态时，订单ID不能为空");
		OrderCarport old = this.orderCarportMapper.selectByPrimaryKey(orderCarport.getId());
		ParamVerifyUtils.paramNotNull(old, "解锁订单不存在，无法修改解锁订单状态");
		boolean isFrozen = this.isFrozen(old.getMemberId());
		if (isFrozen) {
			ExceptionUtil.throwWarn("当前用户已被冻结，无法进行该操作");
		}
		if (!OrderCarportStatus.WAIT_PAY.getIndex().equals(old.getStatus())) {
			ExceptionUtil.throwWarn("当前订单状态为" + OrderCarportStatus.getName(old.getStatus()) + "无法进行该操作");
		}
		OrderCarport target = new OrderCarport();
		target.setId(orderCarport.getId());
		target.setStatus(OrderCarportStatus.PAYING.getIndex());
		this.orderCarportMapper.updateByPrimaryKeySelective(target);
		old.setStatus(OrderCarportStatus.PAYING.getIndex());
		this.createOrderCarportLog(old, null);
		return old;
	}

	@Override
	public OrderCarport updateOrderCarportStatusByPartPay(OrderCarport orderCarport) {
		// 判断解锁订单状态
		ParamVerifyUtils.paramNotNull(orderCarport);
		ParamVerifyUtils.paramNotNull(orderCarport.getId(), "修改解锁订单状态时，订单ID不能为空");
		OrderCarport old = this.orderCarportMapper.selectByPrimaryKey(orderCarport.getId());
		ParamVerifyUtils.paramNotNull(old, "解锁订单不存在，无法修改解锁订单状态");
		boolean isFrozen = this.isFrozen(old.getMemberId());
		if (isFrozen) {
			ExceptionUtil.throwWarn("当前用户已被冻结，无法进行该操作");
		}
		BigDecimal cur = orderCarport.getPayment();
		if (null == cur || cur.intValue() <= 0) {
			ExceptionUtil.throwWarn("支付金额不合法");
		}
		BigDecimal totalPayment = old.getPayment().add(cur);
		if (totalPayment.subtract(old.getPayment()).intValue() >= 0) {
			return this.updateOrderCarportStatusByCompletePay(orderCarport);
		}
		if (!OrderCarportStatus.PART_PAY.getIndex().equals(old.getStatus()) && !OrderCarportStatus.PAYING.getIndex().equals(old.getStatus())) {
			ExceptionUtil.throwWarn("当前订单状态为" + OrderCarportStatus.getName(old.getStatus()) + "无法进行该操作");
		}
		OrderCarport target = new OrderCarport();
		target.setId(orderCarport.getId());
		target.setStatus(OrderCarportStatus.PART_PAY.getIndex());
		target.setPayment(totalPayment);
		this.orderCarportMapper.updateByPrimaryKeySelective(target);
		old.setStatus(OrderCarportStatus.PART_PAY.getIndex());
		this.createOrderCarportLog(old, null);
		// 修改会员车库状态为"解锁中"
		MemberCarport memberCarport = new MemberCarport();
		memberCarport.setMemberId(old.getMemberId());
		memberCarport.setCarportId(old.getCarportId());
		memberCarport = this.memberCarportService.getMemberCarportListByPage(memberCarport).getItems().get(0);
		this.memberCarportService.updateMemberCarportStatusByUnlockin(memberCarport.getId());
		return old;
	}

	@Override
	public OrderCarport updateOrderCarportStatusByCompletePay(OrderCarport orderCarport) {
		// 判断解锁订单状态
		ParamVerifyUtils.paramNotNull(orderCarport);
		ParamVerifyUtils.paramNotNull(orderCarport.getId(), "修改解锁订单状态时，订单ID不能为空");
		OrderCarport old = this.orderCarportMapper.selectByPrimaryKey(orderCarport.getId());
		ParamVerifyUtils.paramNotNull(old, "解锁订单不存在，无法修改解锁订单状态");
		boolean isFrozen = this.isFrozen(old.getMemberId());
		if (isFrozen) {
			ExceptionUtil.throwWarn("当前用户已被冻结，无法进行该操作");
		}
		BigDecimal cur = orderCarport.getPayment();
		if (null == cur || cur.intValue() <= 0) {
			ExceptionUtil.throwWarn("支付金额不合法");
		}
		BigDecimal totalPayment = old.getPayment().add(cur);
		if (totalPayment.subtract(old.getPayment()).intValue() < 0) {
			ExceptionUtil.throwWarn("支付金额不足");
		}
		if (OrderCarportStatus.CANCEL.getIndex().equals(old.getStatus()) || OrderCarportStatus.COMPLETE_PAY.getIndex().equals(old.getStatus())) {
			ExceptionUtil.throwWarn("当前订单状态为" + OrderCarportStatus.getName(old.getStatus()) + "无法进行该操作");
		}
		OrderCarport target = new OrderCarport();
		target.setId(orderCarport.getId());
		target.setStatus(OrderCarportStatus.COMPLETE_PAY.getIndex());
		target.setPayment(totalPayment);
		this.orderCarportMapper.updateByPrimaryKeySelective(target);
		old.setStatus(OrderCarportStatus.COMPLETE_PAY.getIndex());
		this.createOrderCarportLog(old, null);
		// 修改会员车库状态为"已解锁"
		MemberCarport memberCarport = new MemberCarport();
		memberCarport.setMemberId(old.getMemberId());
		memberCarport.setCarportId(old.getCarportId());
		memberCarport = this.memberCarportService.getMemberCarportListByPage(memberCarport).getItems().get(0);
		this.memberCarportService.updateMemberCarportStatusByUnlocked(memberCarport.getId());
		//同步账户
		target.setModifer(old.getModifer());
		target.setModifierId(old.getModifierId());
		this.memberAccountService.updateMemberAccountByUnlockCarPort(target);
		return old;
	}

	@Override
	public PageResult<OperateLog> getOrderCarportLogListByPage(OrderCarport orderCarport) {
		ParamVerifyUtils.paramNotNull(orderCarport);
		ParamVerifyUtils.paramNotNull(orderCarport.getId(),"分页查询解锁订单日志列表时，解锁订单ID不能为空");
		OperateLog operateLog = new OperateLog();
		operateLog.setPage(orderCarport.getPage());
		operateLog.setPageSize(orderCarport.getPageSize());
		operateLog.setType(OperateLogEnum.ORDER_CARPORT.getValue());
		operateLog.setOperationId(orderCarport.getId());
		return this.operateLogService.getOperateLogByPage(operateLog);
	}

	@Override
	public Boolean validateWhetherCanBuy(MemberCarport memberCarport) {
		// TODO Auto-generated method stub
		return true;
	}

	private OrderCarport getOrderCarportById(Long id) {
		return this.orderCarportMapper.selectByPrimaryKey(id);
	}

	private String createOrderNum() {
		return "JS" + DateUtils.getDate("yyyyMMdd") + RandomUtils.nextInt(99999, 999999);
	}

	private void createOrderCarportLog(OrderCarport orderCarport, String message) {
		OperateLog log = new OperateLog();
		log.setCreated(new Date());
		log.setStatus(orderCarport.getStatus());
		log.setOperationId(orderCarport.getId());
		log.setType(OperateLogEnum.ORDER_CARPORT.getValue());
		log.setRemark(message);
		this.operateLogService.createOperateLog(log);
	}

	private List<OrderCarport> getUnfinishOrderListByMemberId(Long memberId) {
		OrderCarport target = new OrderCarport();
		target.setMemberId(memberId);
		return this.orderCarportMapper.getUnfinishOrderCarportList(target);
	}

	private boolean isFrozen(Long memberId) {
		Member member = this.memberService.getMemberById(memberId);
		if (member.getStatuts().equals(MemberStatus.FROZEN.getIndex())) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtils.parseDate("2017-02-27 10:01:00").getTime() - new Date().getTime());
	}
}
