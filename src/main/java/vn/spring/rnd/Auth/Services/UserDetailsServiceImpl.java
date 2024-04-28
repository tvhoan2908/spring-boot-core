package vn.spring.rnd.Auth.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.spring.rnd.Auth.Dtos.JwtUserDto;
import vn.spring.rnd.Common.Models.User;
import vn.spring.rnd.Common.Repository.UserRepository;
import vn.spring.rnd.User.Constant.EUserStatus;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username).orElseThrow();

    if (!user.getStatus().equals(EUserStatus.ACTIVE.value)) {
      throw new UsernameNotFoundException("Tài khoản " + username + " chưa được kích hoạt hoặc đã bị khoá.");
    }

    JwtUserDto jwtUserDto = new JwtUserDto(user);

    return UserDetailsImpl.build(jwtUserDto);
  }

}
