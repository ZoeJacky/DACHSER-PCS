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
public class ShipmentWithProfitDto {

    private Long id;
    private String reference;
    private String description;
    private LocalDateTime createdDate;
    private BigDecimal totalIncome;
    private BigDecimal totalCost;
    private BigDecimal profit;

    public ShipmentWithProfitDto(Long id, String reference, String description, LocalDateTime createdDate,
                                 Number totalIncome, Number totalCost) {
        this.id = id;
        this.reference = reference;
        this.description = description;
        this.createdDate = createdDate;
        this.totalIncome = totalIncome == null ? BigDecimal.ZERO : new BigDecimal(totalIncome.toString());
        this.totalCost = totalCost == null ? BigDecimal.ZERO : new BigDecimal(totalCost.toString());
        this.profit = this.totalIncome.subtract(this.totalCost);
    }
}
