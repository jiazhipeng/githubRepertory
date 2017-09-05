package com.hy.gcar.constant;

public enum MemberType {

	MEMBER_TYPE_0(0,"个人会员"),
	MEMBER_TYPE_1(1,"企业会员");
	
	MemberType(int index,String name) {
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
	
	
	public static String getMemberType(Integer index){
		if(index == null){
			return "";
		}	
		for(MemberType memberType: MemberType.values()){
			if(memberType.getIndex() == index.intValue()){
				return memberType.getName();
			}
		}
		return "";	
	}
	
	
}
