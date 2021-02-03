package top.fuguijar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fuguijar.pojo.Role;

import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
public interface RoleService extends IService<Role> {
    List<Role> findById(Integer roleid);
}
