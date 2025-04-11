package com.monolhybrid.est256.module.pharmacy.dto.projection;

import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PharmacyRequestProjection {

    Long getPharmacyRequestId();
    LocalDate getRequestDate();
    String getRequestBy();
    PharmacyRequest.UnitType getUnitFrom();
    PharmacyRequest.StatusRequest getStatus();
    LocalDateTime getCreatedAtPharmacyRequest();
    LocalDateTime getUpdatedAtPharmacyRequest();

    Long getPharmacyRequestItemId();
    Long getPharmacyRequestIdItem();
    Long getBatchId();
    Integer getQuantity();
    LocalDateTime getCreatedAtPharmacyRequestItem();
    LocalDateTime getUpdatedAtPharmacyRequestItem();

}
