package com.cloudta.shirodemo.config;

import com.cloudta.shirodemo.realm.CustomRealm;
import com.cloudta.shirodemo.realm.CustomeCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 使用@Value值注入值时，切记莫不可以使用包装类或者static（类级别）关键字
     */
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private long maxWaitMillis;



    /**#####################    Shiro Redis     #################**/
    /*#################################################################################
     #                                                                                #
     #    public <K, V> Cache<K, V> getCache(String name) throws CacheException       #
     #                                                                                #
     #                                                                                #
     #################################################################################*/
    @Bean("cacheManager")
    public RedisCacheManager redisCacheManager(){
        LOG.info("Come IN ShiroConfig.redisCacheManager()");

        RedisCacheManager redisCacheManager = new RedisCacheManager();
        /**
         * RedisManager implements IRedisManager接口
         */
        RedisManager redisManager = new RedisManager();
        //redisManager.setJedisPool(jedisPool);
        /**
         * @Override
         * 	protected Jedis getJedis() {
         * 		if (jedisPool == null) {
         * 			init();
         * 		}
         * 		return jedisPool.getResource();
         * 	}
         * 	这样可以获取到JRedisPool的值
         *
         * 	#####   目前存在的问题是？怎么让RedisConfig比ShiroConfig注入IOC快   #####
         *
         * 	private void init() {
         * 		synchronized (this) {
         * 			if (jedisPool == null) {
         * 				String[] hostAndPort = host.split(":");
         * 				jedisPool = new JedisPool(getJedisPoolConfig(), hostAndPort[0], Integer.parseInt(hostAndPort[1]), timeout, password, database);
         * 			}
         * 		}
         * 	}
         */
        redisManager.setPassword(password);
        redisManager.setTimeout(timeout);
        redisManager.setHost(host);
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    /**
     * 安全认证过滤器，过滤器只初始化一次，启动时Spring创建Bean时，already init
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")@Autowired SecurityManager securityManager){
        LOG.info("Come IN ShiroConfig.shiroFilterFactoryBean()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        /**
         * 拦截器，拦截静态资源
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        /****############################ 执行链  ############################**/

        filterChainDefinitionMap.put("/shiro01", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        /**
         * 设置全局量，如何设置成我们要跳转的Controller层的url（前后端分离）
         */


        /**
         * 这里可以设置成Controller层的链接，默认前缀：http://localhost:端口号/context-path
         */
        shiroFilterFactoryBean.setLoginUrl("/loginUrl");
        // 权限认证失败，则跳转到指定页面
        //shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

//

    /**
     * 在Shiro中最核心的是SecurityManager
     * 在web环境下，使用DefaultWebSecurityManager，javaSE环境下使用DefaultSecurityManager
     * 设置项（可选）：
     *          Relam（Realm singleRealm或者Collection<Realm> realms）
     *          SessionManager（SessionManager sessionManager）
     *          setCacheManager(CacheManager cacheManager)实际上是CachingSecurityManager类的方法
     *          备注：用Redis作为缓存，Shiro提供了一个缓存的抽象概念，虽然Shiro本身也有缓存实现，
     *                但我们一般不用，结合第三方做缓存
     * @return
     */
    @Bean(value = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("customRealm")@Autowired CustomRealm customRealm
            ,@Qualifier("cacheManager")@Autowired RedisCacheManager cacheManager
    ){
        LOG.info("Come IN ShiroConfig.securityManager()");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
        //defaultWebSecurityManager.setAuthorizer(customRealm);
        //使用Redis缓存作为shiro的缓存
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
    }

    /**
     * 注入CredentialsMatcher(接口，我们要实现这接口)，密码校验
     * @return
     */
    @Bean("customRealm")
    public CustomRealm customRealm(@Qualifier("credentialsMatcher")@Autowired CustomeCredentialsMatcher credentialsMatcher
                                   ,@Qualifier("cacheManager")@Autowired RedisCacheManager redisCacheManager
    ){
        LOG.info("Come IN ShiroConfig.customRealm()");
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(credentialsMatcher);

        //customRealm.setAuthorizationCache();

        //给Realm设置缓存管理器
        customRealm.setCacheManager(redisCacheManager);

        /**#########    默认true（设置可选）      ##########**/
        customRealm.setCachingEnabled(true);
        customRealm.setAuthorizationCachingEnabled(true);

        return customRealm;
    }

    @Bean("credentialsMatcher")
    public CustomeCredentialsMatcher customeCredentialsMatcher(){
        LOG.info("Come in ShiroConfig.customeCredentialsMatcher()");
        return new CustomeCredentialsMatcher();
    }

    /**#############################    在Spring中开启Shiro的注解权限验证      #############################**/

    /**
     * Shiro管理生命周期
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LOG.info("Come IN ShiroConfig.lifecycleBeanPostProcessor()");
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    /**
     * AOP级别权限配置
     * 启用Spring IOC中Shiro的注解，但必须配置了LifecycleBeanPostProcessor方可使用
     * @return
     */
    @Bean("defaultAdvisorAutoProxyCreator")
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        LOG.info("Come IN ShiroConfig.getDefaultAdvisorAutoProxyCreator()");
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }






    /**#########################    未使用     #########################**/

//    /**
//     * 得到IRedisManager
//     * @param cacheManager
//     * @return
//     */
//    @Bean
//    public IRedisManager redisManager(@Autowired RedisCacheManager cacheManager){
//        IRedisManager iRedisManager = cacheManager.getRedisManager();
//        iRedisManager.set("role".getBytes(), "admin".getBytes(), 100);
//        return iRedisManager;
//    }

}
