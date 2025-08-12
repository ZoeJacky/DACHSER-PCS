package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.entity.Shipment;
import com.DACHSER.pcs_backend.entity.Income;
import com.DACHSER.pcs_backend.entity.Cost;
import com.DACHSER.pcs_backend.service.ProfitCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProfitCalculationServiceImpl implements ProfitCalculationService {

    public BigDecimal calculateTotalIncome(Shipment shipment) {
        return shipment.getIncomes().stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalCost(Shipment shipment) {
        return shipment.getCosts().stream()
                .map(Cost::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateProfit(Shipment shipment) {
        BigDecimal totalIncome = calculateTotalIncome(shipment);
        BigDecimal totalCost = calculateTotalCost(shipment);
        return totalIncome.subtract(totalCost);
    }
}
