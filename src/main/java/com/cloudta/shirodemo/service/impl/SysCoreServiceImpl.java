package com.cloudta.shirodemo.service.impl;

import com.cloudta.shirodemo.common.BaseResult;
import com.cloudta.shirodemo.constant.MsgCodeConstant;
import com.cloudta.shirodemo.dao.SysUserDao;
import com.cloudta.shirodemo.pojo.SysUser;
import com.cloudta.shirodemo.service.SysCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysCoreServiceImpl implements SysCoreService {

    private static final Logger LOG = LoggerFactory.getLogger(SysCoreServiceImpl.class);

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public BaseResult registerUser(SysUser sysUser) throws Exception {
        LOG.info("Come IN SysCoreServiceImpl.registerUser()");

        sysUser.setRegisterTime(System.currentTimeMillis() / 1000);
        int count = sysUserDao.countSysUserByUsername(sysUser.getUsername());
        SysUser afterSysUser = new SysUser();
        if(count <= 0 ){
            sysUserDao.insertSysUser(sysUser);
            afterSysUser = sysUserDao.findSysUserById(sysUser.getId());
        }

        LOG.info("count = {}, id = {}, afterUser = {}", count,  sysUser.getId(), afterSysUser);
        return BaseResult.jsonData(MsgCodeConstant.SUCCESS_CODE.CODE_VALUE(), MsgCodeConstant.SUCCESS_MSG.MSG_VALUE(),
                null, afterSysUser);
    }

}
