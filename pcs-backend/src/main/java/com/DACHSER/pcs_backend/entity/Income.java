package com.DACHSER.pcs_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incomes", indexes = {@Index(name = "idx_income_shipment_id", columnList = "shipment_id"), @Index(name = "idx_income_date", columnList = "date")})
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String source;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
