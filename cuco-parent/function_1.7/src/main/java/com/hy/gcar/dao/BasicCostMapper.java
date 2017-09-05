package com.hy.gcar.dao;

import com.hy.gcar.entity.BasicCost;

public interface BasicCostMapper<T> extends BaseDao<T> {
	BasicCost selectBasicCost();
}
