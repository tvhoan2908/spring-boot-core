package vn.spring.rnd.User.Services;

import org.springframework.stereotype.Service;

import vn.spring.rnd.Auth.Dtos.JwtUserDto;
import vn.spring.rnd.Auth.Services.UserDetailsImpl;
import vn.spring.rnd.User.Utils.UserUtils;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public JwtUserDto getMe() {
    UserDetailsImpl userDetailsImpl = UserUtils.getCurrentUser();

    return userDetailsImpl.toDTO();
  }

}
