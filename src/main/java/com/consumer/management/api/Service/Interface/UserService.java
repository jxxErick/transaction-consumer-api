package com.consumer.management.api.Service.Interface;

import com.consumer.management.api.Model.Request.UserRequest;
import com.consumer.management.api.Model.Response.UserResponse;

public interface UserService {
    void createUser(UserRequest request);
    UserResponse login(UserRequest request);
}
