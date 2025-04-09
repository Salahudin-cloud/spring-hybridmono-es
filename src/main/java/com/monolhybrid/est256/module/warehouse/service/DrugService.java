package com.monolhybrid.est256.module.warehouse.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.warehouse.dto.drug.DrugCreateRequest;
import com.monolhybrid.est256.module.warehouse.entity.Drug;
import com.monolhybrid.est256.module.warehouse.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
@Transactional
public class DrugService {

    private final DrugRepository drugRepository;

    public WebResponse<Void> createDrug(DrugCreateRequest request){
        Drug newDrug = Drug.builder()
                .drugName(request.getDrugName())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();

        drugRepository.save(newDrug);

        return WebResponse.<Void>builder()
                .message("Berhasil menambahkan data obat")
                .status(HttpStatus.CREATED.value())
                .build();
    }

    public Drug getDrugById(Long drugId) {
        return drugRepository.findById(drugId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data obat dengan id " + drugId + " tidak ditemukan"));
    }
}
