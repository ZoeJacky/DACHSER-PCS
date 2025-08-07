package com.DACHSER.pcs_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {
    private Long id;
    private BigDecimal amount;
    private String source;
    private LocalDateTime date;
}
