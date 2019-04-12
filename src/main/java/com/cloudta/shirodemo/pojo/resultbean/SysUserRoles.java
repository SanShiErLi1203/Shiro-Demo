package com.cloudta.shirodemo.pojo.resultbean;

import com.cloudta.shirodemo.pojo.SysRole;
import com.cloudta.shirodemo.pojo.SysUser;

import java.util.List;

public class SysUserRoles extends SysUser {

    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysUserRoles{" +
                "roles=" + roles +
                '}';
    }
}
