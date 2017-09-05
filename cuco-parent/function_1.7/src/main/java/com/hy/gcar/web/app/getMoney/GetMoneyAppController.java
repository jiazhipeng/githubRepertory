package com.hy.gcar.web.app.getMoney;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.Bank;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.entity.GetmoneyApply;
import com.hy.gcar.entity.GetmoneyApplyLog;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberBank;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.bank.BankService;
import com.hy.gcar.service.getmoney.GetmoneyApplyLogService;
import com.hy.gcar.service.getmoney.GetmoneyApplyService;
import com.hy.gcar.service.item.ItemCartypeService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.memberBank.MemberBankService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;

@Controller
public class GetMoneyAppController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private BankService bankService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberItemService memberItemService;
	@Autowired
	private ItemCartypeService ItemCartypeService;
	@Autowired
	private GetmoneyApplyService getmoneyApplyService;
	@Autowired
	private MemberItemShareService memberItemShareService;
	@Autowired
	private PowerUsedService powerUsedService;
	@Autowired
	private MemberBankService memberBankService;
	@Autowired
	private GetmoneyApplyLogService getmoneyApplyLogService;
	@Autowired
	BasicNoticeService basicNoticeService;
	 
	/**
	 * 获取银行列表
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/getBank")
	@ResponseBody
	public Object getBankAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		
		List<Map<String, Object>> returnList = new ArrayList<>();
		try {
			Bank bank = new Bank();
			List<Bank> bankList = this.bankService.selectByCondition(bank);
			
			for (Bank ban : bankList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("bankName", ban.getBankName());
				returnMap.put("bankUrl", ban.getBankUrl());
				returnMap.put("bankId", ban.getId()+"");
				returnList.add(returnMap);
			}
			
		} catch (Exception e) {
			this.logger.error("查询银行信息失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		return new OpenResponse("true", "操作成功", returnList, "100000");
	}
	
	@RequestMapping(value="/gcarapp/ios/getMoney/getBank")
	@ResponseBody
	public Object getBankIOS(@RequestParam(value = "message", required = true) String message){
		
		return this.getBankAndroid(message);
	}
	
	/**
	 * 提现校验接口
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/check")
	@ResponseBody
	public Object getMoneyByCheckAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		//获取业务参数
		String memberId = params.getString("memberId");
		String memberItemId = params.getString("memberItemId");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "参数异常：会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "参数异常：权益ID不能为空！", null, "100001");
		}
		Map<String, Object> returnMap = new HashMap<>();
		try {
			Member member = new Member();
			member = this.memberService.findMemberByID(Long.parseLong(memberId));
			MemberItem memberItem = new MemberItem();
			memberItem = this.memberItemService.findById(Long.parseLong(memberItemId));
			//校验信息返回
			if(null == member){
				return new OpenResponse("false", "参数异常：查询用户出错！", null, "100001");
			}
			if(null == memberItem){
				return new OpenResponse("false", "参数异常：查询权益出错！", null, "100001");
			}
			//首先判断用户是否冻结
			if(0==member.getStatuts()){
				//该用户被冻结
				returnMap.put("code", "1");
				returnMap.put("msg", "您已被冻结，请联系客服400-9029-858");
				return new OpenResponse("true", "您已被冻结，请联系客服400-9029-858", returnMap, "100000");
			}
			//其次判断权益是否发起了押金提现，查询是否有进行中的审核
			GetmoneyApply getmoneyApply = new GetmoneyApply();
			getmoneyApply.setMemberItemId(Long.parseLong(memberItemId));
			List<GetmoneyApply> getmoneyList = getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply);
			if(getmoneyList.size()>0){
				//如果已经发起提现，进入提现流程页
				returnMap.put("code", "3");
				returnMap.put("msg", "已经发起提现，进入提现流程页");
				return new OpenResponse("true", "已经发起提现，进入提现流程页", returnMap, "100000");
			}
			//判断是否是主账号
			MemberItemShare memberItemShare = this.memberItemShareService.selectByMemberId(Long.parseLong(memberItemId));
			if(null != memberItemShare){
				if(0==memberItemShare.getIsMain()){
					//判断当前用户是否为主账号,0表示不是主账号
					//如果已经发起提现，进入提现流程页
					returnMap.put("code", "4");
					returnMap.put("msg", "只有主账号可发起提现");
					return new OpenResponse("true", "只有主账号可发起提现", returnMap, "100000");
				}
			}
			//判断当前押金小于等于0
			if(!(1==memberItem.getDeposit().compareTo(new BigDecimal(0)))){
				//表示余额小于等于0了
				returnMap.put("code", "6");
				returnMap.put("msg", "您没有可以提现的押金");
				return new OpenResponse("true", "您没有可以提现的押金", returnMap, "100000");
			}
			//判断权益剩余余额是否大于当前套餐下，车型的最低日使用费
			ItemCartype itemCart = new ItemCartype();
			itemCart.setItemId(memberItem.getItemId());
			BigDecimal minimumPrice = this.ItemCartypeService.selectMinimumPrice(itemCart);
			if(1==memberItem.getBalance().compareTo(minimumPrice)){
				//权益剩余余额是否大于当前套餐下，车型的最低日使用费
				returnMap.put("code", "2");
				returnMap.put("msg", "只有余额不足支付1天的使用费时，才可发起提现。");
				return new OpenResponse("true", "只有余额不足支付1天的使用费时，才可发起提现。", returnMap, "100000");
			}
			//首先查询是否有正在进行中的用车记录
			PowerUsed powerUs = new PowerUsed();
			//powerUs.setMemberId(Long.parseLong(memberId));
			powerUs.setMemberItemId(Long.parseLong(memberItemId));
			List<PowerUsed> powerUsedNotCompeletedList = powerUsedService.selectpowerUsedByNoCompleted(powerUs);
			if(CollectionUtils.isNotEmpty(powerUsedNotCompeletedList)){
				returnMap.put("code", "5");
				returnMap.put("msg", "押金提现需在用车完成后30天发起");
				return new OpenResponse("true", "押金提现需在用车完成后30天发起", returnMap, "100000");
			}
			//判断当前用户距最后一次用车完成时间是否大于30天
			PowerUsed powerUsed = new PowerUsed();
			//powerUsed.setMemberId(Long.parseLong(memberId));
			powerUsed.setMemberItemId(Long.parseLong(memberItemId));
			powerUsed.setUsedStatus(6);
			List<PowerUsed> powerUsedList = powerUsedService.selectpowerUsedByCompleted(powerUsed);
			if(CollectionUtils.isNotEmpty(powerUsedList)){
				powerUsed = powerUsedList.get(0);
				double days = DateUtils.getDistanceOfTwoDate(powerUsed.getLasttimeModify(), new Date());
				if(days<=30){
					returnMap.put("code", "5");
					returnMap.put("msg", "押金提现需在用车完成后30天发起");
					return new OpenResponse("true", "押金提现需在用车完成后30天发起", returnMap, "100000");
				}
			}
		} catch (Exception e) {
			this.logger.error("提现校验失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		returnMap.put("code", "0");
		returnMap.put("msg", "校验通过");
		return new OpenResponse("true", "操作成功", returnMap, "100000");
	}
	
	@RequestMapping(value="/gcarapp/ios/getMoney/check")
	@ResponseBody
	public Object getMoneyByCheckIOS(@RequestParam(value = "message", required = true) String message){
		
		return this.getMoneyByCheckAndroid(message);
	}
	
	/**
	 * 跳转提现页
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/toGetMoney")
	@ResponseBody
	public Object toGetMoneyAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		//获取业务参数
		String memberId = params.getString("memberId");
		String memberItemId = params.getString("memberItemId");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "参数异常：会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "参数异常：权益ID不能为空！", null, "100001");
		}
		try {
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			MemberItem memberItem = new MemberItem();
			memberItem = this.memberItemService.findById(Long.parseLong(memberItemId));
			if(null == memberItem){
				return new OpenResponse("false", "参数异常：权益不存在！", null, "100001");
			}
			BigDecimal applyPayment = memberItem.getDeposit();
			dataMap.put("applyPayment", applyPayment+"");
			//根据用户ID查询用户账号信息
			MemberBank memberBank = new MemberBank();
			memberBank.setMemberId(Long.parseLong(memberId));
			List<MemberBank> memberBankList = this.memberBankService.selectByCondition(memberBank);
			if(CollectionUtils.isNotEmpty(memberBankList)){
				dataMap.put("bankName",memberBankList.get(0).getBankName());
				Bank bank = this.bankService.findById(memberBankList.get(0).getBankId());
				dataMap.put("bankUrl",bank.getBankUrl());
				String bankCard = memberBankList.get(0).getBankCard();
				bankCard = "尾号"+bankCard.substring(bankCard.length()-4, bankCard.length());
				dataMap.put("bankCard",bankCard);
				return new OpenResponse("true", "操作成功", dataMap, "100000");
			}else{
				return new OpenResponse("true", "操作成功", dataMap, "100000");
			}
		} catch (Exception e) {
			this.logger.error("跳转提现页失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		
	}
	
	@RequestMapping(value="/gcarapp/ios/getMoney/toGetMoney")
	@ResponseBody
	public Object toGetMoneyIOS(@RequestParam(value = "message", required = true) String message){
		
		return this.toGetMoneyAndroid(message);
	}
	
	/**
	 * 提现保存
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/getMoneySubmit")
	@ResponseBody
	public Object getMoneySubmitAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		//获取业务参数
		String memberId = params.getString("memberId");
		String applyPayment = params.getString("applyPayment");
		String memberItemId = params.getString("memberItemId");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "参数异常：会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(applyPayment)){
			return new OpenResponse("false", "参数异常：提现金额不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "参数异常：权益ID不能为空！", null, "100001");
		}
		Map<String, Object> returnMap = new HashMap<>();
		try {
			Member member = new Member();
			member = this.memberService.findMemberByID(Long.parseLong(memberId));
			if(null == member){
				return new OpenResponse("false", "参数异常：没有该用户信息！", null, "100001");
			}
			if(0==member.getStatuts()){
				//该用户被冻结
				//该用户被冻结
				returnMap.put("code", "1");
				returnMap.put("msg", "您已被冻结，请联系客服400-9029-858");
				return new OpenResponse("true", "您已被冻结，请联系客服400-9029-858", returnMap, "100000");
			}
			MemberBank memberBank = new MemberBank();
			memberBank.setMemberId(Long.parseLong(memberId));
			List<MemberBank> memberBankList = this.memberBankService.selectByCondition(memberBank);
			if(CollectionUtils.isEmpty(memberBankList)){
				returnMap.put("code", "2");
				returnMap.put("msg", "请添加银行卡");
				return new OpenResponse("true", "请添加银行卡", returnMap, "100000");
			}
			//查询是否有进行中的审核
			GetmoneyApply getmoneyApply_old = new GetmoneyApply();
			getmoneyApply_old.setMemberItemId(Long.parseLong(memberItemId));
			List<GetmoneyApply> getmoneyList_old = getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply_old);
			if(getmoneyList_old.size()>0){
				//如果已经发起提现，进入提现流程页
				returnMap.put("code", "3");
				returnMap.put("msg", "押金提现需在用车完成后30天发起");
				return new OpenResponse("true", "押金提现需在用车完成后30天发起", returnMap, "100000");
			}
			
			memberBank = memberBankList.get(0);
			GetmoneyApply getmoneyApply = new GetmoneyApply();
			getmoneyApply.setAccountName(memberBank.getAccountName());
			getmoneyApply.setApplyPayment(new BigDecimal(applyPayment));
			getmoneyApply.setBankCard(memberBank.getBankCard());
			getmoneyApply.setBankName(memberBank.getBankName());
			getmoneyApply.setBranchName(memberBank.getBranchName());
			getmoneyApply.setCreated(new Date());
			getmoneyApply.setMemberId(Long.parseLong(memberId));
			getmoneyApply.setMemberName(member.getName());
			getmoneyApply.setMemberMobile(member.getMobile());
			getmoneyApply.setMemberItemId(Long.parseLong(memberItemId));
			getmoneyApply.setStatus(0);
			//查询初始负责人
			BasicNotice b = new BasicNotice();
			b.setNoticeType(7);
			//查询正常的
			b.setStatus(0);
			List<BasicNotice> basicNotices = basicNoticeService.selectByCondition(b);
			if(CollectionUtils.isNotEmpty(basicNotices)){
				//查询默认的审核负责人
				this.logger.info("查询后台设置的默认审核人开始---");
				Member member_notice = new Member();
				b = basicNotices.get(0);
				long memberNoticeId = b.getNoticeUserId();
				member_notice = this.memberService.findMemberByID(memberNoticeId);
				getmoneyApply.setModifer(member_notice.getSysuserName());
				getmoneyApply.setModiferId(member_notice.getSysuserId());
			}
			
			this.getmoneyApplyService.insertSelective(getmoneyApply);
			//插入提现申请日志
			GetmoneyApplyLog getmoneyApplyLog = new GetmoneyApplyLog();
			getmoneyApplyLog.setStatus(0);
			getmoneyApplyLog.setGetmoneyApplyId(getmoneyApply.getId());
			getmoneyApplyLog.setOperaterId(member.getId()+"");
			getmoneyApplyLog.setOperaterName(member.getName());
			getmoneyApplyLog.setRemark("提交提现审核");
			getmoneyApplyLog.setApproveTime(new Date());
			this.getmoneyApplyLogService.insertSelective(getmoneyApplyLog);
			//将权益状态变成冻结
			MemberItem memberItem_old = new MemberItem();
			memberItem_old.setId(Long.parseLong(memberItemId));
			memberItem_old.setStatus(1);
			this.memberItemService.updateByPrimaryKeySelective(memberItem_old);
			//前端返回
			returnMap.put("code", "0");
			returnMap.put("msg", "提交成功");
			return new OpenResponse("true", "提交成功", returnMap, "100000");
		} catch (Exception e) {
			this.logger.error("提现保存失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		
	}
	
	@RequestMapping(value="/gcarapp/ios/getMoney/getMoneySubmit")
	@ResponseBody
	public Object getMoneySubmitIOS(@RequestParam(value = "message", required = true) String message){
		return this.getMoneySubmitAndroid(message);
	}
	
	/**
	 * 跳转添加银行卡
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/toAddMemberBank")
	@ResponseBody
	public Object toAddMemberBankAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		//获取业务参数
		String memberId = params.getString("memberId");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "参数异常：会员ID不能为空！", null, "100001");
		}
		try {
			//根据用户ID查询用户账号信息
			MemberBank memberBank = new MemberBank();
			memberBank.setMemberId(Long.parseLong(memberId));
			List<MemberBank> memberBankList = this.memberBankService.selectByCondition(memberBank);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if(CollectionUtils.isNotEmpty(memberBankList)){
				//dataMap.put("hasAccount", "1");
				dataMap.put("bankId",memberBankList.get(0).getBankId()+"");
				dataMap.put("bankName",memberBankList.get(0).getBankName());
				Bank bank = this.bankService.findById(memberBankList.get(0).getBankId());
				dataMap.put("bankUrl",bank.getBankUrl());
				dataMap.put("branchName",memberBankList.get(0).getBankName());
				dataMap.put("accountName",memberBankList.get(0).getAccountName());
				dataMap.put("accountMobile",memberBankList.get(0).getAccountMobile());
				dataMap.put("bankCard",memberBankList.get(0).getBankCard());
				return new OpenResponse("true", "操作成功", dataMap, "100000");
			}else{
				Member member = new Member();
				member = this.memberService.findMemberByID(Long.parseLong(memberId));
				//dataMap.put("hasAccount", "0");
				dataMap.put("accountName", member.getName());
				dataMap.put("accountMobile", member.getMobile());
				return new OpenResponse("true", "操作成功", dataMap, "100000");
			}
			
		} catch (Exception e) {
			this.logger.error("跳转添加银行卡失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
	}
	
	@RequestMapping(value="/gcarapp/ios/getMoney/toAddMemberBank")
	@ResponseBody
	public Object toAddMemberBankIOS(@RequestParam(value = "message", required = true) String message){
		return this.toAddMemberBankAndroid(message);
	}
	
	/**
	 * 添加银行卡保存
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/addMemberBank")
	@ResponseBody
	public Object addMemberBankAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		//获取业务参数
		String memberId = params.getString("memberId");
		String bankId = params.getString("bankId");
		String bankName = params.getString("bankName");
		String branchName = params.getString("branchName");
		String accountName = params.getString("accountName");
		String accountMobile = params.getString("accountMobile");
		String bankCard = params.getString("bankCard");
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "参数异常：会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(bankId)){
			return new OpenResponse("false", "参数异常：所选银行ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(bankName)){
			return new OpenResponse("false", "参数异常：银行名称不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(branchName)){
			return new OpenResponse("false", "参数异常：支行名不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(bankCard)){
			return new OpenResponse("false", "参数异常：账户信息不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(accountName)){
			return new OpenResponse("false", "参数异常：账户姓名不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(accountMobile)){
			return new OpenResponse("false", "参数异常：账户手机号不能为空！", null, "100001");
		}
		
		try {
			Member member = new Member();
			member = this.memberService.findMemberByID(Long.parseLong(memberId));
			if(null == member){
				return new OpenResponse("false", "参数异常：查询用户出错！", null, "100001");
			}
			//根据用户ID查询用户账号信息
			MemberBank memberBank = new MemberBank();
			memberBank.setMemberId(Long.parseLong(memberId));
			List<MemberBank> memberBankList = this.memberBankService.selectByCondition(memberBank);
			if(CollectionUtils.isEmpty(memberBankList)){
				//没有就是新建
				MemberBank memberbank = new MemberBank();
				memberbank.setMemberId(Long.parseLong(memberId));
				memberbank.setMemberName(member.getName());
				memberbank.setAccountMobile(member.getMobile());
				memberbank.setAccountName(accountName);
				memberbank.setAccountMobile(accountMobile);
				memberbank.setBankCard(bankCard);
				memberbank.setBankName(bankName);
				memberbank.setBranchName(branchName);
				memberbank.setCreated(new Date());
				memberbank.setBankId(Long.parseLong(bankId));
				this.memberBankService.insertSelective(memberbank);
			}else{
				//有就是修改
				MemberBank memberbank = new MemberBank();
				memberbank.setMemberId(Long.parseLong(memberId));
				memberbank.setMemberName(member.getName());
				memberbank.setAccountMobile(member.getMobile());
				memberbank.setAccountName(accountName);
				memberbank.setAccountMobile(accountMobile);
				memberbank.setBankCard(bankCard);
				memberbank.setBankName(bankName);
				memberbank.setBranchName(branchName);
				memberbank.setCreated(new Date());
				memberbank.setBankId(Long.parseLong(bankId));
				memberbank.setId(memberBankList.get(0).getId());
				this.memberBankService.updateByPrimaryKeySelective(memberbank);
			}
			return new OpenResponse("true", "操作成功", null, "100000");
		} catch (Exception e) {
			this.logger.error("添加银行卡保存失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		
	}
	
	@RequestMapping(value="/gcarapp/ios/getMoney/addMemberBank")
	@ResponseBody
	public Object addMemberBankIOS(@RequestParam(value = "message", required = true) String message){
		return this.addMemberBankAndroid(message);
	}
	
	
	/**
	 * 提现流程页
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/gcarapp/android/getMoney/process")
	@ResponseBody
	public Object processAndroid(@RequestParam(value = "message", required = true) String message){
		message = AppUtil.decode(message);
		// 解析json数据
		JSONObject params = JSONObject.parseObject(message);
		String mobile = params.getString("mobile");
		// sign校验
		String sign = params.getString("sign");
		//获取业务参数
		String memberId = params.getString("memberId");
		String memberItemId = params.getString("memberItemId");
		
		boolean validate = AppUtil.validateBySign(mobile + Global.PWD_FOR_APP, sign);
		if (validate == false) {
			this.logger.info("解密错误---");
			return new OpenResponse("false", "sign不匹配", null, "100002");
		}
		if(StringUtils.isBlank(memberId)){
			return new OpenResponse("false", "参数异常：会员ID不能为空！", null, "100001");
		}
		if(StringUtils.isBlank(memberItemId)){
			return new OpenResponse("false", "参数异常：权益ID不能为空！", null, "100001");
		}
		try {
			//查询是否有进行中的审核
			GetmoneyApply getmoneyApply = new GetmoneyApply();
			getmoneyApply.setMemberItemId(Long.parseLong(memberItemId));
			List<GetmoneyApply> getmoneyList = getmoneyApplyService.selectMoneyApplyOfUnfinished(getmoneyApply);
			if(getmoneyList.size()<0){
				//没有进行中的提现申请
				return new OpenResponse("false", "没有进行中的提现申请", null, "100001");
			}
			getmoneyApply = getmoneyList.get(0);
			GetmoneyApplyLog getmoneyApplyLog = new GetmoneyApplyLog();
			getmoneyApplyLog.setGetmoneyApplyId(getmoneyApply.getId());
			List<GetmoneyApplyLog> getmoneyApplyLogList = this.getmoneyApplyLogService.selectByCondition(getmoneyApplyLog);
			List<Map<String, Object>> list = getConstansMap();
			if(CollectionUtils.isNotEmpty(getmoneyApplyLogList)){
				GetmoneyApplyLog log = getmoneyApplyLogList.get(0);
				if(0 == log.getStatus()){
					Map<String, Object> map = list.get(0);
					map.put("butlerInfo", "金额："+getmoneyList.get(0).getApplyPayment()+"元");
					map.put("time", DateUtils.formatDate(log.getApproveTime(),"yyyy-MM-dd HH:mm"));
					map.put("status", "1");
				}
				if(1 == log.getStatus()){
					Map<String, Object> map = list.get(0);
					map.put("butlerInfo", "金额："+getmoneyList.get(0).getApplyPayment()+"元");
					map.put("time", DateUtils.formatDate(log.getApproveTime(),"yyyy-MM-dd HH:mm"));
					map.put("status", "1");
					
					Map<String, Object> map1 = list.get(1);
					map1.put("time", DateUtils.formatDate(log.getApproveTime(),"yyyy-MM-dd HH:mm"));
					map1.put("status", "1");
				}
				if(2== log.getStatus() || 3== log.getStatus() ){
					Map<String, Object> map = list.get(0);
					map.put("butlerInfo", "金额："+getmoneyList.get(0).getApplyPayment()+"元");
					map.put("time", DateUtils.formatDate(log.getApproveTime(),"yyyy-MM-dd HH:mm"));
					map.put("status", "1");
					
					Map<String, Object> map1 = list.get(1);
					map1.put("time", DateUtils.formatDate(log.getApproveTime(),"yyyy-MM-dd HH:mm"));
					map1.put("status", "1");
					
					Map<String, Object> map2 = list.get(2);
					map2.put("time", DateUtils.formatDate(log.getApproveTime(),"yyyy-MM-dd HH:mm"));
					map2.put("status", "1");
				}
			}
			
			return new OpenResponse("true", "操作成功", list, "100000");
		} catch (Exception e) {
			this.logger.error("提现流程页失败--"+e);
			return new OpenResponse("false", "操作异常", null, "100014");
		}
		
	}
	
	public List<Map<String, Object>> getConstansMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map0 = new HashMap<>();
		map0.put("msg", "提现申请");
		map0.put("status", "0");
		list.add(map0);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("msg", "提现审核");
		map1.put("status", "0");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("msg", "提现完成");
		map2.put("status", "0");
		list.add(map2);
		return list;
	}
	
	
	@RequestMapping(value="/gcarapp/ios/getMoney/process")
	@ResponseBody
	public Object processIOS(@RequestParam(value = "message", required = true) String message){
		return this.processAndroid(message);
	}
}
