package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.PackedLunchWeightEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PackedLunchWeightRepository extends CrudRepository<PackedLunchWeightEntity, String> {

    @Query(value = "SELECT * FROM tbl_packed_lunch_weight tplw WHERE tplw.name =:name", nativeQuery = true)
    PackedLunchWeightEntity findByName(String name);
}
