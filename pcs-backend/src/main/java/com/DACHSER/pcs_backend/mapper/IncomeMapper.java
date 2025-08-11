package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.IncomeDto;
import com.DACHSER.pcs_backend.entity.Income;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

    // Map Income entity to IncomeDto
    public static IncomeDto mapToIncomeDto(Income income) {
        return new IncomeDto(
                income.getId(),
                income.getAmount(),
                income.getSource(),
                income.getDate(),
                null
        );
    }

    // Map IncomeDto to Income entity
    public static Income mapToIncome(IncomeDto incomeDto) {
        Income income = new Income();
        income.setId(incomeDto.getId());
        income.setAmount(incomeDto.getAmount());
        income.setSource(incomeDto.getSource());
        income.setDate(incomeDto.getDate());

        return income;
    }
}
