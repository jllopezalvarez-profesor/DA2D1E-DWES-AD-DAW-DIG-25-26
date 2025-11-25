package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services;

import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomServiceImpl implements RandomService {

    private final Faker faker;

    public RandomServiceImpl(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Collection<String> getRandomNames(int count) {
        return IntStream.range(0, count).mapToObj(i -> faker.name().fullName()).collect(Collectors.toList());
    }
}
