package es.jllopezalvarez.dwes.spring.ejercicio05.services;

import es.jllopezalvarez.dwes.spring.ejercicio05.entities.Persona;

import java.util.Collection;

public interface PersonaService {
    Collection<Persona> find(int count);
    Persona findOne();
}
