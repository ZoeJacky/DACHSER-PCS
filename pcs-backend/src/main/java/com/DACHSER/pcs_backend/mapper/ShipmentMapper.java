package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.entity.Shipment;

import java.util.stream.Collectors;

public class ShipmentMapper {
    public static ShipmentDto mapToShipmentDto(Shipment shipment) {
        return new ShipmentDto(
                shipment.getId(),
                shipment.getReference(),
                shipment.getDescription(),
                shipment.getCreatedDate(),
                shipment.getIncomes().stream()
                        .map(IncomeMapper::mapToIncomeDto)
                        .collect(Collectors.toList()),
                shipment.getCosts().stream()
                        .map(CostMapper::mapToCostDto)
                        .collect(Collectors.toList()),
                null // Profit will be calculated in the service layer
        );
    }

    public static Shipment mapToShipment(ShipmentDto shipmentDto) {
        Shipment shipment = new Shipment();
        shipment.setId(shipmentDto.getId());
        shipment.setReference(shipmentDto.getReference());
        shipment.setDescription(shipmentDto.getDescription());
        shipment.setCreatedDate(shipmentDto.getCreatedDate());

        shipment.setIncomes(shipmentDto.getIncomes().stream()
                .map(IncomeMapper::mapToIncome)
                .collect(Collectors.toSet()));

        shipment.setCosts(shipmentDto.getCosts().stream()
                .map(CostMapper::mapToCost)
                .collect(Collectors.toSet()));

        return shipment;
    }
}
