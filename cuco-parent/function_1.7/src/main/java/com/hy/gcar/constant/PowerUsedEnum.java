package com.hy.gcar.constant;

public class PowerUsedEnum {

	 public enum status{
		 /**
		  * 无换车
		  */
		 NO(0),
		 /**
		  * 有换车
		  */
		 YES(1);
		private final int value;

		status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	 }
}
