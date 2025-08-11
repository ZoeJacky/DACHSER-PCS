package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.entity.*;
import com.DACHSER.pcs_backend.exception.ResourceNotFoundException;
import com.DACHSER.pcs_backend.mapper.ShipmentMapper;
import com.DACHSER.pcs_backend.repository.CostRepository;
import com.DACHSER.pcs_backend.repository.IncomeRepository;
import com.DACHSER.pcs_backend.repository.ShipmentRepository;
import com.DACHSER.pcs_backend.service.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private ShipmentRepository shipmentRepository;
    private IncomeRepository incomeRepository;
    private CostRepository costRepository;


    @Override
    public void initShipment() {
        Income income = new Income();
        income.setAmount(new BigDecimal("12000"));
        income.setSource("Client Order #12345 from Electronics Co");
        income.setDate(LocalDateTime.now());

        Cost cost1 = new Cost();
        cost1.setAmount(new BigDecimal("4500"));
        cost1.setCategory("Shipping Fees (Ocean Freight, Port Charges)");
        cost1.setDate(LocalDateTime.now());

        Cost cost2 = new Cost();
        cost2.setAmount(new BigDecimal("800"));
        cost2.setCategory("Handling and Warehouse Storage");
        cost2.setDate(LocalDateTime.now());

        Shipment shipment = new Shipment();
        shipment.setReference("SHP-2025-001");
        shipment.setDescription("Delivery of electronic components from Shanghai to New York");
        Set<Cost> costs = new HashSet<>();
        costs.add(cost1);
        costs.add(cost2);
        Set<Income> incomes = new HashSet<>();
        incomes.add(income);
        shipment.setIncomes(incomes);
        shipment.setCosts(costs);
        shipment.setCreatedDate(LocalDateTime.now());

        income.setShipment(shipment);
        cost1.setShipment(shipment);
        cost2.setShipment(shipment);
        shipmentRepository.save(shipment);
        incomeRepository.save(income);
        costRepository.save(cost1);
        costRepository.save(cost2);
    }

    @Override
    public ShipmentDto createShipment(ShipmentDto shipmentDto) {
        Shipment shipment = ShipmentMapper.mapToShipment(shipmentDto);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return ShipmentMapper.mapToShipmentDto(savedShipment);
    }

    @Override
    public ShipmentDto getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Shipment not found with given id:"+id));
        return ShipmentMapper.mapToShipmentDto(shipment);
    }

    @Override
    public List<ShipmentDto> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        List<ShipmentDto> shipmentDtos =  new ArrayList<>();

        for(Shipment shipment : shipments){
            BigDecimal totalIncome = BigDecimal.ZERO;
            BigDecimal totalCost = BigDecimal.ZERO;
            for(Income income : shipment.getIncomes()){
                totalIncome = totalIncome.add(income.getAmount());
            }
            for(Cost cost : shipment.getCosts()){
                totalCost = totalCost.add(cost.getAmount());
            }
            BigDecimal profit = totalIncome.subtract(totalCost);
            ShipmentDto shipmentDto = ShipmentMapper.mapToShipmentDto(shipment);
            shipmentDto.setProfit(profit);
            shipmentDtos.add(shipmentDto);
        }
        return shipmentDtos;

//        return shipments.stream().map((shipment) -> ShipmentMapper.mapToShipmentDto(shipment))
//                .collect(Collectors.toList());
    }

    @Override
    public ShipmentDto updateShipment(Long shipmentId, ShipmentDto updatedShipmentDto) {
        Shipment shipment = shipmentRepository.findById(shipmentId).
                orElseThrow(()->new ResourceNotFoundException("Shipment not found with given id:"+shipmentId));
        shipment.setReference(updatedShipmentDto.getReference());
        shipment.setDescription(updatedShipmentDto.getDescription());
        Shipment savedShipment = shipmentRepository.save(shipment);
        return ShipmentMapper.mapToShipmentDto(savedShipment);
    }

    @Override
    public void deleteShipment(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).
                orElseThrow(()->new ResourceNotFoundException("Shipment not found with given id:"+shipmentId));
        shipmentRepository.deleteById(shipmentId);
    }
}
