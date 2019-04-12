package com.cloudta.shirodemo.dao;

import com.cloudta.shirodemo.pojo.resultbean.SysRolePermissions;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SysRolePermissionDao {

    /**
     * 通过角色id查询出权限集合
     * @param id
     * @return
     */
    SysRolePermissions findRolePermissionsById(int id);

    /**
     * 通过角色id查询出权限id集合
     * @param id
     * @return
     */
    Set<Integer> findPermissionIdById(int id);

}
