package com.hy.gcar.web.app.jdPay;

import com.alibaba.fastjson.JSONObject;
import com.hy.common.utils.MD5Util;
import com.hy.constant.Constant;
import com.hy.gcar.entity.ChargeOrder;
import com.hy.gcar.entity.ChargeOrderLog;
import com.hy.gcar.entity.JDPayCallBack;
import com.hy.gcar.service.chargeOrder.ChargeOrderService;
import com.hy.gcar.service.chargeOrderLog.ChargeOrderLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by 海易出行 on 2016/9/21.
 */
@Controller
@RequestMapping("/jd/pay")
public class JDPayController {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ChargeOrderLogService chargeOrderLogService;


    @Autowired
    ChargeOrderService chargeOrderService;

    /**
     * 京东支付成功后异步返回
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/asynchronousCallBack")
    public void asynchronousCallBack(HttpServletRequest request, HttpServletResponse response){  
    	this.logger.info("京东支付成功后异步回调.....");
    	try {
            PrintWriter printWriter = response.getWriter(); 
			boolean result = callBack(request);
			if(result){
		        printWriter.print("ok");
			}else{
		        printWriter.print("error");
			}
	        printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

	public  boolean callBack(HttpServletRequest request) throws Exception {
		JDPayCallBack jdPayCallBack = new JDPayCallBack();
        String key = Constant.JDPAYCONFIG.getProperty("key");// 参照"网银在线支付B2C系统商户接口文档v4.1.doc"中2.4.1进行设置。
        //获取参数
        String v_oid = request.getParameter("v_oid");		// 订单号
        jdPayCallBack.setV_oid(v_oid);
        String v_pmode = request.getParameter("v_pmode");		// 支付方式中文说明，如"中行长城信用卡"
        jdPayCallBack.setV_pmode(v_pmode);
        String v_pstatus = request.getParameter("v_pstatus");	// 支付结果，20支付完成；30支付失败；
        jdPayCallBack.setV_pstatus(v_pstatus);
        String v_pstring = request.getParameter("v_pstring");	// 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
        jdPayCallBack.setV_pstatus(v_pstring);
        String v_amount = request.getParameter("v_amount");		// 订单实际支付金额
        jdPayCallBack.setV_amount(v_amount);
        String v_moneytype = request.getParameter("v_moneytype");	// 币种
        jdPayCallBack.setV_moneytype(v_moneytype);
        String v_md5str = request.getParameter("v_md5str");		// MD5校验码
        jdPayCallBack.setV_md5str(v_md5str);
        String remark1 = request.getParameter("remark1");		// 备注1
        jdPayCallBack.setRemark1(remark1);
        String remark2 = request.getParameter("remark2");		// 备注2
        jdPayCallBack.setRemark2(remark2);

        String text = v_oid + v_pstatus + v_amount + v_moneytype + key; //拼凑加密串
        this.logger.info(v_oid + ",京东支付回掉>>>>>拼凑加密前串" + text);
        String v_md5text = MD5Util.MD5Encode(text).toUpperCase();
        this.logger.info(v_oid + ",京东支付回掉>>>>>拼凑加密后串" + v_md5text);
        if (!v_md5str.equals(v_md5text)) {
            this.logger.info(v_oid + "," + text + "！=" + v_md5text);
            return false;
        }
        // 支付成功，商户 根据自己业务做相应逻辑处理
        // 此处加入商户系统的逻辑处理（例如判断金额，判断支付状态(20成功,30失败)，更新订单状态等等）......
        ChargeOrderLog chargeOrderLog = chargeOrderLogService.findChargeOrderLogByOrderNo(v_oid);
        if(chargeOrderLog == null){
            this.logger.info(v_oid + ",订单不存在.....");
            return false;
        }
        //验证返回的金额
        if(chargeOrderLog.getOrderAmount().doubleValue() != Double.valueOf(v_amount)){
            this.logger.info(v_oid + ",京东支付返回的金额和订单金额不一致..."+chargeOrderLog.getOrderAmount().doubleValue()+"," + Double.valueOf(v_amount));
            return false;

        }
        if(chargeOrderLog.getOrderStatus().intValue() == 3){
            this.logger.info(v_oid + ",支付已成功.......,不需要重新支付");
            return true;

        }
        chargeOrderLog.setOrderNo(v_oid);
        chargeOrderLog.setBank(v_pmode);
        chargeOrderLog.setJdJson(JSONObject.toJSONString(jdPayCallBack));
        if ("20".equals(v_pstatus)) {
        	chargeOrderLogService.updateChargeOrderLogComplete(chargeOrderLog);
        }else if("30".equals(v_pstatus)){//支付失败
        	chargeOrderLogService.updateChargeOrderLogFail(chargeOrderLog);
        }
        
        this.logger.info(v_oid + ",v_pstatus>>>>>>" + v_pstatus + ">>>>> 支付成功");
        return true;
	}

    /**
     * 京东支付成功后同步返回
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/synchronizationCallBack")
    public String synchronizationCallBack(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
    	this.logger.info("京东支付成功后同步回调.....");
    	String v_pstatus = request.getParameter("v_pstatus");	// 支付结果，20支付完成；30支付失败；
        if("30".equals(v_pstatus)){
            map.put("v_pstatus","支付失败");
            return "redirect:"+Constant.JDPAYCONFIG.getProperty("jd_callBack_fail_url");
        }
		try {
			boolean result = callBack(request);
			if(!result){
	            return "redirect:"+Constant.JDPAYCONFIG.getProperty("jd_callBack_fail_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        String v_oid = request.getParameter("v_oid");		// 订单号
        ChargeOrder chargeOrder = chargeOrderService.findChargeOrderByChargeOrderNo(v_oid);
        if(chargeOrder.getStatus().intValue() == 3){
            return "redirect:"+Constant.JDPAYCONFIG.getProperty("jd_callBack_success_url");
        }else if(chargeOrder.getStatus().intValue() == 4){
            return "redirect:"+Constant.JDPAYCONFIG.getProperty("jd_callBack_fail_url");
        }
        return "redirect:"+Constant.JDPAYCONFIG.getProperty("jd_callBack_success_url");
    }

    }
