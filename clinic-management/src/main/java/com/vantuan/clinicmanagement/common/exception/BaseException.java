package com.vantuan.clinicmanagement.common.exception;

import lombok.Getter;
import org.slf4j.MDC;

import com.vantuan.common.exception.GeneralException;

@Getter
public class BaseException extends GeneralException {

  public static final String TRACE_ID = "traceId";
  private final String traceId = MDC.get(TRACE_ID);

  public BaseException(Throwable cause) {
    super(cause);
  }

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  protected boolean isClientReadable() {
    return false;
  }
}
