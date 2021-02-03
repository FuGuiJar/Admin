package top.fuguijar.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.provider.MD5;
import top.fuguijar.constants.AppConstants;
import top.fuguijar.pojo.User;
import top.fuguijar.service.UserService;
import top.fuguijar.utils.AesUtil;
import top.fuguijar.utils.RedisUtil;
import top.fuguijar.utils.StatelessToken;
import top.fuguijar.vo.Result;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**自定义 过滤器
 * @author fuguijar.top
 * @date 2021-01-29
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest res = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;
        response.setContentType("text/html;charset=utf-8");
        Result result = new Result();

        String username = request.getParameter("u");
        String pwd = request.getParameter("p");
        String token = res.getHeader(AppConstants.TOKEN);
        Map<String,String[]> params=new HashMap<>(request.getParameterMap());

        String servletPath = res.getServletPath();

        if ("/login".equals(servletPath)) {
            System.out.println("login登录");
            try {
                /*判断该用户是否重复登录*/
                String hashName = AesUtil.aesEncryption(username);
                if (redisUtil.hasKey(hashName)) {
                    result.setData(hashName);
                    result.setMsg("重复登录");
                    result.setCode(AppConstants.RESULT_CODE_OK);
                    objectMapper.writeValue(response.getWriter(),result);
                    return false;
                }
                /*正常登录*/
                StatelessToken statelessToken = new StatelessToken(username,pwd,params,token);
                getSubject(request, response).login(statelessToken);
                User byUsername = userService.findByUsername(username);
                boolean set = redisUtil.set(AesUtil.aesEncryption(byUsername.getUsername()), byUsername, 180);
                if (set) {
                    result.setData(hashName);
                    result.setMsg("登录成功");
                    result.setCode(AppConstants.RESULT_CODE_OK);
                }else {
                    result.setMsg("登录失败");
                    result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
                }
            }catch (IncorrectCredentialsException e) {
                result.setMsg("密码错误");
                result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
            } catch (LockedAccountException e) {
                result.setMsg("登录失败，该用户已被冻结");
                result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
            } catch (AuthenticationException e) {
                result.setMsg("该用户不存在");
                result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
            } catch (Exception e) {
                result.setMsg("登录失败");
                result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
            }
        }else {
            System.out.println("token登录");
            /*是否携带token*/
            try {
                if (!StringUtils.isBlank(token)) {
                    /*验证token*/
                    if (redisUtil.hasKey(token)) {
                        User user = (User) redisUtil.get(token);
                        StatelessToken statelessToken = new StatelessToken(user.getUsername(),user.getPassword(),params,token);
                        try {
                            getSubject(request, response).login(statelessToken);
                            result.setMsg("登录成功");
                            result.setCode(AppConstants.RESULT_CODE_OK);
                        } catch (IncorrectCredentialsException e) {
                            result.setMsg("密码错误");
                            result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
                        } catch (LockedAccountException e) {
                            result.setMsg("登录失败，该用户已被冻结");
                            result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
                        } catch (AuthenticationException e) {
                            result.setMsg("该用户不存在");
                            result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
                        } catch (Exception e) {
                            result.setMsg("登录失败");
                            result.setCode(AppConstants.RESULT_CODE_SERVERERROR);
                        }
                    }else {
                        result.setCode(AppConstants.RESULT_CODE_UNAUTHORIZED);
                        result.setMsg("token登失败");
                    }
                }else {
                    result.setCode(AppConstants.RESULT_CODE_UNAUTHORIZED);
                    result.setMsg("token为null");
                }
            } catch (Exception e) {
                result.setCode(AppConstants.RESULT_CODE_UNAUTHORIZED);
                result.setMsg("token为null");
            }
        }
        objectMapper.writeValue(response.getWriter(),result);
        return false;
    }
    // 登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
    private void onLoginOK(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        httpResponse.getWriter().write("ok");
    }


}