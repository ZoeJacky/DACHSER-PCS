package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.CostDto;
import com.DACHSER.pcs_backend.entity.Cost;
import com.DACHSER.pcs_backend.entity.Shipment;

public class CostMapper {
    public static CostDto mapToCostDto(Cost cost) {
        return new CostDto(
                cost.getId(),
                cost.getAmount(),
                cost.getCategory(),
                cost.getDate()
        );
    }

    public static Cost maptoCost(CostDto costDto, Shipment shipment){
        return new Cost(
                costDto.getId(),
                costDto.getAmount(),
                costDto.getCategory(),
                costDto.getDate(),
                shipment
        );
    }
}
