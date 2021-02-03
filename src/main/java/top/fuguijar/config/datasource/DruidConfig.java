package top.fuguijar.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author fuguijar.top
 * @date 2021-01-31
 */
@Configuration
@PropertySource("classpath:druid.properties")
public class DruidConfig {
//      不知道咋回事一旦在Spring上面加载了SiroConfig，这里的@Value 就不好使了
//    @Value("${jdbc.username}")
//    private String name;
//    @Value("${jdbc.password}")
//    private String pwd;
//    @Value("${jdbc.url}")
//    private String url;


    private final static String name = "root";
    private final static String pwd = "root";
    private final static String url = "jdbc:mysql://127.0.0.1:3306/db?serverTimezone=UTC";

    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(name);
        druidDataSource.setPassword(pwd);
        druidDataSource.setUrl(url);
        return druidDataSource;
    }

}
