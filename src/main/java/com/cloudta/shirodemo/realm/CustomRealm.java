package com.cloudta.shirodemo.realm;

import com.cloudta.shirodemo.dao.*;
import com.cloudta.shirodemo.pojo.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义权限验证Realm
 * 为什么继承AuthorizingRealm?
 *      当查看源码时会发现AuthorizingRealm extends AuthenticatingRealm，所以我们如果
 *      需求是要做登录认证以及授权时，就使用AuthorizingRealm，如果需求是只做登录认证，
 *  *      就extends AuthenticatingRealm。
 */

/******##################既然是授权，查看是否具有缓存？##############******/

public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysPermissionDao sysPermissionDao;

    @Autowired
    SysUserRoleDao sysUserRoleDao;

    @Autowired
    SysRolePermissionDao sysRolePermissionDao;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        LOG.info("Come IN CustomRealm.doGetAuthorizationInfo()");

        String username = principalCollection.getPrimaryPrincipal().toString();



        /**
         * 从数据库中查询角色，权限，添加进去缓存
         */

        /**
         * 角色，权限，添加进SimpleAuthorizationInfo，是将其放进缓存
         * protected Set<String> roles;
         * protected Set<String> stringPermissions
         */
        Integer userId = sysUserDao.findSysUserIdByUsername(username);
        Set<Integer> roleIds = sysUserRoleDao.findRoleIdById(userId);
        Set<String> sysRoleNames = sysRoleDao.findSysRoleNameByIds(roleIds);
        Set<Integer> permissionIds = new HashSet<>();
        for (Integer roleId : roleIds) {
            permissionIds.addAll(sysRolePermissionDao.findPermissionIdById(roleId));
        }

        Set<String> sysPermissioNames = sysPermissionDao.findSysPermissioNameByIds(permissionIds);


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(sysRoleNames);
        info.setStringPermissions(sysPermissioNames);

        return info;
    }

    /**
     * 认证方法
     * 观看源码，可发现UsernamePasswordToken是AuthenticationToken的实现类
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOG.info("Come IN CustomRealm.doGetAuthenticationInfo()");

          UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String password = new String((char[]) token.getCredentials());
          String username = token.getPrincipal().toString();
//
//        LOG.info("username = {}, password = {}", username, password);

        SysUser sysUser = sysUserDao.findSysUserByUsername(username);


        return new SimpleAuthenticationInfo(
                sysUser.getUsername(),
                sysUser.getPassword(),
                getName()
        );
    }
}
