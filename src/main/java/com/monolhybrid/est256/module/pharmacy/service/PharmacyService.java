package com.monolhybrid.est256.module.pharmacy.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.pharmacy.dto.pharmacyRequest.PharmacyCreateRequest;
import com.monolhybrid.est256.module.pharmacy.dto.pharmacyRequest.PharmacyItemRequest;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequestItem;
import com.monolhybrid.est256.module.pharmacy.repository.PharmacyRepository;
import com.monolhybrid.est256.module.warehouse.entity.Batch;
import com.monolhybrid.est256.module.warehouse.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final BatchService batchService;

    public WebResponse<Void> createPharmacyRequest(PharmacyCreateRequest request) {
        PharmacyRequest newRequest = PharmacyRequest.builder()
                .requestDate(request.getRequestDate())
                .requestBy(request.getRequestBy())
                .unitFrom(request.getUnitType())
                .status(PharmacyRequest.StatusRequest.PENDING)
                .build();

        List<PharmacyRequestItem> itemsRequest = request.getItems().stream().map( item -> {
            Batch batch = batchService.getBatchById(item.getBatchId());

            return PharmacyRequestItem.builder()
                    .pharmacyRequest(newRequest)
                    .batch(batch)
                    .quantity(item.getQty())
                    .build();
        }).toList();

        newRequest.setPharmacyRequestItems(itemsRequest);

        pharmacyRepository.save(newRequest);

        return WebResponse.<Void>builder()
                .message("Berhasil membuat permohonan")
                .status(HttpStatus.CREATED.value())
                .build();
    }

}
