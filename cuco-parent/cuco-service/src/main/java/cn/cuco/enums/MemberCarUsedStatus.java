package cn.cuco.enums;

/**
* @ClassName: MemberCarUsedStatus 
* @Description: 用车状态枚举
* @author huanghua
* @date 2017年2月28日 下午5:20:34
 */
//用车的状态 0:待送车;1:使用中; 2:已完成; 3:已取消;4:已失联;5:发起用车;6:确认用车;
public enum MemberCarUsedStatus {
	MEMBERCARUSED_WAITSENDCAR(0,"待送车"),
	MEMBERCARUSED_USING(1,"使用中"),
	MEMBERCARUSED_COMPLETE(2,"已完成"),
	MEMBERCARUSED_CANCEL(3,"已取消"),
	MEMBERCARUSED_LOSED(4,"已失联"),
	MEMBERCARUSED_CREATE(5,"发起用车"),
	MEMBERCARUSED_CONFIRMUSE(6,"确认用车");

	private Integer index;
	
	private String name;

	private MemberCarUsedStatus(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(MemberCarUsedStatus status: MemberCarUsedStatus.values()){
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
