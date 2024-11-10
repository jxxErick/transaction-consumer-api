package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.PackedLunchEntity;
import org.springframework.data.repository.CrudRepository;

public interface PackedLunchRepository extends CrudRepository<PackedLunchEntity, String> {
}
