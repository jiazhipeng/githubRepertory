package cn.cuco.controller.manager.basic.operateSetting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.cuco.annotation.API;
import cn.cuco.common.httpservice.HttpServiceContext;
import cn.cuco.common.utils.PrePersistUtils;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.entity.OperateSetting;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.business.OperateSettingService;


/**
 * @ClassName: OperateSettingController 
 * @Description: 系统参数管理
 * @author: wangchuntao 
 * @date: 2017年2月23日 上午10:22:09
 */
@RestController
public class OperateSettingController {
	protected Logger logger = Logger.getLogger(this.getClass());
    
	@Autowired
	OperateSettingService operateSettingService;
    /**
     * @Title: getOperateSettingList 
     * @Description: 参数列表
     * @author: wangchuntao 
     * @param operateSetting
     * @return Object
     * @date: 2017年2月23日 上午10:24:10
     */
	@API(value="系统参数列表")
    @RequestMapping(value = "/operateSetting/getOperateSettingList",method = RequestMethod.GET)
    public Object getOperateSettingList(Integer type){
		ParamVerifyUtils.paramNotNull(type);
//		参数类型 1：费用管理；2：参数管理
		OperateSetting operateSetting = new OperateSetting();
		PageResult<OperateSetting> pageResult =null;
		if(type==1){
			pageResult =operateSettingService.getOperateSettingCostByPage(operateSetting);
		}else{
			pageResult =operateSettingService.getOperateSettingParameterByPage(operateSetting);
		}
        return pageResult;
    }
    
    /**
     * @Title: updateOperateSetting 
     * @Description: 修改参数设置
     * @author: wangchuntao 
     * @param carType
     * @return 
     * @return Object
     * @date: 2017年2月23日 上午10:28:26
     */
	@API(value="修改参数设置")
    @RequestMapping(value = "/operateSetting/updateOperateSetting",method = RequestMethod.POST)
    public Object updateOperateSetting(@RequestBody OperateSetting operateSetting){
		PrePersistUtils.onModify(operateSetting, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return operateSettingService.updateOperateSetting(operateSetting);
    }
    
}
