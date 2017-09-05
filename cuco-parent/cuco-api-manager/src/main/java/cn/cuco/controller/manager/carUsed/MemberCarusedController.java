/**   

 * @Title: MemberCarusedController.java 
 * @Prject: cuco-api-manager
 * @Package: cn.cuco.controller.manager.carUsed 
 * @Description: TODO
 * @author: wangchuntao 
 * @date: 2017年2月23日 下午2:06:48 
 * @version: V1.0   
 */
package cn.cuco.controller.manager.carUsed;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.cuco.annotation.API;
import cn.cuco.controller.entity.memberCarUsedDetailVO;
import cn.cuco.entity.MemberCarport;
import cn.cuco.entity.OrderMemberCarUsed;
import cn.cuco.httpservice.ResponseUtil;
import cn.cuco.service.member.carUsed.MemberCarportService;
import cn.cuco.service.order.OrderMemberCarUsedService;

/** 
 * @ClassName: MemberCarusedController 
 * @Description: TODO
 * @author: wangchuntao 
 * @date: 2017年2月23日 下午2:06:48  
 */
@RestController
public class MemberCarusedController {
//	根据手机号查询用户用车信息接口
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderMemberCarUsedService memberCarUsedService;
	@Autowired
	MemberCarportService memberCarportService;
	/**
	 * @Title: getMemberCarusedInfo 
	 * @Description: 查询用户用车信息(保险)
	 * @author: wangchuntao 
	 * @param memberCarUsed
	 * @return Object
	 * @date: 2017年2月23日 下午4:56:29
	 */
	@API(value="查询用户用车信息")
	@RequestMapping(value = "/memberCarused/getMemberCarusedInfo",method = RequestMethod.POST)
	public Object getMemberCarusedInfo(@RequestBody OrderMemberCarUsed memberCarUsed){
		OrderMemberCarUsed carUsed = memberCarUsedService.getOrderMemberCarUsedByMobileAndCarPlateNum(memberCarUsed.getMemberMobile(), memberCarUsed.getCarPlateNum());
		return ResponseUtil.toSuccessBody(carUsed);
	}
	
	
	/**
	 * @Title: getOrderCarUsedListByPage 
	 * @Description: 用车详情
	 * @author: wangchuntao 
	 * @param memberId
	 * @return Object
	 * @date: 2017年2月23日 下午4:32:15
	 */
	@API(value="用车详情")
    @RequestMapping(value = "/memberCarused/getmemberCarusedDetail",method = RequestMethod.GET)
    public Object getmemberCarusedDetail(long memberId){
		memberCarUsedDetailVO carUsedDetailVO = new memberCarUsedDetailVO();
		
		carUsedDetailVO.setCarUsedTotal(memberCarUsedService.getOrderMemberCarUsedCountByMemberId(memberId));
		carUsedDetailVO.setMileageTotal(memberCarUsedService.getOrderMemberCarUsedMileagesByMemberId(memberId)==null?0:memberCarUsedService.getOrderMemberCarUsedMileagesByMemberId(memberId).intValue());
		MemberCarport memberCarport = new MemberCarport();
		memberCarport.setMemberId(memberId);
//		carUsedDetailVO.setCarportList(memberCarportService.getMemberCarportListByPage(memberCarport ).getItems());
		carUsedDetailVO.setCarUsedHabit(memberCarUsedService.getMemberDriveMileageStatistics(memberId));
		carUsedDetailVO.setCarUsedPreference(memberCarUsedService.getMemberCarTypeUsedStatistics(memberId));
        return carUsedDetailVO;
    }
	/**
	 * @Title: getmemberCarusedLogs 
	 * @Description: 用车明细日志
	 * @author: wangchuntao 
	 * @param memberId
	 * @return 
	 * @return Object
	 * @date: 2017年2月23日 下午4:59:01
	 */
	@API(value="用车明细")
	@RequestMapping(value = "/memberCarused/getmemberCarusedLogs",method = RequestMethod.GET)
	public Object getmemberCarusedLogs(long memberId){
		OrderMemberCarUsed memberCarUsed = new OrderMemberCarUsed();
		memberCarUsed.setMemberId(memberId);
		return memberCarUsedService.getOrderMemberCarUsedListByPage(memberCarUsed );
	}
}
