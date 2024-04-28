package vn.spring.rnd.Common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "forbiden")
public class ForbidenException extends RuntimeException {
  public ForbidenException() {
    super("forbiden");
  }

  public ForbidenException(String message) {
    super(message);
  }

  public ForbidenException(String message, Throwable cause) {
    super(message, cause);
  }
}
