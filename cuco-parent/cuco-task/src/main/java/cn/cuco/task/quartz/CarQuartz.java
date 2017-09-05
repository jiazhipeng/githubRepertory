package cn.cuco.task.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cn.cuco.service.car.carInfo.CarService;

public class CarQuartz {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	 @Autowired
	 private CarService carService;
	 
	/**   
	 * @Title: createCarUsedLog   
	 * @Description: 每20分钟自动生成车辆使用日志  
	 * @return: void      
	 */
	@Scheduled(cron = "0 0/20 * * * ?" )
	public void createCarUsedLog(){
		this.logger.info("自动生成车辆使用日志开始--");
		this.carService.createCarUsedLog();
		this.logger.info("自动生成车辆使用日志结束--");
	}
	
	/**   
	 * @Title: updateCar2Reorganized   
	 * @Description: 4小时还没有整备完成的车辆自动整备完成
	 * @param:       
	 * @return: void      
	 */
	@Scheduled(cron = "0 0/30 * * * ?" )
	public void updateCar2Reorganized(){
		this.logger.info("自动整备完成，将车辆状态变为待分配开始--");
		this.carService.updateCarByReorganizedBySystem();
		this.logger.info("自动整备完成，将车辆状态变为待分配结束--");
	}
}
