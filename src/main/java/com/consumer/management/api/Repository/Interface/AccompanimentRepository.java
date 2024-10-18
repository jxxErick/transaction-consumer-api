package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.AccompanimentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AccompanimentRepository extends CrudRepository<AccompanimentEntity, String> {
    @Modifying
    @Query(value = "UPDATE tbl_accompaniment ta SET ta.status =:status WHERE ta.id =:id", nativeQuery = true)
    void updateStatusAccompaniment(String status, Integer id);

    @Query(value = "SELECT * FROM tbl_accompaniment ta WHERE ta.status = :stock AND DATE(ta.dt_register) = DATE(:dtRegister)", nativeQuery = true)
    List<AccompanimentEntity> findByStockAndDtRegister(String stock, Date dtRegister);
}
