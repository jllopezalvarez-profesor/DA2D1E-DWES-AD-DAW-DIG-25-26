package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDto {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private Set<AppRoleDto> roles = new HashSet<>();

}
