package vn.spring.rnd.Common.Models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "modules")
@Getter
@Setter
@NamedEntityGraphs({
    @NamedEntityGraph(name = "graph.Module.list", attributeNodes = {
        @NamedAttributeNode(value = "permissions")
    })
})
public class Module extends BaseModel {
  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  @OneToMany(mappedBy = "module")
  private Set<Permission> permissions;
}
