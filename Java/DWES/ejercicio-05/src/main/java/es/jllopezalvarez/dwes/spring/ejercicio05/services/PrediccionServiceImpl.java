package es.jllopezalvarez.dwes.spring.ejercicio05.services;

import es.jllopezalvarez.dwes.spring.ejercicio05.entities.Prediccion;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrediccionServiceImpl implements PrediccionService {
    private final Faker faker;

    public PrediccionServiceImpl(Faker faker) {
        this.faker = faker;
    }

    @Override
    public List<Prediccion> getPrediccionSemanal() {
        List<Prediccion> predicciones = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            predicciones.add(crearPrediccionAleatoria(LocalDate.now().plusDays(i)));
        }
        return predicciones;
    }

    private Prediccion crearPrediccionAleatoria(LocalDate fecha) {
        return Prediccion.builder()
                .fecha(fecha)
                .probabilidadPrecipitacion(faker.random().nextInt(100+1))
                .temperaturaMinima(faker.random().nextInt(10+1))
                .temperaturaMaxima(faker.random().nextInt(10, 20+1))
                .nivelAlertaUv(faker.random().nextInt(5+1))
                .build();

    }
}
