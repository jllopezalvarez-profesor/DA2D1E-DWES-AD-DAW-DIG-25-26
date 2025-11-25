package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services;

import java.util.Collection;

public interface RandomService {
    Collection<String> getRandomNames(int count);
}
