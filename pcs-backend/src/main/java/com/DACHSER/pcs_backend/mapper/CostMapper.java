package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.CostDto;
import com.DACHSER.pcs_backend.entity.Cost;
import org.springframework.stereotype.Component;

@Component
public class CostMapper {

    // Map Cost entity to CostDto
    public static CostDto mapToCostDto(Cost cost) {
        return new CostDto(
                cost.getId(),
                cost.getAmount(),
                cost.getCategory(),
                cost.getDate(),
                null
        );
    }

    // Map CostDto to Cost entity
    public static Cost mapToCost(CostDto costDto) {
        Cost cost = new Cost();
        cost.setId(costDto.getId());
        cost.setAmount(costDto.getAmount());
        cost.setCategory(costDto.getCategory());
        cost.setDate(costDto.getDate());

        return cost;
    }
}
