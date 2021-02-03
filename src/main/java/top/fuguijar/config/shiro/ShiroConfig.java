package top.fuguijar.config.shiro;

import org.apache.shiro.mgt.*;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fuguijar.filter.StatelessAuthcFilter;
import top.fuguijar.realm.StatelessRealm;
import top.fuguijar.subject.StatelessDefaultSubjectFactory;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fuguijar.top
 * @date 2021-01-30
 */
@Configuration
public class ShiroConfig {

    /**自定义过滤器
     * @return StatelessAuthcFilter
     */
    @Bean
    public StatelessAuthcFilter statelessAuthcFilter(){
        return new StatelessAuthcFilter();
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        System.out.println("shiroFilter-------------->");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
//        设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager());
//        设置自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("statelessAuthc",statelessAuthcFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
//        设置内置过滤器
        Map<String,String> filterStringMap=new LinkedHashMap<>();
        filterStringMap.put("/lo", "roles[p]");
        filterStringMap.put("/**", "statelessAuthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterStringMap);
        /*login*/
//        shiroFilterFactoryBean.setLoginUrl("/login");
        /*设置无权限页面*/
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
//        关联realm
        securityManager.setRealm(statelessRealm());
//        设置session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        securityManager.setSessionManager(defaultSessionManager());
        securityManager.setSubjectFactory(statelessDefaultSubjectFactory());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }
    @Bean
    public DefaultSessionManager defaultSessionManager(){
        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
        return defaultSessionManager;
    }

    @Bean
    public StatelessDefaultSubjectFactory statelessDefaultSubjectFactory(){
        return new StatelessDefaultSubjectFactory();
    }
    /**
     * 配置RedisManager
     * @return
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }
    /**
     * 配置CacheManager的具体实现类
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(){//RedisCacheManager实现了CacheManager的接口
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //设置过期时间
        redisCacheManager.setExpire(1800);
        return redisCacheManager;
    }

    /**
     * 创建Realm
     */
    @Bean
    public StatelessRealm statelessRealm(){
        return new StatelessRealm();
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(defaultSecurityManager());
        return methodInvokingFactoryBean;
    }

}
