package com.consumer.management.api.Model.Response;

import com.consumer.management.api.Model.Request.PackedLunchRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackedLunchResponse {

    private boolean success;
    private BigDecimal price;
    private Integer idWeight;
}
