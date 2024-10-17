package com.consumer.management.api.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFoodStatus {
    private Integer id;
    private boolean stock;
    private boolean meat;
    private boolean accompaniment;

}
