package top.fuguijar.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("role_permission")
public class RolePermission implements Serializable {

  private static final long serialVersionUID =  1709205048078662280L;


  private Integer roleId;
  private Integer permissionId;

  public RolePermission() {
  }

  @Override
  public String toString() {
    return "RolePermission{" +
            "roleId=" + roleId +
            ", permissionId=" + permissionId +
            '}';
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public Integer getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Integer permissionId) {
    this.permissionId = permissionId;
  }

}
