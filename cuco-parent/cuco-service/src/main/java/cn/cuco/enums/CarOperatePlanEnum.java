package cn.cuco.enums;

/** 
* @ClassName: CarOperatePlanEnum 
* @Description: 车辆运营计划枚举
* @author gongbw
* @date 2017年2月25日 下午1:02:02  
*/
public class CarOperatePlanEnum {

	/** 
	* @ClassName: CarOperatePlanStatus 
	* @Description: 车辆运营计划状态的枚举
	* @author gongbw
	* @date 2017年2月25日 下午1:02:44  
	*/
	public enum CarOperatePlanStatus{
		//运营状态（0:待执行；1:执行中；2:已完成；3:已取消；）
		WAITEXECUTE(0,"待执行"),
		EXECUTING(1,"执行中"),
		COMPELETE(2,"已完成"),
		CANCEL(3,"已取消");
		
		private Integer index;
		
		private String name;

		private CarOperatePlanStatus(Integer index, String name) {
			this.index = index;
			this.name = name;
		}
		
		public static  String getName(Integer targetIndex){
			if(null == targetIndex)
				return "";
			for(CarOperatePlanStatus status: CarOperatePlanStatus.values()){
				if(status.getIndex().intValue() == targetIndex.intValue()){
					return status.getName();
				}
			}
			return "";
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
