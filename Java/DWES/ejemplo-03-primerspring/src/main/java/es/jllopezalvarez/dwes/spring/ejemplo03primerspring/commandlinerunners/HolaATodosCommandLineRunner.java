package es.jllopezalvarez.dwes.spring.ejemplo03primerspring.commandlinerunners;

import es.jllopezalvarez.dwes.spring.ejemplo03primerspring.services.TestService;
import es.jllopezalvarez.dwes.spring.ejemplo03primerspring.services.TestServiceImpl;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HolaATodosCommandLineRunner implements CommandLineRunner {

    private final TestService testService;
    private final Faker faker;

    public HolaATodosCommandLineRunner(@Qualifier("nombreAlternativo") TestService testService, Faker faker) {
        this.testService = testService;
        this.faker = faker;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola a todos");

        testService.hacerAlgo();

        System.out.printf("%s\n", faker.stargate().quotes());


    }
}
