package vn.spring.rnd.Role.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.spring.rnd.Common.Dtos.BaseFilterDTO;
import vn.spring.rnd.Common.Dtos.BasePageRequest;
import vn.spring.rnd.Common.Dtos.BasePaginationDTO;
import vn.spring.rnd.Common.Exception.NotFoundException;
import vn.spring.rnd.Common.Models.Role;
import vn.spring.rnd.Common.Repository.RoleRepository;
import vn.spring.rnd.Common.Utils.DateUtils;
import vn.spring.rnd.Role.Dtos.CreateRoleDTO;
import vn.spring.rnd.Role.Dtos.RoleDTO;
import vn.spring.rnd.Role.Dtos.RoleDetailDTO;
import vn.spring.rnd.Role.Dtos.RoleTransformer;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoleTransformer roleTransformer;

  @Override
  public Long create(CreateRoleDTO request) {
    Role role = request.toEntity();
    role.setPermissions(request.toPermissions());

    roleRepository.save(role);

    return role.getId();
  }

  @Override
  public BasePaginationDTO<List<RoleDTO>> findAll(BaseFilterDTO request) {
    Pageable pageable = BasePageRequest.of(request.getPage(), request.getSize(), Sort.by("id").descending());
    Page<Object[]> page = roleRepository.findAllRoles(pageable);
    final List<RoleDTO> rolesDto = roleTransformer.transform(page.getContent());

    return new BasePaginationDTO<List<RoleDTO>>(rolesDto, page);
  }

  @Override
  public void update(long id, CreateRoleDTO request) {
    Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException());
    role.setName(request.getName());
    role.setDescription(request.getDecription());
    role.setPermissions(request.toPermissions());
    roleRepository.save(role);
  }

  @Override
  @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
  public void delete(long id) {
    Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException());
    role.setDeletedAt(DateUtils.getCurrentTimestamp());
    roleRepository.save(role);
    roleRepository.deleteUserRole(id);
  }

  @Override
  public RoleDetailDTO show(long id) {
    List<Object[]> rs = roleRepository.findRoleById(id);
    if (rs.isEmpty())
      throw new NotFoundException();

    return roleTransformer.toDetailDTO(rs);
  }

}
