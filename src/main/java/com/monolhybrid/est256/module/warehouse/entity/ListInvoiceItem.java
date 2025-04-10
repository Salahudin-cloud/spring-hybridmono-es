package com.monolhybrid.est256.module.warehouse.entity;

import com.monolhybrid.est256.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListInvoiceItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer totalPriceDrug;
}
