package ru.gbjava.kinozen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KinozenApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinozenApplication.class, args);
    }

}
