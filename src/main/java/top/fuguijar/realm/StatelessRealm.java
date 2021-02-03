package top.fuguijar.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.fuguijar.pojo.Permission;
import top.fuguijar.pojo.Role;
import top.fuguijar.pojo.User;
import top.fuguijar.service.UserService;
import top.fuguijar.utils.RedisUtil;
import top.fuguijar.utils.StatelessToken;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
public class StatelessRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**仅支持StatelessToken类型的Token
     * @param token
     * @return
     */
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();
        System.out.println(username);
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        User user = userService.findByUsername(username);
        System.out.println(user);
        for (Role role : user.getRoles()) {
            //2.1添加角色
            authorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //2.1.1添加权限
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        String username = statelessToken.getUsername();
        User user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }


        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName());
    }


}