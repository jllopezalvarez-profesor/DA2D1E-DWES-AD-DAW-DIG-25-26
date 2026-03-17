package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.AppUserDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.ProjectDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AppUserMapper.class})
public interface ProjectMapper {
    ProjectDto map(Project project);
}
