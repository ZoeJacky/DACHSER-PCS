package com.DACHSER.pcs_backend.service;

import com.DACHSER.pcs_backend.dto.CostDto;

import java.util.List;

public interface CostService {

    CostDto createCost(CostDto costDto);

    CostDto getCostById(Long id);

    List<CostDto> getAllCosts();

    CostDto updateCost(Long costId, CostDto updatedCostDto);

    void deleteCost(Long costId);
}
