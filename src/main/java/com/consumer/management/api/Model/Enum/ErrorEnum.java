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
    ERROR_INSERT_USER(4, "001.004", "Erro ao inserir usuario na base de dados", "Ocorreu um erro ao inserir usuario na base de dados", HttpStatus.BAD_REQUEST),
    ERROR_FIND_USER(5, "001.005", "Username não existe", "Email informado não existe na base de dados", HttpStatus.BAD_REQUEST),
    ERROR_LOGIN(6, "001.006", "Erro ao realizar login", "senhas nao são iguais ", HttpStatus.BAD_REQUEST),
    ERROR_CREATE_PACKED_LUNCH_WEIGHT(7, "001.007", "Erro ao estabelecer preço para tamanho da marmita", "Ocorreu um erro ao estabelecer o preço", HttpStatus.BAD_REQUEST),





    ;




    private final Integer Code;
    private final String ErrorCode;
    private final String TituloMessage;
    private final String ErrorMessage;
    private final HttpStatus httpStatus;
}
