package com.monolhybrid.est256.module.warehouse.repository;

import com.monolhybrid.est256.module.warehouse.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
}
