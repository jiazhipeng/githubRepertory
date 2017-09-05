package cn.cuco.enums;

/**
* @ClassName: UserCarType 
* @Description: 用车类型
* @author huanghua
* @date 2017年2月28日 上午10:57:32
 */
public enum UserCarType {
	//任务类型 1:用户用车；2:用户换车；3:限号换车；',
	USERCARTYPE_USERUSECAR(1,"用户用车"),
	USERCARTYPE_USERCHANGE(2,"用户换车"),
	USERCARTYPE_RESTRICTION(3,"限号换车");


	private Integer index;
	
	private String name;

	private UserCarType(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(UserCarType status: UserCarType.values()){
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
