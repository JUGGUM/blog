package me.utils.error.exception;

import me.utils.error.ErrorCode;

public class BadRequestException extends CustomBaseException {

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode.getMessage(), errorCode);
  }

  public BadRequestException() {
    super(ErrorCode.INVALID_INPUT_VALUE);
  }

}
