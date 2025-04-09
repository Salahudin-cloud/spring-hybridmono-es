package com.monolhybrid.est256.module.warehouse.entity;

import com.monolhybrid.est256.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice extends BaseEntity {
    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false, name = "staff_name")
    private String staffName;

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false, name = "total_amount")
    private Integer totalAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListInvoiceItem> listInvoiceItems;
}
