package top.fuguijar.config;

import ch.qos.logback.classic.joran.action.ReceiverAction;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.fuguijar.config.datasource.DruidConfig;
import top.fuguijar.config.redis.RedisConfig;
import top.fuguijar.config.shiro.ShiroConfig;
import top.fuguijar.utils.RedisUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.ws.soap.Addressing;

/**
 * @author fuguijar.top
 */

@Configuration
@EnableTransactionManagement
@MapperScan("top.fuguijar.mapper")
@ComponentScan(value = {"top.fuguijar.service"},basePackageClasses = {
        RedisUtil.class, DruidConfig.class, RedisConfig.class,ShiroConfig.class
})
public class SpringConfig {

    @Autowired
    private DataSource druidDataSource;

    @Bean
    public MybatisConfiguration mybatisConfiguration(){
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
//        mybatisConfiguration.setMapUnderscoreToCamelCase(false);
        return mybatisConfiguration;
    }
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(){
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(druidDataSource);
        mybatisSqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor());
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration());
        return mybatisSqlSessionFactoryBean;
    }
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public TransactionManager transactionManager(){
            return new DataSourceTransactionManager(druidDataSource);
    }
}
