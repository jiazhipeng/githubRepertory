package com.hy.gcar.service.carType;


import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.PowerUsed;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <MemberItemCartype>
 * @since 1.0,2016-08-12 17:45:07
 */
public interface MemberItemCartypeService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdMemberItemCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(MemberItemCartype tdMemberItemCartype) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdMemberItemCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<MemberItemCartype> tdMemberItemCartype) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdMemberItemCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdMemberItemCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdMemberItemCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(MemberItemCartype tdMemberItemCartype);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdMemberItemCartype
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public MemberItemCartype findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdMemberItemCartype
    *@返回值 List<MemberItemCartype> 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public List<MemberItemCartype> selectByCondition(MemberItemCartype tdMemberItemCartype);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdMemberItemCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月12日 17:45:07
    *@修改时间 2016年08月12日 17:45:07
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(MemberItemCartype tdMemberItemCartype);

    /**根据权益ID删除字表信息
     * @param memberItemCartype
     * @return
     */
    public Integer deleteByMemberItemId(MemberItemCartype memberItemCartype);
	/**
	 * 修改我的启用车型
	 * @param powerUsed
	 */
	public void updateMemberItemCartype(PowerUsed powerUsed);
	/**
	 * 修改我的启用车型为 已经发起用车
	 * @param powerUsed
	 */
	public void updateMyCarTypeStatus(PowerUsed powerUsed);


    /**
     * 根据用户id 和权益id 查询用户所购买的启用和非启用车型
     * @param memberItem
     * @return
     */
    public  List<MemberItemCartype> findEnableAndNOnEnableCarType(MemberItem memberItem);

    /**
     * 根据用户id 和权益id 查询用户所购买的启用和非启用车型
     * @param memberItem
     * @return
     */
    public  List<MemberItemCartype> findEnableCarType(MemberItem memberItem);
    
    /**
     * 取车完成，需要将权益车型变成空。权益车位状态变成待使用
     * @param powerUsed
     */
    public  void updateMemberItemCartypeByGetCarComplete(MemberItemCartype memberItemCartype);
}
