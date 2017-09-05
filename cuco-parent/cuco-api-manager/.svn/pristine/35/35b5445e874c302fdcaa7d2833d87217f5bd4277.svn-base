package cn.cuco.controller.manager.carUsed;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.cuco.annotation.API;
import cn.cuco.entity.OrderCarUsed;
import cn.cuco.service.order.OrderCarUsedService;


/** 
 * @ClassName: OrderCarUsedController 
 * @Description: 用车日结算明细表
 * @author: wangchuntao 
 * @date: 2017年2月22日 下午5:47:38  
 */
@RestController
public class OrderCarUsedController {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	OrderCarUsedService orderCarUsedService;
	/**
	 * @Title: getOrderCarUsedListByPage 
	 * @Description: 用车日结算明细列表
	 * @author: wangchuntao 
	 * @param orderCarUsed
	 * @return Object
	 * @date: 2017年2月23日 下午4:13:11
	 */
    @API(value="用车日结算明细列表")
    @RequestMapping(value = "/orderCarUsed/getOrderCarUsedListByPage",method = RequestMethod.POST)
    public Object getOrderCarUsedListByPage(@RequestBody OrderCarUsed orderCarUsed){
        return orderCarUsedService.getOrderCarUsedListByPage(orderCarUsed);
    }
}
