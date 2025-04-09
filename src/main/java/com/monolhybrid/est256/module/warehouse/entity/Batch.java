package com.monolhybrid.est256.module.warehouse.entity;

import com.monolhybrid.est256.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Batch extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @Column(nullable = false)
    private String productionCode;

    @Column(nullable = false)
    private LocalDate expDate;

    @Builder.Default
    @Column(nullable = false)
    private Integer totalWarehouseStock = 0;

    @Builder.Default
    @Column(nullable = false)
    private Integer totalInpatientStock = 0 ;

    @Builder.Default
    @Column(nullable = false)
    private Integer totalOutpatientStock = 0;
}
