package vn.spring.rnd.Common.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.spring.rnd.Common.Models.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
  Permission findByName(String name);
}
