package vn.spring.rnd.Auth.Services;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vn.spring.rnd.Auth.Dtos.JwtUserDto;
import vn.spring.rnd.User.Constant.EAccountType;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private Long id;
  private String username;
  private String fullName;
  private String email;
  private EAccountType accountType;
  private String password;
  private List<String> permissions;
  private Timestamp createdAt;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserDetailsImpl build(JwtUserDto user) {
    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getFullName(),
        user.getEmail(),
        user.getAccountType(),
        user.getPassword(),
        user.getPermissions(),
        user.getCreatedAt(),
        user.getAuthorities());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);

  }

  public JwtUserDto toDTO() {
    JwtUserDto jwtUserDto = new JwtUserDto();
    jwtUserDto.setId(id);
    jwtUserDto.setFullName(fullName);
    jwtUserDto.setAccountType(accountType);
    jwtUserDto.setAuthorities(authorities);
    jwtUserDto.setCreatedAt(createdAt);
    jwtUserDto.setUsername(username);
    jwtUserDto.setEmail(email);
    jwtUserDto.setPermissions(permissions);

    return jwtUserDto;
  }
}
