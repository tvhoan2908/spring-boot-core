package vn.spring.rnd.User.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import vn.spring.rnd.Auth.Services.UserDetailsImpl;

public class UserUtils {
  public static UserDetailsImpl getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null ? (UserDetailsImpl) authentication.getPrincipal() : null;
  }
}
