package cn.cuco.service.wechat.menu;


import java.util.List;

import cn.cuco.entity.WechatMenu;

/**
 * 
* @ClassName: WechatMenuService 
* @Description: wechatMenu 
* @author jiaxiaoxian
* @date 2017年3月13日 下午1:41:33
 */
public interface WechatMenuService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdWechatMenu
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public Integer create(WechatMenu tdWechatMenu) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdWechatMenu
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public Integer createList(List<WechatMenu> tdWechatMenu) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdWechatMenu
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteById(Object id);
    
    /**
     * 删除所有
    * @Title: deleteAll 
    * @Description: TODO
    * @author jiaxiaoxian
    * @return
    * @return Integer
     */
    public Integer deleteAll();

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdWechatMenu
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public Integer update(WechatMenu tdWechatMenu);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdWechatMenu
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public WechatMenu getById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdWechatMenu
    *@返回值 List<WechatMenu> 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public List<WechatMenu> getByMenu(WechatMenu tdWechatMenu);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdWechatMenu
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年12月27日 18:19:36
    *@修改时间 2016年12月27日 18:19:36
    *@版本 V1.0
    *@异常
    **/
    public Integer getCountByMenu(WechatMenu tdWechatMenu);


}
