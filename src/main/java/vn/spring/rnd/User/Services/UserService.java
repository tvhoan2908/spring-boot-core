package vn.spring.rnd.User.Services;

import vn.spring.rnd.Auth.Dtos.JwtUserDto;

public interface UserService {
  JwtUserDto getMe();
}
