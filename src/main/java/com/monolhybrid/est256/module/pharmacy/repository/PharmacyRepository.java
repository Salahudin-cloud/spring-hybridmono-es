package com.monolhybrid.est256.module.pharmacy.repository;

import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyRequest, Long> {
}
