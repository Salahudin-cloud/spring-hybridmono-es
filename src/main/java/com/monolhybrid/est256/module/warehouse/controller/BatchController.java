package com.monolhybrid.est256.module.warehouse.controller;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.warehouse.dto.batch.BatchCreateRequest;
import com.monolhybrid.est256.module.warehouse.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/batch")
public class BatchController {

    private final BatchService batchService;

    @PostMapping(
            path = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void>create(@Valid @RequestBody BatchCreateRequest request) {
        return batchService.createBatch(request);
    }

}
