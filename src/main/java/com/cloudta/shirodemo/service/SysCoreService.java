package com.cloudta.shirodemo.service;

import com.cloudta.shirodemo.common.BaseResult;
import com.cloudta.shirodemo.pojo.SysUser;
import org.springframework.stereotype.Service;

/**
 * 系统核心业务
 */
@Service
public interface SysCoreService {

    /**
     * 注册用户
     * @param sysUser
     * @return
     * @throws Exception
     */
    BaseResult registerUser(SysUser sysUser) throws Exception;



}
