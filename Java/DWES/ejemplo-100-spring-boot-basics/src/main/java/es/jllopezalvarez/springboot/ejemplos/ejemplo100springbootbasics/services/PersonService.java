package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.services;


import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto.validation.NewPersonFormDto;

public interface PersonService {
    void create(NewPersonFormDto dto);
}
