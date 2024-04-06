package com.example.lee_dong_uk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LeeDongUkPrjApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeeDongUkPrjApplication.class, args);
    }

}
