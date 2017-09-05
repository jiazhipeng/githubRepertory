package cn.cuco.enums;

/** 
* @ClassName: PaymentType 
* @Description: 支付方式(渠道)
* @author zc.du
* @date 2017年2月23日 下午2:51:58  
*/
public enum PaymentType {
	
	JIDONG(0,"京东"),
	WECHAT(1,"微信"),
	ZHIFUBAO(2,"支付宝");

	private Integer index;
	
	private String name;

	private PaymentType(Integer index, String name) {
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
