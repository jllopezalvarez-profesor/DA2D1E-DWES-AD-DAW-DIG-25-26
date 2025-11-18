package es.jllopezalvarez.dwes.spring.ejemplo03primerspring.commandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdiosATodosCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Adios a todos");
    }
}
