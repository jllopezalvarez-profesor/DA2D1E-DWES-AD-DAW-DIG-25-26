package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.repositories;

import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User findUserByEmail(String email) {
        System.out.println("findUserByEmail de UserRepository");
        return User.builder()
                .email(email)
                .firstName("Nombre")
                .lastName("Apellido")
                .birthDate(LocalDate.now())
                .build();
        //return new User(email, "Nombre", "Apellido", LocalDate.now());

    }
}
