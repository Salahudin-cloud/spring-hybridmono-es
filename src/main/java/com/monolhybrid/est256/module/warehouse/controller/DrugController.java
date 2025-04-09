package com.monolhybrid.est256.module.warehouse.controller;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.warehouse.dto.drug.DrugCreateRequest;
import com.monolhybrid.est256.module.warehouse.service.DrugService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/drug")
public class DrugController {

    private final DrugService drugService;

    @PostMapping(
            path = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void>create(@Valid @RequestBody DrugCreateRequest request) {
        return drugService.createDrug(request);
    }

}
