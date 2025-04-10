package com.monolhybrid.est256.module.pharmacy.dto.pharmacyRequest;

import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PharmacyCreateRequest {

    @NotNull(message = "Tanggal permintaan jangan dibiarkan kosong")
    private LocalDate requestDate;

    @NotEmpty(message = "Nama petugas request jangan dibiarkan kosong")
    private String requestBy;

    @NotNull(message = "Request unit jangan dibiarkan kosong")
    private PharmacyRequest.UnitType unitType;

    @NotNull(message = "Item diminta jangan dibiarkan kosong")
    private List<PharmacyItemRequest> items;

}
