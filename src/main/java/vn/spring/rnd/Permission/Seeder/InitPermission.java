package vn.spring.rnd.Permission.Seeder;

import java.util.ArrayList;
import java.util.List;

import vn.spring.rnd.Permission.Constant.PermissionConstant;
import vn.spring.rnd.Permission.Dtos.CreateModuleDTO;
import vn.spring.rnd.Permission.Dtos.CreatePermissionDTO;

public class InitPermission {
  public static final List<CreateModuleDTO> init() {
    return new ArrayList<>() {
      {
        add(new CreateModuleDTO("User", "Tài khoản", new ArrayList<>() {
          {
            add(new CreatePermissionDTO(PermissionConstant.CREATE_NEW_USER, "Thêm mới"));
            add(new CreatePermissionDTO(PermissionConstant.EDIT_ANY_USER, "Sửa"));
            add(new CreatePermissionDTO(PermissionConstant.VIEW_ALL_USER, "Xem"));
            add(new CreatePermissionDTO(PermissionConstant.DELETE_ANY_USER, "Xóa"));
          }
        }));
        add(new CreateModuleDTO("Role", "Vai trò", new ArrayList<>() {
          {
            add(new CreatePermissionDTO(PermissionConstant.CREATE_NEW_ROLE, "Thêm mới"));
            add(new CreatePermissionDTO(PermissionConstant.EDIT_ANY_ROLE, "Sửa"));
            add(new CreatePermissionDTO(PermissionConstant.VIEW_ALL_ROLE, "Xem"));
            add(new CreatePermissionDTO(PermissionConstant.DELETE_ANY_ROLE, "Xóa"));
          }
        }));
      }
    };
  }
}
