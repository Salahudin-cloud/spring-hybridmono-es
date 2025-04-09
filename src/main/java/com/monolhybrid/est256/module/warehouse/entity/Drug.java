package com.monolhybrid.est256.module.warehouse.entity;

import com.monolhybrid.est256.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drug extends BaseEntity {
    @Column(nullable = false, name = "drug_name")
    private String drugName;

    @Column(nullable = false)
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrugCategory category;

    public enum DrugCategory {
        TABLET, SYRUP, CAPSULE
    }
}
