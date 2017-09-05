package com.hy.gcar.service.powerUserd;


import com.hy.gcar.entity.CarType;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.MemberItem;
import com.hy.gcar.entity.PowerUsed;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author auto create
 * @param <PowerUsed>
 * @since 1.0,2016-09-12 10:36:30
 */
public interface PowerUsedService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdPowerUsed
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(PowerUsed tdPowerUsed) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdPowerUsed
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<PowerUsed> tdPowerUsed) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdPowerUsed
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdPowerUsed
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdPowerUsed
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(PowerUsed tdPowerUsed);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdPowerUsed
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public PowerUsed findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdPowerUsed
    *@返回值 List<PowerUsed> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public List<PowerUsed> selectByCondition(PowerUsed tdPowerUsed);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdPowerUsed
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(PowerUsed tdPowerUsed);
    /**
     * 
     * @param powerUsed
     * @param flag  1 车位用车  2 展厅用车 
     * @param index  车位坐标  从0开始
     * @return
     * @throws Exception
     */
	public Map<String, Object> launchCarShowUseCar(PowerUsed powerUsed,String flag,int index)throws Exception;
	/**
	 * 空车位点击用车
	 * @param powerUsed
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectEmptyMyCarsUseCar(PowerUsed powerUsed)throws Exception;
	/**
	 * 非空车位用车
	 * @param powerUsed
	 * @return
	 */
//	public Map<String, Object> notEmptyMyCarsUseCar(PowerUsed powerUsed,int index) throws Exception;
	/**
	 * 该车型是否是我的启用车型
	 * @param valueOf
	 * @param valueOf2
	 * @return
	 */
	public Map<String, Object> checkIsMyEnableCar(Long carTypeId, Long memberId)throws Exception;
	/**
	 * 获取我的启用车型
	 * @param memberId 
	 * @return
	 */
	public List<CarType> selectMyEnableCars(Long memberId);
	/**
	 * 用车确定按钮 生成用车记录
	 * @param tdPowerUsed
	 * @return
	 */
	public Map<String, Object> insertPowerUsed(PowerUsed tdPowerUsed)throws Exception;
	/**
	 * 空车位更换完车型点确定按钮
	 * @param powerUsed
	 * @return
	 */
	public Map<String, Object> insertEmptyMyCarsUseCarSubmit(PowerUsed powerUsed)throws Exception;
	/**
	 * 查询正在使用的车型记录
	 * @param t
	 * @return
	 */
	public List<PowerUsed> selectPowerUsedOfUseingCars(PowerUsed powerUsed);
	/**
	 * 还车确定
	 * @param powerUsed
	 * @return
	 */
	public Map<String, Object> returnCarSubmit(PowerUsed powerUsed);
	/**
	 * 取消用车
	 * @param powerUsed
	 */
	public void cancelCarSubmit(PowerUsed powerUsed)throws Exception;
	/**
	 * 查询当前权益下所有待送车 、使用中、已完成、已取消
	 * @param powerUsed 
	 * @return
	 */
	public List<PowerUsed> selectPowerUsedListOfStatus(PowerUsed powerUsed);
	
	/**
	* @Title: selectPowerUsed 
	* @Description: TODO(用车记录详情) 
	* @param @param powerUsed
	* @param @return    设定文件 
	* @return PowerUsed    返回类型 
	* @throws
	 */
	public PowerUsed selectPowerUsed(PowerUsed powerUsed);

	
	public List<PowerUsed> selectByPageList(PowerUsed powerUsed);
	
	/**
	 * 
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-30 下午4:40:44   
	* @return String
	 */
	public List<PowerUsed> selectByMemBerIdItemIdCarId(PowerUsed t);
	/**
	 * 查询该车型是否已发起用车
	 * @param carTypeId
	 * @param m 
	 * @return false 未发起  true 已发起
	 */
	public Map<String, Object> checkIsUsedCar(Integer index, Member m);
	
	/**
	 * 通过用户ID查询用车记录
	 * @param t
	 * @return
	 */
	public List<PowerUsed> selectpowerUsedByCompleted(PowerUsed t);
	
	/**
	 * 查询未完成的的用车记录
	 * @param t
	 * @return
	 */
	public List<PowerUsed> selectpowerUsedByNoCompleted(PowerUsed t);

	public Integer insertSelectiveReadExcel(PowerUsed powerUsed);
	
	/**
	 * 插入用车记录 和 任务  已完成的送车任务  和待执行的 取车任务
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-12 下午5:47:24   
	* @return String
	 */
	public Map<String, Object> insertPowerUsedAndButler(PowerUsed powerUsed,Long loginId)  throws Exception;
}
