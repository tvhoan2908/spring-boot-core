package vn.spring.rnd.Auth.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import vn.spring.rnd.Auth.Config.JwtTokenProvider;
import vn.spring.rnd.Auth.Dtos.LoginRequest;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public String login(LoginRequest request) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
    return jwtTokenProvider.createToken(userDetailsImpl.getUsername());
  }

}
