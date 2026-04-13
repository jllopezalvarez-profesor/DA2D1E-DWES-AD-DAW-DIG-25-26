package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.IssueDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    IssueDto map(Issue issue);
    List<IssueDto> map(List<Issue> issues);

}
