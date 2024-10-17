package com.consumer.management.api.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodResponse {
    private List<AccompanimentResponse> accompaniment;
    private List<MeatResponse> meat;
}
