package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.dto.CostDto;
import com.DACHSER.pcs_backend.dto.ShipmentDto;
import com.DACHSER.pcs_backend.entity.Cost;
import com.DACHSER.pcs_backend.entity.Shipment;
import com.DACHSER.pcs_backend.exception.ResourceNotFoundException;
import com.DACHSER.pcs_backend.mapper.CostMapper;
import com.DACHSER.pcs_backend.repository.CostRepository;
import com.DACHSER.pcs_backend.repository.ShipmentRepository;
import com.DACHSER.pcs_backend.service.CostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CostServiceImpl implements CostService {
    private CostRepository costRepository;
    private ShipmentRepository shipmentRepository;

    @Override
    public CostDto createCost(CostDto costDto) {
        Cost cost = CostMapper.mapToCost(costDto);
        Shipment shipment = shipmentRepository.findById(costDto.getShipmentId()).
                orElseThrow(()-> new ResourceNotFoundException("Shipment not found with given id:"+costDto.getShipmentId()));
        cost.setShipment(shipment);
        Cost savedCost = costRepository.save(cost);
        return CostMapper.mapToCostDto(savedCost);
    }

    @Override
    public CostDto getCostById(Long id) {
        Cost cost = costRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Cost not found with given id:"+id));
        return CostMapper.mapToCostDto(cost);
    }

    @Override
    public List<CostDto> getAllCosts() {
        List<Cost> costs = costRepository.findAll();
        List<CostDto> costDtos =  new ArrayList<>();
        for(Cost cost : costs){
            CostDto costDto = CostMapper.mapToCostDto(cost);
            costDto.setShipmentId(cost.getShipment().getId());
            costDtos.add(costDto);
        }
        return costDtos;
//        return costs.stream().map(CostMapper::mapToCostDto)
//               .collect(Collectors.toList());
    }

    @Override
    public CostDto updateCost(Long costId, CostDto updatedCostDto) {
        Cost cost = costRepository.findById(costId).
                orElseThrow(()->new ResourceNotFoundException("Cost not found with given id:"+costId));
        cost.setAmount(updatedCostDto.getAmount());
        cost.setCategory(updatedCostDto.getCategory());
        cost.setDate(updatedCostDto.getDate());
        Cost savedCost = costRepository.save(cost);
        return CostMapper.mapToCostDto(savedCost);
    }

    @Override
    public void deleteCost(Long costId) {
        Cost cost = costRepository.findById(costId).
                orElseThrow(()->new ResourceNotFoundException("Cost not found with given id:"+costId));
        costRepository.deleteById(costId);
    }
}
