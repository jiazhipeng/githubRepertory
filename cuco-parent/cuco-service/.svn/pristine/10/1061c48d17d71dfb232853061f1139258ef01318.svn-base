package cn.cuco.enums;

/** 
* @ClassName: CarViolationEnum 
* @Description: 车辆违章枚举类
* @author gongbw
* @date 2017年3月7日 上午11:17:53  
*/
public class CarViolationEnum {

	
	/** 
	* @ClassName: status 
	* @Description: 车辆违章状态 
	* @author gongbw
	* @date 2017年3月7日 上午11:21:23  
	*/
	public enum status{
		//违章处理状态（0：待确认；1：未处理；2：已处理）
		WAITECONFIRM(0,"待确认"),
		PROCESSING(1,"未处理"),
		PROCESSED(2,"已处理");
		
		private Integer index;
		
		private String name;

		private status(Integer index, String name) {
			this.index = index;
			this.name = name;
		}
		
		public static  String getName(Integer targetIndex){
			if(null == targetIndex)
				return "";
			for(status status: status.values()){
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
	* @ClassName: dealType 
	* @Description: 车辆违章处理类型
	* @author gongbw
	* @date 2017年3月7日 上午11:21:41  
	*/
	public enum dealType{
		//处理方式（0：自行处理；1：cuco代处理）
		DEALWITHOWN(0,"自行处理"),
		DEALWITHCUCO(1,"cuco代处理");
		
		private Integer index;
		
		private String name;

		private dealType(Integer index, String name) {
			this.index = index;
			this.name = name;
		}
		
		public static  String getName(Integer targetIndex){
			if(null == targetIndex)
				return "";
			for(dealType status: dealType.values()){
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
