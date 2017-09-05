package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.CarViolation;

public interface CarViolationMapper<T> extends BaseDao<T> {

	/**   
	 * @Title: selectByConditionOrderByCreatedDesc   
	 * @Description: 根据创建时间倒叙查询车辆违章记录列表
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: List<CarViolation>       
	 */
	public List<CarViolation> selectByConditionOrderByCreatedDesc(CarViolation carViolation);
	
	/**   
	 * @Title: selectCountOrderByCreatedDesc   
	 * @Description: 根据创建时间倒叙查询车辆违章记录列表数量
	 * @param: @param carViolation
	 * @param: @return      
	 * @return: int       
	 */
	public int selectCountOrderByCreatedDesc(CarViolation carViolation);
}
