package com.monolhybrid.est256.module.pharmacy.repository;

import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRequestItemRepository extends JpaRepository<PharmacyRequestItem, Long> {

    List<PharmacyRequestItem> findByPharmacyRequestId(Long id);

}
