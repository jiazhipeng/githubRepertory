package com.hy.gcar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.gcar.entity.Version;

@Repository
public interface VersionMapper {


    /**
     * 根据主键查询会员
     */
    Version selectByPrimaryKey(Long id);

    
    List<Version> selectVersinByCode(String  code);

    List<Version> selectVersinByCodeIos();

   List<Version> selectVersinByCodeAndr(String  code);


   
   List<Version> selectVersinByFlag(Integer flag);




}