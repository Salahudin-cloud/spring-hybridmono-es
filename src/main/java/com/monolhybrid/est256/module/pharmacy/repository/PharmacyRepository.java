package com.monolhybrid.est256.module.pharmacy.repository;

import com.monolhybrid.est256.module.pharmacy.dto.projection.PharmacyRequestProjection;
import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyRequest, Long> {

    @Query(value = """
            select
            	pr.id as pharmacyRequestId,
            	pr.request_date as requestDate,
            	pr.request_by as requestBy,
            	pr.unit_from as unitFrom,
            	pr.status,
            	pr.created_at as createdAtPharmacyRequest,
            	pr.updated_at as updatedAtPharmacyRequest,
            	pri.id as pharmacyRequestItemId,
            	pri.pharmacy_request_id  as pharmacyRequestIdItem,
            	pri.batch_id as batchId,
            	pri.quantity,
            	pri.created_at as createdAtPharmacyRequestItem,
            	pri.updated_at as updatedAtPharmacyRequestItem
            from
            	pharmacy_request_item pri
            inner join pharmacy_request pr on
            	pri.pharmacy_request_id = pr.id
            where pr.status = 'PENDING'
            """, nativeQuery = true)
    List<PharmacyRequestProjection> getAllPharmacyRequestStatusPending();

}
