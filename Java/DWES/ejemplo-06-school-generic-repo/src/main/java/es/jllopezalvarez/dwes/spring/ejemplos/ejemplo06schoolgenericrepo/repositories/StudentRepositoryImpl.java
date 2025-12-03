package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.repositories;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl extends RepositoryImpl<Long, Student> implements StudentRepository {
}
