package com.monolhybrid.est256.module.warehouse.dto.requestapproval;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PharmacyListItemResponse {

    private Long pharmacyRequestItemId;
    private Long pharmacyRequestIdItem;
    private Long batchId;
    private Integer quantity;
    private LocalDateTime createdAtPharmacyRequestItem;
    private LocalDateTime updatedAtPharmacyRequestItem;

}
