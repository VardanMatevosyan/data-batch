package org.example.personbatch.service;

import java.util.List;
import org.example.personbatch.entity.PersonEntity;

public interface PersonService {

  List<PersonEntity> findAll();

}
