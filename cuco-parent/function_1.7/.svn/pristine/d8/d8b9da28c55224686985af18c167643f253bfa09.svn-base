package com.hy.gcar.constant;

public enum MemberSex {
	
	MEMBER_SEX_UNKNOWN(-1,"未知"),
	MEMBER_SEX_WOMAN(0,"女"),
	MEMBER_SEX_MAN(1,"男");
	
	MemberSex(int index,String name){
		this.index = index;
		this.name = name;
	}
	
	private int index;
	private String name;

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}
	
	public static String getSex(Integer index){
		if(index == null){
			return "";
		}
		
		for(MemberSex memberSex: MemberSex.values()){
			if (memberSex.getIndex() == index.intValue()){
				return memberSex.getName();
			}
		}
		return "";
		
	}
	
	
	
}
