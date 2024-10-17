package com.consumer.management.api.Exception.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ErrorApi {

    public ErrorApi(int httpStatusCode, String errorCode, String errorMessage, String titleError) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        TitleError = titleError;
    }

    private int httpStatusCode;
    private String errorCode;
    private String TitleError;
    private String errorMessage;
}