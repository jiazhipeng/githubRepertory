package com.hy.gcar.service.item;


import java.util.List;

import com.hy.gcar.entity.Item;

/**
 * 
 * @author auto create
 * @param <Item>
 * @since 1.0,2016-08-11 16:59:56
 */
public interface ItemService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(Item tdItem) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<Item> tdItem) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(Item tdItem);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdItem
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Item findById(Object id);

    /**查询会员成长列表
     * @param item
     * @return
     */
    public List<Item> selectByCondition(Item item);
   
    
    
    /**
     * 查询套餐最大启用车辆数
     * @return
     */
    public Integer selectMaxEnableCount();
    
    /**
     * 查询满足条件套餐的所有车型
     * @return
     */
    public List<Item> selectCarTypeList(Item item);
    
}
