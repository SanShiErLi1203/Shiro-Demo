package com.cloudta.shirodemo.realm;

import com.cloudta.shirodemo.dao.SysUserDao;
import com.cloudta.shirodemo.pojo.SysUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义密码校验匹配
 */
public class CustomeCredentialsMatcher implements CredentialsMatcher {

    @Autowired
    SysUserDao sysUserDao;

    private static final Logger LOG = LoggerFactory.getLogger(CustomeCredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        LOG.info("Come IN CustomeCredentialsMatcher.doCredentialsMatch()");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getPrincipal().toString();
        String password = new String((char[]) token.getCredentials());
        LOG.info("username = " + username + ", password = " + password);

        //数据库查询出密码，比对，密码加密，解密
        SysUser sysUser = sysUserDao.findSysUserByUsername(username);
        LOG.info("The Database' s data： username = {}, password = {}", username, password);

        return sysUser.getUsername().equals(username) && sysUser.getPassword().equals(password);
    }
}
