package com.hy.gcar.service.MemberItemShare;


import com.hy.gcar.entity.MemberItemShare;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author auto create
 * @param <MemberItemShare>
 * @since 1.0,2016-08-27 11:11:32
 */
public interface MemberItemShareService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param trMemberItemShare
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:32
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public MemberItemShare insertSelective(MemberItemShare trMemberItemShare) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param trMemberItemShare
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<MemberItemShare> trMemberItemShare) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param trMemberItemShare
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param trMemberItemShare
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param trMemberItemShare
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public MemberItemShare updateByPrimaryKeySelective(MemberItemShare trMemberItemShare);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param trMemberItemShare
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public MemberItemShare findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param trMemberItemShare
    *@返回值 List<MemberItemShare> 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public List<MemberItemShare> selectByCondition(MemberItemShare trMemberItemShare);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param trMemberItemShare
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月27日 11:11:33
    *@修改时间 2016年08月27日 11:11:33
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(MemberItemShare trMemberItemShare);

    /**
     * 根据会员ID查询不能创建订单的公用人列表(即memberId!=parentMemberId)
     * 传入一个会员ID，如果查询出记录表示该会员不能创建订单
     * @param trMemberItemShare
     * @return
     */
    public List<MemberItemShare> selectByNoPersonalIsMain(MemberItemShare trMemberItemShare);
    
    /**创建共用人
     * @param trMemberItemShare
     * @return
     */
    public Map<String, Object> createMemberItemShare(MemberItemShare trMemberItemShare);
    /**
     * 根据用户查询拥有人
     * @param valueOf
     * @return
     */
	public MemberItemShare selectByMemberId(Long valueOf);
    
}
