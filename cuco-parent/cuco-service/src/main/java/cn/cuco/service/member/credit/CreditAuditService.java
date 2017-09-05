package cn.cuco.service.member.credit;

import cn.cuco.entity.CreditAudit;
import cn.cuco.entity.OperateLog;
import cn.cuco.page.PageResult;

/** 
* @ClassName: CreditAuditService 
* @Description: 风控审核相关接口
* @author zc.du
* @date 2017年2月23日 上午9:16:43  
*/
public interface CreditAuditService {
	
	/** 
	* @Title: createCreditAudit 
	* @Description: 提交风控审核
	* @author zc.du
	* @param creditAudit
	* @return CreditAudit
	*/
	public CreditAudit createCreditAudit(CreditAudit creditAudit);
	
	/** 
	* @Title: getCreditAudit 
	* @Description: 根据审核编号查询审核详情
	* @author zc.du
	* @param auditNum
	* @return CreditAudit
	*/
	public CreditAudit getCreditAuditByAuditNum(String auditNum);

	/** 
	* @Title: getMemberCreditAuditListByPage 
	* @Description: 分页获取指定用户审核列表
	* @author zc.du
	* @param creditAudit
	* @return PageResult<CreditAudit>
	*/
	public PageResult<CreditAudit> getMemberCreditAuditListByPage(CreditAudit creditAudit); 
	
	/** 
	* @Title: getOrderRenewalLogListByPage 
	* @Description: 分页查询审核日志列表
	* @author zc.du
	* @param creditAudit
	* @return PageResult<OperateLog>
	*/
	public PageResult<OperateLog> getCreditAuditLogListByPage(CreditAudit creditAudit);
    
    /** 
    * @Title: synchronizeCreditAuditStatus 
    * @Description: 同步用户审核状态(征信系统回调用)
    * @author zc.du
    * @param creditAudit
    * @return void
    */
    public void synchronizeCreditAuditStatus(CreditAudit creditAudit);
}
