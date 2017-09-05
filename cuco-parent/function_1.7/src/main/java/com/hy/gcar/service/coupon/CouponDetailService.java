package com.hy.gcar.service.coupon;


import com.hy.gcar.entity.CouponDetail;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <CouponDetail>
 * @since 1.0,2016-09-12 18:22:55
 */
public interface CouponDetailService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdCouponDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(CouponDetail tdCouponDetail) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdCouponDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<CouponDetail> tdCouponDetail) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdCouponDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdCouponDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdCouponDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(CouponDetail tdCouponDetail);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdCouponDetail
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public CouponDetail findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdCouponDetail
    *@返回值 List<CouponDetail> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public List<CouponDetail> selectByCondition(CouponDetail tdCouponDetail);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdCouponDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(CouponDetail tdCouponDetail);


}
