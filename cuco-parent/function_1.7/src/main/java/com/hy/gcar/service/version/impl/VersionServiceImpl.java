package com.hy.gcar.service.version.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.VersionMapper;
import com.hy.gcar.entity.Version;
import com.hy.gcar.service.version.VersionService;

/**版本service实现
 * @author wy
 *
 */
@Service
public class VersionServiceImpl implements VersionService{

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public List<Version> selectVersinByCode(String code) {
		return versionMapper.selectVersinByCode(code);
	}

	@Override
	public List<Version> selectVersinByCodeAndr(String code) {
		return versionMapper.selectVersinByCodeAndr(code);
	}

	@Override
	public List<Version> selectVersinByFlag(Integer flag) {
		return versionMapper.selectVersinByFlag(flag);
	}


}
