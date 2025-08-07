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
public class CostDto {
    private Long id;
    private BigDecimal amount;
    private String category;
    private LocalDateTime date;
}
