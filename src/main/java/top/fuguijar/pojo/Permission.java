package top.fuguijar.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
public class Permission implements Serializable {
	private static final long serialVersionUID =  4921812605476281536L;
  @TableId(type = IdType.AUTO)
  private Integer permissionId;
  private String permission;
  private String permissionName;

  @Override
  public String toString() {
    return "Permission{" +
            "permissionId=" + permissionId +
            ", permission='" + permission + '\'' +
            ", permissionName='" + permissionName + '\'' +
            '}';
  }

  public Integer getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Integer permissionId) {
    this.permissionId = permissionId;
  }


  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }


  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }

}
