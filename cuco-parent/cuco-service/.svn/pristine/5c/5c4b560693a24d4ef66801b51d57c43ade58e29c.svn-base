package cn.cuco.service.security;

import cn.cuco.enums.security.RoleEnum;
import com.hy.authorization.entity.User;

import java.util.List;

/**
 * Created by WangShuai on 2017/3/1.
 */
public interface SecurityService {

    List<User> getUsersByRole(RoleEnum roleEnum);

    /**
     * 根据手机号和邮箱获取用户信息
     * @param mobile
     * @param email
     * @return
     */
    User getUserByMobileAndEmail(String mobile,String email);

    User getUserById(Long userId);
}
