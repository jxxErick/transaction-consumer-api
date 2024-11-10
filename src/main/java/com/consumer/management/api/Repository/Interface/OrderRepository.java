package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
}
