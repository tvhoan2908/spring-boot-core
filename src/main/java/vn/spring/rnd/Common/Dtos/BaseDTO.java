package vn.spring.rnd.Common.Dtos;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
  protected Long id;
  protected Timestamp createdAt;
  protected Timestamp updatedAt;
}
