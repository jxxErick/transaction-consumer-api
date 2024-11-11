package com.consumer.management.api.Controller;

import com.consumer.management.api.Model.Request.UserRequest;
import com.consumer.management.api.Model.Response.UserResponse;
import com.consumer.management.api.Service.Interface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Método usado para salvar novo usuario")
    @PostMapping("/new")
    ResponseEntity<?> createUser(
            @RequestBody UserRequest userRequest
            ) {
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Método usado para fazer login")
    @PostMapping("/login")
    ResponseEntity<UserResponse> login(
            @RequestBody UserRequest userRequest
    ) {
        UserResponse userResponse = userService.login(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }
}
