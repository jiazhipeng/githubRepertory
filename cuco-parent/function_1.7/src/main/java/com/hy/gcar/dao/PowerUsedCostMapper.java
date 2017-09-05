package com.hy.gcar.dao;

import com.hy.gcar.entity.PowerUsedCost;

public interface PowerUsedCostMapper<T> extends BaseDao<T> {

	void updateCancelCarSubmit(PowerUsedCost usedCost);

}
