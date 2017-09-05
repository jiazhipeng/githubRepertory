package cn.cuco.controller.manager.store;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.cuco.annotation.API;
import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.controller.entity.StoreVO;
import cn.cuco.service.car.stock.StockService;

/** 
 * @ClassName: StoreController 
 * @Description: 库存管理
 * @author: wangchuntao 
 * @date: 2017年2月22日 下午6:01:24  
 */
@RestController
public class StoreController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	StockService stockService;
	/**
	 * @Title: searchDateStore 
	 * @Description: TODO
	 * @author: wangchuntao 
	 * @param carportId 库存id
	 * @param startDay 今天
	 * @param endDay 月末
	 * @return 
	 * @return Object
	 * @date: 2017年2月27日 下午5:21:24
	 */
	@API(value="查询库存")
	@RequestMapping( value="/store/getRepertoryByDay" ,method=RequestMethod.POST)
	public Object getRepertoryByDay(@RequestBody StoreVO storeVo){
		ParamVerifyUtils.paramNotNull(storeVo);
		ParamVerifyUtils.paramNotNull(storeVo.getStartDay(),"开始时间");
		ParamVerifyUtils.paramNotNull(storeVo.getEndDay(),"结束时间");
//		ParamVerifyUtils.paramNotNull(storeVo.getCarTypeId());
		
		return stockService.getRepertoryByDayForCarType(storeVo.getCarTypeId(), storeVo.getStartDay(), storeVo.getEndDay());
	}
	
}
