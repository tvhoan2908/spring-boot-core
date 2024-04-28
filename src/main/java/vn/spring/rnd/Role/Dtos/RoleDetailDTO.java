package vn.spring.rnd.Role.Dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.spring.rnd.Common.Dtos.BaseDTO;
import vn.spring.rnd.Common.Dtos.BasicDTO;
import vn.spring.rnd.Common.Models.Role;

@Getter
@Setter
@NoArgsConstructor
public class RoleDetailDTO extends BaseDTO {
  private String name;
  private String description;

  private List<BasicDTO> permissions = new ArrayList<>();

  public RoleDetailDTO(Role entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.createdAt = entity.getCreatedAt();
    this.updatedAt = entity.getUpdatedAt();
    this.description = entity.getDescription();
    this.permissions = entity.getPermissions().stream().map(element -> new BasicDTO(element.getId(), element.getName()))
        .collect(Collectors.toList());
  }

  public void addPermission(BasicDTO dto) {
    this.permissions.add(dto);
  }
}
