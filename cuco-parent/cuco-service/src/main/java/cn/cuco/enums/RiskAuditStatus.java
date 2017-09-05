package cn.cuco.enums;

/** 
* @ClassName: MemberStatus 
* @Description: 风控状态枚举
* @author zc.du
* @date 2017年2月23日 下午2:51:58  
*/
public enum RiskAuditStatus {
	
	UNCOMMITTED(0,"未提交资料"),
	SYSTEM_PASS(1,"系统审核通过"),
	SYSTEM_REFUSE(2,"系统审核拒绝"),
	WAIT_MANUAL(3,"等待人工审核"),
	SYSTEM_FAILURE(4,"系统审核失败"),
	MANUAL_PASS(5,"人工审核通过"),
	MANUAL_REFUSE(6,"人工审核拒绝");

	private Integer index;
	
	private String name;

	private RiskAuditStatus(Integer index, String name) {
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
