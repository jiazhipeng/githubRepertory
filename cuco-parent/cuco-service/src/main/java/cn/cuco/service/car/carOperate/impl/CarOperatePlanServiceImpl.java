package cn.cuco.service.car.carOperate.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.CarOperatePlanMapper;
import cn.cuco.entity.CarOperatePlan;
import cn.cuco.entity.Member;
import cn.cuco.enums.CarOperatePlanEnum;
import cn.cuco.exception.RuntimeExceptionWarn;
import cn.cuco.service.car.carOperate.CarOperatePlanService;
import cn.cuco.service.member.info.MemberService;

/** 
* @ClassName: CarOperatePlanServiceImpl 
* @Description: 车辆运营计划service实现层
* @author gongbw
* @date 2017年2月22日 下午3:33:32  
*/
@Service
public class CarOperatePlanServiceImpl implements CarOperatePlanService {
	
	@Autowired
	private CarOperatePlanMapper<CarOperatePlan> carOperatePlanMapper;
	@Autowired
	private MemberService memberService;

	@Override
	public CarOperatePlan createCarOperatePlan(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"创建将车辆运营计划，运营计划对象不能为空");
		//ParamVerifyUtils.paramNotNull(carOperatePlan.getCarportId(),"创建将车辆运营计划，车库ID不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getCarTypeId(),"创建将车辆运营计划，车型ID不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberId(),"创建将车辆运营计划，用户ID不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"创建将车辆运营计划，用车记录ID不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getStartTime(),"创建将车辆运营计划，开始时间不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getEndTime(),"创建将车辆运营计划，结束时间不能为空");
		//ParamVerifyUtils.paramNotNull(carOperatePlan.getType(),"创建将车辆运营计划，用车类型不能为空");
		Member member = this.memberService.getMemberById(carOperatePlan.getMemberId());
		if(null == member){
			throw new RuntimeExceptionWarn("用户不存在");
		}
		carOperatePlan.setMemberMobile(member.getMobile());
		carOperatePlan.setMemberName(member.getName());
		carOperatePlan.setCreated(new Date());
		carOperatePlan.setStatus(CarOperatePlanEnum.CarOperatePlanStatus.WAITEXECUTE.getIndex());
		this.carOperatePlanMapper.insertSelective(carOperatePlan);
		return this.carOperatePlanMapper.selectByPrimaryKey(carOperatePlan.getId());
	}

	@Override
	public CarOperatePlan updateOperatePlanInfoByCarUsedId(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"根据用车记录ID修改车辆运营计划基础信息，运营计划对象不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"根据用车记录ID修改车辆运营计划基础信息，用车记录ID不能为空");
		return this.carOperatePlanMapper.updateOperatePlanByCarUsedId(carOperatePlan);
	}

	@Override
	public List<CarOperatePlan> getCarOperatePlanList(CarOperatePlan carOperatePlan) {
		return this.carOperatePlanMapper.selectByCondition(carOperatePlan);
	}

	@Override
	public CarOperatePlan updateOperatePlan2WaitExecuteByCarUsedId(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"根据用车记录ID将车辆运营计划状态修改为待执行，运营计划对象不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"根据用车记录ID将车辆运营计划状态修改为待执行，用车记录ID不能为空");
		carOperatePlan.setType(CarOperatePlanEnum.CarOperatePlanStatus.WAITEXECUTE.getIndex());
		return this.carOperatePlanMapper.updateOperatePlanByCarUsedId(carOperatePlan);
	}

	@Override
	public CarOperatePlan updateOperatePlan2ExecutingByCarUsedId(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"根据用车记录ID将车辆运营计划状态修改为执行中，运营计划对象不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"根据用车记录ID将车辆运营计划状态修改为执行中，用车记录ID不能为空");
		carOperatePlan.setType(CarOperatePlanEnum.CarOperatePlanStatus.EXECUTING.getIndex());
		return this.carOperatePlanMapper.updateOperatePlanByCarUsedId(carOperatePlan);
	}

	@Override
	public CarOperatePlan updateOperatePlan2CompeleteByCarUsedId(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"根据用车记录ID将车辆运营计划状态修改为已完成，运营计划对象不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"根据用车记录ID将车辆运营计划状态修改为已完成，用车记录ID不能为空");
		carOperatePlan.setType(CarOperatePlanEnum.CarOperatePlanStatus.COMPELETE.getIndex());
		return this.carOperatePlanMapper.updateOperatePlanByCarUsedId(carOperatePlan);
	}

	@Override
	public CarOperatePlan updateOperatePlan2CancelByCarUsedId(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"根据用车记录ID将车辆运营计划状态修改为已取消，运营计划对象不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"根据用车记录ID将车辆运营计划状态修改为已取消，用车记录ID不能为空");
		carOperatePlan.setType(CarOperatePlanEnum.CarOperatePlanStatus.CANCEL.getIndex());
		return this.carOperatePlanMapper.updateOperatePlanByCarUsedId(carOperatePlan);
	}

	@Override
	public void deleteOperatePlanByCarUsedId(CarOperatePlan carOperatePlan) {
		ParamVerifyUtils.paramNotNull(carOperatePlan,"根据用车记录ID删除运营计划，运营计划对象不能为空");
		ParamVerifyUtils.paramNotNull(carOperatePlan.getMemberUsecarId(),"根据用车记录ID删除运营计划，用车记录ID不能为空");
		this.carOperatePlanMapper.deleteByCarUsedId(carOperatePlan);
	}
	
}
