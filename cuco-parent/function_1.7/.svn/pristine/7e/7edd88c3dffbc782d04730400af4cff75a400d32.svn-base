package com.hy.gcar.dao;

import org.springframework.stereotype.Repository;

import com.hy.gcar.entity.Captcha;

@Repository
public interface CaptchaMapper {
	
	/**
     * 新增
     */
    void insert(Captcha record);
    
    /**
     * 根据主键更新验证码信息
     * @param record
     */
	void updateByPrimaryKey(Captcha record);
	
	/**
	 * 根据手机号查询验证码信息
	 * @param mobile
	 * @return
	 */
	Captcha getCaptchaByMobile(String mobile);
	
}