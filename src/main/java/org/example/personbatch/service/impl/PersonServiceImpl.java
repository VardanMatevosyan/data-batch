package org.example.personbatch.service.impl;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.personbatch.entity.PersonEntity;
import org.example.personbatch.repository.PersonEntityRepository;
import org.example.personbatch.service.PersonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonServiceImpl implements PersonService {

  PersonEntityRepository personEntityRepository;

  @Override
  public List<PersonEntity> findAll() {
    return personEntityRepository.findAll();
  }

}
