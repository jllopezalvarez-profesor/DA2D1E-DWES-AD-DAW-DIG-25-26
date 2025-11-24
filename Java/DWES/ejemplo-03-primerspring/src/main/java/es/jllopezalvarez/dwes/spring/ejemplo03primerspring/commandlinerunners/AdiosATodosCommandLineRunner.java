package es.jllopezalvarez.dwes.spring.ejemplo03primerspring.commandlinerunners;

import es.jllopezalvarez.dwes.spring.ejemplo03primerspring.services.TestService;
import es.jllopezalvarez.dwes.spring.ejemplo03primerspring.services.TestServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdiosATodosCommandLineRunner implements CommandLineRunner {
    private final TestService testService;

    public AdiosATodosCommandLineRunner(TestService testService) {
        this.testService = testService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Adios a todos");

        testService.hacerAlgo();
    }
}
