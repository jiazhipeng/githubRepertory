package cn.cuco.enums;

/** 
* @ClassName: CarOperatePlanEnum 
* @Description: 车辆运营计划枚举
* @author gongbw
* @date 2017年2月25日 下午1:02:02  
*/
public class CarUsedLogTypeEnum {

	/** 
	* @ClassName: type 
	* @Description: 车辆使用日志枚举
	* @author gongbw
	* @date 2017年2月25日 下午1:02:44  
	*/
	public enum type{
		//车辆运营类型（0：闲置；1：会员使用中；2：非会员使用中；3：维修中；4：整备中；5：失联中；6：其他）
		LEAVING(0,"闲置"),
		MEMBERUSING(1,"会员使用中"),
		NOTMEMBERUSING(2,"非会员使用中"),
		REPAIRING(3,"维修中"),
		REORGANIZING(4,"整备中"),
		LOSING(5,"失联中"),
		OTHER(6,"其他");
		
		private Integer index;
		
		private String name;

		private type(Integer index, String name) {
			this.index = index;
			this.name = name;
		}
		
		public static  String getName(Integer targetIndex){
			if(null == targetIndex)
				return "";
			for(type status: type.values()){
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
