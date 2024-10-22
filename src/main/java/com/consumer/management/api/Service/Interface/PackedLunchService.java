package com.consumer.management.api.Service.Interface;

import com.consumer.management.api.Model.Request.PackedLunchWeightRequest;
import com.consumer.management.api.Model.Response.PackedLunchWeightResponse;

import java.util.List;

public interface PackedLunchService {
    PackedLunchWeightResponse getPackedLunchWeight(String name);
    PackedLunchWeightResponse createPackedLunchWeight(PackedLunchWeightRequest request);
    List<PackedLunchWeightResponse> listPackedLunchWeights();
}
