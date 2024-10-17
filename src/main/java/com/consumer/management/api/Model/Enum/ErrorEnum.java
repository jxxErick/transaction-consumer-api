package com.consumer.management.api.Model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    ERROR_INSERT_FOOD_OF_DAY(1, "001.001", "Erro ao inserir comidas", "Ocorreu um erro ao inserir comidas do dia", HttpStatus.BAD_REQUEST),

    ;




    private final Integer Code;
    private final String ErrorCode;
    private final String TituloMessage;
    private final String ErrorMessage;
    private final HttpStatus httpStatus;
}
