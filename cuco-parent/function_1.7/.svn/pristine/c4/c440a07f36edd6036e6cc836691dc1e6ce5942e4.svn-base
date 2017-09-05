package com.hy.gcar.facede;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.CaptchaMapper;
import com.hy.gcar.entity.Captcha;


@Service
public class CaptchaServiceImpl implements CaptchaService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CaptchaMapper captchaMapper;
	@Override
	public void insert(Captcha captcha) {
		captchaMapper.insert(captcha);
	}

	@Override
	public Captcha getCaptchaByMobile(String mobile) {
		return captchaMapper.getCaptchaByMobile(mobile);
	}

	@Override
	public void updateByPrimaryKey(Captcha record) {
		captchaMapper.updateByPrimaryKey(record);
	}

}
