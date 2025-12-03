package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.repositories;


import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Entity;

import java.util.*;

public class RepositoryImpl<ID, T extends Entity<ID>> implements Repository<ID, T> {
    private final Map<ID, T> items = new HashMap<>();

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public T save(T item) {
        if (item.getId() == null) {
            throw new IllegalArgumentException("El id no puede ser nulo.");
        }

        return items.put(item.getId(), item);
    }

    @Override
    public void delete(T item) {
        if (!items.containsKey(item.getId())) {
            throw new NoSuchElementException(String.format("No se encuentra el elemento con id %s.\n", item.getId()));
        }

        items.remove(item.getId());
    }
}
