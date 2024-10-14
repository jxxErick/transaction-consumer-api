package com.consumer.management.api.Repository.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_packed_lunch")
public class PackedLunchWeightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "quantityAccompaniment", nullable = false)
    private int quantityAccompaniment;

    @Column(name = "quantityMeat", nullable = false)
    private int quantityMeat;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
