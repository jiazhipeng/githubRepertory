package com.hy.gcar.web.wechat.personalCenter;

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

import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Suggest;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.member.MemberService;

@Controller
@RequestMapping("/wechat/personalCenter")
public class PersonalCenterController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private CouponInfoService couponInfoService;
	/**
	 * 进入优惠券页面(第一阶段只需要跳静态页面)
	 * @return
	 */
	@RequestMapping(value="/toCouponList",method=RequestMethod.GET)
	public String toCouponList(CouponInfo couponInfo,ModelMap modelMap){
		this.logger.info("跳转进入优惠券页面--");
		String memberId = couponInfo.getMemberId()+"";
		//首先需要获取可用券的数量
		int couponInfoCount = this.couponInfoService.getCouponInfoCountByMemberId(couponInfo);
		//得到该用户下面所有的优惠券信息
		List<CouponInfo> couponInfoList = this.couponInfoService.getCouponInfoListByEnable(couponInfo);
		
		modelMap.put("memberId", memberId);
		modelMap.put("couponInfoCount", couponInfoCount);
		modelMap.put("couponInfoList", couponInfoList);
		return "gcar/coupon/couponList";
	}
	
	/**
	 * 进入我的资产
	 * @return
	 */
	@RequestMapping(value="/toAssets",method=RequestMethod.GET)
	public String toAssets(){
		this.logger.info("进入我的资产---");
		return "gcar/asset/assets";
	}
	
	/**进入关于极车
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/toAbout",method=RequestMethod.GET)
	public String toAbout(String memberId,ModelMap map){
		this.logger.info("进入关于公社---");
		map.put("memberId", memberId);
		return "gcar/about/about";
	}
	
	/**进入意见建议
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/toSuggest",method=RequestMethod.GET)
	public String suggest(Suggest suggest,ModelMap map) throws Exception{
		this.logger.info("进入意见建议---");
		Member member =memberService.findMemberByID(suggest.getMemberId());
		map.put("member", member);
		return "gcar/suggest/suggestCreate";
	}
}
