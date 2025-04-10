package com.monolhybrid.est256.module.warehouse.dto.invoice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceCreateRequest {

    @NotNull(message = "Tanggal faktur jangan dibiarkan kosong")
    private LocalDate invoiceDate;

    @NotEmpty(message = "Nama supplier jangan dibiarkan kosong")
    private String supplierName;

    @NotEmpty(message = "Nama staff jangan dibiarkan kosong")
    private String staffName;

    @NotNull(message = "Item faktur jangan dibiarkan kosong")
    private List<InvoiceListItem> items;

}
