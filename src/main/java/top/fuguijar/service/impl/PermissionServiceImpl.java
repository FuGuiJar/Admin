package top.fuguijar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.fuguijar.mapper.PermissionMapper;
import top.fuguijar.pojo.Permission;
import top.fuguijar.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findById(Integer permissionid) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_id",permissionid);
        return permissionMapper.selectList(queryWrapper);
    }
}
