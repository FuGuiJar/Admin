package top.fuguijar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fuguijar.mapper.RolePermissionMapper;
import top.fuguijar.pojo.RolePermission;
import top.fuguijar.service.RolePermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-02-01
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> findById(Integer roleid) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleid);
        return rolePermissionMapper.selectList(queryWrapper);
    }

}
