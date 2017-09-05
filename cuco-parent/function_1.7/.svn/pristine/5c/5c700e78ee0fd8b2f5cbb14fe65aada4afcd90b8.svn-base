package com.hy.gcar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hy.gcar.entity.CarType;

public interface CarTypeMapper<T> extends BaseDao<T> {

	List<CarType> selectCarTypes(@Param("carType")String carType);

	List<CarType> selectByItemId(Long itemId);


    List<CarType> selectCarTypeByIds(List<Long> carTypeIds);
}
