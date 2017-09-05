package cn.cuco.enums;

/** 
* @ClassName: AccountOperateType 
* @Description: 账户操作类型
* @author zc.du
* @date 2017年2月23日 下午2:51:58  
*/
public enum AccountOperateType {
	
	UNLOCK_CARPORT(1,"解锁车库"),
	ORDER_RENEWAL(2,"账户续费"),
	MANUAL_DEDUCTION(3,"系统补扣"),
	SYSTEM_DEDUCTION(4,"系统扣费"),
	MANUAL_RENEWAL(5,"系统补回"),
	SYSTEM_BACK(6,"系统退回"),
	FROZEN_DEPOSIT(7,"押金冻结")
	;

	private Integer index;
	
	private String name;

	private AccountOperateType(Integer index, String name) {
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
