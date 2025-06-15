package com.gostech.swiftportbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SwiftportBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiftportBackendApplication.class, args);
    }
}
