package vn.spring.rnd.Auth.Services;

import vn.spring.rnd.Auth.Dtos.LoginRequest;

public interface AuthService {
  String login(LoginRequest request);
}
