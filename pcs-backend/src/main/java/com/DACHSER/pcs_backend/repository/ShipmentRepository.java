package com.DACHSER.pcs_backend.repository;

import com.DACHSER.pcs_backend.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
