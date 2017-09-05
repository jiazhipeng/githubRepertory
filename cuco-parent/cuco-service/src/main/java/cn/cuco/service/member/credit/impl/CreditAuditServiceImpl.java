package cn.cuco.service.member.credit.impl;

import org.springframework.stereotype.Service;

import cn.cuco.entity.CreditAudit;
import cn.cuco.entity.OperateLog;
import cn.cuco.page.PageResult;
import cn.cuco.service.member.credit.CreditAuditService;

/** 
* @ClassName: CreditAuditServiceImpl 
* @Description: 风控审核相关接口实现
* @author zc.du
* @date 2017年2月23日 上午10:31:56  
*/
@Service
public class CreditAuditServiceImpl implements CreditAuditService {

	@Override
	public CreditAudit createCreditAudit(CreditAudit creditAudit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditAudit getCreditAuditByAuditNum(String auditNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<CreditAudit> getMemberCreditAuditListByPage(
			CreditAudit creditAudit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<OperateLog> getCreditAuditLogListByPage(
			CreditAudit creditAudit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronizeCreditAuditStatus(CreditAudit creditAudit) {
		// TODO Auto-generated method stub
		
	}

	
}
