package com.consumer.management.api.Controller;

import com.consumer.management.api.Model.Request.CreateOrderRequest;
import com.consumer.management.api.Model.Response.CreateOrderResponse;
import com.consumer.management.api.Model.Response.ItemsResponse;
import com.consumer.management.api.Service.Interface.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Este método é usado para criar novos pedidos")
    @PostMapping("/post")
    ResponseEntity<CreateOrderResponse> createOrder(
            @RequestBody CreateOrderRequest createOrderRequest
    ) {
        CreateOrderResponse createOrderResponse = orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok().body(createOrderResponse);
    }

    @Operation(summary = "Este método é usado para trazer pedidos")
    @GetMapping("")
    ResponseEntity<List<ItemsResponse>> createOrder(
            @RequestParam int id
    ) {
        List<ItemsResponse> createOrderResponse = orderService.getItemsByOrderId(id);
        return ResponseEntity.ok().body(createOrderResponse);
    }
}
