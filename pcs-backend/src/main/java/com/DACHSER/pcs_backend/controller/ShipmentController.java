package com.DACHSER.pcs_backend.controller;

import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.dto.ShipmentWithProfitDto;
import com.DACHSER.pcs_backend.service.ShipmentService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    private ShipmentService shipmentService;

    @PostConstruct
    public void initShipment() {
        shipmentService.initShipment();
    }

    @PostMapping()
    @PreAuthorize("hasRole('finance-dep')")
    public ResponseEntity<ShipmentDto> createShipment(@RequestBody ShipmentDto shipmentDto) {
        ShipmentDto savedShipment = shipmentService.createShipment(shipmentDto);
        return new ResponseEntity<>(savedShipment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('finance-dep')")
    public ResponseEntity<ShipmentDto> getShipmentById(@PathVariable("id") Long shipmentId) {
        ShipmentDto shipmentDto = shipmentService.getShipmentById(shipmentId);
        return ResponseEntity.ok(shipmentDto);
    }

    @GetMapping()
    @PreAuthorize("hasRole('finance-dep')")
    public ResponseEntity<List<ShipmentWithProfitDto>> getAllShipments() {
        List<ShipmentWithProfitDto> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('finance-dep')")
    public ResponseEntity<ShipmentDto> updateShipment(@PathVariable("id") Long shipmentId,
                                                      @RequestBody ShipmentDto updatedShipmentDto){
        ShipmentDto shipmentDto = shipmentService.updateShipment(shipmentId, updatedShipmentDto);
        return ResponseEntity.ok(shipmentDto);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('finance-dep')")
    public ResponseEntity<String> deleteShipment(@PathVariable("id") Long shipmentId){
        shipmentService.deleteShipment(shipmentId);
        return ResponseEntity.ok("Shipment:"+shipmentId+" has been deleted");
    }
}
