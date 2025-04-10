package com.monolhybrid.est256.module.pharmacy.dto.pharmacyRequest;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PharmacyItemRequest {

    @NotNull(message = "Batch id jangan dibiarkan kosong")
    private Long batchId;

    @NotNull(message = "Kuantitas jangan dibiarkan kosong")
    private Integer qty;

}
