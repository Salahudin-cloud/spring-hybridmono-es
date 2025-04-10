package com.monolhybrid.est256.module.warehouse.repository;

import com.monolhybrid.est256.module.warehouse.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    Optional<Batch>findByProductionCode(String productionCode);
}
