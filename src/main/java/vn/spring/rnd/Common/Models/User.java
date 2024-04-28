package vn.spring.rnd.Common.Models;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.spring.rnd.User.Constant.EAccountType;
import vn.spring.rnd.User.Constant.EUserStatus;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends CoreModel {
  @Column(nullable = false, unique = true, length = 100)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "status")
  private Integer status = EUserStatus.ACTIVE.value;

  @Enumerated(EnumType.STRING)
  @Column(name = "account_type")
  private EAccountType accountType = EAccountType.AUTHENTICATED;

  @Column(name = "token_expired_at")
  private Timestamp tokenExpiredAt;

  @ManyToMany
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
}
