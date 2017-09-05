package cn.cuco.service.member.account;

import java.math.BigDecimal;

import cn.cuco.entity.MemberAccount;
import cn.cuco.entity.MemberAccountLog;
import cn.cuco.entity.OrderCarUsed;
import cn.cuco.entity.OrderCarport;
import cn.cuco.entity.OrderRenewal;
import cn.cuco.page.PageResult;

/**
 * @ClassName: MemberAccountService
 * @Description: 用户账户相关接口
 * @author zc.du
 * @date 2017年2月23日 上午10:33:33
 */
public interface MemberAccountService {
	/**
	 * @Title: createMemberAccount
	 * @Description: 创建用户账户
	 * @author zc.du
	 * @param memberAccount
	 * @return MemberAccount
	 */
	public MemberAccount createMemberAccount(MemberAccount memberAccount);
	
	/** 
	* @Title: updateMemberAccountBalance 
	* @Description: 修改账户余额
	* @author zc.du
	* @param memberAccountLog
	* @return MemberAccount
	*/
	public MemberAccount updateMemberAccountBalance(MemberAccountLog memberAccountLog);
	
	/** 
	* @Title: updateMemberAccountByUnlockCarPort 
	* @Description: 解锁订单变更账户
	* @author zc.du
	* @param orderCarport
	* @return MemberAccount
	*/
	public MemberAccount updateMemberAccountByUnlockCarPort(OrderCarport orderCarport);

	/**
	 * @Title: updateBalanceForBackstage
	 * @Description: 后台变更账户余额（后台人工操作）
	 * @author zc.du
	 * @param memberAccountLog
	 * @return MemberAccount
	 */
	public MemberAccount updateBalanceForBackstage(MemberAccountLog memberAccountLog);

	/**
	 * @Title: updateMemberAccountForSettlement
	 * @Description: 结算引起账户变动
	 * @author zc.du
	 * @param memberAccount
	 * @return MemberAccount
	 */
	public MemberAccount updateMemberAccountForSettlement(OrderCarUsed orderCarUsed);

	/**
	 * @Title: updateMemberAccountForMemberRenewal
	 * @Description: 用户续费引起账户变动
	 * @author zc.du
	 * @param memberAccount
	 * @return MemberAccount
	 */
	public MemberAccount updateMemberAccountForMemberRenewal(OrderRenewal orderRenewal);

	/**
	 * @Title: getMemberAccountByMemberId
	 * @Description: 根据用户ID查询用户账户
	 * @author zc.du
	 * @param memberAccount
	 * @return MemberAccount
	 */
	public MemberAccount getMemberAccountByMemberId(Long memberId);
	
	/**
	 * @Title: getMemberAccountByMemberIdForBackstage
	 * @Description: 根据用户ID查询用户账户(后台专用)
	 * @author zc.du
	 * @param memberAccount
	 * @return MemberAccount
	 */
	public MemberAccount getMemberAccountByMemberIdForBackstage(Long memberId);

	/**
	 * @Title: getMemberAccountLogListByPage
	 * @Description: 分页查询用户账户明细列表
	 * @author zc.du
	 * @param memberAccountLog
	 * @return PageResult<MemberAccountLog>
	 */
	public PageResult<MemberAccountLog> getMemberAccountLogListByPage(MemberAccountLog memberAccountLog);

	/**
	 * @Title: getALlCostByMemberId
	 * @Description: 根据用户ID获取用户消费总额
	 * @author zc.du
	 * @param memberId
	 * @return BigDecimal
	 */
	public BigDecimal getALlCostByMemberId(Long memberId);
	
	/** 
	* @Title: updateMemberAccountForFrozenDeposit 
	* @Description: 冻结用户余额作为押金
	* @author zc.du
	* @return memberAccount（用户ID不能为空，deposit：押金金额,modifer:操作人，modifierId：操作人ID）
	* @return MemberAccount
	*/
	public MemberAccount updateMemberAccountForFrozenDeposit(MemberAccount memberAccount);
	
	/** 
	* @Title: updateMemberAccountForSystemBack 
	* @Description: 系统退回
	* @author zc.du
	* @param memberAccount（memberId:用户Id;modifierId:操作人Id;modifer:操作人；remark：备注；balance:退回金额）
	* @return MemberAccount
	*/
	public MemberAccount updateMemberAccountForSystemBack(MemberAccount memberAccount);

}
