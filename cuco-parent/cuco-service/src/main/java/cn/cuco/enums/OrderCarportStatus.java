package cn.cuco.enums;

/** 
* @ClassName: OrderCarportStatus 
* @Description: 解锁订单状态枚举
* @author zc.du
* @date 2017年2月23日 下午2:51:58  
*/
public enum OrderCarportStatus {
	
	WAIT_PAY(0,"待付款"),
	PAYING(1,"支付中"),
	PART_PAY(2,"部分付款"),
	COMPLETE_PAY(3,"支付中"),
	CANCEL(4,"已取消");

	private Integer index;
	
	private String name;

	private OrderCarportStatus(Integer index, String name) {
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
