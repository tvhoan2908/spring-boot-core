package vn.spring.rnd.Role.Services;

import java.util.List;

import vn.spring.rnd.Common.Dtos.BaseFilterDTO;
import vn.spring.rnd.Common.Dtos.BasePaginationDTO;
import vn.spring.rnd.Role.Dtos.CreateRoleDTO;
import vn.spring.rnd.Role.Dtos.RoleDTO;
import vn.spring.rnd.Role.Dtos.RoleDetailDTO;

public interface RoleService {
  Long create(CreateRoleDTO request);

  BasePaginationDTO<List<RoleDTO>> findAll(BaseFilterDTO request);

  void update(long id, CreateRoleDTO request);

  void delete(long id);

  RoleDetailDTO show(long id);
}
