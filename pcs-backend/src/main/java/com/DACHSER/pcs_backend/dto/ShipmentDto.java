package com.DACHSER.pcs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDto {

    private Long id;
    private String reference;
    private String description;
    private LocalDateTime createdDate;
    private List<IncomeDto> incomes;
    private List<CostDto> costs;

    // Profit calculated dynamically in the service layer
    private BigDecimal profit;
}
