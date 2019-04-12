package com.cloudta.shirodemo.pojo.resultbean;

import com.cloudta.shirodemo.pojo.SysPermission;
import com.cloudta.shirodemo.pojo.SysRole;

import java.util.List;

public class SysRolePermissions extends SysRole {

    private List<SysPermission> permissions;

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "SysRolePermissions{" +
                "permissions=" + permissions +
                '}';
    }
}
