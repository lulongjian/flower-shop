package com.example.mall.shiro;

import com.example.common.utils.Constant;
import com.example.common.utils.RASEncrypt;
import com.example.mall.dao.UserMapper;
import com.example.mall.domain.UserDO;
import com.example.mall.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 *
 * @author
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写Shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String SnoOrEmail = token.getUsername();
        UserDO userDO = new UserDO();
        //判断邮箱还是学号登陆，用是否含有@特殊字符
        if (SnoOrEmail.contains("@")) {
            userDO.setEmail(SnoOrEmail);
        } else {
            userDO.setSno(SnoOrEmail);
        }
        UserDO user = userMapper.selectOne(userDO);
        //RAS解密
        try {
            user.setPassword(RASEncrypt.decrypt(user.getPassword(), Constant.PRIVATEKRY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnknownAccountException异常
        }
        //判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
