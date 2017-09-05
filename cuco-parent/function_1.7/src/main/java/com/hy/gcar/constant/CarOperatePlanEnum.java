package com.hy.gcar.constant;

public class CarOperatePlanEnum {

	public enum status {
		/**
		 * 待执行
		 */
		STAY(1,"待执行"),
		/**
		 * 执行中
		 */
		USEING(2,"执行中"),
		/**
		 * 已完成
		 */
		SUCCESS(3,"已完成"),
		/**
		 * 已取消
		 */
		CANCLE(4,"已取消"),
		/**
		 * 已终止
		 */
		TERMINATION(5,"已终止");


		status(int value,String name){
			this.value = value;
			this.name = name;
		}
		private final int value;

		private String name;

		status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static String getName(Integer value){
			if(value == null){
				return  "";
			}

			for(status s:CarOperatePlanEnum.status.values()){
				if(s.value == value.intValue()){
					return s.name;
				}
			}
			return "";
		}
	}

	public enum handle {
		/**
		 * 用户继续使用
		 */
		use(1),
		/**
		 * 车辆入库
		 */
		storage(2);

		private final int value;

		handle(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	/**
	 * 计算  所用 枚举，  --业务
	* description：    
	* author：JIAZHIPENG    
	* time：2016-9-19 下午1:57:38    
	* 修改时间：2016-9-19 下午1:57:38    
	* 修改备注：
	 */
	public enum algorithms{
		/**
		 * 加法
		 */
		add(1),
		/**
		 * 减法
		 */
		subtract(2);
		private final int value;

		algorithms(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	public enum wapType{
		/**
		 * 用
		 */
		use(1),
		/**
		 * 障
		 */
		bad(2),
		/**
		 * 整
		 */
		READINESSING(3),
		/**
		 * 预
		 */
		reserved(4),
		/**
		 * 失联
		 */
		OutOfContact(5),
		/**
		 * 预约
		 */
		APPOINTMENT(6);
		
		private final int value;

		wapType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
