package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.MeatEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface MeatRepository extends CrudRepository<MeatEntity, String> {
    @Modifying
    @Query(value = "UPDATE tbl_meat tm SET tm.status =:status WHERE tm.id =:id", nativeQuery = true)
    void updateStatusMeat(String status, Integer id);

    @Query(value = "SELECT * FROM tbl_meat tm WHERE tm.status = :status AND DATE(tm.dt_register) = DATE(:dtRegister)", nativeQuery = true)
    List<MeatEntity> findByStatusAndDtRegister(String status, Date dtRegister);
}
