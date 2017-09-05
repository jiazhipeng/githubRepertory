package com.hy.gcar.constant;

public class CarOperateEnum {

	/**
	 * 车辆状态  只使用  待分配，维修中 ，整备中
	* description：    车辆预约运营计划表 中的 operate_status车辆状态中 包含 
	*  会员待使用，平台待使用，会员使用中，平台使用中，状态
	* author：JIAZHIPENG    
	* time：2016-9-23 下午3:02:44    
	* 修改时间：2016-9-23 下午3:02:44    
	* 修改备注：
	 */
	 public enum status{
		 /**
		  * 待分配
		  */
		 STAY(1,"待分配"),
		 /**
		  * 会员待使用
		  */
		 MEMBERUSE(2,"会员待使用"),
		 /**
		  * 平台待使用
		  */
		 PLATFORMUSE(3,"平台待使用"),
		 /**
		  * 会员使用中
		  */
		 MEMBERUSEING(4,"会员使用中"),
		 /**
		  * 平台使用中
		  */
		 PLATFORMUSEING(5,"平台使用中"),
		 /**
		  * 维修中
		  */
		 REPAIRSING(6,"维修中"),
		 /**
		  * 整备中
		  */
		 READINESSING(7,"整备中"),
		 /**
		  * 失联
		  */
		 OUTOFCONTACT(8,"失联"),
		 /**
		  * 预约
		  */
		 APPOINTMENT(9,"签约");
	
	      private final int value;
		  private String key;
	      status(int value,String key) {
	          this.value = value;
			  this.key = key;
	      }
	      
	      public int getValue() {
	          return value;
	      }

	      public String getKey(){
	      	return this.key;
		  }

		  public static  String getKey(Integer value){
		  	if(value == null){
		  		return "";
			}
			for(CarOperateEnum.status s:CarOperateEnum.status.values()){
                if(s.getValue() == value.intValue()){
                    return s.getKey();
                }
			}
			return "";
		  }
	 }
}
