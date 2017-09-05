package cn.cuco.service.basic.business;


import cn.cuco.entity.OperateLog;
import cn.cuco.entity.OperateSetting;
import cn.cuco.page.PageResult;

/**
* @ClassName: OperateSettingService 
* @Description: 运营参数接口
* @author huanghua
* @date 2017年2月22日 下午2:33:38
 */
public interface OperateSettingService {

    /**
    * @Title: updateOperateSetting 
    * @Description: 修改运营参数
    * @param @param pperateSetting
    * @return OperateSetting
     */
    public OperateSetting updateOperateSetting(OperateSetting operateSetting);

    /**
    * @Title: getOperateSettingById 
    * @Description: 取运营参数详情
    * @author huanghua
    * @param id
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingById(Long id);

    /**
    * @Title: getOperateSettingParameterByPage 
    * @Description:分页取运营参数
    * @author huanghua
    * @param operateSetting
    * @return
    * @return PageResult<OperateSetting>
     */
    public PageResult<OperateSetting> getOperateSettingParameterByPage(OperateSetting operateSetting);
    
    /**
    * @Title: getOperateSettingCostByPage 
    * @Description: 分页取费用管理
    * @param operateSetting
    * @return List<OperateSetting>
     */
    public PageResult<OperateSetting> getOperateSettingCostByPage(OperateSetting operateSetting);
    
    /**
    * @Title: getOperateLogByPage 
    * @Description: 分页取操作日志
    * @author huanghua
    * @param operateSetting
    * @return PageResult<OperateLog>
     */
     public PageResult<OperateLog> getOperateLogByPage(OperateLog operateLog);
    
    /**
    * @Title: getOperateSettingForFuel 
    * @Description: 燃油参数
    * @author huanghua
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForFuel();
    
    /**
    * @Title: getOperateSettingForDeferredFinesRatio 
    * @Description: 延期还车违约金比例
    * @author huanghua
    * @return
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForDeferredFinesRatio();
    
    /**
    * @Title: getOperateSettingForService 
    * @Description: 首次储值金额
    * @author huanghua
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForAmountMoney();

    /**
    * @Title: getOperateSettingForAmountOfIllegalDeduction 
    * @Description: 罚分代处理金额
    * @author huanghua
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForAmountOfIllegalDeduction();
    
    /**
    * @Title: getOperateSettingForIllegalDepositServiceFeeRatio 
    * @Description: 违章代处理服务费比例
    * @author huanghua
    * @return
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForIllegalDepositServiceFeeRatio();

    /**
    * @Title: getOperateSettingForHandover 
    * @Description: 交接车延时参数
    * @author huanghua
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForHandover();
    
    /**
    * @Title: getOperateSettingForAbout 
    * @Description: 约车最短时长
    * @author huanghua
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForAbout();
    
    /**
    * @Title: getOperateSettingForDeliveryServiceInterva 
    * @Description: 送车服务区间
    * @author huanghua
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForDeliveryServiceInterva();
    
    /**
    * @Title: getOperateSettingForCarAhead 
    * @Description: 用车提前
    * @author huanghua
    * @return
    * @return OperateSetting
     */
    public OperateSetting getOperateSettingForCarAhead();
}
