package cn.cuco.enums;

/**
* @ClassName: CarRepairStatus 
* @Description: TODO
* @author huanghua
* @date 2017年2月27日 上午10:35:17
 */
//0：待处理；1：跟进中；2：待赔付；3：已完成
public enum CarAccidentStatus {
	CARACCIDENT_WAIT(0,"待处理"),
	CARACCIDENT_FOLLOWUP(1,"跟进中"),
	CARACCIDENT_REPAIRCOMPLETED(2,"维修完成"),
	CARACCIDENT_COMPLETE(3,"已完成");

	private Integer index;
	
	private String name;

	private CarAccidentStatus(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(CarAccidentStatus status: CarAccidentStatus.values()){
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
