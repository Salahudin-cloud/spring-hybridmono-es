package com.monolhybrid.est256.module.pharmacy.controller;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.pharmacy.dto.pharmacyRequest.PharmacyCreateRequest;
import com.monolhybrid.est256.module.pharmacy.service.PharmacyRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/pharmacy-request")
public class PharmacyRequestController {

    private final PharmacyRequestService pharmacyRequestService;

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void> createRequest(@Valid @RequestBody PharmacyCreateRequest request){
        return pharmacyRequestService.createPharmacyRequest(request);
    }

}
