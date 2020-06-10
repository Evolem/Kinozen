package ru.gbjava.kinozen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import ru.gbjava.kinozen.services.storage.StorageService;

@EnableFeignClients
@SpringBootApplication
//@EnableConfigurationProperties(StorageProperties.class)
public class KinozenApplication {

	public static void main(String[] args) {
		SpringApplication.run(KinozenApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
