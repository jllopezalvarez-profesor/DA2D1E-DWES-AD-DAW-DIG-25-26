package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.dto.NumericSimpleValueDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services
        .IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {
    private final IssueService issueService;

    public TestController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Conectado correctamente");
    }

    @GetMapping("/count-derivada/{userId}")
    public ResponseEntity<NumericSimpleValueDto> testCountDerivada(@PathVariable Long userId) {

        long result = issueService.countIssuesByAssigneeUserIdDerived(userId);
        return ResponseEntity.ok(NumericSimpleValueDto.builder().value(result).build());

    }

    @GetMapping("/count-sql/{userId}")
    public ResponseEntity<NumericSimpleValueDto> testCountSql(@PathVariable Long userId) {

        long result = issueService.countIssuesByAssigneeUserIdSql(userId);
        return ResponseEntity.ok(NumericSimpleValueDto.builder().value(result).build());

    }

    @GetMapping("/count-jpql/{userId}")
    public ResponseEntity<NumericSimpleValueDto> testCountJpql(@PathVariable Long userId) {

        long result = issueService.countIssuesByAssigneeUserIdJpql(userId);
        return ResponseEntity.ok(NumericSimpleValueDto.builder().value(result).build());

    }

}
