package cn.cuco.controller.manager.basic.carport;

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
import cn.cuco.entity.Carport;
import cn.cuco.httpservice.ResponseUtil;
import cn.cuco.service.basic.carport.CarportService;


/**
 * @ClassName: CarportController 
 * @Description: 车库控制器
 * @author: wangchuntao 
 * @date: 2017年2月23日 上午9:58:35
 */
@RestController
public class CarportController {
	protected Logger logger = Logger.getLogger(this.getClass());
    
	@Autowired
	CarportService carportService;
    /** 
     * @Title: getCarportListByPage 
     * @Description: 车库列表
     * @author: wangchuntao 
     * @param carport
     * @return Object
     * @date: 2017年2月23日 上午9:55:02
     */
	@API(value="车库列表")
    @RequestMapping(value = "/carport/getCarportListByPage",method = RequestMethod.POST)
    public Object getCarportListByPage(Carport carport){
        return carportService.getCarportByPage(carport);
    }
    
    /** 
     * @Title: createCarport 
     * @Description: 新建车库
     * @author: wangchuntao 
     * @param carport
     * @return Object
     * @date: 2017年2月23日 上午9:55:23
     */
	@API(value="新建车库")
    @RequestMapping(value = "/carport/createCarport",method = RequestMethod.POST)
    public Object createCarport(@RequestBody Carport carport){
		PrePersistUtils.onCreate(carport, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return carportService.createCarport(carport);
    }
    
    /** 
     * @Title: getCarportDetail 
     * @Description: 查看详情
     * @author: wangchuntao 
     * @param id
     * @return Object
     * @date: 2017年2月23日 上午9:55:49
     */
    @API(value="查看车库详情")
    @RequestMapping(value = "/carport/getCarportDetail",method = RequestMethod.GET)
    public Object getCarportDetail(Long id){
    	return carportService.getCarportById(id);
    }
    
    /**
     * @Title: updateCarport 
     * @Description: 修改车库
     * @author: wangchuntao 
     * @param carport
     * @return Object
     * @date: 2017年2月23日 上午9:56:23
     */
    @API(value="修改车库")
    @RequestMapping(value = "/carport/updateCarport",method = RequestMethod.POST)
    public Object updateCarport(@RequestBody Carport carport){
    	PrePersistUtils.onModify(carport, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	return carportService.updateCarport(carport);
    }
    
    /**
     * @Title: updateCarportShelves 
     * @Description: 车库上下架
     * @author: wangchuntao 
     * @param carport
     * @return Object
     * @date: 2017年2月27日 下午5:12:51
     */
    @API(value="车库上下架")
    @RequestMapping(value = "/carport/updateCarportShelves",method = RequestMethod.POST)
    public Object updateCarportShelves(@RequestBody Carport carport){
    	
//    	valid;//上下架 0：下架；1：上架
    	ParamVerifyUtils.paramNotNull(carport);
    	ParamVerifyUtils.paramNotNull(carport.getValid());
    	PrePersistUtils.onModify(carport, HttpServiceContext.getCurrentUserId(), HttpServiceContext.getCurrentUserName());
    	if(carport.getValid()==1){
    		carportService.updateCarportShelves(carport);
    	}else{
    		carportService.updateCarportTheShelves(carport);
    	}
    	return ResponseUtil.toSuccessBody(null);
    }
   
    
    
}
