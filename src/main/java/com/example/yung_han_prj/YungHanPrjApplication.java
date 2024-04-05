package com.example.yung_han_prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YungHanPrjApplication {

    public static void main(String[] args) {
        SpringApplication.run(YungHanPrjApplication.class, args);
    }

}
