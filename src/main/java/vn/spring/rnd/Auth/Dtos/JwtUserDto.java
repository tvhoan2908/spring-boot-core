package vn.spring.rnd.Auth.Dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import vn.spring.rnd.Auth.Constant.AuthConstant;
import vn.spring.rnd.Common.Models.Permission;
import vn.spring.rnd.Common.Models.Role;
import vn.spring.rnd.Common.Models.User;
import vn.spring.rnd.User.Constant.EAccountType;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtUserDto implements Serializable {
  private static final long serialVersionUID = 123L;

  private Long id;

  private String username;

  @JsonIgnore
  private String password;

  private String email;

  private String fullName;

  private EAccountType accountType;

  private Timestamp createdAt;

  @JsonIgnore
  Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

  private List<String> permissions = new ArrayList<>();

  public JwtUserDto(Map<String, Object> map) {
    this.id = map.get("id") != null ? Long.parseLong(map.get("id").toString()) : null;
    this.username = map.get("username") != null ? map.get("username").toString() : null;
    this.password = map.get("password") != null ? map.get("password").toString() : null;
    this.email = map.get("email") != null ? map.get("email").toString() : null;
    this.fullName = map.get("fullName") != null ? map.get("fullName").toString() : null;
    this.accountType = map.get("accountType") != null ? (EAccountType) map.get("accountType") : null;
    this.createdAt = map.get("createdAt") != null ? (Timestamp) map.get("createdAt") : null;
  }

  public JwtUserDto(User user) {
    this.id = user.getId();
    this.accountType = user.getAccountType();
    this.createdAt = user.getCreatedAt();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.fullName = user.getFullName();
    this.email = user.getEmail();
    Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(getPrivileges(user.getRoles()),
        user.getAccountType().toString());
    this.setAuthorities(authorities);

    List<String> permissions = new ArrayList<>();
    authorities.forEach(item -> permissions.add(item.getAuthority()));
    this.setPermissions(permissions);
  }

  private List<String> getPrivileges(Collection<Role> roles) {
    List<String> privileges = new ArrayList<>();
    List<Permission> permissions = new ArrayList<>();
    roles.forEach(item -> permissions.addAll(item.getPermissions()));
    permissions.forEach(item -> privileges.add(item.getName()));

    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges, String accountType) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(AuthConstant.ROLE_PREFIX + accountType));
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }

    return authorities;
  }
}
