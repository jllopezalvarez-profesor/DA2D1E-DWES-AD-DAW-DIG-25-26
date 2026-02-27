package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.AppUser;

public interface AppUserService {
    AppUser save(AppUser user);
}
