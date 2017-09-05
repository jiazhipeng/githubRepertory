package cn.cuco.service.basic.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.dao.OperateSettingMapper;
import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OperateSetting;
import cn.cuco.enums.OperateLogEnum;
import cn.cuco.enums.OperateSettingTypeEnum;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.business.OperateSettingService;
import cn.cuco.service.log.OperateLogService;

@Service(value = "operateSettingService")
public class OperateSettingServiceImpl implements OperateSettingService {

    @Autowired
    private OperateSettingMapper<OperateSetting> operateSettingMapper;
    @Autowired
    private OperateLogService operateLogService;
    /**
     * 修改运营参数
     */
	@Override
	public OperateSetting updateOperateSetting(OperateSetting operateSetting) {
		ParamVerifyUtils.paramNotNull(operateSetting);
		ParamVerifyUtils.paramNotNull(operateSetting.getId(), "修改城市运营参数ID不能为空");
		ParamVerifyUtils.paramNotNull(operateSetting.getModifier(), "修改城市运营参数操作人不能为空");
		ParamVerifyUtils.paramNotNull(operateSetting.getModifierId(), "修改城市运营参数操作ID不能为空");
		//判断运营参数是否存在
		ParamVerifyUtils.paramNotNull(this.getOperateSettingById(operateSetting.getId()), "运营参数不存在");
		//创建城市运营参数修改日志
		operateLogService.createOperateLogForOperateSetting(operateSetting);
		operateSetting.setLasttimeModify(new Date());
		//修改城市运营参数
		operateSettingMapper.updateByPrimaryKeySelective(operateSetting);
		
		return operateSettingMapper.selectByPrimaryKey(operateSetting.getId());
	}

	/**
	 * 查看参数详情
	 */
	@Override
	public OperateSetting getOperateSettingById(Long id) {
		ParamVerifyUtils.paramNotNull(id, "修改城市运营参数ID不能为空");
		return operateSettingMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询参数列表
	 */
	@Override
	public PageResult<OperateSetting> getOperateSettingParameterByPage(OperateSetting operateSetting) {
		//设置参数类型
		Integer page = operateSetting.getPage();
	    Integer pageSize = operateSetting.getPageSize();
	     /*搜索条件*/
	    OperateSetting operateSettingSearch = new OperateSetting();
	    operateSettingSearch.setType(OperateSettingTypeEnum.PARAMETER.getValue());
	    List<OperateSetting> operateSettings = null;
	    /*总记录数*/
	    Integer totalSize = this.operateSettingMapper.selectCountByCondition(operateSettingSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    operateSettings = this.operateSettingMapper.selectByCondition(operateSettingSearch);
    
        PageResult<OperateSetting> pageResult = new PageResult<OperateSetting>(page,pageSize,totalSize,operateSettings);
		return pageResult;
	}

	/**
	 * 分页查询费用列表
	 */
	@Override
	public PageResult<OperateSetting> getOperateSettingCostByPage(OperateSetting operateSetting) {
		//设置参数类型
		Integer page = operateSetting.getPage();
	    Integer pageSize = operateSetting.getPageSize();
	     /*搜索条件*/
	    OperateSetting operateSettingSearch = new OperateSetting();
	    operateSettingSearch.setType(OperateSettingTypeEnum.COST.getValue());
	    List<OperateSetting> operateSettings = null;
	    /*总记录数*/
	    Integer totalSize = this.operateSettingMapper.selectCountByCondition(operateSettingSearch);
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    operateSettings = this.operateSettingMapper.selectByCondition(operateSettingSearch);
    
        PageResult<OperateSetting> pageResult = new PageResult<OperateSetting>(page,pageSize,totalSize,operateSettings);
		return pageResult;
	}

	/**
	 * 燃油参数
	 */
	@Override
	public OperateSetting getOperateSettingForFuel(){
		return operateSettingMapper.selectByPrimaryKey(1L);
	}

	/**
	 * 延期还车罚金比例
	 */
	@Override
	public OperateSetting getOperateSettingForDeferredFinesRatio(){
		return operateSettingMapper.selectByPrimaryKey(2L);
	}
	
	/**
	 * 首次储值金额
	 */
	@Override
	public OperateSetting getOperateSettingForAmountMoney(){
		return operateSettingMapper.selectByPrimaryKey(3L);
	}
	
	/**
	 * 罚分代处理金额
	 */
	@Override
	public OperateSetting getOperateSettingForAmountOfIllegalDeduction(){
		return operateSettingMapper.selectByPrimaryKey(4L);
	}
	
	/**
	 * 违章押金服务费比例
	 */
	@Override
	public OperateSetting getOperateSettingForIllegalDepositServiceFeeRatio(){
		return operateSettingMapper.selectByPrimaryKey(5L);
	}
	
	/**
	 * 交接车延时参数
	 */
	@Override
	public OperateSetting getOperateSettingForHandover(){
		return operateSettingMapper.selectByPrimaryKey(6L);
	}

	/**
	 * 约车最短时长
	 */
	@Override
	public OperateSetting getOperateSettingForAbout(){
		return operateSettingMapper.selectByPrimaryKey(7L);
	}
	
	
	/**
	 * 送车服务区间
	 */
    public OperateSetting getOperateSettingForDeliveryServiceInterva(){
		return operateSettingMapper.selectByPrimaryKey(8L);
	}
	
	/**
	 * 用车提前
	 */
	@Override
	public OperateSetting getOperateSettingForCarAhead(){
		return operateSettingMapper.selectByPrimaryKey(9L);
	}

	
	/**
	 * 分页查询操作日志
	 */
	@Override
	public PageResult<OperateLog> getOperateLogByPage(OperateLog operateLog) {
		ParamVerifyUtils.paramNotNull(operateLog);
		ParamVerifyUtils.paramNotNull(operateLog.getOperationId(), "查询城市运营参数变更日志ID不能为空");
	    operateLog.setType(OperateLogEnum.OPERATE_SETTING.getValue());
		return operateLogService.getOperateLogByPage(operateLog);
	}
  
}
