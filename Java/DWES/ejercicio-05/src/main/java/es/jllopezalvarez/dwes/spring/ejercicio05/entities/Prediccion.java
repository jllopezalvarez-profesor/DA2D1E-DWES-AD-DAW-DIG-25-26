package es.jllopezalvarez.dwes.spring.ejercicio05.entities;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prediccion {
    private LocalDate fecha;
    private int probabilidadPrecipitacion;
    private int temperaturaMinima;
    private int temperaturaMaxima;
    private int nivelAlertaUv;

}
