package com.DACHSER.pcs_backend.repository;

import com.DACHSER.pcs_backend.dto.ShipmentWithProfitDto;
import com.DACHSER.pcs_backend.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    @Query("SELECT new com.DACHSER.pcs_backend.dto.ShipmentWithProfitDto(" +
        "s.id, s.reference, s.description, s.createdDate, " +
        "SUM(DISTINCT i.amount), SUM(DISTINCT c.amount)) " +
        "FROM Shipment s " +
        "LEFT JOIN s.incomes i " +
        "LEFT JOIN s.costs c " +
        "GROUP BY s.id")
    List<ShipmentWithProfitDto> getShipmentsWithTotalIncomeAndCost();

}
