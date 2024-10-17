package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.AccompanimentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccompanimentRepository extends CrudRepository<AccompanimentEntity, String> {
    @Modifying
    @Query(value = "UPDATE tbl_accompaniment ta SET ta.status =:status WHERE ta.id =:id", nativeQuery = true)
    void updateStatusAccompaniment(String status, Integer id);
}
