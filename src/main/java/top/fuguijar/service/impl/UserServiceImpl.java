package top.fuguijar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fuguijar.mapper.UserMapper;
import top.fuguijar.pojo.Permission;
import top.fuguijar.pojo.Role;
import top.fuguijar.pojo.User;
import top.fuguijar.service.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public User findByUsername(String username){
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);

        Set<Role> roles = new HashSet<>();
        userRoleService.findById(user.getUserId()).forEach( userRole -> {
            roleService.findById(userRole.getRoleId()).forEach(role -> {
                Set<Permission> permissions = new HashSet<>();
                rolePermissionService.findById(role.getRoleId()).forEach(rolePermission -> {
                    permissionService.findById(rolePermission.getPermissionId()).forEach(permission -> {
                       permissions.add(permission);
                   });
                    role.setPermissions(permissions);
                });
                roles.add(role);
            });
            user.setRoles(roles);
        });
        return user;
    }





}
