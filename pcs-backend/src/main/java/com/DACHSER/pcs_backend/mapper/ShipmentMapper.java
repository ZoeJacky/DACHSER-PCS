package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.CostDto;
import com.DACHSER.pcs_backend.dto.IncomeDto;
import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.entity.Cost;
import com.DACHSER.pcs_backend.entity.Income;
import com.DACHSER.pcs_backend.entity.Shipment;

import java.math.BigDecimal;
import java.util.List;

public class ShipmentMapper {
    public static ShipmentDto mapToShipmentDto(Shipment shipment) {
        List<IncomeDto> incomeDto = shipment.getIncomes().stream().map(IncomeMapper::mapToIncomeDto).toList();
        List<CostDto> costDto = shipment.getCosts().stream().map(CostMapper::mapToCostDto).toList();

        BigDecimal totalIncome = new BigDecimal(0);
        for (Income income:shipment.getIncomes()){
            totalIncome.add(income.getAmount());
        }
        BigDecimal totalCost = new BigDecimal(0);
        for (Cost cost:shipment.getCosts()){
            totalCost.add(cost.getAmount());
        }

        return new ShipmentDto(
                shipment.getId(),
                shipment.getReference(),
                shipment.getDescription(),
                shipment.getCreatedDate(),
                incomeDto,
                costDto,
                totalIncome.subtract(totalCost)
        );
    }
}
