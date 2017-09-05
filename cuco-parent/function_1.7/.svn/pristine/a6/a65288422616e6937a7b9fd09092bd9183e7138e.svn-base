package com.hy.gcar.dao;

import java.util.List;

import com.hy.gcar.entity.Marketing;


public interface MarketingMapper {
	 /**
   	 * 根据id删除活动
   	 * @param id
   	 * @return
   	 */
    int deleteByPrimaryKey(Long id);

    /**
   	 * 插入活动
   	 * @param id
   	 * @return
   	 */
    int insert(Marketing record);

    /**
   	 * 插入活动
   	 * @param id
   	 * @return
   	 */
    int insertSelective(Marketing record);

    /**
   	 * 根据id查询活动
   	 * @param id
   	 * @return
   	 */
    Marketing selectByPrimaryKey(Long id);

    /**
   	 * 修改活动
   	 * @param id
   	 * @return
   	 */
    int updateByPrimaryKeySelective(Marketing record);

    /**
   	 * 修改活动
   	 * @param id
   	 * @return
   	 */
    int updateByPrimaryKey(Marketing record);
    /**
   	 * 分条件查询活动列表
   	 * @param suggest
   	 * @return
   	 */
     List<Marketing> getMarketingList(Marketing marketing);
     /**
	 * 获取活动效果统计数据（指定活动带来游客、用户、会员、VIP等数量）
	 * @param id
	 * @return
	 */
     List<Marketing> getMarketingStatis(Marketing marketing);
}