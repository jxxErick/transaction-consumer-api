package com.consumer.management.api.Exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ConsumerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String errorCode;
    private String TitleError;
    private String errorMessage;

    public ConsumerException(Exception e, HttpStatus httpStatus, String errorCode, String TitleError, String errorMessage) {
        super(e);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.TitleError = TitleError;
        this.errorMessage = errorMessage;
    }

    public ConsumerException(HttpStatus httpStatus, String errorCode, String TitleError, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.TitleError = TitleError;
        this.errorMessage = errorMessage;
    }
}
