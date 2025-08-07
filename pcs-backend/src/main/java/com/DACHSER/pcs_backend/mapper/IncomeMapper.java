package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.IncomeDto;
import com.DACHSER.pcs_backend.entity.Income;
import com.DACHSER.pcs_backend.entity.Shipment;

public class IncomeMapper {
    public static IncomeDto mapToIncomeDto(Income income) {
        return new IncomeDto(
                income.getId(),
                income.getAmount(),
                income.getSource(),
                income.getDate()
        );
    }

    public static Income maptoIncome(IncomeDto incomeDto, Shipment shipment){
        return new Income(
                incomeDto.getId(),
                incomeDto.getAmount(),
                incomeDto.getSource(),
                incomeDto.getDate(),
                shipment
        );
    }


}
