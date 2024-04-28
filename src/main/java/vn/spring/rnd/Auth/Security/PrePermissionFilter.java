package vn.spring.rnd.Auth.Security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.spring.rnd.Auth.Services.UserDetailsImpl;
import vn.spring.rnd.Common.Exception.ForbidenException;
import vn.spring.rnd.User.Constant.EAccountType;
import vn.spring.rnd.User.Utils.UserUtils;

public class PrePermissionFilter extends OncePerRequestFilter {
  @Autowired
  private RequestMappingHandlerMapping requestMappingHandlerMapping;

  private static final Logger logger = LoggerFactory.getLogger(PrePermissionFilter.class);

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    try {
      HandlerMethod method = (HandlerMethod) requestMappingHandlerMapping.getHandler(request).getHandler();
      return !method.getMethod().isAnnotationPresent(PrePermission.class);
    } catch (Exception e) {
      return true;
    }
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      HandlerMethod method = (HandlerMethod) requestMappingHandlerMapping.getHandler(request).getHandler();
      PrePermission prePermission = method.getMethod().getAnnotation(PrePermission.class);
      if (prePermission != null) {
        String[] permissions = prePermission.value();
        UserDetailsImpl userDetailsImpl = UserUtils.getCurrentUser();
        if (userDetailsImpl.getAccountType().equals(EAccountType.SUPER_ADMIN)) {
          filterChain.doFilter(request, response);
          return;
        }
        if (userDetailsImpl.getAuthorities().isEmpty())
          throw new ForbidenException();

        for (String permisison : permissions) {
          if (userDetailsImpl.getAuthorities().contains(new SimpleGrantedAuthority(permisison))) {
            filterChain.doFilter(request, response);
            return;
          }
        }
        throw new ForbidenException();
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      logger.error("PrePermissionFilter-Exception: {}", e.getMessage());
      if (e instanceof ForbidenException) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
      } else {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
      }

    }
  }
}
