package cn.cuco.enums;

/**
* @ClassName: ButlerTaskType 
* @Description: 任务类型枚举
* @author huanghua
* @date 2017年2月28日 上午9:49:51
 */
public enum ButlerTaskType {
	//任务类型 1:充电任务；2:取车任务；3:送车任务；',
	CAROPERATESTATUS_CHARGING(1,"充电任务"),
	CAROPERATESTATUS_TASKCAR(2,"取车任务"),
	CAROPERATESTATUS_SENDCAR(3,"送车任务");


	private Integer index;
	
	private String name;

	private ButlerTaskType(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(ButlerTaskType status: ButlerTaskType.values()){
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
