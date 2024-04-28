package vn.spring.rnd.Role.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import vn.spring.rnd.Auth.Security.PrePermission;
import vn.spring.rnd.Common.Dtos.BaseFilterDTO;
import vn.spring.rnd.Common.Dtos.BasePaginationDTO;
import vn.spring.rnd.Common.Dtos.BaseResponse;
import vn.spring.rnd.Common.Dtos.BaseResponsePagination;
import vn.spring.rnd.Permission.Constant.PermissionConstant;
import vn.spring.rnd.Role.Dtos.CreateRoleDTO;
import vn.spring.rnd.Role.Dtos.RoleDTO;
import vn.spring.rnd.Role.Dtos.RoleDetailDTO;
import vn.spring.rnd.Role.Services.RoleService;

@RestController
@RequestMapping("/api/roles")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Role")
public class RoleController {
  @Autowired
  private RoleService roleService;

  @PrePermission({ PermissionConstant.CREATE_NEW_ROLE })
  @PostMapping
  public BaseResponse<Long> create(@Valid @RequestBody CreateRoleDTO request) {
    final long id = roleService.create(request);
    return new BaseResponse<Long>(id);
  }

  @PrePermission({ PermissionConstant.VIEW_ALL_ROLE })
  @GetMapping
  public BaseResponsePagination<RoleDTO> findAll(BaseFilterDTO request) {
    final BasePaginationDTO<List<RoleDTO>> page = roleService.findAll(request);

    return new BaseResponsePagination<RoleDTO>(page.getPage(), page.getData());
  }

  @PutMapping("/{id}")
  @PrePermission({ PermissionConstant.EDIT_ANY_ROLE })
  public BaseResponse<Boolean> update(@Valid @RequestBody CreateRoleDTO request, @PathVariable("id") long id) {
    roleService.update(id, request);

    return new BaseResponse<Boolean>(true);
  }

  @DeleteMapping("/{id}")
  @PrePermission({ PermissionConstant.DELETE_ANY_ROLE })
  public BaseResponse<Boolean> destroy(@PathVariable("id") long id) {
    roleService.delete(id);

    return new BaseResponse<Boolean>(true);
  }

  @GetMapping("/{id}")
  @PrePermission({ PermissionConstant.EDIT_ANY_ROLE })
  public BaseResponse<RoleDetailDTO> show(@PathVariable("id") long id) {
    final RoleDetailDTO data = roleService.show(id);

    return new BaseResponse<RoleDetailDTO>(data);
  }
}
