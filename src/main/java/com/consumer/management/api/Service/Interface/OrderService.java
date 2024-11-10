package com.consumer.management.api.Service.Interface;

import com.consumer.management.api.Model.Request.CreateOrderRequest;
import com.consumer.management.api.Model.Response.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest request);
}
