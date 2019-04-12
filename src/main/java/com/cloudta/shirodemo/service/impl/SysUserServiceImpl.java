package com.cloudta.shirodemo.service.impl;

import com.cloudta.shirodemo.dao.SysRoleDao;
import com.cloudta.shirodemo.dao.SysUserDao;
import com.cloudta.shirodemo.dao.SysUserRoleDao;
import com.cloudta.shirodemo.pojo.SysRole;
import com.cloudta.shirodemo.pojo.SysUser;
import com.cloudta.shirodemo.pojo.resultbean.SysUserRoles;
import com.cloudta.shirodemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysUserRoleDao sysUserRoleDao;

    @Override
    public SysUserRoles getUserRolesByUserId(Integer id) {

        Set<Integer> roleIds = sysUserRoleDao.findRoleIdById(id);
        List<SysRole> sysRoles = sysRoleDao.findSysRoleByIds(roleIds);

        SysUserRoles sysUserRoles = new SysUserRoles();
        sysUserRoles.setRoles(sysRoles);

        return null;
    }
}
