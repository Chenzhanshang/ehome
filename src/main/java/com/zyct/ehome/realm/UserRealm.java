package com.zyct.ehome.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户认证
 * @author JGZ
 * @Classname UserRealm
 * @Date 2019/11/4 18:43
 * @Email 1945282561@qq.com
 */
public class UserRealm extends AuthorizingRealm {



    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //转换为UsernamePasswordToken
//        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
//        //从数据库中获取用户信息
//        Manage manage = manageService.getManageByLoginName(upToken.getUsername());
//        if (manage == null){
//            throw new UnknownAccountException("用户不存在");
//        }
//        if(false){
//            throw new LockedAccountException("用户被锁定");
//        }
//        //盐值 以用户名作为盐值
//        //配置文件中标明用MD5加密了1024次
//        ByteSource credentialsSalt = ByteSource.Util.bytes(manage.getLoginName());
//        SimpleAuthenticationInfo info = null;
//        //比对密码
//        info = new SimpleAuthenticationInfo(manage,manage.getPassword(),credentialsSalt,this.getName());

        return null;
    }

//    @Test
//    public void func(){
//        String fun = "MD5";
//        String pwd = "123456";
//        ByteSource credentialsSalt = ByteSource.Util.bytes("刘八");
//        int i = 1024;
//        SimpleHash simpleHash = new SimpleHash(fun, pwd, credentialsSalt, i);
//        System.out.println(simpleHash);
//    }


}
