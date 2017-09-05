package cn.cuco.enums;

/** 
* @ClassName: OrderType 
* @Description: 订单类型
* @author zc.du
* @date 2017年2月23日 下午2:51:58  
*/
public enum PreAuthorizedDebitType {
	
	USEVOUCHER(0,"预授权用车押金"),
	VIOLATIONVOUCHER(1,"预授权违章押金");

	private Integer index;
	
	private String name;

	private PreAuthorizedDebitType(Integer index, String name) {
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
