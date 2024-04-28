package vn.spring.rnd.Common.Dtos;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class BasePageRequest {
  public static PageRequest of(int page, int size) {
    page = page > 0 ? page - 1 : 0;
    size = size > 0 ? size : 20;
    return PageRequest.of(page, size);
  }

  public static PageRequest of(int page, int size, Sort sort) {
    page = page > 0 ? page - 1 : 0;
    size = size > 0 ? size : 20;
    return PageRequest.of(page, size, sort);
  }
}
