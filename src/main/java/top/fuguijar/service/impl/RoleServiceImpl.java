package top.fuguijar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.fuguijar.mapper.RoleMapper;
import top.fuguijar.pojo.Role;
import top.fuguijar.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findById(Integer roleid) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleid);
        return roleMapper.selectList(queryWrapper);
    }


}
