package com.monolhybrid.est256.module.warehouse.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.pharmacy.dto.projection.PharmacyRequestProjection;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import com.monolhybrid.est256.module.pharmacy.service.PharmacyRequestService;
import com.monolhybrid.est256.module.warehouse.dto.requestapproval.PharmacyListItemResponse;
import com.monolhybrid.est256.module.warehouse.dto.requestapproval.PharmacyRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RequestApprovalService {

    private final PharmacyRequestService pharmacyRequestService;

    public WebResponse<Void> approvePharmacyRequest(Long pharmacyRequestId, String approver, PharmacyRequest.StatusRequest statusRequest) {
        return pharmacyRequestService.approveRequest(pharmacyRequestId, approver, statusRequest);
    }

    public WebResponse<List<PharmacyRequestResponse>> getAllPharmacyPending() {
        List<PharmacyRequestProjection> data = pharmacyRequestService.getAllRequestStatusPending();

        Map<Long, List<PharmacyRequestProjection>> mapPharmacyRequest = groupedByPharmacyRequest(data);

        List<PharmacyRequestResponse> pharmacyRequestResponses =
                mapPharmacyRequest
                        .entrySet()
                        .stream()
                        .map(this::MapToPharmacyRequestResponse)
                        .toList();

        return WebResponse.<List<PharmacyRequestResponse>>builder()
                .message("Berhasil mendapatkan data")
                .status(HttpStatus.OK.value())
                .data(pharmacyRequestResponses)
                .build();
    }

    private PharmacyRequestResponse MapToPharmacyRequestResponse(Map.Entry<Long, List<PharmacyRequestProjection>> pharmacyRequestRecord) {

        List<PharmacyRequestProjection> record = pharmacyRequestRecord.getValue();
        PharmacyRequestProjection first = record.getFirst();

        List<PharmacyListItemResponse> items = record
                .stream()
                .filter(x -> x.getPharmacyRequestItemId() != null)
                .collect(Collectors.groupingBy(PharmacyRequestProjection::getPharmacyRequestItemId))
                .entrySet()
                .stream()
                .map(this::mapToPharmacyLisItemResponse)
                .toList();

        return  PharmacyRequestResponse.builder()
                .pharmacyRequestId(first.getPharmacyRequestId())
                .requestDate(first.getRequestDate())
                .requestBy(first.getRequestBy())
                .unitFrom(first.getUnitFrom())
                .status(first.getStatus())
                .createdAtPharmacyRequest(first.getCreatedAtPharmacyRequest())
                .updatedAtPharmacyRequest(first.getUpdatedAtPharmacyRequest())
                .items(items)
                .build();

    }

    private PharmacyListItemResponse mapToPharmacyLisItemResponse(Map.Entry<Long,  List<PharmacyRequestProjection>> record) {

        PharmacyRequestProjection first = record.getValue().getFirst();

        return PharmacyListItemResponse.builder()
                .pharmacyRequestItemId(first.getPharmacyRequestItemId())
                .pharmacyRequestIdItem(first.getPharmacyRequestIdItem())
                .batchId(first.getBatchId())
                .quantity(first.getQuantity())
                .createdAtPharmacyRequestItem(first.getCreatedAtPharmacyRequestItem())
                .updatedAtPharmacyRequestItem(first.getUpdatedAtPharmacyRequestItem())
                .build();
    }

    private Map<Long, List<PharmacyRequestProjection>> groupedByPharmacyRequest(List<PharmacyRequestProjection> data) {
        return data.stream()
                .filter(x -> x.getPharmacyRequestId() != null)
                .collect(Collectors.groupingBy(PharmacyRequestProjection::getPharmacyRequestId));
    }
}
