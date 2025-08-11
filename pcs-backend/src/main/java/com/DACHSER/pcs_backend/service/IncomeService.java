package com.DACHSER.pcs_backend.service;

import com.DACHSER.pcs_backend.dto.IncomeDto;

import java.util.List;

public interface IncomeService {

    IncomeDto createIncome(IncomeDto incomeDto);

    IncomeDto getIncomeById(Long id);

    List<IncomeDto> getAllIncomes();

    IncomeDto updateIncome(Long incomeId, IncomeDto updatedIncomeDto);

    void deleteIncome(Long incomeId);
}
