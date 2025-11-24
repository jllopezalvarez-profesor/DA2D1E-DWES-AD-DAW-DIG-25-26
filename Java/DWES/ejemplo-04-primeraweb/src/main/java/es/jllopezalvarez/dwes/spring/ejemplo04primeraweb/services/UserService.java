package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services;

import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities.User;

public interface UserService {
    User findUserByEmail(String email);
}
