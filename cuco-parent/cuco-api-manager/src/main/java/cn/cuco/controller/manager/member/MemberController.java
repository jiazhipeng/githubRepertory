package cn.cuco.controller.manager.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.cuco.annotation.API;
import cn.cuco.common.httpservice.HttpServiceContext;
import cn.cuco.common.utils.PrePersistUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.controller.entity.MemberAccountLogVO;
import cn.cuco.controller.entity.MemberAccountVO;
import cn.cuco.controller.entity.MemberVO;
import cn.cuco.entity.Member;
import cn.cuco.entity.MemberAccount;
import cn.cuco.entity.MemberAccountLog;
import cn.cuco.entity.MemberCarport;
import cn.cuco.entity.PreAuthorizedDebit;
import cn.cuco.enums.MemberStatus;
import cn.cuco.page.PageResult;
import cn.cuco.service.member.account.MemberAccountService;
import cn.cuco.service.member.info.MemberService;
import cn.cuco.service.payment.preAuthorizedDebit.PreAuthorizedDebitService;


@RestController
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private  PreAuthorizedDebitService preAuthorizedDebitService;
	
	@API(value = "用户列表")
	@RequestMapping(value = "getMemberListByPage", method = RequestMethod.POST)
	public Object getMemberListByPage(@RequestBody MemberVO memberVO){
		
		ParamVerifyUtils.paramNotNull(memberVO,"参数不可以为null");
		Member member = new Member();
		Integer page = member.getPage();
		Integer pageSize = member.getPageSize();
		BeanUtils.copyProperties(memberVO, member);
		if(memberVO.getCreatedBegin() != null){
			member.setRegisterStartDate(memberVO.getCreatedBegin());
			member.setRegisterEndDate(memberVO.getCreatedEnd());
		}
		if(memberVO.getLastUseCarBeginTime() != null){
			member.setUseCarStartDate(memberVO.getLastUseCarBeginTime());
			member.setUseCarEndDate(memberVO.getLastUseCarEndTime());
		}
		System.out.println(member.getPageSize());
		PageResult<Member> memberResult = memberService.getMemberListByPageForBackstage(member);
		Integer totalSize = memberResult.getTotalSize();
		List<MemberVO> memberVOList = null;
		if(CollectionUtils.isNotEmpty(memberResult.getItems())){
			memberVOList = new ArrayList<MemberVO>();
			for(Member getMember : memberResult.getItems()){
				MemberVO vo = new MemberVO();
				BeanUtils.copyProperties(getMember, vo);
				Map<String, Integer> carports = null;
				if(getMember.getCarportList() != null){
					carports = new HashMap<String, Integer>();
					for(MemberCarport memberCarport :getMember.getCarportList()){
						carports.put(memberCarport.getCarportName() + "-" + memberCarport.getFavoriteCarType().getBrand() + " " + memberCarport.getFavoriteCarType().getCarType() , memberCarport.getStatus());
					}
				}
				vo.setBalance(getMember.getBalane());
				vo.setLastUseCarTime(getMember.getLastUseCarDate());
				vo.setConsumptionAmount(getMember.getCostTotal());
				vo.setPayCount(getMember.getRenewalCount());
				vo.setCarports(carports);
				memberVOList.add(vo);
			}
		}
		return new PageResult<MemberVO>(page, pageSize, totalSize, memberVOList);
	} 
	
	@API(value = "用户详情")
	@RequestMapping(value = "getMemberById", method = RequestMethod.GET)
	public Object getMemberById(Long id){
		
		Member member =  memberService.getMemberById(id);
		MemberVO memberVO = null;
		if(member != null){
			memberVO = new MemberVO();
			BeanUtils.copyProperties(member, memberVO);
		}
		return memberVO;
	}
	
	@API(value = "账户信息")
	@RequestMapping(value = "getAccountByMemberId", method = RequestMethod.GET)
	public Object getAccountByMemberId(MemberAccountVO memberAccountVO){
		
		ParamVerifyUtils.paramNotNull(memberAccountVO,"参数不可以为null");
		ParamVerifyUtils.paramNotNull(memberAccountVO.getMemberId(),"参数memberId不可以为null");
		MemberAccount memberAccount =  memberAccountService.getMemberAccountByMemberIdForBackstage(memberAccountVO.getMemberId());
		memberAccountVO = null;
		if(memberAccount != null){
			memberAccountVO = new MemberAccountVO();
			BeanUtils.copyProperties(memberAccount, memberAccountVO);
		}
		
		return memberAccountVO;
	}
	
	@API(value = "风控审核的用户资料")
	@RequestMapping(value = "getRiskByMemberId", method = RequestMethod.GET)
	public Object getRiskByMemberId(Long id){
		
		ParamVerifyUtils.paramNotNull(id,"参数id必传");
		Member member = memberService.getMemberInfoById(id);
		MemberVO memberVO = null;
		if(member != null){
		    memberVO = new MemberVO();
			BeanUtils.copyProperties(member, memberVO);
			if(member.getMemberInfo() != null){
				memberVO.setCreditCard(member.getMemberInfo().getCreditCard());
				memberVO.setDrivingLicenseCopy(member.getMemberInfo().getDrivingLicenseCopy());
				memberVO.setDrivingLicenseOriginal(member.getMemberInfo().getDrivingLicenseOriginal());
				memberVO.setIdcardBack(member.getMemberInfo().getIdcardBack());
				memberVO.setIdcardFront(member.getMemberInfo().getIdcardFront());
			}
		}
		return memberVO;
	}
	
	@API(value = "变更余额")
	@RequestMapping(value = "updateAccount", method = RequestMethod.POST)
	public Object updateAccount(@RequestBody MemberAccountVO memberAccountVO){
		
		ParamVerifyUtils.paramNotNull(memberAccountVO,"参数不可以为null");
        PrePersistUtils.onCreate(memberAccountVO, HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
        MemberAccountLog memberAccountLog = new MemberAccountLog();
        BeanUtils.copyProperties(memberAccountVO, memberAccountLog);
        memberAccountLog.setTransformType(memberAccountVO.getType());
        memberAccountLog.setTotal(memberAccountVO.getBalance());
        memberAccountLog.setModifierId(memberAccountVO.getModifierId());
        memberAccountLog.setModifer(memberAccountVO.getModifer());
        return memberAccountService.updateBalanceForBackstage(memberAccountLog);
	}
	
	@API(value = "冻结解冻用户")
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public Object updateStatus(@RequestBody MemberVO memberVO){
		
		ParamVerifyUtils.paramNotNull(memberVO,"参数不可以为null");
		ParamVerifyUtils.paramNotNull(memberVO.getId(),"参数id不可以为null");
        PrePersistUtils.onModify(memberVO,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
		Member member = new Member();
		BeanUtils.copyProperties(memberVO, member);
		Member memberStatus = memberService.getMemberById(member.getId());
		Integer status = memberStatus.getStatuts();
		if(status == MemberStatus.NORMAL.getIndex()){
			return memberService.updateMemberByFreeze(member);
		}else{
			return memberService.updateMemberByUnfreeze(member);
		}
		
	}
	
	@API(value = "余额明细")
	@RequestMapping(value = "getAccountLogByMemberId", method = RequestMethod.POST)
	public Object getAccountLogByMemberId(@RequestBody MemberAccountLogVO memberAccountLogVO){
		
		ParamVerifyUtils.paramNotNull(memberAccountLogVO,"参数不可以为null");
		ParamVerifyUtils.paramNotNull(memberAccountLogVO.getMemberId(),"参数memberId不可以为null");
		MemberAccountLog memberAccountLog = new MemberAccountLog();
		BeanUtils.copyProperties(memberAccountLogVO, memberAccountLog);
		if(memberAccountLogVO.getCreatedBegin() != null){
			memberAccountLog.setStartDate(memberAccountLogVO.getCreatedBegin());
			memberAccountLog.setEndDate(memberAccountLogVO.getCreatedEnd());
		}
		
		return memberAccountService.getMemberAccountLogListByPage(memberAccountLog);
	}
	
	@API(value = "预授权列表")
	@RequestMapping(value = "getDepositLogByMemberId", method = RequestMethod.POST)
	public Object getDepositLogByMemberId(@RequestBody PreAuthorizedDebit preAuthorizedDebit){
		
		ParamVerifyUtils.paramNotNull(preAuthorizedDebit,"参数不可以为null");
		ParamVerifyUtils.paramNotNull(preAuthorizedDebit.getMemberId(),"参数memberId不可以为null");
		PageResult<PreAuthorizedDebit> result = preAuthorizedDebitService.getPreAuthorizedDebitListByPage(preAuthorizedDebit);
		return result;
	}
	
	
	
	
}
