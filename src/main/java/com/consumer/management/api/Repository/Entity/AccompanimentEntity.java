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
@Table(name = "tbl_accompaniment")
public class AccompanimentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "accompaniment", nullable = false)
    private String accompaniment;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "dt_register", nullable = false)
    private String dtRegister;
}
