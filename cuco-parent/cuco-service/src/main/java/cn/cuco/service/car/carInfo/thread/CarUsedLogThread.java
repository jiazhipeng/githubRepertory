package cn.cuco.service.car.carInfo.thread;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.cuco.entity.Car;
import cn.cuco.service.car.carInfo.CarService;

/** 
* @ClassName: CarUsedLogThread 
* @Description: 处理车辆日志
* @author gongbw
* @date 2017年2月27日 下午3:39:39  
*/
public class CarUsedLogThread implements Runnable{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private Car car;
	
	public CarUsedLogThread(Car car){
		this.car = car;
	}

	@Autowired
    private CarService carService;
    
	@Override
	public void run() {
		this.logger.info("传入的车辆信息为---" + JSONObject.toJSONString(car));
		try {
			//根据车辆ID获取该车辆最近的一条车辆使用日志
			this.carService.createCarUsedLogByCar(car);
		} catch (Exception e) {
			logger.error("车辆使用日志处理异常--",e);
			e.printStackTrace();
		}finally {
			car = null;
		}
		
	}
	
}
