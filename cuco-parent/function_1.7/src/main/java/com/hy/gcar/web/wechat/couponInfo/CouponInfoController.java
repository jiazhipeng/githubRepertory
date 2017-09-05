package com.hy.gcar.web.wechat.couponInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.entity.Coupon;
import com.hy.gcar.entity.CouponDetail;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.service.coupon.CouponDetailService;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.coupon.CouponService;

@Controller
@RequestMapping("/wechat/couponInfo")
public class CouponInfoController {

protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CouponInfoService couponInfoService;
	
	@Autowired
	private CouponDetailService couponDetailService;
	
	@Autowired
	private CouponService couponService;
	
	/**根据会员Id清除已过期的优惠券
	 * @param couponInfo
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/cleanUpCouponOutTime",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> cleanUpCouponOutTime(CouponInfo couponInfo){
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			this.logger.info("一键清除开始---");
			this.couponInfoService.removeCouponInfoByOutTime(couponInfo);
			obj.put("result", true);
		} catch (Exception e) {
			obj.put("result", false);
			obj.put("msg", "一键清除失败");
			this.logger.error("一键清除失败---"+e);
		}
		return obj;
	}
	
	/**根据会员ID获取优惠券明细列表
	 * @param couponInfo
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> couponInfoList(CouponInfo couponInfo){
		Map<String, Object> obj = new HashMap<String, Object>();
		try{
			this.logger.info("查询会员ID为"+couponInfo.getMemberId()+"的优惠券列表");
			//首先需要获取可用券的数量
			int couponInfoCount = this.couponInfoService.getCouponInfoCountByMemberId(couponInfo);
			//得到该用户下面所有的优惠券信息
			List<CouponInfo> couponInfoList = this.couponInfoService.getCouponInfoListByEnable(couponInfo);
			//用于前台返值
			obj.put("result", true);
			obj.put("couponInfoCount", couponInfoCount);
			obj.put("couponInfoList", couponInfoList);
		}catch(Exception e){
			obj.put("result", false);
			obj.put("msg", "查询优惠券失败");
			this.logger.error("查询优惠券明细列表失败---"+e);
			e.printStackTrace();
		}
		return obj;
	}
	
	@RequestMapping(value="/couponDetail",method=RequestMethod.GET)
	public String couponDetail(Long couponId,String remark,String couponName,ModelMap map){
		CouponDetail couponDetail = new CouponDetail();
		couponDetail.setCouponId(couponId);
		List<CouponDetail> couponDetailList = couponDetailService.selectByCondition(couponDetail);
		Coupon coupon = new Coupon();
		coupon = this.couponService.findById(couponId);
		map.put("couponDetailList", couponDetailList);
		map.put("remark", coupon.getRemark());
		map.put("couponName", couponName);
		return "gcar/coupon/couponDetail";
	}
}
