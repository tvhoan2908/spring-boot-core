package vn.spring.rnd.Permission.Dtos;

import java.util.List;

public record CreateModuleDTO(String name, String description, List<CreatePermissionDTO> permissions) {

}
