package top.fuguijar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.fuguijar.mapper.UserRoleMapper;
import top.fuguijar.pojo.UserRole;
import top.fuguijar.service.UserRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-02-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> findById(Integer userid) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userid);
        return userRoleMapper.selectList(queryWrapper);
    }
}
