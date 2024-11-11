package com.consumer.management.api.Repository.Interface;

import com.consumer.management.api.Repository.Entity.FoodPackedLunchEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodPackedLunchRepository extends CrudRepository<FoodPackedLunchEntity, String> {

    @Query(value = """
        SELECT ta.accompaniment, tm.meat, tpl.id
        FROM tbl_food_packed_lunch tfpl
        INNER JOIN tbl_packed_lunch tpl ON tpl.id = tfpl.id_packed_lunch
        INNER JOIN tbl_order tor ON tpl.id_order = tor.id
        LEFT JOIN tbl_accompaniment ta ON ta.id = tfpl.id_accompaniment
        LEFT JOIN tbl_meat tm ON tm.id = tfpl.id_meat
        WHERE tor.id = :orderId
        """, nativeQuery = true)
    List<Object[]> findItemsByOrderId(@Param("orderId") int orderId);
}
