package vn.spring.rnd.Common.Dtos;

import org.springframework.http.HttpStatus;

public class BaseResponse<T> {
  public Boolean success = true;
  public HttpStatus status = HttpStatus.OK;
  public String message = "Success";
  public T data = null;

  public BaseResponse(T data) {
    this.data = data;
  }

  public BaseResponse(T data, Boolean success, HttpStatus status) {
    this.data = data;
    this.success = success;
    this.status = status;
  }

  public BaseResponse(T data, Boolean success, HttpStatus status, String message) {
    this.data = data;
    this.success = success;
    this.status = status;
    this.message = message;
  }
}
