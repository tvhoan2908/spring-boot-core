package vn.spring.rnd.Role.Dtos;

import lombok.Getter;
import lombok.Setter;
import vn.spring.rnd.Common.Dtos.BaseDTO;

@Getter
@Setter
public class RoleDTO extends BaseDTO {
  private String name;
  private String description;
}
