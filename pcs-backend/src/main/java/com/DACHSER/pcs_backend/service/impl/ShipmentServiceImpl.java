package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.config.ShipmentConfig;
import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.dto.ShipmentWithProfitDto;
import com.DACHSER.pcs_backend.entity.*;
import com.DACHSER.pcs_backend.exception.ResourceNotFoundException;
import com.DACHSER.pcs_backend.mapper.ShipmentMapper;
import com.DACHSER.pcs_backend.repository.CostRepository;
import com.DACHSER.pcs_backend.repository.IncomeRepository;
import com.DACHSER.pcs_backend.repository.ShipmentRepository;
import com.DACHSER.pcs_backend.service.ProfitCalculationService;
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
    private ProfitCalculationService profitCalculationService;
    private ShipmentConfig shipmentConfig;


    @Override
    public void initShipment() {
        Set<Income> incomes = new HashSet<>();
        for (ShipmentConfig.Income incomeConfig : shipmentConfig.getIncomes()) {
            Income income = new Income();
            income.setAmount(incomeConfig.getAmount());
            income.setSource(incomeConfig.getSource());
            income.setDate(LocalDateTime.now());

            incomes.add(income);
        }

        Set<Cost> costs = new HashSet<>();
        for (ShipmentConfig.Cost costConfig : shipmentConfig.getCosts()) {
            Cost cost = new Cost();
            cost.setAmount(costConfig.getAmount());
            cost.setCategory(costConfig.getCategory());
            cost.setDate(LocalDateTime.now());

            costs.add(cost);
        }

        Shipment shipment = new Shipment();
        shipment.setReference(shipmentConfig.getReference());
        shipment.setDescription(shipmentConfig.getDescription());
        shipment.setIncomes(incomes);
        shipment.setCosts(costs);
        shipment.setCreatedDate(LocalDateTime.now());

        incomes.forEach(income -> income.setShipment(shipment));
        costs.forEach(cost -> cost.setShipment(shipment));

        shipmentRepository.save(shipment);
        incomeRepository.saveAll(incomes);
        costRepository.saveAll(costs);
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
    public List<ShipmentWithProfitDto> getAllShipments() {
        return shipmentRepository.getShipmentsWithTotalIncomeAndCost();
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
