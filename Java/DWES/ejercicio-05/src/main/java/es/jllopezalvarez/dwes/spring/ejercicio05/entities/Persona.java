package es.jllopezalvarez.dwes.spring.ejercicio05.entities;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona {
    @EqualsAndHashCode.Include
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;

    public int getEdad(){
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (!(obj instanceof Persona other)) {
//            return false;
//        }
//        return this.dni.equals(other.dni);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(dni);
//    }


}
