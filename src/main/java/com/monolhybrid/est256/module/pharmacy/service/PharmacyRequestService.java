package com.monolhybrid.est256.module.pharmacy.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.pharmacy.dto.pharmacyRequest.PharmacyCreateRequest;
import com.monolhybrid.est256.module.pharmacy.dto.projection.PharmacyRequestProjection;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequestItem;
import com.monolhybrid.est256.module.pharmacy.repository.PharmacyRepository;
import com.monolhybrid.est256.module.warehouse.entity.Batch;
import com.monolhybrid.est256.module.warehouse.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PharmacyRequestService {

    private final PharmacyRepository pharmacyRepository;
    private final BatchService batchService;
    private final PharmacyRequestItemService pharmacyRequestItemService;

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

    public WebResponse<Void> approveRequest(Long pharmacyRequestId, String approver, PharmacyRequest.StatusRequest statusRequest) {
        PharmacyRequest request = getPharmacyRequestById(pharmacyRequestId);
        List<PharmacyRequestItem> items = pharmacyRequestItemService.getAllItemByRequestId(pharmacyRequestId);

        for (PharmacyRequestItem data : items) {
            batchService.decreaseStock(data.getBatch().getId(), data.getQuantity(), request.getUnitFrom());
        }

        request.setApprovedBy(approver);
        request.setApprovedDate(LocalDate.now());
        request.setStatus(statusRequest);
        pharmacyRepository.save(request);

        return WebResponse.<Void>builder()
                .message("Berhasil melakukan approve")
                .status(HttpStatus.OK.value())
                .build();
    }

    public PharmacyRequest getPharmacyRequestById(Long pharmacyId) {
        return pharmacyRepository.findById(pharmacyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data farmasi request dengan id " + pharmacyId + " tidak ditemukan"));
    }

    public List<PharmacyRequestProjection> getAllRequestStatusPending() {
        List<PharmacyRequestProjection> items =  pharmacyRepository.getAllPharmacyRequestStatusPending();

        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        return items;
    }

}
