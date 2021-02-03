package top.fuguijar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fuguijar.pojo.UserRole;

import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-02-01
 */
public interface UserRoleService extends IService<UserRole> {
    List<UserRole> findById(Integer userid);
}
