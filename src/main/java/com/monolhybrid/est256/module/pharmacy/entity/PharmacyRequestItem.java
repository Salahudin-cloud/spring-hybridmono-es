package com.monolhybrid.est256.module.pharmacy.entity;

import com.monolhybrid.est256.common.entity.BaseEntity;
import com.monolhybrid.est256.module.warehouse.entity.Batch;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class PharmacyRequestItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pharmacy_request_id", nullable = false)
    private PharmacyRequest pharmacyRequest;

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @Column(nullable = false)
    private Integer quantity;

}
