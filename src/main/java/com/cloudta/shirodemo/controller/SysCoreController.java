package com.cloudta.shirodemo.controller;

import com.cloudta.shirodemo.common.BaseResult;
import com.cloudta.shirodemo.dao.SysPermissionDao;
import com.cloudta.shirodemo.dao.SysRoleDao;
import com.cloudta.shirodemo.dao.SysUserDao;
import com.cloudta.shirodemo.pojo.SysRole;
import com.cloudta.shirodemo.pojo.SysUser;
import com.cloudta.shirodemo.service.SysCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Sys核心控制器
 */
@Controller
public class SysCoreController {

    private static final Logger LOG = LoggerFactory.getLogger(SysCoreController.class);

    @Autowired
    SysCoreService sysCoreService;

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysPermissionDao sysPermissionDao;

    @Autowired
    SysUserDao sysUserDao;

    /**
     * 注册用户
     * @param sysUser
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    @ResponseBody
    public BaseResult registerUser(@RequestBody SysUser sysUser) throws Exception{
        LOG.info("Come IN SysCoreController.registerUser()");
        LOG.info("sysUser：{}" + sysUser.toString());
        return sysCoreService.registerUser(sysUser);
    }

    @PostMapping("/addRole")
    @ResponseBody
    public BaseResult addRole(@RequestBody Map<String, Object> map) throws Exception{
        LOG.info("Come IN SysCoreController.addRole()");
        Integer userId = (Integer)map.get("id");
        String roleName = (String)map.get("roleName");
        SysRole sysRole = new SysRole();
        sysRole.setCreateTime(System.currentTimeMillis() / 1000);
        sysRole.setName(roleName);





        return null;

    }


}
