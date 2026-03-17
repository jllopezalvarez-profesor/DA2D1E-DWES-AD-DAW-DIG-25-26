package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.AppRoleDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppRoleMapper {
    AppRoleDto map (AppRole role);
}
