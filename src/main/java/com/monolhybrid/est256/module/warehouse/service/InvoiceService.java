package com.monolhybrid.est256.module.warehouse.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.warehouse.dto.invoice.InvoiceCreateRequest;
import com.monolhybrid.est256.module.warehouse.dto.invoice.InvoiceListItem;
import com.monolhybrid.est256.module.warehouse.entity.Batch;
import com.monolhybrid.est256.module.warehouse.entity.Invoice;
import com.monolhybrid.est256.module.warehouse.entity.ListInvoiceItem;
import com.monolhybrid.est256.module.warehouse.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final BatchService batchService;

    public WebResponse<Void> createInvoice(InvoiceCreateRequest request){
        int totalAmount = 0;
        Invoice newInvoice = Invoice.builder()
                .invoiceDate(request.getInvoiceDate())
                .staffName(request.getStaffName())
                .supplierName(request.getSupplierName())
                .build();

        List<ListInvoiceItem> newItems = new ArrayList<>();


        for (InvoiceListItem data : request.getItems()) {
            totalAmount += data.getPriceMount();

            Batch batch = batchService.updateStockByProductionCode(data.getProductionCode(), data.getStock());

            ListInvoiceItem item = ListInvoiceItem.builder()
                    .batch(batch)
                    .quantity(data.getStock())
                    .totalPriceDrug(data.getPriceMount())
                    .build();

            item.setInvoice(newInvoice);

            newItems.add(item);
        }

        newInvoice.setListInvoiceItems(newItems);
        newInvoice.setTotalAmount(totalAmount);

        invoiceRepository.save(newInvoice);

        return WebResponse.<Void>builder()
                .message("Data invoice berhasil ditambahkan")
                .status(HttpStatus.OK.value())
                .build();
    }

}
