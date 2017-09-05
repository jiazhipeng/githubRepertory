package cn.cuco.enums;

/**
* @ClassName: CarRepairStatus 
* @Description: TODO
* @author huanghua
* @date 2017年2月27日 上午10:35:17
 */
//0：待维修；1：维修中；2：维修完成
public enum CarRepairStatus {
	CARREPAIR_WAIT(0,"待维修"),
	CARREPAIR_IN(2,"维修中"),
	CARREPAIR_COMPLETE(3,"维修完成");

	private Integer index;
	
	private String name;

	private CarRepairStatus(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(CarRepairStatus status: CarRepairStatus.values()){
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
