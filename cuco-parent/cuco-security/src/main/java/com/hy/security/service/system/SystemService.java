package com.hy.security.service.system;

import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.view.response.SystemResView;
import java.util.List;

/**
 * Created by WangShuai on 2016/7/21.
 */
public interface SystemService {

    public Boolean exists(Long systemId);

    public List<System> findBySystem(System system);

    /**
     * 添加系统
     * @param system
     * @return
     */
    public System createSystem(System system);

    /**
     * 修改系统
     * @param system
     * @return
     */
    public System updateSystem(System system);

    public String getSystemType(System system);

    /**
     * 是否是主系统
     * @param system
     * @return
     */
    public boolean isMain(System system);

    /**
     * 是否是子系统
     * @param system
     * @return
     */
    public boolean isSub(System system);

    /**
     * 查询所有系统
     * @return
     */
    public List<SystemResView> findAllSystems();

    /**
     * 根据“系统ID”查询系统
     * @param id
     * @return
     */
    public System findById(Long id);

    public Object findSystemPage(System system);

    /**
     * 获取用户可访问的系统
     * @param user
     * @return
     */
    public List<System> findSystemsOfUser(User user);
    
    public System findSystemByToken(String token);
}
