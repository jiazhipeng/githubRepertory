package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.MemberCarUsedAddress;

public interface MemberCarUsedAddressMapper<T> extends BaseDao<T> {
	
	/** 
	* @Title: getMemberCarUsedAddressListWithSort 
	* @Description: 获取用户用车地址(带排序)
	* @author zc.du
	* @param memberCarUsedAddress
	* @return List<MemberCarUsedAddress>
	*/
	public List<MemberCarUsedAddress> getMemberCarUsedAddressListWithSort(MemberCarUsedAddress memberCarUsedAddress); 
}
