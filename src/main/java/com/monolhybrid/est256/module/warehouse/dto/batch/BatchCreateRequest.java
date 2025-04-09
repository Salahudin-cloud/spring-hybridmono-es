package com.monolhybrid.est256.module.warehouse.dto.batch;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BatchCreateRequest {
    @NotNull(message = "Drug id jangan dibiarkan kosong")
    private Long drugId;

    @NotEmpty(message = "Kode produksi jangan dibiarkan kosong")
    private String productionCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Tanggal kadaluarsa jangan dibiarkan kosong")
    private LocalDate expDate;
}
