package com.monolhybrid.est256.module.warehouse.controller;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.warehouse.dto.invoice.InvoiceCreateRequest;
import com.monolhybrid.est256.module.warehouse.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping(
            path = "/input",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void>input(@Valid @RequestBody InvoiceCreateRequest request){
        return invoiceService.createInvoice(request);
    }

}
