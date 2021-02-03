package top.fuguijar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fuguijar.pojo.RolePermission;

import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-02-01
 */
public interface RolePermissionService extends IService<RolePermission> {
    List<RolePermission> findById(Integer roleid);

}
