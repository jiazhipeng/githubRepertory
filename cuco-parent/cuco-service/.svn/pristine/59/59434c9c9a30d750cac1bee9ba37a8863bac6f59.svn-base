package cn.cuco.service.member.carUsed.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.MemberCarportMapper;
import cn.cuco.dao.MemberFavoriteCartypeMapper;
import cn.cuco.entity.CarType;
import cn.cuco.entity.Carport;
import cn.cuco.entity.Member;
import cn.cuco.entity.MemberCarport;
import cn.cuco.entity.MemberFavoriteCartype;
import cn.cuco.entity.OperateLog;
import cn.cuco.enums.MemberCarportStatus;
import cn.cuco.enums.MemberStatus;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.enums.Valid;
import cn.cuco.exception.ExceptionUtil;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.carport.CarportService;
import cn.cuco.service.log.OperateLogService;
import cn.cuco.service.member.carUsed.MemberCarportService;
import cn.cuco.service.member.info.MemberService;

/** 
* @ClassName: MemberCarportServiceImpl 
* @Description: 用户车库相关接口实现
* @author zc.du
* @date 2017年2月23日 上午10:39:43
*/
@Service(value = "memberCarportService")
public class MemberCarportServiceImpl implements MemberCarportService {

	@Autowired
	private MemberCarportMapper<MemberCarport> memberCarportMapper;
	@Autowired
    private MemberFavoriteCartypeMapper<MemberFavoriteCartype> memberFavoriteCartypeMapper;
	@Autowired
	private CarportService carportService;
	@Autowired
	private OperateLogService logService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OperateLogService operateLogService;
	
	@Override
	public MemberCarport createMemberCarport(MemberCarport memberCarport) {
		ParamVerifyUtils.paramNotNull(memberCarport);
		Long memberId = memberCarport.getMemberId();
		Long carTypeId = memberCarport.getCartypeId();
		Long carportId = memberCarport.getCarportId();
		ParamVerifyUtils.paramNotNull(memberId, "创建用户车库时，用户ID不能为空");
		ParamVerifyUtils.paramNotNull(carportId, "创建用户车库时，车库ID不能为空");
		Member member = memberService.getMemberById(memberId);
		ParamVerifyUtils.paramNotNull(member, "用户不存在，创建用户车库失败");
		
		MemberCarport search = new MemberCarport();
		search.setMemberId(memberId);
		search.setCarportId(carportId);
		List<MemberCarport> list = this.memberCarportMapper.selectByCondition(search);
		if(CollectionUtils.isNotEmpty(list)){
			ExceptionUtil.throwWarn("该用户已经创建过该车库");
		}
		this.validateCarportAndCarType(carportId, carTypeId);
		Date date = new Date();
		memberCarport.setCreated(date);
		this.memberCarportMapper.insertSelective(memberCarport);
		//插入日志
		OperateLog log = new OperateLog(memberCarport.getId(), OperateLogEnum.MEMBER_CARPORT.getValue(), MemberCarportStatus.LOCK.getIndex(), member.getId(), member.getName(), date, "用户创建自己的车库");
		logService.createOperateLog(log);
		return this.getMemberCarportById(memberCarport.getId());
	}

	@Override
	public PageResult<MemberCarport> getMemberCarportListByPage(
			MemberCarport memberCarport) {
		ParamVerifyUtils.paramNotNull(memberCarport);
		Integer page = memberCarport.getPage();
		Integer pageSize = memberCarport.getPageSize();
		Integer totalSize = this.memberCarportMapper.selectCountByCondition(memberCarport);
		PageHelper.startPage(page, pageSize);
		List<MemberCarport> list = this.memberCarportMapper.selectByCondition(memberCarport);
		PageResult<MemberCarport> pageResult = new PageResult<MemberCarport>(page, pageSize, totalSize, list);
		return pageResult;
	}

	@Override
	public MemberCarport getMemberCarportById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "用户车库ID不能为空");
		return this.memberCarportMapper.selectByPrimaryKey(id);
	}

	@Override
	public MemberCarport updateMemberCarportStatusByUnlockin(Long memberCarportId) {
		ParamVerifyUtils.paramNotNull(memberCarportId);
		MemberCarport old = this.getMemberCarportById(memberCarportId);
		ParamVerifyUtils.paramNotNull("用户车库不存在，无法进行该操作");
		Integer status = old.getStatus();
		if(!status.equals(MemberCarportStatus.LOCK.getIndex()) && !status.equals(MemberCarportStatus.UNLOCK_IN.getIndex())){
			ExceptionUtil.throwWarn("当前用户车库状态为"+MemberCarportStatus.getName(status)+"无法进行该操作");
		}
		Date date = new Date();
		MemberCarport target = new MemberCarport();
		target.setId(old.getId());
		target.setStatus(MemberCarportStatus.UNLOCK_IN.getIndex());
		this.memberCarportMapper.updateByPrimaryKeySelective(target);
		//插入日志
		Member member = this.memberService.getMemberById(old.getMemberId());
		OperateLog log = new OperateLog(old.getId(), OperateLogEnum.MEMBER_CARPORT.getValue(), MemberCarportStatus.LOCK.getIndex(), member.getId(), member.getName(), date, "修改车库状态为解锁中");
		logService.createOperateLog(log);
		return this.getMemberCarportById(memberCarportId);
	}

	@Override
	public MemberCarport updateMemberCarportStatusByUnlocked(Long memberCarportId) {
		ParamVerifyUtils.paramNotNull(memberCarportId);
		MemberCarport old = this.getMemberCarportById(memberCarportId);
		ParamVerifyUtils.paramNotNull("用户车库不存在，无法进行该操作");
		Integer status = old.getStatus();
		if(!status.equals(MemberCarportStatus.LOCK.getIndex()) && !status.equals(MemberCarportStatus.UNLOCK_IN.getIndex())){
			ExceptionUtil.throwWarn("当前用户车库状态为"+MemberCarportStatus.getName(status)+"无法进行该操作");
		}
		Date date = new Date();
		MemberCarport target = new MemberCarport();
		target.setId(old.getId());
		target.setStatus(MemberCarportStatus.UNLOCKED.getIndex());
		target.setBuyTime(date);
		this.memberCarportMapper.updateByPrimaryKeySelective(target);
		//插入日志
		Member member = this.memberService.getMemberById(old.getMemberId());
		OperateLog log = new OperateLog(old.getId(), OperateLogEnum.MEMBER_CARPORT.getValue(), MemberCarportStatus.UNLOCKED.getIndex(), member.getId(), member.getName(), date, "修改车库状态为已解锁");
		logService.createOperateLog(log);
		return this.getMemberCarportById(memberCarportId);
	}

	@Override
	public MemberCarport updateMemberCarportStatusByDisable(Long memberCarportId) {
		ParamVerifyUtils.paramNotNull(memberCarportId);
		MemberCarport old = this.getMemberCarportById(memberCarportId);
		ParamVerifyUtils.paramNotNull("用户车库不存在，无法进行该操作");
		Integer status = old.getStatus();
		if(!status.equals(MemberCarportStatus.UNLOCKED.getIndex())){
			ExceptionUtil.throwWarn("当前用户车库状态为"+MemberCarportStatus.getName(status)+"无法进行该操作");
		}
		Date date = new Date();
		MemberCarport target = new MemberCarport();
		target.setId(old.getId());
		target.setStatus(MemberCarportStatus.DISABLE.getIndex());
		this.memberCarportMapper.updateByPrimaryKeySelective(target);
		//插入日志
		Member member = this.memberService.getMemberById(old.getMemberId());
		OperateLog log = new OperateLog(old.getId(), OperateLogEnum.MEMBER_CARPORT.getValue(), MemberCarportStatus.DISABLE.getIndex(), member.getId(), member.getName(), date, "修改车库状态为已停用");
		logService.createOperateLog(log);
		return this.getMemberCarportById(memberCarportId);
	}

	@Override
	public MemberFavoriteCartype createMarkFavoriteCarType(MemberFavoriteCartype memberFavoriteCartype) {
		ParamVerifyUtils.paramNotNull(memberFavoriteCartype);
		Long memberId = memberFavoriteCartype.getMemberId();
		Long carTypeId = memberFavoriteCartype.getCartypeId();
		Long carportId = memberFavoriteCartype.getCarportId();
		ParamVerifyUtils.paramNotNull(memberId, "标记喜欢车型时，用户ID不能为空");
		ParamVerifyUtils.paramNotNull(carTypeId, "标记喜欢车型时，车型ID不能为空");
		ParamVerifyUtils.paramNotNull(carportId, "标记喜欢车型时，车库ID不能为空");
		Member member = memberService.getMemberById(memberId);
		ParamVerifyUtils.paramNotNull(member, "用户不存在，标记喜欢车型失败");
		if(MemberStatus.FROZEN.getIndex().equals(member.getStatuts())){
			ExceptionUtil.throwWarn("用户已冻结，无法标记喜欢车型");
		}
		this.validateCarportAndCarType(carportId, carTypeId);
		int status = 0;
		MemberCarport target = new MemberCarport();
		target.setMemberId(memberId);
		target.setCarportId(carportId);
		List<MemberCarport> list = this.memberCarportMapper.selectByCondition(target);
		if(!CollectionUtils.isEmpty(list)){
			//修改用户车库、喜爱车型
			target = list.get(0);
			MemberCarport newOne = new MemberCarport();
			newOne.setCartypeId(carTypeId);
			newOne.setId(target.getId());
			this.memberCarportMapper.updateByPrimaryKeySelective(newOne);
			status = target.getStatus();
		}
		
		MemberCarport memberCarport = new MemberCarport();
		memberCarport.setCarportId(memberFavoriteCartype.getCarportId());
		memberCarport.setCartypeId(memberFavoriteCartype.getCartypeId());
		memberCarport.setMemberId(memberId);
		Date date = new Date();
		memberCarport.setCreated(date);
		this.memberCarportMapper.insertSelective(memberCarport);
		
		OperateLog log = new OperateLog(memberId, OperateLogEnum.MEMBER_CARPORT.getValue(), status, memberId, member.getName(), date, "设置喜爱车型");
		this.operateLogService.createOperateLog(log);
		memberFavoriteCartype.setCreated(date);
		this.memberFavoriteCartypeMapper.insertSelective(memberFavoriteCartype);
		return memberFavoriteCartype;
	}
	
	private boolean validateCarportAndCarType(Long carportId,Long carTypeId){
		Carport carport = carportService.getCarportById(carportId);
		ParamVerifyUtils.paramNotNull(carport,"车库不存在，无法执行该操作");
		if(Valid.DOWN.getValue().equals(carport.getValid())){
			ExceptionUtil.throwWarn("车库已下架，无法执行该操作");
		}
		if(null != carTypeId){
			int flag = 0;
			List<CarType> types = carport.getCarTypes();
			for(CarType type : types){
				if(type.getId().equals(carTypeId)){
					return true;
				}
			}
			if(flag == 0){
				ExceptionUtil.throwWarn("车库中不存在车型,标记喜欢车型失败");
				return false;
			}
		}
		return true;
	}

	@Override
	public MemberCarport updateCountOrMileage(MemberCarport memberCarport) {
		ParamVerifyUtils.paramNotNull(memberCarport);
		ParamVerifyUtils.paramNotNull(memberCarport.getModifer(),"操作人不能为空");
		ParamVerifyUtils.paramNotNull(memberCarport.getModifierId(),"操作人ID不能为空");
		BigDecimal mileage = memberCarport.getMileage();
		Integer count = memberCarport.getCount();
		if(null == count && null == mileage){
			ExceptionUtil.throwWarn("使用次数、行驶里程不能同时为空");
		}
		Long id = memberCarport.getId();
		ParamVerifyUtils.paramNotNull(id,"用户车库ID不能为空");
		MemberCarport old = this.getMemberCarportById(id);
		ParamVerifyUtils.paramNotNull(old,"用户车库不存在，无法执行该操作");
		String message = "";
		MemberCarport target = new MemberCarport();
		target.setId(id);
		if(null != count){
			if(count < old.getCount().intValue()){
				ExceptionUtil.throwWarn("使用次数参数错误");
			}
			target.setCount(count);
			message = "更新使用次数为"+count;
		}
		if(null != mileage){
			if(mileage.subtract(old.getMileage()).doubleValue() < 0){
				ExceptionUtil.throwWarn("行驶里程参数错误");
			}
			target.setMileage(mileage);
			if(null != count){
				message += ",";
			}
			message += "更新行驶里程为"+mileage;
		}
		OperateLog log = new OperateLog(id, OperateLogEnum.MEMBER_CARPORT.getValue(), old.getStatus(), memberCarport.getModifierId(), memberCarport.getModifer(), new Date(), message);
		this.operateLogService.createOperateLog(log);
		return this.getMemberCarportById(id);
	}

	@Override
	public MemberCarport getMemberCarport(MemberCarport memberCarport) {
		ParamVerifyUtils.paramNotNull(memberCarport);
		List<MemberCarport> list = this.memberCarportMapper.selectByCondition(memberCarport);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

}
