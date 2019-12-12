package com.zyct.ehome.realm;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Promise;
import com.zyct.ehome.entity.Role;
import com.zyct.ehome.service.AdminService;
import com.zyct.ehome.service.PromiseService;
import com.zyct.ehome.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户认证
 * @author JGZ
 * @Classname UserRealm
 * @Date 2019/11/4 18:43
 * @Email 1945282561@qq.com
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PromiseService promiseService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //开始获取权限
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        if(primaryPrincipal instanceof Admin){
            Admin admin = (Admin) primaryPrincipal;
            //获取admin的角色
            Set<Role> roleSet = roleService.getRoleByAdminId(admin.getAdminId());
            //
            admin.setRoles(roleSet);

            Set<String> roles = new HashSet<>();
            for (Role role:roleSet) {
                roles.add(role.getRoleName());
            }
            simpleAuthorizationInfo.addRoles(roles);

            //通过角色获取权限
            Set<Promise> promiseSet = promiseService.getPromiseByRole(roleSet);
            Set<String> promises = new HashSet<>();
            for (Promise promise:promiseSet) {
                promises.add(promise.getPromiseName());
            }
            simpleAuthorizationInfo.addStringPermissions(promises);
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //从数据库中获取用户信息
        Admin adminByAccount = adminService.getAdminByAccount(upToken.getUsername());
        if (adminByAccount == null){
            throw new UnknownAccountException("用户不存在");
        }
        adminByAccount.setRoles(roleService.getRoleByAdminId(adminByAccount.getAdminId()));
        //盐值 以用户名作为盐值
        //配置文件中标明用MD5加密了1024次
        ByteSource credentialsSalt = ByteSource.Util.bytes(adminByAccount.getAdminAccount());
        SimpleAuthenticationInfo info = null;
        //比对密码
        info = new SimpleAuthenticationInfo(adminByAccount,adminByAccount.getAdminPassword(),credentialsSalt,this.getName());

        return info;
    }

    @Test
    public void func(){
        String fun = "MD5";
        String pwd = "123456";
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        int i = 1024;
        SimpleHash simpleHash = new SimpleHash(fun, pwd, credentialsSalt, i);
        System.out.println(simpleHash);
    }


}
