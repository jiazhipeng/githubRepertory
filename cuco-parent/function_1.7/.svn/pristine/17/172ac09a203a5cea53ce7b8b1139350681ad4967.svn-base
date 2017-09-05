package com.hy.gcar.dao;

import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.PowerUsed;

import java.util.List;

public interface MemberItemCartypeMapper<T> extends BaseDao<T> {

	/**根据权益ID删除字表信息
     * @param memberItemCartype
     * @return
     */
    public Integer deleteByMemberItemId(MemberItemCartype memberItemCartype);
    /**
     * 修改我的启用车型
     * @param powerUsed
     */
	public void updateMemberItemCartype(PowerUsed powerUsed);
	/**
	 * 修改我的启用车型状态
	 * @param powerUsed
	 */
	public void updateMyCarTypeStatus(PowerUsed powerUsed);

	/**
	 * 根据会员id 和权益id 查询 启用和非启用的车型
	 * @param memberItem
	 * @return
	 */
    List<MemberItemCartype> findEnableAndNOnEnableCarType(MemberItem memberItem);

	/**
	 * 根据会员id 和权益id 查询 启用车型
	 * @param memberItem
	 * @return
	 */
    List<MemberItemCartype> findEnableCarType(MemberItem memberItem);
    
    /**
     * 取车完成将权益车位状态变为待使用，清空车位
     * @param memberItemCartype
     */
    public void updateMemberItemCartypeByGetCarComplete(MemberItemCartype memberItemCartype);
}
