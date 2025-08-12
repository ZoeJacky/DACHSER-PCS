package com.DACHSER.pcs_backend;

import com.DACHSER.pcs_backend.config.ShipmentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ShipmentConfig.class)
public class PcsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcsBackendApplication.class, args);
	}

}
