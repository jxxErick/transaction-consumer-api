package com.consumer.management.api.Repository.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_meat")
public class MeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "meat", nullable = false)
    private String meat;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "dt_register", nullable = false)
    private Date dtRegister;


}
