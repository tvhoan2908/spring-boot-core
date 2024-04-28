package vn.spring.rnd.Role.Dtos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import vn.spring.rnd.Common.Models.Permission;
import vn.spring.rnd.Common.Models.Role;

@Getter
@Setter
public class CreateRoleDTO {
  @NotEmpty()
  private String name;

  private String decription;

  @NotEmpty()
  private List<Long> permissionsId;

  public Role toEntity() {
    Role entity = new Role();
    entity.setName(name);
    entity.setDescription(decription);

    return entity;
  }

  public Set<Permission> toPermissions() {
    return this.getPermissionsId().stream().map(p -> Permission.toEntity(p))
        .collect(Collectors.toSet());
  }
}
