package top.fuguijar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fuguijar.pojo.Permission;

import java.util.List;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> findById(Integer permissionid);
}
