package com.consumer.management.api.Repository.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_food_packed_lunch")
public class FoodPackedLunchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_meat", nullable = true)
    private Integer idMeat;

    @Column(name = "id_accompaniment", nullable = true)
    private Integer idAccompaniment;

    @Column(name = "id_accompaniment", nullable = true)
    private Integer idPackedLunch;


}
