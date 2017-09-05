package cn.cuco.service.security.impl;

import cn.cuco.common.utils.param.ParamVerifyUtils;
import cn.cuco.enums.security.RoleEnum;
import cn.cuco.service.security.SecurityService;
import com.hy.authorization.entity.User;
import com.hy.authorization.service.ParentServiceImpl;
import com.hy.common.utils.ParamVerifyUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年03月01 17:57:00
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public List<User> getUsersByRole(RoleEnum roleEnum) {
        //TODO
        if(true) return Collections.emptyList();

        ParamVerifyUtils.paramNotNull(roleEnum,"根据角色获取用户失败：角色标识不可为空");

        List<User> users = ParentServiceImpl.getUsersByRoleUniqueCode(roleEnum.name());

        return users;
    }

    @Override
    public User getUserByMobileAndEmail(String mobile, String email) {
        //TODO
        if(true) return null;

        User user = ParentServiceImpl.getBasicUserByMobileAndEmail(mobile,email);
        ParamVerifyUtil.paramNotNull(user,"根据用户手机号和邮箱获取用户失败：用户不存在");

        return ParentServiceImpl.getUserRolesByUserId(user.getId());
    }

    @Override
    public User getUserById(Long userId) {
        //TODO
        if(true) return null;

        ParamVerifyUtil.paramNotNull(userId,"userId不可为空");
        User user = ParentServiceImpl.getBasicUserByUserId(userId);

        return user;
    }

}
