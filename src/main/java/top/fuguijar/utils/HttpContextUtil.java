package top.fuguijar.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
public class HttpContextUtil {

    private HttpContextUtil(){}

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getOrigin() {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        String origin = httpServletRequest.getHeader("Origin");
        System.err.println(origin);
        return origin;
    }
}
