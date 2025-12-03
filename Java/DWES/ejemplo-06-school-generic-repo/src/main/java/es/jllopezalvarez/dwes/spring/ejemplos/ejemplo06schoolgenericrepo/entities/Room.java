package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Room implements Entity<String>{
    private String id;
    private String name;
}
