package com.hy.gcar.dao;

import com.hy.gcar.entity.MemberItem;

public interface MemeberItemMapper<T> extends BaseDao<T> {

	MemberItem selectMyPowerList(MemberItem memberItem);

}
