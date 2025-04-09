package com.monolhybrid.est256.module.warehouse.repository;

import com.monolhybrid.est256.module.warehouse.entity.ListInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListInvoiceItemRepository extends JpaRepository<ListInvoiceItem, Long> {
}
