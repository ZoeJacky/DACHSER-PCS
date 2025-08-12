package com.DACHSER.pcs_backend.service;

import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.dto.ShipmentWithProfitDto;

import java.util.List;

public interface ShipmentService {
    void initShipment();

    ShipmentDto createShipment(ShipmentDto shipmentDto);

    ShipmentDto getShipmentById(Long id);

    List<ShipmentWithProfitDto> getAllShipments();

    ShipmentDto updateShipment(Long shipmentId, ShipmentDto updatedShipmentDto);

    void deleteShipment(Long shipmentId);
}
