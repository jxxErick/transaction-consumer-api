package com.consumer.management.api.Service.Interface;

import com.consumer.management.api.Model.Request.CreateOrderRequest;
import com.consumer.management.api.Model.Response.CreateOrderResponse;
import com.consumer.management.api.Model.Response.ItemsResponse;

import java.util.List;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest request);
    List<ItemsResponse> getItemsByOrderId(int orderId);

}
