/**   

 * @Title: StoreVo.java 
 * @Prject: cuco-api-manager
 * @Package: cn.cuco.controller.entity 
 * @Description: TODO
 * @author: wangchuntao 
 * @date: 2017年2月27日 下午5:41:42 
 * @version: V1.0   
 */
package cn.cuco.controller.entity;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName: StoreVo 
 * @Description: 库存
 * @author: wangchuntao 
 * @date: 2017年2月27日 下午5:41:42  
 */
public class StoreVO implements Serializable{
	
	Long carportId;
	Long carTypeId;
	Date startDay;
	Date endDay;
	
	
	public Long getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}
	public Long getCarportId() {
		return carportId;
	}
	public void setCarportId(Long carportId) {
		this.carportId = carportId;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	
}
