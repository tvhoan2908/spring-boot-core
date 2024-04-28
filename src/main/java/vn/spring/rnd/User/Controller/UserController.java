package vn.spring.rnd.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.spring.rnd.Auth.Dtos.JwtUserDto;
import vn.spring.rnd.Common.Dtos.BaseResponse;
import vn.spring.rnd.User.Services.UserService;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public BaseResponse<JwtUserDto> getMe() {
    JwtUserDto response = userService.getMe();

    return new BaseResponse<JwtUserDto>(response);
  }
}
