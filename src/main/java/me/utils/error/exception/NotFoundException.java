package me.utils.error.exception;

import me.utils.error.ErrorCode;

public class NotFoundException extends CustomBaseException{

  public NotFoundException(ErrorCode errorCode) {
    super(errorCode.getMessage(), errorCode);
  }

  public NotFoundException() {
    super(ErrorCode.NOT_FOUND);
  }

}
