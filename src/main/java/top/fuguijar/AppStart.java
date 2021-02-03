package top.fuguijar;

import org.apache.catalina.Engine;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.session.StandardManager;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import top.fuguijar.constants.AppConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.*;

/**
 * @author fuguijar.top
 * @date 2021-01-28
 */
public class AppStart {
    public static void main(String[] args)  throws Exception {
        /*Banner ADMIN_ERP*/
        AppConstants.ADMIN_ERP();
        /*获取 web pages 路径*/
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resolverResource = pathMatchingResourcePatternResolver.getResource(AppConstants.STATIC_RESOURCES_NAME+"/"+AppConstants.STATIC_PROJECT_NAME);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        StandardContext ctx = (StandardContext)  tomcat.addWebapp("/", resolverResource.getFile().getAbsolutePath());
        /*添加SpringMVC提供的编码过滤器*/
        FilterDef encoding = new FilterDef();
        encoding.addInitParameter("encoding", "UTF-8");
        encoding.setFilter(new CharacterEncodingFilter());
        encoding.setFilterName(CharacterEncodingFilter.class.getSimpleName());
        ctx.addFilterDef(encoding);

        /*shiroFilter*/
        FilterDef shiroFilter = new FilterDef();
        shiroFilter.setFilter(new DelegatingFilterProxy());
        shiroFilter.setFilterName("shiroFilter");
        shiroFilter.addInitParameter("targetFilterLifecycle","true");
        ctx.addFilterDef(shiroFilter);

        /*Filter Mapping*/
        FilterMap filter1mapping = new FilterMap();
        filter1mapping.setFilterName(CharacterEncodingFilter.class.getSimpleName());
        filter1mapping.setFilterName("shiroFilter");
        filter1mapping.addURLPattern("/*");
        ctx.addFilterMap(filter1mapping);

        /*Banner FUGUIJAR*/
        AppConstants.FUGUIJAR();
        /*启动*/
        tomcat.start();
        tomcat.getServer().await();
    }
}
