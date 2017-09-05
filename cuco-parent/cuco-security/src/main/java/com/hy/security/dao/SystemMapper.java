package com.hy.security.dao;

import com.hy.security.entity.System;
import java.util.List;

public interface SystemMapper<T> extends BaseDao<T> {

    public Integer countByCondition(T t);

    public List<System> findSystemsOfUser(Long userId);
    
    public System selectSystemByToken(String token);

}
