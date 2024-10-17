package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.MeatEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MeatRepository extends CrudRepository<MeatEntity, String> {
    @Modifying
    @Query(value = "UPDATE tbl_meat ta SET tm.status =:status WHERE tm.id =:id", nativeQuery = true)
    void updateStatusMeat(String status, Integer id);
}
