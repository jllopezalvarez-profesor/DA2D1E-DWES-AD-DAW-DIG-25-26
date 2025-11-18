package es.jllopezalvarez.dwes.spring.ejemplo03primerspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        System.out.println("Hola desde el main. Esto no es lo más correcto, porque Spring no está realmente inicializado.");


    }

}
