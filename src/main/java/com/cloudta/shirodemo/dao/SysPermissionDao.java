package com.cloudta.shirodemo.dao;

import com.cloudta.shirodemo.pojo.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysPermissionDao {
    /**
     * 通过id查询权限
     * @param id
     * @return
     */
    SysPermission findSysPermissionById(Integer id);

    /**
     * 根据id集合批量查询权限
     * @param ids
     * @return
     */
    List<SysPermission> findSysPermissionByIds(Set<Integer> ids);


    /**
     * 根据id集合批量查询权限名称
     * @param ids
     * @return
     */
    Set<String> findSysPermissioNameByIds(Set<Integer> ids);

    /**
     * 通过name查询权限
     * @param name
     * @return
     */
    SysPermission findSysPermissionByName(String name);

    /**
     * 插入权限
     * @param sysPermission
     * @return
     */
    int insertSysPermission(SysPermission sysPermission);

    /**
     * 更新权限
     * @param sysPermission
     * @return
     */
    int updateSysPermission(SysPermission sysPermission);

    /**
     * 通过if查询存在的条数
     * @param id
     * @return
     */
    int countSysPermissionById(Integer id);

    /**
     * 通过name查询存在的条数
     * @param name
     * @return
     */
    int countSysPermissionByName(String name);


}
