package com.hy.gcar.facede;

import com.hy.gcar.entity.Captcha;

/**
 * <p>
 * Title:验证码相关业务
 * </p>
 * <p>
 * Description: 验证码查询
 * </p>
 * <p>
 * Date: 2016/1/4
 * </p>
 * 
 * @author duzc
 * @version 1.0
 */
public interface CaptchaService {
	/**
	 * 新增验证码记录
	 * @param captcha
	 */
	public void insert(Captcha captcha);
	/**
	 * 根据手机号查询对应验证码信息
	 * @param mobile
	 * @return
	 */
	public Captcha getCaptchaByMobile(String mobile);
	
	/**
	 * 根据主键更新验证码信息
	 * @param record
	 */
	public void updateByPrimaryKey(Captcha record);

}
