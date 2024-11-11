package com.consumer.management.api.Repository.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_packed_lunch")
public class PackedLunchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_packed_lunch_weight", nullable = false)
    private Integer packedLunchWeight;

    @Column(name = "id_order", nullable = false)
    private Integer order;

    @OneToMany(mappedBy = "packedLunchEntity", cascade = CascadeType.ALL)
    private List<FoodPackedLunchEntity> foodPackedLunchEntity;

}
