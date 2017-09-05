package com.hy.security.service.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.dao.AccessTokenMapper;
import com.hy.security.entity.AccessToken;
import com.hy.security.entity.User;
import com.hy.security.service.user.AccessTokenService;
import com.hy.security.service.user.UserService;
/**
 * 
 * @author zhubing 20161114
 *
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private AccessTokenMapper<AccessToken> accessTokenMapper;

	@Autowired
    private UserService userService;
	@Override
	public void save(AccessToken accessToken) {
		
		accessToken.setInvalidTime(new Date(accessToken.getCreateDate().getTime() + accessToken.getLiveTime()*1000));
		accessTokenMapper.insertSelective(accessToken);
	}

	@Override
	public List<AccessToken> selectByCondition(AccessToken accessToken) {
		
		return accessTokenMapper.selectByCondition(accessToken);
	}

	@Override
	public void delById(Long id) {
		
		accessTokenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void delByToken(String userToken) {
		
		AccessToken accessToken = new AccessToken();
		accessToken.setToken(userToken);
		List<AccessToken> accessTokenByCondition = this.selectByCondition(accessToken);
		if(accessTokenByCondition != null && !accessTokenByCondition.isEmpty()){
			for(int i=0;i<accessTokenByCondition.size();i++){
				this.delById(accessTokenByCondition.get(i).getId());
			}
		}
	}

	@Override
	public User getUserAuthByUserToken(String userToken) {
		
		AccessToken accessTokenSearch = new AccessToken();
		accessTokenSearch.setToken(userToken);
		//根据token获取userId
		List<AccessToken> selectByCondition = this.selectByCondition(accessTokenSearch);
		
		if(selectByCondition == null || selectByCondition.isEmpty()){
			//用户token失效
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		AccessToken accessToken = selectByCondition.get(0);
		if(accessToken.getInvalidTime().before(new Date())){
			//用户token失效
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		
		User user = new User();
		user.setId(accessToken.getUserId());
		user.setSystemId(accessToken.getSystemId());
		//根据userId和systemId获取权限
		user = userService.findApiByUserIdAndSystemId(user);
		return user;
	}

}
