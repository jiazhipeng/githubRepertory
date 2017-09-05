package com.hy.gcar.service.coupon;


import com.hy.gcar.entity.CouponInfo;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author auto create
 * @param <CouponInfo>
 * @since 1.0,2016-09-12 18:22:55
 */
public interface CouponInfoService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdCouponInfo
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(CouponInfo tdCouponInfo) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdCouponInfo
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<CouponInfo> tdCouponInfo) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdCouponInfo
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
    *@param tdCouponInfo
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
    *@param tdCouponInfo
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(CouponInfo tdCouponInfo);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdCouponInfo
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public CouponInfo findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdCouponInfo
    *@返回值 List<CouponInfo> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public List<CouponInfo> selectByCondition(CouponInfo tdCouponInfo);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdCouponInfo
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 18:22:55
    *@修改时间 2016年09月12日 18:22:55
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(CouponInfo tdCouponInfo);

    /**
    * @Title: getCouponInfoCountByMemberId 
    * @Description: TODO(根据用户ID获取可使用优惠券列表(状态为待使用，审核中的券)) 
    * @param @param couponInfo
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws
     */
    public int getCouponInfoCountByMemberId(CouponInfo couponInfo);
    
    /**
    * @Title: getCouponInfoListByEnable 
    * @Description: TODO(根据用户ID获取可使用优惠券列表(用于前端兑换那里展现待使用以及审核中的券)) 
    * @param @param couponInfo
    * @param @return    设定文件 
    * @return List<CouponInfo>    返回类型 
    * @throws
     */
	public List<CouponInfo> getCouponInfoListByEnable(CouponInfo couponInfo);

	/**
	* @Title: removeCouponInfoByOutTime 
	* @Description: TODO(清除用户已过期的优惠卷(前端一键清除)) 
	* @param @param couponInfo
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public void removeCouponInfoByOutTime(CouponInfo couponInfo);
	
	/**
	 * 通过会员ID以及套餐获取用户可用的优惠券列表
	 * @param couponInfo
	 * @return
	 */
	public List<CouponInfo> getCouponInfoByMemberIdAndItemId(CouponInfo couponInfo);

}
