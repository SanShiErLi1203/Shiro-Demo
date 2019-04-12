package com.cloudta.shirodemo.controller;

import com.cloudta.shirodemo.common.BaseResult;
import com.cloudta.shirodemo.constant.MsgCodeConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.Map;


@Controller
@SuppressWarnings("all")
public class ShiroController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/loginUrl")
    @ResponseBody
    public BaseResult testLoginUrl() throws Exception{
        return BaseResult.jsonData(MsgCodeConstant.SUCCESS_CODE.CODE_VALUE(), "F - U - C - K 认证失败....");
    }


    private static final Logger LOG = LoggerFactory.getLogger(ShiroController.class);

    @RequestMapping(value = "/exception01")
    public BaseResult exceptionTest(@RequestBody Object object) throws Exception{
        LOG.info("come in exceptionTest method.....");
        throw new NoSuchMethodException();
    }

    /***#####################测试Shiro，使用@RequiredRoles生效了####################***/
    @GetMapping("/shiro01")
    @ResponseBody
    //@RequiresRoles("admin")
    public BaseResult login(@RequestBody Map<String, Object> map) throws Exception{
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        LOG.info("ShiroController.login()： username = {}, password = {}" , username, password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        Subject currentUser = SecurityUtils.getSubject();


        currentUser.login(token);

        Serializable sessionId = currentUser.getSession().getId();
        LOG.info("id = {} " + sessionId);
        /**
         * 只有登录成功后才检查是否有什么角色
         */
        currentUser.checkRole("admin");

        return BaseResult.jsonData(MsgCodeConstant.SUCCESS_CODE.CODE_VALUE(), MsgCodeConstant.SUCCESS_LOGIN_MSG.MSG_VALUE());
    }




    @RequestMapping("/shiro")
    @ResponseBody
    public BaseResult shiroTest()throws Exception{
        LOG.info("Come IN ShiroController.shiroTest()");

        Jedis jedis1 = jedisPool.getResource();
        jedis1.set("name", "wuwuue");
        String name = jedis1.get("name");

        Jedis jedis2 = jedisPool.getResource();
        jedis2.set("name", "wuwuue");
        String name2 = jedis1.get("name");

        Jedis jedis3 = jedisPool.getResource();
        jedis3.set("name", "wuwuue");
        String name3 = jedis1.get("name");

        Jedis jedis4 = jedisPool.getResource();
        jedis4.set("name", "wuwuue");
        String name4 = jedis1.get("name");

        LOG.info("name = {}, numActive = {}", name, jedisPool.getNumActive());
        return  null;
    }

}
