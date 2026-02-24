package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleFormDto {
    private String dni;
    private String firstName;
    private String lastName;

}
