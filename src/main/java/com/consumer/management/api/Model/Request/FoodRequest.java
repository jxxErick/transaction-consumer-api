package com.consumer.management.api.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodRequest {
    private List<AccompanimentRequest> accompaniment;
    private List<MeatRequest> meat;
}
