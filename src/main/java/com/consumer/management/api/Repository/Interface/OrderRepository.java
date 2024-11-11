package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

    @Query(value = "SELECT * FROM tbl_order WHERE status = 'PENDING'",nativeQuery = true)
    List<OrderEntity> findStatusPending();
}
