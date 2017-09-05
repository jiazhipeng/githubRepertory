package com.hy.security.service.nation;


import com.hy.security.entity.Nation;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <Nation>
 * @since 1.0,2016-08-05 15:24:23
 */
public interface NationService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tcNation
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(Nation tcNation) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tcNation
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<Nation> tcNation) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tcNation
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tcNation
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tcNation
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(Nation tcNation);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tcNation
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Nation findById(Long id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tcNation
    *@返回值 List<Nation> 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public List<Nation> selectByCondition(Nation tcNation);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tcNation
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月05日 15:24:23
    *@修改时间 2016年08月05日 15:24:23
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(Nation tcNation);

    /**
     * 查询所有城市
    * @Title: selectAllCity 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param nation
    * @param @return    设定文件 
    * @return List<Nation>    返回类型 
    * @throws
     */
    public List<Nation> selectAllCity(Nation nation);

}
