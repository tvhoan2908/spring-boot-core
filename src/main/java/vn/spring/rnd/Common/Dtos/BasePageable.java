package vn.spring.rnd.Common.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePageable {
  private int totalPages;
  private long totalElements;
  private int numberOfElements;
  private int size;
  private int page;
  private boolean first;
  private boolean last;
  private boolean empty;
}
