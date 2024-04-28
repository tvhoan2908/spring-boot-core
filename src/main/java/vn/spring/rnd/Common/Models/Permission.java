package vn.spring.rnd.Common.Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "permissions")
public class Permission extends BaseModel {
  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "module_id")
  private Module module;

  @ManyToMany(mappedBy = "permissions")
  private Set<Role> roles = new HashSet<>();

  public static Permission toEntity(Long id) {
    Permission entity = new Permission();
    entity.setId(id);
    return entity;
  }
}
