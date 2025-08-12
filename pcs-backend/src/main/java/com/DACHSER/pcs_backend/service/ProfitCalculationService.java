package com.DACHSER.pcs_backend.service;

import com.DACHSER.pcs_backend.entity.Shipment;

import java.math.BigDecimal;

public interface ProfitCalculationService {
    BigDecimal calculateTotalIncome(Shipment shipment);

    BigDecimal calculateTotalCost(Shipment shipment);

    BigDecimal calculateProfit(Shipment shipment);
}
