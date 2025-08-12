package com.DACHSER.pcs_backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "shipment")
public class ShipmentConfig {
    private String reference;
    private String description;
    private List<Income> incomes;
    private List<Cost> costs;

    @Getter
    @Setter
    public static class Income {
        private BigDecimal amount;
        private String source;
    }

    @Getter
    @Setter
    public static class Cost {
        private BigDecimal amount;
        private String category;
    }
}
