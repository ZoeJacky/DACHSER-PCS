package com.DACHSER.pcs_backend.repository;

import com.DACHSER.pcs_backend.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
//    @Query("SELECT s.id AS shipmentId, s.reference AS shipmentReference, " +
//            "SUM(DISTINCT i.amount) AS totalIncome, SUM(DISTINCT c.amount) AS totalCost " +
//            "FROM Shipment s " +
//            "LEFT JOIN s.incomes i " +
//            "LEFT JOIN s.costs c " +
//            "GROUP BY s.id")
//    List<Object[]> getShipmentsWithTotalIncomeAndCost();
}
