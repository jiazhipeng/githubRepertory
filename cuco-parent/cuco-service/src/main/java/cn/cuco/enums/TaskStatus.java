package cn.cuco.enums;

/**
* @ClassName: TaskStatus 
* @Description: 任务状态枚举
* @author huanghua
* @date 2017年2月21日 下午4:28:50
 */
//1：待处理 2：待接单；3:待送车；4:送车中；5:送车待取车；6:待取车；7:取车中；8:已到达；9:待入库；10:整备中；11:已完成；12：已失联；13:已取消；
public enum TaskStatus {
	TASKSTATUS_PROCESSED(1,"待处理"),
	TASKSTATUS_WAITACCEPT(2,"待接单"),
	TASKSTATUS_WAITSENDCAR(3,"待送车"),
	TASKSTATUS_SENDCAR(4,"送车中"),
	TASKSTATUS_SENDWAITTASKCAR(5,"送车待取车"),
	TASKSTATUS_WAITTASKCAR(6,"待取车"),
	TASKSTATUS_TASKCAR(7,"取车中"),
	TASKSTATUS_ARRIVE(8,"已到达"),
	TASKSTATUS_STORAGE(9,"待入库"),
	TASKSTATUS_INREADINESS(10,"整备中"),
	TASKSTATUS_COMPLETE(11,"已完成"),
	TASKSTATUS_LOSTCONTACT(12,"已失联"),
	TASKSTATUS_CANCEL(13,"已取消");
	
	private Integer index;
	
	private String name;

	private TaskStatus(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(TaskStatus status: TaskStatus.values()){
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
