package com.hy.gcar.web.gatewayPay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hy.common.utils.Identities;
import com.hy.common.utils.JedisUtils;
import com.hy.common.utils.MD5Util;
import com.hy.common.utils.StringUtils;
import com.hy.constant.Constant;
import com.hy.gcar.entity.ChargeOrder;
import com.hy.gcar.entity.ChargeOrderLog;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.chargeOrderLog.ChargeOrderLogService;
import com.hy.gcar.utils.ResultUtils;
import com.hy.jd.pay.query.util.JDPayQueryUtils;

/**
 * Created by 海易出行 on 2016/9/23.
 */
@Controller
@RequestMapping("/gateway/pay")
public class GatewayPayController {
    protected Logger logger = Logger.getLogger(this.getClass());


    @Autowired
    private ChargeOrderService chargeOrderService;
    
    @Autowired
    private ChargeOrderLogService chargeOrderLogService;
    /**
     * 生成支付二维码
     * @return
     */
    @RequestMapping("/createPayQRCode")
    public  @ResponseBody Map<String,Object> createPayQRCode(){
        //生成一个随机数
        String uuid = Identities.uuid2();
        //String qrCodePath =
        //String path = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path")+"/payQRCode/";
        //String savePath = null;
        Map<String,Object> map = Maps.newHashMap();
        try {
            //String qrcodePicture  = QRCodeUtil.encode(uuid, null, path, false);
            //savePath = Constant.APPLICATION_LOAD.getProperty("picture_database_path")+"/payQRCode/"+qrcodePicture;
        	uuid = "jcgs_" + uuid;
        	JedisUtils.set(uuid,"",120);
           // map.put("qrCode",savePath);
            map.put("token",uuid);
            this.logger.info("京东支付二维码生成随机数>>>>>" + uuid);
           // this.logger.info("京东支付二维码url地址" + savePath);
        } catch (Exception e) {
            logger.error("生成支付二维码失败..",e);
            return ResultUtils.fail("生成支付二维码失败..");
        }
        return ResultUtils.success(map);
    }


    public static void main(String[] args) {
      
    }


    /**
     * 获取订单信息
     * @return
     */
    @RequestMapping("/getOrderInfo")
    public @ResponseBody Map<String,Object> getOrderInfo(String chargeOrderNo,String memberID){
    	
    	if(StringUtils.isEmpty(chargeOrderNo)){
            return ResultUtils.fail("订单号不允许为空");
    	}
    	if(StringUtils.isEmpty(memberID)){
            return ResultUtils.fail("用户id不允许为空");	
    	}
    	
    	ChargeOrder chargeOrder = new ChargeOrder();
    	chargeOrder.setChargeOrderNo(chargeOrderNo);
    	chargeOrder.setCreatedMemberId(memberID);
    	
        List<ChargeOrder> chargeOrders = chargeOrderService.selectByCondition(chargeOrder);
        if(CollectionUtils.isEmpty(chargeOrders)){
            return ResultUtils.fail("订单信息不存在");
        }
        chargeOrder = chargeOrders.get(0);
        if(chargeOrder.getStatus().intValue() == 2){
            return ResultUtils.fail("订单已无效，不允许支付");
        }
        if(chargeOrder.getStatus().intValue() == 3){
            return ResultUtils.fail("订单已经成功支付，不允许支付");
        }
        
        
        
        String total = chargeOrder.getTotal().toString();
        Map<String,Object> maps = Maps.newHashMap();
        Map<String,Object> orderInfoMap = Maps.newHashMap();
        orderInfoMap.put("chargeOrderNo",chargeOrder.getChargeOrderNo()+"");
        orderInfoMap.put("total",total);
        orderInfoMap.put("memberName",chargeOrder.getCreatedMemberName());
        orderInfoMap.put("memberMobile",chargeOrder.getCreatedMemberMobile());
        maps.put("orderInfo",orderInfoMap);



        //京东支付的参数
        String v_oid = chargeOrderNo;//订单号
        String v_amount = total;// 订单金额
        String v_mid = Constant.JDPAYCONFIG.getProperty("v_mid");// 1001是网银在线的测试商户号，商户要替换为自己的商户号。
        String v_url = Constant.JDPAYCONFIG.getProperty("v_url");// 商户自定义返回接收支付结果的页面。对应Receive.jsp示例
        //参照"网银在线支付B2C系统商户接口文档v4.1.doc"中2.3.3.1
        String key = Constant.JDPAYCONFIG.getProperty("key");// 参照"网银在线支付B2C系统商户接口文档v4.1.doc"中2.4.1进行设置。
        String remark2 =  Constant.JDPAYCONFIG.getProperty("remark2");//服务器异步通知的接收地址。对应AutoReceive.jsp示例。必须要有[url:=]格式
        remark2 = "[url:="+ remark2+"]";
        //以上三项必须修改
        String v_moneytype ="CNY";	                  				// 币种
        String text = v_amount+v_moneytype+v_oid+v_mid+v_url+key;   // 拼凑加密串
        String v_md5info = MD5Util.MD5Encode(text).toUpperCase();                 // 网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写
        Map<String,Object> jdPayParam = Maps.newHashMap();
        jdPayParam.put("v_md5info",v_md5info);
        jdPayParam.put("v_mid",v_mid);
        jdPayParam.put("v_oid",v_oid);
        jdPayParam.put("v_amount",v_amount);
        jdPayParam.put("v_moneytype",v_moneytype);
        jdPayParam.put("v_url",v_url);
        jdPayParam.put("remark2",remark2);
        jdPayParam.put("submitUrl",Constant.JDPAYCONFIG.getProperty("submitUrl"));
        maps.put("jdPay",jdPayParam);
        return ResultUtils.success(maps);
    }
    
    /**
     * 校验支付是否成功
     * @return
     */
    @RequestMapping("/checkPaymentIsSuccess")
    public @ResponseBody  Map<String,Object> checkPaymentIsSuccess(String chargeOrderNo){
    	if(StringUtils.isEmpty(chargeOrderNo)){
    		return ResultUtils.fail("订单号不能为空");
    	}
    	try{
	    	ChargeOrder chargeOrder = chargeOrderService.findChargeOrderByChargeOrderNo(chargeOrderNo);
	    	if(chargeOrder == null){
	    		return ResultUtils.fail("订单号不存在");
	    	}
	    	if(chargeOrder.getStatus().intValue() == 1){
	    		return verificationPayment(chargeOrder);
	    	}else if(chargeOrder.getStatus().intValue() == 3){
	    		return ResultUtils.success("支付成功",3);
	    	}else if(chargeOrder.getStatus().intValue() == 4){
	    		return ResultUtils.success("支付失败",4);
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		return ResultUtils.success("支付失败",3);
    	}
		return ResultUtils.success("支付成功",3);
    	
    }

    /**
     * 调用京东查询接口验证是否支付成功，如果成功则需要返回
     * @param chargeOrderNo
     * @return
     * @throws Exception
     */
	public Map<String,Object> verificationPayment(ChargeOrder chargeOrder) throws Exception {
		try{
			//调用京东查询接口查询此订单是否支付成功
			String json = JDPayQueryUtils.transactionQuery(chargeOrder.getChargeOrderNo());
			logger.info("json>>>>>>>" + json);
			Map<String,String>  map = JSONObject.parseObject(json,Map.class);
			if("0000000".equals(map.get(JDPayQueryUtils.API_DATA_CODE))){
				ChargeOrderLog chargeOrderLog = new ChargeOrderLog(); 
				chargeOrderLog.setOrderNo(map.get(JDPayQueryUtils.API_DATA_TRADE));
			    chargeOrderLog.setJdJson(json);
				if("S".equalsIgnoreCase(map.get(JDPayQueryUtils.API_DATA_STATUS))){
					updateChargeOrderLog(chargeOrder, map, chargeOrderLog);
				}else if("F".equalsIgnoreCase(map.get(JDPayQueryUtils.API_DATA_STATUS))){
			    	chargeOrderLogService.updateChargeOrderLogFail(chargeOrderLog);
				}	
			}
			ChargeOrder ChargeOrder = chargeOrderService.findChargeOrderByChargeOrderNo(chargeOrder.getChargeOrderNo());
			if(ChargeOrder.getStatus().intValue() == 1){
				return ResultUtils.success("订单正在支付中",1);
			}else if(ChargeOrder.getStatus().intValue() == 3){
				return ResultUtils.success("支付成功",3);
			}else if(ChargeOrder.getStatus().intValue() == 4){
				return ResultUtils.success("支付失败",4);
			}
			return ResultUtils.success("支付成功",3);
		}catch(Exception e){
			e.printStackTrace();
			return ResultUtils.success("服务器异常...",1); 
		}

	}


	private void updateChargeOrderLog(ChargeOrder chargeOrder, Map<String, String> map, ChargeOrderLog chargeOrderLog)throws Exception {
		double amount = Double.parseDouble(map.get(JDPayQueryUtils.API_DATA_AMOUNT));//返回的金额 以分为单位
		double total = chargeOrder.getTotal().multiply(new BigDecimal(100)).doubleValue();
		if(amount != total){
			String remark = "查询接口订单【"+map.get(JDPayQueryUtils.API_DATA_ORDER_TRADE)+"】返回的金额为"+ amount + ",数据库中的金额为"+ total;
			logger.info(remark);
			chargeOrderLog.setLasttimeModify(new Date());
			chargeOrderLog.setRemark(remark);
			chargeOrderLogService.updateByPrimaryKeySelective(chargeOrderLog);
		}else{
			chargeOrderLog.setBalance(chargeOrder.getBalance());
			chargeOrderLogService.updateChargeOrderLogComplete(chargeOrderLog);
		}
	}

}
