package top.fuguijar.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.fuguijar.constants.AppConstants;

/**
 * @author fuguijar.top
 * @date 2021-01-26
 */
@ComponentScan("top.fuguijar.controller")
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**映射 web page
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/"+ AppConstants.STATIC_RESOURCES_NAME +"/"+AppConstants.STATIC_PROJECT_NAME+"/");
    }
}
