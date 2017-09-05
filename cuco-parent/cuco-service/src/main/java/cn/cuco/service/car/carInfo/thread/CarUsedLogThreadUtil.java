package cn.cuco.service.car.carInfo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* @ClassName: CarUsedLogThreadUtil 
* @Description: 车辆使用日志的线程池，定义为1000
* @author gongbw
* @date 2017年2月27日 下午3:41:10  
*/
public class CarUsedLogThreadUtil {
	
	public static  ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
}
