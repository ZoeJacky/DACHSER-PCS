package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.dto.CostDto;
import com.DACHSER.pcs_backend.dto.IncomeDto;
import com.DACHSER.pcs_backend.entity.Cost;
import com.DACHSER.pcs_backend.entity.Income;
import com.DACHSER.pcs_backend.entity.Shipment;
import com.DACHSER.pcs_backend.exception.ResourceNotFoundException;
import com.DACHSER.pcs_backend.mapper.CostMapper;
import com.DACHSER.pcs_backend.mapper.IncomeMapper;
import com.DACHSER.pcs_backend.repository.IncomeRepository;
import com.DACHSER.pcs_backend.repository.ShipmentRepository;
import com.DACHSER.pcs_backend.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private IncomeRepository incomeRepository;
    private ShipmentRepository shipmentRepository;

    @Override
    public IncomeDto createIncome(IncomeDto incomeDto) {
        Income income = IncomeMapper.mapToIncome(incomeDto);
        Shipment shipment = shipmentRepository.findById(incomeDto.getShipmentId()).
                orElseThrow(()-> new ResourceNotFoundException("Shipment not found with given id:"+incomeDto.getShipmentId()));
        income.setShipment(shipment);
        Income savedIncome = incomeRepository.save(income);
        return IncomeMapper.mapToIncomeDto(savedIncome);
    }

    @Override
    public IncomeDto getIncomeById(Long id) {
        Income income = incomeRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Income not found with given id:"+id));
        return IncomeMapper.mapToIncomeDto(income);
    }

    @Override
    public List<IncomeDto> getAllIncomes() {
        List<Income> incomes = incomeRepository.findAll();
        List<IncomeDto> incomeDtos =  new ArrayList<>();
        for(Income income : incomes){
            IncomeDto incomeDto = IncomeMapper.mapToIncomeDto(income);
            incomeDto.setShipmentId(income.getShipment().getId());
            incomeDtos.add(incomeDto);
        }
        return incomeDtos;
//        return incomes.stream().map(IncomeMapper::mapToIncomeDto)
//               .collect(Collectors.toList());
    }

    @Override
    public IncomeDto updateIncome(Long incomeId, IncomeDto updatedIncomeDto) {
        Income income = incomeRepository.findById(incomeId).
                orElseThrow(()->new ResourceNotFoundException("Income not found with given id:"+incomeId));
        income.setAmount(updatedIncomeDto.getAmount());
        income.setSource(updatedIncomeDto.getSource());
        income.setDate(updatedIncomeDto.getDate());
        Income savedIncome = incomeRepository.save(income);
        return IncomeMapper.mapToIncomeDto(savedIncome);
    }

    @Override
    public void deleteIncome(Long incomeId) {
        Income income = incomeRepository.findById(incomeId).
                orElseThrow(()->new ResourceNotFoundException("Income not found with given id:"+incomeId));
        incomeRepository.deleteById(incomeId);
    }
}
