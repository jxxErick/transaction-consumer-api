package com.consumer.management.api.Model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    ERROR_INSERT_FOOD_OF_DAY(1, "001.001", "Erro ao inserir comidas", "Ocorreu um erro ao inserir comidas do dia", HttpStatus.BAD_REQUEST),
    ERROR_UPDATE_STATUS_FOOD(2, "001.002", "Erro ao editar comidas", "Ocorreu um erro ao editar comidas do dia", HttpStatus.BAD_REQUEST),
    ERROR_FIND_FOODS_OF_THE_DAY(3, "001.003", "Erro ao buscar comidas do dia", "Ocorreu um erro ao buscar comidas do dia", HttpStatus.BAD_REQUEST),




    ;




    private final Integer Code;
    private final String ErrorCode;
    private final String TituloMessage;
    private final String ErrorMessage;
    private final HttpStatus httpStatus;
}
