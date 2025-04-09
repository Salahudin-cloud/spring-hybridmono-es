package com.monolhybrid.est256.module.warehouse.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.warehouse.dto.batch.BatchCreateRequest;
import com.monolhybrid.est256.module.warehouse.entity.Batch;
import com.monolhybrid.est256.module.warehouse.entity.Drug;
import com.monolhybrid.est256.module.warehouse.entity.ListInvoiceItem;
import com.monolhybrid.est256.module.warehouse.repository.BatchRepository;
import com.monolhybrid.est256.module.warehouse.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BatchService {

    private final BatchRepository batchRepository;
    private final DrugService drugService;

    public WebResponse<Void>createBatch(BatchCreateRequest request) {
        Drug drug = drugService.getDrugById(request.getDrugId());

        Batch newBatch = Batch.builder()
                .drug(drug)
                .productionCode(request.getProductionCode())
                .expDate(request.getExpDate())
                .build();

        batchRepository.save(newBatch);

        return WebResponse.<Void>builder()
                .message("Berhasil menambahkan data batch")
                .status(HttpStatus.CREATED.value())
                .build();
    }


}
