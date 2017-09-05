package com.hy.gcar.service.item;


import java.util.List;

import com.hy.gcar.entity.MemberItem;

/**
 * 
 * @author auto create
 * @param <MemberItem>
 * @since 1.0,2016-08-12 10:02:08
 */
public interface MemberItemService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdMemberItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public MemberItem insertSelective(MemberItem tdMemberItem) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdMemberItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<MemberItem> tdMemberItem) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdMemberItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdMemberItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdMemberItem
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public MemberItem updateByPrimaryKeySelective(MemberItem tdMemberItem);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdMemberItem
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public MemberItem findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdMemberItem
    *@返回值 List<MemberItem> 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月12日 10:02:08
    *@修改时间 2016年08月12日 10:02:08
    *@版本 V1.0
    *@异常
    **/
    public List<MemberItem> selectByCondition(MemberItem tdMemberItem);
/**
 * 我的车库
 * @param memberItem
 * @return
 */
    public MemberItem selectMyPowerList(MemberItem memberItem);
    
    

    /**
     * 根据id查询权益信息（包含押金、余额、车型集合）
     * @param ItemId
     * @return
     */
    public MemberItem  selectByMemberItemId(MemberItem memberItem);

    /**
     * 分条件查询权益信息（包含押金、余额、车型集合）
     * @param ItemId
     * @return
     */
    public MemberItem  selectMemberItem(MemberItem memberItem);


}
