package com.consumer.management.api.Exception.Handler;

import com.consumer.management.api.Exception.ConsumerException;
import com.consumer.management.api.Exception.Model.ErrorApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ConsumerExceptionHandler {


    @ExceptionHandler({ConsumerException.class})
    public ResponseEntity<Object> handleConsumerException(ConsumerException ex) {
        log.error("Consumer Exception: {}", ex.getMessage());

        ErrorApi error = new ErrorApi(
                ex.getErrorCode().hashCode(),
                ex.getErrorCode(),
                ex.getErrorMessage(),
                ex.getTitleError()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
        );
        return new ResponseEntity<>(
                error, headers, ex.getHttpStatus()
        );
    }
}
