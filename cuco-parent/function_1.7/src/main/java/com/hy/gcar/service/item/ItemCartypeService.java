package com.hy.gcar.service.item;


import com.hy.gcar.entity.Item;
import com.hy.gcar.entity.ItemCartype;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <ItemCartype>
 * @since 1.0,2016-08-12 10:02:07
 */
public interface ItemCartypeService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdItemCartype
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(ItemCartype tdItemCartype) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdItemCartype
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<ItemCartype> tdItemCartype) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdItemCartype
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdItemCartype
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdItemCartype
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(ItemCartype tdItemCartype);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdItemCartype
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public ItemCartype findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdItemCartype
    *@返回值 List<ItemCartype> 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:07
    *@修改时间 2016年08月12日 10:02:07
    *@版本 V1.0
    *@异常
    **/
    public List<ItemCartype> selectByCondition(ItemCartype tdItemCartype);

    /**
     * @方法名: selectCountByCondition
     * @方法描述: 分条件查询总数
     * @param t
     * @return
     * @返回值 List 返回类型
     * @作者：q.p.x @创建时间 2015年3月13日 上午12:59:39
     * @修改时间 2015年3月13日 上午12:59:39
     * @版本 V1.0
     * @异常
     */
    public Integer selectCountByCondition(ItemCartype tdItemCartype);
    /**
     * 根据车型id查询所属套餐
     * @param carTypeId
     * @return
     */
	public List<ItemCartype> selectByCarTypeId(Long carTypeId);


	/**
	 * 根据套餐ID查询当前套餐下的最低日使用费
	 * @return
	 */
	public BigDecimal selectMinimumPrice(ItemCartype tdItemCartype);

}
