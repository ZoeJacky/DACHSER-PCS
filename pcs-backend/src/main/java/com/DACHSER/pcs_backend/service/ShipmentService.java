package com.DACHSER.pcs_backend.service;

import com.DACHSER.pcs_backend.dto.ShipmentDto;

import java.util.List;

public interface ShipmentService {
    void initShipment();

    ShipmentDto createShipment(ShipmentDto shipmentDto);

    ShipmentDto getShipmentById(Long id);

    List<ShipmentDto> getAllShipments();

    ShipmentDto updateShipment(Long shipmentId, ShipmentDto updatedShipmentDto);

    void deleteShipment(Long shipmentId);
}
