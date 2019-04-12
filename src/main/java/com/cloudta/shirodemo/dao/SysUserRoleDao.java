package com.cloudta.shirodemo.dao;

import com.cloudta.shirodemo.pojo.resultbean.SysUserRoles;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SysUserRoleDao {

    /**
     * 通过用户id查询出一个用户下的所有角色
     * @param id
     * @return
     */
    SysUserRoles findUserRolesById(int id);

    /**
     * 通过用户id查询角色id集合
     * @param id
     * @return
     */
    Set<Integer> findRoleIdById(int id);

    /**
     * 插入到sys_user_role表
     * @param userId
     * @param roleId
     * @return
     */
    int insertSysUserRole(int userId, int roleId);


}
