package com.utimgapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.utimgapi.cons","com.utimgapi.service","com.utimgapi.mapper"})
public class UtImgApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtImgApiApplication.class, args);
    }

}
