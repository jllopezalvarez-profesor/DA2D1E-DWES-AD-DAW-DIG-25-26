package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api",
                "es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common",
                "es.jllopezalvarez.ejemplos.ejemplo20issuetracker.security"
        })
@EntityScan(basePackages = {"es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common"})
@EnableJpaRepositories(basePackages = {"es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common"})
public class ApiApp {

    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class, args);
    }

}
