package com.cloudta.shirodemo.dao;

import com.cloudta.shirodemo.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser findSysUserById(int id);


    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    SysUser findSysUserByUsername(String username);

    /**
     * 通过用户名查询用户Id
     * @param username
     * @return
     */
    Integer findSysUserIdByUsername(String username);

    /**
     * 通过koken查询用户
     * @param password
     * @return
     */
    SysUser findSysUserByToken(String password);

    /**
     * 创建用户
     * @param sysUser
     * @return
     */
    int insertSysUser(SysUser sysUser);

    /**
     * 更新用户
     * @param sysUser
     * @return
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 通过用户名查询数据库存在的条数
     * @param username
     * @return
     */
    int countSysUserByUsername(String username);

    /**
     * 通过id查询数据库存在的条数
     * @param id
     * @return
     */
    int countSysUserById(int id);



}
