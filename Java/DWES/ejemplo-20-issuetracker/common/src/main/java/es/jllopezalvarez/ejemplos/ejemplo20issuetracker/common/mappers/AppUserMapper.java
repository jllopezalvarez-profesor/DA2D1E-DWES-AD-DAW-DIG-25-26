package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.AppRoleDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.AppUserDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppRole;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AppRoleMapper.class})
public interface AppUserMapper {
    AppUserDto map(AppUser user);
}
