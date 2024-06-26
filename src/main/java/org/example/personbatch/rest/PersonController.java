package org.example.personbatch.rest;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.personbatch.entity.PersonEntity;
import org.example.personbatch.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonController {

  PersonService personService;

  @GetMapping("/persons")
  public ResponseEntity<List<PersonEntity>> getAll() {
    List<PersonEntity> persons = personService.findAll();
    return ResponseEntity.ok(persons);
  }

}
