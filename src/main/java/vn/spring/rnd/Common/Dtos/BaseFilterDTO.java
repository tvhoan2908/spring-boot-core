package vn.spring.rnd.Common.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseFilterDTO {
  private int page = 1;
  private int size = 20;
}
