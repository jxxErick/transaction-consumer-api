package com.consumer.management.api.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackedLunchWeightResponse {
    private int id;
    private int quantityAccompaniment;
    private int quantityMeat;
    private int weight;
    private String name;
    private BigDecimal price;
}
