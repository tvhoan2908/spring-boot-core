package vn.spring.rnd.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import vn.spring.rnd.Auth.Dtos.JwtResponseDto;
import vn.spring.rnd.Auth.Dtos.LoginRequest;
import vn.spring.rnd.Auth.Services.AuthService;
import vn.spring.rnd.Common.Dtos.BaseResponse;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public BaseResponse<JwtResponseDto> login(@Valid @RequestBody LoginRequest request) {
    String token = authService.login(request);

    return new BaseResponse<JwtResponseDto>(new JwtResponseDto(token));
  }
}
