package me.utils.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private String code;
  private String message;
  private HttpStatus status;

  private ErrorResponse(final ErrorCode code) {
    this.code = code.getCode();
    this.message = code.getMessage();
    this.status = code.getStatus();
  }

  public ErrorResponse(final ErrorCode code, final String message) {
    this.code = code.getCode();
    this.message = message;
    this.status = code.getStatus();
  }

  public static ErrorResponse of(final ErrorCode code) {
    return new ErrorResponse(code);
  }

  public static ErrorResponse of(final ErrorCode code, final String message) {
    return new ErrorResponse(code, message);
  }

}
