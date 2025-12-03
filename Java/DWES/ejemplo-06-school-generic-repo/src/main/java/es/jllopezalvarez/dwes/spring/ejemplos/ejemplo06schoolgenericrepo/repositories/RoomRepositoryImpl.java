package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.repositories;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Room;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImpl extends RepositoryImpl<String, Room> implements RoomRepository {
}
