package com.monolhybrid.est256.module.warehouse.dto.invoice;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceListItem {

    @NotEmpty(message = "Kode produksi jangan dibiarkan kosong")
    private String productionCode;

    @NotNull(message = "Stock jangan dibiarkan kosong")
    private Integer stock;

    @NotNull(message = "Total harga item jangan dibiarkan kosong")
    private Integer priceMount;

}
