package com.monolhybrid.est256.module.pharmacy.entity;

import com.monolhybrid.est256.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PharmacyRequest extends BaseEntity {
    
    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "request_by", nullable = false)
    private String requestBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitType unitFrom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRequest status;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @OneToMany(mappedBy = "pharmacyRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyRequestItem> pharmacyRequestItems;

    public enum StatusRequest{
        PENDING, APPROVED, REJECTED
    }

    public enum UnitType {
        INPATIENT, OUTPATIENT
    }

}
