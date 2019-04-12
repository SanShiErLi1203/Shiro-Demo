package com.cloudta.shirodemo.pojo.resultbean;

import com.cloudta.shirodemo.pojo.SysPermission;
import com.cloudta.shirodemo.pojo.SysUser;

import java.util.List;

public class SysUserPermissions extends SysUser {

    private List<SysPermission> permissions;

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "SysUserPermissions{" +
                "permissions=" + permissions +
                '}';
    }
}
