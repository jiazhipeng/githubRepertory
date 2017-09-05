package com.hy.gcar.service.carType;


import com.hy.gcar.entity.CarType;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author auto create
 * @param <CarType>
 * @since 1.0,2016-08-11 16:59:56
 */
public interface CarTypeService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tcCarType
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(CarType tcCarType) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tcCarType
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<CarType> tcCarType) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tcCarType
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
    *@param tcCarType
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
    *@param tcCarType
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(CarType tcCarType);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tcCarType
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public CarType findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tcCarType
    *@返回值 List<CarType> 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月11日 16:59:56
    *@修改时间 2016年08月11日 16:59:56
    *@版本 V1.0
    *@异常
    **/
    public List<CarType> selectByCondition(CarType tcCarType);
    
    
    /**
     * 极车展厅
     * @return
     */
    public List<CarType> selectCarTypes(String carType);
    /**
     * 查看极车展厅  车型详情 
     * @param carType（memberId 会员id 、id:车型id）
     * @return
     */
    public CarType selectCarTypeDetail(CarType carType);
    
    
    
	/**
	 * 根据itemid查询所有车型
	 * @param itemId
	 * @return
	 */
    public List<CarType> selectByItemId(Long itemId);
   
    /**
     * 查询所属套餐下我的非启用车型
     * @param memberId
     * @return
     */
    public List<CarType> selectMyNotEnablecarTypes(Long memberId);
    
    
    
    public Map<String,Object> getStoreOfCartype(String memberItemId, String useCarStartTime, String useCarEndTime);

    /**
     * 根据id
     * @param carTypeIds
     * @return
     */
    public List<CarType> selectCarTypeByIds(List<Long> carTypeIds);
   
}
