package cn.cuco.enums;

/** 
* @ClassName: CarStatusEnum 
* @Description: 车辆枚举类
* @author gongbw
* @date 2017年2月23日 下午1:32:22  
*/
public class CarEnum {

	/** 
	* @ClassName: CarStatus 
	* @Description: 车辆状态的枚举
	* @author gongbw
	* @date 2017年2月23日 下午1:33:57  
	*/
	public enum CarStatus{
		//车辆状态（0：待分配；1:已分配；2：会员使用中；3：非会员使用中；4：维修中；5：待整备；6：整备中；7：已失联）
		WAITDISTRIBUTE(0,"待分配"),
		DISTRIBUTED(1,"已分配"),
		MEMBERUSING(2,"会员使用中"),
		NOTMEMBERUSING(3,"非会员使用中"),
		REPAIRING(4,"维修中"),
		WAITREORGANIZE(5,"待整备"),
		REORGANIZING(6,"整备中"),
		LOSED(7,"已失联");
		
		private Integer index;
		
		private String name;

		private CarStatus(Integer index, String name) {
			this.index = index;
			this.name = name;
		}
		
		public static  String getName(Integer targetIndex){
			if(null == targetIndex)
				return "";
			for(CarStatus status: CarStatus.values()){
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
	
	/** 
	* @ClassName: CarInsuranceUrlType 
	* @Description: 车辆保险图片类型枚举
	* @author gongbw
	* @date 2017年2月23日 下午1:54:18  
	*/
	public enum CarAttachmentType{
		//图片类型（0：交强险；1：商业险；3：违章凭证）
		COMPULSORY_INSURANCE_URL(0,"交强险"),
		BUSINESS_INSURANCE_URL(1,"商业险"),
		VIOLATION_URL(2,"违章凭证");
		
		private Integer index;
		
		private String name;

		private CarAttachmentType(Integer index, String name) {
			this.index = index;
			this.name = name;
		}
		
		public static  String getName(Integer targetIndex){
			if(null == targetIndex)
				return "";
			for(CarAttachmentType status: CarAttachmentType.values()){
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
