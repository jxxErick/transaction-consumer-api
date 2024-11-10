package com.consumer.management.api.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackedLunchRequest {

    private String weight;
    private List<Integer> accompaniments;
    private List<Integer> meats;
}
