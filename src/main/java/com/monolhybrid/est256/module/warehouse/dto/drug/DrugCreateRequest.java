package com.monolhybrid.est256.module.warehouse.dto.drug;

import com.monolhybrid.est256.module.warehouse.entity.Drug;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DrugCreateRequest {

    @NotEmpty(message = "Nama obat jangan dibiarkan kosong")
    private String drugName;

    @NotNull(message = "Harga obat jangan dibiarkan kosong")
    private Integer price;

    @NotNull(message = "Kategori obat jangan dibiarkan kosong")
    private Drug.DrugCategory category;

}
