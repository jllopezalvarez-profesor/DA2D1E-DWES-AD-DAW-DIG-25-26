package es.jllopezalvarez.dwes.spring.ejercicio05.services;

import es.jllopezalvarez.dwes.spring.ejercicio05.entities.Persona;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    private final Faker faker;

    public PersonaServiceImpl(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Collection<Persona> find(int count) {
        List<Persona> personas = new ArrayList<Persona>();
        for (int i = 0; i < count; i++) {
            personas.add(crearPersonaAleatoria());
        }

        return personas;
    }


    @Override
    public Persona findOne() {
        return null;
    }


    private Persona crearPersonaAleatoria() {

        Persona persona = new Persona();
        persona.setDni(faker.idNumber().valid());
        persona.setNombre(faker.name().firstName());
        persona.setApellidos(faker.name().lastName());
        persona.setFechaNacimiento(faker.timeAndDate().birthday(20, 50));
        return persona;
    }
}
