package com.monolhybrid.est256.module.warehouse.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
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
import org.springframework.web.server.ResponseStatusException;

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

    public Batch getBatchById(Long batchId){
        return batchRepository.findById(batchId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data batch dengan id " + batchId + " tidak ditemukan"));
    }

    public Batch getBatchByProductionCode(String productionCode){
        return batchRepository.findByProductionCode(productionCode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data batch dengan kode produksi " + productionCode + " tidak ditemukan"));
    }

    public Batch updateStockByProductionCode(String productionCode, int addedStock) {
        Batch batch = getBatchByProductionCode(productionCode);
        batch.setTotalWarehouseStock(batch.getTotalWarehouseStock() + addedStock);
        return batch;
    }

    public void decreaseStock(Long batchId, int stockRequest, PharmacyRequest.UnitType unitType) {
        Batch batch = getBatchById(batchId);
        switch (unitType) {
            case INPATIENT -> {
                batch.setTotalWarehouseStock(batch.getTotalWarehouseStock() - stockRequest);
                batch.setTotalInpatientStock(batch.getTotalInpatientStock() + stockRequest);
            }
            case OUTPATIENT -> {
                batch.setTotalWarehouseStock(batch.getTotalWarehouseStock() - stockRequest);
                batch.setTotalInpatientStock(batch.getTotalOutpatientStock() + stockRequest);
            }
            default -> {
                throw new IllegalArgumentException("Unit tidak terdaftar");
            }
        }

        batchRepository.save(batch);
    }

}
