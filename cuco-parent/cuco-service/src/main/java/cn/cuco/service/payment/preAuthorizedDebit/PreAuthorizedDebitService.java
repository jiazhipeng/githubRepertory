package cn.cuco.service.payment.preAuthorizedDebit;

import java.math.BigDecimal;
import java.util.List;

import cn.cuco.entity.OperateLog;
import cn.cuco.entity.PreAuthorizedDebit;
import cn.cuco.entity.TransferList;
import cn.cuco.page.PageResult;

/**
 * @ClassName: PreAuthorizedDebitService
 * @Description: 预授权接口
 * @author huanghua
 * @date 2017年3月6日 下午4:20:59
 */
public interface PreAuthorizedDebitService {
	/**
	 * @Title: createUseCarPreAuthorizedDebit
	 * @Description: 创建用车预授权信息
	 * @author huanghua
	 * @param preAuthorizedDebit
	 * @return PreAuthorizedDebit
	 */
	public PreAuthorizedDebit createUseCarPreAuthorizedDebit(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: createViolationPreAuthorizedDebit
	 * @Description: 创建违章押金预授权信息
	 * @author huanghua
	 * @param preAuthorizedDebit
	 * @return PreAuthorizedDebit
	 */
	public PreAuthorizedDebit createViolationPreAuthorizedDebit(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: getPreAuthorizedDebitById
	 * @Description: 查询预授权详情
	 * @author huanghua
	 * @param id
	 * @return PreAuthorizedDebit
	 */
	public PreAuthorizedDebit getPreAuthorizedDebitById(Long id);

	/**
	 * @Title: updatePreAuthorizedDebitUnfreeze
	 * @Description: 预授权信息解冻
	 * @author huanghua
	 * @param preAuthorizedDebit
	 * @return PreAuthorizedDebit
	 */
	public void updatePreAuthorizedDebitUnfreeze(PreAuthorizedDebit preAuthorizedDebit);
	
	/**
	* @Title: getPreAuthorizedDebitByUsed 
	* @Description: 查询用车冻结押金记录
	* @author huanghua
	* @param preAuthorizedDebit
	* @return
	* @return PreAuthorizedDebit
	 */
	public PreAuthorizedDebit getPreAuthorizedDebitByUsed(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: getPreAuthorizedDebitListByPage
	 * @Description: 分页查询
	 * @author huanghua
	 * @param preAuthorizedDebit
	 * @return PageResult<PreAuthorizedDebit>
	 */
	public PageResult<PreAuthorizedDebit> getPreAuthorizedDebitListByPage(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: getOperateLogListByPage
	 * @Description: 日志列表
	 * @author huanghua
	 * @param operateLog
	 * @return PageResult<OperateLog>
	 */
	public PageResult<OperateLog> getOperateLogListByPage(OperateLog operateLog);

	/**
	 * @Title: createOperateLogForPreAuthorizedDebit
	 * @Description: 添加预授权备注信息
	 * @author huanghua
	 * @param operateLog
	 * @return
	 * @return OperateLog
	 */
	public void createOperateLogForPreAuthorizedDebit(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: getTotalFrozenMoneyByMemberId
	 * @Description: 根据用户ID获取冻结金额总和
	 * @author zc.du
	 * @param memberId
	 * @return BigDecimal
	 */
	public BigDecimal getTotalFrozenMoneyByMemberId(Long memberId);

	/**
	 * @Title: createPreAuthorizedDebitTransfer
	 * @Description: 创建预授权附件
	 * @author huanghua
	 * @param transferList
	 * @return
	 * @return TransferList
	 */
	public TransferList createPreAuthorizedDebitTransfer(TransferList transferList);
	
	/**   
	 * @Title: getPreAuthorizedDebits   
	 * @Description: 查询15日还没有解冻的类型为违章押金的预授权记录
	 * @param: @return      
	 * @return: List<PreAuthorizedDebit>       
	 */
	public List<PreAuthorizedDebit> getPreAuthorizedDebitsOver15Days();
}
