package com.monolhybrid.est256.module.warehouse.controller;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import com.monolhybrid.est256.module.warehouse.dto.requestapproval.PharmacyRequestResponse;
import com.monolhybrid.est256.module.warehouse.service.RequestApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/approve-request")
public class RequestApprovalController {

    private final RequestApprovalService requestApprovalService;

    @PatchMapping(
            path = "/pharmacy-batch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void> approveRequestPharmacyBatch(@RequestParam Long pharmacyRequestId, @RequestParam String approver, @RequestParam PharmacyRequest.StatusRequest statusRequest){
        return requestApprovalService.approvePharmacyRequest(pharmacyRequestId, approver, statusRequest);
    }

    @GetMapping(
            path = "/pharmacy-all-pending",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<PharmacyRequestResponse>> getAllPendingPharmacyRequest() {
        return requestApprovalService.getAllPharmacyPending();
    }
}
