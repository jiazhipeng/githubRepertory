package com.hy.gcar.web.app.pay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.gcar.entity.ChargeOrder;
import com.hy.gcar.entity.ChargeOrdertypeLog;
import com.hy.gcar.entity.ChargeReward;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemShare;
import com.hy.gcar.entity.OpenResponse;
import com.hy.gcar.entity.WeChatQueryOrder;
import com.hy.gcar.entity.WeiChatPreAppOrder;
import com.hy.gcar.service.MemberItemShare.MemberItemShareService;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.chargeOrdertypeLog.ChargeOrdertypeLogService;
import com.hy.gcar.service.chargeReward.ChargeRewardService;
import com.hy.gcar.service.item.MemberItemService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.utils.AppUtil;
import com.hy.gcar.utils.Global;
import com.hy.gcar.websoket.SystemWebSocketHandler;
import com.hy.weixin.utils.Signature;
import com.hy.weixin.utils.WechatUrl;

/**
 * Created by 海易出行 on 2016/9/22.
 */
@Controller
@RequestMapping("/gcarapp")
public class RenewController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberItemService memberItemService;

    @Autowired
    ChargeOrderService chargeOrderService;
    
    @Autowired
    ChargeOrdertypeLogService chargeOrdertypeLogService;
    
    @Autowired
    SystemWebSocketHandler systemWebSocketHandler;
    
    @Autowired
    MemberItemShareService memberItemShareService;

    @Autowired
    private ChargeRewardService chargeRewardService;
    
	protected Logger logger = Logger.getLogger(this.getClass());
    /**
     * 押金限制额度
     */
    public final static int DEPOSIT_THRESHOLD = Constant.JDPAYCONFIG.getInteger("DEPOSIT_THRESHOLD");
    
    /**
     * 大额支付限制额度
     */
    public final static int LARGE_PAYMENT_MONEY = Constant.JDPAYCONFIG.getInteger("LARGE_PAYMENT_MONEY");
    
    /**
     * 是否联系客服 0联系 1不联系
     */
    public final static String IS_CONTACT_CUSTOMER = Constant.JDPAYCONFIG.getProperty("IS_CONTACT_CUSTOMER");
    /**
     * 进入android 续费
     * @return
     */
    @RequestMapping("/android/torenew")
    public @ResponseBody  OpenResponse toAndroidRenew(@RequestParam(value = "message", required = true) String message){
        message = AppUtil.decode(message);
        JSONObject params = JSONObject.parseObject(message);
        String memberId = params.getString("memberId");
        String sign = params.getString("sign");
        boolean validate = AppUtil.validateBySign(memberId + Global.PWD_FOR_APP, sign);
        if (validate == false) {
            return new OpenResponse("false", "sign不匹配", null, "100002");
        }

        try {
            Member member = memberService.findMemberByID(Long.valueOf(memberId));
            if(member == null){
                return new OpenResponse("false", "用户不存在", null, "100002");
            }
            if(member.getStatuts() == 0){
                return new OpenResponse("false", "用户已被冻结", null, "100002");
            }
            
            //这里应该先查询权益分享，然后再去查询权益表，这里一定要考虑到共用人的情况
            MemberItemShare memberitemShare = new MemberItemShare();
            memberitemShare = memberItemShareService.selectByMemberId(Long.valueOf(memberId));
            if(memberitemShare == null){
            	return new OpenResponse("false", "用户没有任何权益", null, "100002");
            }
            MemberItem memberItem = new MemberItem();
            memberItem = memberItemService.findById(memberitemShare.getMemberItemId());
            

            //计算押金
            Double deposit = DEPOSIT_THRESHOLD - memberItem.getDeposit().doubleValue();
            deposit = Math.ceil(deposit);
            if(deposit <= 0){//当前押金大于10000的时候，往前台传值为0
                deposit = 0D;
            }
            Map<String,Object> maps = Maps.newHashMap();
            maps.put("deposit",deposit.intValue()+"");
            maps.put("balance","");
            maps.put("deposit_before",memberItem.getDeposit().intValue()+"");
            maps.put("balance_before",memberItem.getBalance().intValue()+"");
            maps.put("memberId","memberId");
            return new OpenResponse("true", "查询成功", maps, "100000");

        } catch (Exception e) {
            e.printStackTrace();
            return new OpenResponse("false", "服务器异常..", null, "100001");
        }

    }

//    public static void main(String[] args) {
////        Map<String,Object> maps = Maps.newHashMap();
////        maps.put("deposit","11000");
////        maps.put("balance","123");
////        maps.put("deposit_before","123456");
////        maps.put("balance_before","1111");
////        maps.put("memberId","memberId");
////        System.out.println(JSONObject.toJSONString(new OpenResponse("false", "查询成功", maps, "100000")));
////
////        Integer a = 15000 * 10000;
////        System.out.println(a);
//
////        Map<String,Object> maps = Maps.newHashMap();
////        maps.put("chargeOrderNo","123456789");
////        maps.put("totalMoney","1500");
//    	
//        Map<String,Object> maps = Maps.newHashMap();
//        maps.put("paymentType","0");
//        maps.put("chargeOrderNo", "100010101010");
//    	Map<String,Object> jdpayment = Maps.newHashMap();
//    	jdpayment.put("paymentUrl",Constant.JDPAYCONFIG.getProperty("paymentUrl"));
//    	maps.put("jdPayment", jdpayment);
//        maps.put("wechatPayment",null);
//        System.out.println(JSONObject.toJSONString(new OpenResponse("true", "操作成功", maps, "100000")));
//
//
//    }

    /**
     * 进入ios 续费
     * @return
     */
    @RequestMapping("/ios/torenew")
    public @ResponseBody  OpenResponse toIOSRenew(@RequestParam(value = "message", required = true) String message){
        return this.toAndroidRenew(message);
    }


    /**
     * 创建android续费订单
     * @return
     */
    @RequestMapping("/android/create/renew/order")
    public @ResponseBody OpenResponse createAndroidRenewOrder(@RequestParam(value = "message", required = true) String message){
        message = AppUtil.decode(message);
        JSONObject params = JSONObject.parseObject(message);
        String memberId = params.getString("memberId");
        String balance = params.getString("balance");
        balance = balance == null?"":balance;
        String deposit = params.getString("deposit");
        deposit = deposit == null?"":deposit;
        String sign = params.getString("sign");
        boolean validate = AppUtil.validateBySign(memberId + deposit +balance + Global.PWD_FOR_APP, sign);
//        if (validate == false) {
//            return new OpenResponse("false", "sign不匹配", null, "100002");
//        }
        Integer balanceInt = StringUtils.isEmpty(balance)?0:Integer.valueOf(balance);
        Integer depositInt = StringUtils.isEmpty(deposit)?0:Integer.valueOf(deposit);
        if(balanceInt <= 0 && depositInt<=0){
        	return new OpenResponse("false", "储值金额不能0", null, "100001");
        }

        ChargeOrder chargeOrder = new ChargeOrder();
        String chargeOrderNo = chargeOrderService.createOrderNo();
        chargeOrder.setChargeOrderNo(chargeOrderNo);
        chargeOrder.setCreatedMemberId(memberId);
        chargeOrder.setBalance(new BigDecimal(balanceInt));
        chargeOrder.setDeposit(new BigDecimal(depositInt));
        chargeOrder.setTotal(chargeOrder.getBalance().add(chargeOrder.getDeposit()));
      
        if(balanceInt > 0 && depositInt>0){
        	chargeOrder.setAccountType(3);//押金
        }else if(balanceInt > 0){
        	chargeOrder.setAccountType(1);//余额
        }else if(depositInt > 0){
        	chargeOrder.setAccountType(2);//押金
        }
        
        try {
            chargeOrder = chargeOrderService.createChargeOrder(chargeOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return new OpenResponse("false", "服务器异常..", null, "100001");
        }
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("chargeOrderNo",chargeOrder.getChargeOrderNo()+"");
        maps.put("totalMoney",chargeOrder.getTotal().intValue()+"");
        //判断是否大额支付
        double total = chargeOrder.getTotal().doubleValue();
        String isLargePayment = "0";
        if(total > LARGE_PAYMENT_MONEY){
        	isLargePayment = "1";
        }
        maps.put("isLargePayment",isLargePayment);
        maps.put("isContactCustomer",IS_CONTACT_CUSTOMER);//是否联系客服 0 联系 1不联系
        return new OpenResponse("true", "操作成功", maps, "100000");
    }

    /**
     * 创建ios续费订单
     * @return
     */
    @RequestMapping("/ios/create/renew/order")
    public @ResponseBody OpenResponse createIOSRenewOrder(@RequestParam(value = "message", required = true) String message){
        return this.createAndroidRenewOrder(message);
    }


    /**
     * 修改android支付方式
     * @param message
     * @return
     */
    @RequestMapping("/android/update/payMode")
    public @ResponseBody OpenResponse updateAndroidPayMode(@RequestParam(value = "message", required = true) String message,HttpServletRequest request){

        message = AppUtil.decode(message);
        JSONObject params = JSONObject.parseObject(message);
        String chargeOrderNo = params.getString("chargeOrderNo");
        String chargeSource = params.getString("chargeSource");//'支付渠道 0:京东；1:微信；2:后台人工
        String sign = params.getString("sign");
        boolean validate = AppUtil.validateBySign(chargeOrderNo + chargeSource + Global.PWD_FOR_APP, sign);
        if (validate == false) {
            return new OpenResponse("false", "sign不匹配", null, "100002");
        }
        ChargeOrder chargeOrderUpdate = new ChargeOrder();
        chargeOrderUpdate.setChargeOrderNo(chargeOrderNo);
        chargeOrderUpdate.setChargeSource(Integer.valueOf(chargeSource));
        ChargeOrder  chargeOrder = null;
        try {
            chargeOrder = chargeOrderService.findChargeOrderByChargeOrderNo(chargeOrderNo);
            chargeOrderUpdate.setId(chargeOrder.getId());
            chargeOrder = chargeOrderService.updateChargeOrderByPayType(chargeOrderUpdate);
        } catch (Exception e) {
            return new OpenResponse("false", "服务器异常..", null, "100001");
        }
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("paymentType",chargeSource);
        maps.put("chargeOrderNo", chargeOrderNo);
        if (chargeSource.equals("0")){//0 京东
        	Map<String,Object> jdpayment = Maps.newHashMap();
        	jdpayment.put("paymentUrl",Constant.JDPAYCONFIG.getProperty("paymentUrl"));
        	maps.put("jdPayment", jdpayment);
        }else if(chargeSource.equals("1")){//0 微信
        	SortedMap<String, Object> weChatmap = makeWechatAppPay(chargeOrder, request);
            maps.put("wechatPayment",weChatmap);
        }
        return new OpenResponse("true", "操作成功", maps, "100000");
    }
    
    /**
     * 构造微信统一下单接口 返回数据
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-10-19 下午7:17:19   
    * @return String
     */
    private SortedMap<String, Object> makeWechatAppPay(ChargeOrder chargeOrder,HttpServletRequest request){
    	logger.info("进去系统统一下单接口");
    	SortedMap<String, Object> paySignparams = new TreeMap<String, Object>();
    	SortedMap<String, Object> newPaySignparams = new TreeMap<String, Object>();
    	try {
			// 签名随机字符串，由于微信里面的坑太多，所以我们所有的签名字符串和时间戳最好公用，不然真的会蒙圈的
			String weiPaySignStr = WechatUrl.getRandomStringByLength(20);
			// 签名用时间戳 秒
			String timestamp = (System.currentTimeMillis()/1000) + "";
			// 统一下单签名参数哈希表
			// 统一下单的body,就是商品详情，要是传中文记得newString(body.getBytes("ISO8859-1"))，不然JS签名失败，
			// 但是即使这样,支付成功后微信给你发的支付凭证中商品详情还是乱码显示，所以传英文吧，这坑懒得去填
			String body = "极车公社";
			Map<String, Object> prepaySignParam = new HashMap<String, Object>();
			prepaySignParam.put("appid",  Constant.APPIDPAY);
			prepaySignParam.put("mch_id",  Constant.MCHID);
			prepaySignParam.put("body", body);
			prepaySignParam.put("nonce_str", weiPaySignStr);
			// 微信异步通知地址，在这里处理后续订单逻辑
			prepaySignParam.put("notify_url",
					Constant.domain+"wechat/wechatPay/weipayCallBack.do");
			prepaySignParam.put("out_trade_no", chargeOrder.getChargeOrderNo()); //获取系统订单编号
			prepaySignParam.put("spbill_create_ip", request.getRemoteAddr());// 用户IP地址
			//测试先改为0.01
			// 单位分
			//prepaySignParam.put("total_fee",2);
			//正式
			prepaySignParam.put("total_fee",chargeOrder.getTotal().multiply(new BigDecimal(100)).intValue());
			prepaySignParam.put("trade_type", "APP");
			
			// 构造微信预支付订单
			WeiChatPreAppOrder weiChatPreOrder = new WeiChatPreAppOrder();
			weiChatPreOrder.setAppid(Constant.APPIDPAY);
			weiChatPreOrder.setMch_id(Constant.MCHID);
			weiChatPreOrder.setBody(body);
			weiChatPreOrder.setNonce_str(weiPaySignStr);
			weiChatPreOrder
					.setNotify_url(Constant.domain+"wechat/wechatPay/weipayCallBack.do");
			weiChatPreOrder.setOut_trade_no(chargeOrder.getChargeOrderNo());// 系统订单号
			weiChatPreOrder.setSpbill_create_ip(request.getRemoteAddr());
			//测试先改为0.01
			//weiChatPreOrder.setTotal_fee(2);
			//正式
			weiChatPreOrder.setTotal_fee(chargeOrder.getTotal().multiply(new BigDecimal(100)).intValue());
			weiChatPreOrder.setTrade_type("APP");
			// 计算统一下单签名参数计算预支付订单的MD5签名
			logger.info("微信的key"+Constant.WECHAT_KEY);
			weiChatPreOrder.setSign(Signature.getSign(prepaySignParam));
			// 生成XML订单
			String xmlOrder = weiChatPreOrder.toXml();
			logger.info("统一下单接口的xml"+xmlOrder);
			// 通过微信下单接口获取prepay_id
			String prepayId = WechatUrl.getPrepayId(xmlOrder);
			logger.info("统一下单接口的prepayId"+prepayId);
			// 支付参数  切记全小写,对照APP支付微信文档 粘贴过来
			paySignparams.put("appid", Constant.APPIDPAY);
			paySignparams.put("partnerid", Constant.MCHID);
			paySignparams.put("prepayid", prepayId);
			paySignparams.put("package", "Sign=WXPay");
			paySignparams.put("noncestr", weiPaySignStr);
			paySignparams.put("timestamp", timestamp);
			logger.info("传给app的参数 appId"+Constant.APPID+"partnerId"+Constant.MCHID
					+"prepayId"+prepayId+"noncestr"+weiPaySignStr+"timeStamp"+timestamp+"key"+Constant.WECHAT_KEY);
			// 计算MD5签名 APP发起支付参与 [参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package]
			String paySign = Signature.getSign(paySignparams);
			logger.info(paySign);
			//String paySign = MD5Util.MD5Encode(signBuff.toString());
			paySignparams.put("sign", paySign);
			newPaySignparams.put("appId", Constant.APPIDPAY);
			newPaySignparams.put("partnerId", Constant.MCHID);
			newPaySignparams.put("prepayId", prepayId);
			newPaySignparams.put("packageValue", "Sign=WXPay");
			newPaySignparams.put("nonceStr", weiPaySignStr);
			newPaySignparams.put("timeStamp", timestamp);
			newPaySignparams.put("sign", paySign);
		} catch (Exception e) {
			logger.info("app获取支付所需参数错误"+e.getMessage());
		}
		// 返回支付页面
         return newPaySignparams;
    }
    /**
     * 修改ios支付方式
     * @param message
     * @return
     */
    @RequestMapping("/ios/update/payMode")
    public @ResponseBody OpenResponse updateIOSPayMode(@RequestParam(value = "message", required = true) String message,HttpServletRequest request){
       return  this.updateAndroidPayMode(message,request);
    }


    /**
     * Android扫码支付
     * message
     * @return
     */
    @RequestMapping("/android/scanCode/payment")
    public @ResponseBody OpenResponse checkAndroidScanCodePayment(@RequestParam(value = "message", required = true) String message){
        message = AppUtil.decode(message);
        JSONObject params = JSONObject.parseObject(message);
        String chargeOrderNo = params.getString("chargeOrderNo");
        String memberID = params.getString("memberId");
        String toKen = params.getString("toKen");
        String sign = params.getString("sign");
        this.logger.info("params>>>>>>>>" + message);
        if(StringUtils.isEmpty(chargeOrderNo)){
            return new OpenResponse("false", "支付订单号不允许为空", null, "100002");
        }
        if(StringUtils.isEmpty(memberID)){
            return new OpenResponse("false", "用户id不能为空", null, "100002");
        }
        if(StringUtils.isEmpty(toKen)){
            return new OpenResponse("false", "token不能为空", null, "100002");
        }
        
        boolean validate = AppUtil.validateBySign(chargeOrderNo + memberID + toKen + Global.PWD_FOR_APP, sign);
        if (validate == false) {
            return new OpenResponse("false", "sign不匹配", null, "100002");
        }

        if(JedisUtils.exists(toKen) == false){
            return new OpenResponse("false", "二维码已失效", null, "100002");
        }
         
    	ChargeOrder chargeOrder = new ChargeOrder();
    	chargeOrder.setChargeOrderNo(chargeOrderNo);
    	chargeOrder.setCreatedMemberId(memberID);
    	
        List<ChargeOrder> chargeOrders = chargeOrderService.selectByCondition(chargeOrder);
        if(CollectionUtils.isEmpty(chargeOrders)){
            return new OpenResponse("false", "订单信息不存在", null, "100002");
        }
        chargeOrder = chargeOrders.get(0);
        if(chargeOrder.getStatus().intValue() == 2){
            return new OpenResponse("false", "订单已无效，不允许支付", null, "100002");
        }
        if(chargeOrder.getStatus().intValue() == 3){
            return new OpenResponse("false", "订单已经成功支付，不允许支付", null, "100002");
        }
        
        String value = "{\"chargeOrderNo\":\""+chargeOrderNo+"\",\"memberID\":\""+memberID+"\",\"type\":\"jdPayment\"}";
        JedisUtils.set(toKen,value,120);

        //修改 td_charge_ordertype_log  是否打开网页 1：已打开；',
        ChargeOrdertypeLog  chargeOrdertypeLog = new ChargeOrdertypeLog();
        chargeOrdertypeLog.setOrderNo(chargeOrderNo);
        chargeOrdertypeLogService.updateIsOpenpage(chargeOrdertypeLog);
        
        //调用 webSocket 通知前端
		systemWebSocketHandler.sendMessageToUser(toKen, new TextMessage(value));

        return new OpenResponse("true", "操作成功", null, "100000");
    }
    
    
    public static void main(String[] args) {
    	Map<String,Object> maps = Maps.newHashMap();
    	maps.put("chargeOrderNo", "1234567");
    	maps.put("memberID", "1234567");
    	maps.put("type", "jdPayment");
        System.out.println(JSONObject.toJSONString(maps));

	}
    /**
     * IOS校验扫码支付
     * message
     * @return
     */
    @RequestMapping("/ios/scanCode/payment")
    public @ResponseBody OpenResponse checkIOSScanCodePayment(@RequestParam(value = "message", required = true) String message){
        return this.checkAndroidScanCodePayment(message);
    }
    
    /**
   	 * 
   	* @Description:TODO      
   	* @author:JIAZHIPENG  
   	* @time:2016-10-25 下午2:03:01   
   	* @return String
   	 */
   	@RequestMapping("/ios/queryWeChatOrder")
   	@ResponseBody
    public OpenResponse queryIosWeChatOrder(@RequestParam(value = "message", required = true)String message){
   		return queryWeChatOrder(message);
    }
   	
	@RequestMapping("/ios/queryIosRechargeMoney")
   	@ResponseBody
   	public OpenResponse queryIosRechargeMoney(@RequestParam(value = "message", required = true)String message){
   		return queryAndroidRechargeMoney(message);
   	}
   	
	@RequestMapping("/android/queryAndroidRechargeMoney")
   	@ResponseBody
   	public OpenResponse queryAndroidRechargeMoney(@RequestParam(value = "message", required = true)String message){
   		message = AppUtil.decode(message);
        JSONObject params = JSONObject.parseObject(message);
        String account = params.getString("account");
        Map<String, Object> param = new HashMap<String, Object>();
        ChargeReward chargeReward = chargeRewardService.selectObjectByCodition(new ChargeReward());
        param.put("message", "");
        if(chargeReward != null){
        	 Date start = chargeReward.getStartTime();
        	 Date end = chargeReward.getEndTime();
        	 Date nowDate = new Date();
        	 //查询是否有充返活动
        	 if(start.before(nowDate) && end.after(nowDate)){
        		  Double proportion = Double.parseDouble(chargeReward.getPercent().toEngineeringString());
        		  Long accountL = Long.decode(account);
                  Long yuan = (accountL * 10000);
                  Long giveAccount = (long) (yuan * (proportion -1));
                  Long money = yuan + giveAccount;
                  param.put("message", "送"+giveAccount+"元余额,共得余额"+money+"元");
        	 }
        }
        return new OpenResponse("true", "查询成功", param, "100000");
   	}
    
    /**
	 * 
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-10-25 下午2:03:01   
	* @return String
	 */
	@RequestMapping("/android/queryWeChatOrder")
	@ResponseBody
	public OpenResponse queryWeChatOrder(@RequestParam(value = "message", required = true)String message){
		message = AppUtil.decode(message);
        JSONObject params = JSONObject.parseObject(message);
        String chargeOrderNo = params.getString("chargeOrderNo");
		String weiPaySignStr = WechatUrl.getRandomStringByLength(20);
		Map<String, Object> prepaySignParam = new HashMap<String, Object>();
		prepaySignParam.put("appid",  Constant.APPIDPAY);
		prepaySignParam.put("mch_id",  Constant.MCHID);
		prepaySignParam.put("nonce_str", weiPaySignStr);
		prepaySignParam.put("out_trade_no", chargeOrderNo);

		WeChatQueryOrder order = new WeChatQueryOrder();
		order.setAppid(Constant.APPIDPAY);
		order.setMch_id(Constant.MCHID);
		order.setOut_trade_no(chargeOrderNo);
		order.setNonce_str(weiPaySignStr);
		order.setSign(Signature.getSign(prepaySignParam));
		// 生成XML订单
		String xmlOrder = order.toXml();
		String errCodeDes = "支付失败";
		Map<String,Object> maps = Maps.newHashMap();
		try {
		     Map<String, Object> resultMap = WechatUrl.getOrder(xmlOrder);
			 if(resultMap != null){
				 String resultCode = (String) resultMap.get("result_code");
				 String tradeState = (String) resultMap.get("trade_state");
				 if("SUCCESS".equals(resultCode)){
					switch (tradeState) {
						case "SUCCESS":
							 maps.put("code", 0);
							 return new OpenResponse("true", "支付成功", maps, "100000");
						case "REFUND":
							 maps.put("code", 1);
							 return new OpenResponse("true", "转入退款", maps, "100000");
						case "NOTPAY":
							 maps.put("code", 1);
							 return new OpenResponse("true", "未支付", maps, "100000");
						case "CLOSED":
							 maps.put("code", 1);
							 return new OpenResponse("true", "已关闭", maps, "100000");
						case "USERPAYING":
							 maps.put("code", 1);
							 return new OpenResponse("true", "用户支付中", maps, "100000");
						case "REVOKED":
							 maps.put("code", 1);
							 return new OpenResponse("true", "已撤销", maps, "100000");
						case "PAYERROR":
							 maps.put("code", 1);
							 return new OpenResponse("true", "支付失败", maps, "100000");
					}
					
				 }else{
					 maps.put("code", 1);
					 errCodeDes = (String) resultMap.get("err_code_des");
					 return new OpenResponse("true", errCodeDes, maps, "100000");
				 }
			 }
		} catch (Exception e) {
			maps.put("code", 1);
			return new OpenResponse("true", errCodeDes, maps, "100000");
		}	
		maps.put("code", 1);
		return new OpenResponse("true", errCodeDes, maps, "100000");
	}
}
