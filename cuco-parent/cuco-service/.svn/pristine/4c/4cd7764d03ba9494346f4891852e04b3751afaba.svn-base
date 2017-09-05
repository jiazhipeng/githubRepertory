package cn.cuco.enums;

/** 
* @ClassName: MemberStatus 
* @Description: 用户车库状态枚举
* @author zc.du
* @date 2017年2月23日 下午2:51:58  
*/
public enum MemberCarportStatus {
	
	LOCK(0,"未解锁"),
	UNLOCK_IN(1,"解锁中"),
	UNLOCKED(2,"已解锁"),
	DISABLE(3,"已停用");

	private Integer index;
	
	private String name;

	private MemberCarportStatus(Integer index, String name) {
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
