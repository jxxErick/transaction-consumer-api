package com.consumer.management.api.Model.Response;

import com.consumer.management.api.Model.Request.PackedLunchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private BigDecimal totalPrice;
    private Integer orderId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<PackedLunchRequest> erros;
}
