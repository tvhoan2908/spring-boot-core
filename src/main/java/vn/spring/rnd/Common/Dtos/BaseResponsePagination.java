package vn.spring.rnd.Common.Dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponsePagination<T> {
  public Boolean success = true;
  public HttpStatus status = HttpStatus.OK;
  public List<T> data = new ArrayList<>();
  private BasePageable pagination = new BasePageable();

  public BaseResponsePagination() {
  }

  private void updatePagination(Page<?> page) {
    this.pagination.setEmpty(page.isEmpty());
    this.pagination.setFirst(page.isFirst());
    this.pagination.setLast(page.isLast());
    this.pagination.setTotalElements(page.getTotalElements());
    this.pagination.setTotalPages(page.getTotalPages());
    this.pagination.setNumberOfElements(page.getNumberOfElements());
    this.pagination.setSize(page.getSize());
    this.pagination.setPage(page.getNumber() + 1);
  }

  public BaseResponsePagination(Page<T> page) {
    this.updatePagination(page);

    this.data = page.getContent();
  }

  public BaseResponsePagination(Page<?> page, List<T> data) {
    this.updatePagination(page);

    this.data = data;
  }

  public BaseResponsePagination(Page<T> page, Boolean success, HttpStatus httpStatus) {
    this.updatePagination(page);

    this.data = page.getContent();

    this.success = success;
    this.status = httpStatus;
  }

  public BaseResponsePagination(List<T> data, BaseFilterDTO request, long totalElements) {
    this.data = data;
    int page = (int) Math.ceil(totalElements / request.getSize());
    this.pagination.setEmpty(data.size() == 0);
    this.pagination.setFirst(request.getPage() == 1);
    this.pagination.setLast(request.getPage() == page);
    this.pagination.setPage(request.getPage());
    this.pagination.setNumberOfElements(data.size());
    this.pagination.setTotalPages(page);
    this.pagination.setSize(request.getSize());
    this.pagination.setTotalElements(totalElements);
  }
}
