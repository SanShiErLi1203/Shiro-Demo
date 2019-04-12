package com.cloudta.shirodemo.dao;

import com.cloudta.shirodemo.pojo.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysRoleDao {
    /**
     * 通过角色id查询角色
     * @param id
     * @return
     */
    SysRole findSysRoleById(Integer id);

    /**
     * 根据id集合批量查询角色
     * @param ids
     * @return
     */
    List<SysRole> findSysRoleByIds(Set<Integer> ids);

    /**
     * 根据id集合批量查询角色名
     * @param ids
     * @return
     */
    Set<String> findSysRoleNameByIds(Set<Integer> ids);

    /**
     * 通过角色名查询角色
     * @param name
     * @return
     */
    SysRole findSysRoleByName(String name);

    /**
     * 插入角色
     * @param sysRole
     * @return
     */
    int insertSysRole(SysRole sysRole);

    /**
     * 更新角色
     * @param sysRole
     * @return
     */
    int updateSysRole(SysRole sysRole);

    /**
     * 通过id查询数据库中存在的条数
     * @param id
     * @return
     */
    int countSysRoleById(Integer id);

    /**
     * 通过name查询数据库中的条数
     * @param name
     * @return
     */
    int countSysRoleByName(String name);


}
