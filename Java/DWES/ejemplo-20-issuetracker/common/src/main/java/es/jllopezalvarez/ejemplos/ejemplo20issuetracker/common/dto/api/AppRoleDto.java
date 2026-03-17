package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppRoleDto {
    private String name;
    private String description;
}
