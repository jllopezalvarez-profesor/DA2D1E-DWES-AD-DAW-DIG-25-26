package es.jllopezalvarez.dwes.spring.ejemplo03primerspring.services;


import org.springframework.stereotype.Component;

@Component("nombreAlternativo")
public class TestServiceImplAlt implements TestService {
    private int contadorLlamadas = 0;
    @Override
    public void hacerAlgo() {
        contadorLlamadas++;
        System.out.printf("Soy la clase TestServiceImpl y estoy en la llamada número %d a hacerAlgo().\n",  contadorLlamadas);
    }
}
