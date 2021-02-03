package top.fuguijar.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {
  private static final long serialVersionUID =  3170696976210007406L;
  @TableId(value = "user_id",type = IdType.AUTO)
  private Integer userId;
  private String password;
  private String username;
  @TableField(exist = false)
  private Set<Role> roles;


  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", password='" + password + '\'' +
            ", username='" + username + '\'' +
            ", roles=" + roles +
            '}';
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
