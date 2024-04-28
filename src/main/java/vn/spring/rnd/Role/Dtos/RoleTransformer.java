package vn.spring.rnd.Role.Dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.spring.rnd.Common.Dtos.BasicDTO;

@Component
public class RoleTransformer {
  public List<RoleDTO> transform(List<Object[]> rs) {
    final Map<Long, RoleDTO> dtoMap = new HashMap<>();
    for (Object[] o : rs) {
      Long id = ((Number) o[0]).longValue();
      RoleDTO dto = dtoMap.get(id);
      if (dto == null) {
        dto = new RoleDTO();
        dto.setId(id);
        dto.setDescription((String) o[4]);
        dto.setName((String) o[3]);
        dto.setCreatedAt((Timestamp) o[1]);
        dto.setUpdatedAt((Timestamp) o[2]);
      }

      dtoMap.putIfAbsent(id, dto);
    }

    return new ArrayList<>(dtoMap.values());
  }

  public RoleDetailDTO toDetailDTO(List<Object[]> rs) {
    RoleDetailDTO dto = null;
    for (Object[] o : rs) {
      if (dto == null) {
        dto = new RoleDetailDTO();
        dto.setId((long) o[0]);
        dto.setCreatedAt((Timestamp) o[3]);
        dto.setName((String) o[1]);
        dto.setDescription((String) o[2]);
      }
      if (((long) o[4]) > 0) {
        dto.addPermission(new BasicDTO((long) o[4], (String) o[5]));
      }
    }

    return dto;
  }
}
