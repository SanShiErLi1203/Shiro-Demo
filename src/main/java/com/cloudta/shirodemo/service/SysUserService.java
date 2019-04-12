package com.cloudta.shirodemo.service;

import com.cloudta.shirodemo.pojo.resultbean.SysUserRoles;
import org.springframework.stereotype.Service;

@Service
public interface SysUserService {

    /**
     * 通过用户id获取，用户和角色关联的数据
     * @param id
     * @return
     */
    SysUserRoles getUserRolesByUserId(Integer id);

}
