package cn.cuco.dao;

import java.util.List;

public interface BaseDao<T> {

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
	public Integer selectCountByCondition(T t);

	/**
	 * @方法名: selectByCondition
	 * @方法描述: 分条件查询
	 * @param t
	 * @return
	 * @返回值 List 返回类型
	 * @作者：q.p.x @创建时间 2015年3月13日 上午12:59:39
	 * @修改时间 2015年3月13日 上午12:59:39
	 * @版本 V1.0
	 * @异常
	 */
	public List<T> selectByCondition(T t);

	/**
	 * @方法名: insertSelective
	 * @方法描述: 根据对象插入单条记录
	 * @param t
	 * @return
	 * @返回值 Integer 返回类型
	 * @作者：FuShihua
	 * @创建时间 2015年3月13日 上午12:59:39
	 * @修改时间 2015年3月13日 上午12:59:39
	 * @版本 V1.0
	 * @异常
	 */
	public Integer insertSelective(T t);

	/**
	 * @方法名: insertBatch
	 * @方法描述: 根据对象批量插入记录
	 * @param t
	 * @return
	 * @返回值 Integer 返回类型
	 * @作者：FuShihua
	 * @创建时间 2015年3月13日 上午12:59:59
	 * @修改时间 2015年3月13日 上午12:59:59
	 * @版本 V1.0
	 * @异常
	 */
	public Integer insertBatch(List<T> t);

	/**
	 * @方法名: deleteByPrimaryKey
	 * @方法描述: 根据主键删除单条记录
	 * @param t
	 * @return
	 * @返回值 Integer 返回类型
	 * @作者：FuShihua
	 * @创建时间 2015年3月13日 上午12:49:01
	 * @修改时间 2015年3月13日 上午12:49:01
	 * @版本 V1.0
	 * @异常
	 */
	public Integer deleteByPrimaryKey(Object id);

	/**
	 * @方法名: deleteBatchByPrimaryKey
	 * @方法描述: 根据主键批量删除
	 * @param t
	 * @return
	 * @返回值 Integer 返回类型
	 * @作者：FuShihua
	 * @创建时间 2015年3月13日 上午12:44:25
	 * @修改时间 2015年3月13日 上午12:44:25
	 * @版本 V1.0
	 * @异常
	 */
	public Integer deleteBatchByPrimaryKey(List<Object> ids);

	/**
	 * @方法名: updateByPrimaryKeySelective
	 * @方法描述: 根据主键选择更新单个对象
	 * @param t
	 * @return
	 * @返回值 Integer 返回类型
	 * @作者：FuShihua
	 * @创建时间 2015年3月13日 上午1:08:26
	 * @修改时间 2015年3月13日 上午1:08:26
	 * @版本 V1.0
	 * @异常
	 */
	public Integer updateByPrimaryKeySelective(T t);

	/**
	 * @方法名: selectByPrimaryKey
	 * @方法描述: 根据主键查询对象
	 * @param id
	 * @return
	 * @返回值 T 返回类型
	 * @作者：FuShihua
	 * @创建时间 2015年3月13日 上午1:31:57
	 * @修改时间 2015年3月13日 上午1:31:57
	 * @版本 V1.0
	 * @异常
	 */
	public T selectByPrimaryKey(Object id);

}
