package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    @Query(value = "SELECT * FROM tbl_user tu WHERE tu.username =:username", nativeQuery = true)
    UserEntity findByUserName(String username);
}
