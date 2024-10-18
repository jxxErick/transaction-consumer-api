package com.consumer.management.api.Controller;

import com.consumer.management.api.Model.Request.FoodRequest;
import com.consumer.management.api.Model.Request.UpdateFoodStatus;
import com.consumer.management.api.Model.Response.FoodResponse;
import com.consumer.management.api.Service.Interface.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Operation(summary = "Este método é usado para registrar as comidas do dia")
    @PostMapping("/day/create")
    public ResponseEntity<FoodResponse> postNewFoods(
            @RequestBody FoodRequest foodRequest) {
        FoodResponse response = foodService.createFoodsOfDay(foodRequest);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Este método é usado para retornar as comidas do dia")
    @GetMapping("/day/list")
    public ResponseEntity<FoodResponse> getFoodsByDay(
            @RequestParam("stock") boolean stock
    ){
        FoodResponse response = foodService.getFoodOfDay(stock);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Este método é usado para tirar e colocar comidas no estoque")
    @PatchMapping("/day/edit")
    public ResponseEntity<Boolean> updateFood(
            @RequestBody UpdateFoodStatus updateFoodStatus
    ){
        return ResponseEntity.ok().body(foodService.updateFoodOfDay(updateFoodStatus));
    }
}
