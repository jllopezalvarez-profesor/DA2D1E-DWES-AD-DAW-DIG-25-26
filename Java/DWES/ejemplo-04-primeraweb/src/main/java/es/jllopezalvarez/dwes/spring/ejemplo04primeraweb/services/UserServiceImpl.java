package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services;

import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities.User;
import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.repositories.UserRepository;
import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.repositories.UserRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        System.out.println("findUserByEmail de UserService");
        return userRepository.findUserByEmail(email);
    }
}
