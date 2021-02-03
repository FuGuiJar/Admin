package top.fuguijar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.fuguijar.pojo.User;

/**
 * @author fuguijar.top
 * @date 2021-01-29
 */

public interface UserService extends IService<User> {
    User findByUsername(String username);
}
