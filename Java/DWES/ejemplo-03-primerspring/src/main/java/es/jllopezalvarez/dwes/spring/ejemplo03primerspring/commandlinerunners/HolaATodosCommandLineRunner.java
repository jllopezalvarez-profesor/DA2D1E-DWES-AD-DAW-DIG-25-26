package es.jllopezalvarez.dwes.spring.ejemplo03primerspring.commandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HolaATodosCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola a todos");
    }
}
