package ru.gbjava.configzen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@SpringBootApplication
@EnableConfigServer
public class ConfigServerKinozen {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerKinozen.class, args);
    }

}
