package com.consumer.management.api.Service.Impl;

import com.consumer.management.api.Exception.ConsumerException;
import com.consumer.management.api.Model.Enum.ErrorEnum;
import com.consumer.management.api.Model.Request.UserRequest;
import com.consumer.management.api.Model.Response.UserResponse;
import com.consumer.management.api.Repository.Entity.UserEntity;
import com.consumer.management.api.Repository.Interface.UserRepository;
import com.consumer.management.api.Service.Interface.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final HashMap<Integer, String> map = new HashMap<Integer, String>() {{
        put(1, "SELLER");
        put(2, "COOKER");
        put(3, "WAITER");
    }};

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserRequest request) {
        try{
            userRepository.save(mapRequestToEntity(request));
        } catch (Exception ex){
            throw new ConsumerException(ex, ErrorEnum.ERROR_INSERT_USER.getHttpStatus(),
                    ErrorEnum.ERROR_INSERT_USER.getErrorCode(),
                    ErrorEnum.ERROR_INSERT_USER.getTituloMessage(),
                    ErrorEnum.ERROR_INSERT_USER.getErrorMessage());
        }
    }

    @Override
    public UserResponse login(UserRequest request) {

        UserEntity entity = userRepository.findByUserName(request.getUsername());
        if(entity == null){
            throw new ConsumerException(ErrorEnum.ERROR_FIND_USER.getHttpStatus(),
                    ErrorEnum.ERROR_FIND_USER.getErrorCode(),
                    ErrorEnum.ERROR_FIND_USER.getTituloMessage(),
                    ErrorEnum.ERROR_FIND_USER.getErrorMessage());
        }

        try{

            if(!entity.getPassword().equals(request.getPassword())){
                throw new Exception();
            }

            return mapEntityToResponse(entity);
        } catch (Exception ex){
            throw new ConsumerException(ex, ErrorEnum.ERROR_LOGIN.getHttpStatus(),
                    ErrorEnum.ERROR_LOGIN.getErrorCode(),
                    ErrorEnum.ERROR_LOGIN.getTituloMessage(),
                    ErrorEnum.ERROR_LOGIN.getErrorMessage());
        }
    }

    private UserResponse mapEntityToResponse(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setRole(entity.getRole());
        return response;
    }

    private UserEntity mapRequestToEntity(UserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(0);
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword());
        userEntity.setRole(map.get(request.getRole()));
        return userEntity;
    }
}
