package com.birbuket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.birbuket.entity")
@EnableJpaRepositories("com.birbuket.repository")
public class BirBuketApplication {

    public static void main(String[] args) {
        SpringApplication.run(BirBuketApplication.class, args);
    }

}
