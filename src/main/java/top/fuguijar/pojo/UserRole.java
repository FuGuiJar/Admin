package top.fuguijar.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


@TableName("user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID =  1303502646634920795L;


  private Integer userId;
  private Integer roleId;

  public UserRole() {
  }

  @Override
  public String toString() {
    return "UserRole{" +
            "userId=" + userId +
            ", roleId=" + roleId +
            '}';
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

}
