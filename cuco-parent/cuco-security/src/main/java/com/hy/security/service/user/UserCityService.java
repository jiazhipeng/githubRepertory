package com.hy.security.service.user;


import java.util.List;

import com.hy.security.entity.UserCity;

/**
 * Created by WangShuai on 2016/7/21.
 */
public interface UserCityService {

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdUser
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<UserCity> userCitys);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdUser
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);
    
    /**
     * 查询城市集合
    * @Title: selectByCondition 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param tdUser
    * @param @return    设定文件 
    * @return List<UserCity>    返回类型 
    * @throws
     */
    public List<UserCity> selectByCondition(UserCity tdUser);
    
    /**
     * 根据用户删除城市
    * @Title: deleteCityByUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param userCity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteCityByUser(UserCity userCity);

}
