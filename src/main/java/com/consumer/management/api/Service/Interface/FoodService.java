package com.consumer.management.api.Service.Interface;

import com.consumer.management.api.Model.Request.FoodRequest;
import com.consumer.management.api.Model.Response.FoodResponse;

public interface FoodService {
    FoodResponse createFoodsOfDay(FoodRequest foodRequest);

}