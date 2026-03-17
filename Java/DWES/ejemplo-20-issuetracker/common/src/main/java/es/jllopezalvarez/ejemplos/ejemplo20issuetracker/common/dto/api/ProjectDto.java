package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long projectId;
    private String name;
    private String description;
    private Set<AppUserDto> members = new HashSet<>();
}
