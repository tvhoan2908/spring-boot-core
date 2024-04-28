package vn.spring.rnd.Common.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.spring.rnd.Common.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Page<Role> findAll(Pageable pageable);

  @Query(value = "SELECT r.id, r.createdAt, r.updatedAt, r.name, r.description FROM Role r")
  Page<Object[]> findAllRoles(Pageable pageable);

  @Modifying
  @Query(value = "DELETE FROM users_roles WHERE role_id = ?", nativeQuery = true)
  int deleteUserRole(long id);

  @Query(value = "SELECT r.id, r.name, r.description, r.createdAt, p.id AS permissionId, p.name AS permissionName FROM Role r LEFT JOIN r.permissions p WHERE r.id = ?1")
  List<Object[]> findRoleById(long id);
}
