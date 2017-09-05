package cn.cuco.dao;

import cn.cuco.entity.CarAttachment;

public interface CarAttachmentMapper<T> extends BaseDao<T> {

	/**   
	 * 根据车辆附件主表ID以及类型删除
	 * @Title: deleteByParentId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param carAttachment      
	 * @return: void       
	 */
	public void deleteByParentId(CarAttachment carAttachment);
}
