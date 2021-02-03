package top.fuguijar.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.fuguijar.pojo.User;
import top.fuguijar.utils.StatelessToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
@RestController
public class TestController {

    @Autowired
    private Environment environment;

    @RequestMapping("yml")
    public void a(){
        String app = environment.getProperty("app");
        System.out.println(app);
    }

    /**
     * 登录测试
     * @param
     * @return
     */
//    @RequestMapping(value = "/login")
    public Map<String, Object> login(String u,String p) {
        Map<String,Object> jsonObject = new HashMap<>();

        StatelessToken statelessToken = new StatelessToken(u,p);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(statelessToken);
//            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 鉴权测试
     * @param
     * @return
     */
//    @RequestMapping(value = "/check")
//    @RequiresRoles("guest")
//    public String check() {
//        return "check";
//    }
}
