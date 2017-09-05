package com.hy.gcar.service.getmoney;


import com.hy.gcar.entity.GetmoneyApply;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <GetmoneyApply>
 * @since 1.0,2016-11-03 17:02:16
 */
public interface GetmoneyApplyService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdGetmoneyApply
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(GetmoneyApply tdGetmoneyApply) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdGetmoneyApply
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<GetmoneyApply> tdGetmoneyApply) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdGetmoneyApply
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdGetmoneyApply
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdGetmoneyApply
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(GetmoneyApply tdGetmoneyApply);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdGetmoneyApply
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public GetmoneyApply findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdGetmoneyApply
    *@返回值 List<GetmoneyApply> 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public List<GetmoneyApply> selectByCondition(GetmoneyApply tdGetmoneyApply);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdGetmoneyApply
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 17:02:16
    *@修改时间 2016年11月03日 17:02:16
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(GetmoneyApply tdGetmoneyApply);
    /**
     * 查询权益下进行中的提现记录 
     * @param getmoneyApply
     * @return
     */
	public List<GetmoneyApply> selectMoneyApplyOfUnfinished(GetmoneyApply getmoneyApply);


}
