package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.MeatEntity;
import org.springframework.data.repository.CrudRepository;

public interface MeatRepository extends CrudRepository<MeatEntity, String> {
}
