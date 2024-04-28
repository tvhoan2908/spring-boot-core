package vn.spring.rnd.Common.Dtos;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasePaginationDTO<T> {
  private T data;
  private Page<?> page;
}
