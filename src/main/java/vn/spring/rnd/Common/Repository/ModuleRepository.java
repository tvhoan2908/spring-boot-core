package vn.spring.rnd.Common.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.spring.rnd.Common.Models.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
  Module findByName(String name);
}
