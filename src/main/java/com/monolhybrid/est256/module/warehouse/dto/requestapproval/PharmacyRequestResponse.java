package com.monolhybrid.est256.module.warehouse.dto.requestapproval;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PharmacyListResponse {

    private Long pharmacyRequestId;
    private LocalDate requestDate;
    private String requestBy;
    private PharmacyRequest.UnitType unitFrom;
    private PharmacyRequest.StatusRequest status;
    private LocalDateTime createdAtPharmacyRequest;
    private LocalDateTime updatedAtPharmacyRequest;


}
